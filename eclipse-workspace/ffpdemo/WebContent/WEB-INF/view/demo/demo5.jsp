<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>平台管理中心-四方精创</title>
</head>
<body>
<script src="../resources/third/jquery/jquery-easyui-1.3.3/jquery.min.js" type="text/javascript"></script>
<script src="../resources/third/jquery/jquery.tmpl.js" ></script>
<script type="text/javascript"> 
jQuery(function($){
	$( "#movieTemplate" )
	.tmpl( movies )
	.appendTo( "#movieList" );
	
	$("#x").val("2");
	
	top.$.messager.progress('close');
});
 
var movies = [
	{ Name: "leo yang Meet Joe Black", Languages: "French", Subtitles: "English" },
	{ Name: "leo yang The Mighty", Subtitles: "French and Spanish" },
	{ Name: "leo yang The Mighty" },
	{ Name: "leo yang City Hunter", Languages: "Mandarin and Cantonese" }
];
</script>
 
<script id="movieTemplate" type="text/x-jquery-tmpl">
<li>
	Title: {{= Name}}.
	{{if Languages}}
		(Alternative languages: {{= Languages}}).
	{{else Subtitles}}
		(Original language only. Subtitles in {{= Subtitles}}).
	{{else}}
		(Original version only, without subtitles).
	{{/if}}
</li>
</script>
 
<ul id="movieList"></ul>
 <select id="x">
 	<option value="1">a</option>
 	<option value="2">b</option>
 </select>
</body>

</html>
