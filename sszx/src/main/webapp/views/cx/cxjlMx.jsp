<%@ page language="java" pageEncoding="utf-8"%>
<%@ page language="java" import="com.bsoft.sszx.entity.zjqd.*" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%
 Zjqd z=(Zjqd)session.getAttribute("cljlMx");
 String nr=z.getLzjl();
%>
  
<body style="font-size:12px;">
    <div align="center">
	    <table style="font-size:12px;margin-left:10px;" id="ssclzjqd">
	     <tr>
	     	<td style="background-color:#AAAAAA; font-size:14px; font-weight:bold;color:#FF5511" colspan="4">案件转交流程记录</td>
	     </tr>
	     <tr>
		     <td align="center" colspan="4">
		     	<textarea id="nr" rows="18" cols="70" style="margin-top:20px;font-size:12px;"><%=nr %></textarea>
		     </td>
	     </tr>  
	     </table>
    </div>
</body>
</html>