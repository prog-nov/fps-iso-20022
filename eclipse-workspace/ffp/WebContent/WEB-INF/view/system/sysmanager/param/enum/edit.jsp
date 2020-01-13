<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<form id="updateForm" method="post" style="width: 97%; text-align: center;">
	<div class="bf4j-group">
		<div class="bf4j-group-content">
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c4">
					<label><spring:message code="system.sysmanager.param.enum.paramCode" /></label>
				</div>
				<div class="bf4j-cell bf4j-c7">
					<span class="bf4j-input"> 
						<input type="text" name="paramCode" class="easyui-validatebox bf4j-readonly" style="width: 90%" 
							data-options="required:true, validType:'length[4,32]'" readonly="readonly"/>
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c4">
					<label><spring:message code="system.sysmanager.param.enum.paramName" /></label>
				</div>
				<div class="bf4j-cell bf4j-c7">
					<span class="bf4j-input"> 
						<input type="text" name="paramName" class="easyui-validatebox" style="width: 90%" 
							data-options="required:true" />
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c4">
					<label><spring:message code="system.sysmanager.param.enum.paramGroup" /></label>
				</div>
				<div class="bf4j-cell bf4j-c7">
					<span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="dataKey:'PARAM_GROUP',extra:'select'" data-options="required:true"  name="paramGroup"  />
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c4">
					<label><spring:message code="system.sysmanager.param.enum.paramAttr" /></label>
				</div>
				<div class="bf4j-cell bf4j-c7">
					<span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="dataKey:'ENUM_PARAM_TYPE',extra:'select'" data-options="required:true"  name="paramAttr" />
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c4">
					<label><spring:message code="system.sysmanager.param.enum.seqNo" /></label>
				</div>
				<div class="bf4j-cell bf4j-c7">
					<span class="bf4j-input"> 
						<input type="text" name="seqno" class="easyui-validatebox" style="width: 90%" 
							data-options="required:true" />
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c4">
					<label><spring:message code="system.sysmanager.param.enum.des" /></label>
				</div>
				<div class="bf4j-cell bf4j-c7">
					<span class="bf4j-input"> 
						<input type="text" name="des" class="easyui-validatebox" style="width: 90%"/>
					</span>
				</div>
			</div>
		</div>
	</div>
</form>
