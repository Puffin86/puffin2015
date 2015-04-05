<%@ page import="com.bsoft.sszx.entity.user.User"%>
<%@ page import="com.bsoft.sszx.dao.UserDao"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>新任务-预约当事人领取材料</title>
<jsp:include page="/common/include.jsp" />
</head>

<%
	String user=(String)session.getAttribute("user");
	String fydm=(String)session.getAttribute("fydm");
	UserDao userDao=new UserDao();
	User Us=userDao.findUserById(user, fydm);
	String userBm=userDao.findBm(Us.getYhbm(), fydm).getBmmc();
%>
  
<body style="font-size:12px;background:#fff;">

    <table style="font-size:12px;margin-left:10px;" id="ssclzjqd">
     <tr>
       <td width="20%">案号：</td>
       <td>
	       	<input class="easyui-validatebox" required="true" name="ah" type="text"/>
	       	<input name="ahdm" style="display:none;" type="text"/>
	       	<a id="research" onClick="$('#ah_se').dialog('open');" style="margin-top:-5px" iconCls="icon-search"></a>
       </td>
     </tr>
     <tr>
       <td width="20%" >承办人：</td>
       <td><input class="easyui-validatebox" required="true" readOnly="readOnly" name="sjrXm" type="text"></input>
           <input style="display:none;" name="sjr" type="text"></input></td>
       <td width="20%" >&nbsp;&nbsp;承办部门：</td>
       <td><input class="easyui-validatebox" required="true" readOnly="readOnly" name="sjrbmMc" type="text"></input>
           <input style="display:none;" name="sjrbm" type="text"></input>
       </td>
     </tr>
     <tr>
       <td width="20%" >当事人：</td>
       <td><input class="easyui-validatebox" required="true" name="tjr" type="text"></input>
       <input style="display:none;" type="text"></input>
       <a id="dsr_bt" onClick="$('#dsr_se').dialog('open');" style="margin-top:-5px" iconCls="icon-add"></a></td>
       <td width="20%">&nbsp;&nbsp;当事人联系电话：</td>
       <td><input name="tjrlxdh" type="text"></input></td>
     </tr> 
     <tr>
       <td width="20%">当事人证件号码：</td>
       <td><input name="djrsfz" type="text"></input></td>
       <td width="20%">&nbsp;&nbsp;递交日期：</td>
       <td><input editable="false" class="easyui-datebox" id="djrq" name="djrq" type="text"></input></td>
     </tr>
     </table>
     <hr/> 
     
     <div style="margin-left:10px;">     
     <div>添加材料信息：<a id="cl_add" class="add" iconCls="icon-add"></a></div>
     <div class="cl" id="clmxtr" style="visibility:hidden;margin-top:5px">
       <div>材料名称：<input name="clmc" type="text"></input>
       &nbsp;&nbsp;份数： <input name="clfs"  style="width:30px" type="text"></input>
       &nbsp;&nbsp;页数： <input name="clys" style="width:30px" type="text"></input>      
       <a id="cl_remove" class="remove" style="margin-top:-7px" iconCls="icon-remove"></a></div>
     </div>
   </div>
   
   <hr/>
   <div align="center">
     <a id="save" onclick="save()" iconCls="icon-save">保存</a>
     <a id="cancel" href="${path}/to_flq.do" iconCls="icon-cancel">取消</a>
   </div>
   
<script>
$(document).ready(function(){ 
	   $('#research').linkbutton({});
	   $('#cl_remove').linkbutton({});
	   $('#cl_add').linkbutton({});
	   $('#save').linkbutton({});
	   $('#cancel').linkbutton({});
	   $('#cbr_search').linkbutton({});
	   $('#search_bt').linkbutton({});   
	   $('#dsr_bt').linkbutton({});
	   
	   var sjrXm='<%=Us.getYhxm()%>';
	   $('input[name=sjrXm]').attr('value',sjrXm);	   
	   var sjr='<%=Us.getId().getYhid()%>';
	   $('input[name=sjr]').attr('value',sjr);
	   var sjrbm='<%=Us.getYhbm()%>';
	   $('input[name=sjrbm]').attr('value',sjrbm);
	   var sjrbmMc='<%=userBm%>';
	   $('input[name=sjrbmMc]').attr('value',sjrbmMc);
	   
	   
	   var curr_time = new Date();
	   var strDate = curr_time.getFullYear()+"-";
	   strDate += curr_time.getMonth()+1+"-";
	   strDate += curr_time.getDate();
	   $("#djrq").datebox("setValue", strDate); 
	
   //绑定添加行按钮的单击事件 
   $(".add").bind("click",function(){ 
	   $s=$('#clmxtr').clone(true);
	   $s.css("visibility","visible");
	   $('#clmxtr').parent().append($s);
   }); 

   //绑定删除行按钮的单击事件 
   $(".remove").bind("click", function(){ 
	   //取得table的第一行 
	   if($(".remove").length>1){
		   var td = $(this).parent();
		   td.empty();//清空父元素
		   td.remove();
	   }
   }); 
});   
</script>
   
<script>
function save(){
    var ah=$('input[name=ah]').val();
    var sjr=$('input[name=sjr]').val();
    var tjr=$('input[name=tjr]').val();
    var djrq=$('input[name=djrq]').val();
    var tjrlxdh=$('input[name=tjrlxdh]').val();
    var zjr=$('input[name=zjr]').val();
    var zjrq=$('input[name=zjrq]').val();
    var djrsfzhm=$('input[name=djrsfz]').val();
       
    var cl='';
    var alerString='';
   	for(var i=1;i<$('input[name=clmc]').length;i++){//input要使用name属性辨别
   		var clmc=$('input[name=clmc]').eq(i).val();
   	    if(clmc==''){
   	    	alerString=":材料名称";
   	    	break;}
   		var clfs=$('input[name=clfs]').eq(i).val();
   		if(clfs==''){
   	    	alerString=":材料份数";
   	    	break;}
   		var clys=$('input[name=clys]').eq(i).val();
   		if(clys==''){
   	    	alerString=":材料页数";
   	    	break;}
   	    var clfull=clmc+','+clfs+','+clys+';';	
   		cl+=clfull;	   
   	}
   	
    var sjrbm=$('input[name=sjrbm]').val();
	var sjrXm=$('input[name=sjrXm]').val();
	var sjrbmMc=$('input[name=sjrbmMc]').val();
		
    var re=$('input.easyui-validatebox').length;
	var tr=0;
	for(var i=0;i<re;i++){
		var content=$('input.easyui-validatebox').eq(i).val();
		if(content=='')
			tr+=1;
	}
	
	if(tr==0 && alerString==''){
        $.ajax({
   	     	url:'${path}/saveFlq.do',
   	     	type:'POST',
   	     	data:{
   	     		 ah:encodeURI(encodeURI(ah))
                 ,sjr:encodeURI(encodeURI(sjr))
                 ,tjr:encodeURI(encodeURI(tjr))
                 ,djrq:encodeURI(encodeURI(djrq))
                 ,tjrlxdh:encodeURI(encodeURI(tjrlxdh))
                 ,zjr:encodeURI(encodeURI(zjr))
                 ,zjrq:encodeURI(encodeURI(zjrq))
                 ,djrsfzhm:encodeURI(encodeURI(djrsfzhm))
                 ,cl:encodeURI(encodeURI(cl))
                 ,sjrbm:encodeURI(encodeURI(sjrbm))
                 ,sjrXm:encodeURI(encodeURI(sjrXm))
                 ,sjrbmMc:encodeURI(encodeURI(sjrbmMc))
   	     	},
   	     	dataType:'json',
   	     	success: function(data) {
	   	    	 if(data.after==1)
	    	       alert("保存成功");
	    	     if(data.after==0)
	    	       alert("保存失败");    	     
	   	    	 window.location.href="${path}/to_flq.do";
   	     	}
   	  	});
     }else{
    	alert('请输入必输项'+alerString);
     }
}
</script>
   
<div id="ah_se" style="width:400px;height:300px;padding:5px;">
   <table style="font-size:12px">
	   	<tr>
	   		<td>年份：</td>
	   		<td><input id="ahN" style="margin-left:5px;margin-top:5px" type="text"/></td>
	   	</tr>
	    <tr>
	    	<td>关键字：</td>
	    	<td>
		    	<input id="ahG" style="margin-left:5px;margin-top:5px" type="text"/>
		        <a id="search_ah" class="easyui-linkbutton" onclick="searchAh()" iconCls="icon-search"></a>
	        </td>
	    </tr>
   </table>
   <hr/>
   
   <ul id="ah_searchList"></ul>
</div>
   
<script>
$('#ah_se').dialog({
    title:'添加案号',
    iconCls:'icon-search',
    closed:true
});
 
$('#ah_searchList').tree({  
    checkbox: false,
    onClick: function(node){		 
		if(node.attributes.leaf=="true"){				 
			 $('input[name=ah]').val(node.attributes.ah);				 			 
			 $('input[name=sjrXm]').val(node.attributes.cbrxm);
			 $('input[name=sjrbm]').val(node.attributes.cbbm);
			 $('input[name=sjr]').val(node.attributes.cbr);
			 $('input[name=sjrbmMc]').val(node.attributes.bmmc);
			 $('input[name=ahdm]').val(node.attributes.ahdm);
		 }
    }
});  
 
function searchAh(){
	var ahN=$('#ahN').val();
	var ahG=$('#ahG').val();
	
	if(ahN != '' && ahG != ''){
		$.ajax({
	  	     url:'${path}/ahSearch.do',
	  	     type:'POST',
	  	     data:{
	  	    	ahN:encodeURI(encodeURI(ahN)),
	  	    	ahG:encodeURI(encodeURI(ahG)),
	  	    	lx:1
	  	     },
	  	     dataType:'json',
	  	     success:function (data) {
	  	        $('#ah_searchList').tree('loadData', data.data);
	  	     }
	  	});
	}
}
</script>
   
<div id="dsr_se" style="width:400px;height:300px;padding:5px;">
	<div style="margin-left:5px;">
		载入当事人列表：<a id="search_dsr" class="easyui-linkbutton" onclick="searchDsr()" iconCls="icon-search"></a>
	</div>
	<hr/>
	<ul id="dsr_searchList"></ul>
</div>

<script>
$('#dsr_se').dialog({
	title:'添加当事人',
	iconCls:'icon-search',
	closed:true
});
 
$('#dsr_searchList').tree({  
    checkbox: false,
    onClick: function(node){		 
		if(node.attributes.leaf=="true"){				 
			 $('input[name=tjrlxdh]').val(node.attributes.lxdh);				 			 
			 $('input[name=djrsfz]').val(node.attributes.sfzhm);
			 $('input[name=tjr]').val(node.attributes.dsrxm);
		}
    }
});  
 
function searchDsr(){
    var ah=$('input[name=ah]').val();
    
	if(ah!=null && ah!=''){
		$.ajax({
	 	     url:'${path}/dsrSearch.do',
	 	     type:'POST',
	 	     data:{ah:encodeURI(encodeURI(ah))},
	 	     dataType:'json',
	 	     success:function (data) {
	 	     	$('#dsr_searchList').tree('loadData', data.data);
	 	     }
	 	});
	}else{
		alert("请先输入案号");
	}
}
</script>
   
</body>
</html>