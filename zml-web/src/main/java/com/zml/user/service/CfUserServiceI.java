package com.zml.user.service;
import com.zml.user.entity.CfUserEntity;
import com.zml.user.entity.CfUserAddressEntity;

import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface CfUserServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 */
	public void addMain(CfUserEntity cfUser,
	        List<CfUserAddressEntity> cfUserAddressList) ;
	/**
	 * 修改一对多
	 */
	public void updateMain(CfUserEntity cfUser,
	        List<CfUserAddressEntity> cfUserAddressList);
	public void delMain (CfUserEntity cfUser);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(CfUserEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(CfUserEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(CfUserEntity t);
 	
 	/**
	 * 用户登录
	 */
 	public Map login(String userName, String userPwd);
}
