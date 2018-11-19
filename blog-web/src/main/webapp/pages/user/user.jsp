<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
    .input-group-addon{width: 150px;text-align: right;}
    .modal-content{width: 800px;}
    .modal-dialog{width: 800px;}
    .has-feedback .form-control{padding-right: 12px;}
    .input-group{width: 100%;}
</style>
<div class="row">
	<div class="col-md-12">
		<div class="table-responsive">  
			<div style="padding: 10px 0px 10px 0px;float: left;">
			    <shiro:hasPermission name="user:add"> 
	                <button onclick="$.botton.openAddUserDialog();" type="button" class="btn btn-primary">
	                    <i class="fa fa-user-plus"></i>
	                    <spring:message code="content.add" />
	                </button>
			    </shiro:hasPermission> 
			    <shiro:hasPermission name="user:modify">
	                <button onclick="$.botton.openUpdateUserDialog();" type="button" class="btn btn-primary">
	                    <i class="fa fa-pencil-square-o"></i>
	                    <spring:message code="content.modify" />
	                </button>
			    </shiro:hasPermission>
			    <shiro:hasPermission name="user:modifyPwd">
	                <button onclick="$.botton.openUpdateUserPwdDialog();" type="button" class="btn btn-primary">
	                    <i class="fa fa-pencil-square-o"></i>
	                    <spring:message code="content.modify.password" />
	                </button>
			    </shiro:hasPermission>
			    <shiro:hasPermission name="user:disable">
	                <button onclick="$.botton.openDisableUserDialog();" type="button" class="btn btn-primary">
	                    <i class="fa fa-user-times"></i>
	                    <spring:message code="content.disable" />
	                </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:enable">
	                <button onclick="$.botton.openEnableUserDialog();" type="button" class="btn btn-primary">
	                    <i class="fa fa-user-plus"></i>
	                    <spring:message code="content.enable" />
	                </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:offline">
	                <button onclick="$.botton.openForceLogoutUserDialog();" type="button" class="btn btn-primary">
	                    <i class="fa fa-sign-out"></i>
	                    <spring:message code="content.compulsory.down" />
	                </button>
                </shiro:hasPermission>
			</div>
			<shiro:hasPermission name="user:find">
	            <div class="box-tools" style="padding: 10px 0px 10px 0px;float: right;">
	               <div class="input-group input-group-sm" style="width: 200px;">
	                 <input type="text" name="table_search" class="form-control pull-right" placeholder="<spring:message code="content.user.search" />">
	                <!-- <a class="glyphicon glyphicon-remove btn form-control-feedback"style="pointer-events: auto;padding-right: 50px;"></a> -->
	                 <div class="input-group-btn">
	                   <button type="button" onclick="$.botton.search();" class="btn btn-primary"><i class="fa fa-search"></i></button>
	                 </div>
	               </div>
	            </div>
			</shiro:hasPermission>
		    <table id="userTable" class="table table-hover table-bordered">  
		        <thead style="border-bottom: 3px solid #d2d6de;">  
		            <tr style="background: silver;">
		            	<th data-field="checkbox" data-title='<input type="checkbox" class="flat-red">' style="text-align: center;"></th>
		                <th data-field="loginName" data-title="<spring:message code="content.user.login.name" />" data-formatter="$.formatter.forMatter"></th>
		                <th data-field="name" data-title="<spring:message code="content.user.username" />" data-formatter="$.formatter.forMatter"></th>
		                <th data-field="email" data-title="<spring:message code="content.user.email" />" data-formatter="$.formatter.forMatter"></th>
		                <th data-field="isDisable" data-title="<spring:message code="content.user.is.disable" />" data-formatter="$.formatter.isDisable"></th>
		                <th data-field="sessionId" data-title="<spring:message code="content.user.status" />" data-formatter="$.formatter.isOnline"></th>
		                <jsp:include page="./../common/baseTable.jsp" />
		                <!-- <th data-title="操作" data-formatter="$.formatter.testOption"></th> -->
		              </tr> 
		        </thead>  
		    </table>  
		</div>
	</div>
</div>

<div class="modal fade" id="addUserDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title">
					<spring:message code="content.add.user" />
				</h4>
			</div>
            <form id="addUserValidator" onsubmit="return false;" method="post" class="form-horizontal" action="">
				<div class="modal-body">
				    <div class="form-group col-lg-6">
                         <div class="input-group">
                             <span class="input-group-addon"><spring:message code="content.user.login.name" /> <span class="required-red">*</span></span>
                             <input type="text" maxlength="32" class="form-control" name="loginName" placeholder="<spring:message code="content.input.user.login.name" />" />
                         </div>
                    </div>
                    <div class="form-group col-lg-6">
                         <div class="input-group">
                             <span class="input-group-addon"><spring:message code="content.user.username" /> <span class="required-red">*</span></span>
                             <input type="text" maxlength="32" class="form-control" name="name" placeholder="<spring:message code="content.input.user.username" />" />
                         </div>
                    </div>
                    <div class="form-group col-lg-6">
                        <div class="input-group">
                            <span class="input-group-addon"><spring:message code="content.password" /> <span class="required-red">*</span></span>
                            <input type="password" maxlength="32" class="form-control" name="password" placeholder="<spring:message code="content.input.password" />" />
                        </div>
                    </div>
                    <div class="form-group col-lg-6">
                        <div class="input-group">
                            <span class="input-group-addon"><spring:message code="content.confirm.password" /> <span class="required-red">*</span></span>
                            <input type="password" maxlength="32" class="form-control" name="confirmPassword" placeholder="<spring:message code="content.input.confirm.password"/>" />
                        </div>
                    </div>
                    <div class="form-group col-lg-6">
                         <div class="input-group">
                             <span class="input-group-addon"><spring:message code="content.user.email" /> <span class="required-red">*</span></span>
                             <input type="text" maxlength="64" class="form-control" name="email" placeholder="<spring:message code="content.input.user.email"/>" />
                         </div>
                    </div>
                    <div style="clear:both"></div>
				</div>
				<div class="modal-footer">
					<button type="button" disabled="disabled" submit="true" onclick="$.botton.addUser(this);" class="btn btn-primary grebtn"><spring:message code="content.save" /></button>
					<button type="reset" onclick="$.botton.reset('addUserValidator');" class="btn btn-info"><spring:message code="content.reset" /></button>
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="content.close" /></button>
				</div>
            </form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<div class="modal fade" id="updateUserDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title">
					<spring:message code="content.modify.user" />
				</h4>
			</div>
            <form id="updateUserValidator" onsubmit="return false;" method="post" class="form-horizontal" action="">
            	<input type="text" style="display: none;" name="id"/>
				<div class="modal-body">
                    <div class="form-group col-lg-6">
                         <div class="input-group">
                             <span class="input-group-addon" ><spring:message code="content.user.login.name" /> <span class="required-red">*</span></span>
                             <input type="text" disabled="disabled" maxlength="32" class="form-control" name="updateloginName" placeholder="<spring:message code="content.input.user.login.name" />" />
                         </div>
                    </div>
                    <div class="form-group col-lg-6">
                         <div class="input-group">
                             <span class="input-group-addon" ><spring:message code="content.user.username" /> <span class="required-red">*</span></span>
                             <input type="text" maxlength="32" class="form-control" name="name" placeholder="<spring:message code="content.input.user.username" />" />
                         </div>
                    </div>
                    <div class="form-group col-lg-6">
                         <div class="input-group">
                             <span class="input-group-addon" ><spring:message code="content.user.email" /> <span class="required-red">*</span></span>
                             <input type="text" maxlength="64" class="form-control" name="email" placeholder="<spring:message code="content.input.user.email" />" />
                         </div>
                    </div>
                    <div style="clear:both"></div>
				</div>
				<div class="modal-footer">
					<button type="button" onclick="$.botton.updateUser(this);" class="btn btn-primary"><spring:message code="content.save" /></button>
					<button type="button" validatorReset="reset" onclick="$.botton.reset('updateUserValidator');$.botton.resetUpdate();" class="btn btn-info"><spring:message code="content.reset" /></button>
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="content.close" /></button>
				</div>
            </form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<div class="modal fade" id="updateUserPwdDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title">
					<spring:message code="content.modify.password" />
				</h4>
			</div>
            <form id="updateUserPwdValidator" onsubmit="return false;" method="post" class="form-horizontal" action="">
				<div class="modal-body">
                    <div class="form-group col-lg-12" id="checkedUser">
                    	
					</div>
					<div class="col-lg-12">
						<div class="form-group col-lg-6">
	                        <div class="input-group">
	                            <span class="input-group-addon" ><spring:message code="content.password" /> <span class="required-red">*</span></span>
	                            <input type="password" maxlength="32" class="form-control" name="password" placeholder="<spring:message code="content.input.confirm.password" />" />
	                        </div>
	                    </div>
	                    <div class="form-group col-lg-6">
	                        <div class="input-group">
	                            <span class="input-group-addon" ><spring:message code="content.confirm.password" /> <span class="required-red">*</span></span>
	                            <input type="password" maxlength="32" class="form-control" name="confirmPassword" placeholder="<spring:message code="content.input.confirm.password" />" />
	                        </div>
	                    </div>
                    </div>
                    <div style="clear:both"></div>
				</div>
				<div class="modal-footer">
					<button type="button" onclick="$.botton.updateUserPwd(this);" class="btn btn-primary"><spring:message code="content.save" /></button>
					<button type="button" validatorReset="reset" onclick="$.botton.reset('updateUserPwdValidator');" class="btn btn-info"><spring:message code="content.reset" /></button>
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="content.close" /></button>
				</div>
            </form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<div class="modal fade" id="disableUserDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title">
					<spring:message code="content.disable.user"/>
				</h4>
			</div>
            <form id="disableUserValidator" onsubmit="return false;" method="post" class="form-horizontal" action="">
				<div class="modal-body">
                    <div class="form-group col-lg-12" id="disableUser">
                    	
					</div>
					<div style="clear:both"></div>
				</div>
				<div class="modal-footer">
					<button type="button" onclick="$.botton.disableUser(this);" class="btn btn-primary"><spring:message code="content.disable"/></button>
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="content.close"/></button>
				</div>
            </form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<div class="modal fade" id="enableUserDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title">
					<spring:message code="content.enable.user"/>
				</h4>
			</div>
            <form id="enableUserValidator" onsubmit="return false;" method="post" class="form-horizontal" action="">
				<div class="modal-body">
                    <div class="form-group col-lg-12" id="enableUser">
                    	
					</div>
					<div style="clear:both"></div>
				</div>
				<div class="modal-footer">
					<button type="button" onclick="$.botton.enableUser(this);" class="btn btn-primary"><spring:message code="content.enable"/></button>
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="content.close"/></button>
				</div>
            </form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<div class="modal fade" id="forcelogoutUserDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title">
					<spring:message code="content.Force.user.offline"/>
				</h4>
			</div>
            <form id="forcelogoutUserValidator" onsubmit="return false;" method="post" class="form-horizontal" action="">
				<div class="modal-body">
                    <div class="form-group col-lg-12" id="forcelogoutUser">
                    	
					</div>
					<div style="clear:both"></div>
				</div>
				<div class="modal-footer">
					<button type="button" onclick="$.botton.forceLogoutUser(this);" class="btn btn-primary"><spring:message code="content.offline"/></button>
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="content.close"/></button>
				</div>
            </form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<c:if test="${currentLanguage  == 'zh_TW'}">
    <script src="./../../static/js/i18n/user_zh_TW.js"></script>
</c:if>
<c:if test="${currentLanguage  == 'en'}">
    <script src="./../../static/js/i18n/user_en.js"></script>
</c:if>
<c:if test="${currentLanguage  == 'zh_CN'}">
    <script src="./../../static/js/i18n/user_zh_CN.js"></script>
</c:if>
<script src="./../../static/js/user/user.js"></script>
