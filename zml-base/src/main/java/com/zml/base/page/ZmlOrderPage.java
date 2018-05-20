
package com.zml.base.page;
import java.util.ArrayList;
import java.util.List;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

import com.zml.base.entity.ZmlOrderCommodityEntity;

/**   
 * @Title: Entity
 * @Description: 订单
 *
 */
public class ZmlOrderPage implements java.io.Serializable {
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
	/**买家*/
    @Excel(name="买家")
	private java.lang.String buyersId;
	/**商家*/
    @Excel(name="商家")
	private java.lang.String merchantsId;
	/**订单编号*/
    @Excel(name="订单编号")
	private java.lang.String orderNum;
	/**支付编号*/
    @Excel(name="支付编号")
	private java.lang.String payId;
	/**发票类型*/
    @Excel(name="发票类型")
	private java.lang.String invoiceType;
	/**发票抬头*/
    @Excel(name="发票抬头")
	private java.lang.String invoiceTitle;
	/**发票内容*/
    @Excel(name="发票内容")
	private java.lang.String invoiceContent;
	/**订单状态*/
    @Excel(name="订单状态")
	private java.lang.String status;
	/**平台备注*/
    @Excel(name="平台备注")
	private java.lang.String platformRemarks;
	/**订单金额*/
    @Excel(name="订单金额")
	private java.math.BigDecimal allAmount;
	/**实际付款金额*/
    @Excel(name="实际付款金额")
	private java.math.BigDecimal payAmout;
	/**是否退货*/
    @Excel(name="是否退货")
	private java.lang.String isReturns;
	/**退货原因*/
    @Excel(name="退货原因")
	private java.lang.String returnReason;
	/**下单时间*/
    @Excel(name="下单时间",format = "yyyy-MM-dd")
	private java.util.Date orderTime;
	/**用户备注*/
    @Excel(name="用户备注")
	private java.lang.String userRemarks;
	/**收货地址*/
    @Excel(name="收货地址")
	private java.lang.String addressId;
	/**物流公司*/
    @Excel(name="物流公司")
	private java.lang.String logisticsCompany;
	/**物流单号*/
    @Excel(name="物流单号")
	private java.lang.String logisticsNum;
	/**物流费*/
    @Excel(name="物流费")
	private java.math.BigDecimal logisticsFee;
	/**物流联系人*/
    @Excel(name="物流联系人")
	private java.lang.String logisticsLinkPerson;
	/**物流联系人电话*/
    @Excel(name="物流联系人电话")
	private java.lang.String logisticsLinkPhnoe;
	
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
	 *@return: java.lang.String  买家
	 */
	public java.lang.String getBuyersId(){
		return this.buyersId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  买家
	 */
	public void setBuyersId(java.lang.String buyersId){
		this.buyersId = buyersId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商家
	 */
	public java.lang.String getMerchantsId(){
		return this.merchantsId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商家
	 */
	public void setMerchantsId(java.lang.String merchantsId){
		this.merchantsId = merchantsId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单编号
	 */
	public java.lang.String getOrderNum(){
		return this.orderNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单编号
	 */
	public void setOrderNum(java.lang.String orderNum){
		this.orderNum = orderNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付编号
	 */
	public java.lang.String getPayId(){
		return this.payId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付编号
	 */
	public void setPayId(java.lang.String payId){
		this.payId = payId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发票类型
	 */
	public java.lang.String getInvoiceType(){
		return this.invoiceType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发票类型
	 */
	public void setInvoiceType(java.lang.String invoiceType){
		this.invoiceType = invoiceType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发票抬头
	 */
	public java.lang.String getInvoiceTitle(){
		return this.invoiceTitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发票抬头
	 */
	public void setInvoiceTitle(java.lang.String invoiceTitle){
		this.invoiceTitle = invoiceTitle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发票内容
	 */
	public java.lang.String getInvoiceContent(){
		return this.invoiceContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发票内容
	 */
	public void setInvoiceContent(java.lang.String invoiceContent){
		this.invoiceContent = invoiceContent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单状态
	 */
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单状态
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  平台备注
	 */
	public java.lang.String getPlatformRemarks(){
		return this.platformRemarks;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  平台备注
	 */
	public void setPlatformRemarks(java.lang.String platformRemarks){
		this.platformRemarks = platformRemarks;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  订单金额
	 */
	public java.math.BigDecimal getAllAmount(){
		return this.allAmount;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  订单金额
	 */
	public void setAllAmount(java.math.BigDecimal allAmount){
		this.allAmount = allAmount;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  实际付款金额
	 */
	public java.math.BigDecimal getPayAmout(){
		return this.payAmout;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  实际付款金额
	 */
	public void setPayAmout(java.math.BigDecimal payAmout){
		this.payAmout = payAmout;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否退货
	 */
	public java.lang.String getIsReturns(){
		return this.isReturns;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否退货
	 */
	public void setIsReturns(java.lang.String isReturns){
		this.isReturns = isReturns;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  退货原因
	 */
	public java.lang.String getReturnReason(){
		return this.returnReason;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  退货原因
	 */
	public void setReturnReason(java.lang.String returnReason){
		this.returnReason = returnReason;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  下单时间
	 */
	public java.util.Date getOrderTime(){
		return this.orderTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  下单时间
	 */
	public void setOrderTime(java.util.Date orderTime){
		this.orderTime = orderTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户备注
	 */
	public java.lang.String getUserRemarks(){
		return this.userRemarks;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户备注
	 */
	public void setUserRemarks(java.lang.String userRemarks){
		this.userRemarks = userRemarks;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收货地址
	 */
	public java.lang.String getAddressId(){
		return this.addressId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收货地址
	 */
	public void setAddressId(java.lang.String addressId){
		this.addressId = addressId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物流公司
	 */
	public java.lang.String getLogisticsCompany(){
		return this.logisticsCompany;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物流公司
	 */
	public void setLogisticsCompany(java.lang.String logisticsCompany){
		this.logisticsCompany = logisticsCompany;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物流单号
	 */
	public java.lang.String getLogisticsNum(){
		return this.logisticsNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物流单号
	 */
	public void setLogisticsNum(java.lang.String logisticsNum){
		this.logisticsNum = logisticsNum;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  物流费
	 */
	public java.math.BigDecimal getLogisticsFee(){
		return this.logisticsFee;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  物流费
	 */
	public void setLogisticsFee(java.math.BigDecimal logisticsFee){
		this.logisticsFee = logisticsFee;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物流联系人
	 */
	public java.lang.String getLogisticsLinkPerson(){
		return this.logisticsLinkPerson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物流联系人
	 */
	public void setLogisticsLinkPerson(java.lang.String logisticsLinkPerson){
		this.logisticsLinkPerson = logisticsLinkPerson;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物流联系人电话
	 */
	public java.lang.String getLogisticsLinkPhnoe(){
		return this.logisticsLinkPhnoe;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物流联系人电话
	 */
	public void setLogisticsLinkPhnoe(java.lang.String logisticsLinkPhnoe){
		this.logisticsLinkPhnoe = logisticsLinkPhnoe;
	}

	/**保存-商品详情*/
    @ExcelCollection(name="商品详情")
	private List<ZmlOrderCommodityEntity> zmlOrderCommodityList = new ArrayList<ZmlOrderCommodityEntity>();
		public List<ZmlOrderCommodityEntity> getZmlOrderCommodityList() {
		return zmlOrderCommodityList;
		}
		public void setZmlOrderCommodityList(List<ZmlOrderCommodityEntity> zmlOrderCommodityList) {
		this.zmlOrderCommodityList = zmlOrderCommodityList;
		}
}
