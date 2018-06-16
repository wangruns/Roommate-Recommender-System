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
						    <%-- <a href="#" class="card-link">${user.userName}</a> --%>
						    <a href="javascript:;" onclick="reviewLoad(${user.userId})" class="card-link">${user.userName}</a>
						    <a href="javascript:;" title="admire this person" class="card-link <c:if test="${user.whetherLiking}">text-danger</c:if>" onclick="likingFunc(${user.userId})" id="userId${user.userId}" ><i class="icon-heart"></i></a>
						  </div>
					  </div>
		          </div><!-- /.col-lg-4 -->
          	 </c:forEach>
         </div><!-- /.row -->
        </c:forEach>
      </div><!-- /.container -->
 	
      
  </body>
</html>
