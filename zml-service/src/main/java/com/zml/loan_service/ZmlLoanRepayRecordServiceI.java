package com.zml.loan_service;
import java.io.Serializable;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.loan.entity.ZmlLoanRepayRecordEntity;

public interface ZmlLoanRepayRecordServiceI extends CommonService{
	
 	public void delete(ZmlLoanRepayRecordEntity entity) throws Exception;
 	
 	public Serializable save(ZmlLoanRepayRecordEntity entity) throws Exception;
 	public Serializable doSave(String contractId, ZmlLoanRepayRecordEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlLoanRepayRecordEntity entity) throws Exception;
 	
}
