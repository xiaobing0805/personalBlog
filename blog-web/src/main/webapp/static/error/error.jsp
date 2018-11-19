<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
   String path = request.getContextPath();  
   String basePath = request.getScheme() + "://"  
           + request.getServerName() + ":" + request.getServerPort()  
           + path + "/";  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>服务器已累倒 - Young tao</title> 
	<link rel="stylesheet" href="<%=basePath%>static/css/error/reset.css"  type="text/css"/>
	<link rel="stylesheet" href="<%=basePath%>static/css/error/error.css"  type="text/css"/>
	<link href="<%=basePath%>static/images/logo_heard.ico" rel="shortcut icon">
</head>
<body >
	<div class="error_content">
		<div class="error_left">
			 <span class="sp_con"></span> 
		</div>
		<div class="error_right">
			<div class="error_detail">
				<p class="error_p_title">客官~ 服务器已累倒</p>
				<p class="error_p_con">●别急，工程师正在紧急处理，马上就好。</p>
				<p class="error_p_con">●您可发送邮件MicroCreativity@foxmail.com给站长!</p>
				<p class="error_p_con">●如果您觉得本站有帮助到你，可资助站长继续前行，<a class="contribution" href="#">施舍站长</a></p>
			</div>
			<div class="btn_error">
				<a class="btn_back1" href="##">施舍站长</a>
				<a class="btn_back2"href="javascript:history.go(-1);">返回上一页</a>
			</div>
			 
		</div>
	</div>
</body>
</html>