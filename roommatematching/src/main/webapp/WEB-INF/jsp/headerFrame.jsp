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
  
  <nav class="navbar navbar-expand-md navbar-light fixed-top bg-light">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/home.do">Roommate Matching</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item" id="admiringId">
              <a class="nav-link" href="#">Admiring</a>
            </li>
            <li class="nav-item" id="matchingId">
              <a class="nav-link" href="#">Matching</a>
            </li>
          </ul>
          <form class="form-inline mt-2 mt-md-0" id="searchFormId">
            <input class="form-control mr-sm-2" id="searchInputId" type="text" placeholder="Search" aria-label="Search" name="keyword" required autofocus>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
          </form>         

			<ul class="navbar-nav">
				<li id="logout" class="nav-item " <c:if test="${sessionScope.user==null}">style="display:none;"</c:if>><a
					class="nav-link " href="${pageContext.request.contextPath}/logout.do" 
					role="button"> Log out </a>
					</li>
			</ul>
        </div>
      </nav>
      
    <script>
	$(function(){
		//search page
		$("#searchFormId").submit(function(){
			$('#hot').load("searchFrameLoad.do",$('#searchFormId').serialize() );
			$("#playerId").show();
	        return false;
		});
		//matching page;
		$('#matchingId').on('click', function (e) {
			$('#hot').load("matchingFrameLoad.do?index="+2);
		});
		//admiring
		$('#admiringId').on('click', function (e) {
			var index=1
			$('#hot').load("matchingFrameLoad.do?index="+1);
		});
		
		
	});
	</script>
      
  </body>
</html>
