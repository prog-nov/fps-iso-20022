<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<form id="addForm" method="post" enctype="multipart/form-data" style="width: 97%; text-align: center;">
<div class="bf4j-group">
	<div class="pt10"></div>
	<div class="bf4j-group">
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="document.list.docName"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c2">
				<span class="bf4j-input"> 
					<div id="pick"><a class="bf4j-btn btn-add"  title="选择文件"><i class="bf4j-icon-btn-file"></i>选择文件</a></div>
					<span id="fileList"></span>
				</span>
			</div>
			<div class="bf4j-cell bf4j-c4">
				<a class="bf4j-btn btn-add" id="upload"  title="上传">上传</a>
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4" >
				<label><spring:message code="document.list.docType"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8"  style="text-align: left;">
				<span class="bf4j-input"> 
					<input class="bf4j-combo" combo-options="dataKey:'BF_DOC_TYPE',extra:'select'" data-options="required:true"  name="docType"/>
				</span>
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="document.list.docState"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8" style="text-align: left;">
				<span class="bf4j-input"> 
					<input class="bf4j-combo" combo-options="dataKey:'BF_DOC_STATUS',extra:'select'" data-options="required:true"  name="docState"/>
				</span>
			</div>
		</div>
	</div>
</div>
</form>
