/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : jQuery EasyUI的公共设置模块 <br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-30<br>
 */
define(['jquery','common','locale','easyui-base'], function($,$b,locale){
	/**
	 * 修复使用requirejs导入EasyUI时渲染异常的问题
	 * 解决思路是在页面DOM加载完毕后才执行EasyUI的渲染
	 * @author OuLinhai
	 * @date 2016-5-14
	 */
	$.parser.auto = false;
	$(function($) {
		if(!$.parser.auto){
			$.parser.parse();
		}
	});
	
	//修改默认的加载提示
	$.fn.panel.defaults.loadingMessage = locale.operate.load;
	$.fn.datagrid.defaults.loadMsg = locale.operate.load;
	
	//修改数据表格默认配置
	$.extend($.fn.datagrid.defaults, {
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		fit: false,			//表格自动填满父容器
	    fitColumns:false,	//表列自动填充宽度
	    border : true,
	    resizable: true,
	    remoteSort: false,
	    striped:true,		//显示斑马线
	    rownumbers:true,	//显示数据序号列
	    scrollbarSize:0,
	    singleSelect:true,  //是否允许多选
	    checkOnSelect:false,//选择数据的时候是否勾选复选框,
	    selectOnCheck : false,
	    rowTool:true,
	    rowToolButtons:[ {  //行级工具栏按钮配置 
			text : locale.button.look, iconCls : 'icon-btn-look', handler : function(index,data){alert(index+"||"+JSON.stringify(data));}
		}, {
			text : locale.button.edit, iconCls : 'icon-btn-edit', handler : function(index,data){alert(index+"||"+JSON.stringify(data));}
		}, {
			text : locale.button.remove, iconCls : 'icon-btn-delete', handler : function(index,data){alert(index+"||"+JSON.stringify(data));}
		}],
	    onLoadSuccess : function() {
			$b.Msg.closeProgress();//可以放入公共js
			$(this).datagrid('clearSelections');
			$(this).datagrid('clearChecked');
			$(this).datagrid("tooltip");
			$(this).datagrid('createRowTool');
		},
		onLoadError : function(){
			$b.Msg.closeProgress();
			$b.Msg.error(locale.error.loadData);
		}
	});
	
	//修改dialog默认配置
	$.extend($.fn.dialog.defaults, {
		minimizable: false,
	    maximizable: true,
	    resizable: true,
	    modal: true,
	    striped: true,
	    onLoad:function(){
	    	$(".bf4j-group,.bf4j-group-more").bf4jGroupEvent();
	    }
	});
	
	//修改panel默认配置
	$.extend($.fn.panel.defaults, {
		onLoadError : function(xhr) {
			var re = /<body[^>]*>((.|[\n\r])*)<\/body>/im,
				text = xhr.responseText,
				body;
			if(text){
				body = re.exec(text);
				if(body){
					$(this).html(body[1]);
				}else{
					$(this).html(text);
				}
			}else{
				$(this).html(locale.error.loadError);
			}
		}
	});
	
	/**
	 * 修改combotree设置值时的bug
	 * @author LinJisong
	 * @date 2016-5-11
	 */
	var combotreeSetValues = $.fn.combotree.methods.setValues;
	if(combotreeSetValues){
		if(combotreeSetValues.aopFlag !== true){//防止重复处理，这里添加处理标志
			$.fn.combotree.methods.setValues = function(jq, values){
				var me = this,
					arrs = [],
	    			push = function(index, item){
						if(item){
							if($.isArray(item)){
			    				$.each(item, push);
			    			}else if(-1 === $.inArray(item, arrs)){
			    				arrs.push(item);	
							}
						}
		    		};
		    	$.each(values, push);
		    	if(arrs.length >= 1){
		    		combotreeSetValues.apply(me, arguments);
		    	}
			};
			$.fn.combotree.methods.setValues.aopFlag = true;
		}
	} 
	
	/**
	 * 解决layout、panel(及其继承组件) 使用 iframe 嵌入网页时的内存泄漏问题
	 * @author OuLinhai
	 * @date 2016-5-14
	 */
    var onBeforeDestroy = function () {
        $("iframe,frame", this).each(function () {
            try {
                if (this.contentWindow && this.contentWindow.document && this.contentWindow.close) {
                    this.contentWindow.document.write("");
                    this.contentWindow.close();
                }
                if ($.isFunction(window.CollectGarbage)) { window.CollectGarbage(); }
            } catch (ex) { }
        }).remove();
    };
    $.fn.panel.defaults.onBeforeDestroy = onBeforeDestroy;
    $.fn.window.defaults.onBeforeDestroy = onBeforeDestroy;
    $.fn.dialog.defaults.onBeforeDestroy = onBeforeDestroy;
    $.fn.datagrid.defaults.onBeforeDestroy = onBeforeDestroy;
    $.fn.propertygrid.defaults.onBeforeDestroy = onBeforeDestroy;
    $.fn.treegrid.defaults.onBeforeDestroy = onBeforeDestroy;
    

    
	/**
	 * 为EasyUI Form添加getData方法
	 * @author OuLinhai
	 * @date 2016-5-14
	 */
//var getFormData = function(target, param) {
//    if (!param) {
//        var state = $.data(target, "form"), opts = state ? state.options : $.fn.form.defaults;
//        param = opts.serializer;
//    }
//    return $(target).serializeObject(param);
//};
//$.extend($.fn.panel.methods, {
//	getData: function (jq, param) { return getFormData(jq[0], param); }
//});
    
	/**
	 * 为EasyUI validatebox添加校验规则
	 * @author OuLinhai
	 * @date 2016-5-14
	 */
    var validateRuls = {
    	select: {
            validator: function (value) {
                return value !== ' 请选择   ' && value !== '--请选择--' && value !== '请选择';
            },
            message: locale.validate.select
        },
        //只允许输入英文字母或数字
        engNum: {
            validator: function (value) {
                return /^[0-9a-zA-Z]*$/.test(value);
            },
            message: locale.validate.engNum
        },
        //只允许汉字、英文字母或数字
        chsEngNum: {
            validator: function (value, param) {
                return /^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9])*$/.test(value);
            },
            message: locale.validate.chsEngNum
        },
        //是否为合法的用户名
        name: {
            validator: function (value) { 
            	return $b.Check.isUserName(value);
            },
            message: locale.validate.name
        },
        //只允许汉字、英文字母、数字及下划线
        code: {
            validator: function (value, param) {
                return /^[\u0391-\uFFE5\w]+$/.test(value);
            },
            message: locale.validate.code
        },
        //指定字符最小长度
        minLength: {
            validator: function (value, param) {
            	return $.trim(value).length >= param[0]; 
            },
            message: locale.validate.minLength
        },
        //指定字符最大长度
        maxLength: {
            validator: function (value, param) {
            	return $.trim(value).length <= param[0]; 
            },
            message: locale.validate.maxLength
        },
        //电话号码(中国)格式
        tel: {
            validator: function (value) {
            	return $b.Check.isTel(value);
            },
            message: locale.validate.tel
        },
        //移动电话号码(中国)格式
        mobile: {
            validator: function (value) { 
            	return $b.Check.isMobile(value);
            },
            message: locale.validate.mobile
        },
        //电话号码或手机号码
        telOrMobile: {
            validator: function (value) {
            	return $b.Check.isTelOrMobile(value); 
            },
            message: locale.validate.telOrMobile
        },        
        //传真号码(中国)格式
        fax: {
            validator: function (value) { 
            	return $b.Check.isFax(value);
            },
            message: locale.validate.fax
        },
        //邮政编码(中国)格式
        zipCode: {
            validator: function (value) { 
            	return $b.Check.isZipCode(value); 
            },
            message: locale.validate.zipCode
        },
	    pattern : {
	    	validator: function(value, param){ 
	    		var p = param[0], m = param[1];
	    		if(typeof p === 'string'){
	    			p = new RegExp(p);
	    		}
	            var rs = p.test(value);
	            if(!rs){
	            	$(this).validatebox('options').rules.pattern.message = m || locale.error.inputContentError;
	            }
	            return rs;
	        },    
	        message: locale.validate.incorrect
	    },
        //必须包含指定的内容
        contains: {
            validator: function (value, param) {
            	return $b.String.contains(value, param[0]); 
            },
            message: locale.validate.contains
        },
        //以指定的字符开头
        startsWith: {
            validator: function (value, param) {
            	return $b.String.startsWith(value, param[0]); 
            },
            message: locale.validate.startsWith
        },     
        //以指定的字符结束
        endsWith: {
            validator: function (value, param) {
            	return $b.String.endsWith(value, param[0]); 
            },
            message: locale.validate.endsWith
        },
        //长日期时间(yyyy-MM-dd hh:mm:ss)格式
        longDate: {
            validator: function (value) {
            	return $b.Check.isLongDate(value); 
            },
            message: locale.validate.longDate
        },
        //短日期(yyyy-MM-dd)格式
        shortDate: {
            validator: function (value) {
            	return $b.Check.isShortDate(value); 
            },
            message: locale.validate.shortDate
        },         
        //必须包含中文汉字
        existChinese: {
            validator: function (value) {
            	return $b.String.existChinese(value); 
            },
            message: locale.validate.existChinese
        },
        //必须是纯中文汉字
        chinese: {
            validator: function (value) {
            	return $b.Check.isChinese(value); 
            },
            message: locale.validate.chinese
        },
        //必须是纯英文字母
        english: {
            validator: function (value) {
            	return $b.Check.isEnglish(value); 
            },
            message: locale.validate.english
        },
        //必须是合法的文件名(不能包含字符 \\/:*?\"<>|)
        fileName: {
            validator: function (value) {
            	return $b.Check.isFileName(value);
            },
            message: locale.validate.shortDate
        },
        //必须是正确的 IP地址v4 格式
        ip: {
            validator: function (value) {
            	return $b.Check.isIPv4(value); 
            },
            message: locale.validate.ip
        },
        //必须是正确的 url 格式
        url: {
            validator: function (value) {
            	return $b.Check.isUrl(value); 
            },
            message: locale.validate.url
        },        
        //必须是正确 QQ 号码格式
        qq: {
            validator: function (value) {
            	return $b.Check.isQQ(value); 
            },
            message: locale.validate.qq
        },
        //必须是合法的汽车车牌号码格式
        carNo: {
            validator: function (value) {
            	return $b.Check.isCarNo(value); 
            },
            message: locale.validate.carNo
        },
        //必须是合法的汽车发动机序列号格式
        carEngineNo: {
            validator: function (value) { 
            	return $b.Check.isCarEngineNo(value); 
            },
            message: locale.validate.carEngineNo
        },
        //必须是合法的身份证号码(中国)格式
        idCard: {
            validator: function (value) { 
            	return $b.Check.isIDCard(value); 
            },
            message: locale.validate.idCard
        },
        //必须是合法的整数格式
        integer: {
            validator: function (value) { 
            	return $b.Check.isInteger(value); 
            },
            message: locale.validate.integer
        },
        //必须是合法的整数格式且值介于 {0} 与 {1} 之间
        integerRange: {
            validator: function (value, param) {
                return $b.Check.isInteger(value) 
                	&& ((param[0] === undefined || value >= param[0]) && (param[1] === undefined || value <= param[1]));
            },
            message: locale.validate.integerRange
        },
        //必须是指定类型的数字格式
        numeric: {
            validator: function (value, param) { 
            	return $b.Check.isNumeric(value, param ? param[0] : undefined); 
            },
            message: locale.validate.numeric
        },
        //必须是指定类型的数字格式且介于 {0} 与 {1} 之间
        numericRange: {
            validator: function (value, param) {
                return $b.Check.isNumeric(value, param ? param[2] : undefined)
                    && ((param[0] === undefined || value >= param[0]) && (param[1] === undefined || value <= param[1]));
            },
            message: locale.validate.numericRange
        },        
	    same : {
			validator : function(value, param){
				//var cv = $(this).closest('form').find('[name='+param[0]+']').val();
				var cv = $('#'+param[0]).val(); 
				if(cv != "" && value != ""){
					return cv == value; 
				}else{
					return true;
				}
			},
			message : locale.validate.same
		}	
    };
    $.extend($.fn.validatebox.defaults.rules, validateRuls);
    
    //验证框扩展方法
    var extValidMethods = {
    	/**
    	 * 设置值
    	 * @param target
    	 * @param value
    	 */
        setValue : function (target, value) {
            var t = $(target), opts = t.validatebox("options"), val = t.val();
            if (val != value) {
                t.val(opts.value = (value ? value : ""));
            }
            validate(target);
        },

        /**
         * 获取值
         * @param target
         * @returns
         */
        getValue : function(target) {
            return $(target).val();
        },

        /**
         * 清除值
         * @param target
         */
        clear : function(target) {
            var t = $(target), opts = t.validatebox("options");
            t.validatebox("setValue", "");
        },

        /**
         * 重置值
         * @param target
         */
        reset : function(target) {
            var t = $(target), opts = t.validatebox("options");
            t.validatebox("setValue", opts.originalValue ? opts.originalValue : "");
        },

        /**
         * 重新设置大小
         * @param target
         * @param width
         */
        resize : function(target, width) {
            var t = $(target), opts = t.validatebox("options");
            t._outerWidth(opts.width = width);
        },

        /**
         * 设置可用性
         * @param target
         * @param disabled
         */
        setDisabled : function(target, disabled) {
            var t = $(target), state = $.data(target, "validatebox");
            if (disabled) {
                if (state && state.options) { state.options.disabled = true; }
                t.attr("disabled", true);
            } else {
                if (state && state.options) { state.options.disabled = false; }
                t.removeAttr("disabled");
            }
        },
        
        /**
         * 设置和取消红星（标识是否必填）
         * @param target
         * @param disabled
         */
        setStar : function(target, disabled){
            var t = $(target);
            var label = t.closest('.bf4j-cell').prev('.bf4j-cell').find('label');
            if (disabled) {
                label.append('<font color=red>*</font>');
            } else {
                label.remove('font');
            }
        }
    };
    $b.Base.union($.fn.validatebox.methods, {
        setValue: function (jq, value) { 
        	return jq.each(function () { extValidMethods.setValue(this, value); }); 
        },

        getValue: function (jq) { 
        	return extValidMethods.getValue(jq[0]); 
        },

        clear: function (jq) { 
        	return jq.each(function () { extValidMethods.clear(this); }); 
        },

        reset: function (jq) { 
        	return jq.each(function () { extValidMethods.reset(this); }); 
        },

        resize: function (jq, width) { 
        	return jq.each(function () { extValidMethods.resize(this, width); }); 
        },

        enable: function (jq) { 
        	return jq.each(function () { extValidMethods.setDisabled(this, false); }); 
        },

        disable: function (jq) { 
        	return jq.each(function () { extValidMethods.setDisabled(this, true); }); 
        },
        
        unStar: function (jq) { 
        	return jq.each(function () { extValidMethods.setStar(this, false); }); 
        },
        
        setStar: function (jq) { 
        	return jq.each(function () { extValidMethods.setStar(this, true); }); 
        }
    });
    
    
    /**
     * 为datagrid和treegrid添加tooltip
     * 使用样例：
     *   $("#dg").datagrid('tooltip'); 所有列
     *   $("#dg").datagrid('tooltip',['productid','listprice']); 指定列
     * @author 夏悸
     */
    var gridTooltipOptions = {
		tooltip : function(jq, fields) {
			return jq.each(function() {
				var panel = $(this).datagrid('getPanel');
				if (fields && typeof fields == 'object' && fields.sort) {
					$.each(fields, function() {
						var field = this;
						bindEvent($('.datagrid-body td[field=' + field + '] .datagrid-cell', panel));
					});
				} else {
					bindEvent($(".datagrid-body .datagrid-cell", panel));
				}
			});

			function bindEvent(jqs) {
				jqs.mouseover(function() {
					var content = $(this).text();
					if (content.replace(/(^\s*)|(\s*$)/g, '').length > 5) {
						$(this).tooltip({
							content : content,
							trackMouse : true,
							position : 'bottom',
							onHide : function() {
								$(this).tooltip('destroy');
							},
							onUpdate : function(p) {
								var tip = $(this).tooltip('tip');
								if (parseInt(tip.css('width')) > 500) {
									tip.css('width', 500);
								}
							}
						}).tooltip('show');
					}
				});
			}
		}
	};
	$.extend($.fn.datagrid.methods, gridTooltipOptions);
	$.extend($.fn.treegrid.methods, gridTooltipOptions);
	
	/**
	 * 为datagrid添加浮动行图标按钮组
	 * @author leo yang
	 * @date 2016-5-6
	 */
	$.extend($.fn.datagrid.methods, {
		createRowTool: function(jq){   
			var grid=jq, opt = grid.datagrid("options");
	        if(opt.rowTool&&opt.rowToolButtons.length>0)
	        {
	        	var dataRows=grid.datagrid("getRows");
	        	var dpanel=grid.datagrid("getPanel"), htmlRows;
	        	if(dpanel.find(".datagrid-view2 .datagrid-row").length>0)
	        	{
	        		htmlRows=dpanel.find(".datagrid-view2 .datagrid-row");
	        	}else{
	        		htmlRows=dpanel.find(".datagrid-view1 .datagrid-row");
	        	}
	        	
	        	$.each(htmlRows,function(index,s){        		
	        		var $rowTool=$(s).find(".datagrid-row-tool");
	            	if($(s).find("td").eq(0).find(".datagrid-row-tool").length==0){
	    	        	    $rowTool=$("<div class='datagrid-row-tool' ></div>");
	    	        	$.each(opt.rowToolButtons,function(ii,ss){
	    	        		if(ss.check === undefined || typeof ss.check==='function' && ss.check(index,dataRows[index])){
		    	        		var $ibtn=$("<i class='datagrid-row-tool-icon "+ss.iconCls+"' title='"+ss.text+"'  ></i>");
		    	        		$ibtn.on("click",function(){ss.handler(index,dataRows[index])});
		    	        		$rowTool.append($ibtn);
	    	        		}
	    	        	});
	    	        	$(s).find("td").eq(0).append($rowTool);
	    	        	var top=$(s).position().top;
	        			var right ="5%";
	        			if(opt.rowTool.positionRight){
	        				right=opt.rowTool.positionRight;
	        			}
	        			$rowTool.css("top",top).css("right",right);
	            	}
	            	var $tr=$("#datagrid-row-r1-1-"+index+",#datagrid-row-r1-2-"+index);
	            	$tr.on("mouseover",function(event){
	        			if($rowTool.css("display")=="block"){return;}
	        			dpanel.find(".datagrid-row-tool").hide();
	        			//$rowTool.show();
	        			$rowTool.css("top", $tr.position().top).show();
	        			$tr.addClass("datagrid-row-over");
	        			event.stopPropagation();
	        		}).on("mouseout",function(){
	        			$tr.removeClass("datagrid-row-over");
	        		});
	        	});
	        	dpanel.on("mouseout",function(){
	        		dpanel.find(".datagrid-row-tool").hide();
	        	});
	        }  
	    },
	    /**
		 * 表格下载
		 */
		download : function(jq, downloadId, options){
			$b.Download.download(downloadId, options, undefined, jq[0]);
		}
	});
	
	//扩展form
	$.extend($.fn.form.methods, {
		/**
		 * 表单下载
		 */
		download : function(jq, downloadId, options){
			$b.Download.download(downloadId, options, jq[0]);
		},
		/**
		 * 获取表单数据
		 * 先对简单类型做处理，后续遇到再对其它特殊类型处理
		 */
		getValues : function(jq){
			var values = {};
			$('input:enabled,select:enabled,textarea:enabled', jq[0]).each(function(){
				var m = this, name = m.name;
				if(name){
					switch(m.type){
					case 'radio':
						if($(m).is(':checked')){
							values[name] = m.value;
						}
						break;
					case 'checkbox':
						if($(m).is(':checked')){
							var v = values[name]||[];
							v.push(m.value);
							values[name] = v;
						}
						break;
					default:
						values[name] = m.value;
						break;
					}
				}
			});
			return values;
		}
	});
});	
