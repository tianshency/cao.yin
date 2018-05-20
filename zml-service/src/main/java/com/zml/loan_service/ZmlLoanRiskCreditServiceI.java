package com.zml.loan_service;
import java.io.Serializable;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.loan.entity.ZmlLoanRiskCreditEntity;

public interface ZmlLoanRiskCreditServiceI extends CommonService{
	
 	public void delete(ZmlLoanRiskCreditEntity entity) throws Exception;
 	
 	public Serializable save(ZmlLoanRiskCreditEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlLoanRiskCreditEntity entity) throws Exception;
 	
}
