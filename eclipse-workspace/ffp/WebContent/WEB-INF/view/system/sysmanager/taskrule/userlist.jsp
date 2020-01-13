<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<div class="bf4j-warp" >
<!-- 查询页面 -->
<jsp:include page="./usersearch.jsp"></jsp:include>
<!-- 列表展示 -->
<div class="bf4j-group" >
	<table id="userDataList" class="bf4j-grid-auto"></table>
</div>
<script type="text/javascript" src='<c:url value="/"/>system/sysmanager/taskrule/user.js'></script>
</div>
