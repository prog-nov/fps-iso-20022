<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<link rel="stylesheet" href='<b:path url="{root}/resources/beneform4j/css/theme/{theme}/frame-theme.css"/>'  type="text/css">
<title><spring:message code="system.sysmanager.holiday.title.holidayMain" /></title>
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
											<label><spring:message code="system.sysmanager.holiday.isHoliday" /></label>
										</div>
										<div class="bf4j-cell bf4j-c8">
											<span class="bf4j-input"> 
												<input class="bf4j-combo" combo-options="dataKey:'BOOLEAN'" value="1"  style="width: 90%" name="isHoliday"/>
											</span>
										</div>
									</div>
									<div class="bf4j-line">
										<div class="bf4j-cell bf4j-c4">
											<label><spring:message code="system.sysmanager.holiday.holidayName" /></label>
										</div>
										<div class="bf4j-cell bf4j-c8">
											<span class="bf4j-input"> 
												<input type="text" name="holidayName" class="easyui-textbox" style="width: 90%" />
											</span>
										</div>
									</div>
									<div class="bf4j-line">
										<div class="bf4j-cell bf4j-c4">
											<label><spring:message code="system.sysmanager.holiday.selectDates" /></label>
										</div>
										<div class="bf4j-cell bf4j-c8">
											<span class="bf4j-input"> 
												<input type="text" name="selectDates" class="easyui-textbox" style="width: 90%" readonly="readonly" />
											</span>
										</div>
									</div>
									<div class="bf4j-line">
										<div class="bf4j-cell bf4j-c12 bf4j-center">
											<a class="bf4j-btn doSave" title='<spring:message code="system.sysmanager.button.save" />'><i class="bf4j-icon-btn-save"></i><spring:message code="system.sysmanager.button.save" /></a>
											<a class="bf4j-btn doReset" title='<spring:message code="system.sysmanager.button.reset" />'><i class="bf4j-icon-btn-reset"></i><spring:message code="system.sysmanager.button.reset" /></a>
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
