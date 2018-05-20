package org.jeecgframework.core.common.controller;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.interceptors.DateConvertEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zml.cache.RedisUtilTool;
import com.zml.user.entity.CfUserEntity;
import com.zml.util.CookieUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 基础控制器，其他控制器需集成此控制器获得initBinder自动转换的功能
 * 
 */
@Controller
@RequestMapping("/baseController")
public class BaseController {
	
	@Autowired
	public RedisUtilTool redisUtilTool;
	

	public RedisUtilTool getRedisUtilTool() {
		return redisUtilTool;
	}

	public void setRedisUtilTool(RedisUtilTool redisUtilTool) {
		this.redisUtilTool = redisUtilTool;
	}
	
	/**
	 * 将前台传递过来的日期格式的字符串，自动转化为Date类型
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat(
//				"yyyy-MM-dd hh:mm:ss");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(
//				dateFormat, true));
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
	}

	/**
	 * 分页公共方法(非easyui)
	 * @author Alexander
	 */
	public List<?> pageBaseMethod(HttpServletRequest request,
			DetachedCriteria dc, CommonService commonService, int pageRow) {
		// 当前页
		// 总条数
		// 总页数

		int currentPage = 1;

		int totalRow = 0;
		int totalPage = 0;
		// 获取当前页
		String str_currentPage = request.getParameter("str_currentPage");
		currentPage = str_currentPage == null || "".equals(str_currentPage) ? 1
				: Integer.parseInt(str_currentPage);
		// 获取每页的条数
		String str_pageRow = request.getParameter("str_pageRow");
		pageRow = str_pageRow == null || "".equals(str_pageRow) ? pageRow
				: Integer.parseInt(str_pageRow);

		// 统计的总行数
		dc.setProjection(Projections.rowCount());

		totalRow = Integer.parseInt(commonService.findByDetached(dc).get(0)
				.toString());
		totalPage = (totalRow + pageRow - 1) / pageRow;

		currentPage = currentPage < 1 ? 1 : currentPage;
		currentPage = currentPage > totalPage ? totalPage : currentPage;
		// 清空统计函数
		dc.setProjection(null);
		// dc.setResultTransformer(dc.DISTINCT_ROOT_ENTITY);
		List<?> list = commonService.pageList(dc, (currentPage - 1) * pageRow,
				pageRow);

		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageRow", pageRow);
		request.setAttribute("totalRow", totalRow);
		request.setAttribute("totalPage", totalPage);
		return list;
	}

    /**
     * 抽取由逗号分隔的主键列表
     * @param ids 由逗号分隔的多个主键值
     * @return 主键类表
     */
    protected List<String> extractIdListByComma(String ids) {
        List<String> result = new ArrayList<String>();
        if (StringUtils.hasText(ids)) {
            for (String id : ids.split(",")) {
                if (StringUtils.hasLength(id)) {
                    result.add(id.trim());
                }
            }
        }

        return result;
    }
	
  //得到登录loginKey
  	protected String getLoginKey(HttpServletRequest request, HttpServletResponse response) {
  		String loginKey = CookieUtil.getSessionID(request, response);
  		return loginKey;
  	}
  	//获取登录人角色
  	protected String getRoleCode(HttpServletRequest request , HttpServletResponse response) {
  		return (String)redisUtilTool.get(getLoginKey(request, response) + "roleCode");
  	}
  	//获取登录用户
  	protected CfUserEntity getUserInfo(HttpServletRequest request, HttpServletResponse response) {
  		Object value=null;
  		try {
  			value = redisUtilTool.get(getLoginKey(request, response));
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		if (value != null) {
  			return (CfUserEntity) value;
  		} else
  			return null;
  	}
  	
  	/**
  	 * get方式写json数据
  	 * 
  	 * @param response
  	 * @param json
  	 * @return
  	 */
  	protected String printgetjson(int errorcode, String errormsg, HttpServletResponse response) {
  		response.setContentType("application/json");
  		// response.setCharacterEncoding("UTF-8");
  		response.setHeader("Cache-Control", "no-cache");
  		java.io.PrintWriter pw = null;
  		try {
  			pw = response.getWriter();
  			pw.write(String.format("{\"errorcode\":%d,\"errormsg\":\"%s\"}", errorcode, errormsg));
  		} catch (Exception e) {
  			e.printStackTrace();
  		} finally {
  			pw.close();
  		}
  		return null;
  	}

  	protected String g(String name,HttpServletRequest request){
  		return request.getParameter(name) == null ? "" : request.getParameter(name);
  	}
  	
  	public static String getCookie(HttpServletRequest request, String name) {
  		Cookie[] cookies = request.getCookies();
  		if (cookies == null) return null;
  		for (int i = 0; i < cookies.length; i++) {
  			System.out.println("cookies[i].getName:" + cookies[i].getName());
  			if (cookies[i].getName().equals(name)) {
  				return cookies[i].getValue();
  			}
  		}
  		return null;
  	}
}
