package com.zml.loan_service;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.loan.entity.ZmlLoanApplicationEntity;

public interface ZmlLoanApplicationServiceI extends CommonService{
	
 	public void delete(ZmlLoanApplicationEntity entity) throws Exception;
 	
 	public Serializable save(ZmlLoanApplicationEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlLoanApplicationEntity entity) throws Exception;
 	
 	public void commitApply(ZmlLoanApplicationEntity entity, String approvalUserId) throws Exception;
 	
 	//根据用户ID查询列表
    public List<Map<String, Object>> findLoanApplicationByUserIdAndSts(String userId, String sts);
    
    
}
