<%@ page import="com.bsoft.sszx.dao.ZjqdDao"%>
<%@ page import="com.bsoft.sszx.dao.UserDao"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>我的主页</title>
<jsp:include page="/common/include.jsp" />
</head>

<body style="background-color:#fff;padding:20px;">

	<%
		String user = (String) session.getAttribute("user");
		String fydm = (String) session.getAttribute("fydm");
		UserDao userDao = new UserDao();
		//用户姓名
		String userXm = userDao.findUserById(user, fydm).getYhxm();
		//根据状态获取
		int htN=new ZjqdDao().countByZt(fydm, user, 3);
		int drN=new ZjqdDao().countByZt(fydm, user, 1);
		
		//联系电话是否设置
		int lxdh=0;
		if(userDao.findUserById(user, fydm).getLxdh()!=null)
			lxdh=1;
	%>

<div iconCls="icon-tip" class="easyui-panel" title="消息提醒" style="width:300px;height:auto;padding:10px;">
	<p style="color:#770000"> <%=userXm %>, 您好!</p>
	<div id="thN">您有<a style="color:red" href="#" onclick="window.parent.addTab('处理退回材料','to_tuiHuiCL.do')">&nbsp;&nbsp;<%=htN %>&nbsp;&nbsp;</a>份材料被退回，请处理。</div> 
    <div id="drN">您有<a style="color:red" href="#" onclick="window.parent.addTab('接收材料','to_fgJsCl.do')">&nbsp;&nbsp;<%=drN %>&nbsp;&nbsp;</a>份材料待接收，请处理。</div> 
    <div id="lxdh">您的联系手机号码还未设置，<a style="color:red" href="#" onclick="window.parent.addTab('设置联系电话','to_lxdh.do')">请设置</a>。</div>
</div>

<br/>

<div iconCls="icon-help" class="easyui-panel" title="帮助" style="width:300px;height:auto;padding:10px;">
                  下载操作手册
</div>

<script type="text/javascript">
$(function(){
   var htN="<%=htN%>";
   var drN="<%=drN%>";
   var lxdh="<%=lxdh%>";
   
   if(htN==0) $('#thN').hide();
   if(drN==0) $('#drN').hide();
   if(lxdh==1) $('#lxdh').hide();	   
});
</script>
</body>
</html>
