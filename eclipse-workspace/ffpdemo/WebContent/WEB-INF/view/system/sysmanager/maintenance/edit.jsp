<%@ page language="java" pageEncoding="UTF-8"%>
<!-- 修改角色与角色(分配)关系页面 -->
<div class="easyui-layout" data-options="fit:true" style="width:600px;height:300px;">
  	<div data-options="region:'west',title:'角色基本信息'" style="width:350px;">
  	<!-- 角色基本信息展示 -->  
  		<form id="updateForm" method="post" style="width: 97%; text-align: center;">
			<div class="bf4j-group">
				<div class="bf4j-group-content">
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label>角色ID：</label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input type="text" name="roleId" readonly="readonly" class="easyui-validatebox" style="width: 90%"/>
							</span>
						</div>
					</div>
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label>角色名称：</label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input type="text" name="roleName" readonly="readonly" class="easyui-validatebox" style="width: 90%" />
							</span>
						</div>
					</div>
				</div>
			</div>
		</form>
  	</div>
  	<!-- 角色(分配)列表 -->
  	<div data-options="region:'center',title:'角色(分配)列表'" style="width:800px;">
  		<table id="roleAllotList" class="bf4j-grid-auto"></table>
  	</div>
</div>  
