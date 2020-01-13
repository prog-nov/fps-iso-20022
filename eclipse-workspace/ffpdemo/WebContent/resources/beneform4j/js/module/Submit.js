define(function(require) {
	/**
	 * ### 描述
	 * 数据提交
	 * 
	 * - ajax提交
	 * - Form表单提交
	 * @class $b.Submit
	 * @requires $b
	 * @singleton
	 */
	var Submit = (function() {
		// 定义一个私有单例对象
		var singletonInstance,$ ,_msg ,_base, _locale;// undefined
		var initInstance = function() {
			
			$ = require('jquery');
			_msg = require('Msg').instance();
			_base = require('Base').instance();
			_locale = require('locale');
			return {
				/**
    	    	 * ### 描述
    	    	 * Post方法提交
    	    	 * 
    	    	 * ### 示例
    	    	 * 
    	    	 *     @example
    	    	 *     $b.Submit.submit('a/b/c',['1','2']);
    	    	 * 
    		     * @param {String} url  请求URL
    		     * @param {Object} options  对象字面量形式，支持函数、数组和嵌套对象
    	    	 * @member $b.Submit
    	    	 * @method submit
    	    	 */
                submit: function (url, options) {
                    if (typeof options === 'function') {
                        options = options.apply(this);
                    }
                    var fragment = document.createDocumentFragment(),
                        index = url.indexOf('?'),
                        param,
                        strParam,
                        _newPostForm,
                        addNode = function (name, value) {
                            if (!name && !value) {
                                return;
                            } else if (!value || typeof value === 'string' || typeof value === 'boolean' || typeof value === 'number') {
                                var node = document.createElement('input');
                                node.type = 'hidden';
                                node.name = name;
                                if (value) {
                                    node.value = value;
                                }
                                fragment.appendChild(node);
                            } else if (Object.prototype.toString.call(value) === '[object Array]') {
                                for (var i = 0, len = value.length; i < len; i++) {
                                    addNode(name, value[i]);
                                }
                            } else if (typeof value === 'function') {
                                addNode(name, value.apply(this));
                            } else if (typeof value === 'object') {
                                for (var subname in value) {
                                    addNode((name ? (name + '.') : '') + subname, value[subname]);
                                }
                            }
                        };
                    //url中包含的参数
                    if (-1 !== index && index !== url.length - 1) {
                        strParam = url.substring(index + 1).split('&');
                        url = url.substring(0, index + 1);
                        for (var i = 0, len = strParam.length; i < len; i++) {
                            if (strParam[i]) {
                                if (-1 === strParam[i].indexOf('=')) {
                                    addNode(strParam[i]);
                                } else {
                                    param = strParam[i].split('=');
                                    addNode(param[0], param[1]);
                                }
                            }
                        }
                    }
                    //额外的参数
                    addNode(null, options);

                    _newPostForm = document.createElement('FORM');
                    _newPostForm.method = 'POST';
                    _newPostForm.action = url;
                    _newPostForm.acceptCharset = 'UTF-8';
                    _newPostForm.appendChild(fragment);
                    document.body.appendChild(_newPostForm);
                    if (!options || options.needBlock !== false) {
                    	_msg.openProgress();
                    }
                    _newPostForm.submit();
                    document.body.removeChild(_newPostForm);
                },
                /**
    	    	 * ### 描述
    	    	 * ajax调用
    	    	 * 
    	    	 * ### 示例
    	    	 * 
    	    	 *     @example
    	    	 *     $b.Submit.ajaxSubmit('a/b/c',['1','2']);
    	    	 * 
    		     * @param {String} url  请求URL
    		     * @param {Object} options  状态了请求参数的配置项，是一个对象
    		     * 
    		     * - 属性needBlock：是否需要锁屏处理，默认值为true，需要锁屏
    		     * - 属性async：ajax的调用方式，同步还是异步，默认是同步处理。(默认: true) 默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
    		     * - 属性dataType：客户端数据解析的方式。如果指定为json类型，则会把获取到的数据作为一个JavaScript对象来解析，并且把构建好的对象作为结果返回；如果指定为html类型，任何内嵌的JavaScript都会在HTML作为一个字符串返回之前执行；支持的数据类型有html、json、jsonp、script或者text
    		     * - 属性closeProgress：请求完成时，是否需要解除屏幕锁定，与needBlock参数配套使用
    		     * - 其他用户参数属性，需要传递到后台的信息。
    		     * 
    		     * @param {Object} success  请求成功的回调函数，此参数可以不传，系统提示操作成功
    		     * @param {Object} failure  请求失败的回调函数，此参数可以不传，系统提示操作失败
    		     * 
    		     * - 如果返台后台的状态值为false，则会回调此函数
    		     * - 如果ajax调用失败，需要结合failureExcuteFlag标记为true时回调此函数，如果failureExcuteFlag为false时，则不会调用此回调函数
    		     * 
    		     * @param {Object} failureExcuteFlag  对象字面量形式，支持函数、数组和嵌套对象
    	    	 * @member $b.Submit
    	    	 * @method ajaxSubmit
    	    	 */
                ajaxSubmit: function(url, options, success, failure, failureExcuteFlag)
                {
                    options = options || {};
                    var param,
                        strParam,
                        index = url.indexOf('?'),
                        needBlock = options.needBlock !== false,
                        async = options.async !== false,
                        dataType = options.dataType || 'json',
                        closeProgress = options.closeProgress !== false;
                    if(!options.mime){
                        options.mime = 'json';
                    }
                    //url中包含的参数
                    if(-1 !== index && index !== url.length-1){
                        strParam = url.substring(index+1).split('&');
                        url = url.substring(0, index+1);
                        for(var i=0,len=strParam.length; i<len; i++)
                        {
                            if(strParam[i]){
                                if(-1 === strParam[i].indexOf('=')){
                                    options[strParam[i]]='';
                                }else{
                                    param = strParam[i].split('=');
                                    options[param[0]]=param[1];
                                }
                            }
                        }
                    }
                    _base.deleteProperties(options,['needBlock','async','dataType']);
                    $.ajax({
                        url 	: url,
                        type	: 'POST',
                        async 	: async,
                        data	: options,
                        dataType: dataType,
                        beforeSend : function(){
                            if(needBlock){
                            	_msg.openProgress();
                            }
                        },
                        success : function(data, status, xhr){
                            if(dataType === 'json'){
                                if(typeof data === 'string'){
                                    data = _base.parseJson(data);
                                }
                                var status = data.success,
                                    msg = data.message || data.info,
                                    data = data.data || data;
                                if(status === false){
                                    if(typeof failure === 'function'){
                                        failure.call(xhr, data, options);
                                    }else if(data.detail || data.message){
                                    	_msg.error(data.detail || data.message);
                                    }else if($.isArray(data.errors)){
                                    	_msg.error($.map(data.errors, function(i){return i.message;}).join('</br>'));
                                    }else{
                                    	_msg.error(_base.i18n('error.ajaxSubtmitError',url));
                                    }
                                    if(needBlock){
                                    	_msg.closeProgress();
                                    }
                                }else{
                                    if(typeof success === 'function'){
                                        success.call(xhr, data, options);
                                    }else{
                                    	_msg.alert(msg || _locale.operate.doOk);
                                    }
                                }
                            }else if(typeof success === 'function'){
                                success.call(xhr, data, options, status);
                            }
                        },
                        error	: function(xhr, status, error){
                            if(needBlock){
                            	_msg.closeProgress();
                            }
                            if(failureExcuteFlag && typeof failure === 'function'){
                                failure.call(xhr, status, error);
                            }
                            _msg.error(_base.i18n('error.ajaxDebugError',url,xhr.responseText));

                        },
                        complete: function(){
                            if(needBlock && closeProgress){
                            	_msg.closeProgress();
                            }
                        }
                    });
                },
                /**
    	    	 * ### 描述
    	    	 * ajax提交Form
    	    	 * 
    	    	 * ### 示例
    	    	 * 
    	    	 *     @example
    	    	 *     $b.Submit.ajaxSubmitForm('a/b/c',['1','2']);
    	    	 * 
    		     * @param {String} form  jquery选择器选择到的from对象
    		     * @param {Object} options  可以是一个参数对象，也可以是一个函数
    		     * 
    		     * - 参数对象：{userName:'zs',sex:'mail'},以json的形式封装的对象，后台可以直接通过key获取到对应的参数，或是直接通过formbean进行接收前端的参数
    		     * - 函数：提交成功后，执行的函数
    		     * 
    		     * @param {Object} success  请求成功的回调函数，此参数可以不传，系统提示操作成功
    		     * @param {Object} failure  请求失败的回调函数，此参数可以不传，系统提示操作失败
    	    	 * @member $b.Submit
    	    	 * @method ajaxSubmitForm
    	    	 */
                ajaxSubmitForm: function(form, options, success, failure)
                {
                    var params, succ, fail;
                    if(typeof options === 'function'){//如果传入函数，作为回调函数
                        params = {};
                        succ = options;
                        fail = failure || success;
                    }else{
                        params = options || {};
                        succ = success;
                        fail = failure;
                    }

                    $(form).form('submit', {
                        url :  params.url,
                        onSubmit: function(param){
                            if($(this).form('validate')){
                                $.extend(param, params);
                                _msg.openProgress();
                            }else{
                                return false;
                            }
                        },
                        success : function(data){
                        	_msg.closeProgress();
                            if(typeof data === 'string'){
                                data = _base.parseJson(data);
                            }
                            var status = data.success,
                                msg = data.message || data.info,
                                data = data.data || data;
                            if(status === false){
                                if(typeof fail === 'function'){
                                    fail.call(this, data, params);
                                }else if(data.detail || data.message){
                                	_msg.error(data.detail || data.message);
                                }else if($.isArray(data.errors)){
                                	_msg.error($.map(data.errors, function(i){return i.message;}).join('</br>'));
                                }else{
                                	_msg.error(_base.i18n('error.ajaxSubmitError',params.url));
                                }
                            }else{
                                if(typeof succ === 'function'){
                                    succ.call(this, data, params);
                                }else{
                                	_msg.alert(msg || _locale.operate.doOk);
                                }
                            }
                        }
                    });
                },
                
                /**
    	    	 * ### 描述
    	    	 * ajax方式调用标有Ajax注解的方法，并返回JSON数据
    	    	 * 
    	    	 * ### 示例
    	    	 * 
    	    	 *     @example
    	    	 *     $b.Submit.execAjaxMethod('a/b/c',['1','2']);
    	    	 * 
    		     * @param {String} ajaxId  Ajax注解中的id
    		     * @param {Object} options  状态了请求参数的配置项，是一个对象
    		     * 
    		     * - 属性needBlock：是否需要锁屏处理，默认值为true，需要锁屏
    		     * - 属性async：ajax的调用方式，同步还是异步，默认是同步处理。(默认: true) 默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
    		     * - 属性dataType：客户端数据解析的方式。如果指定为json类型，则会把获取到的数据作为一个JavaScript对象来解析，并且把构建好的对象作为结果返回；如果指定为html类型，任何内嵌的JavaScript都会在HTML作为一个字符串返回之前执行；支持的数据类型有html、json、jsonp、script或者text
    		     * - 属性closeProgress：请求完成时，是否需要解除屏幕锁定，与needBlock参数配套使用
    		     * - 其他用户参数属性，需要传递到后台的信息。
    		     * 
    		     * @param {Object} success  请求成功的回调函数，此参数可以不传，系统提示操作成功
    		     * @param {Object} failure  请求失败的回调函数，此参数可以不传，系统提示操作失败
    		     * 
    		     * - 如果返台后台的状态值为false，则会回调此函数
    		     * - 如果ajax调用失败，需要结合failureExcuteFlag标记为true时回调此函数，如果failureExcuteFlag为false时，则不会调用此回调函数
    		     * 
    		     * @param {Object} failureExcuteFlag  对象字面量形式，支持函数、数组和嵌套对象
    	    	 * @member $b.Submit
    	    	 * @method execAjaxMethod
    	    	 * @since 1.1.0
    	    	 */
        		execAjaxMethod: function(ajaxId, options, success, failure, failureExcuteFlag){
        			return $b.Submit.ajaxSubmit($b.context.root+'/ajax.AjaxServlet', $.extend({ajaxHandlerKey:ajaxId, async:false},options), success, failure, failureExcuteFlag);
        		},
        		/**
    	    	 * ### 描述
    	    	 * 含状态的Ajax提交，类似于有两个线程并发，一个线程用于执行业务逻辑，另一个线程不断轮询业务执行是否完成，在轮询过程中包含进度条
    	    	 * 
    	    	 * ### 示例
    	    	 * 
    	    	 *     @example
    	    	 *     $b.Submit.ajaxStatusSubmit('a/b/c',['1','2']);
    	    	 * 
    		     * @param {Function} submitFn  封装了需要执行业务逻辑的函数
    		     * @param {String} memo  业务逻辑说明
    		     * @param {Boolean} needBlock  是否需要进度条
    		     * 
    	    	 * @member $b.Submit
    	    	 * @method ajaxStatusSubmit
    	    	 * @since 1.1.0
    	    	 */
        		ajaxStatusSubmit:	function(submitFn, memo, needBlock){
        			var ajaxOptions = {
        					ajaxStatusId : $b.Base.guid(),
        					ajaxStatusMemo:memo||'Ajax', 
        					needBlock: false
        				},
        				needBlock = needBlock !== false,
        				_queryAjaxStatus = function(id){
        					var options = {'ajaxStatusId': id, needBlock: false};
        					$b.Submit.execAjaxMethod('selectAjaxStatus', options, function(rs){
        						if(rs == 1){//仍在执行中，隔2s再次查询
        							setTimeout(function(){_queryAjaxStatus(id);},2000);
        						}else if(rs == 2){
        							$b.Msg.alert('执行出现异常');
        							$b.Submit.execAjaxMethod('deleteAjaxStatus', {'ajaxStatusId': id, needBlock: false});
        							if(needBlock){
        								$b.Msg.closeProgress();
        							}
        						}else{
        							$b.Submit.execAjaxMethod('deleteAjaxStatus', {'ajaxStatusId': id, needBlock: false});
        							if(needBlock){
        								$b.Msg.closeProgress();
        							}
        						}
        					});
        				};
        			
    				$b.Submit.execAjaxMethod('setAjaxStatus', ajaxOptions, function(){
        				if(needBlock){
        					$b.Msg.openProgress(undefined, 15, true);
        				}
        				submitFn(ajaxOptions.ajaxStatusId);
        				_queryAjaxStatus(ajaxOptions.ajaxStatusId);
        			});
        		}
			}
		};
		return {
			getInstance : function() {
				// 如果单例对象不存在，则建立此对象，如果存在，直接返回
				if (!singletonInstance) {
					singletonInstance = initInstance();
				}
				return singletonInstance;
			}
		};
	})();
	return {
		instance : function() {
			return Submit.getInstance();
		}
	};
});