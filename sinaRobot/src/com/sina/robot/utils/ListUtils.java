package com.sina.robot.utils;

import java.util.List;

public class ListUtils {

	
	public static <T> boolean isListEmpty(List<T> list)
	{
		if(list==null || list.size()==0)
		{
			return true;
		}
		return false;
	}
}
