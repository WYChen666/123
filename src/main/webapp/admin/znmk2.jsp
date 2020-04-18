<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>智能模块管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
<script language="javascript" type="text/javascript"> 
$(function(){
	$("#btn6").bind("click",function(){
		$("#result").html("");
	});
	
	$("#btn3").bind("click",function(){
		var aQuery = {};//$("#info").serialize();  
		$.post('Admin_calc1.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						var rmsg = $("#result").html()+"<br/>已产生：center1.txt、matrix1.txt";
	            		$("#result").html(rmsg)
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		 },'json');
	});
	
	$("#btn5").bind("click",function(){
		var aQuery = {};//$("#info").serialize();  
		$.post('Admin_calc2.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						var rmsg = $("#result").html()+"<br/>"+responseObj.data.result;
	            		$("#result").html(rmsg)
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		 },'json');
	});
	
	$("#file1").bind('input propertychange',function(){
		upload("form1","已导入：data1.txt");
	});
	$("#file2").bind('input propertychange',function(){
		upload("form2","已导入：parameters.txt");
	});
	$("#file3").bind('input propertychange',function(){
		upload("form3","已导入：one-data.txt");
	});
	
	function upload(obj,msg) { 
        var form = $('#'+obj);            
        var formData = new FormData(form[0]);            
        $.ajax({
            url: 'UploadFile.action',
            timeout: 5 * 60 * 1000,
            type : 'post',
            data: formData,
            dataType: 'json',
            contentType: false, 
            processData: false, 
            success: function(responseObj) {
            	if(responseObj.success) {	
            		form[0].reset();
            		var rmsg = $("#result").html()==""?msg:$("#result").html()+"<br/>"+msg;
            		$("#result").html(rmsg)
				}else  if(responseObj.err.msg){
					 alert('失败：'+responseObj.err.msg);
				}else{
					 alert('失败：服务器异常！');
				}	
            },
            error: function(request, textStatus, errorThrown) {
                alert("服务器发生错误"+textStatus);
            }
        });
    }
    
});
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
 #result{
 	width:300px;
 	height:380px;
 	float:left;
 	padding:10px;
 	word-wrap:break-word;
	word-break:break-all;
 }
 #oprate{
 	width:160px;
 	height:400px;
 	float:right;
 	background-color:#82A5AE;
 	text-align:center;
 	line-height:58px;
 }
 .btnstyle2 {
	CURSOR: pointer;
	COLOR: #C4EDD7;
	PADDING-TOP: 2px;
	HEIGHT: 40px;
	background-color: #00B050;
	BORDER:1px solid #C4EDD7;
	font-size:16px;
	width:140px;
}
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">智能模块管理</span>
</div>
<div style="display:none">
<form id="form1" name="form1" method="post">
<input type="hidden" id="num" name="num" value="1"/>
<input type="file" id="file1" name="upload" />
</form>
<form id="form2" name="form2" method="post">
<input type="hidden" id="num" name="num" value="2"/>
<input type="file" id="file2" name="upload" />
</form>
<form id="form3" name="form3" method="post">
<input type="hidden" id="num" name="num" value="3"/>
<input type="file" id="file3" name="upload" />
</form>
</div>
<div style="width:550px;height:400px;margin:0 auto;margin-top:50px;border:2px solid #797A7C">
	<div id="result">
	
	</div>
	<div id="oprate">
		<input id="btn1" type="button" class="btnstyle2" value="导入数据" onclick="file1.click()"/>
		<br/><input id="btn2" type="button" class="btnstyle2" value="导入参数"  onclick="file2.click()"/>
		<br/><input id="btn3" type="button" class="btnstyle2" value="计算输出" style="width:90px"/>
		<br/><input id="btn4" type="button" class="btnstyle2" value="导入目标数据"  onclick="file3.click()"/>
		<br/><input id="btn5" type="button" class="btnstyle2" value="计算输出"/>
		<br/><input id="btn6" type="button" class="btnstyle2" value="清零" style="width:90px"/>
	</div>
</div>


</body>
</html>