<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>诉讼服务中心信息管理系统</title>
<link type="text/css" rel="stylesheet" href="${path}/resources/style/login.css" />
<script type="text/javascript" src="${path}/resources/easyui/jquery-1.8.0.min.js"></script>
</head>

<body>
<div class="wrap moa">
<div class="logo moa"></div>
<div class="loginbg">
<div class="cont moa">

<input type="hidden" id="txt_fymc" value="杭州市下城区人民法院" />
<table width="370" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="61" height="40" class="font14col4b91c6">用户名：</td>
    <td width="187"><input id="txt_user" name="username" type="text" class="inputbg" value=""/></td>
    <td width="80">&nbsp;</td>
    <td width="42">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" class="font14col4b91c6">密　码：</td>
    <td><input id="txt_pass" name="pass" type="password" class="inputbg" value=""/></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="40">&nbsp;</td>
    <td><a href="javascript:login();" class="loginbtn moa"></a></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>

</div>
</div>
<div class="copyright moa">
<p>测试版1.0</p>
<p>为获得最佳浏览效果，建议使用IE6以上版本及1024*768分辨率</p>
<p>版权所有&nbsp;&copy;&nbsp;2015-2017</p>
</div>
</div>

<script type="text/javascript">
$(function(){
	$('#txt_user').focus();
	window.document.onkeydown = disableRefresh;
});

function disableRefresh(evt){
	evt = (evt) ? evt : window.event;
	if (evt.keyCode && evt.keyCode == 13) {
		   login();
	}
}

function login(){
	var user = $('#txt_user').val();
	var pass = $('#txt_pass').val();
	
	if(user!='' && pass!=''){
		$.post('${path}/login.do', {
			fymc: $('#txt_fymc').val(),
			user: user,
 	    	pass: pass
		}, function(data){
			if(data.after==1){
  	    	    window.location.href="${path}/main.jsp";
			}else if(data.after==0){
       	       alert("用户名或密码不正确！"); 
			}else{
				window.location.href="${path}/trial.jsp";
			}
		}, 'json');   
	}else{
		alert("请输入用户名和密码！");   
	}
}
</script>

</body>
</html>
