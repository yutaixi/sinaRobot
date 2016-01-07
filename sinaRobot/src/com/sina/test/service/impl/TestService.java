package com.sina.test.service.impl;

import java.util.Random;

import javax.inject.Inject;
import javax.inject.Named;

import com.sina.mail.regist.EmailVO;
import com.sina.mail.regist.service.IBreakthruMail;
import com.sina.test.service.ITestService;
@Named
public class TestService implements ITestService{

	
	
	
	@Inject
	private IBreakthruMail breakthruMail;
	
	@Override
	public EmailVO testEmailReg(EmailVO email) {
		 
		for(int i=0;i<10000000;i++)
		{
			breakthruMail.registeMail();
			try {
				Thread.currentThread().sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//毫秒   
		}
		return null;
	}
	
	public static void main(String[] args) {
        int max=2;
        int min=0;
        Random random = new Random();

        Double s = random.nextDouble()*(max-min) + min;
        System.out.println((int)(s*1000));
    }

}
