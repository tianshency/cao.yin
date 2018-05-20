package com.zml.app.controller;

import java.text.SimpleDateFormat;
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
import org.springframework.web.servlet.ModelAndView;

import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.web.system.service.SystemService;
import com.zml.base.entity.ZmlUserBookingEntity;
import com.zml.base.entity.ZmlUserEntity;
import com.zml.base.entity.ZmlUserReleaseFoodEntity;
import com.zml.base.entity.ZmlUserReleaseInfoEntity;
import com.zml.base.entity.ZmlUserReleaseTerritoryEntity;
import com.zml.common.ReMsg;
import com.zml.enums.CommonStatus;
import com.zml.enums.ReleaseType;
import com.zml.service.ZmlUserBookingServiceI;
import com.zml.service.ZmlUserReleaseFoodServiceI;
import com.zml.service.ZmlUserReleaseInfoServiceI;
import com.zml.service.ZmlUserReleaseTerritoryServiceI;

//在线预定 控制类
@Controller
@RequestMapping("/bookingController")
public class UserBookingController extends BaseController {
	
	@Autowired
	private ZmlUserBookingServiceI  zmlUserBookingService;
	
	@Autowired
	private ZmlUserReleaseTerritoryServiceI  zmlUserReleaseTerritoryService;
	
	@Autowired
	private ZmlUserReleaseFoodServiceI  zmlUserReleaseFoodService;
	
	@Autowired
	private ZmlUserReleaseInfoServiceI zmlUserReleaseInfoServiceI;
	
	@Autowired
	private SystemService systemService;
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserBookingController.class);
	
	/**
	 * 转到我的预定列表页
	 */
	@RequestMapping("/toMyBookingList")
	public String toMyBooking(HttpServletRequest request, HttpServletResponse response){
		return "release/myBookingList";
	}
	
	/**
	 * 转到添加预定页
	 */
	@RequestMapping("/toAddBookingPage")
	public ModelAndView toAddBookingPage(@RequestParam(required = true) String releaseId,HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.addObject("releaseId", releaseId);
		mv.setViewName("release/booking");
		return mv;
	}
	
	/**
	 * 跳转预定详情页
	 */
	@RequestMapping("/toBookingDetail")
	public ModelAndView toBookingDetail(@RequestParam(required = true) String id, HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", id);
		mv.setViewName("booking/bookingDetail");
		return mv;
	}
	
	/**
	 * 查看详情信息
	 * @param id
	 */
	@RequestMapping("/bookingInfoDetail")
	@ResponseBody
	public ReMsg bookingInfoDetail(@RequestParam(required = true) String id, 
			 HttpServletRequest request) {
		String message = null;
		ReMsg reMsg = null;
		message = "";
		try{
			ZmlUserBookingEntity entity = systemService.getEntity(ZmlUserBookingEntity.class, id);
			String bookingType = entity.getBookingType();
			reMsg = new ReMsg(bookingType, true);
			String hql = "";
			String bookingId = entity.getBookingId();
			if(ReleaseType.TERRITORY.getStatusValue().equals(bookingType)){
				hql = "from ZmlUserReleaseTerritoryEntity where id ='"+ bookingId +"' ";
				ZmlUserReleaseTerritoryEntity releaseInfo = zmlUserReleaseTerritoryService.singleResult(hql);
				reMsg.add("territoryInfo", releaseInfo);
			}else if(ReleaseType.FOOD.getStatusValue().equals(bookingType)){
				hql = "from ZmlUserReleaseFoodEntity where id ='"+ bookingId +"' ";
				ZmlUserReleaseFoodEntity releaseInfo = zmlUserReleaseFoodService.singleResult(hql);
				reMsg.add("foodInfo", releaseInfo);
			}else if(ReleaseType.MACHINE.getStatusValue().equals(bookingType)){
				hql = "from ZmlUserReleaseInfoEntity where id ='"+ bookingId +"' and releaseType='"+bookingType+"' ";
				ZmlUserReleaseInfoEntity tmp = zmlUserReleaseInfoServiceI.singleResult(hql);
			}else if("4".equals(bookingType)){
				hql = "from ZmlUserReleaseInfoEntity where id ='"+ bookingId +"' and releaseType='"+bookingType+"' ";
				ZmlUserReleaseInfoEntity tmp = zmlUserReleaseInfoServiceI.singleResult(hql);
				reMsg.add("foodInfo", tmp);
			}
			else if("5".equals(bookingType)){
				hql = "from ZmlUserReleaseInfoEntity where id ='"+ bookingId +"' and releaseType='"+bookingType+"' ";
				ZmlUserReleaseInfoEntity tmp = zmlUserReleaseInfoServiceI.singleResult(hql);
				reMsg.add("foodInfo", tmp);
			}
			reMsg.add("bookingInfo", entity);
		}catch(Exception e){
			e.printStackTrace();
			message = "查询失败";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
	}
	
	//根据用户ID查询订购数量
	@RequestMapping("/findBookingCountByUserId")
	@ResponseBody
	public ReMsg findBookingCountByUserId(HttpServletRequest request, HttpServletResponse response) {
		ReMsg reMsg = null;
		try {
			String userId = getUserId(request, response);
			Long count = zmlUserBookingService.findBookingCountByUserId(userId);
			reMsg = new ReMsg("" + count, true);
		} catch (Exception e) {
			reMsg = new ReMsg("查询异常！", false);
			e.printStackTrace();
		}
		return reMsg;
	}
	
	//根据用户ID查询订购列表
	@RequestMapping("/findBookingByUserId")
	@ResponseBody
	public ReMsg findBookingByUserId(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		ReMsg reMsg = null;
		try {
			String userId = getUserId(request, response);
			List list = this.zmlUserBookingService.findBookingByUserId(userId);
			reMsg = new ReMsg("", true);
			reMsg.add("data", list);
			reMsg.add("dataGrid", dataGrid);
		} catch (Exception e) {
			reMsg = new ReMsg("查询异常！", false);
			e.printStackTrace();
		}
		return reMsg;
	}
	
	/**
	 * 发布预定
	 * @return
	 */
	@RequestMapping("/addBooking")
	@ResponseBody
	public ReMsg addBooking(ZmlUserBookingEntity entity, HttpServletRequest request, HttpServletResponse response) {
		ReMsg reMsg = null;
		String message = null;
		message = "添加成功";
		try{
			//String userId = getUserId(request, response);
			ZmlUserEntity  user = getUserInfo(request, response);
			entity.setUserId(user.getId());
			entity.setUserName(user.getUserName());
			entity.setCreateDate(new Date());
			entity.setUpdateDate(new Date());
			entity.setStatus(CommonStatus.NORMAL.getStatusValue()); //0：删除 1：正常 2：失效
			
			//查询对方 用户名
			String bookingType = entity.getBookingType();
			String hql = "";
			String bookingId = entity.getBookingId();
			
			//获得最大的序号
			Integer max_orderNo = 0;
			SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
			String sql = "select max(order_no) orderNo from zml_user_booking where id ='"+ bookingId +"' and PLAN_DEAL_DATE='"+sdf.format(entity.getPlanDealDate())+"' ";
			Map<String,Object> map = zmlUserBookingService.findOneForJdbc(sql, new Object[]{});
			if(map!=null && map.size()>0){
				System.out.println(map.toString());
				max_orderNo = (Integer)map.get("orderNo");
				if(max_orderNo==null){
					max_orderNo=0;
				}
			}
			entity.setOrderNo(max_orderNo);
			
			if(ReleaseType.TERRITORY.getStatusValue().equals(bookingType)){
				hql = "from ZmlUserReleaseTerritoryEntity where id ='"+ bookingId +"' ";
				ZmlUserReleaseTerritoryEntity releaseInfo = zmlUserReleaseTerritoryService.singleResult(hql);
				entity.setOtherSideId(releaseInfo.getUserId());
				entity.setOtherSideName(releaseInfo.getCreateName());
				entity.setBookingTitle(releaseInfo.getTitle());
			}else if(ReleaseType.FOOD.getStatusValue().equals(bookingType)){
				hql = "from ZmlUserReleaseFoodEntity where id ='"+ bookingId +"' ";
				ZmlUserReleaseFoodEntity releaseInfo = zmlUserReleaseFoodService.singleResult(hql);
				entity.setOtherSideId(releaseInfo.getUserId());
				entity.setOtherSideName(releaseInfo.getCreateName());
				entity.setBookingTitle(releaseInfo.getTitle());
			}
			else if(ReleaseType.MACHINE.getStatusValue().equals(bookingType)){
				hql = "from ZmlUserReleaseInfoEntity where id ='"+ bookingId +"' and releaseType='"+bookingType+"' ";
				ZmlUserReleaseInfoEntity releaseInfo = zmlUserReleaseInfoServiceI.singleResult(hql);
				entity.setOtherSideId(releaseInfo.getUserId());
				entity.setOtherSideName(releaseInfo.getCreateName());
				entity.setBookingTitle(releaseInfo.getTitle());
				
			}else if("4".equals(bookingType)){
				hql = "from ZmlUserReleaseInfoEntity where id ='"+ bookingId +"' and releaseType='"+bookingType+"' ";
				ZmlUserReleaseInfoEntity releaseInfo = zmlUserReleaseInfoServiceI.singleResult(hql);
				entity.setOtherSideId(releaseInfo.getUserId());
				entity.setOtherSideName(releaseInfo.getCreateName());
				entity.setBookingTitle(releaseInfo.getTitle());
			}
			else if("5".equals(bookingType)){
				hql = "from ZmlUserReleaseInfoEntity where id ='"+ bookingId +"' and releaseType='"+bookingType+"' ";
				ZmlUserReleaseInfoEntity releaseInfo = zmlUserReleaseInfoServiceI.singleResult(hql);
				entity.setOtherSideId(releaseInfo.getUserId());
				entity.setOtherSideName(releaseInfo.getCreateName());
				entity.setBookingTitle(releaseInfo.getTitle());
			}
			
			zmlUserBookingService.save(entity);
			reMsg = new ReMsg("", true);
		}catch(Exception e){
			e.printStackTrace();
			message = "查询异常！";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
	}
	
	/**
	 * 删除信息
	 * @return
	 */
	@RequestMapping("/delBooking")
	@ResponseBody
	public ReMsg delBooking(@RequestParam(required = true) String id, HttpServletRequest request) {
		String message = null;
		ReMsg reMsg = null;
		message = "购物车删除成功";
		try{
			ZmlUserBookingEntity entity = systemService.getEntity(ZmlUserBookingEntity.class, id);
			entity.setStatus(CommonStatus.DEL.getStatusValue());
			zmlUserBookingService.saveOrUpdate(entity);
			reMsg = new ReMsg("", true);
		}catch(Exception e){
			e.printStackTrace();
			message = "删除失败";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
	}
	
	/**
	 * 更新
	 * @param ids
	 * @return
	 */
	@RequestMapping("/doUpdate")
	@ResponseBody
	public ReMsg doUpdate(ZmlUserBookingEntity entity, HttpServletRequest request) {
		String message = null;
		ReMsg reMsg = null;
		message = "更新成功";
		ZmlUserBookingEntity t = zmlUserBookingService.get(ZmlUserBookingEntity.class, entity.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(entity, t);
			zmlUserBookingService.saveOrUpdate(t);
			reMsg = new ReMsg("", true);
		} catch (Exception e) {
			e.printStackTrace();
			message = "更新失败";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
	}
}
