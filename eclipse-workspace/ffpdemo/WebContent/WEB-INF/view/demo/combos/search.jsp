<%@ page language="java" pageEncoding="UTF-8"%>
<form id="searchForm" method="post">
	<div class="bf4j-group">
		<div class="bf4j-group-content">
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-sc3">
					<label title="异步机构树">机构：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-organ" name="orgId" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label title="下拉选项，共用一组参数">是否启用：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="extra:'all',dataKey:'BOOLEAN'" data-options="width:'90%'" name="userStatus"/>
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label title="下拉选项，共用一组参数">是否已锁定：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="extra:'all_com',dataKey:'BOOLEAN'" data-options="width:'90%'" name="lockFlag"/>
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-sc3">
					<label title="同步机构，指定根机构">机构1（指定根机构）：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-organ" organ-options="root:'1000'" name="orgId1" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label title="同步机构，指定根机构，并同时指定显示的最小机构级别">机构2（指定根机构和最小级别）：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-organ" organ-options="root:'1000', minOrgLevel:2" name="orgId2" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label title="异步机构，指定显示的最小机构级别">机构2（指定最小级别）：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-organ" organ-options="minOrgLevel:2" name="orgId3" />
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-sc3">
					<label title="同步机构">同步机构1：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-organ" value="1000" organ-options="synch:true" name="synchOrgId1" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label title="同步机构，指定根机构">同步机构2：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-organ" organ-options="synch:true,root:'1000'" name="synchOrgId2" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label title="同步机构，指定根机构，并同时指定显示的最小机构级别">同步机构3：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-organ" organ-options="synch:true,root:'1000',minOrgLevel:2" name="synchOrgId3" />
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-sc3">
					<label>授权级别：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="'AUTHOR_LEVEL'" data-options="width:'90%'" name="authLevel"/>
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label title="添加“请选择”">授权级别(添加请选择)：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="dataKey:'AUTHOR_LEVEL',extra:'select'" data-options="width:'90%', required:true" name="authLevel2"/>
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label title="添加“全部”，排除代码为“0”的项">授权级别(排除无需授权)：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="dataKey:'AUTHOR_LEVEL',extra:'all', excludes:'0'" data-options="width:'90%',required:true" name="authLevel3"/>
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-sc3">
					<label title="指定参数代码">树形参数1：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="dataKey:'PARAM_GROUP'" data-options="width:'90%'" name="tree1"/>
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label title="指定参数代码和根节点">树形参数2：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="dataKey:'PARAM_GROUP',root:'SYSTEM_CONFIG'" data-options="width:'90%'" name="tree2"/>
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label title="指定参数代码，同时指定多个根节点">树形参数3：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="dataKey:'PARAM_GROUP',root:'SYSTEM_CONFIG,APP_PARAM1'" data-options="width:'90%'" name="tree3"/>
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