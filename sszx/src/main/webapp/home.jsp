<%@ page import="com.bsoft.sszx.dao.HomeDao"%>
<%@ page import="com.bsoft.sszx.dao.UserDao"%>
<%@ page import="com.bsoft.sszx.entity.user.User"%>
<%@ page import="java.util.*"%>
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
		User userBean = userDao.findUserById(user, fydm);
		String userXm = userBean.getYhxm();
	%>

<div  id=pal style="padding:10px;">
	<p style="color:#770000"> <%=userXm %>, 您好!</p>
	<div id="thN">您有<a style="color:red" href="#" onclick="window.parent.addTab('处理退回材料','to_tuiHuiCL.do')">&nbsp;&nbsp; <label id="thN_data">0</label> &nbsp;&nbsp;</a>份材料被退回，请处理。</div> 
    <div id="drN">您有<a style="color:red" href="#" onclick="window.parent.addTab('待接收材料','to_fgJsCl.do')">&nbsp;&nbsp;<label id="drN_data">0</label>&nbsp;&nbsp;</a>份材料待接收，请处理。</div>
    <div id="jsN">您预约当事人领取的材料已有<a style="color:red" href="#" onclick="window.parent.addTab('预约领取已接收材料','to_fgYjsCl.do')">&nbsp;&nbsp;<label id="jsN_data">0</label>&nbsp;&nbsp;</a>份案件被领取！</div>
    <div id="jjlqsx">您有<a style="color:red" href="#" onclick="window.parent.addTab('接近领取时限','to_clsx.do?op=1')">&nbsp;&nbsp;<label id="jjlqsx_data">0</label>&nbsp;&nbsp;</a>份待当事人领取的材料，已接近领取时限。</div> 
    <div id="cglqsx">您有<a style="color:red" href="#" onclick="window.parent.addTab('超过领取时限','to_clsx.do?op=2')">&nbsp;&nbsp;<label id="cglqsx_data">0</label>&nbsp;&nbsp;</a>份待当事人领取的材料，已超过领取时限。</div> 
    <div id="jjtjsx">您有<a style="color:red" href="#" onclick="window.parent.addTab('接近提交时限','to_clsx.do?op=3')">&nbsp;&nbsp;<label id="jjtjsx_data">0</label>&nbsp;&nbsp;</a>份待当事人提交的材料，已接近提交时限。</div> 
    <div id="cgtjsx">您有<a style="color:red" href="#" onclick="window.parent.addTab('超过提交时限','to_clsx.do?op=4')">&nbsp;&nbsp;<label id="cgtjsx_data">0</label>&nbsp;&nbsp;</a>份待当事人提交的材料，已超过提交时限。</div> 
    <div id="lxdh">您的联系手机号码还未设置，<a style="color:red" href="#" onclick="window.parent.addTab('设置联系电话','to_lxdh.do')">请设置</a>。</div>
</div>

<br/>

<!-- <div iconCls="icon-help" class="easyui-panel" title="帮助" style="width:300px;height:auto;padding:10px;"> -->
<!--                   下载操作手册 -->
<!-- </div> -->

<script type="text/javascript">
$(function(){
	loadData();
	$('#pal').panel({
	    width:310,
	    title:'消息提醒',
	    tools:[{
	    iconCls:'icon-reload',
	    handler:function(){
	    	loadData();
	    }
	    }]
	});
	
	
});

function loadData(){
	$.ajax({
  	     url:'${path}/home.do',
  	     type:'POST',
  	     data:{},//注意大小写data
  	     dataType:'json',
  	     success:function (res) {
  	       if(res.success==true){
  	    	  if(res.htN==0){  
  	    		   $('#thN').hide();
  	    	  }else{  
  	    		  $('#thN_data').html(res.htN);
  	    		   $('#thN').show();
  	    	  }
      	      if(res.drN==0){ 
      	    	  $('#drN').hide();
      	      }else{ 
      	    	$('#drN_data').html(res.drN);
      	    	  $('#drN').show();
      	      }
      	      if(res.jsN==0){
      	    	  $('#jsN').hide();
      	      }else{
      	    	$('#jsN_data').html(res.jsN);
      	    	  $('#jsN').show();
      	      }
      	      if(res.lxdh==1){
      	    	  $('#lxdh').hide();
      	      }else{
      	    	  $('#lxdh').show();
      	      }
      	      
      	      if(res.jjlqsx==0){
      	    	  $('#jjlqsx').hide();
      	      }else{
      	    	$('#jjlqsx_data').html(res.jjlqsx);
      	    	  $('#jjlqsx').show();
      	      }
      	      if(res.cglqsx==0){
      	    	  $('#cglqsx').hide();	
      	      }else{
      	    	$('#cglqsx_data').html(res.cglqsx);
      	    	  $('#cglqsx').show();
      	      }
      	      if(res.jjtjsx==0){
      	    	  $('#jjtjsx').hide();	
      	      }else{
      	    	$('#jjtjsx_data').html(res.jjtjsx);
      	    	  $('#jjtjsx').show();
      	      }
      	      if(res.cgtjsx==0){
      	    	  $('#cgtjsx').hide();
      	      }else{
      	    	$('#cgtjsx_data').html(res.cgtjsx);
      	    	  $('#cgtjsx').show();
      	      }
  	       }
  	     }
   });
}

</script>
</body>
</html>
