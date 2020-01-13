/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 节假日维护<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-19<br>
 */
require(['beneform4j-page','mycalendar'], function(Page) {
	Page.create(function($){
		var me = this,
			cal = $('#holiday_calendar'),
			apis = {
				list : 'list',
				update : 'update'
			};
		window.sMemo = new Array();
		
		return {
			init :  function(){
			    
				var now = new Date();
				var today = $b.Date.format(now,'yyyyMMdd');
				var calMemoUrl = server_consts.root + '/portal/memo/getMemoList';
				me.setComboFields($('#searchForm'));
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
						cal.fullCalendar({ 
							fit : false,
							border : true,
						    current : new Date(),
						    onSelect : function(date,obj){
						    	var field =  $('input[name=selectDates]');
						    	var value = field.val();
						    	var dt = $b.Date.format(date,'yyyyMMdd');
						    	if($b.String.trim(value) === ''){
						    		$(obj).addClass('holiday-selected');
						    		field.val(dt);
						    	} else if (value.indexOf(dt) === -1){
						    		$(obj).addClass('holiday-selected');
						    		field.val(value + ',' + $b.Date.format(date,'yyyyMMdd'));
						    	} else {
						    		$(obj).removeClass('holiday-selected');
						    		value = value.replace(',' + dt, '');
						    		value = value.replace(dt + ',', '');
						    		value = value === '' ? '' : value.replace(dt, '');
						    		field.val(value);
						    	}
						    }
						});
					}
				});
				//绑定事件
				$(".doSave").on("click", me.doSave);
				$(".doReset").on("click", me.doReset);
			    $b.Msg.closeProgress();
			},
			doSave : function(){
				var params = {dateStr : $('input[name=selectDates]').val(),
						holidayName : $('input[name=holidayName]').val(),
						isHoliday : $('input[name=isHoliday]').val()};
				if($b.Check.isNullOrEmptyStr(params.dateStr)){
					$b.Msg.warning('请选择日期');
					return;
				}
				$b.Submit.ajaxSubmit(apis.update, params, function(rs){
					$b.Msg.alert('操作成功');
				});
			},
			doReset : function(){
				$('td.holiday-selected').removeClass('holiday-selected');
				$('#searchForm').form('reset');
			}
		};
	});
});