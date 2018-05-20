package com.zml.service;
import com.zml.base.entity.ZmlUserSearchHistoryEntity;
import com.jce.framework.core.common.service.CommonService;

import java.io.Serializable;

public interface ZmlUserSearchHistoryServiceI extends CommonService{
	
 	public void delete(ZmlUserSearchHistoryEntity entity) throws Exception;
 	
 	public Serializable save(ZmlUserSearchHistoryEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlUserSearchHistoryEntity entity) throws Exception;
 	
}
