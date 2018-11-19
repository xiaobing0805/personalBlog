<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><spring:message code="blog.operation.platform" /> <spring:message code="title.suffix" /></title>
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./../../static/images/logo_heard.ico" rel="shortcut icon">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="./../../static/css/AdminLTE.min.css">
    <link rel="stylesheet" href="./../../static/js/learun/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="./../../static/css/learun/font-awesome.min.css">
    <link rel="stylesheet" href="./../../static/css/learun/index.css">
    <link rel="stylesheet" href="./../../static/css/five.css">
    <link rel="stylesheet" href="./../../static/css/icheck/all.css">
    <link rel="stylesheet" href="./../../static/css/bootstrapValidator.min.css">
    <link rel="stylesheet" href="./../../static/css/learun/skins/_all-skins.css">
    <link rel="stylesheet" href="./../../static/css/animate.min.css">
    <script src="./../../static/js/learun/jquery/jQuery-2.2.0.min.js"></script>
</head>

<body class="hold-transition skin-blue sidebar-mini" style="overflow:hidden;">
	<input id="sidebarToggle" value="${sidebarToggle}" style="display: none;"/>
    <div class="wrapper">
        <!--头部信息-->
        <header class="main-header">
            <a href="/" target="_blank" class="logo">
                <span class="logo-mini">YT</span>
                <span class="logo-lg">
                    <!-- <strong>DEMO</strong> -->
                    <div style="margin-top: 5px;" class="cssload-loader-container cssload-abs-center">   
	                    <div class="cssload-olympicloader">
	                        <i class="cssload-loader-circle cssload-first"></i>
	                        <i class="cssload-loader-circle cssload-second"></i>
	                        <i class="cssload-loader-circle cssload-third"></i>
	                        <i class="cssload-loader-circle cssload-fourth"></i>
	                        <i class="cssload-loader-circle cssload-fifth"></i>
	                    </div>
                    </div>
                </span>
            </a>
            <nav class="navbar navbar-static-top">
                <a class="sidebar-toggle">
                    <span class="sr-only">Toggle navigation</span>
                </a>
                <div class="navbar-custom-menu">
                    <ul class="nav navbar-nav">
                        <%-- <li class="dropdown messages-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                               🇨🇳　<spring:message code="basic.purpose" />
                            </a>
                        </li> --%>
                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <img src="./../../static/images/learun/user2-160x160.jpg" class="user-image" alt="User Image">
                                <span class="hidden-xs"><shiro:principal></shiro:principal></span>
                            </a>
                            <!-- <ul class="dropdown-menu pull-right">
                                <li><a class="menuItem" data-id="userInfo" href="/SystemManage/User/Info"><i class="fa fa-user"></i>个人信息</a></li>
                                <li><a href="javascript:void();"><i class="fa fa-trash-o"></i>清空缓存</a></li>
                                <li><a href="javascript:void();"><i class="fa fa-paint-brush"></i>皮肤设置</a></li>
                                <li class="divider"></li>
                                <li><a href="~/Login/OutLogin"><i class="ace-icon fa fa-power-off"></i>安全退出</a></li>
                            </ul> -->
                        </li>
                        <li class="dropdown messages-menu">
                            <a href="javascript:$.logout();" class="dropdown-toggle">
                               <i class="ace-icon fa fa-power-off"></i>
                               <spring:message code="blog.logout" />
                            </a>
                        </li>
                        <jsp:include page="./public/swichLanguage.jsp" />
                    </ul>
                </div>
            </nav>
        </header>
        <!--左边导航-->
        <div class="main-sidebar">
            <div class="sidebar">
                <!-- <div class="user-panel">
                    <div class="pull-left image">
                        <img src="./../../static/images/learun/user2-160x160.jpg" class="img-circle" alt="User Image">
                    </div>
                    <div class="pull-left info">
                        <p>administrator</p>
                        <a><i class="fa fa-circle text-success"></i>在线</a>
                    </div>
                </div> -->
                <!-- <form action="#" method="get" class="sidebar-form">
                    <div class="input-group">
                        <input type="text" name="q" class="form-control" placeholder="Search...">
                        <span class="input-group-btn">
                            <a class="btn btn-flat"><i class="fa fa-search"></i></a>
                        </span>
                    </div>
                </form> -->
                <ul class="sidebar-menu" id="sidebar-menu">
                    
	            </ul>
            </div>
        </div>
        <!--中间内容-->
        <div id="content-wrapper" class="content-wrapper">
            <div class="content-tabs">
                <button class="roll-nav roll-left tabLeft">
                    <i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs menuTabs">
                    <div class="page-tabs-content" style="margin-left: 0px;">
                        <a href="javascript:;" class="menuTab active" data-id="/home"><spring:message code="basics.home" /> </a>
                    </div>
                </nav>
                <button class="roll-nav roll-right tabRight">
                    <i class="fa fa-forward" style="margin-left: 3px;"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown tabClose" data-toggle="dropdown">
                        <spring:message code="basics.tab.operation" /><i class="fa fa-caret-down" style="padding-left: 3px;"></i>
                    </button>
                    <ul class="dropdown-menu dropdown-menu-right">
                        <li><a class="tabReload" href="javascript:void(0);"><spring:message code="basics.refresh.current" /></a></li>
                        <li><a class="tabCloseCurrent" href="javascript:void(0);"><spring:message code="basics.close.current" /></a></li>
                        <li><a class="tabCloseAll" href="javascript:void(0);"><spring:message code="basics.close.all" /></a></li>
                        <li><a class="tabCloseOther" href="javascript:void(0);"><spring:message code="basics.in.addition.close.all" /></a></li>
                    </ul>
                </div>
                <button class="roll-nav roll-right fullscreen"><i class="fa fa-arrows-alt"></i></button>
            </div>
            <div class="content-iframe" style="overflow: hidden;">
                <div style="margin: 10px; margin-bottom: 0px; padding: 0;">
                   	<div class="mainContent col-md-12 box box-primary" id="content-main" style="overflow-y:auto;overflow-x:auto; width:100%; height:100%;background: #ffffff;">
                    	<div class="LRADMS_iframe" id="defaltHome" data-id="/home" style="height: 100px;">
                    		<div class="alert alert-success">
							    <a href="#" class="close" data-dismiss="alert">&times;</a>
							   向世界分享你的 <strong>经验</strong>。
							</div>
                    	</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="./../../static/js/learun/bootstrap/js/bootstrap.min.js"></script>
    <script src="./../../static/js/bootstrap-notify.min.js"></script>
    <script src="./../../static/js/bootstrapValidator.js"></script>
    <script src="./../../static/js/bootstrap-dialog.min.js"></script>
    <script src="./../../static/js//icheck/icheck.min.js"></script>
    <script src="./../../static/js/utils.js"></script>
    <script src="./../../static/js/index.js"></script>
	<script src="./../../static/js/youngtao.js"></script>
	<c:if test="${currentLanguage  == 'zh_TW'}">
        <script src="./../../static/js/local/youngtao-zh_TW.js"></script>
        <script src="./../../static/js/i18n/basics_zh_TW.js">alert("en");</script>
	</c:if>
	<c:if test="${currentLanguage  == 'en'}">
        <script src="./../../static/js/local/youngtao-en.js"></script>
        <script src="./../../static/js/i18n/basics_en.js"></script>
    </c:if>
    <c:if test="${currentLanguage  == 'zh_CN'}">
        <script src="./../../static/js/i18n/basics_zh_CN.js"></script>
    </c:if>
</body>
</html>