package com.zml.loan_service.impl;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.core.common.exception.BusinessException;
import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.oConvertUtils;
import com.zml.base.loan.entity.ZmlLoanPayDocumentEntity;
import com.zml.base.loan.entity.ZmlLoanPayLoanInfoEntity;
import com.zml.common.Constant;
import com.zml.enums.loan.LoanNoPrefix;
import com.zml.loan_service.ZmlLoanPayLoanInfoServiceI;
import com.zml.util.DateUtil;
import com.zml.util.GenerateNo;


@Service("zmlLoanPayLoanInfoService")
@Transactional
public class ZmlLoanPayLoanInfoServiceImpl extends CommonServiceImpl implements ZmlLoanPayLoanInfoServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((ZmlLoanPayLoanInfoEntity)entity);
 	}
	
	public void addMain(ZmlLoanPayLoanInfoEntity zmlLoanPayLoanInfo,
	    List<ZmlLoanPayDocumentEntity> zmlLoanPayDocumentList){
			//保存主信息
		String no = LoanNoPrefix.PAY_NO_PREF.getStatusValue()+ DateUtil.getNumberDateTime() + GenerateNo.getRandomNum(Constant.BIZ_NO_LEN);
		zmlLoanPayLoanInfo.setPayLoanNum(no);
		this.save(zmlLoanPayLoanInfo);
	
		/**保存-放款文档*/
		for(ZmlLoanPayDocumentEntity zmlLoanPayDocument:zmlLoanPayDocumentList){
			//外键设置
			zmlLoanPayDocument.setPayId(zmlLoanPayLoanInfo.getId());
			this.save(zmlLoanPayDocument);
		}
		//查询合同信息
		
		//执行新增操作配置的sql增强
		this.doAddSql(zmlLoanPayLoanInfo);
	}

	
	public void updateMain(ZmlLoanPayLoanInfoEntity zmlLoanPayLoanInfo,
	        List<ZmlLoanPayDocumentEntity> zmlLoanPayDocumentList) {
		//保存主表信息
		this.saveOrUpdate(zmlLoanPayLoanInfo);
		//===================================================================================
		//获取参数
		Object id0 = zmlLoanPayLoanInfo.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-放款文档
	    String hql0 = "from ZmlLoanPayDocumentEntity where 1 = 1 AND pAY_ID = ? ";
	    List<ZmlLoanPayDocumentEntity> zmlLoanPayDocumentOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-放款文档
		if(zmlLoanPayDocumentList!=null&&zmlLoanPayDocumentList.size()>0){
		for(ZmlLoanPayDocumentEntity oldE:zmlLoanPayDocumentOldList){
			boolean isUpdate = false;
				for(ZmlLoanPayDocumentEntity sendE:zmlLoanPayDocumentList){
					//需要更新的明细数据-放款文档
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-放款文档
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-放款文档
			for(ZmlLoanPayDocumentEntity zmlLoanPayDocument:zmlLoanPayDocumentList){
				if(oConvertUtils.isEmpty(zmlLoanPayDocument.getId())){
					//外键设置
					zmlLoanPayDocument.setPayId(zmlLoanPayLoanInfo.getId());
					this.save(zmlLoanPayDocument);
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(zmlLoanPayLoanInfo);
	}

	
	public void delMain(ZmlLoanPayLoanInfoEntity zmlLoanPayLoanInfo) {
		//删除主表信息
		this.delete(zmlLoanPayLoanInfo);
		//===================================================================================
		//获取参数
		Object id0 = zmlLoanPayLoanInfo.getId();
		//===================================================================================
		//删除-放款文档
	    String hql0 = "from ZmlLoanPayDocumentEntity where 1 = 1 AND pAY_ID = ? ";
	    List<ZmlLoanPayDocumentEntity> zmlLoanPayDocumentOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(zmlLoanPayDocumentOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ZmlLoanPayLoanInfoEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ZmlLoanPayLoanInfoEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ZmlLoanPayLoanInfoEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,ZmlLoanPayLoanInfoEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{version}",String.valueOf(t.getVersion()));
 		sql  = sql.replace("#{contract_id}",String.valueOf(t.getContractId()));
 		sql  = sql.replace("#{appl_id}",String.valueOf(t.getApplId()));
 		sql  = sql.replace("#{user_id}",String.valueOf(t.getUserId()));
 		sql  = sql.replace("#{pay_loan_num}",String.valueOf(t.getPayLoanNum()));
 		sql  = sql.replace("#{product_id}",String.valueOf(t.getProductId()));
 		sql  = sql.replace("#{operator_id}",String.valueOf(t.getOperatorId()));
 		sql  = sql.replace("#{currency}",String.valueOf(t.getCurrency()));
 		sql  = sql.replace("#{pay_date}",String.valueOf(t.getPayDate()));
 		sql  = sql.replace("#{arrive_date}",String.valueOf(t.getArriveDate()));
 		sql  = sql.replace("#{contract_rate}",String.valueOf(t.getContractRate()));
 		sql  = sql.replace("#{overduerate}",String.valueOf(t.getOverduerate()));
 		sql  = sql.replace("#{contract_amt}",String.valueOf(t.getContractAmt()));
 		sql  = sql.replace("#{fee}",String.valueOf(t.getFee()));
 		sql  = sql.replace("#{pay_amt}",String.valueOf(t.getPayAmt()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{pay_loan_index}",String.valueOf(t.getPayLoanIndex()));
 		sql  = sql.replace("#{is_upload}",String.valueOf(t.getIsUpload()));
 		sql  = sql.replace("#{loan_premium}",String.valueOf(t.getLoanPremium()));
 		sql  = sql.replace("#{payment_type}",String.valueOf(t.getPaymentType()));
 		sql  = sql.replace("#{bank_name}",String.valueOf(t.getBankName()));
 		sql  = sql.replace("#{bank_address_province}",String.valueOf(t.getBankAddressProvince()));
 		sql  = sql.replace("#{bank_address_city}",String.valueOf(t.getBankAddressCity()));
 		sql  = sql.replace("#{bank_branch}",String.valueOf(t.getBankBranch()));
 		sql  = sql.replace("#{account_number}",String.valueOf(t.getAccountNumber()));
 		sql  = sql.replace("#{trade_no}",String.valueOf(t.getTradeNo()));
 		sql  = sql.replace("#{remarks}",String.valueOf(t.getRemarks()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}