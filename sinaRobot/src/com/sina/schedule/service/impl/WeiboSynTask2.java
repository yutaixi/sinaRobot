package com.sina.schedule.service.impl;

import java.util.TimerTask;


import org.apache.log4j.Logger;

import com.sina.robot.core.ioc.Yop;
import com.sina.weibo.LoginPojo;
import com.sina.weibo.service.IWeiboService;
import com.sina.weibo.service.impl.WeiboService;

public class WeiboSynTask2 extends TimerTask{
 
	private IWeiboService weiboService; 
	private static Logger logger = Logger.getLogger(WeiboClawlerTask.class);
	@Override
	public void run() { 
		 logger.error("start weibo syn");
		 try{
			  weiboService=Yop.getContext().getBean("weiboService", WeiboService.class);
			// 对登陆微博的帐号、密码、uid的设置
			  LoginPojo loginPojo = new LoginPojo();
				loginPojo.setUsername("tiantianyoumo001@sina.com");
				loginPojo.setPassword("1937294381");
				loginPojo.setNicName("重口味幽默笑话大全");
				loginPojo.setDelay(30);
				weiboService.synWeibos(loginPojo);
		 }catch(Exception e)
		 {
			 logger.error(e); 
		 }
		
	}

}
