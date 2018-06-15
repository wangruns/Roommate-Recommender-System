/**
 * 喜欢的用户
 * @param userId
 * @returns
 */
function likingFunc(userId){
	//只有登录的用户才可以享受功能
	if($("#logout")[0].style.display =='none'){
		window.location.href="index.do";
	} 
	var likingElement=$("#userId"+userId);
	var data = {        
	        "likingUserId": userId,
	 };
    url = "likingUser.do";
	$.ajax({
        type:"POST",
        url:url,
        data:data,
        success:function(data){
        	var res=JSON.parse(data);
            if(res.status==200){
            	if(res.msg=="true"){
            		//已经喜欢了
            		likingElement.addClass("text-danger");
            	}else{
            		//已经取消喜欢了
            		likingElement.removeClass("text-danger");
            	}
            }else{
            	alert(res.msg)
            	/*$('#collapse-error-hint').html(res.msg);
            	$('#collapse-error-hint').collapse()*/
            }
        }
    });
}
