package com.jce.framework.web.demo.ws.test;

import javax.jws.WebService;

@WebService
public interface JeecgWServiceI {

	public String say(String context);
	
	public String sayHello();
	
	public void sayUI();
	
	
}
