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
 * @Description: 商品
 *
 */
@Entity
@Table(name = "zml_commodity", schema = "")
@SuppressWarnings("serial")
public class ZmlCommodityEntity implements java.io.Serializable {
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
	/**商户*/
	private java.lang.String merchantsId;
	/**商户名称*/
	private java.lang.String manufacturers;
	/**商品名称*/
    @Excel(name="商品名称")
	private java.lang.String name;
	/**批次号*/
	private java.lang.String batchNumber;
	/**介绍*/
    @Excel(name="介绍")
	private java.lang.String introduction;
	/**总数量*/
    @Excel(name="总数量")
	private java.lang.Integer totalAmount;
	/**数量单位*/
    @Excel(name="数量单位")
	private java.lang.String amountUnit;
	/**商品规格*/
    @Excel(name="商品规格")
	private java.lang.String commercialSpecification;
	/**规格重量*/
    @Excel(name="规格重量")
	private java.math.BigDecimal specificationWeight;
	/**库存数量*/
	private java.lang.Integer reserveAmount;
	/**原价格*/
    @Excel(name="原价格")
	private java.math.BigDecimal price;
	/**实际价格*/
    @Excel(name="实际价格")
	private java.math.BigDecimal realPrice;
	/**生产日期*/
    @Excel(name="生产日期",format = "yyyy-MM-dd")
	private java.util.Date productionDate;
	/**有效期*/
    @Excel(name="有效期")
	private java.lang.Integer periodOfValidity;
	/**有效期单位*/
    @Excel(name="有效期单位")
	private java.lang.Integer povUnit;
	/**是否推荐*/
    @Excel(name="是否推荐")
	private java.lang.String isRecommend;
	/**是否热卖*/
    @Excel(name="是否热卖")
	private java.lang.String isHot;
	/**一级分类*/
    @Excel(name="一级分类")
	private java.lang.String classifyOneLevel;
	/**二级分类*/
    @Excel(name="二级分类")
	private java.lang.String classifyTwoLevel;
	/**商品描述*/
	private java.lang.String details;
	/**状态*/
    @Excel(name="状态")
	private java.lang.String status;
	/**运费*/
    @Excel(name="运费")
	private java.math.BigDecimal fare;
    
    @Excel(name="出售总数量")
	private java.lang.Integer sellCount;//出售总数量
    
    @Excel(name="浏览数量")
	private java.lang.Integer viewCount;//浏览数量
    
    @Excel(name="收藏数量")
	private java.lang.Integer collectionCount;//收藏数量
    
    @Excel(name="关注数量")
	private java.lang.Integer attentionCount;//关注数量
    
    @Excel(name="封面")
	private java.lang.String coverImg;//封面
    
    
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
	 *@return: java.lang.String  商户
	 */
	
	@Column(name ="MERCHANTS_ID",nullable=true,length=36)
	public java.lang.String getMerchantsId(){
		return this.merchantsId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商户
	 */
	public void setMerchantsId(java.lang.String merchantsId){
		this.merchantsId = merchantsId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商户名称
	 */
	
	@Column(name ="MANUFACTURERS",nullable=true,length=200)
	public java.lang.String getManufacturers(){
		return this.manufacturers;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商户名称
	 */
	public void setManufacturers(java.lang.String manufacturers){
		this.manufacturers = manufacturers;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商品名称
	 */
	
	@Column(name ="NAME",nullable=true,length=200)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  批次号
	 */
	
	@Column(name ="BATCH_NUMBER",nullable=true,length=100)
	public java.lang.String getBatchNumber(){
		return this.batchNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  批次号
	 */
	public void setBatchNumber(java.lang.String batchNumber){
		this.batchNumber = batchNumber;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  数量单位
	 */
	
	@Column(name ="AMOUNT_UNIT",nullable=true,length=2)
	public java.lang.String getAmountUnit(){
		return this.amountUnit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  数量单位
	 */
	public void setAmountUnit(java.lang.String amountUnit){
		this.amountUnit = amountUnit;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商品规格
	 */
	
	@Column(name ="COMMERCIAL_SPECIFICATION",nullable=true,length=2)
	public java.lang.String getCommercialSpecification(){
		return this.commercialSpecification;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品规格
	 */
	public void setCommercialSpecification(java.lang.String commercialSpecification){
		this.commercialSpecification = commercialSpecification;
	}
	
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  规格重量
	 */
	
	@Column(name ="SPECIFICATION_WEIGHT",nullable=true,scale=2,length=12)
	public java.math.BigDecimal getSpecificationWeight(){
		return this.specificationWeight;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  规格重量
	 */
	public void setSpecificationWeight(java.math.BigDecimal specificationWeight){
		this.specificationWeight = specificationWeight;
	}
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  库存数量
	 */
	
	@Column(name ="RESERVE_AMOUNT",nullable=true,length=10)
	public java.lang.Integer getReserveAmount(){
		return this.reserveAmount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  库存数量
	 */
	public void setReserveAmount(java.lang.Integer reserveAmount){
		this.reserveAmount = reserveAmount;
	}
	
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  原价格
	 */
	
	@Column(name ="PRICE",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  原价格
	 */
	public void setPrice(java.math.BigDecimal price){
		this.price = price;
	}
	
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  实际价格
	 */
	
	@Column(name ="REAL_PRICE",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getRealPrice(){
		return this.realPrice;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  实际价格
	 */
	public void setRealPrice(java.math.BigDecimal realPrice){
		this.realPrice = realPrice;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  生产日期
	 */
	
	@Column(name ="PRODUCTION_DATE",nullable=true,length=20)
	public java.util.Date getProductionDate(){
		return this.productionDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  生产日期
	 */
	public void setProductionDate(java.util.Date productionDate){
		this.productionDate = productionDate;
	}
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  有效期
	 */
	
	@Column(name ="PERIOD_OF_VALIDITY",nullable=true,length=8)
	public java.lang.Integer getPeriodOfValidity(){
		return this.periodOfValidity;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  有效期
	 */
	public void setPeriodOfValidity(java.lang.Integer periodOfValidity){
		this.periodOfValidity = periodOfValidity;
	}
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  有效期单位
	 */
	
	@Column(name ="POV_UNIT",nullable=true,length=2)
	public java.lang.Integer getPovUnit(){
		return this.povUnit;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  有效期单位
	 */
	public void setPovUnit(java.lang.Integer povUnit){
		this.povUnit = povUnit;
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
	 *@return: java.lang.String  一级分类
	 */
	
	@Column(name ="CLASSIFY_ONE_LEVEL",nullable=true,length=36)
	public java.lang.String getClassifyOneLevel(){
		return this.classifyOneLevel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  一级分类
	 */
	public void setClassifyOneLevel(java.lang.String classifyOneLevel){
		this.classifyOneLevel = classifyOneLevel;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  二级分类
	 */
	
	@Column(name ="CLASSIFY_TWO_LEVEL",nullable=true,length=36)
	public java.lang.String getClassifyTwoLevel(){
		return this.classifyTwoLevel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  二级分类
	 */
	public void setClassifyTwoLevel(java.lang.String classifyTwoLevel){
		this.classifyTwoLevel = classifyTwoLevel;
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
	@Column(name ="SELL_COUNT",nullable=true,length=11)
	public java.lang.Integer getSellCount() {
		return sellCount;
	}

	public void setSellCount(java.lang.Integer sellCount) {
		this.sellCount = sellCount;
	}
	@Column(name ="VIEW_COUNT",nullable=true,length=11)
	public java.lang.Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(java.lang.Integer viewCount) {
		this.viewCount = viewCount;
	}
	@Column(name ="COLLECTION_COUNT",nullable=true,length=11)
	public java.lang.Integer getCollectionCount() {
		return collectionCount;
	}

	public void setCollectionCount(java.lang.Integer collectionCount) {
		this.collectionCount = collectionCount;
	}
	@Column(name ="ATTENTION_COUNT",nullable=true,length=11)
	public java.lang.Integer getAttentionCount() {
		return attentionCount;
	}

	public void setAttentionCount(java.lang.Integer attentionCount) {
		this.attentionCount = attentionCount;
	}
	@Column(name ="COVER_IMG",nullable=true,length=11)
	public java.lang.String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(java.lang.String coverImg) {
		this.coverImg = coverImg;
	}
	
}
