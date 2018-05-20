package com.loan.controller;
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
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.jce.framework.core.beanvalidator.BeanValidators;
import com.jce.framework.core.common.controller.BaseController;
import com.jce.framework.core.common.exception.BusinessException;
import com.jce.framework.core.common.hibernate.qbc.CriteriaQuery;
import com.jce.framework.core.common.model.json.AjaxJson;
import com.jce.framework.core.common.model.json.ComboTree;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.constant.Globals;
import com.jce.framework.core.extend.hqlsearch.HqlGenerateUtil;
import com.jce.framework.core.util.ExceptionUtil;
import com.jce.framework.core.util.IdcardUtils;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.ResourceUtil;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.tag.easyui.TagUtil;
import com.jce.framework.tag.vo.easyui.ComboTreeModel;
import com.jce.framework.web.system.pojo.base.TSDemo;
import com.jce.framework.web.system.pojo.base.TSUser;
import com.jce.framework.web.system.service.SystemService;
import com.zml.base.entity.ZmlUserEntity;
import com.zml.base.loan.entity.ZmlLoanApplicationEntity;
import com.zml.base.loan.entity.ZmlLoanLanInfoEntity;
import com.zml.base.loan.entity.ZmlLoanWfTaskEntity;
import com.zml.common.ReMsg;
import com.zml.enums.YesOrNo;
import com.zml.enums.loan.ApplySts;
import com.zml.enums.loan.ApprovalFlag;
import com.zml.enums.loan.LoanTaskSts;
import com.zml.enums.loan.LoanTaskType;
import com.zml.enums.loan.LoanWFNode;
import com.zml.loan_service.ZmlLoanApplicationServiceI;
import com.zml.loan_service.ZmlLoanApplyDocumentServiceI;
import com.zml.loan_service.ZmlLoanLanInfoServiceI;
import com.zml.loan_service.ZmlLoanWfTaskServiceI;
import com.zml.service.ZmlUserServiceI;
import com.zml.util.DateUtil;

/**   
 * @Title: Controller  
 * @Description: 借款申请表
 * @author onlineGenerator
 * @date 2017-03-01 22:19:26
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zmlLoanApplicationController")
public class ZmlLoanApplicationController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZmlLoanApplicationController.class);

	@Autowired
	private ZmlLoanApplicationServiceI zmlLoanApplicationService;
	
	@Autowired
	private ZmlLoanApplyDocumentServiceI zmlLoanApplyDocumentService;
	
	@Autowired
	private ZmlUserServiceI ZmlUserService;
	
	@Autowired
	private ZmlLoanLanInfoServiceI zmlLoanLanInfoService;
	
	
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	@Autowired
	private ZmlLoanWfTaskServiceI zmlLoanWfTaskService;


	/**
	 * 借款申请表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zml_loan/application/zmlLoanApplicationList");
	}
	
	
	/**
	 * 跳转未审批列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "noApproveList")
	public ModelAndView noApproveList(HttpServletRequest request) {
		return new ModelAndView("com/zml_loan/application/noApproveList");
	}
	
	/**
	 * 业务初审
	 * 
	 * @return
	 */
	@RequestMapping(params = "firstApprove")
	public ModelAndView firstApprove(HttpServletRequest request) {
		return new ModelAndView("com/zml_loan/application/firstApproveList");
	}
	
	/**
	 * 业务终审
	 * 
	 * @return
	 */
	@RequestMapping(params = "endApprove")
	public ModelAndView endApprove(HttpServletRequest request) {
		return new ModelAndView("com/zml_loan/application/endApproveList");
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
	public void datagrid(ZmlLoanApplicationEntity zmlLoanApplication,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
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
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.zmlLoanApplicationService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除借款申请表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ZmlLoanApplicationEntity zmlLoanApplication, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zmlLoanApplication = systemService.getEntity(ZmlLoanApplicationEntity.class, zmlLoanApplication.getId());
		message = "借款申请表删除成功";
		try{
			zmlLoanApplication.setApplySts(ApplySts.APPROVE_REJECT.getStatusValue());
			//zmlLoanApplicationService.delete(zmlLoanApplication);
			zmlLoanApplicationService.saveOrUpdate(zmlLoanApplication);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "借款申请表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除借款申请表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "借款申请表删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlLoanApplicationEntity zmlLoanApplication = systemService.getEntity(ZmlLoanApplicationEntity.class, id);
				zmlLoanApplicationService.delete(zmlLoanApplication);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "借款申请表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加借款申请表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ZmlLoanApplicationEntity zmlLoanApplication, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "借款申请表添加成功";
		try{
			zmlLoanApplicationService.save(zmlLoanApplication);
			String approvalUserId = "";
			ZmlLoanWfTaskEntity task = new ZmlLoanWfTaskEntity();
			task.setUserId(zmlLoanApplication.getUserId());
			task.setCreateDate(new Date());
			task.setBizId(zmlLoanApplication.getId());
			task.setTaskType(LoanTaskType.LOAN_APPLY.getStatusValue());
			task.setStatus(LoanTaskSts.UPCOMING.getStatusValue());
			task.setApprovalFlag(ApprovalFlag.NO_APPROVE.getStatusValue());
			task.setTaskSubject(zmlLoanApplication.getSubject());
			task.setApprovalUserId(approvalUserId);
			task.setIsNotic(YesOrNo.NO.getStatusValue());
			task.setBpmStatus(LoanWFNode.BEGIN_APPROVE.getStatusValue());
			zmlLoanWfTaskService.save(task);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "借款申请表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	//
	/**
	 * 审批业务 更新状态，业务审批人
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "approveBiz")
	@ResponseBody
	public AjaxJson approveBiz(String id, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "借款申请表更新成功";
		ZmlLoanApplicationEntity t = zmlLoanApplicationService.get(ZmlLoanApplicationEntity.class, id);
		try {
//			TSUser user = ResourceUtil.getSessionUserName();
			t.setUpdateDate(new Date());
			t.setApplySts(ApplySts.APPROVE_ING.getStatusValue());
			//t.setPrincipal(user.getId());
			zmlLoanApplicationService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "借款申请表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新借款申请表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ZmlLoanApplicationEntity zmlLoanApplication, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "借款申请表更新成功";
		ZmlLoanApplicationEntity t = zmlLoanApplicationService.get(ZmlLoanApplicationEntity.class, zmlLoanApplication.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(zmlLoanApplication, t);
			zmlLoanApplicationService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "借款申请表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 借款申请表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ZmlLoanApplicationEntity zmlLoanApplication, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zmlLoanApplication.getId())) {
			zmlLoanApplication = zmlLoanApplicationService.getEntity(ZmlLoanApplicationEntity.class, zmlLoanApplication.getId());
			req.setAttribute("zmlLoanApplicationPage", zmlLoanApplication);
		}
		return new ModelAndView("com/zml_loan/application/zmlLoanApplication-add");
	}
	/**
	 * 借款申请表编辑页面跳转
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
	
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","zmlLoanApplicationController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ZmlLoanApplicationEntity zmlLoanApplication,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ZmlLoanApplicationEntity.class, dataGrid);
		HqlGenerateUtil.installHql(cq, zmlLoanApplication, request.getParameterMap());
		List<ZmlLoanApplicationEntity> zmlLoanApplications = this.zmlLoanApplicationService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"借款申请表");
		modelMap.put(NormalExcelConstants.CLASS,ZmlLoanApplicationEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("借款申请表列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,zmlLoanApplications);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ZmlLoanApplicationEntity zmlLoanApplication,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"借款申请表");
    	modelMap.put(NormalExcelConstants.CLASS,ZmlLoanApplicationEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("借款申请表列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<ZmlLoanApplicationEntity> listZmlLoanApplicationEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ZmlLoanApplicationEntity.class,params);
				for (ZmlLoanApplicationEntity zmlLoanApplication : listZmlLoanApplicationEntitys) {
					zmlLoanApplicationService.save(zmlLoanApplication);
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
	 * 审批父级下拉菜单
	 */
	@RequestMapping(params = "treeList")
	@ResponseBody
	public List<ComboTree> treeList(HttpServletRequest request, ComboTree comboTree) {
		/*CriteriaQuery cq = new CriteriaQuery(TSDemo.class);
		if (comboTree.getId() != null) {
			cq.eq("TSDemo.id", comboTree.getId());
		}
		if (comboTree.getId() == null) {
			cq.isNull("TSDemo");
		}
		cq.add();*/
		List<TSDemo> demoList = new ArrayList();
		TSDemo tree = new TSDemo();
		tree.setId("1");
		tree.setDemotitle("申请信息");
		tree.setTsDemos(null);
		tree.setDemourl("");
		demoList.add(tree);
		tree = new TSDemo();
		tree.setId("2");
		tree.setDemotitle("联系人");
		tree.setTsDemos(null);
		tree.setDemourl("");
		demoList.add(tree);
		tree = new TSDemo();
		tree.setId("3");
		tree.setDemotitle("申请文档");
		tree.setTsDemos(null);
		tree.setDemourl("");
		demoList.add(tree);
		tree = new TSDemo();
		tree.setId("4");
		tree.setDemotitle("最终意见");
		tree.setTsDemos(null);
		tree.setDemourl("");
		demoList.add(tree);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		ComboTreeModel comboTreeModel = new ComboTreeModel("id", "demotitle", "tsDemos", "demourl");
		comboTrees = systemService.ComboTree(demoList, comboTreeModel, null, false);
		return comboTrees;
	}
	// 审批子集下拉菜单
	@RequestMapping(params = "treeChildList")
	@ResponseBody
	public String treeChildList(String id){
		String code = systemService.get(TSDemo.class, id).getDemocode();
		return HtmlUtils.htmlUnescape(code);
	}
	
	
	/**
	 * 跳转 借款审批页面
	 * @return
	 */
	@RequestMapping(params = "goTreeChild")
	public ModelAndView goTreeChild(String childId, String userId, String applyId, String wfId,HttpServletRequest req) {
		ModelAndView mv =  new ModelAndView();
		if("1".equals(childId)){//申请信息
			ZmlLoanApplicationEntity zmlLoanApplication = zmlLoanApplicationService.getEntity(ZmlLoanApplicationEntity.class, applyId);
			req.setAttribute("zmlLoanApplicationPage", zmlLoanApplication);
			mv.setViewName("com/zml_loan/application/approveShow");
		}else if("2".equals(childId)){//联系人
			req.setAttribute("applyId", applyId);
			mv.setViewName("com/zml_loan/user_contacts/contactsApproveList");
		}else if("3".equals(childId)){//申请文档
			req.setAttribute("userId", userId);
			req.setAttribute("applyId", applyId);
			Map rsMap = zmlLoanApplyDocumentService.findDocByApplyUserId(applyId, userId);
			//req.setAttribute("rsList", rsList);
			mv.addObject("html1", rsMap.get("html1"));
			mv.addObject("html2", rsMap.get("html2"));
			mv.addObject("scriptSb", rsMap.get("scriptSb"));
			mv.addObject("styleContainer", rsMap.get("styleContainer"));
			mv.addObject("styleGallery", rsMap.get("styleGallery"));
			mv.setViewName("com/zml_loan/application/applyDoc");
		}else{//最终意见
			ZmlLoanWfTaskEntity zmlLoanWfTask = zmlLoanWfTaskService.getEntity(ZmlLoanWfTaskEntity.class, wfId);
			req.setAttribute("zmlLoanWfTaskPage", zmlLoanWfTask);
			req.setAttribute("userId", userId);
			req.setAttribute("applyId", applyId);
			mv.setViewName("com/zml_loan/application/wfFinalOpinion");
		}
		return mv;
	}
	
	/**
	 * 跳转 借款审批页面
	 * @return
	 */
	@RequestMapping(params = "getApplyDocList")
	@ResponseBody
	public ReMsg getApplyDocList(String applyId) {
		ReMsg msg = null;
		try {
			//List rsList = zmlLoanApplyDocumentService.findDocByApplyId(applyId);
			msg = new ReMsg("", true);
			//msg.add("dataList", rsList);
		} catch (Exception e) {
			msg = new ReMsg("", false);
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 身份信息审批
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doIdNumberUpdate")
	@ResponseBody
	public AjaxJson doIdNumberUpdate(ZmlUserEntity zmlUser, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "身份证信息审批成功";
		ZmlUserEntity t = null;
		t = ZmlUserService.get(ZmlUserEntity.class, zmlUser.getId());
		try {
			//解析身份证信息 自动补全其他项目
			String idCard = zmlUser.getIdentificationNumber();
			if( idCard!= null && !"".equals(idCard)){
				zmlUser.setBirthDay(DateUtil.strToDate(IdcardUtils.getBirthByIdCard(idCard), DateUtil.FORMAT_YYYYMMDD));
				zmlUser.setAge(IdcardUtils.getAgeByIdCard(idCard));
				zmlUser.setSex(IdcardUtils.getGenderByIdCard(idCard));
			}
			
			MyBeanUtils.copyBeanNotNull2Bean(zmlUser, t);
			ZmlUserService.saveOrUpdate(t);
			
		} catch (Exception e) {
			e.printStackTrace();
			message = "身份证信息审批失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 实际还款记录-土地信息审批
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doApproveLan")
	@ResponseBody
	public AjaxJson doApproveLan(ZmlLoanLanInfoEntity lanInfo, HttpServletRequest request) {
		String message = null;
		String id = null;
		String zmlLoanInfoId = lanInfo.getId();
		AjaxJson j = new AjaxJson();
		message = "土地信息审批成功";
		ZmlLoanLanInfoEntity t = null;
		String sqlWhere = " appl_id = '"+ lanInfo.getApplId() +"' and user_id = '" + lanInfo.getUserId() + "'";
		String sql = "select id from zml_loan_lan_info ";
		sql += "where" + sqlWhere;
		List<Map<String,Object>> idList = zmlLoanLanInfoService.findForJdbc(sql, null);
		if(idList.size() > 0){
			id = idList.get(0).get("id").toString();
			lanInfo.setId(id.toString());
		}
		try {
			if(id == null || "".equals(id)){
				zmlLoanLanInfoService.save(lanInfo);
			}else{
				t = zmlLoanLanInfoService.get(ZmlLoanLanInfoEntity.class, id);
				MyBeanUtils.copyBeanNotNull2Bean(lanInfo, t);
				zmlLoanLanInfoService.saveOrUpdate(t);
			}
			lanInfo.setId(zmlLoanInfoId);
		} catch (Exception e) {
			e.printStackTrace();
			message = "土地信息审批失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
}
