package com.zml.loan_tools.common;

import java.math.BigDecimal;

/**
 * 定义全局常量
 * @author wh
 *
 */
public class GlobalConstants {
	
	/**
	 * 模块名称
	 */
	public static final String WD_SCHEMA = "wd_dev";
	public static final String FX_SCHEMA = "fx_dev";
	public static final String DASHBOARD = "dashboard";
	public static final String LOGIN = "login";
	public static final String USER_MNG = "userMng"; // 测试用
	public static final String LOAN_CALCULATE = "calcu";//贷款试算
	
	public static final String RISKMNG = "riskmng"; // 风险管理
	public static final String ANTIFRAUD = "antiFraud";// 反欺诈
	
	public static final String WORKFLOW_MONITOR = "wfmonitor";//流程监控
	
	/** 客户编号生成规则  **/
	public static final String CUSTOMER_ENTERPRISE_KEY = "0";//企贷
	public static final String CUSTOMER_PERSONAL_KEY = "1";//个贷
	public static final String CUSTOMER_THREE_ORG_KEY="2";//三方机构
	
	/** 月数 */
	public static final BigDecimal MCNT = new BigDecimal(12);

	/** 天数 */
	public static final BigDecimal DCNT = new BigDecimal(360);

	/** 时间格式 yyyy-MM-dd */
	public static String DATE_FORMAT = "yyyy-MM-dd";
	
	public static final int MAXLOGERRTIME = 3;// 允许登录输入密码错误次数

}