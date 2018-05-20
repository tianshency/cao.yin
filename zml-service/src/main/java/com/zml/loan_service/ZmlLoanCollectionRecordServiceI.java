package com.zml.loan_service;
import java.io.Serializable;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.loan.entity.ZmlLoanCollectionRecordEntity;

public interface ZmlLoanCollectionRecordServiceI extends CommonService{
	
 	public void delete(ZmlLoanCollectionRecordEntity entity) throws Exception;
 	
 	public Serializable save(ZmlLoanCollectionRecordEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlLoanCollectionRecordEntity entity) throws Exception;
 	
}
