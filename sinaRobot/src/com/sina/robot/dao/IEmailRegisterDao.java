package com.sina.robot.dao;

import org.apache.ibatis.annotations.Param;

import com.sina.mail.regist.EmailVO;

public interface IEmailRegisterDao {

	
	public void insertNewEmail(@Param("email")EmailVO EmailVO);
}
