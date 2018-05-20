package com.zml.loan.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zml.app.controller.BaseController;
import com.zml.common.Constant;
import com.zml.common.ReMsg;
import com.zml.util.GenerateNo;

//公共 控制类
@Controller
@RequestMapping("/zmlCommonController")
public class ZmlCommonController extends BaseController {
   /*
    * 发送短信验证码 
    * phone手机号
    * smsType业务类型，1：业务申请，2：
    * */
   @RequestMapping("/sendSmsCode")
   @ResponseBody
   public ReMsg sendSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
	   ReMsg msg = null;
	   try{
		   String telephone = request.getParameter("phone");
		   String smsType = request.getParameter("smsType");
		   if(telephone == null || "".equals(telephone)
				   ||smsType == null || "".equals(smsType)){
			   msg = new ReMsg("数据不能为空！", false);
		   }else{
			   //获取短信
			   String smsCode = (String)redisUtilTool.get(telephone + "_" + smsType);
			   //判断在规定时间内是否已存在
			   if(smsCode != null && !"".equals(smsCode.trim())){
				   msg = new ReMsg("短信已发送！", false);
			   }else{
			      //生成验证码
				   String code = GenerateNo.getRandomNum(Constant.APP_SMS_CODE_LEN);
				   redisUtilTool.set(telephone + "_" + smsType, code, Constant.APP_SMS_TIMEOUT);
				   //调用接口 发送短信
				   
				   msg = new ReMsg("短信已发送！", true);
				   //后期注释掉下面 为了调试方便
				   msg.setContent(code);
				}
		    }
	   }catch(Exception e){
		   msg = new ReMsg("短信已发送！", false);
    	   e.printStackTrace();
	   }
	   return msg;
   }
   
   /*
    * 验证短信验证码 
    * phone手机号
    * smsType业务类型，1：业务申请，2：
    * */
   @RequestMapping("/checkSmsCode")
   public ReMsg checkSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
	   ReMsg msg = null;
	   try{
		   String telephone = request.getParameter("phone");
		   String smsType = request.getParameter("smsType");
		   String code = request.getParameter("code");
		   if(telephone == null || "".equals(telephone)
			   ||smsType == null || "".equals(smsType)
			   ||code == null || "".equals(code)){
			   msg = new ReMsg("数据不能为空！", false);
		   }else{
			   //获取短信
			   String smsCode = (String)redisUtilTool.get(telephone + "_" + smsType);
			   if(!code.equals(smsCode)){
		    		msg = new ReMsg("验证码错误！", false);
		       }else{
		    	   msg = new ReMsg("", true);
		    	   redisUtilTool.set(telephone + "_" + smsType, "");
				}
		    }
	   }catch(Exception e){
		   msg = new ReMsg(Constant.EXCEPTION_MSG, false);
    	   e.printStackTrace();
	   }
	   return msg;
   }
}
