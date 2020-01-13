<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<div style="height:70px;">
    <div class="user-info-div">
    	<div class="icon-user"></div>
    	<div class="user-name"  ><c:out value="${user.userName}"></c:out>&nbsp;</div>
    	<div class="msg-info">
    		<div id="icon-num" style="height: 12px"></div>
    		<div class="icon-msg" ></div>
    	</div>
    </div>
</div>

<div class="well">
	<ul id="layout_west_tree"></ul>
</div>
<div class="mini-menu-bar well" style="display:none"  >
	<ul class="mini-tree floattree" ></ul>
</div>