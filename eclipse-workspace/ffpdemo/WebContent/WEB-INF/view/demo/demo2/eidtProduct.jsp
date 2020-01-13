<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<form id="updateForm" method="post" class="easyui-form">
	<div class="bf4j-pt10"></div>
	<div class="bf4j-group">
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="demo.product.name"/>: <span class="bf4j-red" >*</span></label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span class="bf4j-input" >
				<input name="name" id="name" style="width: 80%"
					class="easyui-validatebox"
					data-options="required:true,
                        invalidMessage:'<spring:message code="demo.valid.name"/>',validType:'length[1,50]'">
                </span>
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="demo.product.type"/>: <span class="bf4j-red" >*</span></label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span class="bf4j-input" >
					<select name="type" id="type" class="easyui-combobox"
						style="width: 120" data-options="required:true">
						<option value="办公用品">办公用品</option>
						<option value="手提电脑">手提电脑</option>
						<option value="电脑配件">电脑配件</option>
						<option value="办公用品2">办公用品2</option>
						<option value="手提电脑2">手提电脑2</option>
						<option value="电脑配件2">电脑配件2</option>
					</select>
				</span>
			</div>
		</div>

		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="demo.product.oldprice"/>: <span class="bf4j-red" >*</span></label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span class="bf4j-input" >
				<input name="oldPrice" id="oldPrice" class="easyui-numberbox"
					style="width: 80%"
					data-options="required:true,min:0,precision:2" />
				</span>
			</div>
		</div>

		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="demo.product.nowprice"/>: <span class="bf4j-red" >*</span></label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span class="bf4j-input" >
				<input name="nowPrice" id="nowPrice" class="easyui-numberbox"
					style="width: 80%"
					data-options="required:true,min:0,precision:2" />
				</span>
			</div>
		</div>

		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="demo.product.count"/>: <span class="bf4j-red" >*</span></label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span class="bf4j-input" >
				<input name="count" id="count" class="easyui-numberbox"
					style="width: 80%"
					data-options="required:true,min:0,precision:0" />
				</span>
			</div>
		</div>

		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="demo.product.date"/>: <span class="bf4j-red" >*</span></label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span class="bf4j-input" >
				<input name="date" id="date" class="easyui-datebox"
					style="width: 120"
					data-options="required:true,min:0,precision:0" />
				</span>
			</div>
		</div>

		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="demo.product.address"/>: <span class="bf4j-red" >*</span></label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span class="bf4j-input" >
				<input name="addr" id="addr" class="easyui-validatebox"
					style="width: 80%"
					data-options="required:true,min:0,precision:0" />
				</span>
			</div>
		</div>

		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c4">
				<label><spring:message code="demo.product.remark"/>:</label>
			</div>
			<div class="bf4j-cell bf4j-c8">
				<span class="bf4j-input" >
				<textarea name="remark" id="remark" class="easyui-validatebox"
					data-options="required:true"
					style="width: 80%; height: 130px;">
                    </textarea>
                </span>
			</div>
		</div>
	</div>

</form>