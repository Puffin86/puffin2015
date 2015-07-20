<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<jsp:include page="/common/include.jsp" />
</head>
<script>
$(document).ready(function(){
	$('#save').linkbutton({});
	$('#save1').linkbutton({});
});


function save(){
	$.ajax({
	     	url:'${path}/fileUploadTest.do',
	     	type:'POST',
	     	data:{
	     		 ah:encodeURI(encodeURI(ah))
	     	},
	     	dataType:'json',
	     	success: function(data) {
   	    	  alert(data);
	     	}
	  	});
}

function formSave(){
	$("#fjForm").ajaxSubmit({
        type: 'post',  
        url: "${path}/fileUploadTest.do" ,  
        success: function(data){
        	var ss = eval("("+data+")");
        	if(ss.flag=="success"){
        		alert("上传文件成功");
        	}else{
        		alert("上传文件失败，请重试...");
        	}
        },  
        error: function(data){ 
        	alert("上传文件发生错误，请重试...");
        }  
    }); 
	
}


</script>

<body>
	   <form id="fjForm" method="post" enctype="multipart/form-data"  theme="simple">
			<table>
  				<tr>
    				<td>附件名称：</td>
    				<td><input class="easyui-validatebox" required="true" id="fjmc2" name="fjmc2" style="margin-left:10px;width:150px" type="text"></input></td>
    			</tr>
    			<tr>
    				<td>选择附件：</td>
    				<td><input type="file" name="fileToUpload" id="fileToUpload" style="width:200px"/></td>
    			</tr>
    			<tr>
    				<td>操作：</td>
    				<td> 
    					<a id="save" onclick="save()" iconCls="icon-save">保存</a>
    					<a id="save1" onclick="formSave()" iconCls="icon-save">form保存</a>
    				</td>
    			</tr>
    		</table>
    	</form>
</body>
</html>