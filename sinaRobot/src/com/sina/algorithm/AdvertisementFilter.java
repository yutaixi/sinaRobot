package com.sina.algorithm;

public class AdvertisementFilter {

	private static final String[] keyWords={"加微信","微星号","+V","薇星号","搜索号","薇星","WX","wx","学姐","关注","想看你就转"};
	
	public static boolean isAdver(String text)
	{
		for(String keyWord : keyWords)
		{
			if(text.indexOf(keyWord)>-1)
			{
				return true;
			}
		}
		return false;
	}
}
