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
	    			<input style="width:150px"  id="cbrText" type="text" />
	    		</td>
	    		<td>承办部门：</td>
	    		<td>
	    			<input style="width:150px"  id="cbbmText" type="text" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>中心经办人：</td>
	    		<td><input style="width:150px"  id="zxjbr" type="text"/></td>
	    		<td>中心经办时间：</td>
	    		<td><input  class="easyui-datebox" id="zxjbsj" type="text"/></td>
	    	</tr>
	    	<tr>
	    		<td>业务类型：</td>
	    		<td><input style="width:150px"  id="ywlx" /></td>
	    		<td>剩余时限：</td>
	    		<td><input style="width:150px"  id="sx" type="text"/></td>
	    	</tr>
	    	<tr>
	    		<td colspan="4" align="center">
	    			<a id="searchBtn" onclick="searchAj()" >查询</a>
	    			<a id="exportBtn" onclick="exportAj()" >导出</a>
	    			<a id="totalBtn" onclick="totalData()" >汇总</a>
<!-- 	    			<a id="printBtn" onclick="print()" >打印</a> -->
	    		</td>
	    	</tr>
	    </table>
	    <div style="position: absolute;top: 170px;right: 1px;bottom: 1px;left: 1px;">
			<table id="grid" style="width:100%;height:100%"></table> 
	    </div>
    </div>
    
    <div id="totalDataDiv" style="width:800px;height:500px;">
     	<table width="100%">
     		<tr>
     			<td>条件设置：</td>
     			<td><input type="checkbox" value="lclx" name="cb"/>业务类型 </td>
     			<td><input type="checkbox" value="zjrXm"  name="cb"/>中心经办人 </td>
     			<td><input type="checkbox" value="sjrBmmc"  name="cb"/>承办部门</td>
     			<td><input type="checkbox" value="sjrXm"  name="cb"/>承办人</td>
     			<td>
     				<a id="dototalBtn" onclick="dototal()" >汇总</a>
	    			<a id="exportTotalBtn" onclick="exportTotal()" >转EXCEL</a>
     			</td>
     		</tr>
     	</table>
     	<div style="position: absolute;top: 60px;right: 7px;bottom: 30px;left: 7px;">
	     	<table id="totalgrid" style="width:100%;height:100%"></table> 
     	</div>
     	<div id="showTotalDiv" style="position: absolute;top: 470px;right: 7px;bottom: 1px;left: 7px;">
     		合计：0
     	</div>
     	
    </div> 
    
    
    <div id="fjdiv" style="width:800px;height:500px;">
     	<div style="position: absolute;top: 30px;right: 7px;bottom: 5px;left: 7px;">
	     	<table id="fjgrid" style="width:100%;height:100%"></table> 
     	</div>
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
{field:'zjrXm',title:'中心经办人',width:120,align:'center'},
{field:'sl',title:'数量',width:120,align:'center'}];

$('#ywlx').combobox({
    url:'ywlxcx.do',    
    valueField:'zdmxbm',    
    textField:'zdmxmc' 
});

$('#searchBtn').linkbutton({});
$('#exportBtn').linkbutton({}).hide();
$('#printBtn').linkbutton({}).hide();
$('#totalBtn').linkbutton({}).hide();
$('#dototalBtn').linkbutton({});
$('#exportTotalBtn').linkbutton({});



$('#totalDataDiv').dialog({
    title:'数据汇总',
    modal: true,
    iconCls:'icon-search',
    closed:true}).dialog('close');

$('#fjdiv').dialog({
    title:'附件信息',
    modal: true,
    iconCls:'icon-search',
    closed:true}).dialog('close');

$('#zxjbsj').datebox({    
    required:false   
});  

$('#fjgrid').datagrid({
	rownumbers:false,
	fit:true,
	border:true,
	singleSelect:true,
	striped:true,
	fitColumns:true,
	singleSelect:true,
	title : '附件列表',
	url:"${path}/fjcx.do",
	queryParams : {
		bh : '',
		fydm : '<%=fydm%>'
	},
	columns:[[
	    {field:'fjmc',title:'附件名称',width:120,align:'center'},
	    {field:'fjdz',title:'附件',width:120,align:'center'},
		{field:'action',title:'操作',width:120,align:'center',
	    	formatter:function(value,row,index){
				var fjbh=row.id.bh;
				var fjxh=row.id.xh;
				var fjdz=row.fjdz;
				var fjmc=row.fjmc;
		        var e = "<a href=\"#\" onClick=\"downloadFile('"+fjxh+"','"+fjbh+"');\">下载</a> ";
		        var c = "";
		        var fjdzArr = fjdz.split(".");
		        if(fjdzArr.length>0 && (fjdzArr[fjdzArr.length-1]=="pdf")){
		        	c = ' <a href=\"#\" onClick=\"Pdf('+fjxh+','+fjbh+');">打开</a> ';
		        }
				
				return e+c;
			}
		}
	]]
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
		cbrText:$('#cbrText').val(),
		cbbmText:$('#cbbmText').val(),
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
		cbrText:$('#cbrText').val(),
		cbbmText:$('#cbbmText').val(),
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
	    {field:'sysx',title:'剩余时限',width:120,align:'center'},
	    {field:'ah',title:'案号',width:200,align:'center',sortable:"true"},
	    {field:'sjrBmmc',title:'承办部门',width:120,align:'center'},
	    {field:'sjrXm',title:'承办人',width:120,align:'center'},
	    {field:'djr',title:'当事人',width:160,align:'center'},
	    {field:'zjrXm',title:'中心经办人',width:120,align:'center'},
	    {field:'zjrq',title:'中心经办日期',width:120,align:'center',
	    	formatter:function(value,row,index){
				if(row.zjrq=='undefined')
					return '';
				else 
					return value;
			 }		
	    },
		{field:'action',title:'操作',width:120,align:'center',
	    	formatter:function(value,row,index){
				var bh=row.id.bh;
				var d = '<a href=\"#\" onClick=\"cxFj('+bh+');">查看附件</a> ';
				return d;
			 }	
		}
	]]
});

function searchAj(){
	$('#grid').datagrid('load',{
		ah:$('#ah').val(),
		dsr:$('#dsr').val(),
		cbrText:$('#cbrText').val(),
		cbbmText:$('#cbbmText').val(),
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
	var cbrText=$('#cbrText').val();
	var cbbmText=$('#cbbmText').val();
	var jbr=$('#zxjbr').val();
	var jbsj=$('#zxjbsj').datebox('getValue');
	var ywlx=$('#ywlx').combobox('getValue');
	var sx=$('#sx').val();
	
	var url = "export_cxjl.do?lx="+<%=lx%>+"&ah="+ah+"&dsr="+dsr
	+"&cbrText="+cbrText+"&cbbmText="+cbbmText+"&jbr="+jbr+"&jbsj="+jbsj
	+"&ywlx="+ywlx+"&sx="+sx;
	window.location.href = url;
}

function totalData(){
	
	$("input[name='cb']").map(function () {
        return $(this).attr("checked",false);
    });
	
	for(var i=0;i<columnsAll.length;i++){
		var field = columnsAll[i].field;
		$('#totalgrid').datagrid('showColumn',field);
	}
	
	$.ajax({
 	     url:'totalSearch.do',
 	     type:'POST',
 	     data:{
 	    	ah:$('#ah').val(),
 			dsr:$('#dsr').val(),
 			cbrText:$('#cbrText').val(),
 			cbbmText:$('#cbbmText').val(),
 			jbr:$('#zxjbr').val(),
 			jbsj:$('#zxjbsj').datebox('getValue'),
 			ywlx:$('#ywlx').combobox('getValue'),
 			sx:$('#sx').val(),
 			lx:'<%=lx%>'
 	     },//注意大小写data
 	     dataType:'json',
 	     success:function (data) {
 	    	$('#totalgrid').datagrid('loadData',data.data);
 	    	$('#showTotalDiv').html("合计："+data.total);
 	      	$('#totalDataDiv').dialog('open');
 	     }
 	});
	
	
	
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
	$.ajax({
	     url:'totalSearch.do',
	     type:'POST',
	     data:{
	    	ah:$('#ah').val(),
			dsr:$('#dsr').val(),
			cbrText:$('#cbrText').val(),
 			cbbmText:$('#cbbmText').val(),
			jbr:$('#zxjbr').val(),
			jbsj:$('#zxjbsj').datebox('getValue'),
			ywlx:$('#ywlx').combobox('getValue'),
			sx:$('#sx').val(),
			lx:'<%=lx%>',
			groupBy:groupBy
	     },//注意大小写data
	     dataType:'json',
	     success:function (data) {
	    	$('#totalgrid').datagrid('loadData',data.data);
	    	$('#showTotalDiv').html("合计："+data.total);
	      	$('#totalDataDiv').dialog('open');
	     }
	});
	
}

function exportTotal(){
	
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
	
	var ah = $('#ah').val();
	var dsr=$('#dsr').val();
	var cbr=$('#cbr').val();
	var cbbm=$('#cbbm').val();
	var jbr=$('#zxjbr').val();
	var jbsj=$('#zxjbsj').datebox('getValue');
	var ywlx=$('#ywlx').combobox('getValue');
	var sx=$('#sx').val();
	
	var url = "export_total.do?lx="+<%=lx%>+"&ah="+ah+"&dsr="+dsr
	+"&cbr="+cbr+"&cbbm="+cbbm+"&jbr="+jbr+"&jbsj="+jbsj
	+"&ywlx="+ywlx+"&sx="+sx+"&groupBy="+groupBy;
	window.location.href = url;
	
}


function cxFj(bh){
	$('#fjgrid').datagrid('load',{
		bh : bh,
		fydm : '<%=fydm%>'
	});
	$('#fjdiv').dialog('open');
}

function downloadFile(xh,bh){
	   window.location.href="${path}/xzFj2.do?bh="+bh+"&xh="+xh;
}

function Pdf(xh,bh){
	url='openPdfxh_new.do?bh='+bh+"&xh="+xh;
	window.open(url,"new","height=600px,width=650px,toolbar=no,status=no,menubar=no,scrollbars=yes,resizable=yes");
}

</script>
</html>