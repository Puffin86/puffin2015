<%@ page language="java" pageEncoding="utf-8"%>
<%@ page language="java" import="com.bsoft.sszx.dao.*,com.bsoft.sszx.entity.user.*" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<jsp:include page="/common/include.jsp" />
</head>
  <%
    String user=(String)session.getAttribute("user");
    String fydm=(String)session.getAttribute("fydm");
    UserDao userDao=new UserDao();
    User u=userDao.findUserById(user, fydm);
    String lx=u.getJs();//得到角色
    String sjrbm=u.getYhbm();
  %>  
  <body style="background-color: #fff;" class="easyui-layout">     
    <div id="p" class="easyui-panel" data-options="region:'center'" title="查询案件材料递交记录" style="width:100%;height:100%;padding:7px;">
    	 案号：<input id="ah" style="width:100px" type="text">
	   &nbsp;&nbsp;当事人：<input style="width:100px"  id="dsr" type="text">
	       <a id="research" onclick="searchAj()" >查询</a>
	       <ul id="searchList"></ul>
	       
	       
	     
    </div>
    
</body>

<script>
$('#research').linkbutton({});
$('#searchList').tree({  
    checkbox: false,
    onClick: function(node){		 
		if(node.attributes.leaf=="true"){
			 url='cxjlMx.do?yhid='+node.attributes.yhid;
			 window.open(url,"new",
					 "height=400px,width=600px,toolbar=no,status=no,menubar=no,scrollbars=no,resizable=no");
		 }
    }
});  

function searchAj(){
	var ah=$('#ah').val();
	var dsr=$('#dsr').val();
	if(ah!=''||dsr!=''){
	$.ajax({
  	     url:'clSearch.do',
  	     type:'POST',
  	     data:{ah:encodeURI(encodeURI(ah)),
  	    	 dsr:encodeURI(encodeURI(dsr)),
  	    	 lx:'<%=lx%>',
  	    	 sjrbm:'<%=sjrbm%>'},//注意大小写data
  	     dataType:'json',
  	     success:function (data) {
  	       $('#searchList').tree('loadData',data.data);
  	     }});
	}
}

</script>
</html>