<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<div class="easyui-layout" data-options="fit:true"
	style="width: 100%; height: 400px">
	<form id="detailForm" class="easyui-form" method="post">
		<div data-options="region:'center'" style="width: 100%; height: 400px">
			<div class="easyui-tabs"
				data-options="plain:true,border:false,fit:true"
				style="width: 100%; border: none; height: 100%;">
				<c:forEach items="${data}" var="k" varStatus="status">
					<div title='<spring:message code="${k.key}" />'>
						<c:choose>
							<c:when
								test="${k.key == 'ffp.cashmanagement.transaction.actionList'}">
								<div style="text-align: center">
									<table border="1" style="width: 100%; margin: auto">
										<thead>
											<c:forEach items="${k.value}" var="item"
												varStatus="statusOut">
												<c:if test="${statusOut.first}">
													<tr>
														<c:forEach items="${item}" var="dataLayout"
															varStatus="statusInner">
															<td><label>${dataLayout.labelStr}</label></td>
														</c:forEach>
													</tr>
												</c:if>
												<tr>
													<c:forEach items="${item}" var="dataLayout"
														varStatus="statusInner">
														<div class="bf4j-cell bf4j-c7">
															<td><label>${dataLayout.valueStr}</label></td>
														</div>
													</c:forEach>
												</tr>
											</c:forEach>
										</thead>
									</table>
								</div>
							</c:when>
							<c:when
								test="${k.key == 'ffp.cashmanagement.transaction.bussiData'}">
								<div style="text-align: center">
									<table border="1" style="width: 80%; margin: auto">
										<thead>
											<c:forEach items="${k.value}" var="item" varStatus="status">
												<tr>
													<td>${item.labelStr}</td>
													<td>${item.valueStr}</td>
												</tr>
											</c:forEach>
										</thead>
									</table>
								</div>
							</c:when>
							<c:when test="${k.key == 'ffp.cashmanagement.transaction.txJnl'}">
								<div style="text-align: center">
									<table border="1" style="width: 80%; margin: auto">
										<thead>
											<c:forEach items="${k.value}" var="item" varStatus="status">
												<tr>
													<td>${item.labelStr}</td>
													<td>${item.valueStr}</td>
												</tr>
											</c:forEach>
										</thead>
									</table>
								</div>
							</c:when>
						</c:choose>
					</div>
				</c:forEach>
			</div>
		</div>
	</form>
</div>
