<%@ page language="java" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ include file="../taglib.jsp"%>
<html>
<head>
<jsp:include page="../includer/requirejs-includer.jsp"></jsp:include>
<script type="text/javascript">
	require(['beneform4j'], function($b) {
		$(function() {
			$b.Msg.closeProgress();
			$b.Msg.error($b.Base.i18n('error.requestError'));
		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>400 Page</title>
</head>
<body>
</body>
</html>