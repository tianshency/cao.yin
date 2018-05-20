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
 * @Description: 贷款产品
 *
 */
@Entity
@Table(name = "zml_loan_product", schema = "")
@SuppressWarnings("serial")
public class ZmlLoanProductEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新日期*/
	private java.util.Date updateDate;
	/**版本*/
	@Excel(name="版本")
	private java.lang.Integer version;
	/**产品名称*/
	@Excel(name="产品名称")
	private java.lang.String proName;
	/**期限*/
	@Excel(name="期限")
	private java.lang.Integer periods;
	/**期限单位*/
	@Excel(name="期限单位")
	private java.lang.String periodsUnit;
	/**利率*/
	@Excel(name="利率")
	private java.math.BigDecimal interestRate;
	/**罚息率*/
	@Excel(name="罚息率")
	private java.math.BigDecimal penaltyRate;
	/**最小金额*/
	@Excel(name="最小金额")
	private java.math.BigDecimal minAmount;
	/**最大金额*/
	@Excel(name="最大金额")
	private java.math.BigDecimal maxAmount;
	/**流程ID*/
	private java.lang.String wfId;
	/**描述*/
	@Excel(name="描述")
	private java.lang.String details;
	/**状态*/
	@Excel(name="状态")
	private java.lang.String status;
	
	/**状态*/
	@Excel(name="还款方式")
	private java.lang.String repayment;
	
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
	 *@return: java.lang.String  产品名称
	 */
	@Column(name ="PRO_NAME",nullable=true,length=50)
	public java.lang.String getProName(){
		return this.proName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品名称
	 */
	public void setProName(java.lang.String proName){
		this.proName = proName;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  期限
	 */
	@Column(name ="PERIODS",nullable=true,length=5)
	public java.lang.Integer getPeriods(){
		return this.periods;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  期限
	 */
	public void setPeriods(java.lang.Integer periods){
		this.periods = periods;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  期限单位
	 */
	@Column(name ="PERIODS_UNIT",nullable=true,length=2)
	public java.lang.String getPeriodsUnit(){
		return this.periodsUnit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  期限单位
	 */
	public void setPeriodsUnit(java.lang.String periodsUnit){
		this.periodsUnit = periodsUnit;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  利率
	 */
	@Column(name ="INTEREST_RATE",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getInterestRate(){
		return this.interestRate;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  利率
	 */
	public void setInterestRate(java.math.BigDecimal interestRate){
		this.interestRate = interestRate;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  罚息率
	 */
	@Column(name ="PENALTY_RATE",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getPenaltyRate(){
		return this.penaltyRate;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  罚息率
	 */
	public void setPenaltyRate(java.math.BigDecimal penaltyRate){
		this.penaltyRate = penaltyRate;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  最小金额
	 */
	@Column(name ="MIN_AMOUNT",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getMinAmount(){
		return this.minAmount;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  最小金额
	 */
	public void setMinAmount(java.math.BigDecimal minAmount){
		this.minAmount = minAmount;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  最大金额
	 */
	@Column(name ="MAX_AMOUNT",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getMaxAmount(){
		return this.maxAmount;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  最大金额
	 */
	public void setMaxAmount(java.math.BigDecimal maxAmount){
		this.maxAmount = maxAmount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程ID
	 */
	@Column(name ="WF_ID",nullable=true,length=36)
	public java.lang.String getWfId(){
		return this.wfId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程ID
	 */
	public void setWfId(java.lang.String wfId){
		this.wfId = wfId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  描述
	 */
	@Column(name ="DETAILS",nullable=true,length=500)
	public java.lang.String getDetails(){
		return this.details;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述
	 */
	public void setDetails(java.lang.String details){
		this.details = details;
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
	@Column(name ="REPAYMENT",nullable=true,length=2)
	public java.lang.String getRepayment() {
		return repayment;
	}

	public void setRepayment(java.lang.String repayment) {
		this.repayment = repayment;
	}
}
