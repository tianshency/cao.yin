package com.zml.loan_service;
import java.io.Serializable;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.loan.entity.ZmlUserRecordEntity;

public interface ZmlUserRecordServiceI extends CommonService{
	
 	public void delete(ZmlUserRecordEntity entity) throws Exception;
 	
 	public Serializable save(ZmlUserRecordEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlUserRecordEntity entity) throws Exception;
 	
}
