package com.zml.app.controller;

import java.io.PrintWriter;
import java.math.BigDecimal;
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
import com.zml.base.wrw.entity.WrwBonusRecord;
import com.zml.common.Constant;
import com.zml.enums.wrw.BonusStatus;
import com.zml.enums.wrw.BonusType;
import com.zml.enums.wrw.BonusTypeAmount;
import com.zml.util.DateUtil;
import com.zml.util.GenerateNo;

import net.sf.json.JSONObject;

/**   
 * @Title: Controller
 * @Description: 用户
 */
@Controller
@RequestMapping("/appWrwUser")
public class WrwUser extends BaseController {
	@Autowired
	private SystemService systemService;
	
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping(params = "loginWrw")
	public void loginWrw(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain");  
        response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        //response.setHeader("Access-Control-Allow-Origin: *");
        response.addHeader("Access-Control-Allow-Origin","*");
        
		String phone = request.getParameter("phone");
		String pwd = request.getParameter("pwd");
		System.out.println("loginWrw===phone=" + phone + "pwd=="+ pwd);
		//ReMsg msg = null;
		//根据手机号查找用户 是否存在
		String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数  
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			PrintWriter out = response.getWriter();
			if(phone != null && !"".equals(phone)
					&&pwd != null && !"".equals(pwd)){
				String password = PasswordUtil.encrypt(phone, pwd, PasswordUtil.getStaticSalt());
				String hql0 = "from ZmlUserEntity where 1 = 1 AND phone = ? and password = ?";
				//systemService.findo
		    	List<ZmlUserEntity> userList = systemService.findHql(hql0,phone, password);
				if(userList != null && userList.size() == 1){
					ZmlUserEntity user = userList.get(0);
					String token = GenerateNo.uuid();
					redisUtilTool.set(token, user);
					System.out.println("token==" + token);
					map.put("success", "true"); 
					map.put("token", token); 
					map.put("userInfo", user); 
				}else if(userList != null && userList.size() > 1){
					map.put("success", "false"); 
					map.put("msg", "失败！"); 
					System.out.println("手机号 ：" + phone + " 对应多个用户！");
				}else{
					map.put("success", "false"); 
					map.put("msg", "失败！"); 
					System.out.println("手机号 ：" + phone + " 用户不存在！");
				}
			}else{
				map.put("success", "false"); 
				map.put("msg", "失败！"); 
				System.out.println("手机号、密码不能为空！");
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
	 * 自动登录
	 * @return
	 */
	@RequestMapping(params = "autoLoginWrw")
	public void autoLoginWrw(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain");  
        response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        //response.setHeader("Access-Control-Allow-Origin: *");
        response.addHeader("Access-Control-Allow-Origin","*");
        
		String token = request.getParameter("token");
		//ReMsg msg = null;
		//根据手机号查找用户 是否存在
		String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数  
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			PrintWriter out = response.getWriter();
			if(token != null && !"".equals(token)){
				ZmlUserEntity user = (ZmlUserEntity)redisUtilTool.get(token);
				ZmlUserEntity newUser = systemService.getEntity(ZmlUserEntity.class, user.getId());
				redisUtilTool.set(token, newUser);
				System.out.println("token==" + token);
				map.put("success", "true"); 
				map.put("token", token); 
				map.put("userInfo", newUser); 
			}else{
				map.put("success", "false"); 
				map.put("msg", "失败！"); 
				System.out.println("自动登录失败 token is null");
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
	 *减少用户奖金
	 * @return
	 */
	@RequestMapping(params = "cutUserBonus")
	public void cutUserBonus(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain");  
        response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        //response.setHeader("Access-Control-Allow-Origin: *");
        response.addHeader("Access-Control-Allow-Origin","*");
        
		String phone = request.getParameter("phone");
		String operate = request.getParameter("operate");
		String operatePwd = request.getParameter("operatePwd");
		String amount = request.getParameter("amount");
		String temp1 = DateUtil.formatDateToStr(DateUtil.FORMAT_YYYY_MM_DD, new Date()) + "1qazxsw2";
		
		//ReMsg msg = null;
		//根据手机号查找用户 是否存在
		String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数  
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			PrintWriter out = response.getWriter();
			if("admin".equals(operate) && temp1.equals(operatePwd)){
				String hql0 = "from ZmlUserEntity where 1 = 1 AND phone = ?";
				//systemService.findo
		    	List<ZmlUserEntity> userList = systemService.findHql(hql0, phone);
				if(userList != null && userList.size() == 1){
					ZmlUserEntity user = userList.get(0);
					BigDecimal balance = user.getBalance();
					user.setBalance(user.getBalance().subtract(new BigDecimal(amount)));
					user.setUpdateDate(new Date());
					systemService.save(user);

	    			//给推荐人增加推荐 奖励记录
	    			WrwBonusRecord bonusRecord = new WrwBonusRecord();
	    			bonusRecord.setUserId(user.getId());
	    			bonusRecord.setOldAmount(balance);
	    			bonusRecord.setAmount(new BigDecimal(amount));
	    			bonusRecord.setType(BonusType.T_SUB.getValue());
	    			bonusRecord.setStatus(BonusStatus.SUB.getValue());
	    			bonusRecord.setCreateDate(new Date());
	    			systemService.save(bonusRecord);
				}else if(userList != null && userList.size() > 1){
					map.put("success", "false"); 
					map.put("msg", "失败！"); 
					System.out.println("手机号 ：" + phone + " 对应多个用户！");
				}else{
					map.put("success", "false"); 
					map.put("msg", "失败！"); 
					System.out.println("手机号 ：" + phone + " 用户不存在！");
				}
			}else{
				map.put("success", "false"); 
				map.put("msg", "失败1！"); 
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
	 *修改 给二级，三级 推荐标识 
	 * @return
	 */
	@RequestMapping(params = "updateRecommendFlag")
	public void updateRecommendFlag(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain");  
        response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        //response.setHeader("Access-Control-Allow-Origin: *");
        response.addHeader("Access-Control-Allow-Origin","*");
        
		String operate = request.getParameter("operate");
		String operatePwd = request.getParameter("operatePwd");
		String flag = request.getParameter("flag");
		String temp1 = DateUtil.formatDateToStr(DateUtil.FORMAT_YYYY_MM_DD, new Date()) + "1qazxsw2";
		
		//ReMsg msg = null;
		//根据手机号查找用户 是否存在
		String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数  
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			PrintWriter out = response.getWriter();
			if("admin".equals(operate) && temp1.equals(operatePwd)){
				if(flag != null && !"".equals(flag)){
					if("1".equals(flag))
						Constant.SDZJ_BOUNS_S_T_F = true;
					else
						Constant.SDZJ_BOUNS_S_T_F = false;
				}else{
					map.put("success", "false"); 
					map.put("msg", "失败222！"); 
				}
			}else{
				map.put("success", "false"); 
				map.put("msg", "失败111！"); 
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
