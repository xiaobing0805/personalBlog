<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<aside class="col-md-4 sidebar">

	<div class="widget">
		<h4 class="title">
			<i class="fa fa-list-ol"></i> <spring:message code="blog.read.list" />
		</h4>
		<div class="content community">
		      <div class="cssload-loader-container cssload-abs-center">   
				    <div class="cssload-olympicloader">
				        <i class="cssload-loader-circle cssload-first"></i>
				        <i class="cssload-loader-circle cssload-second"></i>
				        <i class="cssload-loader-circle cssload-third"></i>
				        <i class="cssload-loader-circle cssload-fourth"></i>
				        <i class="cssload-loader-circle cssload-fifth"></i>
				    </div>
				</div>
			<!-- <ul class="nav nav-pills nav-stacked">
				<li><a href="#"> <span class="badge pull-right">1537411</span>Mysql学习-Linux
						Mysql主从复制
				</a></li>
				<li><a href="#">CentOS学习-CentOS7重置root密码</a></li>
				<li><a href="#"> <span class="badge pull-right">158</span>Spring学习-回滚事务之修改默认回滚类型
				</a></li>
			</ul> -->
		</div>
	</div>
	<div class="widget">
		<h4 class="title">
			<i class="fa fa-book"></i> <spring:message code="blog.directory" />
		</h4>
		<div class="content community">
			<ul class="nav nav-pills nav-stacked">
				<li><a href="#">JAVA学习 (88888)
				</a></li>
				<li><a href="#">Linux学习 (5)</a></li>
				<li><a href="#">ReactNative学习 (12)
				</a></li>
			</ul>
		</div>
	</div>

	<div class="widget">
		<h4 class="title">
			<i class="fa fa-tags"></i> <spring:message code="blog.tag" />
		</h4>
		<div class="content tag-cloud">
			<a href="/tag/jquery/">jQuery</a> <a href="/tag/ghost-0-7-ban-ben/">Ghost
				0.7 版本</a> <a href="/tag/opensource/">开源</a> <a
				href="/tag/zhu-shou-han-shu/">助手函数</a> <a href="/tag/tag-cloud/">标签云</a>
			<a href="/tag/navigation/">导航</a> <a href="/tag/customize-page/">自定义页面</a>
			<a href="/tag/static-page/">静态页面</a> <a href="/tag/roon-io/">Roon.io</a>
			<a href="/tag/configuration/">配置文件</a> <a href="/tag/upyun/">又拍云存储</a>
			<a href="/tag/upload/">上传</a> <a href="/tag/handlebars/">Handlebars</a>
			<a href="/tag/email/">邮件</a> <a href="/tag/shortcut/">快捷键</a> <a
				href="/tag/yong-hu-zhi-nan/">用户指南</a> <a href="/tag/theme-market/">主题市场</a>
			<a href="/tag/release/">新版本发布</a> <a href="/tag-cloud/">...</a>
		</div>
	</div>
	<div class="widget">
		<h4 class="title">
			<i class="fa fa-comments-o"></i> <spring:message code="blog.latest.comments" />
		</h4>
		<div class="content community">
			<p>QQ群：123456789</p>
			<p>
				<a href="#"><i class="fa fa fa-weibo"></i>官方微博</a>
			</p>
		</div>
	</div>
</aside>