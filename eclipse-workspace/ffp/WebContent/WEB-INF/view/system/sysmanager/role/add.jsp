<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<div class="easyui-layout" data-options="fit:true" style="width:800px;height:400px;">
  	<div data-options="region:'west',title:'<spring:message code="system.sysmanager.role.roleInf" />'" style="width:350px;">
  		<form id="addForm" method="post" style="width: 97%; text-align: center;">
			<div class="bf4j-group">
				<div class="bf4j-group-content">
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label><spring:message code="system.sysmanager.role.roleName" /></label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input type="text" name="roleName" class="easyui-validatebox" style="width: 90%" 
									data-options="required:true,  missingMessage:'<spring:message code="system.sysmanager.role.roleNameMiss" />'" />
							</span>
						</div>
					</div>
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label><spring:message code="system.sysmanager.role.des" /></label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<textarea type="text" name="des" class="easyui-validatebox" style="width: 90%;height:100px">
				                </textarea>
							</span>
						</div>
					</div>
				</div>
			</div>
		</form>
  	</div>
  	<div data-options="region:'center',title:'<spring:message code="system.sysmanager.role.roleRightsInf" />'" style="width:450px;">
  		<div id="permissionTabpanel" class="easyui-tabs" data-options="plain:true,border: false,fit: true" style="width:100%;border:none;height: 100%;">
  			<c:forEach items="${permTypes}" var="item">
  			<div title="${item.permTypeName }" permType="${item.permType }">   
		        <ul id="menuPermissionTree${item.permType }"></ul> 
		    </div>
  			</c:forEach>	
<!-- 		    <div title="角色(分配)" class="roleAllot">   -->
<!-- 		    	<table id="roleAllotList" class="bf4j-grid-auto"></table> -->
<!-- 		    </div> -->
  		</div>
  	</div>
</div>  
