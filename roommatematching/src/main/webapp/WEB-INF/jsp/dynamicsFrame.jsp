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
	
  	</head>
	
	<body>
	
	<div class="container">
		<div class="row content">
			<!-- 前DIV -->
      	    <div class="col-sm-1">
      	    </div><!-- 该前DIV为了中DIV居中 -->
      	    
      	    <!--中DIV -->
            <div class="col-sm-10">
             <!-- at dynamic -->
           	  <ul class="list-group list-group-flush">
					<c:forEach items="${atDynamicList}" var="atDynamic" varStatus="status">
						<li class="media list-group-item list-group-item-light">
			                <div class="media-left">
			                    <a href="#">
			                        <img class="rounded img-logo" src="" alt="菜鸟" >
			                    </a>
			                </div>
			                <div class="media-body">
			                    <h6 class="media-heading text-primary">${atDynamic.userName}</h6>
			
			                    <p><a href="javascript:;" onclick="reviewLoad(${atDynamic.targetUserId})">@ me...</a></p>
			                    <div class="ds-comment-footer">
			                        <span class="ds-time" title="${atDynamic.reviewTime}">${atDynamic.reviewTime}</span>&nbsp;
			                    </div>
			                </div>
	        			 </li>
					</c:forEach>
				 </ul>
				 <!-- review dynamic -->
            	 <ul class="list-group list-group-flush">
					<c:forEach items="${reviewDynamicList}" var="reviewDynamic" varStatus="status">
						<li class="media list-group-item list-group-item-light">
			                <div class="media-left">
			                    <a href="#">
			                        <img class="rounded img-logo" src="" alt="菜鸟" >
			                    </a>
			                </div>
			                <div class="media-body">
			                    <h6 class="media-heading text-primary">${reviewDynamic.userName}</h6>
			
			                    <p><a href="javascript:;" onclick="reviewLoad(${reviewDynamic.userId})">made a review on my sharing</a></p>
			                    <div class="ds-comment-footer">
			                        <span class="ds-time" title="${reviewDynamic.reviewTime}">${reviewDynamic.reviewTime}</span>&nbsp;
			                    </div>
			                </div>
	        			 </li>
					</c:forEach>
				 </ul>
            </div><!--中DIV End-->
            
            <!-- 后DIV -->
   		    <div class="col-sm-1">
   		    </div><!-- 该后DIV为了中DIV居中 -->
				 
         </div>
	</div>
	  
		<script>
		$(function(){
			$("#form-applying-id").submit(function(){
				$('#hot').load("applyingInfo.do",$('#form-applying-id').serialize() );
		        return false;
			});
			
		});
		</script>
	</body>
</html>
