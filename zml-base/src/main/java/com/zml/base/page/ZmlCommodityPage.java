
package com.zml.base.page;
import java.util.ArrayList;
import java.util.List;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

import com.zml.base.entity.ZmlCommodityDocEntity;
import com.zml.base.entity.ZmlCommodityStandardEntity;

/**   
 * @Title: Entity
 * @Description: 商品
 * @author onlineGenerator
 * @date 2017-01-13 15:57:38
 * @version V1.0   
 *
 */
public class ZmlCommodityPage implements java.io.Serializable {
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
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
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

	/**保存-文档*/
    @ExcelCollection(name="文档")
	private List<ZmlCommodityDocEntity> zmlCommodityDocList = new ArrayList<ZmlCommodityDocEntity>();
		public List<ZmlCommodityDocEntity> getZmlCommodityDocList() {
		return zmlCommodityDocList;
		}
		public void setZmlCommodityDocList(List<ZmlCommodityDocEntity> zmlCommodityDocList) {
		this.zmlCommodityDocList = zmlCommodityDocList;
		}
	/**保存-适用标准*/
    @ExcelCollection(name="适用标准")
	private List<ZmlCommodityStandardEntity> zmlCommodityStandardList = new ArrayList<ZmlCommodityStandardEntity>();
		public List<ZmlCommodityStandardEntity> getZmlCommodityStandardList() {
		return zmlCommodityStandardList;
		}
		public void setZmlCommodityStandardList(List<ZmlCommodityStandardEntity> zmlCommodityStandardList) {
		this.zmlCommodityStandardList = zmlCommodityStandardList;
		}
}
