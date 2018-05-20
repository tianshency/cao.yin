package com.zml.loan_service;
import java.io.Serializable;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.loan.entity.ZmlLoanLanInfoEntity;

public interface ZmlLoanLanInfoServiceI extends CommonService{
	
 	public void delete(ZmlLoanLanInfoEntity entity) throws Exception;
 	
 	public Serializable save(ZmlLoanLanInfoEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlLoanLanInfoEntity entity) throws Exception;
 	
}
