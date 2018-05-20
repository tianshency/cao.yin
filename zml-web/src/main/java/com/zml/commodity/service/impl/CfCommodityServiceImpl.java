package com.zml.commodity.service.impl;
import com.zml.commodity.service.CfCommodityServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.zml.commodity.entity.CfCommodityEntity;
import com.zml.commodity.entity.CfCommodityImgEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import java.util.ArrayList;
import java.util.UUID;
import java.io.Serializable;


@Service("cfCommodityService")
@Transactional
public class CfCommodityServiceImpl extends CommonServiceImpl implements CfCommodityServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((CfCommodityEntity)entity);
 	}
	
	public void addMain(CfCommodityEntity cfCommodity,
	        List<CfCommodityImgEntity> cfCommodityImgList){
			//保存主信息
			this.save(cfCommodity);
		
			/**保存-商品图片*/
			for(CfCommodityImgEntity cfCommodityImg:cfCommodityImgList){
				//外键设置
				cfCommodityImg.setCommodityId(cfCommodity.getId());
				this.save(cfCommodityImg);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(cfCommodity);
	}

	
	public void updateMain(CfCommodityEntity cfCommodity,
	        List<CfCommodityImgEntity> cfCommodityImgList) {
		//保存主表信息
		this.saveOrUpdate(cfCommodity);
		//===================================================================================
		//获取参数
		Object id0 = cfCommodity.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-商品图片
	    String hql0 = "from CfCommodityImgEntity where 1 = 1 AND cOMMODITY_ID = ? ";
	    List<CfCommodityImgEntity> cfCommodityImgOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-商品图片
		if(cfCommodityImgList!=null&&cfCommodityImgList.size()>0){
		for(CfCommodityImgEntity oldE:cfCommodityImgOldList){
			boolean isUpdate = false;
				for(CfCommodityImgEntity sendE:cfCommodityImgList){
					//需要更新的明细数据-商品图片
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-商品图片
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-商品图片
			for(CfCommodityImgEntity cfCommodityImg:cfCommodityImgList){
				if(oConvertUtils.isEmpty(cfCommodityImg.getId())){
					//外键设置
					cfCommodityImg.setCommodityId(cfCommodity.getId());
					this.save(cfCommodityImg);
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(cfCommodity);
	}

	
	public void delMain(CfCommodityEntity cfCommodity) {
		//删除主表信息
		this.delete(cfCommodity);
		//===================================================================================
		//获取参数
		Object id0 = cfCommodity.getId();
		//===================================================================================
		//删除-商品图片
	    String hql0 = "from CfCommodityImgEntity where 1 = 1 AND cOMMODITY_ID = ? ";
	    List<CfCommodityImgEntity> cfCommodityImgOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(cfCommodityImgOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(CfCommodityEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(CfCommodityEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(CfCommodityEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,CfCommodityEntity t){
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
 		sql  = sql.replace("#{user_id}",String.valueOf(t.getUserId()));
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{introduction}",String.valueOf(t.getIntroduction()));
 		sql  = sql.replace("#{address_id}",String.valueOf(t.getAddressId()));
 		sql  = sql.replace("#{type}",String.valueOf(t.getType()));
 		sql  = sql.replace("#{price}",String.valueOf(t.getPrice()));
 		sql  = sql.replace("#{total_amount}",String.valueOf(t.getTotalAmount()));
 		sql  = sql.replace("#{reserve}",String.valueOf(t.getReserve()));
 		sql  = sql.replace("#{is_recommend}",String.valueOf(t.getIsRecommend()));
 		sql  = sql.replace("#{is_hot}",String.valueOf(t.getIsHot()));
 		sql  = sql.replace("#{manufacturers}",String.valueOf(t.getManufacturers()));
 		sql  = sql.replace("#{fare}",String.valueOf(t.getFare()));
 		sql  = sql.replace("#{is_take}",String.valueOf(t.getIsTake()));
 		sql  = sql.replace("#{brand}",String.valueOf(t.getBrand()));
 		sql  = sql.replace("#{model}",String.valueOf(t.getModel()));
 		sql  = sql.replace("#{position}",String.valueOf(t.getPosition()));
 		sql  = sql.replace("#{components}",String.valueOf(t.getComponents()));
 		sql  = sql.replace("#{details}",String.valueOf(t.getDetails()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}