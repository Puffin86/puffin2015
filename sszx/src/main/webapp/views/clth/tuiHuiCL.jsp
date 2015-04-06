<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>材料退回</title>
<jsp:include page="/common/include.jsp" />
</head>
  
<body style="background-color: #fff;" class="easyui-layout">

	<div data-options="region:'center'">
    	<table id="dsrZzSjTable"></table> 
    </div>

    <div id="search" style="width:250px;">
    <table style="font-size:12px;"><tr><td>
    <span>当事人：</span></td><td><input type="text"  id="djr" name="djr"/></td></tr>
    <tr><td><span>案号：</span></td><td><input type="text"  id="ah" name="ah"/></td></tr>
    </table>
    </div>
    
    <div id="tuiHui" style="width:430px;height:230px;">
    	<textarea id="thyj" rows="8" cols="50" style="margin-left:20px;margin-top:10px;font-size:12px;"></textarea>
    </div>
    
    
   
<script>
$(document).ready(function(){
	 var user = '${user}';
	 var fydm = '${fydm}';

	 $('#search').dialog('close');
	 //$('#add_xinming').hide();
	 
	 $('#dsrZzSjTable').datagrid({
			rownumbers:false,
			fit:true,
			border:false,
			title:'退回材料列表',
			singleSelect:true,
			striped:true,
			fitColumns:true,
			singleSelect:true,
			idField:'itemid',
			pagination:true,
			url:"${path}/tuiHuiCLTable.do?user="+user+"&fydm="+fydm,
			
			columns:[[
		    {field:'id',title:'流水号',width:50,align:'center',
		    	 formatter:function(id){
						return id.bh;
			}},
			{field:'ah',title:'案号',width:200,align:'center'},
			{field:'sjrBmmc',title:'承办部门',width:100,align:'center'},
			{field:'sjrXm',title:'承办人',width:50,align:'center'},
		    {field:'djr',title:'当事人',width:50,align:'center'},
		    {field:'djrq',title:'递交日期',width:100,align:'center'},	
		    {field:'action',title:'操作',width:200,align:'center',
				   formatter:function(value,row,index){
					var sa=row.id.bh;
					var s = '<a style="color:red\"'
					        +'href=\"huiTuiXG.do?bh='+sa+'\">修改</a> ';
					var c = '<a style="color:red\"'
					        +'href=\"#\"'
						    +'onClick=\"veiw('+sa+')\">退回原因</a> '; 
		            var b = '<a style="color:red\"'
					        +'href=\"cxtjTz.do?bh='+sa+'\">重新提交</a> ';  
					var d = '<a style="color:red\"'
						    +'href=\"cx2.do?bh='+sa+'\">撤销</a>';
					return s+c+b+d;
					}
		    }
			]],
			
			toolbar:[	        
			        {text:'查询',
			        iconCls:'icon-search',
			        handler:function(){
			        	$('#djr').val('');
        	    	    $('#ah').val('');
			        	$('#search').dialog('open');
			        }},
			        
			        {text:'所有任务',
			        iconCls:'icon-reload',
			        id:'allCL'}
			    ],
			    
		     pagination:true,
			 sortName:'ah',
			 sortOrder:'desc'     
			});

	 $('#allCL').click(function(event){
	 	$('#dsrZzSjTable').datagrid('reload');
		// window.location.href="tuiHuiCL.jsp";
		 //event.preventDefault();//ie6专用
	 });
	 }	 
);
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
        	     url:'searchFgSJ.do',
        	     type:'POST',
        	     data:{
        	     	   djr:$('#djr').val(),
        	    	   ah:$('#ah').val(),
        	    	   //djr:encodeURI(encodeURI($('#djr').val())),
        	    	   //ah:encodeURI(encodeURI($('#ah').val())),
        	    	   zt:3
        	      },//注意大小写data
        	     dataType:'json',
        	     success:function (res) {
        	       $('#dsrZzSjTable').datagrid('loadData',res.data);
        	       $('#search').dialog('close');
        	     }
        	});
        	
        	$.post('${path}/searchFgSJ.do', {
	        		djr: $('#djr').val(),
	        	    ah: $('#ah').val(),
	        	    zt: 3
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
});
</script>

 <script>
$('#tuiHui').dialog({
    title:'退回原因',
    iconCls:'icon-tip',
    closed:true,
    buttons:[{
        text:'确定',
        iconCls:'icon-ok',
        handler:function(){
            $('#tuiHui').dialog('close');
        }
    }]
});

function veiw(id){	
	 $.ajax({
	     url:'searchHtyj.do',
	     type:'POST',
	     data:{id:id
	      },//注意大小写data
	     dataType:'json',
	     success:function (res) {
	    	 $('#thyj').val(res.htyj);
	    	 $('#thyj').attr('readOnly','true');
	    	 $('#tuiHui').dialog('open');
	     }
});	
}
</script>


</body>
</html>