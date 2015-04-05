<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<jsp:include page="/common/include.jsp" />
</head>
  <body>    
    
    <div class="easyui-accordion" style="margin-left:30px;width:250px;height:400px;"> 
    <div iconCls="icon-edit" title="设置短消息">
    <ul id="dxxList">
    </ul>
    </div></div>
</body>

<script>
$('#dxxList').tree({ 
	url: 'dxxList',
	loadFilter: function(data){
		if (data.data){
			return data.data;
		} else {
			return data;
		}
    },
    checkbox: false,
    onClick: function(node){		 
		if(node.attributes.leaf=="true"){
			 var fydm="${session.fydm}";
			 url='dxxSz?id='+node.attributes.id
					 +'&fydm='+fydm;
			 window.open(url,"new",
					 "height=400px,width=500px,toolbar=no,status=no,menubar=no,scrollbars=no,resizable=no");
		 }
    }
});  
</script>
</html>