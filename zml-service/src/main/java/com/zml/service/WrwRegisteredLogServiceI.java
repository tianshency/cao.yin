package com.zml.service;
import java.io.Serializable;
import java.util.Map;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.wrw.entity.WrwRegisteredLogEntity;

public interface WrwRegisteredLogServiceI extends CommonService{
	
 	public void delete(WrwRegisteredLogEntity entity) throws Exception;
 	
 	public Serializable save(WrwRegisteredLogEntity entity) throws Exception;
 	
 	public void saveOrUpdate(WrwRegisteredLogEntity entity) throws Exception;
 	//发送手机号
 	public boolean sendPhone(WrwRegisteredLogEntity entity, Map<String, String> param) throws Exception;
 	
 	//注册
 	public boolean doRegedit(Map<String, String> param) throws Exception;
 	
}
