package com.zml.biz.controller;
import com.zml.base.entity.ZmlUserViewHistoryEntity;
import com.zml.service.ZmlUserViewHistoryServiceI;
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

import java.io.OutputStream;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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
 * @Description: 用户浏览记录
 * @author onlineGenerator
 * @date 2017-01-01 13:59:37
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zmlUserViewHistoryController")
public class ZmlUserViewHistoryController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlUserViewHistoryController.class);

	@Autowired
	private ZmlUserViewHistoryServiceI zmlUserViewHistoryService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 用户浏览记录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml/view_history/zmlUserViewHistoryList");
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
	public void datagrid(ZmlUserViewHistoryEntity zmlUserViewHistory,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlUserViewHistoryEntity.class, dataGrid);
		//查询条件组装器
		com.jce.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zmlUserViewHistory, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlUserViewHistoryService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除用户浏览记录
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlUserViewHistoryEntity zmlUserViewHistory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zmlUserViewHistory = systemService.getEntity(ZmlUserViewHistoryEntity.class, zmlUserViewHistory.getId());
		message = "用户浏览记录删除成功";
		try{
			zmlUserViewHistoryService.delete(zmlUserViewHistory);
			systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "用户浏览记录删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除用户浏览记录
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "用户浏览记录删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlUserViewHistoryEntity zmlUserViewHistory = systemService.getEntity(ZmlUserViewHistoryEntity.class, 
				id
				);
				zmlUserViewHistoryService.delete(zmlUserViewHistory);
				systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "用户浏览记录删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加用户浏览记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlUserViewHistoryEntity zmlUserViewHistory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "用户浏览记录添加成功";
		try{
			zmlUserViewHistoryService.save(zmlUserViewHistory);
			systemService.addLog("", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "用户浏览记录添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新用户浏览记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlUserViewHistoryEntity zmlUserViewHistory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "用户浏览记录更新成功";
		ZmlUserViewHistoryEntity t = zmlUserViewHistoryService.get(ZmlUserViewHistoryEntity.class, zmlUserViewHistory.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zmlUserViewHistory, t);
			zmlUserViewHistoryService.saveOrUpdate(t);
			systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "用户浏览记录更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 用户浏览记录新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlUserViewHistoryEntity zmlUserViewHistory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlUserViewHistory.getId())) {
			zmlUserViewHistory = zmlUserViewHistoryService.getEntity(ZmlUserViewHistoryEntity.class, zmlUserViewHistory.getId());
			req.setAttribute("zmlUserViewHistoryPage", zmlUserViewHistory);
		}
		return new ModelAndView("com/zml/view_history/zmlUserViewHistory-add");
	}
	/**
	 * 用户浏览记录编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlUserViewHistoryEntity zmlUserViewHistory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlUserViewHistory.getId())) {
			zmlUserViewHistory = zmlUserViewHistoryService.getEntity(ZmlUserViewHistoryEntity.class, zmlUserViewHistory.getId());
			req.setAttribute("zmlUserViewHistoryPage", zmlUserViewHistory);
		}
		return new ModelAndView("com/zml/view_history/zmlUserViewHistory-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","zmlUserViewHistoryController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ZmlUserViewHistoryEntity zmlUserViewHistory,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ZmlUserViewHistoryEntity.class, dataGrid);
		com.jce.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zmlUserViewHistory, request.getParameterMap());
		List<ZmlUserViewHistoryEntity> zmlUserViewHistorys = this.zmlUserViewHistoryService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"用户浏览记录");
		modelMap.put(NormalExcelConstants.CLASS,ZmlUserViewHistoryEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("用户浏览记录列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,zmlUserViewHistorys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ZmlUserViewHistoryEntity zmlUserViewHistory,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"用户浏览记录");
    	modelMap.put(NormalExcelConstants.CLASS,ZmlUserViewHistoryEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("用户浏览记录列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<ZmlUserViewHistoryEntity> listZmlUserViewHistoryEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ZmlUserViewHistoryEntity.class,params);
				for (ZmlUserViewHistoryEntity zmlUserViewHistory : listZmlUserViewHistoryEntitys) {
					zmlUserViewHistoryService.save(zmlUserViewHistory);
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
	public List<ZmlUserViewHistoryEntity> list() {
		List<ZmlUserViewHistoryEntity> listZmlUserViewHistorys=zmlUserViewHistoryService.getList(ZmlUserViewHistoryEntity.class);
		return listZmlUserViewHistorys;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlUserViewHistoryEntity task = zmlUserViewHistoryService.get(ZmlUserViewHistoryEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlUserViewHistoryEntity zmlUserViewHistory, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlUserViewHistoryEntity>> failures = validator.validate(zmlUserViewHistory);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlUserViewHistoryService.save(zmlUserViewHistory);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlUserViewHistory.getId();
		URI uri = uriBuilder.path("/rest/zmlUserViewHistoryController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlUserViewHistoryEntity zmlUserViewHistory) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlUserViewHistoryEntity>> failures = validator.validate(zmlUserViewHistory);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlUserViewHistoryService.saveOrUpdate(zmlUserViewHistory);
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
		zmlUserViewHistoryService.deleteEntityById(ZmlUserViewHistoryEntity.class, id);
	}
}
