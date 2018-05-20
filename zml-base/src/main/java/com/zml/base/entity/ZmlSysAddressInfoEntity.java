package com.zml.base.entity;

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
 * @Description: 地理位置信息
 * @author onlineGenerator
 * @date 2017-01-22 10:05:54
 * @version V1.0   
 *
 */
@Entity
@Table(name = "zml_sys_address_info", schema = "")
@SuppressWarnings("serial")
public class ZmlSysAddressInfoEntity implements java.io.Serializable {
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
	/**用户ID*/
	@Excel(name="用户ID")
	private java.lang.String userId;
	/**来源标识*/
	@Excel(name="来源标识")
	private java.lang.String sourceFlag;
	/**精度*/
	@Excel(name="精度")
	private java.math.BigDecimal addrPrecision;
	/**纬度*/
	@Excel(name="纬度")
	private java.math.BigDecimal addrLatitude;
	/**地点名称*/
	@Excel(name="地点名称")
	private java.lang.String addressName;
	/**省份*/
	@Excel(name="省份")
	private java.lang.String province;
	/**市级*/
	@Excel(name="市级")
	private java.lang.String city;
	/**县城*/
	@Excel(name="县城")
	private java.lang.String county;
	/**村子*/
	@Excel(name="村子")
	private java.lang.String village;
	/**组名*/
	@Excel(name="组名")
	private java.lang.String groupName;
	/**屯名*/
	@Excel(name="屯名")
	private java.lang.String tuenName;
	
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
	 *@return: java.lang.String  用户ID
	 */
	@Column(name ="USER_ID",nullable=true,length=36)
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户ID
	 */
	public void setUserId(java.lang.String userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  来源标识
	 */
	@Column(name ="SOURCE_FLAG",nullable=true,length=2)
	public java.lang.String getSourceFlag(){
		return this.sourceFlag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  来源标识
	 */
	public void setSourceFlag(java.lang.String sourceFlag){
		this.sourceFlag = sourceFlag;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  精度
	 */
	@Column(name ="ADDR_PRECISION",nullable=true,scale=20,length=20)
	public java.math.BigDecimal getAddrPrecision(){
		return this.addrPrecision;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  精度
	 */
	public void setAddrPrecision(java.math.BigDecimal addrPrecision){
		this.addrPrecision = addrPrecision;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  纬度
	 */
	@Column(name ="ADDR_LATITUDE",nullable=true,scale=20,length=20)
	public java.math.BigDecimal getAddrLatitude(){
		return this.addrLatitude;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  纬度
	 */
	public void setAddrLatitude(java.math.BigDecimal addrLatitude){
		this.addrLatitude = addrLatitude;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地点名称
	 */
	@Column(name ="ADDRESS_NAME",nullable=true,length=300)
	public java.lang.String getAddressName(){
		return this.addressName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地点名称
	 */
	public void setAddressName(java.lang.String addressName){
		this.addressName = addressName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  省份
	 */
	@Column(name ="PROVINCE",nullable=true,length=20)
	public java.lang.String getProvince(){
		return this.province;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  省份
	 */
	public void setProvince(java.lang.String province){
		this.province = province;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  市级
	 */
	@Column(name ="CITY",nullable=true,length=20)
	public java.lang.String getCity(){
		return this.city;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  市级
	 */
	public void setCity(java.lang.String city){
		this.city = city;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  县城
	 */
	@Column(name ="COUNTY",nullable=true,length=20)
	public java.lang.String getCounty(){
		return this.county;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  县城
	 */
	public void setCounty(java.lang.String county){
		this.county = county;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  村子
	 */
	@Column(name ="VILLAGE",nullable=true,length=20)
	public java.lang.String getVillage(){
		return this.village;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  村子
	 */
	public void setVillage(java.lang.String village){
		this.village = village;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  组名
	 */
	@Column(name ="GROUP_NAME",nullable=true,length=20)
	public java.lang.String getGroupName(){
		return this.groupName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  组名
	 */
	public void setGroupName(java.lang.String groupName){
		this.groupName = groupName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  屯名
	 */
	@Column(name ="TUEN_NAME",nullable=true,length=100)
	public java.lang.String getTuenName(){
		return this.tuenName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  屯名
	 */
	public void setTuenName(java.lang.String tuenName){
		this.tuenName = tuenName;
	}
}
