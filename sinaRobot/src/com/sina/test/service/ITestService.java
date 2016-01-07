package com.sina.test.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.sina.mail.regist.EmailVO;

@Path("test")
@Produces("application/json")
public interface ITestService {

	@GET
	@Path("testEmailReg") 
	public EmailVO testEmailReg(@QueryParam("")EmailVO email);
}
