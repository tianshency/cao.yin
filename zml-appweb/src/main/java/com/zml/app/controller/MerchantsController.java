package com.zml.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.web.system.pojo.base.TSDepart;
import com.zml.base.entity.ZmlMerchantsEntity;
import com.zml.common.ReMsg;
import com.zml.service.ZmlCommodityClassifyServiceI;
import com.zml.service.ZmlCropTypeServiceI;
import com.zml.service.ZmlMerchantsServiceI;

import net.sf.json.JSONObject;

/**   
 * @Title: Controller
 * @Description: 商户
 */
@Controller
@RequestMapping("/merchantsController")
public class MerchantsController {
	
	@Autowired
	private ZmlMerchantsServiceI zmlMerchantsService;
	
	@Autowired
	private ZmlCropTypeServiceI zmlCropTypeService;
	
	@Autowired
	private ZmlCommodityClassifyServiceI zmlCommodityClassifyService;
	
	//查询农资类型、商户列表
	@RequestMapping("/agriResourcesMerchants")
	@ResponseBody
	public ReMsg agriResourcesMerchants(HttpServletRequest request) {
		ReMsg reMsg = null;
		try {
			int num = 4;
			reMsg = new ReMsg("", true);
			//查询农资类型
			List classifyList = zmlCommodityClassifyService.findIndexList(num);
			reMsg.add("classifyList", classifyList);
			//查询 首页商户
			List mercList = zmlMerchantsService.findRecommendList(null, 0, num);
			reMsg.add("mercList", mercList);
		} catch (Exception e) {
			e.printStackTrace();
			reMsg = new ReMsg("查询异常！", false);
		}
		return reMsg;
	}

	//跳转商户列表页
	@RequestMapping("/toMerchantsList")
	public String toMerchantsList(String merchantsId , HttpServletRequest request) {
		return "/commodity/merchantsList";
	}
	
	//跳转商户页
	@RequestMapping("/toMerchantsInfo")
	public ModelAndView toMerchantsDetail(String merchantsId , HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (StringUtil.isNotEmpty(merchantsId)){
			//ZmlMerchantsEntity zmlCommodity = zmlMerchantsService.getEntity(ZmlMerchantsEntity.class, merchantsId);
			//request.setAttribute("zmlCommodity", zmlCommodity);
			mv.addObject("merchantsId", merchantsId);
			mv.setViewName("/merchants/businessstore");
		}else{
			mv.setViewName("apppage/error/dataError");
		}
		
		return mv;
	}
	
	//跳转到商户介绍页
	@RequestMapping("/toBusinessDetail")
	public ModelAndView toBusinessDetail(String merchantsId , HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (StringUtil.isNotEmpty(merchantsId)){
			//ZmlMerchantsEntity zmlCommodity = zmlMerchantsService.getEntity(ZmlMerchantsEntity.class, merchantsId);
			//request.setAttribute("zmlCommodity", zmlCommodity);
			mv.addObject("merchantsId", merchantsId);
			mv.setViewName("/merchants/storedetail");
		}else{
			mv.setViewName("apppage/error/dataError");
		}
		
		return mv;
	}
	
	
	//查询商户详情页
	@RequestMapping("/merchantsDetail")
	@ResponseBody
	public ReMsg merchantsDetail(String id , HttpServletRequest request) {
		ReMsg reMsg = null;
		if (StringUtil.isNotEmpty(id)){
			try {
				TSDepart zmlCommodity = zmlMerchantsService.getEntity(TSDepart.class, id);
				reMsg = new ReMsg("", true);
				reMsg.add("commodity", zmlCommodity);
			} catch (Exception e) {
				e.printStackTrace();
				reMsg = new ReMsg("查询异常！", false);
			}
		}else{
			reMsg = new ReMsg("数据异常！", false);
		}
		return reMsg;
	}
	
	//根据商家实体参数查询数据  分页显示
	@RequestMapping("/findByParamList")
	@ResponseBody
	public ReMsg findByParamList(ZmlMerchantsEntity entity,Integer currPage, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		//responseDatagrid(response, jObject);
		ReMsg reMsg = null;
		try {
			if(currPage == null){
				currPage = 0;
			}
			if(pageSize == null){
				pageSize = 100;
			}
			//JSONObject jObject = this.zmlMerchantsService.getDatagrid(entity, dataGrid);
			//List list =  this.zmlMerchantsService.getDatagrid(entity, dataGrid);
			//查询 首页商户
			List mercList = zmlMerchantsService.findRecommendList(null, currPage, pageSize);
			reMsg = new ReMsg("", true);
			reMsg.add("data", mercList);
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
	
}
