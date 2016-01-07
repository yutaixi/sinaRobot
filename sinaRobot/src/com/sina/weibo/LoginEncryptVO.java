package com.sina.weibo;

public class LoginEncryptVO {

	private String servertime;
	
	private String nonce;
	
	private String pubKey;
	
	private String rsaKV;
	
	private String pcid;

	public String getServertime() {
		return servertime;
	}

	public void setServertime(String servertime) {
		this.servertime = servertime;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getPubKey() {
		return pubKey;
	}

	public void setPubKey(String pubKey) {
		this.pubKey = pubKey;
	}

	public String getRsaKV() {
		return rsaKV;
	}

	public void setRsaKV(String rsaKV) {
		this.rsaKV = rsaKV;
	}

	public String getPcid() {
		return pcid;
	}

	public void setPcid(String pcid) {
		this.pcid = pcid;
	}
	
	
	
}
