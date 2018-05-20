package com.zml.loan_service;
import java.io.Serializable;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.loan.entity.ZmlLoanRepayRecordEntity;
import com.zml.base.loan.entity.ZmlLoanWfTaskEntity;

public interface ZmlLoanWfTaskServiceI extends CommonService{
	
 	public void delete(ZmlLoanWfTaskEntity entity) throws Exception;
 	
 	public Serializable save(ZmlLoanWfTaskEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlLoanWfTaskEntity entity) throws Exception;
 	//申请审批
 	public void doApplyApprove(ZmlLoanWfTaskEntity entity) throws Exception;
 	//根据审批人ID 和状态查询任务数量
 	public int findTaskCount(String approveUserId, String sts);
 	
 	//还款审批
 	public void doApproveRepay(ZmlLoanWfTaskEntity entity) throws Exception;
 	
}
