define(function (require) {
	/**
	 * ### 描述 
	 * 信息交互子类
	 * 
	 * - alert：操作信息提示
	 * - error：错误信息提示
	 * - warning：警告信息提示
	 * - confirm：确认信息提示
	 * - openProcess：打开锁屏，出现进度条展示效果，一般在提交表示，或是发起后台url请求时，需要调用此方法
	 * - closeProcess：关闭进度显示条，在页面加载完毕后调用此方法，解除页面锁屏
	 * 
	 * @class $b.Msg
	 * @requires $b
	 * @requires $b.String
	 * @singleton
	 */
    var Msg = (function () {

        // 定义一个私有单例对象
        var _singletonInstance, $, _base, _locale ,_string ,_progress , _msg , _formatter ,_money , _subForMsg ,_check, _spaseKeydown;
        var initInstance = function () {
            $ = require('jquery');
            _base =  require("Base").instance();
            _locale = require("locale");
            _string = require("String").instance();
            _check = require("Check").instance();
            /*
	    	 * ### 描述
	    	 * 
	    	 * - 对异常信息或提示信息过长而导致界面毁容，需要对信息进行加工处理，专门提供给信息提示框使用；
	    	 * - 如果字节长度超过50，则只返回43位字节长度 加上一个可以点击详情的超连接字符串
	    	 * 
	    	 * ### 示例
	    	 * 
	    	 * 1、$b.subForMsg('1111111111111111111111111111111111111111111111111');  注意没有超50
	    	 * 		返回 【1111111111111111111111111
	    	 * 			111111111111111111111111】  
	    	 * 上述情况由于形成不了单词语义等，在html当中显示时，不会自动换行处理，需要进行格式化
	    	 * 
	    	 *     @example
	    	 *      _base.subForMsg('1111111111111111111111111111111111111111111111111');
	    	 *
	    	 * 2、$b.subForMsg('111111111111111111111111111111111111111111111111111111111111111111111');  注意有超50
	    	 * 		返回 【1111111111111111111111111
	    	 * 			111111111111111111--><a>提示详情</a>】   显示时，将出现在提示详情上可以点击出详情,通过点击[提示详情]查看详细信息
	    	 * 
	    	 *     @example
	    	 *      $b.EasyUi.subForMsg('111111111111111111111111111111111111111111111111111111111111111111111');
	    	 * 
	    	 * @author luow 2016-5-16
	    	 * @member $b.Msg
	    	 * @param {String} tempStr  为消息字符串
	    	 * @private
	    	 * @returns {String}  信息超长时，返回截取后的字符串信息
	    	 */
            _subForMsg = function(tempStr) {
                var rtnStr = tempStr ;
                var alt =_locale.title.tipDetail;
                var content = tempStr.replace(/\r\n/g,'</br>');
                content = tempStr.replace(/[\n]/g,'</br>');
                content = _string.replaceAll(content , "\'" , "’");
                content = _string.replaceAll(content , "\"" , "”");
                content = _string.replaceAll(content , "\(" , "（");
                content = _string.replaceAll(content , "\)" , "）");
                var detail = '<a onclick="$b.App.errorMsgDetail(\''+content+'\')" href="javascript:void 0">'+alt+'</a>';
                var i = 0;
                var tz = 0 ;
                for (var z = 0; z < tempStr.length; z++) {
                    if (tempStr.charCodeAt(z) > 255) {
                        i = i + 2;
                    } else {
                        i = i + 1;
                    }
                    if(i >= 25  && tz == 0){
                        tz = z ;
                        rtnStr = tempStr.slice(0, (z + 1)) + "</br>";
                    }
                    if (i >= 41) {
                        rtnStr =rtnStr + tempStr.slice((tz + 1), (z + 1)) + "..."+ detail;
                        break;
                    }
                }
                if(tz > 0 && tz < _string.getByteLen(tempStr) && rtnStr.indexOf("onclick") == -1){
                    rtnStr =rtnStr + tempStr.slice((tz + 1), tempStr.length);
                }

                return rtnStr;
            };
            _spaseKeydown = function(spaceKey){
            	if(spaceKey || $b.Base.cache.get("_spaseKeydown") == 'open'){
            		var objEvt = $._data($(document)[0] ,"events");
            		if(!objEvt || !("keydown" in objEvt)){
                		$(document).keydown(function(e){
                			var btns = $(".messager-button > a.l-btn");
                    		if(e.which===32){
                    			btns[0].click();
                    		}
                    		if(e.which===27 && btns.length === 2){
                    			btns[1].click();
                    		}
                    	});
                    }
            	}
            };
            return {
            	/**
     	    	 * ### 描述
     	    	 * 操作信息提示
     	    	 * 
     	    	 * ### 示例一，普通提示
     	    	 * 
     	    	 *     @example
     	    	 *      $b.Msg.alert('请选择要操作的数据');
     	    	 * 
     	    	 * ### 示例二，根据国际化资源的key值获取对应的提示信息
     	    	 * 
     	    	 *     @example
     	    	 *      $b.Msg.alert('error.loadData',true);
     	    	 * 
     	    	 * ### 示例三，根据国际化资源的key值获取对应的提示信息，点击完确认按钮后，希望执行自己的方法
     	    	 * 
     	    	 *     @example
     	    	 *     var fn = function(){
     	    	 *     		//todo ...
    	    	 *     };
    	    	 *     //信息提示
    	    	 *     $b.Msg.alert('error.loadData',fn ,true);
    	    	 * 
    	    	 * ### 示例四，根据国际化资源的key值获取对应的提示信息，点击完确认按钮后，页面的文本框获取焦点
     	    	 * 
     	    	 *     @example
     	    	 *     var userName = $('#inputId');
    	    	 *     //信息提示
    	    	 *     $b.Msg.alert('error.loadData',userName ,true);
     	    	 * 
     		     * @param {String} msg  缓存对象对应的key，通过此key来获取缓存对象
     		     * @param {Object} fn  一个复杂的对象，可以是函数，可以是页面元素对象，可以是boolean值 
     		     * 
     		     * - 1、回调函数，点击确定完后，将执行此函数 
     		     * - 2、页面元素对象，在点击信息提示框的确认按钮后，尝试获取对象的焦点
     		     * - 3、如果是boolean值true，则会以第一个参数msg为key，获取国际化资源文件中对应的描述信息。获取不取则返回空
     		     * 
     		     * @param {String} i18n  提示的信息是否为国际化的KEY值，如果fn设置为true了，此参数无效。
     	    	 * @member $b.Msg
     	    	 * @method alert
     	    	 */
                alert: function (msg, fn, i18n , spaceKey) {
                    var title = _locale.title.systemTip,
                        type = 'info';
                    if (fn === true || i18n === true) {
                        msg = _base.i18n(msg);
                    }
                    msg = _base.unescapeHtml(msg);
                    msg = _subForMsg(msg);
                    switch (typeof fn) {
                        case 'object':
                            top.$.messager.alert(title, msg, type, function () {
                                fn.focus();
                            });
                            break;
                        case 'function':
                            top.$.messager.alert(title, msg, type, fn);
                            break;
                        default:
                            top.$.messager.alert(title, msg, type);
                            break;
                    }
                    _spaseKeydown(spaceKey);
                },
                /**
     	    	 * ### 描述
     	    	 * 错误信息提示
     	    	 * 
     	    	 * ### 示例一，普通提示
     	    	 * 
     	    	 *     @example
     	    	 *      $b.Msg.error('用户名称不合法');
     	    	 * 
     	    	 * ### 示例二，根据国际化资源的key值获取对应的提示信息
     	    	 * 
     	    	 *     @example
     	    	 *      $b.Msg.error('error.loadData',true);
     	    	 * 
     	    	 * ### 示例三，根据国际化资源的key值获取对应的提示信息，点击完确认按钮后，希望执行自己的方法
     	    	 * 
     	    	 *     @example
     	    	 *     var fn = function(){
     	    	 *     		//todo ...
    	    	 *     };
    	    	 *     //信息提示
    	    	 *     $b.Msg.error('error.loadData',fn ,true);
    	    	 * 
    	    	 * ### 示例四，根据国际化资源的key值获取对应的提示信息，点击完确认按钮后，页面的文本框获取焦点
     	    	 * 
     	    	 *     @example
     	    	 *     var userName = $('#inputId');
    	    	 *     //信息提示
    	    	 *     $b.Msg.error('error.loadData',userName ,true);
     	    	 * 
     		     * @param {String} msg  缓存对象对应的key，通过此key来获取缓存对象
     		     * @param {Object} fn  一个复杂的对象，可以是函数，可以是页面元素对象，可以是boolean值 
     		     * 
     		     * - 1、回调函数，点击确定完后，将执行此函数 
     		     * - 2、页面元素对象，在点击信息提示框的确认按钮后，尝试获取对象的焦点
     		     * - 3、如果是boolean值true，则会以第一个参数msg为key，获取国际化资源文件中对应的描述信息。获取不取则返回空
     		     * 
     		     * @param {String} i18n  提示的信息是否为国际化的KEY值，如果fn设置为true了，此参数无效。
     	    	 * @member $b.Msg
     	    	 * @method error
     	    	 */
                error: function (msg, fn, i18n ,spaceKey) {
                    var title = _locale.title.systemError,
                        type = 'error';
                    if (fn === true || i18n === true) {
                        msg = _base.i18n(msg);
                    }
                    msg = _base.unescapeHtml(msg);
                    msg = _subForMsg(msg);
                    switch (typeof fn) {
                        case 'object':
                            top.$.messager.alert(title, msg, type, function () {
                                fn.focus();
                            });
                            break;
                        case 'function':
                            top.$.messager.alert(title, msg, type, fn);
                            break;
                        default:
                            top.$.messager.alert(title, msg, type);
                            break;
                    }
                    _spaseKeydown(spaceKey);
                },
                /**
     	    	 * ### 描述
     	    	 * 警告信息提示
     	    	 * 
     	    	 * ### 示例一，普通提示
     	    	 * 
     	    	 *     @example
     	    	 *      $b.Msg.warning('用户名称不合法');
     	    	 * 
     	    	 * ### 示例二，根据国际化资源的key值获取对应的提示信息
     	    	 * 
     	    	 *     @example
     	    	 *      $b.Msg.warning('error.loadData',true);
     	    	 * 
     	    	 * ### 示例三，根据国际化资源的key值获取对应的提示信息，点击完确认按钮后，希望执行自己的方法
     	    	 * 
     	    	 *     @example
     	    	 *     var fn = function(){
     	    	 *     		//todo ...
    	    	 *     };
    	    	 *     //信息提示
    	    	 *     $b.Msg.warning('error.loadData',fn ,true);
    	    	 * 
    	    	 * ### 示例四，根据国际化资源的key值获取对应的提示信息，点击完确认按钮后，页面的文本框获取焦点
     	    	 * 
     	    	 *     @example
     	    	 *     var userName = $('#inputId');
    	    	 *     //信息提示
    	    	 *     $b.Msg.warning('error.loadData',userName ,true);
     	    	 * 
     		     * @param {String} msg  缓存对象对应的key，通过此key来获取缓存对象
     		     * @param {Object} fn  一个复杂的对象，可以是函数，可以是页面元素对象，可以是boolean值 
     		     * 
     		     * - 1、回调函数，点击确定完后，将执行此函数 
     		     * - 2、页面元素对象，在点击信息提示框的确认按钮后，尝试获取对象的焦点
     		     * - 3、如果是boolean值true，则会以第一个参数msg为key，获取国际化资源文件中对应的描述信息。获取不取则返回空
     		     * 
     		     * @param {String} i18n  提示的信息是否为国际化的KEY值，如果fn设置为true了，此参数无效。
     	    	 * @member $b.Msg
     	    	 * @method warning
     	    	 */
                warning: function (msg, fn, i18n , spaceKey) {
                    var title = _locale.title.systemWarning,
                        type = 'warning';
                    if (fn === true || i18n === true) {
                        msg = _base.i18n(msg);
                    }
                    msg = _base.unescapeHtml(msg);
                    msg = _subForMsg(msg);
                    switch (typeof fn) {
                        case 'object':
                            top.$.messager.alert(title, msg, type, function () {
                                fn.focus();
                            });
                            break;
                        case 'function':
                            top.$.messager.alert(title, msg, type, fn);
                            break;
                        default:
                            top.$.messager.alert(title, msg, type);
                            break;
                    }
                    _spaseKeydown(spaceKey);
                },
                /**
     	    	 * ### 描述
     	    	 * 确认信息提示
     	    	 * 
     	    	 * ### 示例一，点击确认后执行函数提示
     	    	 * 
     	    	 *     @example
     	    	 *     var okFn = function(){
     	    	 *     		//todo 点击确认需要执行的逻辑
    	    	 *     };
     	    	 *     $b.Msg.confirm('确认要删除选择的记录码？' , okFn);
     	    	 * 
     	    	 * ### 示例二，根据国际化资源的key值获取对应的提示信息
     	    	 * 
     	    	 *     @example
     	    	 *     var okFn = function(){
     	    	 *     		//todo 点击确认需要执行的逻辑
    	    	 *     };
     	    	 *     $b.Msg.confirm('local.butn.key' , okFn, true);
     	    	 * 
     	    	 * ### 示例三，根据国际化资源的key值获取对应的提示信息，点击确认按钮调用处理函数，点击取消调用处理函数
     	    	 * 
     	    	 *     @example
     	    	 *     var okFn = function(){
     	    	 *     		//todo 点击确认需要执行的逻辑
    	    	 *     };
    	    	 *     var cancelFn = function(){
     	    	 *     		//todo 点击取消需要执行的逻辑
    	    	 *     };
    	    	 *     //信息提示
    	    	 *     $b.Msg.confirm('error.loadData',fn , cancelFn ,true);
    	    	 * 
     		     * @param {String} msg  缓存对象对应的key，通过此key来获取缓存对象
     		     * @param {Object} ok  点击确认后，回调用户的函数
     		     * @param {Object} cancel  一个复杂的对象，可以是函数，可以是页面元素对象，可以是boolean值 
     		     * 
     		     * - 1、回调函数，点击获取后，将执行此函数 
     		     * - 2、如果是boolean值true，则会以第一个参数msg为key，获取国际化资源文件中对应的描述信息。获取不取则返回空
     		     * 
     		     * @param {String} i18n  提示的信息是否为国际化的KEY值，如果cancel设置为true了，此参数无效。
     	    	 * @member $b.Msg
     	    	 * @method confirm
     	    	 */
                confirm: function (msg, ok, cancel, i18n ,spaceKey) {
                    if (cancel === true || i18n === true) {
                        msg = _base.i18n(msg);
                    }
                    top.$.messager.confirm(_locale.title.systemConfirm, msg, function (v) {
                        if (v && typeof ok === 'function') {
                            ok();
                        } else if (!v && typeof cancel == 'function') {
                            cancel();
                        }
                    });
                    _spaseKeydown(spaceKey);
                },
                /**
    	    	 * ### 描述
    	    	 * 打开进度条
    	    	 * 
    	    	 * ### 示例
    	    	 * 
    	    	 * 打开锁屏，出现进度条展示效果，一般在提交表示，或是发起后台url请求时，需要调用此方法
    	    	 * 
    	    	 *     @example
    	    	 *     $b.Msg.openProgress();
    	    	 * 
    		     * @param {Object} options  进度条选项,传空时采用默认的配置。对象结构：{title:'进度条标题' ,text:'提示内容'}
    		     * @param {Number} seconds seconds=15  进度条滚动的时间超过了此值后，自动解除锁屏，并提示操作超时。单位秒
    		     * @param {Boolean} quietly quietly=false 进度条滚动超出指定时间后，是否“安静”地关闭进度条，如果为true，则关闭但不提示超时，如果为false，关闭并且提示超时
    	    	 * @member $b.Msg
    	    	 * @method openProgress
    	    	 */
                openProgress: function (options, seconds, quietly){
                    seconds = _check.isNullOrEmptyStr(seconds) ? 15 : seconds;
                    options = $.extend({
                        title: _locale.title.pleaseWait,
                        text: _locale.operate.systemDo
                    }, options);
                    var text = options.text,
                        p = top.$.messager.progress(options);
                    _base.showClock(function (time, sec) {
                        p.find('div.messager-p-bar').progressbar({
                            text: text + time,
                            onChange: function (value) {
                                if (sec > seconds && seconds > 0) {
                                	if(!quietly){
                                		_singletonInstance.error(_locale.error.requestTimeout);
									}
                                	_singletonInstance.closeProgress();
                                }
                            }
                        });
                    });
                    return p;
                },
                /**
    	    	 * ### 描述
    	    	 * 去掉进度条
    	    	 * 
    	    	 * ### 示例
    	    	 * 
    	    	 * 关闭进度显示条，在页面加载完毕后调用此方法，解除页面锁屏
    	    	 * 
    	    	 *     @example
    	    	 *     $b.Msg.closeProgress();
    	    	 * 
    	    	 * @member $b.Msg
    	    	 * @method closeProgress
    	    	 */
                closeProgress: function () {
                    top.$.messager.progress('close');
                }
            }
        };
        return {
            getInstance: function () {
                // 如果单例对象不存在，则建立此对象，如果存在，直接返回
                if (!_singletonInstance) {
                    _singletonInstance = initInstance();
                }
                return _singletonInstance;
            }
        };
    })();
    return {
        instance: function () {
            return Msg.getInstance();
        }
    };
});