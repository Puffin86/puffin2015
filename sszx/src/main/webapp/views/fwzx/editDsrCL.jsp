<%@ page language="java" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*,com.bsoft.sszx.entity.clb.*,com.bsoft.sszx.entity.zjqd.*" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>接收材料</title>
<jsp:include page="/common/include.jsp" />
</head>

<%
List<Clb> list=(List<Clb>)session.getAttribute("editDsrZzSjZjqdClb");
Zjqd zjqd = (Zjqd)session.getAttribute("editDsrZzSjZjqd");
ZjqdId zjqdId = zjqd.getId();

%>
  
<body style="font-size:12px;">
    <div id="cbr_dg" style="width:250px;height:150px;">
     <input id="userSearch" style="margin-left:5px;margin-top:5px" type="text">
       <a id="search_bt" onclick="searchUser()" iconCls="icon-search"></a>
       <ul id="searchList"></ul>
    </div>
    
    <table width="750px" style="font-size:12px;margin-left:10px;" id="ssclzjqd">
     <tr>
       <td width="20%">案号：</td>
       <td><input class="easyui-validatebox" required="true" name="ah" type="text"></input>
       <input name="ahdm" style="display:none;" type="text"></input>
       <a id="research" onClick="$('#ah_se').dialog('open');" style="margin-top:-5px" iconCls="icon-search"></a>
       </td>
     </tr>
     <tr>
       <td width="20%" >承办人：</td>
       <td><input class="easyui-validatebox" required="true" readOnly="readOnly" name="sjrXm" type="text"></input>
           <input style="display:none;" name="sjr" type="text"></input>
       <a id="cbr_search" style="margin-top:-5px" 
          iconCls="icon-add" onClick="$('#cbr_dg').dialog('open');"></a></td>
       <td width="20%" >&nbsp;&nbsp;承办部门：</td>
       <td><input class="easyui-validatebox" required="true" readOnly="readOnly" name="sjrbmMc" type="text"></input>
           <input style="display:none;" name="sjrbm" type="text"></input>
       </td>
     </tr>
     <tr>
       <td width="20%" >当事人：</td>
       <td><input class="easyui-validatebox" required="true" name="tjr" type="text"></input>
       <input style="display:none;" type="text"></input>
       <a id="dsr_bt" onClick="searchDsr()" style="margin-top:-5px" iconCls="icon-add"></a></td>
       <td width="20%">&nbsp;&nbsp;当事人手机号码：</td>
       <td><input name="tjrlxdh" type="text"></input></td>
     </tr> 
     <tr>
       <td width="140px" id="changeText">当事人证件号码：</td>
       <td width="230px"><input name="djrsfz" type="text"></input></td>
       <td width="140px">代理人：</td>
       <td><input name="dlr" type="text"></input></td>
     </tr>
     <tr>
       <td width="140px">代理人联系电话：</td>
       <td width="230px"><input name="dlrdh" type="text"></input></td>
       <td width="140px">执业证号：</td>
       <td><input name="zyzh" type="text"></input></td>
     </tr>
     
     <tr>
       <td width="140px">&nbsp;&nbsp;递交日期：</td>
       <td><input editable="false" class="easyui-datebox" id="djrq" name="djrq" type="text"></input></td>
       <td  colspan="2">&nbsp;</td>
     </tr>
     </table>
     <hr/> 
     
     <div style="margin-left:10px;">     
     <div>添加材料信息：<a id="cl_add" class="add" iconCls="icon-add"></a></div>
     <div class="cl" id="clmxtr" style="visibility:hidden;margin-top:5px">
       <div>材料名称：<input name="clmc" type="text"></input>
       &nbsp;&nbsp;份数： <input name="clfs"  style="width:30px" type="text"></input>
       &nbsp;&nbsp;页数： <input name="clys" style="width:30px" type="text"></input>      
       <a id="cl_remove" class="remove" style="margin-top:-7px" iconCls="icon-cancel"></a></div>
     </div>
   </div>
   
   <hr/>
   <div align="center">
     <a id="save" onclick="save()" iconCls="icon-save">保存</a>
     <a id="cancel" onclick="cancel()" iconCls="icon-cancel">取消</a>
   </div>
   <script>
   $('#research').linkbutton({});
   $('#cl_remove').linkbutton({});
   $('#cl_add').linkbutton({});
   $('#save').linkbutton({});
   $('#cancel').linkbutton({});
   $('#cbr_search').linkbutton({});
   $('#search_bt').linkbutton({});   
   $('#dsr_bt').linkbutton({});

   //文档加载完成后要执行的内容 
   $(document).ready(function(){ 
	   var ah = "<%=zjqd.getAh()%>";
	   $('input[name=ah]').attr('value',ah);
	   
	   var ahdm = "<%=zjqd.getAhdm()%>";
	   $('input[name=ahdm]').attr('value',ahdm);
	   
	   //var sjr="${session.editDsrZzSjZjqd.sjr}";
	   var sjr="<%=zjqd.getSjr()%>";
	   $('input[name=sjr]').attr('value',sjr);
	   
	   var sjrbm = "<%=zjqd.getSjrbm()%>";
	   $('input[name=sjrbm]').attr('value',sjrbm);
	   
	   //var tjr="${session.editDsrZzSjZjqd.djr}";
	   var tjr="<%=zjqd.getDjr()%>";
	   $('input[name=tjr]').attr('value',tjr);
	   
	   //var tjrlxdh="${session.editDsrZzSjZjqd.djrlxdh}";
	   var tjrlxdh="<%=zjqd.getDjrlxdh()%>";
	   $('input[name=tjrlxdh]').attr('value',tjrlxdh);
	   
	   //var djrsfz="${session.editDsrZzSjZjqd.dsrsfzhm}";
	   var djrsfz="<%=zjqd.getDsrsfzhm()%>";
	   $('input[name=djrsfz]').attr('value',djrsfz);
	   
	   //var djrq="${session.editDsrZzSjZjqd.djrq}";
	   var djrq="<%=zjqd.getDjrq()%>";
	   $("#djrq").datebox("setValue", djrq); 
	   
	   //var sjrXm="${session.editDsrZzSjZjqd.sjrXm}";
	   var sjrXm="<%=zjqd.getSjrXm()%>";
	   $('input[name=sjrXm]').attr('value',sjrXm);
	   
	   //var sjrbmMc="${session.editDsrZzSjZjqd.sjrBmmc}";
	   var sjrbmMc="<%=zjqd.getSjrBmmc()%>";
	   $('input[name=sjrbmMc]').attr('value',sjrbmMc);
	   
	   var dlr="<%=zjqd.getDlr()%>";
	   $('input[name=dlr]').attr('value',dlr);
	   
	   var dlrdh="<%=zjqd.getDlrdh()%>";
	   $('input[name=dlrdh]').attr('value',dlrdh);
	   
	   var zyzh="<%=zjqd.getZyzh()%>";
	   $('input[name=zyzh]').attr('value',zyzh);
	
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
   </script>
   
   <script>
   function save(){
	   //var bh="${session.editDsrZzSjZjqd.id.bh}";
	   var bh = "<%=zjqdId.getBh()%>";
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
       
       var dlr=$('input[name=dlr]').val();
       var dlrdh=$('input[name=dlrdh]').val();
       var zyzh=$('input[name=zyzh]').val();
      
       var cl='';
       var alerString='';
       if($('input[name=clmc]').length==1){
    	   alerString="请至少录入一条材料记录！";
    	   return;
       }
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
   	     url:'editDsrZjqdQd.do',
   	     type:'POST',
   	     data:{  bh:bh
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
                 ,dlr:encodeURI(encodeURI(dlr))
	             ,dlrdh:encodeURI(encodeURI(dlrdh))
	             ,zyzh:encodeURI(encodeURI(zyzh))
   	     },//注意大小写data
   	     dataType:'json',
   	     success:function (data) {
   	    	 if(data.after==1)
      	       alert("保存成功");
      	     if(data.after==0)
      	       alert("保存失败");    	     
     	     window.location.href="${path}/to_jsdsrzdsj.do";
   	     }})
   	  ;}else alert('请输入必输项'+alerString);}
   
   function cancel(){
	   window.location.href="${path}/to_jsdsrzdsj.do";
   }
   </script>
   
   <script>
   $('#cbr_dg').dialog({
	    title:'添加承办人',
	    iconCls:'icon-search',
	    closed:true});
   
   $('#searchList').tree({  
	    checkbox: false,
	    onClick: function(node){		 
			if(node.attributes.leaf=="true"){
				 var name=node.text.split("：");
				 var bm=name[1];				 
				 $('input[name=sjrbmMc]').val(bm);
				 var xm=name[0];				 
				 $('input[name=sjrXm]').val(xm);
				 $('input[name=sjrbm]').val(node.attributes.yhbm);
				 $('input[name=sjr]').val(node.attributes.yhid);
			 }
	    }
	});  
   
   function searchUser(){
		var userName=$('#userSearch').val();
		if(userName!=''){
		$.ajax({
	  	     url:'userSearch.do',
	  	     type:'POST',
	  	     data:{name:encodeURI(encodeURI(userName))},//注意大小写data
	  	     dataType:'json',
	  	     success:function (data) {
	  	       $('#searchList').tree('loadData',data.data);
	  	     }});
		}
	}
   </script>
   <div id="ah_se" style="width:400px;height:300px;">
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
		   		<td><input id="ahN" style="width:100px;margin-left:5px;margin-top:5px" type="text"></td>
		   		<td >案号：</td>
		    	<td >
		    		<input id="ahG" style="width:100px;margin-left:5px;margin-top:5px" type="text"/>
		       	</td>
		   	</tr>
		    <tr>
		   		<td>当事人：</td>
		   		<td><input id="ahdsr" style="width:100px;margin-left:5px;margin-top:5px" type="text"></td>
		   		<td>&nbsp;</td>
		   		<td>
		       		<a id="search_ah" onclick="searchAh()" iconCls="icon-search">查询</a>
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
	    closed:true});
  
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
		var ahdsr=$('#ahdsr').val();
		var ajly = $("input[name='ajly']:checked").val(); 
		//if(ahN!=''&&ahG!=''){
			$.ajax({
		  	     url:'ahSearch.do',
		  	     type:'POST',
		  	     data:{
		  	     	ahN:encodeURI(encodeURI(ahN)),
		  	    	ahG:encodeURI(encodeURI(ahG)),
		  	    	ahDsr:encodeURI(encodeURI(ahdsr)),
		  	    	ajly : ajly,
		  	    	lx:0},//注意大小写data
		  	     dataType:'json',
		  	     success:function (data) {
		  	       $('#ah_searchList').tree('loadData',data.data);
		  	     }
		  	 });
		//}
	}
   
   </script>
   
   
   <div id="dsr_se" style="width:400px;height:300px;">
    	<table id="dsrgrid" ></table>
   </div>
   <script>
   

   
   //$('#search_dsr').linkbutton({}); 
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
   
   $('#dsr_se').dialog({
	    title:'当事人列表',
	    iconCls:'icon-search',
	    closed:true,
	    buttons:[{
			text:'确定',
			handler:function(){
				var selRow = $('#dsrgrid').datagrid('getSelected');
				if(selRow!=null){
					if(selRow.lx=="09_01001-1"){//自然人
						$('#changeText').html('当事人证件号码：');
					}else{//非自然人
						$('#changeText').html('当事人组织机构代码：');
					}
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
	    var ahdm=$('input[name=ahdm]').val();
		if(ahdm!=''){
			//$('#dsrgrid').datagrid('reload');
			$('#dsrgrid').datagrid('load',{
				ah : $('input[name=ahdm]').val(),
					cbbm : $('input[name=sjrbm]').val()
		    });
			$('#dsrgrid').datagrid('clearSelections');
			$('#dsr_se').dialog('open');
		}else{
			alert("请先输入案号");
		} 
	}
   
   </script>
</body>
</html>