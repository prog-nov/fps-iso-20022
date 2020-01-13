define(function(require) {
	/**
	 * ### 描述
	 * 其它应用工具方法的集合类
	 * 
	 * - 富文件
	 * - 异步树
	 * @class $b.App
	 * @requires $b
	 * @singleton
	 */
	var App = (function() {
		// 定义一个私有单例对象
		var _singletonInstance,$, _string , _msg , _locale ,_base;// undefined
		var initInstance = function() {
			
			$  = require('jquery');
			_string =  require("String").instance();
			_msg =  require("Msg").instance();
			_locale = require('locale');
			_base  = require("Base").instance();
			require("kindeditor");
			/**
 	    	 * ### 描述
 	    	 * 与应用相关的处理工具类集合，，
 	    	 * 
 	    	 * - 富文件处理
 	    	 * - 浮动按钮等
 	    	 * 
 	    	 * ### 示例
 	    	 * 
 	    	 *     @example
 	    	 *      var appObject =$b.App;
 	    	 * 
 	    	 * @class $b.App.formatter
 	    	 */
            _formatter = {
            		/**
        	    	 * ### 描述
        	    	 * 默认保留两位小数
        	    	 * 
        	    	 * - 12345格式化为12,345.00
                     * - 12345.6格式化为12,345.60
                     * - 12345.67格式化为 12,345.67
                     * 
                     * - 注意：如果超过了指定的2位精度，将四舍五入处理
                     *      对于数据异常的情况，原样输出
                     * 
        	    	 * ### 示例，在datagrid的列表栏位上配置
        	    	 * 
        	    	 *     @example
        	    	 *		columns : [ [ 
        	    	 *			{field : 'money2', title : "金额二", align:'center',width:120,formatter:$b.App.formatter.toPercent()}
        	    	 *    	] ]
        	    	 * 
        	    	 * @returns {String}  转换后的字符串
        	    	 * @member $b.App.formatter
        	    	 * @method toPercent
        	    	 * @author luow 2016-5-20
        	    	 */
                    toPercent: function () {
                        return function (value, row, index) {
                            if (!check.isCurrency(value)) {
                                return value;
                            }
                            return _string.toSplitMoney(value, 2);
                        }
                    },
                    /**
        	    	 * ### 描述
        	    	 * 对一个number类型的数值转换为字节、兆、G等单位的数据
        	    	 * 
        	    	 * ### 示例
        	    	 * 
        	    	 *     @example
        	    	 *		columns : [ [ 
        	    	 *			{field : 'filesize', title : "文件大小", align:'center',width:120,formatter:$b.App.formatter.size()}
        	    	 *    	] ]
        	    	 * 
        	    	 * @returns {String}  转换后的字符串
        	    	 * @member $b.App.formatter
        	    	 * @method size
        	    	 * @author luow 2016-5-20
        	    	 */
                    size: function () {
                        return function (value, row, index) {
                            return $b.File.fileSizeRenderer(value);
                        }
                    }
            };
			return {
				/**
    	    	 * ### 描述
    	    	 * 富文本框编辑器的初始化处理,将页面上的元素用此方法初始化后，渲染出富文本框的编辑器效果
    	    	 * 
    	    	 * ### 示例一：opt参数不传，显示默认工具条
    	    	 * 
    	    	 * 为页面中的``<textarea name="content"></textarea>``属性name中字义的名称
    	    	 * 
    	    	 *     @example
    	    	 *     $b.App.iniKindEditor("content");
    	    	 * 
    	    	 * ### 示例二：opt传空数组，不显示工具条
    	    	 * 
    	    	 * 为页面中的``<textarea name="content"></textarea>``属性name中字义的名称
    	    	 * 
    	    	 *     @example
    	    	 *     $b.App.iniKindEditor("content",[]);
    	    	 * 
    	    	 * ### 示例三：opt传指定数组，显示指定的工具条
    	    	 * 
    	    	 * 为页面中的``<textarea name="content"></textarea>``属性name中字义的名称，工具条中只有取消与回退两个操作按钮，工具条的相关选择，可以查看平台源码看到。
    	    	 * 
    	    	 *     @example
    	    	 *     $b.App.iniKindEditor("content",['undo', 'redo']);
    	    	 * 
    		     * @param {String} doc  元素的name属性值
    		     * @param {Array} opt  工具条属性集合，集合中的元素对应工具条控件中的操作按钮
    	    	 * @returns {Object}  返回富文本对象，可以定义一个变量接收此对象，假设定义的对象为editor。可以通过editor.html()的方式获取到富文本当中的值。
    	    	 * @member $b.App
    	    	 * @method iniKindEditor
    	    	 * @author luow 2016-4-28
    	    	 * 
    	    	 */
                iniKindEditor: function (doc, opt) {
                    var options = {
                        //width : '1000px',
                        //height : '900px',
                        items: opt || [
                            'undo', 'redo', '|', 'preview', 'print', 'template', 'cut', 'copy', 'paste',
                            'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                            'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                            'superscript', 'clearhtml', 'quickformat', '|', 'fullscreen', '/',
                            'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                            'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
                            'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'pagebreak'
                        ],
                        uploadJson: $b.context.root + '/kindEdit/fileUpload',
                        fileManagerJson: $b.context.root + '/kindEdit/fileManager',
                        allowFileManager: true,
                        afterBlur: function () {
                        	this.sync();
                        },
                        afterCreate: function () {
                        	this.sync();
                        }
                    };
                    return KindEditor.create('textarea[name="' + doc + '"]', options);
                },
                /**
    	    	 * ### 描述
    	    	 * 异步树，每点击一次树的节点都会向后台发送请求，后台从此请求当中获取对应的id查询此id下面的子节点，返回到前端，并挂在点击的节点下面。
    	    	 * 
    	    	 * ### 示例
    	    	 * 页面元素中定义：``<div class="bf4j-group" > <ul id="forms_tree"></ul></div>``
    	    	 * 
    	    	 *     @example
    	    	 *     var callback = function(node){
    	    	 *     		if("1" == node.attributes){
    	    	 *     			//打开节点触发的事件
    	    	 *     		}else{
    	    	 *     			//收缩节点触发的事件
    	    	 *     		}
    	    	 *     };
    	    	 *     //加载动态树
    	    	 *     var tree = $b.App.dynamicTree('#forms_tree','a/b/c/',callback);
    	    	 *      
    	    	 * 
    		     * @param {String} treeId 为页面中ul对应的元素
    		     * @param {String} treeUrl 为请求后台的url连接，通过此连接获取树形节点
    		     * @param {Object} callback  回调函数 通过回调函数来获取点击的当前节点的数据信息，回调函数的只有一个参数，参数为Object对象。
    	    	 * @member $b.App
    	    	 * @method dynamicTree
    	    	 * @author luow
    	    	 * 
    	    	 */
                dynamicTree: function (treeId, treeUrl, callback) {
                    return $(treeId).tree({
                        url: treeUrl,
                        animate: true,
                        onClick: function (node) {
                            if (node && node.attributes) {
                                if (typeof callback === 'function') {
                                    callback(node);
                                }
                            }
                        },
                        onBeforeLoad: function (node, param) {
                            if (treeUrl) {//只有刷新页面才会执行这个方法
                            	_msg.closeProgress();
                            }
                        },
                        onLoadSuccess: function (node, data) {
                        	_msg.closeProgress();
                        }
                    });
                },
                /**
		    	 * ### 描述
		    	 * 根据URL打开新窗口或TAB页
		    	 * 
		    	 * ### 示例
		    	 * 
		    	 *     @example
		    	 *      $b.App.openUrl('a/b/c/' , '测试' ,{width:400 , height: 500});
		    	 * 
		    	 * @author Kingdom 2016-5-23
		    	 * @param {String} url  目标URL
			     * @param {String} title  窗口标题
			     * @param {Object} options  附加参数对象 ，默认为{}
			     * 
			     * - options.targetPage : 默认为module 打开模式窗口 可选值有 tab:打开选项卡  module:打开模式窗口 其它：用window.open打开新窗口
			     * - options.path       : 菜单路径,只在targetPage = tab时有效
			     * - options.code       : 菜单ID，只在targetPage = tab时有效
			     * - options.features   : 对应到window.open的features参数
			     * - options.replace    : 对应到window.open的replace参数
		    	 */
                openUrl:function(url,title,option){
                    var jq = top.jQuery;
                    option = option || {};
                    if (url) {
                        var targetPage = option.targetPage || 'module';
                        if( targetPage == 'module' ){
                            var dialog = jq('#dialog');
                            dialog.dialog({
                                title:  title,
                                width:  340 || option.width,
                                height: 230 || option.height,
                                href :  url,
                                script: true
                            });
                        }else if( targetPage == 'tab' ){
                            // temp start
                            var iframe = '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>';
                            var t = jq('#index_tabs');
                            var opts = {
                                title : "<span title='"+title+"'  path='" + (option.path||'') + "' menuId='"
                                + (option.menuId||'') + "' menuFlag='$"+(option.menuId ||'')+"$'  style='width: 80px;overflow: hidden;display: block;float: left;boder=0;text-align: center;'>" + title + "</span>",
                                closable : true,
                                content : iframe,
                                border : false,
                                fit : true
                            };
                            var tabs = t.find(".tabs-title");
                            for(var i = 0; i<tabs.length; i++){
                                if($(tabs[i]).html().indexOf("$"+option.menuId+"$")>-1){
                                    t.tabs('select', i);
                                    return;
                                }
                            }
                            if(t.tabs('tabs').length > server_consts.maxTabsNum){
                            	_msg.warning(_locale.operate.openTabMax);
                            } else {
                                t.tabs('add', opts);
                                _msg.openProgress();
                            }
                        }else{
                            window.open(url,targetPage,option.features,option.replace);
                        }
                    }
                },
                /**
		    	 * ### 描述
		    	 * 对于信息提示框（如错误提示，操作提示，凡是调用到了$b.Msg.中的方法），如果信息超超现出提示信息排版难看的问题，进行优化处理
		    	 * 
		    	 * ### 示例
		    	 * 
		    	 * 如下示例设置好了一个缓存对象，后继可以通过luow来获取此对象了。
		    	 * 
		    	 *     @example
		    	 *      $b.App.errorMsgDetail('展示的信息内容');
		    	 * 
		    	 * @author luow 2016-05-16
		    	 * @param {Object} _content  显示的提示信息内容
		    	 * @returns {String}  缓存对象对应的key
		    	 * 
				 *      
		    	 */
                errorMsgDetail : function(_content){
                    var msgInfo = $("#dialog-msg-info") ;
                    msgInfo.dialog({
                        title: _locale.title.tipDetail,
                        minimizable: false,
                        maximizable: true,
                        collapsible:false,
                        resizable: true,
                        modal: true,
                        height:500,
                        width:600,
                        content : _content,
                        buttons : [ {
                            text : _base.i18n("button.close"),
                            size : 'large',
                            handler : function() {
                                msgInfo.dialog('close');
                            }
                        } ]
                    });
                },
                /** ##描述
	             * 一次性获取多个枚举数据。
	             * 
	             * - 注意：一次性获取多个枚举数据，目前只能从平台参数表中获取，暂不能使用自定义URL
	             * 
	             * 例如示例：
	             * 
	             *     @example  
	             * 		$b.Date.toDate('2016-11-25');
	             * 
	             * @returns rs {Object} 返回数据结构{paramCode1:paramCode1_data, paramCode2:paramCode2_data}
	             * 
	             *     @example  
	             *  	{
	             * 			BOOLEAN : {
	             * 				type : 'LIST',
	             *          	data : [{id : '1', text : '是'}, {id : '0', text : '否'}]
	             * 			},
	             *      	PARAM_GROUP: {
	             *          	type : 'TREE',
	             *          	data : [
	             *          		{id : 'SYSTEM_CONFIG', text:'系统配置', children : [...]},
	             *          		{id : 'USER_OPTION', text:'用户设置', children : [...]}
	             *          	]
	             *      	}
	             * 		}
	             * 
	             * @param options {String}  选项，或者直接传入字典项/逗号分隔的多个字典项/字典项数组
	             * 		options.paramCodes 字典项/逗号分隔的多个字典项/字典项数组
	             * @member $b.App
    	    	 * @method getComboDatas
    	    	 * @author LinJisong  2016-5-10
	             */
                getComboDatas: function (options) {
                    var rs = {};
                    if (typeof options === 'string') {
                        options = {paramCodes: options};
                    } else if ($.isArray(options)) {
                        options = {paramCodes: options.join(',')};
                    } else if ($.isArray(options.paramCodes)) {
                        options.paramCodes = options.paramCodes.join(',');
                    }
                    $b.Submit.ajaxSubmit($b.context.root + '/uiholder/getComboDatas', $.extend({
                        async: false,
                        needBlock: false
                    }, options), function (data) {
                        if (data) {
                            $.each(data, function (n, d) {
                                rs[d.paramCode] = d;
                            });
                        }
                    });
                    return rs;
                },
                /**
		    	 * ### 描述 {@link $b.App.formatter 点击进入子类}
	 	    	 * datagrid列表数据中的列格式化处理类，专门用于对列数据项进行格式化处理。
	 	    	 * 
	 	    	 * - money：金额格式化子类
	 	    	 * - file：文件处理子类
		    	 * 
		    	 * ### 示例
		    	 * 
	 	    	 *     @example
	 	    	 *      var formatterObject =$b.App.formatter;
		    	 * 
				 * @member $b.App
		    	 * @property {Object} formatter
		    	 * @author luow 2016-5-20
		    	 * 
		    	 */
                formatter: _formatter
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
			return App.getInstance();
		}
	};
});