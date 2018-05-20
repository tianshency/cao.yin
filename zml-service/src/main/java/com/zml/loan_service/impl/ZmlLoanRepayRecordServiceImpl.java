package com.zml.loan_service.impl;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.jce.framework.core.util.ApplicationContextUtil;
import com.jce.framework.core.util.MyClassLoader;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.web.cgform.enhance.CgformEnhanceJavaInter;
import com.zml.base.loan.entity.ZmlLoanContractEntity;
import com.zml.base.loan.entity.ZmlLoanRepayRecordEntity;
import com.zml.base.loan.entity.ZmlLoanWfTaskEntity;
import com.zml.enums.loan.ApprovalFlag;
import com.zml.enums.loan.ContractStatus;
import com.zml.enums.loan.LoanTaskSts;
import com.zml.enums.loan.LoanTaskType;
import com.zml.enums.loan.LoanWFNode;
import com.zml.loan_service.ZmlLoanRepayRecordServiceI;

@Service("zmlLoanRepayRecordService")
@Transactional
public class ZmlLoanRepayRecordServiceImpl extends CommonServiceImpl implements ZmlLoanRepayRecordServiceI {

	
 	public void delete(ZmlLoanRepayRecordEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ZmlLoanRepayRecordEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ZmlLoanRepayRecordEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ZmlLoanRepayRecordEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ZmlLoanRepayRecordEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ZmlLoanRepayRecordEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(ZmlLoanRepayRecordEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_date", t.getCreateDate());
		map.put("update_date", t.getUpdateDate());
		map.put("version", t.getVersion());
		map.put("contract_id", t.getContractId());
		map.put("user_id", t.getUserId());
		map.put("repay_plan_detail_id", t.getRepayPlanDetailId());
		map.put("repay_time", t.getRepayTime());
		map.put("repay_principal", t.getRepayPrincipal());
		map.put("repay_interest", t.getRepayInterest());
		map.put("repay_penalty", t.getRepayPenalty());
		map.put("repay_nonpayment", t.getRepayNonpayment());
		map.put("repay_other_fee", t.getRepayOtherFee());
		map.put("repay_amt", t.getRepayAmt());
		map.put("repay_channel", t.getRepayChannel());
		map.put("repay_certificate", t.getRepayCertificate());
		map.put("status", t.getStatus());
		map.put("remarks", t.getRemarks());
		map.put("approval_flag", t.getApprovalFlag());
		map.put("approval_user_id", t.getApprovalUserId());
		map.put("approval_date", t.getApprovalDate());
		map.put("approval_opinion", t.getApprovalOpinion());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ZmlLoanRepayRecordEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{version}",String.valueOf(t.getVersion()));
 		sql  = sql.replace("#{contract_id}",String.valueOf(t.getContractId()));
 		sql  = sql.replace("#{user_id}",String.valueOf(t.getUserId()));
 		sql  = sql.replace("#{repay_plan_detail_id}",String.valueOf(t.getRepayPlanDetailId()));
 		sql  = sql.replace("#{repay_time}",String.valueOf(t.getRepayTime()));
 		sql  = sql.replace("#{repay_principal}",String.valueOf(t.getRepayPrincipal()));
 		sql  = sql.replace("#{repay_interest}",String.valueOf(t.getRepayInterest()));
 		sql  = sql.replace("#{repay_penalty}",String.valueOf(t.getRepayPenalty()));
 		sql  = sql.replace("#{repay_nonpayment}",String.valueOf(t.getRepayNonpayment()));
 		sql  = sql.replace("#{repay_other_fee}",String.valueOf(t.getRepayOtherFee()));
 		sql  = sql.replace("#{repay_amt}",String.valueOf(t.getRepayAmt()));
 		sql  = sql.replace("#{repay_channel}",String.valueOf(t.getRepayChannel()));
 		sql  = sql.replace("#{repay_certificate}",String.valueOf(t.getRepayCertificate()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{remarks}",String.valueOf(t.getRemarks()));
 		sql  = sql.replace("#{approval_flag}",String.valueOf(t.getApprovalFlag()));
 		sql  = sql.replace("#{approval_user_id}",String.valueOf(t.getApprovalUserId()));
 		sql  = sql.replace("#{approval_date}",String.valueOf(t.getApprovalDate()));
 		sql  = sql.replace("#{approval_opinion}",String.valueOf(t.getApprovalOpinion()));
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
	public Serializable doSave(String contractId, ZmlLoanRepayRecordEntity entity) throws Exception {
		Serializable t = super.save(entity);
		//修改合同状态为 还款审判
		ZmlLoanContractEntity contract = this.getEntity(ZmlLoanContractEntity.class, contractId);
		contract.setRepayStatus(contract.getStatus());
		contract.setStatus(ContractStatus.REPAY_APPROVE.getStatusValue());
		super.saveOrUpdate(contract);
		//增加还款 代办任务
		ZmlLoanWfTaskEntity task = new ZmlLoanWfTaskEntity();
		task.setUserId(contract.getUserId());
		task.setCreateDate(new Date());
		task.setBizId(contract.getId());
		task.setTaskType(LoanTaskType.REPAY.getStatusValue());
		task.setStatus(LoanTaskSts.PROCESSING.getStatusValue());
		task.setApprovalFlag(ApprovalFlag.NO_APPROVE.getStatusValue());
		task.setBpmStatus(LoanWFNode.REPAY_APPROVE.getStatusValue());
		task.setTaskSubject("还款审批");
		super.save(task);
		
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
	}
}