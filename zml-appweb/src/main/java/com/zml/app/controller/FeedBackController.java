package com.zml.app.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jce.framework.core.constant.Globals;
import com.jce.framework.web.system.service.SystemService;
import com.zml.base.entity.ZmlSysOpinionFeedbackInfoEntity;
import com.zml.common.ReMsg;
import com.zml.service.ZmlSysOpinionFeedbackInfoServiceI;

//意见反馈
@Controller
@RequestMapping("/feedBackController")
public class FeedBackController extends BaseController {
	@Autowired
	private ZmlSysOpinionFeedbackInfoServiceI  sysOpinionFeedbackInfoService;
	@Autowired
	private SystemService systemService;
	
	/**
	 * 转到意见反馈页面
	 * @return
	 */
	@RequestMapping("/toFeedBackPage")
	public String toFeedBackPage(){
		return "personal/feedback";
	}
	
	
	/**
	 * 添加意见反馈
	 * @param zmlSysOpinionFeedbackInfoEntity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFeedBackInfo",headers=("content-type=multipart/*"))
	@ResponseBody
	public ReMsg addFeedBackInfo(ZmlSysOpinionFeedbackInfoEntity entity,
								 @RequestParam(value="showPic") String showPic,
								 @RequestParam(value = "upload", required = false) MultipartFile[] upload
								 ,HttpServletRequest request, HttpServletResponse response){
		ReMsg reMsg = null;
		String message = null;
		message = "添加成功";
		try {
			String userId = getUserId(request, response);
			entity.setUserId(userId);
			entity.setCreateDate(new Date());
			entity.setUpdateDate(new Date());
			sysOpinionFeedbackInfoService.save(entity, upload);
			systemService.addLog("", "", message, Globals.APP_Log_Type_INSERT, Globals.Log_Leavel_INFO);
			reMsg = new ReMsg("", true);
		} catch (Exception e) {
			e.printStackTrace();
			message = "保存异常！";
			reMsg = new ReMsg(message, false);
		}
		return reMsg;
	}
}
