package com.zml.service;
import com.zml.base.entity.ZmlPlatformOrderEntity;
import com.zml.base.entity.ZmlPlatformOrderCommodityEntity;
import com.zml.base.entity.ZmlPorderToCorderEntity;

import java.util.List;
import com.jce.framework.core.common.service.CommonService;
import java.io.Serializable;

public interface ZmlPlatformOrderServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(ZmlPlatformOrderEntity zmlPlatformOrder,
	        List<ZmlPlatformOrderCommodityEntity> zmlPlatformOrderCommodityList,List<ZmlPorderToCorderEntity> zmlPorderToCorderList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ZmlPlatformOrderEntity zmlPlatformOrder,
	        List<ZmlPlatformOrderCommodityEntity> zmlPlatformOrderCommodityList,List<ZmlPorderToCorderEntity> zmlPorderToCorderList);
	public void delMain (ZmlPlatformOrderEntity zmlPlatformOrder);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ZmlPlatformOrderEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ZmlPlatformOrderEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ZmlPlatformOrderEntity t);
}
