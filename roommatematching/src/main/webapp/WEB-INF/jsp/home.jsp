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

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	
	<!-- simple-line-icons CSS http://www.bootcdn.cn/simple-line-icons/-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/simple-line-icons.css" >
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconfont/iconfont.css">
    
    <style>
	body{
		padding-bottom: 6rem;
	}
	.hide{
		display: none;
	}
	.ahover{
		color:#818182;
	}
	.img-logo{
		height:50px;
		width:50px;
		margin-top:1px;
	}
	.fl{
		float:left;
	}
	.float-right{
		float:right;
	}
	.media-left, .media-right, .media-body{
		display: table-cell;
		vertical-align: top;
	}
	.media-heading {
	   margin-top: 0;
	   margin-bottom: 5px;
	}
	.ds-time{
		color: #999;
	}
	.border-line{
		border-bottom: 1px solid #cfcfcf;
	}
	.border-line-color{
		border-bottom: 2px solid #2AD980;
	}
	.margin-left-m{
		margin-left:15px;
	}
	</style>
    
  </head>
  <body>
    <header id="headerId">
    </header>
    
    <h1>&nbsp;</h1>
    <div id="hot-delete">
    </div><!-- hot-delete End -->
	<h1>&nbsp;</h1>
	
      <!-- Marketing messaging and featurettes
      ================================================== -->
      <!-- Wrap the rest of the page in another container to center all the content. -->
	<div id="hot">
      <div class="container marketing">
		<c:forEach items="${rowList}" var="row">
        <div class="row">
          	<c:forEach items="${row}" var="user">
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
						    <a href="#" class="card-link">${user.userName}</a>
						    <a href="javascript:;" title="admire this person" class="card-link <c:if test="${user.whetherLiking}">text-danger</c:if>" onclick="likingFunc(${user.userId})" id="userId${user.userId}" ><i class="icon-heart"></i></a>
						  </div>
					  </div>
		          </div><!-- /.col-lg-4 -->
          	 </c:forEach>
         </div><!-- /.row -->
        </c:forEach>
      </div><!-- /.container -->
      
      </div><!-- hot End -->


      <!-- FOOTER -->
      <footer class="container fixed-bottom">
        <p class="float-right"><a href="#">返回顶部</a></p>
        <p>&copy; 2018 WangRuns, School of Big Data & Software Engineering. &middot; <a href="https://github.com/wangruns/Roommate-Recommender-System">GitHub</a></p>
      </footer>
    

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	
	<script src="${pageContext.request.contextPath}/js/home.js"></script>
	
	<script>
	$(function(){
		$('#headerId').load("headerFrameLoad.do");
	});
	</script>
  </body>
</html>
