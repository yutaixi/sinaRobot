package com.sina.robot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sina.weibo.LoginPojo;
import com.sina.weibo.WeiboCrawler;
import com.sina.weibo.WeiboItem;

public interface IWeiboRobotDao {

	public void insertPublishedWeibo(@Param("weiboItem")WeiboItem weiboItem,@Param("weiboId")String weiboId);
	
	public List<WeiboItem> findWeibo(@Param("weiboItem")WeiboItem weiboItem,@Param("weiboId")String weiboId);
	
	public List<WeiboCrawler> getWeiboCrawler(@Param("weiboCrawler")WeiboCrawler weiboCrawler);
	
	public void insertOrUpdatePublishedWeibo(@Param("weiboList")List<WeiboItem> weiboList,@Param("weiboId")String weiboId);

	public List<WeiboItem> findWeibo2Publish(@Param("weiboItem")WeiboItem weiboItem,@Param("weiboId")String weiboId);

	public void updateWeiboSyning(@Param("weiboList")List<WeiboItem> weiboList);
	
	public void updateWeiboSynDone(@Param("weiboItem")WeiboItem weiboItem);
	
	public void updateWeiboSynError(@Param("weiboItem")WeiboItem weiboItem);
	
	public void resetWeiboSynStatus(@Param("loginPojo")LoginPojo loginPojo);
	
	public List<WeiboItem> hasJobRunning(@Param("loginPojo")LoginPojo loginPojo);
	
	public void updateWeiboSimHash(@Param("weiboItem")WeiboItem weiboItem);
	
	
}
