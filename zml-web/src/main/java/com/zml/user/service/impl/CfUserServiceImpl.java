package com.zml.user.service.impl;
import com.zml.user.service.CfUserServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.zml.user.entity.CfUserEntity;
import com.zml.user.entity.CfUserAddressEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.pojo.base.TSUser;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;
import java.io.Serializable;


@Service("cfUserService")
@Transactional
public class CfUserServiceImpl extends CommonServiceImpl implements CfUserServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((CfUserEntity)entity);
 	}
	
	public void addMain(CfUserEntity cfUser,
	        List<CfUserAddressEntity> cfUserAddressList){
			//保存主信息
			this.save(cfUser);
		
			/**保存-用户地址*/
			for(CfUserAddressEntity cfUserAddress:cfUserAddressList){
				//外键设置
				cfUserAddress.setUserId(cfUser.getId());
				this.save(cfUserAddress);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(cfUser);
	}

	
	public void updateMain(CfUserEntity cfUser,
	        List<CfUserAddressEntity> cfUserAddressList) {
		//保存主表信息
		this.saveOrUpdate(cfUser);
		//===================================================================================
		//获取参数
		Object id0 = cfUser.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-用户地址
	    String hql0 = "from CfUserAddressEntity where 1 = 1 AND uSER_ID = ? ";
	    List<CfUserAddressEntity> cfUserAddressOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-用户地址
		if(cfUserAddressList!=null&&cfUserAddressList.size()>0){
		for(CfUserAddressEntity oldE:cfUserAddressOldList){
			boolean isUpdate = false;
				for(CfUserAddressEntity sendE:cfUserAddressList){
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
			for(CfUserAddressEntity cfUserAddress:cfUserAddressList){
				if(oConvertUtils.isEmpty(cfUserAddress.getId())){
					//外键设置
					cfUserAddress.setUserId(cfUser.getId());
					this.save(cfUserAddress);
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(cfUser);
	}

	
	public void delMain(CfUserEntity cfUser) {
		//删除主表信息
		this.delete(cfUser);
		//===================================================================================
		//获取参数
		Object id0 = cfUser.getId();
		//===================================================================================
		//删除-用户地址
	    String hql0 = "from CfUserAddressEntity where 1 = 1 AND uSER_ID = ? ";
	    List<CfUserAddressEntity> cfUserAddressOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(cfUserAddressOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(CfUserEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(CfUserEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(CfUserEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,CfUserEntity t){
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
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{nickname}",String.valueOf(t.getNickname()));
 		sql  = sql.replace("#{password}",String.valueOf(t.getPassword()));
 		sql  = sql.replace("#{phone}",String.valueOf(t.getPhone()));
 		sql  = sql.replace("#{login_ip}",String.valueOf(t.getLoginIp()));
 		sql  = sql.replace("#{login_date}",String.valueOf(t.getLoginDate()));
 		sql  = sql.replace("#{type}",String.valueOf(t.getType()));
 		sql  = sql.replace("#{locked}",String.valueOf(t.getLocked()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}

	@Override
	public Map login(String userName, String userPwd) {
		Map rsMap = new Hashtable();	    
	    String password = PasswordUtil.encrypt(userName, userPwd, PasswordUtil.getStaticSalt());
		String query = "from TSUser u where u.userName = :username and u.password=:passowrd";
		Query queryObject = getSession().createQuery(query);
		queryObject.setParameter("username", userName);
		queryObject.setParameter("passowrd", password);
		List<TSUser> users = queryObject.list();

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
}