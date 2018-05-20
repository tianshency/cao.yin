package com.zml.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jce.framework.core.util.StringUtil;
import com.jce.framework.web.system.pojo.base.TSNotice;
import com.jce.framework.web.system.service.SystemService;
import com.zml.app.util.Constants;
import com.zml.app.util.WeixinUtil;
import com.zml.base.entity.ZmlBannerEntity;
import com.zml.base.entity.ZmlUserEntity;
import com.zml.base.weixin.entity.WeixinAccountEntity;
import com.zml.common.ReMsg;
import com.zml.common.WeiXinConstants;
import com.zml.enums.BannerCategory;
import com.zml.enums.HeadLinesFlag;
import com.zml.service.ZmlBannerServiceI;
import com.zml.service.ZmlUserServiceI;

import net.sf.json.JSONObject;

/**   
 * @Title: Controller
 * @Description: 用户
 */
@Controller
@RequestMapping("/indexController")
public class IndexController extends BaseController {
	
	@Autowired
	private ZmlBannerServiceI zmlBannerServiceI;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private ZmlUserServiceI zmlUserService;
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(IndexController.class);
	/**
	 * 转到首页
	 * @param request
	 * @return 
	 */
	@RequestMapping("/test")
	@ResponseBody
	public ReMsg test(HttpServletRequest request,String token){
		ReMsg msg = new ReMsg("", true);
		try {
			redisUtilTool.set("AAA", "111111");
			String str1 = (String)redisUtilTool.get("AAA");
			System.out.println("str1==" + str1);
			WeixinAccountEntity account = (WeixinAccountEntity)redisUtilTool.get(WeiXinConstants.WEIXIN_ACCOUNT);
			System.out.println("account=111=" + account.getAccountname());
			
			getSignature("111222");
		} catch (Exception e) {
			e.printStackTrace();
			msg = new ReMsg("", false);
		}
		return msg;
	}
	
	/**
	 * 转到首页
	 * @param request
	 * @return
	 */
	@RequestMapping("/toIndex")
	public String toIndexPage(HttpServletRequest request,String token){
		String sessionToken = (String)request.getSession().getAttribute("token");
		if(!StringUtils.isEmpty(token)){
			if(!StringUtils.isEmpty(sessionToken)&&!token.equals(sessionToken)){
				request.getSession().setAttribute("openId",null);
			}
			request.getSession().setAttribute("token",token);
		}
		
		try{
			String openId = (String)request.getSession().getAttribute("openId");
			System.out.println("IndexController.toIndex.openId=" + openId);
			if(!StringUtils.isEmpty(request.getParameter("code")) ||(!StringUtils.isEmpty(openId) && StringUtils.isEmpty((String)request.getParameter("openId")))){
				logger.info("通过code 获取网页授权的ACCESS_TOKEN __________________________________ start");
				//WeChatInfo weChatInfo = this.getWxPublicNoInfo((String)request.getSession().getAttribute("token"));
				//String appId = weChatInfo.getAppid();
				//String secret = weChatInfo.getSecret();
				String appId=Constants.APPID;
				String secret=Constants.SECRET;
				JSONObject jobj = WeixinUtil.getNetAccessToken(request,request.getParameter("code"),appId,secret);
				logger.info("得到的openId:"+jobj.toString());
				logger.info("通过code 获取网页授权的ACCESS_TOKEN __________________________________ end");
				openId = jobj.getString("openid");
			}else{
				String openId_param = (String)request.getParameter("openId");
				if(!StringUtils.isEmpty(openId_param))
					openId = openId_param;
			}
			System.out.println("------------------------------openId==="+openId);
			/*if(openId == null || "".equals(openId)){
				openId="o61jewi44k-BEfa0CWXz1dSswR8U";
			}*/
			ZmlUserEntity user = (ZmlUserEntity)redisUtilTool.get(openId);
			if(user == null){
				String result = this.getAuthInfo(request,openId);
				System.out.println("------------------------------result==="+result);
				if(!"success".equals(result)){
					return "/error";
				}
			}else{
				request.getSession().setAttribute("zmlUser", user);
		    	request.getSession().setAttribute("appin_info", Constants.APPID);
			}
			request.getSession().setAttribute("openId", openId);
		}catch(Exception ex){
			if("-1".equals(ex.getMessage())){
				return "/error";
			}
			else if("-3".equals(ex.getMessage())){
				return "/weixinstop_error";
			}
			else if("-4".equals(ex.getMessage())){
				return "/redis_error";
			}
			ex.printStackTrace();
			logger.error("这个错误用理会，点返回必然报错，因为code只能用一次-------------："+ex.getMessage());
		}
		
		
		request.setAttribute("wxSigunate", this.getSignature((String)request.getSession().getAttribute("token")));
		
		return "main/index";
	}
	
	
	//查询banner列表
	@RequestMapping(params = "bannerList")
	@ResponseBody
	public ReMsg bannerList(HttpServletRequest request) {		
		ReMsg reMsg = new ReMsg("", true);
		try {
			//reMsg.add("bannersList", list);
			List topBannerList = (List)redisUtilTool.get("topBannerList");
			if(topBannerList == null ){
				topBannerList = zmlBannerServiceI.bannerListGet(BannerCategory.TOP_BANNER.getStatusValue() , 5);
			}
			reMsg.add("bannersList", topBannerList);
			//从redies中获取 最新公告列表(数据是在后台维护的时候插入缓存的)
			List headlinesList = (List)redisUtilTool.get("headlinesList");
			if(headlinesList == null ){
				headlinesList = zmlBannerServiceI.bannerListGet(BannerCategory.HEADLINES_BANNER.getStatusValue() , 2);
			}
			reMsg.add("headlinesList", headlinesList);
		} catch (Exception e) {
			e.printStackTrace();
			reMsg = new ReMsg("", false);
		}
		return reMsg;
	}

	//跳转banner详情
	@RequestMapping("/toBannerDetail")
	public ModelAndView toBannerDetail(@RequestParam(required = true) String bannerId , HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (StringUtil.isNotEmpty(bannerId)){
			mv.addObject("bannerId", bannerId);
			mv.setViewName("/banner/bannerDetail");
		}else{
			mv.setViewName("apppage/error/dataError");
		}
		return mv;
	}
	
	//查询banner详情
	@RequestMapping("/bannerDetail")
	public ModelAndView bannerDetail(@RequestParam(required = true) String bannerId , HttpServletRequest request) {
		ReMsg reMsg = null;
		ModelAndView mv = new ModelAndView();
		if (StringUtil.isNotEmpty(bannerId)){
			try {
				ZmlBannerEntity zmlBannerEntity = zmlBannerServiceI.getEntity(ZmlBannerEntity.class, bannerId);
				reMsg = new ReMsg("", true);
				reMsg.add("banner", zmlBannerEntity);
				//增加浏览数量
			} catch (Exception e) {
				e.printStackTrace();
				reMsg = new ReMsg("异常异常！", false);
			}
		}else{
			reMsg = new ReMsg("数据异常！", false);
		}
		mv.addObject("reMsg", reMsg);
		mv.setViewName("/banner/bannerInfo");
		return mv;
	}
	
	//查询头条列表
	@RequestMapping("/headlinesList")
	@ResponseBody
	public ReMsg headlinesList(HttpServletRequest request) {
		ReMsg reMsg = null;
		try {
			//List<ZmlUserEntity> list = zmlBannerServiceI.bannerListGet();
			//从redies中获取 最新公告列表(数据是在后台维护的时候插入缓存的)
			List headlinesList = (List)redisUtilTool.get("headlinesList");
			if(headlinesList == null ){
				headlinesList = new ArrayList();
				Map temp = new HashMap();
				temp.put("id", "11111111");
				temp.put("name", "暂无数据......");
				temp.put("type", "1");
				headlinesList.add(temp);
				temp = new HashMap();
				temp.put("id", "22222222");
				temp.put("name", "暂无数据......");
				temp.put("type", "1");
				headlinesList.add(temp);
			}
			reMsg = new ReMsg("", true);
			reMsg.add("data", headlinesList);
		} catch (Exception e) {
			e.printStackTrace();
			reMsg = new ReMsg("查询异常", false);
		}
		return reMsg;
	}
	
	//跳转头条列表页面
	@RequestMapping(params = "toHeadlinesList")
	public ModelAndView toHeadlinesList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("headlines/headlinesList");
		return mv;
	}
	//跳转头条页面
	@RequestMapping(params = "toHeadlines")
	public ModelAndView toHeadlines(@RequestParam(required = true) String id,
			@RequestParam(required = true) String flag, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", id);
		mv.addObject("flag", flag);
		mv.setViewName("headlines/headlinesDetail");
		return mv;
	}
	//查看头条详情
	@RequestMapping(params = "headlinesDetail")
	@ResponseBody
	public ReMsg headlinesDetail(@RequestParam(required = true) String id,
			@RequestParam(required = true) String flag, HttpServletRequest request) {
		ReMsg reMsg = null;
		try {
			Map info = new HashMap();
			if(HeadLinesFlag.SYS_ANNOUNCEMENT.getStatusValue().equals(flag)){
				TSNotice zmlUser = systemService.getEntity(TSNotice.class, id);
				info.put("id", zmlUser.getId());
				info.put("detail", zmlUser.getNoticeContent());
			}
			reMsg = new ReMsg("", true);
			reMsg.add("data", info);
		} catch (Exception e) {
			e.printStackTrace();
			reMsg = new ReMsg("查询异常", false);
		}
		return reMsg;
	}
}
