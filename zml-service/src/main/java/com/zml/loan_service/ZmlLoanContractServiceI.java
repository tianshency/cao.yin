package com.zml.loan_service;
import java.util.List;
import java.util.Map;

import com.jce.framework.core.common.service.CommonService;
import com.jce.framework.web.system.pojo.base.TSUser;
import com.zml.base.loan.entity.ZmlLoanContractDocumentEntity;
import com.zml.base.loan.entity.ZmlLoanContractEntity;

public interface ZmlLoanContractServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(ZmlLoanContractEntity zmlLoanContract,
	        List<ZmlLoanContractDocumentEntity> zmlLoanContractDocumentList) ;
	
	/**
	 * 签约
	 */
	public void signedContract(ZmlLoanContractEntity zmlLoanContract) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ZmlLoanContractEntity zmlLoanContract,
	        List<ZmlLoanContractDocumentEntity> zmlLoanContractDocumentList);
	public void delMain (ZmlLoanContractEntity zmlLoanContract);
	
	/**
	 * 审批合同
	 */
	public void doApprove(ZmlLoanContractEntity zmlLoanContract, String approveUserId, String wfId,
	        List<ZmlLoanContractDocumentEntity> zmlLoanContractDocumentList) throws Exception ;
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ZmlLoanContractEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ZmlLoanContractEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ZmlLoanContractEntity t);
 	
 	/**
 	 *创建合同 
	 */
	public void createContract(ZmlLoanContractEntity zmlLoanContract,
	        List<ZmlLoanContractDocumentEntity> zmlLoanContractDocumentList) ;
	//查询用户贷款统计数据
	public Map<String, Object> findMyLoanStatistics(String userId);
	
	//查询用户合同列表
	public List<Map<String, Object>> findMyLoanList(String userId, int pagSize, int pag);
	
	//根据申请ID查询合同
	public Map<String, Object> findContractByApplyId(String applyId);
}
