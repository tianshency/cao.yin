package com.zml.base.entity;

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
 * @Description: 我的预定
 * @author onlineGenerator
 * @date 2017-01-15 21:04:26
 * @version V1.0   
 *
 */
@Entity
@Table(name = "zml_user_booking", schema = "")
@SuppressWarnings("serial")
public class ZmlUserBookingEntity implements java.io.Serializable {
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
	private java.lang.String version;
	/**用户ID*/
	@Excel(name="用户ID")
	private java.lang.String userId;
	/**对方ID*/
	@Excel(name="对方ID")
	private java.lang.String otherSideId;
	/**预订类型*/
	@Excel(name="预订类型")
	private java.lang.String bookingType;
	/**预订ID*/
	@Excel(name="预订ID")
	private java.lang.String bookingId;
	/**数量*/
	@Excel(name="数量")
	private java.lang.String bookingNum;
	/**计划交易日*/
	@Excel(name="计划交易日",format = "yyyy-MM-dd")
	private java.util.Date planDealDate;
	/**用户名*/
	private java.lang.String userName;
	/**对方名称*/
	private java.lang.String otherSideName;
	//联系电话
	private java.lang.String phone;
	/**预定标题*/
	private java.lang.String bookingTitle;
	/**预定描述*/
	private java.lang.String bookingDetail;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String remarks;
	/**状态*/
	@Excel(name="状态")
	private java.lang.String status;
	/**排列序号*/
	@Excel(name="排列序号*")
	private java.lang.Integer orderNo;
	/**预订单位*/
	@Excel(name="预订单位*")
	private java.lang.String unit;
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
	 *@return: java.lang.String  对方ID
	 */
	@Column(name ="OTHER_SIDE_ID",nullable=true,length=36)
	public java.lang.String getOtherSideId(){
		return this.otherSideId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  对方ID
	 */
	public void setOtherSideId(java.lang.String otherSideId){
		this.otherSideId = otherSideId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预订类型
	 */
	@Column(name ="BOOKING_TYPE",nullable=true,length=36)
	public java.lang.String getBookingType(){
		return this.bookingType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预订类型
	 */
	public void setBookingType(java.lang.String bookingType){
		this.bookingType = bookingType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预订ID
	 */
	@Column(name ="BOOKING_ID",nullable=true,length=36)
	public java.lang.String getBookingId(){
		return this.bookingId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预订ID
	 */
	public void setBookingId(java.lang.String bookingId){
		this.bookingId = bookingId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  数量
	 */
	@Column(name ="BOOKING_NUM",nullable=true,scale=2,length=14)
	public java.lang.String getBookingNum(){
		return this.bookingNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  数量
	 */
	public void setBookingNum(java.lang.String bookingNum){
		this.bookingNum = bookingNum;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  计划交易日
	 */
	@Column(name ="PLAN_DEAL_DATE",nullable=true,length=20)
	public java.util.Date getPlanDealDate(){
		return this.planDealDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  计划交易日
	 */
	public void setPlanDealDate(java.util.Date planDealDate){
		this.planDealDate = planDealDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户名
	 */
	@Column(name ="USER_NAME",nullable=true,length=50)
	public java.lang.String getUserName(){
		return this.userName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户名
	 */
	public void setUserName(java.lang.String userName){
		this.userName = userName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  对方名称
	 */
	@Column(name ="OTHER_SIDE_NAME",nullable=true,length=50)
	public java.lang.String getOtherSideName(){
		return this.otherSideName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  对方名称
	 */
	public void setOtherSideName(java.lang.String otherSideName){
		this.otherSideName = otherSideName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预定描述
	 */
	@Column(name ="BOOKING_DETAIL",nullable=true,length=200)
	public java.lang.String getBookingDetail(){
		return this.bookingDetail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预定描述
	 */
	public void setBookingDetail(java.lang.String bookingDetail){
		this.bookingDetail = bookingDetail;
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
	
	@Column(name ="phone",nullable=true,length=15)
	public java.lang.String getPhone() {
		return phone;
	}

	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}
	
	@Column(name ="booking_title",nullable=true,length=200)
	public java.lang.String getBookingTitle() {
		return bookingTitle;
	}

	public void setBookingTitle(java.lang.String bookingTitle) {
		this.bookingTitle = bookingTitle;
	}

	@Column(name ="order_no",nullable=true,length=11)
	public java.lang.Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(java.lang.Integer orderNo) {
		this.orderNo = orderNo;
	}
	@Column(name ="unit",nullable=true,length=20)
	public java.lang.String getUnit() {
		return unit;
	}

	public void setUnit(java.lang.String unit) {
		this.unit = unit;
	}
	
	
	
	
}
