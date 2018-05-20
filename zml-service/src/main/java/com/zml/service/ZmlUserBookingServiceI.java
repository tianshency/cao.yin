package com.zml.service;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.entity.ZmlUserBookingEntity;

public interface ZmlUserBookingServiceI extends CommonService{
	
 	public void delete(ZmlUserBookingEntity entity) throws Exception;
 	
 	public Serializable save(ZmlUserBookingEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlUserBookingEntity entity) throws Exception;
 	
 	//根据用户ID查询订购数量
 	public Long findBookingCountByUserId(String userId);
 	//根据用户ID查询订购列表
    public List<Map<String, Object>> findBookingByUserId(String userId);
 	
 	
}
