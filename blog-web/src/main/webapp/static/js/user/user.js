$(function() {
	$.utils.validator('#addUserValidator',
	{
    	loginName: {
            validators: {
                notEmpty: {
                    message: $.i18n["user.login.name.not.null"]
                },
                regexp: {
                    regexp: /^[a-zA-Z0-9_\.]+$/,
                    message: $.i18n["user.login.name.format.error"]
                },
                stringLength: {
                    min: 6,
                    max: 32,
                    message: $.i18n["user.login.name.median.error"]
                }
            }
        },
        name: {
            validators: {
                notEmpty: {
                    message: $.i18n["user.name.not.null"]
                },
                stringLength: {
                    min: 1,
                    max: 32,
                    message: $.i18n["user.name.median.error"]
                }
            }
        },
        email: {
        	validators: {
                notEmpty: {
                    message: $.i18n["user.email.not.null"]
                },
                emailAddress: {
                    message: $.i18n["user.email.format.error"]
                }
            }
        },
        password: {
            validators: {
                notEmpty: {
                    message: $.i18n["user.password.not.null"]
                }
//                different: {
//                    field: 'loginName',
//                    message: '密码不能和用户名相同'
//                }
            }
        },
        confirmPassword: {
            validators: {
                notEmpty: {
                    message: $.i18n["user.confirm.password.not.null"]
                },
                identical: {
                    field: 'password',
                    message: $.i18n["user.two.passwords.do.not.agree"]
                }
            }
        }
    });
	
	$('#updateUserValidator').bootstrapValidator({
        container: 'tooltip',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                	 notEmpty: {
                         message: $.i18n["user.name.not.null"]
                     },
                     stringLength: {
                         min: 1,
                         max: 32,
                         message: $.i18n["user.name.median.error"]
                     }
                }
            },
            email: {
            	notEmpty: {
                    message: $.i18n["user.email.not.null"]
                },
                emailAddress: {
                    message: $.i18n["user.email.format.error"]
                }
            }
        }
    });
	$('#updateUserPwdValidator').bootstrapValidator({
        container: 'tooltip',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            password: {
                validators: {
                	notEmpty: {
                        message: $.i18n["user.password.not.null"]
                    }
                }
            },
            confirmPassword: {
                validators: {
                	notEmpty: {
                        message: $.i18n["user.confirm.password.not.null"]
                    },
                    identical: {
                        field: 'password',
                        message: $.i18n["user.two.passwords.do.not.agree"]
                    }
                }
            }
        }
    });
});
$.extend({
	botton : {
		search : function(){
			var tableSearch = $("input[name=table_search]").val();
			$("#userTable").youngTao('refreshOptions',{queryParams : function(params){
				return{
					pageSize : params.pageSize,
					currentPage : 1,
					loginName : tableSearch
				};
			}
		});
		},
		openAddUserDialog : function(){
			$("#addUserDialog").modal('show');
		},
		addUser : function(even){
			
			$("#addUserValidator").data('bootstrapValidator').validate();
			if($("#addUserValidator").data('bootstrapValidator').isValid()){
				var submitValue = $.utils.submitPrompt(even);
				$.utils.ajax({
					url : "/user/addUser",
					data : $("#addUserValidator").serializeObject(),
					success : function(data){
						alert($.i18n["basics.save.success"]);
						$("#addUserDialog").modal('hide');
						$("#addUserValidator button[type=reset]").click();
						/** 对table参数进行更新，or 刷新数据 */
						$("#userTable").youngTao('refreshOptions',{queryParams : function(params){
								return{
									pageSize : params.pageSize,
									currentPage : params.currentPage
								};
							}
						});
					},
					done : function(){
						$.utils.submitDone(even,submitValue);
					}
				});
			}
		},
		reset : function(id){
			$("#" + id).bootstrapValidator('resetForm', true);
		},
		resetUpdate : function(){
			$('#updateUserDialog input[name="name"]').val('');
			$('#updateUserDialog input[name="email"]').val('');
		},
		openUpdateUserDialog : function(){
			var selectColumn = $.utils.isSelectOne('userTable');
			if(!selectColumn) return false;
			this.fillUpdateUser(selectColumn);
		},
		fillUpdateUser : function(row){
			$('#updateUserDialog input[name="updateloginName"]').val(row.loginName);
			$('#updateUserDialog input[name="name"]').val(row.name);
			$('#updateUserDialog input[name="email"]').val(row.email);
			$('#updateUserDialog input[name="id"]').val(row.id);
			$("#updateUserDialog").modal('show');
		},
		updateUser : function(even){
			$("#updateUserValidator").data('bootstrapValidator').validate();
			if($("#updateUserValidator").data('bootstrapValidator').isValid()){
				var submitValue = $.utils.submitPrompt(even);
				$.utils.ajax({
					url : "/user/updateUser",
					data : $("#updateUserValidator").serializeObject(),
					success : function(data){
						alert($.i18n["basics.modify.success"]);
						$("#updateUserDialog").modal('hide');
						$("#updateUserValidator button[validatorReset=reset]").click();
						$("#userTable").youngTao('refreshOptions',{queryParams : function(params){
								return{
									pageSize : params.pageSize,
									currentPage : params.currentPage
								};
							}
						});
					},
					done : function(){
						$.utils.submitDone(even,submitValue);
					}
				});
			}
		},
		updateUserPwd : function(even){
			$("#updateUserPwdValidator").data('bootstrapValidator').validate();
			if($("#updateUserPwdValidator").data('bootstrapValidator').isValid()){
				var removeUserCount = $("#checkedUser").children(".form-group").length;
				var data = $("#updateUserPwdValidator").serializeObject();
				if(removeUserCount <= 1){
					var loginNames = $("#checkedUser").children(".form-group").find("input[name=loginNames]").val();
					data = $.extend(data,{"loginNames" : [loginNames]});
				}
				var submitValue = $.utils.submitPrompt(even);
				$.utils.ajax({
					url : "/user/updateUserPwd",
					data : data,
					success : function(data){
						alert($.i18n["basics.modify.success"]);
						$("#updateUserPwdDialog").modal('hide');
						$("#updateUserPwdValidator button[validatorReset=reset]").click();
					},
					done : function(){
						$.utils.submitDone(even,submitValue);
					}
				});
			}
		},
		openUpdateUserPwdDialog : function(){
			var loadResult = $.utils.loadSelectUser("userTable","checkedUser");
			if(loadResult == false) return false;
			$("#updateUserPwdDialog").modal('show');
		},
		openDisableUserDialog : function(){
			var loadResult = $.utils.loadSelectUser("userTable","disableUser");
			if(loadResult == false) return false;
			$("#disableUserDialog").modal('show');
		},
		disableUser : function(even){
			var removeUserCount = $("#disableUser").children(".form-group").length;
			var data = $("#disableUserValidator").serializeObject();
			if(removeUserCount <= 1){
				var loginNames = $("#disableUser").children(".form-group").find("input[name=loginNames]").val();
				data = $.extend(data,{"loginNames" : [loginNames]});
			}
			var submitValue = $.utils.submitPrompt(even);
			$.utils.ajax({
				url : "/user/disableUser",
				data : data,
				success : function(data){
					alert($.i18n["basics.disable.success"]);
					$("#disableUserDialog").modal('hide');
					$("#userTable").youngTao('refreshOptions',{queryParams : function(params){
						return{
							pageSize : params.pageSize,
							currentPage : params.currentPage
						};
					}
				});
				},
				done : function(){
					$.utils.submitDone(even,submitValue);
				}
			});
		},
		openEnableUserDialog : function(){
			var loadResult = $.utils.loadSelectUser("userTable","enableUser");
			if(loadResult == false) return false;
			$("#enableUserDialog").modal('show');
		},
		enableUser : function(even){
			var removeUserCount = $("#enableUser").children(".form-group").length;
			var data = $("#enableUserValidator").serializeObject();
			if(removeUserCount <= 1){
				var loginNames = $("#enableUser").children(".form-group").find("input[name=loginNames]").val();
				data = $.extend(data,{"loginNames" : [loginNames]});
			}
			var submitValue = $.utils.submitPrompt(even);
			$.utils.ajax({
				url : "/user/enableUser",
				data : data,
				success : function(data){
					alert($.i18n["basics.enable.success"]);
					$("#enableUserDialog").modal('hide');
					$("#userTable").youngTao('refreshOptions',{queryParams : function(params){
						return{
							pageSize : params.pageSize,
							currentPage : params.currentPage
						};
					}
				});
				},
				done : function(){
					$.utils.submitDone(even,submitValue);
				}
			});
		},
		openForceLogoutUserDialog : function(){
			var loadResult = $.utils.loadSelectUser("userTable","forcelogoutUser");
			if(loadResult == false) return false;
			$("#forcelogoutUserDialog").modal('show');
		},
		forceLogoutUser : function(even){
			var removeUserCount = $("#forcelogoutUser").children(".form-group").length;
			var data = $("#forcelogoutUserValidator").serializeObject();
			if(removeUserCount <= 1){
				var loginNames = $("#forcelogoutUser").children(".form-group").find("input[name=sessionId]").val();
				data = $.extend(data,{"sessionId" : [loginNames]});
			}
			var submitValue = $.utils.submitPrompt(even);
			$.utils.ajax({
				url : "/user/forceLogout",
				data : data,
				success : function(data){
					$.botton.updateSessionId();
				},
				done : function(){
					$.utils.submitDone(even,submitValue);
				}
			});
		},
		updateSessionId : function(){
			
			var removeUserCount = $("#forcelogoutUser").children(".form-group").length;
			var data = $("#forcelogoutUserValidator").serializeObject();
			if(removeUserCount <= 1){
				var loginNames = $("#forcelogoutUser").children(".form-group").find("input[name=loginNames]").val();
				data = $.extend(data,{"loginNames" : [loginNames]});
			}
			$.utils.ajax({
				url : "/user/updateSessionId",
				data : data,
				success : function(data){
					alert($.i18n["basics.offline.success"]);
					$("#forcelogoutUserDialog").modal('hide');
					$("#userTable").youngTao('refreshOptions',{queryParams : function(params){
							return{
								pageSize : params.pageSize,
								currentPage : params.currentPage
							};
						}
					});
				}
			});
		}
	},
	formatter : $.extend($.formatter,{
		isOnline : function(value,row,i){
			if(value){
				return '<span style="color:green">'+ $.i18n["user.online"] +'</span>';
			}else{
				return '<span style="color:red">'+ $.i18n["user.offline"] +'</span>';
			}
		}
//		}
	})
});
$("#userTable").youngTao({
	url : '/user/findUserByPage',
	locale : $("#currentLanguage").val(),
	responseHandler: function (res) {
        return res;
    },
    loadCompleteExe : function(that){
    	$.utils.complateIcheck(that);
    },
    onDblclickEven : function(row){
    	$.botton.fillUpdateUser(row);
    }
});
