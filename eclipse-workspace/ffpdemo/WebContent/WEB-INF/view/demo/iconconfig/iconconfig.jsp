<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
    <title>平台管理中心-四方精创</title>
    <style type="text/css" >
    	/**树形菜单图标合集**/
    	#MenuIcon .icon-node{
    		margin:5px 0px 5px 10px;
    		padding:5px 0px;
    		border:1px solid #F2E2D7;
    		height:40px;
    		width:100px;
    		float:left;
    		text-align:center;
    		border-radius:5px;
    		cursor:pointer;
    	}
    	#MenuIcon .icon-node:hover{
    		border:1px solid #C01A3F;
    	}
    	#MenuIcon .icon-node i{
    		margin:0px;
    		padding:0px;
    		height:16px;
    		width:16px;
    		display:inline-block;
    	}
    	#MenuIcon .icon-node input{
    		font-size:10px;
    		border:0px solid red;
    		cursor:pointer;
    	}
    	/**首页菜单图标合集**/
    	#HomeIcon .icon-node{
    		margin:5px 0px 5px 10px;
    		padding:5px 0px;
    		border:1px solid #F2E2D7;
    		height:100px;
    		width:100px;
    		float:left;
    		text-align:center;
    		border-radius:5px;
    		cursor:pointer;
    	}
    	#HomeIcon .icon-node:hover{
    		border:1px solid #C01A3F;
    	}
    	#HomeIcon .icon-node i{
    		margin:0px;
    		padding:0px;
    		height:76px;
    		width:76px;
    		display:inline-block;
    	}
    	#HomeIcon .icon-node input{
    		font-size:10px;
    		border:0px solid red;
    		cursor:pointer;
    	}
    	/**悬浮菜单图标合集**/
    	#PointIcon .icon-node{
    		margin:5px 0px 5px 10px;
    		padding:5px 0px;
    		border:1px solid #F2E2D7;
    		height:55px;
    		width:100px;
    		float:left;
    		text-align:center;
    		border-radius:5px;
    		cursor:pointer;
    	}
    	#PointIcon .icon-node:hover{
    		border:1px solid #C01A3F;
    	}
    	#PointIcon .icon-node i{
    		margin:0px;
    		padding:0px;
    		height:32px;
    		width:32px;
    		display:inline-block;
    	}
    	#PointIcon .icon-node input{
    		font-size:10px;
    		border:0px solid red;
    		cursor:pointer;
    	}
    	/**按钮图标合集**/
    	#BtnIcon .icon-node{
    		margin:5px 0px 5px 10px;
    		padding:10px 0px;
    		border:1px solid #F2E2D7;
    		height:40px;
    		width:100px;
    		float:left;
    		text-align:center;
    		border-radius:5px;
    		cursor:pointer;
    	}
    	#BtnIcon .icon-node:hover{
    		border:1px solid #C01A3F;
    	}
    	#BtnIcon .icon-node i{
    		margin:-2px 0px;
    		padding:0px;
    		height:16px;
    		width:16px;
    		display:inline-block;
    	}
    	#BtnIcon .icon-node input{
    		font-size:10px;
    		border:0px solid red;
    		cursor:pointer;
    	}
    	/**右键菜单图标合集**/
    	#RightMenuIcon .icon-node{
    		margin:5px 0px 5px 10px;
    		padding:5px 0px;
    		border:1px solid #F2E2D7;
    		height:40px;
    		width:100px;
    		float:left;
    		text-align:center;
    		border-radius:5px;
    		cursor:pointer;
    	}
    	#RightMenuIcon .icon-node:hover{
    		border:1px solid #C01A3F;
    	}
    	#RightMenuIcon .icon-node i{
    		margin:0px;
    		padding:0px;
    		height:16px;
    		width:16px;
    		display:inline-block;
    	}
    	#RightMenuIcon .icon-node input{
    		font-size:10px;
    		border:0px solid red;
    		cursor:pointer;
    	}
    	/**行级工具栏按钮图标合集**/
    	#RowBtnIcon .icon-node{
    		margin:5px 0px 5px 10px;
    		padding:5px 0px;
    		border:1px solid #F2E2D7;
    		height:50px;
    		width:100px;
    		float:left;
    		text-align:center;
    		border-radius:5px;
    		cursor:pointer;
    	}
    	#RowBtnIcon .icon-node:hover{
    		border:1px solid #C01A3F;
    	}
    	#RowBtnIcon .icon-node i{
    		margin:0px;
    		padding:0px;
    		height:26px;
    		width:26px;
    		display:inline-block;
    	}
    	#RowBtnIcon .icon-node input{
    		font-size:10px;
    		border:0px solid red;
    		cursor:pointer;
    	}
    	/**其他常用图标合集**/
    	#OtherIcon .icon-node{
    		margin:5px 0px 5px 10px;
    		padding:5px 0px;
    		border:1px solid #F2E2D7;
    		height:50px;
    		width:100px;
    		float:left;
    		text-align:center;
    		border-radius:5px;
    		cursor:pointer;
    	}
    	#OtherIcon .icon-node:hover{
    		border:1px solid #C01A3F;
    	}
    	#OtherIcon .icon-node i{
    		margin:0px;
    		padding:0px;
    		height:18px;
    		width:18px;
    		display:inline-block;
    	}
    	#OtherIcon .icon-node input{
    		font-size:10px;
    		border:0px solid red;
    		cursor:pointer;
    	}
    </style>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" >
		<div data-options="region:'center',border:false">
		 <div class="bf4j-warp" >
			<jsp:include page="/WEB-INF/view/common/leader/leaderView.jsp"></jsp:include>
			<div class="bf4j-group" id="MenuIcon"  >
				<div class="bf4j-group-title bf4j-pt25" ><span>菜单图标合集(15px*15px)</span></div>
			    <div class="bf4j-group-content" >
				</div>
			</div>
			<div class="bf4j-group" id="HomeIcon"  >
				<div class="bf4j-group-title bf4j-pt25" ><span>首页快捷菜单图标合集(76px*76px)</span></div>
			    <div class="bf4j-group-content" >
				</div>
			</div>
			<div class="bf4j-group" id="PointIcon"  >
				<div class="bf4j-group-title bf4j-pt25" ><span>悬浮快捷菜单图标合集(32px*32px)</span></div>
			    <div class="bf4j-group-content" >
				</div>
			</div>
			<div class="bf4j-group" id="BtnIcon"  >
				<div class="bf4j-group-title bf4j-pt25" ><span>按钮图标合集(15px*15px)</span></div>
			    <div class="bf4j-group-content" >
				</div>
			</div>
			<div class="bf4j-group" id="RightMenuIcon"  >
				<div class="bf4j-group-title bf4j-pt25" ><span>右键菜单图标合集(16px*16px)</span></div>
			    <div class="bf4j-group-content" >
				</div>
			</div>
			<div class="bf4j-group" id="RowBtnIcon"  >
				<div class="bf4j-group-title bf4j-pt25" ><span>行级工具栏图标合集(26px*26px)</span></div>
			    <div class="bf4j-group-content" >
				</div>
			</div>
			<div class="bf4j-group" id="OtherIcon"  >
				<div class="bf4j-group-title bf4j-pt25" ><span>常用情景图标合集(18px*18px)</span></div>
			    <div class="bf4j-group-content" >
				</div>
			</div>
			<div class="bf4j-group">
				<div class="bf4j-pt100" >&nbsp;</div>
			</div>
		</div>
	  </div>
    </div>
    <script type="text/javascript" src="iconconfig{min}.js"></script>
</body>
</html>
