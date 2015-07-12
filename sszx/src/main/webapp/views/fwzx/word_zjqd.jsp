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
	String tool = (String)session.getAttribute("tool");
	String webpath = request.getRequestURL().toString() ;
	int index = webpath.indexOf("sszx");
	String subpath = webpath.substring(0, index);
	String path = "";
	String serverpath="";
	if(fileName!=null){
		path=subpath+"sszx/scan/jpg/"+fileName;
		serverpath = subpath+"sszx/scan/jpg/test/"+fileName;
	}else{
		path=subpath+"sszx/scan/jpg/test.pdf";
		serverpath = subpath+"sszx/scan/jpg/test/test.pdf";
	}
%>


<script>
var plugin;
$(document).ready(function(){
	$('#handSign').linkbutton({});
	$('#fingerprintSign').linkbutton({});
	$('#upLoad').linkbutton({});
	var toolDis = '<%=tool%>';
	if(toolDis=='hide'){
		$('#handSign').hide();
		$('#fingerprintSign').hide();
		$('#upLoad').hide();
	}else{
		$('#handSign').show();
		$('#fingerprintSign').show();
		$('#upLoad').show();
	}
	load();
	OpenWedFile();
});

function load(){
	plugin = getPluginObjcet("plugin");
	//plugin.DisplayToolBar("0");
	plugin.DisplayToolBar("1");
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
		alert(File);
		plugin.SetFileZoom(75);//设置缩放比例
		var Res = plugin.OpenWebFile(File);
		if(Res != 0){
			alert("打开网络文件失败，错误码为:"+Res);
		}
	}
   
   //签名
   function HandSign()
	{
		var res = plugin.SetSignAccount("xh", "a12345678");
	    var c = "<sign><signType>1</signType><file><fileType>1</fileType><fileName>";
		var path = plugin.CurrentCachePath;
		var d = c.concat(path);
		var e = d.concat("</fileName><posPage></posPage><posX>450</posX><posY>670</posY><keyword></keyword><posMouse>120</posMouse></file><cert><authType>1</authType><mobile></mobile><userPwd></userPwd><payAccount></payAccount><payPwd></payPwd></cert></sign>");
		//alert(e);
		var h = plugin.HandServerSign(e);
	}
   
   //指纹
   function FingerprintSign()
	{
		var Res = plugin.ControlPDF("4001");
	}
   //上传
   function    UpFile()
	{
		//var ServerIP = '<%=path%>';
		//var ServerIP = 'http://localhost:8080/sszx/tempfileUpload.do';
		var ServerIP = '<%=subpath%>'+'sszx/tempfileUpload.do';
		var path = plugin.CurrentCachePath;
		var param= "fileName="+'<%=fileName%>';
		var Res = plugin.UpLoadPdfFile(ServerIP,path,param,"tempfile");
		if(Res == 0)
		{
			alert("上传文件成功");
		}
		else
		{
			alert("上传文件失败");
		}
	}
</script>

<body>

<DIV id='showdiv'  style="Z-INDEX: 0; LEFT:10px;  POSITION: absolute; TOP: 30px; bottom:10px;text-align:center"> 
		<object id="plugin" classid="clsid:EAFFB28E-B6AD-4657-904D-51CD7941A3A4" width="630" height="550">
			<embed name="plugin" type="application/nptseal-plugin" width="630" height="550"></embed>
		</object>  
</DIV>
<DIV id='butdiv' align="center"> 
		<a id="handSign" class="easyui-linkbutton"  onclick="HandSign()">签章</a>
		<a id="fingerprintSign" class="easyui-linkbutton"  onclick="FingerprintSign()">指纹</a>
		<a id="upLoad" class="easyui-linkbutton"  onclick="UpFile()">上传</a>
</DIV>

</body>
</html>