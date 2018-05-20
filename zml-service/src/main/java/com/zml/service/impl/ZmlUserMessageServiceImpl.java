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
import com.zml.base.entity.ZmlUserMessageEntity;
import com.zml.service.ZmlUserMessageServiceI;
import com.zml.util.SqStrParamlUtil;

@Service("zmlUserMessageService")
@Transactional
public class ZmlUserMessageServiceImpl extends CommonServiceImpl implements ZmlUserMessageServiceI {

	
 	public void delete(ZmlUserMessageEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ZmlUserMessageEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ZmlUserMessageEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ZmlUserMessageEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ZmlUserMessageEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ZmlUserMessageEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(ZmlUserMessageEntity t){
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
		map.put("conten", t.getContent());
		map.put("send_time", t.getSendTime());
		map.put("send_flag", t.getSendFlag());
		map.put("msg_type", t.getMsgType());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ZmlUserMessageEntity t){
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
 		sql  = sql.replace("#{conten}",String.valueOf(t.getContent()));
 		sql  = sql.replace("#{send_time}",String.valueOf(t.getSendTime()));
 		sql  = sql.replace("#{send_flag}",String.valueOf(t.getSendFlag()));
 		sql  = sql.replace("#{msg_type}",String.valueOf(t.getMsgType()));
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
	public Map<String, String> findMessageCountByParam(String userId, String status) {
		userId = SqStrParamlUtil.transactSQLInjection(userId);
		status = SqStrParamlUtil.transactSQLInjection(status);
		Map rsMap = new HashMap();
		if(status != null && !"".equals(status)){
			String sql = "select count(1) count from zml_user_message where user_id ='"+ userId +"' and status ='"+ status +"'";
			Long countVal = getCountForJdbc(sql);
			rsMap.put(status, countVal);
		}else {
			String sql = "select status,sum(1) countVal from zml_user_message where user_id ='"+ userId +"' group by status ";
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
	public List<Map<String, Object>> findMessageByParam(String userId, String readStatus) {
		userId = SqStrParamlUtil.transactSQLInjection(userId);
		String sqlWhere = " user_id = '"+ userId +"'";
		if(readStatus != null && !"".equals(readStatus)){
			sqlWhere += " read_status = '"+ readStatus +"'";
		}
		// 取出当前页的数据 
		String sql = "select t.user_type userType,t.manufacturer_id manufacturerId,t.user_id userId,t.content,t.send_time sendTime,t.send_flag sendFlag,t.msg_type msgType,t.read_status readStatus "
		            + "from zml_user_message t ";
		if (!sqlWhere.isEmpty()) {
			sql += " where" + sqlWhere;
		}
		sql += " ORDER BY t.create_date";
		
		
		List<Map<String, Object>> mapList = findForJdbc(sql, null, null);
		return mapList;
	}
 	
}