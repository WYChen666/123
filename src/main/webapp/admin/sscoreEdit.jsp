<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.score!=null && #attr.score.score_id!=0">编辑</s:if><s:else>添加</s:else>评测成绩信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	
	 var num = /^\d+(\.\d+)?$/;
	 $(".editBtn").bind('click',function(){
		var obj = $(this);
		var scoreId = $(this).attr("id").replace("btn_value","score_value");
		var scoreV = $("#"+scoreId).val();
		if(scoreV=="" || !num.exec(scoreV)){
			alert("成绩必须为非空数字")
			return false;
		}
		var postParams = $("#info").serialize();  
 		$.post('Admin_saveSScore.action',postParams,
			function(responseObj) {
					if(responseObj.success) {
						alert("保存成功");
						obj.attr("disabled",true);
						obj.removeClass("btnstyle");
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		},'json');
	});
	
	var admin_type = "${admin.user_type}";
	for(var i=1;i<=5;i++){
		var scoreV = $("#score_value"+i).val();
		if(scoreV!="" && admin_type=='1'){
			$("#btn_value"+i).attr("disabled",true);
			$("#btn_value"+i).removeClass("btnstyle");
		}
	}
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">智能模块管理&gt;&gt;<s:if test="#attr.score!=null && #attr.score.score_id!=0">编辑</s:if><s:else>添加</s:else>评测成绩</span>
</div>
<form id="info" name="info" action="Admin_addScore.action" method="post">   
<s:hidden id="paramsSScore.sscore_id" name="paramsSScore.sscore_id" value="%{#attr.sscore.sscore_id}" /> > 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.score!=null && #attr.score.score_id!=0">编辑</s:if><s:else>添加</s:else>评测成绩</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
       <tr>
          <td width="35%"  align="right" style="padding-right:5px"><font color="red">*</font> 学号：</td>
          <td width="65%">
          	${sscore.user_name}
          </td> 
       </tr>
       <tr>
          <td width="35%"  align="right" style="padding-right:5px"><font color="red">*</font> 姓名：</td>
          <td width="65%">
          	${sscore.real_name}
          </td> 
       </tr>
       <tr>
          <td width="35%"  align="right" style="padding-right:5px"><font color="red">*</font> 学年学期：</td>
          <td width="65%">
          	${sscore.score_year} ${sscore.score_year_halfDesc}
          </td> 
       </tr>
       <tr>
          <td width="35%"  align="right" style="padding-right:5px"><font color="red">*</font> 第一项成绩：</td>
          <td width="65%">
            <s:textfield name="paramsSScore.score_value1" id="score_value1" value="%{#attr.sscore.score_value1}" />
            <input type="button" Class="btnstyle editBtn" id="btn_value1" value="保 存" />
          </td> 
       </tr>
       <tr>
          <td width="35%"  align="right" style="padding-right:5px"><font color="red">*</font> 第二项成绩：</td>
          <td width="65%">
            <s:textfield name="paramsSScore.score_value2" id="score_value2" value="%{#attr.sscore.score_value2}" />
            <input type="button" Class="btnstyle editBtn" id="btn_value2" value="保 存" />
          </td> 
       </tr>
       <tr>
          <td width="35%"  align="right" style="padding-right:5px"><font color="red">*</font> 第三项成绩：</td>
          <td width="65%">
            <s:textfield name="paramsSScore.score_value3" id="score_value3" value="%{#attr.sscore.score_value3}" />
            <input type="button" Class="btnstyle editBtn" id="btn_value3" value="保 存" />
          </td> 
       </tr>
       <tr>
          <td width="35%"  align="right" style="padding-right:5px"><font color="red">*</font> 第四项成绩：</td>
          <td width="65%">
            <s:textfield name="paramsSScore.score_value4" id="score_value4" value="%{#attr.sscore.score_value4}" />
            <input type="button" Class="btnstyle editBtn" id="btn_value4" value="保 存" />
          </td> 
       </tr>
       <tr>
          <td width="35%"  align="right" style="padding-right:5px"><font color="red">*</font> 第五项成绩：</td>
          <td width="65%">
            <s:textfield name="paramsSScore.score_value5" id="score_value5" value="%{#attr.sscore.score_value5}" />
            <input type="button" Class="btnstyle editBtn" id="btn_value5" value="保 存" />
          </td> 
       </tr>
     </table>
     </td>
   </tr>  
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
          	<input type="button" Class="btnstyle" value="返 回" onclick="window.location.href='Admin_listSScores.action?paramsSScore.score_year=${sscore.score_year}&paramsSScore.score_year_half=${sscore.score_year_half}'"/> 
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>