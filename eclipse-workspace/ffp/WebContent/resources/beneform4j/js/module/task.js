/**
 * Copy Right Information : Forms Syntron </br>
 * Project : 四方精创 Java EE 开发平台 </br>
 * Description : 首页任务图标处理 </br>
 * Author : luowei </br>
 * Version : 1.0.0 </br>
 * Since : 1.0.0 </br>
 * Date : 2016-9-13</br>
 */
define(['common'], function($b) {
	var totalPanels = $(".scrollContainer").children().size();
	var regWidth = $(".panel").css("width");
	var regImgWidth = $(".panel img").css("width");
	var regTitleSize = $(".panel h2").css("font-size");
	var regParSize = $(".panel p").css("font-size");

	var movingDistance = 90;

	var curWidth = 105;
	var curImgWidth = 97;
	var curTitleSize = "18px";
	var curParSize = "15px";
	
	var $panels = $('#sliderBox .scrollContainer > div');
	var $container = $('#sliderBox .scrollContainer');
	var initMethod ,returnToNormal,growBigger,change ,curPanel ,updateTaskNum;
	var me = this,
		taskIds = [];
	
	initMethod = function(startPosition){
		$panels.css({
			'float' : 'left',
			'position' : 'relative'
		});
		$("#sliderBox").data("currentlyMoving", false);
		$container
				.css('width', ($panels[0].offsetWidth * $panels.length) + 100)
				.css('left', startPosition);
		$('#sliderBox .scroll').css('overflow', 'hidden');
		
		$(".scrollContainer > div").click(function(){
			var index = $b.String.replaceAll($(this).attr("id"), "panel_" , "");
			if(curPanel > index){
				change(false , curPanel - index);
			}else if(curPanel < index){
				change(true ,index - curPanel);
			}
		});
	};
	returnToNormal = function returnToNormal(element) {
		$(element).animate({
			width : regWidth
		}).find("img").animate({
			width : regImgWidth
		}).end().find("h2").animate({
			fontSize : regTitleSize,
			width : regImgWidth
		}).end().find("p").animate({
			fontSize : regParSize
		});
	};
	growBigger = function growBigger(element) {
		$(element).animate({
			width : curWidth
		}).find("img").animate({
			width : curImgWidth
		}).end().find("h2").animate({
			fontSize : curTitleSize,
			width : curImgWidth
		}).end().find("p").animate({
			fontSize : curParSize
		});
	};
	change = function change(direction , cnt) {
		if ((direction && !(curPanel < totalPanels))
				|| (!direction && (curPanel <= 1))) {
			return false;
		}
		if (($("#sliderBox").data("currentlyMoving") == false)) {
			$("#sliderBox").data("currentlyMoving", true);
			var next = direction ? curPanel + cnt : curPanel - cnt;
			var leftValue = $(".scrollContainer").css("left");
			var movement = direction ? parseFloat(leftValue, 10)
					- movingDistance *cnt : parseFloat(leftValue, 10)
					+ movingDistance *cnt;

			$(".scrollContainer").stop().animate({
				"left" : movement
			}, function() {
				$("#sliderBox").data("currentlyMoving", false);
			});

			returnToNormal("#panel_" + curPanel);
			growBigger("#panel_" + next);
			curPanel = next;
		}
	};
	updateTaskNum = function updateTaskNum (){
		var taskUpdateFlag = $("#taskUpdateFlag").val();	// 更新状态标识：0 未执行；1 执行中
		var tab = top.$("#index_tabs").tabs('getSelected');
		var index = top.$("#index_tabs").tabs('getTabIndex',tab);	// 获取当前活动的标签页
		if(index != 0 || taskUpdateFlag == 1){ // 如果当前活动的选项卡不为首页或正在更新，不进行消息数更新
			return;
		}
		$("#taskUpdateFlag").val("1");	// 更新状态为执行中
       	$b.Submit.ajaxSubmit(server_consts.root+'/portal/center/queryTaskNum', {'needBlock':false,'taskIds':me.taskIds}, 
       			function(data){ // 更新消息数成功时回调函数
	           		// 清除任务数及关闭事件
       				$(".scrollContainer").find("label[id^='panel_label']").html('0');
       				$(".scrollContainer").find("input[id^='taskId']").attr('taskNum',0);
       				// 重置任务数
       				$.each(data, function(j, task){
	                	$('#panel_label_'+task.taskId).html(task.taskNum);
	                	$('#taskId'+task.taskId).attr('targetUrl',task.targetUrl);
	                	$('#taskId'+task.taskId).attr('taskNum',task.taskNum);
	        		});
       				$("#taskUpdateFlag").val("0");
       			},
       			function(data){ // 更新消息数失败时回调函数
       	        	clearInterval(timeID);
       			},
       			true
       	);
    };
	return {
		instTask : function(){
			var totalCnt = $(".scrollContainer > .panel").length;
			var startPosition = "-105px" ;
			if($b.Check.isNullOrWhiteSpace(totalCnt)){
				return false ;
			}
			var printCnt = parseInt(($("#busi_status").width() -50 ) / movingDistance);
			if(totalCnt > printCnt){
				curPanel = parseInt(printCnt / 2) + 2;
			}else{
				curPanel = parseInt(totalCnt / 2) + 1;
				startPosition = (($("#busi_status").width() - $(".scrollContainer").width()) / 2 + 90) + "px" ;
			}
			initMethod(startPosition);  
			growBigger("#panel_" + curPanel);

			$(".right").click(function() {
				change(true , 1);
			});
			$(".left").click(function() {
				change(false , 1);
			});
		},
		movingTask : function(event){
			switch (event.keyCode) {
			case 13: //enter
				$(".right").click();
				break;
			case 32: //space
				$(".right").click();
				break;
			case 37: //left arrow
				$(".left").click();
				break;
			case 39: //right arrow
				$(".right").click();
				break;
			}
		},
		autoFit : function(){
			var sliderWidth = $("#busi_status").width();
			if(sliderWidth > 660){
				sliderWidth = (sliderWidth - 660)/2 ;
			}else{
				sliderWidth = 0;
			}
			$(".slider").css("position","relative").css("left" , sliderWidth + "px");
			$("#sliderBox").css("width" , ($("#busi_status").width() -50 ) + "px");
			$("#sliderBox>.scroll").css("width" , ($("#busi_status").width() -50 ) + "px");
			$("#busi_status i.right").css("left" ,($("#busi_status").width() -10 ) + "px");
		},
		
		initTaskTimer : function(){
			var hasTaskFlag = false;
			// 初始化taskId数组
			var taskObjs = $(".scrollContainer").find("input[id^='taskId']");
	        if(taskObjs != undefined && taskObjs.length > 0){
		       	$.each(taskObjs, function(j, task){
		       		taskIds.push($(task).attr('taskId'));
		       	});
		       	me.taskIds = taskIds;
		       	hasTaskFlag = true;
	        }
	        else{
	       	 	hasTaskFlag = false;
	        }
	        // 若存在代办事项则启动定时任务及绑定时间
	        // 若没有则隐藏
	        if(hasTaskFlag){
				// 定时刷新任务数
	            var timeID = setInterval(updateTaskNum,300000);
	            updateTaskNum();
			}else{
				$(".scrollContainer").hide();
			}
		},
		//初始化绑定点击事件
		initTaskClick : function(){
			$(".scrollContainer").find('.inside').on('click',function(){
            	var divId = $(this).attr('id');
        		var taskObj = $('#'+divId.replace('div','taskId'));
        		if(taskObj.attr('taskNum') != '0'){
        			var herf = server_consts.root +'/' +taskObj.attr('targetUrl');
        			var detailFlag  = taskObj.attr('detailFlag');
        			$b.Submit.ajaxSubmit(server_consts.root+'/portal/center/findTaskParam', {'needBlock':false,'taskId':taskObj.attr('taskId')}, 
               			function(data){ // 更新消息数成功时回调函数
        					$('#panel_label_'+taskObj.attr('taskId')).html(data.taskNum);
                   			$('#taskId'+taskObj.taskId).attr('taskNum',data.taskNum);
                   			if(data.taskNum > '0'){
        	           			if(detailFlag == '1'){
        	           				herf = herf + '?busiKey=' + data.busiKey+'&params='+data.params;
        	           				$('#mcenter-task-detail').window({    
        	           					title: taskObj.attr('detailMenuName'),
        	           					minimizable: false,
        	           					collapsible: false,
        	           					width: 420,    
        	           					height: 380,    
        	           					href: herf
        	           				});
        	        			}else{
        	        				herf = herf + '?params='+data.params;
        	        				$b.App.openUrl(herf,taskObj.attr('menuName'),{targetPage : 'tab',path : taskObj.attr('menuName'),menuId:taskObj.attr('menuId')}); 
        	        			}
        	           		}else{
        	           			$('#panel_label_'+taskObj.attr('taskId')).html(0);
        	           			$('#taskId'+taskObj.taskId).attr('taskNum',0);
        	           		}
               			},
               			function(data){ // 更新消息数失败时回调函数
               				$('#panel_label_'+taskObj.attr('taskId')).html(0);
               			}
                   	);
        		
        		}
        	});
		}
	}
	
});