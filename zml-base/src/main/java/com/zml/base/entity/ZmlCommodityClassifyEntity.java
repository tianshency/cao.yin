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
 * @Description: 商品分类
 * @author onlineGenerator
 * @date 2017-02-10 23:39:53
 * @version V1.0   
 *
 */
@Entity
@Table(name = "zml_commodity_classify", schema = "")
@SuppressWarnings("serial")
public class ZmlCommodityClassifyEntity implements java.io.Serializable {
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
	/**分类*/
	@Excel(name="分类")
	private java.lang.String classify;
	/**分类代码*/
	@Excel(name="分类代码")
	private java.lang.String classifyCode;
	/**名称*/
	@Excel(name="名称")
	private java.lang.String name;
	/**介绍*/
	@Excel(name="介绍")
	private java.lang.String introduction;
	/**标志*/
	@Excel(name="标志")
	private java.lang.String logo;
	/**父ID*/
	@Excel(name="父ID")
	private java.lang.String parentId;
	/**是否热门推荐*/
	@Excel(name="是否热门推荐")
	private java.lang.String isHotRecommend;
	/**是否热门搜索*/
	@Excel(name="是否热门搜索")
	private java.lang.String isHotSearch;
	/**排序号*/
	@Excel(name="排序号")
	private java.lang.Integer orderNum;
	/**状态*/
	@Excel(name="状态")
	private java.lang.Integer status;
	
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
	 *@return: java.lang.String  分类
	 */
	@Column(name ="CLASSIFY",nullable=true,length=2)
	public java.lang.String getClassify(){
		return this.classify;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分类
	 */
	public void setClassify(java.lang.String classify){
		this.classify = classify;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分类代码
	 */
	@Column(name ="CLASSIFY_CODE",nullable=true,length=200)
	public java.lang.String getClassifyCode(){
		return this.classifyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分类代码
	 */
	public void setClassifyCode(java.lang.String classifyCode){
		this.classifyCode = classifyCode;
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
	@Column(name ="INTRODUCTION",nullable=true,length=500)
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
	 *@return: java.lang.String  父ID
	 */
	@Column(name ="PARENT_ID",nullable=true,length=36)
	public java.lang.String getParentId(){
		return this.parentId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  父ID
	 */
	public void setParentId(java.lang.String parentId){
		this.parentId = parentId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否热门推荐
	 */
	@Column(name ="IS_HOT_RECOMMEND",nullable=true,length=1)
	public java.lang.String getIsHotRecommend(){
		return this.isHotRecommend;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否热门推荐
	 */
	public void setIsHotRecommend(java.lang.String isHotRecommend){
		this.isHotRecommend = isHotRecommend;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否热门搜索
	 */
	@Column(name ="IS_HOT_SEARCH",nullable=true,length=1)
	public java.lang.String getIsHotSearch(){
		return this.isHotSearch;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否热门搜索
	 */
	public void setIsHotSearch(java.lang.String isHotSearch){
		this.isHotSearch = isHotSearch;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  排序号
	 */
	@Column(name ="ORDER_NUM",nullable=true,length=10)
	public java.lang.Integer getOrderNum(){
		return this.orderNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  排序号
	 */
	public void setOrderNum(java.lang.Integer orderNum){
		this.orderNum = orderNum;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  状态
	 */
	@Column(name ="STATUS",nullable=true,length=1)
	public java.lang.Integer getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  状态
	 */
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
}
