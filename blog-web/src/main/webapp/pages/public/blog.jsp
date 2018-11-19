<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1, user-scalable=0, minimal-ui">
<title><spring:message code="linyantao.blog" /> <spring:message code="title.suffix" /></title>
	<link href="./../../static/images/logo_heard.ico" rel="shortcut icon">
	
    <link href="./../../static/css/bootstrap.min.css" rel="stylesheet">
	<link href="./../../static/css/blog.css" rel="stylesheet">
	<link href="./../../static/css/five.css" rel="stylesheet">
	<link href="./../../static/css/font-awesome.min.css" rel="stylesheet">
	<!-- <link rel="stylesheet" type="text/css" href="./../../static/css/nprogress/nprogress.css"> -->
	
	<script src="./../../static/js/jquery.min.js"></script>
	<!-- Nprogress -->
	<!-- <script src="./../../static/js/nprogress/nprogress.js"></script> -->
	<style type="text/css">
		.munt {width:auto;height:auto}
		.munt div{float:left;}
	</style>
</head>
<body>
<!-- <script type="text/javascript">
	NProgress.start();
</script> -->
<jsp:include page="./blogHeader.jsp" />
<!-- <div style="background-color:#ffe;padding: 5px;">
	<marquee width=100% onmouseover=this.stop() onmouseout=this.start()>
		<a target="_blank" style="font-size: 12px;" href="http://amazeui.org/">1、感谢Amaze UI、Bootstrap提供这么好的样式库，谢谢。</a>
	</marquee>
</div> -->
<section class="content-wrap">
    <div class="container">
        <div class="row" style="margin-top: 120px;">
            
            <jsp:include page="./blogMain.jsp" />
            <jsp:include page="./blogRightSidebar.jsp" />
        </div>
    </div>
</section>

<jsp:include page="./blogFoot.jsp" />

<a href="#" id="back-to-top" style="display: none;">
	<i></i>
</a>


<script src="./../../static/js/bootstrap.min.js"></script>
<!-- <script src="./../../static/js/carousel/jquery.flexslider-min.js"></script> -->
<script src="./../../static/js/utils.js"></script>
<script>
    $(function(){
		$(window).scroll(function(){
			if ($(this).scrollTop() > 90) {
				
				$("#navbar-fixed-top").removeClass("closed");
				$('#back-to-top').fadeIn();
			} else {
			
				$('#back-to-top').fadeOut();
				$("#navbar-fixed-top").addClass("closed");
			}
		});
		
		$('#back-to-top').on('click', function(e){
			e.preventDefault();
			$('html, body').animate({scrollTop : 0},1000);
			return false;
		});
    });
</script>
</body>
</html>