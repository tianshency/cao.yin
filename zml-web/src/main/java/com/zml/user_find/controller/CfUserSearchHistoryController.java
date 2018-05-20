package com.zml.user_find.controller;
import com.zml.user_find.entity.CfUserSearchHistoryEntity;
import com.zml.user_find.service.CfUserSearchHistoryServiceI;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import org.jeecgframework.core.util.ExceptionUtil;

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
 * @Description: 搜索历史
 * @date 2016-12-19 19:52:00
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/cfUserSearchHistoryController")
public class CfUserSearchHistoryController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CfUserSearchHistoryController.class);

	@Autowired
	private CfUserSearchHistoryServiceI cfUserSearchHistoryService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 搜索历史列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml/user_find/cfUserSearchHistoryList");
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
	public void datagrid(CfUserSearchHistoryEntity cfUserSearchHistory,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CfUserSearchHistoryEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cfUserSearchHistory, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.cfUserSearchHistoryService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除搜索历史
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CfUserSearchHistoryEntity cfUserSearchHistory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		cfUserSearchHistory = systemService.getEntity(CfUserSearchHistoryEntity.class, cfUserSearchHistory.getId());
		message = "搜索历史删除成功";
		try{
			cfUserSearchHistoryService.delete(cfUserSearchHistory);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "搜索历史删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除搜索历史
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "搜索历史删除成功";
		try{
			for(String id:ids.split(",")){
				CfUserSearchHistoryEntity cfUserSearchHistory = systemService.getEntity(CfUserSearchHistoryEntity.class, 
				id
				);
				cfUserSearchHistoryService.delete(cfUserSearchHistory);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "搜索历史删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加搜索历史
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CfUserSearchHistoryEntity cfUserSearchHistory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "搜索历史添加成功";
		try{
			cfUserSearchHistoryService.save(cfUserSearchHistory);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "搜索历史添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新搜索历史
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CfUserSearchHistoryEntity cfUserSearchHistory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "搜索历史更新成功";
		CfUserSearchHistoryEntity t = cfUserSearchHistoryService.get(CfUserSearchHistoryEntity.class, cfUserSearchHistory.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(cfUserSearchHistory, t);
			cfUserSearchHistoryService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "搜索历史更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 搜索历史新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CfUserSearchHistoryEntity cfUserSearchHistory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cfUserSearchHistory.getId())) {
			cfUserSearchHistory = cfUserSearchHistoryService.getEntity(CfUserSearchHistoryEntity.class, cfUserSearchHistory.getId());
			req.setAttribute("cfUserSearchHistoryPage", cfUserSearchHistory);
		}
		return new ModelAndView("com/zml/user_find/cfUserSearchHistory-add");
	}
	/**
	 * 搜索历史编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CfUserSearchHistoryEntity cfUserSearchHistory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cfUserSearchHistory.getId())) {
			cfUserSearchHistory = cfUserSearchHistoryService.getEntity(CfUserSearchHistoryEntity.class, cfUserSearchHistory.getId());
			req.setAttribute("cfUserSearchHistoryPage", cfUserSearchHistory);
		}
		return new ModelAndView("com/zml/user_find/cfUserSearchHistory-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","cfUserSearchHistoryController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(CfUserSearchHistoryEntity cfUserSearchHistory,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(CfUserSearchHistoryEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cfUserSearchHistory, request.getParameterMap());
		List<CfUserSearchHistoryEntity> cfUserSearchHistorys = this.cfUserSearchHistoryService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"搜索历史");
		modelMap.put(NormalExcelConstants.CLASS,CfUserSearchHistoryEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("搜索历史列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,cfUserSearchHistorys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(CfUserSearchHistoryEntity cfUserSearchHistory,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"搜索历史");
    	modelMap.put(NormalExcelConstants.CLASS,CfUserSearchHistoryEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("搜索历史列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<CfUserSearchHistoryEntity> listCfUserSearchHistoryEntitys = ExcelImportUtil.importExcel(file.getInputStream(),CfUserSearchHistoryEntity.class,params);
				for (CfUserSearchHistoryEntity cfUserSearchHistory : listCfUserSearchHistoryEntitys) {
					cfUserSearchHistoryService.save(cfUserSearchHistory);
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
	public List<CfUserSearchHistoryEntity> list() {
		List<CfUserSearchHistoryEntity> listCfUserSearchHistorys=cfUserSearchHistoryService.getList(CfUserSearchHistoryEntity.class);
		return listCfUserSearchHistorys;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		CfUserSearchHistoryEntity task = cfUserSearchHistoryService.get(CfUserSearchHistoryEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody CfUserSearchHistoryEntity cfUserSearchHistory, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<CfUserSearchHistoryEntity>> failures = validator.validate(cfUserSearchHistory);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			cfUserSearchHistoryService.save(cfUserSearchHistory);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = cfUserSearchHistory.getId();
		URI uri = uriBuilder.path("/rest/cfUserSearchHistoryController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody CfUserSearchHistoryEntity cfUserSearchHistory) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<CfUserSearchHistoryEntity>> failures = validator.validate(cfUserSearchHistory);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			cfUserSearchHistoryService.saveOrUpdate(cfUserSearchHistory);
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
		cfUserSearchHistoryService.deleteEntityById(CfUserSearchHistoryEntity.class, id);
	}
}
