require(['beneform4j','locale','task','koala.min.1.5','easyui-portal','mycalendar'],function($b,locale,task){
	$(function(){
		var noteiceUrl = server_consts.root + '/common/listNoteiceData';
		$b.Submit.ajaxSubmit(noteiceUrl,{page:1,rows:7}, function(data){
			if(data && data.length > 0){
				var noteiceList = data;
				var noteiceHtml='';
				$.each(noteiceList,function(i,n){
					var msgTitle = n.msgTitle;
					noteiceHtml = noteiceHtml + '<div class="noteice-item">' + '<a msgId="' + 
						n.msgId + '" title="'+ msgTitle + '">' + (n.msgLevel === "1" ? 
						'<i class="bf4j-icon-other-goldmedal"></i>' : n.msgLevel === "2" ? 
						'<i class="bf4j-icon-other-fire"></i>' : '') + (msgTitle.length > 20 ?
						msgTitle.substr(0,20) + '...' : msgTitle) + '</a></div>';
				});
				$('#mcenter-noteice').append(noteiceHtml);
				$('.noteice-item>a').on('click',function(){
					var $this = $(this);
					$('#mcenter-notice-detail').window({    
					    title: locale.system.main.noteice,
					    minimizable: false,
					    collapsible: false,
					    width: 420,    
					    height: 380,    
					    href: server_consts.root + '/common/viewNoteice?msgId=' + $this.attr('msgId')
					});   
				});
			}
		});
		$('#mcenter-portal').portal({
			border : false,
			fit : true
		});
		$(window).resize(function () {
			$('#mcenter-portal').portal('resize');
			task.autoFit();
		});
		window.sMemo = new Array();
		var today = $b.Date.format(new Date() , 'yyyyMMdd');;
		var calMemoUrl = server_consts.root + '/portal/memo/getMemoList';
		$b.Submit.ajaxSubmit(calMemoUrl,{date: today.substring(0,6)},function(data){
			if(data){
				var arr = data;
				var obj,nMemo={};
				for(i in arr){
					obj = arr[i];
					nMemo[obj.memoDate] = (nMemo[obj.memoDate] ? nMemo[obj.memoDate] + '<br/>': obj.memoDate.substring(4) + ' ') + obj.memoTitle;
				}
				$.each(nMemo,function(i,n){
					sMemo.push(n);
				});
				$('#mcenter-calendar').fullCalendar({ 
					fit : true,
					border : true,
				    current : new Date()    
				});
//				以下代码为备忘录演示，暂时注释
//				$("#mcenter-calendar td[abbr]").on('click',function(){
//					$('#mcenter-calendar-edit').window({    
//					    title: locale.system.main.memo,  
//					    shadow: true,
//					    collapsible : false,
//					    minimizable : false,
//					    width: 328,    
//					    height: 240,
//					    href: server_consts.root + '/system/mainframe/portal/memo.page'
//					}); 
//				});
			}
		});
		
		Qfast.add('widgets', { path:  server_consts.root + "/resources/third/jquery/slider/terminator2.2.min.js", type: "js", requires: ['fx'] });  
		Qfast(false, 'widgets', function () {
			K.tabs({
				id: 'fsD1',   //焦点图包裹id  
				conId: "D1pic1",  //** 大图域包裹id  
				tabId:"D1fBt",  
				tabTn:"a",
				conCn: '.fcon', //** 大图域配置class       
				auto: 1,   //自动播放 1或0
				effect: 'fade',   //效果配置
				eType: 'click', //** 鼠标事件
				pageBt:true,//是否有按钮切换页码
				bns: ['.prev', '.next'],//** 前后按钮配置class                          
				interval: 3000  //** 停顿时间  
			}) ;
		});
		task.instTask();
		task.autoFit();
		task.initTaskTimer();
		//待办事项任务点击事件绑定
		task.initTaskClick();
		
		$(window).keydown(function(event) {
			task.movingTask(event);
		});
		$b.Msg.closeProgress();
	});
	$(document).click(function(event){
		parent.framePlus.pointMenu.close();
	    event.stopPropagation();
	});
});