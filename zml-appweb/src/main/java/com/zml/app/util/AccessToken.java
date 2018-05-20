package com.zml.app.util;

/** 
 * 微信通用接口凭证 
 *  
 * @author zhanglei 
 */  
public class AccessToken {  
    // 获取到的凭证  
    private String accessToken;  
    // 凭证有效时间，单位：s 
    private long expiresIn;  
    
    public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getExpiresIn() {  
        return expiresIn;  
    }  
  
    public void setExpiresIn(long expiresIn) {  
        this.expiresIn = expiresIn;  
    }  
}  