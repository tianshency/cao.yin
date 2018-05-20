package com.zml.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zml.base.entity.ZmlCropPlanEntity;
import com.zml.common.ReMsg;
import com.zml.service.ZmlCropPlanServiceI;

/**   
 * @Title: Controller
 * @Description: 用户
 *
 */
@Controller
@RequestMapping("/cropPlanController")
public class UserCropPlanController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserCropPlanController.class);

	@Autowired
	private ZmlCropPlanServiceI zmlCropPlanService;
	
	/**
	 * 增加种植计划
	 */
	@RequestMapping("/addCropPlan")
	@ResponseBody
	public ReMsg addCropPlan(List<ZmlCropPlanEntity> list,  HttpServletRequest request,  HttpServletResponse response) {
		ReMsg reMsg = null;
		String message = null;
		message = "添加成功";
		try{
			String openId = getUserId(request, response);
			//将用户ID增加到 实体中
			for (ZmlCropPlanEntity entity : list) {
				entity.setOpenId(openId);
			}
			zmlCropPlanService.batchSave(list);
			reMsg = new ReMsg("", true);
		}catch(Exception e){
			e.printStackTrace();
			message = "增加异常！";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
	}
	
	/**
	 * 查询我的种植计划
	 */
	@RequestMapping("/findCropPlanByUserId")
	@ResponseBody
	public ReMsg findCropPlanByUserId(String userId,  HttpServletRequest request) {
		ReMsg reMsg = null;
		String message = null;
		message = "";
		try{
			
			reMsg = new ReMsg("", true);
		}catch(Exception e){
			e.printStackTrace();
			message = "查询异常！";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
	}
	
}
