package com.zml.base.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import com.alibaba.fastjson.annotation.JSONField;

/**   
 * @Title: Entity
 * @Description: 发布粮食
 */
@Entity
@Table(name = "zml_user_release_food", schema = "")
@SuppressWarnings("serial")
public class ZmlUserReleaseFoodEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**版本*/
	private java.lang.String version;
	
	/**类型名称*/
	@Excel(name="类型名称")
	private java.lang.String type;
	/**发布人*/
	@Excel(name="发布人")
	private java.lang.String userId;
	/**标题*/
	@Excel(name="标题")
	private java.lang.String title;
	/**种类*/
	@Excel(name="种类")
	private java.lang.String kind;
	/**价格*/
	@Excel(name="价格")
	private java.math.BigDecimal price;
	/**单位*/
	@Excel(name="单位")
	private java.lang.String unit;
	/**水分*/
	@Excel(name="水分")
	private java.math.BigDecimal moisture;
	/**霉变*/
	@Excel(name="霉变")
	private java.math.BigDecimal mildew;
	/**容重*/
	@Excel(name="容重")
	private java.math.BigDecimal bulkDensity;
	/**粮食类型*/
	@Excel(name="粮食类型")
	private java.lang.String foodType;
	/**筛选类型*/
	@Excel(name="筛选类型")
	private java.lang.String filterType;
	/**年份*/
	@Excel(name="年份")
	private java.lang.String particularYear;
	/**总数量*/
	@Excel(name="总数量")
	private java.math.BigDecimal totalNum;
	/**有效开始日期*/
	@Excel(name="有效开始日期",format = "yyyy-MM-dd")
	private java.util.Date validStartDate;
	/**有效结束日期*/
	@Excel(name="有效结束日期",format = "yyyy-MM-dd")
	private java.util.Date validEndDate;
	/**封面照片*/
	@Excel(name="封面照片")
	private java.lang.String coverImg;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String remark;
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
	
	@Excel(name="浏览数量")
	private java.lang.Integer viewCount;//浏览数量
    
    @Excel(name="收藏数量")
	private java.lang.Integer collectionCount;//收藏数量
    
    @Excel(name="关注数量")
	private java.lang.Integer attentionCount;//关注数量
    
    @Excel(name="是否审核")
	private java.lang.Integer isVerify;//是否审核
    
    @Excel(name="是否置顶")
	private java.lang.Integer isSticky;//是否置顶
	
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
	 *@return: java.lang.String  标题
	 */
	@Column(name ="TITLE",nullable=true,length=200)
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
	 *@return: java.lang.String  种类
	 */
	@Column(name ="KIND",nullable=true,length=2)
	public java.lang.String getKind(){
		return this.kind;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  种类
	 */
	public void setKind(java.lang.String kind){
		this.kind = kind;
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
	 *@return: java.lang.String  单位
	 */
	@Column(name ="UNIT",nullable=true,length=32)
	public java.lang.String getUnit(){
		return this.unit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单位
	 */
	public void setUnit(java.lang.String unit){
		this.unit = unit;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  水分
	 */
	@Column(name ="MOISTURE",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getMoisture(){
		return this.moisture;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  水分
	 */
	public void setMoisture(java.math.BigDecimal moisture){
		this.moisture = moisture;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  霉变
	 */
	@Column(name ="MILDEW",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getMildew(){
		return this.mildew;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  霉变
	 */
	public void setMildew(java.math.BigDecimal mildew){
		this.mildew = mildew;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  容重
	 */
	@Column(name ="BULK_DENSITY",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getBulkDensity(){
		return this.bulkDensity;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  容重
	 */
	public void setBulkDensity(java.math.BigDecimal bulkDensity){
		this.bulkDensity = bulkDensity;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  粮食类型
	 */
	@Column(name ="FOOD_TYPE",nullable=true,length=2)
	public java.lang.String getFoodType(){
		return this.foodType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  粮食类型
	 */
	public void setFoodType(java.lang.String foodType){
		this.foodType = foodType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  筛选类型
	 */
	@Column(name ="FILTER_TYPE",nullable=true,length=2)
	public java.lang.String getFilterType(){
		return this.filterType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  筛选类型
	 */
	public void setFilterType(java.lang.String filterType){
		this.filterType = filterType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  年份
	 */
	@Column(name ="PARTICULAR_YEAR",nullable=true,length=10)
	public java.lang.String getParticularYear(){
		return this.particularYear;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  年份
	 */
	public void setParticularYear(java.lang.String particularYear){
		this.particularYear = particularYear;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  总数量
	 */
	@Column(name ="TOTAL_NUM",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getTotalNum(){
		return this.totalNum;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  总数量
	 */
	public void setTotalNum(java.math.BigDecimal totalNum){
		this.totalNum = totalNum;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARK",nullable=true,length=2000)
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	@Column(name ="STATUS",nullable=true,length=32)
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
	
	@Column(name ="TYPE",nullable=true,length=1)
	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
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
	
	@Column(name ="IS_VERIFY",nullable=true,length=1)
	public java.lang.Integer getIsVerify() {
		return isVerify;
	}
	
	public void setIsVerify(java.lang.Integer isVerify) {
		this.isVerify = isVerify;
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
