package com.zml.user_msg.service;
import com.zml.user_msg.entity.CfUserMessageEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface CfUserMessageServiceI extends CommonService{
	
 	public void delete(CfUserMessageEntity entity) throws Exception;
 	
 	public Serializable save(CfUserMessageEntity entity) throws Exception;
 	
 	public void saveOrUpdate(CfUserMessageEntity entity) throws Exception;
 	
}
