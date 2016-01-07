package com.sina.weibo;

import java.util.List;

import com.sina.robot.BaseResourceVO;

public class WeiboItem extends BaseResourceVO{

	private String mid;
	
	private String title;
	
	private String text;
	
	private String wbVideo;
	
	private String ouid;
	
	private String nickName;
	
	private List<String> picIds;
	
	private String picIdStr;
	
	private String simHash;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOuid() {
		return ouid;
	}

	public void setOuid(String ouid) {
		this.ouid = ouid;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public List<String> getPicIds() {
		return picIds;
	}

	public void setPicIds(List<String> picIds) {
		this.picIds = picIds;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getWbVideo() {
		return wbVideo;
	}

	public void setWbVideo(String wbVideo) {
		this.wbVideo = wbVideo;
	}

	public String getPicIdStr() {
		return picIdStr;
	}

	public void setPicIdStr(String picIdStr) {
		this.picIdStr = picIdStr;
	}

	public String getSimHash() {
		return simHash;
	}

	public void setSimHash(String simHash) {
		this.simHash = simHash;
	}
	
	
	
}
