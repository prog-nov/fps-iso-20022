<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<div id="fsD1" class="slider" style="width:660px;height:380px;">  
    <div id="D1pic1" class="fPic">  
        <div class="fcon" style="display: none;">
            <a  href="#" >
            <img src='<b:path url="{root}/resources/app/css/theme/{theme}/images/slider_01.png"/>' class="sliderimg"></a>
            <span class="shadow"><a  href="#">测试标题一，模特的效果</a></span>
        </div>
        
        <div class="fcon" style="display: none;">
            <a  href="#" >
            <img src='<b:path url="{root}/resources/app/css/theme/{theme}/images/slider_02.png"/>' class="sliderimg"></a>
            <span class="shadow"><a  href="#">测试标题二，模特的效果</a></span>
        </div>
        
        <div class="fcon" style="display: none;">
            <a  href="#" >
            <img src='<b:path url="{root}/resources/app/css/theme/{theme}/images/slider_03.png"/>' class="sliderimg"></a>
            <span class="shadow"><a  href="#">测试标题三，模特的效果</a></span>
        </div>
        
        <div class="fcon" style="display: none;">
            <a  href="#" >
            <img src='<b:path url="{root}/resources/app/css/theme/{theme}/images/slider_04.png"/>' class="sliderimg"></a>
            <span class="shadow"><a  href="#">测试标题四，模特的效果</a></span>
        </div>    
    </div>
    <div class="fbg">  
    <div class="D1fBt" id="D1fBt">  
        <a href="javascript:void(0)" hidefocus="true" target="_self" class=""><i>1</i></a>  
        <a href="javascript:void(0)" hidefocus="true" target="_self" class=""><i>2</i></a>  
        <a href="javascript:void(0)" hidefocus="true" target="_self" class="current"><i>3</i></a>  
        <a href="javascript:void(0)" hidefocus="true" target="_self" class=""><i>4</i></a>  
    </div>  
    </div>  
</div>