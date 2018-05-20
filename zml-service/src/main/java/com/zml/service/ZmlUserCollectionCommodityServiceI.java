package com.zml.service;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.common.service.CommonService;
import com.zml.base.entity.ZmlUserCollectionCommodityEntity;

public interface ZmlUserCollectionCommodityServiceI extends CommonService{
	
 	public void delete(ZmlUserCollectionCommodityEntity entity) throws Exception;
 	
 	public Serializable save(ZmlUserCollectionCommodityEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlUserCollectionCommodityEntity entity) throws Exception;
 	//查询分页
 	public List<Map<String, Object>> findDataByEntity(ZmlUserCollectionCommodityEntity entity, DataGrid dataGrid);
 	
 	
}
