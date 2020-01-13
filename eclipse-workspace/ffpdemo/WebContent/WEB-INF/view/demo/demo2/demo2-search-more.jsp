<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<div class="bf4j-warp">
	<form id="searchForm" method="post">
		<div class="bf4j-group">
			<div class="bf4j-group-title">
				<span><spring:message code="demo.title.cstinfo" /></span>
			</div>
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
						<label><spring:message code="demo.product.oldprice" />：</label>
					</div>
					<div class="bf4j-cell bf4j-sc5">
						<span class="bf4j-input"> <input type="text"
							class="easyui-textbox" style="width: 42%" /> - <input
							type="text" class="easyui-textbox" style="width: 42%" />
						</span>
					</div>
					<div class="bf4j-cell bf4j-sc3">
						<label><spring:message code="demo.product.nowprice" />：</label>
					</div>
					<div class="bf4j-cell bf4j-sc5">
						<span class="bf4j-input"> <input type="text"
							class="easyui-textbox" style="width: 42%" /> - <input
							type="text" class="easyui-textbox" style="width: 42%" />
						</span>
					</div>

					<div class="bf4j-cell bf4j-sc3">
						<label><spring:message code="demo.product.count" />：</label>
					</div>
					<div class="bf4j-cell bf4j-sc5">
						<span class="bf4j-input"> <input type="text"
							class="easyui-textbox" style="width: 42%" /> - <input
							type="text" class="easyui-textbox" style="width: 42%" />
						</span>
					</div>
				</div>
				<div class="bf4j-line">
					<div class="bf4j-cell bf4j-sc3">
						<label><spring:message code="demo.product.datelong" />：</label>
					</div>
					<div class="bf4j-cell bf4j-sc5">
						<span class="bf4j-input"> <input type="checkbox"
							name="type1" />
						<spring:message code="demo.product.datelongone" /> <input
							type="checkbox" name="type1" />
						<spring:message code="demo.product.datelongtwo" /> <input
							type="checkbox" name="type1" />
						<spring:message code="demo.product.datelongthree" />
						</span>
					</div>
					<div class="bf4j-cell bf4j-sc3">
						<label><spring:message code="demo.product.roletype" />：</label>
					</div>
					<div class="bf4j-cell bf4j-sc13">
						<span class="bf4j-input"> <input type="radio" name="type2" />
						<spring:message code="demo.product.roletypelc" /> <input
							type="radio" name="type2" />
						<spring:message code="demo.product.roletypekg" /> <input
							type="radio" name="type2" />
						<spring:message code="demo.product.roletypexd" /> <input
							type="radio" name="type2" />
						<spring:message code="demo.product.roletypedt" />
						</span>
					</div>
				</div>
				<div class="bf4j-line">
					<div class="bf4j-cell bf4j-sc3">
						<label><spring:message code="demo.product.date" />：</label>
					</div>
					<div class="bf4j-cell bf4j-sc5">
						<span class="bf4j-input"> <input type="text"
							class="easyui-datebox" style="width: 85px" /> - <input
							type="text" class="easyui-datebox" style="width: 85px" />
						</span>
					</div>
					<div class="bf4j-cell bf4j-sc3">
						<label><spring:message code="demo.product.address" />：</label>
					</div>
					<div class="bf4j-cell bf4j-sc13">
						<span class="input"> <input type="text"
							class="easyui-textbox" style="width: 95%" />
						</span>
					</div>
				</div>
			</div>
		</div>

		<div class="bf4j-group">
			<div class="bf4j-group-title">
				<span><spring:message code="demo.title.cstfamily" /></span>
			</div>
			<div class="bf4j-group-content" style="display:none" >
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
						<label><spring:message code="demo.product.oldprice" />：</label>
					</div>
					<div class="bf4j-cell bf4j-sc5">
						<span class="bf4j-input"> <input type="text"
							class="easyui-textbox" style="width: 42%" /> - <input
							type="text" class="easyui-textbox" style="width: 42%" />
						</span>
					</div>
					<div class="bf4j-cell bf4j-sc3">
						<label><spring:message code="demo.product.nowprice" />：</label>
					</div>
					<div class="bf4j-cell bf4j-sc5">
						<span class="bf4j-input"> <input type="text"
							class="easyui-textbox" style="width: 42%" /> - <input
							type="text" class="easyui-textbox" style="width: 42%" />
						</span>
					</div>

					<div class="bf4j-cell bf4j-sc3">
						<label><spring:message code="demo.product.count" />：</label>
					</div>
					<div class="bf4j-cell bf4j-sc5">
						<span class="bf4j-input"> <input type="text"
							class="easyui-textbox" style="width: 42%" /> - <input
							type="text" class="easyui-textbox" style="width: 42%" />
						</span>
					</div>
				</div>
				<div class="bf4j-line">
					<div class="bf4j-cell bf4j-sc3">
						<label><spring:message code="demo.product.datelong" />：</label>
					</div>
					<div class="bf4j-cell bf4j-sc5">
						<span class="bf4j-input"> <input type="checkbox"
							name="type1" />
						<spring:message code="demo.product.datelongone" /> <input
							type="checkbox" name="type1" />
						<spring:message code="demo.product.datelongtwo" /> <input
							type="checkbox" name="type1" />
						<spring:message code="demo.product.datelongthree" />
						</span>
					</div>
					<div class="bf4j-cell bf4j-sc3">
						<label><spring:message code="demo.product.roletype" />：</label>
					</div>
					<div class="bf4j-cell bf4j-sc13">
						<span class="bf4j-input"> <input type="radio" name="type2" />
						<spring:message code="demo.product.roletypelc" /> <input
							type="radio" name="type2" />
						<spring:message code="demo.product.roletypekg" /> <input
							type="radio" name="type2" />
						<spring:message code="demo.product.roletypexd" /> <input
							type="radio" name="type2" />
						<spring:message code="demo.product.roletypedt" />
						</span>
					</div>
				</div>
				<div class="bf4j-line">
					<div class="bf4j-cell bf4j-sc3">
						<label><spring:message code="demo.product.date" />：</label>
					</div>
					<div class="bf4j-cell bf4j-sc5">
						<span class="bf4j-input"> <input type="text"
							class="easyui-datebox" style="width: 85px" /> - <input
							type="text" class="easyui-datebox" style="width: 85px" />
						</span>
					</div>
					<div class="bf4j-cell bf4j-sc3">
						<label><spring:message code="demo.product.address" />：</label>
					</div>
					<div class="bf4j-cell bf4j-sc13">
						<span class="input"> <input type="text"
							class="easyui-textbox" style="width: 95%" />
						</span>
					</div>
				</div>
			</div>
		</div>

		<div class="bf4j-group">
			<div class="bf4j-group-title">
				<span><spring:message code="demo.title.cstinterest" /></span><i></i>
			</div>
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
						<label><spring:message code="demo.product.oldprice" />：</label>
					</div>
					<div class="bf4j-cell bf4j-sc5">
						<span class="bf4j-input"> <input type="text"
							class="easyui-textbox" style="width: 42%" /> - <input
							type="text" class="easyui-textbox" style="width: 42%" />
						</span>
					</div>
					<div class="bf4j-cell bf4j-sc3">
						<label><spring:message code="demo.product.nowprice" />：</label>
					</div>
					<div class="bf4j-cell bf4j-sc5">
						<span class="bf4j-input"> <input type="text"
							class="easyui-textbox" style="width: 42%" /> - <input
							type="text" class="easyui-textbox" style="width: 42%" />
						</span>
					</div>

					<div class="bf4j-cell bf4j-sc3">
						<label><spring:message code="demo.product.count" />：</label>
					</div>
					<div class="bf4j-cell bf4j-sc5">
						<span class="bf4j-input"> <input type="text"
							class="easyui-textbox" style="width: 42%" /> - <input
							type="text" class="easyui-textbox" style="width: 42%" />
						</span>
					</div>
				</div>
				<div class="bf4j-line">
					<div class="bf4j-cell bf4j-sc3">
						<label><spring:message code="demo.product.datelong" />：</label>
					</div>
					<div class="bf4j-cell bf4j-sc5">
						<span class="bf4j-input"> <input type="checkbox"
							name="type1" />
						<spring:message code="demo.product.datelongone" /> <input
							type="checkbox" name="type1" />
						<spring:message code="demo.product.datelongtwo" /> <input
							type="checkbox" name="type1" />
						<spring:message code="demo.product.datelongthree" />
						</span>
					</div>
					<div class="bf4j-cell bf4j-sc3">
						<label><spring:message code="demo.product.roletype" />：</label>
					</div>
					<div class="bf4j-cell bf4j-sc13">
						<span class="bf4j-input"> <input type="radio" name="type2" />
						<spring:message code="demo.product.roletypelc" /> <input
							type="radio" name="type2" />
						<spring:message code="demo.product.roletypekg" /> <input
							type="radio" name="type2" />
						<spring:message code="demo.product.roletypexd" /> <input
							type="radio" name="type2" />
						<spring:message code="demo.product.roletypedt" />
						</span>
					</div>
				</div>
				<div class="bf4j-line">
					<div class="bf4j-cell bf4j-sc3">
						<label><spring:message code="demo.product.date" />：</label>
					</div>
					<div class="bf4j-cell bf4j-sc5">
						<span class="bf4j-input"> <input type="text"
							class="easyui-datebox" style="width: 85px" /> - <input
							type="text" class="easyui-datebox" style="width: 85px" />
						</span>
					</div>
					<div class="bf4j-cell bf4j-sc3">
						<label><spring:message code="demo.product.address" />：</label>
					</div>
					<div class="bf4j-cell bf4j-sc13">
						<span class="input"> <input type="text"
							class="easyui-textbox" style="width: 95%" />
						</span>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>