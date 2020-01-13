/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 参数定义<br>
 * Author : LiYun <br>
 * Version : 1.1.0 <br>
 * Since : 1.1.0 <br>
 * Date : 2017-3-23<br>
 */
require(['beneform4j-page'], function(Page) {
	Page.create(function($){
		    var me = this,
		    form = $('#searchForm'), //查找参数项表单
			grid = $('#dataList'),   //参数表
			dialog = $('#dialog'), 
			updateData,//修改参数模块加载的初始数据
			apis = {
				list : 'list',
				add : 'add',
				save : 'edit',
				remove : 'delete'
			},
			editableRender = function(v){
				return v === '1' ? '是' : '否';
			};
		
            return {
	
                // 参数列表初始化          
				init :  function(){
					me.setComboFields(form);//设置查找项的参数组别下拉框
				    grid.datagrid({     //使用EasyUI的数据表格，以表格形式展示数据
					   url : apis.list,//链接列举页面
					   idField : 'paramCode',
					   pagination : true,//显示分页工具栏
					   rowTool: false,
				       queryParams: $.serializeObject(form),//请求远程数据
					   frozenColumns : [ [ //将该列冻结在左侧
	    			   	{checkbox : true}, 
	    			   	{field : 'paramName', title : '参数名称', align:'left'}
	    			   ] ],
					   columns : [ [ 
			            {field : 'paramCode', title : '参数代码', align:'left'},
			            {field : 'paramGroupName', title : '参数组别', align:'left', width : 100, sortable : true},
						{field : 'storeTypeName', title : '参数存储类型', align:'left'},
						{field : 'editable', title : '可否编辑', align:'left', formatter: editableRender},
						{field : 'des', title : '描述', align:'left'}
			           ] ]
				    });
				
				      //绑定事件
				    $(".doQuery").on("click", me.doQuery);//点击查询事件
				    $("a.bf4j-btn-2").filter(".gotoAdd").on("click", me.gotoAdd)//点击添加参数事件
			        .end().filter(".gotoUpdate").on("click", function(){//点击修改参数事件
				        me.selectOne(grid, me.gotoUpdate);})
			        .end().filter(".doDelete").on("click", function(){//点击删除参数事件
				        me.selectRows(grid, me.doDelete);});
			        $b.Msg.closeProgress();
			    },
			
			    /*关闭对话框 */
			    closeDialog: function(){
				    dialog.dialog('close');
			    },
			
			    /*1执行查询*/
			    doQuery : function (){
				    grid.datagrid('load', $.serializeObject(form));
			    },
			
			    /*2添加参数*/
			    gotoAdd : function (){
                    dialog.dialog({    
				        title: '添加参数定义',    
				        height:530,
				        width:380,
				        href : 'add.page',//链接添加页面
				        onLoad : function(){
				    	  me.setComboFields($('#addForm'));
				        },
				        buttons : [ {
						   text : '提交', size : 'large', handler : me.doAdd//执行添加
					       }, {
						   text : '取消', size : 'large', handler : me.closeDialog//关闭对话框
					    } ]
				    }); 
			    },
			
			    doAdd : function (){
				    var form = $('#addForm');
				    if(!form.form('validate')){
					   return false;
				    }
				
				    var params = {url : apis.add};
				    //该函数包含三个参数：提交的表单数据、发送请求的URL、提交后执行的函数
				    $b.Submit.ajaxSubmitForm(form, params, me.addCallback);
			    },
			
			    addCallback : function (rs, params){
				    $b.Msg.alert('成功添加参数 ');
				    grid.datagrid('reload');
				    me.closeDialog();
			    },
			
                /*3修改参数*/	
			    gotoUpdate : function (data){
				    dialog.dialog({    
				        title: '修改参数定义',    
				        height:530,
				        width:380,
				        href : 'edit.page',//链接修改页面
				        onLoad : function(){
				    	    var $paramGroup = $('.showParamGroup');
				    	    $('#updateForm').form('load', data);//载入所选择的待修改参数的数据
                            me.setComboFields($('#updateForm'));//设置下拉框并加载数据
                            updateData= $('#updateForm').serialize();
				    	    $paramGroup.combotree({
				    		    onShowPanel :function(){    //点击下拉按钮则加载触发事件
				    			    if(!$paramGroup.data('initialize')){//initialize用来判断是否是第一次点击下拉按钮
				    				   $paramGroup.data('initialize', 'done');
				    				   var tree = $paramGroup.combotree('tree'),//获取树形对象
				    				   node = tree.tree('find', data.paramGroup);//获取所选择的节点
				    				   tree.tree('expandTo', node.target);//打开从根节点到所选择的节点之间的所有节点
				    			    }
				    		    }
				    	    });
				    	
				        },
				        buttons : [ {
						   text : '保存', size : 'large', handler : me.doUpdate//执行更新
					       }, {
						   text : '取消', size : 'large', handler : me.closeDialog
					    } ]
				    });
			    },
			
			    doUpdate : function (){
				    var form = $('#updateForm'),
				    updataForm= $('#updateForm').serialize();
				    if(updataForm!=updateData){//判断修改参数表单的值是否修改
					    if(!form.form('validate')){//判断所有字段是否有效
					       return false;
					    }
			            var params = {url : apis.save};
					    $b.Submit.ajaxSubmitForm(form, params, me.updateCallback);  //判断表单数据是否做了修改
						
				    }
				    else{
					    $b.Msg.alert('您未做任何修改');
				        me.closeDialog();
					
			        }
		        },
			
			    updateCallback : function (){
				    $b.Msg.alert('修改参数成功 ');
				    grid.datagrid('reload');//更新参数列表显示的数据
				    me.closeDialog();
			    },
			
	
			    /*4删除参数*/			
			    doDelete : function (rows){
				    $b.Msg.confirm('您确定要删除选择的'+rows.length+'个参数项吗？', function(){
					   var paramCodes = [];
					   $.each(rows, function(i, n){
						   paramCodes.push(n.paramCode);
					   });
					   $b.Submit.ajaxSubmit(apis.remove, {'paramCode' : paramCodes}, me.deleteCallback);
				    });
			    },	
				
			    deleteCallback : function (rs){
				    $b.Msg.alert('操作成功，成功删除 ' + -rs/2 + '个参数');
				    me.doQuery();
			    }
			
		    };
	});
});


