package com.loan.controller;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.jce.framework.web.cgform.entity.upload.CgUploadEntity;
import com.jce.framework.web.cgform.service.config.CgFormFieldServiceI;
import com.jce.framework.web.system.pojo.base.TSUser;
import com.jce.framework.web.system.service.SystemService;
import com.zml.base.entity.ZmlUserMessageEntity;
import com.zml.base.loan.entity.ZmlLoanContractEntity;
import com.zml.base.loan.entity.ZmlLoanRepayRecordEntity;
import com.zml.enums.YesOrNo;
import com.zml.enums.loan.ApprovalFlag;
import com.zml.enums.loan.ContractStatus;
import com.zml.enums.loan.RepayPlanDetailStatus;
import com.zml.loan_service.ZmlLoanContractServiceI;
import com.zml.loan_service.ZmlLoanRepayPlanDetailServiceI;
import com.zml.loan_service.ZmlLoanRepayRecordServiceI;
/**   
 * @Title: Controller  
 * @Description: 实际还款记录
 * @author onlineGenerator
 * @date 2017-03-01 22:27:37
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zmlLoanRepayRecordController")
public class ZmlLoanRepayRecordController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlLoanRepayRecordController.class);

	@Autowired
	private ZmlLoanRepayRecordServiceI zmlLoanRepayRecordService;
	
	@Autowired
	private ZmlLoanContractServiceI zmlLoanContractService;
	
	@Autowired
	private ZmlLoanRepayPlanDetailServiceI zmlLoanRepayPlanDetailService;
	
	
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private CgFormFieldServiceI cgFormFieldService;
	


	/**
	 * 实际还款记录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml_loan/repay_record/zmlLoanRepayRecordList");
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
	public void datagrid(ZmlLoanRepayRecordEntity zmlLoanRepayRecord,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZmlLoanRepayRecordEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, zmlLoanRepayRecord, request.getParameterMap());
		try{
		//自定义追加查询条件
		String query_repayTime_begin = request.getParameter("repayTime_begin");
		String query_repayTime_end = request.getParameter("repayTime_end");
		if(StringUtil.isNotEmpty(query_repayTime_begin)){
			cq.ge("repayTime", new SimpleDateFormat("yyyy-MM-dd").parse(query_repayTime_begin));
		}
		if(StringUtil.isNotEmpty(query_repayTime_end)){
			cq.le("repayTime", new SimpleDateFormat("yyyy-MM-dd").parse(query_repayTime_end));
		}
		String query_repayAmt_begin = request.getParameter("repayAmt_begin");
		String query_repayAmt_end = request.getParameter("repayAmt_end");
		if(StringUtil.isNotEmpty(query_repayAmt_begin)){
			cq.ge("repayAmt", Integer.parseInt(query_repayAmt_begin));
		}
		if(StringUtil.isNotEmpty(query_repayAmt_end)){
			cq.le("repayAmt", Integer.parseInt(query_repayAmt_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlLoanRepayRecordService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除实际还款记录
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlLoanRepayRecordEntity zmlLoanRepayRecord, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zmlLoanRepayRecord = systemService.getEntity(ZmlLoanRepayRecordEntity.class, zmlLoanRepayRecord.getId());
		message = "实际还款记录删除成功";
		try{
			zmlLoanRepayRecordService.delete(zmlLoanRepayRecord);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "实际还款记录删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除实际还款记录
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "实际还款记录删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlLoanRepayRecordEntity zmlLoanRepayRecord = systemService.getEntity(ZmlLoanRepayRecordEntity.class, 
				id
				);
				zmlLoanRepayRecordService.delete(zmlLoanRepayRecord);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "实际还款记录删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加实际还款记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlLoanRepayRecordEntity zmlLoanRepayRecord, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "实际还款记录添加成功";
		try{
			zmlLoanRepayRecordService.save(zmlLoanRepayRecord);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "实际还款记录添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		j.setObj(zmlLoanRepayRecord);
		return j;
	}
	
	/**
	 * 更新实际还款记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlLoanRepayRecordEntity zmlLoanRepayRecord, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "实际还款记录更新成功";
		ZmlLoanRepayRecordEntity t = zmlLoanRepayRecordService.get(ZmlLoanRepayRecordEntity.class, zmlLoanRepayRecord.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zmlLoanRepayRecord, t);
			zmlLoanRepayRecordService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "实际还款记录更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 实际还款记录-还款审批
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doApproveRepay")
	@ResponseBody
	public AjaxJson doApproveRepay(ZmlLoanRepayRecordEntity zmlLoanRepayRecord, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "实际还款记录更新成功";
		ZmlLoanRepayRecordEntity t = zmlLoanRepayRecordService.get(ZmlLoanRepayRecordEntity.class, zmlLoanRepayRecord.getId());
		try {
			TSUser user = ResourceUtil.getSessionUserName();
			zmlLoanRepayRecord.setApprovalUserId(user.getId());
			zmlLoanRepayRecord.setApprovalDate(new Date());
			MyBeanUtils.copyBeanNotNull2Bean(zmlLoanRepayRecord, t);
			
			ZmlUserMessageEntity userMessage = new ZmlUserMessageEntity();
			userMessage.setUserId(zmlLoanRepayRecord.getUserId());
			//审批通过 修改还款计划表
			if(ApprovalFlag.APPROVE_PAST.getStatusValue().equals(zmlLoanRepayRecord.getApprovalFlag())){
				//修改还款计划 
				Map<String, Object>  map = zmlLoanRepayPlanDetailService.findContractNoRepayPlan(zmlLoanRepayRecord.getContractId());
				//应还总和
				BigDecimal profitRepaySum = (BigDecimal)map.get("profitRepaySum");

				//根据还款金额修改合同状态，
				ZmlLoanContractEntity contract = zmlLoanContractService.getEntity(ZmlLoanContractEntity.class, zmlLoanRepayRecord.getContractId());
				//还款金额小于 应还总额、并且是逾期状态 将合同状态改为逾期
				if(t.getApproveRepayAmt().doubleValue() < profitRepaySum.doubleValue()
						&& RepayPlanDetailStatus.OVERDUE.getStatusValue().equals(map.get("status"))){
					if(RepayPlanDetailStatus.OVERDUE.getStatusValue().equals(map.get("status")))
						contract.setStatus(ContractStatus.OVERDUE.getStatusValue());
					else
						contract.setStatus(ContractStatus.NORMAL.getStatusValue());
					t.setStatus(RepayPlanDetailStatus.PART_REPAY.getStatusValue());//部分还款
					//修改还款计划表
					
				}else{
					if(RepayPlanDetailStatus.OVERDUE.getStatusValue().equals(map.get("status")))
						t.setStatus(RepayPlanDetailStatus.OVERDUE_REPAY.getStatusValue());//逾期还完
					else
						t.setStatus(RepayPlanDetailStatus.REPAY.getStatusValue());//正常还完
					contract.setStatus(ContractStatus.CLOSED.getStatusValue());
					//修改还款计划表
					
				}
				zmlLoanRepayRecordService.saveOrUpdate(t);
				zmlLoanRepayRecordService.saveOrUpdate(contract);
				userMessage.setContent("您的最近一笔还款成功！");
			}else{
				userMessage.setContent("您的最近一笔还款失败，请确认，如有疑问请联系助民乐客服！");
			}
			userMessage.setSendFlag(YesOrNo.NO.getStatusValue());
			systemService.save(userMessage);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "实际还款记录更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 实际还款记录新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlLoanRepayRecordEntity zmlLoanRepayRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanRepayRecord.getId())) {
			zmlLoanRepayRecord = zmlLoanRepayRecordService.getEntity(ZmlLoanRepayRecordEntity.class, zmlLoanRepayRecord.getId());
			req.setAttribute("zmlLoanRepayRecordPage", zmlLoanRepayRecord);
		}
		return new ModelAndView("com/zml_loan/repay_record/zmlLoanRepayRecord-add");
	}
	/**
	 * 实际还款记录编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ZmlLoanRepayRecordEntity zmlLoanRepayRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanRepayRecord.getId())) {
			zmlLoanRepayRecord = zmlLoanRepayRecordService.getEntity(ZmlLoanRepayRecordEntity.class, zmlLoanRepayRecord.getId());
			req.setAttribute("zmlLoanRepayRecordPage", zmlLoanRepayRecord);
		}
		return new ModelAndView("com/zml_loan/repay_record/zmlLoanRepayRecord-update");
	}
	
	/**
	 * 实际还款记录还款审核页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goApproveRepay")
	public ModelAndView goApproveRepay(ZmlLoanRepayRecordEntity zmlLoanRepayRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanRepayRecord.getId())) {
			zmlLoanRepayRecord = zmlLoanRepayRecordService.getEntity(ZmlLoanRepayRecordEntity.class, zmlLoanRepayRecord.getId());
			req.setAttribute("zmlLoanRepayRecordPage", zmlLoanRepayRecord);
		}
		return new ModelAndView("com/zml_loan/repay_record/zmlLoanRepayRecord-ApproveRepay");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","zmlLoanRepayRecordController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ZmlLoanRepayRecordEntity zmlLoanRepayRecord,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ZmlLoanRepayRecordEntity.class, dataGrid);
		HqlGenerateUtil.installHql(cq, zmlLoanRepayRecord, request.getParameterMap());
		List<ZmlLoanRepayRecordEntity> zmlLoanRepayRecords = this.zmlLoanRepayRecordService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"实际还款记录");
		modelMap.put(NormalExcelConstants.CLASS,ZmlLoanRepayRecordEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("实际还款记录列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,zmlLoanRepayRecords);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ZmlLoanRepayRecordEntity zmlLoanRepayRecord,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"实际还款记录");
    	modelMap.put(NormalExcelConstants.CLASS,ZmlLoanRepayRecordEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("实际还款记录列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<ZmlLoanRepayRecordEntity> listZmlLoanRepayRecordEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ZmlLoanRepayRecordEntity.class,params);
				for (ZmlLoanRepayRecordEntity zmlLoanRepayRecord : listZmlLoanRepayRecordEntitys) {
					zmlLoanRepayRecordService.save(zmlLoanRepayRecord);
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
	 * 获取文件附件信息
	 * 
	 * @param id zmlLoanRepayRecord主键id
	 */
	@RequestMapping(params = "getFiles")
	@ResponseBody
	public AjaxJson getFiles(String id){
		List<CgUploadEntity> uploadBeans = cgFormFieldService.findByProperty(CgUploadEntity.class, "cgformId", id);
		List<Map<String,Object>> files = new ArrayList<Map<String,Object>>(0);
		for(CgUploadEntity b:uploadBeans){
			String title = b.getAttachmenttitle();//附件名
			String fileKey = b.getId();//附件主键
			String path = b.getRealpath();//附件路径
			String field = b.getCgformField();//表单中作为附件控件的字段
			Map<String, Object> file = new HashMap<String, Object>();
			file.put("title", title);
			file.put("fileKey", fileKey);
			file.put("path", path);
			file.put("field", field==null?"":field);
			files.add(file);
		}
		AjaxJson j = new AjaxJson();
		j.setObj(files);
		return j;
	}
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ZmlLoanRepayRecordEntity> list() {
		List<ZmlLoanRepayRecordEntity> listZmlLoanRepayRecords=zmlLoanRepayRecordService.getList(ZmlLoanRepayRecordEntity.class);
		return listZmlLoanRepayRecords;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZmlLoanRepayRecordEntity task = zmlLoanRepayRecordService.get(ZmlLoanRepayRecordEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZmlLoanRepayRecordEntity zmlLoanRepayRecord, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanRepayRecordEntity>> failures = validator.validate(zmlLoanRepayRecord);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlLoanRepayRecordService.save(zmlLoanRepayRecord);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zmlLoanRepayRecord.getId();
		URI uri = uriBuilder.path("/rest/zmlLoanRepayRecordController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZmlLoanRepayRecordEntity zmlLoanRepayRecord) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZmlLoanRepayRecordEntity>> failures = validator.validate(zmlLoanRepayRecord);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			zmlLoanRepayRecordService.saveOrUpdate(zmlLoanRepayRecord);
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
		zmlLoanRepayRecordService.deleteEntityById(ZmlLoanRepayRecordEntity.class, id);
	}
}
