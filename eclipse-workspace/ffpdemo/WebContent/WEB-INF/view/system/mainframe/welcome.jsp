<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>ffp</title>
<jsp:include page="/WEB-INF/view/common/includer/frame-includer.jsp"></jsp:include>
<script type="text/javascript">
require(['mainframe'],function(mainframe){
	$(function(){
		//初始化布局
		mainframe.instMainLayout();
		//初始化菜单
		mainframe.instMenu();
		//初始化顶部消息
		mainframe.instTopMessage();
		//初始化悬浮菜单
		mainframe.instShortMenu();
		//初始化标签页
		mainframe.instMainTabs();
		//初始化标签页右键菜单
		mainframe.instMainTabsCtxMenu();
		//页面事件绑定
		mainframe.bindEvent();
		//首页刷新按钮增加鼠标悬停提示信息 
		mainframe.instRefresh();
		
		//个人消息轮询
		var timeID = setInterval(updateMsgNum,300000);
        function updateMsgNum(){
           	$b.Submit.ajaxSubmit(server_consts.root+'/news/checkMsgNum', {'needBlock':false}, 
           			function(data){ // 更新消息数成功时回调函数
		           		if(data != '' && data != '0'){
	           				$("#icon-num").html(data);
		           			$("#icon-num").addClass("icon-num");
		           		}else{
		           			$("#icon-num").html(' ');
		           			$("#icon-num").removeClass("icon-num");
		           		}
           			},
           			function(data){ // 更新消息数失败时回调函数
           	        	clearInterval(timeID);
           			},
           			true);
        }
        // 进入首页时加载未读消息数
		updateMsgNum();
        // 查看未读消息
        $(".msg-info").on("click", function(){
        	var num = $("#icon-num").html();
        	if(num != ' ' && num != '0'){
        		// 将未读消息数减一
        		num = parseInt(num)-1;
		   	 	$("#icon-num").html(num);
		   	 	if(num === 0){
			   	 	$("#icon-num").html(' ');
	       			$("#icon-num").removeClass("icon-num");
		   	 	}
        		// 展示消息明细
	        	var messageDetailDialog=$("#dialog-detail-message");
		   	 	$(messageDetailDialog).dialog({    
				    title: '消息明细',
				    minimizable: false,
				    collapsible: false,
				    width: 500,    
				    height: 380,    
				    href: server_consts.root+'/news/checkMsgDetail'
				}); 
        	}
        });
	});
});
</script>
</head>
<body>
	<div id="main_layout">
		<div data-options="region:'north',border:false,fit:false"  style="height:82px" >
			<!-- 顶部-->
			<div class="top-websit">
				<div class="left"></div>
				<div class="right">
					 <div class="link-group">
		                <div class="link-but">
		                    <a class="btn-logout" >
		                        <i class="bf4j-icon-node-65"></i>
		                        <span><spring:message code="label.logout"/></span>
		                    </a>
		                </div>
					 	<div class="link-but">
		                    <a class="btn-lang">
		                        <i class="bf4j-icon-node-76"></i>
		                        <span id="lang-text" >&nbsp;&nbsp;&nbsp;&nbsp;</span>
		                    </a>
		                </div>
		            </div>
				</div>
				<div class="center">
					<div class="messageSection"  >
						<i class="laba" ></i>
						<div class="messageContent">
						</div>
					</div>
					<ul class="topmenu floattree" >
					</ul>
				</div>
			</div>
			
		    <!-- point菜单 -->
		    <div class="pointMenu" id="pointMenu">
		        <div class="pointStart" title="我的快捷菜单" >
		        </div>
		        <div class="pointPanel" style="display: none">
		            <div class="point-header">
		                <div class="qieicon"></div>
		                <div class="seticon" title="设置快捷菜单"  onclick="javascript:void 0" > </div>
		            </div>
		            <ul>
		                
		            </ul>
		        </div>
		    </div>
		</div>
		<div data-options="region:'west'" class="main-west-div" style="border-right:1px solid #E4E4E4;overflow: hidden;" >
			<jsp:include page="layout/west.jsp" />
		</div>
		<div data-options="region:'center',border:false" 
			style="overflow: hidden;margin-left:0px">
				<div id="index_tabs" style="overflow: hidden;">
				</div>
		</div>
	</div>
	<div class="tabs-leftline" ></div>
	<div id="index_tabsMenu" style="width: 120px; display: none;">
		<div title="closeOther" data-options="iconCls:'bf4j-icon-right-closeAll'"><spring:message code="main.closeOther"/></div>
		<div title="closeAll" data-options="iconCls:'bf4j-icon-right-closeAll'"><spring:message code="main.closeAll"/></div>
		<div title="favorites" data-options="iconCls:'bf4j-icon-node-40'"><spring:message code="main.favorites"/></div>
	</div>
	<div style="display:none" >
		<div id="dialog" class="esc" >
		</div>
		<div id="dialog-lang" class="esc"  >
			<form id="langForm" name="langForm" >
				<div class="bf4j-pt10" ></div>
				<div class="bf4j-group">
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c4">
							<label><spring:message code="label.langChange"/>:</label>
						</div>
						<div class="bf4j-cell bf4j-c8">
							<span class="bf4j-input" >
								<input type="text" value="" name="language" id="language"  />
			                </span>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div id="dialog-detail-message" class="esc" ></div>
		<div id="dialog-msg-info" class="esc" ></div>
	</div>
</body>
</html>