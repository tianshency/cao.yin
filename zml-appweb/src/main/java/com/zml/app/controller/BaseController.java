package com.zml.app.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

//import java.beans.PropertyEditorSupport;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jce.framework.web.demo.service.task.WeiXinAccountTokenTask;
import com.zml.app.util.AccessToken;
import com.zml.app.util.Constants;
import com.zml.app.util.WeixinService;
import com.zml.app.util.WeixinUtil;
import com.zml.base.entity.ZmlUserEntity;
import com.zml.base.weixin.entity.WeixinAccountEntity;
import com.zml.cache.RedisUtilTool;
import com.zml.common.WeiXinConstants;
import com.zml.service.ZmlUserServiceI;
import com.zml.util.CookieUtil;

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;



/**
 * 控制器支持类
 */
public abstract class BaseController {
	@Autowired
	private ZmlUserServiceI zmlUserService;
	
	@Autowired
	private WeiXinAccountTokenTask weixinAccountTokenTask;
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
	//获取登录人Id
	protected String getUserId(HttpServletRequest request , HttpServletResponse response) {
		ZmlUserEntity user = (ZmlUserEntity)request.getSession().getAttribute("zmlUser");
		//return (String)redisUtilTool.get(getLoginKey(request, response)+ "_ID");
		return user.getId();
	}
	//获取登录人Id
	protected String getUserOpenId(HttpServletRequest request , HttpServletResponse response) {
		String openId = (String)request.getSession().getAttribute("openId");
		//return (String)redisUtilTool.get(getLoginKey(request, response)+ "_OPENID");
		return openId;
	}
	//获取登录用户
	protected ZmlUserEntity getUserInfo(HttpServletRequest request, HttpServletResponse response) {
		/*
		Object value=null;
		try {
			value = redisUtilTool.get(getLoginKey(request, response));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (value != null) {
			return (ZmlUserEntity) value;
		} else
			return null;
		*/
		ZmlUserEntity user = (ZmlUserEntity)request.getSession().getAttribute("zmlUser");
		return user;
	}
	
	
	//获取登录用户
	protected WeixinAccountEntity getWeixinAccount() {
		Object value=null;
		try {
			value = redisUtilTool.get(WeiXinConstants.WEIXIN_ACCOUNT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (value != null) {
			return (WeixinAccountEntity) value;
		} else{
			//重新获取一次 微信账号信息
			try {
				WeixinAccountEntity account = new WeixinAccountEntity();
				weixinAccountTokenTask.reSetWeiXinToken(account);
				value = redisUtilTool.get(WeiXinConstants.WEIXIN_ACCOUNT);
				if (value != null) {
					return (WeixinAccountEntity) value;
				}
			} catch (Exception e) {
				
			}
		}
		return null;
	}
	/**
	 * get方式写json数据
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
	
	 /**
     * 验证权限
	 * @throws IOException 
     */
    public String  getAuthInfo(HttpServletRequest request,String openId) throws IOException{
    	String token = (String)request.getSession().getAttribute("token");
    	if(StringUtils.isEmpty(openId)||StringUtils.isEmpty(token)){
			return "param error_getAuthInfo!";
		}
    	ZmlUserEntity zmlUser = zmlUserService.findUserByOpenId(openId);
    	if(zmlUser==null){
    		//获得用户信息进行存储
    		zmlUser = saveWeixinUserInfo(openId);
    	}
    	request.getSession().setAttribute("zmlUser", zmlUser);
    	request.getSession().setAttribute("appin_info", Constants.APPID);
    	return "success";
    }
    
    /**
	 * 保存用户信息
	 * @param zmlUser
	 * @param openId
	 * @throws IOException
	 */
	public ZmlUserEntity saveWeixinUserInfo(String openId) throws IOException{
		// 1.获取接口访问凭证
		AccessToken accessToken = WeixinUtil.getAccessToken(Constants.APPID, Constants.SECRET);
		// 2.获取用户基本信息
		logger.info("获取用户基本信息");
		ZmlUserEntity userInfo = WeixinService.getUserInfo(accessToken.getAccessToken(), openId);
		logger.info("保存用户基本信息");
		userInfo = (ZmlUserEntity) zmlUserService.save(userInfo);
		logger.info("保存用户基本信息===="+userInfo.getId());
		return userInfo;
	}
    
    /**
	 * 微信获取用户信息
	 * 获取用户信息 </br>
	 * @param accessToken 接口访问凭证 </br>
	 * @param openId 用户标识 </br>
	 * @return WeixinUserInfo </br>
	 */
	public  ZmlUserEntity getUserInfo(String accessToken, String openId) {
		ZmlUserEntity zmlUser = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		// 获取用户信息
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl,"GET", null);
		if (null != jsonObject) {
			try {
				zmlUser = new ZmlUserEntity();
				// 用户的标识
				zmlUser.setOpenId(jsonObject.getString("openid"));
				// 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
				zmlUser.setSubscribe(jsonObject.getInt("subscribe"));
				// 用户关注时间
				zmlUser.setCreateDate(new Date(jsonObject.getString("subscribe_time")));
				// 昵称
				zmlUser.setNickname(jsonObject.getString("nickname"));
				// 用户的性别（1是男性，2是女性，0是未知）
				zmlUser.setSex(jsonObject.getInt("sex")+"");
				// 用户头像
				zmlUser.setAvatar(jsonObject.getString("headimgurl"));
			} catch (Exception e) {
				if (0 == zmlUser.getSubscribe()) {
					logger.error("用户"+ zmlUser.getOpenId()+"已取消关注");
				} else {
					int errorCode = jsonObject.getInt("errcode");
					String errorMsg = jsonObject.getString("errmsg");
					logger.error("获取用户信息失败 errcode:"+errorCode+" errmsg:"+errorMsg);
				}
			}
		}
		return zmlUser;
	}
	
    
   /* public Map<String,String> getSignature(String token){
		Jedis jedis =this.getJedisPool().getResource();
		Map map =  WeixinUtil.getWxConfig(jedis.get("wx_JSSDKTicket_"+token),this.getRealUrl());
		//map.put("url", this.getUrl());
		//jedis.close();
		returnConnection(jedis);//待测试完成后才能知道是否为正确
		return map;
	}
	*/
	/**
     * 获取分享有的url地址
     * @return
     */
    private  String getRealUrl(){
    	HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        StringBuffer requestUrl = request.getRequestURL();
        System.out.println("------------------------------------------requestUrl=========================="+requestUrl); 
        String queryString = request.getQueryString();
        String url="";
        if(!StringUtils.isEmpty(queryString))
        	url = requestUrl +"?"+queryString;
        else{
        	url = requestUrl.toString();
        }
        
        System.out.println("------------------------------------------url=========================="+url);
        return url;
    } 
    //设置token
    protected void setToken(HttpServletRequest request, String token) {
		request.getSession().setAttribute(token, "1");
	}
    //检查token是否存在
    protected boolean checkToken(HttpServletRequest request, String token) {
		/*String rsStr = (String)request.getSession().getAttribute(token);*/
		if("1".equals(token))
			return true;
		else
			return false;
	}
    //移除token
    protected void removeToken(HttpServletRequest request, String token) {
		request.getSession().removeAttribute(token);
	}
    
    
    //获取签名信息
    public Map<String,String> getSignature(String token){
    	WeixinAccountEntity account =(WeixinAccountEntity)redisUtilTool.get(WeiXinConstants.WEIXIN_ACCOUNT);
    	//System.out.println("account=getAccountname=" + account.getAccountname());
    	//System.out.println("account=getJsapiticket=" + account.getJsapiticket());
    	Map<String, String> map=null;
    	if(account!=null)
    		map = WeixinUtil.getWxConfig(account.getJsapiticket(), this.getRealUrl());
    	return map;
	}
    
    /**
     * 向redis中存放鞭用户是否已经同意获取地理位置
     */
    
    public String getLocationToRedis(String openId,String token,String userId){
    	String isLocation = (String)redisUtilTool.get(token+"-"+openId+"-"+userId+"-location");
    	return isLocation;
    }
    /**
     * 向redis中存放鞭用户是否已经同意获取地理位置
     */
    
    public void setLocationToRedis(String openId,String token,String userId){
    	redisUtilTool.set(token+"-"+openId+"-"+userId+"-location","Y");
    }
	
}
