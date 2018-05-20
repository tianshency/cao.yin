package com.zml.loan_service.impl;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.jce.framework.core.util.ApplicationContextUtil;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.MyClassLoader;
import com.jce.framework.core.util.ResourceUtil;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.web.cgform.enhance.CgformEnhanceJavaInter;
import com.jce.framework.web.system.pojo.base.TSUser;
import com.zml.base.loan.entity.ZmlLoanApplicationEntity;
import com.zml.base.loan.entity.ZmlLoanApproveEntity;
import com.zml.base.loan.entity.ZmlLoanContractEntity;
import com.zml.base.loan.entity.ZmlLoanRepayPlanDetailEntity;
import com.zml.base.loan.entity.ZmlLoanRepayRecordEntity;
import com.zml.base.loan.entity.ZmlLoanRiskCreditEntity;
import com.zml.base.loan.entity.ZmlLoanWfTaskEntity;
import com.zml.enums.YesOrNo;
import com.zml.enums.loan.ApplySts;
import com.zml.enums.loan.ApprovalFlag;
import com.zml.enums.loan.ContractStatus;
import com.zml.enums.loan.LoanTaskSts;
import com.zml.enums.loan.LoanTaskType;
import com.zml.enums.loan.LoanWFNode;
import com.zml.enums.loan.RepayPlanDetailStatus;
import com.zml.enums.loan.RepayStatus;
import com.zml.loan_service.ZmlLoanWfTaskServiceI;
import com.zml.util.BigDemicalUtil;
import com.zml.util.DateUtil;

@Service("zmlLoanWfTaskService")
@Transactional
public class ZmlLoanWfTaskServiceImpl extends CommonServiceImpl implements ZmlLoanWfTaskServiceI {

	
 	public void delete(ZmlLoanWfTaskEntity entity) throws Exception{
 		//执行删除操作增强业务
 		super.delete(entity);
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ZmlLoanWfTaskEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ZmlLoanWfTaskEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	
 	
 	public void doApplyApprove(ZmlLoanWfTaskEntity zmlLoanWfTask) throws Exception{
 		ZmlLoanWfTaskEntity t = this.getEntity(ZmlLoanWfTaskEntity.class, zmlLoanWfTask.getId());
 		TSUser user = ResourceUtil.getSessionUserName();
		zmlLoanWfTask.setApprovalUserId(user.getId());
		zmlLoanWfTask.setUpdateDate(new Date());
		zmlLoanWfTask.setEndTime(new Date());
		zmlLoanWfTask.setStatus(LoanTaskSts.DONE.getStatusValue());
		MyBeanUtils.copyBeanNotNull2Bean(zmlLoanWfTask, t);
		super.saveOrUpdate(t);
		if(ApprovalFlag.APPROVE_PAST.getStatusValue().equals(zmlLoanWfTask.getApprovalFlag())){
			//提交到下一节点
			ZmlLoanWfTaskEntity task = new ZmlLoanWfTaskEntity();
			task.setUserId(t.getUserId());
			task.setCreateDate(new Date());
			task.setBizId(t.getBizId());
			task.setTaskType(LoanTaskType.LOAN_APPLY.getStatusValue());
			task.setStatus(LoanTaskSts.PROCESSING.getStatusValue());
			task.setApprovalFlag(ApprovalFlag.NO_APPROVE.getStatusValue());
			task.setTaskSubject(t.getTaskSubject());
			//task.setApprovalUserId(approvalUserId);s
			task.setIsNotic(YesOrNo.NO.getStatusValue() + "");
			if(LoanWFNode.BEGIN_APPROVE.getStatusValue().equals(t.getBpmStatus())){
				task.setBpmStatus(LoanWFNode.AUTO_CREDIT.getStatusValue());
				this.save(task);
			}else if(LoanWFNode.AUTO_CREDIT.getStatusValue().equals(t.getBpmStatus())){
				//增加授信额度 实际上自动授信是由系统定时任务 执行的
				ZmlLoanRiskCreditEntity zmlLoanRiskCredit = new ZmlLoanRiskCreditEntity();
				zmlLoanRiskCredit.setApplId(task.getBizId());
				zmlLoanRiskCredit.setUserId(zmlLoanWfTask.getUserId());
				zmlLoanRiskCredit.setApprovalDate(new Date());
				zmlLoanRiskCredit.setApprovalFlag(ApprovalFlag.APPROVE_PAST.getStatusValue());
				zmlLoanRiskCredit.setApprovalUserId(user.getId());
				zmlLoanRiskCredit.setAssessAmt(zmlLoanWfTask.getApprovalAmt());
				zmlLoanRiskCredit.setCreateDate(new Date());
				zmlLoanRiskCredit.setAssessStartTime(new Date());
				Thread.sleep(2000);
				zmlLoanRiskCredit.setAssessEndTime(new Date());
				this.save(zmlLoanRiskCredit);
				task.setBpmStatus(LoanWFNode.END_APPROVE.getStatusValue());
				this.save(task);
			}else if(LoanWFNode.END_APPROVE.getStatusValue().equals(t.getBpmStatus())){
				//修改业务申请表状态为终审
				ZmlLoanApplicationEntity apply = this.getEntity(ZmlLoanApplicationEntity.class, task.getBizId());
				apply.setUpdateDate(new Date());
				apply.setApplySts(ApplySts.APPROVE_PAST.getStatusValue());
				this.save(apply);
				//增加终审 记录
				ZmlLoanApproveEntity zmlLoanApproveEntity = new ZmlLoanApproveEntity();
				zmlLoanApproveEntity.setCreditAmount(t.getApprovalAmt());
				zmlLoanApproveEntity.setApplId(t.getBizId());
                //zmlLoanApproveEntity.setCreditId(creditId);
				zmlLoanApproveEntity.setUserId(apply.getUserId());
				zmlLoanApproveEntity.setApprovalFlag(t.getApprovalFlag());
				zmlLoanApproveEntity.setApprovalOpinion(t.getApprovalOpinion());
				zmlLoanApproveEntity.setUpdateDate(new Date());
				zmlLoanApproveEntity.setApprovalUserId(user.getId());
				zmlLoanApproveEntity.setApproveAmount(t.getApprovalAmt());
				zmlLoanApproveEntity.setArgeeAmount(t.getApprovalAmt());
				zmlLoanApproveEntity.setFee(t.getApprovalFee());
				zmlLoanApproveEntity.setTerm(t.getApprovalTerm());
				zmlLoanApproveEntity.setTermUnit(""+t.getApprovalTermUnit());
				zmlLoanApproveEntity.setInterestRate(t.getApprovalInterestRate());
				zmlLoanApproveEntity.setRepayment(t.getRepayment());
				//task.setBpmStatus(LoanWFNode.PAY_APPROVE.getStatusValue());
				//zmlLoanWfTaskService.save(task);
				this.save(zmlLoanApproveEntity);
			}/*else if(LoanWFNode.PAY_APPROVE.getStatusValue().equals(t.getBpmStatus())){
				//task.setBpmStatus(LoanWFNode.REPAY_APPROVE.getStatusValue());
				//zmlLoanWfTaskService.save(task);
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
			}*/
		}else{
			//修改申请表状态为 拒绝
			ZmlLoanApplicationEntity apply = this.getEntity(ZmlLoanApplicationEntity.class, t.getBizId());
			apply.setApplySts(ApplySts.APPROVE_REJECT.getStatusValue());
			apply.setUpdateDate(new Date());
			this.saveOrUpdate(apply);
		}
 		//执行更新操作增强业务
 		//this.doUpdateBus(zmlLoanWfTask);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ZmlLoanWfTaskEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ZmlLoanWfTaskEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ZmlLoanWfTaskEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(ZmlLoanWfTaskEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("bpm_status", t.getBpmStatus());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("update_name", t.getUpdateName());
		map.put("update_by", t.getUpdateBy());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("sys_company_code", t.getSysCompanyCode());
		map.put("create_date", t.getCreateDate());
		map.put("update_date", t.getUpdateDate());
		map.put("user_id", t.getUserId());
		map.put("biz_id", t.getBizId());
		map.put("task_type", t.getTaskType());
		map.put("task_subject", t.getTaskSubject());
		map.put("product_id", t.getProductId());
		map.put("task_source", t.getTaskSource());
		map.put("approval_amt", t.getApprovalAmt());
		map.put("approval_interest_rate", t.getApprovalInterestRate());
		map.put("approval_term", t.getApprovalTerm());
		map.put("approval_term_unit", t.getApprovalTermUnit());
		map.put("start_time", t.getStartTime());
		map.put("end_time", t.getEndTime());
		map.put("approval_flag", t.getApprovalFlag());
		map.put("approval_user_id", t.getApprovalUserId());
		map.put("approval_date", t.getApprovalDate());
		map.put("approval_opinion", t.getApprovalOpinion());
		map.put("is_notic", t.getIsNotic());
		map.put("notic_content", t.getNoticContent());
		map.put("from_user_id", t.getFromUserId());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ZmlLoanWfTaskEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{user_id}",String.valueOf(t.getUserId()));
 		sql  = sql.replace("#{biz_id}",String.valueOf(t.getBizId()));
 		sql  = sql.replace("#{task_type}",String.valueOf(t.getTaskType()));
 		sql  = sql.replace("#{task_subject}",String.valueOf(t.getTaskSubject()));
 		sql  = sql.replace("#{product_id}",String.valueOf(t.getProductId()));
 		sql  = sql.replace("#{task_source}",String.valueOf(t.getTaskSource()));
 		sql  = sql.replace("#{approval_amt}",String.valueOf(t.getApprovalAmt()));
 		sql  = sql.replace("#{approval_interest_rate}",String.valueOf(t.getApprovalInterestRate()));
 		sql  = sql.replace("#{approval_term}",String.valueOf(t.getApprovalTerm()));
 		sql  = sql.replace("#{approval_term_unit}",String.valueOf(t.getApprovalTermUnit()));
 		sql  = sql.replace("#{start_time}",String.valueOf(t.getStartTime()));
 		sql  = sql.replace("#{end_time}",String.valueOf(t.getEndTime()));
 		sql  = sql.replace("#{approval_flag}",String.valueOf(t.getApprovalFlag()));
 		sql  = sql.replace("#{approval_user_id}",String.valueOf(t.getApprovalUserId()));
 		sql  = sql.replace("#{approval_date}",String.valueOf(t.getApprovalDate()));
 		sql  = sql.replace("#{approval_opinion}",String.valueOf(t.getApprovalOpinion()));
 		sql  = sql.replace("#{is_notic}",String.valueOf(t.getIsNotic()));
 		sql  = sql.replace("#{notic_content}",String.valueOf(t.getNoticContent()));
 		sql  = sql.replace("#{from_user_id}",String.valueOf(t.getFromUserId()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	/**
	 * 执行JAVA增强
	 */
 	private void executeJavaExtend(String cgJavaType,String cgJavaValue,Map<String,Object> data) throws Exception {
 		if(StringUtil.isNotEmpty(cgJavaValue)){
			Object obj = null;
			try {
				if("class".equals(cgJavaType)){
					//因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if(obj instanceof CgformEnhanceJavaInter){
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					javaInter.execute(data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}

	@Override
	public int findTaskCount(String approveUserId, String sts) {
		String sql = "select count(1) from zml_loan_wf_task where approval_user_id='"+approveUserId+"'";
		if(sts != null){
			sql +=  " and status='"+sts+"'";
		}
		Long count = getCountForJdbc(sql);
		if(count != null)
			return count.intValue();
		else 
			return 0;
	}

	@Override
	public void doApproveRepay(ZmlLoanWfTaskEntity zmlLoanWfTask) throws Exception {
		String bizId = zmlLoanWfTask.getBizId();
		ZmlLoanWfTaskEntity t = this.getEntity(ZmlLoanWfTaskEntity.class, zmlLoanWfTask.getId());
 		TSUser user = ResourceUtil.getSessionUserName();
		zmlLoanWfTask.setApprovalUserId(user.getId());
		zmlLoanWfTask.setUpdateDate(new Date());
		zmlLoanWfTask.setEndTime(new Date());
		zmlLoanWfTask.setStatus(LoanTaskSts.DONE.getStatusValue());
		MyBeanUtils.copyBeanNotNull2Bean(zmlLoanWfTask, t);
		super.saveOrUpdate(t);
		//查询还款记录
		ZmlLoanRepayRecordEntity repayRecord = this.getEntity(ZmlLoanRepayRecordEntity.class, bizId);
		//查询合同
		ZmlLoanContractEntity contract = this.getEntity(ZmlLoanContractEntity.class, repayRecord.getContractId());
		if(ApprovalFlag.APPROVE_PAST.getStatusValue().equals(zmlLoanWfTask.getApprovalFlag())){
			//修改还款记录为 还款拒绝
			repayRecord.setApprovalDate(new Date());
			repayRecord.setApprovalFlag(ApprovalFlag.APPROVE_PAST.getStatusValue());
			repayRecord.setApprovalUserId(user.getId());
			repayRecord.setApproveRepayAmt(zmlLoanWfTask.getApprovalAmt());
			repayRecord.setApprovalOpinion(zmlLoanWfTask.getApprovalOpinion());
			repayRecord.setStatus(RepayStatus.SUCC.getStatusValue());
			//修改合同为还款之前的状态
			contract.setStatus(contract.getRepayStatus());
			contract.setRepayStatus("");
			contract.setUpdateDate(new Date());
		}else{
			//还款顺序按照 ：先本金，二利息，三罚息，五滞纳金 ，四其他(如果有多还，记录在其他中同时追加还款备注)
			//本次实还金额
			BigDecimal repayAmt = zmlLoanWfTask.getApprovalAmt();
			//修改还款记录为 还款拒绝
			repayRecord.setApprovalDate(new Date());
			repayRecord.setApprovalFlag(ApprovalFlag.APPROVE_PAST.getStatusValue());
			repayRecord.setApprovalUserId(user.getId());
			repayRecord.setApproveRepayAmt(zmlLoanWfTask.getApprovalAmt());
			repayRecord.setApprovalOpinion(zmlLoanWfTask.getApprovalOpinion());
			repayRecord.setStatus(RepayStatus.APPROVE_REJECT.getStatusValue());
			//查询还款计划
			//Map<String, Object> planMap = findContractNoRepayPlan(contract.getId());
			ZmlLoanRepayPlanDetailEntity repayPlan = this.getEntity(ZmlLoanRepayPlanDetailEntity.class, repayRecord.getRepayPlanDetailId());
			//当期应还合计
			BigDecimal profitRepaySum = repayPlan.getProfitRepaySum();
			//下面是按照只有一期还款计划计算的
			//足够还完本期的
			double overAmt = BigDemicalUtil.sub(repayAmt.doubleValue(), profitRepaySum.doubleValue());
			if(overAmt >= 0.00){
				//将还款计划的实还 + 对应 应还金额
				repayPlan.setRepayPrincipal(repayPlan.getRepayPrincipal().add(repayPlan.getProfitPrincipal()));//当期实还本金
				repayPlan.setRepayInterest(repayPlan.getRepayInterest().add(repayPlan.getProfitInterest()));//当期实还利息
				repayPlan.setRepayPenalty(repayPlan.getRepayPenalty().add(repayPlan.getProfitPenalty()));//当期实还罚息
				repayPlan.setRepayNonpayment(repayPlan.getRepayNonpayment().add(repayPlan.getProfitNonpayment()));//当期实还滞纳金
				//判断是否有多还 
				if(overAmt > 0.00){
					repayPlan.setRepayOtherFee(repayPlan.getRepayOtherFee().add(new BigDecimal(overAmt)));//当期实还其他费用
					String remarks = repayPlan.getRemarks() + "\n" + "客户于"+ DateUtil.formatDateToStr(DateUtil.FORMAT_YYYY_MM_DD, repayRecord.getRepayTime()) + "还款多还：" + overAmt + "元。";
					repayPlan.setRemarks(remarks);
				}else{
					repayPlan.setRepayOtherFee(repayPlan.getRepayOtherFee().add(repayPlan.getProfitOtherFee()));//当期实还其他费用
				}
				repayPlan.setRepaySum(repayPlan.getRepaySum().add(repayAmt));
				//将还款计划的应还设置为0
				repayPlan.setProfitPrincipal(BigDemicalUtil.bd_zero);
				repayPlan.setProfitInterest(BigDemicalUtil.bd_zero);
				repayPlan.setProfitPenalty(BigDemicalUtil.bd_zero);
				repayPlan.setProfitNonpayment(BigDemicalUtil.bd_zero);
				repayPlan.setProfitOtherFee(BigDemicalUtil.bd_zero);
				repayPlan.setProfitRepaySum(BigDemicalUtil.bd_zero);
				//判断合同之前状态，
				if(ContractStatus.NORMAL.getStatusValue().equals(contract.getRepayStatus())){
					repayPlan.setStatus(RepayPlanDetailStatus.REPAY.getStatusValue());
				}else {
					repayPlan.setStatus(RepayPlanDetailStatus.OVERDUE_REPAY.getStatusValue());
				}
				//修改还款记录表状态
				repayRecord.setStatus(RepayStatus.SUCC.getStatusValue());
				//修改合同
				contract.setContractBalance(contract.getContractBalance().subtract(repayPlan.getProfitPrincipal()));//合同余额 - 当前应还本机
				contract.setImposeInterest(contract.getImposeInterest().add(repayPlan.getProfitPenalty()));
				contract.setPenalty(contract.getPenalty().add(repayPlan.getProfitNonpayment()));
				contract.setStatus(ContractStatus.CLOSED.getStatusValue());
				contract.setRepayStatus("");
			}else{//不足够本还完期的
				//将还款计划的实还 + 对应 应还金额
				//判断本金
				double principal = BigDemicalUtil.sub(repayAmt.doubleValue(), repayPlan.getProfitPrincipal().doubleValue());
				if(principal >= 0.00){
					repayPlan.setRepayPrincipal(repayPlan.getRepayPrincipal().add(repayPlan.getProfitPrincipal()));//当期实还本金
					//判断利息
					double interest = BigDemicalUtil.sub(principal, repayPlan.getProfitInterest().doubleValue());
					if(interest >= 0.00){
						repayPlan.setRepayInterest(repayPlan.getRepayInterest().add(repayPlan.getProfitInterest()));//当期实还利息
						//判断罚息
						double penalty = BigDemicalUtil.sub(interest, repayPlan.getProfitPenalty().doubleValue());
						if(penalty >= 0.00){
							repayPlan.setRepayPenalty(repayPlan.getRepayPenalty().add(repayPlan.getRepayPenalty()));//当期实还罚息
							//判断滞纳金
							double nonpayment =  BigDemicalUtil.sub(penalty, repayPlan.getProfitNonpayment().doubleValue());
							if(nonpayment >= 0.00){
								repayPlan.setRepayNonpayment(repayPlan.getRepayNonpayment().add(repayPlan.getProfitNonpayment()));//当期实还滞纳金
								//判断其他
								double other = BigDemicalUtil.sub(penalty, repayPlan.getProfitOtherFee().doubleValue());
								if(other > 0.00){
									repayPlan.setRepayOtherFee(repayPlan.getRepayOtherFee().add(new BigDecimal(penalty)));//当期实还其他费用
									String remarks = repayPlan.getRemarks() + "\n" + "客户于"+ DateUtil.formatDateToStr(DateUtil.FORMAT_YYYY_MM_DD, repayRecord.getRepayTime()) + "还款多还：" + other + "元。";
									repayPlan.setRemarks(remarks);
								}else if(other == 0.00){
									repayPlan.setRepayOtherFee(repayPlan.getRepayOtherFee().add(repayPlan.getProfitOtherFee()));//当期实还其他费用
								}else{
									repayPlan.setRepayOtherFee(repayPlan.getRepayOtherFee().add(new BigDecimal(penalty)));//当期实还其他费用
								}
							}else{
								repayPlan.setRepayNonpayment(repayPlan.getRepayNonpayment().add(new BigDecimal(nonpayment)));//当期实还滞纳金
							}
						}else{
							repayPlan.setRepayPenalty(repayPlan.getRepayPenalty().add(new BigDecimal(interest)));//当期实还罚息
						}
					}else{
						repayPlan.setRepayInterest(repayPlan.getRepayInterest().add(new BigDecimal(principal)));//当期实还利息
					}
				}else{
					repayPlan.setRepayPrincipal(repayPlan.getRepayPrincipal().add(repayAmt));//当期实还本金
				}
				repayPlan.setRepaySum(repayPlan.getRepaySum().add(repayAmt));
				//将还款计划的应还设置为0
				repayPlan.setProfitPrincipal(BigDemicalUtil.bd_zero);
				repayPlan.setProfitInterest(BigDemicalUtil.bd_zero);
				repayPlan.setProfitPenalty(BigDemicalUtil.bd_zero);
				repayPlan.setProfitNonpayment(BigDemicalUtil.bd_zero);
				repayPlan.setProfitOtherFee(BigDemicalUtil.bd_zero);
				repayPlan.setProfitRepaySum(BigDemicalUtil.bd_zero);
				//判断合同之前状态，
				if(ContractStatus.NORMAL.getStatusValue().equals(contract.getRepayStatus())){
					repayPlan.setStatus(RepayPlanDetailStatus.REPAY.getStatusValue());
				}else {
					repayPlan.setStatus(RepayPlanDetailStatus.OVERDUE_REPAY.getStatusValue());
				}
				//修改还款记录表状态
				repayRecord.setStatus(RepayStatus.SUCC.getStatusValue());
				//修改合同
				contract.setContractBalance(contract.getContractBalance().subtract(repayPlan.getProfitPrincipal()));//合同余额 - 当前应还本机
				contract.setImposeInterest(contract.getImposeInterest().add(repayPlan.getProfitPenalty()));
				contract.setPenalty(contract.getPenalty().add(repayPlan.getProfitNonpayment()));
				contract.setStatus(ContractStatus.CLOSED.getStatusValue());
				contract.setRepayStatus("");
				repayPlan.setStatus(RepayPlanDetailStatus.PART_REPAY.getStatusValue());
			}
			repayPlan.setUpdateDate(new Date());
			//修改还款计划
			this.saveOrUpdate(repayPlan);
		}
		contract.setUpdateDate(new Date());
		repayRecord.setUpdateDate(new Date());
		this.save(repayRecord);
		this.save(contract);
	}
	
	public Map<String, Object> findContractNoRepayPlan(String contractId) {
		String sqlWhere = " contract_id = '"+ contractId +"'";
		String sql = "select id,create_date createDate,update_date updateDate,version version,user_id userId,contract_id contractId,profit_period profitPeriod,profit_start_date profitStartDate,profit_end_date profitEndDate,profit_principal profitPrincipal,profit_interest profitInterest,profit_penalty profitPenalty,profit_nonpayment profitNonpayment,profit_other_fee profitOtherFee,profit_repay_sum profitRepaySum,repay_principal repayPrincipal,repay_interest repayInterest,repay_penalty repayPenalty "
                    + " ,repay_nonpayment repayNonpayment,repay_other_fee repayOtherFee,repay_sum repaySum,last_repay_date lastRepayDate,end_current_principalbalance endCurrentPrincipalbalance,rate rate,overdue_rate overdueRate,overdue_days overdueDays,status,adjustdate adjustdate,recently_repay_time recentlyRepayTime,remarks "
		            + " from zml_loan_repay_plan_detail t ";
		
		//查询未还 状态
		sqlWhere += " and status !='1' ";
		if (!sqlWhere.isEmpty()) {
			sql += " where" + sqlWhere;
		}
		sql += " ORDER BY profit_period asc limit 1";
		Map<String, Object> rsMap = findOneForJdbc(sql, null);
		return rsMap;
	}
}