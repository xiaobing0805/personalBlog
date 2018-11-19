<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message code="user.login" /> <spring:message code="title.suffix" /></title>
	<link href="./../../static/images/logo_heard.ico" rel="shortcut icon">
	<link rel="stylesheet" href="./../../static/css/login.css">
</head>
<body>
	<div id="sign">
		<div class="signBg">
			<div class="signBox">
				<p class="col">
					<spring:message code="user.login" />
				</p>
				<c:if test="${error != null}">
					<p class="error">
						${error}
					</p>
				</c:if>
				<form action="/login" method="post">
					<input type="hidden" id="rememberMe" name="rememberMe" value="true" />
					<div>
						<input onfocus="javascript:removeError();" maxlength="64" type="text" name="username" placeholder="<spring:message code="blog.username" />" value="${username}"/>
					</div>
					<div>
						<input onfocus="javascript:removeError();" maxlength="64" type="password" name="password" placeholder="<spring:message code="blog.password" />"/>
					</div>
					<div class="img">
						<input onfocus="javascript:removeError();" maxlength="4" type="text"  placeholder="<spring:message code="blog.captcha" />" name="captcha" autocomplete="off"/>
						<span>
							<img class="captcha" alt="验证码" src="/common/captcha" title="点击更换" id="img_captcha" onclick="javascript:refreshCaptcha();">
						</span>
					</div>
					<span style="float:left;cursor: pointer;">
						<input checked="checked" id="remenberMeChecked" type="checkbox"/> 
						<a id="MonthWithoutLogin"><spring:message code="blog.free.login" /></a>
					</span>
					<!-- <a class="retrieve" href="#">
						找回密码
					</a> -->

					<button type="submit" class="land">
						<spring:message code="blog.signin" />
					</button>
					<!-- <div class="register">
						<a href="">
							还没有账户，马上注册
						</a>
					</div> -->
				</form>
			</div>
		</div>
	</div>
	<!-- jQuery 2.1.4 -->
    <script src="./../../static/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript"> 
	    function removeError(){
	    	$(".error").remove();
	    }
	    
	    $("#MonthWithoutLogin").click(function(){
	    	
	    	$("#remenberMeChecked").click();
	    });
	    
	    $("#remenberMeChecked").click(function(){
	    	
	    	$("#rememberMe").val($('#remenberMeChecked').is(':checked'));
	    });
	    
	    function remenberMeMonth(){
	    	
	    }
		function refreshCaptcha(){  
		    document.getElementById("img_captcha").src="/common/captcha?t=" + Math.random();  
		}  
	</script>
</body>
</html>