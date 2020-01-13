///**
// * Copy Right Information : Forms Syntron <br>
// * Project : 四方精创 Java EE 开发平台 <br>
// * Description : Html5/Flash双版上传控件,基于webuploader和RequireJS<br>
// * Author : Kingdom <br>
// * Version : 1.0.0 <br>
// * Since : 1.0.0 <br>
// * Date : 2016-4-22<br>
// */
define('multiuploader',['WebUploader'],function(WebUploader){
	
	var getFlashVersion = function() 
	{
        var version;

        try {
            version = window.navigator.plugins[ 'Shockwave Flash' ].description;
        } catch ( ex ) {
            try {
                version = new ActiveXObject('ShockwaveFlash.ShockwaveFlash').GetVariable('$version');
            } catch ( ex2 ) {
                version = '0.0';
            }
        }
        version = version.match( /\d+/g );
        return parseFloat( version[ 0 ] + '.' + version[ 1 ], 10 );
    };
    var isSupportHtml5 = function(){
    	
    	return window.Blob && window.FileReader && window.DataView;
    	
    };
    var isSupportFlash = function(){
    	return getFlashVersion() >= 11.4
    };
    var caller  = function(fn,args){
    		if( fn && typeof fn === 'function' ){
    			if( null == args){
    				fn.apply(this);
    			}
    			else{
    				fn.apply(this,args);
    			}
    			
    		}
    	
    }
	
	var multiuploader =  {
		
			/** 
		     * ### 描述 
	         * 创建上传组件通用API  
	         * 
	         * @author Kingdom  2016-04-22
	    	 * @method create
	         * @param {Object} options  
	         * @returns {Object} 上传组件实例
	         */
		create : function(config){
			
			var defaults = {
					
			};
			
			var options  = $.extend({},defaults, config);
			
			var callBacks = options.callBacks || {};
			
			if( !isSupportFlash() && !isSupportHtml5())
			{
				//环境不支持
				caller(callBacks.onUnsupport);
				return;
			}
			
			this.uploader = WebUploader.create(options);
			
			multiuploader.bindEvent(this.uploader,callBacks);
		},
		get : function(){
			return this.uploader;
		},
		bindEvent : function(uploader,callBacks){
			
			uploader.on( 'uploadBeforeSend', function( obj,data,hearder ) {
		    	caller(callBacks.onUploadBeforeSend,[data]);
		    });
			
			/**
			 * 文件添加到队列事件
			 * @params 文件ID,文件名,文件大小
			 */
			uploader.on( 'fileQueued', function( file ) {
		    	caller(callBacks.onFileQueued,[file]);
		    	
		    });
			
			/**
			 * 上传进度
			 * @file 文件ID：file.id  文件名:file.name
			 * @percentage 上传进度
			 */
			uploader.on( 'uploadProgress', function( file, percentage ) {
	    		caller(callBacks.onUploadProgress,[file,Math.round(percentage * 100)]);
			});
			
			/**
			 * 单个文件上传成功
			 * @file 文件ID：file.id  文件名:file.name
			 * @data 后台返回数据
			 */
			uploader.on( 'uploadSuccess', function( file,response ) {
				var status = response.success;
				if(status === true){
					caller(callBacks.onUploadSuccess,[file,response.data]);
		    	}else if(status === false){
		    		caller(callBacks.onUploadError,[file]);
		    	}
				
		    });
			
			/**
			 * 所有文件上传成功
			 */
			uploader.on( 'uploadFinished', function( ) {
				caller(callBacks.onUploadFinished);
		    });
			
			/**
			 * 不管成功还是失败，文件上传完成时都会触发
			 */
			uploader.on( 'uploadComplete', function( file ) {
				caller(callBacks.onUploadComplete,[file]);
		    });
			
			/**
			 * 上传失败
			 * @file 文件ID：file.id  文件名:file.name
			 * @data 后台返回数据
			 */
			uploader.on( 'uploadError', function( file ) { 
				caller(callBacks.onUploadError,[file]);
		    });
			
			
			uploader.on( 'fileDequeued', function( file ) { 
				caller(callBacks.onFileDequeued,[file]);
		    });
			
			/**
			 * 校验不通过触发如:
			 * 	Q_EXCEED_NUM_LIMIT  在设置了`fileNumLimit`且尝试给`uploader`添加的文件数量超出这个值时派送
			 *  Q_EXCEED_SIZE_LIMIT 在设置了`Q_EXCEED_SIZE_LIMIT`且尝试给`uploader`添加的文件总大小超出这个值时派送
			 *  Q_TYPE_DENIED       当文件类型不满足时触发
			 */
			uploader.on( 'error', function( type,max,file ) { 
				if( type == 'F_EXCEED_SIZE' )
		    	{
		    		caller(callBacks.onFileSingleSizeLimit,[max,file]);
		    		return;
		    	}
		    	if( type == 'Q_EXCEED_NUM_LIMIT' )
		    	{
		    		caller(callBacks.onFileNumLimit,[max,file]);
		    		return;
		    	}
		    	if( type == 'Q_EXCEED_SIZE_LIMIT' )
		    	{
		    		caller(callBacks.onExceedSizeLimit,[WebUploader.Base.formatSize(max),file]);
		    		return;
		    	}
		    	if( type == 'Q_TYPE_DENIED' )
		    	{
		    		caller(callBacks.onTypeDenied,[max,file]);
		    		return;
		    	}
		    });
			
		},
		formatSize : function(size){
			return WebUploader.Base.formatSize(size);
		}
	};
	
	return multiuploader;
	
});