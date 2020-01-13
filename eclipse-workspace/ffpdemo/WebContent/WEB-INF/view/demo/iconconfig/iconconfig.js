/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色管理<br>
 * Author : leoYang <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-5<br>
 */
require(['beneform4j-page'], function(Page) {
	Page.create(function($){

		var me = this,
			MenuIconDiv=$("#MenuIcon").find(".bf4j-group-content"),
			HomeIconDiv=$("#HomeIcon").find(".bf4j-group-content"),
			PointIconDiv=$("#PointIcon").find(".bf4j-group-content"),
			BtnIconDiv=$("#BtnIcon").find(".bf4j-group-content"),
			RowBtnIconDiv=$("#RowBtnIcon").find(".bf4j-group-content"),
			OtherIconDiv=$("#OtherIcon").find(".bf4j-group-content"),
			RightMenuIconDiv=$("#RightMenuIcon").find(".bf4j-group-content")
		 	
		return {
			init :  function(){
				$b.Msg.closeProgress();
				//加载树形菜单图标合集
				me.loadIconList(MenuIconDiv,"bf4j-icon-node-",76);
				
				//加载首页快捷菜单图标合集
				me.loadIconList(HomeIconDiv,"bf4j-icon-big-",15);
				
				//加载悬浮菜单图标合集
				me.loadIconList(PointIconDiv,"bf4j-icon-mid-",22);
				
				//加载按钮图标合集
				var btnArray=[
				              "bf4j-icon-btn-search",
				              "bf4j-icon-btn-add",
				              "bf4j-icon-btn-refresh",
				              "bf4j-icon-btn-delete",
				              "bf4j-icon-btn-edit",
				              "bf4j-icon-btn-save",
				              "bf4j-icon-btn-look",
				              "bf4j-icon-btn-collect",
				              "bf4j-icon-btn-file",
				              "bf4j-icon-btn-date",
				              "bf4j-icon-btn-reset",
				              "bf4j-icon-btn-start",
				              "bf4j-icon-btn-stop",
				              "bf4j-icon-btn-offline",
				              "bf4j-icon-btn-download",
				              "bf4j-icon-btn-delock",
				              "bf4j-icon-btn-lock"
				             ];
				me.loadIconList(BtnIconDiv,"bf4j-icon-mid-",btnArray,"bf4j-btn-2");
				
				//加载右键菜单图标合集
				var rightMenuArray=[
						      "bf4j-icon-right-look",
				              "bf4j-icon-right-search",
				              "bf4j-icon-right-add",
				              "bf4j-icon-right-refresh",
				              "bf4j-icon-right-reset",
				              "bf4j-icon-right-delete",
				              "bf4j-icon-right-deleteAll",
				              "bf4j-icon-right-close",
				              "bf4j-icon-right-closeAll",
				              "bf4j-icon-right-attache",
				              "bf4j-icon-right-edit",
				              "bf4j-icon-right-history",
				              "bf4j-icon-right-save",
				              "bf4j-icon-right-start",
				              "bf4j-icon-right-stop",
				              "bf4j-icon-right-offline",
				              "bf4j-icon-right-download",
				              "bf4j-icon-right-delock",
				              "bf4j-icon-right-lock"
				             ];
				me.loadIconList(RightMenuIconDiv,"bf4j-icon-mid-",rightMenuArray);
				
				//加载行级工具栏按钮图标合集
				var RowBtnArray=[
						      "bf4j-icon-row-look",
				              "bf4j-icon-row-search",
				              "bf4j-icon-row-reset",
				              "bf4j-icon-row-delete",
				              "bf4j-icon-row-edit",
				              "bf4j-icon-row-history",
				              "bf4j-icon-row-add",
				              "bf4j-icon-row-refresh",
				              "bf4j-icon-row-close",
				              "bf4j-icon-row-save",
				              "bf4j-icon-row-download"
				             ];
				me.loadIconList( RowBtnIconDiv,"bf4j-icon-mid-",RowBtnArray);
				
				//加载行级工具栏按钮图标合集
				var OtherArray=[
						      "bf4j-icon-other-hot",
				              "bf4j-icon-other-new",
				              "bf4j-icon-other-fire",
				              "bf4j-icon-other-goldmedal",
				              "bf4j-icon-other-crown"
				             ];
				me.loadIconList( OtherIconDiv,"bf4j-icon-other-",OtherArray);
				
			},
			
			loadIconList :  function(warp,className,object,typeClass){
				if(!typeClass){typeClass="";}
				var addIconToList=function(cName)
				{
					var IconHtml=
						"<div class='icon-node' title='"+cName+"' >"+
						"<a class='"+typeClass+"' ><i class='"+cName+"' ></i></a>"+
						"<input type='text' readonly='readonly' style='width:90%'   value='"+cName+"' />"+
						"</div>";
						$node=$(IconHtml);
						warp.append($node);
						$node.on("click",function(){
							$(this).find("input").select();
						});
						$node.tooltip();
				}
				
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

		};
	});
});