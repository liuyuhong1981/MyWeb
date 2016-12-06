<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.9.0.js"></script>
  </head>
  <body>
    This is my JSP page. <br>
    <input type="text" id="txt_input" maxlength="10" onKeypress="return (/[\d.]/.test(String.fromCharCode(event.keyCode)))" style="ime-mode:Disabled"/><br />
    <input type="text" id="txt_input2" maxlength="10" onKeypress="javascript: numberInputControl()" style="ime-mode:Disabled" /><br /><br />
    <input type="button" id="button" value="OK" /><br /><br />
	<a href="javascript:hrefClick('http://www.baidu.com','http://www.baidu.com');">link</a>
	<a href="http://www.baidu.com">link2</a>
    <%
    	List list = new ArrayList();
    	Map map = new HashMap();
    	map.put("1","aaa");
    	list.add(map);
    	list.add(map);
    	list.add(map);
    	list.add(map);
     %>
    <script type="text/javascript">
   		function hrefClick(win1, win2) {
   			window.open(win1);
   			window.open(win2);
   		}
   		function numberInputControl() {
   			alert((/[\d.]/.test(String.fromCharCode(event.keyCode))));
   			// return (/[\d.]/.test(String.fromCharCode(event.keyCode))) || event.keyCode == '0x1' || event.keyCode == '0x8' || event.keyCode == '0x25' || event.keyCode == '0x27' || event.keyCode == '0x2E';
   			// return (/[\d.]/.test(String.fromCharCode(event.keyCode)));
   		}
     	$(document).ready(function() {
/*  	   	$("#txt_input").keydown(checkInputNumber);
    		function checkInputNumber() {
    			alert(event.keyCode);
    			var numberKeyArray = new Array["48","49","50","51","52","53","54","55","56","57","58","59"];
				if(event.keyCode == "48") {
					event.preventDefault();
                }
    		} */
//	     	$("#button").click(testFunction);
//    		function testFunction() {
//    			var list = '${list}';
//    			alert(list);
//    			alert(list.length);
//    		}
			function showdate(n) 
			{
				var uom = new Date(new Date()-0+n*86400000); 
				uom = uom.getFullYear() + "-" + (uom.getMonth()+1) + "-" + uom.getDate(); 
				return uom; 
			}

			window.alert("今天是："+showdate(0)); 
			window.alert("昨天是："+showdate(-1));
			window.alert("明天是："+showdate(1));
			window.alert("10天前是："+showdate(-10));
			window.alert("5天后是："+showdate(5)); 

			// 时间戳转换为日期时间格式
			function getDateWithTime(time){
			    var date = new Date(time);
			    var year = date.getFullYear();
			    var month;
			    var day;
			    var hour;
			    var minute;
			    var second;
			    if((date.getMonth() + 1) < 10) {
			    	month = "0" + (date.getMonth() + 1);
			    } else {
			    	month = date.getMonth() + 1;
			    }
			    if(date.getDate() < 10) {
			   		day = "0" + date.getDate();
			    } else {
			   		day = date.getDate();
			    }
			    if(date.getHours() < 10) {
			    	hour = "0" + date.getHours();
			    } else {
			    	hour = date.getHours();
			    }
			    if(date.getMinutes() < 10) {
			    	minute = "0" + date.getMinutes();
			    } else {
			    	minute = date.getMinutes();
			    }
			    if(date.getSeconds() < 10) {
			    	second = "0" + date.getSeconds();
			    } else {
			    	second = date.getSeconds();
			    }
			    return year + "/" + month + "/" + day + " " + hour + ":" + minute + ":" + second; 
			}
			// 时间戳转换为日期时间格式
			function getDate(time){
			    var date = new Date(time);
			    var year = date.getFullYear();
			    if((date.getMonth() + 1) < 10) {
			    	var month = "0" + (date.getMonth() + 1);
			    } else {
			    	var month = date.getMonth() + 1;
			    }
			    if(date.getDate() < 10) {
			   		var day = "0" + date.getDate();
			    } else {
			   		var day = date.getDate();
			    }
			    return year + "/" + month + "/" + day;
			}
			// 日期转换为时间戳
			function getDateForStringDate(strDate){
			    //切割年月日与时分秒称为数组
			    var s = strDate.split(" ");
			    var s1 = s[0].split("/");
			    var s2 = s[1].split(":");
			    if(s2.length==2){
			        s2.push("00");
			    }
				var date = new Date(s1[0],s1[1]-1,s1[2],s2[0],s2[1],s2[2]);
				//var localTime = date.getTime();
				//var localOffset = date.getTimezoneOffset() * 60000;
				//var utc = localTime - localOffset;
			    //return new Date(utc);
			    return date;
			}
			// 取得日期相差的天数，日期格式yyyy/MM/dd HH:mm:SS
			function getBetweenDays(day1, day2){
                var y1, y2, m1, m2, d1, d2;//year, month, day;
                y1 = parseInt(day1.split('/')[0]);
                y2 = parseInt(day2.split('/')[0]);
 
                m1 = parseInt(day1.split('/')[1]);
                m2 = parseInt(day2.split('/')[1]);
 
                d1 = parseInt(day1.split('/')[2]);
                d2 = parseInt(day2.split('/')[2]);
 
                var date1 = new Date(y1, m1 - 1, d1);
                var date2 = new Date(y2, m2 - 1, d2);

                //用距标准时间差来获取相距时间
                var minsec = Date.parse(date2) - Date.parse(date1);
                var days = minsec / 1000 / 60 / 60 / 24; //factor: second / minute / hour / day
 
                return days;
            }
			alert(getBetweenDays("2016/06/09 09:00:00","2016/06/30 00:00:00"));
    	});
    </script>
  </body>
</html>
