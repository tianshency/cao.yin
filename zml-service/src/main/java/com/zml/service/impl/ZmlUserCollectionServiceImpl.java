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
import com.zml.base.entity.ZmlUserCollectionEntity;
import com.zml.base.entity.ZmlUserCollectionMerchantsEntity;
import com.zml.enums.CommonStatus;
import com.zml.service.ZmlUserCollectionServiceI;

@Service("zmlUserCollectionService")
@Transactional
public class ZmlUserCollectionServiceImpl extends CommonServiceImpl implements ZmlUserCollectionServiceI {

	
 	public void delete(ZmlUserCollectionEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ZmlUserCollectionEntity entity) throws Exception{
 		entity.setStatus(CommonStatus.NORMAL.getStatusValue());
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ZmlUserCollectionEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ZmlUserCollectionEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ZmlUserCollectionEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ZmlUserCollectionEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(ZmlUserCollectionEntity t){
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
		map.put("data_id", t.getDataId());
		map.put("data_name", t.getDataName());
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
 	public String replaceVal(String sql,ZmlUserCollectionEntity t){
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
 		sql  = sql.replace("#{data_id}",String.valueOf(t.getDataId()));
 		sql  = sql.replace("#{data_name}",String.valueOf(t.getDataName()));
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
	public List<Map<String, Object>> findDataByEntity(ZmlUserCollectionEntity entity, DataGrid dataGrid, String flag) {
		String sqlWhere = getSqlWhere(entity);
		// 取出总数据条数（为了分页处理, 如果不用分页，取iCount值的这个处理可以不要）
		/*
		String sqlCnt = "select count(1) from zml_user_collection t";
		if (!sqlWhere.isEmpty()) {
			sqlCnt += " where" + sqlWhere;
		}
		Long iCount = getCountForJdbcParam(sqlCnt, null);
		*/
		// 取出当前页的数据 
		String sql = "select t.id,t.label, t.collection_type collectionType, t.data_id dataId,t.data_name dataName,t.url " +
		",t.cover_img coverImg ,t.status,t.create_date createDate,t.update_date updateDate " 
		+" from zml_user_collection t ";
		if (!sqlWhere.isEmpty()) {
			sql += " where" + sqlWhere;
			if("app".equals(flag)){
				sqlWhere +=" and status in('1','2')";
			}
		}
		else if("app".equals(flag)){
			sqlWhere +=" where status in('1','2')";
		}
		//查询非删除状态的数据
		//sql += " status ='1'";
		sql += " ORDER BY t.create_date desc";
		List<Map<String, Object>> mapList = findForJdbc(sql, dataGrid.getPage(), dataGrid.getRows());
		//dataGrid.setTotal(iCount.intValue());
		return mapList;
	}
	
	// 拼查询条件（where语句）
	private String getSqlWhere(ZmlUserCollectionEntity pageObj) {
		// 拼出条件语句
		String sqlWhere = "";
		if (StringUtil.isNotEmpty(pageObj.getUserId())) {
			if (!sqlWhere.isEmpty()) {
				sqlWhere += " and";
			}
			sqlWhere += " t.user_id = '" + pageObj.getUserId() + "'";
		}
		if (StringUtil.isNotEmpty(pageObj.getLabel())) {
			if (!sqlWhere.isEmpty()) {
				sqlWhere += " and";
			}
			sqlWhere += " t.label = '" + pageObj.getLabel() + "'";
		}
		return sqlWhere;
	}
	
	@Override
	public Long findCountByUserId(String userId, String flag) {
		String sql = "select count(1) from zml_user_collection where user_id ='"+ userId +"'";
		if("app".equals(flag)){
			sql +=" status in('1','3')";
		}
		return getCountForJdbc(sql);
	}
	
}