<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>字典维护</title>
<jsp:include page="/common/include.jsp" />
</head>

<body style="background-color: #fff;" class="easyui-layout">  

	<div id="zdedit" style="width:250px;height:150px;">
			<table>
				<tr>
					<td>字典编码：</td>
					<td><input class="easyui-validatebox" required="true" id="zdbm"  style="margin-left:10px;width:150px" type="text"></input></td>
				</tr>
				<tr>
					<td>字典名称：</td>
					<td><input class="easyui-validatebox" required="true" id="zdmc"  style="margin-left:10px;width:150px" type="text"></input></td>
				</tr>
			</table>
		 </div>
		 
	<div id="zdmxedit" style="width:280px;height:200px;">
			<table>
				<tr>
					<td>字典编码：</td>
					<td><input class="easyui-validatebox" required="true" id="zdmx_zdbm"  style="margin-left:10px;width:150px" type="text"></input></td>
				</tr>
				<tr>
					<td>字典名称：</td>
					<td><input class="easyui-validatebox" required="true" id="zdmx_zdmc"  style="margin-left:10px;width:150px" type="text"></input></td>
				</tr>
				<tr>
					<td>字典项编码：</td>
					<td><input class="easyui-validatebox" required="true" id="zdmxbm"  style="margin-left:10px;width:150px" type="text"></input></td>
				</tr>
				<tr>
					<td>字典项名称：</td>
					<td><input class="easyui-validatebox" required="true" id="zdmxmc"  style="margin-left:10px;width:150px" type="text"></input></td>
				</tr>
				<tr>
					<td>父节点编码：</td>
					<td><input id="parent"  style="margin-left:10px;width:150px" type="text"></input></td>
				</tr>
			</table>
	</div>
  
	<div id="p" class="easyui-panel" data-options="region:'center'" title="字典设置" style="width:100%;height:100%; padding-top: 7px;">
		<div style="width:100%;height:40px; padding-left: 7px;" >
			字典列表：
			<input id="cc" name="zdList" >
			<a id="add"  iconCls="icon-save">新增</a>
			<a id="update"  iconCls="icon-save">编辑</a>
			<a id="del"  iconCls="icon-save">删除</a>
		</div>
		<div style="width:100%;height:100%" >
			<table id="zdmxgrid" ></table> 
		</div>
	</div>
<script>
$(function(){
	 $('#add').linkbutton({});
	 $('#update').linkbutton({});
	 $('#del').linkbutton({});
	 
	 $('#cc').combobox({ 
		    url:'zdcx.do',    
		    valueField:'zdbm',    
		    textField:'zdmc'   
		});  
	 
	 $('#zdedit').dialog({
		    title:'字典编辑',
		    iconCls:'icon-add',
		    closed:true,
		    buttons:[{
		        text:'保存',
		        iconCls:'icon-ok',
		        handler:function(){
		        	$.ajax({
		          	     url:'editZd.do',
		          	     type:'POST',
		          	     data:{  
		          	    	 zdbm:$('#zdbm').val(),
		          	    	 zdmc:$('#zdmc').val()
		          	     },//注意大小写data
		          	     dataType:'json',
		          	     success:function (data) {
		          	    	$('#cc').combobox('reload'); 
		          	    	$('#zdedit').dialog('close');
		          	     }
		          	 });
		        	     
		        }
		    },{
		        text:'取消',
		        iconCls:'icon-ok',
		        handler:function(){
		        	$('#zdedit').dialog('close');
		        }
		    }]
	   }).dialog('close');
	 
	 
	 $('#zdmxedit').dialog({
		    title:'字典项编辑',
		    iconCls:'icon-add',
		    closed:true,
		    buttons:[{
		        text:'保存',
		        iconCls:'icon-ok',
		        handler:function(){
		        	$.ajax({
		          	     url:'editZdmx.do',
		          	     type:'POST',
		          	     data:{
		          	    	 zdbm:$('#cc').combobox('getValue'),
		          	    	 zdmxbm:$('#zdmxbm').val(),
		          	    	 zdmxmc:$('#zdmxmc').val(),
		          	    	 parent:$('#parent').val()
		          	     },//注意大小写data
		          	     dataType:'json',
		          	     success:function (data) {
		          	    	$('#zdmxgrid').combobox('reload'); 
		          	    	$('#zdmxedit').dialog('close');
		          	     }
		          	 });
		        	     
		        }
		    },{
		        text:'取消',
		        iconCls:'icon-ok',
		        handler:function(){
		        	$('#zdmxedit').dialog('close');
		        }
		    }]
	   }).dialog('close');
	 
	 
	 $('#zdmxgrid').datagrid({
			rownumbers:false,
			fit:true,
			border:true,
			title:'字典明细',
			singleSelect:true,
			striped:true,
			fitColumns:true,
			singleSelect:true,
			idField:'itemid',
			pagination:true,
			sortName:'zdmxbm',
			sortOrder:'desc',
			url:"${path}/zdmxcx.do",
			queryParams : {
				zdbm : "aaa",
				zdmc : ""
			},
			columns:[[
			    {field:'id',title:'ID',width:100,align:'center'},
			    {field:'zdmxbm',title:'编码',width:120,align:'center',sortable:"true"},
				{field:'zdmxmc',title:'名称',width:120,align:'center'},
				{field:'parent',title:'父编码',width:120,align:'center',sortable:"true"},
		    	{field:'action',title:'操作',width:120,align:'center',
				    formatter:function(value,row,index){
						var sa = row.id.bh;
						var s = ' <a style="color:red\"'
					        +'href=\"#\" '
					        +'onClick=\"editZdmx();">编辑</a> ';
				        var e = ' <a style="color:red\"'
					        +'href=\"#\" '
					        +'onClick=\"delZdmx();">删除</a> ';
						return s+e;
				    }
		    	}
			]]
			,toolbar:[{
				text:'新增',
		        iconCls:'icon-add',
		        handler:function(){
		        	$('#zdmx_zdbm').val($('#cc').combobox('getValue'));
		   	     	$('#zdmx_zdmc').val($('#cc').combobox('getText'));
		        	$('#zdmxedit').dialog('open');
		        }
			}]
			
		});
	 
	 
	 
     $("#add").bind("click",function(){
    	 $('#zdbm').val('');
	     $('#zdmc').val('');
    	 $('#zdedit').dialog('open');
     });  
     
	 $("#update").bind("click",function(){
		 $('#zdbm').val($('#cc').combobox('getValue'));
	     $('#zdmc').val($('#cc').combobox('getText'));
	     $('#zdedit').dialog('open');
     });  
     
	$("#del").bind("click",function(){
		
		if($('#cc').combobox('getValue')==''){
			return;
		}
		
		$.ajax({
     	     url:'delZd.do',
     	     type:'POST',
     	     data:{  
     	    	 zdbm:$('#cc').combobox('getValue'),
     	    	 zdmc : $('#cc').combobox('getText')
     	     },//注意大小写data
     	     dataType:'json',
     	     success:function (data) {
     	    	$('#cc').combobox('reload'); 
     	     }
     	 });
	});  
     
	 
})
</script>

</body>
</html>