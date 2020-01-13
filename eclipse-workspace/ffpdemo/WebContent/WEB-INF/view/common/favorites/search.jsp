<%@ page language="java" pageEncoding="UTF-8"%>
<form id="searchForm" method="post">
	<div class="bf4j-group">
		<div class="bf4j-group-content">
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-sc3">
					<label>菜单ID：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input type="text" name="menuId" class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label>菜单名称：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input">
						<input type="text" name="shortMenuName" class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
			</div>
		</div>
	</div>
	<div class="bf4j-group">
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c12 bf4j-center">
				<a class="bf4j-btn doQuery" title="查询"><i class="bf4j-icon-btn-search"></i>提交查询</a>
			</div>
		</div>
	</div>
</form>