<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
function addAction(){
	$('#Add_Form').form('clear');
	$('#Add_Dialog').dialog('open');     
}

function CancelAdd(){
	$('#Add_Dialog').dialog('close');
}

function Save(){
	$('#Add_Form').form('submit', {
		url : '${path}/clqd/save.do',
		onSubmit : function(){
			return $(this).form('validate');
		},
		success : function(result){
			var resultObj = $.parseJSON(result);
			
			$('#Add_Dialog').dialog('close');
			if(resultObj.success){
				$('#datagrid1').datagrid('reload');  
			}else{
				$.messager.alert('提示', '保存失败', 'error');
			}
		}
	});
}
</script>

<div id="Add_Dialog" title="添加用户" class="easyui-dialog" style="width:360px;padding:15px 30px;"  
	data-options="modal:true,closed:true,
    	onClose:function(){
    		$('.validatebox-tip').remove();
    	},
    	buttons:'#Add_Dialog_Btns'">  
    <form id="Add_Form" method="post">
    	<table width="100%" border="0" cellpadding="0" cellspacing="5">
    		<tr>
    			<td width="30%">材料清单:</td>
    			<td width="70%">
    				<input type="text" name="clmc" required="true" 
    					class="easyui-validatebox from-txt" />
    			</td>
    		</tr>
    	</table>
    </form>
</div>

<div id="Add_Dialog_Btns">
	<a href="javascript:void(0)" class="easyui-linkbutton" 
		iconCls="icon-save2" onclick="Save()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" 
    	iconCls="icon-cancel2" onclick="CancelAdd()">取消</a>
</div>  
