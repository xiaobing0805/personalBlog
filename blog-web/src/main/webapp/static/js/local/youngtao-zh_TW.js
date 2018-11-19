$(function(){
	'use strict';
	$.fn.youngTao.locales['zh_TW'] = {
	    formatLoadingMessage: function () {
	        return '正在拼命加載數據中，請稍後......';
	    },
	    formatRecordsPerPage: function (pageNumber) {
	        return '每頁顯示 ' + pageNumber + ' 條記錄';
	    },
	    formatShowingRows: function (pageFrom, pageTo, totalRows) {
	        return '顯示第 ' + pageFrom + ' 到第 ' + pageTo + ' 條記錄，總共 ' + totalRows + ' 條記錄';
	    },
	    formatNoMatches: function () {
	        return '沒有找到匹配的記錄';
	    },
	    requestError: function () {
	        return '服務器繁忙，請稍後再試';
	    },
        nextPage : function(){
        	return '下一頁';
        },
        prevPage : function(){
        	return '上一頁';
        },
        homePage : function(){
        	return '首頁';
        },
        endPage : function(){
        	return '尾頁';
        }
	};
	$.extend($.fn.youngTao.locales, $.fn.youngTao.locales['zh_TW']);
});