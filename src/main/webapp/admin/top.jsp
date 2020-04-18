<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>头部页面</title>
<link type="text/css" href="css/style.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
$(document).ready(function(){
	 
	 $("#logOut").bind('click',function(){
		if(confirm("确认退出？")){
			window.parent.location.href="login.jsp";
		}
		 
	 });
	
});	 
	
</script>
</head>

<body>
<div class="top">
	<div class="top1">
    	<div class="logo">
			<IMG src="images/logo3.png" width="480"/>
		</div>
        <div class="top_info"><a href="modifyInfo.jsp" target="MainFrame">Personal information</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="modifyPwd.jsp" target="MainFrame">change Password</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a id="logOut" href="#">Exit system</a></div>
    </div>
    <div class="top2">
    	<div class="shijian" id="timeDiv"></div>
        <div class="user">${admin.user_typeDesc }：${admin.user_type==3?'admin':admin.real_name}&nbsp;&nbsp;&nbsp;&nbsp;Welcome to the educational management information system!</div>
    </div>
</div>
</body>
</html>
<script language="javascript">
function calender()
{
 var time=new Date();
 var year=time.getFullYear();
 var month=toPair(time.getMonth()+1);
 var day=toPair(time.getDate());
 var hour=toPair(time.getHours());
 var minute=toPair(time.getMinutes());
 var second=toPair(time.getSeconds());
 var dateweek;

switch(time.getDay())
{
	case 0:dateweek = "Sunday";break;
	case 1:dateweek = "Monday";break;
	case 2:dateweek = "Tuesday";break;
	case 3:dateweek = "Wednesday";break;
	case 4:dateweek = "Thursday";break;
	case 5:dateweek = "Friday";break;
	case 6:dateweek = "Saturday";break;
}

 var timeDiv = document.getElementById('timeDiv');
 timeDiv.innerHTML = "Today is："+year+"."+month+"."+day+".　"+hour+":"+minute+":"+second+"　"+dateweek;
 var mytime=setTimeout("calender()",1000);
}

calender();

function toPair(str){
	if(Number(str)<10){
		return "0"+str;
	}else{
		return str;
	}
}
</script>
