<%@ page language="java" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*,com.bsoft.sszx.dao.*,com.bsoft.sszx.entity.fjb.*" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>接收材料</title>
<jsp:include page="/common/include.jsp" />
<style type="text/css">

.icon-return{ 
	background:url('resources/style/images/return_1.png') no-repeat; 
}

.icon-upload{ 
	background:url('resources/style/images/upload_1.png') no-repeat; 
}

.icon-scan{ 
	background:url('resources/style/images/sacn.png') no-repeat; 
}

</style>
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
     
     <div id="fjsc" style="font-size:12px;width:300px;">
          <s:form id="fjForm" action="fileUpload" method="post" enctype="multipart/form-data" theme="simple">
             <input name="bh" style="display:none" type="text" value="<%=bh%>"/>
	         &nbsp;&nbsp;附件名称：
	         <input class="easyui-validatebox" required="true" id="fjmc2" name="fjmc2" style="margin-left:10px;width:150px" type="text"></input>
	         <br/>
	        <div>&nbsp;&nbsp;选择附件：
	        <s:file id="uploadFileName" cssStyle="margin-left:10px;font-size:12px;width:150px" name="uploadFile"/>
	        </div>
          </s:form>
          <input class="easyui-filebox" style="width:150px" >
     </div>
      <!-- 
     <div id="fjsc" style="font-size:12px;width:300px;">
	         &nbsp;&nbsp;附件名称：
	         <input class="easyui-validatebox" required="true" id="fjmc2" name="fjmc2" style="margin-left:10px;width:150px" type="text"></input>
	         <br/>
	        &nbsp;&nbsp;选择附件：
	        <input class="easyui-filebox" id="uploadFileName" style="width:150px" id="fb" >
     </div>
     -->
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
          &nbsp;&nbsp;<a id="fj_sc" onclick="$('#fjsc').dialog('open')" iconCls="icon-upload">上传本地文件</a>
          &nbsp;&nbsp;<a id="fj_return" onclick="window.location.href='${path}/to_jsdsrzdsj.do'" iconCls="icon-return">返回</a>
     </div>
     
     
   </div>
   
   <script>
	   $('#fj_sm').linkbutton({});
	   $('#fj_sc').linkbutton({});
	   $('#fj_return').linkbutton({});
		
	   $('#fb').filebox({
		    buttonText: '选择文件', 
		    buttonAlign: 'left' 
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
		        	if($('#uploadFileName').val()=="")
		        		alert("请选择上传文件");
		        	else if ($('#fjmc2').val()=="")
		        		alert("请输入文件名称");
		        	else
		        		alert(123);
		        	}
		        }]
	   });
	   
	   $('#smq').click(function(event){
			 window.location.href='${path}/addFj.do';
			 //event.preventDefault();//ie6专用
		});
   
   function delFj(xh,bh){
	   var fydm='<%=fydm%>';
	   $.ajax({
    	     url:'delFj.do',
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
	     	     window.location.href='${path}/addFj.do';
   	     		}
    	     });	   
   }
   
   
   </script>
</body>
</html>