package com.jce.framework.web.cgform.service.autoform;
import java.util.List;

import com.jce.framework.core.common.service.CommonService;
import com.jce.framework.web.cgform.entity.autoform.AutoFormDbEntity;
import com.jce.framework.web.cgform.entity.autoform.AutoFormDbFieldEntity;
import com.jce.framework.web.cgform.entity.autoform.AutoFormParamEntity;

public interface AutoFormDbServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(AutoFormDbEntity autoFormDb,
	        List<AutoFormDbFieldEntity> autoFormDbFieldList,List<AutoFormParamEntity> autoFormParamList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(AutoFormDbEntity autoFormDb,
	        List<AutoFormDbFieldEntity> autoFormDbFieldList,List<AutoFormParamEntity> autoFormParamList);
	public void delMain (AutoFormDbEntity autoFormDb);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(AutoFormDbEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(AutoFormDbEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(AutoFormDbEntity t);
 	
 	/**
 	 * 获取动态sql属性部分
 	 * @param sql
 	 * @return
 	 */
 	public List<String> getSqlFields(String sql);
 	
 	/**
 	 * 获取动态sql参数部分
 	 * @param sql
 	 * @return
 	 */
 	public List<String> getSqlParams(String sql);
 	
 	/**
 	 * 支持动态数据源查询
 	 */
 	public List<String> getField(String sql,String dbKey);
}
