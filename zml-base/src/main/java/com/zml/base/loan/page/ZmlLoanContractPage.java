
package com.zml.base.loan.page;
import java.util.ArrayList;
import java.util.List;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

import com.zml.base.loan.entity.ZmlLoanContractDocumentEntity;

/**   
 * @Title: Entity
 * @Description: 借款合同
 * @author onlineGenerator
 * @date 2017-03-01 22:25:56
 * @version V1.0   
 *
 */
public class ZmlLoanContractPage implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新日期*/
	private java.util.Date updateDate;
	/**版本*/
	private java.lang.Integer version;
	/**用户*/
    @Excel(name="用户")
	private java.lang.String userId;
	/**申请*/
    @Excel(name="申请")
	private java.lang.String applId;
	/**合同号*/
    @Excel(name="合同号")
	private java.lang.String contractNo;
	/**产品*/
    @Excel(name="产品")
	private java.lang.String productId;
	/**授信额度*/
    @Excel(name="授信额度")
	private java.lang.String creditAmt;
	/**合同金额*/
    @Excel(name="合同金额")
	private java.lang.String contractAmt;
	/**合同余额*/
    @Excel(name="合同余额")
	private java.lang.String contractBalance;
	/**服务费*/
    @Excel(name="服务费")
	private java.lang.String fee;
	/**中间方*/
    @Excel(name="中间方")
	private java.lang.String intermediaries;
	/**出借人*/
    @Excel(name="出借人")
	private java.lang.String lender;
	/**利率*/
    @Excel(name="利率")
	private java.lang.String interestRate;
	/**罚息率*/
    @Excel(name="罚息率")
	private java.lang.String penaltyRate;
	/**合同路径*/
    @Excel(name="合同路径")
	private java.lang.String contractPath;
	/**还款方式*/
    @Excel(name="还款方式")
	private java.lang.String repayment;
	/**合同开始日*/
    @Excel(name="合同开始日")
	private java.lang.String startTime;
	/**合同到期日*/
    @Excel(name="合同到期日")
	private java.lang.String endTime;
	/**期限*/
    @Excel(name="期限")
	private java.lang.String term;
	/**期限单位*/
    @Excel(name="期限单位")
	private java.lang.String termUnit;
	/**逾期次数*/
    @Excel(name="逾期次数")
	private java.lang.String overTime;
	/**逾期天数*/
    @Excel(name="逾期天数")
	private java.lang.String overDays;
	/**罚息*/
    @Excel(name="罚息")
	private java.lang.String imposeInterest;
	/**滞纳金*/
    @Excel(name="滞纳金")
	private java.lang.String penalty;
	/**状态*/
    @Excel(name="状态")
	private java.lang.String status;
	/**还款状态*/
    @Excel(name="还款状态")
	private java.lang.String repayStatus;
	/**借款次数*/
    @Excel(name="借款次数")
	private java.lang.String loanFrequency;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  版本
	 */
	public java.lang.Integer getVersion(){
		return this.version;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  版本
	 */
	public void setVersion(java.lang.Integer version){
		this.version = version;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户
	 */
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户
	 */
	public void setUserId(java.lang.String userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请
	 */
	public java.lang.String getApplId(){
		return this.applId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请
	 */
	public void setApplId(java.lang.String applId){
		this.applId = applId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同号
	 */
	public java.lang.String getContractNo(){
		return this.contractNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同号
	 */
	public void setContractNo(java.lang.String contractNo){
		this.contractNo = contractNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品
	 */
	public java.lang.String getProductId(){
		return this.productId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品
	 */
	public void setProductId(java.lang.String productId){
		this.productId = productId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  授信额度
	 */
	public java.lang.String getCreditAmt(){
		return this.creditAmt;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  授信额度
	 */
	public void setCreditAmt(java.lang.String creditAmt){
		this.creditAmt = creditAmt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同金额
	 */
	public java.lang.String getContractAmt(){
		return this.contractAmt;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同金额
	 */
	public void setContractAmt(java.lang.String contractAmt){
		this.contractAmt = contractAmt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同余额
	 */
	public java.lang.String getContractBalance(){
		return this.contractBalance;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同余额
	 */
	public void setContractBalance(java.lang.String contractBalance){
		this.contractBalance = contractBalance;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  服务费
	 */
	public java.lang.String getFee(){
		return this.fee;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  服务费
	 */
	public void setFee(java.lang.String fee){
		this.fee = fee;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  中间方
	 */
	public java.lang.String getIntermediaries(){
		return this.intermediaries;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  中间方
	 */
	public void setIntermediaries(java.lang.String intermediaries){
		this.intermediaries = intermediaries;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出借人
	 */
	public java.lang.String getLender(){
		return this.lender;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出借人
	 */
	public void setLender(java.lang.String lender){
		this.lender = lender;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  利率
	 */
	public java.lang.String getInterestRate(){
		return this.interestRate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  利率
	 */
	public void setInterestRate(java.lang.String interestRate){
		this.interestRate = interestRate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  罚息率
	 */
	public java.lang.String getPenaltyRate(){
		return this.penaltyRate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  罚息率
	 */
	public void setPenaltyRate(java.lang.String penaltyRate){
		this.penaltyRate = penaltyRate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同路径
	 */
	public java.lang.String getContractPath(){
		return this.contractPath;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同路径
	 */
	public void setContractPath(java.lang.String contractPath){
		this.contractPath = contractPath;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  还款方式
	 */
	public java.lang.String getRepayment(){
		return this.repayment;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  还款方式
	 */
	public void setRepayment(java.lang.String repayment){
		this.repayment = repayment;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同开始日
	 */
	public java.lang.String getStartTime(){
		return this.startTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同开始日
	 */
	public void setStartTime(java.lang.String startTime){
		this.startTime = startTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同到期日
	 */
	public java.lang.String getEndTime(){
		return this.endTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同到期日
	 */
	public void setEndTime(java.lang.String endTime){
		this.endTime = endTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  期限
	 */
	public java.lang.String getTerm(){
		return this.term;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  期限
	 */
	public void setTerm(java.lang.String term){
		this.term = term;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  期限单位
	 */
	public java.lang.String getTermUnit(){
		return this.termUnit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  期限单位
	 */
	public void setTermUnit(java.lang.String termUnit){
		this.termUnit = termUnit;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  逾期次数
	 */
	public java.lang.String getOverTime(){
		return this.overTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  逾期次数
	 */
	public void setOverTime(java.lang.String overTime){
		this.overTime = overTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  逾期天数
	 */
	public java.lang.String getOverDays(){
		return this.overDays;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  逾期天数
	 */
	public void setOverDays(java.lang.String overDays){
		this.overDays = overDays;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  罚息
	 */
	public java.lang.String getImposeInterest(){
		return this.imposeInterest;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  罚息
	 */
	public void setImposeInterest(java.lang.String imposeInterest){
		this.imposeInterest = imposeInterest;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  滞纳金
	 */
	public java.lang.String getPenalty(){
		return this.penalty;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  滞纳金
	 */
	public void setPenalty(java.lang.String penalty){
		this.penalty = penalty;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  还款状态
	 */
	public java.lang.String getRepayStatus(){
		return this.repayStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  还款状态
	 */
	public void setRepayStatus(java.lang.String repayStatus){
		this.repayStatus = repayStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  借款次数
	 */
	public java.lang.String getLoanFrequency(){
		return this.loanFrequency;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  借款次数
	 */
	public void setLoanFrequency(java.lang.String loanFrequency){
		this.loanFrequency = loanFrequency;
	}

	/**保存-合同文档*/
    @ExcelCollection(name="合同文档")
	private List<ZmlLoanContractDocumentEntity> zmlLoanContractDocumentList = new ArrayList<ZmlLoanContractDocumentEntity>();
		public List<ZmlLoanContractDocumentEntity> getZmlLoanContractDocumentList() {
		return zmlLoanContractDocumentList;
		}
		public void setZmlLoanContractDocumentList(List<ZmlLoanContractDocumentEntity> zmlLoanContractDocumentList) {
		this.zmlLoanContractDocumentList = zmlLoanContractDocumentList;
		}
}
