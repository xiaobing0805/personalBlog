<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Main content -->
<section class="content">
	<div class="col-md-4">
		<div class="info-box bg-green">
			<span class="info-box-icon">
				<i class="fa fa-users" style="line-height: 90px;"></i>
			</span>
			<div class="info-box-content">
				<span class="info-box-text">当前在线人数：<span id="currentOnline"></span></span> 
				<span class="info-box-text">总在线人数：<span id="totalOnline"></span></span> 
				<div class="progress">
					<div class="progress-bar" style="width: 100%"></div>
				</div>
				<span class="info-box-text"><i class="fa fa-bar-chart"></i> 实时数据统计</span>
			</div>
		</div>
	</div>
</section>

<script src="./../../static/js/user/home.js"></script>