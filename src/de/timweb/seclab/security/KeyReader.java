package de.timweb.seclab.security;

import java.io.BufferedReader;
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
	
	public static void main(String[] args) {
		System.out.println(checkKey("4M8K-15BM-16JN-2342"));
		System.out.println(getSubmitKey());
	}
}
