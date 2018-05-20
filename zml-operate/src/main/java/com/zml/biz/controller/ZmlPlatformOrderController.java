package com.zml.biz.controller;
import com.zml.base.entity.ZmlPlatformOrderEntity;
import com.zml.service.ZmlPlatformOrderServiceI;
import com.zml.base.entity.ZmlPlatformOrderCommodityEntity;
import com.zml.base.entity.ZmlPorderToCorderEntity;
import com.zml.base.page.ZmlPlatformOrderPage;

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

import com.jce.framework.core.beanvalidator.BeanValidators;
import com.jce.framework.core.common.controller.BaseController;
import com.jce.framework.core.common.exception.BusinessException;
import com.jce.framework.core.common.hibernate.qbc.CriteriaQuery;
import com.jce.framework.core.common.model.json.AjaxJson;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.constant.Globals;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.tag.easyui.TagUtil;
import com.jce.framework.web.system.pojo.base.TSDepart;
import com.jce.framework.web.system.service.SystemService;
import com.jce.framework.core.util.ExceptionUtil;
import com.jce.framework.core.util.MyBeanUtils;
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
 * @Description: 平台订单
 * @author onlineGenerator
 * @date 2017-01-01 13:58:39
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zmlPlatformOrderController")
public class ZmlPlatformOrderController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlPlatformOrderController.class);

	@Autowired
	private ZmlPlatformOrderServiceI zmlPlatformOrderService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 平台订单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml/platform_order/zmlPlatformOrderList");
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
	public void datagrid(ZmlPlatformOrderEntity zmlPlatformOrder,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlPlatformOrderEntity.class, dataGrid);
		//查询条件组装器
		com.jce.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zmlPlatformOrder);
		try{
		//自定义追加查询条件
		String query_allAmount_begin = request.getParameter("allAmount_begin");
		String query_allAmount_end = request.getParameter("allAmount_end");
		if(StringUtil.isNotEmpty(query_allAmount_begin)){
			cq.ge("allAmount", Integer.parseInt(query_allAmount_begin));
		}
		if(StringUtil.isNotEmpty(query_allAmount_end)){
			cq.le("allAmount", Integer.parseInt(query_allAmount_end));
		}
		String query_orderTime_begin = request.getParameter("orderTime_begin");
		String query_orderTime_end = request.getParameter("orderTime_end");
		if(StringUtil.isNotEmpty(query_orderTime_begin)){
			cq.ge("orderTime", new SimpleDateFormat("yyyy-MM-dd").parse(query_orderTime_begin));
		}
		if(StringUtil.isNotEmpty(query_orderTime_end)){
			cq.le("orderTime", new SimpleDateFormat("yyyy-MM-dd").parse(query_orderTime_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlPlatformOrderService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除平台订单
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlPlatformOrderEntity zmlPlatformOrder, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		zmlPlatformOrder = systemService.getEntity(ZmlPlatformOrderEntity.class, zmlPlatformOrder.getId());
		String message = "平台订单删除成功";
		try{
			zmlPlatformOrderService.delMain(zmlPlatformOrder);
			systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "平台订单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除平台订单
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "平台订单删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlPlatformOrderEntity zmlPlatformOrder = systemService.getEntity(ZmlPlatformOrderEntity.class,
				id
				);
				zmlPlatformOrderService.delMain(zmlPlatformOrder);
				systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "平台订单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加平台订单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlPlatformOrderEntity zmlPlatformOrder,ZmlPlatformOrderPage zmlPlatformOrderPage, HttpServletRequest request) {
		List<ZmlPlatformOrderCommodityEntity> zmlPlatformOrderCommodityList =  zmlPlatformOrderPage.getZmlPlatformOrderCommodityList();
		List<ZmlPorderToCorderEntity> zmlPorderToCorderList =  zmlPlatformOrderPage.getZmlPorderToCorderList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			zmlPlatformOrderService.addMain(zmlPlatformOrder, zmlPlatformOrderCommodityList,zmlPorderToCorderList);
			systemService.addLog("", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "平台订单添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新平台订单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlPlatformOrderEntity zmlPlatformOrder,ZmlPlatformOrderPage zmlPlatformOrderPage, HttpServletRequest request) {
		List<ZmlPlatformOrderCommodityEntity> zmlPlatformOrderCommodityList =  zmlPlatformOrderPage.getZmlPlatformOrderCommodityList();
		List<ZmlPorderToCorderEntity> zmlPorderToCorderList =  zmlPlatformOrderPage.getZmlPorderToCorderList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			zmlPlatformOrderService.updateMain(zmlPlatformOrder, zmlPlatformOrderCommodityList,zmlPorderToCorderList);
			systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新平台订单失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 平台订单新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlPlatformOrderEntity zmlPlatformOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlPlatformOrder.getId())) {
			zmlPlatformOrder = zmlPlatformOrderService.getEntity(ZmlPlatformOrderEntity.class, zmlPlatformOrder.getId());
			req.setAttribute("zmlPlatformOrderPage", zmlPlatformOrder);
		}
		return new ModelAndView("com/zml/platform_order/zmlPlatformOrder-add");
	}
	
	/**
	 * 平台订单编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlPlatformOrderEntity zmlPlatformOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlPlatformOrder.getId())) {
			zmlPlatformOrder = zmlPlatformOrderService.getEntity(ZmlPlatformOrderEntity.class, zmlPlatformOrder.getId());
			req.setAttribute("zmlPlatformOrderPage", zmlPlatformOrder);
		}
		return new ModelAndView("com/zml/platform_order/zmlPlatformOrder-update");
	}
	
	
	/**
	 * 加载明细列表[对应商品]
	 * 
	 * @return
	 */
	@RequestMapping(params = "zmlPlatformOrderCommodityList")
	public ModelAndView zmlPlatformOrderCommodityList(ZmlPlatformOrderEntity zmlPlatformOrder, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object pORDER_NUM0 = zmlPlatformOrder.getPorderNum();
		//===================================================================================
		//查询-对应商品
	    String hql0 = "from ZmlPlatformOrderCommodityEntity where 1 = 1 AND pORDER_NUM = ? ";
	    try{
	    	List<ZmlPlatformOrderCommodityEntity> zmlPlatformOrderCommodityEntityList = systemService.findHql(hql0,pORDER_NUM0);
			req.setAttribute("zmlPlatformOrderCommodityList", zmlPlatformOrderCommodityEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/zml/platform_order/zmlPlatformOrderCommodityList");
	}
	/**
	 * 加载明细列表[对应用户订单]
	 * 
	 * @return
	 */
	@RequestMapping(params = "zmlPorderToCorderList")
	public ModelAndView zmlPorderToCorderList(ZmlPlatformOrderEntity zmlPlatformOrder, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object pORDER_NUM1 = zmlPlatformOrder.getPorderNum();
		//===================================================================================
		//查询-对应用户订单
	    String hql1 = "from ZmlPorderToCorderEntity where 1 = 1 AND pORDER_NUM = ? ";
	    try{
	    	List<ZmlPorderToCorderEntity> zmlPorderToCorderEntityList = systemService.findHql(hql1,pORDER_NUM1);
			req.setAttribute("zmlPorderToCorderList", zmlPorderToCorderEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/zml/platform_order/zmlPorderToCorderList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(ZmlPlatformOrderEntity zmlPlatformOrder,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(ZmlPlatformOrderEntity.class, dataGrid);
    	//查询条件组装器
    	com.jce.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zmlPlatformOrder);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<ZmlPlatformOrderEntity> list=this.zmlPlatformOrderService.getListByCriteriaQuery(cq, false);
    	List<ZmlPlatformOrderPage> pageList=new ArrayList<ZmlPlatformOrderPage>();
        if(list!=null&&list.size()>0){
        	for(ZmlPlatformOrderEntity entity:list){
        		try{
        		ZmlPlatformOrderPage page=new ZmlPlatformOrderPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
           		    Object pORDER_NUM0 = entity.getPorderNum();
				    String hql0 = "from ZmlPlatformOrderCommodityEntity where 1 = 1 AND pORDER_NUM = ? ";
        	        List<ZmlPlatformOrderCommodityEntity> zmlPlatformOrderCommodityEntityList = systemService.findHql(hql0,pORDER_NUM0);
            		page.setZmlPlatformOrderCommodityList(zmlPlatformOrderCommodityEntityList);
           		    Object pORDER_NUM1 = entity.getPorderNum();
				    String hql1 = "from ZmlPorderToCorderEntity where 1 = 1 AND pORDER_NUM = ? ";
        	        List<ZmlPorderToCorderEntity> zmlPorderToCorderEntityList = systemService.findHql(hql1,pORDER_NUM1);
            		page.setZmlPorderToCorderList(zmlPorderToCorderEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"平台订单");
        map.put(NormalExcelConstants.CLASS,ZmlPlatformOrderPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("平台订单列表", "导出人:Jeecg",
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
				List<ZmlPlatformOrderPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), ZmlPlatformOrderPage.class, params);
				ZmlPlatformOrderEntity entity1=null;
				for (ZmlPlatformOrderPage page : list) {
					entity1=new ZmlPlatformOrderEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            zmlPlatformOrderService.addMain(entity1, page.getZmlPlatformOrderCommodityList(),page.getZmlPorderToCorderList());
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
		map.put(NormalExcelConstants.FILE_NAME,"平台订单");
		map.put(NormalExcelConstants.CLASS,ZmlPlatformOrderPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("平台订单列表", "导出人:"+ ResourceUtil.getSessionUserName().getRealName(),
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
		req.setAttribute("controller_name", "zmlPlatformOrderController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ZmlPlatformOrderEntity> list() {
		List<ZmlPlatformOrderEntity> listZmlPlatformOrders=zmlPlatformOrderService.getList(ZmlPlatformOrderEntity.class);
		return listZmlPlatformOrders;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlPlatformOrderEntity task = zmlPlatformOrderService.get(ZmlPlatformOrderEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlPlatformOrderPage zmlPlatformOrderPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlPlatformOrderPage>> failures = validator.validate(zmlPlatformOrderPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<ZmlPlatformOrderCommodityEntity> zmlPlatformOrderCommodityList =  zmlPlatformOrderPage.getZmlPlatformOrderCommodityList();
		List<ZmlPorderToCorderEntity> zmlPorderToCorderList =  zmlPlatformOrderPage.getZmlPorderToCorderList();
		
		ZmlPlatformOrderEntity zmlPlatformOrder = new ZmlPlatformOrderEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(zmlPlatformOrder,zmlPlatformOrderPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		zmlPlatformOrderService.addMain(zmlPlatformOrder, zmlPlatformOrderCommodityList,zmlPorderToCorderList);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlPlatformOrderPage.getId();
		URI uri = uriBuilder.path("/rest/zmlPlatformOrderController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlPlatformOrderPage zmlPlatformOrderPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlPlatformOrderPage>> failures = validator.validate(zmlPlatformOrderPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<ZmlPlatformOrderCommodityEntity> zmlPlatformOrderCommodityList =  zmlPlatformOrderPage.getZmlPlatformOrderCommodityList();
		List<ZmlPorderToCorderEntity> zmlPorderToCorderList =  zmlPlatformOrderPage.getZmlPorderToCorderList();
		
		ZmlPlatformOrderEntity zmlPlatformOrder = new ZmlPlatformOrderEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(zmlPlatformOrder,zmlPlatformOrderPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		zmlPlatformOrderService.updateMain(zmlPlatformOrder, zmlPlatformOrderCommodityList,zmlPorderToCorderList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		ZmlPlatformOrderEntity zmlPlatformOrder = zmlPlatformOrderService.get(ZmlPlatformOrderEntity.class, id);
		zmlPlatformOrderService.delMain(zmlPlatformOrder);
	}
}
