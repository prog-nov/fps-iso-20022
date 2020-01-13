/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 附件管理<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-18<br>
 */
require(['beneform4j-page','locale','multiuploader-common'], function(Page,locale,$multiuploader) {
	Page.create(function($){
		var me = this,
			grid = $('#dataList'),
			dialog = $('#dialog'),
			apis = {
				list : 'list',
				add  : 'insert',
				save   : 'update',
				remove : 'delete',
				lock : 'lock'
			},isSelectAddFile = false,isSelectUpdateFile = false;
		return {
			init :  function(){
				grid.datagrid({
					url : apis.list,
					idField : 'docId',
					pagination : true,
					rowTool: false,
					rownumbers:false ,
					scrollbarSize:0,
					frozenColumns : [ [ 
	    			   	{field : 'docId', checkbox : true}, 
	    			   	{field : 'docName', title : locale.system.document.title.docName, align:'left', width : 260, sortable : true}
	    			] ],
					columns : [ [ 
						{field : 'suffix', title : locale.system.document.title.suffix, align:'left'}, 
						{field : 'checkSumType', title : locale.system.document.title.checkSumType, align:'center'},
						{field : 'docSize', title : locale.system.document.title.docSize, align:'center',formatter:$b.App.formatter.size()},
						{field : 'docTypeName', title : locale.system.document.title.docType, align:'center'},
						{field : 'docStateName', title : locale.system.document.title.docState, align:'center'},
						{field : 'instOper', title : locale.system.document.title.instOper , align:'center'},
						{field : 'instDate', title : locale.system.document.title.instDate , align:'center'},
						{field : 'docState', hidden : true},
						{field : 'checkSum', hidden : true},
						{field : 'instTime', hidden : true},
						{field : 'storePath', hidden : true},
						{field : 'modiDate', hidden : true},
						{field : 'modiTime', hidden : true},
						{field : 'modiOper', hidden : true}
			        ] ]
				});
				
				me.setComboFields($('#searchForm'));
				
				//绑定事件
				$(".SearchBtn").on("click", me.doQuery);
			    $("a.bf4j-btn-2").filter(".gotoAdd").on("click", me.gotoAdd)//新增文档
			    .end().filter(".gotoUpdate").on("click", function(){//修改文档
				   me.selectOne(grid, me.gotoUpdate);
			    })
			    .end().filter(".doDelete").on("click", function(){//删除文档
				   me.selectRows(grid, me.doDelete);
			    })
			    .end().filter(".gotoDownload").on("click", function(){//下载文档
				    var data = me.selectOne(grid);
				    if(data){
				    	if(data.docState == '0'){
				    		$b.Msg.error(locale.system.document.content.undownload);
				    		return false ;
				    	}
				    	$b.File.download("docIds=" + data.docId);
				    }
			    })
			    .end().filter(".gotoStart").on("click", function(){//解锁
			    	var data = me.selectOne(grid);
				    if(data){
				    	if(data.docState == '1'){
				    		$b.Msg.error(locale.system.document.content.notNeedUnLock);
				    		return false ;
				    	}
				    	me.doLock(data, "1");
				    }
			    })
			    .end().filter(".gotoStop").on("click", function(){//锁定
			    	var data = me.selectOne(grid);
				    if(data){
				    	if(data.docState == '0'){
				    		$b.Msg.error(locale.system.document.content.notNeedLock);
				    		return false ;
				    	}
				    	me.doLock(data, "0");
				    }
			    });
			    $b.Msg.closeProgress();
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
			doQuery : function (){
				grid.datagrid('load', $.serializeObject($('#searchForm')));
			},
			/**
			 * 进入新增
			 */
			gotoAdd : function (){
				dialog.dialog({    
				    title: locale.system.document.content.addTitle,    
				    height:300,
				    width:600,
				    href : 'add.page',
				    onLoad : function(){
				    	me.setComboFields($('#addForm'));
				    	$multiuploader.create('#pick',{'fileList':'#fileList','isInitList':false});
				    	$("#upload").on("click",function(){
				    		$multiuploader.upload('#pick');
				    	});
				    },
				    buttons : [ {
						text : locale.button.submit, size : 'large', handler : me.doAdd
					}, {
						text : locale.button.cancel, size : 'large', handler : me.closeDialog
					} ]
				}); 
			},
			
			doAdd : function (){
				var form = $('#addForm');
				if(form.form('validate')){
					if($("input[name='fileKeys']").length <= 0 ){
						$b.Msg.error($b.Base.i18n('validate.file'));
			    		return false;
					}
					if($("input[name='fileKeys']").length != 1 ){
						$b.Msg.error($b.Base.i18n('validate.onefile'));
			    		return false;
					}
					var params = {url : apis.add};
					$b.Submit.ajaxSubmitForm(form, params, me.addCallback);
				}
			},
			
			addCallback : function (rs, params){
				$b.Msg.alert(locale.operate.doOk);
				grid.datagrid('reload');
				me.closeDialog();
			},
			
			gotoUpdate : function (data){
				dialog.dialog({    
				    title: locale.system.document.content.editTitle,    
				    height:300,
				    width:500,
				    href : 'edit.page',
				    onLoad : function(){
				    	me.setComboFields($('#updateForm'));
				    	$multiuploader.create('#pick',{'fileList':'#fileList','isInitList':false});
				    	$("#upload").on("click",function(){
				    		$multiuploader.upload('#pick');
				    	});
				    	$('#updateForm').form('load', data);
				    },
				    buttons : [ {
						text : locale.button.edit, size : 'large', handler : me.doUpdate
					}, {
						text : locale.button.cancel, size : 'large', handler : me.closeDialog
					} ]
				});
			},
			
			doUpdate : function (){
				var form = $('#updateForm');
				if(form.form('validate')){
					if($("input[name='fileKeys']").length > 1 ){
						$b.Msg.error($b.Base.i18n('validate.onefile'));
			    		return false;
					}
					var params = {url : apis.save};
					$b.Submit.ajaxSubmitForm(form, params, me.addCallback);
				}
			},
			
			updateCallback : function (){
				$b.Msg.alert(locale.operate.doOk);
				grid.datagrid('reload');
				me.closeDialog();
			},
			
			doDelete : function (rows){
				$b.Msg.confirm($b.Base.i18n('operate.remove',rows.length), function(){
					var docIds = [];
					$.each(rows, function(i, n){
						docIds.push(n.docId);
					});
					$b.Submit.ajaxSubmit(apis.remove, {'docIds' : docIds}, me.deleteCallback);
				});
			},
			
			deleteCallback : function (rs){
				$b.Msg.alert($b.Base.i18n('operate.msginfo',rs));
				me.doQuery();
			},
			/**
			 * isTrue 0 启用，1 锁定
			 */
			doLock : function(data, isTrue){
				var params = {docId : data.docId};
				params.docState = isTrue;
				$b.Submit.ajaxSubmit(apis.lock, params, function(rs){
					grid.datagrid('reload');
					$b.Msg.alert(locale.operate.doOk);
				});
			}
		};
	});
});