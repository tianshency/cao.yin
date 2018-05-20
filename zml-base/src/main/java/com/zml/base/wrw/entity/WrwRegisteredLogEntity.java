package com.zml.base.wrw.entity;

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
 * @Description: 注册日志
 *
 */
@Entity
@Table(name = "wrw_registered_log", schema = "")
@SuppressWarnings("serial")
public class WrwRegisteredLogEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新日期*/
	private java.util.Date updateDate;
	/**版本*/
	private java.lang.String version;
	/**手机号*/
	@Excel(name="手机号")
	private java.lang.String phone;
	/**任务*/
	@Excel(name="任务")
	private java.lang.String taskId;
	/**来源*/
	@Excel(name="来源")
	private java.lang.String source;
	/**状态*/
	@Excel(name="状态")
	private java.lang.String satus;
	/**请求信息*/
	@Excel(name="请求信息")
	private java.lang.String requestInfo;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String remark;
	private java.lang.String token;
	//推荐人
	private java.lang.String recommended;
	//对应用户表id
	private String userId;
	private BigDecimal amount;
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
	 *@return: java.lang.String  手机号
	 */
	@Column(name ="PHONE",nullable=true,length=12)
	public java.lang.String getPhone(){
		return this.phone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机号
	 */
	public void setPhone(java.lang.String phone){
		this.phone = phone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  任务
	 */
	@Column(name ="TASK_ID",nullable=true,length=36)
	public java.lang.String getTaskId(){
		return this.taskId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  任务
	 */
	public void setTaskId(java.lang.String taskId){
		this.taskId = taskId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  来源
	 */
	@Column(name ="SOURCE",nullable=true,length=50)
	public java.lang.String getSource(){
		return this.source;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  来源
	 */
	public void setSource(java.lang.String source){
		this.source = source;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	@Column(name ="SATUS",nullable=true,length=32)
	public java.lang.String getSatus(){
		return this.satus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setSatus(java.lang.String satus){
		this.satus = satus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  请求信息
	 */
	@Column(name ="REQUEST_INFO",nullable=true,length=1000)
	public java.lang.String getRequestInfo(){
		return this.requestInfo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  请求信息
	 */
	public void setRequestInfo(java.lang.String requestInfo){
		this.requestInfo = requestInfo;
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
	@Column(name ="TOKEN",nullable=true)
	public java.lang.String getToken() {
		return token;
	}

	public void setToken(java.lang.String token) {
		this.token = token;
	}
	@Column(name ="RECOMMENDED",nullable=true)
	public java.lang.String getRecommended() {
		return recommended;
	}

	public void setRecommended(java.lang.String recommended) {
		this.recommended = recommended;
	}
	@Column(name ="USER_ID",nullable=true)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
