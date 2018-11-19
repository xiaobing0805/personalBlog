<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="static_header" style="font-size: 15px;width: 100%;position: fixed;z-index: 1;">

    <nav id="navbar-static-top" class="navbar navbar-default navbar-static-top orange-font" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle orange-frame" data-toggle="collapse"
                    data-target="#example-navbar-collapse">
                <span class="icon-bar orange-frame"></span>
                <span class="icon-bar orange-frame"></span>
                <span class="icon-bar orange-frame"></span>
            </button>
            <a style="width:50;height:50;" class="navbar-logo" href="#"></a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="nav_search">
                    <i id="icon_search" class="fa fa-search"></i>&nbsp;
                    <input class="hide" id="content_search" type="text" placeholder=" <spring:message code="blog.search"/> " style="display: inline-block;">
                    <i id="clear_content" class="fa fa-times-circle-o hide"></i>
                </li>
                <li><a href="#" class="selected"><i class="fa fa-book" ></i> <spring:message code="blog" /></a></li>
                <li><a href="#"><i class="fa fa-road" ></i> <spring:message code="blog.about" /></a></li>
                <li><a href="#"><i class="fa fa-pencil-square-o"></i> 写博客</a></li>
                <shiro:guest>  
                	<li><a href="/redirect/login"><i class="fa fa-github-alt"></i> <spring:message code="blog.signin" /></a></li>
				</shiro:guest>
				<shiro:user> 
					<li class="dropdown user user-menu">
		                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
		                  <img style="width: 30px;height: 30px;border: 1px solid;" src="./../../static/images/heard_img.png" class="user-image" alt="用户图片"><shiro:principal/> 
		                </a>
		                <ul class="dropdown-menu">
		                  <!-- User image -->
		                  <li class="user-header">
		                    <p>
		                      	 🇨🇳　<spring:message code="basic.purpose" />
		                    </p>
		                  </li>
		                  <!-- Menu Footer-->
		                  <li class="user-footer">
		                  	<div class="pull-left">
		                      <a href="#" class="btn btn-flat btn-sign-out"><i class="fa fa-cogs"></i> <spring:message code="blog.edit.data" /></a>
		                    </div>
		                    <div class="pull-right">
		                      <a href="/logout" class="btn btn-flat btn-sign-out"><i class="fa fa-sign-out"></i> <spring:message code="blog.logout" /></a>
		                    </div>
		                  </li>
		                </ul>
		              </li>
				</shiro:user>
				<jsp:include page="./swichLanguage.jsp" />
                <li>　</li>
            </ul>
        </div>
    </nav>
</div>

<script>
    $(function(){
        $("#icon_search").click(function(){

            $("#clear_content").removeClass("hide");
            $("#content_search").removeClass("hide");
            $("#content_search").focus();
        });

        $("#clear_content").click(function(){

            $("#clear_content").addClass("hide");
            $("#content_search").addClass("hide");
        });
		
		$("#icon_scroll_search").click(function(){
			
			$("#clear_scroll_content").removeClass("hide");
            $("#content_scroll_search").removeClass("hide");
            $("#content_scroll_search").focus();
		});
		
		$("#clear_scroll_content").click(function(){

            $("#clear_scroll_content").addClass("hide");
            $("#content_scroll_search").addClass("hide");
        });
    });
</script>