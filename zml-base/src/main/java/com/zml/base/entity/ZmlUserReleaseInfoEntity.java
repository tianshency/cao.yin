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
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**   
 * @Title: Entity
 * @Description: 发布信息 综合信息
 */
@Entity
@Table(name = "zml_user_release_info", schema = "")
@SuppressWarnings("serial")
public class ZmlUserReleaseInfoEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createDate;
	/**更新日期*/
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateDate;
	/**版本*/
	private java.lang.String version;
	/**发布人*/
	@Excel(name="发布人")
	private java.lang.String userId;
	/**发布人名称*/
	@Excel(name="发布人名称")
	private java.lang.String userName;
	/**发布人电话*/
	@Excel(name="发布人电话")
	private java.lang.String userPhone;
	/**标题*/
	@Excel(name="标题")
	private java.lang.String title;
	/**发布类型*/
	@Excel(name="发布类型")
	private java.lang.String releaseType;
	/**封面照片*/
	@Excel(name="封面照片")
	private java.lang.String coverImg;
	/**数量*/
	@Excel(name="数量")
	private java.lang.Integer amount;
	/**数量单位*/
	@Excel(name="数量单位")
	private java.lang.String amountUnit;
	/**价格*/
	@Excel(name="价格")
	private java.math.BigDecimal price;
	/**价格单位*/
	@Excel(name="价格单位")
	private java.lang.String priceUnit;
	/**有效开始日期*/
	@Excel(name="有效开始日期",format = "yyyy-MM-dd")
	private java.util.Date validStartDate;
	/**有效结束日期*/
	@Excel(name="有效结束日期",format = "yyyy-MM-dd")
	private java.util.Date validEndDate;
	/**描述*/
	@Excel(name="描述")
	private java.lang.String description;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String remarks;
	/**状态*/
	@Excel(name="状态")
	private java.lang.String status;
	/**审批意见*/
	@Excel(name="审批意见")
	private java.lang.String approvalOpinion;
	/**审批时间*/
	@Excel(name="审批时间")
	private Date approvalTime;
	/**审批人*/
	@Excel(name="审批人")
	private java.lang.String approvalUserId;
	/**浏览数量*/
	@Excel(name="浏览数量")
	private java.lang.Integer viewCount;
	/**收藏数量*/
	@Excel(name="收藏数量")
	private java.lang.Integer collectionCount;
	/**关注数量*/
	@Excel(name="关注数量")
	private java.lang.Integer attentionCount;
	/**是否审核*/
	@Excel(name="是否审核")
	private java.lang.Integer isVerify;
	
	@Excel(name="是否置顶")
	private java.lang.Integer isSticky;//是否置顶
	
	/**子分类*/
	@Excel(name="最小子分类")
	private java.lang.String kind;
	/**子分类*/
	@Excel(name="二级子分类")
	private java.lang.String childType;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否审核
	 */
	@Column(name ="child_type",nullable=true,length=1)
	public java.lang.String getChildType() {
		return childType;
	}

	public void setChildType(java.lang.String childType) {
		this.childType = childType;
	}

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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  版本
	 */
	@Column(name ="VERSION",nullable=true,length=50)
	public java.lang.String getVersion(){
		return this.version;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  版本
	 */
	public void setVersion(java.lang.String version){
		this.version = version;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发布人
	 */
	@Column(name ="USER_ID",nullable=true,length=36)
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发布人
	 */
	public void setUserId(java.lang.String userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发布人名称
	 */
	@Column(name ="USER_NAME",nullable=true,length=32)
	public java.lang.String getUserName(){
		return this.userName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发布人名称
	 */
	public void setUserName(java.lang.String userName){
		this.userName = userName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发布人电话
	 */
	@Column(name ="USER_PHONE",nullable=true,length=15)
	public java.lang.String getUserPhone(){
		return this.userPhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发布人电话
	 */
	public void setUserPhone(java.lang.String userPhone){
		this.userPhone = userPhone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标题
	 */
	@Column(name ="TITLE",nullable=true,length=100)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标题
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发布类型
	 */
	@Column(name ="RELEASE_TYPE",nullable=true,length=4)
	public java.lang.String getReleaseType(){
		return this.releaseType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发布类型
	 */
	public void setReleaseType(java.lang.String releaseType){
		this.releaseType = releaseType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  封面照片
	 */
	@Column(name ="COVER_IMG",nullable=true,length=200)
	public java.lang.String getCoverImg(){
		return this.coverImg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  封面照片
	 */
	public void setCoverImg(java.lang.String coverImg){
		this.coverImg = coverImg;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  数量
	 */
	@Column(name ="AMOUNT",nullable=true,length=10)
	public java.lang.Integer getAmount(){
		return this.amount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  数量
	 */
	public void setAmount(java.lang.Integer amount){
		this.amount = amount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  数量单位
	 */
	@Column(name ="AMOUNT_UNIT",nullable=true,length=10)
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  价格单位
	 */
	@Column(name ="PRICE_UNIT",nullable=true,length=10)
	public java.lang.String getPriceUnit(){
		return this.priceUnit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  价格单位
	 */
	public void setPriceUnit(java.lang.String priceUnit){
		this.priceUnit = priceUnit;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  有效开始日期
	 */
	@Column(name ="VALID_START_DATE",nullable=true,length=20)
	public java.util.Date getValidStartDate(){
		return this.validStartDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  有效开始日期
	 */
	public void setValidStartDate(java.util.Date validStartDate){
		this.validStartDate = validStartDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  有效结束日期
	 */
	@Column(name ="VALID_END_DATE",nullable=true,length=20)
	public java.util.Date getValidEndDate(){
		return this.validEndDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  有效结束日期
	 */
	public void setValidEndDate(java.util.Date validEndDate){
		this.validEndDate = validEndDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  描述
	 */
	@Column(name ="DESCRIPTION",nullable=true,length=1000)
	public java.lang.String getDescription(){
		return this.description;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述
	 */
	public void setDescription(java.lang.String description){
		this.description = description;
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  浏览数量
	 */
	@Column(name ="VIEW_COUNT",nullable=true,length=10)
	public java.lang.Integer getViewCount(){
		return this.viewCount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  浏览数量
	 */
	public void setViewCount(java.lang.Integer viewCount){
		this.viewCount = viewCount;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  收藏数量
	 */
	@Column(name ="COLLECTION_COUNT",nullable=true,length=10)
	public java.lang.Integer getCollectionCount(){
		return this.collectionCount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  收藏数量
	 */
	public void setCollectionCount(java.lang.Integer collectionCount){
		this.collectionCount = collectionCount;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  关注数量
	 */
	@Column(name ="ATTENTION_COUNT",nullable=true,length=10)
	public java.lang.Integer getAttentionCount(){
		return this.attentionCount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  关注数量
	 */
	public void setAttentionCount(java.lang.Integer attentionCount){
		this.attentionCount = attentionCount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否审核
	 */
	@Column(name ="IS_VERIFY",nullable=true,length=1)
	public java.lang.Integer getIsVerify(){
		return this.isVerify;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否审核
	 */
	public void setIsVerify(java.lang.Integer isVerify){
		this.isVerify = isVerify;
	}
	@Column(name ="kind",nullable=true,length=1)
	public java.lang.String getKind() {
		return kind;
	}

	public void setKind(java.lang.String kind) {
		this.kind = kind;
	}
	
	@Column(name ="IS_STICKY",nullable=true,length=1)
	public java.lang.Integer getIsSticky() {
		return isSticky;
	}

	public void setIsSticky(java.lang.Integer isSticky) {
		this.isSticky = isSticky;
	}
	@Column(name ="APPROVAL_OPINION",nullable=true)
	public java.lang.String getApprovalOpinion() {
		return approvalOpinion;
	}

	public void setApprovalOpinion(java.lang.String approvalOpinion) {
		this.approvalOpinion = approvalOpinion;
	}
	@Column(name ="APPROVAL_TIME",nullable=true)
	public Date getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}
	@Column(name ="APPROVAL_USER_ID",nullable=true)
	public java.lang.String getApprovalUserId() {
		return approvalUserId;
	}

	public void setApprovalUserId(java.lang.String approvalUserId) {
		this.approvalUserId = approvalUserId;
	}
}
