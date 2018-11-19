$("#roleTable").youngTao({
	url : '/role/findRoleByPage',
	locale : $("#currentLanguage").val(),
	responseHandler: function (res) {
        return res;
    },
    loadCompleteExe : function(that){
    	
    	$.utils.complateIcheck(that);
    },
    onDblclickEven : function(row){
    	console.log(row);
    }
});