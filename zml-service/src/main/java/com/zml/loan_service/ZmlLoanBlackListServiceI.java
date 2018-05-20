package com.zml.loan_service;
import java.util.List;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.loan.entity.ZmlLoanBlackListDocumentEntity;
import com.zml.base.loan.entity.ZmlLoanBlackListEntity;

public interface ZmlLoanBlackListServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(ZmlLoanBlackListEntity zmlLoanBlackList,
	        List<ZmlLoanBlackListDocumentEntity> zmlLoanBlackListDocumentList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ZmlLoanBlackListEntity zmlLoanBlackList,
	        List<ZmlLoanBlackListDocumentEntity> zmlLoanBlackListDocumentList);
	public void delMain (ZmlLoanBlackListEntity zmlLoanBlackList);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ZmlLoanBlackListEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ZmlLoanBlackListEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ZmlLoanBlackListEntity t);
}
