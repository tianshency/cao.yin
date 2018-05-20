package com.zml.loan_service.impl;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.jce.framework.core.util.ApplicationContextUtil;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.MyClassLoader;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.web.cgform.enhance.CgformEnhanceJavaInter;
import com.zml.base.loan.entity.ZmlLoanApplyDocumentEntity;
import com.zml.base.loan.entity.ZmlLoanUserContactsEntity;
import com.zml.loan_service.ZmlLoanUserContactsServiceI;

@Service("zmlLoanUserContactsService")
@Transactional
public class ZmlLoanUserContactsServiceImpl extends CommonServiceImpl implements ZmlLoanUserContactsServiceI {

	
 	public void delete(ZmlLoanUserContactsEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ZmlLoanUserContactsEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ZmlLoanUserContactsEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ZmlLoanUserContactsEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ZmlLoanUserContactsEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ZmlLoanUserContactsEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(ZmlLoanUserContactsEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_date", t.getCreateDate());
		map.put("update_date", t.getUpdateDate());
		map.put("version", t.getVersion());
		map.put("appl_id", t.getApplId());
		map.put("user_id", t.getUserId());
		map.put("name", t.getName());
		map.put("phone", t.getPhone());
		map.put("profession", t.getProfession());
		map.put("work", t.getWork());
		map.put("relation", t.getRelation());
		map.put("id_number", t.getIdNumber());
		map.put("age", t.getAge());
		map.put("sex", t.getSex());
		map.put("address", t.getAddress());
		map.put("seq_no", t.getSeqNo());
		map.put("approval_flag", t.getApprovalFlag());
		map.put("approval_user_id", t.getApprovalUserId());
		map.put("approval_date", t.getApprovalDate());
		map.put("approval_opinion", t.getApprovalOpinion());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ZmlLoanUserContactsEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{version}",String.valueOf(t.getVersion()));
 		sql  = sql.replace("#{appl_id}",String.valueOf(t.getApplId()));
 		sql  = sql.replace("#{user_id}",String.valueOf(t.getUserId()));
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{phone}",String.valueOf(t.getPhone()));
 		sql  = sql.replace("#{profession}",String.valueOf(t.getProfession()));
 		sql  = sql.replace("#{work}",String.valueOf(t.getWork()));
 		sql  = sql.replace("#{relation}",String.valueOf(t.getRelation()));
 		sql  = sql.replace("#{id_number}",String.valueOf(t.getIdNumber()));
 		sql  = sql.replace("#{age}",String.valueOf(t.getAge()));
 		sql  = sql.replace("#{sex}",String.valueOf(t.getSex()));
 		sql  = sql.replace("#{address}",String.valueOf(t.getAddress()));
 		sql  = sql.replace("#{seq_no}",String.valueOf(t.getSeqNo()));
 		sql  = sql.replace("#{approval_flag}",String.valueOf(t.getApprovalFlag()));
 		sql  = sql.replace("#{approval_user_id}",String.valueOf(t.getApprovalUserId()));
 		sql  = sql.replace("#{approval_date}",String.valueOf(t.getApprovalDate()));
 		sql  = sql.replace("#{approval_opinion}",String.valueOf(t.getApprovalOpinion()));
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
	public List<ZmlLoanUserContactsEntity> findContactsByApplyId(String applyId) {
		String sqlWhere = " appl_id = '"+ applyId +"'";
		String sql = "select id,create_date createDate,update_date updateDate,version version,appl_id applId,user_id userId,name name,phone phone,profession profession,"
		+" work,relation relation,id_number idNumber,age age,sex sex,address address,seq_no seqNo"
		+"from zml_loan_user_contacts t ";
		if (!sqlWhere.isEmpty()) {
			sql += " where" + sqlWhere;
		}
		sql += " order by create_date ";
		List<Map<String, Object>> list = findForJdbc(sql, null);
		ZmlLoanUserContactsEntity applyContacts = new ZmlLoanUserContactsEntity();
		List<ZmlLoanUserContactsEntity> docList = new ArrayList();
		try {
			if(list != null && list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					MyBeanUtils.copyMap2Bean(applyContacts, list.get(i));
					docList.add(applyContacts);
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return docList;
	}

}