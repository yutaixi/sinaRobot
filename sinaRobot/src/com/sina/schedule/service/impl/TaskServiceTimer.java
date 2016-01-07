package com.sina.schedule.service.impl;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



import org.apache.log4j.Logger;

import com.sina.robot.core.ioc.Yop;
import com.sina.robot.dao.IWeiboRobotDao;
import com.sina.weibo.LoginPojo;
import com.sina.weibo.WeiboCrawler;
import com.sina.weibo.service.IWeiboService;
import com.sina.weibo.service.impl.WeiboService;

public class TaskServiceTimer extends TimerTask{

	 
	private IWeiboService weiboService;
	 
	private IWeiboRobotDao  iWeiboRobotDao;
	
	private static Logger logger = Logger.getLogger(TaskServiceTimer.class);
	@Override
	public void run() {  
		 try{
			  weiboService=Yop.getContext().getBean("weiboService", WeiboService.class);
			  iWeiboRobotDao=Yop.getContext().getBean(IWeiboRobotDao.class);
			  LoginPojo loginPojo = new LoginPojo(); 
			  iWeiboRobotDao.resetWeiboSynStatus(loginPojo);
			  
			  Timer timer = new Timer(); 
		      timer.schedule(new WeiboClawlerTask(),1000, 5*60* 1000);
		      
//		      Timer timer3 = new Timer(); 
//		      timer3.schedule(new WeiboClawlerTask2(),10000, 5*60* 1000);
		      
		      Timer timer2 = new Timer(); 
		      timer2.schedule(new WeiboSynTask(),5000, 10*60* 1000);
		      
//		      Timer timer4 = new Timer(); 
//		      timer4.schedule(new WeiboSynTask2(),30000, 9*60* 1000);
		      
//		      Timer timer5 = new Timer(); 
//		      timer5.schedule(new SimHashWeiboTask(),10000);
		 }catch(Exception e)
		 {
			 logger.error(e); 
		 }
		
	}

}
