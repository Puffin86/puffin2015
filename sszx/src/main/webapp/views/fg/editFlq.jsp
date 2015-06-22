<%@ page import="com.bsoft.sszx.entity.clb.Clb"%>
<%@ page import="java.util.List"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>修改</title>
<jsp:include page="/common/include.jsp" />
</head>

<%
	List<Clb> list=(List<Clb>)session.getAttribute("editFlqClb");
%>
  
<body style="font-size:12px;background:#fff;">
    <table width="100%" border="0" cellpadding="2" cellspacing="0" style="font-size:12px;" id="ssclzjqd">
	      <tr>
	       <td width="15%">案号：</td>
	       <td colspan="3">
	       		<input class="easyui-validatebox" required="true" name="ah" type="text"/>
	       		<input name="ahdm" style="display:none;" type="text"/>
	       		<a id="research" class="easyui-linkbutton" onClick="openAhSearch()" style="margin-top:-5px" iconCls="icon-search"></a>
	       </td>
	     </tr>
	     <tr>
	       <td width="15%">承办人：</td>
	       <td width="35%">
	       	   <input class="easyui-validatebox" required="true" readOnly="readOnly" name="sjrXm" type="text"/>
	           <input style="display:none;" name="sjr" type="text"/>
	       </td>
	       <td width="15%">&nbsp;&nbsp;承办部门：</td>
	       <td width="35%">
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
	       <td>领取时限：</td>
	       <td><input name="lqsx" type="text"/></td>
	       <td>&nbsp;</td>
	       <td>&nbsp;</td>
	     </tr>
     </table>
     
     <hr/> 
     
   <div style="margin-left:10px;">     
     <div>添加材料信息：<a id="cl_add" class="add" iconCls="icon-add"></a></div>
     <div class="cl" id="clmxtr" style="visibility:hidden;margin-top:5px">
       <div>材料名称：<input name="clmc" type="text"/>
	       &nbsp;&nbsp;份数： <input name="clfs"  style="width:30px" type="text"/>
	       &nbsp;&nbsp;页数： <input name="clys" style="width:30px" type="text"/>      
	       <a id="cl_remove" class="easyui-linkbutton" class="remove" style="margin-top:-7px" iconCls="icon-remove"></a>
       </div>
     </div>
   </div>
   
   <hr/>
   <div align="center">
     <a id="save" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">保存</a>
     <a id="cancel" class="easyui-linkbutton" href="${path}/to_flq.do" iconCls="icon-cancel">取消</a>
   </div>

<script>
$(document).ready(function(){ 
	$('#research').linkbutton({});
	$('#cl_remove').linkbutton({});
	$('#cl_add').linkbutton({});
	$('#save').linkbutton({});
	$('#cancel').linkbutton({});
	$('#dsr_bt').linkbutton({});
	   
	var ah="${editFlq.ah}";
	$('input[name=ah]').attr('value',ah);	   
	var sjr="${editFlq.sjr}";
	$('input[name=sjr]').attr('value',sjr);
	var tjr="${editFlq.djr}";
	$('input[name=tjr]').attr('value',tjr);
	var tjrlxdh="${editFlq.djrlxdh}";
	$('input[name=tjrlxdh]').attr('value',tjrlxdh);
	var djrsfz="${editFlq.dsrsfzhm}";
	$('input[name=djrsfz]').attr('value',djrsfz);
	var djrq="${editFlq.djrq}";
	$("#djrq").datebox("setValue", djrq); 
	var sjrXm="${editFlq.sjrXm}";
	$('input[name=sjrXm]').attr('value',sjrXm);
	var sjrbmMc="${editFlq.sjrBmmc}";
	$('input[name=sjrbmMc]').attr('value',sjrbmMc);
	var sx="${editFlq.sx}";
	$('input[name=lqsx]').attr('value',sx);
	
	//绑定添加行按钮的单击事件 
	$(".add").bind("click",function(){ 
		$s=$('#clmxtr').clone(true);
		$s.css("visibility","visible");
		$('#clmxtr').parent().append($s);
	}); 

	//绑定删除行按钮的单击事件 
	$(".remove").bind("click",function(){ 
		//取得table的第一行 
		if($(".remove").length>1){
			var td = $(this).parent();
			td.empty();//清空父元素
			td.remove();
		}
	}); 
});   
   
<%
for(int i=0;i<list.size();i++) {
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
   var bh="${editFlq.id.bh}";
   var ah=$('input[name=ah]').val();
   var sjr=$('input[name=sjr]').val();
   var tjr=$('input[name=tjr]').val();
   var djrq=$('input[name=djrq]').val();
   var tjrlxdh=$('input[name=tjrlxdh]').val();
   var zjr=$('input[name=zjr]').val();
   var zjrq=$('input[name=zjrq]').val();
   var djrsfzhm=$('input[name=djrsfz]').val();
     
   var re=$('input.easyui-validatebox').length;
   var cl='';
   var alerString='';
   
   for(var i=1;i<$('input[name=clmc]').length;i++){//input要使用name属性辨别
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
  		if(clys==''){
  	    	alerString=":材料页数";
  	    	break;
  	    }
  		
  	    var clfull=clmc+','+clfs+','+clys+';';	
  		cl+=clfull;	   
  	}
  	
  	var sjrbm=$('input[name=sjrbm]').val();
	var sjrXm=$('input[name=sjrXm]').val();
	var sjrbmMc=$('input[name=sjrbmMc]').val();
	var lqsx=$('input[name=lqsx]').val();
  	
	var tr=0;
	for(var i=0;i<re;i++){
		var content=$('input.easyui-validatebox').eq(i).val();
		if(content=='')
			tr+=1;
	}
	
	if(tr==0 && alerString==''){
		$.ajax({
			url:'${path}/editDsrZjqdQd.do',
			type:'POST',
			data:{  
				bh:bh
				,ah:encodeURI(encodeURI(ah))
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
			success: function(data){
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