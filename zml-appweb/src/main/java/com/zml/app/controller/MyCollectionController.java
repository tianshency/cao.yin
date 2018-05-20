package com.zml.app.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.constant.Globals;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.web.system.service.SystemService;
import com.zml.base.entity.ZmlCommodityEntity;
import com.zml.base.entity.ZmlUserCollectionCommodityEntity;
import com.zml.base.entity.ZmlUserCollectionEntity;
import com.zml.base.entity.ZmlUserCollectionMerchantsEntity;
import com.zml.base.entity.ZmlUserReleaseFoodEntity;
import com.zml.base.entity.ZmlUserReleaseTerritoryEntity;
import com.zml.common.ReMsg;
import com.zml.enums.CollectionType;
import com.zml.enums.CommonStatus;
import com.zml.service.ZmlCommodityServiceI;
import com.zml.service.ZmlUserCollectionCommodityServiceI;
import com.zml.service.ZmlUserCollectionMerchantsServiceI;
import com.zml.service.ZmlUserCollectionServiceI;
import com.zml.service.ZmlUserReleaseFoodServiceI;
import com.zml.service.ZmlUserReleaseTerritoryServiceI;

//我的收藏 控制类
@Controller
@RequestMapping("/myCollectionController")
public class MyCollectionController extends BaseController {
	@Autowired
	private ZmlUserCollectionMerchantsServiceI  zmlUserCollectionMerchantsService;
	
	@Autowired
	private ZmlUserCollectionCommodityServiceI zmlUserCollectionCommodityService;
	
	@Autowired
	private ZmlUserCollectionServiceI zmlUserCollectionService;
	
	@Autowired
	private ZmlCommodityServiceI zmlCommodityService;
	
	@Autowired
	private ZmlUserReleaseFoodServiceI zmlUserReleaseFoodService;
	
	@Autowired
	private ZmlUserReleaseTerritoryServiceI zmlUserReleaseTerritoryService;
	
	@Autowired
	private SystemService systemService;
	
	private static final Logger logger = Logger.getLogger(MyCollectionController.class);

	
	/**
	 * 转到我的收藏信息页
	 */
	@RequestMapping("/toCollectionPage")
	public String tomyReleaseTypePage(HttpServletRequest request, HttpServletResponse response){
		return "collection/collection";
	}
	
	/**
	 * 转到收藏商品列表页
	 */
	@RequestMapping("/toCollectionCommodityPage")
	public String toCollectionCommodityPage(HttpServletRequest request, HttpServletResponse response){
		return "collection/collectionCommodityList";
	}
	/**
	 * 转到收藏商家列表页
	 */
	@RequestMapping("/toCollectionMerchantsPage")
	public String toCollectionMerchantsPage(HttpServletRequest request, HttpServletResponse response){
		return "collection/collectionMerchantsList";
	}
	
	//根据用户ID查询收藏数量
	@RequestMapping("/findCollectionCountByParam")
	@ResponseBody
	public ReMsg findCollectionCountByParam(String lable,HttpServletRequest request, HttpServletResponse response) {
		ReMsg reMsg = null;
		try {
			String userId = getUserId(request, response);
			Long count = this.zmlUserCollectionService.findCountByUserId(userId, "app");
			reMsg = new ReMsg("" + count, true);
		} catch (Exception e) {
			reMsg = new ReMsg("查询异常！", false);
			e.printStackTrace();
		}
		return reMsg;
	}
	
	//查询用户收藏列表，支持按照标签查询
	@RequestMapping("/findCollectionByParam")
	@ResponseBody
	public ReMsg findCollectionByParam(String label, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		ReMsg reMsg = null;
		try {
			String userId = getUserId(request, response);
			ZmlUserCollectionEntity entity = new ZmlUserCollectionEntity();
			entity.setUserId(userId);
			entity.setLabel(label);
			List list = this.zmlUserCollectionService.findDataByEntity(entity, dataGrid, "app");
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
	
	//根据收藏商品实体参数查询数据  分页显示
	@RequestMapping("/findCollectionByParamList")
	@ResponseBody
	public ReMsg findCollectionByParamList(ZmlUserCollectionEntity entity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		ReMsg reMsg = null;
		try {
			List list = this.zmlUserCollectionService.findDataByEntity(entity, dataGrid, "app");
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
	
	//根据收藏店铺实体参数查询数据  分页显示
	@RequestMapping("/findMerchantsByParamList")
	@ResponseBody
	public ReMsg findMerchantsByParamList(ZmlUserCollectionEntity entity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		ReMsg reMsg = null;
		try {
			List list = this.zmlUserCollectionService.findDataByEntity(entity, dataGrid, "app");
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
	 * 收藏信息
	 * @return
	 */
	@RequestMapping("/collectionInfo")
	@ResponseBody
	public ReMsg collectionInfo(ZmlUserCollectionEntity entity, HttpServletRequest request, HttpServletResponse response) {
			ReMsg reMsg = null;
			String message = null;
			message = "添加成功";
			try{
				if(CollectionType.COMMODITY.getStatusName().equals(entity.getCollectionType())){
					ZmlCommodityEntity commodityEntity = zmlCommodityService.getEntity(ZmlCommodityEntity.class, entity.getDataId());
					entity.setPrice(commodityEntity.getRealPrice());
					entity.setDataName(commodityEntity.getName());
					entity.setCoverImg(commodityEntity.getCoverImg());
				}else if(CollectionType.RELEASE_TERRITORY.getStatusName().equals(entity.getCollectionType())){
					ZmlUserReleaseTerritoryEntity rsEntity = zmlUserReleaseTerritoryService.getEntity(ZmlUserReleaseTerritoryEntity.class, entity.getDataId());
					entity.setDataName(rsEntity.getTitle());
					entity.setCoverImg(rsEntity.getCoverImg());
				}else if(CollectionType.RELEASE_FOOD.getStatusName().equals(entity.getCollectionType())){
					ZmlUserReleaseFoodEntity rsEntity = zmlUserReleaseFoodService.getEntity(ZmlUserReleaseFoodEntity.class, entity.getDataId());
					entity.setDataName(rsEntity.getTitle());
					entity.setCoverImg(rsEntity.getCoverImg());
				}else if(CollectionType.RELEASE_MACHINE.getStatusName().equals(entity.getCollectionType())){
					
				}else if(CollectionType.RELEASE_OTHER.getStatusName().equals(entity.getCollectionType())){
					
				}
				String userId = getUserId(request, response);
				entity.setUserId(userId);
				entity.setCreateDate(new Date());
				entity.setUpdateDate(new Date());
				entity.setStatus(CommonStatus.NORMAL.getStatusValue());
				zmlUserCollectionService.save(entity);
				systemService.addLog("", "", message, Globals.APP_Log_Type_INSERT, Globals.Log_Leavel_INFO);
				reMsg = new ReMsg("", true);
			}catch(Exception e){
				e.printStackTrace();
				message = "查询异常！";
				reMsg = new ReMsg(message, false);
			}
			return reMsg;
		}
	
	/**
	 * 收藏商品信息 
	 * @return
	 */
	@RequestMapping("/collectionCommodityInfo")
	@ResponseBody
	public ReMsg collectionCommodityInfo(ZmlUserCollectionCommodityEntity entity, HttpServletRequest request) {
		ReMsg reMsg = null;
		String message = null;
		message = "添加成功";
		try{
			zmlUserCollectionCommodityService.save(entity);
			systemService.addLog("", "", message, Globals.APP_Log_Type_INSERT, Globals.Log_Leavel_INFO);
			reMsg = new ReMsg("", true);
		}catch(Exception e){
			e.printStackTrace();
			message = "保存异常！";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
	}
	
	/**
	 * 收藏商家信息 
	 * @return
	 */
	@RequestMapping("/collectionMerchantsInfo")
	@ResponseBody
	public ReMsg collectionMerchantsInfo(ZmlUserCollectionMerchantsEntity entity, HttpServletRequest request) {
		ReMsg reMsg = null;
		String message = null;
		message = "添加成功";
		try{
			zmlUserCollectionMerchantsService.save(entity);
			systemService.addLog("", "", message, Globals.APP_Log_Type_INSERT, Globals.Log_Leavel_INFO);
			reMsg = new ReMsg("", true);
		}catch(Exception e){
			e.printStackTrace();
			message = "查询异常！";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
	}
	
	/**
	 * 删除收藏信息
	 * 
	 * @return
	 */
	@RequestMapping("/delCollectionInfo")
	@ResponseBody
	public ReMsg doDel(@RequestParam(required = true) String id,
			@RequestParam(required = true) String flag, HttpServletRequest request) {
		String message = null;
		ReMsg reMsg = null;
		message = "删除成功";
		try{
			//删除收藏
			ZmlUserCollectionEntity entity = systemService.getEntity(ZmlUserCollectionEntity.class, id);
			entity.setStatus(CommonStatus.DEL.getStatusValue());
			zmlUserCollectionService.saveOrUpdate(entity);
			/*if("1".equals(flag)){
				ZmlUserCollectionEntity entity = systemService.getEntity(ZmlUserCollectionEntity.class, id);
				entity.setStatus(CommonStatus.DEL.getStatusValue());
				zmlUserCollectionService.saveOrUpdate(entity);
			//删除收藏商品
			}else if("2".equals(flag)){
				ZmlUserCollectionCommodityEntity entity = systemService.getEntity(ZmlUserCollectionCommodityEntity.class, id);
				entity.setStatus(CommonStatus.DEL.getStatusValue());
				zmlUserCollectionCommodityService.saveOrUpdate(entity);
				//删除收藏商家
			}else if("3".equals(flag)){
				ZmlUserCollectionMerchantsEntity entity = systemService.getEntity(ZmlUserCollectionMerchantsEntity.class, id);
				entity.setStatus(CommonStatus.DEL.getStatusValue());
				zmlUserCollectionMerchantsService.saveOrUpdate(entity);
			}*/
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
	 * 更新收藏
	 * @return
	 */
	@RequestMapping("/doUpdateCollection")
	@ResponseBody
	public ReMsg doUpdateCollection(ZmlUserCollectionEntity entity, HttpServletRequest request) {
		String message = null;
		ReMsg reMsg = null;
		message = "更新成功";
		ZmlUserCollectionEntity t = zmlUserCollectionService.get(ZmlUserCollectionEntity.class, entity.getId());
		try {
			entity.setUpdateDate(new Date());
			MyBeanUtils.copyBeanNotNull2Bean(entity, t);
			zmlUserCollectionService.saveOrUpdate(t);
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
	 * 更新收藏商品
	 * @return
	 */
	@RequestMapping("/doUpdateCollectionCommodity")
	@ResponseBody
	public ReMsg doUpdateCollectionCommodity(ZmlUserCollectionCommodityEntity entity, HttpServletRequest request) {
		String message = null;
		ReMsg reMsg = null;
		message = "更新成功";
		ZmlUserCollectionCommodityEntity t = zmlUserCollectionCommodityService.get(ZmlUserCollectionCommodityEntity.class, entity.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(entity, t);
			zmlUserCollectionCommodityService.saveOrUpdate(t);
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
	 * 更新收藏商家
	 * @return
	 */
	@RequestMapping("/doUpdateCollectionCommodity")
	@ResponseBody
	public ReMsg doUpdateCollectionCommodity(ZmlUserCollectionMerchantsEntity entity, HttpServletRequest request) {
		String message = null;
		ReMsg reMsg = null;
		message = "更新成功";
		ZmlUserCollectionMerchantsEntity t = zmlUserCollectionMerchantsService.get(ZmlUserCollectionMerchantsEntity.class, entity.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(entity, t);
			zmlUserCollectionMerchantsService.saveOrUpdate(t);
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
