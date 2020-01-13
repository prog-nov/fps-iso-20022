<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<script type="text/javascript">
	$(function(){
		var t = $('#cal-tabs');
		t.tabs({
            fit: true,
            height: "auto",
            plain: true,
            tabPosition: 'bottom',
            border: false,
            tools:[{
        		iconCls:'bf4j-icon-node-47',
        		handler:function(){
        			var num = t.tabs('tabs').length;
        			t.tabs('add',{
        				title: '备忘'+(num+1),
        				content: '备忘内容'+(num+1)
        			});
        		}
        	},{
        		iconCls:'bf4j-icon-node-30',
        		handler:function(){
        			alert('保存');
        		}
        	}]

        });
	});
</script>
<div id="cal-tabs"  style="width:328px;height:240px;">   
    <div title='<spring:message code="system.mainframe.portal.demo" />' style="padding:20px;"> 
		<spring:message code="system.mainframe.portal.time" /><br/> 
		<spring:message code="system.mainframe.portal.title" /><br/>
		<spring:message code="system.mainframe.portal.content" />
    </div>   
</div>