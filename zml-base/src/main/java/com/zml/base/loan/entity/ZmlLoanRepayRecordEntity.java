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
 * @Description: 实际还款记录
 * @author onlineGenerator
 * @date 2017-03-01 22:27:37
 * @version V1.0   
 *
 */
@Entity
@Table(name = "zml_loan_repay_record", schema = "")
@SuppressWarnings("serial")
public class ZmlLoanRepayRecordEntity implements java.io.Serializable {
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
	/**用户*/
	@Excel(name="用户")
	private java.lang.String userId;
	/**还款计划*/
	@Excel(name="还款计划")
	private java.lang.String repayPlanDetailId;
	/**还款时间*/
	@Excel(name="还款时间",format = "yyyy-MM-dd")
	private java.util.Date repayTime;
	/**实还本金*/
	@Excel(name="实还本金")
	private java.math.BigDecimal repayPrincipal;
	/**实还利息*/
	@Excel(name="实还利息")
	private java.math.BigDecimal repayInterest;
	/**实还罚息*/
	@Excel(name="实还罚息")
	private java.math.BigDecimal repayPenalty;
	/**实还滞纳金*/
	@Excel(name="实还滞纳金")
	private java.math.BigDecimal repayNonpayment;
	/**实还其他费用*/
	@Excel(name="实还其他费用")
	private java.math.BigDecimal repayOtherFee;
	/**还款金额*/
	@Excel(name="还款金额")
	private java.math.BigDecimal repayAmt;
	/**还款渠道*/
	@Excel(name="还款渠道")
	private java.lang.String repayChannel;
	/**还款凭证*/
	@Excel(name="还款凭证")
	private java.lang.String repayCertificate;
	/**状态*/
	@Excel(name="状态")
	private java.lang.String status;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String remarks;
	
	/**审批还款金额*/
	@Excel(name="审批还款金额")
	private java.math.BigDecimal  approveRepayAmt;
	
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
	 *@return: java.lang.String  还款计划
	 */
	@Column(name ="REPAY_PLAN_DETAIL_ID",nullable=false,length=36)
	public java.lang.String getRepayPlanDetailId(){
		return this.repayPlanDetailId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  还款计划
	 */
	public void setRepayPlanDetailId(java.lang.String repayPlanDetailId){
		this.repayPlanDetailId = repayPlanDetailId;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  还款时间
	 */
	@Column(name ="REPAY_TIME",nullable=true,length=20)
	public java.util.Date getRepayTime(){
		return this.repayTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  还款时间
	 */
	public void setRepayTime(java.util.Date repayTime){
		this.repayTime = repayTime;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  实还本金
	 */
	@Column(name ="REPAY_PRINCIPAL",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getRepayPrincipal(){
		return this.repayPrincipal;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  实还本金
	 */
	public void setRepayPrincipal(java.math.BigDecimal repayPrincipal){
		this.repayPrincipal = repayPrincipal;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  实还利息
	 */
	@Column(name ="REPAY_INTEREST",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getRepayInterest(){
		return this.repayInterest;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  实还利息
	 */
	public void setRepayInterest(java.math.BigDecimal repayInterest){
		this.repayInterest = repayInterest;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  实还罚息
	 */
	@Column(name ="REPAY_PENALTY",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getRepayPenalty(){
		return this.repayPenalty;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  实还罚息
	 */
	public void setRepayPenalty(java.math.BigDecimal repayPenalty){
		this.repayPenalty = repayPenalty;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  实还滞纳金
	 */
	@Column(name ="REPAY_NONPAYMENT",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getRepayNonpayment(){
		return this.repayNonpayment;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  实还滞纳金
	 */
	public void setRepayNonpayment(java.math.BigDecimal repayNonpayment){
		this.repayNonpayment = repayNonpayment;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  实还其他费用
	 */
	@Column(name ="REPAY_OTHER_FEE",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getRepayOtherFee(){
		return this.repayOtherFee;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  实还其他费用
	 */
	public void setRepayOtherFee(java.math.BigDecimal repayOtherFee){
		this.repayOtherFee = repayOtherFee;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  还款金额
	 */
	@Column(name ="REPAY_AMT",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getRepayAmt(){
		return this.repayAmt;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  还款金额
	 */
	public void setRepayAmt(java.math.BigDecimal repayAmt){
		this.repayAmt = repayAmt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  还款渠道
	 */
	@Column(name ="REPAY_CHANNEL",nullable=true,length=2)
	public java.lang.String getRepayChannel(){
		return this.repayChannel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  还款渠道
	 */
	public void setRepayChannel(java.lang.String repayChannel){
		this.repayChannel = repayChannel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  还款凭证
	 */
	@Column(name ="REPAY_CERTIFICATE",nullable=true,length=200)
	public java.lang.String getRepayCertificate(){
		return this.repayCertificate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  还款凭证
	 */
	public void setRepayCertificate(java.lang.String repayCertificate){
		this.repayCertificate = repayCertificate;
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
	@Column(name ="APPROVE_REPAY_AMT",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getApproveRepayAmt() {
		return approveRepayAmt;
	}

	public void setApproveRepayAmt(java.math.BigDecimal approveRepayAmt) {
		this.approveRepayAmt = approveRepayAmt;
	}
	
}
