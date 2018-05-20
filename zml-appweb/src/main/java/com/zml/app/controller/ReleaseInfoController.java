 package com.zml.app.controller;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.constant.Globals;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.web.system.service.SystemService;
import com.zml.base.entity.ZmlUserEntity;
import com.zml.base.entity.ZmlUserReleaseFoodEntity;
import com.zml.base.entity.ZmlUserReleaseInfoEntity;
import com.zml.base.entity.ZmlUserReleaseTerritoryEntity;
import com.zml.base.entity.ZmlUserViewHistoryEntity;
import com.zml.common.Constant;
import com.zml.common.ReMsg;
import com.zml.enums.CommonStatus;
import com.zml.enums.ReleaseType;
import com.zml.enums.ViewType;
import com.zml.enums.YesOrNo;
import com.zml.service.ZmlUserReleaseFoodServiceI;
import com.zml.service.ZmlUserReleaseInfoServiceI;
import com.zml.service.ZmlUserReleaseTerritoryServiceI;
import com.zml.service.ZmlUserViewHistoryServiceI;
import com.zml.util.DateUtil;
//发布信息 控制类
@Controller
@RequestMapping("/releaseInfoController")
public class ReleaseInfoController extends BaseController {
	
	@Autowired
	private ZmlUserReleaseTerritoryServiceI  zmlUserReleaseTerritoryService;
	
	@Autowired
	private ZmlUserReleaseFoodServiceI  zmlUserReleaseFoodService;
	
	@Autowired
	private ZmlUserViewHistoryServiceI  zmlUserViewHistoryService;
	
	@Autowired
	private ZmlUserReleaseInfoServiceI  zmlUserReleaseInfoService;
	
	@Autowired
	private SystemService systemService;
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ReleaseInfoController.class);
	
	/**
	 * 转到资讯信息页
	 */
	@RequestMapping("/toReleaseZiXun")
	public String toReleaseZiXun(HttpServletRequest request, HttpServletResponse response){
		return "release/releaseshowlist";
	}
	
	/**
	 * 转到发布类型页
	 */
	@RequestMapping("/toReleaseTypePage")
	public String toReleaseTypePage(HttpServletRequest request, HttpServletResponse response){
		return "release/releasetype";
	}
	
	/**
	 * 转到我的发布信息页
	 */
	@RequestMapping("/tomyReleaseListPage")
	public String tomyReleaseTypePage(HttpServletRequest request, HttpServletResponse response){
		return "release/myreleaselist";
	}
	/**
	 * 转到发布综合信息列表页
	 */
	@RequestMapping("/toReleaseInfoListPage")
	public String toReleaseInfoListPage(HttpServletRequest request, HttpServletResponse response){
		return "release/releaseInfoList";
	}
	/**
	 * 转到发布土地列表页
	 */
	@RequestMapping("/toTerritoryListPage")
	public String toTerritoryListPage(HttpServletRequest request, HttpServletResponse response){
		return "release/territoryList";
	}
	/**
	 * 转到发布粮食列表页
	 */
	@RequestMapping("/toFoodListPage")
	public String toFoodListPage(HttpServletRequest request, HttpServletResponse response){
		return "release/foodList";
	}
	/**
	 * 转到发布综合详情
	 */
	@RequestMapping("/toReleaseInfoPage")
	public ModelAndView toReleaseInfoPage(String parentType,String childType,HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.addObject("parentType", parentType);
		mv.addObject("childType", childType);
		mv.setViewName("release/releaseinfoTmp");
		return mv;
	}
	/**
	 * 转到发布土地列详情
	 */
	@RequestMapping("/toTerritoryPage")
	public ModelAndView toTerritoryPage(String parentType,String childType,HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.addObject("parentType", parentType);
		mv.addObject("childType", childType);
		mv.setViewName("release/release_territory_info");
		return mv;
	}
	/**
	 * 转到发布粮食列详情
	 */
	@RequestMapping("/toFoodPage")
	public ModelAndView toFoodPage(String parentType,String childType,HttpServletRequest request, HttpServletResponse response){ 
		ModelAndView mv = new ModelAndView();
		mv.addObject("parentType", parentType);
		mv.addObject("childType", childType);
		mv.setViewName("release/release_food_info");
		return mv;
	}
	
	//转到土地、粮食、其它类型详情页面 flag 1:土地，2：粮食， 5：综合信息
	@RequestMapping("/toReleaseDetailPage")
	public ModelAndView toReleaseDetailPage(@RequestParam(required = true) String id, 
			@RequestParam(required = true) String flag,HttpServletRequest request, HttpServletResponse response){ 
		ModelAndView mv = new ModelAndView();
		mv.addObject("flag", flag);
		mv.addObject("id", id);
		mv.setViewName("release/releaseshowdetail");
		//增加浏览记录
		ZmlUserViewHistoryEntity viewHistoryEntity = new ZmlUserViewHistoryEntity();
		viewHistoryEntity.setUserId(getUserId(request, response));
		viewHistoryEntity.setCreateDate(new Date());
		viewHistoryEntity.setUpdateDate(new Date());
		viewHistoryEntity.setViewType(flag);
		viewHistoryEntity.setDataId(id);
		viewHistoryEntity.setStatus(1);
		try {
			zmlUserViewHistoryService.save(viewHistoryEntity);
			//增加 浏览数量
			if(ViewType.RELEASE_TERRITORY.getStatusValue().equals(flag)){
				ZmlUserReleaseTerritoryEntity entity = systemService.getEntity(ZmlUserReleaseTerritoryEntity.class, id);
				int count = 1;
				if(entity.getViewCount() != null){
					count +=1;
				}
				entity.setViewCount(count);
				systemService.saveOrUpdate(entity);
			}else if(ViewType.RELEASE_FOOD.getStatusValue().equals(flag)){
				ZmlUserReleaseFoodEntity entity = systemService.getEntity(ZmlUserReleaseFoodEntity.class, id);
				int count = 1;
				if(entity.getViewCount() != null){
					count +=1;
				}
				entity.setViewCount(count);
				systemService.saveOrUpdate(entity);
			}else if(ViewType.RELEASE_MACHINE.getStatusValue().equals(flag)){
				ZmlUserReleaseInfoEntity entity = systemService.getEntity(ZmlUserReleaseInfoEntity.class, id);
				int count = 1;
				if(entity.getViewCount() != null){
					count +=1;
				}
				entity.setViewCount(count);
				systemService.saveOrUpdate(entity);
				
			}else if(ViewType.RELEASE_OTHER.getStatusValue().equals(flag)){
				ZmlUserReleaseInfoEntity entity = systemService.getEntity(ZmlUserReleaseInfoEntity.class, id);
				int count = 1;
				if(entity.getViewCount() != null){
					count +=1;
				}
				entity.setViewCount(count);
				systemService.saveOrUpdate(entity);
			}else{
				ZmlUserReleaseInfoEntity entity = systemService.getEntity(ZmlUserReleaseInfoEntity.class, id);
				int count = 1;
				if(entity.getViewCount() != null){
					count +=1;
				}
				entity.setViewCount(count);
				systemService.saveOrUpdate(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}
	
	/**
	 * 查看发布信息
	 * @param id, flag 1:土地，2：粮食， 5：综合 
	 */
	@RequestMapping("/releaseInfoDetail")
	@ResponseBody
	public ReMsg releaseInfoDetail(@RequestParam(required = true) String id, 
			@RequestParam(required = true) String flag, HttpServletRequest request) {
		String message = null;
		ReMsg reMsg = null;
		message = "";
		try{
			//获取土地
			reMsg = new ReMsg(flag, true);
			if(ViewType.RELEASE_TERRITORY.getStatusValue().equals(flag)){
				ZmlUserReleaseTerritoryEntity entity = systemService.getEntity(ZmlUserReleaseTerritoryEntity.class, id);
				reMsg.add("releaseInfo", entity);
			//获取粮食
			}else if(ViewType.RELEASE_FOOD.getStatusValue().equals(flag)){
				ZmlUserReleaseFoodEntity entity = systemService.getEntity(ZmlUserReleaseFoodEntity.class, id);
				reMsg.add("releaseInfo", entity);
			}else if(ViewType.RELEASE_MACHINE.getStatusValue().equals(flag)){
				ZmlUserReleaseInfoEntity entity = systemService.getEntity(ZmlUserReleaseInfoEntity.class, id);
				reMsg.add("releaseInfo", entity);
			}else if(ViewType.RELEASE_OTHER.getStatusValue().equals(flag)){
				ZmlUserReleaseInfoEntity entity = systemService.getEntity(ZmlUserReleaseInfoEntity.class, id);
				reMsg.add("releaseInfo", entity);
			}else{
				ZmlUserReleaseInfoEntity entity = systemService.getEntity(ZmlUserReleaseInfoEntity.class, id);
				reMsg.add("releaseInfo", entity);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "查询失败";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
	}
	
	//根据用户ID查询数量
	@RequestMapping("/findReleaseCountByUserId")
	@ResponseBody
	public ReMsg findReleaseCountByUserId(HttpServletRequest request, HttpServletResponse response) {
		ReMsg reMsg = null;
		try {
			String userId = getUserId(request, response);
			Long count = 0L;//this.zmlUserCartsService.findCartsCountByUserId(userId);
			Map map = this.zmlUserReleaseInfoService.findCountByUserId(userId);
			reMsg = new ReMsg("" , true);
			reMsg.add("count", map);
		} catch (Exception e) {
			reMsg = new ReMsg("查询异常！", false);
			e.printStackTrace();
		}
		return reMsg;
	}
	
	//根据发布综合信息实体参数查询数据  分页显示
	@RequestMapping("/findInfoByParamList")
	@ResponseBody
	public ReMsg findInfoByParamList(ZmlUserReleaseInfoEntity entity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		ReMsg reMsg = null;
		try {
			List list = this.zmlUserReleaseInfoService.findDatagrid(entity, dataGrid);
			reMsg = new ReMsg("", true);
			reMsg.add("data", list);
			reMsg.add("dataGrid", dataGrid);
		} catch (Exception e) {
			reMsg = new ReMsg("查询异常！", false);
			e.printStackTrace();
		}
		return reMsg;
	}
	
	//根据发布土地信息实体参数查询数据  分页显示
	@RequestMapping("/findTerritoryByParamList")
	@ResponseBody
	public ReMsg findTerritoryByParamList(ZmlUserReleaseTerritoryEntity entity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		ReMsg reMsg = null;
		try {
			List list = this.zmlUserReleaseTerritoryService.findDatagrid(entity, dataGrid);
			reMsg = new ReMsg("", true);
			reMsg.add("data", list);
			reMsg.add("dataGrid", dataGrid);
		} catch (Exception e) {
			reMsg = new ReMsg("查询异常！", false);
			e.printStackTrace();
		}
		return reMsg;
	}
	
	//根据发布粮食信息实体参数查询数据  分页显示
	@RequestMapping("/findFoodByParamList")
	@ResponseBody
	public ReMsg findFoodByParamList(ZmlUserReleaseFoodEntity entity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		ReMsg reMsg = null;
		try {
			List list = this.zmlUserReleaseFoodService.findDatagrid(entity, dataGrid);
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
	 * 发布综合信息
	 * @param ids
	 * @return
	 */
	@RequestMapping("/releaseInfo")
	@ResponseBody
	public ReMsg releaseInfo(ZmlUserReleaseInfoEntity releaseInfo,  HttpServletRequest request, HttpServletResponse response) {
		ReMsg reMsg = null;
		String message = null;
		message = "添加成功";
		try{
			//String userId = getUserId(request, response);
			ZmlUserEntity  user = getUserInfo(request, response);
			releaseInfo.setCreateDate(new Date());
			releaseInfo.setUpdateDate(new Date());
			releaseInfo.setUserId(user.getId());
			releaseInfo.setUserName(user.getUserName());
			releaseInfo.setStatus(CommonStatus.NORMAL.getStatusValue());
			releaseInfo.setValidStartDate(new Date());
			releaseInfo.setValidEndDate(DateUtil.addMonths(new Date(), Constant.RELEASE_INFO_VALIDENDDATE));
			releaseInfo.setIsSticky(YesOrNo.NO.getStatusValue());//非置顶
			releaseInfo.setIsVerify(YesOrNo.NO.getStatusValue());//未审核
			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest)request;
			zmlUserReleaseInfoService.save(releaseInfo, mrequest);
			systemService.addLog("", "", message, Globals.APP_Log_Type_INSERT, Globals.Log_Leavel_INFO);
			reMsg = new ReMsg("", true);
		}catch(Exception e){
			e.printStackTrace();
			message = "异常！";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
	}
	
	/**
	 * 发布土地信息
	 * @param ids
	 * @return
	 */
	@RequestMapping("/releaseTerritoryInfo")
	@ResponseBody
	public ReMsg releaseTerritoryInfo(ZmlUserReleaseTerritoryEntity releaseInfo,  HttpServletRequest request, HttpServletResponse response) {
		ReMsg reMsg = null;
		String message = null;
		message = "添加成功";
		try{
			//String userId = getUserId(request, response);
			ZmlUserEntity  user = getUserInfo(request, response);
			releaseInfo.setCreateDate(new Date());
			releaseInfo.setUpdateDate(new Date());
			releaseInfo.setUserId(user.getId());
			releaseInfo.setCreateBy(user.getUserName());
			releaseInfo.setStatus(CommonStatus.NORMAL.getStatusValue());
			releaseInfo.setValidStartDate(new Date());
			releaseInfo.setValidEndDate(DateUtil.addMonths(new Date(), Constant.RELEASE_INFO_VALIDENDDATE));
			releaseInfo.setIsSticky(YesOrNo.NO.getStatusValue());//非置顶
			releaseInfo.setIsVerify(YesOrNo.NO.getStatusValue());//未审核
			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest)request;
			zmlUserReleaseTerritoryService.save(releaseInfo, mrequest);
			systemService.addLog("", "", message, Globals.APP_Log_Type_INSERT, Globals.Log_Leavel_INFO);
			reMsg = new ReMsg("", true);
		}catch(Exception e){
			e.printStackTrace();
			message = "异常！";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
	}
	
	/**
	 * 发布粮食信息 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/releaseFoodInfo")
	@ResponseBody
	public ReMsg releaseFoodInfo(ZmlUserReleaseFoodEntity releaseInfo, HttpServletRequest request, HttpServletResponse response) {
		ReMsg reMsg = null;
		String message = null;
		message = "添加成功";
		try{
			//String userId = getUserId(request, response);
			ZmlUserEntity  user = getUserInfo(request, response);
			releaseInfo.setCreateDate(new Date());
			releaseInfo.setUpdateDate(new Date());
			releaseInfo.setUserId(user.getId());
			releaseInfo.setCreateBy(user.getUserName());
			releaseInfo.setValidStartDate(new Date());
			releaseInfo.setValidEndDate(DateUtil.addMonths(new Date(), Constant.RELEASE_INFO_VALIDENDDATE));
			releaseInfo.setStatus(CommonStatus.NORMAL.getStatusValue());
			releaseInfo.setIsSticky(YesOrNo.NO.getStatusValue());//非置顶
			releaseInfo.setIsVerify(YesOrNo.NO.getStatusValue());//未审核
			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest)request;
			zmlUserReleaseFoodService.save(releaseInfo, mrequest);
			systemService.addLog("", "", message, Globals.APP_Log_Type_INSERT, Globals.Log_Leavel_INFO);
			reMsg = new ReMsg("", true);
		}catch(Exception e){
			e.printStackTrace();
			message = "异常！";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
	}
	

	/**
	 * 删除发布信息
	 * 
	 * @return
	 */
	@RequestMapping("/delReleaseInfo")
	@ResponseBody
	public ReMsg doDel(@RequestParam(required = true) String id,@RequestParam(required = true) String flag, HttpServletRequest request) {
		String message = null;
		ReMsg reMsg = null;
		message = "购物车删除成功";
		try{
			//删除土地
			if(ViewType.RELEASE_TERRITORY.getStatusValue().equals(flag)){
				ZmlUserReleaseTerritoryEntity entity = systemService.getEntity(ZmlUserReleaseTerritoryEntity.class, id);
				entity.setStatus(CommonStatus.DEL.getStatusValue());
				zmlUserReleaseTerritoryService.saveOrUpdate(entity);
			//获取粮食
			}else if(ViewType.RELEASE_FOOD.getStatusValue().equals(flag)){
				ZmlUserReleaseFoodEntity entity = systemService.getEntity(ZmlUserReleaseFoodEntity.class, id);
				entity.setStatus(CommonStatus.DEL.getStatusValue());
				zmlUserReleaseFoodService.saveOrUpdate(entity);
			}else if(ViewType.RELEASE_MACHINE.getStatusValue().equals(flag)){
				ZmlUserReleaseInfoEntity entity = systemService.getEntity(ZmlUserReleaseInfoEntity.class, id);
				entity.setStatus(CommonStatus.DEL.getStatusValue());
				zmlUserReleaseInfoService.saveOrUpdate(entity);
			}else if(ViewType.RELEASE_OTHER.getStatusValue().equals(flag)){
				ZmlUserReleaseInfoEntity entity = systemService.getEntity(ZmlUserReleaseInfoEntity.class, id);
				entity.setStatus(CommonStatus.DEL.getStatusValue());
				zmlUserReleaseInfoService.saveOrUpdate(entity);
			}
			
			systemService.addLog("", "", message, Globals.APP_Log_Type_DEL, Globals.Log_Leavel_INFO);
			reMsg = new ReMsg("", true);
		}catch(Exception e){
			e.printStackTrace();
			message = "删除失败";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
	}
	
	/**
	 * 更新土地
	 * @param ids
	 * @return
	 */
	@RequestMapping("/doUpdateTerritory")
	@ResponseBody
	public ReMsg doUpdateTerritory(ZmlUserReleaseTerritoryEntity entity, HttpServletRequest request) {
		String message = null;
		ReMsg reMsg = null;
		message = "更新成功";
		ZmlUserReleaseTerritoryEntity t = zmlUserReleaseTerritoryService.get(ZmlUserReleaseTerritoryEntity.class, entity.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(entity, t);
			zmlUserReleaseTerritoryService.saveOrUpdate(t);
			systemService.addLog("", "", message, Globals.APP_Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			reMsg = new ReMsg("", true);
		} catch (Exception e) {
			e.printStackTrace();
			message = "更新失败";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
	}
	
	/**
	 * 更新粮食
	 * @param ids
	 * @return
	 */
	@RequestMapping("/doUpdateFood")
	@ResponseBody
	public ReMsg doUpdateFood(ZmlUserReleaseFoodEntity entity, HttpServletRequest request) {
		String message = null;
		ReMsg reMsg = null;
		message = "更新成功";
		ZmlUserReleaseFoodEntity t = zmlUserReleaseFoodService.get(ZmlUserReleaseFoodEntity.class, entity.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(entity, t);
			zmlUserReleaseFoodService.saveOrUpdate(t);
			systemService.addLog("", "", message, Globals.APP_Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			reMsg = new ReMsg("", true);
		} catch (Exception e) {
			e.printStackTrace();
			message = "更新失败";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
	}
	
	/**
	 * 更新综合信息
	 * @param
	 * @return
	 */
	@RequestMapping("/doUpdateInfo")
	@ResponseBody
	public ReMsg doUpdateFood(ZmlUserReleaseInfoEntity entity, HttpServletRequest request) {
		String message = null;
		ReMsg reMsg = null;
		message = "更新成功";
		ZmlUserReleaseInfoEntity t = zmlUserReleaseInfoService.get(ZmlUserReleaseInfoEntity.class, entity.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(entity, t);
			zmlUserReleaseInfoService.saveOrUpdate(t);
			systemService.addLog("", "", message, Globals.APP_Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			reMsg = new ReMsg("", true);
		} catch (Exception e) {
			e.printStackTrace();
			message = "更新失败";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
	}

}
