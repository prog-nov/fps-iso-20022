define(function(require) {
	/**
	 * ### 描述
	 * 文件处理工具类集合
	 * 
	 * - 设置缓存
	 * - 获取缓存值
	 * - 获取并移除缓存
	 * - 清空缓存
	 * 
	 * @class $b.Base.file
	 * @requires $b
	 * @singleton
	 * ### 示例
	 * 
	 *     @example
	 *     var fileObject = $b.File;
	 * 
	 */
	var File = (function() {
		// 定义一个私有单例对象
		var _singletonInstance,$ ,_string , _check;// undefined
		var initInstance = function() {
			
			$  = require('jquery');
			_string = require("String").instance();
			_check =  require("Check").instance();
			return {
				/**
		    	 * ### 描述
		    	 * 文件大小渲染函数，将文件的字节大小转换为带合适单位的字符串
		    	 * 
		    	 * ### 示例
		    	 * 
		    	 *     @example
		    	 *      $b.Base.file.fileSizeRenderer('1024001');
		    	 *     
		    	 * - 结果
		    	 * 1000KB
		    	 * 
		    	 * @param {String} v  文件大小，例如‘1024001’
		    	 * @returns {String}  带单位的字符串信息
		    	 * @member $b.Base.file
		    	 * @method fileSizeRenderer
		    	 */
                fileSizeRenderer:function(v){
                    var index, srcSize, size, unitArr;
                    unitArr = new Array("Bytes","KB","MB","GB","TB","PB");
                    srcSize = parseFloat(v);
                    index = Math.floor(Math.log(srcSize)/Math.log(1024));
                    size = Math.round(srcSize/Math.pow(1024,index),2);
                    return size+unitArr[index];
                },
                /**
		    	 * ### 描述
		    	 * 文件下载，支持多文件下载，文件下载时，会自动以压缩包的形式下载到前端IP机上。
		    	 * 
		    	 * ### 示例一 下载单个文件
		    	 * 
		    	 *     @example
		    	 *      $b.Base.file.download('docIds=1234531321546');
		    	 *      
		    	 * ### 示例二 下载多个文件[数组对象参数]
		    	 * 
		    	 *     @example
		    	 *      $b.Base.file.download(['docIds=1234531321546','docIds=1234531321547']);
		    	 * 
		    	 * @param {String} data  是string 或者 array/object 必输项，需要传入附件的id  格式（docIds=1234531321546）
		    	 * @member $b.Base.file
		    	 * @method download 
		    	 */
                download : function(data){
                    if(data){
                        data = typeof data == 'string' ? data : jQuery.param(data);
                        // 把参数组装成 form的 input
                        var inputs = '';
                        jQuery.each(data.split('&'), function(){
                            var pair = this.split('=');
                            inputs+='<input type="hidden" name="'+ pair[0] +'" value="'+ pair[1] +'" />';
                        });
                        // request发送请求
                        jQuery('<form action="'+ server_consts.root+'/doc/download" method="post">'+inputs+'</form>').appendTo('body').submit().remove();
                    };
                },
                /**
		    	 * ### 描述
		    	 * 根据文档编号，删除单个文件
		    	 * 
		    	 * ### 示例
		    	 * 
		    	 *     @example
		    	 *      $b.Base.file.deleteFile({'docId' : '23456454132565445'}, function { //do success }， function{ //do error });
		    	 * 
		    	 * @param {Object} data  将要被删除的参数对象，为一个json对象，key为docId，value为对应的附件id
			     * @param {Object} success  删除成功的回调函数
			     * @param {Object} failure  删除失败的回调函数
			     * @member $b.Base.file
		    	 * @method deleteFile  
		    	 */
                deleteFile : function(data,success, failure){
                    if(data && data.docId){
                        $b.Submit.ajaxSubmit($b.context.root + "/doc/delete" , data , success, failure);
                    }else{
                        $b.Msg.alert(locale.system.document.content.param);
                    };
                }
			}
		};
		return {
			getInstance : function() {
				// 如果单例对象不存在，则建立此对象，如果存在，直接返回
				if (!_singletonInstance) {
					_singletonInstance = initInstance();
				}
				return _singletonInstance;
			}
		};
	})();
	return {
		instance : function() {
			return File.getInstance();
		}
	};
});