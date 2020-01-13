/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 首页框架JS模块<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-15<br>
 */
define('mainframe',['beneform4j','rsa','beneform4j-easyui','easyui-portal',
          'easyui-layout','frame-plus'], function($b,$rsa){
   	var mainLayout = '#main_layout', mainTabs = '#index_tabs', dialog="#dialog",
   	    mainTabsCtxMenu = '#index_tabsMenu',leftMenuTree = '#layout_west_tree',
   	    ctxPath = $b.context.root+'/', leftMenuTreeUrl = ctxPath+'leftMenu',
   	    topMenuTreeUrl = ctxPath+'topMenu', shortMenuTreeUrl = ctxPath+'shortCutMenu',
   	    btnExit = 'a.btn-logout',btnLang="a.btn-lang",
   	    topMessageUrl = ctxPath+'getTopMessage',
   	    miniMenuUrl = ctxPath+'miniMenu',addToMainTab,
   	    converLangUrl = ctxPath+'uiholder/changeLocale',
   	    getLangUrl = ctxPath+'portal/setting/queryUserSettingsMap',
   	    saveFavoritesUrl = ctxPath+'common/favorites/insert';
   	
   	addToMainTab = function(params) {
   		// temp start 解决快捷菜单兼容问题
   		var iframe = '<iframe src="' + params.url + '" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>',
			t = $(mainTabs),
			opts = {
				title : "<span title='"+params.title+"'  path='" + (params.path||'') + "' menuId='" 
					+ (params.menuId ||'') + "' menuFlag='$"+(params.menuId ||'')+"$' style='width: 80px;overflow: hidden;display: block;float: left;boder=0;text-align: center;'>" + params.title + "</span>",
				closable : true,
				iconCls : '',
				content : iframe,
				border : false,
				fit : true
			};
		var tabs = t.find(".tabs-title");
		for(var i = 0; i<tabs.length; i++){
			if($(tabs[i]).html().indexOf("$"+params.menuId+"$")>-1){
				t.tabs('select', i);
				$b.Msg.closeProgress();
				return;
			}
		}
		if(t.tabs('tabs').length > $b.maxTabsNum){
			$b.Msg.closeProgress();
			$b.Msg.warning($b.Base.i18n("operate.openTabMax"));
			return false;
		} else {
			t.tabs('add', opts);
		}
		// temp end
//		var iframe = '<iframe src="' + params.url + '" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>',
//		t = $(mainTabs),
//		opts = {
//			title : "<span title='"+params.title+"'  path='" + (params.path||'') + "' menuId='" 
//				+ (params.menuId ||'') + "' style='width: 80px;overflow: hidden;display: block;float: left;boder=0;text-align: center;'>" + params.title + "</span>",
//			closable : true,
//			iconCls : '',
//			content : iframe,
//			border : false,
//			fit : true
//		};
//		if (t.tabs('exists', opts.title)) {
//			t.tabs('select', opts.title);
//			$b.Msg.closeProgress();
//		} else {
//			if(t.tabs('tabs').length > $b.maxTabsNum){
//				$b.Msg.closeProgress();
//				$b.Msg.warning($b.Base.i18n("operate.openTabMax"));
//				return false;
//			} else {
//				t.tabs('add', opts);
//			}
//		}
	};
	
	
 		
   	return {
   		instMainLayout : function(){
   			$(mainLayout).layout({
   				fit : true
   			});
   		},
   		instRefresh : function(){
   			//首页刷新按钮增加鼠标悬停提示信息 
   			$(".bf4j-icon-node-62").attr({title:"刷新"});
   		},
   		instMainTabs : function(){
   			var t = $(mainTabs);
   			t.tabs({
   				fit : true,
   				border : false,
   				onContextMenu : function(e, title) {
   					e.preventDefault();
   					$(mainTabsCtxMenu).menu('show', {
   						left : e.pageX,
   						top : e.pageY
   					}).data('tabTitle', title);
   				},
   				tools : [ {
   					iconCls : 'bf4j-icon-node-62',
   					handler : function() {
   						if(t.tabs('getSelected').panel('options').title == $b.Base.i18n("title.home")){
   							return ;
   						}
   						$b.Msg.openProgress();
   						var href = t.tabs('getSelected').panel('options').href;
   						if (href) {/*说明tab是以href方式引入的目标页面*/
   							var index = t.tabs('getTabIndex', t.tabs('getSelected'));
   							t.tabs('getTab', index).panel('refresh');
   						} else {/*说明tab是以content方式引入的目标页面*/
   							var panel = t.tabs('getSelected').panel('panel');
   							var frame = panel.find('iframe');
   							try {
   								if (frame.length > 0) {
   									for ( var i = 0; i < frame.length; i++) {
   										frame[i].contentWindow.document.write('');
   										frame[i].contentWindow.close();
   										frame[i].src = frame[i].src;
   									}
   									if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
   										try {
   											CollectGarbage();
   										} catch (e) {
   										}
   									}
   								}
   							} catch (e) {
   							}
   						}
   					}
   				}],
   				//首页右上角删除按钮
   				/*{
   					iconCls : 'bf4j-icon-node-53',
   					handler : function() {
   						var index = t.tabs('getTabIndex', t.tabs('getSelected'));
   						var tab = t.tabs('getTab', index);
   						if (tab.panel('options').closable) {
   							t.tabs('close', index);
   						} else {
   							$b.Msg.error($b.Base.i18n("operate.notClose",'[' + tab.panel('options').title + ']'));
   						}
   					}
   				}*/ 
   				onBeforeClose : function(title,index){
					var target = this;
					if($(title).text() === $b.Base.i18n("title.home")){
						$b.Msg.confirm($b.Base.i18n("operate.closeHome"),function(r){
							if (r){
								var opts = $(target).tabs('options');
								var bc = opts.onBeforeClose;
								opts.onBeforeClose = function(){};  // 允许现在关闭
								$(target).tabs('close',index);
								opts.onBeforeClose = bc;  // 还原事件函数
							}
						});
						return false;	// 阻止关闭
					}
   				},
   				onSelect : function(title,index){
					// 标签切回首页时，对待办事项数进行更新
					if(index==0 && top.frames.length > 1){
						var hasTaskFlag = false;
						var frame = top.frames[0];	// 获取首页frame
						var taskUpdateFlag = frame.$("#taskUpdateFlag").val();	// 更新状态标识：0 未执行；1 执行中
						var taskObjs = frame.$(".scrollContainer").find("input[id^='taskId']");
						var taskIds = [];
				        if(taskObjs != undefined && taskObjs.length > 0 && taskUpdateFlag == 0){
					       	frame.$("#taskUpdateFlag").val("1");	// 更新状态为执行中
					       	$.each(taskObjs, function(j, task){
					       		taskIds.push($(task).attr('taskId'));
					       	});
					       	hasTaskFlag = true;
				        }
				        if(hasTaskFlag){
				        	$b.Submit.ajaxSubmit($b.context.root+'/portal/center/queryTaskNum', {'needBlock':false,'taskIds':taskIds}, 
				        			function(data){ // 更新消息数成功时回调函数
					        		// 清除任务数及关闭事件
					        		frame.$(".scrollContainer").find("label[id^='panel_label']").html('0');
					        		frame.$(".scrollContainer").find("input[id^='taskId']").attr('taskNum',0);
					        		// 重置任务数
					        		$.each(data, function(j, task){
					        			frame.$('#panel_label_'+task.taskId).html(task.taskNum);
					        			frame.$('#taskId'+task.taskId).attr('targetUrl',task.targetUrl);
					        			frame.$('#taskId'+task.taskId).attr('taskNum',task.taskNum);
					        		});
					        		frame.$("#taskUpdateFlag").val("0");
				        		}
				        	);
				        }
					}
				}
   			});
   			addToMainTab({
   				title : $b.Base.i18n("title.home"),
   				url : $b.context.root + '/portal/center/queryCenterShowkData'
   			});
   		},
   	 	instMainTabsCtxMenu : function(){
   	 		var t = $(mainTabs);
   	 		$(mainTabsCtxMenu).menu({
   				onClick : function(item) {
   					var curTabTitle = $(this).data('tabTitle');
   					var type = $(item.target).attr('title');

   					if (type === 'refresh') {
   						t.tabs('getTab', curTabTitle).panel('refresh');
   						return;
   					}

   					if (type === 'close') {
   						var tab = t.tabs('getTab', curTabTitle);
   						if (tab.panel('options').closable) {
   							t.tabs('close', curTabTitle);
   						}
   						return;
   					}
   					
   					if (type === 'favorites') {  // 右键收藏
   						var param = {'menuId' : $(curTabTitle).attr('menuId'),'shortMenuName' : $(curTabTitle).html()};
   						if(param.menuId == ''){		// 判断菜单ID是否为空
   							$b.Msg.error($b.Base.i18n("system.menu.favorites.notSupport"));
   							return;
   						}
   						$b.Submit.ajaxSubmit(saveFavoritesUrl, param, function(data){	// 添加收藏
   							if(data == -9){	// 重复收藏提示
   								$b.Msg.alert($b.Base.i18n("system.menu.favorites.isExist"));
   							}
   							else if(data > 0){	// 收藏成功刷新快捷菜单
   								var ul = $(".pointPanel").find("ul");
   								ul.empty();
   	   				   	 		$b.Submit.ajaxSubmit(shortMenuTreeUrl,{},function(data){
   	   				   	        	$.each(data,function(i,t){
   	   				   	        		ul.append("<li><a href=\"javascript:void(0)\"><i class='"+t.icon+"'></i>"+t.text+"</a></li>");
   	   				   	        		ul.find("li a").eq(i).on("click",function(){
   	   				   	        			$b.App.openUrl(t.url,t.text,{targetPage : t.targetPage,path : t.path,menuId:t.code});
   	   				   	        			framePlus.pointMenu.close();
   	   				   	        		});
   	   				   	        	});
   	   				   			});
   	   				   	 		$b.Msg.alert($b.Base.i18n("system.menu.favorites.success"));
   							}
   							else{	// 收藏失败
   								$b.Msg.error($b.Base.i18n("system.menu.favorites.failure"));
   							}
   						});
   						return;
   					}

   					var allTabs = t.tabs('tabs');
   					var closeTabsTitle = new Array();

   					$.each(allTabs, function() {
   						var opt = $(this).panel('options');
   						if(opt.closable && $(opt.title).text() !== $b.Base.i18n("title.home")){
	   						if ($(opt.title).attr("menuFlag") != $(curTabTitle).attr("menuFlag") && type === 'closeOther') {
	   							closeTabsTitle.push(opt.title);
	   						} else if (type === 'closeAll') {
	   							closeTabsTitle.push(opt.title);
	   						}
   						}
   					});

   					for ( var i = 0; i < closeTabsTitle.length; i++) {
   						t.tabs('close', closeTabsTitle[i]);
   					}
   				}
   			});
   	 	},
   	 	instMenu : function(){
   	 		
   	 			//左边菜单参数对象
	 			var leftMenuOptions = {
				animate: true,
				onClick : function(node) {
					if (node && node.attributes.url) {
						if(node.attributes.disable=="true"){
							$b.Msg.warning($b.Base.i18n("operate.nothingRole")); 
							return ;
						}
						var url = node.attributes.url;
						//如果不是以http打头说明是打开本地资源，否则就是跨域资源
						if (url.indexOf('http://') === -1) {
							url = ctxPath + url;
							$b.Msg.openProgress();
						} 
						$(leftMenuTree).find(".selectedNode").removeClass("selectedNode");
						$(node.target).addClass("selectedNode");
						
						addToMainTab({
							url : url,
							title : node.text,
							path : node.attributes.path,
							iconCls : node.iconCls,
							menuId : node.id
						});
					}
					if(node && !node.isLeaf){
						$(leftMenuTree).tree('toggle', node.target);
						//得到展开图标所在元素
						var target = $(node.target).find('span[name=menu-tree-state]');
						//展开或收缩时切换图标
						if(node.state === 'closed'){
							target.removeClass('menu-tree-collapsed').addClass('menu-tree-expended');
						} else {
							target.removeClass('menu-tree-expended').addClass('menu-tree-collapsed');
						}
					}
				},
				onBeforeExpand:function(node){
					//互斥展开处理
					$(node.target).parent("li").parent("ul").children("li").children("div:has(.tree-expanded)").click();
				},
				onLoadSuccess : function(node, data) {
					if(data){
						//清除原来的树形展开图标
						$(leftMenuTree).find('span.tree-hit').css('visibility','hidden');
						//添加自己定义的展开图标
						$(leftMenuTree).find('.tree-node:has(span.tree-collapsed):not(:has(span[name=menu-tree-state]))').find('span.tree-title').after($('<span name="menu-tree-state" class="menu-tree-collapsed">&nbsp;&nbsp;&nbsp;</span>'));
					}
					$b.Msg.closeProgress();
				}
			};
   	 		
   	 		//加载左边菜单
   	 		var loadLeftMenu=function(node)
   	 		{   
  				if($b.Base.cache.get("TreeData-"+node.id)){
  					var treeData=$b.Base.cache.get("TreeData-"+node.id);
  					//加载minni菜单
  					framePlus.miniTree.init($(".mini-tree"),{"data":treeData,"contextPath" : ctxPath});
  					//加载左侧菜单
  					leftMenuOptions.data=treeData;
   	   				$(leftMenuTree).tree(leftMenuOptions);	
  				}else{
  		   	 		$b.Submit.ajaxSubmit(miniMenuUrl,{ id: node.id},function(treeData){
	  					//加载minni菜单
	   	   				framePlus.miniTree.init($(".mini-tree"),{"data":treeData,"contextPath" : ctxPath});
	   	   				//加载左侧菜单
	   	   				leftMenuOptions.data=treeData;
	   	   				$(leftMenuTree).tree(leftMenuOptions);
	   	   				$b.Base.cache.set(treeData,"TreeData-"+node.id);
  		   			});

  				}
   	 		};
   	 		
	 		//取顶部菜单树对象
   	 		$b.Submit.ajaxSubmit(topMenuTreeUrl,{},function(data){
   	        	loadLeftMenu(data[0]);
   	        	//加载顶部菜单
   	    	    framePlus.topTree.init($(".topmenu"),{"data":data,"contextPath" : ctxPath,
   	    	   			 "nodeClick":function(selector,node){
   	    	   				 	if(node&&!node.attributes.url){
   	    	   				 		loadLeftMenu(node);
   	    	   				 	} 	
   	    	   			 }
   	    	   	});
   			});
   	        
   	        //加载圆形图标悬浮菜单
   	        framePlus.pointMenu.init($("#pointMenu"),{ height: "280px", width: "200px", maxHeight: "440px", maxWidht: "770px" });
   	 	},
   	 	instShortMenu : function(){
   	 		var ul = $(".pointPanel").find("ul");
   	 		$b.Submit.ajaxSubmit(shortMenuTreeUrl,{},function(data){
   	        	$.each(data,function(i,t){
   	        		ul.append("<li><a href=\"javascript:void(0)\"><i class='"+t.icon+"'></i>"+t.text+"</a></li>");
   	        		ul.find("li a").eq(i).on("click",function(){
   	        			$b.App.openUrl(t.url,t.text,{targetPage : t.targetPage,path : t.path,menuId:t.code});
   	        			framePlus.pointMenu.close();
   	        		});
   	        	});
   			});
   	 	},
   	    instTopMessage:function(){
   	 		var msgUl=$(".messageSection .messageContent");
   	 		var msgJson;
   	 		
	   	 	$.getJSON(topMessageUrl, function(data){
	   	 		msgJson=data;
	   	 		if(!msgJson.success||msgJson.data.length<1)
		 		{
		 			$(".messageSection").empty();
		 			return;
		 		}
	   	   	    var $ul=$("<ul></ul>");
	   	   	    $.each(msgJson.data,function(i,s){
	   	   	    	var $li=$("<li><a href='javascript:void 0' >"+s.msgType+"："+s.msgTitle+"</a></li>");
	   	   	    	$ul.append($li);
	   	   	    	$li.find("a").on("click",function(){
	   	   	    		clickMessage(s);
	   	   	    	});
	   	   	    });
	   	   	    msgUl.empty();
	   	   	    msgUl.append($ul);
	   	   	    moveMessage(msgUl);
	   	 	});
	   	 	
   	   	    var clickMessage=function(json)
   	   	    {
   	   	    	var messageDetailDialog=$("#dialog-detail-message");
		   	 	$(messageDetailDialog).window({    
				    title: json.msgType,
				    minimizable: false,
				    collapsible: false,
				    width: 500,    
				    height: 380,    
				    href: $b.context.root  + '/common/viewNoteice?msgId=' + json.msgId
				}); 
   	   	    };
   	   	    
   	   	    var moveMessage=function(thiso,options){
   	   	    	
	   	   	    var d = {time: 3000,s: 'fontColor',num: 1};
	   	        var o = $.extend(d,options);
	   	        var _con = thiso.children('ul').eq(0);
	   	        var _conH = _con.height(); //滚动总高度
	   	        var _conChildH = _con.children().eq(0).height();//一次滚动高度
	   	        var _temp = _conChildH;  //临时变量
	   	        var _time = d.time;  //滚动间隔
	   	        var _s = d.s;  //滚动间隔
	
	   	        _con.clone().insertAfter(_con);//初始化克隆
	
	   	        //样式控制
	   	        var num = d.num;
	   	        var _p = thiso.find('li');
	   	        var allNum = _p.length;
	
	   	        _p.eq(num).addClass(_s);
	
	   	        var timeID = setInterval(Up,_time);
	   	        thiso.hover(function(){clearInterval(timeID);},function(){timeID = setInterval(Up,_time);});
	
	   	        function Up(){
	   	            _con.animate({marginTop: '-'+_conChildH});
	   	            //样式控制
	   	            _p.removeClass(_s);
	   	            num += 1;
	   	            _p.eq(num).addClass(_s);
	   	            
	   	            if(_conH == _conChildH){
	   	                _con.animate({marginTop: '-'+_conChildH},"normal",over);
	   	            } else {
	   	                _conChildH += _temp;
	   	            }
	   	        }
	   	        function over(){
	   	            _con.attr("style",'margin-top:0');
	   	            _conChildH = _temp;
	   	            num = 1;
	   	            _p.removeClass(_s);
	   	            _p.eq(num).addClass(_s);
	   	        }
   	   	    };
   	 		
   	 	},
   	 	// 刷新快捷菜单
        refreshPointPanel : function(){
        	var ul = top.$(".pointPanel").find("ul");
        	ul.empty();
   	 		$b.Submit.ajaxSubmit(shortMenuTreeUrl,{},function(data){
   	        	$.each(data,function(i,t){
   	        		ul.append("<li><a href=\"javascript:void(0)\" onClick='top.$b.App.openUrl(\""+t.url+"\",\""+t.text+"\",{targetPage : \""+t.targetPage+"\",path : \""+t.path+"\",menuId:\""+t.code+"\"});top.framePlus.pointMenu.close();'><i class='"+t.icon+"'></i>"+t.text+"</a></li>");
   	        	});
   			});
   	 	},
   		bindEvent : function(){
   			
   			var data=$b.App.getComboDatas("USER_LOCALE");
			$("#language").combobox({
   					data:data.USER_LOCALE.data,
   					required:true,
   					valueField: 'id',
   					textField: 'text'
   			});
			$('#language').combobox('select', $b.context.locale);
			$.each(data.USER_LOCALE.data,function(i,s){
				if(s.id==$b.context.locale)
				{
					$("#lang-text").html(s.text.substr(0,2));
				}
			});
			
 			$(btnLang).on('click',function(){
   				var dialogLang="#dialog-lang";
   				$(dialogLang).dialog({    
				    title: $b.Base.i18n("title.lang"),
				    minimizable: false,
				    maximizable: false,
				    resizable: false,
				    modal: true,
				    striped: true, 
				    height:180,
				    width:350, 
				    buttons : [ {
						text : $b.Base.i18n("button.submit"),
						size : 'large',
						handler : function() {
							if(!$("#langForm").form('validate')){return;}
							var options = {"locale":$("#language").combobox("getValue")};
							 $b.Submit.ajaxSubmit(converLangUrl,options,function(data){
								$(dialogLang).dialog("close");
								$b.Base.refresh();
							 });
						}
					} ]
				}); 
   			});
			
   			$(btnExit).on('click',function(){
   				$b.Msg.confirm($b.Base.i18n("operate.exitSytem"), function() {
   					$(window).unbind("beforeunload");
   					if( parent ){
   						parent.location.href = ctxPath+'logout';
					}else{
						location.href = ctxPath+'logout';
					}
   				});
   			});
  
   			$(".user-info-div").find(".icon-user").on('click' ,function(){
   				var main_layout = $(mainLayout);
   				var mini_menu_bar = $(".mini-menu-bar");
   				var left_tree = $(leftMenuTree);
   				
   				if (main_layout.hasClass("open_mini_menu_bar")) {
   					main_layout.removeClass("open_mini_menu_bar");
   					left_tree.parent().show();
   					$(".tabs-leftline").css("left", "195px");
   					//打开菜单树
   					left_tree.show();
   					//隐藏迷你小菜单
   					mini_menu_bar.hide();
   					main_layout.layout('panel' , 'west').panel('resize',{width : 196});
   				} else {
   					main_layout.addClass("open_mini_menu_bar");
   					main_layout.layout('panel' , 'west').panel('resize',{width : 58});
   					//隐藏菜单树
   					left_tree.parent().hide();
   					left_tree.hide();
   					$(".tabs-leftline").css("left", "55px");
   					//打开迷你小菜单
   					mini_menu_bar.show();
   				}
   				main_layout.layout('resize');
   			});
   			
   			$(window).bind('beforeunload',function(){
   				return $b.Base.i18n("logout.beforeunloadtitle");
   			});
   			
   			$(window).bind('unload',function(){
   				$b.Submit.ajaxSubmit($b.context.root+'/logoutAjax',{},function(response){});
   			});
   		}
   	};
});