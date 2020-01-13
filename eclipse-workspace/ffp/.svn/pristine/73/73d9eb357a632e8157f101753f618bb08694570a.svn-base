require(['beneform4j-page','locale'], function(Page,locale) {
	Page.create(function($){
		var me = this,
			form = $('#settingForm'),
			apis = {
				switchReceiveMode : 'switchReceiveMode',
				switchRealTimeListenerStat : $b.context.root+'/ffp/systemmaintenance/systemstat/switchRealTimeListenerStat',
				switchController : 'switchController',
				list : 'list'
			};
		
		return {
			init :  function(){			    
				$b.Submit.ajaxSubmit(apis.list,{}, me.showDataCallback);
				
				//绑定事件
//				$("a.bf4j-btn-2").filter(".gotoUpdate").on("click", function(){
//				   me.selectOne(grid, me.gotoUpdate);
//			    }).end();
			    $b.Msg.closeProgress();
			},
			
			showDataCallback : function(data){
				form.form("load", data);
				if('C' == data.realtimeControlStat || 'R' == data.realtimeControlStat)
				{
					$('#switchRealtimeControlButton').attr("title", locale.ffp.systemmaintenance.systemstat.switchController[data.realtimeControlStat]);
					$('#switchRealtimeControlButton').text(locale.ffp.systemmaintenance.systemstat.switchController[data.realtimeControlStat]);
					$('#switchRealtimeControlButton').bind("click", data, me.switchController);
					$('#switchRealtimeControlButton').show();
				}
				else
				{
					$('#switchRealtimeControlButton').unbind("click");
					$('#switchRealtimeControlButton').hide();
				}
				
				if('-' == data.realtimeControlStat || 'C' == data.realtimeControlStat)
				{
					if('C' == data.realtimeListenerStat || 'R' == data.realtimeListenerStat)
					{
						$('#switchReceiveModeButton').attr("title", locale.ffp.systemmaintenance.systemstat.switchmode[data.fpsReceiveMode]);
						$('#switchReceiveModeButton').text(locale.ffp.systemmaintenance.systemstat.switchmode[data.fpsReceiveMode]);
						$('#switchReceiveModeButton').bind("click", data, me.switchReceiveMode);
						$('#switchReceiveModeButton').show();
						$('#switchRealtimeListenerStatButton').attr("title", locale.ffp.systemmaintenance.systemstat.switchListener[data.realtimeListenerStat]);
						$('#switchRealtimeListenerStatButton').text(locale.ffp.systemmaintenance.systemstat.switchListener[data.realtimeListenerStat]);
						$('#switchRealtimeListenerStatButton').bind("click", data, me.switchRealTimeListenerStat);
						$('#switchRealtimeListenerStatButton').show();
					}
					else
					{
						$('#switchReceiveModeButton').unbind("click");
						$('#switchReceiveModeButton').hide();
						$('#switchRealtimeListenerStatButton').unbind("click");
						$('#switchRealtimeListenerStatButton').hide();
					}
				}
				else
				{
					$('#switchReceiveModeButton').unbind("click");
					$('#switchReceiveModeButton').hide();
					$('#switchRealtimeListenerStatButton').unbind("click");
					$('#switchRealtimeListenerStatButton').hide();
				}
				$b.Msg.closeProgress();
			},
			
			switchReceiveMode : function() {
				var fpsReceiveMode = $('#fpsReceiveMode').val();
				var realtimeListenerStat = $('#realtimeListenerStat').val();
				
				if('RLTM' == fpsReceiveMode
						|| ('BTCH' == fpsReceiveMode && 'R' == realtimeListenerStat))
				{
					$b.Msg.confirm(locale.ffp.systemmaintenance.systemstat.confirm1+locale.ffp.systemmaintenance.systemstat.switchmode[fpsReceiveMode]+locale.ffp.systemmaintenance.systemstat.confirm2, function(){
						$('#switchReceiveModeButton').attr("title", locale.ffp.systemmaintenance.systemstat.processing);
						$('#switchReceiveModeButton').text(locale.ffp.systemmaintenance.systemstat.processing);
						$('#switchReceiveModeButton').unbind("click");
						
						var form = $('#settingForm');
						if(!form.form('validate')){
							return false;
						}
						var params = {url : apis.switchReceiveMode}
						$b.Submit.ajaxSubmitForm(form, params, me.showDataCallback);
					});
				}
				else if('BTCH' == fpsReceiveMode && 'R' != realtimeListenerStat)
				{
					alert(locale.ffp.systemmaintenance.systemstat.alertMsg1);
					return false;
				}
				else
				{
					alert(locale.ffp.systemmaintenance.systemstat.processing);
					return false;
				}
			},
			
			switchRealTimeListenerStat : function() {
				var fpsReceiveMode = $('#fpsReceiveMode').val();
				var realtimeListenerStat = $('#realtimeListenerStat').val();
				
				if('C' == realtimeListenerStat
						|| ('R' == realtimeListenerStat && 'BTCH' == fpsReceiveMode))
				{
					$b.Msg.confirm(locale.ffp.systemmaintenance.systemstat.confirm1
							+locale.ffp.systemmaintenance.systemstat.switchListener[realtimeListenerStat]
							+locale.ffp.systemmaintenance.systemstat.confirm2, function(){
						
						$('#switchRealtimeListenerStatButton').attr("title", locale.ffp.systemmaintenance.systemstat.processing);
						$('#switchRealtimeListenerStatButton').text(locale.ffp.systemmaintenance.systemstat.processing);
						$('#switchRealtimeListenerStatButton').unbind("click");
						
						var form = $('#settingForm');
						if(!form.form('validate')){
							return false;
						}
						var params = {url : apis.switchRealTimeListenerStat};
						$b.Submit.ajaxSubmitForm(form, params, me.showDataCallback);
					});
				}
				else if('R' == realtimeListenerStat && 'BTCH' != fpsReceiveMode)
				{
					alert(locale.ffp.systemmaintenance.systemstat.alertMsg2);
					return false;
				}
				else
				{
					alert(locale.ffp.systemmaintenance.systemstat.processing);
					return false;
				}
				
			},
			
			switchController : function() {
				var form = $('#settingForm');
				if(!form.form('validate')){
					return false;
				}
				var params = {url : apis.switchController}
				$b.Submit.ajaxSubmitForm(form, params, me.showDataCallback);
			},
		};
	});
});