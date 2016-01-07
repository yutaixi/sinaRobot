package com.sina.robot.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private static final String DEFAULT_FORMAT="yyyy-MM-dd hh24:mm:ss";
	
	public static String getFormatedDate(Date date,String format)
	{
		if(date==null)
		{
			return "";
		}
		if(StringUtils.isEmpty(format))
		{
			format=DEFAULT_FORMAT;
		}
		DateFormat dateFormat=new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
}
