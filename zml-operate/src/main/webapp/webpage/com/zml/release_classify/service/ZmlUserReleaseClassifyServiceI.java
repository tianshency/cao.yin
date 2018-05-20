package com.zml.release_classify.service;
import com.zml.release_classify.entity.ZmlUserReleaseClassifyEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface ZmlUserReleaseClassifyServiceI extends CommonService{
	
 	public void delete(ZmlUserReleaseClassifyEntity entity) throws Exception;
 	
 	public Serializable save(ZmlUserReleaseClassifyEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlUserReleaseClassifyEntity entity) throws Exception;
 	
}
