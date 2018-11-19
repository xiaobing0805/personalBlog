<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="modal fade" id="updateUserPwdDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="display: block;">
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
                                <input notEmpty="不能为空" type="password" maxlength="32" class="form-control" name="password" placeholder="<spring:message code="content.input.confirm.password" />" />
                            </div>
                        </div>
                    </div>
                    <div style="clear:both"></div>
                </div>
                <div class="modal-footer">
                    <button disabled="disabled" type="button" class="btn btn-primary"><spring:message code="content.save" /></button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script type="text/javascript">
$("#updateUserPwdDialog").modal('show');

$("#updateUserPwdValidator").bootstrapValidator({
    container: 'tooltip',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    live: 'enabled',
    submitButtons: 'button[type="button"]',
    fields: {
        password: {
            validators: {
                notEmpty: {
                    message: "不能为空"
                },
                regexp: {
                    regexp: /^[a-zA-Z0-9_\.]+$/,
                    message: "长度不符合规则"
                },
                stringLength: {
                    min: 5,
                    max: 32,
                    message: "长度最小为5"
                }
            }
        }
    }
});
</script>