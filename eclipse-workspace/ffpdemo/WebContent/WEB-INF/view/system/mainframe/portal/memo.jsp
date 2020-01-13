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
    <div title="备忘1" style="padding:20px;"> 
		时间：9:30 至 11:30<br/> 
		标题：三号会议室开会<br/>
		内容：评审公司平台备忘录功能的设计
    </div>   
</div>