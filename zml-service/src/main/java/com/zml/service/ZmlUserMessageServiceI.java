package com.zml.service;
import com.zml.base.entity.ZmlUserMessageEntity;
import com.jce.framework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface ZmlUserMessageServiceI extends CommonService{
	
 	public void delete(ZmlUserMessageEntity entity) throws Exception;
 	
 	public Serializable save(ZmlUserMessageEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlUserMessageEntity entity) throws Exception;
 	
 	//根据用户ID、 查询消息数量
 	public Map<String,String> findMessageCountByParam(String userId, String status);
 	
 	//根据用户ID查询列表
    public List<Map<String, Object>> findMessageByParam(String userId, String status);
}
