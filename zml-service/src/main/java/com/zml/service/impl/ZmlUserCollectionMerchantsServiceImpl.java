package com.zml.service.impl;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.jce.framework.core.util.ApplicationContextUtil;
import com.jce.framework.core.util.MyClassLoader;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.web.cgform.enhance.CgformEnhanceJavaInter;
import com.zml.base.entity.ZmlUserCollectionCommodityEntity;
import com.zml.base.entity.ZmlUserCollectionMerchantsEntity;
import com.zml.enums.CommonStatus;
import com.zml.service.ZmlUserCollectionMerchantsServiceI;

@Service("zmlUserCollectionMerchantsService")
@Transactional
public class ZmlUserCollectionMerchantsServiceImpl extends CommonServiceImpl implements ZmlUserCollectionMerchantsServiceI {

	
 	public void delete(ZmlUserCollectionMerchantsEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ZmlUserCollectionMerchantsEntity entity) throws Exception{
 		entity.setStatus(CommonStatus.NORMAL.getStatusValue());
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ZmlUserCollectionMerchantsEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ZmlUserCollectionMerchantsEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ZmlUserCollectionMerchantsEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ZmlUserCollectionMerchantsEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(ZmlUserCollectionMerchantsEntity t){
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
		map.put("label", t.getLabel());
		map.put("collection_type", t.getCollectionType());
		map.put("merchants_id", t.getMerchantsId());
		map.put("merchants_name", t.getMerchantsName());
		map.put("merchants_logo", t.getMerchantsLogo());
		map.put("principal", t.getPrincipal());
		map.put("address", t.getAddress());
		map.put("url", t.getUrl());
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
 	public String replaceVal(String sql,ZmlUserCollectionMerchantsEntity t){
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
 		sql  = sql.replace("#{label}",String.valueOf(t.getLabel()));
 		sql  = sql.replace("#{collection_type}",String.valueOf(t.getCollectionType()));
 		sql  = sql.replace("#{merchants_id}",String.valueOf(t.getMerchantsId()));
 		sql  = sql.replace("#{merchants_name}",String.valueOf(t.getMerchantsName()));
 		sql  = sql.replace("#{merchants_logo}",String.valueOf(t.getMerchantsLogo()));
 		sql  = sql.replace("#{principal}",String.valueOf(t.getPrincipal()));
 		sql  = sql.replace("#{address}",String.valueOf(t.getAddress()));
 		sql  = sql.replace("#{url}",String.valueOf(t.getUrl()));
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
	public List<Map<String, Object>> findDataByEntity(ZmlUserCollectionMerchantsEntity entity, DataGrid dataGrid) {
		String sqlWhere = getSqlWhere(entity);
		// 取出总数据条数（为了分页处理, 如果不用分页，取iCount值的这个处理可以不要）
		String sqlCnt = "select count(1) from zml_user_collection_merchants t";
		if (!sqlWhere.isEmpty()) {
			sqlCnt += " where" + sqlWhere;
		}
		Long iCount = getCountForJdbcParam(sqlCnt, null);
		// 取出当前页的数据 
		String sql = "select t.id,t.label, t.merchants_id merchantsId,t.merchants_name merchantsName, t.merchants_logo merchantsLogo,t.url " +
		" " 
		+" from zml_user_collection_merchants t ";
		if (!sqlWhere.isEmpty()) {
			sql += " where" + sqlWhere;
		}
		//查询非删除状态的数据
		//sql += " status ='1'";
		sql += " ORDER BY t.create_date desc";
		List<Map<String, Object>> mapList = findForJdbc(sql, dataGrid.getPage(), dataGrid.getRows());
		dataGrid.setTotal(iCount.intValue());
		return mapList;
	}
	
	// 拼查询条件（where语句）
	private String getSqlWhere(ZmlUserCollectionMerchantsEntity pageObj) {
		// 拼出条件语句
		String sqlWhere = " t.status in ('1') ";
		if (StringUtil.isNotEmpty(pageObj.getUserId())) {
			if (!sqlWhere.isEmpty()) {
				sqlWhere += " and";
			}
			sqlWhere += " t.user_id = '" + pageObj.getUserId() + "'";
		}
		return sqlWhere;
	}
}