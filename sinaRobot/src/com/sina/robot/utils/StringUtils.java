package com.sina.robot.utils;
 


public class StringUtils {

	
	
	public static boolean isEmpty(String string)
	{
		if(string==null || "".equalsIgnoreCase(string.trim()))
		{
			return true;
		}
		return false;
	}
	
	public static String removeWrapCharacter(String str)
	{
		if(isEmpty(str))
		{
			return null;
		}
		return str.replaceAll("\\r", "").replaceAll("\\n", "").replaceAll("\\t", "");
	}
	
	

}
