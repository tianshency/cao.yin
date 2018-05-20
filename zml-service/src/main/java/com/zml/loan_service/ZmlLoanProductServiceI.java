package com.zml.loan_service;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.loan.entity.ZmlLoanProductEntity;

public interface ZmlLoanProductServiceI extends CommonService{
	
 	public void delete(ZmlLoanProductEntity entity) throws Exception;
 	
 	public Serializable save(ZmlLoanProductEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlLoanProductEntity entity) throws Exception;
 	
 	//根据参数查询产品
 	public List<Map<String, Object>> findProduct(Map<String, String> param);
}
