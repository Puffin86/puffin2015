<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>接收材料</title>
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
	        	$.post('${path}/searchDsrZzSj.do', {
	        		djr: $('#djr').val(),
	        	    ah: $('#ah').val(),
	        	    fydm : fydm,
	        	    user : user
	        	}, function (res) {
        	        $('#dsrZzSjTable').datagrid('loadData',res);
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
			title:'接收当事人材料',
			singleSelect:true,
			striped:true,
			fitColumns:true,
			singleSelect:true,
			idField:'itemid',
			pagination:true,
			url:"${path}/jsdsrzdsj.do?user="+user+"&fydm="+fydm,
			
			columns:[[
		    {field:'id',title:'流水号',width:50,align:'center',
		    	 formatter:function(id){
						return id.bh;
			}},
			{field:'ah',title:'案号',width:200,align:'center',sortable:"true"},
			{field:'sjrBmmc',title:'承办部门',width:100,align:'center'},
			{field:'sjrXm',title:'承办人',width:80,align:'center'},
		    {field:'djr',title:'当事人',width:80,align:'center'},
		    {field:'djrq',title:'递交日期',width:90,align:'center',sortable:"true"},	
		    {field:'action',title:'操作',width:158,align:'center',
				   formatter:function(value,row,index){
					var sa=row.id.bh;
					var d = '<a style="color:red\"'
				        +'href=\"${path}/zhJiaoDsrZzSj.do?bh='+sa+'\">转交</a> ';
					var s = '<a style="color:red\"'
					        +'href=\"${path}/editDsrZzSj.do?bh='+sa+'\">修改</a> ';
			        var f = '<a style="color:red\"'
				        +'href=\"#\" '
				        +'onClick=\"Open('
				        +sa+');">扫描</a> ';
			        var e = '<a style="color:red\"'
				        +'href=\"#\" '
				        +'onClick=\"Word('
				        +sa+');">转接单</a> ';
					var c = '<a style="color:red\"'
				        +'href=\"${path}/cx.do?bh='+sa+'\">删除</a> ';
					return d+s+f+e+c;
					}
		    }
			]],
			
			toolbar:[{//工具条
			        text:'新任务',
			        iconCls:'icon-add',
			        id:'addDsrCL',
			        handler:function(){
			        	window.location.href="${path}/adddsrcl.do";
			        }},
			        
			        {text:'查询',
			        iconCls:'icon-search',
			        handler:function(){
			        	$('#djr').val('');
			        	$('#ah').val('');
			        	$('#search').dialog('open');
			        }},
			        
			        {text:'所有任务',
			        iconCls:'icon-reload',
			        id:'allCL',
			        handler:function(){
			        	$('#dsrZzSjTable').datagrid('reload');
			        }
	        }],
			    
		     pagination:true,
			 sortName:'ah',
			 sortOrder:'desc'     
			});
	
	});
	
	
	function Open(bh){
	 //window.location.href='${path}/fj.do?bh='+bh;
	 url='${path}/fj.do?bh='+bh;
	 window.open(url,"new",
			 "height=600px,width=650px,toolbar=no,status=no,menubar=no,scrollbars=yes,resizable=yes");
	}
	
	function Word(bh){
		 url='${path}/word_zjqd.do?bh='+bh+'&tool=show';
		 window.open(url,"new",
				 "height=600px,width=650px,toolbar=no,status=no,menubar=no,scrollbars=yes,resizable=yes");
		
	}
	
</script>
</head>
<body style="background-color: #fff;" class="easyui-layout">

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