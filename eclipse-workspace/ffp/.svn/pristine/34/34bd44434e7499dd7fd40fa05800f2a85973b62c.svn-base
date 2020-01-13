<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<div class="easyui-layout" data-options="fit:true" style="width:800px;height:400px;">
  	<div data-options="region:'west',title:'<spring:message code="common.favorites.menuInf" />'" style="width:430px;">
  		<form id="addForm" method="post" style="width: 97%; text-align: center;">
			<div class="bf4j-group">
				<div class="bf4j-line">
					<div class="bf4j-cell bf4j-c4">
						<label><spring:message code="common.favorites.menuId" /></label>
					</div>
					<div class="bf4j-cell bf4j-c7">
						<span class="bf4j-input"> 
							<input type="text" name="menuId" readonly="readonly" class="easyui-validatebox" style="width: 90%" 
								data-options="required:true,  missingMessage:'<spring:message code="common.favorites.menuIdMiss" />',
								invalidMessage:'<spring:message code="common.favorites.menuIdInvalide" />'" />
						</span>
					</div>
				</div>
				<div class="bf4j-line">
					<div class="bf4j-cell bf4j-c4">
						<label><spring:message code="common.favorites.shortMenuName" /></label>
					</div>
					<div class="bf4j-cell bf4j-c7">
						<span class="bf4j-input"> 
							<input type="text" name="shortMenuName" class="easyui-validatebox" style="width: 90%" 
								data-options="required:true,  missingMessage:'<spring:message code="common.favorites.shortMenuNameMiss" />',validType:'length[1,64]'" />
						</span>
					</div>
				</div>
				<div class="bf4j-line">
					<div class="bf4j-cell bf4j-c4">
						<label><spring:message code="common.favorites.icon" /></label>
					</div>
					<div class="bf4j-cell bf4j-c7">
						<span class="bf4j-input"> 
							<input type="text" name="icon" readonly="readonly" style="width: 90%"/>
						</span>
					</div>
				</div>
				<div class="bf4j-line">
					<div class="bf4j-cell bf4j-c4">
						<label><spring:message code="common.favorites.seqNo" /></label>
					</div>
					<div class="bf4j-cell bf4j-c7">
						<span class="bf4j-input"> 
							<input type="text" name="seqno" class="easyui-numberbox" style="width: 90%" 
								data-options="min:1, max:9999" />
						</span>
					</div>
				</div>
				<div class="bf4j-line">
					<div class="bf4j-cell bf4j-c4">
						<label><spring:message code="common.favorites.des" /></label>
					</div>
					<div class="bf4j-cell bf4j-c7">
						<span class="bf4j-input"> 
							<input type="text" name="des" class="easyui-validatebox" style="width: 90%"
								data-options="validType:'length[0,200]'"/>
						</span>
					</div>
				</div>
				<input type="hidden" name="keyId">
			</div>
		</form>
  	</div>
	<div data-options="region:'center',title:'<spring:message code="common.favorites.iconList" />'" style="width:370px;">
  		<div class="bf4j-group" id="PointIcon"  >
		    <div class="bf4j-group-content" >
			</div>
		</div>
  	</div>
</div>  
