package com.jce.framework.web.system.controller.core;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jce.framework.core.common.controller.BaseController;
import com.jce.framework.core.common.hibernate.qbc.CriteriaQuery;
import com.jce.framework.core.common.model.json.AjaxJson;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.constant.Globals;
import com.jce.framework.core.timer.DynamicTask;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.tag.easyui.TagUtil;
import com.jce.framework.web.system.pojo.base.TSTimeTaskEntity;
import com.jce.framework.web.system.service.SystemService;
import com.jce.framework.web.system.service.TimeTaskServiceI;
import org.quartz.CronTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**   
 * @Title: Controller
 * @Description: 定时任务管理
 *
 */
//@Scope("prototype")
@Controller
@RequestMapping("/timeTaskController")
public class TimeTaskController extends BaseController {

	@Autowired
	private TimeTaskServiceI timeTaskService;
	@Autowired
	private DynamicTask dynamicTask;
	@Autowired
	private SystemService systemService;


	/**
	 * 定时任务管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "timeTask")
	public ModelAndView timeTask(HttpServletRequest request) {
		return new ModelAndView("system/timetask/timeTaskList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(TSTimeTaskEntity timeTask,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSTimeTaskEntity.class, dataGrid);
		//查询条件组装器
		com.jce.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, timeTask, request.getParameterMap());
		this.timeTaskService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除定时任务管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TSTimeTaskEntity timeTask, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		timeTask = systemService.getEntity(TSTimeTaskEntity.class, timeTask.getId());
		message = "定时任务管理删除成功";
		timeTaskService.delete(timeTask);
		systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加定时任务管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TSTimeTaskEntity timeTask, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		CronTrigger trigger = new CronTrigger();
		try {
			trigger.setCronExpression(timeTask.getCronExpression());
		} catch (ParseException e) {
			j.setMsg("Cron表达式错误");
			return j;
		}
		if (StringUtil.isNotEmpty(timeTask.getId())) {
			message = "定时任务管理更新成功";
			TSTimeTaskEntity t = timeTaskService.get(TSTimeTaskEntity.class, timeTask.getId());
			try {
				if(!timeTask.getCronExpression().equals(t.getCronExpression())){
					timeTask.setIsEffect("0");
				}
				MyBeanUtils.copyBeanNotNull2Bean(timeTask, t);
				timeTaskService.saveOrUpdate(t);
				systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "定时任务管理更新失败";
			}
		} else {
			message = "定时任务管理添加成功";
			timeTaskService.save(timeTask);
			systemService.addLog("", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 定时任务管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TSTimeTaskEntity timeTask, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(timeTask.getId())) {
			timeTask = timeTaskService.getEntity(TSTimeTaskEntity.class, timeTask.getId());
			req.setAttribute("timeTaskPage", timeTask);
		}
		return new ModelAndView("system/timetask/timeTask");
	}
	
	/**
	 * 更新任务时间使之生效
	 */
	@RequestMapping(params = "updateTime")
	@ResponseBody
	public AjaxJson updateTime(TSTimeTaskEntity timeTask, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		timeTask = timeTaskService.get(TSTimeTaskEntity.class, timeTask.getId());
		boolean isUpdate = dynamicTask.updateCronExpression(timeTask.getTaskId() , timeTask.getCronExpression());
		if(isUpdate){
			timeTask.setIsEffect("1");
			timeTask.setIsStart("1");
			timeTaskService.updateEntitie(timeTask);
		}
		j.setMsg(isUpdate?"定时任务管理更新成功":"定时任务管理更新失败");
		return j;
	}
	/**
	 * 启动或者停止任务
	 */
	@RequestMapping(params = "startOrStopTask")
	@ResponseBody
	public AjaxJson startOrStopTask(TSTimeTaskEntity timeTask, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		boolean isStart = timeTask.getIsStart().equals("1");
		timeTask = timeTaskService.get(TSTimeTaskEntity.class, timeTask.getId());
		boolean isSuccess = dynamicTask.startOrStop(timeTask.getTaskId() ,isStart);
		if(isSuccess){
			timeTask.setIsStart(isStart?"1":"0");
			timeTaskService.updateEntitie(timeTask);
			systemService.addLog("", "", (isStart?"开启任务":"停止任务")+timeTask.getTaskId(),
					Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}
		j.setMsg(isSuccess?"定时任务管理更新成功":"定时任务管理更新失败");
		return j;
	}
	
}
