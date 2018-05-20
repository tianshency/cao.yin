package com.zml.biz.controller;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
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
import com.jce.framework.core.common.model.common.UploadFile;
import com.jce.framework.core.common.model.json.AjaxJson;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.constant.Globals;
import com.jce.framework.core.extend.hqlsearch.HqlGenerateUtil;
import com.jce.framework.core.util.ExceptionUtil;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.ResourceUtil;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.core.util.oConvertUtils;
import com.jce.framework.tag.easyui.TagUtil;
import com.jce.framework.web.cgform.entity.upload.CgUploadEntity;
import com.jce.framework.web.cgform.service.config.CgFormFieldServiceI;
import com.jce.framework.web.system.service.SystemService;
import com.zml.base.entity.ZmlBannerEntity;
import com.zml.base.entity.ZmlCommodityClassifyEntity;
import com.zml.enums.DocumentDirName;
import com.zml.service.ZmlCommodityClassifyServiceI;
/**   
 * @Title: Controller  
 * @Description: 商品分类
 * @author onlineGenerator
 * @date 2017-02-10 23:16:16
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zmlCommodityClassifyController")
public class ZmlCommodityClassifyController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlCommodityClassifyController.class);

	@Autowired
	private ZmlCommodityClassifyServiceI zmlCommodityClassifyService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private CgFormFieldServiceI cgFormFieldService;
	


	/**
	 * 商品分类列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml/commodity_classify/zmlCommodityClassifyList");
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
	public void datagrid(ZmlCommodityClassifyEntity zmlCommodityClassify,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlCommodityClassifyEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, zmlCommodityClassify, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlCommodityClassifyService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除商品分类
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlCommodityClassifyEntity zmlCommodityClassify, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zmlCommodityClassify = systemService.getEntity(ZmlCommodityClassifyEntity.class, zmlCommodityClassify.getId());
		message = "商品分类删除成功";
		try{
			zmlCommodityClassifyService.delete(zmlCommodityClassify);
			systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "商品分类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除商品分类
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "商品分类删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlCommodityClassifyEntity zmlCommodityClassify = systemService.getEntity(ZmlCommodityClassifyEntity.class, 
				id
				);
				zmlCommodityClassifyService.delete(zmlCommodityClassify);
				systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "商品分类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加商品分类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlCommodityClassifyEntity zmlCommodityClassify, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "商品分类添加成功";
		try{
			zmlCommodityClassifyService.save(zmlCommodityClassify);
			systemService.addLog("", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "商品分类添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		j.setObj(zmlCommodityClassify);
		return j;
	}
	
	/**
	 * 更新商品分类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlCommodityClassifyEntity zmlCommodityClassify, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "商品分类更新成功";
		ZmlCommodityClassifyEntity t = zmlCommodityClassifyService.get(ZmlCommodityClassifyEntity.class, zmlCommodityClassify.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zmlCommodityClassify, t);
			zmlCommodityClassifyService.saveOrUpdate(t);
			systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "商品分类更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 商品分类新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlCommodityClassifyEntity zmlCommodityClassify, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlCommodityClassify.getId())) {
			zmlCommodityClassify = zmlCommodityClassifyService.getEntity(ZmlCommodityClassifyEntity.class, zmlCommodityClassify.getId());
			req.setAttribute("zmlCommodityClassifyPage", zmlCommodityClassify);
		}
		return new ModelAndView("com/zml/commodity_classify/zmlCommodityClassify-add");
	}
	/**
	 * 商品分类编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlCommodityClassifyEntity zmlCommodityClassify, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlCommodityClassify.getId())) {
			zmlCommodityClassify = zmlCommodityClassifyService.getEntity(ZmlCommodityClassifyEntity.class, zmlCommodityClassify.getId());
			req.setAttribute("zmlCommodityClassifyPage", zmlCommodityClassify);
		}
		return new ModelAndView("com/zml/commodity_classify/zmlCommodityClassify-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","zmlCommodityClassifyController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ZmlCommodityClassifyEntity zmlCommodityClassify,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ZmlCommodityClassifyEntity.class, dataGrid);
		HqlGenerateUtil.installHql(cq, zmlCommodityClassify, request.getParameterMap());
		List<ZmlCommodityClassifyEntity> zmlCommodityClassifys = this.zmlCommodityClassifyService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"商品分类");
		modelMap.put(NormalExcelConstants.CLASS,ZmlCommodityClassifyEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("商品分类列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,zmlCommodityClassifys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ZmlCommodityClassifyEntity zmlCommodityClassify,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"商品分类");
    	modelMap.put(NormalExcelConstants.CLASS,ZmlCommodityClassifyEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("商品分类列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<ZmlCommodityClassifyEntity> listZmlCommodityClassifyEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ZmlCommodityClassifyEntity.class,params);
				for (ZmlCommodityClassifyEntity zmlCommodityClassify : listZmlCommodityClassifyEntitys) {
					zmlCommodityClassifyService.save(zmlCommodityClassify);
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
	 * @param id zmlCommodityClassify主键id
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
	public List<ZmlCommodityClassifyEntity> list() {
		List<ZmlCommodityClassifyEntity> listZmlCommodityClassifys=zmlCommodityClassifyService.getList(ZmlCommodityClassifyEntity.class);
		return listZmlCommodityClassifys;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlCommodityClassifyEntity task = zmlCommodityClassifyService.get(ZmlCommodityClassifyEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlCommodityClassifyEntity zmlCommodityClassify, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlCommodityClassifyEntity>> failures = validator.validate(zmlCommodityClassify);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlCommodityClassifyService.save(zmlCommodityClassify);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlCommodityClassify.getId();
		URI uri = uriBuilder.path("/rest/zmlCommodityClassifyController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlCommodityClassifyEntity zmlCommodityClassify) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlCommodityClassifyEntity>> failures = validator.validate(zmlCommodityClassify);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlCommodityClassifyService.saveOrUpdate(zmlCommodityClassify);
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
		zmlCommodityClassifyService.deleteEntityById(ZmlCommodityClassifyEntity.class, id);
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
			
			List<Map<String, String>> list = systemService.uploadFile(uploadFile, DocumentDirName.COMMODITY_CLASSIFY.getStatusValue(), id);
			if(list != null && list.size() > 0){
				Map<String, String> rsMap = list.get(0);
				String path = rsMap.get("rsPath");
				ZmlCommodityClassifyEntity entity = zmlCommodityClassifyService.getEntity(ZmlCommodityClassifyEntity.class, id);
				entity.setLogo(path);
				zmlCommodityClassifyService.saveOrUpdate(entity);
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
