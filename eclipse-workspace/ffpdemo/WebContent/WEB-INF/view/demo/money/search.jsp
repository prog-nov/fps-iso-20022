<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<form id="searchForm" method="post">
	<div class="bf4j-group">
		<div class="bf4j-group-content">
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-sc2">
					<label>金额一:</label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input"> 
						<input type="text" name="money1" class="easyui-numberbox" style="width: 90%" 
							data-options="min:0,precision:2,groupSeparator:','" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc2">
					<label>金额二:</label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input">
						<input type="text" name="money2" class="easyui-numberbox" style="width: 90%" 
							data-options="min:0,precision:2,groupSeparator:',',prefix:'$'" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc2">
					<label>金额三:</label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input">
						<input type="text" name="money3" class="easyui-numberbox" style="width: 90%" 
							data-options="min:0,precision:2,groupSeparator:',',prefix:'￥'" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc2">
					<label>金额四:</label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input">
						<input type="text" name="money4" class="easyui-numberbox" style="width: 90%" 
							data-options="min:0,precision:2,groupSeparator:',',prefix:'€'" />
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-sc2">
					<label>金额五:</label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input"> 
						<input type="text" name="money5" class="easyui-numberbox" style="width: 90%" 
							data-options="min:0,precision:2,groupSeparator:',',suffix:'￥'" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc2">
					<label>金额六:</label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input">
						<input type="text" name="money6" class="easyui-numberbox" style="width: 90%" 
							data-options="min:0,precision:2,groupSeparator:',',suffix:'$'" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc2">
					<label>金额七:</label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input">
						<input type="text" name="money7" class="easyui-numberbox" style="width: 90%" 
							data-options="min:0,precision:2,groupSeparator:',',suffix:'€'" />
					</span>
				</div>
			</div>
		</div>
	</div>
	<div class="bf4j-group">
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c12 bf4j-center">
				<a class="bf4j-btn SearchBtn" title='<spring:message code="button.search"/>'>
					<i class="bf4j-icon-btn-search"></i>
					<spring:message code="button.search"/>
				</a>
			</div>
		</div>
	</div>
</form>