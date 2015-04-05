<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>修改密码</title>
<jsp:include page="/common/include.jsp" />
</head>

<body style="background:#fff;">

<div align="center">
	<table style="font-size:12px;margin-left:10px;" id="ssclzjqd">
		<tr>
			<td style="background-color:#AAAAAA; font-size:14px; font-weight:bold; color:#FF5511" colspan="4">密码设置</td>
		</tr>
		<tr>
			<td>
				请输入新密码：<input id="r_1" type="password"/>
			</td>
		</tr>
		<tr>
			<td>
				请确认新密码：<input id="r_2" type="password"/>
			</td>
		</tr>
	</table>
	
	<hr/>
	<div align="center">
		<a id="reset" class="easyui-linkbutton" onclick="save()" iconCls="icon-ok">确定</a>
	</div>
</div>
     
 
<script>
$('#save').linkbutton({});
$('#reset').linkbutton({});  

function save(){
	var pas1=$('#r_1').val();
	var pas2=$('#r_2').val();
 
	if(pas1 != pas2){
		alert("两次输入的密码不同，请重新输入！");
	}else{
		$.ajax({
		    url:'${path}/savePass.do',
		    type:'POST',
		    data:{pas:encodeURI(encodeURI(pas1))},
		    dataType:'json',
		    success:function (data) {
		      if(data.after==1){ 
		      	  alert("设置成功，请重新登录");
		      	  top.window.location.href="${path}/logout.jsp";
		      }
		      if(data.after==0){
			      alert("设置失败");
		      }
		    }
		 });
	}
}
</script>

</body>
</html>