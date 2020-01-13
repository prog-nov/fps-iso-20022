/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 快捷菜单管理<br>
 * Author : Zhangjj <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-8-23<br>
 */
require(['beneform4j-treepage','mainframe'], function(Page,mainFrame) {
	Page.create(function($){
		var me = this,
			tree = $('#tree'),
			grid = $('#dataList'),
			dialog = $('#dialog'),
			popupMenuAdd = $('#popupMenuAdd'),
			popupMenuEdit = $('#popupMenuEdit'),
			apis = {
				list : 'list',
				add  : 'insert',
				save   : 'update',
				remove : 'delete',
				sort : 'saveSort',
				listMenuTree : 'listMenuTree'
			};
		
		return {
			init :  function(){
				// 搜索功能
				$('#searchArea').searchbox({ 
					searcher : me.doQuery, 
					menu : '#displayType', 
					prompt : ' 请输入菜单名称' 
				}); 
				// 加载异步菜单树
				me.loadMenuTree(apis.listMenuTree);
				// 加载预览列表
				me.loadPreviewList();
				// 关闭进度条
			    $b.Msg.closeProgress();
			    //绑定事件
				$("a.saveSort").on("click", function(){
					me.doSort();
				});
				// 菜单树右键菜单
				popupMenuAdd.find(".gotoAdd").on("click", function(){
					me.gotoAdd(popupMenuAdd.node);
				});
				// 快捷菜单预览右键菜单
				popupMenuEdit.find(".gotoUpdate").on("click", function(){
					me.gotoUpdate(popupMenuEdit.node);
				}) .end().find(".doDelete").on("click", function(){
					me.doDelete(popupMenuEdit.node);
				});
			},
			/**
			 * 构建异步加载树
			 * @param searUrl 请求数据URL
			 * @returns
			 */
			loadMenuTree : function(searUrl){
				tree.tree({
					url : searUrl,
					animate: true,
					lines : true,
					dnd : true,
					//初始化状态
					loadFilter: function(data){
						if($.isArray(data)){
							$.each(data, function(i, node){
								node.state = node.count >= 1? 'closed':'open';
								node.attributes = node;
//								node.text = node.attributes.canAdd == 0? node.text:node.text+"*";
							});
						}
						return data;
					},
					onBeforeLoad : function(node, param) {
						if(node && node.id){
							param.menuId = node.id;
						}
					},
					//单击事件
					onClick :function(node){
						 me.getPreviewIcon(node.id);
					},
					//右键事件
					onContextMenu: function(e,node){
						me.onContextMenuFn(e, node, popupMenuAdd);
					}
				});
			},
			/**
			 * 关闭对话框
			 */
			closeDialog : function(){
				dialog.dialog('close');
			},
			/**
			 * 执行查询
			 */
			doQuery : function(menuName,displayType){ 
				 var searUrl = encodeURI(apis.listMenuTree + '?menuName=' + menuName+"&displayType="+displayType);
				 searUrl = encodeURI(searUrl);
			 	 // 刷新异步菜单树
				 me.loadMenuTree(searUrl);
			 },
			/**
			 * 进入新增
			 */
			gotoAdd : function (node){
				// 判断菜单是否可以收藏
				if(node.attributes.canAdd == 0)
				{
					$b.Msg.error("该菜单已添加或不支持该操作 请检查");
					return;
				}
				// 进入新增页面
				dialog.dialog({    
				    title: '新增快捷菜单',    
				    height:400,
				    width:800,
				    href : 'add.page',
				    onLoad : function(){
				    	$('#addForm').find("input[name='menuId']").val(node.id);
				    	$('#addForm').find("input[name='shortMenuName']").val(node.text);
				    	$('#addForm').find("input[name='displayIcon']").val("bf4j-icon-mid-09");
						//加载悬浮菜单图标合集
				    	var PointIconDiv=$("#PointIcon").find(".bf4j-group-content");
						me.loadIconList(PointIconDiv,"bf4j-icon-mid-",22);
				    },
				    buttons : [ {
						text : '保存', size : 'large', handler : me.doAdd
					}, {
						text : '取消', size : 'large', handler : me.closeDialog
					} ]
				}); 
			},
			/**
			 * 执行新增
			 * @returns
			 */
			doAdd : function (){
				var form = $('#addForm');
				if(!form.form('validate')){
					return false;
				}
				
				var params = {url : apis.add};
				$b.Submit.ajaxSubmitForm(form, params, me.addCallback);
			},
			/**
			 * 新增成功回调函数
			 * @param rs
			 * @param params
			 * @returns
			 */
			addCallback : function (rs, params){
				$b.Msg.alert('操作成功 ');
				// 刷新菜单树
				tree.tree('reload');
				// 刷新快捷菜单
				mainFrame.refreshPointPanel();
				// 刷新预览列表
				me.loadPreviewList();
				me.closeDialog();
			},
			/**
			 * 进入修改
			 * @param data
			 * @returns
			 */
			gotoUpdate : function (data){
				dialog.dialog({    
				    title: '修改快捷菜单',    
				    height:400,
				    width:800,
				    href : 'edit.page',
				    onLoad : function(){
				    	$('#updateForm').form('load', data);
						//加载悬浮菜单图标合集
				    	var PointIconDiv=$("#PointIcon").find(".bf4j-group-content");
						me.loadIconList(PointIconDiv,"bf4j-icon-mid-",22);
				    },
				    buttons : [ {
						text : '保存', size : 'large', handler : me.doUpdate
					}, {
						text : '取消', size : 'large', handler : me.closeDialog
					} ]
				});
			},
			/**
			 * 执行修改
			 * @returns
			 */
			doUpdate : function (){
				var form = $('#updateForm');
				if(!form.form('validate')){
					return false;
				}
				var params = {url : apis.save};
				$b.Submit.ajaxSubmitForm(form, params, me.updateCallback);
			},
			/**
			 * 修改成功回调函数
			 * @returns
			 */
			updateCallback : function (){
				$b.Msg.alert('操作成功 ');
				grid.datagrid('reload');
				me.closeDialog();
				// 刷新快捷菜单
				mainFrame.refreshPointPanel();
				// 刷新预览列表
				me.loadPreviewList();
			},
			/**
			 * 执行删除
			 * @param data
			 * @returns
			 */
			doDelete : function (data){
				$b.Msg.confirm('您确定要删除['+data.shortMenuName+']这个快捷菜单吗？', function(){
					var keyIds = [];
					keyIds.push(data.keyId);
					$b.Submit.ajaxSubmit(apis.remove, {'keyId' : keyIds}, me.deleteCallback);
				});
			},
			/**
			 * 删除成功回调函数
			 * @param rs
			 * @returns
			 */
			deleteCallback : function (rs){
				$b.Msg.alert('操作成功');
				// 刷新快捷菜单
				mainFrame.refreshPointPanel();
				// 刷新预览列表
				me.loadPreviewList();
				// 刷新菜单树
				tree.tree('reload');
			},
			/**
			 * 保存排序
			 * @returns
			 */
			doSort : function(){
				$b.Msg.confirm('确认保存当前快捷菜单的顺序？', function(){
					var divs = $("div[previewMenuId]");
					var keyIds = [];
					$.each(divs,function(i,n){
						keyIds.push($(n).attr("keyId"));
					});
					$b.Submit.ajaxSubmit(apis.sort, {'keyIds' : keyIds}, me.sortCallback);
				});
			},
			/**
			 * 保存排序成功回调函数
			 * @param rs
			 * @returns
			 */
			sortCallback : function(rs){
				$b.Msg.alert('操作成功');
				// 刷新快捷菜单
				mainFrame.refreshPointPanel();
				// 刷新预览列表
				me.loadPreviewList();
				// 屏蔽保存按钮
				$("#iconSort").hide();
			},

			/**
			 * 在预览列表中选中对应的快捷菜单图标
			 * @param node
			 * @returns
			 */
			getPreviewIcon : function(id){
				$("div[previewMenuId]").attr("style","background:#FFFFFF");
				$("div[previewMenuId="+id+"]").attr("style","background:#F5F5F5");
			},
			/**
			 * 加载图标列表(新增，修改用)
			 * @param warp
			 * @param className
			 * @param object
			 * @param typeClass
			 * @returns
			 */
			loadIconList :  function(warp,className,object,typeClass){
				if(!typeClass){typeClass="";}
				var addIconToList=function(cName)
				{
					var IconHtml=
						"<div class='icon-node' title='"+cName+"' >"+
						"<a class='"+typeClass+"' ><i class='"+cName+"' ></i></a>"+
						"<input type='text' readonly='readonly' style='width:80%'   value='"+cName+"' />"+
						"</div>";
						$node=$(IconHtml);
						warp.append($node);
						$node.on("click",function(){
							$(this).find("input").select();
							$("input[name='displayIcon']").val(cName);
							$(this).siblings().attr("style","background:#FFFFFF");
							$(this).attr("style","background:#F5F5F5");
							
						});
						$node.tooltip();
						// 选择默认值
						if($("input[name='displayIcon']").val()==cName){
							$node.find("input").select();
							$node.attr("style","background:#F5F5F5");
						}	
				};
				
				warp.empty();
				if( typeof(object) =="number"){
					for(var i=1;i<=object;i++)
					{
						var classNum= i<=9?"0"+i:i;
						var newClassName=className+classNum;
						addIconToList(newClassName);
					}
				}else{
					$.each(object,function(i,s){
						addIconToList(s);
					});
				}
			},
			/**
			 * 加载预览列表
			 */
			loadPreviewList :  function(){
				var warp = $("#PointIconPreview");
				var addIconToList=function(data)
				{
					var IconHtml=
						"<div class='icon-node' title='"+data.shortMenuName+"' previewMenuId='"+data.menuId+"' keyId='"+data.keyId+"'>"+
						"<div class='line'>"+
						"<a class='' ><i class='"+data.displayIcon+"' ></i></a>"+
						"</div><div class='line' style='height:20px;overflow:hidden;'>"+
						"<span style='width:90%;'>"+data.shortMenuName+"</span>"+
						"</div></div>";
						$node=$(IconHtml);
						warp.append($node);
						$node.bind('contextmenu',function(e){
							e.preventDefault();
							me.getPreviewIcon(data.menuId);
							popupMenuEdit.node = data;
							popupMenuEdit.menu('show',{left: e.pageX, top: e.pageY});
						});
						$node.click(function(){
							me.getPreviewIcon(data.menuId);
						});
						$node.dblclick(function(){
							me.gotoUpdate(data);
						});
						// 图标拖动排序功能
						$node.draggable({  
							proxy : 'clone',
							revert: false,
							onBeforeDrag : function(e){
								me.getPreviewIcon(data.menuId);
								if(e.button==2)
								return false;
							},
							onStopDrag : function(e){
								// 去除图标拖动时新增的样式
								$(e.data.target).attr("style","");
							},
							onDrag : function(e){
								var d = e.data;
								// 限制图标移动的范围
								if(d.left<0)d.left=0;
								if(d.left>($(d.parent).width()-$(d.target).outerWidth()))d.left=$(d.parent).width()-$(d.target).outerWidth();
								if(d.top<30)d.top=30;
								if(d.top>($("#favPreviewDiv").height())-$(d.target).outerHeight())d.top=$("#favPreviewDiv").height()-$(d.target).outerHeight();
								
								// 计算拖动坐标，进行位置的判断处理
								var icons = $(e.data.target).siblings();
								var x = e.data.left;
								var y = e.data.top;
								var iconH = $(e.data.target).outerHeight();
								var iconW = $(e.data.target).outerWidth();
								var h = 0;
								var w = 0;
								$.each(icons,function(i, n){
									w = n.offsetLeft;
									h = n.offsetTop;
									if(x > w && x < (w + iconW) && y > h && y < (h+iconH)){
										$(e.data.target).insertAfter(n);
										$("#iconSort").show();
										return false;
									}
									// 移动到X轴为0的情况(即最左边)则判断Y轴落到哪个图标上
									if((x == 0 && y == 30)||(x == 0 && y > h && y < (h+iconH))){
										$(e.data.target).insertBefore(n);
										$("#iconSort").show();
										return false;
									}
									// 移动到Y轴为30的情况（即最顶部）则判断X轴落到哪个图标上
									if(y == 30 && x > w && x < (w+iconW)){
										$(e.data.target).insertAfter(n);
										$("#iconSort").show();
										return false;
									}
									// 放置到空白处的处理
									if(i+1 == icons.length && (y > (h+iconH) || (y > h && x > (w+iconW)))){
										$(e.data.target).insertAfter(n);
										$("#iconSort").show();
										return false;
									}
								});
							}
						}); 
				};
				// 清空列表
				warp.empty();
				// 加载预览图标列表
				$b.Submit.ajaxSubmit(apis.list, {}, function(data){
					$.each(data,function(i,s){
						addIconToList(s);
					});
				});
			}
		};
	});
});