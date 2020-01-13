<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<script type="text/javascript">
	require([ 'beneform4j-easyui','ext-jquery' ], function() {
		var menuId;
		$(function() {
			var title = $(top.$('#index_tabs', parent.document).tabs('getSelected').panel('options').title);
			menuId = title.attr('menuId');
			//设置导航内容
			$('.nav-path-text').text(title.attr('path'));
			
			$('.leaderPage').on('click', function(){
				$('#winLeaderDetail').window({  
					minimizable:false,
					maximized:true,
				    width:680,    
				    height:560,
				    href : server_consts.root + '/common/leaderViewData?menuId=' + menuId
				}); 
			});
			
			$(".icon-ddppic").tooltip({  
    			onShow: function(){  
    				$(this).tooltip('tip').css({        
	    					backgroundColor: '#FFFEE6',     
	    					borderColor: '#FFFEE6', 
	    					color:'#976311'
    					});  
    				}
    		});
		});
	});
</script>
<div class="bf4j-group">
	<div class="toppath">
		<div class="pathtext">
			<i class="bf4j-icon-gpspic"></i><spring:message code="leader.view.current.place"/> : <span class="nav-path-text"></span>
		</div>
		<div class="pathtool leaderPage">
			<i position="left" title='<spring:message code="leader.view.current.tooltip"/>'
				class="icon-ddppic"   ></i>
		</div>
	</div>
	<div id="winLeaderDetail" title='<spring:message code="leader.view.current.title"/>'></div>
</div>