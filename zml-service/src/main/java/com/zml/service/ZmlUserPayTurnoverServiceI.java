package com.zml.service;
import com.zml.base.entity.ZmlUserPayTurnoverEntity;
import com.jce.framework.core.common.service.CommonService;

import java.io.Serializable;

public interface ZmlUserPayTurnoverServiceI extends CommonService{
	
 	public void delete(ZmlUserPayTurnoverEntity entity) throws Exception;
 	
 	public Serializable save(ZmlUserPayTurnoverEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlUserPayTurnoverEntity entity) throws Exception;
 	
}
