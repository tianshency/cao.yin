package com.zml.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**   
 * @Title: Controller
 * @Description: 我的个人信息controller
 *
 */
@Controller
@RequestMapping("/personalController")
public class PersonalController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PersonalController.class);
	/**
	 * 转到我的个人信息页
	 * @param userId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toPersonalPage")
	public String toPersonalPage(HttpServletRequest request, HttpServletResponse response){
		return "personal/personalcenter";
	}
}
