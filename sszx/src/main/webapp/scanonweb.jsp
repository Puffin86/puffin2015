<%@page import="java.util.*,java.net.URLDecoder" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<HTML>
	<HEAD>
		<title>扫描</title>
		<jsp:include page="/common/include.jsp" />
	</HEAD>
<!-- http://wenku.baidu.com/link?url=O9TE1hFXrvT23biXhQI2GgfSVUE0hD9XwVsz0m7t_7SciuVez_-3qixBsKa10qaI9e8H8DlIcmacdUi2emwbnV6xKFV3aL0FrDeohbr3mGO -->
<%
String bh=request.getParameter("bh");
String fydm=request.getParameter("fydm");
String fjmc=request.getParameter("fjmc");
%>

<script>
$(document).ready(function(){
	$('#save').linkbutton({});
	$('#cancel').linkbutton({});
});

function cancel(){
		window.close();
}

function save(){
		var base64_data = document.getElementById('scaner1').allImageAsTiffData();
		$.ajax({
			url : 'saveScan.do',
			type : 'POST',
			data : {
				fydm : '<%=fydm%>',
				picData : "'" + base64_data + "'",
				picExt:".tiff",
				bh : '<%=bh%>',
				fjmc : '<%=fjmc%>'
			},
			dataType : 'json',
			success :function(data){
				alert("上传附件成功~");
				cancel();
			}
		});
		
}


</script>
<body >

<!-- <p>如果一切正常，您应该在下面的窗口中看到控件的工作窗口，如果未正常工作，请调整浏览器安全设置，启用未签名ActiveX控件下载，启用未签名ActiveX控件运行。</p> -->
	<object classid="clsid:15D142CD-E529-4B01-9D62-22C9A6C00E9B" 
	id="scaner1" width="100%" height="90%" 
	codebase="cabs/ScanOnWeb.cab#version=1,0,0,10">
    <param name="Visible" value="0">
    <param name="AutoScroll" value="0">
    <param name="AutoSize" value="0">
    <param name="AxBorderStyle" value="1">
    <param name="Caption" value="scaner">
    <param name="Color" value="4278190095">
    <param name="Font" value="宋体">
    <param name="KeyPreview" value="0">
    <param name="PixelsPerInch" value="96">
    <param name="PrintScale" value="1">
    <param name="Scaled" value="-1">
    <param name="DropTarget" value="0">
    <param name="HelpFile" value>
    <param name="PopupMode" value="0">
    <param name="ScreenSnap" value="0">
    <param name="SnapBuffer" value="10">
    <param name="DockSite" value="0">
    <param name="DoubleBuffered" value="0">
    <param name="ParentDoubleBuffered" value="0">
    <param name="UseDockManager" value="0">
    <param name="Enabled" value="-1">
    <param name="AlignWithMargins" value="0">
    <param name="ParentCustomHint" value="-1">
    <param name="jpegBase64Data" value="">
    <param name="bmpBase64Data" value="">
    <param name="key1" value="">
    <param name="key2" value="">   
  </object>

    <div align="center" >
     <a id="save" onclick="save()" iconCls="icon-save">上传</a>
     &nbsp;&nbsp;&nbsp;&nbsp;
     <a id="cancel" onclick="cancel()"  iconCls="icon-cancel">返回</a>
   </div>
</body>
</HTML>