package com.sina.robot.http;

public class RequestParamVO {

	private String url;
	
	private String sourceEncode="UTF-8";
	
	private String targetEncode="UTF-8";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSourceEncode() {
		return sourceEncode;
	}

	public void setSourceEncode(String sourceEncode) {
		this.sourceEncode = sourceEncode;
	}

	public String getTargetEncode() {
		return targetEncode;
	}

	public void setTargetEncode(String targetEncode) {
		this.targetEncode = targetEncode;
	}

	
	
}
