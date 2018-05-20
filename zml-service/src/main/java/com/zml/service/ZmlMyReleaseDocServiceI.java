package com.zml.service;
import java.io.Serializable;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.entity.ZmlUserReleaseDocEntity;

public interface ZmlMyReleaseDocServiceI extends CommonService{
	
 	public void delete(ZmlUserReleaseDocEntity entity) throws Exception;
 	
 	public Serializable save(ZmlUserReleaseDocEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlUserReleaseDocEntity entity) throws Exception;
 	
}
