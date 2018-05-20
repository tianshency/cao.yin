package com.jce.framework.core.interceptors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import com.jce.framework.core.constant.Globals;
import com.jce.framework.core.extend.hqlsearch.SysContextSqlConvert;
import com.jce.framework.core.util.ContextHolderUtils;
import com.jce.framework.core.util.JeecgDataAutorUtils;
import com.jce.framework.core.util.ResourceUtil;
import com.jce.framework.core.util.oConvertUtils;
import com.jce.framework.web.system.manager.ClientManager;
import com.jce.framework.web.system.pojo.base.Client;
import com.jce.framework.web.system.pojo.base.TSDataRule;
import com.jce.framework.web.system.pojo.base.TSFunction;
import com.jce.framework.web.system.pojo.base.TSOperation;
import com.jce.framework.web.system.pojo.base.TSUser;
import com.jce.framework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.zml.cache.RedisUtilTool;
import com.zml.common.Constant;
import com.zml.util.CookieUtil;


/**
 * 权限拦截器
 */
public class AuthInterceptor implements HandlerInterceptor {
	 
	private static final Logger logger = Logger.getLogger(AuthInterceptor.class);
	private SystemService systemService;
	private List<String> excludeUrls;
	private static List<TSFunction> functionList;
	
	private static boolean refreshFlag = true;
	//缓存工具类
	@Autowired
	private RedisUtilTool redisUtilTool;
	

	public RedisUtilTool getRedisUtilTool() {
		return redisUtilTool;
	}

	public void setRedisUtilTool(RedisUtilTool redisUtilTool) {
		this.redisUtilTool = redisUtilTool;
	}
	
	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	public SystemService getSystemService() {
		return systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * 在controller后拦截
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在controller前拦截
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		String requestPath = ResourceUtil.getRequestPath(request);// 用户访问的资源地址
		logger.error("AuthInterceptor.preHandle.requestPath=" + requestPath);
		//刷新缓存
		/*if(refreshFlag){
			//查询公告 信息
	    	List<Map<String,Object>> headlinesList = systemService.headlinesList();
	    	redisUtilTool.set("headlinesList", headlinesList);
			refreshFlag = false;
		}*/
		//redisUtilTool.set("testA", "" + System.currentTimeMillis());
		//String testA = (String)redisUtilTool.get("testA");
		String userId = request.getParameter("userId");
		if(userId != null && !"".equals(userId)){
			if(!redisUtilTool.exists(userId)){
				logger.error("AuthInterceptor.preHandle. is not login");
				return false;
			}
		}else{
			logger.error("AuthInterceptor.preHandle.userId is null " );
			return false;
		}
		StringBuffer bfParams = new StringBuffer();
        Enumeration<String> paraNames = null;
        paraNames = request.getParameterNames();
        String key;
        String value;
        while (paraNames.hasMoreElements()) {
            key = paraNames.nextElement();
            value = request.getParameter(key);
            bfParams.append(key).append("=").append(value).append("&");
        }
        if (StringUtils.isBlank(bfParams)) {
            bfParams.append(request.getQueryString());
        }
		String requestInfo = getRequestInfo(request);
        String reqUri = request.getRequestURI();
        String strMessage = String
                .format(">>[请求路径]:%s,[请求参数]:%s", reqUri , bfParams.toString());
        logger.error(strMessage);
		/*HttpSession session = ContextHolderUtils.getSession();
		Client client = ClientManager.getInstance().getClient(session.getId());
		if(client == null){ 
			client = ClientManager.getInstance().getClient(
					request.getParameter("sessionId"));
		}*/
		
		if (excludeUrls.contains(requestPath)) {
			//如果该请求不在拦截范围内，直接返回true
			return true;
		//APP访问 直接过
		}else if(reqUri != null && reqUri.indexOf("/app/") >= 0){
			return true;
		}else {
			
			return true;
		}
	}
	
	/**
	 * 判断用户是否有菜单访问权限
	 * @param request
	 * @return
	 */
	private boolean hasMenuAuth(HttpServletRequest request){
		String requestPath = ResourceUtil.getRequestPath(request);// 用户访问的资源地址
		// 是否是功能表中管理的url
		boolean bMgrUrl = false;
		if (functionList == null) {
//			functionList = systemService.loadAll(TSFunction.class);

			functionList = systemService.findHql("from TSFunction where functionType = ? ", (short)0);

		}
		for (TSFunction function : functionList) {
			if (function.getFunctionUrl() != null && function.getFunctionUrl().startsWith(requestPath)) {
				bMgrUrl = true;
				break;
			}
		}
		if (!bMgrUrl) {
			return true;
		}
		 
		String funcid=oConvertUtils.getString(request.getParameter("clickFunctionId"));
		if(!bMgrUrl && (requestPath.indexOf("loginController.do")!=-1||funcid.length()==0)){
			return true;
		} 
		TSUser currLoginUser = ClientManager.getInstance().getClient(ContextHolderUtils.getSession().getId()).getUser();
        String userid = currLoginUser.getId();
		//requestPath=requestPath.substring(0, requestPath.indexOf("?")+1);
		String sql = "SELECT DISTINCT f.id FROM t_s_function f,t_s_role_function  rf,t_s_role_user ru " +
					" WHERE f.id=rf.functionid AND rf.roleid=ru.roleid AND " +
					"ru.userid='"+userid+"' AND f.functionurl like '"+requestPath+"%'";
		List list = this.systemService.findListbySql(sql);
		if(list.size()==0){

            String orgId = currLoginUser.getCurrentDepart().getId();

            String functionOfOrgSql = "SELECT DISTINCT f.id from t_s_function f, t_s_role_function rf, t_s_role_org ro  " +
                    "WHERE f.ID=rf.functionid AND rf.roleid=ro.role_id " +
                    "AND ro.org_id='" +orgId+ "' AND f.functionurl like '"+requestPath+"%'";
            List functionOfOrgList = this.systemService.findListbySql(functionOfOrgSql);
			return functionOfOrgList.size() > 0;

        }else{
			return true;
		}
	}
	/**
	 * 转发
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "forword")
	public ModelAndView forword(HttpServletRequest request) {
		return new ModelAndView(new RedirectView("loginController.do?login"));
	}

	private void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("webpage/login/timeout.jsp").forward(request, response);
	}
	
	private String getRequestInfo(HttpServletRequest request){
    	String Agent = request.getHeader("User-Agent");
    	/*StringTokenizer st = new StringTokenizer(Agent,";");
    	st.nextToken();
    	//得到用户的浏览器名 
    	String userbrowser = st.nextToken();
    	//得到用户的操作系统名
    	String useros = st.nextToken();
       */
    	String header = request.getHeader("User-agent");//返回客户端浏览器的版本号、类型
    	String method= request.getMethod();//获得客户端向服务器端传送数据的方法有GET、POST、PUT等类型
    	String requestURI= request.getRequestURI();//获得发出请求字符串的客户端地址
    	String servletPath= request.getServletPath();//获得客户端所请求的脚本文件的文件路径
    	String serverName= request.getServerName();//获得服务器的名字
    	int serverPort= request.getServerPort();//获得服务器的端口号
    	String remoteAddr= request.getRemoteAddr();//获得客户端的IP地址
    	String remoteHost= request.getRemoteHost();//获得客户端电脑的名字，若失败，则返回客户端电脑的IP地址
    	String protocol= request.getProtocol();//
    	Enumeration headerNames= request.getHeaderNames();//返回所有request header的名字，结果集是一个Enumeration（枚举）类的实例
    	//String = request.getHeaders(String name);//返回指定名字的request header的所有值，结果集是一个
    	String rsStr =Agent+"&"+header+"&"+method+"&"+requestURI+"&"+servletPath+"&"+serverName+"&"+serverPort+"&"+remoteAddr+"&"+remoteHost+"&"+protocol;
    	return rsStr;
    }
}
