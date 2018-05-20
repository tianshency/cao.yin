package com.zml.util;

import java.util.Random;
import java.util.UUID;

public class GenerateNo {
	
	private static long orderNum = 0l;  
    private static String date ;  
    
	public static void main(String[] args) {
		String rs = getRandomNum(4);
		System.out.println("rs==" + rs);
	}
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
    /** 
     * 生成随机码
     * @return 
     */  
    public static synchronized String getRandom(int length) {
    	StringBuffer code = new StringBuffer();
		for(int i=0; i<length; i++){
			code.append(randomChar());
		}  
        return code.toString();  
    }
    
    private static char randomChar(){
		Random r = new Random();
		String s = "ABCDEFGHJKLMNPRSTUVWXYZ123456789";
		return s.charAt(r.nextInt(s.length()));
	}
    
    /** 
     * 生成存数字的随机码
     * @return 
     */  
    public static synchronized String getRandomNum(int length) {
    	StringBuffer code = new StringBuffer();
		for(int i=0; i<length; i++){
			code.append(randomNum());
		}  
        return code.toString();  
    }
    
    private static char randomNum(){
		Random r = new Random();
		String s = "0123456789";
		return s.charAt(r.nextInt(s.length()));
	}
}
