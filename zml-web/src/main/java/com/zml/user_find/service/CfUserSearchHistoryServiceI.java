package com.zml.user_find.service;
import com.zml.user_find.entity.CfUserSearchHistoryEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface CfUserSearchHistoryServiceI extends CommonService{
	
 	public void delete(CfUserSearchHistoryEntity entity) throws Exception;
 	
 	public Serializable save(CfUserSearchHistoryEntity entity) throws Exception;
 	
 	public void saveOrUpdate(CfUserSearchHistoryEntity entity) throws Exception;
 	
}
