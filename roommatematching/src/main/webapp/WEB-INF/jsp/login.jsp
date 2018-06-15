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
		<!-- 注册Modal -->
		<div class="modal fade" id="SignUpModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalCenterTitleSignUp">Sign Up</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		       
		        <form class="form-signin" id="register-submit">
			      <h1 class="h3 mb-3 font-weight-normal">Please fill out info</h1>
			      <input type="email" id="inputEmail-signup" class="form-control" placeholder="Email address" required autofocus required maxlength="40">
			      
			      <button type="button"  class="btn btn-md btn-primary" id="get-validate-code">Get verified code</button>
			      <input type="number" class="form-control" id="validate-code-signup" placeholder="Verified code" required required maxlength="20"> 
			      
			      <label for="inputPassword" class="sr-only">Password</label>
			      <input type="password" id="inputPassword-signup" class="form-control" placeholder="Password" required required maxlength="20">
		
				  <label for="inputPasswordAgain" class="sr-only">Password Again</label>
				  <input type="password" id="inputPasswordAgain-signup" class="form-control" placeholder="Password again" required required maxlength="20">
			      
			      <div class="collapse" id="collapse-error-hint-signup">
						<div class="card card-body">Twice password are not equal</div>
				 	</div>
			      
				  <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
			      <!-- <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p> -->
			    </form>
		        
		      </div>
		    </div>
		  </div>
		</div><!-- 注册Modal End-->		
	
		<form class="form-signin text-center" id="form-signin-id" action="${pageContext.request.contextPath}/login.do" method="post">
		  <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
		  <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus required maxlength="40">
		  <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required required maxlength="20">
		  <div class="mb-3">
		    <label>
		      <a href="#" id="sign-up-link-id">Sign up</a>
		    </label>
		  </div>
		  <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		  <p class="mt-5 mb-3 text-muted">&copy; 2018</p>
		</form>
	  
	  
	  	<!-- Bootstrap core JavaScript
	    ================================================== -->
	    <!-- Placed at the end of the document so the pages load faster -->
		<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		
		<script>
			$(function(){
				$('#sign-up-link-id').on('click', function (e) {
					$("#SignUpModalCenter").modal("show");
				});
				$("#register-submit").submit(function(){
					var email=$("#inputEmail-signup").val();
			        var password=$("#inputPassword-signup").val();
			        var passwordAgain=$("#inputPasswordAgain-signup").val();
			        if(passwordAgain!=password){
			        	$('#collapse-error-hint-signup').html("Twice password are not equal");
			        	$('#collapse-error-hint-signup').collapse();
			        	return false;
			        }
			        var validateCode=$("#validate-code-signup").val();
			        var data = {        
					        "email": email,
					        "password":password,
					        "validateCode":validateCode,
					 };
			        url = "register.do";
			        $.ajax({
			            type:"POST",
			            url:url,
			            data:data,
			            success:function(data){
			            	var res=JSON.parse(data);
			                if(res.status==200){
			                    $("#SignUpModalCenter").modal('hide');
			                    window.location.href="${pageContext.request.contextPath}/info.do";
			                }else{
			                	$('#collapse-error-hint-signup').html(res.msg);
			                	$('#collapse-error-hint-signup').collapse()
			                }
			            }
			        });
			        return false;
				});//End
				$("#get-validate-code").click(function(){
					var email=$("#inputEmail-signup").val();
					var btn=$(this);
					if(!checkEmail(email)){
						$('#collapse-error-hint-signup').html("Email format is wrong");
				    	$('#collapse-error-hint-signup').collapse()
						return;
					}
				    var data = {        
					        "email": email,
					 };
				    url = "getValidateCode.do";
				    $.ajax({
				        type:"POST",
				        url:url,
				        data:data,
				        success:function(data){
				        	var res=JSON.parse(data);
				        	if(res.status==200){
				        		settime(btn);
				        	}
				        	$('#collapse-error-hint-signup').html(res.msg);
				        	$('#collapse-error-hint-signup').collapse();
				        }
				    });
				    
				  });//End
				  var waitTime=60;
				  var cnt=waitTime;
				  function settime(val) {
				  	if (cnt == 0) {
				  		val.attr("disabled", false);
				  		val.html("重新获取");
				  		val.css({
				  		"background":"#007BFF"
				  		}); 
				  		cnt = waitTime;
				  		return false;
				  	} else {
				  		val.attr("disabled", true);
				  		val.html("正在获取"+cnt);
				  		val.css({
				  		"background":"#ccc"
				  		});
				  		cnt--;
				  	}
				  	setTimeout(function() {
				  		settime(val)
				  	},1000);
				  }//End
				  function checkEmail(str){
					   var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
					   if(re.test(str)){
						   return true;
					   }else{
						   return false;
					   }
				}//End
				
			});
		</script>
	</body>
</html>
