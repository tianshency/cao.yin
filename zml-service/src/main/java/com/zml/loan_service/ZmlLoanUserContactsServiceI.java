package com.zml.loan_service;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.loan.entity.ZmlLoanApplyDocumentEntity;
import com.zml.base.loan.entity.ZmlLoanUserContactsEntity;

public interface ZmlLoanUserContactsServiceI extends CommonService{
	
 	public void delete(ZmlLoanUserContactsEntity entity) throws Exception;
 	
 	public Serializable save(ZmlLoanUserContactsEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlLoanUserContactsEntity entity) throws Exception;
 	
 	//根据申请ID 查询 文档
    public List<ZmlLoanUserContactsEntity> findContactsByApplyId(String applyId);
}
