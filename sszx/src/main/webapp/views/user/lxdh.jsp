<%@ page import="com.bsoft.sszx.dao.UserDao"%>
<%@ page import="com.bsoft.sszx.entity.user.User"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>联系电话</title>
<jsp:include page="/common/include.jsp" />
</head>

<%
	String uid=(String)session.getAttribute("user");
	String fydm=(String)session.getAttribute("fydm");
	User u=new UserDao().findUserById(uid, fydm);
	String lxdh="";
	if(u.getLxdh()!=null)
	 	lxdh=u.getLxdh();
%>
  
<body style="background:#fff;">
    <div align="center">
	     <table style="font-size:12px;margin-left:10px;" id="ssclzjqd">
		     <tr>
			     <td style="background-color:#AAAAAA; font-size:14px; font-weight:bold;
			          color:#FF5511" colspan="4">手机设置</td>
			 </tr>
		     <tr>
		       	<td>原手机号码：<span id="r_1"></span></td>
		     </tr>
		     <tr>
		     	<td>现手机号码：<input id="r_2" type="text"/></td>
		     </tr>
	     </table>
	     
	     <hr/>
	     <div align="center">
	     	<a id="reset" class="easyui-linkbutton" onclick="save()" iconCls="icon-ok">确定</a>
	     </div>
     </div>
     
 
<script>
$(function(){
	$('#save').linkbutton({});
	$('#reset').linkbutton({});  
	$('#r_1').html('<%=lxdh%>');
});

function save(){
	var pas2=$('#r_2').val();
	if(pas2==""){
	 	alert("请输入手机号码!");
	}else{
		$.ajax({
	     	url:'${path}/saveLxdh.do',
	     	type:'POST',
	     	data:{
	     		lxdh: pas2
	     	},
	     	dataType:'json',
	     	success: function(data) {
		       if(data.after==1){ 
		       		alert("设置成功");
		       		window.location.href="${path}/to_lxdh.do";
		       }
		       if(data.after==0){
		       		alert("设置失败");
		       }
	     }});
   }
}
</script>
</body>
</html>