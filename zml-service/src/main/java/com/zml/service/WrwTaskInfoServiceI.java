package com.zml.service;
import java.util.List;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.wrw.entity.WrwTaskInfoEntity;
import com.zml.base.wrw.entity.WrwTaskUserRelationEntity;

public interface WrwTaskInfoServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(WrwTaskInfoEntity wrwTaskInfo,
	        List<WrwTaskUserRelationEntity> wrwTaskUserRelationList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(WrwTaskInfoEntity wrwTaskInfo,
	        List<WrwTaskUserRelationEntity> wrwTaskUserRelationList);
	public void delMain (WrwTaskInfoEntity wrwTaskInfo);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WrwTaskInfoEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WrwTaskInfoEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WrwTaskInfoEntity t);
}
