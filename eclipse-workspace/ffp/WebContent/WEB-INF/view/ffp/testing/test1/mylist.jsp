<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FFP Testing page</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true"
		style="padding-top: 10px;">
		<form id="testingForm" class="easyui-form" method="post">
			<div data-options="region:'center'"
				style="width: 100%; height: 400px">
				<div class="bf4j-cell bf4j-c7">
					<span class="bf4j-input" > <select class="easyui-combobox" 
						id="mqName" name="mqName">
							<option value="TCP">AGENT</option>
							<option value="HMQ">HIGH</option>
							<option value="LMQ">LOW</option>
					</select>
					</span>
				</div>

				<div class="bf4j-line">
					<span class="bf4j-input"> <textarea rows="30" 
							name="requestMsg" style="width: 49%"></textarea> <textarea
							rows="30" name="responseMsg" style="width: 49%"></textarea>
					</span>
				</div>
				<div align="center">
					<!-- <a class="bf4j-btn-2 send" title="SEND" >
					<i class="bf4j-icon-btn-edit"></i>SEND</a> -->
					<input type="button" value="send" onclick="send()" style="height:30px;width:120px;">
					<input type="reset" value="reset" style="height:30px;width:120px;">
				</div>

			</div>
		</form>
	</div>
	<script type="text/javascript" src="/ffp/resources/third/jquery/jquery-1.12.0.min.js"></script> 
	<script type="text/javascript">
		function send() {
			//disable 
			$("input[type='button']").attr("disabled","disabled");
			$("input[type='reset']").attr("disabled","disabled");
			
			$.ajax({
				url : "${pageContext.request.contextPath}/ffp/testing/test1/mySend",
				type : "POST",
				dataType : "json",	//预期服务器返回的数据类型
				data : $('#testingForm').serialize(),
				success : function(result) {
					$("[name='responseMsg']").val(result.responseMsg);
					$("input[type='button']").removeAttr("disabled");
					$("input[type='reset']").removeAttr("disabled");
				},
				error : function() {
					alert("error");
				}
			});
		}
	</script>
</body>
</html>
