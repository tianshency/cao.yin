package com.zml.app.controller;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.zml.common.Constant;
import com.zml.common.ReMsg;
import com.zml.user.entity.CfUserAddressEntity;
import com.zml.user.entity.CfUserEntity;
import com.zml.user.page.CfUserPage;
import com.zml.user.service.CfUserServiceI;
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
	private CfUserServiceI cfUserService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

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
			Map rsMap = cfUserService.login(userName, userPwd);
			String code = (String)rsMap.get("code");
			if("1".equals(code)){
				CfUserEntity user = (CfUserEntity)rsMap.get("value");
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
	
	/**
	 * 加载明细列表[用户地址]
	 * 
	 * @return
	 */
	@RequestMapping(params = "userAddressList")
	public ModelAndView cfUserAddressList(CfUserEntity cfUser, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = cfUser.getId();
		//===================================================================================
		//查询-用户地址
	    String hql0 = "from CfUserAddressEntity where 1 = 1 AND uSER_ID = ? ";
	    try{
	    	List<CfUserAddressEntity> cfUserAddressEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("cfUserAddressList", cfUserAddressEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/zml/user/cfUserAddressList");
	}

    
 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<CfUserEntity> list() {
		List<CfUserEntity> listCfUsers=cfUserService.getList(CfUserEntity.class);
		return listCfUsers;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		CfUserEntity task = cfUserService.get(CfUserEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody CfUserPage cfUserPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<CfUserPage>> failures = validator.validate(cfUserPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<CfUserAddressEntity> cfUserAddressList =  cfUserPage.getCfUserAddressList();
		
		CfUserEntity cfUser = new CfUserEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(cfUser,cfUserPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		cfUserService.addMain(cfUser, cfUserAddressList);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = cfUserPage.getId();
		URI uri = uriBuilder.path("/rest/cfUserController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody CfUserPage cfUserPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<CfUserPage>> failures = validator.validate(cfUserPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<CfUserAddressEntity> cfUserAddressList =  cfUserPage.getCfUserAddressList();
		
		CfUserEntity cfUser = new CfUserEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(cfUser,cfUserPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		cfUserService.updateMain(cfUser, cfUserAddressList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		CfUserEntity cfUser = cfUserService.get(CfUserEntity.class, id);
		cfUserService.delMain(cfUser);
	}
	
	private String setLoginKey(CfUserEntity user, HttpServletRequest request,
			HttpServletResponse response) {
		String loginKey = CookieUtil.getSessionID(request, response);
		// 存入缓存
		redisUtilTool.set(loginKey, user, Constant.APP_LOGIN_TIMEOUT);
		return loginKey;
	}
}
