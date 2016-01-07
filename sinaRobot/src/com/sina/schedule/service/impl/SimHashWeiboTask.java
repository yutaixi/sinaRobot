package com.sina.schedule.service.impl;

import java.util.List;
import java.util.TimerTask;





import org.apache.log4j.Logger;

import com.sina.algorithm.SimHash;
import com.sina.robot.core.ioc.Yop;
import com.sina.robot.dao.IWeiboRobotDao;
import com.sina.weibo.LoginPojo;
import com.sina.weibo.WeiboItem;
import com.sina.weibo.service.IWeiboService;
import com.sina.weibo.service.impl.WeiboService;

public class SimHashWeiboTask extends TimerTask{
 
	private IWeiboService weiboService; 
	private IWeiboRobotDao  iWeiboRobotDao;
	private static Logger logger = Logger.getLogger(WeiboClawlerTask.class);
	@Override
	public void run() { 
		 logger.error("start weibo syn");
		 try{
			  weiboService=Yop.getContext().getBean("weiboService", WeiboService.class);
			  iWeiboRobotDao=Yop.getContext().getBean(IWeiboRobotDao.class);
			  WeiboItem weiboItem=new WeiboItem();
			  List<WeiboItem> weiboList= iWeiboRobotDao.findWeibo(weiboItem, null);
			  for(WeiboItem item : weiboList)
			  {
				  item.setSimHash(SimHash.getSimHash(item.getText(), 64));
				  iWeiboRobotDao.updateWeiboSimHash(item);
			  }
		 }catch(Exception e)
		 {
			 logger.error(e); 
		 }
		
	}

}
