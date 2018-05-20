
package com.zml.base.page;
import com.zml.base.entity.ZmlUserEntity;
import com.zml.base.entity.ZmlUserAddressEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

/**   
 * @Title: Entity
 * @Description: 用户信息
 * @author onlineGenerator
 * @date 2017-01-01 13:51:37
 * @version V1.0   
 *
 */
public class ZmlUserPage implements java.io.Serializable {
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
	/**用户名*/
    @Excel(name="用户名")
	private java.lang.String userName;
	/**昵称*/
    @Excel(name="昵称")
	private java.lang.String nickname;
	/**密码*/
	private java.lang.String password;
	/**联系电话*/
    @Excel(name="联系电话")
	private java.lang.String phone;
	/**真实姓名*/
    @Excel(name="真实姓名")
	private java.lang.String realName;
	/**身份证号码*/
    @Excel(name="身份证号码")
	private java.lang.String identificationNumber;
	/**年龄*/
    @Excel(name="年龄")
	private java.lang.Integer age;
	/**性别*/
    @Excel(name="性别")
	private java.lang.String sex;
	/**登录IP*/
	private java.lang.String loginIp;
	/**登录日期*/
	private java.util.Date loginDate;
	/**用户类型*/
    @Excel(name="用户类型")
	private java.lang.String type;
	/**是否实名认证*/
    @Excel(name="是否实名认证")
	private java.lang.String isVerified;
	/**头像*/
    @Excel(name="头像")
	private java.lang.String avatar;
	/**当前帐号是否禁用*/
    @Excel(name="当前帐号是否禁用")
	private java.lang.String locked;
	/**是否签订注册协议*/
    @Excel(name="是否签订注册协议")
	private java.lang.String registrationAgreement;
	
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
	 *@return: java.lang.String  用户名
	 */
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
	 *@return: java.lang.String  昵称
	 */
	public java.lang.String getNickname(){
		return this.nickname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  昵称
	 */
	public void setNickname(java.lang.String nickname){
		this.nickname = nickname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  密码
	 */
	public java.lang.String getPassword(){
		return this.password;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  密码
	 */
	public void setPassword(java.lang.String password){
		this.password = password;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系电话
	 */
	public java.lang.String getPhone(){
		return this.phone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系电话
	 */
	public void setPhone(java.lang.String phone){
		this.phone = phone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  真实姓名
	 */
	public java.lang.String getRealName(){
		return this.realName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  真实姓名
	 */
	public void setRealName(java.lang.String realName){
		this.realName = realName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  身份证号码
	 */
	public java.lang.String getIdentificationNumber(){
		return this.identificationNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  身份证号码
	 */
	public void setIdentificationNumber(java.lang.String identificationNumber){
		this.identificationNumber = identificationNumber;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  年龄
	 */
	public java.lang.Integer getAge(){
		return this.age;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  年龄
	 */
	public void setAge(java.lang.Integer age){
		this.age = age;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别
	 */
	public java.lang.String getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别
	 */
	public void setSex(java.lang.String sex){
		this.sex = sex;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  登录IP
	 */
	public java.lang.String getLoginIp(){
		return this.loginIp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  登录IP
	 */
	public void setLoginIp(java.lang.String loginIp){
		this.loginIp = loginIp;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  登录日期
	 */
	public java.util.Date getLoginDate(){
		return this.loginDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  登录日期
	 */
	public void setLoginDate(java.util.Date loginDate){
		this.loginDate = loginDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户类型
	 */
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户类型
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否实名认证
	 */
	public java.lang.String getIsVerified(){
		return this.isVerified;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否实名认证
	 */
	public void setIsVerified(java.lang.String isVerified){
		this.isVerified = isVerified;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  头像
	 */
	public java.lang.String getAvatar(){
		return this.avatar;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  头像
	 */
	public void setAvatar(java.lang.String avatar){
		this.avatar = avatar;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  当前帐号是否禁用
	 */
	public java.lang.String getLocked(){
		return this.locked;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  当前帐号是否禁用
	 */
	public void setLocked(java.lang.String locked){
		this.locked = locked;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否签订注册协议
	 */
	public java.lang.String getRegistrationAgreement(){
		return this.registrationAgreement;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否签订注册协议
	 */
	public void setRegistrationAgreement(java.lang.String registrationAgreement){
		this.registrationAgreement = registrationAgreement;
	}

	/**保存-用户地址*/
    @ExcelCollection(name="用户地址")
	private List<ZmlUserAddressEntity> zmlUserAddressList = new ArrayList<ZmlUserAddressEntity>();
		public List<ZmlUserAddressEntity> getZmlUserAddressList() {
		return zmlUserAddressList;
		}
		public void setZmlUserAddressList(List<ZmlUserAddressEntity> zmlUserAddressList) {
		this.zmlUserAddressList = zmlUserAddressList;
		}
}
