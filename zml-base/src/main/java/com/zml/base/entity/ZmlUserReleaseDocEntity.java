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
 * @Description: 发布文档
 */
@Entity
@Table(name = "zml_user_release_doc", schema = "")
@SuppressWarnings("serial")
public class ZmlUserReleaseDocEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新日期*/
	private java.util.Date updateDate;
	/**版本*/
	private java.lang.String version;
	/**主图标识*/
	@Excel(name="主图标识")
	private java.lang.Integer isTop;
	/**发布ID*/
	@Excel(name="发布ID")
	private java.lang.String releaseId;
	/**标识*/
	@Excel(name="标识")
	private java.lang.String fileFlag;
	/**类型*/
	@Excel(name="类型")
	private java.lang.String releaseType;
	/**图片路径*/
	@Excel(name="图片路径")
	private java.lang.String imagePath;
	/**文件路径*/
	@Excel(name="文件路径")
	private java.lang.String filePath;
	/**描述*/
	@Excel(name="描述")
	private java.lang.String details;
	
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  主图标识
	 */
	@Column(name ="IS_TOP",nullable=true,length=1)
	public java.lang.Integer getIsTop(){
		return this.isTop;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  主图标识
	 */
	public void setIsTop(java.lang.Integer isTop){
		this.isTop = isTop;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发布ID
	 */
	@Column(name ="RELEASE_ID",nullable=true,length=36)
	public java.lang.String getReleaseId(){
		return this.releaseId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发布ID
	 */
	public void setReleaseId(java.lang.String releaseId){
		this.releaseId = releaseId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标识
	 */
	@Column(name ="FILE_FLAG",nullable=true,length=1)
	public java.lang.String getFileFlag(){
		return this.fileFlag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标识
	 */
	public void setFileFlag(java.lang.String fileFlag){
		this.fileFlag = fileFlag;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型
	 */
	@Column(name ="RELEASE_TYPE",nullable=true,length=2)
	public java.lang.String getReleaseType(){
		return this.releaseType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型
	 */
	public void setReleaseType(java.lang.String releaseType){
		this.releaseType = releaseType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图片路径
	 */
	@Column(name ="IMAGE_PATH",nullable=true,length=200)
	public java.lang.String getImagePath(){
		return this.imagePath;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片路径
	 */
	public void setImagePath(java.lang.String imagePath){
		this.imagePath = imagePath;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  文件路径
	 */
	@Column(name ="FILE_PATH",nullable=true,length=200)
	public java.lang.String getFilePath(){
		return this.filePath;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  文件路径
	 */
	public void setFilePath(java.lang.String filePath){
		this.filePath = filePath;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  描述
	 */
	@Column(name ="DETAILS",nullable=true,length=500)
	public java.lang.String getDetails(){
		return this.details;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述
	 */
	public void setDetails(java.lang.String details){
		this.details = details;
	}
}
