package com.zml.biz.controller;
import com.zml.base.entity.ZmlUserEntity;
import com.zml.base.page.ZmlUserPage;
import com.zml.service.ZmlUserServiceI;
import com.zml.base.entity.ZmlUserAddressEntity;
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

import com.jce.framework.core.beanvalidator.BeanValidators;
import com.jce.framework.core.common.controller.BaseController;
import com.jce.framework.core.common.exception.BusinessException;
import com.jce.framework.core.common.hibernate.qbc.CriteriaQuery;
import com.jce.framework.core.common.model.json.AjaxJson;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.constant.Globals;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.tag.easyui.TagUtil;
import com.jce.framework.web.system.pojo.base.TSDepart;
import com.jce.framework.web.system.service.SystemService;
import com.jce.framework.core.util.ExceptionUtil;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.ResourceUtil;

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
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller
 * @Description: 用户信息
 * @author onlineGenerator
 * @date 2017-01-01 13:51:37
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zmlUserController")
public class ZmlUserController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlUserController.class);

	@Autowired
	private ZmlUserServiceI zmlUserService;
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
	 * 删除用户信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlUserEntity zmlUser, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		zmlUser = systemService.getEntity(ZmlUserEntity.class, zmlUser.getId());
		String message = "用户信息删除成功";
		try{
			zmlUserService.delMain(zmlUser);
			systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "用户信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除用户信息
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "用户信息删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlUserEntity zmlUser = systemService.getEntity(ZmlUserEntity.class,
				id
				);
				zmlUserService.delMain(zmlUser);
				systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "用户信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加用户信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlUserEntity zmlUser,ZmlUserPage zmlUserPage, HttpServletRequest request) {
		List<ZmlUserAddressEntity> zmlUserAddressList =  zmlUserPage.getZmlUserAddressList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			zmlUserService.addMain(zmlUser, zmlUserAddressList);
			systemService.addLog("", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "用户信息添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新用户信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlUserEntity zmlUser,ZmlUserPage zmlUserPage, HttpServletRequest request) {
		List<ZmlUserAddressEntity> zmlUserAddressList =  zmlUserPage.getZmlUserAddressList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			zmlUserService.updateMain(zmlUser, zmlUserAddressList);
			systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新用户信息失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
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
	
	
	/**
	 * 加载明细列表[用户地址]
	 * 
	 * @return
	 */
	@RequestMapping(params = "zmlUserAddressList")
	public ModelAndView zmlUserAddressList(ZmlUserEntity zmlUser, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = zmlUser.getId();
		//===================================================================================
		//查询-用户地址
	    String hql0 = "from ZmlUserAddressEntity where 1 = 1 AND uSER_ID = ? ";
	    try{
	    	List<ZmlUserAddressEntity> zmlUserAddressEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("zmlUserAddressList", zmlUserAddressEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/zml/user/zmlUserAddressList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(ZmlUserEntity zmlUser,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(ZmlUserEntity.class, dataGrid);
    	//查询条件组装器
    	com.jce.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zmlUser);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<ZmlUserEntity> list=this.zmlUserService.getListByCriteriaQuery(cq, false);
    	List<ZmlUserPage> pageList=new ArrayList<ZmlUserPage>();
        if(list!=null&&list.size()>0){
        	for(ZmlUserEntity entity:list){
        		try{
        		ZmlUserPage page=new ZmlUserPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            	    Object id0 = entity.getId();
				    String hql0 = "from ZmlUserAddressEntity where 1 = 1 AND uSER_ID = ? ";
        	        List<ZmlUserAddressEntity> zmlUserAddressEntityList = systemService.findHql(hql0,id0);
            		page.setZmlUserAddressList(zmlUserAddressEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"用户信息");
        map.put(NormalExcelConstants.CLASS,ZmlUserPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("用户信息列表", "导出人:Jeecg",
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
				List<ZmlUserPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), ZmlUserPage.class, params);
				ZmlUserEntity entity1=null;
				for (ZmlUserPage page : list) {
					entity1=new ZmlUserEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            zmlUserService.addMain(entity1, page.getZmlUserAddressList());
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
		map.put(NormalExcelConstants.FILE_NAME,"用户信息");
		map.put(NormalExcelConstants.CLASS,ZmlUserPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("用户信息列表", "导出人:"+ ResourceUtil.getSessionUserName().getRealName(),
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
		req.setAttribute("controller_name", "zmlUserController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ZmlUserEntity> list() {
		List<ZmlUserEntity> listZmlUsers=zmlUserService.getList(ZmlUserEntity.class);
		return listZmlUsers;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlUserEntity task = zmlUserService.get(ZmlUserEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}
 	
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
}
