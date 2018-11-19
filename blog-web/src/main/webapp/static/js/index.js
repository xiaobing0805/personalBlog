$(function(){
	$.utils.stopFive();
	$.extend({
		constants : {
			urlPrefix : "/operate",
			homePage : "/home",
			openSidebarToggle : "open",
			parentId : 1, /** permissions最终父节点ID */
			en : "en",
			zhTW : "zh_TW"
		},
		variable : {
			childpermissions : [] /** 父节点下所有子节点 */
		},
		logout : function(){
			window.location.href = "/logout?backstageUrl=" + window.document.location.pathname;
		},
    	requestFullScreen: function () {
            var de = document.documentElement;
            if (de.requestFullscreen) {
                de.requestFullscreen();
            } else if (de.mozRequestFullScreen) {
                de.mozRequestFullScreen();
            } else if (de.webkitRequestFullScreen) {
                de.webkitRequestFullScreen();
            }
        },
        exitFullscreen: function () {
            var de = document;
            if (de.exitFullscreen) {
                de.exitFullscreen();
            } else if (de.mozCancelFullScreen) {
                de.mozCancelFullScreen();
            } else if (de.webkitCancelFullScreen) {
                de.webkitCancelFullScreen();
            }
        },
		initEven : function(){
			$.menu.sidebarToggleEven(); /** 侧边栏切换点击事件 */
			$('.menuItem').on('click', function(){
				$.menu.menuClickEven(this,true);
			}); /** 菜单点击事件 */
			$('.menuTabs').on('click', '.menuTab i', $.tab.tabClose); /** 关闭tab */
			$('.menuTabs').on('click', '.menuTab', $.tab.tabActive); /** tab 选择 */
			$('.tabLeft').on('click', $.tab.tabScrollLeft); /** tab向左滚动 */
            $('.tabRight').on('click', $.tab.tabScrollRight); /** tab向右滚动 */
            $('.tabReload').on('click', $.tab.tabRefresh); /** 刷新当前tab */
            $('.tabCloseOther').on('click', $.tab.tabCloseOther); /** 除了当前tab，关闭其他所有tab */
            $('.tabCloseCurrent').on('click', function () { /** 关闭当前tab */
                $('.page-tabs-content').find('.active i').trigger("click");
            });
            $('.tabCloseAll').on('click', function () { /** 关闭所有tab */
                $('.page-tabs-content').children("[data-id]").find('.fa-remove').each(function () {
                    $('.LRADMS_iframe[data-id="' + $(this).parent().data('id') + '"]').remove();
                    $(this).parents('a').remove();
                });
                $('.page-tabs-content').children("[data-id]:first").each(function () {
                    $('.LRADMS_iframe[data-id="' + $(this).data('id') + '"]').show();
                    $(this).addClass("active");
                });
                $('.page-tabs-content').css("margin-left", "0");
            });
            $('.fullscreen').on('click', function () { /** 点击开启全屏/退出全屏 */
                if (!$(this).attr('fullscreen')) {
                    $(this).attr('fullscreen', 'true');
                    $.requestFullScreen(); /** 开启全屏 */
                } else {
                    $(this).removeAttr('fullscreen')
                    $.exitFullscreen(); /** 退出全屏 */
                }
            });
		},
		initBodyHtml : function(){
			var path = document.location.pathname;
			var htmlPathStart = path.indexOf("/",1);
			var htmlPath = path.substring(htmlPathStart,(path.length));
			if(htmlPath == $.constants.urlPrefix){
				$.tab.tabExist($.constants.homePage);
			}else{
				$.menu.firstLoad(htmlPath);
			}
		},
		loadHomeBody : function(){
			$.utils.ajax({
    			type : "GET",
    			url : "/getBodyHtml",
    			data : {htmlPath : "/home"},
    			dataType : "HTML",
    			success : function(data){
    				$("#defaltHome").html(data);
    			}
			});
		},
		tab: {
			tabAdd : function(even,path){
				var dataId = $(even).data('id');
	            var dataUrl = $(even).data('url');
	            var menuName = $.trim($(even).text());
	            $.utils.startFive();
	    		$.utils.ajax({
	    			type : "GET",
	    			url : "/getBodyHtml",
	    			data : {htmlPath : path},
	    			dataType : "HTML",
	    			success : function(data){
	                    var optionTab = [];
	                    optionTab.push('<a href="javascript:;" class="active menuTab" data-id="' + dataUrl + '">');
	                    optionTab.push(		menuName);
	                    optionTab.push('	<i class="fa fa-remove"></i>');
	                    optionTab.push('</a>');
	                    $('.menuTab').removeClass('active');
//	                    var str1 = '<iframe class="LRADMS_iframe" id="iframe' + dataId + '" name="iframe' + dataId + '"  width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
	                    var newBody = [];
	                    newBody.push('<div class="LRADMS_iframe" id="iframe' + dataId + '" name="iframe' + dataId + '" data-id="' + dataUrl + '" style="height: 100px;">');
	                    newBody.push(data);
	                    newBody.push('</div>');
	                    
	                    $('.mainContent').find('div.LRADMS_iframe').hide();
	                    $('.mainContent').append(newBody.join(''));
	                    //$.loading(true);
	                    $('.mainContent iframe:visible').load(function () {
	                        //$.loading(false);
	                    });
	                    $('.menuTabs .page-tabs-content').append(optionTab.join(''));
	                    $.tab.scrollToTab($('.menuTab.active'));
	    			},
	    			done : function(){
	    				$.utils.stopFive();
	    			}
	    		});
	    	},
	        tabClose: function () {
	            var closeTabId = $(this).parents('.menuTab').data('id');
	            var currentWidth = $(this).parents('.menuTab').width();
	            if ($(this).parents('.menuTab').hasClass('active')) {
	                if ($(this).parents('.menuTab').next('.menuTab').size()) {
	                    var activeId = $(this).parents('.menuTab').next('.menuTab:eq(0)').data('id');
	                    $(this).parents('.menuTab').next('.menuTab:eq(0)').addClass('active');

	                    $('.mainContent .LRADMS_iframe').each(function () {
	                        if ($(this).data('id') == activeId) {
	                            $(this).show().siblings('.LRADMS_iframe').hide();
	                            return false;
	                        }
	                    });
	                    var marginLeftVal = parseInt($('.page-tabs-content').css('margin-left'));
	                    if (marginLeftVal < 0) {
	                        $('.page-tabs-content').animate({
	                            marginLeft: (marginLeftVal + currentWidth) + 'px'
	                        }, "fast");
	                    }
	                    $(this).parents('.menuTab').remove();
	                    $('.mainContent .LRADMS_iframe').each(function () {
	                        if ($(this).data('id') == closeTabId) {
	                            $(this).remove();
	                            return false;
	                        }
	                    });
	                }
	                if ($(this).parents('.menuTab').prev('.menuTab').size()) {
	                    var activeId = $(this).parents('.menuTab').prev('.menuTab:last').data('id');
	                    $(this).parents('.menuTab').prev('.menuTab:last').addClass('active');
	                    $('.mainContent .LRADMS_iframe').each(function () {
	                        if ($(this).data('id') == activeId) {
	                            $(this).show().siblings('.LRADMS_iframe').hide();
	                            return false;
	                        }
	                    });
	                    $(this).parents('.menuTab').remove();
	                    $('.mainContent .LRADMS_iframe').each(function () {
	                        if ($(this).data('id') == closeTabId) {
	                            $(this).remove();
	                            return false;
	                        }
	                    });
	                }
	            }
	            else {
	                $(this).parents('.menuTab').remove();
	                $('.mainContent .LRADMS_iframe').each(function () {
	                    if ($(this).data('id') == closeTabId) {
	                        $(this).remove();
	                        return false;
	                    }
	                });
	                $.tab.scrollToTab($('.menuTab.active'));
	            }
	            return false;
	        },
	        tabScrollRight: function () {
	            var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
	            var tabOuterWidth = $.tab.calSumWidth($(".content-tabs").children().not(".menuTabs"));
	            var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
	            var scrollVal = 0;
	            if ($(".page-tabs-content").width() < visibleWidth) {
	                return false;
	            } else {
	                var tabElement = $(".menuTab:first");
	                var offsetVal = 0;
	                while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) {
	                    offsetVal += $(tabElement).outerWidth(true);
	                    tabElement = $(tabElement).next();
	                }
	                offsetVal = 0;
	                while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
	                    offsetVal += $(tabElement).outerWidth(true);
	                    tabElement = $(tabElement).next();
	                }
	                scrollVal = $.tab.calSumWidth($(tabElement).prevAll());
	                if (scrollVal > 0) {
	                    $('.page-tabs-content').animate({
	                        marginLeft: 0 - scrollVal + 'px'
	                    }, "fast");
	                }
	            }
	        },
	        tabScrollLeft: function () {
	            var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
	            var tabOuterWidth = $.tab.calSumWidth($(".content-tabs").children().not(".menuTabs"));
	            var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
	            var scrollVal = 0;
	            if ($(".page-tabs-content").width() < visibleWidth) {
	                return false;
	            } else {
	                var tabElement = $(".menuTab:first");
	                var offsetVal = 0;
	                while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) {
	                    offsetVal += $(tabElement).outerWidth(true);
	                    tabElement = $(tabElement).next();
	                }
	                offsetVal = 0;
	                if ($.tab.calSumWidth($(tabElement).prevAll()) > visibleWidth) {
	                    while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
	                        offsetVal += $(tabElement).outerWidth(true);
	                        tabElement = $(tabElement).prev();
	                    }
	                    scrollVal = $.tab.calSumWidth($(tabElement).prevAll());
	                }
	            }
	            $('.page-tabs-content').animate({
	                marginLeft: 0 - scrollVal + 'px'
	            }, "fast");
	        },
	    	tabExist : function(dataUrl){
	            var flag = true;
	            $('.menuTab').each(function () {
	                if ($(this).data('id') == dataUrl) {
	                    if (!$(this).hasClass('active')) {
	                        $(this).addClass('active').siblings('.menuTab').removeClass('active');
	                        $.tab.scrollToTab(this);
	                        $('.mainContent .LRADMS_iframe').each(function () {
	                            if ($(this).data('id') == dataUrl) {
	                                $(this).show().siblings('.LRADMS_iframe').hide();
	                                flag = false;
	                                return false;
	                            }
	                        });
	                    }
	                    flag = false;
	                    return false;
	                }
	            });
	            return flag;
	    	},
	    	scrollToTab: function (element) {
	            var marginLeftVal = $.tab.calSumWidth($(element).prevAll()), marginRightVal = $.tab.calSumWidth($(element).nextAll());
	            var tabOuterWidth = $.tab.calSumWidth($(".content-tabs").children().not(".menuTabs"));
	            var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
	            var scrollVal = 0;
	            if ($(".page-tabs-content").outerWidth() < visibleWidth) {
	                scrollVal = 0;
	            } else if (marginRightVal <= (visibleWidth - $(element).outerWidth(true) - $(element).next().outerWidth(true))) {
	                if ((visibleWidth - $(element).next().outerWidth(true)) > marginRightVal) {
	                    scrollVal = marginLeftVal;
	                    var tabElement = element;
	                    while ((scrollVal - $(tabElement).outerWidth()) > ($(".page-tabs-content").outerWidth() - visibleWidth)) {
	                        scrollVal -= $(tabElement).prev().outerWidth();
	                        tabElement = $(tabElement).prev();
	                    }
	                }
	            } else if (marginLeftVal > (visibleWidth - $(element).outerWidth(true) - $(element).prev().outerWidth(true))) {
	                scrollVal = marginLeftVal - $(element).prev().outerWidth(true);
	            }
	            $('.page-tabs-content').animate({
	                marginLeft: 0 - scrollVal + 'px'
	            }, "fast");
	        },
	        calSumWidth: function (element) {
	            var width = 0;
	            $(element).each(function () {
	                width += $(this).outerWidth(true);
	            });
	            return width;
	        },
	        refreshTab: function () {
	            var currentId = $('.page-tabs-content').find('.active').attr('data-id');
	            var target = $('.LRADMS_iframe[data-id="' + currentId + '"]');
	            var url = target.attr('src');
	            //$.loading(true);
	            target.attr('src', url).load(function () {
	                //$.loading(false);
	            });
	        },
	        tabActive: function () {
	            var currentId = $(this).data('id');
	            if (!$(this).hasClass('active')) {
	                $('.mainContent .LRADMS_iframe').each(function () {
	                    if ($(this).data('id') == currentId) {
	                        $(this).show().siblings('.LRADMS_iframe').hide();
	                        return false;
	                    }
	                });
	                $(this).addClass('active').siblings('.menuTab').removeClass('active');
	                $.tab.scrollToTab(this);
	            }
	        },
	        tabCloseOther: function () {
	            $('.page-tabs-content').children("[data-id]").find('.fa-remove').parents('a').not(".active").each(function () {
	                $('.LRADMS_iframe[data-id="' + $(this).data('id') + '"]').remove();
	                $(this).remove();
	            });
	            $('.page-tabs-content').css("margin-left", "0");
	        }
		},
		menu : {
			isoPenSidebarToggle : function(value){
				if(value === $.constants.openSidebarToggle){
					$("body").removeClass("sidebar-collapse");
				}else{
					$("body").addClass("sidebar-collapse");
				}
			},
			sidebarToggleEven: function () {
	            $("body").removeClass("hold-transition")
	            $("#content-wrapper").find('.mainContent').height($(window).height() - 100);
	            $(window).resize(function (e) {
	                $("#content-wrapper").find('.mainContent').height($(window).height() - 100);
	            });
	            $(".sidebar-toggle").click(function () {
	                if (!$("body").hasClass("sidebar-collapse")) {
	                    $("body").addClass("sidebar-collapse");
	                } else {
	                    $("body").removeClass("sidebar-collapse");
	                }
	            })
	        },
			firstLoad : function(htmlPath){
				var menuItem = $(".menuItem");
				$.each(menuItem,function(i,el){
					if($(el).data("url") === htmlPath){
						$.menu.menuClickEven($(el));
						return false;
					}
				});
			},
			permissionsLoad: function (initBodyHtml) {
	        	$.utils.loading("#sidebar-menu");
	        	$.utils.ajax({
					type : "GET",
					url : "/permissions/findPermissions",
					success : function(data){
			        	var permissions = ['<li class="header">导航菜单</li>'];
			        	permissions.push($.menu.menuRender(data));
			            $("#sidebar-menu").append(permissions);
			            $.menu.menuFoldEven(); /** 初始化侧边栏菜单折叠事件 */
			        	$.initEven(); /** 初始化事件 */
			        	if(initBodyHtml && typeof initBodyHtml === 'function'){
							initBodyHtml();
						}
			        	$.menu.isoPenSidebarToggle($("#sidebarToggle").val()); /** 是否展开侧边栏 */
					},
					done : function(){
			            $("#sidebar-menu .cssload-loader-container").remove();
					}
	        	});
	        },
	        menuRender : function(data){
	        	var active = true;
	        	var permissions = [];
	        	$.each(data,function(i,el){
        			$.variable.childpermissions = [];
    	        	$.menu.renderMenuChild(el.childPermissions);
    	        	var url = el.childPermissions.length <= 0 ? el.url : 'javascript:void(0);';
    	        	if(active){
    	        		permissions.push('<li class="treeview active">');
    	        	}else{
    	        		permissions.push('<li class="treeview">');
        			}
        			permissions.push('		<a href="'+ url +'">');
        			permissions.push('			<i class="'+ el.icon +'"></i>');
        			var lang = $("#currentLanguage").val();
        			if(lang == $.constants.en){
        				permissions.push(			'<span>'+ el.enName +'</span>');
    				}else if(lang == $.constants.zhTW){
    					permissions.push(			'<span>'+ el.zhHKName +'</span>');
    				}else{
    					permissions.push(			'<span>'+ el.zhCNName +'</span>');
    				}
        			if(el.childPermissions.length > 0){
	        			permissions.push('		<i class="fa fa-angle-left pull-right"></i>');
        			}
    	        	permissions.push('		</a>');
    	        	permissions.push($.variable.childpermissions.join(''));
    	        	active = false;
	        	});
	        	permissions.push('</li>');
	        	return permissions.join('');
	        },
	        renderMenuChild : function(currentEl){
	        	if(currentEl && currentEl.length > 0){
	    			$.variable.childpermissions.push('<ul class="treeview-menu">');
		        	$.each(currentEl,function(i,el){
	        			var url = el.childPermissions.length <= 0 ? el.url : 'javascript:void(0);';
	        			$.variable.childpermissions.push('<li>');
	        			if(el.childPermissions.length <= 0){
	        				$.variable.childpermissions.push('		<a class="menuItem" data-id="youngtao_tab_'+ i +'" data-url="'+ el.url +'" href="javascript:void(0);">');
	        			}else{
	        				$.variable.childpermissions.push('		<a href="javascript:void(0);">');
	        			}
	        			$.variable.childpermissions.push('			<i class="'+ el.icon +'"></i>');
	        			var lang = $("#currentLanguage").val();
	        			if(lang == $.constants.en){
	        				$.variable.childpermissions.push(el.enName);
	    				}else if(lang == $.constants.zhTW){
	    					$.variable.childpermissions.push(el.zhHKName);
	    				}else{
	    					$.variable.childpermissions.push(el.zhCNName);
	    				}
	        			if(el.childPermissions.length > 0){
	        				$.variable.childpermissions.push('		<i class="fa fa-angle-left pull-right"></i>');
	        			}
	        			$.variable.childpermissions.push('		</a>');
	        			if(el.childPermissions.length > 0){
	        				$.menu.renderMenuChild(el.childPermissions);
	        			}
	        			$.variable.childpermissions.push('	</li>');
		        	});
		        	$.variable.childpermissions.push('</ul>');
	        	}
	        },
	        menuClickEven : function(even,isPopstate){
//	            $(".navbar-custom-menu>ul>li.open").removeClass("open");
	            var dataUrl = $(even).data('url');
	            if (dataUrl == undefined || $.trim(dataUrl).length == 0) {
	                return false;
	            }
	            var path = dataUrl == $.constants.urlPrefix ? $.constants.homePage : dataUrl;
	            if(isPopstate){
		            history.pushState(path,"",$.constants.urlPrefix + path);
	            }
	            var flag = $.tab.tabExist(dataUrl); /** 判断tab是否已经打开 */
	            if (flag) {
	            	$.tab.tabAdd(even,path); /** 添加tab，以及加载内容 */
	            }
	            return false;
	        },
	        menuFoldEven : function(){
	        	$("#sidebar-menu li a").click(function () {
	                var d = $(this), e = d.next();
	                if (e.is(".treeview-menu") && e.is(":visible")) {
	                    e.slideUp(500, function () {
	                        e.removeClass("menu-open")
	                    }),
	                    e.parent("li").removeClass("active")
	                } else if (e.is(".treeview-menu") && !e.is(":visible")) {
	                    var f = d.parents("ul").first(),
	                    g = f.find("ul:visible").slideUp(500);
	                    g.removeClass("menu-open");
	                    var h = d.parent("li");
	                    e.slideDown(500, function () {
	                        e.addClass("menu-open"),
	                        f.find("li.active").removeClass("active"),
	                        h.addClass("active");

	                        var _height1 = $(window).height() - $("#sidebar-menu >li.active").position().top - 41;
	                        var _height2 = $("#sidebar-menu li > ul.menu-open").height() + 10;
	                        if (_height2 > _height1) {
	                            $("#sidebar-menu >li > ul.menu-open").css({
	                                overflow: "auto",
	                                height: _height1
	                            })
	                        }
	                    })
	                }
	                e.is(".treeview-menu");
	            });
	        }
		},
		init : {
			
		}
	});
	$("#content-main").css("height",$(window).height());
	$.loadHomeBody(); /** 加载欢迎页内容 */
	$.menu.permissionsLoad($.initBodyHtml); /** 加载侧边栏菜单 */
	window.addEventListener('popstate', function(event) { /** 获取浏览器回退/前进事件 */
		var url = event.state;
		if(url){
			$.menu.firstLoad(url);
		}else{
			$.initBodyHtml();
		}
	});
});