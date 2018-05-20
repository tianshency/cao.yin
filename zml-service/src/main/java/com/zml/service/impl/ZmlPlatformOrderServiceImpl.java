package com.zml.service.impl;
import com.zml.service.ZmlPlatformOrderServiceI;
import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.zml.base.entity.ZmlPlatformOrderEntity;
import com.zml.base.entity.ZmlPlatformOrderCommodityEntity;
import com.zml.base.entity.ZmlPorderToCorderEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.jce.framework.core.common.exception.BusinessException;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.oConvertUtils;
import java.util.ArrayList;
import java.util.UUID;
import java.io.Serializable;


@Service("zmlPlatformOrderService")
@Transactional
public class ZmlPlatformOrderServiceImpl extends CommonServiceImpl implements ZmlPlatformOrderServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((ZmlPlatformOrderEntity)entity);
 	}
	
	public void addMain(ZmlPlatformOrderEntity zmlPlatformOrder,
	        List<ZmlPlatformOrderCommodityEntity> zmlPlatformOrderCommodityList,List<ZmlPorderToCorderEntity> zmlPorderToCorderList){
			//保存主信息
			this.save(zmlPlatformOrder);
		
			/**保存-对应商品*/
			for(ZmlPlatformOrderCommodityEntity zmlPlatformOrderCommodity:zmlPlatformOrderCommodityList){
				//外键设置
				zmlPlatformOrderCommodity.setPorderNum(zmlPlatformOrder.getPorderNum());
				this.save(zmlPlatformOrderCommodity);
			}
			/**保存-对应用户订单*/
			for(ZmlPorderToCorderEntity zmlPorderToCorder:zmlPorderToCorderList){
				//外键设置
				zmlPorderToCorder.setPorderNum(zmlPlatformOrder.getPorderNum());
				this.save(zmlPorderToCorder);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(zmlPlatformOrder);
	}

	
	public void updateMain(ZmlPlatformOrderEntity zmlPlatformOrder,
	        List<ZmlPlatformOrderCommodityEntity> zmlPlatformOrderCommodityList,List<ZmlPorderToCorderEntity> zmlPorderToCorderList) {
		//保存主表信息
		this.saveOrUpdate(zmlPlatformOrder);
		//===================================================================================
		//获取参数
		Object pORDER_NUM0 = zmlPlatformOrder.getPorderNum();
		Object pORDER_NUM1 = zmlPlatformOrder.getPorderNum();
		//===================================================================================
		//1.查询出数据库的明细数据-对应商品
	    String hql0 = "from ZmlPlatformOrderCommodityEntity where 1 = 1 AND pORDER_NUM = ? ";
	    List<ZmlPlatformOrderCommodityEntity> zmlPlatformOrderCommodityOldList = this.findHql(hql0,pORDER_NUM0);
		//2.筛选更新明细数据-对应商品
		if(zmlPlatformOrderCommodityList!=null&&zmlPlatformOrderCommodityList.size()>0){
		for(ZmlPlatformOrderCommodityEntity oldE:zmlPlatformOrderCommodityOldList){
			boolean isUpdate = false;
				for(ZmlPlatformOrderCommodityEntity sendE:zmlPlatformOrderCommodityList){
					//需要更新的明细数据-对应商品
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-对应商品
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-对应商品
			for(ZmlPlatformOrderCommodityEntity zmlPlatformOrderCommodity:zmlPlatformOrderCommodityList){
				if(oConvertUtils.isEmpty(zmlPlatformOrderCommodity.getId())){
					//外键设置
					zmlPlatformOrderCommodity.setPorderNum(zmlPlatformOrder.getPorderNum());
					this.save(zmlPlatformOrderCommodity);
				}
			}
		}
		//===================================================================================
		//1.查询出数据库的明细数据-对应用户订单
	    String hql1 = "from ZmlPorderToCorderEntity where 1 = 1 AND pORDER_NUM = ? ";
	    List<ZmlPorderToCorderEntity> zmlPorderToCorderOldList = this.findHql(hql1,pORDER_NUM1);
		//2.筛选更新明细数据-对应用户订单
		if(zmlPorderToCorderList!=null&&zmlPorderToCorderList.size()>0){
		for(ZmlPorderToCorderEntity oldE:zmlPorderToCorderOldList){
			boolean isUpdate = false;
				for(ZmlPorderToCorderEntity sendE:zmlPorderToCorderList){
					//需要更新的明细数据-对应用户订单
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-对应用户订单
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-对应用户订单
			for(ZmlPorderToCorderEntity zmlPorderToCorder:zmlPorderToCorderList){
				if(oConvertUtils.isEmpty(zmlPorderToCorder.getId())){
					//外键设置
					zmlPorderToCorder.setPorderNum(zmlPlatformOrder.getPorderNum());
					this.save(zmlPorderToCorder);
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(zmlPlatformOrder);
	}

	
	public void delMain(ZmlPlatformOrderEntity zmlPlatformOrder) {
		//删除主表信息
		this.delete(zmlPlatformOrder);
		//===================================================================================
		//获取参数
		Object pORDER_NUM0 = zmlPlatformOrder.getPorderNum();
		Object pORDER_NUM1 = zmlPlatformOrder.getPorderNum();
		//===================================================================================
		//删除-对应商品
	    String hql0 = "from ZmlPlatformOrderCommodityEntity where 1 = 1 AND pORDER_NUM = ? ";
	    List<ZmlPlatformOrderCommodityEntity> zmlPlatformOrderCommodityOldList = this.findHql(hql0,pORDER_NUM0);
		this.deleteAllEntitie(zmlPlatformOrderCommodityOldList);
		//===================================================================================
		//删除-对应用户订单
	    String hql1 = "from ZmlPorderToCorderEntity where 1 = 1 AND pORDER_NUM = ? ";
	    List<ZmlPorderToCorderEntity> zmlPorderToCorderOldList = this.findHql(hql1,pORDER_NUM1);
		this.deleteAllEntitie(zmlPorderToCorderOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ZmlPlatformOrderEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ZmlPlatformOrderEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ZmlPlatformOrderEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,ZmlPlatformOrderEntity t){
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
 		sql  = sql.replace("#{seller_id}",String.valueOf(t.getSellerId()));
 		sql  = sql.replace("#{porder_num}",String.valueOf(t.getPorderNum()));
 		sql  = sql.replace("#{all_amount}",String.valueOf(t.getAllAmount()));
 		sql  = sql.replace("#{pay_way}",String.valueOf(t.getPayWay()));
 		sql  = sql.replace("#{pay_voucher}",String.valueOf(t.getPayVoucher()));
 		sql  = sql.replace("#{invoice_type}",String.valueOf(t.getInvoiceType()));
 		sql  = sql.replace("#{invoice_title}",String.valueOf(t.getInvoiceTitle()));
 		sql  = sql.replace("#{invoice_content}",String.valueOf(t.getInvoiceContent()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{is_returns}",String.valueOf(t.getIsReturns()));
 		sql  = sql.replace("#{return_reason}",String.valueOf(t.getReturnReason()));
 		sql  = sql.replace("#{order_time}",String.valueOf(t.getOrderTime()));
 		sql  = sql.replace("#{pay_time}",String.valueOf(t.getPayTime()));
 		sql  = sql.replace("#{remarks}",String.valueOf(t.getRemarks()));
 		sql  = sql.replace("#{address}",String.valueOf(t.getAddress()));
 		sql  = sql.replace("#{logistics_company}",String.valueOf(t.getLogisticsCompany()));
 		sql  = sql.replace("#{logistics_num}",String.valueOf(t.getLogisticsNum()));
 		sql  = sql.replace("#{logistics_fee}",String.valueOf(t.getLogisticsFee()));
 		sql  = sql.replace("#{logistics_link_person}",String.valueOf(t.getLogisticsLinkPerson()));
 		sql  = sql.replace("#{logistics_link_phnoe}",String.valueOf(t.getLogisticsLinkPhnoe()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}