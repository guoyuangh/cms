<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<link href="<%=request.getContextPath()%>/resources/css/admin.css"
	type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/css/gen.css"
	type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/css/error.css"
	type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/css/page.css"
	type="text/css" rel="stylesheet" />
	
<link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap-modal.css"
	type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/css/zTree/zTreeStyle/zTreeStyle.css" rel="stylesheet"/>
	
	
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/gen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/bootstrap/bootstrap-modal.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/bootstrap/bootstrap-modalmanager.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/bootstrap/bootstrap-validation.js"></script>
<!-- date picker -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/My97DatePicker/WdatePicker.js"></script>
<!-- ztree -->
<script src="<%=request.getContextPath()%>/resources/js/zTree/jquery.ztree.all-3.5.js"></script>
<script>
var appCtx = "<%=request.getContextPath()%>/";
		 	var confirmResult = false;
		 	var confirmTimeout = null;
		 	
		 	//操作成功
		 	function doSuccess(){
		 		$("#successModal").modal("show");
				setTimeout("$('#successModal').modal('hide');", 2000);
		 	}
		 	
		 	//操作失败
		 	function doFail(){
		 		$("#failModal").modal("show");
				setTimeout("$('#failModal').modal('hide');", 2000);
		 	}
		 	
		 	//alert
		 	function doAlert(content){
		 		$("#alertInfo").html(content);
		 		$("#alertModal").modal("show");
		 	}

		 	//doMessageAlert
		 	function doMessageAlert(content){
		 		$("#doMessageAlertInfo").html(content);
		 		$("#doMessageAlertModal").modal("show");
		 	}
		 	
		 	$(document).ready(function(){
		 		//确定按钮事件
		 		$("#confirmOK").click(function() {
		            $('#confirmModal').modal('hide');
                	mDialogCallback(true);
                });
		 		
		 		//取消按钮事件
		 		$("#confirmCancel").click(function() {
                	mDialogCallback(false);
                });
		 	});
		 	
		 	var mDialogCallback;
			
		 	//confirm
            function doConfirm(msg, callback) {
	            mDialogCallback = callback;
	            $("#confirmInfo").html(msg);
	            $('#confirmModal').modal('show');
            };
            
            /*成功后，模态框的状态*/
            function successModalStatus(rowModalCloseBtn,rowModalSaveBtn,rowModalUpdateBtn,rowModal,rowModalTitle,rowModalBody){
            	rowModalCloseBtn.hide();
                rowModalSaveBtn.hide();
            	rowModalUpdateBtn.hide();
            	rowModalBody.html("");
            	rowModalTitle.html("");
            	rowModal.modal("hide");
            }
		</script>
<!-- 确认模态框 -->
<div id="confirmModal" class="modal hide" data-backdrop = "static" data-width="350" style="left: 45% !important;top: 45%!important;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3>确认提示</h3>
	</div>
	<div class="modal-body" style="font-size: 13px;vertical-align: middle;">
		<span class="dialog_icon icon_info_b"></span>
		<div id="confirmInfo" class="dialog_f_c"></div>
	</div>
	<div class="modal-footer">
		<input type="button" class="btn" data-dismiss="modal" aria-hidden="true" id="confirmOK" value="确定"/>
		<input type="button"  class="btn" data-dismiss="modal" aria-hidden="true" id="confirmCancel" value="取消"/>
	</div>
</div>

<!-- 弹出提示模态框 -->
<div id="alertModal" class="modal hide" data-backdrop = "static" data-width="350" style="left: 45% !important;top: 45%!important;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3>提示信息</h3>
	</div>
	<div class="modal-body" style="font-size: 13px;vertical-align: middle;">
		<span class="dialog_icon icon_info_b"></span>
		<div id="alertInfo" class="dialog_f_c"></div>
	</div>
	<div class="modal-footer">
		<input type="button" class="btn" data-dismiss="modal" aria-hidden="true" value="确定"/>
	</div>
</div>

<!-- 弹出提示模态框 大的 -->
<div id="doMessageAlertModal" class="modal hide" data-backdrop = "static" data-width="650" style="left: 45% !important;top: 45%!important;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3>信息</h3>
	</div>
	<div class="modal-body" style="font-size: 13px;vertical-align: middle;">
		<span class="dialog_icon icon_info_b"></span>
		<div id=doMessageAlertInfo class="dialog_f_c"></div>
	</div>
	<div class="modal-footer">
		<input type="button" class="btn" data-dismiss="modal" aria-hidden="true" value="确定"/>
	</div>
</div>

<!-- 操作中模态框 -->
<div id="loading" class="modal hide" data-backdrop = "static" style="top: 45% !important;font-size: 13px;">
	<div class="modal-body" style="text-align: center;">
		<img src="<%=request.getContextPath() %>/resources/images/blue-loading.gif"/>正在操作中，请稍后...
	</div>
</div>

<!-- 操作成功模态框 -->
<div id="successModal" class="modal hide" data-backdrop = "static" style="top: 45% !important;font-size: 13px;">
	<div class="modal-body" style="text-align: center;">
		<img src="<%=request.getContextPath() %>/resources/images/blue-success.gif"/>操作成功！
	</div>
</div>

<!-- 操作失败模态框 -->
<div id="failModal" class="modal hide" data-backdrop = "static" style="top: 45% !important;font-size: 13px;">
	<div class="modal-body" style="text-align: center;">
		<img src="<%=request.getContextPath() %>/resources/images/blue-error.gif"/>操作失败！
	</div>
</div>