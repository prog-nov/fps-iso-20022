<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<div class="easyui-layout" data-options="fit:true" style="width:800px;height:400px;">
  	<div data-options="region:'west',title:'<spring:message code="system.sysmanager.user.userInf" />'" style="width:350px;">
  		<form id="updateForm" method="post" style="width: 97%; text-align: center;">
			<div class="bf4j-group">
				<div class="bf4j-group-content">
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label><spring:message code="system.sysmanager.user.userId" /></label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input type="text" name="userId" readonly="readonly" class="easyui-validatebox" style="width: 90%" 
									data-options="required:true,  missingMessage:'<spring:message code="system.sysmanager.user.userIdMiss" />',
									invalidMessage:'<spring:message code="system.sysmanager.user.userIdInvalid" />',validType:'length[6,16]'" />
							</span>
						</div>
					</div>
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label><spring:message code="system.sysmanager.user.userName" /></label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input type="text" name="userName" class="easyui-validatebox" style="width: 90%" 
									data-options="required:true,  missingMessage:'<spring:message code="system.sysmanager.user.userNameMiss" />'" />
							</span>
						</div>
					</div>
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label><spring:message code="system.sysmanager.user.nickName" /></label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input type="text" name="nickName" class="easyui-validatebox" style="width: 90%"/>
							</span>
						</div>
					</div>
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label><spring:message code="system.sysmanager.user.orgId" /></label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input class="bf4j-organ" name="orgId" data-options="required:true,  missingMessage:'<spring:message code="system.sysmanager.user.orgIdMiss" />'"/>
							</span>
						</div>
					</div>
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label><spring:message code="system.sysmanager.user.certType" /></label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input class="bf4j-combo" combo-options="dataKey:'CERT_TYPE',extra:'select'" data-options="required:true"  name="certType"/>
							</span>
						</div>
					</div>
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label><spring:message code="system.sysmanager.user.centNo" /></label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input type="text" name="certNo" class="easyui-validatebox" style="width: 90%"  data-options="required:true"/>
							</span>
						</div>
					</div>
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label><spring:message code="system.sysmanager.user.telephone" /></label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input type="text" name="telephone" class="easyui-validatebox" style="width: 90%"/>
							</span>
						</div>
					</div>
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label><spring:message code="system.sysmanager.user.mobilePhone" /></label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input type="text" name="mobilePhone" class="easyui-validatebox" style="width: 90%"/>
							</span>
						</div>
					</div>
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label><spring:message code="system.sysmanager.user.email" /></label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input type="text" name="email" class="easyui-validatebox" style="width: 90%"/>
							</span>
						</div>
					</div>
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label><spring:message code="system.sysmanager.user.limitIp" /></label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input type="text" name="limitIp" class="easyui-validatebox" style="width: 90%"/>
							</span>
						</div>
					</div>
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label><spring:message code="system.sysmanager.user.userStatus" /></label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<select class="easyui-combobox" id="userStatus" name="userStatus">
									<option value="1"><spring:message code="system.sysmanager.user.start" /></option>
									<option value="0"><spring:message code="system.sysmanager.user.close" /></option>
								</select>
							</span>
						</div>
					</div>
				</div>
			</div>
		</form>
  	</div>
  	<div data-options="region:'center',title:'<spring:message code="system.sysmanager.user.userRoleList" />'" style="width:450px;">
  		<table id="userRoleList" class="bf4j-grid-auto"></table>
  	</div>
</div>  
