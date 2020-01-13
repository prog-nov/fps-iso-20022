/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 应用使用的上传组件扩展，可二次修改<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-6-21<br>
 */
define('multiuploader-app',['multiuploader-beneform4j','beneform4j'],function(multiuploaderBeneform4j,$b){
	
	if(window.$multiuploaderApp){
		return;
	}
	
    var caller  = function(fn,args){
		if( fn && typeof fn === 'function' ){
			if( null == args){
				return fn.apply(this);
			}
			else{
				return fn.apply(this,args);
			}
		}
    };

	var multiuploaderApp = window.$multiuploaderApp = {

			create : function(pick,params,initMethod,sucFuc,failFuc){
				
				var _params = params || {};
				var defaults = {
			        pick: pick,    //文件选择按钮（DIV）
			        fileListDiv : _params.fileList,
			        swf: $b.context.root+'/resources/third/upload/webuploader/Uploader.swf', //SWF文件默认路径
			        server: $b.context.root + '/doc/upload',//后台文件处理默认路径
			        isInitList : true,
			        initList : 'initList',
			        fileKeys : 'fileKeys'
				};
				
				var options  = $.extend({},defaults,_params);
				
				var suc = sucFuc || function(file,data){
	    			$("#" + file.id).html(file.name + " 上传成功,文件ID：" + data.docId);
			    			$("#" + file.id).append("<input type='hidden' name='"+options.fileKeys+"' value='"+data.docId+"'>");
			    			//保存后台返回的文件ID
			    			$("#" + file.id).attr(options.fileKeys,data.docId);
			    		};
	    		
	    		var fail = failFuc || function(){$b.Msg.error($b.Base.i18n('operate.doFail'))};
				
				var callBacks = {
					onFileQueued : function(file){
						$(options.fileListDiv).append("<li title=\"双击删除\" style=\"cursor:pointer\" id=\""+file.id+"\">" + file.name + " " + file.id + " " + multiuploaderBeneform4j.formatSize(file.size) + "</li>");
						$("#" + file.id).on("dblclick",function(){
							var fileKeys = $("#" + file.id).attr(options.fileKeys);
							if(fileKeys){
								$b.Base.file.deleteFile({'docId' : fileKeys},function(){
									multiuploaderBeneform4j.removeFile(pick,file);
									$("#" + file.id).remove();
								});
							}else{
								multiuploaderBeneform4j.removeFile(pick,file);
								$("#" + file.id).remove();
							}
							
						});
					},
					onFileDequeued : function(file){
						$b.Msg.alert("文件" + file.name + "从队列移除");
					},
					onUploadProgress : function(file,progress){
						$("#" + file.id).html(file.name + " 正在上传 : " + progress + "%");
					},
					onUnsupport : function(){
						$b.Msg.error("上传插件环境检测不通过");
					},
					onUploadSuccess : function(file,d){
						caller(suc,[file,d]);
					},
					onUploadError : function(file){
						caller(fail,[file]);
					}
					
				};
				
				options.callBacks = $.extend({},callBacks,options.callBacks || {});
				
				var init = initMethod || function(){
					$b.Submit.ajaxSubmit($b.context.root + '/initList',{},function(data){
		   	        	$.each(data,function(i,t){
		   	        		$(options.initList).append("<li>" + t + "</li>");
		   	        	});
		   			});
				};
				
				multiuploaderBeneform4j.create(pick,options,init);
			},
			upload : function(pick,success,failure){
				
				
				var suc,fail;
				
				var params = multiuploaderBeneform4j.getOp(pick);
				
				if(success && typeof success === 'function'){
					suc = success;
				}else{
					suc = function(file,data){
		    			$("#" + file.id).html(file.name + " 上传成功,文件ID：" + data.docId);
		    			$("#" + file.id).append("<input type='hidden' name='"+params.fileKeys+"' value='"+data.docId+"'>");
		    			//保存后台返回的文件ID
		    			$("#" + file.id).attr(params.fileKeys,data.docId);
		    		}
				}
				
				if(failure && typeof failure === 'function'){
					fail = failure;
				}else{
					fail = function(file,data){
		    			$b.Msg.error($b.Base.i18n('operate.doFail'));
		    		}
				}
				
				multiuploaderBeneform4j.upload(pick,suc,fail);
			},
			removeFile : function(pick,file){
				multiuploaderBeneform4j.removeFile(pick,file);
			}
			
	};
	
	return multiuploaderApp;
	
});