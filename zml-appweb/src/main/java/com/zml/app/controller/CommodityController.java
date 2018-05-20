package com.zml.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jce.framework.core.common.exception.BusinessException;
import com.jce.framework.core.common.hibernate.qbc.CriteriaQuery;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.tag.easyui.TagUtil;
import com.jce.framework.web.system.service.SystemService;
import com.zml.base.entity.ZmlCommodityDocEntity;
import com.zml.base.entity.ZmlCommodityEntity;
import com.zml.base.entity.ZmlCommodityStandardEntity;
import com.zml.base.entity.ZmlUserReleaseFoodEntity;
import com.zml.base.entity.ZmlUserReleaseTerritoryEntity;
import com.zml.base.entity.ZmlUserViewHistoryEntity;
import com.zml.common.ReMsg;
import com.zml.enums.ViewType;
import com.zml.service.ZmlCommodityServiceI;
import com.zml.service.ZmlUserViewHistoryServiceI;

import net.sf.json.JSONObject;

/**   
 * @Title: Controller
 * @Description: 商品
 */
@Controller
@RequestMapping("/commodityController")
public class CommodityController extends BaseController {
	@Autowired
	private ZmlCommodityServiceI zmlCommodityService;
	
	@Autowired
	private ZmlUserViewHistoryServiceI  zmlUserViewHistoryService;
	
	@Autowired
	private SystemService systemService;
	
	@RequestMapping(params = "test")
	@ResponseBody
	public ReMsg test(HttpServletRequest request) {
		ReMsg reMsg = new ReMsg("CommodityController测试 成功了！！！", true);
		return reMsg;
	}
	
	//查询推荐商品列表
	@RequestMapping("/recommendList")
	@ResponseBody
	public ReMsg recommendList(HttpServletRequest request) {
		ReMsg reMsg = null;
		try {
			List list = zmlCommodityService.findRecommendList();
			reMsg = new ReMsg("", true);
			reMsg.add("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			reMsg = new ReMsg("查询异常！", false);
		}
		return reMsg;
	}

	//查询商品详情跳转
	@RequestMapping("/toCommodityDetail")
	public ModelAndView toCommodityDetail(String commodityId , HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		if (StringUtil.isNotEmpty(commodityId)){
			mv.setViewName("/commodity/commodityDetail");
			mv.addObject("commodityId", commodityId);
			//增加浏览记录
			ZmlUserViewHistoryEntity viewHistoryEntity = new ZmlUserViewHistoryEntity();
			viewHistoryEntity.setUserId(getUserId(request, response));
			viewHistoryEntity.setCreateDate(new Date());
			viewHistoryEntity.setUpdateDate(new Date());
			viewHistoryEntity.setViewType(ViewType.COMMODITY.getStatusValue());
			viewHistoryEntity.setDataId(commodityId);
			viewHistoryEntity.setStatus(1);
			try {
				zmlUserViewHistoryService.save(viewHistoryEntity);
				ZmlCommodityEntity entity = systemService.getEntity(ZmlCommodityEntity.class, commodityId);
				int count = 0;
				if(entity.getViewCount() != null){
					count +=1;
				}
				entity.setViewCount(count);
				systemService.saveOrUpdate(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			mv.setViewName("/error/dataError");
		}
		return mv;
	}
	
	//查询商品详情
	@RequestMapping("/commodityDetail")
	@ResponseBody
	public ReMsg commodityDetail(String commodityId , HttpServletRequest request) {
		ReMsg reMsg = null;
		if (StringUtil.isNotEmpty(commodityId)){
			try {
				ZmlCommodityEntity zmlCommodity = zmlCommodityService.getEntity(ZmlCommodityEntity.class, commodityId);
				String hql0 = "from ZmlCommodityDocEntity where 1 = 1 AND cOMMODITY_ID = ? ";
			    List<ZmlCommodityDocEntity> zmlCommodityDocEntityList = systemService.findHql(hql0,zmlCommodity.getId());
				Map rsMap = new HashMap();
			    reMsg = new ReMsg("", true);
			    rsMap.put("commodity", zmlCommodity);
			    rsMap.put("commodityDoc", zmlCommodityDocEntityList);
				reMsg.add("data", rsMap);
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
	 * 加载明细列表[适用标准]
	 * @return
	 */
	@RequestMapping("/commodityStandardList")
	public ModelAndView zmlCommodityStandardList(String commodityId, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		if (StringUtil.isNotEmpty(commodityId)){
		    try{
		    	List list = zmlCommodityService.commodityStandardList(commodityId);
				mv.addObject("zmlCommodityStandarList", list);
			}catch(Exception e){
				e.printStackTrace();
				mv.addObject("failMsg", e.getMessage());
			}
		}else{
			mv.addObject("failMsg","非法的访问！");
		}
		mv.setViewName("order/preOrder");
		return mv;
	}
	
	/*查询推荐商品列表
	@RequestMapping(params = "findByClassifyList")
	public ModelAndView findByClassifyList(String classifyId , HttpServletRequest request) {
		ReMsg reMsg = null;
		ModelAndView mv = new ModelAndView();
		try {
			List list = zmlCommodityService.findRecommendList();
			reMsg = new ReMsg("", true);
			reMsg.add("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			reMsg = new ReMsg("查询异常！", false);
		}
		return mv;
	}*/
	
	//转到商品信息列表
	@RequestMapping("/toProductListView")
	public ModelAndView toProductList(String typeId,HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.addObject("typeId", typeId);
		mv.setViewName("/commodity/productList");
		return mv;
	}
	
	
	//根据商品实体参数查询数据  分页显示
	@RequestMapping("/findByParamList")
	@ResponseBody
	public ReMsg findByParamList(ZmlCommodityEntity entity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		ReMsg reMsg = null;
		try {
			Map<String,Object> orders = new HashMap<String,Object>();
			
			if("21".equals(request.getParameter("orders"))){
				orders.put("salesNum","1");//销量一直按升序排
			}
			else if("23".equals(request.getParameter("orders"))){
				orders.put("salesPrice", "asc");//升序
			}
			else if("24".equals(request.getParameter("orders"))){
				orders.put("salesPrice", "desc");//降序
			}
			else{
				orders.put("commodityType", request.getParameter("orders"));
			}
			
			orders.put("queryValue", request.getParameter("queryValue"));//总查询商品
			List list =  this.zmlCommodityService.getDatagrid(entity, dataGrid,orders);
			reMsg = new ReMsg("", true);
			reMsg.add("data", list);
			reMsg.add("dataGrid", dataGrid);
		} catch (Exception e) {
			reMsg = new ReMsg("查询异常！", false);
			e.printStackTrace();
		}
		return reMsg;
	}
	
	// 以下各函数可以提成共用部件 (Add by Quainty)
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
}
