<%@ page language="java" pageEncoding="UTF-8"%>
<form id="searchForm" method="post">
	<div class="bf4j-group">
		<div class="bf4j-group-content">
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-sc3">
					<label>参数代码：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input type="text" name="paramCode" class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label>参数名称：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input type="text" name="paramName" class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label>参数组别：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="extra:'all',dataKey:'PARAM_GROUP'"  name="paramGroup" style="width: 200%"/>
					</span>
				</div>
			 </div>
				
				<div class="bf4j-group">
				  <div class="bf4j-line">
				    <div class="bf4j-cell bf4j-sc3">
					  <label>存储类型：</label>
				    </div>
				    <div class="bf4j-cell bf4j-sc5">
					  <span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="extra:'all',dataKey:'STORE_TYPE'"  name="storeType" style="width: 200%"/>
					  </span>
				    </div>
				    <div class="bf4j-cell bf4j-sc3">
					  <label>数据类型：</label>
				    </div>
				    <div class="bf4j-cell bf4j-sc5">
					  <span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="extra:'all',dataKey:'DATA_TYPE'"  name="dataType" style="width: 200%"/>
					  </span>
				    </div>
				    <div class="bf4j-cell bf4j-sc3">
					  <label>可否编辑：</label>
				    </div>
				    <div class="bf4j-cell bf4j-sc5">
					  <span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="extra:'all',dataKey:'BOOLEAN'"  name="editable" style="width: 200%"/>
					  </span>
				    </div>
				    
				  </div>
				</div>
				
				<div class="bf4j-group">
				   <div class="bf4j-line">
				     <div class="bf4j-cell bf4j-c12 bf4j-center">
				      <a class="bf4j-btn  doQuery" title="查询"><i class="bf4j-icon-btn-search"></i>提交查询</a>
			         </div>
			      </div>
			    </div>
			
		</div>
	</div>
</form>