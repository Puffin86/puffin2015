<%@ page language="java" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*,com.bsoft.sszx.dao.*,com.bsoft.sszx.entity.fjb.*" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>接收材料</title>
<jsp:include page="/common/include.jsp" />
</head>

<%
String fydm=(String)session.getAttribute("fydm");
String bh=(String)session.getAttribute("fjbh");
List<Fjb> fjb =(List<Fjb>) new FjDao().fjlist(bh, fydm);
if(fjb==null)
	fjb=new ArrayList();
%>
  
<body style="font-size:12px;">
   <div  align="center">     
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
     
     <div style="font-size:12px;" class="fj" id="fjtb">
        <table style="font-size:12px" width="300" align="center" border="1">
        <tr style="background-color:#E0ECFF;">
          <td width="60%"  align="center">附件名称</td>
          <td width="20%" align="center">下载</td>
          <td width="20%" align="center">删除</td>
        </tr>
        <%for(int i=0;i<fjb.size();i++){
			Fjb m=(Fjb)fjb.get(i);
			String path = getServletContext().getRealPath("/scan/jpg/");
            String name=m.getFjdz();
            if(!name.contains("."))
         	   name=name+".jpg";
            String file=path+"\\"+name;
		%>
        <tr style="font-size:12px"> 
          <td><div align="center"><%=m.getFjmc()%></div></td>
          <td><div align="center">
          <a href="${path}/xzFj.do?downloadFile=<%=file%>">下载</a></div></td>       
          <td><div align="center"><a href="#" onClick="delFj(<%=m.getId().getXh()%>,<%=m.getId().getBh()%>)">删除</a></div></td>
        </tr>
        <%}%>
     </table>
     </div>
     <br/>
     <div align="center">
          <a id="fj_sm" onclick="$('#fjsm').dialog('open')" iconCls="icon-scan">扫描附件</a>
          &nbsp;&nbsp;<a id="fj_sc" onclick="uploadFile()" iconCls="icon-upload">上传本地文件</a>
     </div>
     
     
   </div>
   
   <script>
	   $('#fj_sm').linkbutton({});
	   $('#fj_sc').linkbutton({});
	   $('#fj_return').linkbutton({});
		
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
			            var url='${path}/Scanner.jsp?bh='+bh
			            		+'&fydm='+fydm+
			            		'&fjmc='+fjmc;
			            window.open(url);}
			        }},
			        {
				        text:'确认',
				        iconCls:'icon-reload',
				        id:'smq'}
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
	   	    	 if(data.after==1)
	      	       alert("删除成功");
	      	     if(data.after==0)
	      	       alert("删除失败");    	     
	     	     window.location.href='${path}/fj.do?bh='+<%=bh%>;
   	     		}
    	     });	   
   }
   
   function uploadFile(){
	   $('#fjmc2').val('');
   	   $('#fileToUpload').val('');
	   $('#fjsc').dialog('open');
   }
   
   </script>
</body>
</html>