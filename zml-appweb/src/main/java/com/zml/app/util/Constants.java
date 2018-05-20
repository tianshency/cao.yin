package com.zml.app.util;

/**
 * 常量
 * @author herosky
 *
 */
public class Constants {
	/**
	 * APPID
	 */
	//public static String APPID = "wx4294ae45244ca120";(dbh正式号)
	//public static String APPID = "wx97a5ca90e6361310";//(dbhTest号)
	public static String APPID = "wx2df3d99764faa30a";//(ib扶持号)
	/**
	 * SECRET
	 */
	//public static String SECRET = "bcf65d0591c8f27f7b62652fee447193";//(dbh号密码)
	//public static String SECRET = "87f8920b71d1afcfe6881469cdef8685";//(dbhTest号密码)
	public static String SECRET = "c074ea9d4f6760ef8b105ba0c0c4f8a4";//(zml)
	/**
	 * 获取ACCESS_TOKEN接口
	 */
	public static String GET_ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/**
	 * ACCESS_TOKEN有效时间(单位：ms)
	 */
	public static int EFFECTIVE_TIME = 700000;
	/**
	 * conf.properties文件路径
	 */
	public static String CONF_PROPERTIES_PATH = "config/properties/weixin.properties";
	/**
	 * 微信接入token ，用于验证微信接口
	 */
	public static String TOKEN = "dbh_wh";
	
	/**
	 * 获取网页接口的ACCESS_TOKEN
	 * 通过code置换得到，有效期5分钟
	 */
	public static String NET_ACCESSTOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
	
	/**
	 *  微信JSSDK的ticket请求URL地址 
	 */
	public final static String WEIXIN_JSSDK_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi"; 

	/**
	 * 游客电话
	 */
	public static String PASSENGER_TEL = "88888888888";
}
