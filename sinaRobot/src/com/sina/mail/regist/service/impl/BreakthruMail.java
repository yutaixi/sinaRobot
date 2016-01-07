package com.sina.mail.regist.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sina.mail.regist.EmailVO;
import com.sina.mail.regist.service.IBreakthruMail;
import com.sina.robot.dao.IEmailRegisterDao;
import com.sina.robot.http.HttpClientHelper;
import com.sina.robot.http.HttpResult;
import com.sina.robot.utils.RandomCodeUtil;

 
@Named
public class BreakthruMail implements IBreakthruMail {

	@Inject
	private IEmailRegisterDao iEmailRegisterDao;
	
	private static Logger logger = Logger.getLogger(BreakthruMail.class);
	private static String registPage="http://breakthru.com/p/register.cgi";
	
	private static String registUrl="http://breakthru.com/p/register.cgi";

	private static String WHO_KEY="who=";
	
	private static String SUCCESS_KEY="Thank you for registering";
  
	private static String MAIL_END="@breakthru.com";
	
	public void registeMail( )
	{
		HttpClientHelper hc = new HttpClientHelper(false);
		logger.info("正在获取who值..."); 
		String whoKey=this.getWhoKey(hc);
		if(whoKey==null || "".equalsIgnoreCase(whoKey.trim()))
		{
			logger.info("获取who key 失败");
			return ;
		}
		 logger.info("已获取who值:"+whoKey);
		 logger.info("正在注册邮箱..."); 
		 Map<String, String> regData=getData(whoKey);
		 HttpResult regResult= hc.post(registUrl, regData, BreakthruMail.setHeader()); 
		 if(regResult.getHtml().contains(SUCCESS_KEY))
		 {
			 iEmailRegisterDao.insertNewEmail(this.map2EmailVO(regData));
			 logger.info("---------------注册邮箱成功---------------------"); 
			 logger.info(regData.get("REG_handle")+MAIL_END+"         "+regData.get("password")); 
		 }else
		 {
			 logger.error("--------------注册邮箱失败--------------------"); 
		 }
		  
		
	}
	
	private EmailVO map2EmailVO(Map<String, String> regData)
	{
		EmailVO emailVO=new EmailVO();
		emailVO.setAccount(regData.get("REG_handle"));
		emailVO.setPassword(regData.get("password"));
		emailVO.setBackupEmail(regData.get("email"));
		emailVO.setBdayDay(regData.get("bday_day"));
		emailVO.setBdayMonth(regData.get("bday_month"));
		emailVO.setBdayYear(regData.get("bday_year"));
		emailVO.setCountry(regData.get("country"));
		emailVO.setEmailType(MAIL_END);
		emailVO.setFirstName(regData.get("firstname"));
		emailVO.setLastName(regData.get("lastname"));
		emailVO.setRegSex(regData.get("REG_sex"));
		emailVO.setZipCode(regData.get("zip"));
		return emailVO;
	}
	
	private Map<String, String> getData(String who)
	{
		Map<String, String> data = new HashMap<String, String>();
		
		//必须与之前的不同
		data.put("REG_handle", RandomCodeUtil.genCharNumCode(4, 4));
		data.put("email", RandomCodeUtil.genCharNumCode(4, 4)+"@qq.com");
		
		//可以修改，可以不修改
		data.put("password", "123456789");
		data.put("confirm_password", "123456789");
		data.put("firstname", RandomCodeUtil.genChar(5)); 
		data.put("lastname", RandomCodeUtil.genChar(5)); 
		data.put("bday_day", "01");
		data.put("bday_month", "1");
		data.put("bday_year", "85");
		data.put("country", "China");
		data.put("REG_sex", "1");
		data.put("zip", "118307");
		
		//每次从页面获取新的值
		data.put("who", who);
		
		//固定不变
		data.put("action", "page1");
		data.put("ignore_status_from_reg", "1");
		data.put("from_ad", "");
		
		return data;
	}
	 public static Header[] setHeader() {
	        Header[] result = { 
	                new BasicHeader("User-Agent","Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)"), 
	                new BasicHeader("Accept-Encoding","gzip, deflate"),
	                new BasicHeader("Accept-Language","zh-CN"),
	                new BasicHeader("Cache-Control","no-cache"),
	                new BasicHeader("Connection","Keep-Alive"),
	                new BasicHeader("Content-Type","application/x-www-form-urlencoded"),
	                new BasicHeader("Host","breakthru.com"),
	                new BasicHeader("Referer","http://breakthru.com/p/register.cgi"),
	                new BasicHeader("Accept","text/html, application/xhtml+xml, */*")
	                 
	        };
	        return result;
	    }
	
	private String getWhoKey(HttpClientHelper hc)
	{
		String whoKey=null; 
		 HttpResult rePageResult= hc.get(registPage, null);
		 if(rePageResult!=null)
		 { 
			Document doc = Jsoup.parse(rePageResult.getHtml());
			Elements elements=doc.getElementsByAttributeValueContaining("href", WHO_KEY);
			if(elements.size()>0)
			{
				whoKey=elements.get(0).attr("href"); 
			}
			
		 }
		 if(whoKey!=null)
		 {
			 whoKey=whoKey.substring(whoKey.indexOf("who=")+4);
		 }
		 return whoKey;
	}
	
	public static void main(String[] args)
	{
		BreakthruMail breakthruMail=new BreakthruMail();
		for(int i=0;i<50;i++)
		{
			breakthruMail.registeMail();
			try {
				Thread.currentThread().sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//毫秒   
		}
		
	}
	
	
	
	
}
