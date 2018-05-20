package com.zml.service;
import com.jce.framework.core.common.service.CommonService;
import com.zml.base.entity.ZmlTestEntity;

import java.io.Serializable;

public interface ZmlTestServiceI extends CommonService{
	
 	public void delete(ZmlTestEntity entity) throws Exception;
 	
 	public Serializable save(ZmlTestEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlTestEntity entity) throws Exception;
 	
}
