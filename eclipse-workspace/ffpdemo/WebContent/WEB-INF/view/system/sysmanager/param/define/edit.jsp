<%@ page language="java" pageEncoding="UTF-8"%>
<form id="updateForm" method="post"
	style="width: 97%; text-align: center;">
	<div class="bf4j-group">
		<div class="bf4j-group-content">
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c4">
					<label>参数代码：</label>
				</div>
				<div class="bf4j-cell bf4j-c7">
					<span class="bf4j-input"> 
					 <input type="text"
						name="paramCode" class="easyui-validatebox bf4j-readonly"
						style="width: 90%" data-options="required:true"
						readonly="readonly" />
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c4">
					<label>参数名称：</label>
				</div>
				<div class="bf4j-cell bf4j-c7">
					<span class="bf4j-input"> 
					 <input type="text"
						name="paramName" class="easyui-validatebox" style="width: 90%"
						data-options="required:true,missingMessage:'参数名称不能为空.'" />
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c4">
					<label>存储类型：</label>
				</div>
				<div class="bf4j-cell bf4j-c7">
					<span class="bf4j-input"> 
					 <input class="bf4j-combo"
						combo-options="dataKey:'STORE_TYPE'" data-options="required:true"
						name="storeType" style="width: 195%;" />
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c4">
					<label>参数组别：</label>
				</div>
				<div class="bf4j-cell bf4j-c7">
					<span class="bf4j-input"> 
					 <input name="paramGroup"
						class="bf4j-combo showParamGroup" combo-options="dataKey:'PARAM_GROUP'"
						data-options="required:true,missingMessage:'参数组别不能为空.'"
						style="width: 195%;" />
					</span>
				</div>
			</div>

			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c4">
					<label>数据类型：</label>
				</div>
				<div class="bf4j-cell bf4j-c7">
					<span class="bf4j-input"> 
					 <input class="bf4j-combo"
						combo-options="dataKey:'DATA_TYPE'" data-options="required:true"
						style="width: 195%;" name="dataType" />
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c4">
					<label>可否编辑：</label>
				</div>
				<div class="bf4j-cell bf4j-c7">
					<span class="bf4j-input"> 
					 <input class="bf4j-combo"
						combo-options="dataKey:'BOOLEAN'" name="editable"
						style="width: 195%;" data-options="required:true" />
					</span>
				</div>
			</div>

			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c4">
					<label>默认值：</label>
				</div>
				<div class="bf4j-cell bf4j-c7">
					<span class="bf4j-input"> 
					 <input type="text"
						name="defaultValue" class="easyui-validatebox" style="width: 90%" />
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c4">
					<label>取值规则：</label>
				</div>
				<div class="bf4j-cell bf4j-c7">
					<span class="bf4j-input"> 
					 <input type="text"
						name="valueRule" class="easyui-validatebox" style="width: 90%" />
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c4">
					<label style="width: 90%">取值规则参数：</label>
				</div>
				<div class="bf4j-cell bf4j-c7">
					<span class="bf4j-input"> 
					 <input type="text"
						name="valueRuleParam" class="easyui-validatebox"
						style="width: 90%" />
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c4">
					<label>描述：</label>
				</div>
				<div class="bf4j-cell bf4j-c7">
					<span class="bf4j-input"> 
					 <textarea name="des"
							class="easyui-validatebox"
							style="width: 89%; height: 60px; font-family: Arial; resize: none"></textarea>
					</span>
				</div>
			</div>
		</div>
	</div>
</form>
