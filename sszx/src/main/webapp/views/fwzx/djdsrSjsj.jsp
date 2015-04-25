<%@ page language="java" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*,com.bsoft.sszx.entity.zjqd.*,com.bsoft.sszx.dao.*,com.bsoft.sszx.entity.sms.*,com.bsoft.sszx.entity.clb.*"%>
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
List<Clb> list=(List<Clb>)session.getAttribute("djdsrSjZjqdClb");
Zjqd zjdq=(Zjqd)session.getAttribute("djdsrSjZjqd");
int bh=zjdq.getId().getBh();
String qscyr=zjdq.getQscyr();
String zjrXm=new UserDao().findUserById(qscyr, fydm).getYhxm();

Dxx dxx=new DxxDao().findByZt(String.valueOf(zjdq.getZt()), fydm);
String nr=dxx.getNr();
int lx=dxx.getZdfs();

String uid=(String)session.getAttribute("user");
String fymc=new FyDao().fymc(fydm);//法院名称
String dsr=zjdq.getDjr();//当事人姓名
String cbr=zjdq.getSjrXm();//承办人姓名
String cbrid = zjdq.getSjr();
String lclx = zjdq.getLclx();
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
    <div style="margin-left:20px;margin-top:10px;">
	    	 退回原因:<input id="thList" style="width:100px" />
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
       <td><input class="easyui-datebox" editable="false" id="djrq" name="djrq" type="text"></input></td>
     </tr>
      <tr><td colspan="4"><br/><span>是否发送短消息【预览/编辑】：</span>
        <input id="r_1" type="radio" name="identity" value="0" />是
        <input id="r_2" type="radio" name="identity" value="1" />否
    <br/></td></tr>
    <tr><td colspan="4"><textarea style="font-size:12px" id="sms" cols="75" rows="3"><%=nr%></textarea></td></tr>
     </table>
     <hr/> 
     
     <div style="margin-left:10px;">     
     <div class="cl" id="clmxtr" style="visibility:hidden;margin-top:5px">
       <div>材料名称：<input name="clmc" type="text"></input>
       &nbsp;&nbsp;份数： <input name="clfs" style="width:30px" type="text"></input>
       &nbsp;&nbsp;页数： <input name="clys" style="width:30px" type="text"></input>      
       </div>
     </div>
   </div>
   <hr/>
   <div align="center">
   <a id="print" style="margin-left:10px;" onclick="Word()" iconCls="icon-save">生成并打印表单</a>
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
   $('#print').linkbutton({});

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


   //文档加载完成后要执行的内容 
   $(document).ready(function(){ 
	   var curr_time = new Date();
	   var strDate = curr_time.getFullYear()+"-";
	   strDate += curr_time.getMonth()+1+"-";
	   strDate += curr_time.getDate();
	   $("#djrq").datebox("setValue", strDate); 
	   
	   var lx='<%=lx%>';
	   if(lx==0)
	   	   $('#r_1').attr('checked','checked');
	   if(lx==1)
	   	   $('#r_2').attr('checked','checked');	  
	   
	   //var ah="${session.djdsrSjZjqd.ah}";
	   var ah="<%=zjdq.getAh()%>";
	   $('input[name=ah]').attr('value',ah);	   
	   
	   //var sjr="${session.djdsrSjZjqd.sjrXm}";
	   var sjr="<%=zjdq.getSjrXm()%>";
	   $('input[name=sjr]').attr('value',sjr);
	   
	   //var tjr="${session.djdsrSjZjqd.djr}";
	   var tjr="<%=zjdq.getDjr()%>";
	   $('input[name=tjr]').attr('value',tjr);
	   
	   //var tjrlxdh="${session.djdsrSjZjqd.djrlxdh}";
	   var tjrlxdh="<%=zjdq.getDjrlxdh()%>";
	   $('input[name=tjrlxdh]').attr('value',tjrlxdh);
	   
	   //var djrsfz="${session.djdsrSjZjqd.dsrsfzhm}";
	   var djrsfz="<%=zjdq.getDsrsfzhm()%>";
	   $('input[name=djrsfz]').attr('value',djrsfz);
	   
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
       $s.find('input[name=clfs]').attr('value','<%=clfs%>');
       $('#clmxtr').parent().append($s);
   <%}%>
   </script>
   
   <script>
   function save(){	   
	   var bh="<%=bh%>";
	   var lx=$('input[name=identity]:checked').attr('value');
	   var cl='';
       var alerString='';
   	for(var i=1;i<$('input[name=clmc]').length;i++){//input要使用name属性辨别
   		var clmc=$('input[name=clmc]').eq(i).val();
   		var clfs=$('input[name=clfs]').eq(i).val();
   		var clys=$('input[name=clys]').eq(i).val();
   		if(clys==''){
   	    	alerString=":材料页数";
   	    	break;}
   	    var clfull=clmc+','+clfs+','+clys+';';	
   		cl+=clfull;	   
   	}
   	var re=$('input.easyui-validatebox').length;
	
	var tr=0;
	for(var i=0;i<re;i++){
		var content=$('input.easyui-validatebox').eq(i).val();
		if(content=='')
			tr+=1;
	}
	if(tr==0 && alerString==''){
    $.ajax({
   	     url:'djdsrSjsjQd.do',
   	     type:'POST',
   	     data:{  bh:bh,
   	    	     cl:cl,
   	    	     sffs:lx,
  	    	     sms:$('#sms').val()
   	     },//注意大小写data
   	     dataType:'json',
   	     success:function (data) {
   	    	 if(data.after==1)
      	       alert("操作成功");
      	     if(data.after==0)
      	       alert("操作失败");    	     
     	     window.location.href="${path}/to_djdsrSj.do";
   	     }})
   	    ;}else alert('请输入必输项'+alerString);}
   
   function back(){
	   $('#tuiHui').dialog('open');
	  }
   
   function cancel(){
	   window.location.href="${path}/to_djdsrSj.do";
   }
   
   $('input').attr('readOnly',true);
   $('input[name=clys]').attr('readOnly',false);
   $('input[name=clfs]').attr('readOnly',false);
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
        	 var bh="<%=bh%>";
        	 var htyj=$('#thyj').val();
        	 if(htyj==""){
        		 alert("必须填写退回原因！");
        		 return;
        	 }
             $.ajax({
        	     url:'tuiHuiCL.do',
        	     type:'POST',
        	     data:{  bh:bh,
        	    	     htyj:encodeURI(encodeURI(htyj)),
        	    	     thdx:5
        	     },//注意大小写data
        	     dataType:'json',
        	     success:function (data) {
        	    	 if(data.after==1)
           	       alert("操作成功");
           	     if(data.after==0)
           	       alert("操作失败");    	     
          	     window.location.href="${path}/to_djdsrSj.do";
          	     
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

function Word(){
	var bh="<%=bh%>";
	var cl='';
	var alerString='';
	for(var i=1;i<$('input[name=clmc]').length;i++){//input要使用name属性辨别
		var clmc=$('input[name=clmc]').eq(i).val();
		var clfs=$('input[name=clfs]').eq(i).val();
		var clys=$('input[name=clys]').eq(i).val();
		if(clys==''){
   	    	alerString=":材料页数";
   	    	break;}
	    var clfull=clmc+','+clfs+','+clys+';';	
		cl+=clfull;	   
	}
	
	
	if(alerString==''){
    $.ajax({
	     url:'saveDsrtjYs.do',//保存页数
	     type:'POST',
	     data:{  bh:bh,
	    	     cl:cl
	     },//注意大小写data
	     dataType:'json',
	     success:function(data){
	    	 var bh='<%=bh%>';
	    	 url='word.do?bh='+bh;
	    	 if(data.after==1)
	    	 window.open(url,"new",
	    			 "height=600px,width=650px,toolbar=no,status=no,menubar=no,scrollbars=yes,resizable=yes");
	     }})
	    ;}else alert('请输入必输项'+alerString);
};
</script>
</body>
</html>