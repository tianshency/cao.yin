package com.zml.app.controller;

import java.net.URI;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.jeecgframework.p3.core.utils.common.StringUtils;
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

import com.jce.framework.core.beanvalidator.BeanValidators;
import com.jce.framework.core.common.exception.BusinessException;
import com.jce.framework.core.common.hibernate.qbc.CriteriaQuery;
import com.jce.framework.core.common.model.json.AjaxJson;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.constant.Globals;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.tag.easyui.TagUtil;
import com.jce.framework.web.system.service.SystemService;
import com.zml.base.entity.ZmlUserAddressEntity;
import com.zml.base.entity.ZmlUserEntity;
import com.zml.base.page.ZmlUserPage;
import com.zml.common.ReMsg;
import com.zml.service.ZmlUserMessageServiceI;
import com.zml.service.ZmlUserServiceI;

/**   
 * @Title: Controller
 * @Description: 用户
 */
@Controller
@RequestMapping("/myUserController")
public class MyUserController extends BaseController{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MyUserController.class);

	@Autowired
	private ZmlUserServiceI zmlUserService;
	@Autowired
	private ZmlUserMessageServiceI zmlUserMessageService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 用户信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml/user/zmlUserList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(ZmlUserEntity zmlUser,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlUserEntity.class, dataGrid);
		//查询条件组装器
		com.jce.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zmlUser);
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlUserService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	

	/**
	 * 用户信息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlUserEntity zmlUser, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlUser.getId())) {
			zmlUser = zmlUserService.getEntity(ZmlUserEntity.class, zmlUser.getId());
			req.setAttribute("zmlUserPage", zmlUser);
		}
		return new ModelAndView("com/zml/user/zmlUser-add");
	}
	
	/**
	 * 用户信息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlUserEntity zmlUser, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlUser.getId())) {
			zmlUser = zmlUserService.getEntity(ZmlUserEntity.class, zmlUser.getId());
			req.setAttribute("zmlUserPage", zmlUser);
		}
		return new ModelAndView("com/zml/user/zmlUser-update");
	}
	
	
 	
	@ResponseBody
	public List<ZmlUserEntity> list() {
		List<ZmlUserEntity> listZmlUsers=zmlUserService.getList(ZmlUserEntity.class);
		return listZmlUsers;
	}
	
	/*@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlUserEntity task = zmlUserService.get(ZmlUserEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}*/
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlUserPage zmlUserPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlUserPage>> failures = validator.validate(zmlUserPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<ZmlUserAddressEntity> zmlUserAddressList =  zmlUserPage.getZmlUserAddressList();
		
		ZmlUserEntity zmlUser = new ZmlUserEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(zmlUser,zmlUserPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		zmlUserService.addMain(zmlUser, zmlUserAddressList);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlUserPage.getId();
		URI uri = uriBuilder.path("/rest/zmlUserController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlUserPage zmlUserPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlUserPage>> failures = validator.validate(zmlUserPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<ZmlUserAddressEntity> zmlUserAddressList =  zmlUserPage.getZmlUserAddressList();
		
		ZmlUserEntity zmlUser = new ZmlUserEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(zmlUser,zmlUserPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		zmlUserService.updateMain(zmlUser, zmlUserAddressList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		ZmlUserEntity zmlUser = zmlUserService.get(ZmlUserEntity.class, id);
		zmlUserService.delMain(zmlUser);
	}
	
	/**
	 * 更新用户信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "/doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlUserEntity zmlUser,ZmlUserPage zmlUserPage, HttpServletRequest request) {
		List<ZmlUserAddressEntity> zmlUserAddressList =  zmlUserPage.getZmlUserAddressList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			zmlUserService.updateMain(zmlUser, zmlUserAddressList);
			systemService.addLog("", "", message, Globals.APP_Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新用户信息失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	
	/**
	 * 转到添加用户地址信息
	 * @return
	 */
	@RequestMapping("/toAddUserAddress")
	public String toAddUserAddress(HttpServletRequest request, HttpServletResponse response) {
		return "/address/address";
	}
	/**
	 * 添加用户地址信息
	 * @return
	 */
	@RequestMapping("/addUserAddress")
	@ResponseBody
	public ReMsg addUserAddress(ZmlUserAddressEntity userAddress, HttpServletRequest request, HttpServletResponse response) {
		ReMsg reMsg = null;
		String message = "添加成功";
		try{
			//String userId = getUserId(request, response);
			ZmlUserEntity user = getUserInfo(request, response);
			//String userId = "1";
			userAddress.setUserId(user.getId());
			userAddress.setOpenId(user.getOpenId());
			if(StringUtils.isNotEmpty(userAddress.getConsignee())){
				userAddress.setConsignee(java.net.URLDecoder.decode(userAddress.getConsignee(),"UTF-8"));
			}
			if(StringUtils.isNotEmpty(userAddress.getAddress())){
				userAddress.setAddress(java.net.URLDecoder.decode(userAddress.getAddress(),"UTF-8"));
			}
			zmlUserService.addUserAddress(userAddress);
			this.setLocationToRedis(user.getOpenId(), "APP", user.getId());
			systemService.addLog("", "", message, Globals.APP_Log_Type_INSERT, Globals.Log_Leavel_INFO);
			reMsg = new ReMsg("", true);
		}catch(Exception e){
			e.printStackTrace();
			reMsg = new ReMsg("添加失败", false);
		}
		return reMsg;
	}
	
	/**
	 * 转到修改用户地址信息
	 * @return
	 */
	@RequestMapping("/toUpdateUserAddress")
	public ModelAndView toUpdateUserAddress(String id, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		ZmlUserAddressEntity zae = zmlUserService.findUserAddressById(id);
		mv.addObject("address",zae);
		mv.setViewName("/address/updateAddress");
		//获取用户信息
		return mv;
	}
	
	/**
	 * 修改用户地址信息
	 * @return
	 */
	@RequestMapping("/updateUserAddress")
	@ResponseBody
	public ReMsg updateUserAddress(ZmlUserAddressEntity userAddress, HttpServletRequest request, HttpServletResponse response) {
		ReMsg reMsg = null;
		String message = "修改成功";
		try{
			/*String userId = getUserId(request, response);
			userAddress.setUserId(userId);*/
			ZmlUserAddressEntity address = zmlUserService.findUserAddressById(userAddress.getId());
			address.setConsignee(java.net.URLDecoder.decode(userAddress.getConsignee(),"UTF-8"));
			address.setAddress(java.net.URLDecoder.decode(userAddress.getAddress(),"UTF-8"));
			address.setPhone(userAddress.getPhone());
			address.setIsDefault(userAddress.getIsDefault());
			zmlUserService.updateUserAddress(address);
			systemService.addLog("", "", message, Globals.APP_Log_Type_INSERT, Globals.Log_Leavel_INFO);
			reMsg = new ReMsg("", true);
		}catch(Exception e){
			e.printStackTrace();
			reMsg = new ReMsg("修改失败", false);
		}
		return reMsg;
	}
	
	/**
	 * 修改用户地址信息
	 * @return
	 */
	@RequestMapping("/showUserAddress")
	@ResponseBody
	public ReMsg showUserAddress(String addrId, HttpServletRequest request) {
		ReMsg reMsg = null;
		String message = "添加成功";
		try{
			/*zmlUserService.updateUserAddress(userAddress);
			systemService.addLog("", "", message, Globals.APP_Log_Type_INSERT, Globals.Log_Leavel_INFO);*/
			reMsg = new ReMsg("", true);
		}catch(Exception e){
			e.printStackTrace();
			reMsg = new ReMsg("添加失败", false);
		}
		return reMsg;
	}
	
	/**
	 * 删除用户地址信息
	 * @return
	 */
	@RequestMapping("/delUserAddress")
	@ResponseBody
	public ReMsg delUserAddress(String addressId, HttpServletRequest request) {
		ReMsg reMsg = null;
		String message = "删除成功";
		try{
			zmlUserService.delUserAddress(addressId);
			systemService.addLog("", "", message, Globals.APP_Log_Type_DEL, Globals.Log_Leavel_INFO);
			reMsg = new ReMsg("", true);
		}catch(Exception e){
			e.printStackTrace();
			reMsg = new ReMsg("删除失败", false);
		}
		return reMsg;
	}
	
	
	/**
	 * 跳转查询地址列表
	 * @return
	 */
	@RequestMapping("/toUserAddressList")
	public ModelAndView toUserAddressList(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/address/addresslist");
		return mv;
	}
	/**
	 * 查询地址列表
	 * @return
	 */
	@RequestMapping("/userAddressList")
	@ResponseBody
	public ReMsg userAddressList(HttpServletRequest req, HttpServletResponse response) {
		//查询-用户地址
		ReMsg reMsg = null;
		
	    try{
	    	String hql0 = "from ZmlUserAddressEntity where 1 = 1 AND userId = ? order by createDate desc";
	    	String userId = getUserId(req, response);
			//String userId = "1";
	    	List<ZmlUserAddressEntity> zmlUserAddressEntityList = systemService.findHql(hql0,userId);
			reMsg = new ReMsg("", true);
			reMsg.add("data", zmlUserAddressEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
			reMsg = new ReMsg("查询失败！", false);
		}
		return reMsg;
	}
	
	/**
	 * 获取用户默认地址，没有默认地址去最新地址
	 * @return
	 */
	//@RequestMapping(params = "/findUserDefaultAddress" , method=RequestMethod.GET )
	@RequestMapping("/findUserDefaultAddress")
	@ResponseBody
	public ReMsg findUserDefaultAddress(HttpServletRequest request, HttpServletResponse response) {
		ReMsg reMsg = null;
		String message = "成功";
		try{
			String userId = getUserId(request, response) ;
			ZmlUserAddressEntity address = zmlUserService.findUserAddressByUserId(userId);
			//systemService.addLog("", "", message, Globals.APP_Log_Type_INSERT, Globals.Log_Leavel_INFO);
			reMsg = new ReMsg("", true);
			reMsg.add("address", address);
		}catch(Exception e){
			e.printStackTrace();
			reMsg = new ReMsg("查询失败", false);
		}
		return reMsg;
	}
	/**
	 * 根据ID 查询地址详情
	 * @return
	 */
	//@RequestMapping(params = "/findAddressById" , method=RequestMethod.GET )
	@RequestMapping("/findAddressById")
	@ResponseBody
	public ReMsg findAddressById(String addrId, HttpServletRequest request) {
		ReMsg reMsg = null;
		String message = "成功";
		try{
			ZmlUserAddressEntity address = zmlUserService.getEntity(ZmlUserAddressEntity.class, addrId);
			//systemService.addLog("", "", message, Globals.APP_Log_Type_INSERT, Globals.Log_Leavel_INFO);
			reMsg = new ReMsg("", true);
			reMsg.add("address", address);
		}catch(Exception e){
			e.printStackTrace();
			reMsg = new ReMsg("查询失败", false);
		}
		return reMsg;
	}
	
	/**
	 * 根据ID 查询消息详情
	 * @return
	 */
	//@RequestMapping(params = "/findAddressById" , method=RequestMethod.GET )
	@RequestMapping("/findMessageByUserId")
	@ResponseBody
	public ReMsg findMessageByUserId(HttpServletRequest request, HttpServletResponse response) {
		ReMsg reMsg = null;
		String message = "成功";
		try{
			String userId = getUserId(request, response);
			List list = zmlUserMessageService.findMessageByParam(userId, "0");
			//systemService.addLog("", "", message, Globals.APP_Log_Type_INSERT, Globals.Log_Leavel_INFO);
			reMsg = new ReMsg("", true);
			reMsg.add("data", list);
		}catch(Exception e){
			e.printStackTrace();
			reMsg = new ReMsg("查询失败", false);
		}
		return reMsg;
	}
	
	/**
	 * 判断用户地址位置是否已经同意保存过
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/isAgreementLocation")
	@ResponseBody
	public ReMsg isAgreementLocationByOpenId(ZmlUserAddressEntity userAddress,HttpServletRequest request, HttpServletResponse response) {
		ZmlUserEntity user = getUserInfo(request, response);
		String isFlag = this.getLocationToRedis(user.getOpenId(), "APP", user.getId());
		ReMsg reMsg = new ReMsg("", true);
		reMsg.add("data", isFlag);
		return reMsg;
	}
	
}
