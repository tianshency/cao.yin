package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Test {

	public static void main(String[] args) {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);  
		  for (int i = 0; i < 10; i++) {  
		   final int index = i;  
		   fixedThreadPool.execute(new Runnable() {  
		    public void run() {  
		     try {
		    	 System.out.println("线程名称："+Thread.currentThread().getName() + "开始运行 当前时间：" + getSysCurDateTime());
		    	 System.out.println(index);  
		    	 Thread.sleep(5000);  
		     } catch (InterruptedException e) {  
		    	 e.printStackTrace();  
		     }  
		    }  
		   }); 
		  }
	}
	/**
	 * 获取系统当前时间
	 * @return
	 */
	public static String getSysCurDateTime() {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result=sf.format(new Date());
		return result;
	}
}
