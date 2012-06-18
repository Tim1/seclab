package de.timweb.seclab.security;

public class KeyGen {
	public static String generateSubmitKeyByName(String name){
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
		System.out.println(KeyReader.checkKey("4M8K-15BM-16JN-2342"));
		System.out.println(generateSubmitKeyByName("Tim Schmiedl"));
	}
}
