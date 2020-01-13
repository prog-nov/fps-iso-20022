<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<form class="warp"  id="form" method="post">
	<div class="pt10"></div>
	<div class="bf4j-group">
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c2">
				<label><spring:message code="guide.add.menuName"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c10">
				<input type="hidden" name="menuId" id="menuId" value="${guider.menuId}"/>
				<input type="hidden" name="menuFlag" id="menuFlag" value="${guider.menuFlag}"/>
				<input name="menuName" id="menuName" value="${guider.menuName}" class="bf4j-readonly" readonly="readonly"/>
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c2">
				<label><spring:message code="guide.add.guideContent"/>:</label>
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-sc1">
				&nbsp;
			</div>
			<div class="bf4j-cell bf4j-sc23" >
				<textarea name="content" class="easyui-validatebox" style="width: 95%; height: 300px; visibility: hidden;"> 
						<c:out value="${guider.guideContent}"  escapeXml="false"></c:out>
				</textarea>
				<input type="hidden" name="guideContent" id="guideContent">
			</div>
		</div>
		<div class="bf4j-line" >
			<div class="bf4j-cell bf4j-c12 bf4j-center">
				<a class="bf4j-btn" href="javascript:void 0" title='<spring:message code="button.submit"/>' id="submit">
					<i class="bf4j-icon-btn-save"></i><spring:message code="button.submit"/></a> 
				<a class="bf4j-btn" href="javascript:void 0" title='<spring:message code="button.reset"/>' >
					<i class="bf4j-icon-btn-reset"></i><spring:message code="button.reset"/></a>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript" src='<c:url value="/"/>system/sysmanager/guide/add.js'></script>