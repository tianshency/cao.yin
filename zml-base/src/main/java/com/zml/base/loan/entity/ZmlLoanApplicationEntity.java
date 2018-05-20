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
 * @Description: 借款申请表
 * @author onlineGenerator
 * @date 2017-03-01 22:19:26
 * @version V1.0   
 *
 */
@Entity
@Table(name = "zml_loan_application", schema = "")
@SuppressWarnings("serial")
public class ZmlLoanApplicationEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**用户*/
	@Excel(name="用户")
	private java.lang.String userId;
	/**申请编号*/
	@Excel(name="申请编号")
	private java.lang.String applyNo;
	/**类型*/
	@Excel(name="类型")
	private java.lang.String loanType;
	/**申请人姓名*/
	@Excel(name="申请人姓名")
	private java.lang.String name;
	/**职业*/
	@Excel(name="职业")
	private java.lang.String profession;
	/**申请人电话*/
	@Excel(name="申请人电话")
	private java.lang.String phone;
	/**借款理由*/
	@Excel(name="借款理由")
	private java.lang.String loanReason;
	/**借款金额*/
	@Excel(name="借款金额")
	private java.math.BigDecimal amount;
	/**借款期限*/
	@Excel(name="借款期限")
	private java.lang.String periods;
	/**借款期限单位*/
	@Excel(name="借款期限单位")
	private java.lang.String periodsUnit;
	/**利率*/
	@Excel(name="利率")
	private java.math.BigDecimal interestRate;
	/**罚息率*/
	@Excel(name="罚息率")
	private java.math.BigDecimal penaltyRate;
	/**状态*/
	@Excel(name="状态")
	private java.lang.String applySts;
	/**主题*/
	@Excel(name="主题")
	private java.lang.String subject;
	
	/**推荐人*/
	@Excel(name="推荐人")
	private java.lang.String recommendUser;
	
	/**产品编码*/
	@Excel(name="产品编码")
	private java.lang.String productId;
	/**负责人*/
	@Excel(name="负责人")
	private java.lang.String principal;

	
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */
	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */
	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */
	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态
	 */
	@Column(name ="BPM_STATUS",nullable=true,length=5)
	public java.lang.String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态
	 */
	public void setBpmStatus(java.lang.String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户
	 */
	@Column(name ="USER_ID",nullable=true,length=36)
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
	 *@return: java.lang.String  申请编号
	 */
	@Column(name ="APPLY_NO",nullable=true,length=30)
	public java.lang.String getApplyNo(){
		return this.applyNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请编号
	 */
	public void setApplyNo(java.lang.String applyNo){
		this.applyNo = applyNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型
	 */
	@Column(name ="LOAN_TYPE",nullable=true,length=3)
	public java.lang.String getLoanType(){
		return this.loanType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型
	 */
	public void setLoanType(java.lang.String loanType){
		this.loanType = loanType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请人姓名
	 */
	@Column(name ="NAME",nullable=true,length=100)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请人姓名
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职业
	 */
	@Column(name ="PROFESSION",nullable=true,length=2)
	public java.lang.String getProfession(){
		return this.profession;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职业
	 */
	public void setProfession(java.lang.String profession){
		this.profession = profession;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请人电话
	 */
	@Column(name ="PHONE",nullable=true,length=12)
	public java.lang.String getPhone(){
		return this.phone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请人电话
	 */
	public void setPhone(java.lang.String phone){
		this.phone = phone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  借款理由
	 */
	@Column(name ="LOAN_REASON",nullable=true,length=2000)
	public java.lang.String getLoanReason(){
		return this.loanReason;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  借款理由
	 */
	public void setLoanReason(java.lang.String loanReason){
		this.loanReason = loanReason;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  借款金额
	 */
	@Column(name ="AMOUNT",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getAmount(){
		return this.amount;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  借款金额
	 */
	public void setAmount(java.math.BigDecimal amount){
		this.amount = amount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  借款期限
	 */
	@Column(name ="PERIODS",nullable=true,length=5)
	public java.lang.String getPeriods(){
		return this.periods;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  借款期限
	 */
	public void setPeriods(java.lang.String periods){
		this.periods = periods;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  借款期限单位
	 */
	@Column(name ="PERIODS_UNIT",nullable=true,length=1)
	public java.lang.String getPeriodsUnit(){
		return this.periodsUnit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  借款期限单位
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	@Column(name ="APPLY_STS",nullable=true,length=1)
	public java.lang.String getApplySts(){
		return this.applySts;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setApplySts(java.lang.String applySts){
		this.applySts = applySts;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主题
	 */
	@Column(name ="SUBJECT",nullable=true,length=100)
	public java.lang.String getSubject(){
		return this.subject;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主题
	 */
	public void setSubject(java.lang.String subject){
		this.subject = subject;
	}
	@Column(name ="recommend_user",nullable=true,length=50)
	public java.lang.String getRecommendUser() {
		return recommendUser;
	}

	public void setRecommendUser(java.lang.String recommendUser) {
		this.recommendUser = recommendUser;
	}
	@Column(name ="principal",nullable=true,length=36)
	public java.lang.String getPrincipal() {
		return principal;
	}

	public void setPrincipal(java.lang.String principal) {
		this.principal = principal;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品编码
	 */
	@Column(name ="PRODUCT_ID",nullable=true,length=36)
	public java.lang.String getProductId(){
		return this.productId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品编码
	 */
	public void setProductId(java.lang.String productId){
		this.productId = productId;
	}
	
}
