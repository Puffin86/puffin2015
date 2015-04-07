<%@ page language="java" pageEncoding="utf-8"%>
<%@ page language="java" import="java.io.*,java.util.*,com.bsoft.sszx.dao.*,com.bsoft.sszx.entity.zjqd.*,com.bsoft.sszx.entity.fjb.*,com.bsoft.sszx.entity.clb.*" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>接收材料</title>
<jsp:include page="/common/include.jsp" />
</head>

<%
List clblist=(List)session.getAttribute("wordclb");
if (clblist==null) clblist=new ArrayList();
Zjqd zjqd=(Zjqd)session.getAttribute("wordZjqd");
ZjqdId zjqdId=zjqd.getId();
String zjr=zjqd.getZjr();
String user=(String)session.getAttribute("user");
String fydm=(String)session.getAttribute("fydm");
String zjrXm=new UserDao().findUserById(user, fydm).getYhxm();

String path = getServletContext().getRealPath("/scan/jpg/");
String nameF=zjqd.getId().getFydm()+"_"+zjqd.getId().getBh()+"_"+zjqd.getLclx()+".doc";             
String fileF=path+"\\"+nameF;
File F =new File(fileF);
int Fs=1;
if(F.exists()){
	Fjb Fjb= new FjDao().findFjbBymc(nameF, String.valueOf(zjqd.getId().getBh()), fydm);
	if(Fjb!=null)
	Fs=0;
}
%>
  
<body style="font-size:12px;">  
   <div id="tableW" align="center">
	   <table width="600px" style="font-size:14px;border-collapse:collapse" id="cld"  border="1">
	   <tr><td colspan="5" style="font-family:SimHei;font-size:20px;height:80px" align="center">诉讼材料转接清单</td></tr>
	   <tr style="background-color:#DDDDDD; font-weight:bold;">
	   <td align="center">编号</td><td align="center">案号</td><td align="center">承办人</td><td align="center">日期</td><td align="center">当事人联系电话</td>
	   </tr>
	   <tr>
	   <td align="center"><%=zjqd.getId().getBh()%></td><td align="center"><%=zjqd.getAh()%></td><td align="center"><%=zjqd.getSjrXm()%></td><td align="center"><%=zjqd.getDjrq()%></td><td align="center"><%=zjqd.getDjrlxdh()%></td>
	   </tr>
	   <tr style="background-color:#DDDDDD;font-weight:bold;"><td align="center" colspan="3">材料名称</td><td align="center">页数</td><td align="center">份数</td></tr>
	    <%for(int i=0;i<clblist.size();i++){
				Clb m=(Clb)clblist.get(i);
		%><tr><td align="center" colspan="3"><%=m.getClmc()%></td><td align="center"><%=m.getYs()%></td><td align="center"><%=m.getFs()%></td></tr>
	   <%}%>
	   <tr >
	   <td style="background-color:#DDDDDD;font-weight:bold;" align="center">转接人</td><td colspan="2" align="center"><%=zjrXm%></td><td align="center" style="background-color:#DDDDDD;font-weight:bold;height:60px">当事人签名</td><td align="center"></td>
	   </tr>   
	   </table>
   </div>
   
	<div align="center">
	     <a id="save" onclick="saveWord()" iconCls="icon-save">保存为word</a>
	     <a id="down" iconCls="icon-word" href="${path}/xzFj.do?downloadFile=<%=fileF%>">下载</a>
     </div>
 

<script>
$('#save').linkbutton({});
$('#down').linkbutton({});

function saveWord(){

	var table=$('#tableW').html();
	 $.ajax({
	     url:'htmltoword.do',
	     type:'POST',
	     data:{
	    	 nr:table
	      },//注意大小写data
	     dataType:'json',
	     success:function (data){
 	    	 if(data.after==1)
    	       alert("保存成功");
 	    	 else
    	       alert("保存失败"); 
 	    	window.location.href="${path}/word.do?bh='<%=zjqdId.getBh()%>'";
 	     }});
};

$(function(){
	var s=<%=Fs%>;
	if(s==1){
		var table=$('#tableW').html();
		 $.ajax({
		     url:'htmltoword.do',
		     type:'POST',
		     data:{
		    	 nr:table
		      },//注意大小写data
		     dataType:'json',
		     success:function (data){
		    	 if(data.after==1){
		    		 $('#save').hide();
		    		 $('#down').show();
		    	 }else{
		    		 alert("文档生成失败，请点击保存重新生成...");
		    		 $('#save').show();
		    		 $('#down').hide();
		    	 }
		     }});
	}else{
		$('#save').hide();
		$('#down').show();
	}
		
	
	
	
	
});

</script>


</body>
</html>