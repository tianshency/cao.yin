package com.jce.framework.web.demo.service.impl.test;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.jce.framework.web.demo.service.test.TaskDemoServiceI;
@Service("taskDemoService")
public class TaskDemoServiceImpl implements TaskDemoServiceI {

	
	public void work() {
		com.jce.framework.core.util.LogUtil.info(new Date().getTime());
		com.jce.framework.core.util.LogUtil.info("----------任务测试-------");
	}

}
