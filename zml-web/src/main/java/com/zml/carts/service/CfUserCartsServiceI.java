package com.zml.carts.service;
import com.zml.carts.entity.CfUserCartsEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface CfUserCartsServiceI extends CommonService{
	
 	public void delete(CfUserCartsEntity entity) throws Exception;
 	
 	public Serializable save(CfUserCartsEntity entity) throws Exception;
 	
 	public void saveOrUpdate(CfUserCartsEntity entity) throws Exception;
 	
}
