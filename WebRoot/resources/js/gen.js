// JavaScript Document
/*动态跳转页面*/
function gotoPage(currentSelect) {
	var pn = currentSelect.options[currentSelect.selectedIndex].text;
	_toPage(pn);
}

function selectPage(pn){
	_toPage(pn);
}

function _toPage(pn){
	$("#listCurrentPage").val(pn);
	$("#listForm").submit();
}

/*成功后，刷新表单*/
function succesListFormReflush(){
	$("#listForm").submit();
}

/*查询选中的checkBox的数据*/
function selectOnData(checkBoxName){
	var data = {};
	var count = 0;
	var value = "";
	$("input[name='"+checkBoxName+"']").each(function(index){
		if(!($(this).attr("checked") == undefined)){
			count++;
			value = $(this).val();
		}
	});
	data.count = count;
	data.id = value;
	return data;
}

/*查询选中的checkBox的数据*/
function selectOnData(checkBoxName){
	var data = {};
	var count = 0;
	var value = "";
	$("input[name='"+checkBoxName+"']").each(function(index){
		if(!($(this).attr("checked") == undefined)){
			count++;
			if(count == 1){
				value = $(this).val();
			}else{
				value = value + ","+$(this).val();
			}

		}
	});
	data.count = count;
	data.id = value;
	return data;
}

/**
 * 全选或者全不选
 * @param thiss
 * @param checkBoxName
 */
function listSelectAllOrNone(thiss,checkBoxName){
	var isAllSelect = ($(thiss).attr("checked") != undefined);
	$("input[name='"+checkBoxName+"']").each(function(index){
		if(isAllSelect){
			$(this).attr("checked","checked");
		}else{
			$(this).removeAttr("checked");
		}
	});
}

/**
 * 点击列表checkbox后，设置全选状态
 * @param thiss
 * @param selectAllOrNoneBtnId
 */
function setSelectAllOrNoneBtn(thiss,selectAllOrNoneBtnId){
	var checkBoxName = $(thiss).attr("name");
	var isAllSelect = true;
	$("input[name='"+checkBoxName+"']").each(function(index){
		if($(this).attr("checked") == undefined){
			isAllSelect = false;
			return;
		}
	});
	if(isAllSelect){
		$("#"+selectAllOrNoneBtnId).attr("checked","checked");
	}else{
		$("#"+selectAllOrNoneBtnId).removeAttr("checked");
	}
}