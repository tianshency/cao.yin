package com.zml.service;
import com.zml.base.entity.ZmlUserCartsEntity;

import net.sf.json.JSONObject;

import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface ZmlUserCartsServiceI extends CommonService{
	
 	public void delete(ZmlUserCartsEntity entity) throws Exception;
 	
 	public Serializable save(ZmlUserCartsEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlUserCartsEntity entity) throws Exception;
 	
 	//按照参数查询购物车列表
 	public List<Map<String, Object>> getDatagrid(ZmlUserCartsEntity pageObj, DataGrid dataGrid);
 	//根据用户ID查询订单数量
 	public Long findCartsCountByUserId(String userId);

 	//按照id数组查询购物车列表
 	public List<ZmlUserCartsEntity> findCartsByIds(String[] ids);
 	
 	//按照id数组查询购物车 含商品价格信息列表
 	public List<Map<String, Object>> findCartsAndCommodityByCartsIds(String[] ids);
 	
 	//按照id数组查询购物车 含商品更多信息列表
 	public List<Map<String, Object>> findCartsAndCommodityInfoByCartsIds(String[] ids);
}
