<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<div class="bf4j-warp"  >
	<div class="bf4j-pt10" ></div>
	<div class="bf4j-group" >
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c3">
				<label>标题：</label>
			</div>
			<div class="bf4j-cell bf4j-c9">
				<c:out value="${news.msgTitle }"/> 
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c3">
				<label>内容：</label>
			</div>
			<div class="bf4j-cell bf4j-c9">
				<c:out value="${news.msgContent }"/> 
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c3">
				<label>发送人：</label>
			</div>
			<div class="bf4j-cell bf4j-c9">
				<c:out value="${news.sendOper }"/> 
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c3">
				<label>发出日期：</label>
			</div>
			<div class="bf4j-cell bf4j-c9">
				<c:out value="${news.sendDate }"/> <c:out value="${news.sendTime }"/> 
			</div>
		</div>
	</div>
</div>