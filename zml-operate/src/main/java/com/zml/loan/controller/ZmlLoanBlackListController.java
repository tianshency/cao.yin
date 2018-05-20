package com.zml.loan.controller;
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
import com.jce.framework.core.extend.hqlsearch.HqlGenerateUtil;
import com.jce.framework.core.util.ExceptionUtil;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.ResourceUtil;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.tag.easyui.TagUtil;
import com.jce.framework.web.system.service.SystemService;
import com.zml.base.loan.entity.ZmlLoanBlackListDocumentEntity;
import com.zml.base.loan.entity.ZmlLoanBlackListEntity;
import com.zml.base.loan.page.ZmlLoanBlackListPage;
import com.zml.loan_service.ZmlLoanBlackListServiceI;

/**   
 * @Title: Controller
 * @Description: 黑名单表
 */
@Controller
@RequestMapping("/zmlLoanBlackListController")
public class ZmlLoanBlackListController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlLoanBlackListController.class);

	@Autowired
	private ZmlLoanBlackListServiceI zmlLoanBlackListService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 黑名单表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml_loan/black_list/zmlLoanBlackListList");
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
	public void datagrid(ZmlLoanBlackListEntity zmlLoanBlackList,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlLoanBlackListEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, zmlLoanBlackList);
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlLoanBlackListService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除黑名单表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlLoanBlackListEntity zmlLoanBlackList, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		zmlLoanBlackList = systemService.getEntity(ZmlLoanBlackListEntity.class, zmlLoanBlackList.getId());
		String message = "黑名单表删除成功";
		try{
			zmlLoanBlackListService.delMain(zmlLoanBlackList);
			systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "黑名单表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除黑名单表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "黑名单表删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlLoanBlackListEntity zmlLoanBlackList = systemService.getEntity(ZmlLoanBlackListEntity.class,
				id
				);
				zmlLoanBlackListService.delMain(zmlLoanBlackList);
				systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "黑名单表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加黑名单表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlLoanBlackListEntity zmlLoanBlackList,ZmlLoanBlackListPage zmlLoanBlackListPage, HttpServletRequest request) {
		List<ZmlLoanBlackListDocumentEntity> zmlLoanBlackListDocumentList =  zmlLoanBlackListPage.getZmlLoanBlackListDocumentList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			zmlLoanBlackListService.addMain(zmlLoanBlackList, zmlLoanBlackListDocumentList);
			systemService.addLog("", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "黑名单表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新黑名单表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlLoanBlackListEntity zmlLoanBlackList,ZmlLoanBlackListPage zmlLoanBlackListPage, HttpServletRequest request) {
		List<ZmlLoanBlackListDocumentEntity> zmlLoanBlackListDocumentList =  zmlLoanBlackListPage.getZmlLoanBlackListDocumentList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			zmlLoanBlackListService.updateMain(zmlLoanBlackList, zmlLoanBlackListDocumentList);
			systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新黑名单表失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 黑名单表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlLoanBlackListEntity zmlLoanBlackList, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanBlackList.getId())) {
			zmlLoanBlackList = zmlLoanBlackListService.getEntity(ZmlLoanBlackListEntity.class, zmlLoanBlackList.getId());
			req.setAttribute("zmlLoanBlackListPage", zmlLoanBlackList);
		}
		return new ModelAndView("com/zml_loan/black_list/zmlLoanBlackList-add");
	}
	
	/**
	 * 黑名单表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlLoanBlackListEntity zmlLoanBlackList, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanBlackList.getId())) {
			zmlLoanBlackList = zmlLoanBlackListService.getEntity(ZmlLoanBlackListEntity.class, zmlLoanBlackList.getId());
			req.setAttribute("zmlLoanBlackListPage", zmlLoanBlackList);
		}
		return new ModelAndView("com/zml_loan/black_list/zmlLoanBlackList-update");
	}
	
	
	/**
	 * 加载明细列表[黑名单文档]
	 * 
	 * @return
	 */
	@RequestMapping(params = "zmlLoanBlackListDocumentList")
	public ModelAndView zmlLoanBlackListDocumentList(ZmlLoanBlackListEntity zmlLoanBlackList, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = zmlLoanBlackList.getId();
		//===================================================================================
		//查询-黑名单文档
	    String hql0 = "from ZmlLoanBlackListDocumentEntity where 1 = 1 AND bL_ID = ? ";
	    try{
	    	List<ZmlLoanBlackListDocumentEntity> zmlLoanBlackListDocumentEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("zmlLoanBlackListDocumentList", zmlLoanBlackListDocumentEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/zml_loan/black_list/zmlLoanBlackListDocumentList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(ZmlLoanBlackListEntity zmlLoanBlackList,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(ZmlLoanBlackListEntity.class, dataGrid);
    	//查询条件组装器
    	HqlGenerateUtil.installHql(cq, zmlLoanBlackList);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<ZmlLoanBlackListEntity> list=this.zmlLoanBlackListService.getListByCriteriaQuery(cq, false);
    	List<ZmlLoanBlackListPage> pageList=new ArrayList<ZmlLoanBlackListPage>();
        if(list!=null&&list.size()>0){
        	for(ZmlLoanBlackListEntity entity:list){
        		try{
        		ZmlLoanBlackListPage page=new ZmlLoanBlackListPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            	    Object id0 = entity.getId();
				    String hql0 = "from ZmlLoanBlackListDocumentEntity where 1 = 1 AND bL_ID = ? ";
        	        List<ZmlLoanBlackListDocumentEntity> zmlLoanBlackListDocumentEntityList = systemService.findHql(hql0,id0);
            		page.setZmlLoanBlackListDocumentList(zmlLoanBlackListDocumentEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"黑名单表");
        map.put(NormalExcelConstants.CLASS,ZmlLoanBlackListPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("黑名单表列表", "导出人:Jeecg",
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
				List<ZmlLoanBlackListPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), ZmlLoanBlackListPage.class, params);
				ZmlLoanBlackListEntity entity1=null;
				for (ZmlLoanBlackListPage page : list) {
					entity1=new ZmlLoanBlackListEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            zmlLoanBlackListService.addMain(entity1, page.getZmlLoanBlackListDocumentList());
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
		map.put(NormalExcelConstants.FILE_NAME,"黑名单表");
		map.put(NormalExcelConstants.CLASS,ZmlLoanBlackListPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("黑名单表列表", "导出人:"+ ResourceUtil.getSessionUserName().getRealName(),
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
		req.setAttribute("controller_name", "zmlLoanBlackListController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ZmlLoanBlackListEntity> list() {
		List<ZmlLoanBlackListEntity> listZmlLoanBlackLists=zmlLoanBlackListService.getList(ZmlLoanBlackListEntity.class);
		return listZmlLoanBlackLists;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlLoanBlackListEntity task = zmlLoanBlackListService.get(ZmlLoanBlackListEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlLoanBlackListPage zmlLoanBlackListPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanBlackListPage>> failures = validator.validate(zmlLoanBlackListPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<ZmlLoanBlackListDocumentEntity> zmlLoanBlackListDocumentList =  zmlLoanBlackListPage.getZmlLoanBlackListDocumentList();
		
		ZmlLoanBlackListEntity zmlLoanBlackList = new ZmlLoanBlackListEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(zmlLoanBlackList,zmlLoanBlackListPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		zmlLoanBlackListService.addMain(zmlLoanBlackList, zmlLoanBlackListDocumentList);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlLoanBlackListPage.getId();
		URI uri = uriBuilder.path("/rest/zmlLoanBlackListController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlLoanBlackListPage zmlLoanBlackListPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanBlackListPage>> failures = validator.validate(zmlLoanBlackListPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<ZmlLoanBlackListDocumentEntity> zmlLoanBlackListDocumentList =  zmlLoanBlackListPage.getZmlLoanBlackListDocumentList();
		
		ZmlLoanBlackListEntity zmlLoanBlackList = new ZmlLoanBlackListEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(zmlLoanBlackList,zmlLoanBlackListPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		zmlLoanBlackListService.updateMain(zmlLoanBlackList, zmlLoanBlackListDocumentList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		ZmlLoanBlackListEntity zmlLoanBlackList = zmlLoanBlackListService.get(ZmlLoanBlackListEntity.class, id);
		zmlLoanBlackListService.delMain(zmlLoanBlackList);
	}
}
