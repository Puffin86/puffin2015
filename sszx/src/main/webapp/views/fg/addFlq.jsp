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
  
<body style="background:#fff;">

    <table width="100%" id="ssclzjqd" border="0" cellpadding="2" cellspacing="0" style="font-size:12px;">
     <tr>
       <td width="15%">案号：</td>
       <td width="35%">
	       	<input class="easyui-validatebox" required="true" readOnly="readOnly" name="ah" type="text"/>
	       	<input name="ahdm" type="hidden" />
	       	<a id="research" class="easyui-linkbutton" onClick="openAhSearch()" iconCls="icon-search"></a>
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
       <td>承办部门：</td>
       <td>
       		<input class="easyui-validatebox" required="true" readOnly="readOnly" name="sjrbmMc" type="text"/>
            <input style="display:none;" name="sjrbm" type="text"/>
       </td>
     </tr>
      <tr>
       <td>领取时限：</td>
       <td><input name="lqsx" type="text"/></td>
       
       <td>递交日期：</td>
       <td><input editable="false" class="easyui-datebox" id="djrq" name="djrq" type="text"/></td>
     </tr>
      <tr>
       <td>当事人：<a id="dsr_bt" onClick="searchDsrList()" iconCls="icon-search"></a></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
     </tr> 
     <tr>
     	<td colspan="4">
     		<table id="dsrgridList" ></table>
     	</td>
     </tr>
     
     </table>
     <hr/> 
     
     <div>   
     <table width="100%" border="0" cellpadding="2" cellspacing="0" style="font-size:12px;">
     	<tr>
     		<td width="20%">添加材料信息：<a id="cl_add" class="add" iconCls="icon-add"></a></td>
     		<td width="20%">常用材料清单：<a onclick="choseClqd()" class="easyui-linkbutton">选择</a></td>
     		<td width="60%">&nbsp;</td>
     	</tr>
     </table>  
     
     <div class="cl" id="clmxtr" style="visibility:hidden;margin-top:5px">
       <div>
	       	材料名称：<input name="clmc" type="text"/>&nbsp;&nbsp;
	       	份数： <input name="clfs"  style="width:30px" type="text"/>&nbsp;&nbsp;
	       	页数： <input name="clys" style="width:30px" type="text"/>&nbsp;&nbsp;      
	       	<a id="cl_remove" class="remove" style="margin-top:-7px" iconCls="icon-cancel"></a>
       </div>
     </div>
   </div>
   
   <hr/>
   <div align="center">
     <a onclick="sdhzclqd()" class="easyui-linkbutton">送达回证材料清单</a>
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
    var djrq=$('input[name=djrq]').val();
    var zjr=$('input[name=zjr]').val();
    var zjrq=$('input[name=zjrq]').val();
    
    
       
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
   	
   	if(cl == ''){
   		alert('请至少录入一条材料记录！');
   		return;
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
	if(lqsx==''){
    	alert("必须输入领取时限！");
    	return;
    }
	
	var tjrlxdh=$('input[name=tjrlxdh]').val();
    var tjr=$('input[name=tjr]').val();
    var djrsfzhm=$('input[name=djrsfz]').val();

    var rows = $('#dsrgridList').datagrid("getChecked"); 
    var tjrStr="";
    for(var i=0; i<rows.length;i++){
    	var row = rows[i];
    	var rowStr = "{dsrmc:'"+row.mc+"',dsrlxdh:'"+row.lxdh+"',dsrsfzhm:'"+row.sfzhm+"'}";
    	tjrStr +="@"+rowStr;
    }
    //return;
    if(rows.length==0){
    	alert("必须选择当事人！");
    	return;
    }
    
    var s='';
	var arr = $('input[name=clmc]');
	for(var i=0; i<arr.length; i++){
		var v = $(arr[i]).val();
		if(v!=null && v!=''){
			s+=v+',';
		}
	}
	
	var clqd = s.substring(0, s.length-1);
    
	if(tr==0 && alerString==''){
        $.ajax({
   	     	url:'${path}/saveFlqList.do',
   	     	type:'POST',
   	     	data:{
   	     		 ah:encodeURI(encodeURI(ah))
                 ,sjr:encodeURI(encodeURI(sjr))
                 ,tjrStr:encodeURI(encodeURI(tjrStr))
                 //,tjr:encodeURI(encodeURI(tjr))
                 ,djrq:encodeURI(encodeURI(djrq))
                 //,tjrlxdh:encodeURI(encodeURI(tjrlxdh))
                 ,zjr:encodeURI(encodeURI(zjr))
                 ,zjrq:encodeURI(encodeURI(zjrq))
                 //,djrsfzhm:encodeURI(encodeURI(djrsfzhm))
                 ,cl:encodeURI(encodeURI(cl))
                 ,sjrbm:encodeURI(encodeURI(sjrbm))
                 ,sjrXm:encodeURI(encodeURI(sjrXm))
                 ,sjrbmMc:encodeURI(encodeURI(sjrbmMc))
                 ,lqsx:encodeURI(encodeURI(lqsx))
                 ,clqd:encodeURI(encodeURI(clqd))
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

<!-- 常用材料清单 -->
<div id="clqd_dialog" class="easyui-dialog" title="常用材料清单" 
	style="width:300px;height:200px;" data-options="modal:true,closed:true">  
    <ul id="clqd_tree" class="easyui-tree" 
    	data-options="checkbox:true,url:'${path}/zdmxcx_tree.do?zdbm=cyclqd'"></ul>  
</div> 
<script>

var clqdObj={};//记录已经添加的材料清单
$('#clqd_dialog').dialog({
	buttons:[{
		text:'确定',
		handler:function(){
			var arr = $('#clqd_tree').tree('getChecked');
			for(var i in arr){
				if(clqdObj[arr[i].id]!=null){
					continue;
				}
				var s = buildClxx(arr[i].text,arr[i].id);
				$('#clmxtr').parent().append(s);
			}
			$('#clqd_dialog').dialog('close');
		}
	},{
		text:'取消',
		handler:function(){
			$('#clqd_dialog').dialog('close');
		}
	}]
});

function choseClqd(){
	$('#clqd_dialog').dialog('open');
}



function buildClxx(clmc,cllx){
	var divclmxtr = $('<div class="cl" id="clmxtr" style="margin-top:5px"></div>');
	var indivStr = $('<div>'
		+ '材料名称：<input name="clmc" type="text" value="' +clmc+ '"/>&nbsp;&nbsp;'
		+ '份数： <input name="clfs"  style="width:30px" type="text"/>&nbsp;&nbsp;'
    	+ '页数： <input name="clys" style="width:30px" type="text"/>&nbsp;&nbsp;'
    	+ '</div>');
	
	$('<a></a>').text('').appendTo(indivStr).linkbutton({iconCls:'icon-cancel'}).attr('cllx',cllx).on('click',function(){
		var lx = $(this).attr('cllx');
		var node = $('#clqd_tree').tree('find',lx);
		$('#clqd_tree').tree('uncheck',node.target);
		var td = $(this).parent();
		td.empty();//清空父元素
		td.remove();
		delete clqdObj[lx];
	});
	clqdObj[cllx] = cllx;
	indivStr.appendTo(divclmxtr);
    return divclmxtr;
}



</script>

<!-- 送达回证材料明细 -->
<div id="sdhzcl_dialog" class="easyui-dialog" title="送达回证材料明细" 
	style="width:300px;height:160px;" data-options="modal:true,closed:true">  
	<textarea id="mxnr" rows="5" cols="30"></textarea>
</div> 

<script>
$('#sdhzcl_dialog').dialog({
	buttons:[{
		text:'确定',
		handler:function(){
			//....
			$('#sdhzcl_dialog').dialog('close');
		}
	},{
		text:'取消',
		handler:function(){
			$('#sdhzcl_dialog').dialog('close');
		}
	}]
});

function sdhzclqd(){
	var s='';
	var arr = $('input[name=clmc]');
	for(var i=0; i<arr.length; i++){
		var v = $(arr[i]).val();
		if(v!=null && v!=''){
			s+=v+',';
		}
	}
	
	$('#mxnr').html(s.substring(0, s.length-1));
	$('#sdhzcl_dialog').dialog('open');
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
	    	<td>案号：</td>
	    	<td>
		    	<input id="ahG" type="text"/>
	        </td>
	        <td>
		        <a id="search_ah" class="easyui-linkbutton" onclick="searchAh()" iconCls="icon-search">查询</a>
	        </td>
	        <td>&nbsp;</td>
	    </tr>
   </table>
   <hr/>
   
   <ul id="ah_searchList"></ul>
</div>
   
<script>
$('#ah_se').dialog({
    title: '添加案号',
    iconCls: 'icon-search',
    closed: true,
    modal: true
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
	var ahDsr=$('#ahDsr').val();
	
	if(ahN != '' && ahG != ''){
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
	  	     }
	  	});
	}
}
</script>
   <!-- 
<div id="dsr_se" style="width:400px;height:300px;">
    	<table id="dsrgrid" ></table>
</div>
 -->
<script>

$('#dsrgridList').datagrid({
	rownumbers:false,
	striped:true,
	fitColumns:true,
	title:"当事人列表",
	idField:'mc',
	border:true,
	url:"dsrSearchList.do",
	queryParams : {
		ah : $('input[name=ah]').val()
	},
	columns:[[
	    {field:'ck',checkbox:true }, 
		{field:'mc',title:'当事人',width:120,align:'center'},
		{field:'lx',title:'当事人类型',width:120,align:'center',hidden:'true'},
		{field:'lxdh',title:'联系电话',width:120,align:'center'},
		{field:'sfzhm',title:'证件(机构)号码',width:120,align:'center'}
	]]
});

function searchDsrList(){
	var ah=$('input[name=ahdm]').val();
	if(ah!=''){
		$('#dsrgridList').datagrid('load',{
			ah : $('input[name=ahdm]').val()
		});
		$('#dsrgridList').datagrid('clearSelections');
	}else{
		alert("请先输入案号");
	} 
}

</script>
   
</body>
</html>