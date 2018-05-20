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
 * @Description: 商户信息
 * @author onlineGenerator
 * @date 2017-01-01 11:14:01
 * @version V1.0   
 *
 */
@Entity
@Table(name = "zml_merchants", schema = "")
@SuppressWarnings("serial")
public class ZmlMerchantsEntity implements java.io.Serializable {
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
	/**商户名称*/
	@Excel(name="商户名称")
	private java.lang.String merchantsName;
	/**商家地址*/
	@Excel(name="商家地址")
	private java.lang.String merchantsAdress;
	/**商家描述*/
	@Excel(name="商家描述")
	private java.lang.String merchantsDescription;
	/**营业执照*/
	@Excel(name="营业执照")
	private java.lang.String businesslicense;
	/**标志*/
	@Excel(name="标志")
	private java.lang.String logo;
	/**封面图片*/
	@Excel(name="封面图片")
	private java.lang.String coverImg;
	/**客服电话*/
	@Excel(name="客服电话")
	private java.lang.String consumerHotline;
	/**负责人*/
	@Excel(name="负责人")
	private java.lang.String leader;
	/**负责人电话*/
	@Excel(name="负责人电话")
	private java.lang.String leaderTelephone;
	/**评级*/
	@Excel(name="评级")
	private java.lang.String grade;
	/**分类*/
	@Excel(name="分类")
	private String category;
	/**经度*/
	@Excel(name="经度")
	private BigDecimal longitude;
	/**纬度*/
	@Excel(name="纬度")
	private BigDecimal latitude;
	
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
	 *@return: java.lang.String  商户名称
	 */
	@Column(name ="MERCHANTS_NAME",nullable=true,length=100)
	public java.lang.String getMerchantsName(){
		return this.merchantsName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商户名称
	 */
	public void setMerchantsName(java.lang.String merchantsName){
		this.merchantsName = merchantsName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商家地址
	 */
	@Column(name ="MERCHANTS_ADRESS",nullable=true,length=200)
	public java.lang.String getMerchantsAdress(){
		return this.merchantsAdress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商家地址
	 */
	public void setMerchantsAdress(java.lang.String merchantsAdress){
		this.merchantsAdress = merchantsAdress;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商家描述
	 */
	@Column(name ="MERCHANTS_DESCRIPTION",nullable=true,length=1000)
	public java.lang.String getMerchantsDescription(){
		return this.merchantsDescription;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商家描述
	 */
	public void setMerchantsDescription(java.lang.String merchantsDescription){
		this.merchantsDescription = merchantsDescription;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  营业执照
	 */
	@Column(name ="BUSINESSLICENSE",nullable=true,length=500)
	public java.lang.String getBusinesslicense(){
		return this.businesslicense;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  营业执照
	 */
	public void setBusinesslicense(java.lang.String businesslicense){
		this.businesslicense = businesslicense;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标志
	 */
	@Column(name ="LOGO",nullable=true,length=200)
	public java.lang.String getLogo(){
		return this.logo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标志
	 */
	public void setLogo(java.lang.String logo){
		this.logo = logo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  封面图片
	 */
	@Column(name ="COVER_IMG",nullable=true,length=200)
	public java.lang.String getCoverImg(){
		return this.coverImg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  封面图片
	 */
	public void setCoverImg(java.lang.String coverImg){
		this.coverImg = coverImg;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客服电话
	 */
	@Column(name ="CONSUMER_HOTLINE",nullable=true,length=30)
	public java.lang.String getConsumerHotline(){
		return this.consumerHotline;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客服电话
	 */
	public void setConsumerHotline(java.lang.String consumerHotline){
		this.consumerHotline = consumerHotline;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  负责人
	 */
	@Column(name ="LEADER",nullable=true,length=36)
	public java.lang.String getLeader(){
		return this.leader;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  负责人
	 */
	public void setLeader(java.lang.String leader){
		this.leader = leader;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  负责人电话
	 */
	@Column(name ="LEADER_TELEPHONE",nullable=true,length=30)
	public java.lang.String getLeaderTelephone(){
		return this.leaderTelephone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  负责人电话
	 */
	public void setLeaderTelephone(java.lang.String leaderTelephone){
		this.leaderTelephone = leaderTelephone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  评级
	 */
	@Column(name ="GRADE",nullable=true,length=30)
	public java.lang.String getGrade(){
		return this.grade;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  评级
	 */
	public void setGrade(java.lang.String grade){
		this.grade = grade;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	
	
}
