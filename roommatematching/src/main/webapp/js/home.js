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

/**
 * 评论加载
 * @param userId
 * @returns
 */
function reviewLoad(userId){
	$('#hot').load("reviewFrameLoad.do?userId="+userId);
}

/**
 * 评论计数，不超过140字
 * @param input
 * @returns
 */
function wordCount(input) {  
    var content = $("#num-cnt");
    if (content && input) {  
        // 获取输入框输入内容长度并更新到界面  
        var value = input.value;  
        // 将换行符不计算为单词数  
       // value = value.replace(/\n|\r/gi,"");  
        // 更新计数  
        content.text(140-value.length); 
    }  
}

/**
 * 发表评论
 * @param songId
 * @returns
 */
function reviewFunc(userId){
	//不能为空评论
	if(parseInt($("#num-cnt").text())==140){
		$('#collapse-review-error-hint').html("review can not be null");
		$('#collapse-review-error-hint').collapse();
		return;
	}
	//获取评论信息
	review=$("#reviewId").val();
	var data = {        
	        "review": review,
	        "userId":userId,
	 };
    url = "review.do";
    $.ajax({
        type:"POST",
        url:url,
        data:data,
        success:function(data){
        	var res=JSON.parse(data);
            if(res.status==200){
            	$("#reviewId").val("");
            	$('#collapse-review-error-hint').html(res.msg);
            	$('#collapse-review-error-hint').collapse();
            	//更新最近评论区域
            	$('#newReviewAreaId').load("newReviewFrameLoad.do?userId="+userId);
            	
            }else{
            	$('#collapse-review-error-hint').html(res.msg);
            	$('#collapse-review-error-hint').collapse();
            }
        }
    });
	
}

/**
 * 评论点赞
 * @param reviewId
 * @returns
 */
function supportFunc(reviewId){
	var reviewLikeElement=$("#hotReviewLike"+reviewId);
	var reviewLikeNumElement=$("#hotReviewLikeNum"+reviewId);
	var data = {        
	        "reviewId": reviewId,
	 };
    url = "reviewLike.do";
	$.ajax({
        type:"GET",
        url:url,
        data:data,
        success:function(data){
        	var res=JSON.parse(data);
            if(res.status==200){
            	if(res.msg=="true"){
            		//已经点赞
            		reviewLikeElement.addClass("text-danger");
            		//修改点赞显示次数
            		likeNum=reviewLikeNumElement.text();
            		reviewLikeNumElement.text(parseInt(likeNum) + 1);
            	}else{
            		//已经取消点赞
            		reviewLikeElement.removeClass("text-danger");
            		//修改点赞显示次数
            		likeNum=reviewLikeNumElement.text();
            		reviewLikeNumElement.text(parseInt(likeNum) - 1);
            	}
            }else{
            	alert(res.msg)
            	/*$('#collapse-review-error-hint').html(res.msg);
            	$('#collapse-error-hint').collapse()*/
            }
        }
    });
}
