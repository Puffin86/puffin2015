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
	String webpath = request.getRequestURL().toString() ;
	int index = webpath.indexOf("sszx");
	String subpath = webpath.substring(0, index);
	String path = "";
	if(fileName!=null){
		path=subpath+"sszx/scan/jpg/"+fileName;
	}else{
		path=subpath+"sszx/scan/jpg/test.pdf";
	}
%>


<script>
var plugin;
$(document).ready(function(){
	load();
	OpenWedFile();
});

function load(){
	plugin = getPluginObjcet("plugin");
	//plugin.DisplayToolBar("0");
	plugin.DisplayToolButton("400",0);
	plugin.DisplayToolButton("205",0);
	plugin.DisplayToolButton("417",0);
	plugin.DisplayToolButton("483",0);
	plugin.DisplayToolButton("492",0);
	plugin.DisplayToolButton("484",0);
}
function isIEBrowser() {
	var isAtLeastIE11 = !!(navigator.userAgent.match(/Trident/) && !navigator.userAgent.match(/MSIE/));
		
	if(isAtLeastIE11) 
	{
		return true;
	}

   	return (navigator.appName.indexOf("Microsoft Internet") != -1);
   }
     
   function getPluginObjcet(objname){  
		if (!isIEBrowser()) 
		{    
			if (document.embeds && document.embeds[objname])
			return document.embeds[objname];
		} 
		else
		{
			 return document.getElementById(objname);
		}	
	}
   
   function OpenWedFile()
	{
		var File = '<%=path%>';
		plugin.SetFileZoom(100);
		var Res = plugin.OpenWebFile(File);
		if(Res != 0){
			alert("打开网络文件失败，错误码为:"+Res);
		}
	}
   
</script>

<body>
<DIV id='showdiv'  style="Z-INDEX: 0; LEFT:10px;  POSITION: absolute; TOP: 50px; bottom:10px;text-align:center"> 
		<object id="plugin" classid="clsid:EAFFB28E-B6AD-4657-904D-51CD7941A3A4" width="630" height="550">
			<embed name="plugin" type="application/nptseal-plugin" width="630" height="550"></embed>
		</object>  
</DIV>
</body>
</html>