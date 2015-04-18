<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>常用材料清单</title>
<jsp:include page="/common/include.jsp" />
<script type="text/javascript">
$(function(){
});

function actions(value, row, index){
	var id = row.clbh;
	var s1 = '<a href=\"javascript:editAction('+id+')\">修改</a> ';
	var s2 = '<a href=\"javascript:delAction('+id+')\">删除</a>';
	return s1+s2;
}

function delAction(id){
	if(!confirm('确定删除？')){
		return;
	}
	
	$.get('${path }/clqd/delete.do', {
		id : id
	}, function(data){
		if(data.success){
			$('#datagrid1').datagrid('reload');
		}else{
			$.messager.alert('提示', '删除失败', 'error');
		}
	}, 'json');
}
</script>
</head>

<body class="easyui-layout" style="background:#fff;">
    
    <div data-options="region:'center',border:false">
	    <table id="datagrid1" class="easyui-datagrid" title="常用材料清单" 
	    	data-options="fitColumns:true,singleSelect:true,fit:true,
	    		striped:true,url:'${path }/clqd/list.do',toolbar:'#toolbar1'">
	        <thead>
	            <tr>
	                <th data-options="field:'clbh',width:50,align:'center'">编号</th>
	                <th data-options="field:'clmc',width:100">材料名称</th>
	                <th data-options="field:'action',width:100,align:'center',formatter:actions">操作</th>
	            </tr>
	        </thead>
	    </table>
    </div>

	<div id="toolbar1">
        <a href="javascript:addAction()" class="easyui-linkbutton" 
				iconCls="icon-add2" plain="true">新增</a>
    </div>
    
    <jsp:include page="add_clqd.jsp" />
    <jsp:include page="edit_clqd.jsp" />
  
</body>
</html>