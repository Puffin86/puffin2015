<%@ page language="java" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*,com.bsoft.sszx.entity.zjqd.*"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>材料退回</title>
<jsp:include page="/common/include.jsp" />
</head>
<% 
Zjqd Zjdq=(Zjqd)session.getAttribute("cxZzSj");
String bh=String.valueOf(Zjdq.getId().getBh());
String fydm=Zjdq.getId().getFydm();
%>
<body style="font-size:12px;">
    <div id="QR" style="width:350px;">
    <table style="width:300px;font-size:12px;margin-left:30px;">
    <tr><td width="30%"><span style="color:red">承办部门：</span></td><td><%=Zjdq.getSjrBmmc()%></td></tr>
    <tr><td width="30%"><span style="color:red">承办人：</span></td><td><%=Zjdq.getSjrXm()%></td></tr>
    <tr><td width="30%"><span style="color:red">案号：</span></td><td><%=Zjdq.getAh()%></td></tr>
    <tr><td width="30%"><span style="color:red">当事人：</span></td><td><%=Zjdq.getDjr()%></td></tr>
    </table>
    </div>

<script>
$('#QR').dialog({
    title:'是否撤销该任务',
    iconCls:'icon-tip',  
    closable:false,
    buttons:[{
        text:'确认(不可恢复)',
        iconCls:'icon-ok',
        handler:function(){
        	 $.ajax({
        	     url:'cxQd.do',
        	     type:'POST',
        	     data:{bh:<%=bh%>,
        	    	   fydm:<%=fydm%>
        	      },//注意大小写data
        	     dataType:'json',
        	     success:function (res) {
        	       if(res.after==1)
        	         alert("撤销成功");
        	       //window.location.href="tuiHuiCL.jsp";
        	       window.location.href="${path}/to_tuiHuiCL.do";
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
	 window.location.href="${path}/to_tuiHuiCL.do";
	 //event.preventDefault();//ie6专用
});
</script>

</body>
</html>