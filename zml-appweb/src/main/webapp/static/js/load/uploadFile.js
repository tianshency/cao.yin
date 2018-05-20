//点击图片
var doc_type;
var file_id;
var seq_no;
var imgId;
function clickFile(obj,docType,fileId,seqNo){
	doc_type= docType;
	file_id = fileId;
	seq_no = seqNo;
	imgId=obj.id;
	$("#"+fileId).click();
}

var showPic="";
//上传图片时浏览图片
function getFullPath(obj) {
	if(showPic==file_id){
		return;
	}
	if (obj) {
		showPic=obj.id
		if (obj.files) {
			uploadFile();
			//return window.URL.createObjectURL(obj.files[0]);//返回上传的图片信息
		}
		return obj.value;
	}
}

/**
 * 好传文件或图片
 */
function uploadFile(){
	$.ajaxFileUpload({
        url: activePath+'/loanApplicationController/uploadFile.do', 
        type: 'post',
        data : {
        	applyId:applyId,
        	docType:doc_type,
        	seq_no:seq_no
        },
        secureuri: false, //一般设置为false
        fileElementId: file_id, // 上传文件的id、name属性名
        dataType: 'JSON', //返回值类型，一般设置为json、application/json  这里要用大写  不然会取不到返回的数据
        success: function(data, status){
        	var result = JSON.parse(data);
        	$("#"+imgId).attr("src",picturePath+result.map.imgPath)
        	imgId="";
        },
        error: function(data, status, e){ 
            alert(e);
        }
    }); 
}