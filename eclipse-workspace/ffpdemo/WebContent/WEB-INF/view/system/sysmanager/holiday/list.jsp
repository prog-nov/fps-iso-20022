<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<title>节假日维护</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="padding-top:10px;">
		<div data-options="region:'center',border:false">
			<div class="bf4j-warp" >
				<!-- 导航页面 -->
				<jsp:include page="/WEB-INF/view/common/leader/leaderView.jsp"></jsp:include>
				<!-- 日历展示 -->
				<form id="searchForm" method="post">
					<div class="bf4j-group" >
						<div class="bf4j-group-content">
							<div class="bf4j-line">
								<div class="bf4j-cell bf4j-sc12">
									<div id="holiday_calendar" style="width:420px;height:420px;"></div>
								</div>
								<div class="bf4j-cell bf4j-sc12">
									<div class="bf4j-line">
										<div class="bf4j-cell bf4j-c4">
											<label>是否节假日：</label>
										</div>
										<div class="bf4j-cell bf4j-c8">
											<span class="bf4j-input"> 
												<input class="bf4j-combo" combo-options="dataKey:'BOOLEAN'" value="1"  style="width: 90%" name="isHoliday"/>
											</span>
										</div>
									</div>
									<div class="bf4j-line">
										<div class="bf4j-cell bf4j-c4">
											<label>节假日名称：</label>
										</div>
										<div class="bf4j-cell bf4j-c8">
											<span class="bf4j-input"> 
												<input type="text" name="holidayName" class="easyui-textbox" style="width: 90%" />
											</span>
										</div>
									</div>
									<div class="bf4j-line">
										<div class="bf4j-cell bf4j-c4">
											<label>已选择的日期：</label>
										</div>
										<div class="bf4j-cell bf4j-c8">
											<span class="bf4j-input"> 
												<input type="text" name="selectDates" class="easyui-textbox" style="width: 90%" readonly="readonly" />
											</span>
										</div>
									</div>
									<div class="bf4j-line">
										<div class="bf4j-cell bf4j-c12 bf4j-center">
											<a class="bf4j-btn doSave" title="保存"><i class="bf4j-icon-btn-save"></i>保存</a>
											<a class="bf4j-btn doReset" title="重置"><i class="bf4j-icon-btn-reset"></i>重置</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src='<b:path url="holiday{min}.js"/>'></script>
</body>
</html>
