package com.zml.service;
import java.util.List;
import java.util.Map;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.entity.ZmlUserAddressEntity;
import com.zml.base.entity.ZmlUserEntity;

public interface ZmlUserServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(ZmlUserEntity zmlUser,
	        List<ZmlUserAddressEntity> zmlUserAddressList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ZmlUserEntity zmlUser,
	        List<ZmlUserAddressEntity> zmlUserAddressList);
	public void delMain (ZmlUserEntity zmlUser);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ZmlUserEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ZmlUserEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ZmlUserEntity t);
 	//登录
	public Map login(String userName, String userPwd);
	
	/**
	 * 添加用户地址
	 */
	public void addUserAddress(ZmlUserAddressEntity userAddress) ;
	
	/**
	 * 修改用户地址
	 */
	public void updateUserAddress(ZmlUserAddressEntity userAddress) ;
	/**
	 * 删除用户地址
	 */
	public void delUserAddress(String addressId) ;
	
	/**
	 * 根据用户ID查询地址、有默认地址返回默认，没有返回最新地址
	 */
	public ZmlUserAddressEntity findUserAddressByUserId(String userId) ;
	/**
	 * 根据ID查询地址
	 */
	public ZmlUserAddressEntity findUserAddressById(String id) ;
	
	/**
	 * 根据openID查询地址
	 */
	public ZmlUserEntity findUserByOpenId(String openId) ;
	/**
	 * 根据openId 插入用户地址
	 */
	public void addUserAddress(String openId, ZmlUserAddressEntity zmlUserAddress);
	
}
