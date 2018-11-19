<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<main class="col-md-8 main-content"> 
<!-- <div class="flexslider">
	<ul class="slides">
		<li style="background:url(./../../static/images/carousel/advertising.jpeg) 0% 0% no-repeat;"></li>
		<li style="background:url(./../../static/images/carousel/cooperation.jpeg) 0% 80% no-repeat;"></li>
	</ul>
</div> -->
<article id="97" class="post">
	<!-- style="margin-top: 20px;" -->
	<div class="featured" title="推荐文章">
		<i class="fa fa-thumbs-o-up iconRecommend"></i>
	</div>
	<div class="post-head">
		<h1 class="post-title">
			<a
				href="/ghost-zhuo-mian-ban-app-geng-xin-neng-gou-tong-shi-guan-li-duo-ge-ghost-bo-ke/">Ghost
				桌面版 APP 正式发布，能同时管理多个 Ghost 博客</a>
		</h1>
	</div>
	<div class="post-meta">
		<span class="author">博主：<a href="/author/wangsai/">林延涛</a></span> ·
		<time class="post-date" datetime="2016年4月28日星期四上午10点14分"
			title="2016年4月28日星期四上午10点14分">2016年4月28日</time>
	</div>
	<div class="post-content">
		<p>虽然通过浏览器管理 Ghost 博客虽然很方便，但是在多个 tab 之间切换有时候也会很麻烦，嗯，如果能有一个独立的 app
			应该使用起来会更爽一些！ 今天的主角来了，Ghost 桌面版 APP -- Ghost Desktop！ 目前，Ghost Desktop
			还处于早期阶段</p>
	</div>

	<footer class="post-footer clearfix">
		<!-- <div class="pull-left tag-list">
                            <i class="fa fa-folder-open-o"></i>
                        </div>
                        <div class="pull-right share">
                        	<i class="fa fa-share"></i>
                        	<i class="fa fa-share"></i>
                        </div> -->
		<div class="munt" style="text-align: center;">
			<div
				style="width: 25%; border-right: 1px solid #cccccc; font-size: 12px;">
				<i class="fa fa-folder-open-o"></i> 打赏
			</div>
			<div
				style="width: 25%; border-right: 1px solid #cccccc; font-size: 12px;">
				<i class="fa fa-folder-open-o"></i> 收藏
			</div>
			<div
				style="width: 25%; border-right: 1px solid #cccccc; font-size: 12px;">
				<i class="fa fa-folder-open-o"></i> 评论
			</div>
			<div style="width: 25%; font-size: 12px;">
				<i class="fa fa-folder-open-o"></i> 点赞
			</div>
		</div>
	</footer>
	<div style="margin-top: 20px;">
		<div style="float: left; width: 100%;">
			<div class="comment-left-portrait">
				<img src="./../../static/images/heard_img.png"
					style="width: 30px; height: 30px;" class="img-thumbnail"
					alt="User Image">
			</div>
			<div class="comment-right-content">
				<input
					style="width: 100%; height: 30px; padding: 5px; font-size: 12px; border: 1px solid #cccccc;" />
			</div>
		</div>
		<ul class="media-list" style="padding-top: 40px;">

			<li class="media" style="border-left: 5px solid #f45531;"><a
				class="pull-left" href="#"> <img style="border: 1px solid #f45531;"
					class="media-object img-circle"
					src="./../../static/images/logo.png" alt="通用的占位符图像">
			</a>
				<div class="media-body">
					<div style="width: 100%; float: left;">
						<div style="float: left;">
							<a style="">林延涛</a> <span style="color: #666;"></span>
						</div>
						<div style="float: right;">
							<span style="color: #666;">一个小时前</span>
						</div>
					</div>

					<div style="width: 100%; font-size: 12px; color: #2b2b2b;">
						千万不要因为走的太久，而忘记了当初为什么出发。</div>
					<div style="float: right;">
						<span> <i class="fa fa-thumbs-o-up iconRecommend"></i> <a
							href="#" style="color: #666;">点赞 <span
								style="font-size: 8px;">(888888)</span>
						</a>
						</span> <span style="padding-left: 12px;"> <i
							class="fa fa-folder-open-o"></i> <a href="#" style="color: #666;">回复</a>
						</span>
					</div>
				</div></li>

			<li class="media" style="border-left: 5px solid #f45531;"><a
				class="pull-left" href="#"> <img style="border: 1px solid;"
					class="media-object img-circle"
					src="./../../static/images/logo.png" alt="通用的占位符图像">
			</a>
				<div class="media-body">
					<div style="width: 100%; float: left;">
						<div style="float: left;">
							<a style="">张三</a> <span style="color: #666;">@回复 林延涛</span>
						</div>
						<div style="float: right;">
							<span style="color: #666;">一个小时前</span>
						</div>
					</div>

					<div style="width: 100%; font-size: 12px; color: #2b2b2b;">
						楼上情怀。。。</div>
					<div style="float: right;">
						<span> <i class="fa fa-thumbs-o-up iconRecommend"></i> <a
							href="#" style="color: #666;">点赞 <span
								style="font-size: 8px;">(66666)</span>
						</a>
						</span> <span style="padding-left: 12px;"> <i
							class="fa fa-folder-open-o"></i> <a href="#" style="color: #666;">回复</a>
						</span>
					</div>
				</div></li>

			<li class="media" style="border-left: 5px solid #f45531;"><a
				class="pull-left" href="#"> <img style="border: 1px solid;"
					class="media-object img-circle"
					src="./../../static/images/logo.png" alt="通用的占位符图像">
			</a>

				<div class="media-body">
					<div style="width: 100%; float: left;">
						<div style="float: left;">
							<a style="">林延涛</a> <span style="color: #666;">@回复 张三</span>
						</div>
						<div style="float: right;">
							<span style="color: #666;">一个小时前</span>
						</div>
					</div>

					<div style="width: 100%; font-size: 12px; color: #2b2b2b;">
						千万不要因为走的太久，而忘记了当初为什么出发。千万不要因为走的太久，而忘记了当初为什么出发。千万不要因为走的太久，而忘记了当初为什么出发。千万不要因为走的太久，而忘记了当初为什么出发。千万不要因为走的太久，而忘记了当初为什么出发。
					</div>
					<div style="float: right;">
						<a href="#" style="color: #666;">点赞</a> <a href="#"
							style="color: #666;">回复</a>
					</div>
					<ul class="media-list" style="padding-top: 20px;">
						<li class="media" style="border-left: 5px solid #f45531;"><a
							class="pull-left" href="#"> <img style="border: 1px solid;"
								class="media-object img-circle"
								src="./../../static/images/logo.png" alt="通用的占位符图像">
						</a>

							<div class="media-body">
								<div style="width: 100%; float: left;">
									<div style="float: left;">
										<a style="">林延涛</a> <span style="color: #666;">@回复 张三</span>
									</div>
									<div style="float: right;">
										<span style="color: #666;">一个小时前</span>
									</div>
								</div>

								<div style="width: 100%; font-size: 12px; color: #2b2b2b;">
									千万不要因为走的太久，而忘记了当初为什么出发。千万不要
								</div>
								<div style="float: right;">
									<a href="#" style="color: #666;">点赞</a> <a href="#"
										style="color: #666;">回复</a>
								</div>

							</div></li>
					</ul>
					<ul class="media-list" style="padding-top: 20px;">
						<li class="media" style="border-left: 5px solid #f45531;"><a
							class="pull-left" href="#"> <img style="border: 1px solid;"
								class="media-object img-circle"
								src="./../../static/images/logo.png" alt="通用的占位符图像">
						</a>

							<div class="media-body">
								<div style="width: 100%; float: left;">
									<div style="float: left;">
										<a style="">林延涛</a> <span style="color: #666;">@回复 张三</span>
									</div>
									<div style="float: right;">
										<span style="color: #666;">一个小时前</span>
									</div>
								</div>

								<div style="width: 100%; font-size: 12px; color: #2b2b2b;">
									千万不要因为走的太久，而忘记了当初为什么出发。千万不要因为走的太久，而忘记了当初为什么出发。千万不要因为走的太久，而忘记了当初为什么出发。千万不要因为走的太久，而忘为走的太久，而忘记了当初为什么出发。
								</div>
								<div style="float: right;">
									<a href="#" style="color: #666;">点赞</a> <a href="#"
										style="color: #666;">回复</a>
								</div>

							</div></li>
					</ul>
				</div></li>
		</ul>
	</div>
</article>


<nav class="pagination" role="navigation">
	<a class="newer-posts" href="/"><i class="fa fa-angle-left"></i></a> <span
		class="page-number">第 1 页 / 共 8 页</span> <a class="older-posts"
		href="/page/2/"><i class="fa fa-angle-right"></i></a>
</nav>

</main>
<script>
    $(function(){
		/* $('.flexslider').flexslider({
			directionNav: true,
			pauseOnAction: false
		}); */
    });
</script>