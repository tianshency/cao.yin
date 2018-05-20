package com.zml.service.impl;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.jce.framework.core.util.ApplicationContextUtil;
import com.jce.framework.core.util.MyClassLoader;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.web.cgform.enhance.CgformEnhanceJavaInter;
import com.zml.base.entity.ZmlUserBookingEntity;
import com.zml.service.ZmlUserBookingServiceI;

@Service("zmlUserBookingService")
@Transactional
public class ZmlUserBookingServiceImpl extends CommonServiceImpl implements ZmlUserBookingServiceI {

	
 	public void delete(ZmlUserBookingEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ZmlUserBookingEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ZmlUserBookingEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ZmlUserBookingEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ZmlUserBookingEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ZmlUserBookingEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(ZmlUserBookingEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_name", t.getUpdateName());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("sys_company_code", t.getSysCompanyCode());
		map.put("version", t.getVersion());
		map.put("user_id", t.getUserId());
		map.put("other_side_id", t.getOtherSideId());
		map.put("booking_type", t.getBookingType());
		map.put("booking_id", t.getBookingId());
		map.put("booking_num", t.getBookingNum());
		map.put("plan_deal_date", t.getPlanDealDate());
		map.put("user_name", t.getUserName());
		map.put("other_side_name", t.getOtherSideName());
		map.put("booking_detail", t.getBookingDetail());
		map.put("remarks", t.getRemarks());
		map.put("status", t.getStatus());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ZmlUserBookingEntity t){
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
 		sql  = sql.replace("#{other_side_id}",String.valueOf(t.getOtherSideId()));
 		sql  = sql.replace("#{booking_type}",String.valueOf(t.getBookingType()));
 		sql  = sql.replace("#{booking_id}",String.valueOf(t.getBookingId()));
 		sql  = sql.replace("#{booking_num}",String.valueOf(t.getBookingNum()));
 		sql  = sql.replace("#{plan_deal_date}",String.valueOf(t.getPlanDealDate()));
 		sql  = sql.replace("#{user_name}",String.valueOf(t.getUserName()));
 		sql  = sql.replace("#{other_side_name}",String.valueOf(t.getOtherSideName()));
 		sql  = sql.replace("#{booking_detail}",String.valueOf(t.getBookingDetail()));
 		sql  = sql.replace("#{remarks}",String.valueOf(t.getRemarks()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	/**
	 * 执行JAVA增强
	 */
 	private void executeJavaExtend(String cgJavaType,String cgJavaValue,Map<String,Object> data) throws Exception {
 		if(StringUtil.isNotEmpty(cgJavaValue)){
			Object obj = null;
			try {
				if("class".equals(cgJavaType)){
					//因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if(obj instanceof CgformEnhanceJavaInter){
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					javaInter.execute(data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}

	@Override
	public Long findBookingCountByUserId(String userId) {
		String sql = "select count(1) from zml_user_booking where user_id ='"+ userId +"'";
		return getCountForJdbc(sql);
	}

	@Override
	public List<Map<String, Object>> findBookingByUserId(String userId) {
		String sqlWhere = " user_id ='"+userId+"' or other_side_id = '"+ userId +"'";
		// 取出总数据条数（为了分页处理, 如果不用分页，取iCount值的这个处理可以不要）
		/*
		String sqlCnt = "select count(1) from zml_user_booking t";
		if (!sqlWhere.isEmpty()) {
			sqlCnt += " where" + sqlWhere;
		}
		Long iCount = getCountForJdbcParam(sqlCnt, null);
		*/
		// 取出当前页的数据 
		String sql = "select t.id id, t.user_id userId,t.user_name userName,t.other_side_id otherSideId,t.other_side_name otherSideName,t.booking_type bookingType,t.booking_id bookingId, " 
                     + " t.booking_title bookingTitle, t.booking_detail bookingDetail,t.booking_num bookingNum,t.plan_deal_date planDealDate, t.status "
		             + "from zml_user_booking t where 1=1 and t.status!='0' ";
		if (!sqlWhere.isEmpty()) {
			sql += " and "+sqlWhere;
		}
		sql += " ORDER BY t.create_date";
		List<Map<String, Object>> mapList = findForJdbc(sql, null);
		return mapList;
	}
}