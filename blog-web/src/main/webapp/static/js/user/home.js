$(function() {
	$.utils.ajax({
		type : "GET",
		url : "/recordAccess/getCurrentOnline",
		success : function(data){
			$("#currentOnline").html(data);
		}
	});
	$.utils.ajax({
		type : "GET",
		url : "/recordAccess/getTotalOnline",
		success : function(data){
			$("#totalOnline").html(data);
		}
	});
});