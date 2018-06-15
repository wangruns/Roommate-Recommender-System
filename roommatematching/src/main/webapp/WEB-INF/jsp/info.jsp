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
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/signin.css">
  	</head>
	
	<body>
		<form class="form-signin" id="form-getstarted-id" action="${pageContext.request.contextPath}/fillInfo.do" enctype="multipart/form-data" method="post">
		  <h1 class="h3 mb-3 font-weight-normal text-center">Please fill out info carefully</h1>
		  <!-- 姓名 -->
		  <input type="text" name="userName" maxlength="10" id="userNameId" class="form-control" placeholder="User real name" required autofocus>
		  <!-- 性别 -->
		  <div class="mb-3">
		      <div class="form-check form-check-inline">
		      	<input class="form-check-input" type="radio" name="gender" value="1" checked>
		     	 <label class="form-check-label" for="exampleRadios1">
		     	 Male
		     	 </label>
		      </div>
		      <div class="form-check form-check-inline">
		      	<input class="form-check-input" type="radio" name="gender" value="0" checked>
		     	 <label class="form-check-label" for="exampleRadios1">
		     	 Female
		     	 </label>
		      </div>
		  </div>
		  <!-- 学校 -->
		  <div class="form-group">
		  	 <label for="exampleFormControlSelect1">School select</label>
		  	 <select  name="schoolId" class="form-control" id="exampleFormControlSelect1">
		  	 	<c:forEach items="${schoolList}" var="school">
		  	 		<option value="${school.schoolId}">${school.schoolName}</option>
		  	 	</c:forEach>
		  	 </select>
		  </div>
		  <!-- 专业 -->
		  <div class="form-group">
		  	 <label for="exampleFormControlSelect2">Major select</label>
		  	 <select name="majorId" class="form-control" id="exampleFormControlSelect2">
		  	 	<c:forEach items="${majorList}" var="major">
		  	 		<option value="${major.majorId}">${major.majorName}</option>
		  	 	</c:forEach>
		  	 </select>
		  </div>
		  <!-- 自我描述 -->
		  <div class="form-group">
		  	<label for="exampleFormControlTextarea1">Make myself known</label>
		  	<textarea name="selfInfo" class="form-control" id="exampleFormControlTextarea1" rows="3" required maxlength="255"></textarea>
		  </div>
		  <!-- 期待的室友 -->	  
		  <div class="form-group">
		  	<label for="exampleFormControlTextarea1">Expected roommate character</label>
		  	<textarea name="expectedInfo" class="form-control" id="exampleFormControlTextarea2" rows="3" required></textarea>
		  </div>	
		  <!-- 照片 -->	
		  <h5 class="h5 mb-3 font-weight-normal">Photo</h5>
	      <div class="input-group mb-2">
			  <div class="custom-file">
			    <input name="photo" type="file" id="filePhotoId" class="custom-file-input" id="inputGroupFile01" name="song" onchange="loadFile(this.files[0],'photoHintId')" required>
			    <label id="photoHintId" class="custom-file-label" for="inputGroupFile01">jpg/png/JPEG</label>
			  </div>
		  </div>  
		  
		  <div class="collapse" id="collapse-file-error-hint">
			<div class="card card-body">Something wrong</div>
		  </div>
		  <button class="btn btn-lg btn-primary btn-block" type="submit">Get Started</button>
		  <p class="mt-5 mb-3 text-muted text-center">&copy; 2018</p>
		</form>
	  
	  
	  	<!-- Bootstrap core JavaScript
	    ================================================== -->
	    <!-- Placed at the end of the document so the pages load faster -->
		<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		
		<script>
			$(function(){
				var maxSize=1024*1024*5;//文件最大5M
				var format=["jpg","png","JPEG","JPG","PNG","jpeg"];//jpg/png/JPEG
				$("#form-getstarted-id").submit(function(){
					var photo=$('#filePhotoId').get(0).files[0];
					var isSubmit=false;
					if(photo==null){
		            	return false;
			        }else if(photo.size>maxSize){
			        	$('#collapse-file-error-hint').html("超出文件最大限制");
		            	$('#collapse-file-error-hint').collapse();return false;
			        }else if(format.indexOf(photo.name.substr(photo.name.lastIndexOf(".")+1) )==-1){
			        	$('#collapse-file-error-hint').html("文件格式不对");
		            	$('#collapse-file-error-hint').collapse();return false;
			        }else {
			        	//检验姓名
			        	var userName=$("#userNameId").val();
			        	var data = {        
						        "userName": userName,
						 };
			        	url = "checkUserName.do";
			        	$.ajax({
				            type:"POST",
				            url:url,
				            async:false,
				            data:data,
				            success:function(data){
				            	var res=JSON.parse(data);
				                if(res.status==200){
				                	isSubmit=true;
				                }else{
				                	$('#collapse-file-error-hint').html(res.msg);
				                	$('#collapse-file-error-hint').collapse()
				                	//return false;
				                }
				            }
				        });
			        	return isSubmit;
			        	
			        }
					
				});
				
			});
			function loadFile(file,fileHintId){
				$("#"+fileHintId).html(file.name);
			}
		</script>
	</body>
</html>
