package com.zml.order.service;
import com.zml.order.entity.ZmlOrderEntity;
import com.zml.order.entity.ZmlOrderCommodityEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

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
}
