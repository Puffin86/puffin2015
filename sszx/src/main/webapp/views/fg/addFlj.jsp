<%@ page import="com.bsoft.sszx.entity.user.User"%>
<%@ page import="com.bsoft.sszx.dao.UserDao"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>新任务-预约当事人提交材料</title>
<jsp:include page="/common/include.jsp" />
</head>

<%
	String user=(String)session.getAttribute("user");
	String fydm=(String)session.getAttribute("fydm");
	UserDao userDao=new UserDao();
	User Us=userDao.findUserById(user, fydm);
	String userBm=userDao.findBm(Us.getYhbm(), fydm).getBmmc();
%>
  
<body style="background-color:#fff;">

    <table style="font-size:12px;" id="ssclzjqd" width="100%" border="0" cellpadding="2" cellspacing="0">
     <tr>
       <td width="15%">案号：</td>
       <td width="35%">
	       <input class="easyui-validatebox" required="true" name="ah" type="text"/>
	       <input name="ahdm" style="display:none;" type="text" />
	       <a id="research" onClick="openAhSearch()" iconCls="icon-search"></a>
       </td>
       <td width="15%">&nbsp;</td>
       <td width="35%">&nbsp;</td>
     </tr>
     <tr>
       <td>承办人：</td>
       <td>
       	   <input class="easyui-validatebox" required="true" readOnly="readOnly" name="sjrXm" type="text"/>
           <input style="display:none;" name="sjr" type="text"/>
       </td>
       <td>&nbsp;&nbsp;承办部门：</td>
       <td>
       	   <input class="easyui-validatebox" required="true" readOnly="readOnly" name="sjrbmMc" type="text"/>
           <input style="display:none;" name="sjrbm" type="text"/>
       </td>
     </tr>
     <tr>
       <td>当事人：</td>
       <td>
	       <input class="easyui-validatebox" required="true" name="tjr" type="text"/>
	       <input style="display:none;" type="text"/>
	       <a id="dsr_bt" onClick="searchDsr()" iconCls="icon-add"></a>
	   </td>
       <td>&nbsp;&nbsp;当事人联系电话：</td>
       <td><input name="tjrlxdh" type="text"/></td>
     </tr> 
     <tr>
       <td>当事人证件号码：</td>
       <td><input name="djrsfz" type="text"/></td>
       <td>&nbsp;&nbsp;递交日期：</td>
       <td><input class="easyui-datebox" editable="false" id="djrq" name="djrq" type="text"/></td>
     </tr>
     <tr>
       <td>提交时限：</td>
       <td><input name="lqsx" type="text"/></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
     </tr>
    </table>
    <hr/> 
     
   <div>     
     <div>添加材料信息：<a id="cl_add" class="add" iconCls="icon-add"></a></div>
     <div class="cl" id="clmxtr" style="visibility:hidden;margin-top:5px">
       <div>
       		材料名称：<input name="clmc" type="text"/>&nbsp;&nbsp;
       		份数： <input name="clfs"  style="width:30px" type="text"/>&nbsp;&nbsp;
       		<input name="clys" style="width:30px;display:none;" type="text" value="0"/>    
       		<a id="cl_remove" class="remove" style="margin-top:-7px" iconCls="icon-cancel"></a>
       </div>
     </div>
   </div>
   <hr/>
   
   <div align="center">
     <a id="save" onclick="save()" iconCls="icon-save">保存</a>
     <a id="cancel" href="${path}/to_flj.do" iconCls="icon-cancel">取消</a>
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
	
	$(".add").bind("click", function(){ 
		$s=$('#clmxtr').clone(true);
		$s.css("visibility","visible");
		$('#clmxtr').parent().append($s);
	}); 

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
       
   	for(var i=1;i<$('input[name=clmc]').length;i++){
   		var clmc=$('input[name=clmc]').eq(i).val();
   	    if(clmc==''){
   	    	alerString=":材料名称";
   	    	break;
   	    }
   	    
   		var clfs=$('input[name=clfs]').eq(i).val();
   		if(clfs==''){
   	    	alerString=":材料份数";
   	    	break;
   	    }
   		
   		var clys=$('input[name=clys]').eq(i).val();
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
	
	var lqsx = $('input[name=lqsx]').val();
	
	if(tr==0 && alerString==''){
        $.ajax({
	   	    url:'${path}/saveFlj.do',
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
                ,lqsx:encodeURI(encodeURI(lqsx))
   	     	},
   	     	dataType:'json',
   	     	success:function (data) {
   	    		if(data.after==1)
    	       		alert("保存成功");
    	     	if(data.after==0)
    	       		alert("保存失败");    	     
   	    		window.location.href="${path}/to_flj.do";
   	     	}
   	     });
     }else{
    	 alert('请输入必输项'+alerString);
     }
}
</script>
  
<div id="ah_se" style="width:600px;height:300px;padding:5px;">
	<table style="font-size:12px" width="100%" border="0" cellpadding="2" cellspacing="0">
		<tr>
			<td>年份：</td>
			<td><input id="ahN" type="text"/></td>
			<td>当事人：</td>
	   		<td><input id="ahDsr" type="text"/></td>
		</tr>
		<tr>
			<td>关键字：</td>
			<td>
				<input id="ahG" type="text"/>
			</td>
	        <td>
				<a id="search_ah" onclick="searchAh()" iconCls="icon-search"></a>
			</td>
		</tr>
	</table>
	
	<hr/>
	<ul id="ah_searchList"></ul>
</div>

<script>
$('#search_ah').linkbutton({}); 

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
  
function openAhSearch(){
	 $('#ahN').val('');
	 $('#ahG').val('');
	 $('#ahDsr').val('');
	 $('#ah_se').dialog('open');
}
  
function searchAh(){
	var ahN=$('#ahN').val();
	var ahG=$('#ahG').val();
	if(ahN!=''&&ahG!=''){
	$.ajax({
  	     url:'${path}/ahSearch.do',
  	     type:'POST',
  	     data:{
  	    	 ahN: encodeURI(encodeURI(ahN)),
  	    	 ahG: encodeURI(encodeURI(ahG)),
  	    	 ahDsr: encodeURI(encodeURI(ahDsr)),
  	    	 lx:1
  	     },
  	     dataType:'json',
  	     success:function (data) {
  	       $('#ah_searchList').tree('loadData', data.data);
  	     }});
	}
}
</script>
   
<div id="dsr_se" style="width:400px;height:300px;">
    	<table id="dsrgrid" ></table>
</div>

<script>
$('#dsr_se').dialog({
    title:'当事人列表',
    iconCls:'icon-search',
    closed:true,
    buttons:[{
		text:'确定',
		handler:function(){
			var selRow = $('#dsrgrid').datagrid('getSelected');
			if(selRow!=null){
				$('input[name=tjr]').val(selRow.mc);
				$('input[name=tjrlxdh]').val(selRow.lxdh);
				$('input[name=djrsfz]').val(selRow.sfzhm);
			}
			$('#dsr_se').dialog('close');
		}
	},{
		text:'取消',
		handler:function(){
			$('#dsr_se').dialog('close');
		}
	}]
});
 
$('#dsrgrid').datagrid({
	rownumbers:false,
	striped:true,
	fitColumns:true,
	idField:'ah',
	border:true,
	singleSelect:true,
	url:"dsrSearchList.do",
	queryParams : {
		ah : $('input[name=ah]').val()
	},
	columns:[[
		{field:'mc',title:'当事人',width:120,align:'center'},
		{field:'lx',title:'当事人类型',width:120,align:'center'},
		{field:'lxdh',title:'联系电话',width:120,align:'center'},
		{field:'sfzhm',title:'证件(机构)号码',width:120,align:'center'}
	]]
}); 
 
function searchDsr(){
	var ah=$('input[name=ah]').val();
	if(ah!=''){
		$('#dsrgrid').datagrid('reload');
		$('#dsrgrid').datagrid('clearSelections');
		$('#dsr_se').dialog('open');
	}else{
		alert("请先输入案号");
	}
}
</script> 

</body>
</html>