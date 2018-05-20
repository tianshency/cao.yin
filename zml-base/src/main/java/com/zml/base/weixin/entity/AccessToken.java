package com.zml.base.weixin.entity;

/**
 * 微信通用接口凭证
 */
public class AccessToken {
	// 获取到的凭证
	private String token;
	// 凭证有效时间，单位：秒
    private long expiresIn; 

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}
	
	
	   // 获取到的凭证  
    private String accessToken;  
    
    public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}