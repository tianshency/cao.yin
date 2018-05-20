/**
 * 
 * @param title :显示成功与失败的信息
 * @param tip   ：询问是否继续
 * @param type  成功与失败的类型：success成功 fail:失败
 * @param cfmText  确定按钮的显示文本
 * @param canTest 取消按钮的显示文本
 */
function showAlert(title,tip,type,cfmText,canTest ,href){
	swal({
		title: title,
		text: tip,
		type: type,
		showCancelButton: true,
		confirmButtonColor: '#DD6B55',
		confirmButtonText: cfmText,
		closeOnConfirm: false,
		cancelButtonText: canTest,
		//closeOnCancel: false
	},
	function(){
			location.href=href;
	});
}