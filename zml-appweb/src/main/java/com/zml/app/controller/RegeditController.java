package com.zml.app.controller;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jce.framework.web.system.service.SystemService;
import com.zml.base.wrw.entity.WrwRegisteredLogEntity;
import com.zml.service.WrwRegisteredLogServiceI;
import com.zml.service.ZmlUserServiceI;
import com.zml.util.GenerateNo;

import net.sf.json.JSONObject;

/**   
 * @Title: Controller
 * @Description: 注册
 */
@Controller
@RequestMapping("/appRegeditController")
public class RegeditController {
	private static final Logger logger = Logger.getLogger(RegeditController.class);

	@Autowired
	private WrwRegisteredLogServiceI wrwRegisteredLogService;
	@Autowired
	private SystemService systemService;

	@Autowired
	private ZmlUserServiceI zmlUserServiceI;
	
	/**
	 * 到微任务首页
	 * @return
	 */
	@RequestMapping(params = "toWrwIndex")
	public ModelAndView toWrwIndex(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		return mv;
	}
	/**
	 * 发送手机号
	 * @return
	 */
	@RequestMapping(params = "sendPhone")
	//@ResponseBody
	//public ReMsg sendPhone(HttpServletRequest request) {
	public void sendPhone(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain");  
        response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        //response.setHeader("Access-Control-Allow-Origin: *");
        response.addHeader("Access-Control-Allow-Origin","*");
        
		String phone = request.getParameter("phone");
		String taskId = request.getParameter("tid");
		String source = request.getParameter("source");
		String tjr = request.getParameter("tjr");
		String amount = request.getParameter("amount");
		//ReMsg msg = null;
		WrwRegisteredLogEntity wrwRegisteredLog = new WrwRegisteredLogEntity();
		wrwRegisteredLog.setPhone(phone);
		wrwRegisteredLog.setTaskId(taskId);
		wrwRegisteredLog.setSource(source);
		wrwRegisteredLog.setRecommended(tjr);
		if(amount != null && !"".equals(amount)){
			wrwRegisteredLog.setAmount(new BigDecimal(amount));
		}
		//根据手机号查找用户 是否存在
		String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数  
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			PrintWriter out = response.getWriter();
			Map<String, String> param = new HashMap();
			String token = GenerateNo.uuid();
			param.put("token", token);
			//启动一个线程执行
			boolean rs = wrwRegisteredLogService.sendPhone(wrwRegisteredLog, param);
			if(rs)
				map.put("token", token);
	        map.put("success", "true");  
	        System.out.println("token==" + token);
	        JSONObject resultJSON = JSONObject.fromObject(map); //根据需要拼装json  
	        out.println(jsonpCallback+"("+resultJSON.toString(1,1)+")");//返回jsonp格式数据  
	        out.flush();  
	        out.close();  
		}catch(Exception e){
			e.printStackTrace();
			e.printStackTrace();
		}
	}
	/**
	 * 注册
	 * @return
	 */
	@RequestMapping(params = "doRegedit")
	//@ResponseBody
	//public ReMsg doRegedit(HttpServletRequest request) {
	public void doRegedit(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain");  
        response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        //response.setHeader("Access-Control-Allow-Origin: *");
        response.addHeader("Access-Control-Allow-Origin","*");
        
		String phone = request.getParameter("phone");
		String token = request.getParameter("token");
		String smsCode = request.getParameter("smsCode");
		String pwd = request.getParameter("pwd");
		
		String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数  
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			PrintWriter out = response.getWriter();
			//启动一个线程执行
			Map<String, String> param = new HashMap();
			param.put("token", token);
			param.put("phone", phone);
			param.put("pwd", pwd);
			boolean rs = wrwRegisteredLogService.doRegedit(param);
	    	map.put("success", "true");  
	    	JSONObject resultJSON = JSONObject.fromObject(map); //根据需要拼装json  
	        out.println(jsonpCallback+"("+resultJSON.toString(1,1)+")");//返回jsonp格式数据  
	        out.flush();  
	        out.close();  
		}catch(Exception e){
			e.printStackTrace();
			e.printStackTrace();
		}
	}
}
