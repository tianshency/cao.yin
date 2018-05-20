package com.zml.loan_risk;
import java.io.Serializable;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.loan.entity.ZmlRiskCheckResultEntity;

public interface ZmlRiskCheckResultServiceI extends CommonService{
	
 	public void delete(ZmlRiskCheckResultEntity entity) throws Exception;
 	
 	public Serializable save(ZmlRiskCheckResultEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlRiskCheckResultEntity entity) throws Exception;
 	
}
