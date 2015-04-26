<%@page import="java.util.*,java.net.URLDecoder" contentType="text/html;charset=UTF-8"%>
<HTML>
	<HEAD>
		<title>扫描</title>
	</HEAD>
<script language="javascript" event="OnScanCmp()" for="scan_ocx">
	alert("扫描成功");
</script>
<body >
<%
String bh=request.getParameter("bh");
String fydm=request.getParameter("fydm");
String fjmc=request.getParameter("fjmc");
%>
<OBJECT
	  id='scan_ocx'
	  classid="clsid:E474D455-4401-4A2D-893E-BEC223F056F1"
	  codebase="./cabs/ScannerActiveProj.cab#version=1,0,32,0"
	  width=100%
	  height=100%
	  align=center
	  hspace=0
	  vspace=0
>
</OBJECT>
</body>
<script language='javascript'>
	function initOCX(){
	try{
		var en='jpg';
		var myDate = new Date();
		var name=myDate.getTime();
		var fn='<%=fydm%>'+"_"+'<%=bh%>'+name;
		var fjbh='<%=bh%>';
		var fydm='<%=fydm%>';
		var fjmc='<%=fjmc%>';
		
		fn=fn+"&&"+fjbh+"&&"+fydm+"&&"+fjmc;
		scan_ocx.InitOCX('http://203.0.64.64:8888/sszx/UploadImag.jsp',en,fn);
	}catch(e){
		alert(e.message);
	};
 };
</script>

</HTML>