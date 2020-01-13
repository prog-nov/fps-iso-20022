<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<i class='bf4j-icon-node-55 scrollButtons left' style="margin-right: 10px;">&nbsp;</i>
<div id="sliderBox">
	<div style="overflow: hidden;" class="scroll">
		<div class="scrollContainer">
			<c:forEach items="${taskDataList}" var="taskData" >
				<div class="panel" id="panel_<c:out value='${taskData.idx1}'/>" >
					<div class="inside" id="div<c:out value='${taskData.taskId}'/>">
						<img src='<b:path url="{root}/resources/app/css/theme/{theme}/images/task_${taskData.idx}.jpg"/>' alt="<c:out value='${taskData.taskName}'/>" />
						<h2 align="center">
							<c:out value='${taskData.taskName}'/>(<label id="panel_label_<c:out value='${taskData.taskId}'/>"><c:out value='${taskData.taskNum}'/></label>)
						</h2>
						<input type="hidden" 
							id="taskId<c:out value='${taskData.taskId}'/>"
							taskId="<c:out value='${taskData.taskId}'/>"
							taskNum="<c:out value='${taskData.taskNum}'/>"
							idx1="<c:out value='${taskData.idx1}'/>"
							idx2="<c:out value='${taskData.idx2}'/>"
							detailFlag="<c:out value='${taskData.detailFlag}'/>"
							targetUrl="<c:out value='${taskData.targetUrl}'/>"
							menuId="<c:out value='${taskData.menuId}'/>"
							menuName="<c:out value='${taskData.menuName}'/>"
							detailMenuId="<c:out value='${taskData.detailMenuId}'/>"
							detailMenuName="<c:out value='${taskData.detailMenuName}'/>"
						/>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
<input type="hidden" id="taskUpdateFlag" value="0"> 
<i class='bf4j-icon-node-57 scrollButtons right' >&nbsp;</i>