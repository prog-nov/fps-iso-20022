<%@ page language="java" pageEncoding="UTF-8"%>
<form id="searchForm" method="post">
	<div class="bf4j-group">
		<div class="bf4j-group-content">
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-sc3">
					<label>jnlNo：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input type="text" name="jnlNo" class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label>transactionId：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input">
						<input type="text" name="transactionId" class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label>endToEndId：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input">
						<input type="text" name="endToEndId" class="easyui-textbox" style="width: 90%" />
					</span>
				</div> 
			</div>
			<!-- <div class="bf4j-line">
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
						<input class="bf4j-combo" combo-options="extra:'all',dataKey:'BOOLEAN'" data-options="width:'90%'" name="userStatus"/>
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
			</div> -->
		</div>
	</div>
	<div class="bf4j-group">
		<div class="bf4j-line" style="width: 10%;">
			<div class="bf4j-cell bf4j-c12 bf4j-center">
				<a class="bf4j-btn doQuery" title="查询"><i class="bf4j-icon-btn-search"></i>Query</a>
			</div>
		</div>
		<div class="bf4j-line" style="width:10%;">
			<div class="bf4j-cell bf4j-c12 bf4j-center">
				<a class="bf4j-btn doRefund" title="退款"><i class=""></i>Refund</a>
			</div>
		</div>
		
	</div>
</form>