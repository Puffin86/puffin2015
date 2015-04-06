<%@page import="com.bsoft.sszx.entity.sms.Dxx"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>短信设置</title>
<jsp:include page="/common/include.jsp" />
</head>

<%
	Dxx dxx=(Dxx)session.getAttribute("editDxx");
%>
  
<body style="font-size:12px;">

<div align="center">
	<table style="font-size:12px;margin-left:10px;" id="ssclzjqd">
		<tr>
			<td style="background-color:#AAAAAA; font-size:14px; font-weight:bold;color:#FF5511" colspan="4">是否默认发送</td>
		</tr>
		<tr>
			<td width="10%">&nbsp;</td>
			<td width="20%"><input id="r_1" type="radio" name="identity" value="0" />是</td>
			<td width="20%"><input id="r_2" type="radio" name="identity" value="1" />否 </td>
			<td>&nbsp;</td>       
		</tr>    
		<tr>
			<td colspan="4" style="width:200px;background-color:#AAAAAA; font-size:14px; font-weight:bold;color:#FF5511">设置短信模板</td>
		</tr>
		<tr>
			<td align="center" colspan="4">
				<textarea id="nr" rows="8" cols="50" style="margin-top:20px;margin-left:20px;font-size:12px;"></textarea>
			</td>
		</tr>     
		<tr>
			<td align="center" colspan="4">
				<a id="save" onclick="save()" iconCls="icon-save">保存</a>
			</td>
		</tr>
	</table>
</div>
 
<script>
$(document).ready(function(){ 
	$('#save').linkbutton({});

	var lx="${editDxx.zdfs}";//主动发送
	var s='${editDxx.nr}';
	
	if(lx==0)
		$('#r_1').attr('checked','checked');
	if(lx==1)
		$('#r_2').attr('checked','checked');	
	
	$('#nr').val(s);
}); 

function save(){
	var id="${editDxx.id.id}";
	var fydm="${editDxx.id.fydm}";
	var lx=$('input[name=identity]:checked').attr('value');
 
	$.ajax({
	    url:'${path}/saveDxx.do',
	    type:'POST',
	    data:{
	    	id:encodeURI(encodeURI(id)),
	    	fydm:encodeURI(encodeURI(fydm)),
	    	lx:lx,
	    	nr:encodeURI(encodeURI($('#nr').val()))
	    },
	    dataType:'json',
	    success:function (data) {
		    if(data.after==1){
		   	   lx=data.zdfs;
		   	   
			   	if(lx==0)
			   	   $('#r_1').attr('checked','checked');
		        if(lx==1)
		   	    	$('#r_2').attr('checked','checked');
		        
	      		alert("保存成功");
	      	}
		    
	      	if(data.after==0)
	      		alert("保存失败");
	    }
	});
}
</script>

</body>
</html>