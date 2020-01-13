<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>

<style>
	.panel-noscroll {
		overflow: auto;
	}
</style>
<title><spring:message code="system.sysmanager.menulocale.title.menulocale" /></title>
</head>
<body>

	<div class="easyui-layout" data-options="fit:true" style="padding-top:10px;" id="localeMenus">
		<div data-options="region:'center',border:false">
			<div class="bf4j-warp" >
				<jsp:include page="/WEB-INF/view/common/leader/leaderView.jsp"></jsp:include>
				<div class="bf4j-group Action" >
				<div class="bf4j-line">
					<div class="bf4j-cell bf4j-c3 bf4j-left">
						<a class="bf4j-btn-2 SaveBtn" title='<spring:message code="button.save" />'><i class="bf4j-icon-btn-save"></i><spring:message code="button.save" /></a>
						<a class="bf4j-btn-2 EditBtn"  title='<spring:message code="button.update" />' ><i class="bf4j-icon-btn-edit" ></i><spring:message code="button.update" /></a>
					</div>
					<div class="bf4j-cell bf4j-c9 bf4j-right">

						<a class="bf4j-btn-2 cancelBtn" title='<spring:message code="system.sysmanager.button.cancleEdit" />'><i class="bf4j-icon-btn-stop"></i><spring:message code="system.sysmanager.button.cancleEdit" /></a>
					</div>
					</div>
			 </div>
			
			
				<div class="easyui-layout" data-options="fit:true" style="padding-top:10px;height: 400px;overflow:auto;border-bottom: 1px solid #dedede; ">
					
					<table id="tree"></table>
					
				</div>
				
			
			</div>
		</div>
	</div>
	
	<script type="text/javascript">		
	
		//var menu_locales ="${locales[0].localeName}";
		    		
	</script>
	<script type="text/javascript" src='<b:path url="locale{min}.js"/>'></script>
</body>
</html>
