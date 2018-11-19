(function ($) {
    'use strict';
    
    var sprintf = function (str) {
        var args = arguments,
            flag = true,
            i = 1;

        str = str.replace(/{}/g, function () {
            var arg = args[i++];

            if (typeof arg === 'undefined') {
                flag = false;
                return '';
            }
            return arg;
        });
        return flag ? str : '';
    };
    
   var calculateObjectValue = function(self, name, args){
	   var func = name;
	   if (typeof func === 'function') {
           return func.apply(self, args);
       }
   }
   
   var callBack = function(method,value,row,i){
	   if(method){
		   var func=eval(method);
		   if(!func){
			   throw new Error(method + ' function is no defined');
		   }
	   	   return func(value,row,i);
	   }else{
		   return value;
	   }
   }

    var YoungTao = function(el,options){
    	
    	this.$el = $(el);
    	this.options = options;
    	
    	this.init();
    };

    YoungTao.DEFAULTS = {
    	currentPage: 1,
        pageSize: 10,
        pageList: [10, 25, 50, 100],
        queryParams : undefined,
        type : "GET",
        dataField : "datas",
        totalPageField : "totalPage",
        totalRecordField : "totalRecord",
        checkboxName : 'checkbox',
        loadCompleteExe : function(that){
        	return false;
        },
        onDblclickEven : function(row){
        	return false;
        }
    };

    YoungTao.LOCALES = {};

    YoungTao.LOCALES['zh_CN'] = {
        formatLoadingMessage: function () {
            return '正在拼命加载数据中，请稍后......';
        },
        formatRecordsPerPage: function (pageNumber) {
            return sprintf('每页显示 {} 条记录',pageNumber);
        },
        formatShowingRows: function (pageFrom, pageTo, totalRows) {
            return sprintf('显示第 {} 到第 {} 条记录，总共 {} 条记录',pageFrom,pageTo,totalRows);
        },
        formatNoMatches: function () {
            return '没有找到匹配的记录';
        },
        requestError: function () {
            return '服务器异常，请稍后再试';
        },
        nextPage : function(){
        	return '下一页';
        },
        prevPage : function(){
        	return '上一页';
        },
        homePage : function(){
        	return '首页';
        },
        endPage : function(){
        	return '尾页';
        }
    };

    $.extend(YoungTao.DEFAULTS, YoungTao.LOCALES['zh_CN']);

    var allowedMethods = [
        'getOptions',
        'refreshOptions',
        'getSelectedList'
    ];
    
    YoungTao.prototype = {
		
    	getOptions : function (page) {
	        
	        return page;
	    },
	    refreshOptions  : function(options){
	    	var queryParams = options.queryParams;
	    	var params = queryParams(this.options);
	    	this.options.queryParams = $.extend(this.options.queryParams,params);
	    	this.options = $.extend(this.options,params);
	    	this.initData();
	    },
	    getSelectedList : function(){
	    	var getSelectedList = [],that = this;
	    	this.$el.find('>tbody tr input[type="checkbox"]').each(function(i,item){
	    		if($(this).is(':checked')){
	    			getSelectedList.push(that.data_["data_" + i]);
	    		}
	    	});
	    	return getSelectedList;
	    },
	    onpageSizeChange : function(even){
	    	
	    	this.options.queryParams = $.extend(this.options.queryParams, {
	    		pageSize :  $(even.currentTarget).val(),
	    		currentPage :  1
	    	});
	    	this.options.pageSize = $(even.currentTarget).val();
    		this.initData();
	    },
    	onPageFirst : function(even){
    		if (even && $(even.currentTarget).parent().hasClass('disabled')) return false;
    		this.options = $.extend(this.options, {currentPage :  1});
    		this.options.queryParams = $.extend(this.options.queryParams, {currentPage :  1});
    		this.initData();
    	},
    	onPagePre : function(even){
    		if (even && $(even.currentTarget).parent().hasClass('disabled')) return false;
    		var currentPage = (parseInt(this.options.currentPage) - 1);
    		this.options = $.extend(this.options, {currentPage : currentPage });
    		this.options.queryParams = $.extend(this.options.queryParams, {currentPage :  currentPage});
    		this.initData();
    	},
    	onPageNumber : function(even){
    		if (even && $(even.currentTarget).parent().hasClass('disabled')) return false;
    		this.options = $.extend(this.options, {currentPage : $(even.currentTarget).text()});
    		this.options.queryParams = $.extend(this.options.queryParams, {currentPage : $(even.currentTarget).text()});
    		this.initData();
    	},
    	onPageNext : function(even){
    		if (even && $(even.currentTarget).parent().hasClass('disabled')) return false;
    		var currentPage = (parseInt(this.options.currentPage) + 1);
    		this.options = $.extend(this.options, {currentPage : currentPage});
    		this.options.queryParams = $.extend(this.options.queryParams, {currentPage : currentPage});
    		this.initData();
    	},
    	onPageLast : function(even){
    		if (even && $(even.currentTarget).parent().hasClass('disabled')) return false;
    		this.options = $.extend(this.options, {currentPage : this.totalPages});
    		this.options.queryParams = $.extend(this.options.queryParams, {currentPage : this.totalPages});
    		this.initData();
    	},
	    
    	init : function(){
    		this.initLocale();
    		this.initField();
    		this.initTitle();
    		this.initData();
    	},
    	initLocale : function () {
            if (this.options.locale) {
                var parts = this.options.locale.split(/-|_/);
                parts[0].toLowerCase();
                if (parts[1]) parts[1].toUpperCase();
                if ($.fn.youngTao.locales[this.options.locale]) {
                    // locale as requested
                    $.extend(this.options, $.fn.youngTao.locales[this.options.locale]);
                } else if ($.fn.youngTao.locales[parts.join('-')]) {
                    // locale with sep set to - (in case original was specified with _)
                    $.extend(this.options, $.fn.youngTao.locales[parts.join('-')]);
                } else if ($.fn.youngTao.locales[parts[0]]) {
                    // short locale language code (i.e. 'en')
                    $.extend(this.options, $.fn.youngTao.locales[parts[0]]);
                }
            }
        },
    	initData : function(){
    		var that = this;
    		var params = {
    				currentPage : this.options.currentPage,
    				pageSize : this.options.pageSize
    		};

    		that.$el.find('>tbody').remove();
    		$('<tbody id="tableloading" style="text-align: center;"><tr><td colspan='+ this.columns_.length +'>'+ this.options.formatLoadingMessage() +'</td></tr></tbody>').appendTo(that.$el);
    		params = $.extend(params, this.options.queryParams);
    		$.utils.ajax({
    			url : this.options.url,
    			type : this.options.type,
    			data : params,
    			success : function(res){
    				
    				res = calculateObjectValue(that.options, that.options.responseHandler, [res]);
    				that.initBody(res);
    			},
    			fail : function(data){
    				that.$el.find('>tbody').remove();
    				$('<tbody id="tableloading" style="text-align: center;"><tr><td colspan='+ that.columns_.length +'>'+ data.errorMessage +'</td></tr></tbody>').appendTo(that.$el);
    			},
    			error : function(){
    				that.$el.find('>tbody').remove();
    				$('<tbody id="tableloading" style="text-align: center;"><tr><td colspan='+ that.columns_.length +'>'+ that.options.requestError() +'</td></tr></tbody>').appendTo(that.$el);
    			}
    		});
    	},
    	initField : function(){
    		var columns = [];
    		var columnsTitle = [];
    		var columnsFormatter = [];
    		this.$el.find('th').each(function (i) {
                
    			columns.push($(this).attr("data-field"));
    			columnsTitle.push($(this).attr("data-title"));
    			columnsFormatter.push($(this).attr("data-formatter"));
            });
    		this.columnsFormatter_ = columnsFormatter;
    		this.columnsTitle_ = columnsTitle;
    		this.columns_ = columns;
    	},
    	initTitle : function(){
    		var that = this;
    		$.each(that.columnsTitle_,function(i,item){
    			var title = that.$el.find('th')[i];
    			$(title).html(item ? item : '');
    		});
    	},
    	initBody : function(data){
    		var that = this;
    		that.$body = that.$el.find('>tbody');
    		if (!that.$body.length) {
    			that.$body = $('<tbody style="text-align: center;"></tbody>').appendTo(that.$el);
            }
    		var tbodyHtml = [];
    		var item_ = {};
    		$.each(data[this.options.dataField],function(i,item){
    			item_["data_" + i] = item;
    			if(i % 2 != 0){
        			tbodyHtml.push('<tr index="'+ i +'" style="background-color: #d9edf7;">');
    			}else{
    				tbodyHtml.push('<tr index="'+ i +'">');
    			}
    			$.each(that.columns_,function(j,colimnItems){
        			var method = that.columnsFormatter_[j];
        			var value = colimnItems ? item[colimnItems] : '';
        			if(that.options.checkboxName == colimnItems){
        				tbodyHtml.push('	<td style="text-align: center;">');
            			tbodyHtml.push('		<input type="checkbox" class="flat-red">');
            			tbodyHtml.push('	</td>');
        			}else{
            			var result = callBack(method,value,item,j);
            			tbodyHtml.push('	<td>');
            			tbodyHtml.push(result);
            			tbodyHtml.push('	</td>');
        			}
    			});
    			tbodyHtml.push('</tr>');
    		});
    		this.data_ = item_;
    		that.$body.html(tbodyHtml.join(''));
    		this.initFoots(data);
    		if (typeof that.options.loadCompleteExe === 'function') {
    			that.options.loadCompleteExe(that);
	        }
    		var tbodyTr = that.$el.find('>tbody tr');
    		tbodyTr.on('dblclick',function(){
    			var row = that.data_["data_" + $(this).attr("index")];
    			that.options.onDblclickEven(row);
			});
    	},
    	initFoots : function(data){
    		var that = this;
    		that.$el.parent().find(".tableFoots").remove();
    		that.$el.parent().find(".pagination").remove();
    		var currentPage = this.options.currentPage;
    		var pageSize = this.options.pageSize;
    		var pageFrom = currentPage == 1 ? 1 : ((currentPage - 1) * pageSize) + 1;
    		var pageTo = ((currentPage - 1) * pageSize) + data[this.options.dataField].length;
    		var selectHtml = [];
    		selectHtml.push('<select class="pageSizeChange" data-am-selected="{searchBox: 1}">');
    		$.each(this.options.pageList,function(i,item){
    			selectHtml.push('<option '+ (item == pageSize ? 'selected="selected"' : '') +' value="'+ item +'">'+ item +'</option>');
    		});
    		selectHtml.push('</select>');
    		var recordHtml = [];
			recordHtml.push('<div class="tableFoots" style="font-size: 14px;padding-bottom: 10px;" class="am-cf">');
			recordHtml.push(this.options.formatShowingRows(pageFrom,pageTo,data[this.options.totalRecordField]));
			recordHtml.push("  " + this.options.formatRecordsPerPage(selectHtml.join('')));
			recordHtml.push('</div>');
			that.$el.after(recordHtml.join(''));
			that.$el.parent().find('.pageSizeChange').off('change').on('change',$.proxy(this.onpageSizeChange,this));
			
			this.totalPages = data[this.options.totalPageField];
    		if(this.totalPages > 1){
    			var pageRecord = [];
    			var from = 1;
    			var to  = this.totalPages;
    			if(this.totalPages >= 5){
    				from = currentPage - 2;
    				to = from + 4;
    				if(from < 1){
    					from = 1;
    					to = 5;
    				}
    				if(to > this.totalPages){
    					to = this.totalPages;
    					from = to -4;
    				}
    			}
    			for(var i = from;i <= to; i++ ){
    				pageRecord.push('<li class="page-number '+ (currentPage == i ? 'active' : '') +'"><a href="javascript:void(0);">'+ i +'</a></li>');
    			}
    			var pageHtml = [];
    			pageHtml.push('<ul class="pagination">');
    			pageHtml.push('		<li class="page-first '+ (currentPage == 1 ? 'disabled' : '') +'"><a href="javascript:void(0);">'+ this.options.homePage() +'</a></li>');
    			pageHtml.push('		<li class="page-pre '+ (currentPage == 1 ? 'disabled' : '') +'"><a href="javascript:void(0);">'+ this.options.prevPage() +'</a></li>');
    			pageHtml.push(			pageRecord.join(''));
    			pageHtml.push('		<li class="page-next '+ (currentPage == this.totalPages ? 'disabled' : '') +'"><a href="javascript:void(0);">'+ this.options.nextPage() +'</a></li>');
    			pageHtml.push('		<li class="page-last '+ (currentPage == this.totalPages ? 'disabled' : '') +'"><a href="javascript:void(0);">'+ this.options.endPage() +'</a></li>');
    			pageHtml.push('	</ul>');
    			that.$el.after(pageHtml.join(''));
    			
    			that.$el.parent().find('.page-first a').off('click').on('click',$.proxy(this.onPageFirst,this));
    			that.$el.parent().find('.page-pre a').off('click').on('click',$.proxy(this.onPagePre,this));
    			that.$el.parent().find('.page-number a').off('click').on('click',$.proxy(this.onPageNumber,this));
    			that.$el.parent().find('.page-next a').off('click').on('click',$.proxy(this.onPageNext,this));
    			that.$el.parent().find('.page-last a').off('click').on('click',$.proxy(this.onPageLast,this));
    		}
    		if(data[this.options.totalRecordField] <= 0){
    			$('<tbody id="tableloading" style="text-align: center;"><tr><td colspan='+ that.columns_.length +'>'+ that.options.formatNoMatches() +'</td></tr></tbody>').appendTo(that.$el);
    		}
    	}
    };

    $.fn.youngTao = function(option){
    	var value,
    		$this = $(this),
    		data = $this.data('bootstrap.table'),
    		args = Array.prototype.slice.call(arguments, 1),
    		options = $.extend({}, YoungTao.DEFAULTS, $this.data(),typeof option === 'object' && option);
    	
    	if (typeof option === 'string') {
    		if($.inArray(option,allowedMethods) < 0){
    			throw new Error('Unknown method: ' + option);
    		}
    		
    		if (!data) {
    			throw new Error('Please init object');
            }
    		
    		value = data[option].apply(data, args);
    		
    		if (option === 'destroy') {
                $this.removeData('bootstrap.table');
            }
    		
    	}
    	if (!data) {
            $this.data('bootstrap.table', (data = new YoungTao(this, options)));
        }
    	return typeof value === 'undefined' ? this : value;
    };

    $.fn.youngTao.Constructor = YoungTao;
    $.fn.youngTao.locales = YoungTao.LOCALES;
})(jQuery);