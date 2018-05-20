package com.zml.test;

import net.sf.json.JSONObject;

public class Test {

	public static void main(String[] args) {
		int a = 100;
		int b = 120;
		double aa = 1.55;
		/*System.out.println("今天天气非常好！");
		int c = a + b;
		int d = 1;
		System.out.println("计算结果C=" + c);
		double aa = 1.55;
		String  ss = "甄义----学习";
		System.out.println(ss);\
		*/
		int sum = calcSum(a, b);
		//System.out.println("sum==" + sum);
		
		String str1 = "{\"sex\":1,\"nickname\":\"小豹子\",\"remark\":\"\",\"city\":\"昌平\",\"country\":\"中国\",\"subscribe_time\":1492531722,\"tagid_list\":[],\"subscribe\":1,\"province\":\"北京\",\"openid\":\"oEXj-whsl-HAlK4tBGDbMmfRGidM\",\"language\":\"zh_CN\",\"groupid\":0,\"headimgurl\":\"\"}";
		
		JSONObject objUserInfo = JSONObject.fromObject(str1);
		System.out.println(objUserInfo.getString("city"));          
		System.out.println(objUserInfo.getString("country"));       
		System.out.println(""+objUserInfo.getInt("groupid"));       
		System.out.println(objUserInfo.getString("headimgurl"));    
		System.out.println(objUserInfo.getString("language"));      
		System.out.println(objUserInfo.getString("nickname"));      
		System.out.println(objUserInfo.getString("openid"));        
		System.out.println(objUserInfo.getString("province"));     
		System.out.println(objUserInfo.getString("remark"));        
		System.out.println(""+objUserInfo.getInt("sex"));           
		System.out.println(""+objUserInfo.getInt("subscribe"));     
		System.out.println(""+objUserInfo.getInt("subscribe_time"));

	}
	
	public static int calcSum(int a1 , int b1){
		return a1 + b1;
	}

}
