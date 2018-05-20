package com.zml.commodity.service;
import com.zml.commodity.entity.CfCommodityEntity;
import com.zml.commodity.entity.CfCommodityImgEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface CfCommodityServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(CfCommodityEntity cfCommodity,
	        List<CfCommodityImgEntity> cfCommodityImgList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(CfCommodityEntity cfCommodity,
	        List<CfCommodityImgEntity> cfCommodityImgList);
	public void delMain (CfCommodityEntity cfCommodity);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(CfCommodityEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(CfCommodityEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(CfCommodityEntity t);
}
