define(function(require) {
	/**
	 * ### 描述
	 * 下载公共类
	 * 
	 * - 自动获取下载标题
	 * - 下载公共方法
	 * - 表单控件下载方法
	 * - 表格控件下载方法
	 * 
	 * @class $b.Download
	 * @requires $b
	 * @singleton
	 * ### 示例
	 * 
	 *     @example
	 *     var DownloadObject = $b.Download;
	 * 
	 * @class $b.Base.Download
	 */
	var Download = (function() {
		// 定义一个私有单体对象
		var _singletonInstance, $;// undefined
		var initInstance = function() {
			
			$  = require('jquery');
			return {
				/**
		    	 * ### 描述
		    	 * 自动获取下载标题
		    	 * 
		    	 * @param {Object} form 表单对象
		    	 * @param {Object} grid 表格对象
		    	 * @returns {Array} 含标题和副标题的字符串数组，副标题由表单条件组成
		    	 * @member $b.Base.Download
		    	 * @method getDownloadTitle
		    	 */
				getDownloadTitle: function(form, grid){
					var title = ['',''];
					if(grid){
						title[0] = $(grid).datagrid('options').title || '数据列表';
					}
					if(form){
						var sub = '';
						$('input:enabled,select:enabled,textarea:enabled', $(form)[0]).each(function(){
							var $prev = $(this).parent('td').prev('.labelTd');
							if($prev.length>0){
								var label = $prev.eq(0).html();
								if(label && this.value){
									sub += ' '+label+'：'+this.value;
								}
							}
						});
						title[1] = sub;
					}
					return title;
				},
                /**
		    	 * ### 描述
		    	 * 下载公共方法，支持表单下载、表格下载，以及其它的对象下载
		    	 * 
		    	 * ### 示例一 下载单个文件
		    	 * 
		    	 *     @example
		    	 *      $b.Base.Download.download('docIds=1234531321546');
		    	 *      
		    	 * ### 示例二 下载多个文件[数组对象参数]
		    	 * 
		    	 *     @example
		    	 *      $b.Base.Download.download(['docIds=1234531321546','docIds=1234531321547']);
		    	 * 
		    	 * @param {String} downloadId  是string 或者 object 必输项，需要传入的下载ID，或者包括下载ID的对象
		    	 * @param {Object} options  下载参数
		    	 * 
		    	 * - downloadId：下载ID
		    	 * - form：下载的表单
		    	 * - grid：下载的表格
		    	 * - url：下载的url地址
		    	 * - showType：下载文件显示类型 attachment表示附件形式
		    	 * - buildType：下载对象的构建类型
		    	 * - autoTitle：是否自动获取标题
		    	 * - downloadTitle：下载标题
		    	 * - downloadSubTitle：下载副标题
		    	 * - downloadSuffix：下载文件后缀
		    	 * @member $b.Base.Download
		    	 * @method download 
		    	 */
				download :	function(downloadId, options, downloadForm, downloadGrid){
					/**
					 * 为了兼容各种参数写法，这里对参数做调整，最终将参数包装成一个options对象
					 * 1.如果第一个参数为object，且不为null，直接作为下载的参数
					 * 2.如果第一个参数为string，作为下载模型ID处理
					 * 3.如果第二个参数为boolean，表示是否自动获取标题，相当于配置options.autoTitle
					 */
					if(typeof downloadId === 'object' && null !== downloadId){
						options = downloadId;
					}else{
						if(typeof options === 'boolean'){
							options = {autoTitle:options,downloadId:downloadId};
						}else{
							options = options || {};
							options.downloadId = downloadId || options.downloadId;	
						}
					}
					options.form = options.form || downloadForm;
					options.grid = options.grid || downloadGrid;
					
					if(options.form && !$(options.form).form('validate')){
						return false;
					}
					$b.Submit.ajaxStatusSubmit(function(id){
						var form = options.form, 
							grid = options.grid, 
							url = options.url,
							autoTitle = options.autoTitle,
							title,
							params = {
								showType:'attachment',
								buildType:'DATA-EXPORT',
								downloadSuffix:'xlsx'
							};
						$b.Base.deleteProperties(options,['form','grid','url','autoTitle']);
						if(autoTitle === true && !params.downloadTitle){//如果自动获取标题
							title = $b.getDownloadTitle(form, grid);
							params.downloadTitle = title[0];
							params.downloadSubTitle = title[1];
						}
						if(grid){//如果传入了表格，做为表格的下载
							var goptions = $(grid).datagrid('options'),
								queryParam = goptions.customQueryParam || form,
				    			gparams;
					    	if(typeof queryParam === 'function'){
					    		gparams = queryParam.apply(this, arguments);
					    	}else if($(queryParam).is('.easyui-form')){
					    		gparams = $(queryParam).form('getValues');
					    	}else if(typeof queryParam === 'string'){
					    		gparams = $.serializeObject($('#'+queryParam));
					    	}else if(typeof queryParam === 'object'){
					    		gparams = $.extend({}, queryParam);
					    	}else{
					    	}
					    	$.extend(params, gparams);
					    	url = url || goptions.url;
							options.downloadId = options.downloadId || goptions.downloadId;
						}else if(form){
							var foptions = $(form).form('options'),
								fparams = $(form).form('getValues');
							$.extend(params, fparams);
							url = url || foptions.url; 
							options.downloadId = options.downloadId || foptions.downloadId;
						}else{
							//throw new Error('请至少传入form和grid中的一个参数');
						}
						
						if(typeof options.onBeforeDownload === 'function'){//可以使用onBeforeDownload方法修改参数
							options.onBeforeDownload(params);
							delete options.onBeforeDownload;
						}
						params = $.extend(params, options);//复制传入的其它参数
						//下面三个参数为强制参数，不能被覆盖
						params.needBlock = false;
						params.ajaxStatusId = id;
						params.mime = 'download';
						try{
							$b.Submit.submit(url, params);
						}catch(e){
							//
						}
					}, '下载');
				}
			}
		};
		return {
			getInstance : function() {
				// 如果单体对象不存在，则建立此对象，如果存在，直接返回
				if (!_singletonInstance) {
					_singletonInstance = initInstance();
				}
				return _singletonInstance;
			}
		};
	})();
	
	return {
		instance : function() {
			return Download.getInstance();
		}
	};
});