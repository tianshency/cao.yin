package com.zml.app.controller;

//import java.beans.PropertyEditorSupport;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.InitBinder;

import com.zml.cache.RedisUtilTool;
import com.zml.user.entity.CfUserEntity;
import com.zml.util.CookieUtil;



/**
 * 控制器支持类
 */
public abstract class BaseController {
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	//@Autowired
	//public RedisUtil redisUtil;
	
	@Autowired
	public RedisUtilTool redisUtilTool;
	

	public RedisUtilTool getRedisUtilTool() {
		return redisUtilTool;
	}

	public void setRedisUtilTool(RedisUtilTool redisUtilTool) {
		this.redisUtilTool = redisUtilTool;
	}

	/**
	 * 初始化数据绑定 1. 将所有传递进来的String进行HTML编码，防止XSS攻击 2. 将字段中Date类型转换为String类型
	 */
	/*@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}

			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
	}*/
	//得到登录loginKey
	protected String getLoginKey(HttpServletRequest request, HttpServletResponse response) {
		String loginKey = CookieUtil.getSessionID(request, response);
		return loginKey;
	}
	//获取登录人角色
	protected String getRoleCode(HttpServletRequest request , HttpServletResponse response) {
		return (String)redisUtilTool.get(getLoginKey(request, response) + "roleCode");
	}
	//获取登录用户
	protected CfUserEntity getUserInfo(HttpServletRequest request, HttpServletResponse response) {
		Object value=null;
		try {
			value = redisUtilTool.get(getLoginKey(request, response));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (value != null) {
			return (CfUserEntity) value;
		} else
			return null;
	}
	
	/**
	 * get方式写json数据
	 * 
	 * @param response
	 * @param json
	 * @return
	 */
	protected String printgetjson(int errorcode, String errormsg, HttpServletResponse response) {
		response.setContentType("application/json");
		// response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		java.io.PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(String.format("{\"errorcode\":%d,\"errormsg\":\"%s\"}", errorcode, errormsg));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
		return null;
	}

	protected String g(String name,HttpServletRequest request){
		return request.getParameter(name) == null ? "" : request.getParameter(name);
	}
	
	public static String getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) return null;
		for (int i = 0; i < cookies.length; i++) {
			System.out.println("cookies[i].getName:" + cookies[i].getName());
			if (cookies[i].getName().equals(name)) {
				return cookies[i].getValue();
			}
		}
		return null;
	}
}
