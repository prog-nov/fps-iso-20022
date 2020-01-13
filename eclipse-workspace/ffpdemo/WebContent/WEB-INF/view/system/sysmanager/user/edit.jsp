<%@ page language="java" pageEncoding="UTF-8"%>
<div class="easyui-layout" data-options="fit:true" style="width:800px;height:400px;">
  	<div data-options="region:'west',title:'用户基本信息'" style="width:350px;">
  		<form id="updateForm" method="post" style="width: 97%; text-align: center;">
			<div class="bf4j-group">
				<div class="bf4j-group-content">
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label>用户ID：</label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input type="text" name="userId" readonly="readonly" class="easyui-validatebox" style="width: 90%" 
									data-options="required:true,  missingMessage:'用户ID不能为空.',
									invalidMessage:'输入格式不正确,用户ID长度在6-16个字符之间.',validType:'length[6,16]'" />
							</span>
						</div>
					</div>
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label>用户名称：</label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input type="text" name="userName" class="easyui-validatebox" style="width: 90%" 
									data-options="required:true,  missingMessage:'用户名称不能为空.'" />
							</span>
						</div>
					</div>
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label>用户昵称：</label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input type="text" name="nickName" class="easyui-validatebox" style="width: 90%"/>
							</span>
						</div>
					</div>
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label>机构号：</label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input type="text" name="orgId" class="easyui-validatebox" style="width: 90%" 
									data-options="required:true,  missingMessage:'机构号不能为空.'" />
							</span>
						</div>
					</div>
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label>证件类型：</label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<select class="easyui-combobox" id="certType" name="certType">
									<option value="1">身份证</option>
								</select>
							</span>
						</div>
					</div>
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label>证件号码：</label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input type="text" name="centNo" class="easyui-validatebox" style="width: 90%"/>
							</span>
						</div>
					</div>
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label>电话号码：</label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input type="text" name="telephone" class="easyui-validatebox" style="width: 90%"/>
							</span>
						</div>
					</div>
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label>移动电话：</label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input type="text" name="mobilePhone" class="easyui-validatebox" style="width: 90%"/>
							</span>
						</div>
					</div>
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label>电子邮箱：</label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input type="text" name="email" class="easyui-validatebox" style="width: 90%"/>
							</span>
						</div>
					</div>
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label>登录受限IP：</label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<input type="text" name="limitIp" class="easyui-validatebox" style="width: 90%"/>
							</span>
						</div>
					</div>
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label>用户状态：</label>
						</div>
						<div class="bf4j-cell bf4j-c7">
							<span class="bf4j-input"> 
								<select class="easyui-combobox" id="userStatus" name="userStatus">
									<option value="1">启用</option>
									<option value="0">停用</option>
								</select>
							</span>
						</div>
					</div>
				</div>
			</div>
		</form>
  	</div>
  	<div data-options="region:'center',title:'用户角色列表'" style="width:450px;">
  		<table id="userRoleList" class="bf4j-grid-auto"></table>
  	</div>
</div>  
