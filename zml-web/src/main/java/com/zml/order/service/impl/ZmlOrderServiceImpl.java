package com.zml.order.service.impl;
import com.zml.order.service.ZmlOrderServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.zml.order.entity.ZmlOrderEntity;
import com.zml.order.entity.ZmlOrderCommodityEntity;

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


@Service("zmlOrderService")
@Transactional
public class ZmlOrderServiceImpl extends CommonServiceImpl implements ZmlOrderServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((ZmlOrderEntity)entity);
 	}
	
	public void addMain(ZmlOrderEntity zmlOrder,
	        List<ZmlOrderCommodityEntity> zmlOrderCommodityList){
			//保存主信息
			this.save(zmlOrder);
		
			/**保存-订单对应商品*/
			for(ZmlOrderCommodityEntity zmlOrderCommodity:zmlOrderCommodityList){
				//外键设置
				zmlOrderCommodity.setOrderNum(zmlOrder.getOrderNum());
				this.save(zmlOrderCommodity);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(zmlOrder);
	}

	
	public void updateMain(ZmlOrderEntity zmlOrder,
	        List<ZmlOrderCommodityEntity> zmlOrderCommodityList) {
		//保存主表信息
		this.saveOrUpdate(zmlOrder);
		//===================================================================================
		//获取参数
		Object oRDER_NUM0 = zmlOrder.getOrderNum();
		//===================================================================================
		//1.查询出数据库的明细数据-订单对应商品
	    String hql0 = "from ZmlOrderCommodityEntity where 1 = 1 AND oRDER_NUM = ? ";
	    List<ZmlOrderCommodityEntity> zmlOrderCommodityOldList = this.findHql(hql0,oRDER_NUM0);
		//2.筛选更新明细数据-订单对应商品
		if(zmlOrderCommodityList!=null&&zmlOrderCommodityList.size()>0){
		for(ZmlOrderCommodityEntity oldE:zmlOrderCommodityOldList){
			boolean isUpdate = false;
				for(ZmlOrderCommodityEntity sendE:zmlOrderCommodityList){
					//需要更新的明细数据-订单对应商品
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-订单对应商品
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-订单对应商品
			for(ZmlOrderCommodityEntity zmlOrderCommodity:zmlOrderCommodityList){
				if(oConvertUtils.isEmpty(zmlOrderCommodity.getId())){
					//外键设置
					zmlOrderCommodity.setOrderNum(zmlOrder.getOrderNum());
					this.save(zmlOrderCommodity);
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(zmlOrder);
	}

	
	public void delMain(ZmlOrderEntity zmlOrder) {
		//删除主表信息
		this.delete(zmlOrder);
		//===================================================================================
		//获取参数
		Object oRDER_NUM0 = zmlOrder.getOrderNum();
		//===================================================================================
		//删除-订单对应商品
	    String hql0 = "from ZmlOrderCommodityEntity where 1 = 1 AND oRDER_NUM = ? ";
	    List<ZmlOrderCommodityEntity> zmlOrderCommodityOldList = this.findHql(hql0,oRDER_NUM0);
		this.deleteAllEntitie(zmlOrderCommodityOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ZmlOrderEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ZmlOrderEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ZmlOrderEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,ZmlOrderEntity t){
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
 		sql  = sql.replace("#{buyers_id}",String.valueOf(t.getBuyersId()));
 		sql  = sql.replace("#{merchants_id}",String.valueOf(t.getMerchantsId()));
 		sql  = sql.replace("#{order_num}",String.valueOf(t.getOrderNum()));
 		sql  = sql.replace("#{pay_id}",String.valueOf(t.getPayId()));
 		sql  = sql.replace("#{invoice_type}",String.valueOf(t.getInvoiceType()));
 		sql  = sql.replace("#{invoice_title}",String.valueOf(t.getInvoiceTitle()));
 		sql  = sql.replace("#{invoice_content}",String.valueOf(t.getInvoiceContent()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{platform_remarks}",String.valueOf(t.getPlatformRemarks()));
 		sql  = sql.replace("#{all_amount}",String.valueOf(t.getAllAmount()));
 		sql  = sql.replace("#{pay_amout}",String.valueOf(t.getPayAmout()));
 		sql  = sql.replace("#{is_returns}",String.valueOf(t.getIsReturns()));
 		sql  = sql.replace("#{return_reason}",String.valueOf(t.getReturnReason()));
 		sql  = sql.replace("#{order_time}",String.valueOf(t.getOrderTime()));
 		sql  = sql.replace("#{user_remarks}",String.valueOf(t.getUserRemarks()));
 		sql  = sql.replace("#{address_id}",String.valueOf(t.getAddressId()));
 		sql  = sql.replace("#{logistics_company}",String.valueOf(t.getLogisticsCompany()));
 		sql  = sql.replace("#{logistics_num}",String.valueOf(t.getLogisticsNum()));
 		sql  = sql.replace("#{logistics_fee}",String.valueOf(t.getLogisticsFee()));
 		sql  = sql.replace("#{logistics_link_person}",String.valueOf(t.getLogisticsLinkPerson()));
 		sql  = sql.replace("#{logistics_link_phnoe}",String.valueOf(t.getLogisticsLinkPhnoe()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}