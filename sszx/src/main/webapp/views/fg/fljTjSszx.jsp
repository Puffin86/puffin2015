<%@page import="com.bsoft.sszx.dao.UserDao"%>
<%@page import="com.bsoft.sszx.dao.FyDao"%>
<%@page import="com.bsoft.sszx.dao.DxxDao"%>
<%@page import="com.bsoft.sszx.entity.sms.Dxx"%>
<%@page import="com.bsoft.sszx.entity.zjqd.Zjqd"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>通知当事人提交材料</title>
<jsp:include page="/common/include.jsp" />
</head>

<% 
	Zjqd zjdq=(Zjqd)session.getAttribute("fljtjSsZx");
	String bh=String.valueOf(zjdq.getId().getBh());
	String fydm=zjdq.getId().getFydm();
	
	Dxx dxx=new DxxDao().findByZt(String.valueOf(zjdq.getZt()), fydm);
	String nr=dxx.getNr();
	int lx=dxx.getZdfs();
	
	String uid=(String)session.getAttribute("user");
	String fymc=new FyDao().fymc(fydm);//法院名称
	String dsr=zjdq.getDjr();//当事人姓名
	String cbr=zjdq.getSjrXm();//承办人姓名
	int sx = zjdq.getSx();//时限
	String ah=zjdq.getAh();//案号
	String zjr=new UserDao().findUserById(uid, fydm).getYhxm();//转交人

	String c1="[法院名称]";
	String c2="[当事人]";
	String c3="[承办人]";
	String c4="[案号]";
	String c5="[转交人]";
	String c6="[时限]";
	nr=nr.replace(c1, fymc);
	nr=nr.replace(c2, dsr);
	nr=nr.replace(c3, cbr);
	nr=nr.replace(c4, ah);
	nr=nr.replace(c5, zjr);
	nr=nr.replace(c6, sx+"");
%>

<body style="background:#fff;">

<div id="QR" style="width:350px;">
	<table style="width:300px;font-size:12px;margin-left:30px;">
		<tr>
			<td width="30%"><span style="color:red">接收部门：</span></td>
			<td><%=zjdq.getSjrBmmc()%></td>
		</tr>
		<tr>
			<td width="30%"><span style="color:red">承办人：</span></td>
			<td><%=zjdq.getSjrXm()%></td>
		</tr>
		<tr>
			<td width="30%"><span style="color:red">案号：</span></td>
			<td><%=zjdq.getAh()%></td>
		</tr>
		<tr>
			<td width="30%"><span style="color:red">当事人：</span></td>
			<td><%=zjdq.getDjr()%></td>
		</tr>
		<tr>
			<td colspan="2"><hr/></td>
		</tr>
		<tr>
			<td colspan="2">
				<span>是否发送短消息【预览/编辑】：</span>
			    <input id="r_1" type="radio" name="identity" value="0" />是
			    <input id="r_2" type="radio" name="identity" value="1" />否
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<textarea style="font-size:12px" id="sms" cols="35" rows="5"><%=nr%></textarea>
			</td>
		</tr>
	</table>
</div>

<script>
var lx='<%=lx%>';
if(lx==0)
	$('#r_1').attr('checked','checked');
if(lx==1)
	$('#r_2').attr('checked','checked');	 
	   
$('#QR').dialog({
    title:'是否确认通知诉讼中心收件',
    iconCls:'icon-tip',  
    closable:false,
    modal: true,
    
    buttons:[{
        text:'确认',
        iconCls:'icon-ok',
        handler: function(){
        	 $.ajax({
        	     url:'${path}/fljTjSszxTz.do',
        	     type:'POST',
        	     data:{
        	    	 bh:<%=bh%>,
        	    	 fydm:<%=fydm%>,
        	    	 sffs: $('input:radio[name="identity"]').is(":checked"),//lx
        	    	 sms:$('#sms').val()
        	     },
        	     dataType:'json',
        	     success:function (res) {
		       	       if(res.after==1){
		       	       		alert("通知成功");
			       	        window.location.href="${path}/to_flj.do";
		       	       }else{
		       	    	    alert("通知失败,返回后请重新操作");
		       	    	    window.location.href="${path}/to_flj.do";
		       	       }
        	     }
        	});
        }
    },{
        text:'取消',
        iconCls:'icon-cancel',
        id:'quxiao',
        handler: function(){
        	window.location.href="${path}/to_flj.do";
        }
    }]
});
</script>

</body>
</html>