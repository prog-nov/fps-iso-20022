/**
 * Create a cookie with the given key and value and other optional parameters.
 * 
 * @example $.cookie('the_cookie', 'the_value');
 * @desc Set the value of a cookie.
 * @example $.cookie('the_cookie', 'the_value', { expires: 7, path: '/', domain: 'jquery.com', secure: true });
 * @desc Create a cookie with all available options.
 * @example $.cookie('the_cookie', 'the_value');
 * @desc Create a session cookie.
 * @example $.cookie('the_cookie', null);
 * @desc Delete a cookie by passing null as value. Keep in mind that you have to use the same path and domain used when the cookie was set.
 * 
 * @param String
 *            key The key of the cookie.
 * @param String
 *            value The value of the cookie.
 * @param Object
 *            options An object literal containing key/value pairs to provide optional cookie attributes.
 * @option Number|Date expires Either an integer specifying the expiration date from now on in days or a Date object. If a negative value is specified (e.g. a date in the past), the cookie will be deleted. If set to null or omitted, the cookie will be a session cookie and will not be retained when the the browser exits.
 * @option String path The value of the path atribute of the cookie (default: path of page that created the cookie).
 * @option String domain The value of the domain attribute of the cookie (default: domain of page that created the cookie).
 * @option Boolean secure If true, the secure attribute of the cookie will be set and the cookie transmission will require a secure protocol (like HTTPS).
 * @type undefined
 * 
 * @name $.cookie
 * @cat Plugins/Cookie
 * @author Klaus Hartl/klaus.hartl@stilbuero.de
 * 
 * Get the value of a cookie with the given key.
 * 
 * @example $.cookie('the_cookie');
 * @desc Get the value of a cookie.
 * 
 * @param String
 *            key The key of the cookie.
 * @return The value of the cookie.
 * @type String
 * 
 * @name $.cookie
 * @cat Plugins/Cookie
 * @author Klaus Hartl/klaus.hartl@stilbuero.de
 */
$.cookie = function(key, value, options) {
	if (arguments.length > 1 && (value === null || typeof value !== "object")) {
		options = $.extend({}, options);
		if (value === null) {
			options.expires = -1;
		}
		if (typeof options.expires === 'number') {
			var days = options.expires, t = options.expires = new Date();
			t.setDate(t.getDate() + days);
		}
		return (document.cookie = [ encodeURIComponent(key), '=', options.raw ? String(value) : encodeURIComponent(String(value)), options.expires ? '; expires=' + options.expires.toUTCString() : '', options.path ? '; path=' + options.path : '', options.domain ? '; domain=' + options.domain : '', options.secure ? '; secure' : '' ].join(''));
	}
	options = value || {};
	var result, decode = options.raw ? function(s) {
		return s;
	} : decodeURIComponent;
	return (result = new RegExp('(?:^|; )' + encodeURIComponent(key) + '=([^;]*)').exec(document.cookie)) ? decode(result[1]) : null;
};

/**
 *
 * 
 * @requires jQuery
 * 
 * 将form表单元素的值序列化成对象
 * 
 * @returns object
 */
$.serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};

/**
 * 
 * 实现拦截ajax请求功能
 * 由于直接使用$.ajaxSetup添加success、complete、error等配置时，有可能被具体调用$.ajax时的参数给覆盖掉（比如easyUI组件默认的success回调）
 * 因此这里先修改$.ajaxSetup函数，实现类似于AOP的功能，而AOP的逻辑则是合并公共回调和用户回调（如果有的话）
 * 公共回调则是对后台异常做处理
 * add by LinJisong
 * 2016-7-22
 */
(function($){
	if($.ajaxSetup.updateFlag !== true){//防止多次引入该文件时，出现重复处理
		var jQueryAjaxSetup = $.ajaxSetup,
			ajaxSuccess = function(data, textStatus, jqXHR){
				if(data && data.success === false && data.data){
					if(data.data.detail){
						top.$.messager.progress('close');
						top.$.messager.alert('错误', data.data.detail.replace('\r\n','</br>'),'error');
					}else{
						var rd = data.data,
							code = rd.code;
						if(code === 'BF040001'){
							top.$.messager.progress('close');
							top.$.messager.alert('错误', "对不起，您访问的功能尚未完成开发，URL：" + rd.message,'error');
						}else if(code === 'BF040002'){
							top.$.messager.progress('close');
							top.$.messager.alert('错误', "对不起，您的会话已经超时，请点击【确定】按钮后重新登录",'error',function(){
								top.location = server_consts.root + "/index"; 
							}); 
						}else if(code === 'BF040003'){
							top.$.messager.progress('close');
							top.$.messager.alert('错误', "对不起，您没有访问该功能的权限，URL：" + rd.message,'error');
						}else if(rd.message){
							top.$.messager.progress('close');
							top.$.messager.alert('错误', rd.message.replace('\r\n','</br>'),'error');
						}
					}
				}
			};
		$.ajaxSetup = function(target, settings){
			settings = settings || {};
			if(typeof settings.success === 'function'){
				settings.success = [ajaxSuccess, settings.success];
			}else{
				settings.success = ajaxSuccess;
			}
			return jQueryAjaxSetup.call(this, target, settings);
		};
		$.ajaxSetup.updateFlag === true;
	}
})(jQuery);


/**
 *
 * 
 * @requires jQuery
 * 
 * 改变jQuery的AJAX默认属性和方法 update by luow  2016/05/14
 * 
 * 主要是增加对ajax的拦截处理，如果是会话超时，则重新跳转到登录界面。
 * 
 */
$.ajaxSetup({
	type : 'POST',
	error : function(XMLHttpRequest, textStatus, errorThrown) {
		try {
			top.$.messager.progress('close');
			top.$.messager.alert('错误', XMLHttpRequest.responseText);
		} catch (e) {
			top.$.messager.alert(XMLHttpRequest.responseText);
		}
	},
	contentType : "application/x-www-form-urlencoded;charset=utf-8", 
	complete: function(xhr, textStatus) {
		if(xhr.responseText){
			if(xhr.responseText.indexOf('data') !== -1 
					&& xhr.responseText.indexOf(':') !== -1
					&& xhr.responseText.indexOf('code') !== -1
					&& xhr.responseText.indexOf('message') !== -1){
				var rtnMsg = eval("(" + xhr.responseText + ')')
				if ("BF040002" == rtnMsg.data.code) 
				{ 
					top.$.messager.progress('close');
					top.$.messager.alert("会话超时",rtnMsg.data.message + "，点击【确定】按钮后需重新登录",'error',function(){
						top.location = server_consts.root + "/"; 
					}); 
					return;
				}else if("BF040001" == rtnMsg.data.code || "BF040003" == rtnMsg.data.code ){
					top.$.messager.progress('close');
					top.$.messager.alert(rtnMsg.data.message); 
					return;
				}else{
					top.$.messager.progress('close');
					return;
				}
			}
		}
	}
});
/**
*
* 
* 扩展jquery方法
* 
*/
jQuery.fn.extend({
	  /**给group增加事件处理**/
	  bf4jGroupEvent: function(callback) {
	    return this.each(function() { 

	    	/***1、给group增加收缩效果***/
	    	if($(this).children(".bf4j-group-title").length>0){
				//初始化元素	    		
		    	if($(this).find(".bf4j-group-content").css("display") == "block"){
					if($(this).find(".bf4j-group-title").find("i").length<=0){
						$(this).find(".bf4j-group-title").append("<i></i>");
					}
					$(this).find(".bf4j-group-title").find("i").attr("class","icon-sup");
				}else{
					if($(this).find(".bf4j-group-title").find("i").length<=0){
						$(this).find(".bf4j-group-title").append("<i></i>");
					}
					$(this).find(".bf4j-group-title").find("i").attr("class","icon-sdown");
				}
		    	
		    	//绑定事件
		    	$(this).find(".bf4j-group-title").unbind("click").on("click",function() {
					var titleobj = $(this);
					titleobj.next(".bf4j-group-content").slideToggle(200,function() {
						if (titleobj.next(".bf4j-group-content").css("display") == "block") {
							titleobj.find("i").attr("class","icon-sup");
						} else {
							titleobj.find("i").attr("class","icon-sdown");
						}
						if(callback){
							callback();
						}
					});
				})
	    	}

	    	/***2、给group中的label增加提示***/
	    	if($(this).find(".bf4j-line label").length>0)
	    	{
	    		$(this).find(".bf4j-line label").each(function(){
	    			$(this).attr("title",$(this).html());
	    		});
	    		$(this).find(".bf4j-line label").tooltip({  
	    			onShow: function(){  
	    				$(this).tooltip('tip').css({        
		    					backgroundColor: '#666',     
		    					borderColor: '#666', 
		    					color:'#ffffff'
	    					});  
	    				}
	    		});
	    	}
	    	
	    });
	  }
});

/**
*
* 全局处理代码区域
* 
*/
function overallCode()
{
	//工作区最外层面板Div
	var centerSectionDiv="body .easyui-layout .layout-panel-center .panel-body",
	    BODYSIZE=0;
	
	/***1、datagird到自适应***/
	if($('.bf4j-grid-auto').length>0)
	{
		setInterval(function(){
			autoGirdWidth();
		},500);
		
		var autoGirdWidth=function()
		{
			var centerDiv=$(centerSectionDiv)[0];
			if(!centerDiv){return;}
			var bodyWidth=document.body.clientWidth-20;
			if(centerDiv.scrollHeight>centerDiv.clientHeight)
			{
				//内容区有滚动条 减去滚动条占用宽度
				bodyWidth-=16;
			}
			if(bodyWidth!=BODYSIZE){
				BODYSIZE=bodyWidth;
				$('.bf4j-grid-auto').datagrid("resize",{width:bodyWidth});
			}
		}
	}

	/***2、返回顶部效果***/
	setTimeout(function(){
		var centerDiv=$(centerSectionDiv)[0];		
		if($(centerDiv).length>0){
			$("body").append("<a href='javascript:void 0;' class='gotop'></a>");
		}
		$(centerDiv).scroll(function(){
			var _top = $(centerDiv).scrollTop();
			if(_top>30){
				$('.gotop').fadeIn(600);
			}else{
				$('.gotop').fadeOut(600);
			}
		});
		$(".gotop").click(function(){
			$(centerDiv).animate({scrollTop:0},300);
		});
	},1000)
	
	/***3、解决按回退键导致触发history.back()的Bug,弹出层增加Esc快捷键关闭***/
	$(document).keydown(function(e){
		if(e.which===27){
			$(".window").find(".panel-tool-close").click();
		} else if(e.which ===  8){
			var target = e.target;
			if(target.tagName.toUpperCase() !== "INPUT" && target.tagName.toUpperCase() !== "TEXTAREA" 
					||  target.readOnly || target.disabled ){
				e.preventDefault();
			}
		}
	});
	/***4、为必填的表单域label添加红星标记***/
	$('.easyui-textbox,.easyui-validatebox,.easyui-numberbox,.easyui-combobox').each(function(){
		var t = $(this),
			opt;
		try{
			opt = t.validatebox('options')
		}catch(e){
			opt = null;
		}
		if(opt && opt.required){
			t.validatebox('setStar');
		}
	});
	
}

//页面初始化完需要调用的代码
$(function(){
	//延时加载避免easyui尚未加载
	setTimeout(function(){
		$(".bf4j-group,.bf4j-group-more").bf4jGroupEvent();
		overallCode();	
	},1000);
});
