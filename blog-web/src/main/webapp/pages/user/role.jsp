<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
    <div class="col-md-12">
        <div class="table-responsive">  
            <div style="padding: 10px 0px 10px 0px;float: left;">
                <shiro:hasPermission name="role:add"> 
                    <button onclick="$.botton.openAddUserDialog();" type="button" class="btn btn-primary">
                        <i class="fa fa-user-plus"></i>
                        <spring:message code="content.add" />
                    </button>
                </shiro:hasPermission> 
                <shiro:hasPermission name="role:modify">
                    <button onclick="$.botton.openUpdateUserDialog();" type="button" class="btn btn-primary">
                        <i class="fa fa-pencil-square-o"></i>
                        <spring:message code="content.modify" />
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="role:delete">
                    <button onclick="$.botton.openDisableUserDialog();" type="button" class="btn btn-primary">
                        <i class="fa fa-user-times"></i>
                        <spring:message code="content.delete" />
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="role:assignUser">
                    <button onclick="$.botton.openForceLogoutUserDialog();" type="button" class="btn btn-primary">
                        <i class="fa fa-sign-out"></i>
                        <spring:message code="content.assign.user" />
                    </button>
                </shiro:hasPermission>
            </div>
            <shiro:hasPermission name="role:find">
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
            <table id="roleTable" class="table table-hover table-bordered">  
                <thead style="border-bottom: 3px solid #d2d6de;">  
                    <tr style="background: silver;">
                        <th data-field="checkbox" data-title='<input type="checkbox" class="flat-red">' style="text-align: center;"></th>
                        <th data-field="name" data-title="<spring:message code="content.role.name" />" data-formatter="$.formatter.forMatter"></th>
                        <th data-field="remarks" data-title="<spring:message code="content.role.remarks" />" data-formatter="$.formatter.forMatter"></th>
                        <th data-field="isDisable" data-title="<spring:message code="content.user.is.disable" />" data-formatter="$.formatter.isDisable"></th>
                        <jsp:include page="./../common/baseTable.jsp" />
                      </tr> 
                </thead>  
            </table>  
        </div>
    </div>
</div>
<c:if test="${currentLanguage  == 'zh_TW'}">
    <script src="./../../static/js/i18n/role_zh_TW.js"></script>
</c:if>
<c:if test="${currentLanguage  == 'en'}">
    <script src="./../../static/js/i18n/role_en.js"></script>
</c:if>
<c:if test="${currentLanguage  == 'zh_CN'}">
    <script src="./../../static/js/i18n/role_zh_CN.js"></script>
</c:if>
<script src="./../../static/js/user/role.js"></script>

