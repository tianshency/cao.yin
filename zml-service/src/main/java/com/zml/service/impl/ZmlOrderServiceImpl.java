package com.zml.service.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.core.common.exception.BusinessException;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.core.util.oConvertUtils;
import com.zml.base.entity.ZmlCommodityEntity;
import com.zml.base.entity.ZmlOrderCommodityEntity;
import com.zml.base.entity.ZmlOrderEntity;
import com.zml.base.entity.ZmlUserCartsEntity;
import com.zml.enums.CartsStatus;
import com.zml.service.ZmlOrderServiceI;
import com.zml.util.SqStrParamlUtil;


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
			/**保存-商品列表*/
			for(ZmlOrderCommodityEntity zmlOrderCommodity:zmlOrderCommodityList){
				//外键设置
				zmlOrderCommodity.setOrderNum(zmlOrder.getOrderNum());
				zmlOrderCommodity.setOrderId(zmlOrder.getId());
				this.save(zmlOrderCommodity);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(zmlOrder);
	}
	@Override
	public Integer updateOrderSts(String orderId, String sts) {
		orderId = SqStrParamlUtil.transactSQLInjection(orderId);
		sts = SqStrParamlUtil.transactSQLInjection(sts);
		String sql = "update zml_order set status = ? where id =?";
		Map paramMap = new HashMap();
		paramMap.put("status", sts);
		paramMap.put("id", orderId);
		return executeSql(sql, paramMap);
	}
	
	public void updateMain(ZmlOrderEntity zmlOrder,
	        List<ZmlOrderCommodityEntity> zmlOrderCommodityList) {
		//保存主表信息
		this.saveOrUpdate(zmlOrder);
		//===================================================================================
		//获取参数
		Object id0 = zmlOrder.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-商品列表
	    String hql0 = "from ZmlOrderCommodityEntity where 1 = 1 AND oRDER_ID = ? ";
	    List<ZmlOrderCommodityEntity> zmlOrderCommodityOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-商品列表
		if(zmlOrderCommodityList!=null&&zmlOrderCommodityList.size()>0){
		for(ZmlOrderCommodityEntity oldE:zmlOrderCommodityOldList){
			boolean isUpdate = false;
				for(ZmlOrderCommodityEntity sendE:zmlOrderCommodityList){
					//需要更新的明细数据-商品列表
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-商品列表
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-商品列表
			for(ZmlOrderCommodityEntity zmlOrderCommodity:zmlOrderCommodityList){
				if(oConvertUtils.isEmpty(zmlOrderCommodity.getId())){
					//外键设置
					zmlOrderCommodity.setOrderId(zmlOrder.getId());
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
		Object id0 = zmlOrder.getId();
		//===================================================================================
		//删除-商品列表
	    String hql0 = "from ZmlOrderCommodityEntity where 1 = 1 AND oRDER_ID = ? ";
	    List<ZmlOrderCommodityEntity> zmlOrderCommodityOldList = this.findHql(hql0,id0);
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
 	
 	@Override
	public List<Map<String, Object>> getDatagrid(ZmlOrderEntity pageObj, DataGrid dataGrid) {
		String sqlWhere = getSqlWhere(pageObj);
		// 取出总数据条数（为了分页处理, 如果不用分页，取iCount值的这个处理可以不要）
		String sqlCnt = "select count(1) from zml_order t";
		if (!sqlWhere.isEmpty()) {
			sqlCnt += " where" + sqlWhere;
		}
		Long iCount = getCountForJdbcParam(sqlCnt, null);
		
		// 取出当前页的数据 
		String sql = "select t.id,t.order_num orderNum,t.all_amount allAmount, t.pay_amout payAmout,t.status, "
                    + "oc.commodity_id commodityId, oc.commodity_name commodityName, oc.all_count allCount,oc.price,oc.all_amount ocAllAmount,oc.commodity_cover_img coverImg,oc.logistics_fee logisticsFee "
		            + "from zml_order t LEFT JOIN zml_order_commodity oc on t.id = oc.order_id ";
		if (!sqlWhere.isEmpty()) {
			sql += " where" + sqlWhere;
		}
		sql += " ORDER BY t.create_date";
		List<Map<String, Object>> mapList = findForJdbc(sql, dataGrid.getPage(), dataGrid.getRows());
		dataGrid.setTotal(iCount.intValue());
		return mapList;
	}
	
	// 拼查询条件（where语句）
	private String getSqlWhere(ZmlOrderEntity pageObj) {
		// 拼出条件语句
		String sqlWhere = "";
		if (StringUtil.isNotEmpty(pageObj.getBuyersId())) {
			if (!sqlWhere.isEmpty()) {
				sqlWhere += " and";
			}
			sqlWhere += " t.buyers_id = '" + pageObj.getBuyersId() + "'";
		}
		if (StringUtil.isNotEmpty(pageObj.getStatus())) {
			if (!sqlWhere.isEmpty()) {
				sqlWhere += " and";
			}
			sqlWhere += " t.status = '" + pageObj.getStatus() + "'";
		}
		return sqlWhere;
	}

	@Override
	public Map<String, String> findOrderCountByUserId(String userId, String status) {
		userId = SqStrParamlUtil.transactSQLInjection(userId);
		status = SqStrParamlUtil.transactSQLInjection(status);
		Map rsMap = new HashMap();
		if(status != null && !"".equals(status)){
			String sql = "select count(1) count from zml_order where user_id ='"+ userId +"' and status ='"+ status +"'";
			Long countVal = getCountForJdbc(sql);
			rsMap.put(status, countVal);
		}else {
			String sql = "select status,sum(1) countVal from zml_order where user_id ='"+ userId +"' group by status ";
			List<Map<String, Object>> list = findForJdbc(sql, null);
			if( list != null && list.size() > 0){
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> temp = list.get(i);
					rsMap.put(temp.get("status"), temp.get("countVal"));
				}		
			}
		}
		
		return rsMap;
	}

	@Override
	public Map<String, String> createOrder(String [] ids,ZmlOrderEntity zmlOrder, List<ZmlOrderCommodityEntity> zmlOrderCommodityList) {
		
		SqStrParamlUtil.transactSQLInjection(ids);
		//保存主信息
		this.save(zmlOrder);
		/**保存-商品列表*/
		Map map = new HashMap();
		for(ZmlOrderCommodityEntity zmlOrderCommodity:zmlOrderCommodityList){
			//修改库存
			String commodityId = zmlOrderCommodity.getCommodityId();
			ZmlCommodityEntity zmlCommodity = getEntity(ZmlCommodityEntity.class, commodityId);
			Integer reserveAmount = zmlCommodity.getReserveAmount();
			Integer selectCount = zmlOrderCommodity.getAllCount();
			if(selectCount.intValue() > reserveAmount.intValue()){
				map.put("code", "-1");
				map.put("msg", "库存不足");
				return map;
			}
			zmlCommodity.setReserveAmount(reserveAmount.intValue()-selectCount.intValue());
			//外键设置
			zmlOrderCommodity.setOrderNum(zmlOrder.getOrderNum());
			zmlOrderCommodity.setOrderId(zmlOrder.getId());
			this.save(zmlOrderCommodity);
		}
		//删除商品对的应购物车 状态为失效
		for(int i=0; i<ids.length ;i++){
			ZmlUserCartsEntity zmlUserCarts = getEntity(ZmlUserCartsEntity.class, ids[i]);
			if(zmlUserCarts != null){
				zmlUserCarts.setUpdateDate(new Date());
				zmlUserCarts.setStatus(CartsStatus.SUCCESS.getStatusValue());
				delete(zmlUserCarts);
			}
		}
		map.put("code", "1");
		return map;
	}

	@Override
	public List<Map<String, Object>> findOrderByUserId(String userId) {
		userId = SqStrParamlUtil.transactSQLInjection(userId);
		String sqlWhere = " user_id = '"+ userId +"'";
		
		// 取出当前页的数据 
		String sql = "select t.id,t.order_num orderNum,t.all_amount allAmount, t.pay_amout payAmout,t.status, "
                    + "oc.commodity_id commodityId, oc.commodity_name commodityName, oc.all_count allCount,oc.price,oc.all_amount ocAllAmount,oc.commodity_cover_img coverImg,oc.logistics_fee logisticsFee "
		            + "from zml_order t LEFT JOIN zml_order_commodity oc on t.id = oc.order_id ";
		if (!sqlWhere.isEmpty()) {
			sql += " where" + sqlWhere;
		}
		sql += " ORDER BY t.create_date";
		
		
		List<Map<String, Object>> mapList = findForJdbc(sql, null, null);
		return mapList;
	}
}