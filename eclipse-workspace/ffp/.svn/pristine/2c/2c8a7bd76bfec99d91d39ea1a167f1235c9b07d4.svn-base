<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<form id="searchForm" method="post">
	<div class="bf4j-group">
		<div class="bf4j-group-content">
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c2">
					<label><spring:message code="news.add.msgTitle"/>：</label>
				</div>
				<div class="bf4j-cell bf4j-c4">
					<span class="bf4j-input"> 
						<input type="text" name="msgTitle" class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-c2">
					<label><spring:message code="news.add.msgType"/>：</label>
				</div>
				<div class="bf4j-cell bf4j-c4">
					<span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="dataKey:'BF_NEWS_MSG_TYPE',extra:'select'" name="msgType"/>
					</span>
				</div>
			</div>

			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c2">
					<label><spring:message code="news.search.sendDate"/>：</label>
				</div>
				<div class="bf4j-cell bf4j-c4">
					<span class="bf4j-input"> 
						<input type="text" class="easyui-datebox"  name="sendDate"  />
					</span>
				</div>
				<div class="bf4j-cell bf4j-c2">
					<label><spring:message code="news.add.msgLevel"/>：</label>
				</div>
				<div class="bf4j-cell bf4j-c4">
					<span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="dataKey:'BF_NEWS_MSG_LEVEL',extra:'select'" name="msgLevel"/>
					</span>
				</div>
			</div>
		</div>
	</div>
	<div class="bf4j-group Action">
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c12 bf4j-center">
				<a class="bf4j-btn SearchBtn" title='<spring:message code="button.search"/>'>
					<i class="bf4j-icon-btn-search"></i>
					<spring:message code="button.search"/>
				</a>
			</div>
		</div>
	</div>
</form>