package com.zml.commodity.controller;
import com.zml.commodity.entity.CfCommodityEntity;
import com.zml.commodity.service.CfCommodityServiceI;
import com.zml.commodity.page.CfCommodityPage;
import com.zml.commodity.entity.CfCommodityImgEntity;
import java.util.ArrayList;
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

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;
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
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller
 * @Description: 商品

 * @date 2016-12-19 19:52:54
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/cfCommodityController")
public class CfCommodityController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CfCommodityController.class);

	@Autowired
	private CfCommodityServiceI cfCommodityService;
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
		return new ModelAndView("com/zml/commodity/cfCommodityList");
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
	public void datagrid(CfCommodityEntity cfCommodity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CfCommodityEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cfCommodity);
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.cfCommodityService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除商品
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CfCommodityEntity cfCommodity, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		cfCommodity = systemService.getEntity(CfCommodityEntity.class, cfCommodity.getId());
		String message = "商品删除成功";
		try{
			cfCommodityService.delMain(cfCommodity);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
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
				CfCommodityEntity cfCommodity = systemService.getEntity(CfCommodityEntity.class,
				id
				);
				cfCommodityService.delMain(cfCommodity);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
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
	public AjaxJson doAdd(CfCommodityEntity cfCommodity,CfCommodityPage cfCommodityPage, HttpServletRequest request) {
		List<CfCommodityImgEntity> cfCommodityImgList =  cfCommodityPage.getCfCommodityImgList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			cfCommodityService.addMain(cfCommodity, cfCommodityImgList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
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
	public AjaxJson doUpdate(CfCommodityEntity cfCommodity,CfCommodityPage cfCommodityPage, HttpServletRequest request) {
		List<CfCommodityImgEntity> cfCommodityImgList =  cfCommodityPage.getCfCommodityImgList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			cfCommodityService.updateMain(cfCommodity, cfCommodityImgList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
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
	public ModelAndView goAdd(CfCommodityEntity cfCommodity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cfCommodity.getId())) {
			cfCommodity = cfCommodityService.getEntity(CfCommodityEntity.class, cfCommodity.getId());
			req.setAttribute("cfCommodityPage", cfCommodity);
		}
		return new ModelAndView("com/zml/commodity/cfCommodity-add");
	}
	
	/**
	 * 商品编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CfCommodityEntity cfCommodity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cfCommodity.getId())) {
			cfCommodity = cfCommodityService.getEntity(CfCommodityEntity.class, cfCommodity.getId());
			req.setAttribute("cfCommodityPage", cfCommodity);
		}
		return new ModelAndView("com/zml/commodity/cfCommodity-update");
	}
	
	
	/**
	 * 加载明细列表[商品图片]
	 * 
	 * @return
	 */
	@RequestMapping(params = "cfCommodityImgList")
	public ModelAndView cfCommodityImgList(CfCommodityEntity cfCommodity, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = cfCommodity.getId();
		//===================================================================================
		//查询-商品图片
	    String hql0 = "from CfCommodityImgEntity where 1 = 1 AND cOMMODITY_ID = ? ";
	    try{
	    	List<CfCommodityImgEntity> cfCommodityImgEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("cfCommodityImgList", cfCommodityImgEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/zml/commodity/cfCommodityImgList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(CfCommodityEntity cfCommodity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(CfCommodityEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cfCommodity);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<CfCommodityEntity> list=this.cfCommodityService.getListByCriteriaQuery(cq, false);
    	List<CfCommodityPage> pageList=new ArrayList<CfCommodityPage>();
        if(list!=null&&list.size()>0){
        	for(CfCommodityEntity entity:list){
        		try{
        		CfCommodityPage page=new CfCommodityPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            	    Object id0 = entity.getId();
				    String hql0 = "from CfCommodityImgEntity where 1 = 1 AND cOMMODITY_ID = ? ";
        	        List<CfCommodityImgEntity> cfCommodityImgEntityList = systemService.findHql(hql0,id0);
            		page.setCfCommodityImgList(cfCommodityImgEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"商品");
        map.put(NormalExcelConstants.CLASS,CfCommodityPage.class);
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
				List<CfCommodityPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), CfCommodityPage.class, params);
				CfCommodityEntity entity1=null;
				for (CfCommodityPage page : list) {
					entity1=new CfCommodityEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            cfCommodityService.addMain(entity1, page.getCfCommodityImgList());
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
		map.put(NormalExcelConstants.CLASS,CfCommodityPage.class);
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
		req.setAttribute("controller_name", "cfCommodityController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<CfCommodityEntity> list() {
		List<CfCommodityEntity> listCfCommoditys=cfCommodityService.getList(CfCommodityEntity.class);
		return listCfCommoditys;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		CfCommodityEntity task = cfCommodityService.get(CfCommodityEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody CfCommodityPage cfCommodityPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<CfCommodityPage>> failures = validator.validate(cfCommodityPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<CfCommodityImgEntity> cfCommodityImgList =  cfCommodityPage.getCfCommodityImgList();
		
		CfCommodityEntity cfCommodity = new CfCommodityEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(cfCommodity,cfCommodityPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		cfCommodityService.addMain(cfCommodity, cfCommodityImgList);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = cfCommodityPage.getId();
		URI uri = uriBuilder.path("/rest/cfCommodityController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody CfCommodityPage cfCommodityPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<CfCommodityPage>> failures = validator.validate(cfCommodityPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<CfCommodityImgEntity> cfCommodityImgList =  cfCommodityPage.getCfCommodityImgList();
		
		CfCommodityEntity cfCommodity = new CfCommodityEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(cfCommodity,cfCommodityPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		cfCommodityService.updateMain(cfCommodity, cfCommodityImgList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		CfCommodityEntity cfCommodity = cfCommodityService.get(CfCommodityEntity.class, id);
		cfCommodityService.delMain(cfCommodity);
	}
}
