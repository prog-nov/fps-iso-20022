<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<style>
	#treeGuide{
		width:20%;
		height:100%;
		float:left;
		border:1px solid #dedede;
		box-sizing:border-box;
		-moz-box-sizing:border-box; /* Firefox */
		-webkit-box-sizing:border-box; /* Safari */
		overflow-y:scroll;
	}
	#guideContent{
		float:left;
		width:70%;
		margin-left:2%;
		border:1px solid #dedede;
		height:100%;
		box-sizing:border-box;
		-moz-box-sizing:border-box; /* Firefox */
		-webkit-box-sizing:border-box; /* Safari */
		overflow:hidden;
		color:#888;
	}
	#guideContent .tabs-header{
		width:100%;
	}
	.SaveBtn{
		margin-left:10px;
	}
	#guideContent .tabs-panels{
		margin-top:10px;
	}
	#guideTabs>ul{
		list-style:none;
		padding:0px;
		margin:0px;
		background:#F5F5F5;
		line-height:38px;
		padding-top:5px;
		
	}
	#tabsHead>li{
		display:inline-block;
		margin-left:5px;
		padding-left:5px;
		padding-right:5px;
		color:#555;
		border-top-left-radius:5px;
		border-top-right-radius:5px;
		
	}
	#tabsHead>li:hover{
		background:#aaa;
	}
	#guideTabs .active{
		background:#bbb;
		color:#0081c2;
	}

</style>
<title><spring:message code="system.sysmanager.menulocale.title.menulocale" /></title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true" style="padding-top:10px;" id="localeMenus">
	<div data-options="region:'center',border:false">
		<div class="bf4j-warp" >
			<jsp:include page="/WEB-INF/view/common/leader/leaderView.jsp"></jsp:include>
			<div class="easyui-layout" data-options="fit:true" style="padding-top:10px;height: 400px;overflow:auto;border-bottom: 1px solid #dedede; ">
				
				<ul id="treeGuide"></ul>
				<div id="guideContent">
					
					<h3><spring:message code="system.sysmanager.menulocale.title.menuName" /></h3>
					<form id="guideTabs" >  
						<ul id="tabsHead">
						</ul> 
						<div id="textEditor">
							<textarea name="guideContent" style="width: 99.5%; height: 260px; visibility: hidden;"> 
							</textarea>
						</div>
						<p><a class="bf4j-btn-2 SaveBtn" title=<spring:message code="button.save" />><i class="bf4j-icon-btn-save"></i><spring:message code="button.save" /></a></p>
					</form>
				</div> 
			</div>
		</div>
	</div>
</div>	
<script type="text/javascript" src='<c:url value="/"/>system/sysmanager/menulocale/guide.js'></script>

</body>
</html>
