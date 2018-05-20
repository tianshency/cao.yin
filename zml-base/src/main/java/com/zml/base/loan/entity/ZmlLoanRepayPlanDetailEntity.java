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
 * @Description: 还款计划明细
 * @author onlineGenerator
 * @date 2017-03-01 22:27:11
 * @version V1.0   
 *
 */
@Entity
@Table(name = "zml_loan_repay_plan_detail", schema = "")
@SuppressWarnings("serial")
public class ZmlLoanRepayPlanDetailEntity implements java.io.Serializable {
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
	/**当期期数*/
	@Excel(name="当期期数")
	private java.lang.Integer profitPeriod;
	/**当期开始日*/
	@Excel(name="当期开始日",format = "yyyy-MM-dd")
	private java.util.Date profitStartDate;
	/**当期结束日*/
	@Excel(name="当期结束日",format = "yyyy-MM-dd")
	private java.util.Date profitEndDate;
	/**当期应还本金*/
	@Excel(name="当期应还本金")
	private java.math.BigDecimal profitPrincipal;
	/**当期应还利息*/
	@Excel(name="当期应还利息")
	private java.math.BigDecimal profitInterest;
	/**当期应还罚息*/
	@Excel(name="当期应还罚息")
	private java.math.BigDecimal profitPenalty;
	/**当期应还滞纳金*/
	@Excel(name="当期应还滞纳金")
	private java.math.BigDecimal profitNonpayment;
	/**当期应还其他费用*/
	@Excel(name="当期应还其他费用")
	private java.math.BigDecimal profitOtherFee;
	/**当期应还合计*/
	@Excel(name="当期应还合计")
	private java.math.BigDecimal profitRepaySum;
	/**当期实还本金*/
	@Excel(name="当期实还本金")
	private java.math.BigDecimal repayPrincipal;
	/**当期实还利息*/
	@Excel(name="当期实还利息")
	private java.math.BigDecimal repayInterest;
	/**当期实还罚息*/
	@Excel(name="当期实还罚息")
	private java.math.BigDecimal repayPenalty;
	/**当期实还滞纳金*/
	@Excel(name="当期实还滞纳金")
	private java.math.BigDecimal repayNonpayment;
	/**当期实还其他费用*/
	@Excel(name="当期实还其他费用")
	private java.math.BigDecimal repayOtherFee;
	/**当期实还总额*/
	@Excel(name="当期实还总额")
	private java.math.BigDecimal repaySum;
	/**最后还款日*/
	@Excel(name="最后还款日",format = "yyyy-MM-dd")
	private java.util.Date lastRepayDate;
	/**当期合同余额*/
	@Excel(name="当期合同余额")
	private java.math.BigDecimal endCurrentPrincipalbalance;
	/**利率*/
	@Excel(name="利率")
	private java.math.BigDecimal rate;
	/**逾期利率*/
	@Excel(name="逾期利率")
	private java.math.BigDecimal overdueRate;
	/**逾期天数*/
	@Excel(name="逾期天数")
	private java.lang.Integer overdueDays;
	/**状态*/
	@Excel(name="状态")
	private java.lang.String status;
	/**调整日期*/
	@Excel(name="调整日期",format = "yyyy-MM-dd")
	private java.util.Date adjustdate;
	/**最近一次还款时间*/
	@Excel(name="最近一次还款时间",format = "yyyy-MM-dd")
	private java.util.Date recentlyRepayTime;
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  当期期数
	 */
	@Column(name ="PROFIT_PERIOD",nullable=true,length=5)
	public java.lang.Integer getProfitPeriod(){
		return this.profitPeriod;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  当期期数
	 */
	public void setProfitPeriod(java.lang.Integer profitPeriod){
		this.profitPeriod = profitPeriod;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  当期开始日
	 */
	@Column(name ="PROFIT_START_DATE",nullable=true,length=10)
	public java.util.Date getProfitStartDate(){
		return this.profitStartDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  当期开始日
	 */
	public void setProfitStartDate(java.util.Date profitStartDate){
		this.profitStartDate = profitStartDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  当期结束日
	 */
	@Column(name ="PROFIT_END_DATE",nullable=true,length=10)
	public java.util.Date getProfitEndDate(){
		return this.profitEndDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  当期结束日
	 */
	public void setProfitEndDate(java.util.Date profitEndDate){
		this.profitEndDate = profitEndDate;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  当期应还本金
	 */
	@Column(name ="PROFIT_PRINCIPAL",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getProfitPrincipal(){
		if(this.profitPrincipal ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.profitPrincipal;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  当期应还本金
	 */
	public void setProfitPrincipal(java.math.BigDecimal profitPrincipal){
		this.profitPrincipal = profitPrincipal;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  当期应还利息
	 */
	@Column(name ="PROFIT_INTEREST",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getProfitInterest(){
		if(this.profitInterest ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.profitInterest;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  当期应还利息
	 */
	public void setProfitInterest(java.math.BigDecimal profitInterest){
		this.profitInterest = profitInterest;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  当期应还罚息
	 */
	@Column(name ="PROFIT_PENALTY",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getProfitPenalty(){
		if(this.profitPenalty ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.profitPenalty;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  当期应还罚息
	 */
	public void setProfitPenalty(java.math.BigDecimal profitPenalty){
		this.profitPenalty = profitPenalty;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  当期应还滞纳金
	 */
	@Column(name ="PROFIT_NONPAYMENT",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getProfitNonpayment(){
		if(this.profitNonpayment ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.profitNonpayment;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  当期应还滞纳金
	 */
	public void setProfitNonpayment(java.math.BigDecimal profitNonpayment){
		this.profitNonpayment = profitNonpayment;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  当期应还其他费用
	 */
	@Column(name ="PROFIT_OTHER_FEE",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getProfitOtherFee(){
		if(this.profitOtherFee ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.profitOtherFee;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  当期应还其他费用
	 */
	public void setProfitOtherFee(java.math.BigDecimal profitOtherFee){
		this.profitOtherFee = profitOtherFee;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  当期应还合计
	 */
	@Column(name ="PROFIT_REPAY_SUM",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getProfitRepaySum(){
		if(this.profitRepaySum ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.profitRepaySum;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  当期应还合计
	 */
	public void setProfitRepaySum(java.math.BigDecimal profitRepaySum){
		this.profitRepaySum = profitRepaySum;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  当期实还本金
	 */
	@Column(name ="REPAY_PRINCIPAL",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getRepayPrincipal(){
		if(this.repayPrincipal ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.repayPrincipal;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  当期实还本金
	 */
	public void setRepayPrincipal(java.math.BigDecimal repayPrincipal){
		this.repayPrincipal = repayPrincipal;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  当期实还利息
	 */
	@Column(name ="REPAY_INTEREST",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getRepayInterest(){
		if(this.repayInterest ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.repayInterest;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  当期实还利息
	 */
	public void setRepayInterest(java.math.BigDecimal repayInterest){
		this.repayInterest = repayInterest;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  当期实还罚息
	 */
	@Column(name ="REPAY_PENALTY",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getRepayPenalty(){
		if(this.repayPenalty ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.repayPenalty;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  当期实还罚息
	 */
	public void setRepayPenalty(java.math.BigDecimal repayPenalty){
		this.repayPenalty = repayPenalty;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  当期实还滞纳金
	 */
	@Column(name ="REPAY_NONPAYMENT",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getRepayNonpayment(){
		if(this.repayNonpayment ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.repayNonpayment;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  当期实还滞纳金
	 */
	public void setRepayNonpayment(java.math.BigDecimal repayNonpayment){
		this.repayNonpayment = repayNonpayment;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  当期实还其他费用
	 */
	@Column(name ="REPAY_OTHER_FEE",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getRepayOtherFee(){
		if(this.repayOtherFee ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.repayOtherFee;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  当期实还其他费用
	 */
	public void setRepayOtherFee(java.math.BigDecimal repayOtherFee){
		this.repayOtherFee = repayOtherFee;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  当期实还总额
	 */
	@Column(name ="REPAY_SUM",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getRepaySum(){
		if(this.repaySum ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.repaySum;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  当期实还总额
	 */
	public void setRepaySum(java.math.BigDecimal repaySum){
		this.repaySum = repaySum;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  最后还款日
	 */
	@Column(name ="LAST_REPAY_DATE",nullable=true,length=10)
	public java.util.Date getLastRepayDate(){
		return this.lastRepayDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  最后还款日
	 */
	public void setLastRepayDate(java.util.Date lastRepayDate){
		this.lastRepayDate = lastRepayDate;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  当期合同余额
	 */
	@Column(name ="END_CURRENT_PRINCIPALBALANCE",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getEndCurrentPrincipalbalance(){
		if(this.endCurrentPrincipalbalance ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.endCurrentPrincipalbalance;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  当期合同余额
	 */
	public void setEndCurrentPrincipalbalance(java.math.BigDecimal endCurrentPrincipalbalance){
		this.endCurrentPrincipalbalance = endCurrentPrincipalbalance;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  利率
	 */
	@Column(name ="RATE",nullable=true,scale=6,length=14)
	public java.math.BigDecimal getRate(){
		if(this.rate ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.rate;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  利率
	 */
	public void setRate(java.math.BigDecimal rate){
		this.rate = rate;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  逾期利率
	 */
	@Column(name ="OVERDUE_RATE",nullable=true,scale=6,length=14)
	public java.math.BigDecimal getOverdueRate(){
		if(this.overdueRate ==null)
			return BigDemicalUtil.bd_zero;
		else
			return this.overdueRate;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  逾期利率
	 */
	public void setOverdueRate(java.math.BigDecimal overdueRate){
		this.overdueRate = overdueRate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  逾期天数
	 */
	@Column(name ="OVERDUE_DAYS",nullable=true,length=5)
	public java.lang.Integer getOverdueDays(){
		return this.overdueDays;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  逾期天数
	 */
	public void setOverdueDays(java.lang.Integer overdueDays){
		this.overdueDays = overdueDays;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	@Column(name ="STATUS",nullable=true,length=2)
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  调整日期
	 */
	@Column(name ="ADJUSTDATE",nullable=true,length=10)
	public java.util.Date getAdjustdate(){
		return this.adjustdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  调整日期
	 */
	public void setAdjustdate(java.util.Date adjustdate){
		this.adjustdate = adjustdate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  最近一次还款时间
	 */
	@Column(name ="RECENTLY_REPAY_TIME",nullable=true,length=20)
	public java.util.Date getRecentlyRepayTime(){
		return this.recentlyRepayTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  最近一次还款时间
	 */
	public void setRecentlyRepayTime(java.util.Date recentlyRepayTime){
		this.recentlyRepayTime = recentlyRepayTime;
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
