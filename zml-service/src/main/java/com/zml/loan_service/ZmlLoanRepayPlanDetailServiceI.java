package com.zml.loan_service;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.loan.entity.ZmlLoanRepayPlanDetailEntity;

public interface ZmlLoanRepayPlanDetailServiceI extends CommonService{
	
 	public void delete(ZmlLoanRepayPlanDetailEntity entity) throws Exception;
 	
 	public Serializable save(ZmlLoanRepayPlanDetailEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlLoanRepayPlanDetailEntity entity) throws Exception;
 	
 	//查询合同最近笔未还款 的还款计划 num=1 查最近一笔未还， num=-1 查询全部, num=0 查询所有未还
 	public Map<String, Object> findContractNoRepayPlan(String contractId, String num);
 	
 	//查询合同最近一笔未还款 的还款计划
 	public Map<String, Object> findContractNoRepayPlan(String contractId);
}
