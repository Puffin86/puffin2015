<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>已接收材料</title>
<jsp:include page="/common/include.jsp" />
<script type="text/javascript">
$(function(){
	var user = '${user}';
	var fydm = '${fydm}';
	
	$('#search').dialog({
	    title:'搜索',
	    iconCls:'icon-search',
	    modal: true,
	    buttons:[{
	        text:'搜索',
	        iconCls:'icon-ok',
	        handler:function(){
	        	$.post('${path}/searchFgSJ.do', {
	        		djr: $('#djr').val(),
	        	    ah: $('#ah').val(),
	        	    zt: 1
	        	}, function (res) {
        	        $('#dsrZzSjTable').datagrid('loadData',res.data);
        	        $('#search').dialog('close');
	        	}, 'json');
	        }
	    },{
	        text:'取消',
	        iconCls:'icon-cancel',
	        handler:function(){
	        	$('#search').dialog('close');
	        }
	    }]
	}).dialog('close');
	
	$('#dsrZzSjTable').datagrid({
		rownumbers:false,
		fit:true,
		border:false,
		title:'已接收材料列表',
		singleSelect:true,
		striped:true,
		fitColumns:true,
		singleSelect:true,
		idField:'itemid',
		pagination:true,
		sortName:'ywlx',
		sortOrder:'desc',
		url:"${path}/fgYjsCl.do?user="+user+"&fydm="+fydm+"&lclx=flq",
				
		columns:[[
		    {field:'id',title:'流水号',width:100,align:'center',hidden:true,
		    	formatter:function(id){
					return id.bh;
				}
		    },
		    {field:'lclx',title:'业务类型',width:120,align:'center',sortable:"true",
		    	formatter:function(lclx){
					if(lclx=="flq"){
						return "预约领取";
					}else if(lclx=="flj"){
						return "预约提交";
					}if(lclx=="dzz"){
						return "主动送件";
					}
				}
		    },
			{field:'sx',title:'时限',width:120,align:'center'},
			{field:'ah',title:'案号',width:120,align:'center',sortable:"true"},
			{field:'sjrBmmc',title:'承办部门',width:100,align:'center'},
			{field:'sjrXm',title:'承办人',width:80,align:'center'},
	    	{field:'djr',title:'当事人',width:80,align:'center'},
	    	{field:'zjrXm',title:'中心经办人',width:80,align:'center'},
	    	{field:'zjrq',title:'中心经办日期',width:100,align:'center'},	
	    	{field:'action',title:'操作',width:120,align:'center',
			    formatter:function(value,row,index){
					var sa = row.id.bh;
					var s = ' <a style="color:red\"'
				        +'href=\"#\" '
				        +'onClick=\"Pdf('
				        +sa+');">送达回证</a> ';
					return s;
			    }
	    	}
		]]
		/*
		,toolbar:[{
			text:'查询',
	        iconCls:'icon-search',
	        handler:function(){
	        	$('#search').dialog('open');
	        }
		},{
			text:'所有任务',
	        iconCls:'icon-reload',
	        handler:function(){
	        	$('#dsrZzSjTable').datagrid('reload');
	        }
	    }]
		*/
	});
	
});

function Pdf(bh){
	$.ajax({
  	     url:'openPdf.do',
  	     type:'POST',
  	     data:{
  	    	 bh : bh
  	      },//注意大小写data
  	     dataType:'json',
  	     success:function (res) {
        	url='pdf_view.jsp';
       		window.open(url,"new", "height=600px,width=650px,toolbar=no,status=no,menubar=no,scrollbars=yes,resizable=no");
  	     }
  });
	
}
</script>
</head>

<body class="easyui-layout" style="background-color: #fff;">
<div data-options="region:'center'">
	<table id="dsrZzSjTable"></table> 
</div>

<div id="search" class="easyui-dialog" style="width:250px;padding:5px;">
    <table style="font-size:12px;">
    	<tr>
    		<td><span>当事人：</span></td>
    		<td><input type="text"  id="djr" name="djr"/></td>
    	</tr>
    	<tr>
    		<td><span>案号：</span></td>
    		<td><input type="text"  id="ah" name="ah"/></td>
    	</tr>
    </table>
</div>
</body>
</html>