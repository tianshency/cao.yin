package com.zml.biz.controller;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.jce.framework.web.cgform.entity.upload.CgUploadEntity;
import com.jce.framework.web.cgform.service.config.CgFormFieldServiceI;
import com.jce.framework.web.system.service.SystemService;
import com.zml.base.entity.ZmlUserReleaseFoodEntity;
import com.zml.base.entity.ZmlUserReleaseInfoEntity;
import com.zml.service.ZmlUserReleaseInfoServiceI;
/**   
 * @Title: Controller  
 * @Description: 发布信息
 */
@Controller
@RequestMapping("/zmlUserReleaseInfoController")
public class ZmlUserReleaseInfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlUserReleaseInfoController.class);

	@Autowired
	private ZmlUserReleaseInfoServiceI zmlUserReleaseInfoService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private CgFormFieldServiceI cgFormFieldService;
	


	/**
	 * 发布信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml/release_info/zmlUserReleaseInfoList");
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
	public void datagrid(ZmlUserReleaseInfoEntity zmlUserReleaseInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlUserReleaseInfoEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, zmlUserReleaseInfo, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlUserReleaseInfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除发布信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlUserReleaseInfoEntity zmlUserReleaseInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zmlUserReleaseInfo = systemService.getEntity(ZmlUserReleaseInfoEntity.class, zmlUserReleaseInfo.getId());
		message = "发布信息删除成功";
		try{
			zmlUserReleaseInfoService.delete(zmlUserReleaseInfo);
			systemService.addLog("/zmlUserReleaseInfoController.do?doDel", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "发布信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除发布信息
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "发布信息删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlUserReleaseInfoEntity zmlUserReleaseInfo = systemService.getEntity(ZmlUserReleaseInfoEntity.class, 
				id
				);
				zmlUserReleaseInfoService.delete(zmlUserReleaseInfo);
				systemService.addLog("/zmlUserReleaseInfoController.do?doBatchDel", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "发布信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加发布信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlUserReleaseInfoEntity zmlUserReleaseInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "发布信息添加成功";
		try{
			zmlUserReleaseInfoService.save(zmlUserReleaseInfo);
			systemService.addLog("/zmlUserReleaseInfoController.do?doAdd", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "发布信息添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		j.setObj(zmlUserReleaseInfo);
		return j;
	}
	
	/**
	 * 更新发布信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlUserReleaseInfoEntity zmlUserReleaseInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "发布信息更新成功";
		ZmlUserReleaseInfoEntity t = zmlUserReleaseInfoService.get(ZmlUserReleaseInfoEntity.class, zmlUserReleaseInfo.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zmlUserReleaseInfo, t);
			zmlUserReleaseInfoService.saveOrUpdate(t);
			systemService.addLog("/zmlUserReleaseInfoController.do?doUpdate", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "发布信息更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 发布信息 审批 页面跳转
	 */
	@RequestMapping(params = "goApproval")
	public ModelAndView goApproval(ZmlUserReleaseInfoEntity zmlUserReleaseInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlUserReleaseInfo.getId())) {
			zmlUserReleaseInfo = zmlUserReleaseInfoService.getEntity(ZmlUserReleaseInfoEntity.class, zmlUserReleaseInfo.getId());
			req.setAttribute("zmlUserReleaseInfoPage", zmlUserReleaseInfo);
		}
		return new ModelAndView("com/zml/release_info/infoApproval");
	}
	
	
	/**
	 * 审批发布信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doApproval")
	@ResponseBody
	public AjaxJson doApproval(ZmlUserReleaseInfoEntity zmlUserReleaseInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "审批成功";
		ZmlUserReleaseInfoEntity t = zmlUserReleaseInfoService.get(ZmlUserReleaseInfoEntity.class, zmlUserReleaseInfo.getId());
		try {
			zmlUserReleaseInfo.setApprovalTime(new Date());
			zmlUserReleaseInfo.setApprovalUserId(ResourceUtil.getSessionUserName().getId());
			MyBeanUtils.copyBeanNotNull2Bean(zmlUserReleaseInfo, t); 
			zmlUserReleaseInfoService.saveOrUpdate(t);
			systemService.addLog("/zmlUserReleaseInfoController.do?doApproval", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "审批失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 发布信息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlUserReleaseInfoEntity zmlUserReleaseInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlUserReleaseInfo.getId())) {
			zmlUserReleaseInfo = zmlUserReleaseInfoService.getEntity(ZmlUserReleaseInfoEntity.class, zmlUserReleaseInfo.getId());
			req.setAttribute("zmlUserReleaseInfoPage", zmlUserReleaseInfo);
		}
		return new ModelAndView("com/zml/release_info/zmlUserReleaseInfo-add");
	}
	/**
	 * 发布信息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlUserReleaseInfoEntity zmlUserReleaseInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlUserReleaseInfo.getId())) {
			zmlUserReleaseInfo = zmlUserReleaseInfoService.getEntity(ZmlUserReleaseInfoEntity.class, zmlUserReleaseInfo.getId());
			req.setAttribute("zmlUserReleaseInfoPage", zmlUserReleaseInfo);
		}
		return new ModelAndView("com/zml/release_info/zmlUserReleaseInfo-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","zmlUserReleaseInfoController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ZmlUserReleaseInfoEntity zmlUserReleaseInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ZmlUserReleaseInfoEntity.class, dataGrid);
		HqlGenerateUtil.installHql(cq, zmlUserReleaseInfo, request.getParameterMap());
		List<ZmlUserReleaseInfoEntity> zmlUserReleaseInfos = this.zmlUserReleaseInfoService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"发布信息");
		modelMap.put(NormalExcelConstants.CLASS,ZmlUserReleaseInfoEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("发布信息列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,zmlUserReleaseInfos);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ZmlUserReleaseInfoEntity zmlUserReleaseInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"发布信息");
    	modelMap.put(NormalExcelConstants.CLASS,ZmlUserReleaseInfoEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("发布信息列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<ZmlUserReleaseInfoEntity> listZmlUserReleaseInfoEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ZmlUserReleaseInfoEntity.class,params);
				for (ZmlUserReleaseInfoEntity zmlUserReleaseInfo : listZmlUserReleaseInfoEntitys) {
					zmlUserReleaseInfoService.save(zmlUserReleaseInfo);
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
	
	/**
	 * 获取文件附件信息
	 * 
	 * @param id zmlUserReleaseInfo主键id
	 */
	@RequestMapping(params = "getFiles")
	@ResponseBody
	public AjaxJson getFiles(String id){
		List<CgUploadEntity> uploadBeans = cgFormFieldService.findByProperty(CgUploadEntity.class, "cgformId", id);
		List<Map<String,Object>> files = new ArrayList<Map<String,Object>>(0);
		for(CgUploadEntity b:uploadBeans){
			String title = b.getAttachmenttitle();//附件名
			String fileKey = b.getId();//附件主键
			String path = b.getRealpath();//附件路径
			String field = b.getCgformField();//表单中作为附件控件的字段
			Map<String, Object> file = new HashMap<String, Object>();
			file.put("title", title);
			file.put("fileKey", fileKey);
			file.put("path", path);
			file.put("field", field==null?"":field);
			files.add(file);
		}
		AjaxJson j = new AjaxJson();
		j.setObj(files);
		return j;
	}
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ZmlUserReleaseInfoEntity> list() {
		List<ZmlUserReleaseInfoEntity> listZmlUserReleaseInfos=zmlUserReleaseInfoService.getList(ZmlUserReleaseInfoEntity.class);
		return listZmlUserReleaseInfos;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlUserReleaseInfoEntity task = zmlUserReleaseInfoService.get(ZmlUserReleaseInfoEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlUserReleaseInfoEntity zmlUserReleaseInfo, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlUserReleaseInfoEntity>> failures = validator.validate(zmlUserReleaseInfo);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlUserReleaseInfoService.save(zmlUserReleaseInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlUserReleaseInfo.getId();
		URI uri = uriBuilder.path("/rest/zmlUserReleaseInfoController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlUserReleaseInfoEntity zmlUserReleaseInfo) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlUserReleaseInfoEntity>> failures = validator.validate(zmlUserReleaseInfo);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlUserReleaseInfoService.saveOrUpdate(zmlUserReleaseInfo);
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
		zmlUserReleaseInfoService.deleteEntityById(ZmlUserReleaseInfoEntity.class, id);
	}
}
