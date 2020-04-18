<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评测成绩信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
var tempClassName="";
function tr_mouseover(obj) 
{ 
	tempClassName=obj.className;
	obj.className="list_mouseover";
}
function tr_mouseout(obj)      
{ 
	obj.className=tempClassName;
}      
function CheckAll(obj) 
{
	var checks=document.getElementsByName("chkid");
    for (var i=0;i<checks.length;i++)
	{
	    var e = checks[i];
	    e.checked = obj.checked;
	}
    
}


function serch()
{
   document.info.action="Admin_listSScores.action";
   document.info.submit();
}
function del()
{
	var checks=document.getElementsByName("chkid");
    var ids="";
	for (var i=0;i<checks.length;i++)
    {
        var e = checks[i];
		if(e.checked==true)
		{
		  if(ids=="")
		  {
		    ids=ids+e.value;
		  }
		  else
		  {
		    ids=ids+","+e.value;
		  }
		}
    }
    if(ids=="")
    {
       alert("请至少选择一个要删除的评测成绩！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delSScores.action?paramsSScore.ids="+ids;
       document.info.submit();
    }
    
}
function GoPage()
{
  var pagenum=document.getElementById("goPage").value;
  var patten=/^\d+$/;
  if(!patten.exec(pagenum))
  {
    alert("页码必须为大于0的数字");
    return false;
  }
  document.getElementById("pageNo").value=pagenum;
  var score_valueMin = document.getElementById("score_valueMin");
  var score_valueMax = document.getElementById("score_valueMax");
  if(score_valueMin.value==''){
	  score_valueMin.value=0;
  }
  if(score_valueMax.value==''){
	  score_valueMax.value=0;
  }
  document.info.action="Admin_listSScores.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  var score_valueMin = document.getElementById("score_valueMin");
  var score_valueMax = document.getElementById("score_valueMax");
  if(score_valueMin.value==''){
	  score_valueMin.value=0;
  }
  if(score_valueMax.value==''){
	  score_valueMax.value=0;
  }
  document.info.action="Admin_listSScores.action";
  document.info.submit();
}
$(document).ready(function(){
	$("#export").bind('click',function(){
		var aQuery = $("#info").serialize();  
		$("#info").attr('target','_blank').attr('action','exportScores.action').submit();;
	});
});
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">智能模块管理&gt;&gt;评测成绩查询</span>
</div>
<form name="info" id="info" action="Admin_listSScores.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">评测成绩列表</td>
    <td width="" align="right">
            年份：
      <input type="text" style="width:100px;" id="paramsSScore.score_year" name="paramsSScore.score_year" value="${paramsSScore.score_year}" class="inputstyle" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy'})"/>&nbsp;
            学期：
      <s:select name="paramsSScore.score_year_half" id="paramsSScore.score_year_half" list="#{1:'上半年',2:'下半年'}" value="%{#attr.paramsSScore.score_year_half}" listKey="key" listValue="value" headerKey="0" headerValue="选择学期" cssClass="selectstyle" cssStyle="width:100px"></s:select>&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <s:if test="#attr.admin.user_type==2">
      <input type="button" value="删除" onclick="del();" class="btnstyle"/>&nbsp;
      </s:if>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
   	 <s:if test="#attr.admin.user_type==2">
   	 <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     </s:if>
     <td width="40" align="center">序号</td>
     <td width="" align="center">班级</td>
     <td width="" align="center">学号</td>
     <td width="" align="center">姓名</td>
     <td width="" align="center">课程名称</td>
     <td width="" align="center">第一项成绩</td>
     <td width="" align="center">第二项成绩</td>
     <td width="" align="center">第三项成绩</td>
     <td width="" align="center">第四项成绩</td>
     <td width="" align="center">第五项成绩</td>
     <td width="" align="center">操作</td>
      <td width="" align="center">流程图</td>
   </tr>
   <s:if test="#attr.sscores!=null && #attr.sscores.size()>0">
   <s:iterator value="#attr.sscores" id="score" status="status">
   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <s:if test="#attr.admin.user_type==2">
     <td width="" align="center"><s:checkbox name="chkid" fieldValue="%{#score.sscore_id}" cssStyle="vertical-align:text-bottom;"/></td>
     </s:if>
     <td width="" align="center"><s:property value="#status.index+1"/></td>
     <td width="" align="center"><s:property value="#score.clazz_name"/></td>
     <td width="" align="center"><s:property value="#score.user_name"/></td>
     <td width="" align="center"><s:property value="#score.real_name"/></td>
     <td width="" align="center"><s:property value="#score.course_name"/></td>
     <td width="" align="center"><s:property value="#score.score_value1"/></td>  
     <td width="" align="center"><s:property value="#score.score_value2"/></td>  
     <td width="" align="center"><s:property value="#score.score_value3"/></td>  
     <td width="" align="center"><s:property value="#score.score_value4"/></td>  
     <td width="" align="center"><s:property value="#score.score_value5"/></td>   
     <td width="" align="center" style="height: 458px; width: 115px; ">
       <img src="images/edit.png"/>&nbsp;<s:a href="Admin_editSScore.action?paramsSScore.sscore_id=%{#score.sscore_id}">编辑11</s:a>
    
     <s:if test="#attr.admin.note==2 && #score.flag==1">
     &nbsp;<s:a href="Admin_passSScore.action?paramsSScore.sscore_id=%{#score.sscore_id}&paramsSScore.flag=2&paramsSScore.piid=%{#score.piid}">通过111</s:a> 
      </s:if>
      <s:if test="#attr.admin.note==3 && #score.flag==2">
     &nbsp;<s:a href="Admin_passSScore.action?paramsSScore.sscore_id=%{#score.sscore_id}&paramsSScore.flag=3&paramsSScore.piid=%{#score.piid}">通过111</s:a> 
     &nbsp;<s:a href="Admin_bohui.action?paramsSScore.sscore_id=%{#score.sscore_id}&paramsSScore.flag=1&paramsSScore.piid=%{#score.piid}">驳回</s:a> 
     </s:if>
      <s:if test="#attr.admin.note==4 && #score.flag==3">
     &nbsp;<s:a href="Admin_passSScore.action?paramsSScore.sscore_id=%{#score.sscore_id}&paramsSScore.flag=4&paramsSScore.piid=%{#score.piid}">通过111</s:a> 
         </s:if>
     </td>
      <td width="" align="center">
      
      <s:if test="#score.flag==4">
    		流程已结束
     </s:if>
     <s:elseif test="#score.flag==0">
     		无流程图
     </s:elseif>
     <s:else>
     <s:a href="Admin_listindexpic.action?piid=%{#score.piid}" target="_blank">查看</s:a>   
     </s:else>
      
      </td>   
   </tr> 
   </s:iterator>
   </s:if>
   <s:else>
   <tr>
     <td height="60" colspan="14" align="center"><b>&lt;不存在评测成绩信息&gt;</b></td>
   </tr>
   </s:else>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>