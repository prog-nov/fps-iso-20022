/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 下拉组件示例<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-16<br>
 */
require(['beneform4j-page'], function(Page) {
	Page.create(function($){
		var me = this,
			grid = $('#dataList'),
			apis = {
				list : $b.context.root + '/system/sysmanager/user/list'
			};
		return {
			init :  function(){
				
				/**
				 * 1.设置下拉域(bf4j-combo)
				 */
				me.setComboFields($('#searchForm'));
				/**
				 * 2.设置机构域(bf4j-organ)
				 */
			    me.setOrganField($('#searchForm'));
			    /**
			     * 3.设置除下拉域之外的combo数据，用于渲染列表数据
			     */
			    me.setComboDatas('CERT_TYPE');
			    
			    /**
			     * 4.如果不想多次和服务器交互，也可以先初始化调用me.setComboDatas('CERT_TYPE','BOOLEAN',....)，将所有combo数据一次性获取
			     *   然后再设置 me.setComboFields($('#searchForm'));
			     */
			    //me.setComboDatas('CERT_TYPE','BOOLEAN',....);
			    //me.setComboFields($('#searchForm'));
			    
			    /**
			     * 5.combo数据在表格中的渲染示例
			     */
			    grid.datagrid({
					url : apis.list,
					idField : 'userId',
					pagination : true,
					rowTool: false,
					frozenColumns : [ [ 
	    			   	{field : 'userId', checkbox : true}, 
	    			   	{field : 'userName', title : '用户名称', align:'left', width : 100, sortable : true}
	    			] ],
					columns : [ [ 
					    {field : 'orgId', title : '机构号', align:'center'},
						{field : 'certType', title : '证件类型', align:'left', 
					    	/**
					    	 * 1.使用combo数据渲染列表的方式一：直接使用父类中的渲染函数工厂，返回一个渲染函数
					    	 */
					    	formatter: me.comboFormatter('CERT_TYPE')
					    },
					    {field : 'userStatus', title : '状态', align:'center', 
					    	formatter : function(value,row,index){
					    		/**
					    		 * 2.使用combo数据渲染列表的方式二：先获取combo数据对象，然后根据该对象渲染 
					    		 */
								 var data = me.getComboData('BOOLEAN', value);
								 if(!data){
									 return '';
								 }else{
									 return data.id === '1' ? '启用' : '停用';
								 }
					    	}
					    },
						{field : 'lockFlag', title : '锁定标志', align:'center', 
					    	
					    	formatter : function(value,row,index){
					    		/**
					    		 * 2.使用combo数据渲染列表的方式二：先获取combo数据对象，然后根据该对象渲染 
					    		 */
								 var data = me.getComboData('BOOLEAN', value);
								 if(!data){
									 return '';
								 }else{
									 if(data.id === '1'){
										 return '<font color="green">'+data.text+'</font>';
									 }else{
										 return '<font color="red">'+data.text+'</font>';
									 }
								 }
					    	}
					    },
						{field : 'lockDate', title : '锁定日期', align:'center'},
						{field : 'lockTime', title : '锁定时间', align:'center'}
			        ] ]
				});
				
				//绑定事件
				$(".doQuery").on("click", me.doQuery);
			    $b.Msg.closeProgress();
			},
			
			doQuery : function(){
				$('#searchForm').form('validate');
				$b.Msg.alert('请查看控制台中输出参数....')
			}
		};
	});
});