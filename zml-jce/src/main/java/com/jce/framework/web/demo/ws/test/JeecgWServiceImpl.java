package com.jce.framework.web.demo.ws.test;

import com.jce.framework.core.util.LogUtil;

public class JeecgWServiceImpl implements JeecgWServiceI {

	@Override
	public String say(String context) {
		// TODO Auto-generated method stub
		return "you say context:"+context;
	}

	@Override
	public String sayHello() {
		// TODO Auto-generated method stub
		return "sayHello";
	}

	@Override
	public void sayUI() {
		// TODO Auto-generated method stub
		LogUtil.info("UI");
	}

}
