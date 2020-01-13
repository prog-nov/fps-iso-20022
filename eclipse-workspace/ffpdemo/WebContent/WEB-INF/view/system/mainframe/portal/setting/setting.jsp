<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="portal.setting.title"/></title>
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="padding-top:10px;">
		<div data-options="region:'center',border:false">
			<div class="bf4j-warp" >
			<!-- 导航页面 -->
			<jsp:include page="/WEB-INF/view/common/leader/leaderView.jsp"></jsp:include>
			<!-- 查询页面 -->
			<form id="settingForm" class="easyui-form" method="post">
				<div class="bf4j-group">
					<div class="bf4j-group-content">
						<c:forEach  items="${list}" var="item" varStatus="status">
							<div class="bf4j-line">
								<div class="bf4j-cell bf4j-c2">
									<label>${item.paramName}：</label>
								</div>
								<div class="bf4j-cell bf4j-c4">
									<span class="bf4j-input">
										<c:choose>
											<c:when test="${item.valueRule == 'DICT'}">
												<input value="${item.paramValue}" id="${item.paramCode}" name="paramValue"  class="easyui-combobox" style="width: 100%" />
												<input value="${item.paramCode}" type="hidden" name="paramCode" />
											</c:when>
											<c:otherwise>
												<input value="${item.paramValue}" type="text" name="paramValue"  class="easyui-validatebox" data-options="required:true" style="width: 90%" />
												<input value="${item.paramCode}" type="hidden" name="paramCode" />
											</c:otherwise>
										</c:choose>
								</div>
							</div>
						</c:forEach>
						</div>
					</div>
				</div>
				<div class="bf4j-group newsBtn">
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c12 bf4j-center">
							<a class="bf4j-btn btn-save" title="<spring:message code="button.submit"/>"><i class="bf4j-icon-btn-save"></i><spring:message code="button.submit"/></a>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="<b:path url='{root}/system/mainframe/portal/setting/setting{min}.js'/>"></script>
</body>
</html>
