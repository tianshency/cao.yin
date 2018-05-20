package com.zml.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zml.common.ReMsg;

@Controller
@RequestMapping("/youzan")
public class YouzanController extends BaseController {
	/**
	 * 回调函数
	 * @param request
	 * @return 
	 */
	@RequestMapping("/callBack")
	@ResponseBody
	public ReMsg test(HttpServletRequest request,String token){
		ReMsg msg = new ReMsg("", true);
		try {
			redisUtilTool.set("AAA", "CurrTime==" + System.currentTimeMillis());
			String str1 = (String)redisUtilTool.get("AAA");
			System.out.println("str1==" + str1);
		} catch (Exception e) {
			e.printStackTrace();
			msg = new ReMsg("", false);
		}
		return msg;
	}
}
