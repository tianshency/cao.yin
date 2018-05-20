package com.zml.loan.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.jce.framework.core.common.exception.BusinessException;
import com.jce.framework.core.common.model.json.AjaxJson;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.constant.Globals;
import com.jce.framework.core.util.DateUtils;
import com.jce.framework.core.util.FileUtils;
import com.jce.framework.core.util.ResourceUtil;
import com.jce.framework.web.system.pojo.base.TSUser;
import com.jce.framework.web.system.service.SystemService;
import com.zml.app.controller.BaseController;
import com.zml.base.loan.entity.ZmlLoanApplicationEntity;
import com.zml.base.loan.entity.ZmlLoanApplyDocumentEntity;
import com.zml.base.loan.entity.ZmlLoanApproveEntity;
import com.zml.base.loan.entity.ZmlLoanContractDocumentEntity;
import com.zml.base.loan.entity.ZmlLoanContractEntity;
import com.zml.base.loan.entity.ZmlLoanRepayPlanDetailEntity;
import com.zml.base.loan.entity.ZmlLoanRepayRecordEntity;
import com.zml.base.loan.entity.ZmlLoanWfTaskEntity;
import com.zml.base.loan.page.ZmlLoanContractPage;
import com.zml.common.Constant;
import com.zml.common.ReMsg;
import com.zml.enums.FileType;
import com.zml.enums.YesOrNo;
import com.zml.enums.loan.ApprovalFlag;
import com.zml.enums.loan.ContractStatus;
import com.zml.enums.loan.LoanDocumentDirName;
import com.zml.enums.loan.LoanNoPrefix;
import com.zml.enums.loan.LoanTaskSts;
import com.zml.enums.loan.LoanTaskType;
import com.zml.enums.loan.LoanWFNode;
import com.zml.enums.loan.RepayStatus;
import com.zml.loan_service.ZmlLoanApplicationServiceI;
import com.zml.loan_service.ZmlLoanApplyDocumentServiceI;
import com.zml.loan_service.ZmlLoanContractServiceI;
import com.zml.loan_service.ZmlLoanRepayPlanDetailServiceI;
import com.zml.loan_service.ZmlLoanRepayRecordServiceI;
import com.zml.loan_service.ZmlLoanUserContactsServiceI;
import com.zml.loan_service.ZmlLoanWfTaskServiceI;
import com.zml.util.DateUtil;
import com.zml.util.GenerateNo;

//我的贷款 控制类
@Controller
@RequestMapping("/myLoanController")
public class MyLoanController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MyLoanController.class);

	@Autowired
	private ZmlLoanApplicationServiceI zmlLoanApplicationService;
	
	@Autowired
	private ZmlLoanApplyDocumentServiceI zmlLoanApplyDocumentService;
	
	@Autowired
	private ZmlLoanUserContactsServiceI zmlLoanUserContactsService;
	
	@Autowired
	private ZmlLoanContractServiceI zmlLoanContractServiceI;
	
	@Autowired
	private ZmlLoanRepayRecordServiceI zmlLoanRepayRecordService;
	
	@Autowired
	private ZmlLoanWfTaskServiceI zmlLoanWfTaskService;
	
	@Autowired
	private ZmlLoanRepayPlanDetailServiceI zmlLoanRepayPlanDetailService;
	
	@Autowired
	private ZmlLoanContractServiceI zmlLoanContractService;
	
	@Autowired
	private SystemService systemService;
	
	/**
	 * 跳转 我的借款页面
	 * 
	 * @return
	 */
	@RequestMapping("/toMyLoan")
	public ModelAndView toMyLoan(HttpServletRequest req, HttpServletResponse response) {
		String userId = getUserId(req, response);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/loan/application/myLoanList");
		return mv;
	}
	/**
	 * 跳转 我的还款款页面
	 * 
	 * @return
	 */
	@RequestMapping("/toRepayment")
	public ModelAndView toRepayment(String contractId, HttpServletRequest request, HttpServletResponse response) {
		//String userId = getUserId(request, response);
		ModelAndView mv = new ModelAndView();
		String token = GenerateNo.uuid();
		setToken(request, token);
		mv.addObject("token", token);
		mv.addObject("contractId", contractId);
		mv.setViewName("loan/myaccount/Repayment");
		return mv;
	}
	
	/**
	 * 跳转我的还款工具页面
	 * 
	 * @return
	 */
	@RequestMapping("/toRepayTools")
	public ModelAndView toRepayTools(String contractId, HttpServletRequest request, HttpServletResponse response) {
		//String userId = getUserId(request, response);
		ModelAndView mv = new ModelAndView();
		String token = GenerateNo.uuid();
		setToken(request, token);
		mv.addObject("token", token);
		ZmlLoanContractEntity contractInfo = zmlLoanContractServiceI.getEntity(ZmlLoanContractEntity.class, contractId);
		mv.addObject("contractEntity",contractInfo);
		mv.addObject("contractId", contractId);
		mv.setViewName("loan/myaccount/repayTools");
		return mv;
	}
	/**
	 * 查询我的贷款统计信息
	 */
	@RequestMapping(value="/findMyLoanInfo")
	@ResponseBody
	public ReMsg findMyLoanInfo(HttpServletRequest request, HttpServletResponse response,DataGrid dataGrid) {
		 ReMsg msg = null;
		 try {
			 String userId = getUserId(request, response);
			 //查询 我的贷款统计信息
			 Map rsMap = zmlLoanContractServiceI.findMyLoanStatistics(userId);
			 msg = new ReMsg("", true);
			 msg.add("totalData", rsMap);
			//查询 我的贷款列表
			 if(rsMap != null){
				 List list = zmlLoanContractServiceI.findMyLoanList(userId,dataGrid.getPage(), dataGrid.getRows());
				 msg.add("listData", list);
				 msg.add("dataGrid", dataGrid);
			 }else{
				 msg.add("listData", "");
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
			msg = new ReMsg("", false);
		}
		 return msg;
	}
	/**
	 * 查询我的贷款列表
	 */
	/*@RequestMapping(value="/findMyLoanList")
	@ResponseBody
	public ReMsg findMyLoanList(HttpServletRequest request, HttpServletResponse response) {
		 ReMsg msg = null;
		 try {
			 String userId = getUserId(request, response);
			 List list = zmlLoanContractServiceI.findMyLoanList(userId, 0, 0);
			 msg = new ReMsg("", true);
			 msg.add("listData", list);
		} catch (Exception e) {
			e.printStackTrace();
			msg = new ReMsg("", false);
		}
		return msg;
	}*/
	/**
	 * 查询用户合同详细信息
	 */
	@RequestMapping(value="/findMyContractInfo")
	@ResponseBody
	public ReMsg findMyContractInfo(String contractId, HttpServletRequest request, HttpServletResponse response) {
		 ReMsg msg = null;
		 try {
			 String userId = getUserId(request, response);
			 if(userId == null || "".equals(userId)
			   || contractId == null || "".equals(contractId)){
				 msg = new ReMsg("数据异常！", false);
				 return msg;
			 }
			 ZmlLoanContractEntity contractInfo = zmlLoanContractServiceI.getEntity(ZmlLoanContractEntity.class, contractId);
			 msg = new ReMsg("", true);
			 msg.add("data", contractInfo);
		} catch (Exception e) {
			e.printStackTrace();
			msg = new ReMsg("", false);
		}
		return msg;
	}
	
	/**
	 * 查询用户合同应还款 信息 涵 还款计划
	 * num=1 查所有应还未还， num=-1 查询全部 , num=0 查询所有未还
	 */
	@RequestMapping(value="/findMyContractShouldRepayInfo")
	@ResponseBody
	public ReMsg findMyContractShouldRepayInfo(String contractId, String num, HttpServletRequest request, HttpServletResponse response) {
		 ReMsg msg = null;
		 try {
			 String userId = getUserId(request, response);
			 if(userId == null || "".equals(userId)
			   || contractId == null || "".equals(contractId)){
				 msg = new ReMsg("数据异常！", false);
				 return msg;
			 }
			 Map<String, Object>  map = zmlLoanRepayPlanDetailService.findContractNoRepayPlan(contractId);
			 /*List rsList = (List)map.get("rsList");
			 Map rsMap = (Map)map.get("rsMap");
			 msg = new ReMsg("", true);
			 //根据条件num 查询还款计划表
			 msg.add("dataList", rsList);*/
			 //应还实还信息
			 msg = new ReMsg("", true);
			 msg.add("rsMap", map);
		} catch (Exception e) {
			e.printStackTrace();
			msg = new ReMsg("", false);
		}
		return msg;
	}
	/**
	 * 还款提交
	 */
	@RequestMapping(value="/repayment",headers=("content-type=multipart/*"))
	@ResponseBody
	public ReMsg repayment(String contractId, BigDecimal repayAmt, String repayChannel, String repayPlanId, String token, HttpServletRequest request, HttpServletResponse response) {
		 ReMsg msg = null;
		 //检查token 是否有效
		 if(!checkToken(request, token)){
			msg = new ReMsg("提交异常！", false);
			return msg;
		 }
		 try {
			 String userId = getUserId(request, response);
			 if(userId == null || "".equals(userId)
			   || contractId == null || "".equals(contractId)){
				 msg = new ReMsg("数据异常！", false);
				 return msg;
			 }
			 if(repayAmt.doubleValue() <= 0){
				 msg = new ReMsg("还款金额必须大于0！", false);
				 return msg;
			 }
			 String rsPath = uploadFile(repayPlanId,request, response);
			 if(rsPath == null){
				 msg = new ReMsg("上传凭主证不能为空", false);
				 return msg;
			 }
			 ZmlLoanRepayRecordEntity zmlLoanRepayRecord = new ZmlLoanRepayRecordEntity();
			 zmlLoanRepayRecord.setCreateDate(new Date());
			 zmlLoanRepayRecord.setContractId(contractId);
			 zmlLoanRepayRecord.setUserId(userId);
			 zmlLoanRepayRecord.setRepayAmt(repayAmt);
			 zmlLoanRepayRecord.setRepayChannel(repayChannel);
			 zmlLoanRepayRecord.setApprovalFlag(ApprovalFlag.NO_APPROVE.getStatusValue());
			 zmlLoanRepayRecord.setRepayPlanDetailId(repayPlanId);
			 //保存凭证
			 zmlLoanRepayRecord.setRepayCertificate(rsPath);
			 zmlLoanRepayRecord.setStatus(RepayStatus.APPROVE_ING.getStatusValue());
			 zmlLoanRepayRecordService.doSave(contractId, zmlLoanRepayRecord);
			 msg = new ReMsg("", true);
		} catch (Exception e) {
			e.printStackTrace();
			msg = new ReMsg("", false);
		}
		//移除token
		removeToken(request, token);
		return msg;
	}
	
	/**
	 * 保存图片
	 * @param file
	 * docType:对应枚举 LoanApplyDocType
	 */
	public String uploadFile(String repayPlanId, HttpServletRequest request, HttpServletResponse response) {
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	     //MultipartFile file = multipartRequest.getFiles("errPic");//获取文件集合  对应  jquery $("#imageFile").get(0).files
	     // 获得第1张图片（根据前台的name名称得到上传的文件）
		 String rsPath = null;
	     MultipartFile file = multipartRequest.getFile("upload"); 
	     if (file != null && file.getSize()>0) {
			FileOutputStream out = null;
			String[] filename = file.getOriginalFilename().split("\\.");
			if (filename == null || filename.length == 0 || "".equals(filename[0])) {
				return null;
			}
			String suffix = filename[filename.length - 1];
			//String path = BaseEntity.class.getResource("").getPath();
			//path = path.split("WEB-INF")[0];
			String path = Constant.IMG_FILE_PATH;
			String yyyyMM = DateUtils.formatDateToStr(DateUtils.FORMAT_YYYYMM, new Date());
			String tempPath = Constant.LOAN_IMG_FILE_PATH_ONE + LoanDocumentDirName.RE_PAY_DIR.getStatusValue() +File.separator + yyyyMM + File.separator + repayPlanId + File.separator ;
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
		}else{
			return null;
		}
	    return rsPath;
	}
	
	/**
	 * 签订借款合同
	 * @param applyApproveId 业务审批ID，对应 签订合同书数据查询接口返回的id
	 * @return
	 */
	@RequestMapping(params = "signedContract")
	@ResponseBody
	public ReMsg signedContract(ZmlLoanContractEntity zmlLoanContract, String applyApproveId, HttpServletRequest request, HttpServletResponse response) {
		ReMsg msg = null;
		try{
			String userId = getUserId(request, response);
			String contractNo = LoanNoPrefix.CONTRACT_NO_PREF.getStatusValue()+ DateUtil.getNumberDateTime() + GenerateNo.getRandomNum(Constant.BIZ_NO_LEN);
			zmlLoanContract.setContractNo(contractNo);
			zmlLoanContract.setOperatorId(userId);
			zmlLoanContract.setUserId(userId);
			zmlLoanContract.setApprovalFlag(ApprovalFlag.NO_APPROVE.getStatusValue());
			zmlLoanContract.setCreateDate(new Date());
			//查询最终审判额度
			ZmlLoanApproveEntity zmlLoanApproveEntity = systemService.getEntity(ZmlLoanApproveEntity.class, applyApproveId);
			zmlLoanContract.setCreditAmt(zmlLoanApproveEntity.getApproveAmount());
			zmlLoanContract.setInterestRate(zmlLoanApproveEntity.getInterestRate());
			zmlLoanContract.setTerm(new Integer(zmlLoanApproveEntity.getTerm()));
			zmlLoanContract.setTermUnit(new Integer(zmlLoanApproveEntity.getTermUnit()));
			zmlLoanContract.setFee(zmlLoanApproveEntity.getFee());
			zmlLoanContract.setIsSigned(YesOrNo.YES.getStatusValue() + "");
			zmlLoanContract.setSignedDay(new Date());
			zmlLoanContract.setRepayment(zmlLoanApproveEntity.getRepayment());
			zmlLoanContract.setStatus(ContractStatus.NO_PAY.getStatusValue());
			zmlLoanContractService.addMain(zmlLoanContract, null);
			//增加放款 代办任务
			ZmlLoanWfTaskEntity task = new ZmlLoanWfTaskEntity();
			task.setUserId(zmlLoanContract.getUserId());
			task.setCreateDate(new Date());
			task.setBizId(zmlLoanContract.getId());
			task.setTaskType(LoanTaskType.PAY.getStatusValue());
			task.setStatus(LoanTaskSts.PROCESSING.getStatusValue());
			task.setApprovalFlag(ApprovalFlag.NO_APPROVE.getStatusValue());
			task.setBpmStatus(LoanWFNode.PAY_APPROVE.getStatusValue());
			task.setTaskSubject("放款审批");
			zmlLoanWfTaskService.save(task);
			msg = new ReMsg("合同签约成功，等待确认放款！", true);
		}catch(Exception e){
			e.printStackTrace();
			msg = new ReMsg("合同签约失败！", false);
		}
		return msg;
	}
	
}
