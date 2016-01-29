var oneRowModal,oneRowModalBody,oneRowModalTitle, oneRowModalCloseBtn,oneRowModalSaveBtn,oneRowModalUpdateBtn;
var selectCheckBoxName = "moduleId";//checkbox id 的name值
$(document).ready(function(){
	$("#resetBtn").click(resetSearch);
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
/*重置搜索框*/
function resetSearch(){
	$("#moduleName").val("");
	$("#moduleUrl").val("");
	$("#target").val("");
	$("#rightCode").val("");
	$("#firstTime").val("");
	$("#lastTime").val("");
}
/*添加模态框*/
function showAddModal(){
	var url = appCtx+"admin/sysmodule/sysmoduleForm.do";
	$("#loading").modal("show");
	$.post(url,function(data){
		$("#loading").modal("hide");
		oneRowModalTitle.html("新增系统模块");
		oneRowModalBody.html(data);
		oneRowModalUpdateBtn.hide();
		oneRowModalSaveBtn.show();
		oneRowModalCloseBtn.show();
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
	var url = appCtx+"admin/sysmodule/sysmoduleForm.do";
	$("#loading").modal("show");
	var data={};
	data.id=id;
	$.post(url,data,function(data){
		$("#loading").modal("hide");
		oneRowModalTitle.html("修改系统模块");
		oneRowModalBody.html(data);
		oneRowModalSaveBtn.hide();
		oneRowModalUpdateBtn.show();
		oneRowModalCloseBtn.show();
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
	        var url = appCtx+"admin/sysmodule/batchDelete.do";
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
        var url = appCtx+"admin/sysmodule/insertOrUpdate.do";
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

