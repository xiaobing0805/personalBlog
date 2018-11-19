
'use strict';
$.extend({
	utils : {
		DEFAULTS : {
			timeout : 10000,
			type : 'POST',
			async : true,
			cache : false,
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			dataType : 'JSON',
			defaultErrorMsg : 'Server exception, please try again later',
			notifyTimer : 3000
		},
		/**
		 * Call Ajax before the destruction of the request, please add the following statement
		 * if (this._xhr && this._xhr.readyState !== 4) {
         *   this._xhr.abort();
         * }
		 */
		ajax : function(request){
			var ajax = $.extend({},this.DEFAULTS,request);
			var _xhr = $.ajax({
				type: ajax.type,
				url: ajax.url ,
				timeout : ajax.timeout,
				async : ajax.async,
				cache : ajax.cache,
				contentType : ajax.contentType, 
				data: ajax.data ,
				dataType: ajax.dataType,
				success: function(data){
					if(ajax.dataType === "JSON"){
						if(data.successFul){
							if(ajax.success && typeof ajax.success === 'function'){
								ajax.success(data.data);
							}
						}else{
							alertDanger(data.errorMessage);
							if(ajax.fail && typeof ajax.fail === 'function'){
								ajax.fail(data);
							}
						}
					}else{
						if(ajax.success && typeof ajax.success === 'function'){
							ajax.success(data);
						}
					}
				} ,
				error : function(jqXHR, textStatus, errorThrown){
					if(textStatus === "timeout"){
						alertInfo($.i18n["basics.server.busy"]);
					}else if(textStatus === "parsererror"){
						$.utils.sessionTimeout();
					}else if(jqXHR.status == "401"){
						$.utils.sessionTimeout();
					}else if(jqXHR.status == "404"){
						alertWarning($.i18n["basics.page.not.exits"]);
					}else if(jqXHR.status == "408"){
						$.utils.kickOut();
					}else if(jqXHR.status == "409"){
						$.utils.forceLogout();
					}
						
					if(ajax.error && typeof ajax.error === 'function'){
						ajax.error(jqXHR, textStatus, errorThrown);
					}
				},
				complete : function(){
					if(ajax.done && typeof ajax.done === 'function'){
						ajax.done();
					}
				}
			});
			return _xhr;
		},
		sessionTimeout : function(){
			BootstrapDialog.alert({
				title : $.i18n["basics.prompt"],
				message : $.i18n["basics.session.timeout"],
				buttonLabel : $.i18n["basics.login"],
				callback : function(){
					window.location.reload();
				}
			});
		},
		kickOut : function(){
			BootstrapDialog.alert({
		        title: $.i18n["basics.prompt"],
		        message: $.i18n["basics.multiple.logon"],
		        backdrop: 'static', 
		        buttonLabel : $.i18n["basics.login"],
		        closable: false,
		        callback : function(){
					window.location.reload();
				},
		        buttons: [{
		            label: $.i18n["basics.modify.password"],
		            action: function(dialog) {
		                dialog.setTitle('Title 1');
		            }
		        }]
		    });
		},
		forceLogout : function(){
			BootstrapDialog.alert({
		        title: $.i18n["basics.prompt"],
		        message: $.i18n["basics.kick.out"],
		        backdrop: 'static', 
		        buttonLabel : $.i18n["basics.contact.admini"],
		        closable: false,
		        callback : function(){
					window.location.reload();
				}
		    });
		},
		switchLanguage : function(lang){
			this.ajax({
				type : "GET",
				url : "/common/lang",
				data : {lang : lang},
				success : function(data){
					if(data){
						window.location.reload();
					}else{
						alert($.i18n["basics.swich.language.failed"]);
					}
				}
			});
		},
		loading : function(id){
			var load = [];
			load.push('<div class="cssload-loader-container cssload-abs-center" style="margin-top:20px;">');
			load.push('		<div class="cssload-olympicloader">');
			load.push('			<i class="cssload-loader-circle cssload-first"></i>');
			load.push('			<i class="cssload-loader-circle cssload-second"></i>');
			load.push('			<i class="cssload-loader-circle cssload-third"></i>');
			load.push('			<i class="cssload-loader-circle cssload-fourth"></i>');
			load.push('			<i class="cssload-loader-circle cssload-fifth"></i>');
			load.push('		</div>');
			load.push('</div>');
			$(id).html(load.join(''));
		},
		isSelectOne : function(id){
			/** 获取选中内容,返回对象数组 */
			var getSelectedList = $("#" + id).youngTao("getSelectedList");
			if(getSelectedList.length <= 0){
				alertWarning($.i18n["basics.option.greater.than.1"]);
				return false;
			}else if(getSelectedList.length > 1){
				alertWarning($.i18n["basics.only.one"]);
				return false;
			}
			return getSelectedList[0];
		},
		submitPrompt : function(even){
			var submitValue = $(even).html();
			$(even).attr("disabled",true);
			$(even).html($.i18n["basics.operation.prompt"]);
			return submitValue;
		},
		submitDone : function(even,submitValue){
			$(even).attr("disabled",false);
			$(even).html(submitValue);
		},
		loadSelectUser : function(tableId,containerId){
			var selectColumn = $("#" + tableId).youngTao("getSelectedList");
			if(selectColumn <= 0){
				alertWarning($.i18n["basics.option.greater.than.1"]);
				return false;
			}
			$("#" + containerId).html('');
			$.each(selectColumn,function(i,el){
				var checkedUser = [];
				checkedUser.push('<div class="form-group col-lg-3">');
				checkedUser.push('	<div class="box box-info" style="border-top: 0px;border: 1px solid #d2d6de;margin-bottom: 5px;">');
				checkedUser.push('		<div class="box-header with-border" style="padding: 0px;">');
				checkedUser.push('			<div class="box-tools pull-right" style="right: 0px;top: 0px;">');
				checkedUser.push('				<button style="border-radius: 0px;background-color: #337ab7;" type="button" class="btn btn-box-tool removeUser" data-widget="remove">');
				checkedUser.push('					<i style="color: #FFFFFF;" class="fa fa-times"></i>');
				checkedUser.push('				</button>');
				checkedUser.push('			</div>');
				checkedUser.push('		</div>');
				checkedUser.push('		<input type="text" value="'+ el.sessionId +'" style="display: none;" name="sessionId"/>');
				checkedUser.push('		<input type="text" value="'+ el.loginName +'" style="display: none;" name="loginNames"/>');
				checkedUser.push('		<div class="box-body" style="padding: 4px 12px;">');
				checkedUser.push(			el.name);
				checkedUser.push('		</div>');
				checkedUser.push('	</div>');
				checkedUser.push('</div>');
				$(checkedUser.join('')).appendTo($("#" + containerId));
			});
			this.initRemoveUserEven(containerId);
		},
		initRemoveUserEven : function(containerId){
			var id = "#" + containerId;
			$(id + " .removeUser").click(function(){
				var removeUserCount = $(id).children(".form-group").length;
				if(removeUserCount > 1){
					$(this).parent().parent().parent().parent().remove();
				}else{
					alertWarning($.i18n["basics.greater.than.1"]);
				}
			});
		},
		complateIcheck : function(that){
			
			that.$el.find('input[type="checkbox"]').iCheck({
	    		checkboxClass: 'icheckbox_flat-blue'
	        });
	    	
			var checkAll = that.$el.find('>thead th input[type="checkbox"]');
			var tbodyTr = that.$el.find('>tbody tr');
	    	checkAll.iCheck('uncheck');
			checkAll.on('ifChecked ifUnchecked',function(event){
				if (event.type == 'ifChecked') {
					tbodyTr.find('input[type="checkbox"]').iCheck('check');
				} else {
					tbodyTr.find('input[type="checkbox"]').iCheck('uncheck');
				}
			});
			tbodyTr.find('input[type="checkbox"]').on('ifChecked ifUnchecked',function(){
				$.utils.clickIcheck(that,checkAll);
			});
			tbodyTr.on('click',function(){
				if(!$(this).find('input[type="checkbox"]').is(':checked')){
	    			$(this).find('input[type="checkbox"]').iCheck('check');
				}else{
					$(this).find('input[type="checkbox"]').iCheck('uncheck');
				}
				$.utils.clickIcheck(that,checkAll);
			});
			tbodyTr.on('ifChecked ifUnchecked',function(event){
				if (event.type == 'ifChecked') {
					$(this).find("td").css("background-color","#F5FAE2");
				} else {
					$(this).find("td").css("background-color","transparent");
				}
			});
		},
		clickIcheck : function(that,checkAll){
			var checkboxes = that.$el.find('>tbody tr input[type="checkbox"]');
			if(checkboxes.filter(':checked').length == checkboxes.length) {
				checkAll.prop('checked', true);
			} else {
				checkAll.prop('checked', false);
			}
			checkAll.iCheck('update');
		},
		validator: function(fromId,fields){
			$(fromId).bootstrapValidator({
		        container: 'tooltip',
		        live: 'enabled',
		        submitButtons: 'button[submit="true"]',
		        feedbackIcons: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: fields
		    });
		},
		startFive : function(){
			$(".logo-lg .cssload-first").css("border-color","");
			$(".logo-lg .cssload-second").css("border-color","");
			$(".logo-lg .cssload-third").css("border-color","");
			$(".logo-lg .cssload-fourth").css("border-color","");
			$(".logo-lg .cssload-fifth").css("border-color","");
		},
		stopFive : function(){
			$(".logo-lg .cssload-first").css("border-color","rgb(0,106,176)");
			$(".logo-lg .cssload-second").css("border-color","rgb(29,24,21)");
			$(".logo-lg .cssload-third").css("border-color","rgb(5,147,64)");
			$(".logo-lg .cssload-fourth").css("border-color","rgb(239,167,13)");
			$(".logo-lg .cssload-fifth").css("border-color","rgb(220,47,31)");
		}
	},
	formatter : {
		isDisable : function(value,row,i){
			if(value == "0"){
				return '<span style="color:green">'+ $.i18n["basics.is.disable.0"] +'</span>';
			}else{
				return '<span style="color:red">'+ $.i18n["basics.is.disable.1"] +'</span>';
			}
		},forMatter : function(value,row,i){
			return value;
		},
		timeForMatter : function(value,row,i){
			if(value){
				return value.replace("T"," ");
			}else{
				return value;
			}
		}
//		testOption : function(value,row,i){
//			var optionHtml = [];
//			optionHtml.push('<div class="am-btn-toolbar">');
//			optionHtml.push('	<div class="am-btn-group am-btn-group-xs">');
//			optionHtml.push('		<button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span> 编辑</button>');
//			optionHtml.push('		<button class="am-btn am-btn-default am-btn-xs"><span class="am-icon-copy"></span> 复制</button>');
//			optionHtml.push('		<button class="am-btn am-btn-default am-btn-xs am-text-danger"><span class="am-icon-trash-o"></span> 删除</button>');
//			optionHtml.push('	</div>');
//			optionHtml.push('</div>');
//			return optionHtml.join('');
	}
});
/** 把表单序列化成json对象 */
$.fn.serializeObject = function() {  
    var o = {};  
    var a = this.serializeArray();  
    $.each(a, function() {  
        if (o[this.name]) {  
            if (!o[this.name].push) {  
                o[this.name] = [ o[this.name] ];  
            }  
            o[this.name].push(this.value || '');  
        } else {  
            o[this.name] = this.value || '';  
        }  
    });  
    return o;  
}

var notifyShow = function(type,message,timer){
	
	$.notify(message, {
		type: type,
		timer : timer == undefined ? $.utils.DEFAULTS.notifyTimer : timer,
		placement: {
			align: "center"
		},
		animate: {
			enter: 'animated bounceIn',
			exit: 'animated bounceOut'
		}
	});
}
window.alert = function(message,timer){
	notifyShow("success",message,timer);
}
window.alertInfo = function(message,timer){
	notifyShow("info",message,timer);
}
window.alertWarning = function(message,timer){
	notifyShow("warning",message,timer);
}
window.alertDanger = function(message,timer){
	notifyShow("danger",message,timer);
}