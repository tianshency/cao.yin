package com.zml.util;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookieUtil {
	private final static Logger logger = LoggerFactory.getLogger(CookieUtil.class);
	//public static final String cookieName="dd_s";
	public static final String cookieName="JSESSIONID";
	/**
	 * 用于cookie标识身份的ticket
	 */
	public static final String IDENTYTICKET="Identyticket";
	/**
	 * 系统一级域名
	 */
	public static final String System_DOMAIN = "test.koudaifm.com,http://127.0.0.1:8080/PocketBroadcast,";
	 /**
     * 取得session ID
     * 方法说明:
     * @param request
     * @param response
     * @return
     */
    public static String getSessionID(HttpServletRequest request, HttpServletResponse response) {
    	
//    	String id_cookieName=(String)redisUtil.get(CookieUtil.IDENTYTICKET);
//    	if(null!=id_cookieName && id_cookieName.length()>0){
//    		cookieName=id_cookieName;
//    	}
        String sessionID = CookieUtil.getCookie(request, cookieName);
        if (sessionID == null) {
            UUID uuid = UUID.randomUUID();
            sessionID = uuid.toString();
        }
        CookieUtil.addCookie(cookieName, sessionID, CookieUtil.System_DOMAIN, -1, response);
        return sessionID;
    }
	
	public static void addCookie(String name, String value, String domain, int maxage, String path, HttpServletResponse response)
	  {
	    Cookie cookie = new Cookie(name, value);
	    if (domain != null) {
	      cookie.setDomain(domain);
	    }
	    cookie.setMaxAge(maxage);
	    cookie.setPath(path);
	    response.addCookie(cookie);
	  }

	  public static void addCookie(String name, String value, String domain, int maxage, HttpServletResponse response)
	  {
	    addCookie(name, value, domain, maxage, "/", response);
	  }

	  public static String getCookie(HttpServletRequest request, String name)
	  {
		
	    Cookie[] cookies = request.getCookies();
	    if (cookies == null) return null;
	    for (int i = 0; i < cookies.length; i++) {
	      if (cookies[i].getName().equals(name)) {
	        return cookies[i].getValue();
	      }
	    }
	    if(request.getParameter(name) != null && !"".equals(request.getParameter(name))){
	    	return request.getParameter(name);
	    }
	    return null;
	  }
}
