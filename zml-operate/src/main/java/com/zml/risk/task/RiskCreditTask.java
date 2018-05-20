package com.zml.risk.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.jce.framework.web.system.sms.service.TSSmsServiceI;

/**
 * @ClassName:SmsSendTask 所有信息的发送定时任务类
 */
//@Service("riskCreditTask")
public class RiskCreditTask {
	
	//@Autowired
	private TSSmsServiceI tSSmsService;
	
	/*@Scheduled(cron="0 0/1 * * * ?")*/
	public void run() {
		long start = System.currentTimeMillis();
		com.jce.framework.core.util.LogUtil.info("===================消息中间件定时任务开始===================");
		try {
			tSSmsService.send();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		com.jce.framework.core.util.LogUtil.info("===================消息中间件定时任务结束===================");
		long end = System.currentTimeMillis();
		long times = end - start;
		com.jce.framework.core.util.LogUtil.info("总耗时"+times+"毫秒");
	}
}
