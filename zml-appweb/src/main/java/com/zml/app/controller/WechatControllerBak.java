package com.zml.app.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zml.app.util.WeixinService;
import com.zml.app.util.WeixinUtil;
import com.zml.service.ZmlUserServiceI;

/**
 * @desc 微信接入与消息处理
 * @author xzl
 * @date 2016/6/28
 */

//@Controller
//@RequestMapping("/wechatController")
public class WechatControllerBak extends BaseController{
	//@Autowired
	private ZmlUserServiceI zmlUserService;
	
	/**
	 * @desc 微信公众平台验证url是否有效使用的接口
	 * @author xzl
	 * @date 2016/6/28
	 */
	@RequestMapping(value="/weixin",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String initWeixinURL(HttpServletRequest request){
		String echostr = request.getParameter("echostr");// 随机字符串
		System.out.println("echostr================"+echostr);
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
		if (WeixinUtil.checkSignature(request) && echostr != null) {
			return echostr;
		}else{
			return "error";
		}
	}
	
	/**
	 * @desc 微信公众号接收的消息，处理后再做相应的回复
	 * @author xzl
	 * @date 2016/6/28
	 */
	@RequestMapping(value="/weixin",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String replyMessage(HttpServletRequest request){
		//仅处理微信服务端发的请求
		if (WeixinUtil.checkSignature(request)) {
			//, weixinUserInfoService
			return WeixinService.processRequest(request,zmlUserService);
		}else{
			return "error";
		}
	}
}
