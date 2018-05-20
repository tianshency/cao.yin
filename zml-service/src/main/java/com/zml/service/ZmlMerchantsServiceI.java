package com.zml.service;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.common.service.CommonService;
import com.zml.base.entity.ZmlMerchantsEntity;

import net.sf.json.JSONObject;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface ZmlMerchantsServiceI extends CommonService{
	
 	public void delete(ZmlMerchantsEntity entity) throws Exception;
 	
 	public Serializable save(ZmlMerchantsEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlMerchantsEntity entity) throws Exception;
 	
 	//查询推荐商户列表
 	public List<Object> findRecommendList(Map param, int currPage, int pageSize);
 	
 	//按照参数查询商户列表
 	public List<Map<String, Object>> getDatagrid(ZmlMerchantsEntity pageObj, DataGrid dataGrid);
}
