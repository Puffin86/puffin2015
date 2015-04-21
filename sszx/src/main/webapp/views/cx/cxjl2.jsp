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
	    		<td><input style="width:150px"  id="cbr" type="text"/></td>
	    		<td>承办部门：</td>
	    		<td><input style="width:150px"  id="cbbm" type="text"/></td>
	    	</tr>
	    	<tr>
	    		<td>中心经办人：</td>
	    		<td><input style="width:150px"  id="zxjbr" type="text"/></td>
	    		<td>中心经办时间：</td>
	    		<td><input style="width:150px"  id="zxjbsj" type="text"/></td>
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
	    			<a id="exportBtn" onclick="export()" >导出</a>
	    			<a id="printBtn" onclick="print()" >打印</a>
	    		</td>
	    	</tr>
	    </table>
		<table id="grid" style="width:100%;height:100%"></table> 
    </div>
    
</body>

<script>

$('#ywlx').combobox({
    url:'zdcx.do',    
    valueField:'zdbm',    
    textField:'zdmc' 
});

$('#searchBtn').linkbutton({});
$('#exportBtn').linkbutton({});
$('#printBtn').linkbutton({});



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
	url:"zdmxcx.do",
	queryParams : {
		zdbm : 'aa',
		zdmc : ''
	},
	columns:[[
	    {field:'lclx',title:'业务类型',width:120,align:'center',sortable:"true"},
	    {field:'sx',title:'时限',width:120,align:'center'},
	    {field:'ah',title:'案号',width:120,align:'center'},
	    {field:'cbbm',title:'承办部门',width:120,align:'center'},
	    {field:'cbr',title:'承办人',width:120,align:'center'},
	    {field:'dsr',title:'当事人',width:120,align:'center'},
	    {field:'zxjbr',title:'中心经办人',width:120,align:'center'},
	    {field:'zxjbrq',title:'中心经办日期',width:120,align:'center'},
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



function searchAj(){
	var ah='';
	var dsr='';
	if(ah!=''||dsr!=''){
	$.ajax({
  	     url:'clSearch.do',
  	     type:'POST',
  	     data:{ah:encodeURI(encodeURI(ah)),
  	    	 dsr:encodeURI(encodeURI(dsr)),
  	    	 lx:'<%=lx%>',
  	    	 sjrbm:'<%=sjrbm%>'},//注意大小写data
  	     dataType:'json',
  	     success:function (data) {
  	      	alert(123);
  	     }
  	  });
	}
}

</script>
</html>