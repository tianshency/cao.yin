package com.zml.commodity.entity;
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
 * @Description: 商品

 * @date 2016-12-19 19:52:54
 * @version V1.0   
 *
 */
@Entity
@Table(name = "zml_commodity", schema = "")
@SuppressWarnings("serial")
public class CfCommodityEntity implements java.io.Serializable {
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
	/**卖家*/
    @Excel(name="卖家")
	private java.lang.String userId;
	/**名称*/
    @Excel(name="名称")
	private java.lang.String name;
	/**介绍*/
    @Excel(name="介绍")
	private java.lang.String introduction;
	/**发货地址*/
    @Excel(name="发货地址")
	private java.lang.String addressId;
	/**类型*/
    @Excel(name="类型")
	private java.lang.String type;
	/**价格*/
    @Excel(name="价格")
	private java.math.BigDecimal price;
	/**总数量*/
    @Excel(name="总数量")
	private java.lang.Integer totalAmount;
	/**库存*/
    @Excel(name="库存")
	private java.lang.Integer reserve;
	/**是否推荐*/
    @Excel(name="是否推荐")
	private java.lang.String isRecommend;
	/**是否热卖*/
    @Excel(name="是否热卖")
	private java.lang.String isHot;
	/**生产厂家*/
	private java.lang.String manufacturers;
	/**运费*/
    @Excel(name="运费")
	private java.math.BigDecimal fare;
	/**是否买家自取*/
    @Excel(name="是否买家自取")
	private java.lang.String isTake;
	/**品牌*/
    @Excel(name="品牌")
	private java.lang.String brand;
	/**型号*/
    @Excel(name="型号")
	private java.lang.String model;
	/**位置*/
    @Excel(name="位置")
	private java.lang.String position;
	/**零件*/
    @Excel(name="零件")
	private java.lang.String components;
	/**商品描述*/
    @Excel(name="商品描述")
	private java.lang.String details;
	/**状态*/
    @Excel(name="状态")
	private java.lang.String status;
	
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
	 *@return: java.lang.String  卖家
	 */
	
	@Column(name ="USER_ID",nullable=true,length=36)
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  卖家
	 */
	public void setUserId(java.lang.String userId){
		this.userId = userId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  名称
	 */
	
	@Column(name ="NAME",nullable=true,length=200)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  介绍
	 */
	
	@Column(name ="INTRODUCTION",nullable=true,length=4000)
	public java.lang.String getIntroduction(){
		return this.introduction;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  介绍
	 */
	public void setIntroduction(java.lang.String introduction){
		this.introduction = introduction;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发货地址
	 */
	
	@Column(name ="ADDRESS_ID",nullable=true,length=36)
	public java.lang.String getAddressId(){
		return this.addressId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发货地址
	 */
	public void setAddressId(java.lang.String addressId){
		this.addressId = addressId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型
	 */
	
	@Column(name ="TYPE",nullable=true,length=1)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  价格
	 */
	
	@Column(name ="PRICE",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  价格
	 */
	public void setPrice(java.math.BigDecimal price){
		this.price = price;
	}
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  总数量
	 */
	
	@Column(name ="TOTAL_AMOUNT",nullable=true,length=10)
	public java.lang.Integer getTotalAmount(){
		return this.totalAmount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  总数量
	 */
	public void setTotalAmount(java.lang.Integer totalAmount){
		this.totalAmount = totalAmount;
	}
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  库存
	 */
	
	@Column(name ="RESERVE",nullable=true,length=10)
	public java.lang.Integer getReserve(){
		return this.reserve;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  库存
	 */
	public void setReserve(java.lang.Integer reserve){
		this.reserve = reserve;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否推荐
	 */
	
	@Column(name ="IS_RECOMMEND",nullable=true,length=1)
	public java.lang.String getIsRecommend(){
		return this.isRecommend;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否推荐
	 */
	public void setIsRecommend(java.lang.String isRecommend){
		this.isRecommend = isRecommend;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否热卖
	 */
	
	@Column(name ="IS_HOT",nullable=true,length=1)
	public java.lang.String getIsHot(){
		return this.isHot;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否热卖
	 */
	public void setIsHot(java.lang.String isHot){
		this.isHot = isHot;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产厂家
	 */
	
	@Column(name ="MANUFACTURERS",nullable=true,length=200)
	public java.lang.String getManufacturers(){
		return this.manufacturers;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产厂家
	 */
	public void setManufacturers(java.lang.String manufacturers){
		this.manufacturers = manufacturers;
	}
	
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  运费
	 */
	
	@Column(name ="FARE",nullable=true,scale=2,length=8)
	public java.math.BigDecimal getFare(){
		return this.fare;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  运费
	 */
	public void setFare(java.math.BigDecimal fare){
		this.fare = fare;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否买家自取
	 */
	
	@Column(name ="IS_TAKE",nullable=true,length=1)
	public java.lang.String getIsTake(){
		return this.isTake;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否买家自取
	 */
	public void setIsTake(java.lang.String isTake){
		this.isTake = isTake;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  品牌
	 */
	
	@Column(name ="BRAND",nullable=true,length=36)
	public java.lang.String getBrand(){
		return this.brand;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  品牌
	 */
	public void setBrand(java.lang.String brand){
		this.brand = brand;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  型号
	 */
	
	@Column(name ="MODEL",nullable=true,length=36)
	public java.lang.String getModel(){
		return this.model;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  型号
	 */
	public void setModel(java.lang.String model){
		this.model = model;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  位置
	 */
	
	@Column(name ="POSITION",nullable=true,length=36)
	public java.lang.String getPosition(){
		return this.position;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  位置
	 */
	public void setPosition(java.lang.String position){
		this.position = position;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  零件
	 */
	
	@Column(name ="COMPONENTS",nullable=true,length=36)
	public java.lang.String getComponents(){
		return this.components;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  零件
	 */
	public void setComponents(java.lang.String components){
		this.components = components;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商品描述
	 */
	
	@Column(name ="DETAILS",nullable=true,length=500)
	public java.lang.String getDetails(){
		return this.details;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品描述
	 */
	public void setDetails(java.lang.String details){
		this.details = details;
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
	
}
