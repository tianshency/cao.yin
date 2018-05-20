package com.zml.service.impl;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.jce.framework.core.util.ApplicationContextUtil;
import com.jce.framework.core.util.MyClassLoader;
import com.jce.framework.core.util.PasswordUtil;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.web.cgform.enhance.CgformEnhanceJavaInter;
import com.jce.framework.web.system.service.SystemService;
import com.zml.base.entity.ZmlUserEntity;
import com.zml.base.wrw.entity.WrwBonusRecord;
import com.zml.base.wrw.entity.WrwRecommendRecord;
import com.zml.base.wrw.entity.WrwRegisteredLogEntity;
import com.zml.common.Constant;
import com.zml.enums.YesOrNoStr;
import com.zml.enums.wrw.BonusStatus;
import com.zml.enums.wrw.BonusType;
import com.zml.enums.wrw.BonusTypeAmount;
import com.zml.enums.wrw.RegeditSts;
import com.zml.service.WrwRegisteredLogServiceI;
import com.zml.util.GenerateNo;
import com.zml.util.file.ImageTools;

@Service("wrwRegisteredLogService")
@Transactional
public class WrwRegisteredLogServiceImpl extends CommonServiceImpl implements WrwRegisteredLogServiceI {
	
 	public void delete(WrwRegisteredLogEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(WrwRegisteredLogEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(WrwRegisteredLogEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(WrwRegisteredLogEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(WrwRegisteredLogEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(WrwRegisteredLogEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(WrwRegisteredLogEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_date", t.getCreateDate());
		map.put("update_date", t.getUpdateDate());
		map.put("version", t.getVersion());
		map.put("phone", t.getPhone());
		map.put("task_id", t.getTaskId());
		map.put("source", t.getSource());
		map.put("satus", t.getSatus());
		map.put("request_info", t.getRequestInfo());
		map.put("remark", t.getRemark());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,WrwRegisteredLogEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{version}",String.valueOf(t.getVersion()));
 		sql  = sql.replace("#{phone}",String.valueOf(t.getPhone()));
 		sql  = sql.replace("#{task_id}",String.valueOf(t.getTaskId()));
 		sql  = sql.replace("#{source}",String.valueOf(t.getSource()));
 		sql  = sql.replace("#{satus}",String.valueOf(t.getSatus()));
 		sql  = sql.replace("#{request_info}",String.valueOf(t.getRequestInfo()));
 		sql  = sql.replace("#{remark}",String.valueOf(t.getRemark()));
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
	public boolean sendPhone(WrwRegisteredLogEntity wrwRegisteredLog, Map<String, String> param) throws Exception {
		boolean rs = true;
		try {
			String hql0 = "from ZmlUserEntity where 1 = 1 AND phone = ?";
			//systemService.findo
	    	List<ZmlUserEntity> userList = findHql(hql0,wrwRegisteredLog.getPhone());
	    	if(userList != null && userList.size() > 0){
	    		//已经注册过
	    		wrwRegisteredLog.setSatus(RegeditSts.R_OLD.getValue());
	    		wrwRegisteredLog.setRemark(userList.get(0).getId());
	    		rs = false;
	    	}else{
	    		//没有注册过
	    		wrwRegisteredLog.setSatus(RegeditSts.R_ING.getValue());
	    		ZmlUserEntity user = new ZmlUserEntity();
	    		user.setPhone(wrwRegisteredLog.getPhone());
	    		user.setCreateDate(new Date());
	    		user.setSource(wrwRegisteredLog.getSource());
	    		user.setRecommended(wrwRegisteredLog.getRecommended());
	    		save(user);
	    		wrwRegisteredLog.setUserId(user.getId());
	    	}
	    	wrwRegisteredLog.setCreateDate(new Date());
	    	wrwRegisteredLog.setToken(param.get("token"));
			save(wrwRegisteredLog);
		} catch (Exception e) {
			e.printStackTrace();
			rs = false;
		}
		return rs;
	}

	@Override
	public boolean doRegedit(Map<String, String> param) throws Exception {
		boolean rs = true;
		try {
			String hql0 = "from WrwRegisteredLogEntity where 1 = 1 AND token = ?";
			//systemService.findo
	    	List<WrwRegisteredLogEntity> rlList = findHql(hql0, param.get("token"));
	    	if(rlList != null && rlList.size() > 0){
	    		WrwRegisteredLogEntity entity = rlList.get(0);
	    		entity.setSatus(RegeditSts.R_SUCC.getValue());
	    		entity.setUpdateDate(new Date());
	    		saveOrUpdate(entity);
	    		//增加推荐记录
	    		if(entity.getRecommended() != null){
	    			//给本人生成 推荐码，增加密码
	    			ZmlUserEntity currUser = getEntity(ZmlUserEntity.class, entity.getUserId());
	    			String recommendCode = PasswordUtil.getMD5Pwd(currUser.getPhone());
	    			currUser.setRecommendCode(recommendCode);
	    			currUser.setUpdateDate(new Date());
		    		String url = Constant.SDZJ_URL + "?tjr=" + recommendCode + "&tid=sdzj171111";
					// 生成二维码
					String qRCodePath = ImageTools.createQRCode(currUser.getId(), url);
					currUser.setqRCodePath(qRCodePath);
					String phone = currUser.getPhone();
					String password = PasswordUtil.encrypt(phone, phone.substring(5), PasswordUtil.getStaticSalt());
					currUser.setPassword(password);
					saveOrUpdate(currUser);
	    			//查询推荐人 
					if(entity.getRecommended() != null && !"".equals(entity.getRecommended())){
						//一级推荐人
						String sql = "select id, bonus,phone from zml_user where 1 = 1 AND recommend_code = '"+ entity.getRecommended() +"'";
		    			Map<String, Object> rsMap = findOneForJdbc(sql, null);
		    			if(rsMap != null && rsMap.get("id") != null){
		    				String userId = (String)rsMap.get("id");
		    				WrwRecommendRecord recommendRecord =new WrwRecommendRecord();
			    			recommendRecord.setCreateDate(new Date());
			    			recommendRecord.setIsBonus(YesOrNoStr.NO.getStatusValue());
			    			//recommendRecord.setCurrUser(entity.getUserId());
			    			recommendRecord.setCurrUser(phone);
			    			recommendRecord.setRecommenUser((String)rsMap.get("phone"));
			    			if(Constant.SDZJ_BOUNS_S_T_F){
			    				//得到二级三级推荐人
				    			Map<String,String> map = getRecommendId(userId,(String)rsMap.get("phone"));
				    			if(map!=null && map.size()>0){
				    				recommendRecord.setSecondUser(map.get("second"));
				    				recommendRecord.setThirdUser(map.get("thrid"));
				    			}
			    			}
			    			
			    			recommendRecord.setType(BonusType.T_ONE.getValue());
			    			save(recommendRecord);
			    			
			    			//给推荐人增加推荐 奖励记录
			    			WrwBonusRecord bonusRecord = new WrwBonusRecord();
			    			bonusRecord.setUserId(userId);
			    			bonusRecord.setOldAmount((BigDecimal)rsMap.get("bonus"));
			    			bonusRecord.setAmount(new BigDecimal(BonusTypeAmount.T_ONE.getValue()));
			    			bonusRecord.setType(BonusType.T_ONE.getValue());
			    			bonusRecord.setStatus(BonusStatus.ADD.getValue());
			    			bonusRecord.setCreateDate(new Date());
			    			save(bonusRecord);
			    			
			    			//增加用户奖金
			    			ZmlUserEntity user = getEntity(ZmlUserEntity.class, userId);
			    			
			    			BigDecimal userBonus = user.getBonus();
			    			if(userBonus == null){
			    				userBonus = new BigDecimal("0.00");
			    			}
			    			BigDecimal balance = user.getBalance();
			    			if(balance == null){
			    				balance = new BigDecimal("0.00");
			    			}
			    			user.setBonus(userBonus.add(new BigDecimal(BonusTypeAmount.T_ONE.getValue())));
			    			user.setBalance(balance.add(new BigDecimal(BonusTypeAmount.T_ONE.getValue())));
			    			user.setUpdateDate(new Date());
			    			saveOrUpdate(user);
		    			}
					}
	    		}
	    	}else{
	    		WrwRegisteredLogEntity wrwRegisteredLog = new WrwRegisteredLogEntity();
	    		wrwRegisteredLog.setPhone(param.get("phone"));
	    		wrwRegisteredLog.setSatus(RegeditSts.R_FLASE.getValue());
	    		wrwRegisteredLog.setRemark("注册失败 没有查询到 主信息......");
	    		wrwRegisteredLog.setCreateDate(new Date());
				save(wrwRegisteredLog);
				rs = false;
	    	}
		} catch (Exception e) {
			e.printStackTrace();
			rs = false;
		}
		return rs;
	}
	
	/**
	 * 查询二级或三级推荐人 并给予奖励
	 * @return
	 * @param id:一级推荐者用户的id
	 * @param phone：一级推荐都用户的电话
	 */
	public Map<String,String> getRecommendId(String id,String phone){
		System.out.println("我来了--------------"+phone+"====id==="+id);
		Map<String,String> map = new HashMap<String,String>();
		String hql0 = "from ZmlUserEntity where 1 = 1 AND id = ?";
		String hql1 = "from ZmlUserEntity where 1 = 1 AND phone = ?";
		String hql2 = "from WrwRecommendRecord where currUser = ?"; //查询二、三级推荐人
		List<ZmlUserEntity> userList = findHql(hql0, id);
		if(userList!=null && userList.size()>0){
			ZmlUserEntity user = userList.get(0);
			//查找二级推荐人及三级
			List<WrwRecommendRecord> recList1 = findHql(hql2, phone);
			System.out.println("我来查找上一级");
			if(recList1!=null && recList1.size()>0){
				System.out.println("我来查找二三级");
				WrwRecommendRecord sr = recList1.get(0);
				map.put("second",sr.getRecommenUser());//-级直推人
				map.put("thrid",sr.getSecondUser()); //一级的二级推荐的
				System.out.println("我来查找二三级"+sr.getRecommenUser()+"三级："+sr.getSecondUser());
				//给二级推荐人奖励
				userList = findHql(hql1,sr.getRecommenUser());
				if(userList!=null && userList.size()>0){
					user = userList.get(0);
					BigDecimal oldBonus = user.getBonus();
					user.setBonus(user.getBonus().add(new BigDecimal(BonusTypeAmount.T_TWO.getValue())));
					user.setBalance(user.getBalance().add(new BigDecimal(BonusTypeAmount.T_TWO.getValue())));
	    			user.setUpdateDate(new Date());
					addRecondBonds(user.getId(),new BigDecimal(BonusTypeAmount.T_TWO.getValue()),oldBonus,BonusType.T_TWO.getValue());
				}
				
				//给三极推荐人奖励
				if(sr.getSecondUser()!=null && sr.getSecondUser().trim().length()>0){
					userList = findHql(hql1,sr.getRecommenUser());
					if(userList!=null && userList.size()>0){
						user = userList.get(0);
						BigDecimal oldBonus  = user.getBonus();
						user.setBonus(user.getBonus().add(new BigDecimal(BonusTypeAmount.T_THREE.getValue())));
						user.setBalance(user.getBalance().add(new BigDecimal(BonusTypeAmount.T_THREE.getValue())));
		    			user.setUpdateDate(new Date());
						addRecondBonds(user.getId(),new BigDecimal(BonusTypeAmount.T_THREE.getValue()),oldBonus,BonusType.T_THREE.getValue());
					}
				}
			}
		}
		
		return map;
	}
	
	
	private void addRecondBonds(String userId,BigDecimal bonus,BigDecimal oldBonus,String type){
		//给推荐人增加推荐 奖励记录
		WrwBonusRecord bonusRecord = new WrwBonusRecord();
		bonusRecord.setUserId(userId);
		bonusRecord.setOldAmount(oldBonus);
		bonusRecord.setAmount(bonus);
		bonusRecord.setType(type);
		bonusRecord.setStatus(BonusStatus.ADD.getValue());
		bonusRecord.setCreateDate(new Date());
		save(bonusRecord);
	}
}