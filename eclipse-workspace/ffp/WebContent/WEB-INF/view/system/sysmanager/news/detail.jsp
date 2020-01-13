<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
	<div class="pt10"></div>
	<div class="bf4j-group">
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.msgId"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span><c:out value="${news.msgId}"></c:out></span>
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.msgTitle"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span><c:out value="${news.msgTitle}"></c:out></span>
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.msgType"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span><c:out value="${news.msgTypeName}"></c:out></span>
			</div>
		</div>

		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.msgLevel"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span><c:out value="${news.msgLevelName}"></c:out></span>
			</div>
		</div>

		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.msgContent"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span><c:out value="${news.msgContent}"></c:out></span>
			</div>
		</div>

		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.recvNet"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span><c:out value="${news.recvNet}"></c:out></span>
			</div>
		</div>

		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.recvOper"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span><c:out value="${news.recvOper}"></c:out></span>
			</div>
		</div>

		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.msgIcon"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span><c:out value="${news.msgIcon}"></c:out></span>
			</div>
		</div>

		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.targetUrl"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span ><c:out value="${news.targetUrl}"></c:out></span>
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.effectiveFlag"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span ><c:out value="${news.effectiveFlagName}"></c:out></span>
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.effectiveDayCnt"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span ><c:out value="${news.effectiveDayCnt}"></c:out></span>
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.effectiveStartDate"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span ><c:out value="${news.effectiveStartDate}"></c:out></span>
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.effectiveStartTime"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span ><c:out value="${news.effectiveStartTime}"></c:out></span>
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.effectiveEndDate"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span ><c:out value="${news.effectiveEndDate}"></c:out></span>
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.effectiveEndTime"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span ><c:out value="${news.effectiveEndTime}"></c:out></span>
			</div>
		</div>
	</div>