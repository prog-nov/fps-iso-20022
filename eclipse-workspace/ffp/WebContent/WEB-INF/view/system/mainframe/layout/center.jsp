<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="system.mainframe.title" /></title>
<jsp:include page="/WEB-INF/view/common/includer/frame-includer.jsp"></jsp:include>
</head>
<body>
	<div id="mcenter-portal" >
		<div >
		<!--
			<div style="border: 0; text-align: center; overflow: hidden;height: 140px;" id="busi_status">
				<jsp:include page="/WEB-INF/view/system/mainframe/portal/task/task.jsp"></jsp:include>
			</div>
			
			<div style="border: 0; text-align: center; padding-top: 20px; overflow: hidden;margin: 0px;padding: 0px;" >
				 图片滑动轮播 
				<jsp:include page="/WEB-INF/view/system/mainframe/portal/slide/slider.jsp"></jsp:include>
			</div>
			-->
			<div id="mcenter-noteice" title="<spring:message code='main.noteice'/>" collapsible="true" closable="true" style="height:500px;padding:5px;">
		    </div>
		</div>
		<div style="width:42%;">
			<div id="mcenter-calendar" style="width:320px;height:320px;"></div>
			
		</div>
	</div>
	<div id="mcenter-notice-detail"  class="esc">
    </div>  
	<div id="mcenter-calendar-edit"  class="esc">
    </div>
    <div id="mcenter-task-detail"  class="esc">
	</div> 
    <script type="text/javascript" src="<b:path url='{root}/system/mainframe/layout/center{min}.js'/>"></script>
</body>
</html>	