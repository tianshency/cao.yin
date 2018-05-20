package com.jce.framework.core.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zml.cache.RedisUtilTool;
import com.zml.common.Constant;
import com.zml.util.CookieUtil;

/**
 * 用户使用系统 时间过滤器
 */
@Component
public class OperateTimeFilter implements Filter {
	private static final Logger logger = Logger.getLogger(OperateTimeFilter.class);
	private static final long serialVersionUID = 6357869213649815390L;
	
	//缓存工具类
	@Autowired
	private RedisUtilTool redisUtilTool;
	

	public RedisUtilTool getRedisUtilTool() {
		return redisUtilTool;
	}

	public void setRedisUtilTool(RedisUtilTool redisUtilTool) {
		this.redisUtilTool = redisUtilTool;
	}
	
    /**
     * Default constructor. 
     */
    public OperateTimeFilter() {
    }
    @Override
	public void destroy() {
	}
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//判断如果是/app/路径的 url 检查是否登录
		HttpServletRequest hreq = (HttpServletRequest) request;    
    	String reqUri = hreq.getRequestURI();
        System.out.println("doFilter.reqUri==" + reqUri);
        if(reqUri != null && reqUri.indexOf("/app/") >= 0){
        	String loginKey = null;
			try {
				HttpServletResponse resp = (HttpServletResponse)response;
				loginKey = CookieUtil.getSessionID(hreq, resp);
				/*CfUserEntity user = (CfUserEntity)redisUtilTool.get(loginKey);
				if(user == null){
					response.getWriter().write("{\"success\":\"false\",\"content\":\"用户未登录或登录超时\"}");
					request.getRequestDispatcher("/apppage/main/login.jsp?code=-99").forward(request, response);
				}else{
					redisUtilTool.set(loginKey, user, Constant.APP_LOGIN_TIMEOUT);
					String roleCode = (String)redisUtilTool.get(loginKey + "roleCode");
					redisUtilTool.set(loginKey + "roleCode", roleCode, Constant.APP_LOGIN_TIMEOUT);
				}*/
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write("{\"success\":\"false\",\"msg\":\"用户未登录或登录超时\"}");
				request.getRequestDispatcher("/apppage/main/login.jsp?code=-99").forward(request, response);
			}
        	request.getRequestDispatcher("/notice.jsp").forward(request, response);
        }
        chain.doFilter(request, response);
	}
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		ServletContext context = fConfig.getServletContext();  
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);  
        redisUtilTool = (RedisUtilTool) ctx.getBean("redisUtilTool");  
	}
}
