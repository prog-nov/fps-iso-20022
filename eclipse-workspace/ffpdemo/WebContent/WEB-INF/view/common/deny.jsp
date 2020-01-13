<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<title>授权拒绝页面</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="padding-top:10px;">
		<div data-options="region:'center',border:false">
			<div class="bf4j-warp">
			<!-- 导航页面 -->
			<jsp:include page="/WEB-INF/view/common/leader/leaderView.jsp"></jsp:include>
			<div class="bf4j-group">
				<c:if test="${not empty authorizationInfo}">
					<c:choose>
						<c:when test="${'BF040001'== authorizationInfo.code}">
							<div class="bf4j-line">
								<div class="bf4j-cell bf4j-c12 bf4j-center">
									对不起，没有找到您访问的功能，URL: <c:out value="${authorizationInfo.permission.permUrl }"></c:out>
								</div>
							</div>
						</c:when>
						<c:when test="${'BF040002'== authorizationInfo.code}">
							<div class="bf4j-line">
								<div class="bf4j-cell bf4j-c12 bf4j-center">
									对不起，会话已经超时，请重新 <a href="#" class="gotoLogin">登陆</a>
								</div>
							</div>
						</c:when>
						<c:when test="${'BF040003'== authorizationInfo.code}">
							<div class="bf4j-line">
								<div class="bf4j-cell bf4j-c12 bf4j-center">
									对不起，您没有访问权限，URL: <c:out value="${authorizationInfo.permission.permUrl }"></c:out>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="bf4j-line">
								<div class="bf4j-cell bf4j-c12 bf4j-center">
									Code: <c:out value="${authorizationInfo.code }"></c:out>
								</div>
							</div>
							<div class="bf4j-line">
								<div class="bf4j-cell bf4j-c12 bf4j-center">
									Message: <c:out value="${authorizationInfo.message }"></c:out>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</c:if>
				
			</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		require(['common'], function() {
			$(function() {
				$b.Msg.closeProgress();
				$('a.gotoLogin').click(function(){
					top.location.href = server_consts.root + '/index';
				});
			});
		});
	</script>
</body>
</html>
