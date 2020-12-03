<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookWorm Book Search</title>
<style>
/* logo{
width:200px;
} */
#container{
background: linear-gradient( rgba(0,0,0,0.5), rgba(0, 0, 0, 0.5) ),url("bookimage.png");
/* background-image:url("bookimage.png"); */
background-repeat:no-repeat;
height:100%;
background-size:100% 100%;
}
html, body{
width:100%;
height:100%;
}
body{
margin:0;
}
#content{
padding-top:200px;
width:1350px;
margin-right:auto;
margin-left:auto;
}
#searchInfo{
width:1350px;
height:20px;
}
h1{
color:white;
opacity:0.5;
font-family:Arial, Helvetica, sans-serif;
}
#firstrad{
width:300px;
padding-top:10px;
color:white;
}
#secrad{
width:300px;
padding-right:350px;
padding-top:10px;
float:right;
color:white;
}
#clear{
clear:both;
}

</style>

<script>
function validate(){
	var hasError=false;
	document.getElementById("errormess").innerHTML="";
	if(document.bookForm.searchInfo.value.length<=0){
		document.getElementById("errormess").innerHTML = "Please Enter a Keyword";
		hasError = true;
	}
	return !hasError;
}
</script>

</head>
<body>

<div id="container">


		<div id = "logo" style="background-color:white; padding-bottom:10px;">
		<img src="bookwormlogo.png" alt="Web Logo" width="110px" style = "margin-left:20px; margin-top:30px;">
		</div>
		
		
		<div id = "content">
				<h1>BookWorm: Just a Mini Program...Happy Days!</h1>
				<form name = "bookForm" method = "GET" action="SearchResults.jsp" onsubmit="return validate();" >
					<input id = "searchInfo" type="text" placeholder = "Search for your favorite book!" name="searchInfo"/>
					<div id="errormess" style="color:white; padding-top:10px;"></div>
					<div id="secrad">
					<input id="radButtonISBN" type="radio" name="radButton" value="isbn:"/> ISBN
					<br/>
					<input id="radButtonPub" type="radio" name="radButton" value="inpublisher:"/> Publisher
					</div>
					<div id="firstrad">
					<input id="radButtonTitle" type="radio" name="radButton" value="intitle:" checked/> Name
					<br/>
					<input id="radButtonAuthor" type="radio" name="radButton" value="inauthor:"/> Author
					</div>
					
					<input id="button" type="submit" value="Search!" style="float:right; background-color:black; color:white;"/>
				</form>
		</div>

</div>
</body>

</html>