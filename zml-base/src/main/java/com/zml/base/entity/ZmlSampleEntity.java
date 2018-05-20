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
 * @Description: 例子
 * @author onlineGenerator
 * @date 2017-01-21 18:30:20
 * @version V1.0   
 *
 */
@Entity
@Table(name = "zml_sample", schema = "")
@SuppressWarnings("serial")
public class ZmlSampleEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新日期*/
	private java.util.Date updateDate;
	/**版本*/
	private java.lang.String version;
	/**图片*/
	@Excel(name="图片")
	private java.lang.String imgSample;
	/**文件*/
	@Excel(name="文件")
	private java.lang.String fileSample;
	/**复选*/
	@Excel(name="复选")
	private java.lang.String fuxuankuan;
	/**单选*/
	@Excel(name="单选")
	private java.lang.String danxuan;
	/**时间*/
	@Excel(name="时间")
	private java.lang.String shijian;
	/**多文本*/
	@Excel(name="多文本")
	private java.lang.String duowenben;
	
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
	 *@return: java.lang.String  图片
	 */
	@Column(name ="IMG_SAMPLE",nullable=true,length=200)
	public java.lang.String getImgSample(){
		return this.imgSample;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片
	 */
	public void setImgSample(java.lang.String imgSample){
		this.imgSample = imgSample;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  文件
	 */
	@Column(name ="FILE_SAMPLE",nullable=true,length=200)
	public java.lang.String getFileSample(){
		return this.fileSample;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  文件
	 */
	public void setFileSample(java.lang.String fileSample){
		this.fileSample = fileSample;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  复选
	 */
	@Column(name ="FUXUANKUAN",nullable=true,length=100)
	public java.lang.String getFuxuankuan(){
		return this.fuxuankuan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  复选
	 */
	public void setFuxuankuan(java.lang.String fuxuankuan){
		this.fuxuankuan = fuxuankuan;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单选
	 */
	@Column(name ="DANXUAN",nullable=true,length=20)
	public java.lang.String getDanxuan(){
		return this.danxuan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单选
	 */
	public void setDanxuan(java.lang.String danxuan){
		this.danxuan = danxuan;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  时间
	 */
	@Column(name ="SHIJIAN",nullable=true,length=20)
	public java.lang.String getShijian(){
		return this.shijian;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  时间
	 */
	public void setShijian(java.lang.String shijian){
		this.shijian = shijian;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  多文本
	 */
	@Column(name ="DUOWENBEN",nullable=true,length=2000)
	public java.lang.String getDuowenben(){
		return this.duowenben;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  多文本
	 */
	public void setDuowenben(java.lang.String duowenben){
		this.duowenben = duowenben;
	}
}
