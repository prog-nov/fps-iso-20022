<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<form id="editForm" method="post" class="easyui-form">
	<input type="hidden" name="msgId" value='<c:out value="${news.msgId}"></c:out>'>
	<div class="pt10"></div>
	<div class="bf4j-group">
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.msgTitle"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span class="bf4j-input"> 
					<input type="text" name="msgTitle"  class="easyui-validatebox" style="width: 90%" 
						value='<c:out value="${news.msgTitle}"></c:out>' 
						data-options="required:true,  
							missingMessage:'<spring:message code="news.add.msgTitle.check.required"/>',
	                        invalidMessage:'<spring:message code="news.add.msgTitle.check.invalidMessage"/>',
	                        validType:'length[1,100]'" />
				</span>
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.msgType"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span class="bf4j-input"> 
					<input class="bf4j-combo" combo-options="dataKey:'BF_NEWS_MSG_TYPE',extra:'select'"  
						value='<c:out value="${news.msgType}"></c:out>'
						data-options="width:'90%'" name="msgType" id="msgType"/>
				</span>
			</div>
		</div>

		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.msgLevel"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span class="bf4j-input"> 
					<input class="bf4j-combo" combo-options="dataKey:'BF_NEWS_MSG_LEVEL',extra:'select'" 
						value='<c:out value="${news.msgLevel}"></c:out>'
						data-options="width:'90%'" name="msgLevel"/>
				</span>
				</span>
			</div>
		</div>
		<div class="bf4j-line effectiveFlag">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.effectiveFlag"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span class="bf4j-input"> 
					<input class="bf4j-combo" combo-options="dataKey:'BF_NEWS_EFFECTIVE_DAY',extra:'select'" 
						value='<c:out value="${news.effectiveFlag}"></c:out>'
						data-options="width:'90%'" name="effectiveFlag" id="effectiveFlag"/>
				</span>
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.msgContent"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span class="bf4j-input"> 
					<textarea type="text" name="msgContent" class="easyui-validatebox" style="width: 90%;height:100px" 
						 data-options="required:true,  
						 	missingMessage:'<spring:message code="news.add.msgContent.check.required"/>',
	                        invalidMessage:'<spring:message code="news.add.msgContent.check.invalidMessage"/>',
	                        validType:'length[1,500]'" ><c:out value="${news.msgContent}"></c:out></textarea>
				</span>
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.msgIcon"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span class="bf4j-input"> 
					<input type="text" class="easyui-validatebox" style="width: 90%" 
						name="msgIcon"  value='<c:out value="${news.msgIcon}"></c:out>'/>
				</span>
			</div>
		</div>

		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.targetUrl"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span class="bf4j-input"> 
					<input type="text" class="easyui-validatebox" style="width: 90%"  name="targetUrl"  
						value='<c:out value="${news.targetUrl}"></c:out>'
						data-options="invalidMessage:'<spring:message code="news.add.targetUrl.check.invalidMessage"/>',
							validType:'length[1,200]'" />
				</span>
			</div>
		</div>
		<div class="bf4j-line recvNet">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.recvNet"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span class="bf4j-input"> 
					<input class="bf4j-organ" organ-options="synch:true" name="recvNet"  
						value='<c:out value="${news.recvNet}"></c:out>'/>
				</span>
			</div>
		</div>

		<div class="bf4j-line recvOper">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.recvOper"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span class="bf4j-input"> 
					<input type="text" class="easyui-validatebox" style="width: 90%" 
						name="recvOper"  value='<c:out value="${news.recvOper}"></c:out>'
						data-options="invalidMessage:'<spring:message code="news.add.recvOper.check.invalidMessage"/>',
						validType:'length[1,20]'" />
				</span>
			</div>
		</div>
		<div class="bf4j-line effectiveDayCnt">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.effectiveDayCnt"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span class="bf4j-input"> 
					<input type="text" name="effectiveDayCnt" class="easyui-numberspinner" style="width:80px;" 
						data-options="min:1,max:365" value='<c:out value="${news.effectiveDayCnt}"></c:out>'/>
				</span>
			</div>
		</div>
		<div class="bf4j-line effectiveStartDate">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.effectiveStartDate"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span class="bf4j-input"> 
					<input type="text" name="effectiveStartDate" id="effectiveStartDate" class="easyui-datetimebox" style="width:150px"/>
				</span>
			</div>
		</div>
		<div class="bf4j-line effectiveEndDate">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="news.add.effectiveEndDate"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span class="bf4j-input"> 
					<input type="text" name="effectiveEndDate" id="effectiveEndDate" class="easyui-datetimebox" style="width:150px"/>
				</span>
			</div>
		</div>
	</div>
</form>