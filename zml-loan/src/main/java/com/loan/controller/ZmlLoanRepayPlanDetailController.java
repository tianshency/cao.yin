package com.loan.controller;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
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

import com.zml.base.loan.entity.ZmlLoanRepayPlanDetailEntity;
import com.zml.loan_service.ZmlLoanRepayPlanDetailServiceI;

/**   
 * @Title: Controller  
 * @Description: 还款计划明细
 * @author onlineGenerator
 * @date 2017-03-01 22:27:11
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zmlLoanRepayPlanDetailController")
public class ZmlLoanRepayPlanDetailController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlLoanRepayPlanDetailController.class);

	@Autowired
	private ZmlLoanRepayPlanDetailServiceI zmlLoanRepayPlanDetailService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 还款计划明细列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml_loan/repay_plan_detail/zmlLoanRepayPlanDetailList");
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
	public void datagrid(ZmlLoanRepayPlanDetailEntity zmlLoanRepayPlanDetail,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlLoanRepayPlanDetailEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, zmlLoanRepayPlanDetail, request.getParameterMap());
		try{
		//自定义追加查询条件
		String query_profitStartDate_begin = request.getParameter("profitStartDate_begin");
		String query_profitStartDate_end = request.getParameter("profitStartDate_end");
		if(StringUtil.isNotEmpty(query_profitStartDate_begin)){
			cq.ge("profitStartDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_profitStartDate_begin));
		}
		if(StringUtil.isNotEmpty(query_profitStartDate_end)){
			cq.le("profitStartDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_profitStartDate_end));
		}
		String query_profitEndDate_begin = request.getParameter("profitEndDate_begin");
		String query_profitEndDate_end = request.getParameter("profitEndDate_end");
		if(StringUtil.isNotEmpty(query_profitEndDate_begin)){
			cq.ge("profitEndDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_profitEndDate_begin));
		}
		if(StringUtil.isNotEmpty(query_profitEndDate_end)){
			cq.le("profitEndDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_profitEndDate_end));
		}
		String query_profitPrincipal_begin = request.getParameter("profitPrincipal_begin");
		String query_profitPrincipal_end = request.getParameter("profitPrincipal_end");
		if(StringUtil.isNotEmpty(query_profitPrincipal_begin)){
			cq.ge("profitPrincipal", Integer.parseInt(query_profitPrincipal_begin));
		}
		if(StringUtil.isNotEmpty(query_profitPrincipal_end)){
			cq.le("profitPrincipal", Integer.parseInt(query_profitPrincipal_end));
		}
		String query_profitInterest_begin = request.getParameter("profitInterest_begin");
		String query_profitInterest_end = request.getParameter("profitInterest_end");
		if(StringUtil.isNotEmpty(query_profitInterest_begin)){
			cq.ge("profitInterest", Integer.parseInt(query_profitInterest_begin));
		}
		if(StringUtil.isNotEmpty(query_profitInterest_end)){
			cq.le("profitInterest", Integer.parseInt(query_profitInterest_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlLoanRepayPlanDetailService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除还款计划明细
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlLoanRepayPlanDetailEntity zmlLoanRepayPlanDetail, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zmlLoanRepayPlanDetail = systemService.getEntity(ZmlLoanRepayPlanDetailEntity.class, zmlLoanRepayPlanDetail.getId());
		message = "还款计划明细删除成功";
		try{
			zmlLoanRepayPlanDetailService.delete(zmlLoanRepayPlanDetail);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "还款计划明细删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除还款计划明细
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "还款计划明细删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlLoanRepayPlanDetailEntity zmlLoanRepayPlanDetail = systemService.getEntity(ZmlLoanRepayPlanDetailEntity.class, 
				id
				);
				zmlLoanRepayPlanDetailService.delete(zmlLoanRepayPlanDetail);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "还款计划明细删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加还款计划明细
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlLoanRepayPlanDetailEntity zmlLoanRepayPlanDetail, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "还款计划明细添加成功";
		try{
			zmlLoanRepayPlanDetailService.save(zmlLoanRepayPlanDetail);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "还款计划明细添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新还款计划明细
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlLoanRepayPlanDetailEntity zmlLoanRepayPlanDetail, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "还款计划明细更新成功";
		ZmlLoanRepayPlanDetailEntity t = zmlLoanRepayPlanDetailService.get(ZmlLoanRepayPlanDetailEntity.class, zmlLoanRepayPlanDetail.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zmlLoanRepayPlanDetail, t);
			zmlLoanRepayPlanDetailService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "还款计划明细更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 还款计划明细新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlLoanRepayPlanDetailEntity zmlLoanRepayPlanDetail, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanRepayPlanDetail.getId())) {
			zmlLoanRepayPlanDetail = zmlLoanRepayPlanDetailService.getEntity(ZmlLoanRepayPlanDetailEntity.class, zmlLoanRepayPlanDetail.getId());
			req.setAttribute("zmlLoanRepayPlanDetailPage", zmlLoanRepayPlanDetail);
		}
		return new ModelAndView("com/zml_loan/repay_plan_detail/zmlLoanRepayPlanDetail-add");
	}
	/**
	 * 还款计划明细编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlLoanRepayPlanDetailEntity zmlLoanRepayPlanDetail, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanRepayPlanDetail.getId())) {
			zmlLoanRepayPlanDetail = zmlLoanRepayPlanDetailService.getEntity(ZmlLoanRepayPlanDetailEntity.class, zmlLoanRepayPlanDetail.getId());
			req.setAttribute("zmlLoanRepayPlanDetailPage", zmlLoanRepayPlanDetail);
		}
		return new ModelAndView("com/zml_loan/repay_plan_detail/zmlLoanRepayPlanDetail-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","zmlLoanRepayPlanDetailController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ZmlLoanRepayPlanDetailEntity zmlLoanRepayPlanDetail,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ZmlLoanRepayPlanDetailEntity.class, dataGrid);
		HqlGenerateUtil.installHql(cq, zmlLoanRepayPlanDetail, request.getParameterMap());
		List<ZmlLoanRepayPlanDetailEntity> zmlLoanRepayPlanDetails = this.zmlLoanRepayPlanDetailService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"还款计划明细");
		modelMap.put(NormalExcelConstants.CLASS,ZmlLoanRepayPlanDetailEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("还款计划明细列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,zmlLoanRepayPlanDetails);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ZmlLoanRepayPlanDetailEntity zmlLoanRepayPlanDetail,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"还款计划明细");
    	modelMap.put(NormalExcelConstants.CLASS,ZmlLoanRepayPlanDetailEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("还款计划明细列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<ZmlLoanRepayPlanDetailEntity> listZmlLoanRepayPlanDetailEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ZmlLoanRepayPlanDetailEntity.class,params);
				for (ZmlLoanRepayPlanDetailEntity zmlLoanRepayPlanDetail : listZmlLoanRepayPlanDetailEntitys) {
					zmlLoanRepayPlanDetailService.save(zmlLoanRepayPlanDetail);
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
	public List<ZmlLoanRepayPlanDetailEntity> list() {
		List<ZmlLoanRepayPlanDetailEntity> listZmlLoanRepayPlanDetails=zmlLoanRepayPlanDetailService.getList(ZmlLoanRepayPlanDetailEntity.class);
		return listZmlLoanRepayPlanDetails;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlLoanRepayPlanDetailEntity task = zmlLoanRepayPlanDetailService.get(ZmlLoanRepayPlanDetailEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlLoanRepayPlanDetailEntity zmlLoanRepayPlanDetail, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanRepayPlanDetailEntity>> failures = validator.validate(zmlLoanRepayPlanDetail);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlLoanRepayPlanDetailService.save(zmlLoanRepayPlanDetail);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlLoanRepayPlanDetail.getId();
		URI uri = uriBuilder.path("/rest/zmlLoanRepayPlanDetailController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlLoanRepayPlanDetailEntity zmlLoanRepayPlanDetail) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanRepayPlanDetailEntity>> failures = validator.validate(zmlLoanRepayPlanDetail);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlLoanRepayPlanDetailService.saveOrUpdate(zmlLoanRepayPlanDetail);
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
		zmlLoanRepayPlanDetailService.deleteEntityById(ZmlLoanRepayPlanDetailEntity.class, id);
	}
}
