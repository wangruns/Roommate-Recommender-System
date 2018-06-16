<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">
    <title>Roommate Matching</title>
	
  </head>
  <body>
  
      <div class="container marketing">
        <div class="row">
		          <div class="col-lg-4">
		            <div class="card" style="width: 18rem;">
						  <img class="card-img-top" src="${pageContext.request.contextPath}/${user.photoAddress}" alt="Card image cap">
						  <div class="card-body">
						    <h5 class="card-title">Make myself known</h5>
						    <p class="card-text">${user.selfInfo}</p>
						  </div>
						  <div class="card-body">
						    <h5 class="card-title">Expected character</h5>
						    <p class="card-text">${user.expectedInfo}</p>
						  </div>
						  <div class="card-body">
						    <a href="#" onclick="reviewLoad(${user.userId})" class="card-link">${user.userName}</a>
						    <a href="javascript:;" title="admire this person" class="card-link <c:if test="${user.whetherLiking}">text-danger</c:if>" onclick="supportFunc(${user.userId})" id="userId${user.userId}" ><i class="icon-heart"></i></a>
						  </div>
					  </div>
		          </div><!-- /.col-lg-4 -->
		          
		          
		          <div class="col-lg-8">
		          	
				     <!--中DIV -->
		            <div class="col-sm-10">
		            	<h6>&nbsp;</h6>
		            	<h3 class="border-line-color">Review</h3>
		            	<!-- 发表评论 -->
		            	<div class="container">
		            		<div class="row content">
		            			 <div class="col-sm-5 ">
		            				<div>
		            					<img src="${pageContext.request.contextPath}/${curUserPhotoAddress}"  alt="..." class="rounded img-logo">
		            				</div>
		            				<div>
		            				   <textarea id="reviewId" rows="2" cols="20" style="resize:none" placeholder="Write something..." onkeyup="wordCount(this)" maxlength="140"></textarea>
		            				   <span id="num-cnt">140</span>
		            				   <button onclick="reviewFunc(${user.userId})" class="btn btn-outline-success my-2 my-sm-0 fl">Submit</button>
		            				</div>
		            				
		            			</div> 
		            			<div class="col-sm-5 ">
		            				<div class="collapse" id="collapse-review-error-hint">
									 	<div class="card card-body">Something wrong</div>
								    </div>
		            			</div>
		            		</div>
		            	</div><!-- 发表评论 End-->
		            	
		            	
		            	<h6>&nbsp;</h6>
		            	<h3 class="border-line">Wonderful Review</h3>
		            	<!-- 精彩评论 -->
            			<div id="newReviewAreaId">
		            	<div class="container">
		            		<div class="row content">
								  <ul class="list-group list-group-flush">
									<c:forEach items="${hotReviewList}" var="review" varStatus="status">
										<li class="media list-group-item list-group-item-light">
							                <div class="media-left">
							                    <a href="#">
							                        <img class="rounded img-logo" src="" alt="Newcomer" >
							                    </a>
							                </div>
							                <div class="media-body">
							                    <h6 class="media-heading text-primary">${review.userName}</h6>
							
							                    <p>${review.review}</p>
							                    <div class="ds-comment-footer">
							                        <span class="ds-time" title="${review.reviewTime}">${review.reviewTime}</span>&nbsp;
							                        <a href="javascript:;"
								                        class="<c:if test="${review.whetherLiked}">text-danger</c:if>" 
														onclick="supportFunc(${review.reviewId})" id="hotReviewLike${review.reviewId}"
														title="Support this review"><span class="icon-like" id="hotReviewLikeNum${review.reviewId}">${review.likeCoefficient}</span> 
													</a>
							                    </div>
							                </div>
		           						  </li>
										
									</c:forEach>
								 </ul>
		            		</div>
		            	</div><!-- 精彩评论  End-->
		            	</div><!-- newReviewAreaId  End-->
		            	
		            </div><!-- 中DIV -->
		          	
		   </div><!-- /.col-lg-8 -->
		          
         </div><!-- /.row -->
         
      </div><!-- /.container End-->
		
  </body>
</html>