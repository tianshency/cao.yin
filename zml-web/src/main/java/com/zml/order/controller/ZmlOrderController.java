package com.zml.order.controller;
import com.zml.order.entity.ZmlOrderEntity;
import com.zml.order.service.ZmlOrderServiceI;
import com.zml.order.page.ZmlOrderPage;
import com.zml.order.entity.ZmlOrderCommodityEntity;
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
 * @Description: 订单
 * @author onlineGenerator
 * @date 2017-01-08 22:29:41
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zmlOrderController")
public class ZmlOrderController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlOrderController.class);

	@Autowired
	private ZmlOrderServiceI zmlOrderService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 订单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml/order/zmlOrderList");
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
	public void datagrid(ZmlOrderEntity zmlOrder,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlOrderEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zmlOrder);
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlOrderService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除订单
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlOrderEntity zmlOrder, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		zmlOrder = systemService.getEntity(ZmlOrderEntity.class, zmlOrder.getId());
		String message = "订单删除成功";
		try{
			zmlOrderService.delMain(zmlOrder);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "订单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除订单
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "订单删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlOrderEntity zmlOrder = systemService.getEntity(ZmlOrderEntity.class,
				id
				);
				zmlOrderService.delMain(zmlOrder);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "订单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加订单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlOrderEntity zmlOrder,ZmlOrderPage zmlOrderPage, HttpServletRequest request) {
		List<ZmlOrderCommodityEntity> zmlOrderCommodityList =  zmlOrderPage.getZmlOrderCommodityList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			zmlOrderService.addMain(zmlOrder, zmlOrderCommodityList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "订单添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新订单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlOrderEntity zmlOrder,ZmlOrderPage zmlOrderPage, HttpServletRequest request) {
		List<ZmlOrderCommodityEntity> zmlOrderCommodityList =  zmlOrderPage.getZmlOrderCommodityList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			zmlOrderService.updateMain(zmlOrder, zmlOrderCommodityList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新订单失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 订单新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlOrderEntity zmlOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlOrder.getId())) {
			zmlOrder = zmlOrderService.getEntity(ZmlOrderEntity.class, zmlOrder.getId());
			req.setAttribute("zmlOrderPage", zmlOrder);
		}
		return new ModelAndView("com/zml/order/zmlOrder-add");
	}
	
	/**
	 * 订单编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlOrderEntity zmlOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlOrder.getId())) {
			zmlOrder = zmlOrderService.getEntity(ZmlOrderEntity.class, zmlOrder.getId());
			req.setAttribute("zmlOrderPage", zmlOrder);
		}
		return new ModelAndView("com/zml/order/zmlOrder-update");
	}
	
	
	/**
	 * 加载明细列表[订单对应商品]
	 * 
	 * @return
	 */
	@RequestMapping(params = "zmlOrderCommodityList")
	public ModelAndView zmlOrderCommodityList(ZmlOrderEntity zmlOrder, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object oRDER_NUM0 = zmlOrder.getOrderNum();
		//===================================================================================
		//查询-订单对应商品
	    String hql0 = "from ZmlOrderCommodityEntity where 1 = 1 AND oRDER_NUM = ? ";
	    try{
	    	List<ZmlOrderCommodityEntity> zmlOrderCommodityEntityList = systemService.findHql(hql0,oRDER_NUM0);
			req.setAttribute("zmlOrderCommodityList", zmlOrderCommodityEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/zml/order/zmlOrderCommodityList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(ZmlOrderEntity zmlOrder,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(ZmlOrderEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zmlOrder);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<ZmlOrderEntity> list=this.zmlOrderService.getListByCriteriaQuery(cq, false);
    	List<ZmlOrderPage> pageList=new ArrayList<ZmlOrderPage>();
        if(list!=null&&list.size()>0){
        	for(ZmlOrderEntity entity:list){
        		try{
        		ZmlOrderPage page=new ZmlOrderPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
           		    Object oRDER_NUM0 = entity.getOrderNum();
				    String hql0 = "from ZmlOrderCommodityEntity where 1 = 1 AND oRDER_NUM = ? ";
        	        List<ZmlOrderCommodityEntity> zmlOrderCommodityEntityList = systemService.findHql(hql0,oRDER_NUM0);
            		page.setZmlOrderCommodityList(zmlOrderCommodityEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"订单");
        map.put(NormalExcelConstants.CLASS,ZmlOrderPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("订单列表", "导出人:Jeecg",
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
				List<ZmlOrderPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), ZmlOrderPage.class, params);
				ZmlOrderEntity entity1=null;
				for (ZmlOrderPage page : list) {
					entity1=new ZmlOrderEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            zmlOrderService.addMain(entity1, page.getZmlOrderCommodityList());
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
		map.put(NormalExcelConstants.FILE_NAME,"订单");
		map.put(NormalExcelConstants.CLASS,ZmlOrderPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("订单列表", "导出人:"+ ResourceUtil.getSessionUserName().getRealName(),
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
		req.setAttribute("controller_name", "zmlOrderController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ZmlOrderEntity> list() {
		List<ZmlOrderEntity> listZmlOrders=zmlOrderService.getList(ZmlOrderEntity.class);
		return listZmlOrders;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlOrderEntity task = zmlOrderService.get(ZmlOrderEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlOrderPage zmlOrderPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlOrderPage>> failures = validator.validate(zmlOrderPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<ZmlOrderCommodityEntity> zmlOrderCommodityList =  zmlOrderPage.getZmlOrderCommodityList();
		
		ZmlOrderEntity zmlOrder = new ZmlOrderEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(zmlOrder,zmlOrderPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		zmlOrderService.addMain(zmlOrder, zmlOrderCommodityList);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlOrderPage.getId();
		URI uri = uriBuilder.path("/rest/zmlOrderController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlOrderPage zmlOrderPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlOrderPage>> failures = validator.validate(zmlOrderPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<ZmlOrderCommodityEntity> zmlOrderCommodityList =  zmlOrderPage.getZmlOrderCommodityList();
		
		ZmlOrderEntity zmlOrder = new ZmlOrderEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(zmlOrder,zmlOrderPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		zmlOrderService.updateMain(zmlOrder, zmlOrderCommodityList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		ZmlOrderEntity zmlOrder = zmlOrderService.get(ZmlOrderEntity.class, id);
		zmlOrderService.delMain(zmlOrder);
	}
}
