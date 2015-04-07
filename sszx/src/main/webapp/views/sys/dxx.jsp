<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>短信维护</title>
<jsp:include page="/common/include.jsp" />
</head>

<body style="background-color: #fff;" class="easyui-layout">    
	<div id="p" class="easyui-panel" data-options="region:'center'" title="短信息设置" style="width:100%;height:100%;padding:7px;">
		<ul id="dxxList"></ul>
	</div>
<script>
$('#dxxList').tree({ 
	url: '${path}/dxxList.do',
	loadFilter: function(data){
		if (data.data){
			return data.data;
		} else {
			return data;
		}
    },
    checkbox: false,
    onClick: function(node){		 
		if(node.attributes.leaf=="true"){
			 var fydm="${fydm}";
			 url='${path}/dxxSz.do?id='+node.attributes.id+'&fydm='+fydm;
			 window.open(url,"new", "height=400px,width=500px,toolbar=no,status=no,menubar=no,scrollbars=no,resizable=no");
		 }
    }
});  
</script>

</body>
</html>