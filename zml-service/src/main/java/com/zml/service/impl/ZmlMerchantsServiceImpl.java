package com.zml.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.jce.framework.core.util.ApplicationContextUtil;
import com.jce.framework.core.util.MyClassLoader;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.web.cgform.enhance.CgformEnhanceJavaInter;
import com.zml.base.entity.ZmlMerchantsEntity;
import com.zml.service.ZmlMerchantsServiceI;
import com.zml.util.DbToPage;

import net.sf.json.JSONObject;

@Service("zmlMerchantsService")
@Transactional
public class ZmlMerchantsServiceImpl extends CommonServiceImpl implements ZmlMerchantsServiceI {

	
 	public void delete(ZmlMerchantsEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ZmlMerchantsEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ZmlMerchantsEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ZmlMerchantsEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ZmlMerchantsEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ZmlMerchantsEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(ZmlMerchantsEntity t){
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
		map.put("merchants_name", t.getMerchantsName());
		map.put("merchants_adress", t.getMerchantsAdress());
		map.put("merchants_description", t.getMerchantsDescription());
		map.put("businesslicense", t.getBusinesslicense());
		map.put("logo", t.getLogo());
		map.put("cover_img", t.getCoverImg());
		map.put("consumer_hotline", t.getConsumerHotline());
		map.put("leader", t.getLeader());
		map.put("leader_telephone", t.getLeaderTelephone());
		map.put("grade", t.getGrade());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ZmlMerchantsEntity t){
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
 		sql  = sql.replace("#{merchants_name}",String.valueOf(t.getMerchantsName()));
 		sql  = sql.replace("#{merchants_adress}",String.valueOf(t.getMerchantsAdress()));
 		sql  = sql.replace("#{merchants_description}",String.valueOf(t.getMerchantsDescription()));
 		sql  = sql.replace("#{businesslicense}",String.valueOf(t.getBusinesslicense()));
 		sql  = sql.replace("#{logo}",String.valueOf(t.getLogo()));
 		sql  = sql.replace("#{cover_img}",String.valueOf(t.getCoverImg()));
 		sql  = sql.replace("#{consumer_hotline}",String.valueOf(t.getConsumerHotline()));
 		sql  = sql.replace("#{leader}",String.valueOf(t.getLeader()));
 		sql  = sql.replace("#{leader_telephone}",String.valueOf(t.getLeaderTelephone()));
 		sql  = sql.replace("#{grade}",String.valueOf(t.getGrade()));
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
 	//根据等级、添加时间 倒叙 排,取 num 条数据
	@Override
	public List<Object> findRecommendList(Map param, int currPage, int pageSize){
		Session session = getSession(); 
	    String hql = "select id,merchantsName,logo from ZmlMerchantsEntity u order by grade, createDate desc limit "+currPage +", " + pageSize; 
	    Query query = session.createQuery(hql); 
	    List<Object[]> list = query.list(); 
	    System.out.println("------------SQL执行完毕---------------"); 
	    List reList = null;
	    if(list != null && list.size() > 0){
	    	reList = new ArrayList();
	    	Map temp = null;
	    	for(Object[] objs : list){
	    		temp = new HashMap();
	    		temp.put("id", objs[0]);
	    		temp.put("merchantsName", objs[1]);
	    		temp.put("logo", objs[2]);
	    		reList.add(temp);
	    	}
	    	temp = null;
	    }
	    list = null;
	    return reList;
	}

	@Override
	public List<Map<String, Object>> getDatagrid(ZmlMerchantsEntity pageObj, DataGrid dataGrid) {
		String sqlWhere = getSqlWhere(pageObj);
		
		// 取出总数据条数（为了分页处理, 如果不用分页，取iCount值的这个处理可以不要）
		String sqlCnt = "select count(1) from t_s_depart t";
		if (!sqlWhere.isEmpty()) {
			sqlCnt += " where" + sqlWhere;
		}
		Long iCount = getCountForJdbcParam(sqlCnt, null);
		
		// 取出当前页的数据 
		String sql = "select t.org_code,t.id,t.departname,t.logo from t_s_depart t " ;
		if (!sqlWhere.isEmpty()) {
			sql += " where" + sqlWhere;
		}
		List<Map<String, Object>> mapList = findForJdbc(sql, dataGrid.getPage(), dataGrid.getRows());
		dataGrid.setTotal(iCount.intValue());
		return mapList;
		// 将结果集转换成页面上对应的数mapList据集
		/*DbToPage[] dbToPages = {
				new DbToPage("id")
				,new DbToPage("merchantsName", "merchants_name", null)
				,new DbToPage("logo")
				,new DbToPage("coverImg", "cover_img", null)
				,new DbToPage("grade", null, new MyDataExchangerGrade())
				,new DbToPage("merchantsAdress", "merchants_adress", null)//
				,new DbToPage("createDate", "create_date", null)//
		};
		JSONObject jObject = getJsonDatagridEasyUI(mapList, iCount.intValue(), dbToPages);
		return jObject;*/
		// end of 方式3 ========================================= */
	}
	
	// 拼查询条件（where语句）
	String getSqlWhere(ZmlMerchantsEntity pageObj) {
		// 拼出条件语句
		String sqlWhere = " parentdepartid is null ";
		if (StringUtil.isNotEmpty(pageObj.getMerchantsName())) {
			if (!sqlWhere.isEmpty()) {
				sqlWhere += " and";
			}
			sqlWhere += " t.merchants_name like '%" + pageObj.getMerchantsName() + "%'";
		}
		if (StringUtil.isNotEmpty(pageObj.getMerchantsDescription())) {
			if (!sqlWhere.isEmpty()) {
				sqlWhere += " and";
			}
			sqlWhere += " t.merchants_description like '%" + pageObj.getMerchantsDescription() + "%'";
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
}