package com.zml.util;

public class SqStrParamlUtil {

	public static void main(String[] args) {
		String[] strs = {"aaaa","ccccc","ddddd"};
		String rs1 = strArrayToInParam(strs);
		System.out.println("rs1===" + rs1);
		String str1 = "aaa'a ccc;cc d--dd*dd";
		str1 = transactSQLInjection(str1);
		System.out.println("str1==" + str1);
		String[] strs2 = {"aa'a;a","c,cc--c'c","ddd?d,d"};
		transactSQLInjection(strs2);
		System.out.println("strs2==" + strs2[0] + "=====" + strs2[1]+ "=====" + strs2[2]);
	}
	//将数组转为In 查询参数
	public static String strArrayToInParam(String[] strs){
		StringBuffer strSb = new StringBuffer();
		if(strs != null && strs.length > 0){
			strSb.append("(");
			for (int i = 0; i < strs.length; i++) {
				if(i < strs.length -1){
					strSb.append("'" + strs[i] + "',");
				}else{
					strSb.append("'" + strs[i] + "'");
				}
			}
			strSb.append(")");
			return strSb.toString();
		}else{
			return null;
		}
	}
	//非法sql字符转换成空串
	public static String transactSQLInjection(String str) {
       	return str.replaceAll("([';])+|(--)+","");//-->这是原作者的注释，个人不是很赞同。
    }
	
	//非法sql字符转换成空串
	public static void transactSQLInjection(String[] strs) {
		if(strs != null && strs.length > 0){
			for (int i = 0; i < strs.length; i++) {
				strs[i] = transactSQLInjection(strs[i]);
			}
		}
    }
}
