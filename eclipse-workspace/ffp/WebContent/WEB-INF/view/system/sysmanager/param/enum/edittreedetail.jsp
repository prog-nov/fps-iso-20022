<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'west',title:'<spring:message code="system.sysmanager.param.enum.treeStr" /><font color=red><spring:message code="system.sysmanager.param.enum.dragAvail" /></font>'" style="width:320px;">
		<ul id="dataTreeDetail" class="easyui-tree"></ul>
	</div>
	<div data-options="region:'center',title:'<spring:message code="system.sysmanager.param.enum.nodeAttr" />'" >
		<form id="editTreeForm" method="post" style="width: 60%; text-align: center;">
			<input type="hidden" name="paramCode" />
			<div class="bf4j-group">
				<div class="bf4j-group-content">
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c3">
							<label><spring:message code="system.sysmanager.param.enum.supID" /></label>
						</div>
						<div class="bf4j-cell bf4j-c5">
							<span class="bf4j-input"> 
								<input type="text" name="parentDataCode" class="easyui-validatebox bf4j-readonly" style="width: 90%" 
									readonly="readonly"/>
							</span>
						</div>
					</div>
					<div class="bf4j-line">	
						<div class="bf4j-cell bf4j-c3">
							<label><spring:message code="system.sysmanager.param.enum.ID" /></label>
						</div>
						<div class="bf4j-cell bf4j-c5">
							<span class="bf4j-input"> 
								<input type="text" name="dataCode" class="easyui-validatebox" data-options="required:true" style="width: 90%" />
							</span>
						</div>
					</div>
					<div class="bf4j-line">							
						<div class="bf4j-cell bf4j-c3">
							<label><spring:message code="system.sysmanager.param.enum.dataText" /></label>
						</div>
						<div class="bf4j-cell bf4j-c5">
							<span class="bf4j-input"> 
								<input type="text" name="dataText" class="easyui-validatebox" data-options="required:true" style="width: 90%" />
							</span>
						</div>
					</div>
					<div class="bf4j-line">							
						<div class="bf4j-cell bf4j-c3">
							<label><spring:message code="system.sysmanager.param.enum.dataIcon" /></label>
						</div>
						<div class="bf4j-cell bf4j-c5">
							<span class="bf4j-input"> 
								<input type="text" name="dataIcon" class="easyui-validatebox" style="width: 90%" />
							</span>
						</div>
					</div>
					<div class="bf4j-line">							
						<div class="bf4j-cell bf4j-c3">
							<label><spring:message code="system.sysmanager.param.enum.dataParam" /></label>
						</div>
						<div class="bf4j-cell bf4j-c5">
							<span class="bf4j-input"> 
								<input type="text" name="dataParam" class="easyui-validatebox" style="width: 90%" />
							</span>
						</div>
					</div>
					<div class="bf4j-line">							
						<div class="bf4j-cell bf4j-c3">
							<label><spring:message code="system.sysmanager.param.enum.seqNo" /></label>
						</div>
						<div class="bf4j-cell bf4j-c5">
							<span class="bf4j-input"> 
								<input type="text" name="seqno" class="easyui-validatebox" data-options="required:true" style="width: 90%" />
							</span>
						</div>
					</div>
					<div class="bf4j-line">							
						<div class="bf4j-cell bf4j-c3">
							<label><spring:message code="system.sysmanager.param.enum.des" /></label>
						</div>
						<div class="bf4j-cell bf4j-c5">
							<span class="bf4j-input"> 
								<input type="text" name="des" class="easyui-validatebox" style="width: 90%" />
							</span>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
<div id="popupMenu" class="easyui-menu" style="width:80px;">
	<div class="menuAdd" data-options="iconCls:'bf4j-icon-right-add'"><spring:message code="button.add" /></div>
	<div class="menuEdit" data-options="iconCls:'bf4j-icon-right-edit'"><spring:message code="button.update" /></div>
	<div class="menuDelete" data-options="iconCls:'bf4j-icon-right-delete'"><spring:message code="button.delete" /></div>
</div>