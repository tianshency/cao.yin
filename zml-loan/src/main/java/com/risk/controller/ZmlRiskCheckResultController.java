package com.risk.controller;
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
import com.zml.base.loan.entity.ZmlRiskCheckResultEntity;
import com.zml.loan_risk.ZmlRiskCheckResultServiceI;

/**   
 * @Title: Controller  
 * @Description: 风控检查结果
 * @author onlineGenerator
 * @date 2017-04-02 09:03:22
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zmlRiskCheckResultController")
public class ZmlRiskCheckResultController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlRiskCheckResultController.class);

	@Autowired
	private ZmlRiskCheckResultServiceI zmlRiskCheckResultService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 风控检查结果列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml_loan/check_result/zmlRiskCheckResultList");
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
	public void datagrid(ZmlRiskCheckResultEntity zmlRiskCheckResult,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlRiskCheckResultEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, zmlRiskCheckResult, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlRiskCheckResultService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除风控检查结果
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlRiskCheckResultEntity zmlRiskCheckResult, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zmlRiskCheckResult = systemService.getEntity(ZmlRiskCheckResultEntity.class, zmlRiskCheckResult.getId());
		message = "风控检查结果删除成功";
		try{
			zmlRiskCheckResultService.delete(zmlRiskCheckResult);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "风控检查结果删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除风控检查结果
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "风控检查结果删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlRiskCheckResultEntity zmlRiskCheckResult = systemService.getEntity(ZmlRiskCheckResultEntity.class, 
				id
				);
				zmlRiskCheckResultService.delete(zmlRiskCheckResult);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "风控检查结果删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加风控检查结果
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlRiskCheckResultEntity zmlRiskCheckResult, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "风控检查结果添加成功";
		try{
			zmlRiskCheckResultService.save(zmlRiskCheckResult);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "风控检查结果添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新风控检查结果
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlRiskCheckResultEntity zmlRiskCheckResult, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "风控检查结果更新成功";
		ZmlRiskCheckResultEntity t = zmlRiskCheckResultService.get(ZmlRiskCheckResultEntity.class, zmlRiskCheckResult.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zmlRiskCheckResult, t);
			zmlRiskCheckResultService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "风控检查结果更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 风控检查结果新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlRiskCheckResultEntity zmlRiskCheckResult, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlRiskCheckResult.getId())) {
			zmlRiskCheckResult = zmlRiskCheckResultService.getEntity(ZmlRiskCheckResultEntity.class, zmlRiskCheckResult.getId());
			req.setAttribute("zmlRiskCheckResultPage", zmlRiskCheckResult);
		}
		return new ModelAndView("com/zml_loan/check_result/zmlRiskCheckResult-add");
	}
	/**
	 * 风控检查结果编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlRiskCheckResultEntity zmlRiskCheckResult, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlRiskCheckResult.getId())) {
			zmlRiskCheckResult = zmlRiskCheckResultService.getEntity(ZmlRiskCheckResultEntity.class, zmlRiskCheckResult.getId());
			req.setAttribute("zmlRiskCheckResultPage", zmlRiskCheckResult);
		}
		return new ModelAndView("com/zml_loan/check_result/zmlRiskCheckResult-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","zmlRiskCheckResultController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ZmlRiskCheckResultEntity zmlRiskCheckResult,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ZmlRiskCheckResultEntity.class, dataGrid);
		HqlGenerateUtil.installHql(cq, zmlRiskCheckResult, request.getParameterMap());
		List<ZmlRiskCheckResultEntity> zmlRiskCheckResults = this.zmlRiskCheckResultService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"风控检查结果");
		modelMap.put(NormalExcelConstants.CLASS,ZmlRiskCheckResultEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("风控检查结果列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,zmlRiskCheckResults);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ZmlRiskCheckResultEntity zmlRiskCheckResult,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"风控检查结果");
    	modelMap.put(NormalExcelConstants.CLASS,ZmlRiskCheckResultEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("风控检查结果列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<ZmlRiskCheckResultEntity> listZmlRiskCheckResultEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ZmlRiskCheckResultEntity.class,params);
				for (ZmlRiskCheckResultEntity zmlRiskCheckResult : listZmlRiskCheckResultEntitys) {
					zmlRiskCheckResultService.save(zmlRiskCheckResult);
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
	public List<ZmlRiskCheckResultEntity> list() {
		List<ZmlRiskCheckResultEntity> listZmlRiskCheckResults=zmlRiskCheckResultService.getList(ZmlRiskCheckResultEntity.class);
		return listZmlRiskCheckResults;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlRiskCheckResultEntity task = zmlRiskCheckResultService.get(ZmlRiskCheckResultEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlRiskCheckResultEntity zmlRiskCheckResult, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlRiskCheckResultEntity>> failures = validator.validate(zmlRiskCheckResult);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlRiskCheckResultService.save(zmlRiskCheckResult);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlRiskCheckResult.getId();
		URI uri = uriBuilder.path("/rest/zmlRiskCheckResultController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlRiskCheckResultEntity zmlRiskCheckResult) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlRiskCheckResultEntity>> failures = validator.validate(zmlRiskCheckResult);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlRiskCheckResultService.saveOrUpdate(zmlRiskCheckResult);
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
		zmlRiskCheckResultService.deleteEntityById(ZmlRiskCheckResultEntity.class, id);
	}
}
