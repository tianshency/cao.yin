package com.zml.release_classify.controller;
import com.zml.release_classify.entity.ZmlUserReleaseClassifyEntity;
import com.zml.release_classify.service.ZmlUserReleaseClassifyServiceI;
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
import com.jce.framework.core.extend.hqlsearch.HqlGenerateUtil;
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
 * @Description: 发布类型
 * @author onlineGenerator
 * @date 2017-01-15 20:20:26
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zmlUserReleaseClassifyController")
public class ZmlUserReleaseClassifyController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlUserReleaseClassifyController.class);

	@Autowired
	private ZmlUserReleaseClassifyServiceI zmlUserReleaseClassifyService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 发布类型列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml/release_classify/zmlUserReleaseClassifyList");
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
	public void datagrid(ZmlUserReleaseClassifyEntity zmlUserReleaseClassify,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlUserReleaseClassifyEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, zmlUserReleaseClassify, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlUserReleaseClassifyService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除发布类型
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlUserReleaseClassifyEntity zmlUserReleaseClassify, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zmlUserReleaseClassify = systemService.getEntity(ZmlUserReleaseClassifyEntity.class, zmlUserReleaseClassify.getId());
		message = "发布类型删除成功";
		try{
			zmlUserReleaseClassifyService.delete(zmlUserReleaseClassify);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "发布类型删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除发布类型
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "发布类型删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlUserReleaseClassifyEntity zmlUserReleaseClassify = systemService.getEntity(ZmlUserReleaseClassifyEntity.class, 
				id
				);
				zmlUserReleaseClassifyService.delete(zmlUserReleaseClassify);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "发布类型删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加发布类型
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlUserReleaseClassifyEntity zmlUserReleaseClassify, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "发布类型添加成功";
		try{
			zmlUserReleaseClassifyService.save(zmlUserReleaseClassify);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "发布类型添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新发布类型
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlUserReleaseClassifyEntity zmlUserReleaseClassify, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "发布类型更新成功";
		ZmlUserReleaseClassifyEntity t = zmlUserReleaseClassifyService.get(ZmlUserReleaseClassifyEntity.class, zmlUserReleaseClassify.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zmlUserReleaseClassify, t);
			zmlUserReleaseClassifyService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "发布类型更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 发布类型新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlUserReleaseClassifyEntity zmlUserReleaseClassify, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlUserReleaseClassify.getId())) {
			zmlUserReleaseClassify = zmlUserReleaseClassifyService.getEntity(ZmlUserReleaseClassifyEntity.class, zmlUserReleaseClassify.getId());
			req.setAttribute("zmlUserReleaseClassifyPage", zmlUserReleaseClassify);
		}
		return new ModelAndView("com/zml/release_classify/zmlUserReleaseClassify-add");
	}
	/**
	 * 发布类型编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlUserReleaseClassifyEntity zmlUserReleaseClassify, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlUserReleaseClassify.getId())) {
			zmlUserReleaseClassify = zmlUserReleaseClassifyService.getEntity(ZmlUserReleaseClassifyEntity.class, zmlUserReleaseClassify.getId());
			req.setAttribute("zmlUserReleaseClassifyPage", zmlUserReleaseClassify);
		}
		return new ModelAndView("com/zml/release_classify/zmlUserReleaseClassify-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","zmlUserReleaseClassifyController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ZmlUserReleaseClassifyEntity zmlUserReleaseClassify,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ZmlUserReleaseClassifyEntity.class, dataGrid);
		HqlGenerateUtil.installHql(cq, zmlUserReleaseClassify, request.getParameterMap());
		List<ZmlUserReleaseClassifyEntity> zmlUserReleaseClassifys = this.zmlUserReleaseClassifyService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"发布类型");
		modelMap.put(NormalExcelConstants.CLASS,ZmlUserReleaseClassifyEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("发布类型列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,zmlUserReleaseClassifys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ZmlUserReleaseClassifyEntity zmlUserReleaseClassify,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"发布类型");
    	modelMap.put(NormalExcelConstants.CLASS,ZmlUserReleaseClassifyEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("发布类型列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<ZmlUserReleaseClassifyEntity> listZmlUserReleaseClassifyEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ZmlUserReleaseClassifyEntity.class,params);
				for (ZmlUserReleaseClassifyEntity zmlUserReleaseClassify : listZmlUserReleaseClassifyEntitys) {
					zmlUserReleaseClassifyService.save(zmlUserReleaseClassify);
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
	public List<ZmlUserReleaseClassifyEntity> list() {
		List<ZmlUserReleaseClassifyEntity> listZmlUserReleaseClassifys=zmlUserReleaseClassifyService.getList(ZmlUserReleaseClassifyEntity.class);
		return listZmlUserReleaseClassifys;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlUserReleaseClassifyEntity task = zmlUserReleaseClassifyService.get(ZmlUserReleaseClassifyEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlUserReleaseClassifyEntity zmlUserReleaseClassify, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlUserReleaseClassifyEntity>> failures = validator.validate(zmlUserReleaseClassify);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlUserReleaseClassifyService.save(zmlUserReleaseClassify);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlUserReleaseClassify.getId();
		URI uri = uriBuilder.path("/rest/zmlUserReleaseClassifyController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlUserReleaseClassifyEntity zmlUserReleaseClassify) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlUserReleaseClassifyEntity>> failures = validator.validate(zmlUserReleaseClassify);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlUserReleaseClassifyService.saveOrUpdate(zmlUserReleaseClassify);
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
		zmlUserReleaseClassifyService.deleteEntityById(ZmlUserReleaseClassifyEntity.class, id);
	}
}
