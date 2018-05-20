package com.zml.service;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.common.service.CommonService;
import com.zml.base.entity.ZmlUserCollectionEntity;

public interface ZmlUserCollectionServiceI extends CommonService{
	
 	public void delete(ZmlUserCollectionEntity entity) throws Exception;
 	
 	public Serializable save(ZmlUserCollectionEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlUserCollectionEntity entity) throws Exception;
 	//查询分页
 	public List<Map<String, Object>> findDataByEntity(ZmlUserCollectionEntity entity, DataGrid dataGrid, String flag);
 	//查询用户收藏数量
	public Long findCountByUserId(String userId, String flag);

}
