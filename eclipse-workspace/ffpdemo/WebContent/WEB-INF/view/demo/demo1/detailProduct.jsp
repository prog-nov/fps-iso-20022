<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<form id="detailForm" method="post" class="easyui-form">
	<div class="bf4j-pt10"></div>
	<div class="bf4j-group">
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="demo.product.code"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8"  name="id" id="id" >
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="demo.product.name"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8" name="name" id="name" >
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="demo.product.type"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8" name="type" id="type" >
			</div>
		</div>

		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="demo.product.oldprice"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8" name="oldPrice" id="oldPrice" >
			</div>
		</div>

		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="demo.product.nowprice"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8"   name="nowPrice" id="nowPrice" >
			</div>
		</div>

		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="demo.product.count"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8" name="count" id="count">
			</div>
		</div>

		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="demo.product.date"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8" name="date" id="date" >
			</div>
		</div>

		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="demo.product.address"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8" name="addr" id="addr" >
			</div>
		</div>

		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="demo.product.remark"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8" name="remark" id="remark" >
			</div>
		</div>
	</div>

</form>