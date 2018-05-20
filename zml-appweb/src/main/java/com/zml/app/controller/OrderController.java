package com.zml.app.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.p3.core.common.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.constant.Globals;
import com.jce.framework.core.util.DateUtils;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.web.system.service.SystemService;
import com.sun.corba.se.impl.protocol.giopmsgheaders.RequestMessage;
import com.zml.base.entity.ZmlOrderCommodityEntity;
import com.zml.base.entity.ZmlOrderEntity;
import com.zml.base.entity.ZmlUserAddressEntity;
import com.zml.base.entity.ZmlUserCartsEntity;
import com.zml.base.page.ZmlOrderPage;
import com.zml.common.ReMsg;
import com.zml.enums.OrderStatus;
import com.zml.service.ZmlOrderServiceI;
import com.zml.service.ZmlUserCartsServiceI;
import com.zml.service.ZmlUserServiceI;

/**   
 * @Title: Controller
 * @Description: 订单
 *
 */
@Controller
@RequestMapping("/orderController")
public class OrderController extends BaseController {
	
	@Autowired
	private ZmlOrderServiceI zmlOrderService;
	
	@Autowired
	private ZmlUserCartsServiceI zmlUserCartsService;
	
	@Autowired
	private ZmlUserServiceI zmlUserService;
	
	@Autowired
	private SystemService systemService;
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(OrderController.class);
	
	/**
	 * 订单列表 页面跳转 
	 * @return
	 */
	@RequestMapping("/toOrderlist")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("order/orderList");
	}
	
	
	//根据商品实体参数查询数据  分页显示
	@RequestMapping("/findByParamList")
	@ResponseBody
	public ReMsg findByParamList(ZmlOrderEntity entity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		ReMsg reMsg = null;
		try {
			List list = this.zmlOrderService.getDatagrid(entity, dataGrid);
			reMsg = new ReMsg("", true);
			//dataGrid.setResults(list);
			reMsg.add("data", list);
			reMsg.add("dataGrid", dataGrid);
		} catch (Exception e) {
			reMsg = new ReMsg("查询异常！", false);
			e.printStackTrace();
		}
		return reMsg;
	}
	
	//根据用户ID查询列表
	@RequestMapping("/findOrderByUserId")
	@ResponseBody
	public ReMsg findOrderByUserId(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		ReMsg reMsg = null;
		try {
			String userId = getUserId(request, response);
			List list = this.zmlOrderService.findOrderByUserId(userId);
			reMsg = new ReMsg("", true);
			//dataGrid.setResults(list);
			reMsg.add("data", list);
		} catch (Exception e) {
			reMsg = new ReMsg("查询异常！", false);
			e.printStackTrace();
		}
		return reMsg;
	}
	
	/**
	 * 查询结算信息
	 * @param cartsIds
	 * @return
	 */
	@RequestMapping("/findSettlementInfo")
	@ResponseBody
	public ReMsg findSettlementInfo(String userId, String ids, HttpServletRequest request) {
		ReMsg reMsg = null;
		String message = null;
		message = "创建结算信息成功";
		String[] cIds = ids.split(",");
		if(cIds != null && cIds.length > 0){
			try{
				//查询购物车中的选定的商品列表
			    List<Map<String, Object>> selectCartList = zmlUserCartsService.findCartsAndCommodityInfoByCartsIds(cIds);
			    
				if(selectCartList != null && selectCartList.size() > 0){
					//总价钱
					BigDecimal allPrice = new BigDecimal("0.00");
					//总运费
					BigDecimal allFare = new BigDecimal("0.00");
					for(int i=0; i< selectCartList.size() ; i ++){
						Map temp = selectCartList.get(i);
						Integer allCount = (Integer)temp.get("amount");
						BigDecimal realPrice = (BigDecimal)temp.get("realPrice");
						BigDecimal tempPrice = realPrice.multiply(new BigDecimal(allCount.intValue())).setScale(2, BigDecimal.ROUND_UP);
						allPrice.add(tempPrice);
						BigDecimal fare = (BigDecimal)temp.get("fare");
						allFare.add(fare);
					}
					//查询地址
					ZmlUserAddressEntity address = zmlUserService.findUserAddressByUserId(userId); 
					systemService.addLog("", "", message, Globals.APP_Log_Type_ORDER_SETT, Globals.Log_Leavel_INFO);
					reMsg = new ReMsg("", true);
					reMsg.add("selectCartList", selectCartList);
					reMsg.add("allPrice", allPrice);
					reMsg.add("allFare", allFare);
					reMsg.add("address", address);
				}else{
					reMsg = new ReMsg("数据错误！", false);
				}
				
			}catch(Exception e){
				e.printStackTrace();
				message = "创建结算信息异常！";
				reMsg = new ReMsg(message, false);
			}
		}else{
			reMsg = new ReMsg("数据错误,请重试！", false);
		}
		return reMsg;
	}

	/**
	 * 创建订单
	 * @param ids
	 * @return
	 */
	//@RequestMapping(params = "/createOrder", method=RequestMethod.POST )
	@RequestMapping("/createOrder")
	@ResponseBody
	public ReMsg createOrder(String  ids, String  userId, String userRemarks, String addressId, BigDecimal allPrice, HttpServletRequest request) {
		ReMsg reMsg = null;
		String message = null;
		message = "创建订单成功";
		String[] cartsIds = ids.split(",");
		if(cartsIds != null && cartsIds.length > 0){
			try{
				String merchantsId = "";
				//查询购物车中的选定的商品列表
			    List<Map<String, Object>> selectCartList = zmlUserCartsService.findCartsAndCommodityInfoByCartsIds(cartsIds);
				List<ZmlOrderCommodityEntity> zmlOrderCommodityList = new ArrayList();
				if(selectCartList != null && selectCartList.size() > 0){
					for(int i=0; i< selectCartList.size() ; i ++){
						Map temp = selectCartList.get(i);
						ZmlOrderCommodityEntity oce = new ZmlOrderCommodityEntity();
						//商品信息
						String commodityId = (String)temp.get("commodityId");
						String commodityName = (String)temp.get("commodityName");
						String commodityCoverImg = (String)temp.get("commodityCoverImg");
						String classifyTwoLevel = (String)temp.get("classifyTwoLevel");
						oce.setCommodityId(commodityId);
						oce.setCommodityName(commodityName);
						oce.setCommodityCoverImg(commodityCoverImg);
						oce.setCommodityType(classifyTwoLevel);
						//商户信息
						merchantsId = (String)temp.get("merchantsId");
						String merchantsName = (String)temp.get("manufacturers");
						oce.setMerchantsId(merchantsId);
						oce.setMerchantsName(merchantsName);
						oce.setMerchantsLogo("");
						//总数量
						Integer amount = (Integer)temp.get("amount");
						oce.setAllCount(amount);
						//总价钱
						BigDecimal realPrice = (BigDecimal)temp.get("realPrice");
						oce.setPrice(realPrice);
						BigDecimal allAmount = realPrice.multiply(new BigDecimal(amount.intValue()));
						oce.setAllAmount(allAmount);
						//物流费
						BigDecimal fare = (BigDecimal)temp.get("fare");
						oce.setLogisticsFee(fare);
						oce.setCreateDate(new Date());
						oce.setUpdateDate(new Date());
						zmlOrderCommodityList.add(oce);
					}
					//List ccIds = ArrayList.
					//List<ZmlOrderCommodityEntity> zmlOrderCommodityList =  zmlOrderPage.getZmlOrderCommodityList();
					ZmlOrderEntity zmlOrder = new ZmlOrderEntity();
					String orderNumber = "O" + DateUtils.date2Str(new Date(), DateUtils.yyyymmddhhmmss) + RandomUtils.generateNum(6);
					zmlOrder.setBuyersId(userId);
					zmlOrder.setMerchantsId(merchantsId);
					zmlOrder.setUserRemarks(userRemarks);
					zmlOrder.setAddressId(addressId);
					zmlOrder.setOrderNum(orderNumber);
					zmlOrder.setAllAmount(allPrice);
					zmlOrder.setPayAmout(new BigDecimal("0.00"));
					zmlOrder.setOrderTime(new Date());
					zmlOrder.setCreateDate(new Date());
					zmlOrder.setUpdateDate(new Date());
					zmlOrder.setStatus(OrderStatus.NO_PAY.getStatusValue());
					Map<String, String> map = zmlOrderService.createOrder(cartsIds, zmlOrder, zmlOrderCommodityList);
					String code = map.get("code");
					if("1".equals(code)){
						reMsg = new ReMsg("", true);
					}else{
						reMsg = new ReMsg(map.get("msg"), false);
					}
					systemService.addLog("", "", message, Globals.APP_Log_Type_INSERT_ORDER, Globals.Log_Leavel_INFO);
				}else{
					reMsg = new ReMsg("数据错误！", false);
				}
				
			}catch(Exception e){
				e.printStackTrace();
				message = "创建订单异常！";
				reMsg = new ReMsg(message, false);
			}
		}else{
			reMsg = new ReMsg("数据错误,请重试！", false);
		}
		return reMsg;
	}
	
	/**
	 * 删除订单
	 * 修改订单状态为删除
	 * @return
	 */
	@RequestMapping("/delOrder")
	@ResponseBody
	public ReMsg delOrder(@RequestParam(required = true) String orderId, HttpServletRequest request) {
		String message = null;
		ReMsg reMsg = null;
		ZmlOrderEntity zmlOrderEntity = systemService.getEntity(ZmlOrderEntity.class, orderId);
		message = "删除成功";
		if(zmlOrderEntity != null){
			try{
				zmlOrderEntity.setStatus(OrderStatus.USER_DEL.getStatusValue());
				//zmlOrderService.delete(zmlUserCarts);
				zmlOrderService.saveOrUpdate(zmlOrderEntity);
				systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
				reMsg = new ReMsg("", true);
			}catch(Exception e){
				e.printStackTrace();
				message = "删除失败";
				reMsg = new ReMsg(message, false);
			}
		}else{
			message = "删除失败,数据异常";
			reMsg = new ReMsg(message, false);
		}
		
		return reMsg;
	}
	
	/**
	 * 批量删除订单
	 * 
	 * @return
	 */
	@RequestMapping("/batchDelCarts")
	@ResponseBody
	public ReMsg doBatchDel(@RequestParam(required = true) String ids,HttpServletRequest request){
		ReMsg reMsg = null;
		String message = null;
		message = "删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlOrderEntity zmlOrderEntity = systemService.getEntity(ZmlOrderEntity.class, id);
				zmlOrderEntity.setStatus(OrderStatus.USER_DEL.getStatusValue());
				//zmlOrderService.delete(zmlUserCarts);
				zmlOrderService.saveOrUpdate(zmlOrderEntity);
				systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
			reMsg = new ReMsg("", true);
		}catch(Exception e){
			e.printStackTrace();
			message = "删除失败";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
   }
	
	/**
	 * 更新订单
	 * @param ids
	 * @return
	 */
	@RequestMapping("/doUpdate")
	@ResponseBody
	public ReMsg doUpdate(ZmlOrderEntity zmlOrder,ZmlOrderPage zmlOrderPage, HttpServletRequest request) {
		String message = null;
		ReMsg reMsg = null;
		message = "订单更新成功";
		try {
			List<ZmlOrderCommodityEntity> zmlOrderCommodityList =  zmlOrderPage.getZmlOrderCommodityList();
			zmlOrderService.updateMain(zmlOrder, zmlOrderCommodityList);
			systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			reMsg = new ReMsg("", true);
		} catch (Exception e) {
			e.printStackTrace();
			message = "订单更新失败";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
	}
	
	//跳转订单详情页面
	@RequestMapping("/toOrderDetail")
	public ModelAndView toBannerDetail(@RequestParam(required = true) String orderId , HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (StringUtil.isNotEmpty(orderId)){
			mv.addObject("orderId", orderId);
			mv.setViewName("/order/orderDetail");
		}else{
			mv.setViewName("apppage/error/dataError");
		}
		return mv;
	}
	
	//查询订单详情
	@RequestMapping("/orderDetail")
	@ResponseBody
	public ReMsg bannerDetail(@RequestParam(required = true) String orderId , HttpServletRequest request) {
		ReMsg reMsg = null;
		if (StringUtil.isNotEmpty(orderId)){
			try {
				ZmlOrderEntity zmlOrder = zmlOrderService.getEntity(ZmlOrderEntity.class, orderId);
				String hql0 = "from ZmlOrderCommodityEntity where 1 = 1 AND oRDER_ID = ? ";
			    List<ZmlOrderCommodityEntity> zmlOrderCommodityEntityList = systemService.findHql(hql0,orderId);
			  //查询地址
				ZmlUserAddressEntity address = zmlUserService.findUserAddressById(zmlOrder.getAddressId()); 
				reMsg = new ReMsg("", true);
				reMsg.add("order", zmlOrder);
				reMsg.add("orderCommodity", zmlOrderCommodityEntityList);
				reMsg.add("address", address);
			} catch (Exception e) {
				e.printStackTrace();
				reMsg = new ReMsg("异常异常！", false);
			}
		}else{
			reMsg = new ReMsg("数据异常！", false);
		}
		return reMsg;
	}
	
	/**
	 * 更新订单状态
	 * @param id
	 * @return
	 */
	@RequestMapping("/updateOrderSts")
	@ResponseBody
	public ReMsg updateOrderSts(@RequestParam(required = true) String id, @RequestParam(required = true) String sts, HttpServletRequest request) {
		String message = null;
		ReMsg reMsg = null;
		message = "更新成功";
		try {
			/*zmlOrderService.updateOrderSts(id, sts);
			systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);*/
			reMsg = new ReMsg("", true);
		} catch (Exception e) {
			e.printStackTrace();
			message = "更新失败";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
	}
}
