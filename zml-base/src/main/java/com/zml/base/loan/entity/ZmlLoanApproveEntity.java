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
 * @Description: 借款审批结果
 * @author onlineGenerator
 * @date 2017-03-01 22:25:26
 * @version V1.0   
 *
 */
@Entity
@Table(name = "zml_loan_approve", schema = "")
@SuppressWarnings("serial")
public class ZmlLoanApproveEntity implements java.io.Serializable {
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
	/**额度*/
	@Excel(name="额度")
	private java.lang.String creditId;
	/**授信额度*/
	@Excel(name="授信额度")
	private BigDecimal creditAmount;
	/**服务费*/
	@Excel(name="服务费")
	private BigDecimal fee;
	/**批准金额*/
	@Excel(name="批准金额")
	private BigDecimal approveAmount;
	/**批准利率*/
	@Excel(name="批准利率")
	private BigDecimal interestRate;
	/**批准期限*/
	@Excel(name="批准期限")
	private java.lang.Integer term;
	/**批准期限单位*/
	@Excel(name="批准期限单位")
	private java.lang.String termUnit;
	/**建议审批额度*/
	@Excel(name="建议审批额度")
	private java.math.BigDecimal argeeAmount;
	/**还款方式*/
    @Excel(name="还款方式")
	private java.lang.String repayment;
	/**审批标识*/
	@Excel(name="审批标识")
	private java.lang.String approvalFlag;
	/**审批人*/
	@Excel(name="审批人")
	private java.lang.String approvalUserId;
	/**审批时间*/
	@Excel(name="审批时间",format = "yyyy-MM-dd")
	private java.util.Date approvalDate;
	/**审批意见*/
	@Excel(name="审批意见")
	private java.lang.String approvalOpinion;
	
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
	 *@return: java.lang.String  额度
	 */
	@Column(name ="CREDIT_ID",nullable=false,length=36)
	public java.lang.String getCreditId(){
		return this.creditId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  额度
	 */
	public void setCreditId(java.lang.String creditId){
		this.creditId = creditId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  授信额度
	 */
	@Column(name ="CREDIT_AMOUNT",nullable=true,scale=2,length=14)
	public BigDecimal getCreditAmount(){
		return this.creditAmount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  授信额度
	 */
	public void setCreditAmount(BigDecimal creditAmount){
		this.creditAmount = creditAmount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  服务费
	 */
	@Column(name ="FEE",nullable=true,scale=2,length=14)
	public BigDecimal getFee(){
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
	 *@return: java.lang.String  批准金额
	 */
	@Column(name ="APPROVE_AMOUNT",nullable=true,scale=2,length=14)
	public BigDecimal getApproveAmount(){
		return this.approveAmount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  批准金额
	 */
	public void setApproveAmount(BigDecimal approveAmount){
		this.approveAmount = approveAmount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  批准利率
	 */
	@Column(name ="INTEREST_RATE",nullable=true,scale=6,length=14)
	public BigDecimal getInterestRate(){
		return this.interestRate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  批准利率
	 */
	public void setInterestRate(BigDecimal interestRate){
		this.interestRate = interestRate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  批准期限
	 */
	@Column(name ="TERM",nullable=true,length=5)
	public java.lang.Integer getTerm(){
		return this.term;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  批准期限
	 */
	public void setTerm(java.lang.Integer term){
		this.term = term;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  批准期限单位
	 */
	@Column(name ="TERM_UNIT",nullable=true,length=1)
	public java.lang.String getTermUnit(){
		return this.termUnit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  批准期限单位
	 */
	public void setTermUnit(java.lang.String termUnit){
		this.termUnit = termUnit;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  建议审批额度
	 */
	@Column(name ="ARGEE_AMOUNT",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getArgeeAmount(){
		return this.argeeAmount;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  建议审批额度
	 */
	public void setArgeeAmount(java.math.BigDecimal argeeAmount){
		this.argeeAmount = argeeAmount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审批标识
	 */
	@Column(name ="APPROVAL_FLAG",nullable=true,length=1)
	public java.lang.String getApprovalFlag(){
		return this.approvalFlag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批标识
	 */
	public void setApprovalFlag(java.lang.String approvalFlag){
		this.approvalFlag = approvalFlag;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审批人
	 */
	@Column(name ="APPROVAL_USER_ID",nullable=true,length=36)
	public java.lang.String getApprovalUserId(){
		return this.approvalUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批人
	 */
	public void setApprovalUserId(java.lang.String approvalUserId){
		this.approvalUserId = approvalUserId;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  审批时间
	 */
	@Column(name ="APPROVAL_DATE",nullable=true,length=20)
	public java.util.Date getApprovalDate(){
		return this.approvalDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  审批时间
	 */
	public void setApprovalDate(java.util.Date approvalDate){
		this.approvalDate = approvalDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审批意见
	 */
	@Column(name ="APPROVAL_OPINION",nullable=true,length=500)
	public java.lang.String getApprovalOpinion(){
		return this.approvalOpinion;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批意见
	 */
	public void setApprovalOpinion(java.lang.String approvalOpinion){
		this.approvalOpinion = approvalOpinion;
	}
	@Column(name ="REPAYMENT",nullable=true,length=500)
	public java.lang.String getRepayment() {
		return repayment;
	}

	public void setRepayment(java.lang.String repayment) {
		this.repayment = repayment;
	}
	
}
