package com.zml.loan_service;
import java.io.Serializable;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.loan.entity.ZmlLoanOverdueDetailEntity;

public interface ZmlLoanOverdueDetailServiceI extends CommonService{
	
 	public void delete(ZmlLoanOverdueDetailEntity entity) throws Exception;
 	
 	public Serializable save(ZmlLoanOverdueDetailEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlLoanOverdueDetailEntity entity) throws Exception;
 	
}
