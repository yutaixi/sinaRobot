package com.sina.weibo.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.inject.Inject;
import javax.inject.Named;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.http.Header; 
import org.apache.http.NameValuePair; 
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair; 
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sina.algorithm.AdvertisementFilter;
import com.sina.algorithm.SimHash;
import com.sina.robot.dao.IWeiboRobotDao;
import com.sina.robot.http.HttpClientHelper;
import com.sina.robot.http.HttpResult;
import com.sina.robot.utils.PasswordUtil4Sina;
import com.sina.robot.utils.RandomCodeUtil;
import com.sina.robot.utils.RandomUtils;
import com.sina.robot.utils.RegexPaserUtil;
import com.sina.schedule.service.impl.WeiboSynTask;
import com.sina.weibo.LoginEncryptVO;
import com.sina.weibo.LoginPojo;
import com.sina.weibo.WeiboCrawler;
import com.sina.weibo.WeiboItem;
import com.sina.weibo.service.IWeiboService;
 
@Named
public class WeiboService implements IWeiboService{

	private static Logger logger = Logger.getLogger(WeiboService.class);

	@Inject
	private IWeiboRobotDao  iWeiboRobotDao;
	
	private static final String SINA_WEIBO_REG_PAGE="http://weibo.com/signup/mobile.php?lang=zh-cn&inviteCode=&from=&appsrc=&backurl=&showlogo=";
	
	private static final String SINA_PRE_LOGIN_URL="http://login.sina.com.cn/sso/prelogin.php?entry=weibo&callback=sinaSSOController.preloginCallBack&su=&rsakt=mod&client=ssologin.js(v1.4.18)&_=";
	
	private static final String REG_WEIBO_URL="http://weibo.com/signup/v5/reg";
	
	private static final String VERIFY_CODE_URL="http://weibo.com/signup/v5/pincode/pincode.php?lang=zh&sinaId=142e374cc2cb4accf5ec5fa9fe744c40&r=";
	
	private static final String ADD_WEIBO="http://weibo.com/p/aj/v6/mblog/add?ajwvr=6&domain=100505&__rnd=";

	
	private GetUserCookie cookieUtil;
	public boolean login(LoginPojo loginPojo)
	{
		
		
		// 非代理登陆
		 cookieUtil = new GetUserCookie(loginPojo);
		// 用代理登陆
		// GetUserCookie cookieUtil = new GetUserCookie(loginPojo, proxyPojo);
		
		String cookieString=null;
		try {
			cookieString = cookieUtil.getCookies("7.0");
			loginPojo.setCookie(cookieString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		if (cookieString == null || cookieString.isEmpty()) {
			System.out.println("最終得到的cookies為空，請檢查!");
			return false;
		} else {
			System.out.println("cookieString---" + cookieString);
			
		}
		return true;
	}
	

	public String follow(String uid)
	{
		
		String followUrl="http://weibo.com/aj/f/followed?ajwvr=6";
		List<NameValuePair> qparams=new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("uid", uid));
//		qparams.add(new BasicNameValuePair("fnick", "%E5%AD%A6%E7%94%9F%E9%82%A3%E7%82%B9%E5%B0%8F%E4%BA%8B")); 
		qparams.add(new BasicNameValuePair("objectid", ""));
		qparams.add(new BasicNameValuePair("f", "1"));
		qparams.add(new BasicNameValuePair("extra", ""));
		qparams.add(new BasicNameValuePair("refer_sort", ""));
		qparams.add(new BasicNameValuePair("refer_flag", ""));
		qparams.add(new BasicNameValuePair("location", "page_100505_home"));
		qparams.add(new BasicNameValuePair("oid", "1913382117"));
		qparams.add(new BasicNameValuePair("wforce", "1"));
		qparams.add(new BasicNameValuePair("nogroup", "false")); 
		qparams.add(new BasicNameValuePair("_t", "0"));
		qparams.add(new BasicNameValuePair("encoding", "UTF-8"));
		 try {
			 cookieUtil.doPost(followUrl, qparams);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return null;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException
	{
		// 对登陆微博的帐号、密码、uid的设置
		LoginPojo loginPojo = new LoginPojo(); 
		loginPojo.setUsername("dageboclub@sina.com");
		loginPojo.setPassword("");
		WeiboService weiboService=new WeiboService();
		weiboService.login(loginPojo);
//		weiboService.follow("1663073073");
//		weiboService.registe(); 
		System.out.println(loginPojo.getUid());
//		weiboService.addWeibo(loginPojo,"测试发送微博[奥特曼]"); 
//		weiboService.cookieUtil.uploadPic(loginPojo, null);
		WeiboCrawler weiboCrawler=new WeiboCrawler();
		weiboCrawler.setOuid("3083674060");
		weiboCrawler.setNicName("每日健身日记"); 
		List<WeiboItem> weiboList=weiboService.getWeiboList(weiboCrawler);
//		WeiboItem weibo=new WeiboItem();
//		weibo.setText("测试视频 http://t.cn/RL3yXzm"); 
//		if(weiboList!=null && weiboList.size()>0)
//		{
//			for(WeiboItem weibo : weiboList)
//			{
//				weiboService.addWeibo(loginPojo,weibo); 
//				try {
//					Thread.currentThread().sleep(60000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}//毫秒   
//			}
//		}
	}
 
	@Override
	public boolean registe() {
		HttpClientHelper hc = new HttpClientHelper(false);
		Map<String,String> regData=this.getRegData(hc); 
		
		LoginEncryptVO loginEncryptVO=this.getPubKey(hc);  
		this.getVerifyCode(hc);
		this.addRegData(regData, loginEncryptVO); 
		HttpResult response=hc.post(REG_WEIBO_URL, regData, this.setRegWeiboPostHeaders());
		System.out.println(response.getHtml());
		return false;
	}
	private void getVerifyCode(HttpClientHelper hc )
	{
		HttpResult response=hc.get(VERIFY_CODE_URL, null); 
		try {
			FileUtils.writeByteArrayToFile(new File("d:/1.jpg"), response.getResponse());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addRegData(Map<String,String> regData,LoginEncryptVO loginEncryptVO)
	{ 
		//可以修改，可以不修改
		regData.put("password", this.getEncPassword(loginEncryptVO, "1937294381"));
		regData.put("replaceurl", "http%3A%2F%2Fweibo.com%2Fsignup%2Fv5%2Fajaxreg");
		regData.put("username", "sfdfsd432@breakthru.com");
		Scanner sc = new Scanner(System.in); 
		System.out.println("请输入验证码：");
		String TPL_checkcode = sc.next();
		regData.put("pincode", TPL_checkcode);
//		regData.put("rejectFake", "clickCount%253D11%2526subBtnClick%253D0%2526keyPress%253D36%2526menuClick%253D0%2526mouseMove%253D2533%2526checkcode%253D0%2526subBtnPosx%253D554%2526subBtnPosy%253D482%2526subBtnDelay%253D118%2526keycode%253D49%252C50%252C51%252C52%252C53%252C54%252C55%252C56%252C57%252C89%252C100%252C116%252C120%252C49%252C50%252C51%252C52%252C53%252C54%252C55%252C56%252C57%252C49%252C57%252C51%252C55%252C50%252C57%252C52%252C51%252C56%252C49%252C104%252C102%252C104%252C99%2526winWidth%253D1536%2526winHeight%253D747%2526userAgent%253DMozilla%252F5.0%2520%28Windows%2520NT%252010.0%253B%2520Win64%253B%2520x64%29%2520AppleWebKit%252F537.36%2520%28KHTML%252C%2520like%2520Gecko%29%2520Chrome%252F42.0.2311.135%2520Safari%252F537.36%2520Edge%252F12.10240");
	}
	public static Header[] setRegWeiboPostHeaders() {
        Header[] result = { 
                
                new BasicHeader("Accept","text/html, application/xhtml+xml, */*"),
                new BasicHeader("Accept-Encoding","gzip, deflate"),
                new BasicHeader("Accept-Language","zh-CN"),
                new BasicHeader("Cache-Control","no-cache"),
                new BasicHeader("Connection","Keep-Alive"),
                new BasicHeader("Content-Type","application/x-www-form-urlencoded"),
                new BasicHeader("Host","weibo.com"),
                new BasicHeader("Referer","http://weibo.com/signup/mobile.php?lang=zh-cn&inviteCode=&from=&appsrc=&backurl=&showlogo="),
                new BasicHeader("User-Agent","Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)") 
                 
        };
        return result;
    }
	 
	// 得到密码经加密后的密文
		public String getEncPassword(LoginEncryptVO LoginEncryptVO,String password) {
			String realPwd = PasswordUtil4Sina.getRegPassEncoding(LoginEncryptVO.getPubKey(), LoginEncryptVO.getServertime(),
					LoginEncryptVO.getNonce(), password);
			// System.out.println("加密后的密码是---" + realPwd);
			return realPwd;
		}
	
	public LoginEncryptVO getPubKey(HttpClientHelper hc)
	{
		LoginEncryptVO loginEncryptVO=new LoginEncryptVO();
		HttpResult response = hc.get(SINA_PRE_LOGIN_URL+(new Date()).getTime(), null); 
		String repHtml=response.getHtml();
		this.getEncryptKey(repHtml, loginEncryptVO);
		return loginEncryptVO;
	}
	
	public void getEncryptKey(String response,LoginEncryptVO loginEncryptVO)
	{ 
		RegexPaserUtil ap = new RegexPaserUtil("servertime\":", ",", response,
				RegexPaserUtil.TEXTTEGEX);
		loginEncryptVO.setServertime(ap.getText()); 
		// System.out.println("得到的servertime---" + servertime);
		ap = new RegexPaserUtil("nonce\":\"", "\"", response,
				RegexPaserUtil.TEXTTEGEX);
		loginEncryptVO.setNonce(ap.getText());
		// System.out.println("得到的nonce---" + nonce);
		ap = new RegexPaserUtil("pubkey\":\"", "\"", response,
				RegexPaserUtil.TEXTTEGEX);
		loginEncryptVO.setPubKey(ap.getText());
		// System.out.println("得到的pubKey---" + pubKey);
		ap = new RegexPaserUtil("rsakv\":\"", "\"", response,
				RegexPaserUtil.TEXTTEGEX);
		loginEncryptVO.setRsaKV(ap.getText());
		ap = new RegexPaserUtil("pcid\":\"", "\"", response,
				RegexPaserUtil.TEXTTEGEX);
		loginEncryptVO.setPcid(ap.getText()); 
	}
	
	
	private Map<String,String> getRegData(HttpClientHelper hc)
	{
		 Map<String,String> data=new HashMap<String,String>();
		HttpResult rePageResult=hc.get(SINA_WEIBO_REG_PAGE, null);
		 if(rePageResult!=null)
		 { 
			Document doc = Jsoup.parse(rePageResult.getHtml());
			Elements regForm=doc.getElementsByClass("W_reg_form");
			if(regForm!=null && regForm.size()>0)
			{ 
				Document regFormDoc=Jsoup.parse(regForm.html());
				Elements inputs=regFormDoc.getElementsByAttributeValue("type", "hidden");
				if(inputs!=null && inputs.size()>0)
				{
					for(Element input : inputs)
					{
						if("input".equalsIgnoreCase(input.tagName()))
						{ 
							data.put(input.attr("name"), input.attr("value"));
//							 System.out.println(input.attr("name")+"::::"+input.attr("value"));
						}
					}
				}
				
			}
			
		 }
		 
		 return data;
	}
	
	
	//发布微博（文字）
	public boolean addWeibo(LoginPojo loginPojo,WeiboItem weiboItem)
	{
		String url=ADD_WEIBO+new Date().getTime();
		List<NameValuePair> qparams=new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("_t", "0"));
//		qparams.add(new BasicNameValuePair("fnick", "%E5%AD%A6%E7%94%9F%E9%82%A3%E7%82%B9%E5%B0%8F%E4%BA%8B")); 
		qparams.add(new BasicNameValuePair("appkey", ""));
		qparams.add(new BasicNameValuePair("location", "v6_content_home"));
		qparams.add(new BasicNameValuePair("module", "stissue"));
		qparams.add(new BasicNameValuePair("pdetail", ""));
//		String picIds="";
//		if(weiboItem.getPicIds()!=null && weiboItem.getPicIds().size()>0)
//		{
//			for(String picId : weiboItem.getPicIds())
//			{
//				picIds+=" "+picId;
//			}
//			picIds=picIds.substring(1);
//		}
		qparams.add(new BasicNameValuePair("pic_id", weiboItem.getPicIdStr()));
		qparams.add(new BasicNameValuePair("pub_type", "dialog"));
		qparams.add(new BasicNameValuePair("rank", "0"));
		qparams.add(new BasicNameValuePair("rankid", ""));
		qparams.add(new BasicNameValuePair("style_type", "1"));
		qparams.add(new BasicNameValuePair("text", weiboItem.getText()));
		   
		try {
			cookieUtil.doPost(url, qparams,WeiboService.getAddWeiboHeader(loginPojo));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public static Header[] getAddWeiboHeader(LoginPojo loginPojo) {
        Header[] result = { 
                new BasicHeader("User-Agent","Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)"), 
                new BasicHeader("Accept-Encoding","gzip, deflate"),
                new BasicHeader("Accept-Language","en-US, en,zh-CN"), 
                new BasicHeader("Cache-Control","no-cache"),
                new BasicHeader("Connection","Keep-Alive"),
                new BasicHeader("Content-Type","application/x-www-form-urlencoded"),
                new BasicHeader("Host","weibo.com"),
                new BasicHeader("Referer","http://weibo.com/u/"+loginPojo.getUid()+"/home?wvr=5&lf=reg"),
                new BasicHeader("Accept","text/html, application/xhtml+xml, */*"),
                new BasicHeader("X-Requested-With","XMLHttpRequest") 
                 
        };
        return result;
    }
	
	public List<WeiboItem> getWeiboList(WeiboCrawler weibo2SynItem)
	{
		List<WeiboItem> weiboList=new ArrayList<WeiboItem>();
//		Map<String,String> params=new HashMap<String,String>();
//		params.put("domain", "100505");
//		params.put("pre_page", "1");
//		params.put("page", "1");
//		params.put("max_id", "");
//		params.put("end_id", "3874809366209616");
//		params.put("pagebar", "0");
//		params.put("filtered_min_id", "");
//		params.put("pl_name", "Pl_Official_MyProfileFeed__22");
//		params.put("id", "100505"+uid);
//		params.put("script_uri", "/u/"+uid);
//		params.put("feed_type", "0");
//		params.put("domain_op", "100505");
//		params.put("__rnd", String.valueOf(new Date().getTime()));
//		params.put("ajwvr", "6");
		WeiboItem weiboItem=null;
		String content=null;
		try {
			content=cookieUtil.doGet("http://weibo.com/"+weibo2SynItem.getOuid(), null, null);
//			content=cookieUtil.doGet("http://weibo.com/p/aj/v6/mblog/mbloglist?ajwvr=6&domain=100505&pre_page=1&page=1&max_id=&end_id="+"3874809366209616"+"&pagebar=0&filtered_min_id=&pl_name=Pl_Official_MyProfileFeed__22&id=100505"+uid+"&script_uri=/u/"+uid+"&feed_type=0&domain_op=100505&__rnd="+new Date().getTime(),null,null);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Document doc = Jsoup.parse(content); 
		Elements scripts=doc.getElementsByTag("script");
		if(scripts!=null && scripts.size()>0)
		{
			for(Element script : scripts)
			{
				String temp=script.html();
				if(temp.startsWith("FM.view") && temp.contains("\"html\":") && temp.contains("tbinfo="))
				{
					String json=temp.substring(temp.indexOf("FM.view(")+8,temp.length()-1);
					JSONObject  obj =  JSONObject.fromObject(json) ;
 
					String weiboHtml=obj.get("html").toString();
					Document weibosDoc = Jsoup.parse(weiboHtml); 
					Elements weibos=weibosDoc.getElementsByAttribute("tbinfo");
					for(Element weibo : weibos)
					{
						weiboItem=new WeiboItem();
						String ouid=this.getOuid(weibo);
						String mid=this.getMid(weibo); 
						String text=this.getText(weibo); 
						String picIds=this.getPicStr(weibo); 
						 
						weiboItem.setOuid(ouid);
						weiboItem.setMid(mid);
						weiboItem.setText(text); 
						weiboItem.setSimHash(SimHash.getSimHash(text, 64));
						weiboItem.setPicIdStr(picIds);
						weiboItem.setNickName(weibo2SynItem.getNicName());
						weiboList.add(weiboItem);
						
						
					}
					
				}
			}
		}
		return weiboList;
	}
	public void preprocessWeibo(WeiboItem weiboItem,WeiboCrawler weibo2SynItem,LoginPojo loginPojo)
	{
		if(weiboItem.getText()!=null && weiboItem.getText().indexOf(weibo2SynItem.getNicName())>-1)
		{
			weiboItem.setText(weiboItem.getText().replaceAll(weibo2SynItem.getNicName(),loginPojo.getNicName()));
			weiboItem.setSimHash(SimHash.getSimHash(weiboItem.getText(), 64));
		}
		 
	}
	
	private List<String> getPics(Element weibo)
	{
		Elements pics=weibo.getElementsByAttributeValue("node-type", "fl_pic_list");
		List<String> picIds=new ArrayList<String>();
		if(pics!=null && pics.size()>0)
		{
			Element pic=pics.get(0);
			String data=pic.attr("action-data");
			String[] dataArray=data.split("&");
			if(dataArray!=null && dataArray.length>0)
			{
				for(String temp : dataArray)
				{
				    if(temp.startsWith("pic_ids="))	
				    {
				    	String picIdsStr=temp.substring(temp.indexOf("=")+1);
				    	String[] picIdsArray=picIdsStr.split(",");
				    	picIds=Arrays.asList(picIdsArray);
				    }
				}
			} 
		}
		return picIds;
		
	}
	
	private String getPicStr(Element weibo)
	{
		Elements pics=weibo.getElementsByAttributeValue("node-type", "fl_pic_list");
		if(pics==null || pics.size()==0)
		{
			pics=weibo.getElementsByAttributeValue("action-type", "feed_list_media_img");
		}
		String picIds=null;
		if(pics!=null && pics.size()>0)
		{
			Element pic=pics.get(0);
			String data=pic.attr("action-data");
			String[] dataArray=data.split("&");
			if(dataArray!=null && dataArray.length>0)
			{
				for(String temp : dataArray)
				{
				    if(temp.startsWith("pic_ids=") || temp.startsWith("pid="))	
				    {
				    	picIds=temp.substring(temp.indexOf("=")+1);
				    	picIds=picIds.replaceAll(",", " ");
				    	break;
				    }
				}
			} 
		}
		return picIds;
		
	}
	
	private String getText(Element weibo)
	{
		Elements weiboTextElement=weibo.getElementsByClass("WB_text");
		String text=null;
		if(weiboTextElement!=null && weiboTextElement.size()>0)
		{
			Element textElement=weiboTextElement.get(weiboTextElement.size()-1);
			textElement.removeClass("W_icon_feedhot").removeClass("W_icon icon_feedhot_lite"); 
		 
			text=textElement.ownText();
			 
			Elements videos=textElement.getElementsByAttributeValue("action-type", "feed_list_url");
			if(videos!=null && videos.size()>0)
			{
				text+="  "+videos.get(0).attr("href");
			}
		}
		return text;
		 
	}
	private String getMid(Element weibo)
	{
		return weibo.attr("mid");
	}
	private String getOuid(Element weibo)
	{
		String ouid=weibo.attr("tbinfo");
		ouid=ouid.substring(ouid.indexOf("=")+1); 
		return ouid;
	}

	public void weiboclawler(LoginPojo loginPojo,List<WeiboCrawler> weiboCrawlerList)
	{
		this.login(loginPojo);
		if(weiboCrawlerList==null || weiboCrawlerList.size()==0)
		{
			return;
		}
		for(WeiboCrawler weibo2SynItem : weiboCrawlerList)
		{
			List<WeiboItem> weiboList=null;
			try{
				weiboList= this.getWeiboList(weibo2SynItem);
				if(weiboList!=null && weiboList.size()>0)
				{
					for(WeiboItem weibo :weiboList)
					{
						this.preprocessWeibo(weibo, weibo2SynItem, loginPojo);
						
					}
					iWeiboRobotDao.insertOrUpdatePublishedWeibo(weiboList, loginPojo.getUsername());
				}
			}catch(Exception e)
			{
				logger.error(e);
				e.printStackTrace();
			}
			
			
		 logger.info("syn weibo complete:"+weibo2SynItem.getOuid()+"---"+weibo2SynItem.getNicName());
		
	   }
	}
	public void synWeibos(LoginPojo loginPojo)
	{
		iWeiboRobotDao.resetWeiboSynStatus(loginPojo);
		try{
			this.login(loginPojo);
		}catch(Exception e)
		{
			return;
		}
		
		WeiboItem weiboItem=new WeiboItem();
		List<WeiboItem> weibo2Publish=iWeiboRobotDao.findWeibo2Publish(weiboItem,loginPojo.getUsername());
		if(weibo2Publish==null || weibo2Publish.size()==0)
		{
			return;
		}
		iWeiboRobotDao.updateWeiboSyning(weibo2Publish);
		if(weibo2Publish!=null && weibo2Publish.size()>0)
		{
			for(WeiboItem weibo : weibo2Publish)
			{
				try
				{
					if(!AdvertisementFilter.isAdver(weibo.getText()))
					{
						this.addWeibo(loginPojo, weibo);
						iWeiboRobotDao.updateWeiboSynDone(weibo);
						logger.error("weibo published "+weibo.getMid());
						Thread.currentThread().sleep(1000*loginPojo.getDelay() + (int)(RandomUtils.getRandom(0, 2)*1000*60));
						
					}else
					{
						iWeiboRobotDao.updateWeiboSynDone(weibo);
					} 
				}catch(Exception e)
				{
					iWeiboRobotDao.updateWeiboSynError(weibo);
					logger.error("publish weibo"+weibo.getMid()+" failed:"+e);
				}
				
			}
		}
	}
	
	
	public void synWeibo(LoginPojo loginPojo,List<WeiboCrawler> weiboCrawlerList) {
 
		WeiboService weiboService = new WeiboService();
		weiboService.login(loginPojo); 
		for(WeiboCrawler weibo2SynItem : weiboCrawlerList)
		{
			List<WeiboItem> weiboList=null;
			try{
				weiboList= weiboService.getWeiboList(weibo2SynItem);
			}catch(Exception e)
			{
				
			}
			
			List<WeiboItem> weiboExist=null;
			if (weiboList != null && weiboList.size() > 0) {
				try
				{
					for (WeiboItem weibo : weiboList) {
						this.preprocessWeibo(weibo, weibo2SynItem, loginPojo);
						weiboExist=iWeiboRobotDao.findWeibo(weibo,loginPojo.getUsername());
						if(weiboExist!=null && weiboExist.size()>0)
						{
							continue;
						}
						weiboService.addWeibo(loginPojo, weibo);
						iWeiboRobotDao.insertPublishedWeibo(weibo,loginPojo.getUsername());
						logger.error("weibo published "+weibo.getMid());
						try {
							Thread.currentThread().sleep(60000*1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}// 毫秒
					}
				}catch(Exception e)
				{
					logger.error(e);
				}
				
			}
		}
		
	}
}


