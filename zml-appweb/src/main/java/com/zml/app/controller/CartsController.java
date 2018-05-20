package com.zml.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jce.framework.core.common.exception.BusinessException;
import com.jce.framework.core.common.model.json.AjaxJson;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.constant.Globals;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.web.system.service.SystemService;
import com.zml.base.entity.ZmlUserCartsEntity;
import com.zml.common.ReMsg;
import com.zml.enums.CartsStatus;
import com.zml.service.ZmlUserCartsServiceI;

import net.sf.json.JSONObject;

/**   
 * @Title: Controller
 * @Description: 购物车
 */
@Controller
@RequestMapping("/cartsController")
public class CartsController extends BaseController {
	
	@Autowired
	private ZmlUserCartsServiceI zmlUserCartsService;
	
	@Autowired
	private SystemService systemService;
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CartsController.class);
	/**
	 * 转到购物车列表页
	 * @param userId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toShoppingCartPage")
	public String toShoppingCartPage(HttpServletRequest request, HttpServletResponse response){
		return "order/gootdcar";
	}
	
	//根据用户ID查询购物车数量
	@RequestMapping("/findCartsCountByUserId")
	@ResponseBody
	public ReMsg findCartsCountByUserId(@RequestParam(required = true) String userId,HttpServletRequest request, HttpServletResponse response) {
		ReMsg reMsg = null;
		try {
			Long count = this.zmlUserCartsService.findCartsCountByUserId(userId);
			reMsg = new ReMsg("" + count, true);
		} catch (Exception e) {
			reMsg = new ReMsg("查询异常！", false);
			e.printStackTrace();
		}
		return reMsg;
	}
	
	//根据商品实体参数查询数据  分页显示
	@RequestMapping("/findByParamList")
	@ResponseBody
	public ReMsg findByParamList(ZmlUserCartsEntity entity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		ReMsg reMsg = null;
		try {
			List list = this.zmlUserCartsService.getDatagrid(entity, dataGrid);
			reMsg = new ReMsg("", true);
			reMsg.add("data", list);
			reMsg.add("dataGrid", dataGrid);
		} catch (Exception e) {
			reMsg = new ReMsg("查询异常！", false);
			e.printStackTrace();
		}
		return reMsg;
	}
	
	//根据订购车ID数组列表查询 订购车列表
	@RequestMapping("/findByIds")
	@ResponseBody
	public ReMsg findByParamList(String [] ids,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		ReMsg reMsg = null;
		try {
			//List list = this.zmlUserCartsService.findCartsByIds(ids);
			List list = this.zmlUserCartsService.findCartsAndCommodityInfoByCartsIds(ids);
			reMsg = new ReMsg("", true);
			//dataGrid.setResults(list);
			reMsg.add("data", list);
		} catch (Exception e) {
			reMsg = new ReMsg("查询异常！", false);
			e.printStackTrace();
		}
		return reMsg;
	}
	
	 	
	// 以下各函数可以提成共用部件 
	public void responseDatagrid(HttpServletResponse response, JSONObject jObject) {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter pw = null;
		try {
			pw=response.getWriter();
			pw.write(jObject.toString());
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				pw.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}


	/**
	 * 添加购物车
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/addCarts")
	@ResponseBody
	public ReMsg addCarts(ZmlUserCartsEntity zmlUserCarts, HttpServletRequest request) {
		ReMsg reMsg = null;
		String message = null;
		message = "添加成功";
		try{
			//按照用户Id与商品id在购物车中查找是否已有此商品，有则进行数量加1  否则进行新加一条购物车信息
			DataGrid dataGrid = new DataGrid();
			dataGrid.setPage(1);
			dataGrid.setRows(10);
			List<Map<String, Object>> list = this.zmlUserCartsService.getDatagrid(zmlUserCarts,dataGrid);
			if(list!=null&& list.size()>0){
				ZmlUserCartsEntity t = zmlUserCartsService.get(ZmlUserCartsEntity.class, (String)list.get(0).get("id"));
				t.setAmount(t.getAmount()+1);
				zmlUserCartsService.saveOrUpdate(t);
				systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
			else{
				zmlUserCarts.setCreateDate(new Date());
				zmlUserCarts.setStatus(CartsStatus.NORMAL.getStatusValue());
				zmlUserCartsService.save(zmlUserCarts);
				systemService.addLog("", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}
			reMsg = new ReMsg("", true);
		}catch(Exception e){
			e.printStackTrace();
			message = "查询异常！";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
	}
	
	

	/**
	 * 删除购物车
	 * 
	 * @return
	 */
	@RequestMapping("/delCarts")
	@ResponseBody
	public ReMsg doDel(@RequestParam(required = true) String id, HttpServletRequest request) {
		String message = null;
		ReMsg reMsg = null;
		ZmlUserCartsEntity zmlUserCarts = systemService.getEntity(ZmlUserCartsEntity.class, id);
		message = "购物车删除成功";
		try{
			zmlUserCarts.setUpdateDate(new Date());
			zmlUserCarts.setStatus(CartsStatus.DEL.getStatusValue());
			zmlUserCartsService.save(zmlUserCarts);
			//zmlUserCartsService.delete(zmlUserCarts);
			systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			reMsg = new ReMsg("", true);
		}catch(Exception e){
			e.printStackTrace();
			message = "删除失败";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
	}
	
	/**
	 * 批量删除购物车
	 * 
	 * @return
	 */
	@RequestMapping("/batchDelCarts")
	@ResponseBody
	public ReMsg doBatchDel(@RequestParam(required = true) String ids,HttpServletRequest request){
		ReMsg reMsg = null;
		String message = null;
		message = "购物车批量删除成功";
		try{
			for(String id:ids.split(",")){
				ZmlUserCartsEntity zmlUserCarts = systemService.getEntity(ZmlUserCartsEntity.class, id);
				zmlUserCarts.setUpdateDate(new Date());
				zmlUserCarts.setStatus(CartsStatus.DEL.getStatusValue());
				zmlUserCartsService.save(zmlUserCarts);
				//zmlUserCartsService.delete(zmlUserCarts);
				systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
			reMsg = new ReMsg("", true);
		}catch(Exception e){
			e.printStackTrace();
			message = "购物车删除失败";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
   }
	
	/**
	 * 更新购物车数量
	 * @param ids
	 * @return
	 */
	@RequestMapping("/doUpdate")
	@ResponseBody
	public ReMsg doUpdate(@RequestParam(required = true) String id, @RequestParam(required = true) Integer amount, HttpServletRequest request) {
		String message = null;
		ReMsg reMsg = null;
		if(amount.intValue() > 0){
			message = "购物车更新成功";
			ZmlUserCartsEntity t = zmlUserCartsService.get(ZmlUserCartsEntity.class, id);
			try {
				t.setAmount(amount);
				zmlUserCartsService.saveOrUpdate(t);
				systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
				reMsg = new ReMsg("", true);
			} catch (Exception e) {
				e.printStackTrace();
				message = "购物车更新失败";
				reMsg = new ReMsg(message, false);
			}
		}else{
			message = "更新失败数据错误！";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
	}
	 
	/**
	 *  转到去支付确认商品页面
	 * @param userId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toPayShoppingConfirm")
	public String toPayShoppingConfirm(String ids,HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("ids", ids);
		return "order/payinfo";
	}
	
}
