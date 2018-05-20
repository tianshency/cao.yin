package com.zml.service.impl;
import com.zml.service.ZmlUserServiceI;
import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.zml.base.entity.ZmlUserEntity;
import com.zml.base.loan.entity.ZmlLoanRepayRecordEntity;
import com.zml.enums.YesOrNo;
import com.zml.base.entity.ZmlUserAddressEntity;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

import com.jce.framework.core.common.exception.BusinessException;
import com.jce.framework.core.util.ApplicationContextUtil;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.MyClassLoader;
import com.jce.framework.core.util.PasswordUtil;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.core.util.oConvertUtils;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;
import java.io.Serializable;


@Service("zmlUserService")
@Transactional
public class ZmlUserServiceImpl extends CommonServiceImpl implements ZmlUserServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((ZmlUserEntity)entity);
 	}
 	
 	/*public void saveOrUpdate(ZmlUserEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}*/
 	
 	@Override
	public Map login(String userName, String userPwd) {
		Map rsMap = new Hashtable();	    
	    String password = PasswordUtil.encrypt(userName, userPwd, PasswordUtil.getStaticSalt());
		String query = "from ZmlUserEntity u where u.userName = :username and u.password=:passowrd";
		Query queryObject = getSession().createQuery(query);
		queryObject.setParameter("username", userName);
		queryObject.setParameter("passowrd", password);
		List<ZmlUserEntity> users = queryObject.list();

		if (users != null && users.size() == 1) {
			rsMap.put("code", "1");
			rsMap.put("value", users.get(0));
			return rsMap;
		} else if (users != null && users.size() > 1){
			rsMap.put("code", "2");
			return rsMap;
		}else{
			rsMap.put("code", "0");
			return rsMap;
		}
	}
	
	public void addMain(ZmlUserEntity zmlUser,
        List<ZmlUserAddressEntity> zmlUserAddressList){
		//保存主信息
		this.save(zmlUser);
	
		/**保存-用户地址*/
		if(zmlUserAddressList != null){
			for(ZmlUserAddressEntity zmlUserAddress:zmlUserAddressList){
				//外键设置
				zmlUserAddress.setUserId(zmlUser.getId());
				this.save(zmlUserAddress);
			}
		}
		//执行新增操作配置的sql增强
		this.doAddSql(zmlUser);
	}
	
	public void updateMain(ZmlUserEntity zmlUser,
	        List<ZmlUserAddressEntity> zmlUserAddressList) {
		//保存主表信息
		this.saveOrUpdate(zmlUser);
		//===================================================================================
		//获取参数
		Object id0 = zmlUser.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-用户地址
	    String hql0 = "from ZmlUserAddressEntity where 1 = 1 AND uSER_ID = ? ";
	    List<ZmlUserAddressEntity> zmlUserAddressOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-用户地址
		if(zmlUserAddressList!=null&&zmlUserAddressList.size()>0){
			for(ZmlUserAddressEntity oldE:zmlUserAddressOldList){
				boolean isUpdate = false;
				for(ZmlUserAddressEntity sendE:zmlUserAddressList){
					//需要更新的明细数据-用户地址
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-用户地址
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-用户地址
			for(ZmlUserAddressEntity zmlUserAddress:zmlUserAddressList){
				if(oConvertUtils.isEmpty(zmlUserAddress.getId())){
					//外键设置
					zmlUserAddress.setUserId(zmlUser.getId());
					this.save(zmlUserAddress);
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(zmlUser);
	}

	
	public void delMain(ZmlUserEntity zmlUser) {
		//删除主表信息
		this.delete(zmlUser);
		//===================================================================================
		//获取参数
		Object id0 = zmlUser.getId();
		//===================================================================================
		//删除-用户地址
	    String hql0 = "from ZmlUserAddressEntity where 1 = 1 AND uSER_ID = ? ";
	    List<ZmlUserAddressEntity> zmlUserAddressOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(zmlUserAddressOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ZmlUserEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ZmlUserEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ZmlUserEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,ZmlUserEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{version}",String.valueOf(t.getVersion()));
 		sql  = sql.replace("#{user_name}",String.valueOf(t.getUserName()));
 		sql  = sql.replace("#{nickname}",String.valueOf(t.getNickname()));
 		sql  = sql.replace("#{password}",String.valueOf(t.getPassword()));
 		sql  = sql.replace("#{phone}",String.valueOf(t.getPhone()));
 		sql  = sql.replace("#{real_name}",String.valueOf(t.getRealName()));
 		sql  = sql.replace("#{identification_number}",String.valueOf(t.getIdentificationNumber()));
 		sql  = sql.replace("#{age}",String.valueOf(t.getAge()));
 		sql  = sql.replace("#{sex}",String.valueOf(t.getSex()));
 		sql  = sql.replace("#{login_ip}",String.valueOf(t.getLoginIp()));
 		sql  = sql.replace("#{login_date}",String.valueOf(t.getLoginDate()));
 		sql  = sql.replace("#{type}",String.valueOf(t.getType()));
 		sql  = sql.replace("#{is_verified}",String.valueOf(t.getIsVerified()));
 		sql  = sql.replace("#{avatar}",String.valueOf(t.getAvatar()));
 		sql  = sql.replace("#{locked}",String.valueOf(t.getLocked()));
 		sql  = sql.replace("#{registration_agreement}",String.valueOf(t.getRegistrationAgreement()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	@Override
 	public void addUserAddress(ZmlUserAddressEntity userAddress){
		//如果设置为默认地址 将其他默认地址职位 非默认
		if("1".equals(userAddress.getIsDefault())){
			String sql = "update zml_user_address set is_default='0' where user_id=? and  is_default ='1'";
			executeSql(sql, userAddress.getUserId());
		}
		this.save(userAddress);
	}
	@Override
	public void updateUserAddress(ZmlUserAddressEntity userAddress) {
		//如果设置为默认地址 将其他默认地址职位 非默认
		if("1".equals(userAddress.getIsDefault())){
			String sql = "update zml_user_address set is_default='0' where user_id=? and  is_default ='1'";
			executeSql(sql, userAddress.getUserId());
		}
		this.saveOrUpdate(userAddress);
		
	}

	@Override
	public void delUserAddress(String addressId) {
		String sql = "delete from zml_user_address where id=?";
		executeSql(sql, addressId);
	}

	@Override
	public ZmlUserAddressEntity findUserAddressByUserId(String userId) {
		String hql0 = "from ZmlUserAddressEntity where 1 = 1 AND userId = :userId order by createDate desc";
    	Query query = getSession().createQuery(hql0);
    	query.setParameter("userId", userId);
    	List<ZmlUserAddressEntity> list = query.list();
    	if(list != null && list.size() > 0 ){
    		for (int i = 0; i < list.size(); i++) {
    			ZmlUserAddressEntity entity = list.get(i);
    			if(YesOrNo.YES.getStatusValue().equals(entity.getIsDefault())){
    				return entity;
    			}
			}
    		return list.get(0); 
    	}
		return null;
	}

	@Override
	public ZmlUserAddressEntity findUserAddressById(String id) {
		String hql0 = "from ZmlUserAddressEntity where 1 = 1 AND id = :id";
    	Query query = getSession().createQuery(hql0);
    	query.setParameter("id", id);
    	List<ZmlUserAddressEntity> list = query.list();
    	if(list != null && list.size() > 0 ){
    		return list.get(0); 
    	}
		return null;
	}

	@Override
	public ZmlUserEntity findUserByOpenId(String openId) {
		String hql0 = "from ZmlUserEntity where 1 = 1 AND openId = :openId";
    	Query query = getSession().createQuery(hql0);
    	query.setParameter("openId", openId);
    	List<ZmlUserEntity> list = query.list();
    	if(list != null && list.size() > 0 ){
    		return list.get(0); 
    	}
		return null;
	}
	
	public void addUserAddress(String openId, ZmlUserAddressEntity zmlUserAddress){
		ZmlUserEntity zmlUser = this.findUniqueByProperty(ZmlUserEntity.class, "openId", openId);
		/**保存-用户地址*/
		if(zmlUserAddress != null){
			//外键设置
			zmlUserAddress.setUserId(zmlUser.getId());
			zmlUserAddress.setOpenId(openId);
			this.save(zmlUserAddress);
		}
		//执行新增操作配置的sql增强
		this.doAddSql(zmlUser);
	}
	
}