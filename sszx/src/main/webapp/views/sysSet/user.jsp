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
    
    <div class="easyui-accordion" style="margin-left:150px;width:200px;height:400px;"> 
    <div iconCls="icon-edit" title="设置用户角色">
    <ul id="userList">
    </ul>
    </div>  
    <div iconCls="icon-search" title="搜索">
       <input id="userSearch" style="margin-left:5px;margin-top:5px" type="text">
       <a id="research" onclick="searchUser()" iconCls="icon-search"></a>
       <ul id="searchList"></ul>
    </div></div>
</body>

<script>
$('#research').linkbutton({});
$('#userList').tree({  
    checkbox: false,  
    url: 'bmList',
    loadFilter: function(data){
		if (data.data){
			return data.data;
		} else {
			return data;
		}
    },
    onClick: function(node){
    	var bm=node.text;    
		if(node.attributes.leaf!="true" && node.attributes.opened=="false"){
	    node.attributes.opened="true";
		$.ajax({
   	     url:'userList',
   	     type:'POST',
   	     data:{bm:encodeURI(encodeURI(bm))},//注意大小写data
   	     dataType:'json',
   	     success:function (data) {
   	       var selected = $('#userList').tree('getSelected');
   	       $('#userList').tree('append',{parent:selected.target,data:data.data});
   	     }});}
		 
		if(node.attributes.leaf=="true"){
			 url='jueSeFP?yhid='+node.attributes.yhid;
			 window.open(url,"new",
					 "height=400px,width=400px,toolbar=no,status=no,menubar=no,scrollbars=no,resizable=no");
		 }
    }
});  

$('#searchList').tree({  
    checkbox: false,
    onClick: function(node){		 
		if(node.attributes.leaf=="true"){
			 url='jueSeFP?yhid='+node.attributes.yhid;
			 window.open(url,"new",
					 "height=400px,width=400px,toolbar=no,status=no,menubar=no,scrollbars=no,resizable=no");
		 }
    }
});  

function searchUser(){
	var userName=$('#userSearch').val();
	if(userName!=''){
	$.ajax({
  	     url:'userSearch',
  	     type:'POST',
  	     data:{name:encodeURI(encodeURI(userName))},//注意大小写data
  	     dataType:'json',
  	     success:function (data) {
  	       $('#searchList').tree('loadData',data.data);
  	     }});
	}
}

</script>
</html>