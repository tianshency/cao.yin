package com.zml.test;

import com.zml.cache.RedisUtilTool;

public class BorrowObject implements Runnable {  
    private RedisUtilTool redisUtilTool; 
  
    public BorrowObject(RedisUtilTool redisUtilTool) {  
        this.redisUtilTool = redisUtilTool;  
    }  
  
    @Override  
    public void run() {  
    	String currTime = "val:" + System.currentTimeMillis();
    	System.out.println("currTime==" + currTime);
    	redisUtilTool.set(currTime, currTime);
    	System.out.println("--------1-----------");
    	String redisRs = (String)redisUtilTool.get(currTime);
    	System.out.println("redisRs==" + redisRs);
    	System.out.println("--------end-----------");
    } 
}
