package com.zml.app.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jce.framework.core.util.StringUtil;
import com.jce.framework.web.system.service.SystemService;
import com.zml.base.entity.ZmlUserEntity;
import com.zml.base.weixin.entity.WeixinAccountEntity;
import com.zml.common.Constant;
import com.zml.common.ReMsg;
import com.zml.service.ZmlUserServiceI;
import com.zml.test.BorrowObject;
import com.zml.util.CookieUtil;


/**   
 * @Title: Controller
 * @Description: 用户
 *
 */
@Controller
@RequestMapping("/appUserController")
public class AppUserController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AppUserController.class);

	@Autowired
	private ZmlUserServiceI zmlUserServiceI;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	@RequestMapping(params = "test")
	@ResponseBody
	public ReMsg test(HttpServletRequest request) {
		ReMsg reMsg = new ReMsg("AppUserController测试 成功了！！！", true);
		List list = new ArrayList();
		list.add("aaaaaaaa");
		list.add("bbbbbbbb");
		list.add("cccccccc");
		reMsg.add("abc", list);
		return reMsg;
	}
	
	/**
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml/");
	}

	/**
	 * 用户登录 
	 * @return
	 */
	@RequestMapping(params = "login" ,method = RequestMethod.POST)
	@ResponseBody
	public ReMsg login(String userName ,String userPwd, String randCode, HttpServletRequest request,
			HttpServletResponse response) {
		ReMsg msg = null;
		//检查验证码
		if (StringUtil.isNotEmpty(userName) || StringUtil.isNotEmpty(userPwd)) {
			Map rsMap = zmlUserServiceI.login(userName, userPwd);
			String code = (String)rsMap.get("code");
			if("1".equals(code)){
				ZmlUserEntity user = (ZmlUserEntity)rsMap.get("value");
				String loginKey = null;
				loginKey = setLoginKey(user, request, response);
				msg = new ReMsg("",true);
				return msg;
			}else if("0".equals(code)){
				msg = new ReMsg("用户名或密码错误！",false);
				return msg;
			}else {
				msg = new ReMsg("用户信息异常！",false);
				return msg;
			}
		}else {
			msg = new ReMsg("用户名或密码不能为空！",false);
			return msg;
		}
	}
	
	private String setLoginKey(ZmlUserEntity user, HttpServletRequest request,
			HttpServletResponse response) {
		String loginKey = CookieUtil.getSessionID(request, response);
		// 存入缓存
		redisUtilTool.set(loginKey, user, Constant.APP_LOGIN_TIMEOUT);
		return loginKey;
	}
	
	
	@RequestMapping(params = "testRedis" ,method = RequestMethod.GET)
	@ResponseBody
	public ReMsg testRedis(Integer count){
		ReMsg msg = null;
		try {
			
			for (int i = 0; i < count.intValue(); i++) {  
	            new Thread(new BorrowObject(redisUtilTool)).start();  
	        }
			WeixinAccountEntity account = getWeixinAccount();
			msg = new ReMsg("测试 redis 成功：次数：" + count.intValue(), true);
			msg.add("wxaccount", account);
		} catch (Exception e) {
			e.printStackTrace();
			msg = new ReMsg("测试 redis 失败：次数：" + count.intValue(), false);
		}
		System.out.println("----------over-----------");
        return msg;
	}
	
	@RequestMapping(params = "refreshCache" ,method = RequestMethod.GET)
	@ResponseBody
	public ReMsg refreshCache(){
		ReMsg msg = null;
		try {
				//查询公告 信息
	    	List<Map<String,Object>> headlinesList = systemService.headlinesList();
	    	redisUtilTool.set("headlinesList", headlinesList);
			msg = new ReMsg("刷新成功！", true);
		} catch (Exception e) {
			e.printStackTrace();
			msg = new ReMsg("刷新异常！", false);
		}
		System.out.println("----------over-----------");
        return msg;
	}
	
	
}
