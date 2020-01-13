<%@ page language="java" pageEncoding="UTF-8"%>
<div class="easyui-layout" data-options="fit:true" style="width:800px;height:400px;">
  	<div data-options="region:'west',title:'基本信息'" style="width:430px;">
  		<form id="addForm" method="post" style="width: 97%; text-align: center;">
			<div class="bf4j-group">
				<div class="bf4j-line">
					<div class="bf4j-cell bf4j-c4">
						<label>菜单ID：</label>
					</div>
					<div class="bf4j-cell bf4j-c7">
						<span class="bf4j-input"> 
							<input type="text" name="menuId" readonly="readonly" class="easyui-validatebox" style="width: 90%" 
								data-options="required:true,  missingMessage:'用户ID不能为空.',
								invalidMessage:'输入格式不正确,必须为数字'" />
						</span>
					</div>
				</div>
				<div class="bf4j-line">
					<div class="bf4j-cell bf4j-c4">
						<label>快捷菜单名称：</label>
					</div>
					<div class="bf4j-cell bf4j-c7">
						<span class="bf4j-input"> 
							<input type="text" name="shortMenuName" class="easyui-validatebox" style="width: 90%" 
								data-options="required:true,  missingMessage:'用户名称不能为空.',validType:'length[1,64]'" />
						</span>
					</div>
				</div>
				<div class="bf4j-line">
					<div class="bf4j-cell bf4j-c4">
						<label>图标：</label>
					</div>
					<div class="bf4j-cell bf4j-c7">
						<span class="bf4j-input"> 
							<input type="text" name="displayIcon" readonly="readonly" style="width: 90%"/>
						</span>
					</div>
				</div>
				<div class="bf4j-line">
					<div class="bf4j-cell bf4j-c4">
						<label>序号：</label>
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
						<label>描述：</label>
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
	<div data-options="region:'center',title:'图标列表'" style="width:370px;">
  		<div class="bf4j-group" id="PointIcon"  >
		    <div class="bf4j-group-content" >
			</div>
		</div>
  	</div>
</div>  
