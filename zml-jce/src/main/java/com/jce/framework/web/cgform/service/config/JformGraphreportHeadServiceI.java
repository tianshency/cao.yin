package com.jce.framework.web.cgform.service.config;

import java.util.List;
import com.jce.framework.core.common.service.CommonService;
import com.jce.framework.web.cgform.entity.config.JformGraphreportHeadEntity;
import com.jce.framework.web.cgform.entity.config.JformGraphreportItemEntity;

import java.io.Serializable;

public interface JformGraphreportHeadServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(JformGraphreportHeadEntity jformGraphreportHead,
						List<JformGraphreportItemEntity> jformGraphreportItemList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(JformGraphreportHeadEntity jformGraphreportHead,
						   List<JformGraphreportItemEntity> jformGraphreportItemList);
	public void delMain(JformGraphreportHeadEntity jformGraphreportHead);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(JformGraphreportHeadEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(JformGraphreportHeadEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(JformGraphreportHeadEntity t);
}
