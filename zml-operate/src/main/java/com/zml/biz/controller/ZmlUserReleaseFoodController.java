package com.zml.biz.controller;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
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
import com.zml.base.entity.ZmlUserReleaseFoodEntity;
import com.zml.service.ZmlUserReleaseFoodServiceI;

/**   
 * @Title: Controller  
 * @Description: 发布粮食
 *
 */
@Controller
@RequestMapping("/zmlUserReleaseFoodController")
public class ZmlUserReleaseFoodController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlUserReleaseFoodController.class);

	@Autowired
	private ZmlUserReleaseFoodServiceI zmlUserReleaseFoodService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 发布粮食列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml/release_food/zmlUserReleaseFoodList");
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
	public void datagrid(ZmlUserReleaseFoodEntity zmlUserReleaseFood,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlUserReleaseFoodEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, zmlUserReleaseFood, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlUserReleaseFoodService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除发布粮食
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlUserReleaseFoodEntity zmlUserReleaseFood, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zmlUserReleaseFood = systemService.getEntity(ZmlUserReleaseFoodEntity.class, zmlUserReleaseFood.getId());
		message = "发布粮食删除成功";
		try{
			zmlUserReleaseFoodService.delete(zmlUserReleaseFood);
			systemService.addLog("/zmlUserReleaseFoodController.do?doDel", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "发布粮食删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除发布粮食
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "发布粮食删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlUserReleaseFoodEntity zmlUserReleaseFood = systemService.getEntity(ZmlUserReleaseFoodEntity.class, 
				id
				);
				zmlUserReleaseFoodService.delete(zmlUserReleaseFood);
				systemService.addLog("/zmlUserReleaseFoodController.do?doBatchDel", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "发布粮食删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加发布粮食
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlUserReleaseFoodEntity zmlUserReleaseFood, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "发布粮食添加成功";
		try{
			zmlUserReleaseFoodService.save(zmlUserReleaseFood);
			systemService.addLog("/zmlUserReleaseFoodController.do?doAdd", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "发布粮食添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新发布粮食
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlUserReleaseFoodEntity zmlUserReleaseFood, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "发布粮食更新成功";
		ZmlUserReleaseFoodEntity t = zmlUserReleaseFoodService.get(ZmlUserReleaseFoodEntity.class, zmlUserReleaseFood.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zmlUserReleaseFood, t);
			zmlUserReleaseFoodService.saveOrUpdate(t);
			systemService.addLog("/zmlUserReleaseFoodController.do?doUpdate", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "发布粮食更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 审批发布粮食
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doApproval")
	@ResponseBody
	public AjaxJson doApproval(ZmlUserReleaseFoodEntity zmlUserReleaseFood, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "审批成功";
		ZmlUserReleaseFoodEntity t = zmlUserReleaseFoodService.get(ZmlUserReleaseFoodEntity.class, zmlUserReleaseFood.getId());
		try {
			zmlUserReleaseFood.setApprovalTime(new Date());
			zmlUserReleaseFood.setApprovalUserId(ResourceUtil.getSessionUserName().getId());
			MyBeanUtils.copyBeanNotNull2Bean(zmlUserReleaseFood, t);
			zmlUserReleaseFoodService.saveOrUpdate(t);
			systemService.addLog("/zmlUserReleaseFoodController.do?doApproval", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "审批失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 发布粮食新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlUserReleaseFoodEntity zmlUserReleaseFood, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlUserReleaseFood.getId())) {
			zmlUserReleaseFood = zmlUserReleaseFoodService.getEntity(ZmlUserReleaseFoodEntity.class, zmlUserReleaseFood.getId());
			req.setAttribute("zmlUserReleaseFoodPage", zmlUserReleaseFood);
		}
		return new ModelAndView("com/zml/release_food/zmlUserReleaseFood-add");
	}
	/**
	 * 发布粮食编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlUserReleaseFoodEntity zmlUserReleaseFood, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlUserReleaseFood.getId())) {
			zmlUserReleaseFood = zmlUserReleaseFoodService.getEntity(ZmlUserReleaseFoodEntity.class, zmlUserReleaseFood.getId());
			req.setAttribute("zmlUserReleaseFoodPage", zmlUserReleaseFood);
		}
		return new ModelAndView("com/zml/release_food/zmlUserReleaseFood-update");
	}
	
	
	/**
	 * 发布粮食 审批 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goApproval")
	public ModelAndView goApproval(ZmlUserReleaseFoodEntity zmlUserReleaseFood, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlUserReleaseFood.getId())) {
			zmlUserReleaseFood = zmlUserReleaseFoodService.getEntity(ZmlUserReleaseFoodEntity.class, zmlUserReleaseFood.getId());
			req.setAttribute("zmlUserReleaseFoodPage", zmlUserReleaseFood);
		}
		return new ModelAndView("com/zml/release_food/infoFoodApproval");
	}
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","zmlUserReleaseFoodController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ZmlUserReleaseFoodEntity zmlUserReleaseFood,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ZmlUserReleaseFoodEntity.class, dataGrid);
		HqlGenerateUtil.installHql(cq, zmlUserReleaseFood, request.getParameterMap());
		List<ZmlUserReleaseFoodEntity> zmlUserReleaseFoods = this.zmlUserReleaseFoodService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"发布粮食");
		modelMap.put(NormalExcelConstants.CLASS,ZmlUserReleaseFoodEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("发布粮食列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,zmlUserReleaseFoods);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ZmlUserReleaseFoodEntity zmlUserReleaseFood,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"发布粮食");
    	modelMap.put(NormalExcelConstants.CLASS,ZmlUserReleaseFoodEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("发布粮食列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<ZmlUserReleaseFoodEntity> listZmlUserReleaseFoodEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ZmlUserReleaseFoodEntity.class,params);
				for (ZmlUserReleaseFoodEntity zmlUserReleaseFood : listZmlUserReleaseFoodEntitys) {
					zmlUserReleaseFoodService.save(zmlUserReleaseFood);
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
	public List<ZmlUserReleaseFoodEntity> list() {
		List<ZmlUserReleaseFoodEntity> listZmlUserReleaseFoods=zmlUserReleaseFoodService.getList(ZmlUserReleaseFoodEntity.class);
		return listZmlUserReleaseFoods;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlUserReleaseFoodEntity task = zmlUserReleaseFoodService.get(ZmlUserReleaseFoodEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlUserReleaseFoodEntity zmlUserReleaseFood, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlUserReleaseFoodEntity>> failures = validator.validate(zmlUserReleaseFood);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlUserReleaseFoodService.save(zmlUserReleaseFood);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlUserReleaseFood.getId();
		URI uri = uriBuilder.path("/rest/zmlUserReleaseFoodController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlUserReleaseFoodEntity zmlUserReleaseFood) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlUserReleaseFoodEntity>> failures = validator.validate(zmlUserReleaseFood);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlUserReleaseFoodService.saveOrUpdate(zmlUserReleaseFood);
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
		zmlUserReleaseFoodService.deleteEntityById(ZmlUserReleaseFoodEntity.class, id);
	}
}
