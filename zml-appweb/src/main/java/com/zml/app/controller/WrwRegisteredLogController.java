package com.zml.app.controller;
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
import com.zml.base.wrw.entity.WrwRegisteredLogEntity;
import com.zml.service.WrwRegisteredLogServiceI;
import com.zml.service.ZmlUserServiceI;

/**   
 * @Title: Controller  
 * @Description: 注册
 */
@Controller
@RequestMapping("/app/wrwRegisteredLogController")
public class WrwRegisteredLogController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WrwRegisteredLogController.class);

	@Autowired
	private WrwRegisteredLogServiceI wrwRegisteredLogService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private ZmlUserServiceI zmlUserServiceI;


	/**
	 * 注册日志列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml_loan/log/wrwRegisteredLogList");
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
	public void datagrid(WrwRegisteredLogEntity wrwRegisteredLog,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WrwRegisteredLogEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, wrwRegisteredLog, request.getParameterMap());
		try{
		//自定义追加查询条件
		String query_createDate_begin = request.getParameter("createDate_begin");
		String query_createDate_end = request.getParameter("createDate_end");
		if(StringUtil.isNotEmpty(query_createDate_begin)){
			cq.ge("createDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_createDate_begin));
		}
		if(StringUtil.isNotEmpty(query_createDate_end)){
			cq.le("createDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_createDate_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.wrwRegisteredLogService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除注册日志
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WrwRegisteredLogEntity wrwRegisteredLog, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		wrwRegisteredLog = systemService.getEntity(WrwRegisteredLogEntity.class, wrwRegisteredLog.getId());
		message = "注册日志删除成功";
		try{
			wrwRegisteredLogService.delete(wrwRegisteredLog);
			systemService.addLog("/wrwRegisteredLogController/doDel", "",  message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "注册日志删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除注册日志
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "注册日志删除成功";
		try{
			for(String id:ids.split(",")){
				WrwRegisteredLogEntity wrwRegisteredLog = systemService.getEntity(WrwRegisteredLogEntity.class, id);
				wrwRegisteredLogService.delete(wrwRegisteredLog);
				systemService.addLog("/wrwRegisteredLogController/doBatchDel", "",message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "注册日志删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	  
	
	/**
	 * 更新注册日志
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WrwRegisteredLogEntity wrwRegisteredLog, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "注册日志更新成功";
		WrwRegisteredLogEntity t = wrwRegisteredLogService.get(WrwRegisteredLogEntity.class, wrwRegisteredLog.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(wrwRegisteredLog, t);
			wrwRegisteredLogService.saveOrUpdate(t);
			systemService.addLog("/wrwRegisteredLogController/doUpdate", "",message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "注册日志更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 注册日志新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WrwRegisteredLogEntity wrwRegisteredLog, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(wrwRegisteredLog.getId())) {
			wrwRegisteredLog = wrwRegisteredLogService.getEntity(WrwRegisteredLogEntity.class, wrwRegisteredLog.getId());
			req.setAttribute("wrwRegisteredLogPage", wrwRegisteredLog);
		}
		return new ModelAndView("com/zml_loan/log/wrwRegisteredLog-add");
	}
	/**
	 * 注册日志编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WrwRegisteredLogEntity wrwRegisteredLog, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(wrwRegisteredLog.getId())) {
			wrwRegisteredLog = wrwRegisteredLogService.getEntity(WrwRegisteredLogEntity.class, wrwRegisteredLog.getId());
			req.setAttribute("wrwRegisteredLogPage", wrwRegisteredLog);
		}
		return new ModelAndView("com/zml_loan/log/wrwRegisteredLog-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","wrwRegisteredLogController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(WrwRegisteredLogEntity wrwRegisteredLog,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(WrwRegisteredLogEntity.class, dataGrid);
		HqlGenerateUtil.installHql(cq, wrwRegisteredLog, request.getParameterMap());
		List<WrwRegisteredLogEntity> wrwRegisteredLogs = this.wrwRegisteredLogService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"注册日志");
		modelMap.put(NormalExcelConstants.CLASS,WrwRegisteredLogEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("注册日志列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,wrwRegisteredLogs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(WrwRegisteredLogEntity wrwRegisteredLog,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"注册日志");
    	modelMap.put(NormalExcelConstants.CLASS,WrwRegisteredLogEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("注册日志列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<WrwRegisteredLogEntity> listWrwRegisteredLogEntitys = ExcelImportUtil.importExcel(file.getInputStream(),WrwRegisteredLogEntity.class,params);
				for (WrwRegisteredLogEntity wrwRegisteredLog : listWrwRegisteredLogEntitys) {
					wrwRegisteredLogService.save(wrwRegisteredLog);
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
	public List<WrwRegisteredLogEntity> list() {
		List<WrwRegisteredLogEntity> listWrwRegisteredLogs=wrwRegisteredLogService.getList(WrwRegisteredLogEntity.class);
		return listWrwRegisteredLogs;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		WrwRegisteredLogEntity task = wrwRegisteredLogService.get(WrwRegisteredLogEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody WrwRegisteredLogEntity wrwRegisteredLog, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<WrwRegisteredLogEntity>> failures = validator.validate(wrwRegisteredLog);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			wrwRegisteredLogService.save(wrwRegisteredLog);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = wrwRegisteredLog.getId();
		URI uri = uriBuilder.path("/rest/wrwRegisteredLogController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody WrwRegisteredLogEntity wrwRegisteredLog) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<WrwRegisteredLogEntity>> failures = validator.validate(wrwRegisteredLog);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			wrwRegisteredLogService.saveOrUpdate(wrwRegisteredLog);
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
		wrwRegisteredLogService.deleteEntityById(WrwRegisteredLogEntity.class, id);
	}
}
