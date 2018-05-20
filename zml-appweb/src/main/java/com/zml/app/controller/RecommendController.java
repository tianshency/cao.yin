package com.zml.app.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jce.framework.core.util.PasswordUtil;
import com.jce.framework.web.system.service.SystemService;
import com.zml.base.entity.ZmlUserEntity;
import com.zml.base.wrw.entity.WrwRecommendRecord;
import com.zml.common.Constant;
import com.zml.util.file.ImageTools;

import net.sf.json.JSONObject;

/**   
 * @Title: Controller
 * @Description: 推荐
 */
@Controller
@RequestMapping("/appRecommendController")
public class RecommendController extends BaseController {
	@Autowired
	private SystemService systemService;
	/**
	 * 提交推荐信息
	 * @return
	 */
	@RequestMapping(params = "commitRecommendInfo")
	//@ResponseBody
	//public ReMsg commitRecommendInfo(HttpServletRequest request) {
	public void commitRecommendInfo(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain");  
        response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        //response.setHeader("Access-Control-Allow-Origin: *");
        response.addHeader("Access-Control-Allow-Origin","*");
        
		String phone = request.getParameter("phone");
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		//根据手机号查找用户 是否存在
		String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数  
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			PrintWriter out = response.getWriter();
			Map<String, String> param = new HashMap();
			String hql0 = "from ZmlUserEntity where 1 = 1 AND phone = ?";
			//systemService.findo
	    	List<ZmlUserEntity> userList = systemService.findHql(hql0, phone);
	    	if(userList != null && userList.size() == 1){
	    		ZmlUserEntity user = userList.get(0);
	    		
	    		String password = PasswordUtil.encrypt(phone, pwd, PasswordUtil.getStaticSalt());
	    		user.setPassword(password);
	    		user.setUserName(userName);
	    		String recommendCode = PasswordUtil.getMD5Pwd(phone);
	    		user.setRecommendCode(recommendCode);
	    		user.setUpdateDate(new Date());
	    		String url = Constant.SDZJ_URL + "?tjr=" + recommendCode + "&tid=sdzj171111";
				// 生成二维码
				String qRCodePath = ImageTools.createQRCode(user.getId(), url);
				user.setqRCodePath(qRCodePath);
				systemService.saveOrUpdate(user);
				map.put("success", "true");  
	    	}if(userList != null && userList.size() > 1){
	    		map.put("success", "false");  
	    	}else{
	    		//用户不存在，新注册用户
	    		ZmlUserEntity user = new ZmlUserEntity();
	    		String password = PasswordUtil.encrypt(phone, user.getPassword(), PasswordUtil.getStaticSalt());
	    		user.setPhone(phone);
	    		user.setPassword(password);
	    		user.setUserName(userName);
	    		String recommendCode = PasswordUtil.getMD5Pwd(phone);
	    		user.setRecommendCode(recommendCode);
	    		user.setCreateDate(new Date());
	    		user.setUpdateDate(new Date());
	    		String url = Constant.SDZJ_URL + "?tjr=" + recommendCode + "&tid=sdzj171111";
				// 生成二维码
				String qRCodePath = ImageTools.createQRCode(user.getId(), url);
				user.setqRCodePath(qRCodePath);
				systemService.save(user);
	    		map.put("success", "true");
	    	}
	        
	        JSONObject resultJSON = JSONObject.fromObject(map); //根据需要拼装json  
	        out.println(jsonpCallback+"("+resultJSON.toString(1,1)+")");//返回jsonp格式数据  
	        out.flush();  
	        out.close();  
		}catch(Exception e){
			e.printStackTrace();
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询我推荐列表
	 * @return
	 */
	@RequestMapping(params = "searchMyRecommendList")
	public void searchMyRecommendList(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain");  
        response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        //response.setHeader("Access-Control-Allow-Origin: *");
        response.addHeader("Access-Control-Allow-Origin","*");
        
		String token = request.getParameter("token");
		String type = request.getParameter("type");
		String currPage = request.getParameter("currPage");
		//根据手机号查找用户 是否存在
		String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数  
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			PrintWriter out = response.getWriter();
			if(token != null && !"".equals(token)){
				int page = 1;
				int rows = 100;
				if(currPage != null && !"".equals(currPage)){
					page = Integer.parseInt(currPage);
				}
				System.out.println("token============"+token);
				ZmlUserEntity user = (ZmlUserEntity)redisUtilTool.get(token);
				String sql = "select CONCAT(substring(curr_user,1,8),'****') re_user, date_format(create_date,'%Y-%m-%d') cd "+
				"from wrw_recommend_record where recommen_user=? and type=? order by create_date desc";
				List list = systemService.findForJdbcParam(sql, page, rows, user.getPhone(), type);
				//我的二级
				String sql1 = "select CONCAT(substring(curr_user,1,8),'****') re_user, CONCAT(substring(recommen_user,1,8),'****')  recommenUser,date_format(create_date,'%Y-%m-%d') cd "+
						"from wrw_recommend_record where second_user=? and type=? order by create_date desc";
				List list1 = systemService.findForJdbcParam(sql1, page, rows, user.getPhone(), type);
				//我的三级
				String sql2 = "select CONCAT(substring(curr_user,1,8),'****') re_user,CONCAT(substring(recommen_user,1,8),'****') recommenUser, date_format(create_date,'%Y-%m-%d') cd "+
						"from wrw_recommend_record where third_user=? and type=? order by create_date desc";
				List list2 = systemService.findForJdbcParam(sql2, page, rows, user.getPhone(), type);
				
		    	map.put("success", "true"); 
				map.put("msg", ""); 
				Map<String,Object> hm = new HashMap<String,Object>();
				hm.put("first", list);
				hm.put("second", list1);
				hm.put("thrid", list2);
				map.put("data", hm); 
			}else{
				map.put("success", "false"); 
				map.put("msg", "没有登录！"); 
			}
	        JSONObject resultJSON = JSONObject.fromObject(map); //根据需要拼装json  
	        out.println(jsonpCallback+"("+resultJSON.toString(1,1)+")");//返回jsonp格式数据  
	        out.flush();  
	        out.close();  
		}catch(Exception e){
			e.printStackTrace();
			e.printStackTrace();
		}
	}
}
