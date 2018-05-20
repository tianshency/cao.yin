package com.loan.controller;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
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
import com.zml.base.loan.entity.ZmlLoanContractEntity;
import com.zml.base.loan.entity.ZmlLoanRepayRecordEntity;
import com.zml.base.loan.entity.ZmlLoanRiskCreditEntity;
import com.zml.base.loan.entity.ZmlLoanWfTaskEntity;
import com.zml.enums.YesOrNo;
import com.zml.enums.loan.ApplySts;
import com.zml.enums.loan.ApprovalFlag;
import com.zml.enums.loan.LoanTaskSts;
import com.zml.enums.loan.LoanTaskType;
import com.zml.enums.loan.LoanWFNode;
import com.zml.loan_service.ZmlLoanApplicationServiceI;
import com.zml.loan_service.ZmlLoanApproveServiceI;
import com.zml.loan_service.ZmlLoanContractServiceI;
import com.zml.loan_service.ZmlLoanRepayRecordServiceI;
import com.zml.loan_service.ZmlLoanRiskCreditServiceI;
import com.zml.loan_service.ZmlLoanWfTaskServiceI;

/**   
 * @Title: Controller  
 * @Description: 贷款工作流任务
 * @author onlineGenerator
 * @date 2017-03-20 08:55:52
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zmlLoanWfTaskController")
public class ZmlLoanWfTaskController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlLoanWfTaskController.class);

	@Autowired
	private ZmlLoanWfTaskServiceI zmlLoanWfTaskService;
	
	@Autowired
	private ZmlLoanApproveServiceI zmlLoanApproveService;
	
	@Autowired
	private ZmlLoanRiskCreditServiceI zmlLoanRiskCreditService;
	
	@Autowired
	private ZmlLoanApplicationServiceI zmlLoanApplicationService;
	
	@Autowired
	private ZmlLoanContractServiceI zmlLoanContractService;
	
	@Autowired
	private ZmlLoanRepayRecordServiceI zmlLoanRepayRecordService;
	
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 代办任务列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml_loan/wf_task/zmlLoanWfTaskList");
	}
	/**
	 * 已办任务列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "overTaskList")
	public ModelAndView overTaskList(HttpServletRequest request) {
		return new ModelAndView("com/zml_loan/wf_task/overTask");
	}
	/**
	 * easyui AJAX请求数据
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(ZmlLoanWfTaskEntity zmlLoanWfTask, String flag, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		if(LoanTaskSts.DONE.getStatusValue().equals(flag)){
			zmlLoanWfTask.setStatus(LoanTaskSts.DONE.getStatusValue());
		}else{
			zmlLoanWfTask.setStatus(LoanTaskSts.PROCESSING.getStatusValue());
		}
		CriteriaQuery cq = new CriteriaQuery(ZmlLoanWfTaskEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, zmlLoanWfTask, request.getParameterMap());
		try{
		//自定义追加查询条件
		String query_approvalDate_begin = request.getParameter("approvalDate_begin");
		String query_approvalDate_end = request.getParameter("approvalDate_end");
		if(StringUtil.isNotEmpty(query_approvalDate_begin)){
			cq.ge("approvalDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_approvalDate_begin));
		}
		if(StringUtil.isNotEmpty(query_approvalDate_end)){
			cq.le("approvalDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_approvalDate_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlLoanWfTaskService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除贷款工作流任务
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlLoanWfTaskEntity zmlLoanWfTask, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zmlLoanWfTask = systemService.getEntity(ZmlLoanWfTaskEntity.class, zmlLoanWfTask.getId());
		message = "贷款工作流任务删除成功";
		try{
			zmlLoanWfTaskService.delete(zmlLoanWfTask);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "贷款工作流任务删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除贷款工作流任务
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "贷款工作流任务删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlLoanWfTaskEntity zmlLoanWfTask = systemService.getEntity(ZmlLoanWfTaskEntity.class, 
				id
				);
				zmlLoanWfTaskService.delete(zmlLoanWfTask);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "贷款工作流任务删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加贷款工作流任务
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlLoanWfTaskEntity zmlLoanWfTask, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "贷款工作流任务添加成功";
		try{
			zmlLoanWfTaskService.save(zmlLoanWfTask);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "贷款工作流任务添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新贷款工作流任务
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlLoanWfTaskEntity zmlLoanWfTask, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "贷款工作流任务更新成功";
		ZmlLoanWfTaskEntity t = zmlLoanWfTaskService.get(ZmlLoanWfTaskEntity.class, zmlLoanWfTask.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zmlLoanWfTask, t);
			zmlLoanWfTaskService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "贷款工作流任务更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 跳转 业务审批页面
	 * @return
	 */
	@RequestMapping(params = "goBizApprove")
	public ModelAndView goBizApprove(ZmlLoanApplicationEntity zmlLoanApplication, String bizId, String wfId, String bizType, HttpServletRequest req) {
		TSUser user = ResourceUtil.getSessionUserName();
		ModelAndView mv =  new ModelAndView();
		if(bizId != null && !"".equals(bizId)){
		}else{
			/*if (StringUtil.isNotEmpty(zmlLoanApplication.getId())) {
				zmlLoanApplication = zmlLoanApplicationService.getEntity(ZmlLoanApplicationEntity.class, zmlLoanApplication.getId());
				req.setAttribute("zmlLoanApplicationPage", zmlLoanApplication);
			}*/
			mv.setViewName("com/error");
			return mv;
		}
		ZmlLoanWfTaskEntity zmlLoanWfTask = zmlLoanWfTaskService.getEntity(ZmlLoanWfTaskEntity.class, wfId);
		zmlLoanWfTask.setStartTime(new Date());
		zmlLoanWfTask.setUpdateDate(new Date());
		zmlLoanWfTask.setApprovalUserId(user.getId());
		zmlLoanWfTask.setStatus(LoanTaskSts.PROCESSING.getStatusValue());
		try {
			zmlLoanWfTaskService.saveOrUpdate(zmlLoanWfTask);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(LoanTaskType.LOAN_APPLY.getStatusValue().equals(bizType)){
			zmlLoanApplication = zmlLoanApplicationService.getEntity(ZmlLoanApplicationEntity.class, bizId);
			req.setAttribute("zmlLoanApplicationPage", zmlLoanApplication);
			mv.setViewName("com/zml_loan/application/applyApprove");
		}else if(LoanTaskType.PAY.getStatusValue().equals(bizType)){
			ZmlLoanContractEntity zmlLoanContract = zmlLoanContractService.getEntity(ZmlLoanContractEntity.class, bizId);
			//1、查询申请信息，判断
			if(zmlLoanContract.getPaymentType() != null && !"".equals(zmlLoanContract.getPaymentType())){
				switch(zmlLoanContract.getPaymentType()) {
				//2、收款方式为银行卡 判断收款人姓名跟 申请人实际姓名是否一致，不一致给出提示，前端必须打电话确认
				case "1":
					zmlLoanApplication = zmlLoanApplicationService.getEntity(ZmlLoanApplicationEntity.class, zmlLoanContract.getApplId());
					String proposer = zmlLoanApplication.getName() == null ? "" : zmlLoanApplication.getName();
					String payee = zmlLoanContract.getUserName() == null ? "" : zmlLoanContract.getUserName();
					if("".equals(proposer)){
						mv.addObject("failMsg","申请人姓名不存在，请确认！");	
//						mv.setViewName("redirect:zmlLoanWfTaskController.do?list");
						//mv.setViewName("com/zml_loan/wf_task/zmlLoanWfTaskList");
						//return mv;
					} else if ("".equals(payee)){
						mv.addObject("failMsg","收款人姓名不存在，请确认！");
//						mv.setViewName("redirect:zmlLoanWfTaskController.do?list");
						//mv.setViewName("com/zml_loan/wf_task/zmlLoanWfTaskList");
						//return mv;
					} else {
						if(!proposer.equals(payee)){
							mv.addObject("failMsg","收款人姓名与申请人实际姓名不一致，请确认！");
//							mv.setViewName("redirect:zmlLoanWfTaskController.do?list");
							//mv.setViewName("com/zml_loan/wf_task/zmlLoanWfTaskList");
							//return mv;
						}
					}
					break;
				//3、收款方式为 微信时， 用户的收款微信账号 是否与填写微信 一致
				case "2":
					
					break;
				//4、收款方式为 支付宝时，实名认证方式必须是支付宝 认证的
				case "3":
					
					break;
				default:
					mv.addObject("failMsg","数据库数据异常，请确认！");
					break;
				}
				req.setAttribute("zmlLoanContractPage", zmlLoanContract);
				mv.setViewName("com/zml_loan/contract/approveContract");
			}
		}else if(LoanTaskType.REPAY.getStatusValue().equals(bizType)){
			ZmlLoanRepayRecordEntity record = zmlLoanRepayRecordService.getEntity(ZmlLoanRepayRecordEntity.class, bizId);
			req.setAttribute("zmlLoanRepayRecordPage", record);
			mv.setViewName("com/zml_loan/repay_record/approveRepay");
		}else if(LoanTaskType.COLLECTION_ONE.getStatusValue().equals(bizType)){
			
		}else if(LoanTaskType.COLLECTION_TWO.getStatusValue().equals(bizType)){
			
		}
		mv.addObject("wfId", wfId);
		return mv;
	}
	
	/**
	 * 贷款申请环节 审批
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doApprove")
	@ResponseBody
	public AjaxJson doApprove(ZmlLoanWfTaskEntity zmlLoanWfTask, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "审批成功！";
		try {
			zmlLoanWfTaskService.doApplyApprove(zmlLoanWfTask);
			systemService.addLog(message, Globals.Log_Type_Loan_Approve, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "审批异常！";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 最终审批合同
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doApproveContract")
	@ResponseBody
	public AjaxJson doApproveContract(ZmlLoanWfTaskEntity zmlLoanWfTask,String contractId, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "审批成功";
		ZmlLoanWfTaskEntity t = zmlLoanWfTaskService.get(ZmlLoanWfTaskEntity.class, zmlLoanWfTask.getId());
		try {
			TSUser user = ResourceUtil.getSessionUserName();
			zmlLoanWfTask.setApprovalUserId(user.getId());
			zmlLoanWfTask.setUpdateDate(new Date());
			zmlLoanWfTask.setEndTime(new Date());
			MyBeanUtils.copyBeanNotNull2Bean(zmlLoanWfTask, t);
			zmlLoanWfTaskService.saveOrUpdate(t);
			if(ApprovalFlag.APPROVE_PAST.getStatusValue().equals(zmlLoanWfTask.getApprovalFlag())){
				zmlLoanWfTask.setStatus(LoanTaskSts.DONE.getStatusValue());
				//修改合同
				ZmlLoanContractEntity contract = zmlLoanContractService.getEntity(ZmlLoanContractEntity.class, t.getBizId());
				contract.setContractAmt(t.getApprovalAmt());
				contract.setContractBalance(t.getApprovalAmt());
				contract.setApprovalUserId(user.getId());
				contract.setApprovalDate(new Date());
				contract.setApprovalFlag(t.getApprovalFlag());
				contract.setApprovalOpinion(t.getApprovalOpinion());
				contract.setFee(t.getApprovalFee());
				//生成还款计划
			    
			}else{
				//修改申请表状态为 拒绝
				ZmlLoanApplicationEntity apply = zmlLoanApplicationService.getEntity(ZmlLoanApplicationEntity.class, t.getBizId());
				apply.setApplySts(ApplySts.APPROVE_REJECT.getStatusValue());
				apply.setUpdateDate(new Date());
				zmlLoanApplicationService.saveOrUpdate(apply);
			}
			
			systemService.addLog(message, Globals.Log_Type_Loan_Approve, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "贷款工作流任务审批失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 还款 审批
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doApproveRepay")
	@ResponseBody
	public AjaxJson doApproveRepay(ZmlLoanWfTaskEntity zmlLoanWfTask, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "审批成功！";
		try {
			zmlLoanWfTaskService.doApproveRepay(zmlLoanWfTask);
			systemService.addLog(message, Globals.Log_Type_Loan_Approve, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "审批异常！";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 贷款工作流任务新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlLoanWfTaskEntity zmlLoanWfTask, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanWfTask.getId())) {
			zmlLoanWfTask = zmlLoanWfTaskService.getEntity(ZmlLoanWfTaskEntity.class, zmlLoanWfTask.getId());
			req.setAttribute("zmlLoanWfTaskPage", zmlLoanWfTask);
		}
		return new ModelAndView("com/zml_loan/wf_task/zmlLoanWfTask-add");
	}
	/**
	 * 贷款工作流任务编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlLoanWfTaskEntity zmlLoanWfTask, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanWfTask.getId())) {
			zmlLoanWfTask = zmlLoanWfTaskService.getEntity(ZmlLoanWfTaskEntity.class, zmlLoanWfTask.getId());
			req.setAttribute("zmlLoanWfTaskPage", zmlLoanWfTask);
		}
		return new ModelAndView("com/zml_loan/wf_task/zmlLoanWfTask-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","zmlLoanWfTaskController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ZmlLoanWfTaskEntity zmlLoanWfTask,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ZmlLoanWfTaskEntity.class, dataGrid);
		HqlGenerateUtil.installHql(cq, zmlLoanWfTask, request.getParameterMap());
		List<ZmlLoanWfTaskEntity> zmlLoanWfTasks = this.zmlLoanWfTaskService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"贷款工作流任务");
		modelMap.put(NormalExcelConstants.CLASS,ZmlLoanWfTaskEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("贷款工作流任务列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,zmlLoanWfTasks);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ZmlLoanWfTaskEntity zmlLoanWfTask,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"贷款工作流任务");
    	modelMap.put(NormalExcelConstants.CLASS,ZmlLoanWfTaskEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("贷款工作流任务列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<ZmlLoanWfTaskEntity> listZmlLoanWfTaskEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ZmlLoanWfTaskEntity.class,params);
				for (ZmlLoanWfTaskEntity zmlLoanWfTask : listZmlLoanWfTaskEntitys) {
					zmlLoanWfTaskService.save(zmlLoanWfTask);
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
	public List<ZmlLoanWfTaskEntity> list() {
		List<ZmlLoanWfTaskEntity> listZmlLoanWfTasks=zmlLoanWfTaskService.getList(ZmlLoanWfTaskEntity.class);
		return listZmlLoanWfTasks;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlLoanWfTaskEntity task = zmlLoanWfTaskService.get(ZmlLoanWfTaskEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlLoanWfTaskEntity zmlLoanWfTask, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanWfTaskEntity>> failures = validator.validate(zmlLoanWfTask);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlLoanWfTaskService.save(zmlLoanWfTask);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlLoanWfTask.getId();
		URI uri = uriBuilder.path("/rest/zmlLoanWfTaskController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlLoanWfTaskEntity zmlLoanWfTask) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanWfTaskEntity>> failures = validator.validate(zmlLoanWfTask);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlLoanWfTaskService.saveOrUpdate(zmlLoanWfTask);
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
		zmlLoanWfTaskService.deleteEntityById(ZmlLoanWfTaskEntity.class, id);
	}
}
