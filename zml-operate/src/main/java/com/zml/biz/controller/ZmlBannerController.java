package com.zml.biz.controller;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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
import com.jce.framework.core.common.model.common.UploadFile;
import com.jce.framework.core.common.model.json.AjaxJson;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.constant.Globals;
import com.jce.framework.core.util.ExceptionUtil;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.ResourceUtil;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.core.util.oConvertUtils;
import com.jce.framework.tag.easyui.TagUtil;
import com.jce.framework.web.cgform.entity.upload.CgUploadEntity;
import com.jce.framework.web.demo.entity.test.FileMeta;
import com.jce.framework.web.system.service.SystemService;
import com.zml.base.entity.ZmlBannerEntity;
import com.zml.common.ReMsg;
import com.zml.enums.DocumentDirName;
import com.zml.enums.YesOrNo;
import com.zml.enums.YesOrNoStr;
import com.zml.service.ZmlBannerServiceI;

/**   
 * @Title: Controller  
 * @Description: 首页轮播
 */
@Controller
@RequestMapping("/zmlBannerController")
public class ZmlBannerController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlBannerController.class);
	
	/**
	 * update-begin--Author:huangzq  Date:20151125 for：[732]【常用示例】上传文件下载报错
	 */
	private static LinkedList<FileMeta> files = new LinkedList<FileMeta>();
	/**
	 * update-end--Author:huangzq  Date:20151125 for：[732]【常用示例】上传文件下载报错
	 */
	FileMeta fileMeta = null;

	@Autowired
	private ZmlBannerServiceI zmlBannerService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 首页轮播列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml/banner/zmlBannerList");
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
	public void datagrid(ZmlBannerEntity zmlBanner,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlBannerEntity.class, dataGrid);
		//查询条件组装器
		com.jce.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zmlBanner, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlBannerService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除首页轮播
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlBannerEntity zmlBanner, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zmlBanner = systemService.getEntity(ZmlBannerEntity.class, zmlBanner.getId());
		message = "首页轮播删除成功";
		try{
			zmlBannerService.delete(zmlBanner);
			systemService.addLog("/zmlBannerController.do?doDel", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "首页轮播删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除首页轮播
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "首页轮播删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlBannerEntity zmlBanner = systemService.getEntity(ZmlBannerEntity.class, id);
				zmlBannerService.delete(zmlBanner);
				systemService.addLog("/zmlBannerController.do?doBatchDel", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "首页轮播删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加首页轮播
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlBannerEntity zmlBanner, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "首页轮播添加成功";
		try{
			MultipartHttpServletRequest mrequest = null;//(MultipartHttpServletRequest)request;
			zmlBannerService.save(zmlBanner, mrequest);
			systemService.addLog("/zmlBannerController.do?doAdd", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "首页轮播添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		j.setObj(zmlBanner);
		return j;
	}
	
	/**
	 * 添加首页轮播
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doSave")
	//@ResponseBody
	public String doSave(ZmlBannerEntity zmlBanner, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "首页轮播添加成功";
		try{
			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest)request;
			zmlBannerService.save(zmlBanner, mrequest);
			systemService.addLog("", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "首页轮播添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return "test";
	}
	
	/**
	 * 更新首页轮播
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlBannerEntity zmlBanner, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "首页轮播更新成功";
		ZmlBannerEntity t = zmlBannerService.get(ZmlBannerEntity.class, zmlBanner.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zmlBanner, t);
			zmlBannerService.saveOrUpdate(t);
			systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "首页轮播更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 首页轮播新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlBannerEntity zmlBanner, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlBanner.getId())) {
			zmlBanner = zmlBannerService.getEntity(ZmlBannerEntity.class, zmlBanner.getId());
			req.setAttribute("zmlBannerPage", zmlBanner);
		}
		return new ModelAndView("com/zml/banner/zmlBanner-add");
	}
	/**
	 * 首页轮播编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlBannerEntity zmlBanner, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlBanner.getId())) {
			zmlBanner = zmlBannerService.getEntity(ZmlBannerEntity.class, zmlBanner.getId());
			req.setAttribute("zmlBannerPage", zmlBanner);
		}
		return new ModelAndView("com/zml/banner/zmlBanner-update");
	}
	
	/**
	 * 前端显示 加入缓存
	 */
	@RequestMapping(params = "addCache")
	@ResponseBody
	public ReMsg addCache(ZmlBannerEntity zmlBanner, HttpServletRequest req) {
		ReMsg reMsg = new ReMsg("成功！", true);
		try {
			if (StringUtil.isNotEmpty(zmlBanner.getId())) {
				ZmlBannerEntity oldZmlBanner = zmlBannerService.getEntity(ZmlBannerEntity.class, zmlBanner.getId());
				if(YesOrNoStr.NO.getStatusValue().equals(oldZmlBanner.getIsView())){
					String rmId = zmlBannerService.setCacheBannerData(redisUtilTool, oldZmlBanner);
					//将从 缓存中取消显示的 数据状态改为 0
					if(rmId != null && !"".equals(rmId)){
						ZmlBannerEntity tempZmlBanner = zmlBannerService.getEntity(ZmlBannerEntity.class, rmId);
						tempZmlBanner.setIsView(YesOrNoStr.NO.getStatusValue());
						tempZmlBanner.setUpdateDate(new Date());
						zmlBannerService.saveOrUpdate(tempZmlBanner);
					}
				}else{
					reMsg = new ReMsg("目前已经是显示状态！", false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			reMsg = new ReMsg("异常：" + e.getMessage(), false);
		}
		return reMsg;
	}
	/**
	 * 取消前端显示 移除缓存
	 */
	@RequestMapping(params = "cancelShow")
	@ResponseBody
	public ReMsg cancelShow(ZmlBannerEntity zmlBanner, HttpServletRequest req) {
		ReMsg reMsg = new ReMsg("成功！", true);
		try {
			if (StringUtil.isNotEmpty(zmlBanner.getId())) {
				ZmlBannerEntity oldZmlBanner = zmlBannerService.getEntity(ZmlBannerEntity.class, zmlBanner.getId());
				if(YesOrNoStr.YES.getStatusValue().equals(oldZmlBanner.getIsView())){
					String rmId = zmlBannerService.setCancelCacheBannerData(redisUtilTool, oldZmlBanner);
					//将从 缓存中取消显示的 数据状态改为 0
					oldZmlBanner.setUpdateDate(new Date());
					oldZmlBanner.setIsView(YesOrNoStr.NO.getStatusValue());
					zmlBannerService.saveOrUpdate(oldZmlBanner);
				}else{
					reMsg = new ReMsg("目前已经是取消状态！", false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			reMsg = new ReMsg("异常：" + e.getMessage(), false);
		}
		return reMsg;
	}
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","zmlBannerController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ZmlBannerEntity zmlBanner,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ZmlBannerEntity.class, dataGrid);
		com.jce.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zmlBanner, request.getParameterMap());
		List<ZmlBannerEntity> zmlBanners = this.zmlBannerService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"首页轮播");
		modelMap.put(NormalExcelConstants.CLASS,ZmlBannerEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("首页轮播列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,zmlBanners);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ZmlBannerEntity zmlBanner,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"首页轮播");
    	modelMap.put(NormalExcelConstants.CLASS,ZmlBannerEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("首页轮播列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<ZmlBannerEntity> listZmlBannerEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ZmlBannerEntity.class,params);
				for (ZmlBannerEntity zmlBanner : listZmlBannerEntitys) {
					zmlBannerService.save(zmlBanner);
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
	public List<ZmlBannerEntity> list() {
		List<ZmlBannerEntity> listZmlBanners=zmlBannerService.getList(ZmlBannerEntity.class);
		return listZmlBanners;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlBannerEntity task = zmlBannerService.get(ZmlBannerEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlBannerEntity zmlBanner, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlBannerEntity>> failures = validator.validate(zmlBanner);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlBannerService.save(zmlBanner);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlBanner.getId();
		URI uri = uriBuilder.path("/rest/zmlBannerController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlBannerEntity zmlBanner) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlBannerEntity>> failures = validator.validate(zmlBanner);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlBannerService.saveOrUpdate(zmlBanner);
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
		zmlBannerService.deleteEntityById(ZmlBannerEntity.class, id);
	}
	
	/**
	 * 保存文件
	 * @param request
	 * @param response
	 * @param cgUploadEntity
	 * @return
	 */
	@RequestMapping(params = "saveFiles", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveFiles(HttpServletRequest request, HttpServletResponse response, CgUploadEntity cgUploadEntity) {
		AjaxJson j = new AjaxJson();
		try {
			//Map<String, Object> attributes = new HashMap<String, Object>();
			String fileKey = oConvertUtils.getString(request.getParameter("fileKey"));// 文件ID
			String id = oConvertUtils.getString(request.getParameter("cgFormId"));//动态表主键ID
			String tableName = oConvertUtils.getString(request.getParameter("cgFormName"));//动态表名
			String cgField = oConvertUtils.getString(request.getParameter("cgFormField"));//动态表上传控件字段
			if(!StringUtil.isEmpty(id)){
				cgUploadEntity.setCgformId(id);
				cgUploadEntity.setCgformName(tableName);
				cgUploadEntity.setCgformField(cgField);
			}
			if (StringUtil.isNotEmpty(fileKey)) {
				cgUploadEntity.setId(fileKey);
				cgUploadEntity = systemService.getEntity(CgUploadEntity.class, fileKey);
			}
			UploadFile uploadFile = new UploadFile(request, cgUploadEntity);
			uploadFile.setCusPath("files");
			uploadFile.setSwfpath("swfpath");
			uploadFile.setByteField(null);//不存二进制内容
			
			List<Map<String, String>> list = systemService.uploadFile(uploadFile, DocumentDirName.BANNER.getStatusValue(), id);
			if(list != null && list.size() > 0){
				Map<String, String> rsMap = list.get(0);
				String path = rsMap.get("rsPath");
				ZmlBannerEntity zmlBanner = zmlBannerService.getEntity(ZmlBannerEntity.class, id);
				zmlBanner.setPath(path);
				zmlBannerService.saveOrUpdate(zmlBanner);
			}
			//attributes.put("fileKey", cgUploadEntity.getId());
			//attributes.put("viewhref", "commonController.do?objfileList&fileKey=" + cgUploadEntity.getId());
			//attributes.put("delurl", "commonController.do?delObjFile&fileKey=" + cgUploadEntity.getId());
			j.setMsg("操作成功");
			//j.setAttributes(attributes);
		} catch (Exception e) {
			j.setMsg("操作异常！");
			e.printStackTrace();
		}
		
		return j;
	}
}
