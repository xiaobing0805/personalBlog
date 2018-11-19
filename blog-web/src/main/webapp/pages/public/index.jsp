<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="./../../static/js/jquery-3.1.1.min.js"></script>
<script src="./../../static/ueditor/ueditor.config.js"></script>
<script src="./../../static/ueditor/ueditor.all.js"></script>
<script src="./../../static/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
	<div id="myEditor">  
		
    </div>
<script type="text/javascript">
	$(function(){  
	    
	    //富文本编辑器  
	    UE.getEditor('myEditor',{
	    	toolbars: [
   	        	[
   	            	'fullscreen', 'source', 'undo', 'redo', 'bold','anchor','autosubmit','autotypeset','italic','subscript','superscript',
   	            	'blockquote','cleardoc','touppercase','tolowercase','customstyle','directionality','forecolor','backcolor','fontsize',
   	            	'fontfamily','underline','strikethrough','fontborder','formatmatch','horizontal','imagefloat','insertimage','indent',
   	            	'insertcode','inserthtml','insertparagraph','justify','lineheight','link','unlink','insertorderedlist','insertunorderedlist',
   	            	'music','pagebreak','paragraph','preview','print','pasteplain','removeformat','rowspacing','selectall','date','time','insertvideo'
   	        	]
   	    	]
	    });  
	      
	    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;  
	    UE.Editor.prototype.getActionUrl = function(action){  
	    	
	        if(action == '/upload/images'){
	            return '/upload/images';  
	        }else{  
	            return this._bkGetActionUrl.call(this, action);  
	        }  
	    }; 
	});
</script>
</body>
</html>