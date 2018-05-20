package weixin.idea.huodong.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jce.framework.core.common.controller.BaseController;
import com.jce.framework.core.common.hibernate.qbc.CriteriaQuery;
import com.jce.framework.core.common.model.json.AjaxJson;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.constant.Globals;
import com.jce.framework.core.extend.hqlsearch.HqlGenerateUtil;
import com.jce.framework.core.util.LogUtil;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.ResourceUtil;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.tag.easyui.TagUtil;
import com.jce.framework.web.system.service.SystemService;

import weixin.idea.huodong.entity.PrizeRecordEntity;
import weixin.idea.huodong.service.PrizeRecordServiceI;

/**   
 * @Title: Controller
 * @Description: 中奖纪录
 * @author zhangdaihao
 * @date 2014-06-08 14:28:28
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/prizeRecordController")
public class PrizeRecordController extends BaseController {
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PrizeRecordController.class);

	@Autowired
	private PrizeRecordServiceI prizeRecordService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 中奖纪录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goPrizeRecord")
	public ModelAndView goPrizeRecord(HttpServletRequest request) {
		String hdId = request.getParameter("hdId");
		request.setAttribute("hdId", hdId);
		return new ModelAndView("weixin/idea/huodong/prizerecord/prizeRecordList");
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
	public void datagrid(PrizeRecordEntity prizeRecord,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String hdid = request.getParameter("hdid");
		LogUtil.info(".....openwin...."+hdid);
		CriteriaQuery cq = new CriteriaQuery(PrizeRecordEntity.class, dataGrid);
		cq.eq("hdid",hdid);
		cq.eq(ACCOUNTID, ResourceUtil.getWeiXinAccountId());
		cq.add();
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, prizeRecord, request.getParameterMap());
		this.prizeRecordService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除中奖纪录
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(PrizeRecordEntity prizeRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		prizeRecord = systemService.getEntity(PrizeRecordEntity.class, prizeRecord.getId());
		message = "中奖纪录删除成功";
		prizeRecordService.delete(prizeRecord);
		systemService.addLog("", "", message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加中奖纪录
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(PrizeRecordEntity prizeRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(prizeRecord.getId())) {
			message = "中奖纪录更新成功";
			PrizeRecordEntity t = prizeRecordService.get(PrizeRecordEntity.class, prizeRecord.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(prizeRecord, t);
				prizeRecordService.saveOrUpdate(t);
				systemService.addLog("", "", message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "中奖纪录更新失败";
			}
		} else {
			message = "中奖纪录添加成功";
			prizeRecordService.save(prizeRecord);
			systemService.addLog("", "", message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 中奖纪录列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addOrUpdate")
	public ModelAndView addOrUpdate(PrizeRecordEntity prizeRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(prizeRecord.getId())) {
			prizeRecord = prizeRecordService.getEntity(PrizeRecordEntity.class, prizeRecord.getId());
			req.setAttribute("prizeRecordPage", prizeRecord);
		}
		return new ModelAndView("weixin/idea/huodong/prizerecord/prizeRecord");
	}
}
