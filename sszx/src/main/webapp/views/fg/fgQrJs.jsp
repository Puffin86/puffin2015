<%@ page import="java.util.ArrayList"%>
<%@ page import="com.bsoft.sszx.dao.FjDao"%>
<%@ page import="com.bsoft.sszx.entity.fjb.Fjb"%>
<%@ page import="com.bsoft.sszx.dao.UserDao"%>
<%@ page import="com.bsoft.sszx.entity.zjqd.Zjqd"%>
<%@ page import="com.bsoft.sszx.entity.clb.Clb"%>
<%@ page import="java.util.List"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>法官确认接收</title>
<jsp:include page="/common/include.jsp" />
</head>

<%
	String fydm = (String) session.getAttribute("fydm");
	List<Clb> list = (List<Clb>) session.getAttribute("fgQrJsZjqdClb");
	Zjqd zjdq = (Zjqd) session.getAttribute("fgQrJsZjqd");
	String lclx = zjdq.getLclx();
	String cbrid = zjdq.getSjr();
	String qscyr = zjdq.getQscyr();
	String zjrXm = new UserDao().findUserById(qscyr, fydm).getYhxm();
%>
  
<body style="background:#fff;">

    <div id="tuiHui" style="width:430px;height:250px;">
	    <div style="margin-left:20px;margin-top:10px;color:red;">
	    	 <input id="r_1" type="radio" name="thlx" value="1" checked="checked"/>退回诉讼服务中心
	         <input id="r_2" type="radio" name="thlx" value="2" />退回当事人
	         &nbsp;&nbsp;退回原因:<input id="thList" style="width:100px" />
	    </div>
    	<textarea id="thyj" rows="8" cols="50" style="margin-left:20px;font-size:12px;"></textarea>
    </div>
    
    <table style="font-size:12px;margin-left:10px;" id="ssclzjqd">
     <tr>
       <td>案号：</td>
       <td><input name="ah" type="text"></input></td>
     </tr>
     <tr>
       <td width="20%" >承办人：</td>
       <td><input name="sjr" type="text"></input></td>
       <td width="20%" >&nbsp;&nbsp;转交人：</td>
       <td><input name="qscyr" type="text"></input></td>
     </tr>
     <tr>
       <td width="20%" >当事人：</td>
       <td><input name="tjr" type="text"></input></td>
       <td width="25%">&nbsp;&nbsp;当事人联系电话：</td>
       <td><input name="tjrlxdh" type="text"></input></td>
     </tr> 
     <tr>
       <td width="20%">当事人证件号码：</td>
       <td><input name="djrsfz" type="text"></input></td>
       <td width="25%">&nbsp;&nbsp;递交日期：</td>
       <td><input class="easyui-datebox" required="true" id="djrq" name="djrq" type="text"></input></td>
     </tr>
   </table>
    
   <hr/> 
     
   <div style="margin-left:10px;">     
     <div class="cl" id="clmxtr" style="visibility:hidden;margin-top:5px">
       <div>
       	材料名称：<input name="clmc" type="text"></input>&nbsp;&nbsp;
       	份数： <input name="clfs"  style="width:30px" type="text"></input>&nbsp;&nbsp;
       	页数： <input name="clys" style="width:30px" type="text"></input>      
       </div>
     </div>
   </div>
   
   <hr/>
   <div style="font-size:12px;width:400px;margin-left:10px">附件下载：</div>
   <div style="font-size:12px;width:400px;margin-left:122px" class="fj" id="fjtb">
		<%
			String bh = String.valueOf(zjdq.getId().getBh());
			List<Fjb> fjb = (List<Fjb>) new FjDao().fjlist(bh, fydm);
			if (fjb == null)
				fjb = new ArrayList();
		%>
		<table style="font-size:12px" width="400" align="center" border="1" cellpadding="0" cellspacing="0">
        <tr style="background-color:#FF9966;">
          <td width="80%"  align="center">附件名称</td>
          <td width="20%" align="center">下载</td>
        </tr>
        <%
        	for (int i = 0; i < fjb.size(); i++) {
        		Fjb m = (Fjb) fjb.get(i);
        %>
        <tr style="font-size:12px"> 
          <td><div align="center"><%=m.getFjmc()%></div></td>
          <td>
	          <div align="center">
	               <%
	               		String path = getServletContext().getRealPath("/scan/jpg/");
	       				String name = m.getFjdz();
	       				if (!name.contains("."))
	       					name = name + ".jpg";
	       				String file = path + "\\" + name;
	               %>
	          	  <a href="${path}/xzFj.do?downloadFile=<%=file%>">下载</a>
	          </div>
          </td>       
        </tr>
        <%
        	}
        %>
     </table>
     </div>
   
   <hr/>
   
   <div align="center">
     <a id="save" class="easyui-linkbutton" onclick="save()" iconCls="icon-ok">接收</a>
     <a id="back" class="easyui-linkbutton" onclick="back()" iconCls="icon-no">退回</a>
     <a id="cancel" class="easyui-linkbutton" onclick="cancel()" iconCls="icon-cancel">取消</a>
   </div>
   
   <script>

   //文档加载完成后要执行的内容 
   $(document).ready(function(){
	   var ah="${fgQrJsZjqd.ah}";
	   $('input[name=ah]').attr('value',ah);	   
	   var sjr="${fgQrJsZjqd.sjrXm}";
	   $('input[name=sjr]').attr('value',sjr);
	   var tjr="${fgQrJsZjqd.djr}";
	   $('input[name=tjr]').attr('value',tjr);
	   var tjrlxdh="${fgQrJsZjqd.djrlxdh}";
	   $('input[name=tjrlxdh]').attr('value',tjrlxdh);
	   var djrsfz="${fgQrJsZjqd.dsrsfzhm}";
	   $('input[name=djrsfz]').attr('value',djrsfz);
	   var djrq="${fgQrJsZjqd.djrq}";
	   $("#djrq").datebox("setValue", djrq); 
	   var qscyr='<%=zjrXm%>';
	   $('input[name=qscyr]').attr('value',qscyr);
   });   
   
   <%
   for (int i = 0; i < list.size(); i++) {
		String clmc = list.get(i).getClmc();
		String clfs = String.valueOf(list.get(i).getFs());
		String clys = String.valueOf(list.get(i).getYs());
   %>
       $s=$('#clmxtr').clone(true);
       $s.css("visibility","visible");
       $s.find('input[name=clmc]').attr('value','<%=clmc%>');
       $s.find('input[name=clys]').attr('value','<%=clys%>');
       $s.find('input[name=clfs]').attr('value','<%=clfs%>');
       $('#clmxtr').parent().append($s);
   <%}%>
   </script>
   
   <script>
   function save(){
       $.post('${path}/fgJsClQd.do', {
    	   bh: '${fgQrJsZjqd.id.bh}'
  	   }, function(data){
	   	   if(data.after==1)
   	       	  alert("操作成功");
   	       if(data.after==0)
   	          alert("操作失败");    	     
   	       window.location.href="${path}/to_fgJsCl.do";
  	   }, 'json')
   }
   
   function back(){
	   $('#tuiHui').dialog('open');
   }
   
   function cancel(){
	   window.location.href="${path}/to_fgJsCl.do";
   }
   
   $('input').attr('readOnly',true);
   </script>
   
<script>
var cbrid = '<%=cbrid%>';
var lclx = '<%=lclx%>';
$('#thList').combobox({
	url:'zdmxcx_thyy.do?zdbm=thyy&cbr='+cbrid+"&lclx="+lclx,  
    valueField:'zdmxbm',    
    textField:'zdmxmc',
    onSelect :function(record){
    	var text = $('#thyj').val();
    	if(text=="")
    		$('#thyj').val(record.zdmxmc);
    	else
    		$('#thyj').val(text+","+record.zdmxmc);
    }
})

$('#tuiHui').dialog({
    title:'退回原因',
    iconCls:'icon-tip',
    closed: true,
    
    buttons:[{
        text:'确认',
        iconCls:'icon-ok',        
        handler:function(){
	       	 var thdx=$('input[name=thlx]:checked').val();
	       	 if(thdx==null){
	       		 alert("请选择回退对象");
	       		 return;
	       	 }
	       	 var bh="${fgQrJsZjqd.id.bh}";
	       	 var htyj=$('#thyj').val();
	       	 if(htyj==''){
	       		alert("必须输入退回原因！");
	       		return;
	       	 }
	         $.post('${path}/tuiHuiCL.do', {  
	           	 bh:bh,
	       	   	 htyj:htyj,
	       	     thdx:thdx
	       	 }, function(data) {
	       	     if(data.after==1)
	          	   alert("操作成功");
          	     if(data.after==0)
          	       alert("操作失败");    	     
         	     window.location.href="${path}/to_fgJsCl.do";
	       	 }, 'json');
        }
    },{
        text:'取消',
        iconCls:'icon-cancel',
        handler:function(){
            $('#tuiHui').dialog('close');
        }
    }]
});

var lclx="${fgQrJsZjqd.lclx}";
if(lclx=='flj'){
	$('#back').css('display','none');
}
</script>

</body>
</html>