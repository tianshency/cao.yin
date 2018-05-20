<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ page import="com.zml.common.Constant" %>

<c:set var="ctx" value="${pageContext.request.contextPath}/" />
<c:set var="ctxContents" value="${pageContext.request.contextPath}/static/" />

<%--  <c:set var="ctx" value="https://www.zhuminle.com" />
<c:set var="ctxContents" value="https://www.static.zhuminle.com/static" /> --%>
<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/" ;
String picStaticPath = Constant.STATIC_URL;
%>

<c:set var="staticUrl" value="<%=Constant.STATIC_URL%>" />
<script type="text/javascript">
    var basePath = "<%=basePath%>";//静态路径
    var activePath="<%=basePath%>";//动态路径
    var userId = "${sessionScope.zmlUser.id}";
    var pageSize=20;//每页显示条数
    var curPage=1;//当前页
    var errorImg = basePath+"static/img/noImg.jpg";
    var picturePath = "<%=picStaticPath %>";
</script>