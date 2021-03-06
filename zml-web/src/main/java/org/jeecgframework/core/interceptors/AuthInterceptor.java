package org.jeecgframework.core.interceptors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.SysContextSqlConvert;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.JeecgDataAutorUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.Client;
import org.jeecgframework.web.system.pojo.base.TSDataRule;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSOperation;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.zml.cache.RedisUtilTool;
import com.zml.common.Constant;
import com.zml.user.entity.CfUserEntity;
import com.zml.util.CookieUtil;


/**
 * 权限拦截器
 */
public class AuthInterceptor implements HandlerInterceptor {
	 
	private static final Logger logger = Logger.getLogger(AuthInterceptor.class);
	private SystemService systemService;
	private List<String> excludeUrls;
	private static List<TSFunction> functionList;
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
		/*redisUtilTool.set("testA", "" + System.currentTimeMillis());
		String testA = (String)redisUtilTool.get("testA");
		System.out.println("testA====" + testA);*/
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
		HttpSession session = ContextHolderUtils.getSession();
		Client client = ClientManager.getInstance().getClient(session.getId());
		if(client == null){ 
			client = ClientManager.getInstance().getClient(
					request.getParameter("sessionId"));
		}
		
		if (excludeUrls.contains(requestPath)) {
			//如果该请求不在拦截范围内，直接返回true
			return true;
		//APP访问 直接过
		}else if(reqUri != null && reqUri.indexOf("/app/") >= 0){
			return true;
		}else {
			if (client != null && client.getUser()!=null ) {
				if((!hasMenuAuth(request)) && !client.getUser().getUserName().equals("admin")){
					 response.sendRedirect("loginController.do?noAuth");
					//request.getRequestDispatcher("webpage/common/noAuth.jsp").forward(request, response);
					return false;
				} 
				//String functionId=oConvertUtils.getString(request.getParameter("clickFunctionId"));
				String functionId="";

				//onlinecoding的访问地址有规律可循，数据权限链接篡改
				if(requestPath.equals("cgAutoListController.do?datagrid")) {
					requestPath += "&configId=" +  request.getParameter("configId");
				}
				if(requestPath.equals("cgAutoListController.do?list")) {
					requestPath += "&id=" +  request.getParameter("id");
				}
				if(requestPath.equals("cgFormBuildController.do?ftlForm")) {
					requestPath += "&tableName=" +  request.getParameter("tableName");
				}

				if(requestPath.equals("cgFormBuildController.do?goAddFtlForm")) {
					requestPath += "&tableName=" +  request.getParameter("tableName");
				}
				if(requestPath.equals("cgFormBuildController.do?goUpdateFtlForm")) {
					requestPath += "&tableName=" +  request.getParameter("tableName");
				}
				if(requestPath.equals("cgFormBuildController.do?goDatilFtlForm")) {
					requestPath += "&tableName=" +  request.getParameter("tableName");
				}
				//这个地方用全匹配？应该是模糊查询吧
				//TODO
				String uri= request.getRequestURI().substring(request.getContextPath().length() + 1);
				String realRequestPath = null;
				if(uri.endsWith(".do")||uri.endsWith(".action")){
					realRequestPath=requestPath;
				}else {
					realRequestPath=uri;
				}
				List<TSFunction> functions = systemService.findByProperty(TSFunction.class, "functionUrl", realRequestPath);

				if (functions.size()>0){
					functionId = functions.get(0).getId();
				}
				
				//Step.1 第一部分处理页面表单和列表的页面控件权限（页面表单字段+页面按钮等控件）
				if(!oConvertUtils.isEmpty(functionId)){
					//获取菜单对应的页面控制权限（包括表单字段和操作按钮）
					Set<String> operationCodes = systemService.getOperationCodesByUserIdAndFunctionId(client.getUser().getId(), functionId);
					request.setAttribute(Globals.OPERATIONCODES, operationCodes);
				}
				if(!oConvertUtils.isEmpty(functionId)){

					//List<String> allOperation=this.systemService.findListbySql("SELECT operationcode FROM t_s_operation  WHERE functionid='"+functionId+"'"); 
					List<TSOperation> allOperation=this.systemService.findByProperty(TSOperation.class, "TSFunction.id", functionId);
					
					List<TSOperation> newall = new ArrayList<TSOperation>();
					if(allOperation.size()>0){
						for(TSOperation s:allOperation){ 
						    //s=s.replaceAll(" ", "");
							newall.add(s); 
						}
						//---author:jg_xugj----start-----date:20151210--------for：#781  【oracle兼容】兼容问题fun.operation!='' 在oracle 数据下不正确
						String hasOperSql="SELECT operation FROM t_s_role_function fun, t_s_role_user role WHERE  " +
							"fun.functionid='"+functionId+"' AND fun.operation is not null  AND fun.roleid=role.roleid AND role.userid='"+client.getUser().getId()+"' ";
						List<String> hasOperList = this.systemService.findListbySql(hasOperSql); 
					    for(String operationIds:hasOperList){
							    for(String operationId:operationIds.split(",")){
							    	operationId=operationId.replaceAll(" ", "");
							        TSOperation operation =  new TSOperation();
							        operation.setId(operationId);
							    	newall.remove(operation);
							    } 
						} 
					}
					request.setAttribute(Globals.NOAUTO_OPERATIONCODES, newall);
					
					 //Step.2  第二部分处理列表数据级权限
					 //小川 -- 菜单数据规则集合(数据权限)
					 List<TSDataRule> MENU_DATA_AUTHOR_RULES = new ArrayList<TSDataRule>(); 
					 //小川 -- 菜单数据规则sql(数据权限)
					 String MENU_DATA_AUTHOR_RULE_SQL="";

					
				 	//数据权限规则的查询
				 	//查询所有的当前这个用户所对应的角色和菜单的datarule的数据规则id
					Set<String> dataruleCodes = systemService.getOperationCodesByUserIdAndDataId(client.getUser().getId(), functionId);
					request.setAttribute("dataRulecodes", dataruleCodes);
					for (String dataRuleId : dataruleCodes) {
						TSDataRule dataRule = systemService.getEntity(TSDataRule.class, dataRuleId);
						    MENU_DATA_AUTHOR_RULES.add(dataRule);
							MENU_DATA_AUTHOR_RULE_SQL += SysContextSqlConvert.setSqlModel(dataRule);
					
					}
					 JeecgDataAutorUtils.installDataSearchConditon(request, MENU_DATA_AUTHOR_RULES);//菜单数据规则集合
					 JeecgDataAutorUtils.installDataSearchConditon(request, MENU_DATA_AUTHOR_RULE_SQL);//菜单数据规则sql

				}
				return true;
			} else {
				//forword(request);
				forward(request, response);
				return false;
			}

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
