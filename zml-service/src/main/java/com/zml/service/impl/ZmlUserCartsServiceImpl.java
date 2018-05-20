package com.zml.service.impl;
import com.zml.service.ZmlUserCartsServiceI;
import com.zml.util.DbToPage;
import com.zml.util.SqStrParamlUtil;
import com.zml.util.data_exchanger.MyDataExchangerGrade;

import net.sf.json.JSONObject;

import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.zml.base.entity.ZmlMerchantsEntity;
import com.zml.base.entity.ZmlUserCartsEntity;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import com.jce.framework.core.common.exception.BusinessException;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.util.ApplicationContextUtil;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.MyClassLoader;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.core.util.oConvertUtils;
import com.jce.framework.web.cgform.enhance.CgformEnhanceJavaInter;

@Service("zmlUserCartsService")
@Transactional
public class ZmlUserCartsServiceImpl extends CommonServiceImpl implements ZmlUserCartsServiceI {

	
 	public void delete(ZmlUserCartsEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ZmlUserCartsEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ZmlUserCartsEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ZmlUserCartsEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ZmlUserCartsEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ZmlUserCartsEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(ZmlUserCartsEntity t){
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
		map.put("commodity_id", t.getCommodityId());
		map.put("amount", t.getAmount());
		map.put("status", t.getStatus());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ZmlUserCartsEntity t){
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
 		sql  = sql.replace("#{commodity_id}",String.valueOf(t.getCommodityId()));
 		sql  = sql.replace("#{amount}",String.valueOf(t.getAmount()));
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
	public List<Map<String, Object>> getDatagrid(ZmlUserCartsEntity pageObj, DataGrid dataGrid) {
		String sqlWhere = getSqlWhere(pageObj);
		// 取出总数据条数（为了分页处理, 如果不用分页，取iCount值的这个处理可以不要）
		String sqlCnt = "select count(1) from zml_user_carts t";
		if (!sqlWhere.isEmpty()) {
			sqlCnt += " where" + sqlWhere;
		}
		Long iCount = getCountForJdbcParam(sqlCnt, null);
		
		// 取出当前页的数据 
		String sql = "select t.id,t.commodity_id commodityId,t.amount,t.status ,tc.real_price realPrice,tc.name, " +
		"tc.reserve_amount reserveAmount,tc.merchants_id merchantsId, tc.manufacturers, tc.cover_img coverImg " 
		+" from zml_user_carts t LEFT JOIN zml_commodity tc on t.commodity_id = tc.id ";
		if (!sqlWhere.isEmpty()) {
			sql += " where" + sqlWhere;
		}
		//查询非删除状态的数据
		//sql += " status !='0'";
		sql += " ORDER BY tc.merchants_id, t.create_date desc";
		List<Map<String, Object>> mapList = findForJdbc(sql, dataGrid.getPage(), dataGrid.getRows());
		dataGrid.setTotal(iCount.intValue());
		return mapList;
		// 将结果集转换成页面上对应的数据集
		/*DbToPage[] dbToPages = {
				new DbToPage("id")
				,new DbToPage("commodityId")
				,new DbToPage("status")
				,new DbToPage("amount")
				,new DbToPage("realPrice")
				,new DbToPage("name")//
				,new DbToPage("reserveAmount")//
				,new DbToPage("merchantsId")
				,new DbToPage("manufacturers")
				,new DbToPage("coverImg")
		};
		JSONObject jObject = getJsonDatagridEasyUI(mapList, iCount.intValue(), dbToPages);
		return jObject;*/
		// end of 方式3 ========================================= */
	}
	
	// 拼查询条件（where语句）
	private String getSqlWhere(ZmlUserCartsEntity pageObj) {
		// 拼出条件语句
		String sqlWhere = " t.status in ('1','2') ";//20170717
		//String sqlWhere = " t.status in ('0','1','2') ";
		if (StringUtil.isNotEmpty(pageObj.getUserId())) {
			if (!sqlWhere.isEmpty()) {
				sqlWhere += " and";
			}
			sqlWhere += " t.user_id = '" + pageObj.getUserId() + "'";
		}
		return sqlWhere;
	}
	// 以下各函数可以提成共用部件 
	/**
	 * 返回easyUI的DataGrid数据格式的JSONObject对象
	 * @param mapList : 从数据库直接取得的结果集列表
	 * @param iTotalCnt : 从数据库直接取得的结果集总数据条数
	 * @param dataExchanger : 页面表示数据与数据库字段的对应关系列表
	 * @return JSONObject
	 */
	public JSONObject getJsonDatagridEasyUI(List<Map<String, Object>> mapList, int iTotalCnt, DbToPage[] dataExchanger) {
		//easyUI的dataGrid方式  －－－－这部分可以提取成统一处理
		String jsonTemp = "{\'total\':" + iTotalCnt + ",\'rows\':[";
		for (int j = 0; j < mapList.size(); j++) {
			Map<String, Object> m = mapList.get(j);
			if (j > 0) {
				jsonTemp += ",";
			}
			jsonTemp += "{";
			for (int i = 0; i < dataExchanger.length; i++) {
				if (i > 0) {
					jsonTemp += ",";
				}
				jsonTemp += "'" + dataExchanger[i].getKey() + "'" + ":";
				Object objValue = dataExchanger[i].getData(m);
				if (objValue == null) {
					jsonTemp += "null";
				} else {
					jsonTemp += "'" + objValue + "'";
				}
			}
			jsonTemp += "}";
		}
		jsonTemp += "]}";
		JSONObject jObject = JSONObject.fromObject(jsonTemp);
		return jObject;
	}

	@Override
	public Long findCartsCountByUserId(String userId) {
		String sql = "select count(1) from zml_user_carts where user_id ='"+ userId +"'";
		return getCountForJdbc(sql);
	}

	@Override
	public List<ZmlUserCartsEntity> findCartsByIds(String[] ids) {
		String HQL = "form ZmlUserCartsEntity where id in (:ids) and ";
		Query query = getSession().createQuery(HQL);
		query.setParameterList("ids", ids);
		return query.list();
	}

	@Override
	public List<Map<String, Object>> findCartsAndCommodityByCartsIds(String[] ids) {
		String sql = "select uc.id ucId,uc.amount, " +
		"c.price,c.real_price realPrice,c.total_amount totalAmount,c.reserve_amount reserveAmount,c.fare " 
		+ " from zml_user_carts uc LEFT JOIN zml_commodity c on uc.commodity_id = c.id  ";
		//+ " where uc.id in(?)  ";
		SqStrParamlUtil.transactSQLInjection(ids);
		String param = SqStrParamlUtil.strArrayToInParam(ids);
		//查询非删除状态的数据
		//sql += " status !='0'";
		sql += " where uc.id in " + param;
		sql += " ORDER BY uc.create_date desc ";
		List<Map<String, Object>> mapList = findForJdbc(sql, null);
		return mapList;
	}

	@Override
	public List<Map<String, Object>> findCartsAndCommodityInfoByCartsIds(String[] ids) {
		String sql = "select uc.id ucId,uc.amount, " +
			"c.price,c.real_price realPrice,c.name commodityName,c.id commodityId,c.merchants_id merchantsId,c.manufacturers ,c.cover_img,"
			+ "c.total_amount totalAmount,c.reserve_amount reserveAmount,c.fare,c.classify_two_level classifyTwoLevel" 
			+ " from zml_user_carts uc LEFT JOIN zml_commodity c on uc.commodity_id = c.id  ";
			//+ " where uc.id in(?)  ";
			SqStrParamlUtil.transactSQLInjection(ids);
			String param = SqStrParamlUtil.strArrayToInParam(ids);
			//查询非删除状态的数据
			//sql += " status !='0'";
			sql += " where uc.id in " + param;
			sql += " ORDER BY uc.create_date desc ";
			List<Map<String, Object>> mapList = findForJdbc(sql, null);
			return mapList;
	}
}