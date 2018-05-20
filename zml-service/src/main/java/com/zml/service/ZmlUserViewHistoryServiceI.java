package com.zml.service;
import com.zml.base.entity.ZmlUserViewHistoryEntity;
import com.jce.framework.core.common.service.CommonService;

import java.io.Serializable;

public interface ZmlUserViewHistoryServiceI extends CommonService{
	
 	public void delete(ZmlUserViewHistoryEntity entity) throws Exception;
 	
 	public Serializable save(ZmlUserViewHistoryEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlUserViewHistoryEntity entity) throws Exception;
 	
}
