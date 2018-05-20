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

import com.zml.base.loan.entity.ZmlLoanContractEntity;
import com.zml.base.loan.entity.ZmlLoanPayDocumentEntity;
import com.zml.base.loan.entity.ZmlLoanPayLoanInfoEntity;
import com.zml.base.loan.page.ZmlLoanPayLoanInfoPage;
import com.zml.loan_service.ZmlLoanContractServiceI;
import com.zml.loan_service.ZmlLoanPayLoanInfoServiceI;

/**   
 * @Title: Controller
 * @Description: 放款信息
 */
@Controller
@RequestMapping("/zmlLoanPayLoanInfoController")
public class ZmlLoanPayLoanInfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlLoanPayLoanInfoController.class);
	
	@Autowired
	private ZmlLoanContractServiceI zmlLoanContractService;
	
	@Autowired
	private ZmlLoanPayLoanInfoServiceI zmlLoanPayLoanInfoService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 放款信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml_loan/pay_loan_info/zmlLoanPayLoanInfoList");
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
	public void datagrid(ZmlLoanPayLoanInfoEntity zmlLoanPayLoanInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlLoanPayLoanInfoEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, zmlLoanPayLoanInfo);
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlLoanPayLoanInfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除放款信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlLoanPayLoanInfoEntity zmlLoanPayLoanInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		zmlLoanPayLoanInfo = systemService.getEntity(ZmlLoanPayLoanInfoEntity.class, zmlLoanPayLoanInfo.getId());
		String message = "放款信息删除成功";
		try{
			zmlLoanPayLoanInfoService.delMain(zmlLoanPayLoanInfo);
			systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "放款信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除放款信息
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "放款信息删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlLoanPayLoanInfoEntity zmlLoanPayLoanInfo = systemService.getEntity(ZmlLoanPayLoanInfoEntity.class,
				id
				);
				zmlLoanPayLoanInfoService.delMain(zmlLoanPayLoanInfo);
				systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "放款信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加放款信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlLoanPayLoanInfoEntity zmlLoanPayLoanInfo,ZmlLoanPayLoanInfoPage zmlLoanPayLoanInfoPage, HttpServletRequest request) {
		List<ZmlLoanPayDocumentEntity> zmlLoanPayDocumentList =  zmlLoanPayLoanInfoPage.getZmlLoanPayDocumentList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			zmlLoanPayLoanInfo.setOperatorId(user.getId());
			zmlLoanPayLoanInfoService.addMain(zmlLoanPayLoanInfo, zmlLoanPayDocumentList);
			systemService.addLog("", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "放款信息添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新放款信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlLoanPayLoanInfoEntity zmlLoanPayLoanInfo,ZmlLoanPayLoanInfoPage zmlLoanPayLoanInfoPage, HttpServletRequest request) {
		List<ZmlLoanPayDocumentEntity> zmlLoanPayDocumentList =  zmlLoanPayLoanInfoPage.getZmlLoanPayDocumentList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			zmlLoanPayLoanInfoService.updateMain(zmlLoanPayLoanInfo, zmlLoanPayDocumentList);
			systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新放款信息失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 放款信息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlLoanPayLoanInfoEntity zmlLoanPayLoanInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanPayLoanInfo.getId())) {
			zmlLoanPayLoanInfo = zmlLoanPayLoanInfoService.getEntity(ZmlLoanPayLoanInfoEntity.class, zmlLoanPayLoanInfo.getId());
			req.setAttribute("zmlLoanPayLoanInfoPage", zmlLoanPayLoanInfo);
		}
		return new ModelAndView("com/zml_loan/pay_loan_info/zmlLoanPayLoanInfo-add");
	}
	
	/**
	 * 放款跳转 
	 * 
	 * @return
	 */
	@RequestMapping(params = "goPayLoan")
	public ModelAndView goPayLoan(String id, HttpServletRequest req) {
		ZmlLoanContractEntity zmlLoanContract = null;
		if (StringUtil.isNotEmpty(id)) {
			zmlLoanContract = zmlLoanContractService.getEntity(ZmlLoanContractEntity.class, id);
			req.setAttribute("zmlLoanContract", zmlLoanContract);
		}
		
		return new ModelAndView("com/zml_loan/pay_loan_info/payLoan");
	}
	
	/**
	 * 放款信息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlLoanPayLoanInfoEntity zmlLoanPayLoanInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanPayLoanInfo.getId())) {
			zmlLoanPayLoanInfo = zmlLoanPayLoanInfoService.getEntity(ZmlLoanPayLoanInfoEntity.class, zmlLoanPayLoanInfo.getId());
			req.setAttribute("zmlLoanPayLoanInfoPage", zmlLoanPayLoanInfo);
		}
		return new ModelAndView("com/zml_loan/pay_loan_info/zmlLoanPayLoanInfo-update");
	}
	/**
	 * 加载明细列表[放款文档]
	 * 
	 * @return
	 */
	@RequestMapping(params = "zmlLoanPayDocumentList")
	public ModelAndView zmlLoanPayDocumentList(ZmlLoanPayLoanInfoEntity zmlLoanPayLoanInfo, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = zmlLoanPayLoanInfo.getId();
		//===================================================================================
		//查询-放款文档
	    String hql0 = "from ZmlLoanPayDocumentEntity where 1 = 1 AND pAY_ID = ? ";
	    try{
	    	List<ZmlLoanPayDocumentEntity> zmlLoanPayDocumentEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("zmlLoanPayDocumentList", zmlLoanPayDocumentEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/zml_loan/pay_loan_info/zmlLoanPayDocumentList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(ZmlLoanPayLoanInfoEntity zmlLoanPayLoanInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(ZmlLoanPayLoanInfoEntity.class, dataGrid);
    	//查询条件组装器
    	HqlGenerateUtil.installHql(cq, zmlLoanPayLoanInfo);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<ZmlLoanPayLoanInfoEntity> list=this.zmlLoanPayLoanInfoService.getListByCriteriaQuery(cq, false);
    	List<ZmlLoanPayLoanInfoPage> pageList=new ArrayList<ZmlLoanPayLoanInfoPage>();
        if(list!=null&&list.size()>0){
        	for(ZmlLoanPayLoanInfoEntity entity:list){
        		try{
        		ZmlLoanPayLoanInfoPage page=new ZmlLoanPayLoanInfoPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            	    Object id0 = entity.getId();
				    String hql0 = "from ZmlLoanPayDocumentEntity where 1 = 1 AND pAY_ID = ? ";
        	        List<ZmlLoanPayDocumentEntity> zmlLoanPayDocumentEntityList = systemService.findHql(hql0,id0);
            		page.setZmlLoanPayDocumentList(zmlLoanPayDocumentEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"放款信息");
        map.put(NormalExcelConstants.CLASS,ZmlLoanPayLoanInfoPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("放款信息列表", "导出人:Jeecg",
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
				List<ZmlLoanPayLoanInfoPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), ZmlLoanPayLoanInfoPage.class, params);
				ZmlLoanPayLoanInfoEntity entity1=null;
				for (ZmlLoanPayLoanInfoPage page : list) {
					entity1=new ZmlLoanPayLoanInfoEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            zmlLoanPayLoanInfoService.addMain(entity1, page.getZmlLoanPayDocumentList());
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
		map.put(NormalExcelConstants.FILE_NAME,"放款信息");
		map.put(NormalExcelConstants.CLASS,ZmlLoanPayLoanInfoPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("放款信息列表", "导出人:"+ ResourceUtil.getSessionUserName().getRealName(),
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
		req.setAttribute("controller_name", "zmlLoanPayLoanInfoController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ZmlLoanPayLoanInfoEntity> list() {
		List<ZmlLoanPayLoanInfoEntity> listZmlLoanPayLoanInfos=zmlLoanPayLoanInfoService.getList(ZmlLoanPayLoanInfoEntity.class);
		return listZmlLoanPayLoanInfos;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlLoanPayLoanInfoEntity task = zmlLoanPayLoanInfoService.get(ZmlLoanPayLoanInfoEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlLoanPayLoanInfoPage zmlLoanPayLoanInfoPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanPayLoanInfoPage>> failures = validator.validate(zmlLoanPayLoanInfoPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<ZmlLoanPayDocumentEntity> zmlLoanPayDocumentList =  zmlLoanPayLoanInfoPage.getZmlLoanPayDocumentList();
		
		ZmlLoanPayLoanInfoEntity zmlLoanPayLoanInfo = new ZmlLoanPayLoanInfoEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(zmlLoanPayLoanInfo,zmlLoanPayLoanInfoPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		zmlLoanPayLoanInfoService.addMain(zmlLoanPayLoanInfo, zmlLoanPayDocumentList);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlLoanPayLoanInfoPage.getId();
		URI uri = uriBuilder.path("/rest/zmlLoanPayLoanInfoController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlLoanPayLoanInfoPage zmlLoanPayLoanInfoPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanPayLoanInfoPage>> failures = validator.validate(zmlLoanPayLoanInfoPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<ZmlLoanPayDocumentEntity> zmlLoanPayDocumentList =  zmlLoanPayLoanInfoPage.getZmlLoanPayDocumentList();
		
		ZmlLoanPayLoanInfoEntity zmlLoanPayLoanInfo = new ZmlLoanPayLoanInfoEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(zmlLoanPayLoanInfo,zmlLoanPayLoanInfoPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		zmlLoanPayLoanInfoService.updateMain(zmlLoanPayLoanInfo, zmlLoanPayDocumentList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		ZmlLoanPayLoanInfoEntity zmlLoanPayLoanInfo = zmlLoanPayLoanInfoService.get(ZmlLoanPayLoanInfoEntity.class, id);
		zmlLoanPayLoanInfoService.delMain(zmlLoanPayLoanInfo);
	}
}
