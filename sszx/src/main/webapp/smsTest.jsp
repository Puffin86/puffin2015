<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<jsp:include page="/common/include.jsp" />
</head>
<script>
 function send(){
 	$.ajax({
   	     	url:'${path}/sendsms.do',
   	     	type:'POST',
   	     	data:{
   	     		 fsr_userid : $('#fsr_userid').val(),
   	     		 jssjhm : $('#jssjhm').val(),
   	     		 jsrmc : $('#jsrmc').val(),
   	     		 fsnr : $('#fsnr').val()
   	     	},
   	     	dataType:'json',
   	     	success: function(data) {
	   	    	 alert(data)
   	     	}
   	  	});
 }
 
 function sendDemo(){
	 $.ajax({
	     	url:'${path}/sendsmsdemo.do',
	     	type:'POST',
	     	data:{
	     		 fsr_userid : $('#fsr_userid').val(),
	     		 jssjhm : $('#jssjhm').val(),
	     		 jsrmc : $('#jsrmc').val(),
	     		 fsnr : $('#fsnr').val()
	     	},
	     	dataType:'json',
	     	success: function(data) {
	   	    	 alert(data)
	     	}
	  	});
}
</script>

<body>

<DIV id='butdiv' align="center"> 
<table>
	<tr>
		<td>fsr_userid</td>
		<td><input class="easyui-validatebox" required="true" id="fsr_userid" type="text" value='1303-Zuh'/></td>
	</tr>
	<tr>
		<td>jssjhm</td>
		<td><input class="easyui-validatebox" required="true" id="jssjhm" type="text" value=''/></td>
	</tr>
	<tr>
		<td>jsrmc</td>
		<td><input class="easyui-validatebox" required="true" id="jsrmc" type="text" value='张玺'/></td>
	</tr>
	<tr>
		<td>fsnr</td>
		<td><input class="easyui-validatebox" required="true" id="fsnr" type="text" value='测试短信内容'/></td>
	</tr>
	<tr>
		<td colspan="2">
			<a id="handSign" class="easyui-linkbutton"  onclick="send()">发送</a>&nbsp;&nbsp;
			<a id="handSign2" class="easyui-linkbutton"  onclick="sendDemo()">测试案例</a>
		</td>
	</tr>
</table>
		
</DIV>
</body>
</html>