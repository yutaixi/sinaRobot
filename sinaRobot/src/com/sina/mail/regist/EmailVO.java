package com.sina.mail.regist;

import com.sina.robot.BaseResourceVO;

public class EmailVO extends BaseResourceVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3763355099961241343L;

	private String account;
	
	private String password;
	
	private String backupEmail;
	
	private String firstName;
	
	private String lastName;
	
	private String bdayDay;
	private String bdayMonth;
	private String bdayYear;
	private String country;
	private String regSex;
	private String  zipCode;
	
	private String emailType;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBackupEmail() {
		return backupEmail;
	}
	public void setBackupEmail(String backupEmail) {
		this.backupEmail = backupEmail;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBdayDay() {
		return bdayDay;
	}
	public void setBdayDay(String bdayDay) {
		this.bdayDay = bdayDay;
	}
	public String getBdayMonth() {
		return bdayMonth;
	}
	public void setBdayMonth(String bdayMonth) {
		this.bdayMonth = bdayMonth;
	}
	public String getBdayYear() {
		return bdayYear;
	}
	public void setBdayYear(String bdayYear) {
		this.bdayYear = bdayYear;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegSex() {
		return regSex;
	}
	public void setRegSex(String regSex) {
		this.regSex = regSex;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getEmailType() {
		return emailType;
	}
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
	
	
	
	

}
