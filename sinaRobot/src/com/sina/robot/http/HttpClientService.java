package com.sina.robot.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
 

/**
 * HTTPClient相关方法服务
 * @author Arthur
 *
 */
public class HttpClientService   {

	private static Logger logger = Logger.getLogger(HttpClientService.class);
	/**
	 * 通过RUL获取页面HTML信息
	 */
	 
	public String getWebHTML(RequestParamVO param) throws ClientProtocolException,
			IOException {
		HttpClient httpclient = new DefaultHttpClient();
		String content = null;
		try {
			// 创建 httpget.
			HttpGet httpget = new HttpGet(param.getUrl());
			// 执行 get 请求 .
			HttpResponse response = httpclient.execute(httpget);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
			{
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				// 打印响应状态
				logger.info(response.getStatusLine());
				if (entity != null) 
				{
					// 打印响应内容
					content = EntityUtils.toString(entity,param.getSourceEncode());
				}
			}
			
		} finally {
			// 关闭连接 , 释放资源
			httpclient.getConnectionManager().shutdown();
		}
		return content;
	}
	
	public static void main(String[] args)
	{
		HttpClientService httpClientService=new HttpClientService();
		RequestParamVO param=new RequestParamVO();
		param.setUrl("http://content.battlenet.com.cn/wow/icons/56/inv_crown_02.jpg");
		int i;
		try {
			for(i=0;i<100;i++)
			{
				Long time1=System.currentTimeMillis();
				httpClientService.getWebHTML(param);
				Long time2=System.currentTimeMillis();
				System.out.println(time2-time1);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
