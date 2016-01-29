var oneRowModal,oneRowModalBody,oneRowModalTitle, oneRowModalCloseBtn,oneRowModalSaveBtn,oneRowModalUpdateBtn;
var selectCheckBoxName = "roleId";//checkbox id 的name值
$(document).ready(function(){
	$("#resetBtn").click(function(){
		$("#roleCode").val("");
		$("#roleName").val("");
		$("#firstTime").val("");
		$("#lastTime").val("");
	});
	/*modal element*/
	oneRowModal = $("#oneRowModal");
	oneRowModalBody = $("#oneRowModal .modal-body");
	oneRowModalTitle = $("#oneRowModal .modal-header #title");
	oneRowModalCloseBtn = $("#oneRowModalCloseBtn");
	oneRowModalSaveBtn = $("#oneRowModalSaveBtn");
	oneRowModalUpdateBtn = $("#oneRowModalUpdateBtn");
	
	/*添加模态框*/
	$("#addBtn").click(showAddModal);
	
	/*修改模态框*/
	$("#updateBtn").click(showUpdateModal);
	
	/*删除模态框*/
	$("#deleteBtn").click(showDeleteModal);

	
	oneRowModalSaveBtn.click(saveOrUpdateForm);
	oneRowModalUpdateBtn.click(saveOrUpdateForm);
});

/*添加模态框*/
function showAddModal(){
	var url = appCtx+"admin/sysrole/sysroleForm.do";
	$("#loading").modal("show");
	$.post(url,function(data){
		$("#loading").modal("hide");
		oneRowModalTitle.html("新增权限角色");
		oneRowModalBody.html(data);
		oneRowModalUpdateBtn.hide();
		oneRowModalSaveBtn.show();
		oneRowModalCloseBtn.show();
		/*ztree 模块授权树*/
		showSysModuleTree();
        
		oneRowModal.modal("show");
	});
}
/*修改模态框*/
function showUpdateModal(){
	var data = selectOnData(selectCheckBoxName);
	if(1 == data.count){
		showOneUpdateModal(data.id);
	}else{
		doAlert("只能选择一条记录进行操作！");
	}
}
/*一条记录进行修改*/
function showOneUpdateModal(id){
	var url = appCtx+"admin/sysrole/sysroleForm.do";
	$("#loading").modal("show");
	var data={};
	data.id=id;
	$.post(url,data,function(data){
		$("#loading").modal("hide");
		oneRowModalTitle.html("修改权限角色");
		oneRowModalBody.html(data);
		oneRowModalSaveBtn.hide();
		oneRowModalUpdateBtn.show();
		oneRowModalCloseBtn.show();
		/*ztree 模块授权树*/
		showSysModuleTree(true);
        
		oneRowModal.modal("show");
	});
}

/*删除模态框*/
function showDeleteModal(){
	var data = selectOnData(selectCheckBoxName);
	if(data.count > 0){
		deleteModalDo(data.id);
	}else{
		doAlert("至少选择一条记录进行操作！");
	}
}

function deleteModalDo(id){
	doConfirm("确认删除所选记录？",function(result){
		if(result){
	        var url = appCtx+"admin/sysrole/batchDelete.do";
	        var params = {};
	        params.ids = id;
	    	$("#loading").modal("show");
	        $.post(url,params,function(data){
		    	$("#loading").modal("hide");
	        	if("success" == data.result){
	        		doSuccess();
	        		/*成功后，模态框的状态*/
	        		successModalStatus(oneRowModalCloseBtn,oneRowModalSaveBtn,oneRowModalUpdateBtn,oneRowModal,oneRowModalTitle,oneRowModalBody);
	        		/*成功后，刷新表单*/
	        		succesListFormReflush();
	        	}else if("fail" == data.result){
	        		doFail();
	        	}else{
	        		doAlert("失败!"+data.message);
	        	}
	        });
	    }
	});
}
/*添加或者更新结果*/
function saveOrUpdateForm(){
    var flag = $("#addOrUpdateModalForm").validationAjax();
    if(flag){
        var url = appCtx+"admin/sysrole/insertOrUpdate.do";
        var params = $("#addOrUpdateModalForm").serialize();
    	$("#loading").modal("show");
        $.post(url,params,function(data){
        	$("#loading").modal("hide");
        	if("success" == data.result){
        		doSuccess();
        		/*成功后，模态框的状态*/
        		successModalStatus(oneRowModalCloseBtn,oneRowModalSaveBtn,oneRowModalUpdateBtn,oneRowModal,oneRowModalTitle,oneRowModalBody);
        		/*成功后，刷新表单*/
        		succesListFormReflush();
        	}else if("fail" == data.result){
        		doFail();
        	}else{
        		doAlert("失败!"+data.message);
        	}
        });
    }
}
/*显示树，isUpdate = ture,是更新，勾选已经选中的*/
function showSysModuleTree(isUpdate) {
	var url = appCtx + "admin/sysmodule/sysmoduleZTree.do";
	$.post(url,function(data){ 
		var showOrgZTree = $.fn.zTree.init($("#treeSysModuleModalForm"), sysModuleSetting, data);
		showOrgZTree.expandAll(true);
		
		/*设置图标样式*/
		var nodes = showOrgZTree.getNodes();
		for(var i=0;i<nodes.length;i++){
			var node = nodes[i];
			/*把没子节点的图标设置成 文件夹关闭样式 -> not_children_ico_docu*/
			if(undefined == node.children || null == node.children || node.children.length == 0){
				node.iconSkin="not_children";
				showOrgZTree.updateNode(node);
			}
		}
		if(isUpdate){
			parseSysModuleCheckedNodes(showOrgZTree);
		}
	});
}
/**
 * ztree 资源树
*/
	var sysModuleSetting = { 
			check: {
				enable: true
			},
			data: {
				key:{
					name:"moduleName"
				},
				simpleData: {
					enable: true,
					idKey:"moduleId",  
                    pIdKey:"parentModuleId",
    				rootPId:0
				}
			},
			callback: {
				onCheck:getSysModuleCheckedNodes
			}
        };
//获得组织授权树勾选的节点,不存父节点
function getSysModuleCheckedNodes(){
	var treeObj = $.fn.zTree.getZTreeObj("treeSysModuleModalForm");
	var nodes = treeObj.getCheckedNodes(true);
	var idArray = new Array();
	for(var i=0;i<nodes.length;i++){
		var node = nodes[i];
		var halfCheck = node.getCheckStatus().half;
		if(halfCheck == false){
			if(node.parentModuleId != 0){
				idArray.push(node.moduleId);
			}else{
				if(undefined == node.children || null == node.children || node.children.length == 0){
					treeObj.checkNode(node,false,false);
				}
			}
		}
	}
	$("#treeSysModuleModalIds").val(idArray.join(","));
}

//展开
function parseSysModuleCheckedNodes(objTree){
	var ids = $("#treeSysModuleModalIds").val();
	if(ids!=""){
		objTree.expandAll(true);
		var idsArray = ids.split(",");
		for(var i=0;i<idsArray.length;i++){
			var currentNode = objTree.getNodeByParam("moduleId",idsArray[i],null);
			if(null != currentNode){
				objTree.checkNode(currentNode,true,false);
				
				//如果父节点不为空,并此父节点
				var parentNode = currentNode.getParentNode();
				if(parentNode != null){
					//循环判断其父节点是否存在,是否已经被勾选
					while( (parentNode != null && !parentNode.checked)){
						objTree.checkNode(parentNode,true,false);//勾选上父节点
						parentNode = parentNode.getParentNode();
					}
				}
			}
		}
	}
}