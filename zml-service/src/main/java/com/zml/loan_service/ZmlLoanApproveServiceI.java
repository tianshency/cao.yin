package com.zml.loan_service;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.loan.entity.ZmlLoanApproveEntity;

public interface ZmlLoanApproveServiceI extends CommonService{
	
 	public void delete(ZmlLoanApproveEntity entity) throws Exception;
 	
 	public Serializable save(ZmlLoanApproveEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlLoanApproveEntity entity) throws Exception;
 	//根据申请ID 和状态查询 终审数据
 	List<Map<String, Object>> findByApplyId(String applyId, String sts);
}
