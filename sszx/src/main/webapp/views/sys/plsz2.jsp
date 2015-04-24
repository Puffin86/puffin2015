<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	String roleId = request.getParameter("roleId");
	System.out.println("@"+roleId);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>批量设置角色</title>
<jsp:include page="/common/include.jsp" />
</head>

<body style="background-color:#fff;" >     

<div  style="height:500px;overflow:auto;">
	<table style="height:40px;">
		<tr>
			<td>部门：<input id="bmList" /></td>
			<td>&nbsp;</td>
			<td><a id="searchBtn"  iconCls="icon-search_sel">查询</a></td>
		</tr>
	</table>
	<div style="width:700px;height:400px;">
		<table>
			<tr>
				<td width="220px;">
					<div id="bmdiv" title="部门列表" class="easyui-panel" style="width:220px;height:410px;">
						<ul id="bmtree"></ul>  
					</div>
				</td>
				<td width="120px;">
					<div id="czdiv" style="text-align:center;">
						<a id="addBtn"  iconCls="icon-add2">添加</a><br/><br/>
						<a id="delBtn"  iconCls="icon-remove">删除</a><br/><br/>
						<a id="selectAllBtn"  iconCls="icon-checked">全选</a><br/><br/>
						<a id="unSelectAllBtn"  iconCls="icon-unchecked">全不选</a>
					</div>
				</td>
				<td width="350px;">
					<table id="usergrid" data-options="fit:true,border:false"></table> 
				</td>
			</tr>
		</table>
	</div>
	<div style="height:40px;">
		<table>
			<tr>
				<td width="200px"></td>
				<td>
				<br/>
					<a id="saveBtn"  iconCls="icon-save">保存</a>
					&nbsp;&nbsp;
					<a id="cancleBtn"  iconCls="icon-return" href="${path}/to_user.do">返回</a>
				</td>
			</tr>
		</table>
	</div>
</div>  

<script>
$(function(){
	$('#searchBtn').linkbutton({});
	$('#saveBtn').linkbutton({});
	$('#cancleBtn').linkbutton({});
	$('#addBtn').linkbutton({});
	$('#delBtn').linkbutton({});
	$('#selectAllBtn').linkbutton({});
	$('#unSelectAllBtn').linkbutton({});
	$('#bmList').combobox({
	    url:'bmList2.do',    
	    valueField:'bmdm',    
	    textField:'bmmc' 
	});
	//alert('${roleId }');
	$('#bmtree').tree({
	    checkbox: true,  
	    lines :true,
	    url: '${path}/bmryList.do',
	    loadFilter: function(data){
			if (data.data){
				return data.data;
			} else {
				return data;
			}
	    }
	});
	
	$('#usergrid').datagrid({
		rownumbers:true,
		//singleSelect:true,
		striped:true,
		fitColumns:true,
		idField:'zdmxbm',
		border:true,
		url:"userListByJs.do",
		queryParams : {
			js : '${roleId }',
			bmdm : $('#bmList').combobox('getValue')
		},
		columns:[[
			{field:'ck',checkbox:true }, 
			{field:'yhid',title:'人员id',width:120,align:'center',hidden:'true'},      
		    {field:'bmmc',title:'部门',width:120,align:'center'},
			{field:'yhxm',title:'人员',width:120,align:'center'},
			{field:'jsmc',title:'角色',width:120,align:'center'}
		]]
	});
	
	
})

</script>

</body>
</html>