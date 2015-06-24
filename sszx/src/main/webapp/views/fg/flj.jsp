<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>预约当事人提交材料</title>
<jsp:include page="/common/include.jsp" />
</head>
  
<body class="easyui-layout">
    <div id="search" style="width:250px;padding:5px;">
	    <table style="font-size:12px;">
	    	<tr>
	    		<td><span>当事人：</span></td>
	    		<td><input type="text" id="djr" name="djr"/></td>
	    	</tr>
	    	<tr>
	    		<td><span>案号：</span></td>
	    		<td><input type="text"  id="ah" name="ah"/></td>
	    	</tr>
	    </table>
    </div>
    
    <div data-options="region:'center'">
    	<table id="dsrZzSjTable"></table> 
    </div>
   
<script>
$(document).ready(function(){
	var user="${user}";
	var fydm="${fydm}";

	 
	$('#dsrZzSjTable').datagrid({
			rownumbers:false,
			title:'联系当事人交件列表',
			singleSelect:true,
			idField:'itemid',
			url:"${path}/fljTable.do?user="+user+"&fydm="+fydm,
			fit:true,
			border:false,
			fitColumns:true,
		    pagination:true,
			sortName:'ah',
			sortOrder:'desc',
			
			columns:[[
			    {field:'id',title:'流水号',width:50,align:'center',
			    	 formatter:function(id){
						return id.bh;
					 }
			    },
				{field:'ah',title:'案号',width:200,sortable:true,align:'center'},
				{field:'sjrBmmc',title:'承办部门',width:80,align:'center'},
				{field:'sjrXm',title:'承办人',width:80,align:'center'},
			    {field:'djr',title:'当事人',width:80,align:'center'},
			    {field:'djrq',title:'递交日期',width:80,align:'center'},	
			    {field:'action',title:'操作',width:180,align:'center',
					formatter:function(value,row,index){
						var sa=row.id.bh;
						var s = '<a style="color:red\"'
						        +'href=\"${path}/editFlj.do?bh='+sa+'\">修改</a> ';
						var d = '<a style="color:red\"'
						        +'href=\"${path}/fljTjSszx.do?bh='+sa+'\">通知当事人提交材料</a> ';
						var c = '<a style="color:red"'
								    +'href=\"${path}/cx4.do?bh='+sa+'\">撤销</a>';
						return s+d+c;
					}
			    }
			]],
			
			toolbar:[{
		        text:'新任务',
		        iconCls:'icon-add',
		        handler:function(){
		        	window.location.href="${path}/addFlj.do";
		        }
		    },{
		    	text:'查询',
		        iconCls:'icon-search',
		        handler:function(){
		        	$('#search').dialog('open');
		        }
			},{
				text:'所有任务',
		        iconCls:'icon-reload',
		        handler:function(){
		        	window.location.href="${path}/to_flj.do";
		        }
		    }]    
	 });
});
</script>

<script>
$('#search').dialog({
    title:'搜索',
    iconCls:'icon-search',
    
    buttons:[{
        text:'搜索',
        iconCls:'icon-ok',
        handler:function(){
        	 $.ajax({
        	     url:'${path}/searchFgSJ.do',
        	     type:'POST',
        	     data:{
        	    	 djr: $('#djr').val(),
        	    	 ah: $('#ah').val(),
        	    	 zt:9
        	     },
        	     dataType:'json',
        	     success: function(res) {
        	         $('#dsrZzSjTable').datagrid('loadData', res.data);
        	         $('#search').dialog('close');
        	     }
        	});
        }
    },{
        text:'取消',
        iconCls:'icon-cancel',
        handler: function(){
            $('#search').dialog('close');
        }
    }]
}).dialog('close');
</script>

</body>
</html>