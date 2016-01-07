package com.sina.schedule.service.impl;

import java.util.List;
import java.util.TimerTask;


import org.apache.log4j.Logger;

import com.sina.robot.core.ioc.Yop;
import com.sina.robot.dao.IWeiboRobotDao;
import com.sina.weibo.LoginPojo;
import com.sina.weibo.WeiboCrawler;
import com.sina.weibo.service.IWeiboService;
import com.sina.weibo.service.impl.WeiboService;

public class WeiboClawlerTask extends TimerTask{

	 
	private IWeiboService weiboService;
	 
	private IWeiboRobotDao  iWeiboRobotDao;
	
	private static Logger logger = Logger.getLogger(WeiboClawlerTask.class);
	@Override
	public void run() { 
		 logger.error("start weibo syn");
		 try{
			  weiboService=Yop.getContext().getBean("weiboService", WeiboService.class);
			  iWeiboRobotDao=Yop.getContext().getBean(IWeiboRobotDao.class);
			// 对登陆微博的帐号、密码、uid的设置
				LoginPojo loginPojo = new LoginPojo();
				loginPojo.setUsername("dageboclub@sina.com");
				loginPojo.setPassword("Ydh515844094718");
				loginPojo.setNicName("大胳膊俱乐部");
				WeiboCrawler weiboCrawler=new WeiboCrawler();
				weiboCrawler.setType("fitness");
				List<WeiboCrawler> weibo2Crawl=iWeiboRobotDao.getWeiboCrawler( weiboCrawler); 
			    weiboService.weiboclawler(loginPojo, weibo2Crawl);
		 }catch(Exception e)
		 {
			 logger.error(e); 
		 }
		
	}

}
