package com.zml.service;
import java.io.Serializable;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.entity.ZmlSampleEntity;

public interface ZmlSampleServiceI extends CommonService{
	
 	public void delete(ZmlSampleEntity entity) throws Exception;
 	
 	public Serializable save(ZmlSampleEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlSampleEntity entity) throws Exception;
 	
}
