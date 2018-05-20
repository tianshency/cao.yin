package com.zml.banner.service;
import com.zml.banner.entity.CfBannerEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface CfBannerServiceI extends CommonService{
	
 	public void delete(CfBannerEntity entity) throws Exception;
 	
 	public Serializable save(CfBannerEntity entity) throws Exception;
 	
 	public void saveOrUpdate(CfBannerEntity entity) throws Exception;
 	
}
