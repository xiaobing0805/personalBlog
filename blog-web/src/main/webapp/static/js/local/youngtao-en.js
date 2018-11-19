$(function(){
	'use strict';
	$.fn.youngTao.locales['en'] = {
	    formatLoadingMessage: function () {
	        return 'Loading, please wait...';
	    },
	    formatRecordsPerPage: function (pageNumber) {
	    	return pageNumber + ' rows per page';
	    },
	    formatShowingRows: function (pageFrom, pageTo, totalRows) {
	    	return 'Showing ' + pageFrom + ' to ' + pageTo + ' of ' + totalRows + ' rows';
	    },
	    formatNoMatches: function () {
	        return 'No matching records found';
	    },
	    requestError: function () {
	        return 'The server is busy. Please try again later';
	    },
        nextPage : function(){
        	return 'Next page';
        },
        prevPage : function(){
        	return 'Prev page';
        },
        homePage : function(){
        	return 'Home';
        },
        endPage : function(){
        	return 'Shadowe';
        }
	};
	$.extend($.fn.youngTao.locales, $.fn.youngTao.locales['en']);
});