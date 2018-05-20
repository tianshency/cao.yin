package com.zml.loan_risk;
import java.io.Serializable;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.loan.entity.ZmlRiskModeConfigEntity;

public interface ZmlRiskModeConfigServiceI extends CommonService{
	
 	public void delete(ZmlRiskModeConfigEntity entity) throws Exception;
 	
 	public Serializable save(ZmlRiskModeConfigEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlRiskModeConfigEntity entity) throws Exception;
 	
}
