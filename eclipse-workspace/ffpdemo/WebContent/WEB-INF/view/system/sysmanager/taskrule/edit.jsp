<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<div class="easyui-layout" data-options="fit:true" style="width:800px;height:800px;">
	<div data-options="region:'center',border:false">
  		<div class="bf4j-warp" >
  		<form id="updateForm" method="post" style="width: 97%; text-align: center;">
			<div class="bf4j-group">
				<div class="bf4j-group-content">
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label><spring:message code="systemmanager.taskrule.taskname"/>：</label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input type="hidden" id="taskId" name="taskId" readonly="readonly" />
								<input type="hidden" id="ruleId" name="ruleId" readonly="readonly" />
								<input id="taskName" name="taskName" style="width: 90%" />
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="bf4j-group">
				<div class="bf4j-group-content" style="height:500px;">
					<div id="permissionTabpanel" class="easyui-tabs" data-options="plain:true,border: false,fit: true" style="width:100%;border:none;height: 100%;">
			  			<div id="roleFlagDiv" title="<spring:message code="systemmanager.taskrule.limitroles"/>">
			  				<div class="bf4j-line">
								<div class="bf4j-cell bf4j-c4">
									<label><spring:message code="systemmanager.taskrule.limitroles"/>：</label>
								</div>
								<div class="bf4j-cell bf4j-c7">
									<span class="bf4j-input"> 
										<input  class="control-show" id="roleFlag" name="roleFlag"/>
									</span>
								</div>
							</div>
							<div class="bf4j-line" id="roleFlagCon">
					        	<table id="roleFlagShow" class="bf4j-grid-auto"></table>
					        </div> 
					    </div>
					    <div id="orgFlagDiv" title="<spring:message code="systemmanager.taskrule.limitorgs"/>">
					   		<div class="bf4j-line">
								<div class="bf4j-cell bf4j-c4">
									<label><spring:message code="systemmanager.taskrule.limitorgs"/>：</label>
								</div>
								<div class="bf4j-cell bf4j-c7">
									<span class="bf4j-input"> 
										<input  class="control-show" id="orgFlag" name="orgFlag"/>
									</span>
								</div>
							</div>
							<div class="bf4j-line" id="orgFlagCon">
					   			<table id="orgFlagShow" class="bf4j-grid-auto"></table>
					   		</div>
					    </div>
					    <div id="operFlagDiv" title="<spring:message code="systemmanager.taskrule.limitusers"/>">
					    	<div class="bf4j-line">
								<div class="bf4j-cell bf4j-c4">
									<label><spring:message code="systemmanager.taskrule.limitusers"/>：</label>
								</div>
								<div class="bf4j-cell bf4j-c7">
									<span class="bf4j-input"> 
										<input  class="control-show" id="operFlag" name="operFlag"/>
									</span>
								</div>
							</div>
							<div id="operFlagCon">
								<div class="bf4j-line" >
									<div class="bf4j-cell bf4j-c12 bf4j-right">
										<a class="bf4j-btn-2 gotoUserAdd" >
											<i class="bf4j-icon-btn-add"></i><spring:message code="systemmanager.taskrule.adduser"/></a>
										<a class="bf4j-btn-2 doUserDelete" >
											<i class="bf4j-icon-btn-delete"></i><spring:message code="systemmanager.taskrule.deluser"/></a>
										<a class="bf4j-btn-2 doDeleteAll" >
											<i class="bf4j-icon-btn-delete"></i><spring:message code="systemmanager.taskrule.deluserall"/></a>
									</div>
								</div>
								<div class="bf4j-line" >
						        	<table id="operFlagShow" class="bf4j-grid-auto"></table>
						        </div>
					        </div>
				        </div>
					    <div id="detailFlagDiv" title="<spring:message code="systemmanager.taskrule.linkdetail"/>">
					    	<div class="bf4j-line">
								<div class="bf4j-cell bf4j-c4">
									<label><spring:message code="systemmanager.taskrule.islinkdetail"/>：</label>
								</div>
								<div class="bf4j-cell bf4j-c7">
									<span class="bf4j-input"> 
										<input  class="control-show" id="detailFlag" name="detailFlag" />
									</span>
								</div>
							</div>
							<div class="bf4j-line" id="detailFlagCon">
								<div class="bf4j-cell bf4j-c4">
									<label><spring:message code="systemmanager.taskrule.detailmenuname"/>：</label>
								</div>
								<div class="bf4j-cell bf4j-c7" align="left">
									<input id="detailMenuName" name="detailMenuName" class="easyui-textbox" style="width: 40%" readonly="readonly" />
									<input type="hidden" id="detailMenuId" name="detailMenuId" />
								</div>
							</div>
					    </div>
			  		</div>
		  		</div>
	  		</div>
		</form>
		</div>
	</div>
</div>  
