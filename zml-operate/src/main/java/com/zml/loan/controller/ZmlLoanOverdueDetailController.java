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

import com.zml.base.loan.entity.ZmlLoanOverdueDetailEntity;
import com.zml.loan_service.ZmlLoanOverdueDetailServiceI;

/**   
 * @Title: Controller  
 * @Description: 逾期明细
 */
@Controller
@RequestMapping("/zmlLoanOverdueDetailController")
public class ZmlLoanOverdueDetailController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlLoanOverdueDetailController.class);

	@Autowired
	private ZmlLoanOverdueDetailServiceI zmlLoanOverdueDetailService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 逾期明细列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml_loan/overdue_detail/zmlLoanOverdueDetailList");
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
	public void datagrid(ZmlLoanOverdueDetailEntity zmlLoanOverdueDetail,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlLoanOverdueDetailEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, zmlLoanOverdueDetail, request.getParameterMap());
		try{
		//自定义追加查询条件
		String query_principal_begin = request.getParameter("principal_begin");
		String query_principal_end = request.getParameter("principal_end");
		if(StringUtil.isNotEmpty(query_principal_begin)){
			cq.ge("principal", Integer.parseInt(query_principal_begin));
		}
		if(StringUtil.isNotEmpty(query_principal_end)){
			cq.le("principal", Integer.parseInt(query_principal_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlLoanOverdueDetailService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除逾期明细
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlLoanOverdueDetailEntity zmlLoanOverdueDetail, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zmlLoanOverdueDetail = systemService.getEntity(ZmlLoanOverdueDetailEntity.class, zmlLoanOverdueDetail.getId());
		message = "逾期明细删除成功";
		try{
			zmlLoanOverdueDetailService.delete(zmlLoanOverdueDetail);
			systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "逾期明细删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除逾期明细
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "逾期明细删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlLoanOverdueDetailEntity zmlLoanOverdueDetail = systemService.getEntity(ZmlLoanOverdueDetailEntity.class, 
				id
				);
				zmlLoanOverdueDetailService.delete(zmlLoanOverdueDetail);
				systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "逾期明细删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加逾期明细
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlLoanOverdueDetailEntity zmlLoanOverdueDetail, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "逾期明细添加成功";
		try{
			zmlLoanOverdueDetailService.save(zmlLoanOverdueDetail);
			systemService.addLog("", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "逾期明细添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新逾期明细
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlLoanOverdueDetailEntity zmlLoanOverdueDetail, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "逾期明细更新成功";
		ZmlLoanOverdueDetailEntity t = zmlLoanOverdueDetailService.get(ZmlLoanOverdueDetailEntity.class, zmlLoanOverdueDetail.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zmlLoanOverdueDetail, t);
			zmlLoanOverdueDetailService.saveOrUpdate(t);
			systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "逾期明细更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 逾期明细新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlLoanOverdueDetailEntity zmlLoanOverdueDetail, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanOverdueDetail.getId())) {
			zmlLoanOverdueDetail = zmlLoanOverdueDetailService.getEntity(ZmlLoanOverdueDetailEntity.class, zmlLoanOverdueDetail.getId());
			req.setAttribute("zmlLoanOverdueDetailPage", zmlLoanOverdueDetail);
		}
		return new ModelAndView("com/zml_loan/overdue_detail/zmlLoanOverdueDetail-add");
	}
	/**
	 * 逾期明细编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlLoanOverdueDetailEntity zmlLoanOverdueDetail, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanOverdueDetail.getId())) {
			zmlLoanOverdueDetail = zmlLoanOverdueDetailService.getEntity(ZmlLoanOverdueDetailEntity.class, zmlLoanOverdueDetail.getId());
			req.setAttribute("zmlLoanOverdueDetailPage", zmlLoanOverdueDetail);
		}
		return new ModelAndView("com/zml_loan/overdue_detail/zmlLoanOverdueDetail-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","zmlLoanOverdueDetailController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ZmlLoanOverdueDetailEntity zmlLoanOverdueDetail,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ZmlLoanOverdueDetailEntity.class, dataGrid);
		HqlGenerateUtil.installHql(cq, zmlLoanOverdueDetail, request.getParameterMap());
		List<ZmlLoanOverdueDetailEntity> zmlLoanOverdueDetails = this.zmlLoanOverdueDetailService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"逾期明细");
		modelMap.put(NormalExcelConstants.CLASS,ZmlLoanOverdueDetailEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("逾期明细列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,zmlLoanOverdueDetails);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ZmlLoanOverdueDetailEntity zmlLoanOverdueDetail,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"逾期明细");
    	modelMap.put(NormalExcelConstants.CLASS,ZmlLoanOverdueDetailEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("逾期明细列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<ZmlLoanOverdueDetailEntity> listZmlLoanOverdueDetailEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ZmlLoanOverdueDetailEntity.class,params);
				for (ZmlLoanOverdueDetailEntity zmlLoanOverdueDetail : listZmlLoanOverdueDetailEntitys) {
					zmlLoanOverdueDetailService.save(zmlLoanOverdueDetail);
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
	public List<ZmlLoanOverdueDetailEntity> list() {
		List<ZmlLoanOverdueDetailEntity> listZmlLoanOverdueDetails=zmlLoanOverdueDetailService.getList(ZmlLoanOverdueDetailEntity.class);
		return listZmlLoanOverdueDetails;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlLoanOverdueDetailEntity task = zmlLoanOverdueDetailService.get(ZmlLoanOverdueDetailEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlLoanOverdueDetailEntity zmlLoanOverdueDetail, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanOverdueDetailEntity>> failures = validator.validate(zmlLoanOverdueDetail);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlLoanOverdueDetailService.save(zmlLoanOverdueDetail);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlLoanOverdueDetail.getId();
		URI uri = uriBuilder.path("/rest/zmlLoanOverdueDetailController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlLoanOverdueDetailEntity zmlLoanOverdueDetail) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanOverdueDetailEntity>> failures = validator.validate(zmlLoanOverdueDetail);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlLoanOverdueDetailService.saveOrUpdate(zmlLoanOverdueDetail);
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
		zmlLoanOverdueDetailService.deleteEntityById(ZmlLoanOverdueDetailEntity.class, id);
	}
}
