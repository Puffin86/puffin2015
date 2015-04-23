<%@ page language="java" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*,com.bsoft.sszx.dao.*"%>
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
int bh=new ZjqdDao().getMaxId(fydm);
%>
<body style="font-size:12px;">
    <div id="cbr_dg" style="width:250px;height:150px;">
     <input id="userSearch" style="margin-left:5px;margin-top:5px" type="text">
       <a id="search_bt" onclick="searchUser()" iconCls="icon-search"></a>
       <ul id="searchList"></ul>
    </div>    

    <table width="100%" style="font-size:12px;margin-left:10px;" id="ssclzjqd">
     <tr>
       <td width="20%">案号：</td>
       <td>
	       <input class="easyui-validatebox" required="true" name="ah" type="text"></input>
	       <input name="ahdm" style="display:none;" type="text"></input>
	       <a id="research" onClick="$('#ah_se').dialog('open');" style="margin-top:-5px" iconCls="icon-search"></a>
       </td>
     </tr>
     <tr>
       <td width="20%" >承办人：</td>
       <td>
       		<input class="easyui-validatebox" required="true" readOnly="readOnly" name="sjrXm" type="text"></input>
           	<input style="display:none;" name="sjr" type="text"></input>
       		<a id="cbr_search" style="margin-top:-5px"  iconCls="icon-add" onClick="openSearch()"></a>
       	</td>
       <td width="20%" >&nbsp;&nbsp;承办部门：</td>
       <td><input class="easyui-validatebox" required="true"  readOnly="readOnly" name="sjrbmMc" type="text"></input>
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
       <a id="cl_remove" class="remove" style="margin-top:-7px" iconCls="icon-cancel"></a></div>
     </div>
   </div>
   
   <hr/>
   <div align="center">
     <a id="save" onclick="save()" iconCls="icon-save">保存</a>
     <!-- <a id="scan" onclick="scan(<%=bh%>)" iconCls="icon-scan">扫描</a> -->
     <a id="cancel" onclick="window.location.href='${path}/to_jsdsrzdsj.do';" iconCls="icon-cancel">取消</a>
   </div>
   <!-- <a onclick="test()">test</a> -->
   <script>
   $('#research').linkbutton({});
   $('#cl_remove').linkbutton({});
   $('#cl_add').linkbutton({});
   $('#save').linkbutton({});
   $('#scan').linkbutton({});
   $('#cancel').linkbutton({});
   $('#cbr_search').linkbutton({});
   $('#search_bt').linkbutton({});   
   $('#dsr_bt').linkbutton({});
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
   $(".remove").bind("click",function(){ 
	   //取得table的第一行 
	   if($(".remove").length>1){
	   var td = $(this).parent();
	   td.empty();//清空父元素
	   td.remove();}
	   }); 
   });   
   </script>
   
   <script>
   
   function scan(bh){
			 url='${path}/fj.do?bh='+bh;
			 window.open(url,"new",
					 "height=600px,width=650px,toolbar=no,status=no,menubar=no,scrollbars=yes,resizable=yes");
   }
   
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
	   	     url:'saveDsrZjqd.do',
	   	     type:'POST',
	   	     data:{ah:encodeURI(encodeURI(ah))
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
	   	     },//注意大小写data
	   	     dataType:'json',
	   	     success:function (data) {
	   	    	 if(data.after==1){
	   	    		$.messager.confirm('确认',
	   	    				'是否需要添加附件?',
	   	    				function(r){
	   	    					if (r){
	   	    						$('#save').hide();
	   	    						$('#cancel').linkbutton({   
	   	    							text: '返回'  
	   	    						});  
	   	    						scan(<%=bh%>);
	   	    					}else{
	   	    						window.location.href="${path}/to_jsdsrzdsj.do";
	   	    					}
	   	    				}
	   	    		);
	   	    	 }else{
	   	    		 alert("保存失败");  
	   	    	 }
	   	    	
	   	     }});
       }else 
    	   alert('请输入必输项'+alerString);}
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
				 $('#cbr_dg').dialog('close');
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
   
   function openSearch(){
	   $('#userSearch').val('');
		$('#cbr_dg').dialog('open');
		$('#userSearch').focus();
   }
   </script>
   <div id="ah_se" style="width:400px;height:300px;">
	   <table style="font-size:12px">
	   	<tr>
	   		<td>年份：</td>
	   		<td><input id="ahN" style="margin-left:5px;margin-top:5px" type="text"></td>
	   	</tr>
	    <tr>
	   		<td>当事人：</td>
	   		<td><input id="ahdsr" style="margin-left:5px;margin-top:5px" type="text"></td>
	   	</tr>
	    <tr>
	    	<td>关键字：</td>
	    	<td >
	    		<input id="ahG" style="margin-left:5px;margin-top:5px" type="text"/>
	       		<a id="search_ah" onclick="searchAh()" >查询</a>
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
		if(ahN!=''&&ahG!=''){
			$.ajax({
		  	     url:'ahSearch.do',
		  	     type:'POST',
		  	     data:{
		  	     	ahN:encodeURI(encodeURI(ahN)),
		  	    	ahG:encodeURI(encodeURI(ahG)),
		  	    	ahDsr:encodeURI(encodeURI(ahdsr)),
		  	    	lx:0},//注意大小写data
		  	     dataType:'json',
		  	     success:function (data) {
		  	       $('#ah_searchList').tree('loadData',data.data);
		  	     }
		  	 });
		}
	}
   
   </script>
   
   <div id="dsr_se" style="width:400px;height:300px;">
    <div style="margin-left:5px;">载入当事人列表：<a id="search_dsr" onclick="searchDsr()" iconCls="icon-search"></a></div>
    <hr/><ul id="dsr_searchList"></ul>
   </div>
   <script>
   $('#search_dsr').linkbutton({}); 
   
   $('#dsr_se').dialog({
	    title:'添加当事人',
	    iconCls:'icon-search',
	    closed:true});
  
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
		if(ah!=''){
		$.ajax({
	  	     url:'dsrSearch.do',
	  	     type:'POST',
	  	     data:{ah:encodeURI(encodeURI(ah))},//注意大小写data
	  	     dataType:'json',
	  	     success:function (data) {
	  	       $('#dsr_searchList').tree('loadData',data.data);
	  	     }});
		}else alert("请先输入案号");
	}
   
   </script>
</body>
</html>
