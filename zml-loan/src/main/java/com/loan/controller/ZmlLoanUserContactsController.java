package com.loan.controller;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.log4j.Logger;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;

import com.jce.framework.core.beanvalidator.BeanValidators;
import com.jce.framework.core.common.controller.BaseController;
import com.jce.framework.core.common.exception.BusinessException;
import com.jce.framework.core.common.hibernate.qbc.CriteriaQuery;
import com.jce.framework.core.common.model.json.AjaxJson;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.constant.Globals;
import com.jce.framework.core.extend.hqlsearch.HqlGenerateUtil;
import com.jce.framework.core.util.ExceptionUtil;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.ResourceUtil;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.tag.easyui.TagUtil;
import com.jce.framework.web.system.pojo.base.TSUser;
import com.jce.framework.web.system.service.SystemService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.zml.base.loan.entity.ZmlLoanUserContactsEntity;
import com.zml.enums.loan.ApprovalFlag;
import com.zml.loan_service.ZmlLoanUserContactsServiceI;

/**   
 * @Title: Controller  
 * @Description: 借款用户联系人
 * @author onlineGenerator
 * @date 2017-03-01 22:20:50
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zmlLoanUserContactsController")
public class ZmlLoanUserContactsController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlLoanUserContactsController.class);

	@Autowired
	private ZmlLoanUserContactsServiceI zmlLoanUserContactsService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 借款用户联系人列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml_loan/user_contacts/zmlLoanUserContactsList");
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
	public void datagrid(ZmlLoanUserContactsEntity zmlLoanUserContacts,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlLoanUserContactsEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, zmlLoanUserContacts, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlLoanUserContactsService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除借款用户联系人
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlLoanUserContactsEntity zmlLoanUserContacts, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zmlLoanUserContacts = systemService.getEntity(ZmlLoanUserContactsEntity.class, zmlLoanUserContacts.getId());
		message = "借款用户联系人删除成功";
		try{
			zmlLoanUserContactsService.delete(zmlLoanUserContacts);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "借款用户联系人删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除借款用户联系人
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "借款用户联系人删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlLoanUserContactsEntity zmlLoanUserContacts = systemService.getEntity(ZmlLoanUserContactsEntity.class, 
				id
				);
				zmlLoanUserContactsService.delete(zmlLoanUserContacts);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "借款用户联系人删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加借款用户联系人
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlLoanUserContactsEntity zmlLoanUserContacts, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "借款用户联系人添加成功";
		try{
			zmlLoanUserContactsService.save(zmlLoanUserContacts);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "借款用户联系人添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新借款用户联系人
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlLoanUserContactsEntity zmlLoanUserContacts, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "借款用户联系人更新成功";
		ZmlLoanUserContactsEntity t = zmlLoanUserContactsService.get(ZmlLoanUserContactsEntity.class, zmlLoanUserContacts.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zmlLoanUserContacts, t);
			zmlLoanUserContactsService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "借款用户联系人更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 审批借款用户联系人
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doApprove")
	@ResponseBody
	public AjaxJson doApprove(ZmlLoanUserContactsEntity zmlLoanUserContacts, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "借款用户联系人审批成功!";
		if(zmlLoanUserContacts.getApprovalFlag() == null || "".equals(zmlLoanUserContacts.getApprovalFlag())){
			j.setSuccess(false);
			j.setMsg("审批标识必填！");
			return j;
		}
		if(zmlLoanUserContacts.getApprovalOpinion() == null 
				|| "".equals(zmlLoanUserContacts.getApprovalOpinion())){
			j.setSuccess(false);
			j.setMsg("拒绝审批，审批意见必填！");
			return j;
		}
		TSUser user = ResourceUtil.getSessionUserName();
		zmlLoanUserContacts.setApprovalUserId(user.getId());
		zmlLoanUserContacts.setApprovalDate(new Date());
		ZmlLoanUserContactsEntity t = zmlLoanUserContactsService.get(ZmlLoanUserContactsEntity.class, zmlLoanUserContacts.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zmlLoanUserContacts, t);
			zmlLoanUserContactsService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "借款用户联系人审批失败!";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 借款用户联系人新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlLoanUserContactsEntity zmlLoanUserContacts, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanUserContacts.getId())) {
			zmlLoanUserContacts = zmlLoanUserContactsService.getEntity(ZmlLoanUserContactsEntity.class, zmlLoanUserContacts.getId());
			req.setAttribute("zmlLoanUserContactsPage", zmlLoanUserContacts);
		}
		return new ModelAndView("com/zml_loan/user_contacts/zmlLoanUserContacts-add");
	}
	/**
	 * 借款用户联系人编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlLoanUserContactsEntity zmlLoanUserContacts, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanUserContacts.getId())) {
			zmlLoanUserContacts = zmlLoanUserContactsService.getEntity(ZmlLoanUserContactsEntity.class, zmlLoanUserContacts.getId());
			req.setAttribute("zmlLoanUserContactsPage", zmlLoanUserContacts);
		}
		return new ModelAndView("com/zml_loan/user_contacts/zmlLoanUserContacts-update");
	}
	
	/**
	 * 借款用户联系人审批页面跳转
	 * @return
	 */
	@RequestMapping(params = "goContactsApprove")
	public ModelAndView goContactsApprove(ZmlLoanUserContactsEntity zmlLoanUserContacts, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanUserContacts.getId())) {
			zmlLoanUserContacts = zmlLoanUserContactsService.getEntity(ZmlLoanUserContactsEntity.class, zmlLoanUserContacts.getId());
			req.setAttribute("zmlLoanUserContactsPage", zmlLoanUserContacts);
		}
		return new ModelAndView("com/zml_loan/user_contacts/contactsApprove");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","zmlLoanUserContactsController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ZmlLoanUserContactsEntity zmlLoanUserContacts,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ZmlLoanUserContactsEntity.class, dataGrid);
		HqlGenerateUtil.installHql(cq, zmlLoanUserContacts, request.getParameterMap());
		List<ZmlLoanUserContactsEntity> zmlLoanUserContactss = this.zmlLoanUserContactsService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"借款用户联系人");
		modelMap.put(NormalExcelConstants.CLASS,ZmlLoanUserContactsEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("借款用户联系人列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,zmlLoanUserContactss);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ZmlLoanUserContactsEntity zmlLoanUserContacts,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"借款用户联系人");
    	modelMap.put(NormalExcelConstants.CLASS,ZmlLoanUserContactsEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("借款用户联系人列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
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
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<ZmlLoanUserContactsEntity> listZmlLoanUserContactsEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ZmlLoanUserContactsEntity.class,params);
				for (ZmlLoanUserContactsEntity zmlLoanUserContacts : listZmlLoanUserContactsEntitys) {
					zmlLoanUserContactsService.save(zmlLoanUserContacts);
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
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ZmlLoanUserContactsEntity> list() {
		List<ZmlLoanUserContactsEntity> listZmlLoanUserContactss=zmlLoanUserContactsService.getList(ZmlLoanUserContactsEntity.class);
		return listZmlLoanUserContactss;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlLoanUserContactsEntity task = zmlLoanUserContactsService.get(ZmlLoanUserContactsEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlLoanUserContactsEntity zmlLoanUserContacts, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanUserContactsEntity>> failures = validator.validate(zmlLoanUserContacts);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlLoanUserContactsService.save(zmlLoanUserContacts);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlLoanUserContacts.getId();
		URI uri = uriBuilder.path("/rest/zmlLoanUserContactsController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlLoanUserContactsEntity zmlLoanUserContacts) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanUserContactsEntity>> failures = validator.validate(zmlLoanUserContacts);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlLoanUserContactsService.saveOrUpdate(zmlLoanUserContacts);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		zmlLoanUserContactsService.deleteEntityById(ZmlLoanUserContactsEntity.class, id);
	}
}
