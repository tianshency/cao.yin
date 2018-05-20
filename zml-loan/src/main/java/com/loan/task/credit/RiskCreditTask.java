package com.loan.task.credit;

import org.springframework.stereotype.Service;

import com.jce.framework.core.util.LogUtil;
//风控授信额度 定时任务
@Service("riskCreditTask")
public class RiskCreditTask {
	/*@Scheduled(cron="0 0/1 * * * ?")*/
	public void run() {
		System.out.println("RiskCreditTask.run...");
		long start = System.currentTimeMillis();
		LogUtil.info("===================风控授信额度 定时任务开始===================");
		try {
			
		} catch (Exception e) {
			//e.printStackTrace();
		}
		LogUtil.info("===================风控授信额度 定时任务结束===================");
		long end = System.currentTimeMillis();
		long times = end - start;
		com.jce.framework.core.util.LogUtil.info("总耗时"+times+"毫秒");
	}
}
