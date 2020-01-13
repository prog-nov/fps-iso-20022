<%@ page language="java" pageEncoding="UTF-8"%>
<form id="searchForm" method="post">
	<div class="bf4j-group">
		<div class="bf4j-group-content">
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
					<label>用户代码：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input type="text" name="userId" class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label>用户名称：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input">
						<input type="text" name="userName" class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
			</div>
			<div class="bf4j-line">
					<div class="bf4j-cell bf4j-sc3">
						<label>访问日期：</label>
					</div>
					<div class="bf4j-cell bf4j-sc5">
						<span class="bf4j-input"> 
							<input type="text" name="beginDate"  style="width: 86px" /> - 
							<input type="text" name="endDate" style="width: 86px" />
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