package com.zml.service;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.common.service.CommonService;
import com.zml.base.entity.ZmlUserCollectionMerchantsEntity;

public interface ZmlUserCollectionMerchantsServiceI extends CommonService{
	
 	public void delete(ZmlUserCollectionMerchantsEntity entity) throws Exception;
 	
 	public Serializable save(ZmlUserCollectionMerchantsEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlUserCollectionMerchantsEntity entity) throws Exception;
 	//查询分页
 	public List<Map<String, Object>> findDataByEntity(ZmlUserCollectionMerchantsEntity entity, DataGrid dataGrid);
 	 	
}
