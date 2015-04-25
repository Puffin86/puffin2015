<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>字典维护</title>
<jsp:include page="/common/include.jsp" />
</head>

<body style="background-color:#fff;" class="easyui-layout">  

	<div id="zdedit" style="width:250px;height:150px;">
		<table>
			<tr>
				<td>字典编码：</td>
				<td><input class="easyui-validatebox" required="true" id="zdbm"  style="margin-left:10px;width:150px" type="text"/></td>
			</tr>
			<tr>
				<td>字典名称：</td>
				<td><input class="easyui-validatebox" required="true" id="zdmc"  style="margin-left:10px;width:150px" type="text"/></td>
			</tr>
		</table>
	</div>
		 
	<div id="zdmxedit" style="width:280px;height:150px;">
		<table>
			<tr>
				<td>字典项编码：</td>
				<td><input class="easyui-validatebox" required="true" id="zdmxbm"  style="margin-left:10px;width:150px" type="text"/></td>
			</tr>
			<tr>
				<td>字典项名称：</td>
				<td><input class="easyui-validatebox" required="true" id="zdmxmc"  style="margin-left:10px;width:150px" type="text"/></td>
			</tr>
			<tr>
				<td>父节点编码：</td>
				<td><input id="parent"  style="margin-left:10px;width:150px" type="text"/></td>
			</tr>
		</table>
	</div>
  
	<div id="p" class="easyui-panel" data-options="region:'center'" title="字典配置" style="width:100%;height:100%;padding-top:7px;">
		字典列表：
		<input id="cc" name="zdList" />
		<a id="add"  iconCls="icon-add">新增</a>
		<a id="update"  iconCls="icon-save">编辑</a>
		<a id="del"  iconCls="icon-save">删除</a>
		<div style="position: absolute;top: 70px;right: 1px;bottom: 1px;left: 1px;">
			<table id="zdmxgrid" data-options="fit:true,border:false"></table> 
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
		    textField:'zdmc' ,
		    onSelect :function(record){
		    	$('#zdmxgrid').datagrid('load',{
		    		zdbm : record.zdbm,
    				zdmc : record.zdmc
		    	});
		    },
		    onLoadSuccess:function(data){
		    	//if(data.length!=0){
		    		$('#zdmxgrid').datagrid({
		    			rownumbers:false,
		    			title:'字典明细',
		    			singleSelect:true,
		    			striped:true,
		    			fitColumns:true,
		    			idField:'zdmxbm',
		    			border:true,
		    			//pagination:true,
		    			url:"zdmxcx.do",
		    			queryParams : {
		    				zdbm : $('#cc').combobox('getValue'),
		    				zdmc : $('#cc').combobox('getText')
		    			},
		    			columns:[[
		    			    //{field:'id',title:'内码',width:100,align:'center'},
		    			    {field:'zdmxbm',title:'字典项编码',width:120,align:'center'},
		    				{field:'zdmxmc',title:'字典项名称',width:120,align:'center'},
		    				{field:'parent',title:'父编码',width:120,align:'center'}
		    			]]
		    			,toolbar:[{
		    				text:'新增',
		    		        iconCls:'icon-add2',
		    		        handler:function(){
		    		        	$('#zdmxbm').val('');
		    		        	$('#zdmxmc').val('');
		    		        	$('#parent').val('');
		    		        	$('#zdmxedit').dialog('open');
		    		        }
		    			},{
		    				text:'编辑',
		    		        iconCls:'icon-edit',
		    		        handler:function(){
		    		        	var row = $('#zdmxgrid').datagrid('getSelected');
		    		        	$('#zdmxbm').val(row.zdmxbm);
		    		            $('#zdmxmc').val(row.zdmxmc);
		    		        	$('#zdmxedit').dialog('open');
		    		        }
		    			},{
		    				text:'删除',
		    		        iconCls:'icon-remove',
		    		        handler:function(){
		    		        	var row = $('#zdmxgrid').datagrid('getSelected');
		    		        	var mxbm = row.zdmxbm;
		    		        	var mxmc = row.zdmxmc;
		    		        	$.messager.confirm('确认框', '确定要删除该字典项吗？', function(r){
		    		        		if (r){
		    		        			$.ajax({
		    		        	     	     url:'delzdmx.do',
		    		        	     	     type:'POST',
		    		        	     	     data:{  
		    		        	     	    	zdbm:$('#cc').combobox('getValue'),
		    		        	     	    	zdmxbm:mxbm,
		    		        	     	    	zdmxmc:mxmc
		    		        	     	     },
		    		        	     	     dataType:'json',
		    		        	     	     success:function (data) {
		    		        	     	    	$('#zdmxgrid').datagrid('load',{
		    		        			    		zdbm : $('#cc').combobox('getValue')
		    		        			    	});
		    		        	     	     }
		    		        	     	 });
		    		        		}
		    		        	});
		    		        }
		    			},{
		    				text:'刷新',
		    		        iconCls:'icon-reload',
		    		        handler:function(){
		    		        	$('#zdmxgrid').datagrid('load',{
	        			    		zdbm : $('#cc').combobox('getValue')
	        			    	});
		    		        }
		    			}]
		    			
		    		});
		    	//}
		    }
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
		          	    	$('#cc').combobox({
		          			    url:'zdcx.do',    
		          			    valueField:'zdbm',    
		          			    textField:'zdmc' ,
		          			    onLoadSuccess:function(data){
		          			    	$("#cc").combobox('select',$('#zdbm').val());
		          			    	$('#zdmxgrid').datagrid('load',{
		        			    		zdbm : $('#cc').combobox('getValue')
		        			    	});
		          			    }
		          			});  
		          	    	$('#zdedit').dialog('close');
		          	     }
		          	 });
		        	     
		        }
		    },{
		        text:'取消',
		        iconCls:'icon-cancel',
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
		          	     url:'saveZdmx.do',
		          	     type:'POST',
		          	     data:{
		          	    	 zdbm:$('#cc').combobox('getValue'),
		          	    	 zdmxbm:$('#zdmxbm').val(),
		          	    	 zdmxmc:$('#zdmxmc').val(),
		          	    	 parent:$('#parent').val()
		          	     },//注意大小写data
		          	     dataType:'json',
		          	     success:function (data) {
		          	    	$('#zdmxedit').dialog('close');
		          	    	$('#zdmxgrid').datagrid('load',{
        			    		zdbm : $('#cc').combobox('getValue')
        			    	});
		          	     }
		          	 });
		        	     
		        }
		    },{
		        text:'取消',
		        iconCls:'icon-cancel',
		        handler:function(){
		        	$('#zdmxedit').dialog('close');
		        }
		    }]
	   }).dialog('close');
	 
	 
	 
	 
	 
	 
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
		if($('#cc').combobox('getData').length==0){
			return;
		}
		
		$.messager.confirm('确认框', '确定要删除该字典吗？', function(r){
			if (r){
				$.ajax({
		     	     url:'delZd.do',
		     	     type:'POST',
		     	     data:{  
		     	    	 zdbm:$('#cc').combobox('getValue'),
		     	    	 zdmc : $('#cc').combobox('getText')
		     	     },//注意大小写data
		     	     dataType:'json',
		     	     success:function (data) {
		     	    	//重新渲染解决combobox的数据删除BUG
		     	    	$('#cc').combobox({ 
		     			    url:'zdcx.do',    
		     			    valueField:'zdbm',    
		     			    textField:'zdmc',
		     			   onLoadSuccess : function(data){
		     				  if(data.length!=0){
		     			    		$("#cc").combobox('select',data[0].zdbm);
		     			    	}
		     				  
		     				 $('#zdmxgrid').datagrid('load',{
	        			    		zdbm : $('#cc').combobox('getValue')
	        			     });
		     			   }
		     			});
		     	     }
		     	 });
			}
		});
		
	});  
});
</script>

</body>
</html>
