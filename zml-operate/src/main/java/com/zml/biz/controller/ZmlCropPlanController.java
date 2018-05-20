package com.zml.biz.controller;
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
import com.jce.framework.core.util.ExceptionUtil;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.ResourceUtil;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.tag.easyui.TagUtil;
import com.jce.framework.web.system.service.SystemService;
import com.zml.base.entity.ZmlCropPlanEntity;
import com.zml.service.ZmlCropPlanServiceI;

/**   
 * @Title: Controller  
 * @Description: 种植计划
 * @author onlineGenerator
 * @date 2017-01-03 22:07:48
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zmlCropPlanController")
public class ZmlCropPlanController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlCropPlanController.class);

	@Autowired
	private ZmlCropPlanServiceI zmlCropPlanService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 种植计划列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml/cropplan/zmlCropPlanList");
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
	public void datagrid(ZmlCropPlanEntity zmlCropPlan,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlCropPlanEntity.class, dataGrid);
		//查询条件组装器
		com.jce.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zmlCropPlan, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlCropPlanService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除种植计划
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlCropPlanEntity zmlCropPlan, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zmlCropPlan = systemService.getEntity(ZmlCropPlanEntity.class, zmlCropPlan.getId());
		message = "种植计划删除成功";
		try{
			zmlCropPlanService.delete(zmlCropPlan);
			systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "种植计划删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除种植计划
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "种植计划删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlCropPlanEntity zmlCropPlan = systemService.getEntity(ZmlCropPlanEntity.class, 
				id
				);
				zmlCropPlanService.delete(zmlCropPlan);
				systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "种植计划删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加种植计划
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlCropPlanEntity zmlCropPlan, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "种植计划添加成功";
		try{
			zmlCropPlanService.save(zmlCropPlan);
			systemService.addLog("", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "种植计划添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新种植计划
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlCropPlanEntity zmlCropPlan, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "种植计划更新成功";
		ZmlCropPlanEntity t = zmlCropPlanService.get(ZmlCropPlanEntity.class, zmlCropPlan.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zmlCropPlan, t);
			zmlCropPlanService.saveOrUpdate(t);
			systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "种植计划更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 种植计划新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlCropPlanEntity zmlCropPlan, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlCropPlan.getId())) {
			zmlCropPlan = zmlCropPlanService.getEntity(ZmlCropPlanEntity.class, zmlCropPlan.getId());
			req.setAttribute("zmlCropPlanPage", zmlCropPlan);
		}
		return new ModelAndView("com/zml/cropplan/zmlCropPlan-add");
	}
	/**
	 * 种植计划编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlCropPlanEntity zmlCropPlan, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlCropPlan.getId())) {
			zmlCropPlan = zmlCropPlanService.getEntity(ZmlCropPlanEntity.class, zmlCropPlan.getId());
			req.setAttribute("zmlCropPlanPage", zmlCropPlan);
		}
		return new ModelAndView("com/zml/cropplan/zmlCropPlan-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","zmlCropPlanController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ZmlCropPlanEntity zmlCropPlan,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ZmlCropPlanEntity.class, dataGrid);
		com.jce.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zmlCropPlan, request.getParameterMap());
		List<ZmlCropPlanEntity> zmlCropPlans = this.zmlCropPlanService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"种植计划");
		modelMap.put(NormalExcelConstants.CLASS,ZmlCropPlanEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("种植计划列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,zmlCropPlans);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ZmlCropPlanEntity zmlCropPlan,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"种植计划");
    	modelMap.put(NormalExcelConstants.CLASS,ZmlCropPlanEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("种植计划列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<ZmlCropPlanEntity> listZmlCropPlanEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ZmlCropPlanEntity.class,params);
				for (ZmlCropPlanEntity zmlCropPlan : listZmlCropPlanEntitys) {
					zmlCropPlanService.save(zmlCropPlan);
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
	public List<ZmlCropPlanEntity> list() {
		List<ZmlCropPlanEntity> listZmlCropPlans=zmlCropPlanService.getList(ZmlCropPlanEntity.class);
		return listZmlCropPlans;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlCropPlanEntity task = zmlCropPlanService.get(ZmlCropPlanEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlCropPlanEntity zmlCropPlan, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlCropPlanEntity>> failures = validator.validate(zmlCropPlan);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlCropPlanService.save(zmlCropPlan);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlCropPlan.getId();
		URI uri = uriBuilder.path("/rest/zmlCropPlanController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlCropPlanEntity zmlCropPlan) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlCropPlanEntity>> failures = validator.validate(zmlCropPlan);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlCropPlanService.saveOrUpdate(zmlCropPlan);
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
		zmlCropPlanService.deleteEntityById(ZmlCropPlanEntity.class, id);
	}
}
