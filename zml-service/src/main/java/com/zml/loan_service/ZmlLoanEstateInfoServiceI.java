package com.zml.loan_service;
import java.io.Serializable;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.loan.entity.ZmlLoanEstateInfoEntity;

public interface ZmlLoanEstateInfoServiceI extends CommonService{
	
 	public void delete(ZmlLoanEstateInfoEntity entity) throws Exception;
 	
 	public Serializable save(ZmlLoanEstateInfoEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlLoanEstateInfoEntity entity) throws Exception;
 	
}
