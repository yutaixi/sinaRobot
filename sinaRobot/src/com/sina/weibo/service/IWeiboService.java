package com.sina.weibo.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.sina.weibo.LoginPojo;
import com.sina.weibo.WeiboCrawler;
 

@Path("weiboService")
@Produces("application/json")
public interface IWeiboService {
 
	public boolean registe();
	@GET
	@Path("synWeibo")
	public void synWeibos(LoginPojo loginPojo);
	
	public void weiboclawler(LoginPojo loginPojo,List<WeiboCrawler> weiboCrawlerList);
	
}
