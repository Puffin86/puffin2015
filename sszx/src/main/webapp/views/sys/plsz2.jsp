<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	String roleId = request.getParameter("roleId");
	String roleText = request.getParameter("roleText");
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
			<td>
				<a id="searchBtn"  iconCls="icon-search_sel">查询</a>
				&nbsp;&nbsp;
				<a id="searchAllBtn"  iconCls="icon-search_sel">全部</a>
				&nbsp;&nbsp;
				<a id="saveBtn"  iconCls="icon-save">保存</a>
				&nbsp;&nbsp;
				<a id="cancleBtn"  iconCls="icon-return" href="${path}/to_user.do">返回</a>
			</td>
		</tr>
	</table>
	<div style="width:700px;height:400px;">
		<table>
			<tr>
				<td width="220px;">
					<div id="bmdiv" title="部门列表" class="easyui-panel" style="width:220px;height:350px;">
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
</div>  

<script>
$(function(){
	
	var cacheData = {};
	
	$('#searchBtn').linkbutton({});
	$('#saveBtn').linkbutton({});
	$('#cancleBtn').linkbutton({});
	$('#addBtn').linkbutton({});
	$('#delBtn').linkbutton({});
	$('#selectAllBtn').linkbutton({});
	$('#unSelectAllBtn').linkbutton({});
	
	$('#searchAllBtn').linkbutton({});
	
	
	
	$("#selectAllBtn").bind("click",function(){
		var roots = $('#bmtree').tree('getRoots');//返回tree的所有根节点数组
		for ( var i = 0; i < roots.length; i++) {  
            var node = $('#bmtree').tree('find', roots[i].id);//查找节点  
            $('#bmtree').tree('check', node.target);//将得到的节点选中  
        }
	});
	
	$("#unSelectAllBtn").bind("click",function(){
		var roots = $('#bmtree').tree('getRoots');//返回tree的所有根节点数组
		for ( var i = 0; i < roots.length; i++) {  
            var node = $('#bmtree').tree('find', roots[i].id);//查找节点  
            $('#bmtree').tree('uncheck', node.target);//将得到的节点选中  
        }
	});
	
	$("#searchAllBtn").bind("click",function(){
		$('#bmList').combobox('setValue','');
		$('#bmtree').tree('reload');
		 $('#usergrid').datagrid('load',{
			 js : '${roleId }',
			 bmdm : ''
		 });
	});
	
	$("#saveBtn").bind("click",function(){
		var addArr = new Array();
		var delArr = new Array();
		for(var key in cacheData){
			if(cacheData[key].type=="add"){
				addArr.push(key);
			}else if(cacheData[key].type=="del"){
				delArr.push(key);
			}
		}
		
		$.ajax({
     	     url:'plszJs.do',
     	     type:'POST',
     	     data:{
     	    	 addItems : addArr,
     	    	 delItems : delArr,
     	    	 lx : '${roleId}'
     	     },//注意大小写data
     	     dataType:'json',
     	     success:function (data) {
     	    	alert('保存成功')
     	     }
     	 });
		
		
    }); 
	
	 $("#searchBtn").bind("click",function(){
		 $('#bmtree').tree('reload');
		 $('#usergrid').datagrid('load',{
			 js : '${roleId }',
			 bmdm : $('#bmList').combobox('getValue')
		 });
     });  
	 
	 
	 $("#delBtn").bind("click",function(){
		 var rows = $('#usergrid').datagrid("getSelections"); 
	     var copyRows = [];
        for ( var j= 0; j < rows.length; j++) {
        	copyRows.push(rows[j]);
        }
		 
        for(var i =0;i<copyRows.length;i++){
        	var row = copyRows[i];
        	var treeNode = {
					id : row.yhid,
					text:row.yhxm,
			 };
			 treeNode.attributes={
					 type : 'ry'
			 };
			 
			 var parentNode = $('#bmtree').tree('find', row.yhbm);
			 $('#bmtree').tree('append', {
					parent: parentNode.target,
					data: [treeNode]
			 });
            var index = $('#usergrid').datagrid('getRowIndex',copyRows[i]);
            $('#usergrid').datagrid('deleteRow',index); 
            cacheData[row.yhid] = {
            		 yhid : row.yhid,
					 bmdm : row.yhbm,
					 type : 'del'
			 }
        }
     });
	 
	 
	 
	 
	 $("#addBtn").bind("click",function(){
		 var nodes = $('#bmtree').tree('getChecked');
		 for(var i=0;i<nodes.length;i++){
			 var node = nodes[i];
			 var type = node.attributes.type;
			 if(type=="ry"){
				 var parentNode = $('#bmtree').tree('getParent',node.target);
				 $('#usergrid').datagrid('appendRow',{
					 yhid: node.id,
					 bmmc: parentNode.text,
					 yhxm: node.text,
					 yhbm: parentNode.id,
					 jsmc: '${roleText}',
					 js : '${roleId}'
				});
				 $('#bmtree').tree('remove',node.target);
				 cacheData[node.id] = {
						 yhid : node.id,
						 bmdm : parentNode.id,
						 type : 'add'
				 }
			 }
		 }
     });
	
	$('#bmList').combobox({
	    url:'bmList2.do',    
	    valueField:'bmdm',    
	    textField:'bmmc' 
	});
	
	$('#bmtree').tree({
		animate : true,
	    checkbox: true,  
	    lines :true,
	    url: '${path}/bmryList.do',
	    onBeforeLoad :function(node,param){
	    	param.bmdm=$('#bmList').combobox('getValue');
	    	param.js='${roleId }';
	    },
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
		idField:'yhid',
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
		    {field:'yhbm',title:'部门id',width:120,align:'center',hidden:'true'},
			{field:'yhxm',title:'人员',width:120,align:'center'},
			{field:'jsmc',title:'角色',width:120,align:'center'},
			{field:'js',title:'角色id',width:120,align:'center',hidden:'true'}
		]]
	});
	
	
})

</script>

</body>
</html>