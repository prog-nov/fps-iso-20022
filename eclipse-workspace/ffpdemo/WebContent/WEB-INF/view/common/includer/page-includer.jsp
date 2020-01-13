<!--**
 * Copy Right Information : Forms Syntron 
 * Project : 四方精创 Java EE 开发平台 
 * Description : 工作区内页需要引用的样式文件,色调放布局样式前面
 * Author : Leo Yang
 * Version : 1.0.0
 * Since : 1.0.0 
 * Date : 2016-5-13
 * -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="b" uri="http://www.formssi.com/beneform4j/tags" %>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!-- 引入easyui.css 皮肤文件 (色调)-->
<link href='<b:path url="{root}/resources/beneform4j/css/theme/{theme}/easyui/easyui.css"/>'  rel="stylesheet" >
<!-- 引入easyui icon扩展样式 -->
<link href='<b:path url="{root}/resources/beneform4j/css/extensions/extEasyUIIcon.css"/>'  rel="stylesheet" >

<!-- 引用内页皮肤样式(色调)-->
<link rel="stylesheet" href='<b:path url="{root}/resources/beneform4j/css/theme/{theme}/innerpage-theme.css?v=20160513130400"/>'  type="text/css">
<!-- 引用内页自身样式 -->
<link rel="stylesheet" href='<b:path url="{root}/resources/beneform4j/css/innerpage.css?v=20160513130400"/>'  type="text/css">

<!-- 引用平台公共样式皮肤样式(色调)-->
<link rel="stylesheet" href='<b:path url="{root}/resources/beneform4j/css/theme/{theme}/public-theme.css?v=20150606130400"/>'  type="text/css">
<!-- 引用平台公共样式 -->
<link rel="stylesheet" href='<b:path url="{root}/resources/beneform4j/css/public.css?v=20160513130400"/>'  type="text/css">

<!-- 引入平台图标配置文件 config-icons.css(色调) -->
<link rel="stylesheet" href='<b:path url="{root}/resources/beneform4j/css/theme/{theme}/config-icons.css"/>'  rel="stylesheet" >

<!-- 引入文本编辑器样式 -->
<link rel="stylesheet" href='<b:path url="{root}/resources/third/kindeditor-4.1.10/themes/default/default.css"/>'  />

<!-- 引入requirejs -->
<jsp:include page="./requirejs-includer.jsp"></jsp:include>

<script>
//处理内页点击关闭pointMenu
require(['jquery'],function($){
	$(document).click(function(event){
		parent.framePlus.pointMenu.close();
	    event.stopPropagation();
	});
});	
</script>
