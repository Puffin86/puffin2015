<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<jsp:include page="/common/include.jsp" />
</head>
<%
	String fileName = (String)session.getAttribute("fileName");
	//System.out.println("$$$:"+fileName);
	String path="";
	if(fileName!=null)
		path="scan/jpg/"+fileName;
	else
		path="scan/jpg/test.pdf";
%>
<body style="font-size:12px;">
	<DIV id=showdiv  style="Z-INDEX: 0; LEFT:10px;  POSITION: absolute; TOP: 10px; HEIGHT: 10px;bottom:10px;text-align:center"> 
		<object classid="clsid:CA8A9780-280D-11CF-A24D-444553540000"  border="0" height="580px"  width="635px"  name="pdf" >  
		      <param name="toolbar" value="true"> 
		      <param name="_Version" value="65539">
		      <param name="_ExtentX" value="20108">
		      <param name="_ExtentY" value="10866">
		      <param name="_StockProps" value="0">
		      <param name="SRC" value="<%=path%>">  
		</object>  
</DIV> 
</body>
</html>