package com.zml.service;
import com.zml.base.entity.ZmlCommodityEntity;
import com.zml.base.entity.ZmlCommodityDocEntity;
import com.zml.base.entity.ZmlCommodityStandardEntity;

import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.common.service.CommonService;
import java.io.Serializable;

public interface ZmlCommodityServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(ZmlCommodityEntity zmlCommodity,
	        List<ZmlCommodityDocEntity> zmlCommodityDocList,List<ZmlCommodityStandardEntity> zmlCommodityStandardList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ZmlCommodityEntity zmlCommodity,
	        List<ZmlCommodityDocEntity> zmlCommodityDocList,List<ZmlCommodityStandardEntity> zmlCommodityStandardList);
	public void delMain (ZmlCommodityEntity zmlCommodity);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ZmlCommodityEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ZmlCommodityEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ZmlCommodityEntity t);
 	
 	//查询推荐商品列表
 	public List<Object> findRecommendList();
 	
 	//按照类型查询商品列表
 	public List<Object> findByClassifyList(String classifyId);
 	
 	public List<Map<String, Object>> getDatagrid(ZmlCommodityEntity pageObj, DataGrid dataGrid,Map<String,Object> orders);
 	
 	public List<Map<String, Object>> commodityStandardList(String commodityId);
}
