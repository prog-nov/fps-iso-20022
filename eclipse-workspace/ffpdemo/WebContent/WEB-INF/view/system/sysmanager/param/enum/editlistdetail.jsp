<%@ page language="java" pageEncoding="UTF-8"%>
<form id="editListForm" method="post" style="width: 97%; text-align: center;">
	<div class="bf4j-group">
		<div class="bf4j-group-content">
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-sc3">
					<label>参数代码：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input type="text" name="paramCode" class="easyui-validatebox bf4j-readonly" style="width: 90%" 
							readonly="readonly"/>
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label>参数名称：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input type="text" name="paramName" class="easyui-validatebox bf4j-readonly" style="width: 90%" 
							readonly="readonly" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label>所属组别：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input type="text" name="paramGroup" class="easyui-validatebox bf4j-readonly" style="width: 90%"
							readonly="readonly" />
					</span>
				</div>
			</div>
		</div>
	</div>
</form>
<div class="bf4j-group">
	<div class="bf4j-line">
		<div class="bf4j-cell bf4j-c12 bf4j-right">
			<a class="bf4j-btn-2 addRecord" title='添加'>
				<i class="bf4j-icon-btn-add"></i>添加</a>
			<a class="bf4j-btn-2 delRecord" title='删除'>
				<i class="bf4j-icon-btn-delete"></i>删除</a>
		</div>
	</div>
</div>	
<div class="bf4j-group" >
	<table id="dataListDetail" class="bf4j-grid-auto"></table>
</div>
