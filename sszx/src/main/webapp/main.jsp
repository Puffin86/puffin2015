<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>main</title>
<jsp:include page="/common/include.jsp" />
<style type="text/css">
.layout-split-west {
    border-right: 5px solid #f9f9f9;
}

#Main_Tabs .panel-body{
	padding-top: 5px;
	padding-left: 5px;
	padding-right: 5px;
}

.icon-userSet{ 
	background:url('resources/style/images/userSet.png') no-repeat; 
}

.icon-systemSet{ 
	background:url('resources/style/images/systemSet.png') no-repeat; 
}

.icon-search_sel{ 
	background:url('resources/style/images/search.png') no-repeat; 
}

.icon-fallback{ 
	background:url('resources/style/images/fallback.png') no-repeat; 
}

.icon-upload{ 
	background:url('resources/style/images/upload.png') no-repeat; 
}

.icon-menu{ 
	background:url('resources/style/images/menu.png') no-repeat; 
}

</style>
</head>

<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:90px;background-color:#f9f9f9;background-image:url('resources/style/images/title.png');">	
		<a href="${path}/logout.jsp" class="easyui-linkbutton" style="position:absolute;top:55px;right:20px;">注销</a>  
	</div>
    
    <div data-options="region:'west',split:true,title:'菜单导航',iconCls:'icon-menu'" style="width:210px;">
    	<div class="easyui-accordion" data-options="fit:true,border:false">
    		<div iconCls="icon-upload" title="材料接收和交递（法官）">
    			<ul name="mtree" class="easyui-tree"
	        		data-options="lines:true,url:'${path}/data/menu_1.json'"></ul>
		    </div>  
		    <div iconCls="icon-upload" title="材料接收和交递（服务中心）">
		    	<ul name="mtree" class="easyui-tree"
	        		data-options="lines:true,url:'${path}/data/menu_2.json'"></ul>
		    </div>  
		    <div iconCls="icon-fallback" title="退回材料处理">
		    	<ul name="mtree" class="easyui-tree"
	        		data-options="lines:true,url:'${path}/data/menu_3.json'"></ul>
		    </div>
		    <div iconCls="icon-search_sel" title="查询">
		    	<ul name="mtree" class="easyui-tree"
	        		data-options="lines:true,url:'${path}/data/menu_4.json'"></ul>
		    </div>
		    <div iconCls="icon-userSet" title="用户设置">
		    	<ul name="mtree" class="easyui-tree"
	        		data-options="lines:true,url:'${path}/data/menu_5.json'"></ul>
		    </div>
		    <div iconCls="icon-systemSet" title="系统设置">
		    	<ul name="mtree" class="easyui-tree"
	        		data-options="lines:true,url:'${path}/data/menu_6.json'"></ul>
		    </div>
        </div>
    </div>
    
	<div data-options="region:'center'" >
    	<div id="Main_Tabs" class="easyui-tabs" 
    		data-options="fit:true,border:false,tools:'#Main_Tabs_Tools'">
            <div title="我的主页" data-options="iconCls:'icon-home'">
            	<iframe frameborder="0" width="100%" height="99%" src="${path}/home.jsp"></iframe>
            </div>
		</div>
        
        <div id="Main_Tabs_Tools" style="border-left:none;border-top:none;border-right:none;">
            <a href="javascript:closeAll()" class="easyui-linkbutton" 
            	data-options="plain:true,iconCls:'icon-close'"></a>
        </div>
    </div>
    
<script type="text/javascript">
$(function(){
	
	$('ul[name="mtree"]').each(function(i, o) {
		$(o).tree({
			onClick: function(node) {
				addTab(node.text, node.attributes.url);
			}
		});
	});
	
});

function addTab(title, url) {
	if ($('#Main_Tabs').tabs('exists', title)){
        $('#Main_Tabs').tabs('select', title);
        return;
    } else {
        var _content = $('<iframe>').attr({
			width : '100%',
			height : '100%',
			frameborder : 0,
			src : '${path}/' + url
		});
        
        $('#Main_Tabs').tabs('add', {
			title : title,
			iconCls : 'icon-tab',
			content : _content,
			closable : true,
			selected : true
		});
    }
}

function closeAll(){
	var tabs = $('#Main_Tabs').tabs('tabs');
	if (tabs.length > 1) {
		for ( var i = tabs.length - 1; i > 0; i--) {
			$('#Main_Tabs').tabs('close', i);
		}
	}
}
</script>

</body>
</html>
