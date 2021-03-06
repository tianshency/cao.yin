package com.jce.framework.web.demo.controller.test;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import com.jce.framework.core.common.controller.BaseController;
import com.jce.framework.core.common.model.json.AjaxJson;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.constant.Globals;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.tag.easyui.TagUtil;
import com.jce.framework.web.demo.entity.test.JeecgMinidaoEntity;
import com.jce.framework.web.demo.service.test.JeecgMinidaoServiceI;
import com.jce.framework.web.system.pojo.base.TSDepart;
import com.jce.framework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Title: Controller
 * @Description: Minidao例子
 * @author fancq
 * @date 2013-12-23 21:18:59
 * @version V1.0   
 *
 */
//@Scope("prototype")
@Controller
@RequestMapping("/jeecgMinidaoController")
public class JeecgMinidaoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(JeecgMinidaoController.class);

	@Autowired
	private JeecgMinidaoServiceI jeecgMinidaoService;
	@Autowired
	private SystemService systemService;


	/**
	 * Minidao例子列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "jeecgMinidao")
	public ModelAndView jeecgMinidao(HttpServletRequest request) {
		return new ModelAndView("sys/demo/test/jeecgMinidaoList");
	}

	/**
	 * 通过Minidao SQL方式查询数据，进行列表展现
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(JeecgMinidaoEntity jeecgMinidao,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		List<JeecgMinidaoEntity> list = jeecgMinidaoService.listAll(jeecgMinidao, dataGrid.getPage(), dataGrid.getRows());
		Integer count = jeecgMinidaoService.getCount();
		dataGrid.setTotal(count);
		dataGrid.setResults(list);
		String total_salary = String.valueOf(jeecgMinidaoService.getSumSalary());
		/*
		 * 说明：格式为 字段名:值(可选，不写该值时为分页数据的合计) 多个合计 以 , 分割
		 */
		dataGrid.setFooter("salary:"+(total_salary.equalsIgnoreCase("null")?"0.0":total_salary)+",age,email:合计");
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除Minidao例子
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(JeecgMinidaoEntity jeecgMinidao, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		jeecgMinidao = systemService.getEntity(JeecgMinidaoEntity.class, jeecgMinidao.getId());
		message = "Minidao例子删除成功";
		systemService.delete(jeecgMinidao);
		systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加Minidao例子
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(JeecgMinidaoEntity jeecgMinidao, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(jeecgMinidao.getId())) {
			message = "Minidao例子更新成功";
			JeecgMinidaoEntity t = systemService.getEntity(JeecgMinidaoEntity.class, jeecgMinidao.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(jeecgMinidao, t);
				systemService.updateEntitie(t);
				systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "Minidao例子更新失败";
			}
		} else {
			message = "Minidao例子添加成功";
			jeecgMinidao.setStatus("0");
			systemService.save(jeecgMinidao);
			systemService.addLog("", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * Minidao例子列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(JeecgMinidaoEntity jeecgMinidao, HttpServletRequest req) {
		//获取部门信息
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		req.setAttribute("departList", departList);
		
		if (StringUtil.isNotEmpty(jeecgMinidao.getId())) {
			jeecgMinidao = systemService.getEntity(JeecgMinidaoEntity.class, jeecgMinidao.getId());
			req.setAttribute("jeecgMinidaoPage", jeecgMinidao);
		}
		return new ModelAndView("sys/demo/test/jeecgMinidao");
	}
}
