<!DOCTYPE html> 
<html>
 <head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	 <link rel="stylesheet" href="CSS/Login.css">
	 <title>Login page</title>
 </head>
 <body onload="disableLock()">
	<nav class="navbar navbar-expand-lg navbar-light">
	  <a class="navbar-brand text-white" href="#">USC CS310 Stock Portfolio Management</a>
	</nav>
	
 	 <div class="col-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto text-center form p-4">
		<form class="justify-content-center" id="form" action="LoginServlet" method="POST"> 
			<div class="form-group">
				<div class="banner">
					<p class="banner-text">Login</p>
				</div>
			</div>
			<div id="formerror" style="color:red;"> ${errorMessage}</div>
			<div class="form-group">
				<input id="userLog" class="bar" type="text" name="username" placeholder="Username"/>
			</div>
			<div class="form-group">
				<input id="passLog" class="bar" type="password" name="userpass" placeholder="Password"/>
			</div>
			<div class="form-group">
				<div class="bottomButtons">	
					<a class= "link" href="Register.jsp">Register</a>
					 
					<input id="signInBut" class="button" type="submit" name="status" value="Sign in"/> 
				</div>
			</div>
		</form>  
	</div>  
	
	<!-- Bootstrap Scripts -->
	  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	  <script>
	 function disableLock(){
		 <%
			String holder = "";
		 	String un = "";
			if(session.getAttribute("locked") != null ) {
				holder = (String)session.getAttribute("locked");
				un = (String)session.getAttribute("username");
			}
			else{
				session.setAttribute("locked", "false");
			}
		%>
		 var hold = "<%= holder%>";
		 if (hold.localeCompare("true")==0){
			 console.log("FINALLY LOCKED INSIDE IF");
			 var untest = document.getElementById("userLog").value;
			 console.log("username is: ");
			 console.log(untest);
			 /* document.getElementById("signInBut").disabled = true;
			 document.getElementById("userLog").disabled = true;
	    	 document.getElementById("passLog").disabled = true;
			 document.getElementById("userLog").placeholder="Locked";
	    	 document.getElementById("passLog").placeholder="Locked";  */
			 document.getElementById("formerror").innerHTML = "You are locked for a minute"
		     setTimeout(function(){
		    	 document.getElementById("signInBut").disabled = false;
		    	 document.getElementById("userLog").disabled = false;
		    	 document.getElementById("passLog").disabled = false;
		    	 document.getElementById("userLog").placeholder="Username";
		    	 document.getElementById("passLog").placeholder="Password"; 
		    	 
		     },60000); 
		 }
		 else{
			 console.log("testinggggggggg out if");
		 }
	 }
	 
	 </script>
</body>
</html>