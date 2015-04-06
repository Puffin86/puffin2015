<%@ page language="java" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*,com.bsoft.sszx.dao.*,com.bsoft.sszx.entity.zjqd.*,com.bsoft.sszx.entity.fjb.*,com.bsoft.sszx.entity.clb.*,com.bsoft.sszx.entity.sms.*" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>接收材料</title>
<jsp:include page="/common/include.jsp" />
</head>

<%
String fydm=(String)session.getAttribute("fydm");
List<Clb> list=(List<Clb>)session.getAttribute("djdsrLqZjqdClb");
Zjqd zjdq=(Zjqd)session.getAttribute("djdsrLqZjqd");
ZjqdId zjdqId=zjdq.getId();
String qscyr=zjdq.getQscyr();
String zjrXm=new UserDao().findUserById(qscyr, fydm).getYhxm();

Dxx dxx=new DxxDao().findByZt(String.valueOf(zjdq.getZt()), fydm);
String nr=dxx.getNr();
int lx=dxx.getZdfs();

String uid=(String)session.getAttribute("user");
String fymc=new FyDao().fymc(fydm);//法院名称
String dsr=zjdq.getDjr();//当事人姓名
String cbr=zjdq.getSjrXm();//承办人姓名
String ah=zjdq.getAh();//案号
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
    <div id="tuiHui" style="width:430px;height:250px;">
    <textarea id="thyj" rows="8" cols="50" style="margin-top:20px;margin-left:20px;font-size:12px;"></textarea>
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
       <tr><td colspan="4"><br/><span>是否发送短消息【预览/编辑】：</span>
        <input id="r_1" type="radio" name="identity" value="0" />是
        <input id="r_2" type="radio" name="identity" value="1" />否
    <br/></td></tr>
    <tr><td colspan="4"><textarea style="font-size:12px" id="sms" cols="73" rows="3"><%=nr%></textarea></td></tr>
     </table>
     <hr/> 
     
     <div style="margin-left:10px;">     
     <div class="cl" id="clmxtr" style="visibility:hidden;margin-top:5px">
       <div>材料名称：<input name="clmc" type="text"></input>
       &nbsp;&nbsp;份数： <input name="clfs"  style="width:30px" type="text"></input>
       &nbsp;&nbsp;页数： <input name="clys" style="width:30px" type="text"></input>      
       </div>
     </div>
   </div>
   
   <hr/>
   <div align="center">
     <a id="save" onclick="save()" iconCls="icon-ok">确认</a>
     <a id="back" onclick="back()" iconCls="icon-no">退回</a>
     <a id="cancel" onclick="cancel()" iconCls="icon-cancel">取消</a>
   </div>
   <!-- <a onclick="test()">test</a> -->
   <script>
   $('#back').linkbutton({});
   $('#save').linkbutton({});
   $('#cancel').linkbutton({});

   /*function test(){
   	var s='';
   	for(var i=0;i<$('input[name=clmc]').length;i++){//input要使用name属性辨别
   		var ss=$('input[name=clmc]').eq(i).val();
   	    s+=ss;	
   	}
   		alert(s);
   }*/


   //文档加载完成后要执行的内容 
   $(document).ready(function(){ 
	   var lx='<%=lx%>';
	   if(lx==0)
	   	   $('#r_1').attr('checked','checked');
	   if(lx==1)
	   	   $('#r_2').attr('checked','checked');	  
	   	   
	   var ah="<%=zjdq.getAh()%>";
	   $('input[name=ah]').attr('value',ah);
	   	   
	   //var sjr="${session.djdsrLqZjqd.sjrXm}";
	   var sjr='<%=zjdq.getSjrXm()%>';
	   $('input[name=sjr]').attr('value',sjr);
	   
	   //var tjr="${session.djdsrLqZjqd.djr}";
	   var tjr='<%=zjdq.getDjr()%>';
	   $('input[name=tjr]').attr('value',tjr);
	   
	   //var tjrlxdh="${session.djdsrLqZjqd.djrlxdh}";
	   var tjrlxdh='<%=zjdq.getDjrlxdh()%>';
	   $('input[name=tjrlxdh]').attr('value',tjrlxdh);
	   
	   //var djrsfz="${session.djdsrLqZjqd.dsrsfzhm}";
	   var djrsfz='<%=zjdq.getDsrsfzhm()%>';
	   $('input[name=djrsfz]').attr('value',djrsfz);
	   
	   //var djrq="${session.djdsrLqZjqd.djrq}";
	   var djrq='<%=zjdq.getDjrq()%>';
	   $("#djrq").datebox("setValue", djrq); 
	   
	   var qscyr='<%=zjrXm%>';
	   $('input[name=qscyr]').attr('value',qscyr);
   });   
   
   <%for(int i=0;i<list.size();i++) 
   {
	   String clmc=list.get(i).getClmc();
	   String clfs=String.valueOf(list.get(i).getFs());
	   String clys=String.valueOf(list.get(i).getYs());
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
	   //var bh="${session.djdsrLqZjqd.id.bh}";
	   var bh='<%=zjdqId.getBh()%>';
	   var lx=$('input[name=identity]:checked').attr('value');
        $.ajax({
   	     url:'djdsrLqQd.do',
   	     type:'POST',
   	     data:{  bh:bh,
	    	   sffs:lx,
	    	   sms:$('#sms').val()
   	     },//注意大小写data
   	     dataType:'json',
   	     success:function (data) {
   	    	 if(data.after==1)
      	       alert("操作成功");
      	     if(data.after==0)
      	       alert("操作失败");    	     
     	     window.location.href="${path}/to_djdsrLq.do";
   	     }})
       ;}
   
   function back(){
	   $('#tuiHui').dialog('open');
	  }
   
   function cancel(){
	   window.location.href="${path}/to_djdsrLq.do";
   }
   
   $('input').attr('readOnly',true);
   </script>
   
   <script>
$('#tuiHui').dialog({
    title:'退回原因',
    iconCls:'icon-tip',
    closed:true,
    buttons:[{
        text:'确认',
        iconCls:'icon-ok',        
        handler:function(){
        	 //var bh="${session.djdsrLqZjqd.id.bh}";
        	  var bh='<%=zjdqId.getBh()%>';
        	 var htyj=$('#thyj').val();
             $.ajax({
        	     url:'tuiHuiCL.do',
        	     type:'POST',
        	     data:{  bh:bh,
        	    	     htyj:encodeURI(encodeURI(htyj)),
        	    	     thdx:4
        	     },//注意大小写data
        	     dataType:'json',
        	     success:function (data) {
        	    	 if(data.after==1)
           	       alert("操作成功");
           	     if(data.after==0)
           	       alert("操作失败");    	     
          	     window.location.href="${path}/to_djdsrLq.do";
        	     }});
        }
    },
    {
        text:'取消',
        iconCls:'icon-cancel',
        handler:function(){
            $('#tuiHui').dialog('close');
        }
    }]
});
</script>
</body>
</html>