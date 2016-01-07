package com.sina.weibo.service.impl;

import java.util.ArrayList;

public class UploadImage_Test {
	 
	public static void main(String[] args) throws Exception {
		// 设定服务地址
		String serverUrl = "http://picupload.service.weibo.com/interface/pic_upload.php?app=miniblog&data=1&url=weibo.com/u/5645930573&markpos=1&logo=1&nick=@大胳膊俱乐部&marks=1&mime=image/jpeg&ct=0.555120256729424";//上传地址
		
		// 设定要上传的普通Form Field及其对应的value
		ArrayList<FormFieldKeyValuePair> ffkvp = new ArrayList<FormFieldKeyValuePair>();
		ffkvp.add(new FormFieldKeyValuePair("app", "miniblog"));//其他参数
 
		ffkvp.add(new FormFieldKeyValuePair("s", "json"));
		ffkvp.add(new FormFieldKeyValuePair("rotate", "0"));
		ffkvp.add(new FormFieldKeyValuePair("nick", ""));
		ffkvp.add(new FormFieldKeyValuePair("url", ""));
		ffkvp.add(new FormFieldKeyValuePair("cb", ""));
		// 设定要上传的文件
		ArrayList<UploadFileItem> ufi = new ArrayList<UploadFileItem>();
		ufi.add(new UploadFileItem("image", "D:/1.jpg"));
		HttpPostEmulator hpe = new HttpPostEmulator();
		String response = hpe.sendHttpPostRequest(serverUrl, ffkvp, ufi);
		System.out.println("Responsefrom server is: " + response);
		
		
	}
}