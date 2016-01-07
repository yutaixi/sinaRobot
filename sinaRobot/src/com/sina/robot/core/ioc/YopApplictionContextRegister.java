package com.sina.robot.core.ioc;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.sina.schedule.service.impl.WeiboSynService;
 


public class YopApplictionContextRegister implements
		ApplicationContextAware  {
	private static Logger logger = Logger.getLogger(YopApplictionContextRegister.class);
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		
		YopApplictionContext yopApplictionContext=new YopApplictionContext(arg0);
		Yop.setContext(yopApplictionContext);
		InetAddress addr=null;
		try {
			addr= InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String localHostName=addr.getHostName();
		String localHostIP=addr.getHostAddress();
		Yop.setHostName(localHostName);
		Yop.setLocalAddress(localHostIP);
		logger.debug("----------setApplicationContext----------");
		
		
		WeiboSynService weiboSynService=new WeiboSynService();
		weiboSynService.startSyn();
		logger.debug("----------startSyn in 20 seconds----------");
	}

}
