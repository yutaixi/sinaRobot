package com.sina.schedule.service.impl;

import java.util.Timer;

import org.apache.log4j.Logger;

public class WeiboSynService {
	
	private static Logger logger = Logger.getLogger(WeiboSynService.class);
	
	public  void startSyn(){
		 Timer timer = new Timer(); 
	      timer.schedule(new TaskServiceTimer(),5000);
		
//	      Timer timer = new Timer(); 
//	      timer.schedule(new WeiboClawlerTask(),5000, 5*60* 1000);
	      
//	      Timer timer3 = new Timer(); 
//	      timer3.schedule(new WeiboClawlerTask2(),10000, 5*60* 1000);
	      
//	      Timer timer2 = new Timer(); 
//	      timer2.schedule(new WeiboSynTask(),5000, 1*60* 1000);
	      
//	      Timer timer4 = new Timer(); 
//	      timer4.schedule(new WeiboSynTask2(),30000, 9*60* 1000);
	      
//	      Timer timer5 = new Timer(); 
//	      timer5.schedule(new SimHashWeiboTask(),10000);
	      logger.error("WeiboSynTask scheduled in 5 secondes");
	    }
	
	public static void main(String[] args)
	{
		WeiboSynService WeiboSynService=new WeiboSynService();
		WeiboSynService.startSyn();
	}
}
