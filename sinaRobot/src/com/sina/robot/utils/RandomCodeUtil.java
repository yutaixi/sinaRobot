package com.sina.robot.utils;

 

public class RandomCodeUtil {

	//字母大小写，数字，特殊字符
	private static String[] charBase1={"a","b","c","d","e","f","g","h","i","g","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
		"A","B","C","D","E","F","G","H","I","G","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
		"~","@","#","$","%","^","&","*","(",")","_","+","|","`",".",""
		,"1","2","3","4","5","6","7","8","9","0"};
	//小写字母，数字，特殊字符
	private static String[] charBase2={"a","b","c","d","e","f","g","h","i","g","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
		"~","@","#","$","%","^","&","*","(",")","_","+","|","`",".",""
		,"1","2","3","4","5","6","7","8","9","0"};
	//小写字母，数字
	private static String[] charBase3={"a","b","c","d","e","f","g","h","i","g","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"
		,"1","2","3","4","5","6","7","8","9","0"};
	//字母大小写，数字，特殊字符
	private static String[] charBase4={"a","b","c","d","e","f","g","h","i","g","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
		"A","B","C","D","E","F","G","H","I","G","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"
		,"1","2","3","4","5","6","7","8","9","0"};
	//字母大小写
	private static String[] charBase={"a","b","c","d","e","f","g","h","i","g","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
		"A","B","C","D","E","F","G","H","I","G","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	//字母小写
	private static String[] charBase0={"a","b","c","d","e","f","g","h","i","g","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		
	//数字
	private static String[] numBase={"1","2","3","4","5","6","7","8","9","0"};
	
	
	
	public static String genRandomCode(int length){
		StringBuffer b = new StringBuffer();
		java.util.Random r;
		int k ;
		for(int i=0;i<length;i++){
			 r = new java.util.Random();
			 k = r.nextInt();
			 b.append(String.valueOf(charBase1[Math.abs(k % 76)]));
		}
		
		return b.toString();
	}
	public static String genCharNumCode(int charLength,int numLength){
		StringBuffer b = new StringBuffer();
		java.util.Random r;
		int k ;
		for(int i=0;i<charLength;i++){
			 r = new java.util.Random();
			 k = r.nextInt();
			 b.append(String.valueOf(charBase0[Math.abs(k % 26)]));
		}
		for(int i=0;i<numLength;i++){
			 r = new java.util.Random();
			 k = r.nextInt();
			 b.append(String.valueOf(numBase[Math.abs(k % 10)]));
		}
		
		return b.toString();
	}
	public static String genChar(int charLength){
		StringBuffer b = new StringBuffer();
		java.util.Random r;
		int k ;
		for(int i=0;i<charLength;i++){
			 r = new java.util.Random();
			 k = r.nextInt();
			 b.append(String.valueOf(charBase0[Math.abs(k % 26)]));
		} 
		return b.toString();
	}
	
	public static void main(String[] args) {
		for(int i=0;i<50;i++){ 
			System.out.println(genCharNumCode(4,4));
		}
		  
	}
	
}
