package com.zml.loan_service;
import java.util.List;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.loan.entity.ZmlLoanPayDocumentEntity;
import com.zml.base.loan.entity.ZmlLoanPayLoanInfoEntity;

public interface ZmlLoanPayLoanInfoServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(ZmlLoanPayLoanInfoEntity zmlLoanPayLoanInfo,
	        List<ZmlLoanPayDocumentEntity> zmlLoanPayDocumentList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ZmlLoanPayLoanInfoEntity zmlLoanPayLoanInfo,
	        List<ZmlLoanPayDocumentEntity> zmlLoanPayDocumentList);
	public void delMain (ZmlLoanPayLoanInfoEntity zmlLoanPayLoanInfo);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ZmlLoanPayLoanInfoEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ZmlLoanPayLoanInfoEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ZmlLoanPayLoanInfoEntity t);
}
