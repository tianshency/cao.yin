package com.zml.service.impl;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.core.common.exception.BusinessException;
import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.oConvertUtils;
import com.zml.base.wrw.entity.WrwTaskInfoEntity;
import com.zml.base.wrw.entity.WrwTaskUserRelationEntity;
import com.zml.service.WrwTaskInfoServiceI;


@Service("wrwTaskInfoService")
@Transactional
public class WrwTaskInfoServiceImpl extends CommonServiceImpl implements WrwTaskInfoServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((WrwTaskInfoEntity)entity);
 	}
	
	public void addMain(WrwTaskInfoEntity wrwTaskInfo,
	        List<WrwTaskUserRelationEntity> wrwTaskUserRelationList){
			//保存主信息
			this.save(wrwTaskInfo);
		
			/**保存-任务人*/
			for(WrwTaskUserRelationEntity wrwTaskUserRelation:wrwTaskUserRelationList){
				//外键设置
				wrwTaskUserRelation.setTaskId(wrwTaskInfo.getId());
				//外键设置
				wrwTaskUserRelation.setUserId(wrwTaskInfo.getId());
				this.save(wrwTaskUserRelation);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(wrwTaskInfo);
	}

	
	public void updateMain(WrwTaskInfoEntity wrwTaskInfo,
	        List<WrwTaskUserRelationEntity> wrwTaskUserRelationList) {
		//保存主表信息
		this.saveOrUpdate(wrwTaskInfo);
		//===================================================================================
		//获取参数
		Object id0 = wrwTaskInfo.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-任务人
	    String hql0 = "from WrwTaskUserRelationEntity where 1 = 1 AND tASK_ID = ?  AND uSER_ID = ? ";
	    List<WrwTaskUserRelationEntity> wrwTaskUserRelationOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-任务人
		if(wrwTaskUserRelationList!=null&&wrwTaskUserRelationList.size()>0){
		for(WrwTaskUserRelationEntity oldE:wrwTaskUserRelationOldList){
			boolean isUpdate = false;
				for(WrwTaskUserRelationEntity sendE:wrwTaskUserRelationList){
					//需要更新的明细数据-任务人
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-任务人
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-任务人
			for(WrwTaskUserRelationEntity wrwTaskUserRelation:wrwTaskUserRelationList){
				if(oConvertUtils.isEmpty(wrwTaskUserRelation.getId())){
					//外键设置
					wrwTaskUserRelation.setTaskId(wrwTaskInfo.getId());
					wrwTaskUserRelation.setUserId(wrwTaskInfo.getId());
					this.save(wrwTaskUserRelation);
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(wrwTaskInfo);
	}

	
	public void delMain(WrwTaskInfoEntity wrwTaskInfo) {
		//删除主表信息
		this.delete(wrwTaskInfo);
		//===================================================================================
		//获取参数
		Object id0 = wrwTaskInfo.getId();
		//===================================================================================
		//删除-任务人
	    String hql0 = "from WrwTaskUserRelationEntity where 1 = 1 AND tASK_ID = ?  AND uSER_ID = ? ";
	    List<WrwTaskUserRelationEntity> wrwTaskUserRelationOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(wrwTaskUserRelationOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WrwTaskInfoEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WrwTaskInfoEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WrwTaskInfoEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,WrwTaskInfoEntity t){
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
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{amout}",String.valueOf(t.getAmout()));
 		sql  = sql.replace("#{start_date}",String.valueOf(t.getStartDate()));
 		sql  = sql.replace("#{end_date}",String.valueOf(t.getEndDate()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{remark}",String.valueOf(t.getRemark()));
 		sql  = sql.replace("#{lave_amout}",String.valueOf(t.getLaveAmout()));
 		sql  = sql.replace("#{task_url}",String.valueOf(t.getTaskUrl()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}