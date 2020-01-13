<%@ page language="java" pageEncoding="UTF-8"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'west',title:'树形结构<font color=red>（可拖动）</font>'" style="width:320px;">
		<ul id="dataTreeDetail" class="easyui-tree"></ul>
	</div>
	<div data-options="region:'center',title:'节点属性'" >
		<form id="editTreeForm" method="post" style="width: 60%; text-align: center;">
			<input type="hidden" name="paramCode" />
			<div class="bf4j-group">
				<div class="bf4j-group-content">
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c3">
							<label>父ID：</label>
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
							<label>ID：</label>
						</div>
						<div class="bf4j-cell bf4j-c5">
							<span class="bf4j-input"> 
								<input type="text" name="dataCode" class="easyui-validatebox" data-options="required:true" style="width: 90%" />
							</span>
						</div>
					</div>
					<div class="bf4j-line">							
						<div class="bf4j-cell bf4j-c3">
							<label>名称：</label>
						</div>
						<div class="bf4j-cell bf4j-c5">
							<span class="bf4j-input"> 
								<input type="text" name="dataText" class="easyui-validatebox" data-options="required:true" style="width: 90%" />
							</span>
						</div>
					</div>
					<div class="bf4j-line">							
						<div class="bf4j-cell bf4j-c3">
							<label>图标：</label>
						</div>
						<div class="bf4j-cell bf4j-c5">
							<span class="bf4j-input"> 
								<input type="text" name="dataIcon" class="easyui-validatebox" style="width: 90%" />
							</span>
						</div>
					</div>
					<div class="bf4j-line">							
						<div class="bf4j-cell bf4j-c3">
							<label>参数：</label>
						</div>
						<div class="bf4j-cell bf4j-c5">
							<span class="bf4j-input"> 
								<input type="text" name="dataParam" class="easyui-validatebox" style="width: 90%" />
							</span>
						</div>
					</div>
					<div class="bf4j-line">							
						<div class="bf4j-cell bf4j-c3">
							<label>序号：</label>
						</div>
						<div class="bf4j-cell bf4j-c5">
							<span class="bf4j-input"> 
								<input type="text" name="seqno" class="easyui-validatebox" data-options="required:true" style="width: 90%" />
							</span>
						</div>
					</div>
					<div class="bf4j-line">							
						<div class="bf4j-cell bf4j-c3">
							<label>描述：</label>
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
	<div class="menuAdd" data-options="iconCls:'bf4j-icon-right-add'">新增</div>
	<div class="menuEdit" data-options="iconCls:'bf4j-icon-right-edit'">修 改</div>
	<div class="menuDelete" data-options="iconCls:'bf4j-icon-right-delete'">删 除</div>
</div>