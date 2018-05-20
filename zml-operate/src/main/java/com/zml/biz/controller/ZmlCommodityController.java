package com.zml.biz.controller;
import com.zml.base.entity.ZmlCommodityEntity;
import com.zml.service.ZmlCommodityServiceI;
import com.zml.base.entity.ZmlBannerEntity;
import com.zml.base.entity.ZmlCommodityDocEntity;
import com.zml.base.entity.ZmlCommodityStandardEntity;
import com.zml.base.page.ZmlCommodityPage;
import com.zml.enums.DocumentDirName;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jce.framework.core.beanvalidator.BeanValidators;
import com.jce.framework.core.common.controller.BaseController;
import com.jce.framework.core.common.exception.BusinessException;
import com.jce.framework.core.common.hibernate.qbc.CriteriaQuery;
import com.jce.framework.core.common.model.common.UploadFile;
import com.jce.framework.core.common.model.json.AjaxJson;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.constant.Globals;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.core.util.oConvertUtils;
import com.jce.framework.tag.easyui.TagUtil;
import com.jce.framework.web.cgform.entity.upload.CgUploadEntity;
import com.jce.framework.web.system.pojo.base.TSDepart;
import com.jce.framework.web.system.pojo.base.TSDocument;
import com.jce.framework.web.system.pojo.base.TSType;
import com.jce.framework.web.system.pojo.base.TSTypegroup;
import com.jce.framework.web.system.service.SystemService;
import com.jce.framework.core.util.DateUtils;
import com.jce.framework.core.util.ExceptionUtil;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.MyClassLoader;
import com.jce.framework.core.util.ResourceUtil;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.IOException;
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
 * @Description: 商品
 * @author onlineGenerator
 * @date 2017-01-01 13:54:03
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zmlCommodityController")
public class ZmlCommodityController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlCommodityController.class);

	@Autowired
	private ZmlCommodityServiceI zmlCommodityService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 商品列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml/commodity/zmlCommodityList");
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
	public void datagrid(ZmlCommodityEntity zmlCommodity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlCommodityEntity.class, dataGrid);
		//查询条件组装器
		com.jce.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zmlCommodity);
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlCommodityService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除商品
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlCommodityEntity zmlCommodity, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		zmlCommodity = systemService.getEntity(ZmlCommodityEntity.class, zmlCommodity.getId());
		String message = "商品删除成功";
		try{
			zmlCommodityService.delMain(zmlCommodity);
			systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "商品删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除商品
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "商品删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlCommodityEntity zmlCommodity = systemService.getEntity(ZmlCommodityEntity.class,
				id
				);
				zmlCommodityService.delMain(zmlCommodity);
				systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "商品删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加商品
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlCommodityEntity zmlCommodity,ZmlCommodityPage zmlCommodityPage, HttpServletRequest request) {
		List<ZmlCommodityDocEntity> zmlCommodityDocList =  zmlCommodityPage.getZmlCommodityDocList();
		List<ZmlCommodityStandardEntity> zmlCommodityStandardList =  zmlCommodityPage.getZmlCommodityStandardList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			zmlCommodityService.addMain(zmlCommodity, zmlCommodityDocList,zmlCommodityStandardList);
			systemService.addLog("", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "商品添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新商品
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlCommodityEntity zmlCommodity,ZmlCommodityPage zmlCommodityPage, HttpServletRequest request) {
		List<ZmlCommodityDocEntity> zmlCommodityDocList =  zmlCommodityPage.getZmlCommodityDocList();
		List<ZmlCommodityStandardEntity> zmlCommodityStandardList =  zmlCommodityPage.getZmlCommodityStandardList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			zmlCommodityService.updateMain(zmlCommodity, zmlCommodityDocList,zmlCommodityStandardList);
			systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新商品失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 商品新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlCommodityEntity zmlCommodity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlCommodity.getId())) {
			zmlCommodity = zmlCommodityService.getEntity(ZmlCommodityEntity.class, zmlCommodity.getId());
			req.setAttribute("zmlCommodityPage", zmlCommodity);
		}
		return new ModelAndView("com/zml/commodity/zmlCommodity-add");
	}
	
	/**
	 * 商品编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlCommodityEntity zmlCommodity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlCommodity.getId())) {
			zmlCommodity = zmlCommodityService.getEntity(ZmlCommodityEntity.class, zmlCommodity.getId());
			req.setAttribute("zmlCommodityPage", zmlCommodity);
		}
		return new ModelAndView("com/zml/commodity/zmlCommodity-update");
	}
	
	
	/**
	 * 加载明细列表[商品文档]
	 * 
	 * @return
	 */
	@RequestMapping(params = "zmlCommodityDocList")
	public ModelAndView zmlCommodityDocList(ZmlCommodityEntity zmlCommodity, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = zmlCommodity.getId();
		//===================================================================================
		//查询-商品文档
	    String hql0 = "from ZmlCommodityDocEntity where 1 = 1 AND cOMMODITY_ID = ? ";
	    try{
	    	List<ZmlCommodityDocEntity> zmlCommodityDocEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("zmlCommodityDocList", zmlCommodityDocEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/zml/commodity/zmlCommodityDocList");
	}
	/**
	 * 加载明细列表[适用标准]
	 * 
	 * @return
	 */
	@RequestMapping(params = "zmlCommodityStandardList")
	public ModelAndView zmlCommodityStandardList(ZmlCommodityEntity zmlCommodity, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id1 = zmlCommodity.getId();
		//===================================================================================
		//查询-适用标准
	    String hql1 = "from ZmlCommodityStandardEntity where 1 = 1 AND cOMMODITY_ID = ? ";
	    try{
	    	List<ZmlCommodityStandardEntity> zmlCommodityStandardEntityList = systemService.findHql(hql1,id1);
			req.setAttribute("zmlCommodityStandardList", zmlCommodityStandardEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/zml/commodity/zmlCommodityStandardList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(ZmlCommodityEntity zmlCommodity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(ZmlCommodityEntity.class, dataGrid);
    	//查询条件组装器
    	com.jce.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zmlCommodity);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<ZmlCommodityEntity> list=this.zmlCommodityService.getListByCriteriaQuery(cq, false);
    	List<ZmlCommodityPage> pageList=new ArrayList<ZmlCommodityPage>();
        if(list!=null&&list.size()>0){
        	for(ZmlCommodityEntity entity:list){
        		try{
        		ZmlCommodityPage page=new ZmlCommodityPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            	    Object id0 = entity.getId();
				    String hql0 = "from ZmlCommodityDocEntity where 1 = 1 AND cOMMODITY_ID = ? ";
        	        List<ZmlCommodityDocEntity> zmlCommodityDocEntityList = systemService.findHql(hql0,id0);
            		page.setZmlCommodityDocList(zmlCommodityDocEntityList);
            	    Object id1 = entity.getId();
				    String hql1 = "from ZmlCommodityStandardEntity where 1 = 1 AND cOMMODITY_ID = ? ";
        	        List<ZmlCommodityStandardEntity> zmlCommodityStandardEntityList = systemService.findHql(hql1,id1);
            		page.setZmlCommodityStandardList(zmlCommodityStandardEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"商品");
        map.put(NormalExcelConstants.CLASS,ZmlCommodityPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("商品列表", "导出人:Jeecg",
            "导出信息"));
        map.put(NormalExcelConstants.DATA_LIST,pageList);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

    /**
	 * 通过excel导入数据
	 * @param request
	 * @param
	 * @return
	 */
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
			params.setHeadRows(2);
			params.setNeedSave(true);
			try {
				List<ZmlCommodityPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), ZmlCommodityPage.class, params);
				ZmlCommodityEntity entity1=null;
				for (ZmlCommodityPage page : list) {
					entity1=new ZmlCommodityEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            zmlCommodityService.addMain(entity1, page.getZmlCommodityDocList(),page.getZmlCommodityStandardList());
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
	* 导出excel 使模板
	*/
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ModelMap map) {
		map.put(NormalExcelConstants.FILE_NAME,"商品");
		map.put(NormalExcelConstants.CLASS,ZmlCommodityPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("商品列表", "导出人:"+ ResourceUtil.getSessionUserName().getRealName(),
		"导出信息"));
		map.put(NormalExcelConstants.DATA_LIST,new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	* 导入功能跳转
	*
	* @return
	*/
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "zmlCommodityController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ZmlCommodityEntity> list() {
		List<ZmlCommodityEntity> listZmlCommoditys=zmlCommodityService.getList(ZmlCommodityEntity.class);
		return listZmlCommoditys;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlCommodityEntity task = zmlCommodityService.get(ZmlCommodityEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlCommodityPage zmlCommodityPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlCommodityPage>> failures = validator.validate(zmlCommodityPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<ZmlCommodityDocEntity> zmlCommodityDocList =  zmlCommodityPage.getZmlCommodityDocList();
		List<ZmlCommodityStandardEntity> zmlCommodityStandardList =  zmlCommodityPage.getZmlCommodityStandardList();
		
		ZmlCommodityEntity zmlCommodity = new ZmlCommodityEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(zmlCommodity,zmlCommodityPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		zmlCommodityService.addMain(zmlCommodity, zmlCommodityDocList,zmlCommodityStandardList);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlCommodityPage.getId();
		URI uri = uriBuilder.path("/rest/zmlCommodityController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlCommodityPage zmlCommodityPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlCommodityPage>> failures = validator.validate(zmlCommodityPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<ZmlCommodityDocEntity> zmlCommodityDocList =  zmlCommodityPage.getZmlCommodityDocList();
		List<ZmlCommodityStandardEntity> zmlCommodityStandardList =  zmlCommodityPage.getZmlCommodityStandardList();
		
		ZmlCommodityEntity zmlCommodity = new ZmlCommodityEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(zmlCommodity,zmlCommodityPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		zmlCommodityService.updateMain(zmlCommodity, zmlCommodityDocList,zmlCommodityStandardList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		ZmlCommodityEntity zmlCommodity = zmlCommodityService.get(ZmlCommodityEntity.class, id);
		zmlCommodityService.delMain(zmlCommodity);
	}
	
	/**
	 * 保存文件
	 * @param request
	 * @param response
	 * @param cgUploadEntity 智能表单文件上传实体
	 * @return
	 */
	@RequestMapping(params = "saveFiles2", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveFiles2(HttpServletRequest request, HttpServletResponse response, CgUploadEntity cgUploadEntity) {
		AjaxJson j = new AjaxJson();
		try {
			//Map<String, Object> attributes = new HashMap<String, Object>();
			String docType = oConvertUtils.getString(request.getParameter("docType"));// 文档类型
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
				ZmlCommodityEntity entity = zmlCommodityService.getEntity(ZmlCommodityEntity.class, id);
				//entity.set
				zmlCommodityService.saveOrUpdate(entity);
				for(Map<String, String> map : list){
					String tempPath = map.get("rsPath");
					ZmlCommodityDocEntity zmlCommodityDoc = new ZmlCommodityDocEntity();
					zmlCommodityDoc.setCommodityId(id);
					zmlCommodityDoc.setCreateDate(new Date());
					zmlCommodityDoc.setUpdateDate(new Date());
					zmlCommodityDoc.setFilePath(tempPath);
					zmlCommodityDoc.setType(new Integer(docType));
					systemService.save(zmlCommodityDoc);
				}
			}
			j.setMsg("操作成功");
			//j.setAttributes(attributes);
		} catch (Exception e) {
			j.setMsg("操作异常！");
			e.printStackTrace();
		}
		
		return j;
	}
	
	/**
	 * 保存文件
	 * @param document
	 * @throws Exception
	 */
	@RequestMapping(params = "saveFiles", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveFiles(HttpServletRequest request, HttpServletResponse response, TSDocument document) {
		AjaxJson j = new AjaxJson();
		Map<String, Object> attributes = new HashMap<String, Object>();
		TSTypegroup tsTypegroup=systemService.getTypeGroup("fieltype","文档分类");
		TSType tsType = systemService.getType("files","附件", tsTypegroup);
		String fileKey = oConvertUtils.getString(request.getParameter("fileKey"));// 文件ID
		String documentTitle = oConvertUtils.getString(request.getParameter("documentTitle"));// 文件标题
		if (StringUtil.isNotEmpty(fileKey)) {
			document.setId(fileKey);
			document = systemService.getEntity(TSDocument.class, fileKey);
			document.setDocumentTitle(documentTitle);
		}
		document.setSubclassname(MyClassLoader.getPackPath(document));
		document.setCreatedate(DateUtils.gettimestamp());
		document.setTSType(tsType);
		UploadFile uploadFile = new UploadFile(request, document);
		uploadFile.setCusPath("files");
		//设置weboffice转化【不设置该字段，则不做在线预览转化】
		uploadFile.setSwfpath("swfpath");
		//document = systemService.uploadFile(uploadFile);
		List<Map<String, String>> list = systemService.uploadFile(uploadFile, DocumentDirName.COMMODITY.getStatusValue(), null);
		String path = null;
		if(list != null && list.size() > 0){
			Map<String, String> rsMap = list.get(0);
			path = rsMap.get("rsPath");
		}
		//path 需要拼接上 nginx 静态资源的 对应二级域名
		attributes.put("url", path);
		attributes.put("fileKey", document.getId());
		attributes.put("name", document.getAttachmenttitle());
		attributes.put("viewhref", "commonController.do?objfileList&fileKey=" + document.getId());
		attributes.put("delurl", "commonController.do?delObjFile&fileKey=" + document.getId());
		j.setMsg("文件添加成功");
		j.setAttributes(attributes);
		return j;
	}
}
