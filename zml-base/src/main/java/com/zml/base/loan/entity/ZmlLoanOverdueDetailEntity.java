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
 * @Description: 逾期明细
 * @author onlineGenerator
 * @date 2017-03-01 22:27:54
 * @version V1.0   
 *
 */
@Entity
@Table(name = "zml_loan_overdue_detail", schema = "")
@SuppressWarnings("serial")
public class ZmlLoanOverdueDetailEntity implements java.io.Serializable {
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
	/**逾期开始时间*/
	@Excel(name="逾期开始时间",format = "yyyy-MM-dd")
	private java.util.Date overStartDate;
	/**应还本金*/
	@Excel(name="应还本金")
	private java.math.BigDecimal principal;
	/**应还利息*/
	@Excel(name="应还利息")
	private java.math.BigDecimal interest;
	/**应还罚息*/
	@Excel(name="应还罚息")
	private java.math.BigDecimal imposeInterest;
	/**滞纳金*/
	@Excel(name="滞纳金")
	private java.math.BigDecimal penalty;
	/**期次*/
	@Excel(name="期次")
	private java.lang.Integer period;
	
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
	@Column(name ="REPAY_PLAN_DETAIL_ID",nullable=true,length=36)
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
	 *@return: java.util.Date  逾期开始时间
	 */
	@Column(name ="OVER_START_DATE",nullable=true,length=10)
	public java.util.Date getOverStartDate(){
		return this.overStartDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  逾期开始时间
	 */
	public void setOverStartDate(java.util.Date overStartDate){
		this.overStartDate = overStartDate;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  应还本金
	 */
	@Column(name ="PRINCIPAL",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getPrincipal(){
		return this.principal;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  应还本金
	 */
	public void setPrincipal(java.math.BigDecimal principal){
		this.principal = principal;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  应还利息
	 */
	@Column(name ="INTEREST",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getInterest(){
		return this.interest;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  应还利息
	 */
	public void setInterest(java.math.BigDecimal interest){
		this.interest = interest;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  应还罚息
	 */
	@Column(name ="IMPOSE_INTEREST",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getImposeInterest(){
		return this.imposeInterest;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  应还罚息
	 */
	public void setImposeInterest(java.math.BigDecimal imposeInterest){
		this.imposeInterest = imposeInterest;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  滞纳金
	 */
	@Column(name ="PENALTY",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getPenalty(){
		return this.penalty;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  滞纳金
	 */
	public void setPenalty(java.math.BigDecimal penalty){
		this.penalty = penalty;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  期次
	 */
	@Column(name ="PERIOD",nullable=true,length=5)
	public java.lang.Integer getPeriod(){
		return this.period;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  期次
	 */
	public void setPeriod(java.lang.Integer period){
		this.period = period;
	}
}
