define(function(require) {
	/**
	 * ### 描述 
	 * 平台公共对象，为平台的基础核对对象类，封装了基本的变更、属性、方法。
	 * 
	 * - 国际化处理函数
	 * - 生成唯一键
	 * - json的解析转换
	 * - html的解析转换
	 * - 文件处理（字节计算，文件下载与删除）
	 * - 打开弹出框
	 * @class $b.Base
	 * @requires $b
	 * @requires $b.String
	 * @singleton
	 */
	var Base = (function() {
		// 定义一个私有单例对象
		var _singletonInstance, $ ,locale,_string,_inner_cache = {};// undefined
		
		/**
    	 * ### 描述
    	 * 缓存对象，包含如下功能项：
    	 * 
    	 * - 设置缓存
    	 * - 获取缓存值
    	 * - 获取并移除缓存
    	 * - 清空缓存
    	 * 
    	 * ### 示例
		 * 获取缓存对象
		 * 
		 *     @example
		 *     var cacheObject = $b.Base.cache;
		 * 
		 * @class $b.Base.cache
		 * 
    	 */
		var _cache = {
			/**
	    	 * ### 描述
	    	 * 设置缓存，将数值信息设置到缓存当中
	    	 * 
	    	 * ### 示例
	    	 * 
	    	 * 如下示例设置好了一个缓存对象，后继可以通过luow来获取此对象了。
	    	 * 
			 *     @example
			 *      $b.Base.cache.set({userName:"lw" , age:"18" , worker:"四方精创" }, "luow");
	    	 * 
	    	 * @param {Object} obj  被缓存的对象
		     * @param {String} key  缓存对象对应的key，后继可以通过此key来获取缓存对象
	    	 * @returns {String}  缓存对象对应的key
	    	 * @member $b.Base.cache
	    	 * @method set
	    	 * 
	    	 */
			set : function(obj, key){
				key = key || _inner.guid();
				_inner_cache[key] = obj;
				return key;
			},
			/**
	    	 * ### 描述
	    	 * 获取缓存，根据key值获取缓存数据对象
	    	 * 
	    	 * ### 示例
	    	 * 
	    	 * 下面示例当中获取了缓存数据对象，并打印出来了对应的用户名称,此示例中能获取userName属性，是基于{@link $b.Base.cache#set 在set示例方法中}有设置缓存对象
	    	 * 
	    	 *     @example
	    	 *      var cacher = $b.Base.cache.get("luow");
	    	 *      document.write(cacher.userName);
	    	 * 
		     * @param {String} key  缓存对象对应的key，通过此key来获取缓存对象
	    	 * @returns {Object}  缓存对象
	    	 * @member $b.Base.cache
	    	 * @method get
	    	 * 
	    	 */
			get : function(key){
				return _inner_cache[key];
			},
			/**
	    	 * ### 描述
	    	 * 获取并移除缓存
	    	 * 
	    	 * ### 示例
	    	 * 
	    	 * 移除luow对应的缓存对象，基于{@link $b.Base.cache#set 在set示例方法中}有设置缓存对象key[luow]
	    	 * 
	    	 *     @example
	    	 *      $b.Base.cache.remove("luow");
	    	 * 
	    	 * 
		     * @param {String} key  缓存对象对应的key，通过此key来获取缓存对象
	    	 * @returns {Object}  被移除的缓存对象
	    	 * @member $b.Base.cache
	    	 * @method remove
	    	 */
			remove : function(key){
				var v = _inner_cache[key];
				delete _inner_cache[key];
				return v;
			},
			/**
	    	 * ### 描述
	    	 * 清空缓存，清空所有的缓存
	    	 * 
	    	 * ### 示例
	    	 * 
	    	 *     @example
	    	 *      $b.Base.cache.clear();
	    	 * 
	    	 * @member $b.Base.cache
	    	 * @method clear
	    	 */
			clear : function(){
				_inner_cache = null;
				_inner_cache = {};
			}
		};
		
		/**
    	 * ### 描述
    	 * 数组操作，包含的功能有
    	 * 
    	 * - 数组拷贝
    	 * - 获取源数组副本
    	 * 
    	 * ### 示例
    	 * 
    	 *     @example
    	 *     var cacheObject = $b.Base.array;
    	 * 
    	 * @class $b.Base.array
    	 */
		var _array = {
        	/**
	    	 * ### 描述
	    	 * 将指定源数组中的所有项复制到目标数组当中,会改变目标数组的内容和大小。
	    	 * 
	    	 * ### 示例
	    	 * 
	    	 *     @example
	    	 *      $b.Base.array.copy([1,2,3], ['l' , 'u' , 'o' , 'w']);
	    	 * 
	    	 * - 结果
	    	 * Array [ 1, 2, 3, "l", "u", "o", "w" ]
	    	 * 
	    	 * @param {Array} target  目标数组，将要拷贝到的数组对象
		     * @param {Array} source  源数组，被拷贝的数组对象，
	    	 * @returns {String}  目标数组
	    	 * @member $b.Base.array
	    	 * @method copy  
	    	 */
            copy : function (target, source) {
                target = $.type(target) === 'array' ? target : [];
                source = $.type(source) === 'array' ? source : [];
                var l = source.length, i = 0;
                if ($.type(l) === 'number') {
                    while (i < l) { target.push(source[i++]); };
                } else {
                    while (source[i] !== undefined) { target.push(source[i++]); }
                }
                return target;
            },
            /**
	    	 * ### 描述
	    	 * 创建源数组的副本并返回
	    	 * 
	    	 * ### 示例
	    	 * 
	    	 *     @example
	    	 *      $b.Base.cache.set(['l' , 'u' , 'o' , 'w']);
	    	 * 
	    	 * @param {Array} source  源数组对象
	    	 * @returns {Array}  数组对象
	    	 * @member $b.Base.array
	    	 * @method clone  
	    	 */
            clone : function (source) {
                return _inner.array.copy([], source);
            }
        };
		
		var initInstance = function() {
			$ = require('jquery');
            locale = require('locale');
            _string = require("String").instance();
			return {
				/**
		    	 * ### 描述
		    	 * 国际化文本，根据系统运行的语言环境返回国际化资源配置文件（beneform4j-en_US.js/beneform4j-zh_CN.js/beneform4j-zh_TW.js）当中key对应的value值
		    	 * 
		    	 * ### 示例一，没有占位符参数
		    	 * 
		    	 * 资源配置文件中的key、value对应的信息为【loadData		:	'加载数据异常！'】，没有占位参数，只需要值key值，不需要传后继的参数
		    	 * 下例中返回“加载数据异常”
		    	 * 
		    	 *     @example
		    	 *      $b.Base.i18n("error.loadData");
		    	 *      
		    	 * ### 示例二，有点位符参数
		    	 * 
		    	 * 资源配置文件中的key、value对应的信息为【ajaxDebugError  :   'Ajax调试出现错误，URL：{1}，{2}'】，
		    	 * 有两个占位参数，在调用时根据占位符的个数传递对应的参数值，参与值与占位符个数一一对应
		    	 * 下例中返回【Ajax调试出现错误，URL：路径参数，后台没有找到对应的处理控制器】
		    	 * 
		    	 *     @example
		    	 *      $b.Base.i18n("error.ajaxDebugError","路径参数", "后台没有找到对应的处理控制器");
		    	 * 
		    	 * @param {String} key  国际化JS中的key值，支持级联
			     * @param {String} params  占位符参数
		    	 * @returns {String}  国际化文本信息
		    	 * @member $b.Base
		    	 * @method i18n
		    	 */
                i18n : function(key, params){
                    var text = '',
                        keys,
                        length,
                        tmp,
                        k,
                        i,
                        pattern;
                    if(locale && key){
                        keys = key.split('.');
                        length = keys.length;
                        if(1 === length){
                            text = locale[key];
                        }else{
                            tmp = locale;
                            for(k=0; k<length; k++){
                                tmp = tmp[keys[k]];
                                if(!tmp){
                                    break;
                                }
                            }
                            text = tmp ? tmp : text;
                        }
                        //替换占位符
                        for(i=1,pattern=/\{1\}/g; arguments[i] && pattern.test(text); i++,pattern = new RegExp('\\{'+i+'\\}','g'))
                        {
                            text = text.replace(pattern, arguments[i]);
                        }
                    }
                    return text;
                },
                /**
		    	 * ### 描述
		    	 * 产生唯一键值
		    	 * 
		    	 * ### 示例
		    	 * 
		    	 *     @example
		    	 *      $b.Base.guid();
		    	 * 
		    	 * @member $b.Base
		    	 * @method guid
		    	 * @returns {String}  唯一键值的字符串
		    	 */
                guid : function(){
                    var guid = '', i;
                    for (i = 32; i > 0; i--){
                        guid += Math.floor(Math.random()*16.0).toString(16);
                    }
                    return guid;
                },
    			/**
		    	 * ### 描述
		    	 * 将符合json格式的字符串，封装为JSON对象
		    	 * 
		    	 * ### 示例
		    	 * 
		    	 * 返回一个json对象，可以通过对象.key，获取到对应的value。下例中转换后，可以通过打印用户名信息。如果是字符串，是无法通过对象.key来进行访问的。
		    	 * 
		    	 *     @example
		    	 *      var jsonOjb = $b.Base.parseJson('{userName:"lw" , age:"18" , worker:"四方精创" }');
		    	 *      document.write(jsonOjb.userName);
		    	 * 
		    	 * @member $b.Base
		    	 * @method parseJson
		    	 * @param {String} json  json对象
		    	 * @returns {String}  返回转换后的json对象
		    	 */
                parseJson : function(json){
                    //$.parseJSON函数要求属性要使用双引号
                    return eval("(" + json + ')');
                },
                /**
		    	 * ### 描述
		    	 * 转义HTML字符串，将带有html元素标签的字符串进行转换处理，防止被浏览器解析渲染。
		    	 * 
		    	 * ### 示例
		    	 * 
		    	 * 下面示例当中将返回【&lt;lable&gt;123456&lt;/lable&gt;】，对传入参数的标签元素进行了转换处理。
		    	 * 
		    	 *     @example
		    	 *      var text = $b.Base.escapeHtml('<lable>123456</lable>');
		    	 *      document.write(text);
		    	 * 
		    	 * @member $b.Base
		    	 * @method escapeHtml
		    	 * @param {String} html  被转换的带有html标签元素的字符串
		    	 * @returns {String}  没有被浏览器解析的字符串
		    	 */
                escapeHtml : function(html){
                    return $('<div>').text(html).html();
                },
                /**
		    	 * ### 描述
		    	 * 反转义HTML字符串，将带有html元素标签转义符的字符串进行转换处理形成正常的html标签。
		    	 * 
		    	 * ### 示例
		    	 * 
		    	 * 下面示例当中将返回``<lable>123456</lable>``，对传入参数的标签元素进行了转换处理。
		    	 * 
		    	 *     @example
		    	 *      var text = $b.Base.unescapeHtml('&lt;lable&gt;123456&lt;/lable&gt;');
		    	 *      document.write(text);
		    	 * 
		    	 * @member $b.Base
		    	 * @method unescapeHtml
		    	 * @param {String} html  被转换的带有html标签元素的字符串
		    	 * @returns {String}  没有被浏览器解析的字符串
		    	 */
                unescapeHtml : function(html){
                    return $('<div>').html(html).text();
                },
                /**
		    	 * ### 描述
		    	 * 时钟处理方法，可以当计数器用，也可以当一秒刷一次回调函数的处理工具来用
		    	 * 
		    	 * ### 示例
		    	 * 
		    	 *     @example
		    	 *      $b.Base.showClock(function(time, sec){//todo ...});
		    	 * 
		    	 * @param {Object} callback  回调函数,回调函数当中可以传入两个参数，一个参数为当前时间信息，格式为 hh:24m:ss，此值将1秒钟刷一次； 别一个参数是执行次数
		    	 * @member $b.Base
		    	 * @method showClock
		    	 */
                showClock : function(callback){
                    var i = 0,
                        toClock = function(t){
                            var h = Math.floor(t / 3600),
                                m = Math.floor(t % 3600 / 60),
                                s = t % 3600 % 60;
                            h = h < 10 ? ('0'+h) : h;
                            m = m < 10 ? ('0'+m) : m;
                            s = s < 10 ? ('0'+s) : s;
                            return h + ':' + m +  ':' + s;
                        },
                        setClock = function(){
                            callback(toClock(i), i++);
                            setTimeout(setClock,1000);
                        };
                    //初始调用
                    setClock();
                },
                /**
		    	 * ### 描述
		    	 * 删除对象的属性
		    	 * 
		    	 * ### 示例
		    	 * 
		    	 * 如下示例设置好了一个缓存对象，后继可以通过luow来获取此对象了。
		    	 * 
		    	 *     @example
		    	 *      $b.Base.deleteProperties(user , ['userName' , 'age' , 'sex']);
		    	 * 
		    	 * @member $b.Base
		    	 * @method deleteProperties
		    	 * @param {Object} obj  需要删除属性的对象
			     * @param {Array} deleteProperties  删除的属性数组
		    	 */
                deleteProperties: function(obj, deleteProperties){
                    for(var i=deleteProperties.length-1; i>=0; i--){
                        try{
                            delete obj[deleteProperties[i]];
                        }catch(e){}
                    }
                },
                /**
		    	 * ### 描述
		    	 * 将参数转换为不重复的数组
		    	 * 
		    	 * ### 示例一		'a' ==> ['a']
		    	 * 
		    	 *     @example
		    	 *      $b.Base.makeArray('a');
		    	 *      
		    	 * ### 示例二		'a','b' ==> ['a','b']
		    	 * 
		    	 *     @example
		    	 *      $b.Base.makeArray('a','b');
		    	 *
		    	 * ### 示例三		['a','b'],'c'
		    	 * 
		    	 *     @example
		    	 *      $b.Base.makeArray(['a','b'],'c');
		    	 *
		    	 * ### 示例四		['a','b'],['b','c'] ==> ['a','b','c']
		    	 * 
		    	 *     @example
		    	 *      $b.Base.makeArray(['a','b'],['b','c']);
		    	 *
		    	 * ### 示例五		'a', ['b',['c','d']],'e' ==> ['a','b','c','d','e']
		    	 * 
		    	 *     @example
		    	 *      $b.Base.makeArray('a', ['b',['c','d']],'e');
		    	 * 
		    	 * @member $b.Base
		    	 * @method makeArray
		    	 * @param {Object} obj  可以传入多个参数，类似java当中的...
		    	 * @returns {Array}  转换后的数组
		    	 */
                makeArray  : function(){
                    var arrs = [],
                        push = function(index, item){
                            if($.isArray(item)){
                                $.each(item, push);
                            }else if(-1 === $.inArray(item, arrs)){
                                arrs.push(item);
                            }
                        };
                    $.each($.makeArray(arguments), push);
                    return arrs;
                },
                /**
		    	 * ### 描述
		    	 * 根据extra和exclude参数解析数组，此方法多用地combox等组件的数据处理上。
		    	 * 
		    	 * ### 示例
		    	 * 
		    	 *     @example
		    	 *      $b.Base.evaluateExtraData([{id:'1' , text:'小学'} , {id:'2' , text:'中学'} , {id:'3' , text:'大学'}] ,  'all' , '3' );
		    	 * 
		    	 * - 返回结果：Array  [ Object, Object, Object ]，新增了对象{id:'' , text:'全部'},且删除了为对象{id:'3' , text:'大学'}
		    	 * 
		    	 * @member $b.Base
		    	 * @method evaluateExtraData
		    	 * @param {Array} arr  原始数组
			     * @param {String} extra  需附加数据的类型，目前可取的值有 all, com, select, all_com
			     * @param {Array} exclude  需排除数据的代码或代码数组
		    	 * @returns {Array}  解析后的数组，是在原数组基础之上解析，不会影响原数组
		    	 */
                evaluateExtraData:function(arr, extra, exclude){
                    var data = [].concat(arr||[]);//创建本地副本
                    if(exclude){
                        exclude = $.isArray(exclude)?exclude:[exclude];
                        var length = exclude.length;
                        data = $.grep(data, function(value){
                            for(var index = 0; index < length; index++){
                                if(value.id === exclude[index]){
                                    return false;
                                }
                            }
                            return true;
                        });
                    }
                    if(extra){
                        switch(extra){
                            case 'all':
                                data.unshift({id : '', text : locale.label.all});
                                break;
                            case 'com':
                                data.unshift({id : 'com', text : locale.label.pub});
                                break;
                            case 'select':
                                data.unshift({id : '', text :  locale.label.pleaseSelect});
                                break;
                            case 'all_com':
                                data.unshift({id : '', text : locale.label.all}, {id : 'com', text : locale.label.pub});
                                break;
                            default:
                                break;
                        }
                    }
                    return data;
                },
                /**
		    	 * ### 描述
		    	 * 用一个或多个其他对象来扩展一个对象
		    	 * 
		    	 * - 本方法与jQuery.extend的区别：
		    	 *     + 1、jQuery.extend：无论 target 对象中是否存在 object1、object2、objectN等待合并对象中相应的属性，待合并对象中的相应属性都将会合并到 target 中；
		    	 *     + 2、union: 如果 target 对象中存在 object1、object2、objectN等，待合并对象中相应的属性，则该属性将不会被合并到 target 中。
		    	 * 
		    	 * ### 示例
		    	 * 
		    	 *     @example
		    	 *      $b.Base.union({}, {userName:'uu' , userAge :'18'});
		    	 * 
		    	 * @param {Boolean} deep  可选，如果设为 true，则递归合并
			     * @param {Object} target  可选；一个对象，如果附加的对象被传递给这个方法将那么它将接收新的属性，如果它是唯一的参数将扩展jQuery的命名空间；
			     * @param {Object} object1  待合并到 target 的对象；
			     * @param {Object} object2  待合并到 target 的对象；
			     * @param {Object} objectN  待合并到 target 的对象；
		    	 * @returns {Object}  被扩展的对象
		    	 * @member $b.Base
		    	 * @method union
		    	 */
                union : function () {
                    var src, copyIsArray, copy, name, options, clone, target = arguments[0] || {}, i = 1, length = arguments.length, deep = false;
                    if (typeof target === 'boolean') { deep = target; target = arguments[1] || {}; i = 2; }
                    if (typeof target !== 'object' && !$.isFunction(target)) { target = {}; }
                    if (length === i) { target = this; --i; }
                    for (; i < length; i++) {
                        if ((options = arguments[i]) != null) {
                            for (name in options) {
                                src = target[name];
                                copy = options[name];
                                if (target === copy) { continue; }
                                if (deep && copy && ($.isPlainObject(copy) || (copyIsArray = $.isArray(copy)))) {
                                    if (copyIsArray) {
                                        copyIsArray = false;
                                        clone = src && $.isArray(src) ? src : [];
                                    } else {
                                        clone = src && $.isPlainObject(src) ? src : {};
                                    }
                                    target[name] = Base.union(deep, clone, copy);
                                } else if (copy !== undefined && copy !== null && (src === undefined || src === null)) {
                                    target[name] = copy;
                                }
                            }
                        }
                    }
                    return target;
                },
                /**
		    	 * ### 描述
		    	 * 将数组转换为对象
		    	 * 
		    	 * ### 示例
		    	 * 
		    	 * 如将 [{id:'id1',text:'text1'},{id:'id2',text:'text2'}] 转换为 ==>{id1:'text1', id2:'text2'}
		    	 * 
		    	 *     @example
		    	 *      $b.Base.convertArray2Object([{id:'id1',text:'text1'},{id:'id2',text:'text2'}]);
		    	 * 
		    	 * @member $b.Base
		    	 * @method convertArray2Object
		    	 * @author LinJisong 2016-5-10
		    	 * @param {Array} arr  原始数组
			     * @param {String} key  数组元素对象中表示键值的属性名称，默认为id
			     * @param {String} value 数组元素对象中表示值的属性名称，默认为text，若传入'this'，则将数组中的项作为值
		    	 * @returns {Object}  解析后的对象
		    	 */
                convertArray2Object : function(arr, key, value){
                    var rs = {},
                        self = false;
                    fn = function(index, val){
                        rs[val[key]] = self ? val : val[value];
                        if($.isArray(val.children)){
                            $.each(val.children, fn);
                        }
                    };
                    key = key || 'id';
                    value = value || 'text';
                    self = value === 'this';
                    $.each(arr, fn);
                    return rs;
                },
                /**
		    	 * ### 描述
		    	 * 刷新页面时取消绑定onunload事件
		    	 * 
		    	 * ### 示例
		    	 * 
		    	 *     @example
		    	 *      $b.Base.refresh();
		    	 * 
		    	 * @member $b.Base
		    	 * @method refresh
		    	 */
                refresh : function(){
                    if(top){
                        top.window.onunload = function(){};
                        top.window.location.href=top.window.location.href;
                    }else{
                        window.onunload = function(){};
                        window.location.href=window.location.href;
                    }

                },
                /**
		    	 * ### 描述 {@link $b.Base.cache 点击进入子类}
		    	 * 缓存对象，包含如下功能项：
		    	 * 
		    	 * - 设置缓存
		    	 * - 获取缓存值
		    	 * - 获取并移除缓存
		    	 * - 清空缓存
		    	 * 
				 * ### 示例
				 * 
				 *     @example
				 *     var cacheObject = $b.Base.cache;
				 * 
				 * @member $b.Base
		    	 * @property {Object} cache
		    	 * 
		    	 */
    			cache	: _cache,
    			/**
		    	 * ### 描述 {@link $b.Base.array 点击进入子类}
		    	 * 集合对象处理工具类
		    	 * 
		    	 * ### 示例
		    	 * 
				 *     @example
				 *      var arrayObject = $b.Base.array;
				 * 
				 * @member $b.Base
		    	 * @property {Object} array
		    	 * 
		    	 */
                array : _array
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
		instance : function(){
			 return Base.getInstance() ;
		}
	};
});