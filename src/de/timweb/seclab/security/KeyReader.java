package de.timweb.seclab.security;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;

public class KeyReader {

	public static boolean checkKey(String key){
		try {
			if(key == null ||key.length() < 1)
				return false;
			
			FileImageInputStream fileIn = new FileImageInputStream(new File("licence.key"));
			
			int curser = 0;
			while(curser != -1){
				curser = fileIn.read();
				if(curser == ':'){
					for(int i=0;i<key.length();i++){
						char cKey = key.charAt(i);
						int cFile = fileIn.read();
						if(cFile == -1 || cKey != cFile)
							return false;
					}
					return true;
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	public static String getSubmitKey(){
		String result = "";
		try {
			FileImageInputStream fileIn = new FileImageInputStream(new File("licence.key"));
			
			int curser = 0;
			while(curser != -1){
				curser = fileIn.read();
				if(curser == '"'){
					curser = fileIn.read();
					while(true){
						if(curser == -1 || curser == '"')
							return result;
						result += (char)curser;
						curser = fileIn.read();
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static String generateSubmitKey(String name){
		String result = "";
		String obf_name = obfuscate(name);
		String length = name.length() > 10 ? ""+name.length() : "0"+name.length();
		
		
		String obf_rand = obfuscate(randomStr(12-((name.length()+2) % 4)));
		System.out.println("random: "+obf_rand);
		
		String buffer = obf_name+obf_rand+length;
		for(int i=0;i<buffer.length();i++) {
			result += buffer.charAt(i);
			if(i%4==3 && i != buffer.length()-1)
				result += "-";
		}
		
		
		return result;
	}
	
	private static String randomStr(int length) {
		String result = "";
		for(int i=0;i<length;i++){
			result += (char) (Math.random()*25+65);
		}
		return result;
	}

	private static String obfuscate(String name){
		String result = "";
		String clear = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";
		String obf =   "XleVmgDprkSP2Ku4_3nWQRH9i8sZF1EIjdbTM7qoLYGUcB5zyaC0w6vxhNAJOft";
		for(int i=0;i<name.length();i++){
			result += obf.charAt(clear.indexOf(name.charAt(i)));
		}
		return result;
	}
	
	public static void main(String[] args) {
//		System.out.println(checkKey("4M8K-15BM-16JN-2342"));
		System.out.println(generateSubmitKey("Richard Alpert"));
	}
}
