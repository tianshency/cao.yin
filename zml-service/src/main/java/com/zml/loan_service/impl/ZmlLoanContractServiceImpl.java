package com.zml.loan_service.impl;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.core.common.exception.BusinessException;
import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.oConvertUtils;
import com.jce.framework.web.system.pojo.base.TSUser;
import com.zml.base.loan.entity.ZmlLoanContractDocumentEntity;
import com.zml.base.loan.entity.ZmlLoanContractEntity;
import com.zml.base.loan.entity.ZmlLoanRepayPlanDetailEntity;
import com.zml.base.loan.entity.ZmlLoanWfTaskEntity;
import com.zml.common.LoanConstant;
import com.zml.enums.loan.ApprovalFlag;
import com.zml.enums.loan.ContractStatus;
import com.zml.enums.loan.FiveClassificationSts;
import com.zml.enums.loan.RepayPlanDetailStatus;
import com.zml.loan_service.ZmlLoanContractServiceI;
import com.zml.loan_tools.common.model.InterestCalCulateForm;
import com.zml.loan_tools.common.model.InterestVO;
import com.zml.loan_tools.common.utils.CalcUtil;
import com.zml.util.DateUtil;


@Service("zmlLoanContractService")
@Transactional
public class ZmlLoanContractServiceImpl extends CommonServiceImpl implements ZmlLoanContractServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((ZmlLoanContractEntity)entity);
 	}
	
	public void addMain(ZmlLoanContractEntity zmlLoanContract,
        List<ZmlLoanContractDocumentEntity> zmlLoanContractDocumentList){
		//保存主信息
		this.save(zmlLoanContract);
		
		/**保存-合同文档*/
		if(zmlLoanContractDocumentList != null){
			for(ZmlLoanContractDocumentEntity zmlLoanContractDocument:zmlLoanContractDocumentList){
				//外键设置
				zmlLoanContractDocument.setContractId(zmlLoanContract.getId());
				this.save(zmlLoanContractDocument);
			}
		}
		
		//执行新增操作配置的sql增强
		this.doAddSql(zmlLoanContract);
	}

	
	public void updateMain(ZmlLoanContractEntity zmlLoanContract,
	        List<ZmlLoanContractDocumentEntity> zmlLoanContractDocumentList) {
		//保存主表信息
		this.saveOrUpdate(zmlLoanContract);
		//===================================================================================
		//获取参数
		Object id0 = zmlLoanContract.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-合同文档
	    String hql0 = "from ZmlLoanContractDocumentEntity where 1 = 1 AND cONTRACT_ID = ? ";
	    List<ZmlLoanContractDocumentEntity> zmlLoanContractDocumentOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-合同文档
		if(zmlLoanContractDocumentList!=null&&zmlLoanContractDocumentList.size()>0){
		for(ZmlLoanContractDocumentEntity oldE:zmlLoanContractDocumentOldList){
			boolean isUpdate = false;
				for(ZmlLoanContractDocumentEntity sendE:zmlLoanContractDocumentList){
					//需要更新的明细数据-合同文档
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-合同文档
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-合同文档
			for(ZmlLoanContractDocumentEntity zmlLoanContractDocument:zmlLoanContractDocumentList){
				if(oConvertUtils.isEmpty(zmlLoanContractDocument.getId())){
					//外键设置
					zmlLoanContractDocument.setContractId(zmlLoanContract.getId());
					this.save(zmlLoanContractDocument);
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(zmlLoanContract);
	}

	
	public void delMain(ZmlLoanContractEntity zmlLoanContract) {
		//删除主表信息
		this.delete(zmlLoanContract);
		//===================================================================================
		//获取参数
		Object id0 = zmlLoanContract.getId();
		//===================================================================================
		//删除-合同文档
	    String hql0 = "from ZmlLoanContractDocumentEntity where 1 = 1 AND cONTRACT_ID = ? ";
	    List<ZmlLoanContractDocumentEntity> zmlLoanContractDocumentOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(zmlLoanContractDocumentOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ZmlLoanContractEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ZmlLoanContractEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ZmlLoanContractEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,ZmlLoanContractEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{version}",String.valueOf(t.getVersion()));
 		sql  = sql.replace("#{user_id}",String.valueOf(t.getUserId()));
 		sql  = sql.replace("#{appl_id}",String.valueOf(t.getApplId()));
 		sql  = sql.replace("#{contract_no}",String.valueOf(t.getContractNo()));
 		sql  = sql.replace("#{product_id}",String.valueOf(t.getProductId()));
 		sql  = sql.replace("#{credit_amt}",String.valueOf(t.getCreditAmt()));
 		sql  = sql.replace("#{contract_amt}",String.valueOf(t.getContractAmt()));
 		sql  = sql.replace("#{contract_balance}",String.valueOf(t.getContractBalance()));
 		sql  = sql.replace("#{fee}",String.valueOf(t.getFee()));
 		sql  = sql.replace("#{intermediaries}",String.valueOf(t.getIntermediaries()));
 		sql  = sql.replace("#{lender}",String.valueOf(t.getLender()));
 		sql  = sql.replace("#{interest_rate}",String.valueOf(t.getInterestRate()));
 		sql  = sql.replace("#{penalty_rate}",String.valueOf(t.getPenaltyRate()));
 		sql  = sql.replace("#{contract_path}",String.valueOf(t.getContractPath()));
 		sql  = sql.replace("#{repayment}",String.valueOf(t.getRepayment()));
 		sql  = sql.replace("#{start_time}",String.valueOf(t.getStartTime()));
 		sql  = sql.replace("#{end_time}",String.valueOf(t.getEndTime()));
 		sql  = sql.replace("#{term}",String.valueOf(t.getTerm()));
 		sql  = sql.replace("#{term_unit}",String.valueOf(t.getTermUnit()));
 		sql  = sql.replace("#{over_time}",String.valueOf(t.getOverTime()));
 		sql  = sql.replace("#{over_days}",String.valueOf(t.getOverDays()));
 		sql  = sql.replace("#{impose_interest}",String.valueOf(t.getImposeInterest()));
 		sql  = sql.replace("#{penalty}",String.valueOf(t.getPenalty()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{repay_status}",String.valueOf(t.getRepayStatus()));
 		sql  = sql.replace("#{loan_frequency}",String.valueOf(t.getLoanFrequency()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}

	@Override
	public void createContract(ZmlLoanContractEntity zmlLoanContract,
			List<ZmlLoanContractDocumentEntity> zmlLoanContractDocumentList) {
		zmlLoanContract.setCreateDate(new Date());
		
		//保存主信息
		this.save(zmlLoanContract);
	
		/**保存-合同文档*/
		for(ZmlLoanContractDocumentEntity zmlLoanContractDocument:zmlLoanContractDocumentList){
			//外键设置
			zmlLoanContractDocument.setContractId(zmlLoanContract.getId());
			this.save(zmlLoanContractDocument);
		}
		//执行新增操作配置的sql增强
		this.doAddSql(zmlLoanContract);
	}

	@Override
	public Map<String, Object> findMyLoanStatistics(String userId) {
		String sqlWhere = " user_id = '"+ userId +"'";
		String sql = "select count(1) contractCount,sum(contract_amt) totalContractAmt, sum(contract_balance) totalContractBalance "
		            + "from zml_loan_contract t ";
		if (!sqlWhere.isEmpty()) {
			sql += " where" + sqlWhere;
		}
		sql += " group by user_id ";
		Map<String, Object> map = findOneForJdbc(sql, null);
		return map;
	}

	@Override
	public List<Map<String, Object>> findMyLoanList(String userId , int pagSize, int pag) {
		String sqlWhere = " user_id = '"+ userId +"'";
		String sql = "select t.id contractId,t.contract_no contractNo,t.contract_amt contractAmt, t.contract_balance contractBalance,t.status, "
                    + "t.start_time startTime, t.end_time endTime, t.over_days overDays "
		            + "from zml_loan_contract t ";
		if (!sqlWhere.isEmpty()) {
			sql += " where" + sqlWhere;
		}
		sql += " ORDER BY t.create_date desc";
		List<Map<String, Object>> mapList = findForJdbc(sql, null);
		return mapList;
	}

	@Override
	public Map<String, Object> findContractByApplyId(String applyId) {
		String sqlWhere = " appl_id = '"+ applyId +"'";
		String sql = "select id, contract_no contractNo, contract_amt contractAmt,contract_balance contractBalance, status,start_time startTime,end_time endTime "
		            + ", term, term_unit termUnit,pay_day payDay from zml_loan_contract t ";
		if (!sqlWhere.isEmpty()) {
			sql += " where" + sqlWhere;
		}
		Map<String, Object> map = findOneForJdbc(sql, null);
		ZmlLoanContractEntity contract = new ZmlLoanContractEntity();
		try {
			MyBeanUtils.copyMap2Bean(contract, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public void doApprove(ZmlLoanContractEntity zmlLoanContract, String approveUserId, String wfId,
			List<ZmlLoanContractDocumentEntity> zmlLoanContractDocumentList) throws Exception {
		zmlLoanContract.setOperatorId(approveUserId);
		zmlLoanContract.setApprovalUserId(approveUserId);
		zmlLoanContract.setApprovalDate(new Date());
		//zmlLoanContract.setApprovalFlag(ApprovalFlag.NO_APPROVE.getStatusValue());
		zmlLoanContract.setUpdateDate(new Date());
		
		ZmlLoanWfTaskEntity wfTask = getEntity(ZmlLoanWfTaskEntity.class, wfId);
		wfTask.setApprovalFlag(zmlLoanContract.getApprovalFlag());
		wfTask.setApprovalDate(new Date());
		wfTask.setApprovalOpinion(zmlLoanContract.getApprovalOpinion());
		wfTask.setApprovalUserId(approveUserId);
		if(ApprovalFlag.APPROVE_PAST.getStatusValue().equals(zmlLoanContract.getApprovalFlag())){
			/*//查询最终审判额度
			ZmlLoanApproveEntity zmlLoanApproveEntity = systemService.getEntity(ZmlLoanApproveEntity.class, zmlLoanContract.getApplId());
			*/
			zmlLoanContract.setStatus(ContractStatus.NORMAL.getStatusValue());
			zmlLoanContract.setRepayStatus("0");
			zmlLoanContract.setFiveClassification(FiveClassificationSts.NORMAL.getStatusValue());
			zmlLoanContract.setContractBalance(zmlLoanContract.getContractAmt());
			//修改流程
			wfTask.setApprovalAmt(zmlLoanContract.getContractAmt());
			wfTask.setApprovalFee(zmlLoanContract.getFee());
			wfTask.setApprovalTerm(zmlLoanContract.getTerm());
			wfTask.setApprovalTermUnit(zmlLoanContract.getTermUnit());
			wfTask.setApprovalInterestRate(zmlLoanContract.getInterestRate());
			
			//生成还款计划
			InterestCalCulateForm interestForm  = new InterestCalCulateForm();
			interestForm.setLoanAmount(zmlLoanContract.getContractAmt()); // 贷款金额
			interestForm.setApplyTerm(zmlLoanContract.getTerm()); // 期限
			interestForm.setApplyTermUnit("" + zmlLoanContract.getTermUnit()); // 期限单位
			interestForm.setRepaymentNumber(0); // 还款周期月数
			interestForm.setRepayment(zmlLoanContract.getRepayment()); // // 还款方式
			interestForm.setRepaymentDate(LoanConstant.REPAY_DAY); // 还款日期
			interestForm.setLoanStartDate(zmlLoanContract.getPayDay()); // 贷款开始时间
			interestForm.setRate(zmlLoanContract.getInterestRate());
			List<InterestVO> planList = CalcUtil.calcRepayPlan(interestForm);
			Date endTime = null;
			if(planList != null && planList.size() > 0){
				Date currDt =  new Date();
				String userId = zmlLoanContract.getUserId();
				String contractId = zmlLoanContract.getId();
				BigDecimal rate = zmlLoanContract.getInterestRate();
				BigDecimal imposeInterest = zmlLoanContract.getImposeInterest(); 
				int count = planList.size();
				for (int i = 0; i < count; i++) {
					InterestVO plan = planList.get(i);
					ZmlLoanRepayPlanDetailEntity repayPlan = new ZmlLoanRepayPlanDetailEntity();
					repayPlan.setCreateDate(currDt);
					repayPlan.setUserId(userId);
					repayPlan.setContractId(contractId);
					repayPlan.setProfitPeriod(i+1);
					repayPlan.setProfitStartDate(plan.getStartDate());
					repayPlan.setProfitEndDate(plan.getEndDate());
					repayPlan.setProfitPrincipal(plan.getCurrentPricipal());
					repayPlan.setProfitInterest(plan.getCurrentInterest());
					repayPlan.setProfitRepaySum(plan.getCurrentPricipalInterest());
					repayPlan.setEndCurrentPrincipalbalance(plan.getEndCurrentPrincipalBalance());
					repayPlan.setRate(rate);
					repayPlan.setOverdueRate(imposeInterest);
					repayPlan.setStatus(RepayPlanDetailStatus.NO_REPAY.getStatusValue());
					this.save(repayPlan);
					if(i == count -1){
						endTime = plan.getEndDate();
					}
				}
			}else{
				throw new BusinessException("生成还款计划失败！");
			}
			zmlLoanContract.setStartTime(DateUtil.formatDateToStr(DateUtil.FORMAT_YYYY_MM_DD, zmlLoanContract.getPayDay()));
			zmlLoanContract.setEndTime(DateUtil.formatDateToStr(DateUtil.FORMAT_YYYY_MM_DD, endTime));
		}

		ZmlLoanContractEntity oldContract = getEntity(ZmlLoanContractEntity.class, zmlLoanContract.getId());
		
		MyBeanUtils.copyBeanNotNull2Bean(zmlLoanContract, oldContract);
		this.saveOrUpdate(wfTask);
		
		//保存主表信息
		this.saveOrUpdate(oldContract);
		//===================================================================================
		//获取参数
		Object id0 = zmlLoanContract.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-合同文档
	    String hql0 = "from ZmlLoanContractDocumentEntity where 1 = 1 AND cONTRACT_ID = ? ";
	    List<ZmlLoanContractDocumentEntity> zmlLoanContractDocumentOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-合同文档
		if(zmlLoanContractDocumentList!=null&&zmlLoanContractDocumentList.size()>0){
			for(ZmlLoanContractDocumentEntity oldE:zmlLoanContractDocumentOldList){
				oldE.setApprovalDate(new Date());
				oldE.setApprovalUserId(approveUserId);
				boolean isUpdate = false;
				for(ZmlLoanContractDocumentEntity sendE:zmlLoanContractDocumentList){
					//需要更新的明细数据-合同文档
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-合同文档
		    		super.delete(oldE);
	    		}
		    		
			}
			//3.持久化新增的数据-合同文档
			for(ZmlLoanContractDocumentEntity zmlLoanContractDocument:zmlLoanContractDocumentList){
				if(oConvertUtils.isEmpty(zmlLoanContractDocument.getId())){
					//外键设置
					zmlLoanContractDocument.setContractId(zmlLoanContract.getId());
					this.save(zmlLoanContractDocument);
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(zmlLoanContract);
	}

	@Override
	public void signedContract(ZmlLoanContractEntity zmlLoanContract) {
		// TODO Auto-generated method stub
		
	}
}