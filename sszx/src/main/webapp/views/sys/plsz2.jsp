<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>批量设置角色</title>
<jsp:include page="/common/include.jsp" />
</head>

<body style="background-color:#fff;" class="easyui-layout">     

<div data-options="region:'north',border:false" style="height:80px;">
	<table>
		<tr>
			<td>部门：<input id="bmList" /></td>
			<td>&nbsp;</td>
			<td><a id="searchBtn"  iconCls="icon-save">查询</a></td>
		</tr>
	</table>
</div>  

<div data-options="region:'west'" style="width:200px;">
	<ul id="bm_tree" class="easyui-tree" data-options="fit:true,url:'${path }/bmList.do'"></ul>
</div>  

<div data-options="region:'center',border:false" style="padding:5px;">
	<table width="100%" border="0" cellpadding="10" cellspacing="0">
		<tr><td align="center"><a class="easyui-linkbutton" iconCls="icon-back">添&nbsp;&nbsp;加</a></td></tr>
		<tr><td align="center"><a class="easyui-linkbutton" iconCls="icon-back">删&nbsp;&nbsp;除</a></td></tr>
		<tr><td align="center"><a class="easyui-linkbutton" iconCls="icon-back">全&nbsp;&nbsp;选</a></td></tr>
		<tr><td align="center"><a class="easyui-linkbutton" iconCls="icon-back">全不选</a></td></tr>
	</table>
</div>  

<div data-options="region:'east'" style="width:600px;">
</div>

<div data-options="region:'south',border:false" style="height:100px;padding-top:20px;text-align:center;">
	<a class="easyui-linkbutton" iconCls="icon-save">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="${path}/to_user.do" class="easyui-linkbutton" iconCls="icon-back">返回</a>
</div> 

<script>
$(function(){
	$('#searchBtn').linkbutton({});
	$('#bmList').combobox({
	    url:'bmList2.do',    
	    valueField:'bmdm',    
	    textField:'bmmc' 
	});
	
	
	
})

</script>

</body>
</html>