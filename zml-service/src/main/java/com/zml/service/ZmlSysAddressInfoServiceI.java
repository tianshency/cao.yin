package com.zml.service;
import java.io.Serializable;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.entity.ZmlSysAddressInfoEntity;

public interface ZmlSysAddressInfoServiceI extends CommonService{
	
 	public void delete(ZmlSysAddressInfoEntity entity) throws Exception;
 	
 	public Serializable save(ZmlSysAddressInfoEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlSysAddressInfoEntity entity) throws Exception;
 	
}
