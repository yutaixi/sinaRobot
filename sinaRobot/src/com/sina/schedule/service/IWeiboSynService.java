package com.sina.schedule.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("weiboSynService")
@Produces("application/json")
public interface IWeiboSynService {
	@GET
	@Path("startSyn")
	public  void startSyn();

}
