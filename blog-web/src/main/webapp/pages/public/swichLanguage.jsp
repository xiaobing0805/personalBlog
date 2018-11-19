<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
    .dropdown-toggle{height : 50px;}
    .show-hidden-xs{float: left;}
</style>
    <input id="currentLanguage" type="text" style="display: none;" value="<spring:message code="file.i18n" />" />
    <c:set var="currentLanguage" scope="session">
        <spring:message code="file.i18n" />
    </c:set>
<li class="dropdown user user-menu language">
    <c:if test="${currentLanguage  == 'zh_CN'}">
        <a href="javascript:void();" class="dropdown-toggle" data-toggle="dropdown">
            <img src="./../../static/images/guoqi/china.jpeg" class="user-image" alt="China">
            <span class="hidden-xs show-hidden-xs">中国（简体）</span>
        </a>
    </c:if>
    <c:if test="${currentLanguage  == 'zh_TW'}">
        <a href="javascript:void();" class="dropdown-toggle" data-toggle="dropdown">
            <img src="./../../static/images/guoqi/china.jpeg" class="user-image" alt="China">
            <span class="hidden-xs show-hidden-xs">中国（繁体）</span>
        </a>
    </c:if>
    <c:if test="${currentLanguage  == 'en'}">
        <a href="javascript:void();" class="dropdown-toggle" data-toggle="dropdown">
            <img src="./../../static/images/guoqi/english.jpeg" class="user-image" alt="China">
            <span class="hidden-xs show-hidden-xs">English</span>
        </a>
    </c:if>
    <ul class="dropdown-menu pull-right" style="width: 137px;">
        <li>
            <a class="switchLanguage"  value="zh_CN" href="javascript:void();">
                <img style="margin-top: -5px;" src="./../../static/images/guoqi/china.jpeg" class="user-image" alt="China">
                <span class="hidden-xs">中国（简体）</span>
            </a>
        </li>
        <li>
            <a class="switchLanguage"  value="zh_TW" href="javascript:void();">
                <img style="margin-top: -5px;" src="./../../static/images/guoqi/china.jpeg" class="user-image" alt="China">
                <span class="hidden-xs">中国（繁体）</span>
            </a>
        </li>
        <li>
            <a class="switchLanguage" value="en" href="javascript:void();">
                <img style="margin-top: -5px;" src="./../../static/images/guoqi/english.jpeg" class="user-image" alt="English">
                <span class="hidden-xs">English</span>
            </a>
        </li>
    </ul>
</li>
<script type="text/javascript">
	$(function(){
		$(".switchLanguage").click(function(){
	        $.utils.switchLanguage($(this).attr("value"));
	    });		
	});
</script>