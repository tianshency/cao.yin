package com.zml.loan_service;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.loan.entity.ZmlLoanApplyDocumentEntity;

public interface ZmlLoanApplyDocumentServiceI extends CommonService{
	
 	public void delete(ZmlLoanApplyDocumentEntity entity) throws Exception;
 	
 	public Serializable save(ZmlLoanApplyDocumentEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlLoanApplyDocumentEntity entity) throws Exception;
 	//根据申请ID、userID 查询 文档
 	public Map findDocByApplyUserId(String applyId, String userId);
 	
 	//根据申请ID 查询 文档
 	public List<ZmlLoanApplyDocumentEntity> findDocByApplyId(String applyId);
}
