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

/**   
 * @Title: Entity
 * @Description: 放款信息
 * @author onlineGenerator
 * @date 2017-03-01 22:26:48
 * @version V1.0   
 *
 */
@Entity
@Table(name = "zml_loan_pay_loan_info", schema = "")
@SuppressWarnings("serial")
public class ZmlLoanPayLoanInfoEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新日期*/
	private java.util.Date updateDate;
	/**版本*/
	private java.lang.Integer version;
	/**合同*/
    @Excel(name="合同")
	private java.lang.String contractId;
	/**申请*/
    @Excel(name="申请")
	private java.lang.String applId;
	/**用户*/
    @Excel(name="用户")
	private java.lang.String userId;
	/**放款编号*/
    @Excel(name="放款编号")
	private java.lang.String payLoanNum;
	/**产品*/
    @Excel(name="产品")
	private java.lang.String productId;
	/**操作人ID*/
    @Excel(name="操作人ID")
	private java.lang.String operatorId;
	/**币种*/
    @Excel(name="币种")
	private java.lang.String currency;
	/**放款日*/
    @Excel(name="放款日",format = "yyyy-MM-dd")
	private java.util.Date payDate;
	/**到期日*/
    @Excel(name="到期日",format = "yyyy-MM-dd")
	private java.util.Date arriveDate;
	/**利率*/
    @Excel(name="利率")
	private java.math.BigDecimal contractRate;
	/**逾期利率*/
    @Excel(name="逾期利率")
	private java.math.BigDecimal overduerate;
	/**合同金额*/
    @Excel(name="合同金额")
	private java.math.BigDecimal contractAmt;
	/**服务费*/
    @Excel(name="服务费")
	private java.math.BigDecimal fee;
	/**放款金额*/
    @Excel(name="放款金额")
	private java.math.BigDecimal payAmt;
	/**状态*/
    @Excel(name="状态")
	private java.lang.String status;
	/**放款序号*/
    @Excel(name="放款序号")
	private java.lang.Integer payLoanIndex;
	/**是否上传借据*/
    @Excel(name="是否上传借据")
	private java.lang.String isUpload;
	/**额外费用*/
    @Excel(name="额外费用")
	private java.math.BigDecimal loanPremium;
	/**付款类型*/
    @Excel(name="付款类型")
	private java.lang.String paymentType;
	/**银行名称*/
    @Excel(name="银行名称")
	private java.lang.String bankName;
	/**银行省份*/
    @Excel(name="银行省份")
	private java.lang.String bankAddressProvince;
	/**银行城市*/
    @Excel(name="银行城市")
	private java.lang.String bankAddressCity;
	/**银行*/
    @Excel(name="银行")
	private java.lang.String bankBranch;
	/**账号*/
    @Excel(name="账号")
	private java.lang.String accountNumber;
	/**交易流水号*/
    @Excel(name="交易流水号")
	private java.lang.String tradeNo;
	/**备注*/
    @Excel(name="备注")
	private java.lang.String remarks;
	
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
	 *@return: java.lang.String  合同
	 */
	
	@Column(name ="CONTRACT_ID",nullable=false,length=36)
	public java.lang.String getContractId(){
		return this.contractId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同
	 */
	public void setContractId(java.lang.String contractId){
		this.contractId = contractId;
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
	 *@return: java.lang.String  放款编号
	 */
	
	@Column(name ="PAY_LOAN_NUM",nullable=true,length=30)
	public java.lang.String getPayLoanNum(){
		return this.payLoanNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  放款编号
	 */
	public void setPayLoanNum(java.lang.String payLoanNum){
		this.payLoanNum = payLoanNum;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品
	 */
	
	@Column(name ="PRODUCT_ID",nullable=true,length=30)
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
	 *@return: java.lang.String  操作人ID
	 */
	
	@Column(name ="OPERATOR_ID",nullable=true,length=36)
	public java.lang.String getOperatorId(){
		return this.operatorId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  操作人ID
	 */
	public void setOperatorId(java.lang.String operatorId){
		this.operatorId = operatorId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  币种
	 */
	
	@Column(name ="CURRENCY",nullable=true,length=5)
	public java.lang.String getCurrency(){
		return this.currency;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  币种
	 */
	public void setCurrency(java.lang.String currency){
		this.currency = currency;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  放款日
	 */
	
	@Column(name ="PAY_DATE",nullable=true,length=10)
	public java.util.Date getPayDate(){
		return this.payDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  放款日
	 */
	public void setPayDate(java.util.Date payDate){
		this.payDate = payDate;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  到期日
	 */
	
	@Column(name ="ARRIVE_DATE",nullable=true,length=10)
	public java.util.Date getArriveDate(){
		return this.arriveDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  到期日
	 */
	public void setArriveDate(java.util.Date arriveDate){
		this.arriveDate = arriveDate;
	}
	
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  利率
	 */
	
	@Column(name ="CONTRACT_RATE",nullable=true,scale=6,length=14)
	public java.math.BigDecimal getContractRate(){
		return this.contractRate;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  利率
	 */
	public void setContractRate(java.math.BigDecimal contractRate){
		this.contractRate = contractRate;
	}
	
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  逾期利率
	 */
	
	@Column(name ="OVERDUERATE",nullable=true,scale=6,length=14)
	public java.math.BigDecimal getOverduerate(){
		return this.overduerate;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  逾期利率
	 */
	public void setOverduerate(java.math.BigDecimal overduerate){
		this.overduerate = overduerate;
	}
	
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  合同金额
	 */
	
	@Column(name ="CONTRACT_AMT",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getContractAmt(){
		return this.contractAmt;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  合同金额
	 */
	public void setContractAmt(java.math.BigDecimal contractAmt){
		this.contractAmt = contractAmt;
	}
	
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  服务费
	 */
	
	@Column(name ="FEE",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getFee(){
		return this.fee;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  服务费
	 */
	public void setFee(java.math.BigDecimal fee){
		this.fee = fee;
	}
	
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  放款金额
	 */
	
	@Column(name ="PAY_AMT",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getPayAmt(){
		return this.payAmt;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  放款金额
	 */
	public void setPayAmt(java.math.BigDecimal payAmt){
		this.payAmt = payAmt;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	
	@Column(name ="STATUS",nullable=true,length=1)
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  放款序号
	 */
	
	@Column(name ="PAY_LOAN_INDEX",nullable=true,length=2)
	public java.lang.Integer getPayLoanIndex(){
		return this.payLoanIndex;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  放款序号
	 */
	public void setPayLoanIndex(java.lang.Integer payLoanIndex){
		this.payLoanIndex = payLoanIndex;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否上传借据
	 */
	
	@Column(name ="IS_UPLOAD",nullable=true,length=1)
	public java.lang.String getIsUpload(){
		return this.isUpload;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否上传借据
	 */
	public void setIsUpload(java.lang.String isUpload){
		this.isUpload = isUpload;
	}
	
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  额外费用
	 */
	
	@Column(name ="LOAN_PREMIUM",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getLoanPremium(){
		return this.loanPremium;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  额外费用
	 */
	public void setLoanPremium(java.math.BigDecimal loanPremium){
		this.loanPremium = loanPremium;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  付款类型
	 */
	
	@Column(name ="PAYMENT_TYPE",nullable=true,length=2)
	public java.lang.String getPaymentType(){
		return this.paymentType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  付款类型
	 */
	public void setPaymentType(java.lang.String paymentType){
		this.paymentType = paymentType;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  银行名称
	 */
	
	@Column(name ="BANK_NAME",nullable=true,length=50)
	public java.lang.String getBankName(){
		return this.bankName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  银行名称
	 */
	public void setBankName(java.lang.String bankName){
		this.bankName = bankName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  银行省份
	 */
	
	@Column(name ="BANK_ADDRESS_PROVINCE",nullable=true,length=20)
	public java.lang.String getBankAddressProvince(){
		return this.bankAddressProvince;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  银行省份
	 */
	public void setBankAddressProvince(java.lang.String bankAddressProvince){
		this.bankAddressProvince = bankAddressProvince;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  银行城市
	 */
	
	@Column(name ="BANK_ADDRESS_CITY",nullable=true,length=20)
	public java.lang.String getBankAddressCity(){
		return this.bankAddressCity;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  银行城市
	 */
	public void setBankAddressCity(java.lang.String bankAddressCity){
		this.bankAddressCity = bankAddressCity;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  银行
	 */
	
	@Column(name ="BANK_BRANCH",nullable=true,length=100)
	public java.lang.String getBankBranch(){
		return this.bankBranch;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  银行
	 */
	public void setBankBranch(java.lang.String bankBranch){
		this.bankBranch = bankBranch;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账号
	 */
	
	@Column(name ="ACCOUNT_NUMBER",nullable=true,length=50)
	public java.lang.String getAccountNumber(){
		return this.accountNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账号
	 */
	public void setAccountNumber(java.lang.String accountNumber){
		this.accountNumber = accountNumber;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易流水号
	 */
	
	@Column(name ="TRADE_NO",nullable=true,length=50)
	public java.lang.String getTradeNo(){
		return this.tradeNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易流水号
	 */
	public void setTradeNo(java.lang.String tradeNo){
		this.tradeNo = tradeNo;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	
	@Column(name ="REMARKS",nullable=true,length=500)
	public java.lang.String getRemarks(){
		return this.remarks;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemarks(java.lang.String remarks){
		this.remarks = remarks;
	}
	
}
