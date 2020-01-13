/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Page页面对象，将页面的公共依赖依次导入，具体应用只需导入本模块即可 <br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-30<br>
 */
define([ 'jquery', 'common','locale', 'frame-plus', 'ext-jquery', 'beneform4j-easyui'], function($, $b,locale) {
    // 创建页面对象构造函数
    var Page = window.Page || (function() {}), 
        _cache = Page._$cache = Page._$cache || {};
        
    //扩展jQuery的选择器，后面需要放入jQuery的扩展文件中
    $.extend($.expr[':'], {
    	fn : function(obj){
    		var me = $(obj),fn = me.attr('fn');
    		if(fn){
    			var index = fn.indexOf(':'), type, fname;
    			if(-1 === index){
    				type = 'click';
    				fname = fn;
    			}else{
    				type = fn.substring(0, index) || 'click';
    				fname = fn.substring(index+1); 
    			}
    			me.data('fn', {type : type, fname : fname});
    			return true;
    		}else{
    			return false;
    		}
    	},
    	combo : function(obj){
    		return $(obj).attr('combo-options') != '';
    	}
    });
        
    /**
     * 添加静态方法
     */
    $.extend(Page, {
		/**
		 * 创建页面实例对象
		 * 
		 * @param name 页面对象名称，用作唯一标识
		 * @param options 页面对象选项，这些属性和方法将被添加到新创建的对象实例中
		 * @param callback 页面对象创建完成后的回调函数
		 * @return 页面对象
		 */
		create : function(name, options, callback) {
		    var foptions,
		    	page;
			if (typeof name === 'function'){//如果第一个参数为函数，先保存该函数，在创建对象之后再合并该函数的返回值
				foptions = name;
		    	name = $b.Base.guid();
		    }else if (typeof name === 'object') {// 如果第一个参数为对象，将参数向前移动一位，并使用随机ID作为名称
				callback = options;
				options = name;
				name = $b.Base.guid();
		    }else if (_cache[name]) {
		    	$b.Msg.error($b.Base.i18n('error.hasObject',name));
		    	return;
		    } 
		    
		    page = new Page();// 创建新实例
	    	_init.call(page, options);
	    	page._$name = name;
	    	_cache[name] = page;
	    	if(foptions){
	    		$.extend(page, foptions.call(page, $));
	    	}
	    	_callWhenIsFunction(page.init, page, $);
	    	_callWhenIsFunction(callback, page, $);
			return page;
		},
		/**
		 * 销毁页面对象
		 * 
		 * @param name 页面对象名称，用作唯一标识
		 * @param callback 页面对象销毁完成后的回调函数
		 */
		destory : function(name, callback) {
		    delete _cache[name];
		    _callWhenIsFunction(callback);
		},
		/**
		 * 扩展页面对象
		 * 
		 * @param name 页面对象名称，用作唯一标识
		 * @param options 页面对象选项，这些属性和方法将被添加到新创建的对象实例中
		 * @param callback 页面对象创建完成后的回调函数
		 */
		extend : function(name, options, callback) {
		    if (name && name._$name) {
		    	name = name._$name;
		    }
		    var page = _cache[name], o = options, init;
		    if (_isFunction(options)) {
		    	o = options.call(page, $);
		    }
		    if (o && _isFunction(o.init)) {
		    	init = o.init;
		    }
		    $.extend(page, o);
		    if (init) {
		    	init.call(page, $);
		    }
		    _callWhenIsFunction(callback, page, $);
		}
    });
    /**
     * 实例方法
     */
    $.extend(Page.prototype, {
    	
    	/**
    	 * 下拉列表项数据
    	 */
    	comboDatas : {},
    	
    	/**
		 * 初始化函数，子类可覆盖
		 */
		init : function() {
		},
		
    	/**
    	 * 设置下拉项数据
    	 * @param 参数代码或参数代码数组
    	 */
    	setComboDatas : function(){
    		var datas = $b.App.getComboDatas($b.Base.makeArray.apply($b,arguments));
    		if(!datas){
    			return;
    		}else{
    			$.each(datas, function(key, data){
    				data.map = $b.Base.convertArray2Object(data.data, 'id', 'this');
    			});
    		}
    		$.extend(this.comboDatas, datas);
    	},
    	
    	/**
    	 * 获取下拉数据项
    	 */
    	getComboData  : function(dataKey, dataCode){
    		var data = this.comboDatas[dataKey];
    		if(data && data.map){
    			return data.map[dataCode];
    		}
    		return null;
    	},
    	
    	/**
    	 * 获取下拉选项的渲染函数
    	 * 注：返回值是一个函数
    	 */
    	comboFormatter: function(dataKey){
    		var me = this;
    		return function(value,row,index){
    			var item = me.getComboData(dataKey, value);
    			return item ? item.text : '';
    		};
    	},
    	
		/**
		 * 获取选择的一条记录，如果未选择或者已选择多条，返回false
		 * @param dataGrid 数据表格
		 * @param callback 回调函数
		 */
		selectOne : function(dataGrid, callback){
			var rows = $(dataGrid).datagrid('getChecked');
			switch(rows.length){
			case 0:
				$b.Msg.warning(locale.operate.selectToRecord);
				return false;
			case 1:
				if(typeof callback === 'function'){
					return callback.call(this, rows[0]);
				}else{
					return rows[0];
				}
			default:
				$b.Msg.warning(locale.operate.selectRecordToMore);
				return false;
			}
		},
		
		/**
		 * 获取选择的记录，如果未选择，返回false
		 * @param dataGrid 数据表格
		 * @param callback 回调函数
		 */
		selectRows : function(dataGrid, callback){
			var rows = $(dataGrid).datagrid('getChecked');
			if(rows.length === 0){
				$b.Msg.warning(locale.operate.selectToRecord);
				return false;
			}else if(typeof callback === 'function'){
				return callback.call(this, rows);
			}else{
				return rows;
			}
		},
		
		/**
		 * 根据fn属性绑定事件
		 * @Param container jQuery搜索范围的DOM元素
		 */
		bindEvent : function(container){
			var me = this;
			$(container || document).find(':fn').each(function(i, n){
				var m = $(this), fn = m.data('fn');
				if(typeof me[fn.fname] === 'function'){
					m.off(fn.type).on(fn.type, me[fn.fname]);
				}
			});
		},
		
		/**
		 * 初始化设置下拉域
		 * 这里只处理最通用的情形，其它特殊要求的需要单独写脚本
		 * @param container jQuery搜索范围的DOM元素
		 */
		setComboFields : function(container){
			var me = this,
				paramCodes = [],
				comboFields = {};
			//搜索所有包含combo-options属性的元素，提取访问后台需要的属性
			$.each($(container || document).find('.bf4j-combo,[combo-options]'), function(i, f){
				var $f = $(f), co = $f.attr('combo-options'), options, key, data;
				if(co && co.indexOf(':') !== -1){
					co = '{'+co+'}';
				}
				options = $b.Base.parseJson(co);
				if(options){
					if(typeof options === 'string'){//只配置了简单字符串
						key = options;
					}else if(options.dataKey){
						key = options.dataKey;
					}else{// 如果既没有dataKey，也不是简单字符串，则不处理
						return;
					}
					data = me.comboDatas[key];
					if(data){//已经加载过，直接渲染
						me.renderCombo($f, data, options);
					}else{//需要加载
						if(-1 === $.inArray(key, paramCodes)){
							paramCodes.push(key);	
						}
						comboFields[key] = comboFields[key] || [];
						comboFields[key].push([$f, options]);
					}
				}
				
			});
			if(paramCodes.length >= 1){
				me.setComboDatas(paramCodes);
				$.each(comboFields, function(key, fields){
					var datas = me.comboDatas[key];
					if(!datas){
						$b.Msg.error($b.Base.i18n('error.notFindKey',key));
					}else{
						$.each(fields, function(i, f){
							me.renderCombo(f[0], datas, f[1]);
						});	
					}
				});
			}
		},
		
		/**
		 * 设置机构域
		 */
		setOrganField : function(container){
			var me = this, options;
			//搜索所有包含combo-options属性的元素，提取访问后台需要的属性
			$.each($(container || document).find('.bf4j-organ,[organ-options]'), function(i, f){
				var $f = $(f), co = $f.attr('organ-options'), options, params, synch;
				if(co && co.indexOf(':') !== -1){
					co = '{'+co+'}';
				}
				options = $b.Base.parseJson(co) || {};
				synch = options.synch === true;
				params = {root : options.root || '', minOrgLevel: options.minOrgLevel || '-1'};
				$b.Base.deleteProperties(options,['root','minOrgLevel', 'synch']);
				if(synch){
					$b.Submit.ajaxSubmit($b.context.root+'/uiholder/getOrganTree', params, function(data){
						$f.addClass('easyui-combotree').combotree($.extend({
							animate: true,
							lines : true,
							idField:'id',
							treeField:'text'
						}, options)).combotree('loadData', data);
					});
				}else{
					// 构建异步加载树
					$f.addClass('easyui-combotree').combotree($.extend({
						url : $b.context.root+'/uiholder/getOrganChildren',
						animate: true,
						lines : true,
						onBeforeLoad : function(node, param) {
							$.extend(param, params);
						},
						loadFilter: function(data){
							if($.isArray(data)){
								$.each(data, function(i, node){
									node.id = node.orgId;
									node.text = node.orgName;
									node.state = node.count >= 1? 'closed':'open';
								});
							}
							return data;
						}
					}, options));
				}
			});
		},
		
		/**
		 * 渲染combo域
		 */
		renderCombo : function(field, data, options){
			var $f = $(field), 
				dataOptions = $f.attr('data-options'), 
				map = data.map;
			if(dataOptions){
				dataOptions = $b.Base.parseJson('{'+dataOptions+'}');
			}else{
				dataOptions = {};
			}
			//添加请选择的校验
			if(dataOptions.required === true && dataOptions.validType === undefined){
				dataOptions.validType = 'select';
			}
			if(data.type === 'TREE'){
				if(options.root && map){
					data = [];
					$.each(options.root.split(','), function(i, v){
						var item = map[v];
						if(item){
							data.push(item);
						}else{
							$b.Msg.error($b.Base.i18n('error.notFindNode',v));
						}
					});
				}else{
					data = data.data;
				}
				$f.addClass('easyui-combotree').combotree($.extend({idField:'id',treeField:'text',animate:' true',
					}, dataOptions)).combotree('loadData', data);
			}else{
				data = data.data;
				if(options.extra || options.excludes){
					data = $b.Base.evaluateExtraData(data, options.extra, options.excludes);
				}
				$f.addClass('easyui-combobox').combobox($.extend({valueField:'id',textField:'text'},dataOptions)).combobox('loadData', data);
			}
		}
    });

    /**
     * 以下为私有方法，暂以下划线_开头
     */
    // 初始化方法
    function _init(options) {
		var m = this;
		// 将选项设置为页面实例对象的属性
		if (_isFunction(options)) {
		    $.extend(m, options.call(m, $));
		} else {
		    $.extend(m, options);
		}
    }
    // 判断是否为函数
    function _isFunction(f) {
    	return typeof f === 'function';
    }
    // 如果是函数，就调用
    function _callWhenIsFunction(f, scope) {
		if (_isFunction(f)) {
		    f.apply(scope || this, Array.prototype.slice.call(arguments, 2));
		}
    }
    // 返回页面对象
    return Page;
});