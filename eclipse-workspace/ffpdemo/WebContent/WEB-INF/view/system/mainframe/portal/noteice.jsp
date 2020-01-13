<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<div class="bf4j-warp"  >
	<div class="bf4j-pt10" ></div>
	<div class="bf4j-group" >
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label>公告标题：</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<c:out value="${noteice.msgTitle }"></c:out>
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label>公告内容：</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<c:out value="${noteice.msgContent }"></c:out>
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label>发出日期：</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<c:out value="${noteice.sendDate }"></c:out>
			</div>
		</div>
	</div>
</div>