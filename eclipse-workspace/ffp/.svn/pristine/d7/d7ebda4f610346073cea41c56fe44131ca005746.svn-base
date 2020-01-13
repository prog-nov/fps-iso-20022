///**
// * Copy Right Information : Forms Syntron <br>
// * Project : 四方精创 Java EE 开发平台 <br>
// * Description : 平台的通用上传组件<br>
// * Author : Kingdom <br>
// * Version : 1.0.0 <br>
// * Since : 1.0.0 <br>
// * Date : 2016-6-21<br>
// */
define('multiuploader-common',['multiuploader-beneform4j','locale'],function(multiuploaderBeneform4j,locale){
	
	if(window.$multiuploaderCommon){
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

	var multiuploaderCommon = window.$multiuploaderCommon = {

			
			/** 
		     * ### 描述 
	         * 平台上传组件实现  
	         * 
	         * ### 示例
	         * 
	         *     @example
	         *     	var pick = "#pick";
	         * 		multiuploaderCommon.create(pick,{},initMethod,sucFuc,failFuc);
	         * 
	         * @author Kingdom  2016-06-21
	    	 * @method create
	         * @param {String} pick 渲染上传组件的容器ID，可以为DIV,INPUT,BUTTON
	         * @param {Object} params  
	         * 
	         * ### params参数说明
	         * 
	         * var params = {
			 *       pick        : 渲染上传组件的容器ID，可以为DIV,INPUT,BUTTON
			 *       fileListDiv : 存放文件列表的容器id
			 *       swf         : SWF文件路径
			 *       server      : 后台处理上传请求路径
			 *       isInitList  : 是否显示历史文件列表 默认为true,
			 *       initList    : 历史文件列表容器id,
			 *       fileKeys    : 文件对象名称
			 *	};
	         * @param {Function} initMethod 初始化方法
	         * @param {Function} success 成功回调函数
	         * @param {Function} failure 失败回调函数
	         * @returns 
	         */
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
	    			$("#" + file.id).html(file.name + $b.Base.i18n('system.multiuploader.success',data.docId));
			    			$("#" + file.id).append("<input type='hidden' name='"+options.fileKeys+"' value='"+data.docId+"'>");
			    			//保存后台返回的文件ID
			    			$("#" + file.id).attr(options.fileKeys,data.docId);
			    		};
	    		
	    		var fail = failFuc || function(){$b.Msg.error($b.Base.i18n('operate.doFail'))};
				
				var callBacks = {
					onFileQueued : function(file){
						$(options.fileListDiv).append("<li title=\""+$b.Base.i18n('system.multiuploader.dbclickdel')+"\" style=\"cursor:pointer\" id=\""+file.id+"\">" + file.name + " " + file.id + " " + multiuploaderBeneform4j.formatSize(file.size) + "</li>");
						$("#" + file.id).on("dblclick",function(){
							var fileKeys = $("#" + file.id).attr(options.fileKeys);
							if(fileKeys){
								$b.File.deleteFile({'docId' : fileKeys},function(){
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
						$b.Msg.alert($b.Base.i18n('system.multiuploader.del',file.name));
					},
					onUploadProgress : function(file,progress){
						$("#" + file.id).html(file.name + $b.Base.i18n('system.multiuploader.uploading',progress));
					},
					onUnsupport : function(){
						$b.Msg.error($b.Base.i18n('system.multiuploader.checkerror'));
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
		    			$("#" + file.id).html(file.name + $b.Base.i18n('system.multiuploader.success',data.docId));
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
	
	return multiuploaderCommon;
	
});