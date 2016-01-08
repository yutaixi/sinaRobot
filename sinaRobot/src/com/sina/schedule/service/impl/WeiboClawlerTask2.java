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

public class WeiboClawlerTask2 extends TimerTask{

	 
	private IWeiboService weiboService;
	 
	private IWeiboRobotDao  iWeiboRobotDao;
	
	private static Logger logger = Logger.getLogger(WeiboClawlerTask2.class);
	@Override
	public void run() { 
		 logger.error("start weibo syn");
		 try{
			  weiboService=Yop.getContext().getBean("weiboService", WeiboService.class);
			  iWeiboRobotDao=Yop.getContext().getBean(IWeiboRobotDao.class);
			// 对登陆微博的帐号、密码、uid的设置
				LoginPojo loginPojo = new LoginPojo();
				loginPojo.setUsername("tiantianyoumo001@sina.com");
				loginPojo.setPassword("");
				loginPojo.setNicName("重口味幽默笑话大全");
				WeiboCrawler weiboCrawler=new WeiboCrawler();
				weiboCrawler.setType("joke");
				List<WeiboCrawler> weibo2Crawl=iWeiboRobotDao.getWeiboCrawler( weiboCrawler); 
			    weiboService.weiboclawler(loginPojo, weibo2Crawl);
		 }catch(Exception e)
		 {
			 logger.error(e); 
		 }
		
	}

}
