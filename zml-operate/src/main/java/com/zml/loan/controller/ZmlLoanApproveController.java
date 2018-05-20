package com.zml.loan.controller;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
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

import com.zml.base.loan.entity.ZmlLoanApproveEntity;
import com.zml.loan_service.ZmlLoanApproveServiceI;

/**   
 * @Title: Controller  
 * @Description: 借款审批结果
 */
@Controller
@RequestMapping("/zmlLoanApproveController")
public class ZmlLoanApproveController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlLoanApproveController.class);

	@Autowired
	private ZmlLoanApproveServiceI zmlLoanApproveService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 借款审批结果列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml_loan/approve/zmlLoanApproveList");
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
	public void datagrid(ZmlLoanApproveEntity zmlLoanApprove,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlLoanApproveEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, zmlLoanApprove, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlLoanApproveService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除借款审批结果
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlLoanApproveEntity zmlLoanApprove, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zmlLoanApprove = systemService.getEntity(ZmlLoanApproveEntity.class, zmlLoanApprove.getId());
		message = "借款审批结果删除成功";
		try{
			zmlLoanApproveService.delete(zmlLoanApprove);
			systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "借款审批结果删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除借款审批结果
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "借款审批结果删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlLoanApproveEntity zmlLoanApprove = systemService.getEntity(ZmlLoanApproveEntity.class, 
				id
				);
				zmlLoanApproveService.delete(zmlLoanApprove);
				systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "借款审批结果删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加借款审批结果
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlLoanApproveEntity zmlLoanApprove, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "借款审批结果添加成功";
		try{
			zmlLoanApproveService.save(zmlLoanApprove);
			systemService.addLog("", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "借款审批结果添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新借款审批结果
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlLoanApproveEntity zmlLoanApprove, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "借款审批结果更新成功";
		ZmlLoanApproveEntity t = zmlLoanApproveService.get(ZmlLoanApproveEntity.class, zmlLoanApprove.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zmlLoanApprove, t);
			zmlLoanApproveService.saveOrUpdate(t);
			systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "借款审批结果更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 借款审批结果新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlLoanApproveEntity zmlLoanApprove, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanApprove.getId())) {
			zmlLoanApprove = zmlLoanApproveService.getEntity(ZmlLoanApproveEntity.class, zmlLoanApprove.getId());
			req.setAttribute("zmlLoanApprovePage", zmlLoanApprove);
		}
		return new ModelAndView("com/zml_loan/approve/zmlLoanApprove-add");
	}
	/**
	 * 借款审批结果编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlLoanApproveEntity zmlLoanApprove, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanApprove.getId())) {
			zmlLoanApprove = zmlLoanApproveService.getEntity(ZmlLoanApproveEntity.class, zmlLoanApprove.getId());
			req.setAttribute("zmlLoanApprovePage", zmlLoanApprove);
		}
		return new ModelAndView("com/zml_loan/approve/zmlLoanApprove-update");
	}
	
	/**
	 * 跳转 借款审批页面
	 * @return
	 */
	@RequestMapping(params = "goLoanApprove")
	public ModelAndView goLoanApprove(ZmlLoanApproveEntity zmlLoanApprove, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanApprove.getId())) {
			zmlLoanApprove = zmlLoanApproveService.getEntity(ZmlLoanApproveEntity.class, zmlLoanApprove.getId());
			req.setAttribute("zmlLoanApprovePage", zmlLoanApprove);
		}
		ModelAndView mv =  new ModelAndView();
		mv.setViewName("com/zml_loan/approve/zmlLoanApprove");
		
		return mv;
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","zmlLoanApproveController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ZmlLoanApproveEntity zmlLoanApprove,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ZmlLoanApproveEntity.class, dataGrid);
		HqlGenerateUtil.installHql(cq, zmlLoanApprove, request.getParameterMap());
		List<ZmlLoanApproveEntity> zmlLoanApproves = this.zmlLoanApproveService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"借款审批结果");
		modelMap.put(NormalExcelConstants.CLASS,ZmlLoanApproveEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("借款审批结果列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,zmlLoanApproves);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ZmlLoanApproveEntity zmlLoanApprove,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"借款审批结果");
    	modelMap.put(NormalExcelConstants.CLASS,ZmlLoanApproveEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("借款审批结果列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<ZmlLoanApproveEntity> listZmlLoanApproveEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ZmlLoanApproveEntity.class,params);
				for (ZmlLoanApproveEntity zmlLoanApprove : listZmlLoanApproveEntitys) {
					zmlLoanApproveService.save(zmlLoanApprove);
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
	public List<ZmlLoanApproveEntity> list() {
		List<ZmlLoanApproveEntity> listZmlLoanApproves=zmlLoanApproveService.getList(ZmlLoanApproveEntity.class);
		return listZmlLoanApproves;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlLoanApproveEntity task = zmlLoanApproveService.get(ZmlLoanApproveEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlLoanApproveEntity zmlLoanApprove, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanApproveEntity>> failures = validator.validate(zmlLoanApprove);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlLoanApproveService.save(zmlLoanApprove);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlLoanApprove.getId();
		URI uri = uriBuilder.path("/rest/zmlLoanApproveController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlLoanApproveEntity zmlLoanApprove) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanApproveEntity>> failures = validator.validate(zmlLoanApprove);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlLoanApproveService.saveOrUpdate(zmlLoanApprove);
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
		zmlLoanApproveService.deleteEntityById(ZmlLoanApproveEntity.class, id);
	}
}
