<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<form id="comboSearchForm" method="post">
	<div class="bf4j-group">
		<div class="bf4j-group-content">
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-sc3">
					<label><spring:message code="demo.product.name" />：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> <input type="text"
						class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label><spring:message code="demo.product.type" />：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> <select name="type"
						class="easyui-combobox" style="width: 90%"
						data-options="required:true">
							<option value="办公用品">办公用品</option>
							<option value="手提电脑">手提电脑</option>
							<option value="电脑配件">电脑配件</option>
							<option value="办公用品2">办公用品2</option>
							<option value="手提电脑2">手提电脑2</option>
							<option value="电脑配件2">电脑配件2</option>
					</select>
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label><spring:message code="demo.product.code" />：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> <input type="text"
						class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-sc3">
					<label><spring:message code="demo.product.oldprice"/>：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> <input type="text"
						class="easyui-textbox" style="width: 42%" /> - <input type="text"
						class="easyui-textbox" style="width: 42%" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label><spring:message code="demo.product.nowprice"/>：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> <input type="text"
						class="easyui-textbox" style="width: 42%" /> - <input type="text"
						class="easyui-textbox" style="width: 42%" />
					</span>
				</div>

				<div class="bf4j-cell bf4j-sc3">
					<label><spring:message code="demo.product.count"/>：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> <input type="text"
						class="easyui-textbox" style="width: 42%" /> - <input type="text"
						class="easyui-textbox" style="width: 42%" />
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-sc3">
					<label>机构：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-organ" name="orgId" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label>是否启用：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input combo-options="extra:'all',dataKey:'BOOLEAN'" data-options="width:'90%'" name="userStatus"/>
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label>是否已锁定：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="extra:'all',dataKey:'BOOLEAN'" data-options="width:'90%'" name="lockFlag"/>
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-sc3">
					<label>机构1（指定根机构）：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-organ" organ-options="root:'1000'" name="orgId1" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label>机构2（指定根机构和最小级别）：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-organ" organ-options="root:'1000', minOrgLevel:2" name="orgId2" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label>机构2（指定最小级别）：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-organ" organ-options="minOrgLevel:2" name="orgId3" />
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-sc3">
					<label>树形参数1：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="dataKey:'PARAM_GROUP'" data-options="width:'90%'" name="tree1"/>
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label>树形参数2：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="dataKey:'PARAM_GROUP',root:'SYSTEM_CONFIG'" data-options="width:'90%'" name="tree2"/>
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label>树形参数3：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="dataKey:'PARAM_GROUP',root:'SYSTEM_CONFIG,APP_PARAM1'" data-options="width:'90%'" name="tree3"/>
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-sc3">
					<label>同步机构1：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-organ" value="1000" organ-options="synch:true" name="synchOrgId1" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label>同步机构2：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-organ" organ-options="synch:true,root:'1000'" name="synchOrgId2" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label>同步机构3：</label>
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
					<label>授权级别(添加请选择)：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="dataKey:'AUTHOR_LEVEL',extra:'select'" data-options="width:'90%'" name="authLevel2"/>
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label>授权级别(排除无需授权)：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="dataKey:'AUTHOR_LEVEL',extra:'select', excludes:'0'" data-options="width:'90%'" name="authLevel3"/>
					</span>
				</div>
			</div>
		</div>
	</div>
	</form>