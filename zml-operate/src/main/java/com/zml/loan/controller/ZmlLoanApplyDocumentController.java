package com.zml.loan.controller;
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
import com.jce.framework.web.system.pojo.base.TSUser;
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

import com.zml.base.loan.entity.ZmlLoanApplyDocumentEntity;
import com.zml.loan_service.ZmlLoanApplyDocumentServiceI;
/**   
 * @Title: Controller  
 * @Description: 借款申请文档
 */
@Controller
@RequestMapping("/zmlLoanApplyDocumentController")
public class ZmlLoanApplyDocumentController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlLoanApplyDocumentController.class);

	@Autowired
	private ZmlLoanApplyDocumentServiceI zmlLoanApplyDocumentService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private CgFormFieldServiceI cgFormFieldService;
	


	/**
	 * 借款申请文档列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml_loan/apply_document/zmlLoanApplyDocumentList");
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
	public void datagrid(ZmlLoanApplyDocumentEntity zmlLoanApplyDocument,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlLoanApplyDocumentEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, zmlLoanApplyDocument, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlLoanApplyDocumentService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除借款申请文档
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlLoanApplyDocumentEntity zmlLoanApplyDocument, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zmlLoanApplyDocument = systemService.getEntity(ZmlLoanApplyDocumentEntity.class, zmlLoanApplyDocument.getId());
		message = "借款申请文档删除成功";
		try{
			zmlLoanApplyDocumentService.delete(zmlLoanApplyDocument);
			systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "借款申请文档删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除借款申请文档
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "借款申请文档删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlLoanApplyDocumentEntity zmlLoanApplyDocument = systemService.getEntity(ZmlLoanApplyDocumentEntity.class, 
				id
				);
				zmlLoanApplyDocumentService.delete(zmlLoanApplyDocument);
				systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "借款申请文档删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加借款申请文档
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlLoanApplyDocumentEntity zmlLoanApplyDocument, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "借款申请文档添加成功";
		try{
			zmlLoanApplyDocumentService.save(zmlLoanApplyDocument);
			systemService.addLog("", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "借款申请文档添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		j.setObj(zmlLoanApplyDocument);
		return j;
	}
	
	/**
	 * 更新借款申请文档
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlLoanApplyDocumentEntity zmlLoanApplyDocument, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "借款申请文档更新成功";
		ZmlLoanApplyDocumentEntity t = zmlLoanApplyDocumentService.get(ZmlLoanApplyDocumentEntity.class, zmlLoanApplyDocument.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zmlLoanApplyDocument, t);
			zmlLoanApplyDocumentService.saveOrUpdate(t);
			systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "借款申请文档更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 审批申请文档
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doApprove")
	@ResponseBody
	public AjaxJson doApprove(ZmlLoanApplyDocumentEntity zmlLoanApplyDocument, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "借款申请文档更新成功";
		ZmlLoanApplyDocumentEntity t = zmlLoanApplyDocumentService.get(ZmlLoanApplyDocumentEntity.class, zmlLoanApplyDocument.getId());
		try {
			zmlLoanApplyDocument.setApprovalDate(new Date());
			TSUser user = ResourceUtil.getSessionUserName();
			zmlLoanApplyDocument.setApprovalUserId(user.getId());
			
			MyBeanUtils.copyBeanNotNull2Bean(zmlLoanApplyDocument, t);
			zmlLoanApplyDocumentService.saveOrUpdate(t);
			systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "借款申请文档更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 借款申请文档新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlLoanApplyDocumentEntity zmlLoanApplyDocument, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanApplyDocument.getId())) {
			zmlLoanApplyDocument = zmlLoanApplyDocumentService.getEntity(ZmlLoanApplyDocumentEntity.class, zmlLoanApplyDocument.getId());
			req.setAttribute("zmlLoanApplyDocumentPage", zmlLoanApplyDocument);
		}
		return new ModelAndView("com/zml_loan/apply_document/zmlLoanApplyDocument-add");
	}
	/**
	 * 借款申请文档编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlLoanApplyDocumentEntity zmlLoanApplyDocument, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanApplyDocument.getId())) {
			zmlLoanApplyDocument = zmlLoanApplyDocumentService.getEntity(ZmlLoanApplyDocumentEntity.class, zmlLoanApplyDocument.getId());
			req.setAttribute("zmlLoanApplyDocumentPage", zmlLoanApplyDocument);
		}
		return new ModelAndView("com/zml_loan/apply_document/zmlLoanApplyDocument-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","zmlLoanApplyDocumentController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ZmlLoanApplyDocumentEntity zmlLoanApplyDocument,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ZmlLoanApplyDocumentEntity.class, dataGrid);
		HqlGenerateUtil.installHql(cq, zmlLoanApplyDocument, request.getParameterMap());
		List<ZmlLoanApplyDocumentEntity> zmlLoanApplyDocuments = this.zmlLoanApplyDocumentService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"借款申请文档");
		modelMap.put(NormalExcelConstants.CLASS,ZmlLoanApplyDocumentEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("借款申请文档列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,zmlLoanApplyDocuments);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ZmlLoanApplyDocumentEntity zmlLoanApplyDocument,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"借款申请文档");
    	modelMap.put(NormalExcelConstants.CLASS,ZmlLoanApplyDocumentEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("借款申请文档列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<ZmlLoanApplyDocumentEntity> listZmlLoanApplyDocumentEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ZmlLoanApplyDocumentEntity.class,params);
				for (ZmlLoanApplyDocumentEntity zmlLoanApplyDocument : listZmlLoanApplyDocumentEntitys) {
					zmlLoanApplyDocumentService.save(zmlLoanApplyDocument);
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
	 * @param id zmlLoanApplyDocument主键id
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
	public List<ZmlLoanApplyDocumentEntity> list() {
		List<ZmlLoanApplyDocumentEntity> listZmlLoanApplyDocuments=zmlLoanApplyDocumentService.getList(ZmlLoanApplyDocumentEntity.class);
		return listZmlLoanApplyDocuments;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlLoanApplyDocumentEntity task = zmlLoanApplyDocumentService.get(ZmlLoanApplyDocumentEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlLoanApplyDocumentEntity zmlLoanApplyDocument, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanApplyDocumentEntity>> failures = validator.validate(zmlLoanApplyDocument);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlLoanApplyDocumentService.save(zmlLoanApplyDocument);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlLoanApplyDocument.getId();
		URI uri = uriBuilder.path("/rest/zmlLoanApplyDocumentController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlLoanApplyDocumentEntity zmlLoanApplyDocument) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanApplyDocumentEntity>> failures = validator.validate(zmlLoanApplyDocument);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlLoanApplyDocumentService.saveOrUpdate(zmlLoanApplyDocument);
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
		zmlLoanApplyDocumentService.deleteEntityById(ZmlLoanApplyDocumentEntity.class, id);
	}
}
