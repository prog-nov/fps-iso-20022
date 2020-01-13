<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<title>用户管理</title>

</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="padding-top:10px;">
		<div data-options="region:'center',border:false">
			<div class="bf4j-warp" >
			<jsp:include page="/WEB-INF/view/common/leader/leaderView.jsp"></jsp:include>
			
				<!-- 手动上传表单  -->
				<form id="uploadForm" class="easyui-form" method="POST" enctype="multipart/form-data">
				<div class="bf4j-group">
					<div class="bf4j-group-content">
						<div class="bf4j-line">
							<div class="bf4j-cell bf4j-sc4"><label>标题</label></div>
							<div class="bf4j-cell bf4j-sc4">
								<input type="text" name="title" class="easyui-validatebox" data-options="required:true" >
							</div>
						</div>
						
						<div class="bf4j-line">
							<div class="bf4j-cell bf4j-sc4"><label>正文</label></div>
							<div class="bf4j-cell bf4j-sc4">
								<input type="text" name="content" class="easyui-validatebox" data-options="required:true" >
							</div>
						</div>
						
						<div class="bf4j-line">
							<div class="bf4j-cell bf4j-sc4"><label>附件</label></div>
							<div class="bf4j-cell bf4j-sc4">
								<div id="pick" style="width:90px;"><a class="bf4j-btn btn-add"  title="选择文件"><i class="bf4j-icon-btn-file"></i>选择文件</a></div>
							</div>
							<div class="bf4j-cell bf4j-sc4">
								<a class="bf4j-btn btn-add" id="upload"  title="上传">上传</a>
							</div>
						</div>
						<div class="bf4j-line">
							<div class="bf4j-cell bf4j-sc4"><label>文件队列</label></div>
							<div class="bf4j-cell bf4j-sc12"><div id="fileList"></div></div>
						</div>
						<div class="bf4j-line">
							<div class="bf4j-cell bf4j-sc4"><label>已上传文件列表</label></div>
							<div class="bf4j-cell bf4j-sc12"><div id="initList"></div></div>
						</div>
						
						<div class="bf4j-group newsBtn">
							<div class="bf4j-line">
								<div class="bf4j-cell bf4j-c12 bf4j-center">
									<a class="bf4j-btn bf4j-btn-save" title="<spring:message code="button.submit"/>"><i class="bf4j-icon-btn-save"></i><spring:message code="button.submit"/></a>
								</div>
							</div>
						</div>
					</div>
			</div>
		</form>
		
		<form id="uploadFormAuto" class="easyui-form" method="POST" enctype="multipart/form-data">
				<div class="bf4j-group">
					<div class="bf4j-group-content">
						<div class="bf4j-line">
							<div class="bf4j-cell bf4j-sc4"><label>标题</label></div>
							<div class="bf4j-cell bf4j-sc4">
								<input type="text" name="title" class="easyui-validatebox" data-options="required:true" >
							</div>
						</div>
						
						<div class="bf4j-line">
							<div class="bf4j-cell bf4j-sc4"><label>正文</label></div>
							<div class="bf4j-cell bf4j-sc4">
								<input type="text" name="content" class="easyui-validatebox" data-options="required:true" >
							</div>
						</div>
						
						<div class="bf4j-line">
							<div class="bf4j-cell bf4j-sc4"><label>附件</label></div>
							<div class="bf4j-cell bf4j-sc4">
								<div id="pickAuto" style="width:90px;"><a class="bf4j-btn btn-add"  title="选择文件"><i class="bf4j-icon-btn-file"></i>选择文件</a></div>
							</div>
						</div>
						<div class="bf4j-line">
							<div class="bf4j-cell bf4j-sc4"><label>文件队列</label></div>
							<div class="bf4j-cell bf4j-sc12"><div id="fileListAuto"></div></div>
						</div>
						<div class="bf4j-line">
							<div class="bf4j-cell bf4j-sc4"><label>已上传文件列表</label></div>
							<div class="bf4j-cell bf4j-sc12"><div id="initListAuto"></div></div>
						</div>
						
						<div class="bf4j-group newsBtnAuto">
							<div class="bf4j-line">
								<div class="bf4j-cell bf4j-c12 bf4j-center">
									<a class="bf4j-btn bf4j-btn-save" title="<spring:message code="button.submit"/>"><i class="bf4j-icon-btn-save"></i><spring:message code="button.submit"/></a>
								</div>
							</div>
						</div>
					</div>
			</div>
		</form>

		
		
	</div>
	  	<script type="text/javascript">
  	
	    require(['multiuploader-common'],function(multiuploaderCommon){
	    	
	    	jQuery(function($){
	      		init();
	      		bind();
	      		bindAuto();
	      		parent.$.messager.progress('close');
	    	});
	    	
	    	function init(){
	    		
	    		var pick = "#pick",fileList = "#fileList",upload = "#upload",initList="#initList";
	    		var pickAuto = "#pickAuto",fileListAuto = "#fileListAuto",initListAuto="#initListAuto";
	    		
	    		multiuploaderCommon.create(pick,{
	    			accept:{
	    				title:'支持的类型',
	    				extensions:'jpg,jpeg,gif',
	    				mimeTypes:'image/*'
	    		},"fileList":fileList,"initList":initList});
		    	$(upload).on("click",function(){
		    		multiuploaderCommon.upload(pick);
		    	});
		    	
		    	multiuploaderCommon.create(pickAuto,{"fileList":fileListAuto,"auto":true,fileKeys:'fileKeysAuto',"initList":initListAuto});
		    	
		    	
	    	}
	    	
	    	
	    	function bind(){
	    		$(".newsBtn").find("a.bf4j-btn-save").on("click", function(){
		    		var form = $('#uploadForm');
					if(form.form('validate')){
						if($("input[name='fileKeys']").length <= 0 ){
							$b.Msg.error($b.Base.i18n('validate.file'));
				    		return false;
						}
						$b.Submit.ajaxSubmitForm(form,{'url':$b.context.root + "/save"},function(){
							$b.Msg.alert($b.Base.i18n('operate.doOk'));
						});
					}
		    	});
	    	}
	    	
	    	function bindAuto(){
	    		$(".newsBtnAuto").find("a.bf4j-btn-save").on("click", function(){
		    		var form = $('#uploadFormAuto');
					if(form.form('validate')){
						if($("input[name='fileKeysAuto']").length <= 0 ){
							$b.Msg.error($b.Base.i18n('validate.file'));
				    		return false;
						}
						$b.Submit.ajaxSubmitForm(form,{'url':$b.context.root + "/save"},function(){
							$b.Msg.alert($b.Base.i18n('operate.doOk'));
						});
					}
		    	});
	    	}
	    	
	    	
		});	
	    
  	</script>
</body>
</html>
