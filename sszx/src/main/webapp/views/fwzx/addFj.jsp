<%@ page language="java" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*,com.bsoft.sszx.dao.*,com.bsoft.sszx.entity.fjb.*" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>附件列表</title>
<jsp:include page="/common/include.jsp" />
</head>

<%
String fydm=(String)session.getAttribute("fydm");
String bh=(String)session.getAttribute("fjbh");
%>
  
<body style="font-size:12px;" class="easyui-layout">

	<div id="fjsm" style="font-size:12px;width:250px;">
             &nbsp;&nbsp;附件名称: <input class="easyui-validatebox" required="true" id="fjmc" style="margin-left:10px;width:150px" type="text"></input>      
    </div>
    <div id="fjsc" style="font-size:12px;width:320px;height:150px;">
    		<form id="fjForm" method="post" enctype="multipart/form-data"  theme="simple">
    			<input name="bh" style="display:none" type="text" value="<%=bh%>"/>
  				<table>
  				<tr>
    				<td>附件名称：</td>
    				<td><input class="easyui-validatebox" required="true" id="fjmc2" name="fjmc2" style="margin-left:10px;width:150px" type="text"></input></td>
    			</tr>
    			<tr>
    				<td>选择附件：</td>
    				<td><input type="file" name="fileToUpload" id="fileToUpload" style="width:200px"/></td>
    			</tr>
    			</table>
			</form>
    </div>

     <div data-options="region:'center'">
     	<table id="fjgrid" data-options="fit:true,border:false" ></table>
     </div> 
   
   <script>
	   $('#fjgrid').datagrid({
			rownumbers:false,
			title:'附件列表',
			singleSelect:true,
			striped:true,
			fitColumns:true,
			idField:'fjmc',
			border:true,
			url:"${path}/fjcx.do",
			queryParams : {
				bh : '<%=bh%>',
				fydm : '<%=fydm%>'
			},
			columns:[[
			    {field:'fjmc',title:'附件名称',width:120,align:'center'},
			    {field:'fjdz',title:'附件',width:120,align:'center',hidden:'true'},
				{field:'action',title:'操作',width:120,align:'center',
			    	formatter:function(value,row,index){
						var fjbh=row.id.bh;
						var fjxh=row.id.xh;
						var fjdz=row.fjdz;
						var fjmc=row.fjmc;
				        var e = "<a href=\"#\" onClick=\"downloadFile('"+fjxh+"','"+fjbh+"');\">下载</a> ";
						var c = '<a href=\"#\" onClick=\"delFj('+fjxh+','+fjbh+');">删除</a> ';
						return e+c;
					}
				}
			]],
			toolbar:[
				{//工具条
			        text:'扫描件',
			        iconCls:'icon-add',
			        id:'addDsrCL',
			        handler:function(){
			        	$('#fjsm').dialog('open');
			        }
				},{
		        	text:'上传附件',
			        iconCls:'icon-add',
			        handler:function(){
			        	uploadFile();
			        }
		        }
        	]
	   });
	   
	   $('#fjsm').dialog({
			    title:'扫描附件',
			    iconCls:'icon-add',
			    closed:true,
			    buttons:[{
			        text:'扫描',
			        iconCls:'icon-ok',
			        handler:function(){
			        	var bh='<%=bh%>';
			        	var fydm='<%=fydm%>';
			        	var fjmc=encodeURI(encodeURI($('#fjmc').val()));
			        	if(fjmc=="")
			        		alert("请输入附件名称");
			            else{
			            var url='${path}/scanonweb.jsp?bh='+bh
			            		+'&fydm='+fydm+
			            		'&fjmc='+fjmc;
			            window.open(url);}
			        }},
			        {
				        text:'确认',
				        iconCls:'icon-reload',
				        id:'smq',
				        handler:function(){
				        	window.location.href='${path}/fj.do?bh='+<%=bh%>;
				        }
			        }
			        ]
			        });
		 
	   $('#fjsc').dialog({
		    title:'上传附件',
		    iconCls:'icon-add',
		    closed:true,
		    buttons:[{
		        text:'上传',
		        iconCls:'icon-ok',
		        handler:function(){
		        	if ($('#fjmc2').val()==""){
		        		alert("请输入文件名称");
		        		return;
		        	}else if($('#fileToUpload').val()==""){
		        		alert("请选择上传文件");
		        		return;
		        	}else{
		        		var fileNameArr = $('#fileToUpload').val().split(".");
		        		var prefix = fileNameArr[fileNameArr.length-1];
		        		$("#fjForm").ajaxSubmit({
		                    type: 'post',  
		                    url: "${path}/fileUpload.do" ,  
		                    success: function(data){  
		                    	if(data=="success"){
		                    		alert("上传文件成功")
		                    	}else{
		                    		alert("上传文件失败，请重试...")
		                    	}
		                    	window.location.href='${path}/fj.do?bh='+<%=bh%>;
		                    },  
		                    error: function(XmlHttpRequest, textStatus, errorThrown){  
		                    	alert("上传文件失败，请重试...")
		                    	window.location.href='${path}/fj.do?bh='+<%=bh%>;
		                    }  
		                }); 
		        	}
		        }
		    }]
	   });
	   
	   $('#smq').click(function(event){
			 window.location.href='${path}/fj.do?bh='+<%=bh%>;
			 //event.preventDefault();//ie6专用
		});
   
   function delFj(xh,bh){
	   var fydm='<%=fydm%>';
	   $.ajax({
    	     url:'${path}/delFj.do',
    	     type:'POST',
    	     data:{bh:bh
                  ,fydm:fydm
                  ,xh:xh
    	     },//注意大小写data
    	     dataType:'json',
    	     success:function (data) {
    	    	 $('#fjgrid').datagrid('load',{
    	 				bh : '<%=bh%>',
    	 				fydm : '<%=fydm%>'
    	 			});
   	     		}
    	     });	   
   }
   
   function uploadFile(){
	   $('#fjmc2').val('');
   	   $('#fileToUpload').val('');
	   $('#fjsc').dialog('open');
   }
   
   function downloadFile(xh,bh){
	   window.location.href="${path}/xzFj2.do?bh="+bh+"&xh="+xh;
   }
   
   </script>
</body>
</html>