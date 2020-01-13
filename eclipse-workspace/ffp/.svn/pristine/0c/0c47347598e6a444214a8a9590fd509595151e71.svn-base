<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<title><spring:message code="system.sysmanager.param.singleParam.title.singleParamMain"/></title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="padding-top:10px;">
	<div data-options="region:'center',border:false">
			<div class="bf4j-warp" >
			<!-- 导航页面 -->
			<jsp:include page="/WEB-INF/view/common/leader/leaderView.jsp"></jsp:include>
			<!-- 查询页面 -->
			<form id="settingForm" class="easyui-form" method="post">
				<div data-options="region:'center'" style="width:100%;height:400px">
			  		<div class="easyui-tabs" data-options="plain:true,border:false,fit:true" style="width:100%;border:none;height: 100%;">
			  			<c:forEach  items="${data}" var="k" varStatus="status">
				  			<div title="${k.key}">   
				  				<c:forEach  items="${k.value}" var="item" varStatus="status">
									<div class="bf4j-line">
										<div class="bf4j-cell bf4j-c5">
											<label>${item.paramName}</label>
										</div>
										<div class="bf4j-cell bf4j-c7">
											<span class="bf4j-input">
												<c:choose>
													<c:when test="${item.valueRule == 'DICT'}">
														<input value="${item.paramValue}" id="${item.paramCode}" name="paramValue" class="easyui-combobox"  style="width: 100%" />
														<input value="${item.paramCode}" type="hidden" name="paramCode" />
													</c:when>
													<c:otherwise>
														<input value="${item.paramValue}" type="text" name="paramValue"  class="easyui-validatebox" data-options="required:true" style="width: 90%" />
														<input value="${item.paramCode}" type="hidden" name="paramCode" />
													</c:otherwise>
												</c:choose>
											</span>
										</div>
									</div>
							</c:forEach>
						    </div>
					    </c:forEach>
			  		</div>
 				</div>

				<div class="bf4j-group newsBtn">
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c12 bf4j-center">
							<a class="bf4j-btn bf4j-btn-save" title='<spring:message code="button.submit"/>'><i class="bf4j-icon-btn-save"></i><spring:message code="button.submit"/></a>
						</div>
					</div>
				</div>
			</form>
			</div>
			</div>
			
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/system/sysmanager/param/single/single-param{min}.js"></script>
</body>
</html>
