package com.zml.loan_service.impl;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.jce.framework.core.util.ApplicationContextUtil;
import com.jce.framework.core.util.MyClassLoader;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.web.cgform.enhance.CgformEnhanceJavaInter;
import com.zml.base.loan.entity.ZmlUserRecordEntity;
import com.zml.loan_service.ZmlUserRecordServiceI;

@Service("zmlUserRecordService")
@Transactional
public class ZmlUserRecordServiceImpl extends CommonServiceImpl implements ZmlUserRecordServiceI {

	
 	public void delete(ZmlUserRecordEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ZmlUserRecordEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ZmlUserRecordEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ZmlUserRecordEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ZmlUserRecordEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ZmlUserRecordEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(ZmlUserRecordEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_date", t.getCreateDate());
		map.put("update_date", t.getUpdateDate());
		map.put("version", t.getVersion());
		map.put("apply_id", t.getApplyId());
		map.put("user_id", t.getUserId());
		map.put("real_name", t.getRealName());
		map.put("type", t.getType());
		map.put("phone", t.getPhone());
		map.put("identification_number", t.getIdentificationNumber());
		map.put("birth_day", t.getBirthDay());
		map.put("age", t.getAge());
		map.put("sex", t.getSex());
		map.put("account_location", t.getAccountLocation());
		map.put("issuing_authority", t.getIssuingAuthority());
		map.put("valid_start", t.getValidStart());
		map.put("valid_end", t.getValidEnd());
		map.put("is_verified", t.getIsVerified());
		map.put("verified_mode", t.getVerifiedMode());
		map.put("wx_nickname", t.getWxNickname());
		map.put("wx_sex", t.getWxSex());
		map.put("wx_country", t.getWxCountry());
		map.put("wx_province", t.getWxProvince());
		map.put("wx_city", t.getWxCity());
		map.put("wx_subscribe_time", t.getWxSubscribeTime());
		map.put("wx_tagid_list", t.getWxTagidList());
		map.put("wx_subscribe", t.getWxSubscribe());
		map.put("wx_language", t.getWxLanguage());
		map.put("wx_openid", t.getWxOpenid());
		map.put("wx_groupid", t.getWxGroupid());
		map.put("wx_headimgurl", t.getWxHeadimgurl());
		map.put("wx_remark", t.getWxRemark());
		map.put("account_number", t.getAccountNumber());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ZmlUserRecordEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{version}",String.valueOf(t.getVersion()));
 		sql  = sql.replace("#{apply_id}",String.valueOf(t.getApplyId()));
 		sql  = sql.replace("#{user_id}",String.valueOf(t.getUserId()));
 		sql  = sql.replace("#{real_name}",String.valueOf(t.getRealName()));
 		sql  = sql.replace("#{type}",String.valueOf(t.getType()));
 		sql  = sql.replace("#{phone}",String.valueOf(t.getPhone()));
 		sql  = sql.replace("#{identification_number}",String.valueOf(t.getIdentificationNumber()));
 		sql  = sql.replace("#{birth_day}",String.valueOf(t.getBirthDay()));
 		sql  = sql.replace("#{age}",String.valueOf(t.getAge()));
 		sql  = sql.replace("#{sex}",String.valueOf(t.getSex()));
 		sql  = sql.replace("#{account_location}",String.valueOf(t.getAccountLocation()));
 		sql  = sql.replace("#{issuing_authority}",String.valueOf(t.getIssuingAuthority()));
 		sql  = sql.replace("#{valid_start}",String.valueOf(t.getValidStart()));
 		sql  = sql.replace("#{valid_end}",String.valueOf(t.getValidEnd()));
 		sql  = sql.replace("#{is_verified}",String.valueOf(t.getIsVerified()));
 		sql  = sql.replace("#{verified_mode}",String.valueOf(t.getVerifiedMode()));
 		sql  = sql.replace("#{wx_nickname}",String.valueOf(t.getWxNickname()));
 		sql  = sql.replace("#{wx_sex}",String.valueOf(t.getWxSex()));
 		sql  = sql.replace("#{wx_country}",String.valueOf(t.getWxCountry()));
 		sql  = sql.replace("#{wx_province}",String.valueOf(t.getWxProvince()));
 		sql  = sql.replace("#{wx_city}",String.valueOf(t.getWxCity()));
 		sql  = sql.replace("#{wx_subscribe_time}",String.valueOf(t.getWxSubscribeTime()));
 		sql  = sql.replace("#{wx_tagid_list}",String.valueOf(t.getWxTagidList()));
 		sql  = sql.replace("#{wx_subscribe}",String.valueOf(t.getWxSubscribe()));
 		sql  = sql.replace("#{wx_language}",String.valueOf(t.getWxLanguage()));
 		sql  = sql.replace("#{wx_openid}",String.valueOf(t.getWxOpenid()));
 		sql  = sql.replace("#{wx_groupid}",String.valueOf(t.getWxGroupid()));
 		sql  = sql.replace("#{wx_headimgurl}",String.valueOf(t.getWxHeadimgurl()));
 		sql  = sql.replace("#{wx_remark}",String.valueOf(t.getWxRemark()));
 		sql  = sql.replace("#{account_number}",String.valueOf(t.getAccountNumber()));
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
}