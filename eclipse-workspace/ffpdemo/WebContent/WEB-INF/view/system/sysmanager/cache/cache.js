/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 缓存<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-11-18<br>
 */
require(['beneform4j-page', 'datagrid-detailview'], function(Page) {
	Page.create(function($){
		var me = this,
			grid = $('#dataList'),
			apis = {
				list  : 'list',
				remove : 'remove',
				clear : 'clear',
				clearAll : 'clearAll'
			};
		
		return {
			init :  function(){
				grid.datagrid({
					url : apis.list,
					pagination: false,
					rownumbers: true,
					remoteSort:false,
					singleSelect:true,
					nowrap:false,
					columns : [ [ 
						{field : 'cacheName', title : '缓存名称', align:'left', width : 200, sortable : true},
			            {field : 'cacheType', title : '缓存类型', align:'left'},
						{field : 'dynamic', title : '是否动态', align:'center'},
						{field : 'capacity', title : '缓存容量', align:'right', sortable : true},
						{field : 'size', title : '已缓存数', align:'right', sortable : true},
						{field : 'visitNum', title : '访问次数', align:'right', sortable : true},
						{field : 'hitNum', title : '命中次数', align:'right', sortable : true},
						{field : 'nativeProperties', title : '特征属性', align:'left', 
							formatter : function(value,row,index){
								return JSON.stringify(value);
							}
						},
						{field : 'des', title : '描述', align:'left'}
			        ] ],
			        rowToolButtons:[ 
			        	{text : '清空缓存', iconCls : 'bf4j-icon-row-delete', handler : me.doClear}
					],
					view : detailview,
					detailFormatter: me.detailRender,
					onExpandRow: function(){
						if(!me.bindExpandRowEvent){
							me.bindExpandRowEvent = true;
							grid.datagrid('getPanel').find('span.treeCheckAll').each(function(i, c){
				        		var $all = $(this),
				        			$table = $all.parentsUntil('table'),
				        			$items = $table.find('span[cache-key]'),
				        			$btn = $table.find('i[cache-name]'); 
				        		me.setThirdCheckboxList($all, $items);
				        		$btn.click(function(){
				        			var params = {
					        				cacheName : $(this).attr('cache-name'),
					        				keys      : []
					        			}, 
				        				keys = params.keys;
				        			
				        			$.each($items, function(i, n){
				        				if($(n).hasClass('tree-checkbox1')){
				        					keys.push($(n).attr('cache-key'));
				        				}
									});
				        			if(keys.length > 0){
				        				$b.Msg.confirm('您确定要移除所选的<font color="red">'+keys.length+'</font>条缓存条目吗？', function(){
				        					$b.Submit.ajaxSubmit(apis.remove, params, me.doQuery);
				        				});
				        			}else{
				        				$b.Msg.warning("请勾选需要移除的缓存条目！");
				        			}
				        		});
				        	});
						}
					}
				});
				
				//绑定事件
			    $("a.bf4j-btn-2").filter(".doQuery").on("click", me.doQuery)
			       .end().filter(".doClearAll").on("click", me.doClearAll);
			    $b.Msg.closeProgress();
			},
			/**
			 * 执行查询
			 */
			doQuery : function (){
				grid.datagrid('load', {});
			},
			
			/**
			 * 清空所有缓存
			 */
			doClearAll : function(){
				$b.Msg.confirm('您确定要清除所有缓存吗？', function(){
					$b.Submit.ajaxSubmit(apis.clearAll, {}, me.doQuery);
				});
			},
			/**
			 * 清空单个缓存
			 */
			doClear : function(index, data){
				$b.Msg.confirm('您确定要清除缓存'+data.cacheName+'吗？', function(){
					$b.Submit.ajaxSubmit(apis.clear, {cacheName : data.cacheName}, me.doQuery);
				});
			},
			/**
			 * 渲染缓存条目
			 */
			detailRender : function(index, data){
				var keys = data.keys,
					html,
					i = 0,
					size = 5,//每行显示多少条
					length,
					colspan = 1;
				if(!keys || keys.length == 0){
					return '';
				}
				html = '<table>'
					 + '<tr><td colspan="'+size+'"> 缓存名称：'+data.cacheName + '</td></tr>'
				     + '<tr><td colspan="'+size+'">'
				     + '<span class="treeCheckAll tree-checkbox tree-checkbox0"> </span> 全选'
				     + '&nbsp;<a class="bf4j-btn-2" title="移除所选条目"><i cache-name="'+data.cacheName+'" class="bf4j-icon-btn-delete"></i></a>'
				     + '</td></tr>';
				
				for(length = keys.length; i < length; i++){
					if(i % size == 0){
						html += '<tr>';
					}
					if(i == length - 1){//计算最后一项的跨列数
						colspan = size - length % size + 1;
					}
					html += '<td colspan="'+colspan+'"><span cache-key="'+keys[i]+'" class="tree-checkbox tree-checkbox0"> </span> ('+ (i+1)+')'+ keys[i]+'</td>';
					if((i+1) % size == 0 || i == length - 1){
						html += '</tr>';
					}
				}
				html += '</table>';
				return html;
			},
			/**
			 * 设置缓存条目中的“全选”和其它复选框
			 */
			setThirdCheckboxList : function($all, $items){
				$items.on('click', function() {
				    var checked = 0, cls;
				    me.changeCheckboxCls(this);
				    $.each($items, function(){
						if($(this).hasClass('tree-checkbox1')){
							checked++;
						}
					});
				    cls = (checked === 0) ? '0' : (checked === $items.length ? '1' : '2');
				    me.addCheckboxCls($all, 'tree-checkbox'+cls);
				});
        		$all.on('click', function(){
					var cls;
					me.changeCheckboxCls(this);
					cls = $all.hasClass('tree-checkbox1') ? '1' : '0';
					$.each($items, function(){
						me.addCheckboxCls(this, 'tree-checkbox'+cls);
					});
				});
			},
			/**
			 * 改变复选框样式
			 */
			changeCheckboxCls : function(obj){
				me.addCheckboxCls(obj, $(obj).hasClass('tree-checkbox1') ? 'tree-checkbox0' : 'tree-checkbox1');
			},
			/**
			 * 添加复选框样式
			 */
			addCheckboxCls : function(obj, cls) {
			    $(obj).removeClass('tree-checkbox0 tree-checkbox1 tree-checkbox2').addClass(cls);
			}
		};
	});
});