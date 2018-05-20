package com.zml.commodity.service;
import com.zml.commodity.entity.CfCommodityClassifyEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface CfCommodityClassifyServiceI extends CommonService{
	
 	public void delete(CfCommodityClassifyEntity entity) throws Exception;
 	
 	public Serializable save(CfCommodityClassifyEntity entity) throws Exception;
 	
 	public void saveOrUpdate(CfCommodityClassifyEntity entity) throws Exception;
 	
}
