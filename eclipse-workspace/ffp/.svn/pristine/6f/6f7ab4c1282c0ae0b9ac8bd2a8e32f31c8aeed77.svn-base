<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.forms.beneform4j.webapp.common.utils.UIHolder" %>
<script type="text/javascript">
/**
 * 从服务器获取的常量
 */
var _isDebug = <%=UIHolder.isDebug()%>;
var server_consts = {
	root     : '${pageContext.request.contextPath}',  // 项目的根路径
	locale   : '<%=UIHolder.getLocale(request)%>',//'zh_CN', // 和当前用户相关的本地化对象字符串
	theme    : '<%=UIHolder.getTheme(request)%>', //'boc-red', // 和当前用户相关的主题样式
	min	     : (_isDebug ? '' : '.min'),              // 和环境相关的最小化JS文件名
	debug    : (_isDebug ? '-debug' : ''),            // 和环境相关的调试JS文件名
	comments : (_isDebug ? '-with-comments' : ''),    // 和环境相关的包含注释的JS文件名
	maxTabsNum : parseInt('7',10)					  // 可打开的标签页数量限制
};
var require = {urlArgs: 'v=1.0.0'};
</script>
<script src="${pageContext.request.contextPath}/resources/config.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/third/requirejs/require.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/beneform4j/js/require-config.js" type="text/javascript"></script>
