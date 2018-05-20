package com.zml.service;
import java.util.List;
import java.util.Map;

import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.common.service.CommonService;
import com.zml.base.entity.ZmlOrderCommodityEntity;
import com.zml.base.entity.ZmlOrderEntity;

public interface ZmlOrderServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(ZmlOrderEntity zmlOrder,
	        List<ZmlOrderCommodityEntity> zmlOrderCommodityList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ZmlOrderEntity zmlOrder,
	        List<ZmlOrderCommodityEntity> zmlOrderCommodityList);
	public void delMain (ZmlOrderEntity zmlOrder);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ZmlOrderEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ZmlOrderEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ZmlOrderEntity t);
 	//根据 订单实体查询 订单列表
 	public List<Map<String, Object>> getDatagrid(ZmlOrderEntity pageObj, DataGrid dataGrid);
 	//更新订单状态
 	public Integer updateOrderSts(String orderId, String sts);
 	
 	//根据用户ID、订单状态查询订单数量
 	public Map<String,String> findOrderCountByUserId(String userId, String status);
 	
 	//根据用户ID查询列表
    public List<Map<String, Object>> findOrderByUserId(String userId);
    
 	/**
	 *创建订单
	 */
	public Map<String, String> createOrder(String [] ids,ZmlOrderEntity zmlOrder,
	        List<ZmlOrderCommodityEntity> zmlOrderCommodityList) ;
}
