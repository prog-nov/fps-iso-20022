/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 菜单国际化维护<br>
 * Author : XGP <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-11-07<br> 
 */
require(['beneform4j-treepage','locale'], function(Page,locale) {

	Page.create(function($){
		
		
		var me = this,
			editChange = false,
			tree = $('#tree'),
			//保存需要处理的数据
			changesObj = {},
			apis = {
				list : 'list',
				save : 'save'
 			};	 
			//保存已经在编辑的行
			changesObj.bengingIdArr = [];
			//保存修改过的行ID,用于提交数据时处理
			changesObj.changesIdArr = [];
			
		// 从服务器获取USER_LOCALE类型的枚举参数
		me.setComboDatas('USER_LOCALE');
		
		return {
			init :  function(){	
				var i,
					len,
					column,
					menuLocales,
					columns;
					columns = [ {title:locale.system.sysmanager.menulocale.locale.menu,field:'menuName',width:10,
						formatter: function(v,row,index){

							  if (!row.leaf && row.count > 0){

								  v = v + '<font color="red">(' + row.count + ')</font>';
							  };
							  return '<span tree-node-id="'+ row.menuId +'" depth="'+row.depth+'" class="tree-checkbox tree-checkbox0"> </span> '+v;
						  }}];	
				//获取的语言种类数据
				if(me.comboDatas.USER_LOCALE){
					menuLocales = me.comboDatas.USER_LOCALE.data;
					changesObj.menuLocales = menuLocales;
				}else{
					return false;
				};				
				//循环生产动态列数据
				for(i=0,len=menuLocales.length; i<len; i++){
				 	column = {
				 		title : menuLocales[i].text,
						field : menuLocales[i].id,
						width : 10,
						editor:'text'
					};
					columns.push(column);
				};
				/**
				 * 生成菜单树表格
				 */
				tree.treegrid({
		            url : apis.list,
		            idField:'menuId',
					treeField: 'menuName',
					animate: true,
					fitColumns:true,
					loadFilter: function(data){
						if($.isArray(data)){
							$.each(data, function(i, node){
								var j = 0,
									len,
									menus,
									menu,
									nodeId;
								nodeId = node.menuId;
								//记录下初始化的每一行数据
								changesObj[nodeId] = node;
								menus = node.localeMenus;
								len = menus.length;
								node.state = node.count >= 1? 'closed':'open';								
								for( ; j < len; j++){
									menu = menus[j];
									node[menu.locale] = menu.menuName;
								};							
							});
						};
						return data;
					},	
					columns:[columns],
					onBeforeLoad : function(node, param) {
						if(node && node.menuId){
							param.menuId = node.menuId;
						};
					},
					/**
					 * 双击表格单元格进入编辑
					 */
					onDblClickCell : function(index,data,value){
						me.beginEdit(data.menuId);
						var ed = $(this).datagrid('getEditor', {index:data.menuId,field:index});
						if(ed){
							$(ed.target).focus();	
						};
					},
					/**
					 * 结束编辑时,提交数据
					 */
					onAfterEdit : function(rowData){
						var index,
							bengingArr,
							len,
							params ={};
						index = rowData.menuId;
						bengingArr = changesObj.bengingIdArr;
						len = bengingArr.length - 1;
						if(index === bengingArr[len]){
							if(editChange){
								$b.Msg.confirm(locale.system.sysmanager.menulocale.locale.alertOut, function(){
									me.collectParams(params);
									$b.Submit.ajaxSubmit(apis.save, params , me.saveCallback);
					   			},function(){
					   				me.doCancel();
					   			});
							};
						};
					},
					/**
					 * 点击菜单树列，当前行标记为选中状态
					 * @param rowIndex 行id
					 * @param field 点击的列字段
					 */
					onClickCell:function(field , rowData ){
						var index,
							domNode,
							checkBox,
							checkedArr,
							checkId = rowData.menuId;
						if(changesObj.checkedIdArr){
							checkedArr = changesObj.checkedIdArr;
							if(checkedArr.length>0){
								index = checkedArr.indexOf(checkId);
								if(index !== -1){
									checkedArr.splice(index,1);
								};
							};
						}else{
							changesObj.checkedIdArr = [];
						};
						domNode = $('#localeMenus').find("#datagrid-row-r1-2-" + checkId);
						checkBox = $(domNode).find('span[tree-node-id]');
						if(field === "menuName"){
							if(checkBox.hasClass('tree-checkbox1')){
								checkBox.attr('class','tree-checkbox tree-checkbox0');
							}else{
								checkBox.attr('class','tree-checkbox tree-checkbox1');
								if(changesObj.checkedIdArr.indexOf(checkId) === -1){
									changesObj.checkedIdArr.push(checkId);
								};
							};
						};
					}
				});
				$b.Msg.closeProgress();
				//按钮组绑定事件
				$(".Action").find("a.SaveBtn").on("click", me.doSave)
				.end().find('a.EditBtn').on('click',me.doEdit)
				.end().find('a.EditAllBtn').on('click',me.doEditAll)
				.end().find('a.cancelBtn').on('click',me.doCancelEdit);
			},
			/**
			 * 提交数据时点击取消按钮，需要重新回到编辑器
			 */
			doCancel : function(){
				var i = 0,
					len,
					ed,
					changingIndex;
				
				if(changesObj.bengingIdArr){
					changingIndex = changesObj.bengingIdArr[0];
					len = changesObj.bengingIdArr.length;
					for( ; i<len ; i++){
						me.beginEdit( changesObj.bengingIdArr[i]); 
					};
					ed = tree.treegrid('getEditors',changingIndex);
					if(ed.length>0){
						ed[0].target.focus();
					};
				};
			},
			/**
			 * 数据提交成功，并初始化数据。
			 */
			saveCallback : function(){				
				//初始化数据				
				me.initData();
				editChange = false;
				$b.Msg.alert(locale.operate.doOk);
			},
			/**
			 * 初始化对象，初始化数据
			 */
			initData : function(){
				changesObj.bengingIdArr = [];
				changesObj.changesIdArr = [];
				changesObj.checkedIdArr = [];
			},
			/**
			 * 点击保存按钮，提交数据
			 */
			doSave : function(){
				var i = 0,
					bengings,
					changesLen ,
					bengingLen ;
				    bengings = changesObj.bengingIdArr;
					changesLen = changesObj.changesIdArr.length;
					bengingLen = bengings.length;
				if(changesLen > 0){
					if(bengingLen > 0){
						for( ; i<bengingLen ; i++){
							me.endEdits(bengings[i]);
						};
					};
				}else{
					$b.Msg.warning(locale.system.sysmanager.menulocale.locale.alertOk);
				};
			},
			/**
			 * 	点击编辑按钮，编辑选中行
			 */
			doEdit : function(){
				var i = 0,
					len,
					index,
					checkedArr,
					bengingArr;
				if(changesObj.bengingIdArr){
					bengingArr = changesObj.bengingIdArr;
					bengingLen = bengingArr.length;
				}else{
					changesObj.bengingIdArr = [];
				};
				if(changesObj.checkedIdArr){
					checkedArr = changesObj.checkedIdArr;
					if(checkedArr.length > 0 || bengingLen > 0){
						for(len=checkedArr.length ; i<len ; i++){
							index = checkedArr[i];
							if(changesObj.bengingIdArr.indexOf(index) === -1){
								me.beginEdit(index);
							};
						};
					}else{

						$b.Msg.warning(locale.system.sysmanager.menulocale.locale.alertIng);
					};
				}else{

					$b.Msg.warning(locale.system.sysmanager.menulocale.locale.alertIng);

				};
				me.onFirstFoucus();
			},
			/**
			 * 点击批量编辑，则页面中的所有行都能编辑
			 */
			doEditAll : function(){
				var bengingEdit;
				bengingEdit = changesObj.bengingIdArr;
				$.each(changesObj,function(key,value){
					if(value.localeMenus){
						if(bengingEdit.indexOf(value.menuId) === -1){
							me.beginEdit(value.menuId);
						};
					};
				});
				me.onFirstFoucus();
			},
			/**
			 * 点击退出编辑按钮，关闭所有编辑器
			 */ 
			doCancelEdit : function(){
				var i = 0,
					j,
					len,
					len2,
					index,
					bengingEdits,
					$localeMenus,
					$rowItem,
					changesItem,
					localeMenus,
					locale,
					tdText,
					oldName;
				editChange = false;
				bengingEdits = changesObj.bengingIdArr;
				len = changesObj.bengingIdArr.length;
				//是否有正在编辑的行
				if(len > 0){
					$localeMenus = $('#localeMenus');
					for( ; i <len ; i++){
						index = bengingEdits[i];
						me.endEdits(index);
						//当前行数据 tr
						$rowItem = $localeMenus.find("#datagrid-row-r1-2-"+index);
						//清空当前行数据
						$rowItem.find('td').each(function(){
							locale = $(this).attr('field');
							if(locale !== "menuName"){
								$(this).find('div')[0].innerHTML= " ";
							};
						});
						//修改当前行数据
						changesItem = changesObj[index].localeMenus;
						for(j=0, len2 = changesItem.length ; j < len2 ; j++){
							locale = changesItem[j].locale;
							tdText = $rowItem.find(".datagrid-cell-c1-"+locale);
							oldName = changesItem[j].menuName;
							tdText.text(oldName)
						};
	
					};
				}else{
					$b.Msg.warning(locale.system.sysmanager.menulocale.locale.notBeingEdited);
				};
				//初始化数据
				me.initData();
			},
			/**
			 * 结束行编辑
			 * @param index 当前编辑的行ID
			 */
			endEdits : function(index){
				if(index){
					tree.treegrid('endEdit', index);
				};
			},
			onFirstFoucus : function(){
				var firstIndex,
					ed;
				if(changesObj.bengingIdArr){
					firstIndex = changesObj.bengingIdArr[0];
					ed = tree.treegrid('getEditors',firstIndex);
					if(ed.length > 0){
						ed[0].target.focus();
					};
				};
			},
			/**
			 * 开始行编辑
			 * @param index 需要编辑的行Id
			 */
			beginEdit : function(index){
				var i,
					len,
					edits,
					domNode,
					editValue,
					editItem,
					checkBox,
					cellWidth,
					checkedIndex;
				//IE8 不支持indexOf;
				if (!Array.prototype.indexOf){
					  Array.prototype.indexOf = function(elt /*, from*/){
					    var len = this.length >>> 0;

					    var from = Number(arguments[1]) || 0;
					    from = (from < 0)
					         ? Math.ceil(from)
					         : Math.floor(from);
					    if (from < 0)
					      from += len;

					    for (; from < len; from++){
					      if (from in this && this[from] === elt)
					        return from;
					    }
					    return -1;
					  };
					};
				if(changesObj.bengingIdArr.indexOf(index) === -1){
					changesObj.bengingIdArr.push(index);
				};
				
				tree.treegrid('beginEdit',index);
				edits = tree.treegrid('getEditors',index);																							
				//改变复选框的状态
				domNode = $('#localeMenus').find("#datagrid-row-r1-2-" + index);;
				checkBox = $(domNode).find('span[tree-node-id]');
				checkBox.attr('class','tree-checkbox tree-checkbox1');
				//设置统一的列宽度
				cellWidth = $('#localeMenus').find('.datagrid-header-row').find('td').width();
				//如果已经有在编辑的行，则记录为已经选中
				if(changesObj.checkedIdArr){
					checkedIndex = changesObj.checkedIdArr.indexOf(index);
					if(checkedIndex === -1){
						changesObj.checkedIdArr.push(index);
					};
				}else{
					changesObj.checkedIdArr = [];
					changesObj.checkedIdArr.push(index);
				};
				//遍历每一列值，并设置回输入框中
				for(i=0,len=edits.length;i<len;i++){
					editValue = edits[i].oldHtml;
					editItem = $(edits[i].target)[0];
					$(edits[i].target).closest('div').css({width:cellWidth,margin:0}).closest('td').css({width:cellWidth});
					if(editValue){						
						editItem.value = editValue;
					}else{
						editItem.value = '';
					};
					//如果修改了单元格中的值
					editItem.onchange = function(event){
						editChange = true;	
						if(changesObj.changesIdArr.indexOf(index) === -1){
							changesObj.changesIdArr.push(index);
						};
					};
				};
				$('#localeMenus').find('.datagrid-cell-c1-menuName').closest('td').css({width:cellWidth});
			},
			/**
			 * 拼接处理需要提交的数据
			 */
			collectParams : function(params){
				var  i,
					 j,
					 len,
					 indexId,
					 locale,
					 rowData,
					 changesLen,
				 //保存有多少种语言数据
				 menuLocales = changesObj.menuLocales;
				//保存改变了多少行数据的ID数组
				changesLen = changesObj.changesIdArr.length;
				//IE 8 不支持trim()
				if(!String.prototype.trim) {
			         String.prototype.trim = function () {
			             return this.replace(/^\s+|\s+$/g,'');
			        };
			    }
				for( j=0; j< changesLen ; j++){
					n = 0;

					indexId = changesObj.changesIdArr[j];
					rowData = changesObj[indexId];
					for(i=0, len = menuLocales.length ; i<len ; i++){
						locale = menuLocales[i].id;

						//如果列数据为空，就不拼接该列数据
						menuName = rowData[locale].trim();
						params['menus['+j+'].menuId'] = indexId ;
						if(menuName){
							params['menus['+j+'].localeMenus['+ n +'].menuId'] = indexId;
							params['menus['+j+'].localeMenus['+ n +'].locale'] = locale;
							params['menus['+j+'].localeMenus['+ n +'].menuName'] = rowData[locale];
							n++;
						};
					};
				};
			}
		};
		
	});
	
	
	
});