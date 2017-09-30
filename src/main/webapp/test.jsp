<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script charset="utf-8" src="/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="/kindeditor/lang/zh-CN.js"></script>
<script charset="utf-8" src="/kindeditor/plugins/code/prettify.js"></script>

<script>
	    var editor1;
		KindEditor.ready(function(K) {
			    editor1 = K.create('textarea[name="content"]', {
				cssPath : '/kindeditor/plugins/code/prettify.css',
				uploadJson : '/kindeditor/jsp/upload_json.jsp',//标识处理图片的文件
				fileManagerJson : '/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,//允许上传文件和图片
			    afterCreate : function() { 
		            this.sync(); 
		        }, 
		        afterBlur:function(){ 
		            this.sync(); 
		        }                 
			});
			prettyPrint();
		});
</script>
<body>
	<form action="testAction" method="post">
	
	<textarea id="editor_id" name="content" style="width: 700px; height: 300px;">
		
	</textarea>
	<button onclick="aa()">点击</button>
	
	</form>
</body>
<script type="text/javascript">
	function aa(){
		
		var content=document.getElementById("editor_id");
		
		
		alert(content);
	}
</script>
</html>