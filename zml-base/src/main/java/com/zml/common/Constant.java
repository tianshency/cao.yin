package com.zml.common;

import java.io.File;

public class Constant {
	//后台APP登录超时时间  -1没有时间限制
	public static Long APP_LOGIN_TIMEOUT = 900000L;
	//图片总路径
	//public static String IMG_FILE_PATH = "C:" + File.separator + "temp" +File.separator;
	public static String IMG_FILE_PATH = "D:/soft/apache-tomcat-8.0.9/wtpwebapps/" ;
	//public static String IMG_FILE_PATH = "/usr/local/nginx/zmlstatic/";
	//
	public static String WRW_USERIMG_FILE_PATH = "/usr/local/nginx/wrw/";
	//业务图片路径
	public static String IMG_FILE_PATH_ONE = "picture" + File.separator;
	//贷款图片路径
	public static String LOAN_IMG_FILE_PATH_ONE = "loan" + File.separator;
	//静态URL 域名;
	//public static String STATIC_URL = "http://127.0.0.1:8080/zml-loan/";
	//public static String STATIC_URL = "http://192.168.1.7:8080/zml-loan/";
	public static String STATIC_URL = "http://zmlstatic.rzt100.com/";
	//速度之家 路径
	public static String SDZJ_URL = "http://wrw.rzt100.com";
	
	//二级三级奖金 发放标识
	public static boolean SDZJ_BOUNS_S_T_F = true;
		
	//异常提示信息
	public static String EXCEPTION_MSG = "服务器开小差了，请重试！";
	
	//短信验证码长度
	public static int APP_SMS_CODE_LEN = 6;
	
	//短信验证码超时时长
	public static Long APP_SMS_TIMEOUT = 60000L;
		
	//生成文件名随机长度
	public static int FILE_NAME_RAND_LEN = 6;
	//生成小图 宽度
	public static int MINI_IMG_FILE_WIDTH = 300;
	//生成小图高度
	public static int MINI_IMG_FILE_HEIGHT = 0;
	
	//生成大图 宽度
	public static int MAX_IMG_FILE_WIDTH = 800;
	//生成大图高度
	public static int MAX_IMG_FILE_HEIGHT = 800;
	
	//生成大图高度
	public static int RELEASE_INFO_VALID_DATE = 3;
	//申请编号随机长度
	public static int APPLY_NO_LEN = 5;
	//业务编号随机长度
	public static int BIZ_NO_LEN = 5;
	
	//有效结束日期
	public static int RELEASE_INFO_VALIDENDDATE = 30;
	
	//首页顶部轮播最大数量
	public static int TOP_BANNER_NUM_MAX = 5;
	//首页头条轮播最大数量
	public static int HEADLINES_BANNER_NUM_MAX = 2;	
	
	//进入公众号自动推送消息版本
	public static String AUTO_PUSH_V = "0.0.1";	
}
