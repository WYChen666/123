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
	$("#btn1").bind("click",function(){
		var aQuery = {};//$("#info").serialize();  
		$.post('Admin_daoruchengji.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
	            		$("#btn11").val("√");
	            		$("#btn1Txt").attr("disabled",true);
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		 },'json');
	});
	
	var maxHieght=Math.max(document.body.scrollHeight , document.documentElement.scrollHeight);
	$("#mask").height(maxHieght);
	$("#btn2").bind("click",function(){
		$("#hbzj1").show();
		$("#mask").show();
	});
	$("#btnHbzjTj1").bind("click",function(){
		$("#btnHbzjTj1").attr("disabled",true);
		$("#btnHbzjTj1").css("color","#ccc");;
		$("#btn22").val("√")
	});
	
	//关闭
	$(".btnClose1").bind("click",function(){
		$("#hbzj1").hide();
		$("#mask").hide();
	});
	$(".btnClose2").bind("click",function(){
		$("#hbzj2").hide();
		$("#mask").hide();
	});
	$(".btnClose3").bind("click",function(){
		$("#hbzj3").hide();
		$("#mask").hide();
	});
	
	$("#btn3").bind("click",function(){
		var aQuery = $("#info1").serialize();  
		$.post('Admin_canshudaoru.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
	            		$("#btn33").val("√")
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		 },'json');
	});
	
	$("#btn4").bind("click",function(){
		var aQuery = {};//$("#info").serialize();  
		$.post('Admin_calc1.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
	            		$("#btn44").val("√")
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		 },'json');
	});
	
	$("#btn5").bind("click",function(){
		$("#hbzj2").show();
		$("#mask").show();
	});
	
	$("#btnHbzjTj2").bind("click",function(){
		var aQuery = $("#info2").serialize();  
		$.post('Admin_mubiaodaoru.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
	            		$("#btn55").val("√")
	            		$("#btnHbzjTj2").attr("disabled",true);
						$("#btnHbzjTj2").css("color","#ccc");;
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		 },'json');
	});
	
	$("#btn6").bind("click",function(){
		var aQuery = {};//$("#info").serialize();  
		$.post('Admin_calc2.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						var rmsg = responseObj.data.result;
						$("#hbzj3").show();
						$("#mask").show();
						$("#result").html(rmsg);
						$("#btn66").val("√")
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		 },'json');
	});
	
	$("#btn7").bind("click",function(){
		$("#btn11").val("——");
		$("#btn11Txt").val("——");
		$("#btn22").val("——");
		$("#btn33").val("——");
		$("#btn44").val("——");
		$("#btn55").val("——");
		$("#btn66").val("——");
		$("#info1")[0].reset();
		$("#info2")[0].reset();
		
		$("#btn1").removeAttr("disabled");
		$("#btn1Txt").removeAttr("disabled");
		$("#btnHbzjTj1").removeAttr("disabled");
		$("#btnHbzjTj2").removeAttr("disabled");
		$("#btnHbzjTj1").css("color","#000");;
		$("#btnHbzjTj2").css("color","#000");;
	});
	
	$("#file1").bind('input propertychange',function(){
		upload("form1");
	});
	
	function upload(obj) { 
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
            		$("#btn11Txt").val("√");
            		$("#btn1").attr("disabled",true);
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
 #oprate{
 	width:160px;
 	height:400px;
 	float:left;
 	margin-left:50px;
 	background-color:#82A5AE;
 	text-align:center;
 	line-height:50px;
 }
 #tips{
 	width:160px;
 	height:400px;
 	float:left;
 	margin-left:50px;
 	line-height:50px;
 }
 .btnstyle2 {
	CURSOR: pointer;
	COLOR: white;
	PADDING-TOP: 2px;
	HEIGHT: 40px;
	background-color: #B5A57B;
	BORDER:1px solid #C4EDD7;
	font-size:16px;
	width:140px;
}
.btnstyle3 {
	CURSOR: pointer;
	COLOR: #E6F413;
	PADDING-TOP: 2px;
	HEIGHT: 40px;
	background-color: #92D050;
	BORDER:1px solid #C4EDD7;
	font-size:16px;
	width:100px;
}
#mask{
	position:absolute;
	zIndex:1;
	width:100%;
	height:100%;
	top:0px;
	left:0px;
	background-color:#000;
	filter:alpha(opacity=40);
	opacity:0.40;
	display:none;
}
#hbzj1,#hbzj3{
	position:absolute;
	zIndex:999;
	width:450px;
	height:350px;
	top:80px;
	left:200px;
	border:5px solid #5da638;
	background-color:white; 
	display:none;
}
#hbzj2{
	position:absolute;
	zIndex:999;
	width:450px;
	height:480px;
	top:40px;
	left:200px;
	border:5px solid #5da638;
	background-color:white; 
	display:none;
}
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">智能模块管理 &gt;&gt; 智能监测</span>
</div>
<div id="mask"></div>
<div id="hbzj1">
	<div style="width:100%;height:22px;background-color:#5da638">
		<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">参数设置</span>
	</div>
	<div style="width:100%;height:310px;overflow:auto;">
	<form id="info1" name="info1" method="post">
	<table width="95%" align="center" cellpadding="0" cellspacing="0">
	  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
	  <tr>
	    <td colspan="2"  width="" align="left">
	              学生人数：<input type="text" name="paramsCanshu.param1" style="width:100px"/>
	      <br/>考核目标数：<input type="text" name="paramsCanshu.param2" style="width:100px"/>
	      <br/>分类数量：<input type="text" name="paramsCanshu.param3" style="width:100px"/>
	      <br/>模糊精度：<input type="text" name="paramsCanshu.param4" style="width:100px"/>
	      <br/>迭代计算次数：<input type="text" name="paramsCanshu.param5" style="width:100px"/>
	      <br/>停止限定阈值：<input type="text" name="paramsCanshu.param6" style="width:100px"/>
	      <br/><input type="button" id="btnHbzjTj1" Class="btnstyle" value="保 存"/>&nbsp;&nbsp;
	      <input type="button" Class="btnstyle btnClose1" value="关 闭"/>
	    </td>
	  </tr>
	  <tr><td colspan="2" height="2px"></td></tr>  
	</table>
	</form>
	</div>
</div>
<div id="hbzj2">
	<div style="width:100%;height:22px;background-color:#5da638">
		<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">输入目标数据</span>
	</div>
	<div style="width:100%;height:450px;overflow:auto;">
	<form id="info2" name="info2" method="post">
	<table width="95%" align="center" cellpadding="0" cellspacing="0">
	  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
	  <tr>
	    <td colspan="2"  width="" align="left" style="line-height:25px;">
	            　第一项成绩：<input type="text" name="paramsCanshu.param1" style="width:100px"/>
	      <br/>　第二项成绩：<input type="text" name="paramsCanshu.param2" style="width:100px"/>
	      <br/>　第三项成绩：<input type="text" name="paramsCanshu.param3" style="width:100px"/>
	      <br/>　第四项成绩：<input type="text" name="paramsCanshu.param4" style="width:100px"/>
	      <br/>　第五项成绩：<input type="text" name="paramsCanshu.param5" style="width:100px"/>
	      <br/>　第六项成绩：<input type="text" name="paramsCanshu.param6" style="width:100px"/>
	      <br/>　第七项成绩：<input type="text" name="paramsCanshu.param7" style="width:100px"/>
	      <br/>　第八项成绩：<input type="text" name="paramsCanshu.param8" style="width:100px"/>
	      <br/>　第九项成绩：<input type="text" name="paramsCanshu.param9" style="width:100px"/>
	      <br/>　第十项成绩：<input type="text" name="paramsCanshu.param10" style="width:100px"/>
	      <br/>第十一项成绩：<input type="text" name="paramsCanshu.param11" style="width:100px"/>
	      <br/>第十二项成绩：<input type="text" name="paramsCanshu.param12" style="width:100px"/>
	      <br/>第十三项成绩：<input type="text" name="paramsCanshu.param13" style="width:100px"/>
	      <br/>第十四项成绩：<input type="text" name="paramsCanshu.param14" style="width:100px"/>
	      <br/>第十五项成绩：<input type="text" name="paramsCanshu.param15" style="width:100px"/>
	      <br/>第十六项成绩：<input type="text" name="paramsCanshu.param16" style="width:100px"/>
	      <br/><input type="button" id="btnHbzjTj2" Class="btnstyle" value="保 存"/>&nbsp;&nbsp;
	      <input type="button" Class="btnstyle btnClose2" value="关 闭"/>
	    </td>
	  </tr>
	  <tr><td colspan="2" height="2px"></td></tr>  
	</table>
	</form>
	</div>
</div>

<div id="hbzj3">
	<div style="width:100%;height:22px;background-color:#5da638">
		<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">计算结果</span>
	</div>
	<div id="result" style="width:100%;height:280px;overflow:auto;line-height:22px;padding:10px;">
	 
	</div>
	<div style="width:100%;height:30px;line-height:30px;padding-left:10px;">
	 	<input type="button" Class="btnstyle btnClose3" value="关 闭"/>
	</div>
</div>

<div style="display:none">
<form id="form1" name="form1" method="post">
<input type="hidden" id="num" name="num" value="1"/>
<input type="file" id="file1" name="upload" />
</form>
</div>

<div style="width:550px;height:400px;margin:0 auto;margin-top:50px;border:20px solid #5B9BD5;border-radius:20px;background-color:#759FCC"
		title="点击【导入成绩】时，系统会把该教师对应的选修课的平时成绩提交到算法备用；【导入批量成绩】用于选择上传成绩提交到算法备用；【参数设置】【参数导入】用于设置6项参数；【计算分类】会自动计算数据，生成2个文件；【输入目标数据】用于配置5项成绩目标；【计算结果】用于输出最终结果；【清空数据】会重置所有">
	<div id="oprate">
		<input id="btn1" type="button" class="btnstyle2" value="导入成绩"/>
		<br/><input id="btn1Txt" type="button" class="btnstyle2" value="导入批量成绩" onclick="file1.click()"/>
		<br/><input id="btn2" type="button" class="btnstyle2" value="参数设置"/>
		<br/><input id="btn3" type="button" class="btnstyle2" value="参数导入"/>
		<br/><input id="btn4" type="button" class="btnstyle2" value="计算分类"/>
		<br/><input id="btn5" type="button" class="btnstyle2" value="输入目标数据"/>
		<br/><input id="btn6" type="button" class="btnstyle2" value="计算结果"/>
		<br/><input id="btn7" type="button" class="btnstyle2" value="清空数据"/>
	</div>
	<div id="tips">
		<input id="btn11" type="button" class="btnstyle3" value="———"/>
		<br/><input id="btn11Txt" type="button" class="btnstyle3" value="———"/>
		<br/><input id="btn22" type="button" class="btnstyle3" value="———"/>
		<br/><input id="btn33" type="button" class="btnstyle3" value="———"/>
		<br/><input id="btn44" type="button" class="btnstyle3" value="———"/>
		<br/><input id="btn55" type="button" class="btnstyle3" value="———"/>
		<br/><input id="btn66" type="button" class="btnstyle3" value="———"/>
		<br/><input id="btn77" type="button" class="btnstyle3" value="———"/>
	</div>
</div>


</body>
</html>