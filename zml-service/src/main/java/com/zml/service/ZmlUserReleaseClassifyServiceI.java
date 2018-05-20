package com.zml.service;
import java.io.Serializable;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.entity.ZmlUserReleaseClassifyEntity;

public interface ZmlUserReleaseClassifyServiceI extends CommonService{
	
 	public void delete(ZmlUserReleaseClassifyEntity entity) throws Exception;
 	
 	public Serializable save(ZmlUserReleaseClassifyEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlUserReleaseClassifyEntity entity) throws Exception;
 	
}
