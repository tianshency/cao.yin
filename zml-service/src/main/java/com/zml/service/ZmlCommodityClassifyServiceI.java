package com.zml.service;
import java.io.Serializable;
import java.util.List;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.entity.ZmlCommodityClassifyEntity;

public interface ZmlCommodityClassifyServiceI extends CommonService{
	
 	public void delete(ZmlCommodityClassifyEntity entity) throws Exception;
 	
 	public Serializable save(ZmlCommodityClassifyEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlCommodityClassifyEntity entity) throws Exception;
 	
 	//查询首页列表
 	public List<Object> findIndexList(int num);
}
