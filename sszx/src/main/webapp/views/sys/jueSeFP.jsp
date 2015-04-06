<%@page import="com.bsoft.sszx.entity.user.User"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>角色配置</title>
<jsp:include page="/common/include.jsp" />
</head>

<%
	User user=(User)session.getAttribute("editUser");
%>
  
<body style="font-size:12px;">

<div align="center">
	<table style="font-size:12px;margin-left:10px;" id="ssclzjqd">
		<tr>
			<td style="background-color:#AAAAAA; font-size:14px; font-weight:bold;color:#FF5511" colspan="4">角色配置</td>
		</tr>
		<tr>
			<td><input id="r_1" type="radio" name="identity" value="1" />管理员</td>
			<td><input id="r_2" type="radio" name="identity" value="2" />法官 </td>
			<td><input id="r_3" type="radio" name="identity" value="3" />诉讼服务中心人员</td>
			<td><input id="r_4" type="radio" name="identity" value="4" />部门内勤</td>
		</tr>
		<tr>
			<td align="center" colspan="4">
				<a id="save" onclick="save()" iconCls="icon-save">保存角色</a>
				<hr/>
			</td>
		</tr>
		<tr>
			<td colspan="4" style="width:200px;background-color:#AAAAAA; font-size:14px; font-weight:bold;color:#FF5511">重置初始密码</td>
		</tr>
		<tr>
			<td align="center" colspan="4">
				<a id="reset" onclick="resetPass()" iconCls="icon-clear">重置初始密码</a>
			</td>
		</tr>
	</table>
</div>
     
 
<script>
$(document).ready(function(){ 
	$('#save').linkbutton({});
	$('#reset').linkbutton({});
	
	var lx="${editUser.js}"; //读取角色

	if(lx==1)
	 	$('#r_1').attr('checked','checked');
	
	if(lx==2)
	 	$('#r_2').attr('checked','checked');
	
	if(lx==3)
	 	$('#r_3').attr('checked','checked');
	
	if(lx==4)
	 	$('#r_4').attr('checked','checked');   
}); 

function resetPass(){
	var id="${editUser.id.yhid}";
	var fydm="${editUser.id.dwdm}";
	
	$.ajax({
	     url:'${path}/resetPass.do',
	     type:'POST',
	     data:{
	    	 yhid:encodeURI(encodeURI(id)),
	    	 fydm:encodeURI(encodeURI(fydm))
	     },
     	 dataType:'json',
	     success:function (data) {
	       if(data.after==1)
	       		alert("重置成功");
	       if(data.after==0)
	       		alert("重置失败");
	     }
   });
}

function save(){
	var id="${editUser.id.yhid}";
	var fydm="${editUser.id.dwdm}";
	
	var lx=$('input[name=identity]:checked').attr('value');
 
	$.ajax({
		url:'${path}/saveJs.do',
		type:'POST',
		data:{
			yhid:encodeURI(encodeURI(id)),
			fydm:encodeURI(encodeURI(fydm)),
			lx:lx
		},
    	dataType:'json',
    	success:function (data) {
	      if(data.after==1){
	   	   	lx=data.js;
	   	   	
   	 		if(lx==1)
   	   			$('#r_1').attr('checked','checked');
	        if(lx==2)
	   	   		$('#r_2').attr('checked','checked');
	        if(lx==3)
	   	   		$('#r_3').attr('checked','checked');
	        if(lx==4)
	   	   		$('#r_4').attr('checked','checked');   
	        
      		alert("保存成功");
      	  }
      	  if(data.after==0)
      		alert("保存失败");
    	}
	});
}
</script>
</body>
</html>