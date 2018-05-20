/**
 * 选择类型，转到对应的发部页
 */
function toReleasePage(parentType,childType){
	if(parentType=='01'){//食物
		location.href=activePath+"releaseInfoController/toFoodPage.do?parentType="+parentType+"&childType="+childType;
	}
	else if(parentType=='02'){//土地
		location.href=activePath+"releaseInfoController/toTerritoryPage.do?parentType="+parentType+"&childType="+childType;
	}
	else if(parentType=='03'){
		location.href=activePath+"releaseInfoController/toReleaseInfoPage.do?parentType="+parentType+"&childType="+childType;
	}
	else if(parentType=='04'){
		location.href=activePath+"releaseInfoController/toReleaseInfoPage.do?parentType="+parentType+"&childType="+childType;
	}	
	else if(parentType=='05'){
		location.href=activePath+"releaseInfoController/toReleaseInfoPage.do?parentType="+parentType+"&childType="+childType;
	}
}

$(function(){
	$("#myRelease").click(function(){
		location.href=activePath+"releaseInfoController/tomyReleaseListPage.do"
	});
});