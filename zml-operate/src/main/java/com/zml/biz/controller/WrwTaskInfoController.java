package com.zml.biz.controller;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
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
import com.zml.base.page.WrwTaskInfoPage;
import com.zml.base.wrw.entity.WrwTaskInfoEntity;
import com.zml.base.wrw.entity.WrwTaskUserRelationEntity;
import com.zml.service.WrwTaskInfoServiceI;

/**   
 * @Title: Controller
 * @Description: 任务表
 */
@Controller
@RequestMapping("/wrwTaskInfoController")
public class WrwTaskInfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WrwTaskInfoController.class);

	@Autowired
	private WrwTaskInfoServiceI wrwTaskInfoService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 任务表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml_loan/wrw_task/wrwTaskInfoList");
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
	public void datagrid(WrwTaskInfoEntity wrwTaskInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WrwTaskInfoEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, wrwTaskInfo);
		try{
		//自定义追加查询条件
		String query_startDate_begin = request.getParameter("startDate_begin");
		String query_startDate_end = request.getParameter("startDate_end");
		if(StringUtil.isNotEmpty(query_startDate_begin)){
			cq.ge("startDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_startDate_begin));
		}
		if(StringUtil.isNotEmpty(query_startDate_end)){
			cq.le("startDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_startDate_end));
		}
		String query_endDate_begin = request.getParameter("endDate_begin");
		String query_endDate_end = request.getParameter("endDate_end");
		if(StringUtil.isNotEmpty(query_endDate_begin)){
			cq.ge("endDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_endDate_begin));
		}
		if(StringUtil.isNotEmpty(query_endDate_end)){
			cq.le("endDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_endDate_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.wrwTaskInfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除任务表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WrwTaskInfoEntity wrwTaskInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		wrwTaskInfo = systemService.getEntity(WrwTaskInfoEntity.class, wrwTaskInfo.getId());
		String message = "任务表删除成功";
		try{
			wrwTaskInfoService.delMain(wrwTaskInfo);
			systemService.addLog("/wrwTaskInfoController/doDel", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "任务表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除任务表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "任务表删除成功";
		try{
			for(String id:ids.split(",")){
				WrwTaskInfoEntity wrwTaskInfo = systemService.getEntity(WrwTaskInfoEntity.class,
				id
				);
				wrwTaskInfoService.delMain(wrwTaskInfo);
				systemService.addLog("/wrwTaskInfoController/doBatchDel", "",message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "任务表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加任务表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WrwTaskInfoEntity wrwTaskInfo,WrwTaskInfoPage wrwTaskInfoPage, HttpServletRequest request) {
		List<WrwTaskUserRelationEntity> wrwTaskUserRelationList =  wrwTaskInfoPage.getWrwTaskUserRelationList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			wrwTaskInfoService.addMain(wrwTaskInfo, wrwTaskUserRelationList);
			systemService.addLog("/wrwTaskInfoController/doAdd", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "任务表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新任务表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WrwTaskInfoEntity wrwTaskInfo,WrwTaskInfoPage wrwTaskInfoPage, HttpServletRequest request) {
		List<WrwTaskUserRelationEntity> wrwTaskUserRelationList =  wrwTaskInfoPage.getWrwTaskUserRelationList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			wrwTaskInfoService.updateMain(wrwTaskInfo, wrwTaskUserRelationList);
			systemService.addLog("/wrwTaskInfoController/doUpdate", "",message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新任务表失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 任务表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WrwTaskInfoEntity wrwTaskInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(wrwTaskInfo.getId())) {
			wrwTaskInfo = wrwTaskInfoService.getEntity(WrwTaskInfoEntity.class, wrwTaskInfo.getId());
			req.setAttribute("wrwTaskInfoPage", wrwTaskInfo);
		}
		return new ModelAndView("com/zml_loan/wrw_task/wrwTaskInfo-add");
	}
	
	/**
	 * 任务表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WrwTaskInfoEntity wrwTaskInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(wrwTaskInfo.getId())) {
			wrwTaskInfo = wrwTaskInfoService.getEntity(WrwTaskInfoEntity.class, wrwTaskInfo.getId());
			req.setAttribute("wrwTaskInfoPage", wrwTaskInfo);
		}
		return new ModelAndView("com/zml_loan/wrw_task/wrwTaskInfo-update");
	}
	
	
	/**
	 * 加载明细列表[任务人]
	 * 
	 * @return
	 */
	@RequestMapping(params = "wrwTaskUserRelationList")
	public ModelAndView wrwTaskUserRelationList(WrwTaskInfoEntity wrwTaskInfo, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = wrwTaskInfo.getId();
		//===================================================================================
		//查询-任务人
	    String hql0 = "from WrwTaskUserRelationEntity where 1 = 1 AND tASK_ID = ?  AND uSER_ID = ? ";
	    try{
	    	List<WrwTaskUserRelationEntity> wrwTaskUserRelationEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("wrwTaskUserRelationList", wrwTaskUserRelationEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/zml_loan/wrw_task/wrwTaskUserRelationList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(WrwTaskInfoEntity wrwTaskInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(WrwTaskInfoEntity.class, dataGrid);
    	//查询条件组装器
    	HqlGenerateUtil.installHql(cq, wrwTaskInfo);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<WrwTaskInfoEntity> list=this.wrwTaskInfoService.getListByCriteriaQuery(cq, false);
    	List<WrwTaskInfoPage> pageList=new ArrayList<WrwTaskInfoPage>();
        if(list!=null&&list.size()>0){
        	for(WrwTaskInfoEntity entity:list){
        		try{
        		WrwTaskInfoPage page=new WrwTaskInfoPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            	    Object id0 = entity.getId();
				    String hql0 = "from WrwTaskUserRelationEntity where 1 = 1 AND tASK_ID = ?  AND uSER_ID = ? ";
        	        List<WrwTaskUserRelationEntity> wrwTaskUserRelationEntityList = systemService.findHql(hql0,id0);
            		page.setWrwTaskUserRelationList(wrwTaskUserRelationEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"任务表");
        map.put(NormalExcelConstants.CLASS,WrwTaskInfoPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("任务表列表", "导出人:Jeecg",
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
				List<WrwTaskInfoPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), WrwTaskInfoPage.class, params);
				WrwTaskInfoEntity entity1=null;
				for (WrwTaskInfoPage page : list) {
					entity1=new WrwTaskInfoEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            wrwTaskInfoService.addMain(entity1, page.getWrwTaskUserRelationList());
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
		map.put(NormalExcelConstants.FILE_NAME,"任务表");
		map.put(NormalExcelConstants.CLASS,WrwTaskInfoPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("任务表列表", "导出人:"+ ResourceUtil.getSessionUserName().getRealName(),
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
		req.setAttribute("controller_name", "wrwTaskInfoController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<WrwTaskInfoEntity> list() {
		List<WrwTaskInfoEntity> listWrwTaskInfos=wrwTaskInfoService.getList(WrwTaskInfoEntity.class);
		return listWrwTaskInfos;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		WrwTaskInfoEntity task = wrwTaskInfoService.get(WrwTaskInfoEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody WrwTaskInfoPage wrwTaskInfoPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<WrwTaskInfoPage>> failures = validator.validate(wrwTaskInfoPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<WrwTaskUserRelationEntity> wrwTaskUserRelationList =  wrwTaskInfoPage.getWrwTaskUserRelationList();
		
		WrwTaskInfoEntity wrwTaskInfo = new WrwTaskInfoEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(wrwTaskInfo,wrwTaskInfoPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		wrwTaskInfoService.addMain(wrwTaskInfo, wrwTaskUserRelationList);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = wrwTaskInfoPage.getId();
		URI uri = uriBuilder.path("/rest/wrwTaskInfoController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody WrwTaskInfoPage wrwTaskInfoPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<WrwTaskInfoPage>> failures = validator.validate(wrwTaskInfoPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<WrwTaskUserRelationEntity> wrwTaskUserRelationList =  wrwTaskInfoPage.getWrwTaskUserRelationList();
		
		WrwTaskInfoEntity wrwTaskInfo = new WrwTaskInfoEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(wrwTaskInfo,wrwTaskInfoPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		wrwTaskInfoService.updateMain(wrwTaskInfo, wrwTaskUserRelationList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		WrwTaskInfoEntity wrwTaskInfo = wrwTaskInfoService.get(WrwTaskInfoEntity.class, id);
		wrwTaskInfoService.delMain(wrwTaskInfo);
	}
}
