package com.zml.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity	
 * @Description: 适用标准
 *
 */
@Entity
@Table(name = "zml_commodity_standard", schema = "")
@SuppressWarnings("serial")
public class ZmlCommodityStandardEntity implements java.io.Serializable {
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
	/**版本*/
	private java.lang.Integer version;
	/**商品ID*/
	private java.lang.String commodityId;
	/**农作物种类*/
	@Excel(name="农作物种类")
	private java.lang.String corpId;
	/**标准亩*/
	private java.lang.String standardMu;
	/**亩用量起*/
	@Excel(name="亩用量起")
	private java.math.BigDecimal dosageStart;
	/**亩用量至*/
	@Excel(name="亩用量至")
	private java.math.BigDecimal dosageEnd;
	/**用量单位*/
	@Excel(name="用量单位")
	private java.lang.Integer dosageUnit;
	/**使用说明*/
	@Excel(name="使用说明")
	private java.lang.String useDescription;
	
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  版本
	 */
	@Column(name ="VERSION",nullable=true,length=10)
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
	 *@return: java.lang.String  商品ID
	 */
	@Column(name ="COMMODITY_ID",nullable=true,length=36)
	public java.lang.String getCommodityId(){
		return this.commodityId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品ID
	 */
	public void setCommodityId(java.lang.String commodityId){
		this.commodityId = commodityId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  农作物种类
	 */
	@Column(name ="CORP_ID",nullable=true,length=36)
	public java.lang.String getCorpId(){
		return this.corpId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  农作物种类
	 */
	public void setCorpId(java.lang.String corpId){
		this.corpId = corpId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标准亩
	 */
	@Column(name ="STANDARD_MU",nullable=true,length=1)
	public java.lang.String getStandardMu(){
		return this.standardMu;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标准亩
	 */
	public void setStandardMu(java.lang.String standardMu){
		this.standardMu = standardMu;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  亩用量起
	 */
	@Column(name ="DOSAGE_START",nullable=true,scale=2,length=10)
	public java.math.BigDecimal getDosageStart(){
		return this.dosageStart;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  亩用量起
	 */
	public void setDosageStart(java.math.BigDecimal dosageStart){
		this.dosageStart = dosageStart;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  亩用量至
	 */
	@Column(name ="DOSAGE_END",nullable=true,scale=2,length=10)
	public java.math.BigDecimal getDosageEnd(){
		return this.dosageEnd;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  亩用量至
	 */
	public void setDosageEnd(java.math.BigDecimal dosageEnd){
		this.dosageEnd = dosageEnd;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  用量单位
	 */
	@Column(name ="DOSAGE_UNIT",nullable=true,length=1)
	public java.lang.Integer getDosageUnit(){
		return this.dosageUnit;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  用量单位
	 */
	public void setDosageUnit(java.lang.Integer dosageUnit){
		this.dosageUnit = dosageUnit;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  使用说明
	 */
	@Column(name ="USE_DESCRIPTION",nullable=true,length=500)
	public java.lang.String getUseDescription(){
		return this.useDescription;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  使用说明
	 */
	public void setUseDescription(java.lang.String useDescription){
		this.useDescription = useDescription;
	}
}
