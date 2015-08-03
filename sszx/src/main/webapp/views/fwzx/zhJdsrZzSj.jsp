<%@ page language="java" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*,com.bsoft.sszx.dao.*,com.bsoft.sszx.entity.zjqd.*,com.bsoft.sszx.entity.sms.*" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<jsp:include page="/common/include.jsp" />
</head>
<% 
Zjqd Zjdq=(Zjqd)session.getAttribute("zhJDsrZzSj");
String bh=String.valueOf(Zjdq.getId().getBh());
String fydm=Zjdq.getId().getFydm();

Dxx dxx=new DxxDao().findByZt(String.valueOf(Zjdq.getZt()), fydm);
String nr=dxx.getNr();
int lx=dxx.getZdfs();

String uid=(String)session.getAttribute("user");
String fymc=new FyDao().fymc(fydm);//法院名称
String dsr=Zjdq.getDjr();//当事人姓名
String cbr=Zjdq.getSjrXm();//承办人姓名
String ah=Zjdq.getAh();//案号
String zjr=new UserDao().findUserById(uid, fydm).getYhxm();//转交人

String c1="[法院名称]";
String c2="[当事人]";
String c3="[承办人]";
String c4="[案号]";
String c5="[转交人]";
nr=nr.replace(c1, fymc);
nr=nr.replace(c2, dsr);
nr=nr.replace(c3, cbr);
nr=nr.replace(c4, ah);
nr=nr.replace(c5, zjr);
%>
<body style="font-size:12px;">
    <div id="QR" style="width:350px;">
    <table style="width:300px;font-size:12px;margin-left:30px;">
    <tr><td width="30%"><span style="color:red">承办部门：</span></td><td><%=Zjdq.getSjrBmmc()%></td></tr>
    <tr><td width="30%"><span style="color:red">承办人：</span></td><td><%=Zjdq.getSjrXm()%></td></tr>
    <tr><td width="30%"><span style="color:red">案号：</span></td><td><%=Zjdq.getAh()%></td></tr>
    <tr><td width="30%"><span style="color:red">当事人：</span></td><td><%=Zjdq.getDjr()%></td></tr>
    <tr><td colspan="2"><hr/></td></tr>
    <tr><td colspan="2"><span>是否发送短消息【预览/编辑】：</span>
        <input id="r_1" type="radio" name="identity" value="0" />是
        <input id="r_2" type="radio" name="identity" value="1" />否
    <br/></td></tr>
    <tr><td colspan="2"><textarea style="font-size:12px" id="sms" cols="35" rows="5"><%=nr%></textarea></td></tr>
    </table>
    </div>

<script>
var lx='<%=lx%>';
if(lx==0)
	   $('#r_1').attr('checked','checked');
if(lx==1)
	   $('#r_2').attr('checked','checked');	  

$('#QR').dialog({
    title:'是否确认转交给承办人',
    iconCls:'icon-tip',  
    closable:false,
    buttons:[{
        text:'确认',
        iconCls:'icon-ok',
        handler:function(){
        	 var lx=$('input[name=identity]:checked').attr('value');
        	 $.ajax({
        	     url:'zhJiaoDsrZzSjZj.do',
        	     type:'POST',
        	     data:{bh:<%=bh%>,
        	    	   fydm:<%=fydm%>,
        	    	   sffs:lx,
        	    	   sms:$('#sms').val()
        	      },//注意大小写data
        	     dataType:'json',
        	     success:function (res) {
        	       if(res.after==1)
        	         alert("转交成功"+res.msg);
        	       else
        	       	alert("转交失败:"+res.msg)
        	       window.location.href="${path}/to_jsdsrzdsj.do";
        	     }
        });
        }
    },{
        text:'取消',
        iconCls:'icon-cancel',
        id:'quxiao'
    }]
});

$('#quxiao').click(function(event){
	 window.location.href="${path}/to_jsdsrzdsj.do";
	 event.preventDefault();//ie6专用
});
</script>

</body>
</html>