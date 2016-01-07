package com.sina.weibo;

import com.sina.robot.BaseResourceVO;

public class WeiboCrawler extends BaseResourceVO{

	private String ouid;
	private String nicName;
	
	private String type;

	public String getOuid() {
		return ouid;
	}

	public void setOuid(String ouid) {
		this.ouid = ouid;
	}

	 

	public String getNicName() {
		return nicName;
	}

	public void setNicName(String nicName) {
		this.nicName = nicName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
