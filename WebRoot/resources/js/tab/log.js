$(document).ready(function(){
	$("#logResetBtn").click(function(){
		$("#action").val("");
		$("#firstTime").val("");
		$("#lastTime").val("");
	});
});
function showAllAction(actionId){
	doMessageAlert($("#"+actionId).val());
}