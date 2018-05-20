package com.zml.user.controller;
import com.zml.user.entity.CfUserEntity;
import com.zml.user.service.CfUserServiceI;
import com.zml.user.page.CfUserPage;
import com.zml.user.entity.CfUserAddressEntity;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller
 * @Description: 用户

 * @date 2016-12-19 17:26:08
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/cfUserController")
public class CfUserController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CfUserController.class);

	@Autowired
	private CfUserServiceI cfUserService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 用户列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml/user/cfUserList");
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
	public void datagrid(CfUserEntity cfUser,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CfUserEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cfUser);
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.cfUserService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除用户
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CfUserEntity cfUser, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		cfUser = systemService.getEntity(CfUserEntity.class, cfUser.getId());
		String message = "用户删除成功";
		try{
			cfUserService.delMain(cfUser);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "用户删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除用户
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "用户删除成功";
		try{
			for(String id:ids.split(",")){
				CfUserEntity cfUser = systemService.getEntity(CfUserEntity.class,
				id
				);
				cfUserService.delMain(cfUser);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "用户删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加用户
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CfUserEntity cfUser,CfUserPage cfUserPage, HttpServletRequest request) {
		List<CfUserAddressEntity> cfUserAddressList =  cfUserPage.getCfUserAddressList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			String pwd = PasswordUtil.encrypt(cfUser.getName(), cfUser.getPassword(), PasswordUtil.getStaticSalt());
			cfUser.setPassword(pwd);
			cfUserService.addMain(cfUser, cfUserAddressList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "用户添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新用户
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CfUserEntity cfUser,CfUserPage cfUserPage, HttpServletRequest request) {
		List<CfUserAddressEntity> cfUserAddressList =  cfUserPage.getCfUserAddressList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			cfUserService.updateMain(cfUser, cfUserAddressList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新用户失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 用户新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CfUserEntity cfUser, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cfUser.getId())) {
			cfUser = cfUserService.getEntity(CfUserEntity.class, cfUser.getId());
			req.setAttribute("cfUserPage", cfUser);
		}
		return new ModelAndView("com/zml/user/cfUser-add");
	}
	
	/**
	 * 用户编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CfUserEntity cfUser, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cfUser.getId())) {
			cfUser = cfUserService.getEntity(CfUserEntity.class, cfUser.getId());
			req.setAttribute("cfUserPage", cfUser);
		}
		return new ModelAndView("com/zml/user/cfUser-update");
	}
	
	
	/**
	 * 加载明细列表[用户地址]
	 * 
	 * @return
	 */
	@RequestMapping(params = "cfUserAddressList")
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

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(CfUserEntity cfUser,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(CfUserEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cfUser);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<CfUserEntity> list=this.cfUserService.getListByCriteriaQuery(cq, false);
    	List<CfUserPage> pageList=new ArrayList<CfUserPage>();
        if(list!=null&&list.size()>0){
        	for(CfUserEntity entity:list){
        		try{
        		CfUserPage page=new CfUserPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            	    Object id0 = entity.getId();
				    String hql0 = "from CfUserAddressEntity where 1 = 1 AND uSER_ID = ? ";
        	        List<CfUserAddressEntity> cfUserAddressEntityList = systemService.findHql(hql0,id0);
            		page.setCfUserAddressList(cfUserAddressEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"用户");
        map.put(NormalExcelConstants.CLASS,CfUserPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("用户列表", "导出人:Jeecg",
            "导出信息"));
        map.put(NormalExcelConstants.DATA_LIST,pageList);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

    /**
	 * 通过excel导入数据
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(2);
			params.setNeedSave(true);
			try {
				List<CfUserPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), CfUserPage.class, params);
				CfUserEntity entity1=null;
				for (CfUserPage page : list) {
					entity1=new CfUserEntity();
					String pwd = PasswordUtil.encrypt(entity1.getName(), entity1.getPassword(), PasswordUtil.getStaticSalt());
					entity1.setPassword(pwd);
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            cfUserService.addMain(entity1, page.getCfUserAddressList());
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			}
			return j;
	}
	/**
	* 导出excel 使模板
	*/
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ModelMap map) {
		map.put(NormalExcelConstants.FILE_NAME,"用户");
		map.put(NormalExcelConstants.CLASS,CfUserPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("用户列表", "导出人:"+ ResourceUtil.getSessionUserName().getRealName(),
		"导出信息"));
		map.put(NormalExcelConstants.DATA_LIST,new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	* 导入功能跳转
	*
	* @return
	*/
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "cfUserController");
		return new ModelAndView("common/upload/pub_excel_upload");
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
}
