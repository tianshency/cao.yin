package com.zml.loan.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import com.jce.framework.core.common.exception.BusinessException;
import com.jce.framework.core.common.hibernate.qbc.CriteriaQuery;
import com.jce.framework.core.common.model.json.AjaxJson;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.constant.Globals;
import com.jce.framework.core.extend.hqlsearch.HqlGenerateUtil;
import com.jce.framework.core.util.DateUtils;
import com.jce.framework.core.util.FileUtils;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.tag.easyui.TagUtil;
import com.jce.framework.web.system.service.SystemService;
import com.zml.app.controller.BaseController;
import com.zml.base.entity.ZmlUserEntity;
import com.zml.base.loan.entity.ZmlLoanApplicationEntity;
import com.zml.base.loan.entity.ZmlLoanApplyDocumentEntity;
import com.zml.base.loan.entity.ZmlLoanApproveEntity;
import com.zml.base.loan.entity.ZmlLoanProductEntity;
import com.zml.base.loan.entity.ZmlLoanRepayPlanDetailEntity;
import com.zml.base.loan.entity.ZmlLoanUserContactsEntity;
import com.zml.base.loan.entity.ZmlLoanWfTaskEntity;
import com.zml.common.Constant;
import com.zml.common.LoanConstant;
import com.zml.common.ReMsg;
import com.zml.enums.FileType;
import com.zml.enums.YesOrNo;
import com.zml.enums.loan.ApplySts;
import com.zml.enums.loan.ApprovalFlag;
import com.zml.enums.loan.LoanDocumentDirName;
import com.zml.enums.loan.LoanNoPrefix;
import com.zml.enums.loan.LoanTaskSts;
import com.zml.enums.loan.LoanTaskType;
import com.zml.enums.loan.LoanType;
import com.zml.enums.loan.LoanWFNode;
import com.zml.enums.loan.RepayPlanDetailStatus;
import com.zml.loan_service.ZmlLoanApplicationServiceI;
import com.zml.loan_service.ZmlLoanApplyDocumentServiceI;
import com.zml.loan_service.ZmlLoanApproveServiceI;
import com.zml.loan_service.ZmlLoanContractServiceI;
import com.zml.loan_service.ZmlLoanProductServiceI;
import com.zml.loan_service.ZmlLoanUserContactsServiceI;
import com.zml.loan_service.ZmlLoanWfTaskServiceI;
import com.zml.loan_tools.common.model.InterestCalCulateForm;
import com.zml.loan_tools.common.model.InterestVO;
import com.zml.loan_tools.common.utils.CalcUtil;
import com.zml.util.DateUtil;
import com.zml.util.GenerateNo;

//借款申请 控制类
@Controller
@RequestMapping("/loanApplicationController")
public class LoanApplicationController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LoanApplicationController.class);

	@Autowired
	private ZmlLoanApplicationServiceI zmlLoanApplicationService;
	
	@Autowired
	private ZmlLoanContractServiceI zmlLoanContractService;
	
	@Autowired
	private ZmlLoanApplyDocumentServiceI zmlLoanApplyDocumentService;
	
	@Autowired
	private ZmlLoanUserContactsServiceI zmlLoanUserContactsService;
	
	@Autowired
	private ZmlLoanWfTaskServiceI zmlLoanWfTaskService;
	
	@Autowired
	private ZmlLoanApproveServiceI zmlLoanApproveService;
	
	@Autowired
	private ZmlLoanProductServiceI zmlLoanProductService;
	
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	//借款申请新增页面跳转
	@RequestMapping("/toApplyLoan")
	public ModelAndView toApplyLoan(ZmlLoanApplicationEntity zmlLoanApplication,HttpServletRequest req, HttpServletResponse response) {
		ZmlUserEntity user = getUserInfo(req, response);
		ModelAndView mv = new ModelAndView();
		//查询用户是否有申请中的贷款
		String sts = null ;//"'" + ApplySts.NO_COMMIT.getStatusValue() +"','"+ApplySts.APPROVE_ING.getStatusValue()+"'";
		List<Map<String, Object>> list = zmlLoanApplicationService.findLoanApplicationByUserIdAndSts(user.getId(), sts);
		if(list != null && list.size() > 0){
			Map<String, Object> map = list.get(0);
			String applyId = (String)map.get("id");
			zmlLoanApplication = zmlLoanApplicationService.getEntity(ZmlLoanApplicationEntity.class, applyId);
			
			//未提交、已提交 允许修改数据
			if(ApplySts.NO_COMMIT.getStatusValue().equals(map.get("applySts"))
					//||ApplySts.COMMIT.getStatusValue().equals(map.get("applySts"))
					){
				/*//查询联系人
				List<ZmlLoanUserContactsEntity> listContacts = zmlLoanUserContactsService.findContactsByApplyId(applyId);*/
				//mv.addObject("listContacts", listContacts);
				//查询文档
				//List<ZmlLoanApplyDocumentEntity> listDoc = zmlLoanApplyDocumentService.findDocByApplyId(applyId);
				//mv.addObject("listDoc", listDoc);
				mv.setViewName("loan/application/loanApply");
			//审批中
			}else if(ApplySts.APPROVE_ING.getStatusValue().equals(map.get("applySts"))){
				mv.setViewName("loan/application/loanApproing");
			}
			//审批通过
			else if(ApplySts.APPROVE_PAST.getStatusValue().equals(map.get("applySts"))){
				//查询是否签订合同
				Map<String, Object> contract = zmlLoanContractService.findContractByApplyId(applyId);
				if(contract == null){
					//跳转到签订合同页面
					mv.setViewName("loan/application/approvePass");
				}else{
					//查询是否放款
					Date payDay = (Date) contract.get("payDay");
					if(payDay == null){
						//跳转到等待放款提示页面
						mv.setViewName("loan/application/waitingloan");
					}else{
						//跳转到最近对应合同详情页面
						mv.addObject("contract", contract);
						mv.setViewName("loan/application/myLoanList");
					}
				}
				
				//mv.setViewName("loan/application/");
			}
			//审批拒绝
			else if(ApplySts.APPROVE_REJECT.getStatusValue().equals(map.get("applySts"))){
				mv.setViewName("loan/application/");
			}
			else{
				zmlLoanApplication = new ZmlLoanApplicationEntity();
				zmlLoanApplication.setSubject(user.getUserName() + "-" + LoanType.getLoanTypeDesc("1"));
				zmlLoanApplication.setCreateDate(new Date());
				zmlLoanApplication.setUserId(user.getId());
				String applyNo = LoanNoPrefix.APPLY_NO_PREF.getStatusValue()+ DateUtil.getNumberDateTime() + GenerateNo.getRandomNum(Constant.BIZ_NO_LEN);
				zmlLoanApplication.setApplyNo(applyNo);
				zmlLoanApplication.setApplySts(ApplySts.NO_COMMIT.getStatusValue());
				
				try {
					zmlLoanApplicationService.save(zmlLoanApplication);
				} catch (Exception e) {
					e.printStackTrace();
				}
				mv.setViewName("loan/application/loanApply");
			}
			mv.addObject("loanApplication", zmlLoanApplication);
			
		}else{
			zmlLoanApplication = new ZmlLoanApplicationEntity();
			zmlLoanApplication.setSubject(user.getUserName() + "-" + LoanType.getLoanTypeDesc("1"));
			zmlLoanApplication.setCreateDate(new Date());
			zmlLoanApplication.setUserId(user.getId());
			String applyNo = LoanNoPrefix.APPLY_NO_PREF.getStatusValue()+ DateUtil.getNumberDateTime() + GenerateNo.getRandomNum(Constant.BIZ_NO_LEN);
			zmlLoanApplication.setApplyNo(applyNo);
			zmlLoanApplication.setApplySts(ApplySts.NO_COMMIT.getStatusValue());
			
			try {
				zmlLoanApplicationService.save(zmlLoanApplication);
			} catch (Exception e) {
				e.printStackTrace();
			}
			mv.setViewName("loan/application/loanApply");
			mv.addObject("loanApplication", zmlLoanApplication);
		}
		//查询产品
		Map<String, String> param = new HashMap();
		param.put("status", "Y");
		List<Map<String, Object>> proList = zmlLoanProductService.findProduct(param);
		mv.addObject("proList", proList);
		String token = GenerateNo.uuid();
		setToken(req, token);
		mv.addObject("token", token);
		return mv;
	}
	
	/**
	 * 查询最近一笔贷款
	 * @return
	 */
	@RequestMapping("/findLoanApplicationDataBylast")
	@ResponseBody
	public AjaxJson findLoanApplicationDataBylast(HttpServletRequest request, HttpServletResponse response) {
		String userId = getUserId(request, response);
		//查询用户是否有申请中的贷款
		String sts = null ;//"'" + ApplySts.NO_COMMIT.getStatusValue() +"','"+ApplySts.APPROVE_ING.getStatusValue()+"'";
		List<Map<String, Object>> list = zmlLoanApplicationService.findLoanApplicationByUserIdAndSts(userId, sts);
		AjaxJson j = new AjaxJson();
		if(list!=null && list.size()>0){
			j.setAttributes(list.get(0));
			Map<String, Object> map = list.get(0);
			String applyId = (String)map.get("id");
			ZmlLoanApplicationEntity zmlLoanApplication = zmlLoanApplicationService.getEntity(ZmlLoanApplicationEntity.class, applyId);
			j.setObj(zmlLoanApplication);
		}
		j.setSuccess(true);
		return j;
	}
	
	/**
	 * 借款申请列表 页面跳转
	 * @return
	 */
	@RequestMapping("/goList")
	public ModelAndView goList(HttpServletRequest request) {
		return new ModelAndView("/loan/application/myLoanList");
	}

	/**
	 * 查询本人申请列表，允许带参数
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping("/findLoanApplicationData")
	@ResponseBody
	public void findLoanApplicationData(ZmlLoanApplicationEntity zmlLoanApplication,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//用户不存在时直接返回
		String userId = getUserId(request, response);
		if(userId == null || "".equals(userId)){
			return ;
		}
		CriteriaQuery cq = new CriteriaQuery(ZmlLoanApplicationEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, zmlLoanApplication, request.getParameterMap());
		try{
			//自定义追加查询条件
			String query_amount_begin = request.getParameter("amount_begin");
			String query_amount_end = request.getParameter("amount_end");
			if(StringUtil.isNotEmpty(query_amount_begin)){
				cq.ge("amount", Integer.parseInt(query_amount_begin));
			}
			if(StringUtil.isNotEmpty(query_amount_end)){
				cq.le("amount", Integer.parseInt(query_amount_end));
			}
			cq.le("userId", userId);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlLoanApplicationService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除借款申请 修改状态 为删除
	 * 
	 * @return
	 */
	@RequestMapping("/doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlLoanApplicationEntity zmlLoanApplication, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zmlLoanApplication = systemService.getEntity(ZmlLoanApplicationEntity.class, zmlLoanApplication.getId());
		message = "借款申请删除成功";
		try{
			zmlLoanApplication.setApplySts("-1");
			//zmlLoanApplicationService.delete(zmlLoanApplication);
			zmlLoanApplicationService.saveOrUpdate(zmlLoanApplication);
			systemService.addLog("", "", message, Globals.APP_Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "借款申请删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 *借款申请 提交
	 * @param imgIds 所有图片的ID
	 * @return
	 */
	@RequestMapping("/doAdd")
	@ResponseBody
	public AjaxJson commitApply(ZmlLoanApplicationEntity zmlLoanApplication, String token, HttpServletRequest request, HttpServletResponse response) {
		String message = null;
		AjaxJson j = new AjaxJson();
		//检查token 是否有效
		if(!checkToken(request, token)){
			j.setSuccess(false);
			j.setMsg("无效的提交！");
			return j;
		}
		if(zmlLoanApplication.getProductId() == null || "".equals(zmlLoanApplication.getProductId())){
			j.setSuccess(false);
			j.setMsg("请选择贷款期限！");
			return j;
		}
		message = "申请提交成功!";
		try{
			
			ZmlLoanApplicationEntity t = zmlLoanApplicationService.get(ZmlLoanApplicationEntity.class, zmlLoanApplication.getId());
			t.setUpdateDate(new Date());
			//查询产品信息
			ZmlLoanProductEntity product = zmlLoanProductService.getEntity(ZmlLoanProductEntity.class, zmlLoanApplication.getProductId());
			zmlLoanApplication.setApplySts(ApplySts.APPROVE_ING.getStatusValue());
			zmlLoanApplication.setInterestRate(product.getInterestRate());
			zmlLoanApplication.setPenaltyRate(product.getPenaltyRate());
			zmlLoanApplication.setPeriods("" +product.getPeriods());
			zmlLoanApplication.setPeriodsUnit(product.getPeriodsUnit());
			MyBeanUtils.copyBeanNotNull2Bean(zmlLoanApplication, t);
			zmlLoanApplicationService.commitApply(t, "");
			systemService.addLog("", "", message, Globals.APP_Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "申请提交失败!";
			throw new BusinessException(e.getMessage());
		}
		//移除token
		removeToken(request, token);
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新借款申请
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlLoanApplicationEntity zmlLoanApplication, HttpServletRequest request, String token) {
		String message = null;
		AjaxJson j = new AjaxJson();
		//检查token 是否有效
		if(!checkToken(request, token)){
			j.setSuccess(false);
			j.setMsg("无效的提交！");
			return j;
		}
		message = "更新成功";
		ZmlLoanApplicationEntity t = zmlLoanApplicationService.get(ZmlLoanApplicationEntity.class, zmlLoanApplication.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zmlLoanApplication, t);
			t.setUpdateDate(new Date());
			zmlLoanApplicationService.saveOrUpdate(t);
			systemService.addLog("", "", message, Globals.APP_Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "更新失败";
			throw new BusinessException(e.getMessage());
		}
		//移除token
		setToken(request, token);
		
		j.setMsg(message);
		return j;
	}

	/**
	 * 借款申请编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlLoanApplicationEntity zmlLoanApplication, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanApplication.getId())) {
			zmlLoanApplication = zmlLoanApplicationService.getEntity(ZmlLoanApplicationEntity.class, zmlLoanApplication.getId());
			req.setAttribute("zmlLoanApplicationPage", zmlLoanApplication);
		}
		return new ModelAndView("com/zml_loan/application/zmlLoanApplication-update");
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ZmlLoanApplicationEntity> list() {
		List<ZmlLoanApplicationEntity> listZmlLoanApplications=zmlLoanApplicationService.getList(ZmlLoanApplicationEntity.class);
		return listZmlLoanApplications;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlLoanApplicationEntity task = zmlLoanApplicationService.get(ZmlLoanApplicationEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlLoanApplicationEntity zmlLoanApplication, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanApplicationEntity>> failures = validator.validate(zmlLoanApplication);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlLoanApplicationService.save(zmlLoanApplication);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlLoanApplication.getId();
		URI uri = uriBuilder.path("/rest/zmlLoanApplicationController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlLoanApplicationEntity zmlLoanApplication) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanApplicationEntity>> failures = validator.validate(zmlLoanApplication);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlLoanApplicationService.saveOrUpdate(zmlLoanApplication);
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
		zmlLoanApplicationService.deleteEntityById(ZmlLoanApplicationEntity.class, id);
	}
	/**
	 * 添加借款用户联系人
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAddContacts")
	@ResponseBody
	public AjaxJson doAddContacts(ZmlLoanUserContactsEntity zmlLoanUserContacts, HttpServletRequest request,HttpServletResponse response) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "添加成功";
		try{
			//用户不存在时直接返回
			String userId = getUserId(request, response);
			if(userId == null || "".equals(userId)){
				j.setSuccess(false);
				j.setMsg("添加失败,无法获取用户信息，请重新进入！");
				return j;
			}
			zmlLoanUserContactsService.save(zmlLoanUserContacts);
			systemService.addLog("", "", message, Globals.APP_Log_Type_INSERT, Globals.Log_Leavel_INFO);
			Map map = new HashMap();
			map.put("contacts", zmlLoanUserContacts);
			j.setAttributes(map);
		}catch(Exception e){
			e.printStackTrace();
			message = "添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新借款用户联系人
	 * @return
	 */
	@RequestMapping(params = "doUpdateContacts")
	@ResponseBody
	public AjaxJson doUpdateContacts(ZmlLoanUserContactsEntity zmlLoanUserContacts, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "更新成功";
		ZmlLoanUserContactsEntity t = zmlLoanUserContactsService.get(ZmlLoanUserContactsEntity.class, zmlLoanUserContacts.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zmlLoanUserContacts, t);
			zmlLoanUserContactsService.saveOrUpdate(t);
			systemService.addLog("", "", message, Globals.APP_Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			Map map = new HashMap();
			map.put("contacts", zmlLoanUserContacts);
			j.setAttributes(map);
		} catch (Exception e) {
			e.printStackTrace();
			message = "更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 删除借款用户联系人
	 * 
	 * @return
	 */
	@RequestMapping("/doDelContacts")
	@ResponseBody
	public AjaxJson doDelContacts(ZmlLoanUserContactsEntity zmlLoanUserContacts, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zmlLoanUserContacts = systemService.getEntity(ZmlLoanUserContactsEntity.class, zmlLoanUserContacts.getId());
		message = "删除成功！";
		try{
			zmlLoanUserContactsService.delete(zmlLoanUserContacts);
			systemService.addLog("", "", message, Globals.APP_Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "删除失败！";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 查询接口联系人
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping("/findContacts")
	@ResponseBody
	public ReMsg findContacts(String applId,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//用户不存在时直接返回
		ReMsg reMsg = null;
		String userId = getUserId(request, response);
		if(userId == null || "".equals(userId)){
			reMsg = new ReMsg("数据不能为空！", false);
			return reMsg;
		}
		ZmlLoanUserContactsEntity zmlLoanUserContacts = new ZmlLoanUserContactsEntity();
		CriteriaQuery cq = new CriteriaQuery(ZmlLoanUserContactsEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, zmlLoanUserContacts, request.getParameterMap());
		try{
			//自定义追加查询条件
			//默认查询本人的所有联系人
			cq.le("userId", userId);
			//如果传入申请ID 查询本人 申请贷款对应的联系人
			if(applId != null && !"".equals(applId)){
				cq.le("applId", applId);
			}
		}catch (Exception e) {
			e.printStackTrace();
			//throw new BusinessException(e.getMessage());
			reMsg = new ReMsg("数据不能为空！", false);
			return reMsg;
		}
		cq.add();
		this.zmlLoanUserContactsService.getDataGridReturn(cq, true);
		reMsg = new ReMsg("", true);
		reMsg.add("data", cq.getDataGrid().getResults());
		//TagUtil.datagrid(response, dataGrid);
		return reMsg;
	}
	
	/**
	 * 查询申请对应的文档
	 * @param request
	 * @param response
	 */
	@RequestMapping("/findApplyDoc")
	@ResponseBody
	public ReMsg findApplyDoc(String applyId,HttpServletRequest request, HttpServletResponse response) {
		//用户不存在时直接返回
		ReMsg reMsg = null;
		//String userId = getUserId(request, response);
		if(applyId == null || "".equals(applyId)){
			reMsg = new ReMsg("数据不能为空！", false);
			return reMsg;
		}
		try{
			//查询文档
			List<ZmlLoanApplyDocumentEntity> listDoc = zmlLoanApplyDocumentService.findDocByApplyId(applyId);
			reMsg = new ReMsg("", true);
			reMsg.add("data", listDoc);
		}catch (Exception e) {
			e.printStackTrace();
			//throw new BusinessException(e.getMessage());
			reMsg = new ReMsg("数据不能为空！", false);
			return reMsg;
		}
		return reMsg;
	}
	
	//贷款试算器
	/*
	 * loanAmount 金额 *
	 * productId 产品ID *
	 * startDate 开始时间不传 默认当天
	 * startDate
	 * */
	@RequestMapping("/loanCalc")
	@ResponseBody
	public ReMsg loanCalc(BigDecimal loanAmount, String productId, String startDate,// Integer term, String applyTermUnit, String repayment, 
			HttpServletRequest request) {
		ReMsg msg = null;
		try{
			if(loanAmount == null || productId == null ||
				"".equals(productId)){
				msg = new ReMsg("数据不能为空！", false);
				return msg;
			}
			ZmlLoanProductEntity product = zmlLoanProductService.getEntity(ZmlLoanProductEntity.class, productId);
			//生成还款计划
			InterestCalCulateForm interestForm  = new InterestCalCulateForm();
			interestForm.setLoanAmount(loanAmount); // 贷款金额
			interestForm.setApplyTerm(product.getPeriods()); // 期限
			interestForm.setApplyTermUnit(product.getPeriodsUnit()); // 期限单位
			interestForm.setRepaymentNumber(0); // 还款周期月数
			interestForm.setRepayment(product.getRepayment()); // // 还款方式
			interestForm.setRepaymentDate(LoanConstant.REPAY_DAY); // 还款日期
			Date loanStartDate = null;
			if(startDate == null || "".equals(startDate)){
				loanStartDate = new Date();
			}else{
				loanStartDate = DateUtil.strToDate(startDate, DateUtil.FORMAT_YYYY_MM_DD);
			}
			interestForm.setLoanStartDate(loanStartDate); // 贷款开始时间
			interestForm.setRate(product.getInterestRate());
			List<InterestVO> planList = CalcUtil.calcRepayPlan(interestForm);
			if(planList != null && planList.size() > 0){
				msg = new ReMsg("", true);
				msg.add("data", planList);
			}else{
				msg = new ReMsg("计算还款计划失败", false);
			}
		}catch(Exception e){
			e.printStackTrace();
			msg = new ReMsg("计算还款计划异常", false);
		}
		return msg;
	}
	
	
	/**
	 * 上传图片
	 * @param file
	 * docType:对应枚举 LoanApplyDocType
	 * @param docDir 对应枚举  LoanDocumentDirName
	 */
	@RequestMapping(value="/uploadFile",headers=("content-type=multipart/*"))
	@ResponseBody
	public ReMsg uploadFile(String applyId, String docType,Integer seqNo, HttpServletRequest request, HttpServletResponse response) {
		 ReMsg msg = null;
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	     //MultipartFile file = multipartRequest.getFiles("errPic");//获取文件集合  对应  jquery $("#imageFile").get(0).files
	     // 获得第1张图片（根据前台的name名称得到上传的文件）
	     MultipartFile file = multipartRequest.getFile("upload"); 
	     if (file != null && file.getSize()>0) {
	    	String userId = getUserId(multipartRequest, response);
	    	String rsPath = "";
			FileOutputStream out = null;
			String[] filename = file.getOriginalFilename().split("\\.");
			if (filename == null || filename.length == 0 || "".equals(filename[0])) {
				msg = new ReMsg("file is null", false);
				return msg;
			}
			String suffix = filename[filename.length - 1];
			//String path = BaseEntity.class.getResource("").getPath();
			//path = path.split("WEB-INF")[0];
			String path = Constant.IMG_FILE_PATH;
			String yyyyMM = DateUtils.formatDateToStr(DateUtils.FORMAT_YYYYMM, new Date());
			String tempPath = Constant.LOAN_IMG_FILE_PATH_ONE + LoanDocumentDirName.APPLY_DIR.getStatusValue() +File.separator + yyyyMM + File.separator + applyId + File.separator ;
			String tempFile = System.currentTimeMillis() + GenerateNo.getRandomNum(Constant.FILE_NAME_RAND_LEN);
			path += tempPath;
			//存大图
			File maxFile = new File(path + tempFile + "_max." + suffix);
			File dir = maxFile.getParentFile();
			if (dir != null && !dir.exists()) {
				dir.mkdirs();
			}
			try {
				maxFile.createNewFile();
				out = new FileOutputStream(maxFile);
				out.write(file.getBytes());
				out.flush();
				out.close();
				rsPath = tempPath + tempFile + "_min." + suffix;
				rsPath = rsPath.replace("\\", "/");
				String minFilePath = path + tempFile + "_min." + suffix;
				// 缩略图
				File minFile = new File(minFilePath);
				FileUtils.writeImgFile(file, minFile, Constant.MAX_IMG_FILE_WIDTH, Constant.MINI_IMG_FILE_HEIGHT);
			} catch (IOException e) {
				e.printStackTrace();
			}
			msg = new ReMsg("", true);
			try {
				ZmlLoanApplyDocumentEntity doc = new ZmlLoanApplyDocumentEntity();
		    	doc.setUserId(userId);
		    	doc.setCreateDate(new Date());
		    	doc.setApprovalFlag(ApprovalFlag.NO_APPROVE.getStatusValue());
		    	doc.setFileFlag(FileType.IMG.getStatusValue());
		    	if(seqNo == null) seqNo = 1;
		    	doc.setSeqNo(seqNo);
		    	doc.setDocType(docType);
		    	doc.setApplId(applyId);
		    	doc.setDetails(filename[0]);
				doc.setFilePath(rsPath);
				zmlLoanApplyDocumentService.save(doc);
				msg.add("imgPath", rsPath);
				msg.add("imgId", doc.getId());
			} catch (Exception e) {
				msg = new ReMsg(e.getMessage(), false);
				e.printStackTrace();
			}
		}else{
			msg = new ReMsg("file is null", false);
		}
	    return msg;
	}
	
	//跳转 签约文件
	@RequestMapping("/toSignContract")
	public ModelAndView toSignContract(String applyId,HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("loan/signContract/sign");
		mv.addObject("applyId",applyId);
		return mv;
	}
	
	/**
	 * 查询签约数据
	 * @param request
	 * @param response
	 */
	@RequestMapping("/findSignContractData")
	@ResponseBody
	public ReMsg findSignContractData(String applyId,HttpServletRequest request, HttpServletResponse response) {
		//用户不存在时直接返回
		ReMsg reMsg = null;
		//String userId = getUserId(request, response);
		if(applyId == null || "".equals(applyId)){
			reMsg = new ReMsg("数据不能为空！", false);
			return reMsg;
		}
		try{
			ZmlLoanApplicationEntity loanApply = systemService.getEntity(ZmlLoanApplicationEntity.class, applyId);
			//查询审批通过的 业务终审数据
			List<Map<String, Object>> rsList = zmlLoanApproveService.findByApplyId(applyId, "1");
			if(rsList == null){
				reMsg = new ReMsg("无签约数据！", false);
			}else if(rsList != null && rsList.size() ==1){
				reMsg = new ReMsg("", true);
				reMsg.add("data", rsList.get(0));
				reMsg.add("applyData", loanApply);
			}else{
				reMsg = new ReMsg("签约数据异常！", false);
			}
		}catch (Exception e) {
			e.printStackTrace();
			reMsg = new ReMsg("数据异常！", false);
			return reMsg;
		}
		
		return reMsg;
	}
	/**
	 * 查询产品信息
	 */
	@RequestMapping("/findProductInfo")
	@ResponseBody
	public ReMsg findProductInfo(){
		ReMsg reMsg = null;
		try {
			Map<String, String> param = new HashMap();
			param.put("status", "Y");
			List<Map<String, Object>> proList = zmlLoanProductService.findProduct(param);
			reMsg = new ReMsg("", true);
			reMsg.add("data", proList);
		} catch (Exception e) {
			reMsg = new ReMsg("查询异常!", false);
		}
		return reMsg;
	}
}
