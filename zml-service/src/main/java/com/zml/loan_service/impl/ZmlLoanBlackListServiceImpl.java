package com.zml.loan_service.impl;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.core.common.exception.BusinessException;
import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.oConvertUtils;
import com.zml.base.loan.entity.ZmlLoanBlackListDocumentEntity;
import com.zml.base.loan.entity.ZmlLoanBlackListEntity;
import com.zml.loan_service.ZmlLoanBlackListServiceI;


@Service("zmlLoanBlackListService")
@Transactional
public class ZmlLoanBlackListServiceImpl extends CommonServiceImpl implements ZmlLoanBlackListServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((ZmlLoanBlackListEntity)entity);
 	}
	
	public void addMain(ZmlLoanBlackListEntity zmlLoanBlackList,
	        List<ZmlLoanBlackListDocumentEntity> zmlLoanBlackListDocumentList){
			//保存主信息
			this.save(zmlLoanBlackList);
		
			/**保存-黑名单文档*/
			for(ZmlLoanBlackListDocumentEntity zmlLoanBlackListDocument:zmlLoanBlackListDocumentList){
				//外键设置
				zmlLoanBlackListDocument.setBlId(zmlLoanBlackList.getId());
				this.save(zmlLoanBlackListDocument);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(zmlLoanBlackList);
	}

	
	public void updateMain(ZmlLoanBlackListEntity zmlLoanBlackList,
	        List<ZmlLoanBlackListDocumentEntity> zmlLoanBlackListDocumentList) {
		//保存主表信息
		this.saveOrUpdate(zmlLoanBlackList);
		//===================================================================================
		//获取参数
		Object id0 = zmlLoanBlackList.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-黑名单文档
	    String hql0 = "from ZmlLoanBlackListDocumentEntity where 1 = 1 AND bL_ID = ? ";
	    List<ZmlLoanBlackListDocumentEntity> zmlLoanBlackListDocumentOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-黑名单文档
		if(zmlLoanBlackListDocumentList!=null&&zmlLoanBlackListDocumentList.size()>0){
		for(ZmlLoanBlackListDocumentEntity oldE:zmlLoanBlackListDocumentOldList){
			boolean isUpdate = false;
				for(ZmlLoanBlackListDocumentEntity sendE:zmlLoanBlackListDocumentList){
					//需要更新的明细数据-黑名单文档
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-黑名单文档
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-黑名单文档
			for(ZmlLoanBlackListDocumentEntity zmlLoanBlackListDocument:zmlLoanBlackListDocumentList){
				if(oConvertUtils.isEmpty(zmlLoanBlackListDocument.getId())){
					//外键设置
					zmlLoanBlackListDocument.setBlId(zmlLoanBlackList.getId());
					this.save(zmlLoanBlackListDocument);
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(zmlLoanBlackList);
	}

	
	public void delMain(ZmlLoanBlackListEntity zmlLoanBlackList) {
		//删除主表信息
		this.delete(zmlLoanBlackList);
		//===================================================================================
		//获取参数
		Object id0 = zmlLoanBlackList.getId();
		//===================================================================================
		//删除-黑名单文档
	    String hql0 = "from ZmlLoanBlackListDocumentEntity where 1 = 1 AND bL_ID = ? ";
	    List<ZmlLoanBlackListDocumentEntity> zmlLoanBlackListDocumentOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(zmlLoanBlackListDocumentOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ZmlLoanBlackListEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ZmlLoanBlackListEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ZmlLoanBlackListEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,ZmlLoanBlackListEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{version}",String.valueOf(t.getVersion()));
 		sql  = sql.replace("#{user_id}",String.valueOf(t.getUserId()));
 		sql  = sql.replace("#{type}",String.valueOf(t.getType()));
 		sql  = sql.replace("#{source}",String.valueOf(t.getSource()));
 		sql  = sql.replace("#{data}",String.valueOf(t.getData()));
 		sql  = sql.replace("#{remarks}",String.valueOf(t.getRemarks()));
 		sql  = sql.replace("#{approval_flag}",String.valueOf(t.getApprovalFlag()));
 		sql  = sql.replace("#{approval_user_id}",String.valueOf(t.getApprovalUserId()));
 		sql  = sql.replace("#{approval_date}",String.valueOf(t.getApprovalDate()));
 		sql  = sql.replace("#{approval_opinion}",String.valueOf(t.getApprovalOpinion()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}