<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.bsoft.sszx.entity.clb.Clb,java.util.List"%>
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
  
<body style="background:#fff;">
    <table width="100%" border="0" cellpadding="2" cellspacing="0" style="font-size:12px;" id="ssclzjqd">
	      <tr>
	       <td width="15%">案号：</td>
	       <td width="35%">
	       		<input class="easyui-validatebox" required="true" readOnly="readOnly" name="ah" type="text"/>
	       		<input name="ahdm" style="display:none;" type="text"/>
	       		<a id="research" class="easyui-linkbutton" onClick="openAhSearch()" style="margin-top:-5px" iconCls="icon-search"></a>
	       </td>
	       <td width="15%">&nbsp;</td>
       	   <td width="35%">&nbsp;</td>
	     </tr>
	     <tr>
	       <td >承办人：</td>
	       <td >
	       	   <input class="easyui-validatebox" required="true" readOnly="readOnly" name="sjrXm" type="text"/>
	           <input style="display:none;" name="sjr" type="text"/>
	       </td>
	       <td >&nbsp;&nbsp;承办部门：</td>
	       <td >
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
	       <td>&nbsp;&nbsp;当事人手机号码：</td>
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
	       <td><input name="lqsx" type="text"/>天内</td>
	       <td>&nbsp;</td>
	       <td>&nbsp;</td>
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
       		材料名称：<input name="clmc" type="text"></input>&nbsp;&nbsp;
       		份数： <input name="clfs"  style="width:30px" type="text"></input>&nbsp;&nbsp;
       		页数： <input name="clys" style="width:30px" type="text"></input>      
       		<a id="cl_remove" href="#" class="easyui-linkbutton remove" data-options="iconCls:'icon-cancel'"></a>
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
	var ahdm="${editFlq.ahdm}";
	$('input[name=ahdm]').attr('value',ahdm);	
	var sjr="${editFlq.sjr}";
	$('input[name=sjr]').attr('value',sjr);
	var sjrbm = "${editFlq.sjrbm}";
	$('input[name=sjrbm]').attr('value',sjrbm);
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
   //$('#clmxtr').css("visibility","hidden");
   //$('#clmxtr>td:first-child>input').val("");//IDclmxtr元素的最后儿子元素的input表情值为空
   }); 

   //绑定删除行按钮的单击事件 
   $(".remove").bind("click",function(){ 
   //取得table的第一行 
   if($(".remove").length>1){
   var td = $(this).parent();
   td.empty();//清空父元素
   td.remove();}
   }); 
   });   
   
   <%
   for(int i=0;i<list.size();i++){
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


function save(){
   var bh="${editFlq.id.bh}";
   var ah=$('input[name=ah]').val();
   var ahdm=$('input[name=ahdm]').val();
   var sjr=$('input[name=sjr]').val();
   var tjr=$('input[name=tjr]').val();
   var djrq=$('input[name=djrq]').val();
   var tjrlxdh=$('input[name=tjrlxdh]').val();
   var phone = /^1([38]\d|4[57]|5[0-35-9]|7[06-8]|8[89])\d{8}$/;
   if(!phone.test(tjrlxdh)){
       alert("当事人手机号码输入有误，请检查！");
       return;
   }
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
				,ahdm:encodeURI(encodeURI(ahdm))
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
	   		<td>案件类型：</td>
	   		<td colspan="3">
	   			<input name="ajly"  type="radio" checked="checked" value="sp">审判</input>
	   			<input name="ajly"  type="radio" value="zx">执行</input>
	   		</td>
	   	</tr>
		<tr>
			<td>年份：</td>
			<td><input id="ahN" type="text" style="width:100px;margin-left:5px;margin-top:5px"/></td>
	   		<td>案号：</td>
			<td>
				<input id="ahG" type="text" style="width:100px;margin-left:5px;margin-top:5px"/>
			</td>
		</tr>
		<tr>
			<td>当事人：</td>
	   		<td><input id="ahDsr" type="text" style="width:100px;margin-left:5px;margin-top:5px"/></td>
	        <td>
				<a id="search_ah" onclick="searchAh()" iconCls="icon-search"></a>
			</td>
		</tr>
	</table>
	
	<hr/>
	<ul id="ah_searchList"></ul>
	<div id="errorSearch">无数据...</div>
</div>

<script>
$('#search_ah').linkbutton({}); 
$('#errorSearch').hide();

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
	 $('#errorSearch').hide();
	 $('#ah_searchList').hide();
	 $('#ah_se').dialog('open');
}
 
function searchAh(){
	var ahN=$('#ahN').val();
	var ahG=$('#ahG').val();
	var ahDsr=$('#ahDsr').val();
	var ajly = $("input[name='ajly']:checked").val(); 
	//if(ahN!=''&&ahG!=''){
		$.ajax({
	  	     url:'${path}/ahSearch.do',
	  	     type:'POST',
	  	     data:{
	  	    	ahN:encodeURI(encodeURI(ahN)),
	  	    	ahG:encodeURI(encodeURI(ahG)),
	  	    	ahDsr: encodeURI(encodeURI(ahDsr)),
	  	    	ajly : ajly,
	  	    	lx:1
	  	     },
	  	     dataType:'json',
	  	     success:function (data) {
	  	         if(data.data.length>0){
	  	         	$('#ah_searchList').show();
	  	    		$('#errorSearch').hide();
		  	        $('#ah_searchList').tree('loadData', data.data);
	  	    	 }else{
	  	    		$('#ah_searchList').hide();
	  	    		 $('#errorSearch').show();
	  	    	 }
	  	     }
  	    });
	//}
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
		ah : $('input[name=ahdm]').val(),
		cbbm : $('input[name=sjrbm]').val()
	},
	columns:[[
		{field:'mc',title:'当事人',width:120,align:'center'},
		{field:'lx',title:'当事人类型',width:120,align:'center',formatter:function(value,row,index){
			if(value=="09_01001-2"){
				return "法人";
			}else if(value=="09_01001-1"){
				return "自然人";
			}else if(value=="09_01001-3"){
				return "非法人组织";
			}else if(value=="申请执行人"){
				return "申请执行人";
			}else if(value=="被执行人"){
				return "被执行人";
			}else if(value=="09_01001-4"){
				return "单位犯罪直接责任人";
			}else{
				return "其他";
			}
		 }},
		{field:'lxdh',title:'联系电话',width:120,align:'center'},
		{field:'sfzhm',title:'证件(机构)号码',width:120,align:'center'}
	]]
}); 
 
function searchDsr(){
		$('#dsrgrid').datagrid('load',{
			ah : $('input[name=ahdm]').val(),
			cbbm : $('input[name=sjrbm]').val()
		});
		$('#dsrgrid').datagrid('clearSelections');
		$('#dsr_se').dialog('open');
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


</body>
</html>