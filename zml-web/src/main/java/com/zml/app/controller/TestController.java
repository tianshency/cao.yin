package com.zml.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app/appTestController")
public class TestController extends BaseController {
	/**
	 * @return
	 */
	@RequestMapping(params = "index")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("apppage/main/index");
	}
}
