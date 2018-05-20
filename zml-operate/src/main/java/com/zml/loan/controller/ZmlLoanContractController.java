package com.zml.loan.controller;
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
import com.jce.framework.web.system.pojo.base.TSUser;
import com.jce.framework.web.system.service.SystemService;
import com.zml.base.loan.entity.ZmlLoanApplicationEntity;
import com.zml.base.loan.entity.ZmlLoanApproveEntity;
import com.zml.base.loan.entity.ZmlLoanContractDocumentEntity;
import com.zml.base.loan.entity.ZmlLoanContractEntity;
import com.zml.base.loan.entity.ZmlLoanWfTaskEntity;
import com.zml.base.loan.page.ZmlLoanContractPage;
import com.zml.common.Constant;
import com.zml.enums.loan.ApprovalFlag;
import com.zml.enums.loan.ContractStatus;
import com.zml.enums.loan.LoanNoPrefix;
import com.zml.loan_service.ZmlLoanApplicationServiceI;
import com.zml.loan_service.ZmlLoanContractServiceI;
import com.zml.loan_service.ZmlLoanWfTaskServiceI;
import com.zml.util.DateUtil;
import com.zml.util.GenerateNo;

/**
 * @Title: Controller
 * @Description: 借款合同
 */
@Controller
@RequestMapping("/zmlLoanContractController")
public class ZmlLoanContractController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlLoanContractController.class);

	@Autowired
	private ZmlLoanContractServiceI zmlLoanContractService;
	@Autowired
	private ZmlLoanApplicationServiceI zmlLoanApplicationService;
	
	@Autowired
	private ZmlLoanWfTaskServiceI zmlLoanWfTaskService;
	
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 借款合同列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml_loan/contract/zmlLoanContractList");
	}
	/**
	 * 未创建借款合同列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "noCreateContractList")
	public ModelAndView noCreateContract(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("com/zml_loan/contract/noCreateList");
		return mv;
	}
	/**
	 * 创建合同 页面跳转
	 * id 业务申请ID
	 * @return
	 */
	@RequestMapping(params = "goCreateContract")
	public ModelAndView goCreateContract(String id ,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("com/zml_loan/contract/createContract");
		if (StringUtil.isNotEmpty(id)) {
			ZmlLoanApplicationEntity zmlLoanApplication = zmlLoanApplicationService.getEntity(ZmlLoanApplicationEntity.class, id);
			request.setAttribute("zmlLoanApplicationPage", zmlLoanApplication);
		}
		return mv;
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
	public void datagrid(ZmlLoanContractEntity zmlLoanContract,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlLoanContractEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, zmlLoanContract);
		try{
		//自定义追加查询条件
		String query_contractAmt_begin = request.getParameter("contractAmt_begin");
		String query_contractAmt_end = request.getParameter("contractAmt_end");
		if(StringUtil.isNotEmpty(query_contractAmt_begin)){
			cq.ge("contractAmt", Integer.parseInt(query_contractAmt_begin));
		}
		if(StringUtil.isNotEmpty(query_contractAmt_end)){
			cq.le("contractAmt", Integer.parseInt(query_contractAmt_end));
		}
		String query_contractBalance_begin = request.getParameter("contractBalance_begin");
		String query_contractBalance_end = request.getParameter("contractBalance_end");
		if(StringUtil.isNotEmpty(query_contractBalance_begin)){
			cq.ge("contractBalance", Integer.parseInt(query_contractBalance_begin));
		}
		if(StringUtil.isNotEmpty(query_contractBalance_end)){
			cq.le("contractBalance", Integer.parseInt(query_contractBalance_end));
		}
		String query_interestRate_begin = request.getParameter("interestRate_begin");
		String query_interestRate_end = request.getParameter("interestRate_end");
		if(StringUtil.isNotEmpty(query_interestRate_begin)){
			cq.ge("interestRate", Integer.parseInt(query_interestRate_begin));
		}
		if(StringUtil.isNotEmpty(query_interestRate_end)){
			cq.le("interestRate", Integer.parseInt(query_interestRate_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlLoanContractService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除借款合同
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlLoanContractEntity zmlLoanContract, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		zmlLoanContract = systemService.getEntity(ZmlLoanContractEntity.class, zmlLoanContract.getId());
		String message = "借款合同删除成功";
		try{
			zmlLoanContractService.delMain(zmlLoanContract);
			systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "借款合同删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除借款合同
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "借款合同删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlLoanContractEntity zmlLoanContract = systemService.getEntity(ZmlLoanContractEntity.class,
				id
				);
				zmlLoanContractService.delMain(zmlLoanContract);
				systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "借款合同删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	 /**
	 * 借款合同编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goApprover")
	public ModelAndView goApprover(ZmlLoanContractEntity zmlLoanContract, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanContract.getId())) {
			zmlLoanContract = zmlLoanContractService.getEntity(ZmlLoanContractEntity.class, zmlLoanContract.getId());
			req.setAttribute("zmlLoanContractPage", zmlLoanContract);
		}
		return new ModelAndView("com/zml_loan/contract/approveContract");
	}
	
	/**
	 * 审批合同
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doApprove")
	@ResponseBody
	public AjaxJson doApprove(ZmlLoanContractEntity zmlLoanContract,ZmlLoanContractPage zmlLoanContractPage, String wfId, HttpServletRequest request) {
		List<ZmlLoanContractDocumentEntity> zmlLoanContractDocumentList =  zmlLoanContractPage.getZmlLoanContractDocumentList();
		AjaxJson j = new AjaxJson();
		String message = "审批成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			zmlLoanContractService.doApprove(zmlLoanContract, user.getId(), wfId, zmlLoanContractDocumentList);
			systemService.addLog("", "", message, Globals.Log_Type_Contract_Approve, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "审批失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新借款合同
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlLoanContractEntity zmlLoanContract,ZmlLoanContractPage zmlLoanContractPage, HttpServletRequest request) {
		List<ZmlLoanContractDocumentEntity> zmlLoanContractDocumentList =  zmlLoanContractPage.getZmlLoanContractDocumentList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			zmlLoanContractService.updateMain(zmlLoanContract, zmlLoanContractDocumentList);
			systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新借款合同失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 审批合同
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doApproveContract")
	@ResponseBody
	public AjaxJson doApproveContract(ZmlLoanContractEntity zmlLoanContract,ZmlLoanContractPage zmlLoanContractPage, HttpServletRequest request) {
		List<ZmlLoanContractDocumentEntity> zmlLoanContractDocumentList =  zmlLoanContractPage.getZmlLoanContractDocumentList();
		AjaxJson j = new AjaxJson();
		String message = "审批成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			zmlLoanContract.setContractBalance(zmlLoanContract.getContractAmt());
			zmlLoanContract.setApprovalUserId(user.getId());
			zmlLoanContract.setApprovalDate(new Date());
			zmlLoanContractService.updateMain(zmlLoanContract, zmlLoanContractDocumentList);
			systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "提交失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 借款合同新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlLoanContractEntity zmlLoanContract, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanContract.getId())) {
			zmlLoanContract = zmlLoanContractService.getEntity(ZmlLoanContractEntity.class, zmlLoanContract.getId());
			req.setAttribute("zmlLoanContractPage", zmlLoanContract);
		}
		return new ModelAndView("com/zml_loan/contract/zmlLoanContract-add");
	}
	
	/**
	 * 借款合同编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlLoanContractEntity zmlLoanContract, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanContract.getId())) {
			zmlLoanContract = zmlLoanContractService.getEntity(ZmlLoanContractEntity.class, zmlLoanContract.getId());
			req.setAttribute("zmlLoanContractPage", zmlLoanContract);
		}
		return new ModelAndView("com/zml_loan/contract/zmlLoanContract-update");
	}
	
	
	/**
	 * 加载明细列表[合同文档]
	 * 
	 * @return
	 */
	@RequestMapping(params = "zmlLoanContractDocumentList")
	public ModelAndView zmlLoanContractDocumentList(ZmlLoanContractEntity zmlLoanContract, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = zmlLoanContract.getId();
		//===================================================================================
		//查询-合同文档
	    String hql0 = "from ZmlLoanContractDocumentEntity where 1 = 1 AND cONTRACT_ID = ? ";
	    try{
	    	List<ZmlLoanContractDocumentEntity> zmlLoanContractDocumentEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("zmlLoanContractDocumentList", zmlLoanContractDocumentEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/zml_loan/contract/zmlLoanContractDocumentList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(ZmlLoanContractEntity zmlLoanContract,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(ZmlLoanContractEntity.class, dataGrid);
    	//查询条件组装器
    	HqlGenerateUtil.installHql(cq, zmlLoanContract);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<ZmlLoanContractEntity> list=this.zmlLoanContractService.getListByCriteriaQuery(cq, false);
    	List<ZmlLoanContractPage> pageList=new ArrayList<ZmlLoanContractPage>();
        if(list!=null&&list.size()>0){
        	for(ZmlLoanContractEntity entity:list){
        		try{
        		ZmlLoanContractPage page=new ZmlLoanContractPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            	    Object id0 = entity.getId();
				    String hql0 = "from ZmlLoanContractDocumentEntity where 1 = 1 AND cONTRACT_ID = ? ";
        	        List<ZmlLoanContractDocumentEntity> zmlLoanContractDocumentEntityList = systemService.findHql(hql0,id0);
            		page.setZmlLoanContractDocumentList(zmlLoanContractDocumentEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"借款合同");
        map.put(NormalExcelConstants.CLASS,ZmlLoanContractPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("借款合同列表", "导出人:Jeecg",
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
				List<ZmlLoanContractPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), ZmlLoanContractPage.class, params);
				ZmlLoanContractEntity entity1=null;
				for (ZmlLoanContractPage page : list) {
					entity1=new ZmlLoanContractEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            zmlLoanContractService.addMain(entity1, page.getZmlLoanContractDocumentList());
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
		map.put(NormalExcelConstants.FILE_NAME,"借款合同");
		map.put(NormalExcelConstants.CLASS,ZmlLoanContractPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("借款合同列表", "导出人:"+ ResourceUtil.getSessionUserName().getRealName(),
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
		req.setAttribute("controller_name", "zmlLoanContractController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ZmlLoanContractEntity> list() {
		List<ZmlLoanContractEntity> listZmlLoanContracts=zmlLoanContractService.getList(ZmlLoanContractEntity.class);
		return listZmlLoanContracts;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlLoanContractEntity task = zmlLoanContractService.get(ZmlLoanContractEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlLoanContractPage zmlLoanContractPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanContractPage>> failures = validator.validate(zmlLoanContractPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<ZmlLoanContractDocumentEntity> zmlLoanContractDocumentList =  zmlLoanContractPage.getZmlLoanContractDocumentList();
		
		ZmlLoanContractEntity zmlLoanContract = new ZmlLoanContractEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(zmlLoanContract,zmlLoanContractPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		zmlLoanContractService.addMain(zmlLoanContract, zmlLoanContractDocumentList);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlLoanContractPage.getId();
		URI uri = uriBuilder.path("/rest/zmlLoanContractController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlLoanContractPage zmlLoanContractPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanContractPage>> failures = validator.validate(zmlLoanContractPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<ZmlLoanContractDocumentEntity> zmlLoanContractDocumentList =  zmlLoanContractPage.getZmlLoanContractDocumentList();
		
		ZmlLoanContractEntity zmlLoanContract = new ZmlLoanContractEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(zmlLoanContract,zmlLoanContractPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		zmlLoanContractService.updateMain(zmlLoanContract, zmlLoanContractDocumentList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		ZmlLoanContractEntity zmlLoanContract = zmlLoanContractService.get(ZmlLoanContractEntity.class, id);
		zmlLoanContractService.delMain(zmlLoanContract);
	}
}
