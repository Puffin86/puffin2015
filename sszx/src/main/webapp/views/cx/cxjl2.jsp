<%@ page language="java" pageEncoding="utf-8"%>
<%@ page language="java" import="com.bsoft.sszx.dao.*,com.bsoft.sszx.entity.user.*" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<jsp:include page="/common/include.jsp" />
</head>
  <%
    String user=(String)session.getAttribute("user");
    String fydm=(String)session.getAttribute("fydm");
    UserDao userDao=new UserDao();
    User u=userDao.findUserById(user, fydm);
    String lx=u.getJs();//得到角色
    String sjrbm=u.getYhbm();
    
    
    
  %>  
  <body style="background-color: #fff;" class="easyui-layout">     
    <div id="p" class="easyui-panel" data-options="region:'center'" title="查询案件材料递交记录" style="width:100%;height:100%;padding-top:7px;">
	    <table width="100%" border="0" cellpadding="2" cellspacing="0">
	    	<tr>
	    		<td>案号：</td>
	    		<td><input id="ah" style="width:150px" type="text"/></td>
	    		<td>当事人：</td>
	    		<td><input style="width:150px"  id="dsr" type="text"/></td>
	    	</tr>
	    	<tr>
	    		<td>承办人：</td>
	    		<td>
	    			<input style="width:150px"  id="cbrText" type="text" readOnly="readOnly"/>
	    			<a id="cbr_search" style="margin-top:-5px"  iconCls="icon-add" onClick="openSearch()"></a>
	    			<input   id="cbr" style="display:none;" type="text" />
	    		</td>
	    		<td>承办部门：</td>
	    		<td>
	    			<input style="width:150px"  id="cbbmText" type="text" readOnly="readOnly"/>
	    			<input  id="cbbm" type="text" style="display:none;"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>中心经办人：</td>
	    		<td><input style="width:150px"  id="zxjbr" type="text"/></td>
	    		<td>中心经办时间：</td>
	    		<td><input editable="false" class="easyui-datebox" id="zxjbsj" type="text"/></td>
	    	</tr>
	    	<tr>
	    		<td>业务类型：</td>
	    		<td><input style="width:150px"  id="ywlx" /></td>
	    		<td>时限：</td>
	    		<td><input style="width:150px"  id="sx" type="text"/></td>
	    	</tr>
	    	<tr>
	    		<td colspan="4" align="center">
	    			<a id="searchBtn" onclick="searchAj()" >查询</a>
	    			<a id="exportBtn" onclick="exportAj()" >导出</a>
	    			<a id="totalBtn" onclick="totalData()" >汇总</a>
	    			<a id="printBtn" onclick="print()" >打印</a>
	    		</td>
	    	</tr>
	    </table>
		<table id="grid" style="width:100%;height:100%"></table> 
    </div>
    <div id="cbr_dg" style="width:250px;height:300px;">
     <input id="userSearch" style="margin-left:5px;margin-top:5px" type="text">
       <a id="search_bt" onclick="searchUser()" iconCls="icon-search"></a>
       <ul id="searchList"></ul>
    </div> 
    
    <div id="totalDataDiv" style="width:800px;height:600px;">
     	<table width="100%">
     		<tr>
     			<td>条件设置：</td>
     			<td><input type="checkbox" value="lclx" name="cb"/>业务类型 </td>
     			<td><input type="checkbox" value="zjr"  name="cb"/>中心经办人 </td>
     			<td><input type="checkbox" value="sjrBmmc"  name="cb"/>承办部门</td>
     			<td><input type="checkbox" value="sjrXm"  name="cb"/>承办人</td>
     			<td>
     				<a id="dototalBtn" onclick="dototal()" >汇总</a>
	    			<a id="exportTotalBtn" onclick="exportTotal()" >转EXCEL</a>
     			</td>
     		</tr>
     	</table>
     	<table id="totalgrid" style="width:700px;height:500px"></table> 
     	<table width="100%">
     		<tr>
     			<td>合计：</td>
     		</tr>
     	</table>
    </div> 
    
</body>

<script>

var columnsAll = [{field:'lclx',title:'业务类型',width:120,align:'center',
	formatter:function(lclx){
		if(lclx=="dzz"){
			return "主动送件";
		}else if(lclx=="flq"){
			return "预约领取";
		}else if(lclx=="flj"){
			return "预约提交";
		}
	 }	
},
{field:'sjrBmmc',title:'承办部门',width:120,align:'center'},
{field:'sjrXm',title:'承办人',width:120,align:'center'},
{field:'zjr',title:'中心经办人',width:120,align:'center'},
{field:'sl',title:'数量',width:120,align:'center'}];

$('#ywlx').combobox({
    url:'ywlxcx.do',    
    valueField:'zdmxbm',    
    textField:'zdmxmc' 
});

$('#searchBtn').linkbutton({});
$('#exportBtn').linkbutton({}).hide();
$('#printBtn').linkbutton({}).hide();
$('#cbr_search').linkbutton({});
$('#search_bt').linkbutton({});
$('#totalBtn').linkbutton({}).hide();
$('#dototalBtn').linkbutton({});
$('#exportTotalBtn').linkbutton({});


$('#cbr_dg').dialog({
    title:'添加承办人',
    iconCls:'icon-search',
    closed:true}).dialog('close');

$('#totalDataDiv').dialog({
    title:'数据汇总',
    modal: true,
    iconCls:'icon-search',
    closed:true}).dialog('close');


$('#zxjbsj').datebox({    
    required:false   
});  

$('#totalgrid').datagrid({
	rownumbers:false,
	fit:true,
	border:true,
	singleSelect:true,
	striped:true,
	fitColumns:true,
	singleSelect:true,
	idField:'lclx',
	//pagination:true,
	url:"totalSearch.do",
	queryParams : {
		ah:$('#ah').val(),
		dsr:$('#dsr').val(),
		cbr:$('#cbr').val(),
		cbbm:$('#cbbm').val(),
		jbr:$('#zxjbr').val(),
		jbsj:$('#zxjbsj').datebox('getValue'),
		ywlx:$('#ywlx').combobox('getValue'),
		sx:$('#sx').val(),
		lx:'<%=lx%>'
	},
	columns:[columnsAll]
});


$('#grid').datagrid({
	rownumbers:false,
	fit:true,
	border:true,
	title:'查询结果列表',
	singleSelect:true,
	striped:true,
	fitColumns:true,
	singleSelect:true,
	idField:'ah',
	pagination:true,
	url:"clSearch.do",
	queryParams : {
		ah:$('#ah').val(),
		dsr:$('#dsr').val(),
		cbr:$('#cbr').val(),
		cbbm:$('#cbbm').val(),
		jbr:$('#zxjbr').val(),
		jbsj:$('#zxjbsj').datebox('getValue'),
		ywlx:$('#ywlx').combobox('getValue'),
		sx:$('#sx').val(),
		lx:'<%=lx%>',
	    sjrbm:'<%=sjrbm%>'
	},
	onLoadSuccess : function(){
		var len = $('#grid').datagrid('getData').rows.length;
		if(len>0){
			$('#exportBtn').show();
			$('#totalBtn').show();
			$('#printBtn').hide();
		}else{
			$('#exportBtn').hide();
			$('#totalBtn').hide();
			$('#printBtn').hide();
		}
	},
	onDblClickRow :function(rowIndex, rowData){
		 var url='cxjlMx.do?yhid='+rowData.id.bh;
		 window.open(url,"new","height=400px,width=600px,toolbar=no,status=no,menubar=no,scrollbars=no,resizable=no");
	},
	columns:[[
	    {field:'lclx',title:'业务类型',width:120,align:'center',sortable:"true",
	    	formatter:function(lclx){
				if(lclx=="dzz"){
					return "主动送件";
				}else if(lclx=="flq"){
					return "预约领取";
				}else if(lclx=="flj"){
					return "预约提交";
				}
			 }	
	    },
	    {field:'sx',title:'时限',width:120,align:'center'},
	    {field:'ah',title:'案号',width:120,align:'center',sortable:"true"},
	    {field:'sjrBmmc',title:'承办部门',width:120,align:'center'},
	    {field:'sjrXm',title:'承办人',width:120,align:'center'},
	    {field:'djr',title:'当事人',width:120,align:'center'},
	    {field:'zjr',title:'中心经办人',width:120,align:'center'},
	    {field:'zjrq',title:'中心经办日期',width:120,align:'center'},
		{field:'action',title:'操作',width:120,align:'center',
	    	formatter:function(value,row,index){
				var sa=row.id.bh;
				var d = '<a style="color:red\"'
				        +'href=\"${path}/flqTjSsZx.do?bh='+sa+'\">查看附件</a> ';
				return d;
			 }	
		}
	]]
});

$('#searchList').tree({  
    checkbox: false,
    onClick: function(node){		 
		if(node.attributes.leaf=="true"){
			 var name=node.text.split("：");
			 var bm=name[1];				 
			 $('input[id=cbbmText]').val(bm);
			 var xm=name[0];				 
			 $('input[id=cbrText]').val(xm);
			 $('input[id=cbbmbm]').val(node.attributes.yhbm);
			 $('input[id=cbr]').val(node.attributes.yhid);
			 $('#cbr_dg').dialog('close');
		 }
    }
}); 

function searchAj(){
	$('#grid').datagrid('load',{
		ah:$('#ah').val(),
		dsr:$('#dsr').val(),
		cbr:$('#cbr').val(),
		cbbm:$('#cbbm').val(),
		jbr:$('#zxjbr').val(),
		jbsj:$('#zxjbsj').datebox('getValue'),
		ywlx:$('#ywlx').combobox('getValue'),
		sx:$('#sx').val(),
		lx:'<%=lx%>',
	    sjrbm:'<%=sjrbm%>'
	});
}

function openSearch(){
	$('#userSearch').val('');
	$('#cbr_dg').dialog('open');
	$('#userSearch').focus();
}

function exportAj(){
	var ss = $('#grid').datagrid('options').columns[0];
	var columnArr = new Array();
	
	for (var i=0;i<ss.length;i++){
		var obj = ss[i];
		var s = obj.title+"@"+obj.field;
		columnArr.push(s);
	}
	
	var ah = $('#ah').val();
	var dsr=$('#dsr').val();
	var cbr=$('#cbr').val();
	var cbbm=$('#cbbm').val();
	var jbr=$('#zxjbr').val();
	var jbsj=$('#zxjbsj').datebox('getValue');
	var ywlx=$('#ywlx').combobox('getValue');
	var sx=$('#sx').val();
	
	var url = "export_cxjl.do?lx="+<%=lx%>+"&ah="+ah+"&dsr="+dsr
	+"&cbr="+cbr+"&cbbm="+cbbm+"&jbr="+jbr+"&jbsj="+jbsj
	+"&ywlx="+ywlx+"&sx="+sx;
	window.location.href = url;
}

function totalData(){
	$('#totalDataDiv').dialog('open');
}

function dototal(){
		
	var qx = $("input[name='cb']:checked").map(function () {
        return $(this).val();
    }).get().join('#');
	
	var groupBy='';
	for(var i=0;i<columnsAll.length;i++){
		var field = columnsAll[i].field;
		if(field=="sl"){
			continue;
		}else if(qx.indexOf(field)!=-1){
			$('#totalgrid').datagrid('hideColumn',field);
		}else{
			$('#totalgrid').datagrid('showColumn',field);
			groupBy=groupBy+field+",";
		}
	}
	$('#totalgrid').datagrid('load',{
		ah:$('#ah').val(),
		dsr:$('#dsr').val(),
		cbr:$('#cbr').val(),
		cbbm:$('#cbbm').val(),
		jbr:$('#zxjbr').val(),
		jbsj:$('#zxjbsj').datebox('getValue'),
		ywlx:$('#ywlx').combobox('getValue'),
		sx:$('#sx').val(),
		lx:'<%=lx%>',
		groupBy:groupBy
	})
}

function exportTotal(){
	
}

function searchUser(){
	var userName=$('#userSearch').val();
	if(userName!=''){
		$.ajax({
	  	     url:'userSearch.do',
	  	     type:'POST',
	  	     data:{name:encodeURI(encodeURI(userName))},//注意大小写data
	  	     dataType:'json',
	  	     success:function (data) {
	  	       $('#searchList').tree('loadData',data.data);
	  	     }
	  	});
	}
}

</script>
</html>