package com.loan.controller;
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

import com.zml.base.loan.entity.ZmlLoanEstateInfoEntity;
import com.zml.loan_service.ZmlLoanEstateInfoServiceI;

/**   
 * @Title: Controller  
 * @Description: 借款房产信息
 * @author onlineGenerator
 * @date 2017-03-01 22:22:09
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zmlLoanEstateInfoController")
public class ZmlLoanEstateInfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlLoanEstateInfoController.class);

	@Autowired
	private ZmlLoanEstateInfoServiceI zmlLoanEstateInfoService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 借款房产信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml_loan/estate_info/zmlLoanEstateInfoList");
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
	public void datagrid(ZmlLoanEstateInfoEntity zmlLoanEstateInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlLoanEstateInfoEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, zmlLoanEstateInfo, request.getParameterMap());
		try{
		//自定义追加查询条件
		String query_totalArea_begin = request.getParameter("totalArea_begin");
		String query_totalArea_end = request.getParameter("totalArea_end");
		if(StringUtil.isNotEmpty(query_totalArea_begin)){
			cq.ge("totalArea", Integer.parseInt(query_totalArea_begin));
		}
		if(StringUtil.isNotEmpty(query_totalArea_end)){
			cq.le("totalArea", Integer.parseInt(query_totalArea_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlLoanEstateInfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除借款房产信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlLoanEstateInfoEntity zmlLoanEstateInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zmlLoanEstateInfo = systemService.getEntity(ZmlLoanEstateInfoEntity.class, zmlLoanEstateInfo.getId());
		message = "借款房产信息删除成功";
		try{
			zmlLoanEstateInfoService.delete(zmlLoanEstateInfo);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "借款房产信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除借款房产信息
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "借款房产信息删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlLoanEstateInfoEntity zmlLoanEstateInfo = systemService.getEntity(ZmlLoanEstateInfoEntity.class, 
				id
				);
				zmlLoanEstateInfoService.delete(zmlLoanEstateInfo);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "借款房产信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加借款房产信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlLoanEstateInfoEntity zmlLoanEstateInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "借款房产信息添加成功";
		try{
			zmlLoanEstateInfoService.save(zmlLoanEstateInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "借款房产信息添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新借款房产信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlLoanEstateInfoEntity zmlLoanEstateInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "借款房产信息更新成功";
		ZmlLoanEstateInfoEntity t = zmlLoanEstateInfoService.get(ZmlLoanEstateInfoEntity.class, zmlLoanEstateInfo.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zmlLoanEstateInfo, t);
			zmlLoanEstateInfoService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "借款房产信息更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 借款房产信息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlLoanEstateInfoEntity zmlLoanEstateInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanEstateInfo.getId())) {
			zmlLoanEstateInfo = zmlLoanEstateInfoService.getEntity(ZmlLoanEstateInfoEntity.class, zmlLoanEstateInfo.getId());
			req.setAttribute("zmlLoanEstateInfoPage", zmlLoanEstateInfo);
		}
		return new ModelAndView("com/zml_loan/estate_info/zmlLoanEstateInfo-add");
	}
	/**
	 * 借款房产信息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlLoanEstateInfoEntity zmlLoanEstateInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanEstateInfo.getId())) {
			zmlLoanEstateInfo = zmlLoanEstateInfoService.getEntity(ZmlLoanEstateInfoEntity.class, zmlLoanEstateInfo.getId());
			req.setAttribute("zmlLoanEstateInfoPage", zmlLoanEstateInfo);
		}
		return new ModelAndView("com/zml_loan/estate_info/zmlLoanEstateInfo-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","zmlLoanEstateInfoController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ZmlLoanEstateInfoEntity zmlLoanEstateInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ZmlLoanEstateInfoEntity.class, dataGrid);
		HqlGenerateUtil.installHql(cq, zmlLoanEstateInfo, request.getParameterMap());
		List<ZmlLoanEstateInfoEntity> zmlLoanEstateInfos = this.zmlLoanEstateInfoService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"借款房产信息");
		modelMap.put(NormalExcelConstants.CLASS,ZmlLoanEstateInfoEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("借款房产信息列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,zmlLoanEstateInfos);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ZmlLoanEstateInfoEntity zmlLoanEstateInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"借款房产信息");
    	modelMap.put(NormalExcelConstants.CLASS,ZmlLoanEstateInfoEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("借款房产信息列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<ZmlLoanEstateInfoEntity> listZmlLoanEstateInfoEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ZmlLoanEstateInfoEntity.class,params);
				for (ZmlLoanEstateInfoEntity zmlLoanEstateInfo : listZmlLoanEstateInfoEntitys) {
					zmlLoanEstateInfoService.save(zmlLoanEstateInfo);
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
	public List<ZmlLoanEstateInfoEntity> list() {
		List<ZmlLoanEstateInfoEntity> listZmlLoanEstateInfos=zmlLoanEstateInfoService.getList(ZmlLoanEstateInfoEntity.class);
		return listZmlLoanEstateInfos;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlLoanEstateInfoEntity task = zmlLoanEstateInfoService.get(ZmlLoanEstateInfoEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlLoanEstateInfoEntity zmlLoanEstateInfo, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanEstateInfoEntity>> failures = validator.validate(zmlLoanEstateInfo);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlLoanEstateInfoService.save(zmlLoanEstateInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlLoanEstateInfo.getId();
		URI uri = uriBuilder.path("/rest/zmlLoanEstateInfoController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlLoanEstateInfoEntity zmlLoanEstateInfo) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanEstateInfoEntity>> failures = validator.validate(zmlLoanEstateInfo);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlLoanEstateInfoService.saveOrUpdate(zmlLoanEstateInfo);
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
		zmlLoanEstateInfoService.deleteEntityById(ZmlLoanEstateInfoEntity.class, id);
	}
}
