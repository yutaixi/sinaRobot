package com.sina.robot.core.base;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="AjaxResult")
public class AjaxResult implements Serializable   {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@XmlElement
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
