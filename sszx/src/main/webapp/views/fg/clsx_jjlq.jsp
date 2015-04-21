<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>接近领取时限</title>
<jsp:include page="/common/include.jsp" />
<script type="text/javascript">
$(function(){
	var user = '${user}';
	var fydm = '${fydm}';
	$('#dsrZzSjTable').datagrid({
		rownumbers:false,
		fit:true,
		border:false,
		title:'明细列表',
		singleSelect:true,
		striped:true,
		fitColumns:true,
		singleSelect:true,
		idField:'lclx',
		pagination:true,
		sortName:'lclx',
		sortOrder:'desc',
		url:"${path}/clcx.do?user="+user+"&fydm="+fydm+"&lclx=flq&type=jj",
				
		columns:[[
		    {field:'id',title:'流水号',width:100,align:'center',hidden:true,
		    	formatter:function(id){
					return id.bh;
				}
		    },
		    {field:'lclx',title:'业务类型',width:120,align:'center',sortable:"true",
		    	formatter:function(lclx){
					return "预约领取";
				}
		    },
		    {field:'djrq',title:'递交日期',width:120,align:'center'},
			{field:'sx',title:'时限',width:120,align:'center'},
			{field:'ah',title:'案号',width:120,align:'center',sortable:"true"},
			{field:'sjrBmmc',title:'承办部门',width:100,align:'center'},
			{field:'sjrXm',title:'承办人',width:80,align:'center'},
	    	{field:'djr',title:'当事人',width:80,align:'center'}
		]]
	});
	
});
</script>
</head>

<body class="easyui-layout" style="background-color: #fff;">
<div data-options="region:'center'">
	<table id="dsrZzSjTable"></table> 
</div>
</body>
</html>