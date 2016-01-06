<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>设置权限</title>
<jsp:include page="/common/include.jsp" />
</head>

<body style="background-color:#fff;" class="easyui-layout">     
  
  <div id="p1" class="easyui-panel" data-options="region:'west',collapsible:false"   style="width:200px;" title="批量设置">
  		<ul id="roleList" class="easyui-tree" data-options="url:'${path}/zdmxcx_tree.do?zdbm=js'"></ul>
  </div>
  
  <div id="p" class="easyui-panel" data-options="region:'center'" title="用户权限设置" style="width:100%;">
  	<input id="userSearch" style="margin-left:5px;margin-top:5px" type="text"/>
  	<a id="research" onclick="searchUser()" iconCls="icon-search">查询</a>
  	<a id="searchAll" onclick="searchAllUser()" iconCls="icon-search">全部</a>
	<ul id="userList"></ul>
  </div>

<script>
$('#research').linkbutton({});
$('#searchAll').linkbutton({});

$('#roleList').tree({
	onClick: function(node){
		//alert(node.id +';'+ node.text);
		//location.href='${path}/to_plsz.do?roleId=' + node.id+"&roleText="+node.text;
		location.href='${path}/to_plsz.do?roleId=' + node.id+"&roleText="+encodeURI(encodeURI(node.text));
	}
});

$('#userList').tree({  
    checkbox: false,  
    url: '${path}/bmList.do',
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
	   	     	url:'${path}/userList.do',
	   	     	type:'POST',
	   	     	data:{bm:encodeURI(encodeURI(bm))},//注意大小写data
	   	     	dataType:'json',
	   	     	success:function (data) {
	   	       		var selected = $('#userList').tree('getSelected');
	   	       		$('#userList').tree('append',{parent:selected.target,data:data.data});
	   	     	}
	   	    });
		}
		 
		if(node.attributes.leaf=="true"){
			 url='${path}/jueSeFP.do?yhid='+node.attributes.yhid;
			 window.open(url, "new", "height=400px,width=400px,toolbar=no,status=no,menubar=no,scrollbars=no,resizable=no");
		}
    }
});  

function searchUser(){
	var userName=$('#userSearch').val();
	if(userName!=''){
		$.ajax({
	  	     url:'${path}/userSearch.do',
	  	     type:'POST',
	  	     data:{name:encodeURI(encodeURI(userName))},
	  	     dataType:'json',
	  	     success:function (data) {
	  	        $('#userList').tree('loadData', data.data);
	  	     }});
	}
}

function searchAllUser(){
	$('#userSearch').val('');
	$.ajax({
  	     url:'${path}/bmList.do',
  	     type:'POST',
  	     dataType:'json',
  	     success:function (data) {
  	        $('#userList').tree('loadData', data.data);
  	     }});
	
}
</script>

</body>
</html>