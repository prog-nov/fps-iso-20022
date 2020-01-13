/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 平台基于multiuploader.js的实现<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-22<br>
 */
define('multiuploader-beneform4j',['multiuploader'],function($multiuploader){
	
	if(window.$multiuploaderBeneform4j){
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
	
	var multiuploaderBeneform4j = {

			_cache : {},
			
			_fnCache : {},
			
			_opCache : {},
			
			get : function(pick){
				return multiuploaderBeneform4j._cache[pick]
			},
			put : function(pick,object){
				multiuploaderBeneform4j._cache[pick] = object;
			},
			getCl : function(pick){
				return multiuploaderBeneform4j._fnCache[pick]
			},
			putCl : function(pick,object){
				multiuploaderBeneform4j._fnCache[pick] = object;
			}
			,
			getOp : function(pick){
				return multiuploaderBeneform4j._opCache[pick]
			},
			putOp : function(pick,object){
				multiuploaderBeneform4j._opCache[pick] = object;
			}
			,
			
			/**
			 * 创建上传组件实例
			 * @author Kingdom
			 * @date 2016-5-16
			 * @param contextPath 上下文
			 * @param pick 放置上传插件容器
			 * @param fileList 显示上传文件列表的容器ID
			 * @param params 附加参数对象 ,默认为{}
			 * 		        params.formData : 附件请求参数对象 默认为{},传入格式:"formData:{"testParam":"123"}"
			 * 				params.auto     :是否自动上传默认为true,如果需要改成手动上传则传入"auto":false
			 * @return 返回当前上传对象实例
			 */
			create : function(pick,params,init){
				var _params = params || {};
				var defaults = {
			        pick: pick,    //文件选择按钮（DIV）
			        fileListDiv : _params.fileList,
			        auto : false,          //是否在选择文件后自动上传
			        fileNumLimit : 10,    //队列默认最大文件个数限制
			        fileSingleSizeLimit : 100 * 1024 * 1024, //单个文件默认大小限制 单位：字节
			        resize : false,
			        disableWidgets: 'log',
			        server : _params.server,
			        isInitList : false
				};
				
				var options  = $.extend({},defaults,_params);
				
				multiuploaderBeneform4j.putCl(pick,options.callBacks);
				
				multiuploaderBeneform4j.putOp(pick,options);
				
				var mul = $multiuploader.create(options);
				
				multiuploaderBeneform4j.put(pick,$multiuploader.get());
				if( options.isInitList === true ){
					jQuery(function($){
						caller(init);
					});
				}
				
				return mul; 
			},
			upload : function(pick,options,success,failure){
				
				var params, succ, fail,callBacks={};
				if(typeof options === 'function'){//如果传入函数，作为回调函数
					params = {};
					succ = options;
					fail = failure || success;
				}else{
					params = options || {};
					succ = success;
					fail = failure;
				}
				callBacks.onUploadSuccess = succ;
				callBacks.onUploadError = fail;
				
				$.extend(multiuploaderBeneform4j.getCl(pick),callBacks);
				
				multiuploaderBeneform4j.get(pick).upload();
			}
			,
			removeFile : function(pick,file){
				//这里先执行回调函数的操作然后再从前端组件删除
				//caller(del);
				multiuploaderBeneform4j.get(pick).removeFile(file,true);
				
			},
			getMd5 : function(pick,file,progress,func){
				multiuploaderBeneform4j.get(pick).md5File(file).progress(function(percent){
					caller(progress,[percent]);
				}).then(function(val){
					
					caller(func,[val]);
					
				});
			},
			formatSize : function(size){
				return $multiuploader.formatSize(size)
			},
			getStats : function(pick){
				return multiuploaderBeneform4j.get(pick).getStats();
			}
			,
			getFiles : function(pick,status){
				return multiuploaderBeneform4j.get(pick).getFiles(status);
			}
	};
	
	return multiuploaderBeneform4j;
	
});