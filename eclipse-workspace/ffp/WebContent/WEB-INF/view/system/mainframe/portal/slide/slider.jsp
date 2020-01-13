<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<div id="fsD1" class="slider" style="width:660px;height:380px;">  
    <div id="D1pic1" class="fPic">  
        <div class="fcon" style="display: none;">
            <a  href="#" >
            <img src='<b:path url="{root}/resources/app/css/theme/{theme}/images/slider_01.png"/>' class="sliderimg"></a>
            <span class="shadow"><a  href="#"><spring:message code="system.mainframe.portal.slide.testTitleOne" /></a></span>
        </div>
        
        <div class="fcon" style="display: none;">
            <a  href="#" >
            <img src='<b:path url="{root}/resources/app/css/theme/{theme}/images/slider_02.png"/>' class="sliderimg"></a>
            <span class="shadow"><a  href="#"><spring:message code="system.mainframe.portal.slide.testTitleTwo" /></a></span>
        </div>
        
        <div class="fcon" style="display: none;">
            <a  href="#" >
            <img src='<b:path url="{root}/resources/app/css/theme/{theme}/images/slider_03.png"/>' class="sliderimg"></a>
            <span class="shadow"><a  href="#"><spring:message code="system.mainframe.portal.slide.testTitleThree" /></a></span>
        </div>
        
        <div class="fcon" style="display: none;">
            <a  href="#" >
            <img src='<b:path url="{root}/resources/app/css/theme/{theme}/images/slider_04.png"/>' class="sliderimg"></a>
            <span class="shadow"><a  href="#"><spring:message code="system.mainframe.portal.slide.testTitleFour" /></a></span>
        </div>    
    </div>
    <div class="fbg">  
    <div class="D1fBt" id="D1fBt">  
        <a href="javascript:void(0)" hidefocus="true" target="_self" class=""><i><spring:message code="system.mainframe.portal.slide.one" /></i></a>  
        <a href="javascript:void(0)" hidefocus="true" target="_self" class=""><i><spring:message code="system.mainframe.portal.slide.two" /></i></a>  
        <a href="javascript:void(0)" hidefocus="true" target="_self" class="current"><i><spring:message code="system.mainframe.portal.slide.three" /></i></a>  
        <a href="javascript:void(0)" hidefocus="true" target="_self" class=""><i><spring:message code="system.mainframe.portal.slide.four" /></i></a>  
    </div>  
    </div>  
</div>