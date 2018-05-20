package com.zml.base.loan.entity;
import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import com.zml.util.BigDemicalUtil;

/**   
 * @Title: Entity
 * @Description: 借款合同
 * @author onlineGenerator
 * @date 2017-03-01 22:25:56
 * @version V1.0   
 *
 */
@Entity
@Table(name = "zml_loan_contract", schema = "")
@SuppressWarnings("serial")
public class ZmlLoanContractEntity implements java.io.Serializable {
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
	private BigDecimal creditAmt;
	/**合同金额*/
    @Excel(name="合同金额")
	private BigDecimal contractAmt;
	/**合同余额*/
    @Excel(name="合同余额")
	private BigDecimal contractBalance;
	/**服务费*/
    @Excel(name="服务费")
	private BigDecimal fee;
	/**中间方*/
    @Excel(name="中间方")
	private java.lang.String intermediaries;
	/**出借人*/
    @Excel(name="出借人")
	private java.lang.String lender;
	/**利率*/
    @Excel(name="利率")
	private BigDecimal interestRate;
	/**罚息率*/
    @Excel(name="罚息率")
	private BigDecimal penaltyRate;
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
	private java.lang.Integer term;
	/**期限单位*/
    @Excel(name="期限单位")
	private java.lang.Integer termUnit;
	/**逾期次数*/
    @Excel(name="逾期次数")
	private java.lang.Integer overTime;
	/**逾期天数*/
    @Excel(name="逾期天数")
	private java.lang.Integer overDays;
	/**罚息*/
    @Excel(name="罚息")
	private BigDecimal imposeInterest;
	/**滞纳金*/
    @Excel(name="滞纳金")
	private BigDecimal penalty;
	/**状态*/
    @Excel(name="状态")
	private java.lang.String status;
	/**还款状态*/
    @Excel(name="还款状态")
	private java.lang.String repayStatus;
	/**借款次数*/
    @Excel(name="借款次数")
	private java.lang.Integer loanFrequency;
    
    private java.lang.String  isSigned;//是否签约
    private Date  signedDay;//签约日期
    private Date  payDay;//放款日期
    private java.lang.String  paymentType;//付款类型
    private java.lang.String  userName;//收款人姓名
    private java.lang.String  bankName;//银行名称
    private java.lang.String  bankAddressProvince;//银行省份
    private java.lang.String  bankAddressCity;//银行城市
    private java.lang.String  bankBranch;//银行
    private java.lang.String  accountNumber;//账号
    private java.lang.String  tradeNo;//交易流水号
    private java.lang.String  remarks;//备注
    private java.lang.String  operatorId;//操作人ID
    private java.lang.String  approvalFlag;//审批标识
    private java.lang.String  approvalUserId;//审批人
    private Date  approvalDate;//审批时间
    private java.lang.String  approvalOpinion;//审批意见
    private java.lang.String  fiveClassification;//	五级分类
    
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	
	@Column(name ="ID",nullable=false,length=36)
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
	
	@Column(name ="CREATE_DATE",nullable=true,length=20)
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
	
	@Column(name ="UPDATE_DATE",nullable=true,length=20)
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
	
	@Column(name ="VERSION",nullable=true,length=5)
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
	
	@Column(name ="USER_ID",nullable=false,length=36)
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
	
	@Column(name ="APPL_ID",nullable=false,length=36)
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
	
	@Column(name ="CONTRACT_NO",nullable=true,length=50)
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
	
	@Column(name ="PRODUCT_ID",nullable=true,length=32)
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
	
	@Column(name ="CREDIT_AMT",nullable=true,length=32)
	public BigDecimal getCreditAmt(){
		if(this.creditAmt ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.creditAmt;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  授信额度
	 */
	public void setCreditAmt(BigDecimal creditAmt){
		this.creditAmt = creditAmt;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同金额
	 */
	
	@Column(name ="CONTRACT_AMT",nullable=true,length=32)
	public BigDecimal getContractAmt(){
		if(this.contractAmt ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.contractAmt;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同金额
	 */
	public void setContractAmt(BigDecimal contractAmt){
		this.contractAmt = contractAmt;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同余额
	 */
	
	@Column(name ="CONTRACT_BALANCE",nullable=true,length=32)
	public BigDecimal getContractBalance(){
		if(this.contractBalance ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.contractBalance;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同余额
	 */
	public void setContractBalance(BigDecimal contractBalance){
		this.contractBalance = contractBalance;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  服务费
	 */
	
	@Column(name ="FEE",nullable=true,length=32)
	public BigDecimal getFee(){
		if(this.fee ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.fee;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  服务费
	 */
	public void setFee(BigDecimal fee){
		this.fee = fee;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  中间方
	 */
	
	@Column(name ="INTERMEDIARIES",nullable=true,length=32)
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
	
	@Column(name ="LENDER",nullable=true,length=32)
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
	
	@Column(name ="INTEREST_RATE",nullable=true,length=32)
	public BigDecimal getInterestRate(){
		if(this.interestRate ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.interestRate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  利率
	 */
	public void setInterestRate(BigDecimal interestRate){
		this.interestRate = interestRate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  罚息率
	 */
	
	@Column(name ="PENALTY_RATE",nullable=true,length=32)
	public BigDecimal getPenaltyRate(){
		if(this.penaltyRate ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.penaltyRate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  罚息率
	 */
	public void setPenaltyRate(BigDecimal penaltyRate){
		this.penaltyRate = penaltyRate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同路径
	 */
	
	@Column(name ="CONTRACT_PATH",nullable=true,length=32)
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
	
	@Column(name ="REPAYMENT",nullable=true,length=32)
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
	
	@Column(name ="START_TIME",nullable=true,length=32)
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
	
	@Column(name ="END_TIME",nullable=true,length=32)
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
	
	@Column(name ="TERM",nullable=true,length=32)
	public java.lang.Integer getTerm(){
		return this.term;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  期限
	 */
	public void setTerm(java.lang.Integer term){
		this.term = term;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  期限单位
	 */
	
	@Column(name ="TERM_UNIT",nullable=true,length=32)
	public java.lang.Integer getTermUnit(){
		return this.termUnit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  期限单位
	 */
	public void setTermUnit(java.lang.Integer termUnit){
		this.termUnit = termUnit;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  逾期次数
	 */
	
	@Column(name ="OVER_TIME",nullable=true,length=32)
	public java.lang.Integer getOverTime(){
		return this.overTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  逾期次数
	 */
	public void setOverTime(java.lang.Integer overTime){
		this.overTime = overTime;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  逾期天数
	 */
	
	@Column(name ="OVER_DAYS",nullable=true,length=32)
	public java.lang.Integer getOverDays(){
		return this.overDays;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  逾期天数
	 */
	public void setOverDays(java.lang.Integer overDays){
		this.overDays = overDays;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  罚息
	 */
	
	@Column(name ="IMPOSE_INTEREST",nullable=true,length=32)
	public BigDecimal getImposeInterest(){
		if(this.imposeInterest ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.imposeInterest;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  罚息
	 */
	public void setImposeInterest(BigDecimal imposeInterest){
		this.imposeInterest = imposeInterest;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  滞纳金
	 */
	
	@Column(name ="PENALTY",nullable=true,length=32)
	public BigDecimal getPenalty(){
		if(this.penalty ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.penalty;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  滞纳金
	 */
	public void setPenalty(BigDecimal penalty){
		this.penalty = penalty;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	
	@Column(name ="STATUS",nullable=true,length=32)
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
	
	@Column(name ="REPAY_STATUS",nullable=true,length=32)
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
	
	@Column(name ="LOAN_FREQUENCY",nullable=true,length=32)
	public java.lang.Integer getLoanFrequency(){
		return this.loanFrequency;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.Integer  借款次数
	 */
	public void setLoanFrequency(java.lang.Integer loanFrequency){
		this.loanFrequency = loanFrequency;
	}
	@Column(name ="is_signed",nullable=true)
	public java.lang.String getIsSigned() {
		return isSigned;
	}

	public void setIsSigned(java.lang.String isSigned) {
		this.isSigned = isSigned;
	}
	@Column(name ="signed_day",nullable=true)
	public Date getSignedDay() {
		return signedDay;
	}

	public void setSignedDay(Date signedDay) {
		this.signedDay = signedDay;
	}
	@Column(name ="pay_day",nullable=true)
	public Date getPayDay() {
		return payDay;
	}

	public void setPayDay(Date payDay) {
		this.payDay = payDay;
	}
	@Column(name ="payment_type",nullable=true)
	public java.lang.String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(java.lang.String paymentType) {
		this.paymentType = paymentType;
	}
	@Column(name ="bank_name",nullable=true)
	public java.lang.String getBankName() {
		return bankName;
	}

	public void setBankName(java.lang.String bankName) {
		this.bankName = bankName;
	}
	@Column(name ="bank_address_province",nullable=true)
	public java.lang.String getBankAddressProvince() {
		return bankAddressProvince;
	}

	public void setBankAddressProvince(java.lang.String bankAddressProvince) {
		this.bankAddressProvince = bankAddressProvince;
	}
	@Column(name ="bank_address_city",nullable=true)
	public java.lang.String getBankAddressCity() {
		return bankAddressCity;
	}

	public void setBankAddressCity(java.lang.String bankAddressCity) {
		this.bankAddressCity = bankAddressCity;
	}
	@Column(name ="bank_branch",nullable=true)
	public java.lang.String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(java.lang.String bankBranch) {
		this.bankBranch = bankBranch;
	}
	@Column(name ="account_number",nullable=true)
	public java.lang.String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(java.lang.String accountNumber) {
		this.accountNumber = accountNumber;
	}
	@Column(name ="trade_no",nullable=true)
	public java.lang.String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(java.lang.String tradeNo) {
		this.tradeNo = tradeNo;
	}
	@Column(name ="remarks",nullable=true)
	public java.lang.String getRemarks() {
		return remarks;
	}

	public void setRemarks(java.lang.String remarks) {
		this.remarks = remarks;
	}
	@Column(name ="operator_id",nullable=true)
	public java.lang.String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(java.lang.String operatorId) {
		this.operatorId = operatorId;
	}
	@Column(name ="approval_flag",nullable=true)
	public java.lang.String getApprovalFlag() {
		return approvalFlag;
	}

	public void setApprovalFlag(java.lang.String approvalFlag) {
		this.approvalFlag = approvalFlag;
	}
	@Column(name ="approval_user_id",nullable=true)
	public java.lang.String getApprovalUserId() {
		return approvalUserId;
	}

	public void setApprovalUserId(java.lang.String approvalUserId) {
		this.approvalUserId = approvalUserId;
	}
	@Column(name ="approval_date",nullable=true)
	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}
	@Column(name ="approval_opinion",nullable=true)
	public java.lang.String getApprovalOpinion() {
		return approvalOpinion;
	}

	public void setApprovalOpinion(java.lang.String approvalOpinion) {
		this.approvalOpinion = approvalOpinion;
	}
	@Column(name ="user_name",nullable=true)
	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}
	@Column(name ="five_classification",nullable=true)
	public java.lang.String getFiveClassification() {
		return fiveClassification;
	}

	public void setFiveClassification(java.lang.String fiveClassification) {
		this.fiveClassification = fiveClassification;
	}
	
	
}
