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
	
	<div class="container ">
		<form class="form-signin" id="form-applying-id" action="${pageContext.request.contextPath}/applyingInfo.do"  method="post">
		  <h1 class="h3 mb-3 font-weight-normal text-center">Please fill out info carefully(u only have one chance)</h1>
		  <!-- 学号 -->
		  <input type="text" name="studentId" maxlength="12" id="studentId" class="form-control" placeholder="Your student ID" required autofocus>
		  <!-- 入学方式 -->
		  <div class="form-group">
		  	 <label for="exampleFormControlSelect1">Admission type</label>
		  	 <select  name="graduateType" class="form-control" id="exampleFormControlSelect1">
		  	 	<option value="1">本校推免</option>
		  	 	<option value="2">外校推免</option>
		  	 	<option value="3">本校考研</option>
		  	 	<option value="4">外校考研</option>
		  	 </select>
		  </div>
		  
		  
		  <div class="collapse" id="collapse-file-error-hint">
			<div class="card card-body">Something wrong</div>
		  </div>
		  <button class="btn btn-lg btn-primary btn-block " type="submit">Submit</button>
		  <p class="mt-5 mb-3 text-muted text-center">&copy; 2018</p>
		</form>
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
