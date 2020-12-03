<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<%@ page import="csci310.Stock_api"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="csci310.Stock"%>
<%@ page import="csci310.Portfolio"%>
<%@ page import="csci310.DatePrice"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>




<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main page</title>
<link rel="stylesheet" href="CSS/MainPage.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<link rel="shortcut icon" href="#">
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.min.js"></script>
<script
  src="https://code.jquery.com/jquery-3.5.1.js"
  integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
  crossorigin="anonymous"></script>
<script>
	var IDLE_TIMEOUT = 120; //seconds
	var _idleSecondsCounter = 0;
	
	document.onclick = function() {
		idleSecondsCounter = 0;
	};
	
	document.onmousemove = function() {
		_idleSecondsCounter = 0;
	};
		
	document.onkeypress = function() {
		_idleSecondsCounter = 0;
	};
	
	window.setInterval(CheckIdleTime, 1000);
		
	function CheckIdleTime() {
		_idleSecondsCounter++;
		var oPanel = document.getElementById("SecondsUntilExpire");
		if (oPanel)
			oPanel.innerHTML = (IDLE_TIMEOUT - _idleSecondsCounter) + "";
		if (_idleSecondsCounter >= IDLE_TIMEOUT) {
			//alert("Time expired!");
			document.location.href = "index.jsp";
		}
	}

	function searchStock() {
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				var data = xhr.responseText;
				if (data === "Fail") {
					/* alert("Please enter the right stock code"); */
					document.getElementById('searchFormerror').innerHTML = "Please enter the right stock code";
				}
				else{
					stockCode = stockCode.toUpperCase();
					document.getElementById('searchPrice').innerHTML = data;
					document.getElementById('searchCode').innerHTML = stockCode;
					document.getElementById('searchResultTable').style.display = "initial";
					document.getElementById('searchFormerror').innerHTML = "";
				}
				return false;
			}
		}
		var stockCode = document.getElementById('stockCode').value;
		xhr.open('GET', 'PortfolioServlet?stockCode=' + stockCode, true);
		xhr.send();
	}

	function addToPortfolio() {
		console.log("inside add function");
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				var servletResponse = xhr.responseText;
				if (servletResponse === "success") {
					console.log("inside callback")
					/* $('#addToPortfolio').modal('hide'); */
					location.reload();
				}
				
				else {
					/* alert(servletResponse); */
					document.getElementById('searchFormerror').innerHTML = servletResponse;
					console.log("invalid"); 
				}
				/* document.getElementById('searchPrice').innerHTML = data;
				document.getElementById('searchCode').innerHTML = stockCode;
				document.getElementById('searchResultTable').style.display = "initial"; */
				return false;
			}
		}
		var stockCode = document.getElementById('searchCode').value;
	/* 	var currPrice = document.getElementById('searchPrice').innerHTML; */
		var numShares = document.getElementById('numShares').value;
		var buyInDate = document.getElementById('buyInDate').value;
		var sellDate = document.getElementById('sellDate').value;
		console.log(buyInDate);
		
		console.log("call")
		xhr.open('GET', 'PortfolioServlet?stockCode=' + stockCode
				+ "&numShares=" + numShares + "&buyInDate=" + buyInDate + "&sellDate=" + sellDate, true);
		xhr.send();
	}
	
	function addToWatchlist() {
		console.log("inside add watchlist function");
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				var servletResponse = xhr.responseText;
				/* var responseCheck = servletResponse.substring(0, 7); */
				console.log(servletResponse);
				if (servletResponse === "success") {
					console.log("inside callback")
					/* $('#addToPortfolio').modal('hide'); */
					/* var stockINFO = servletResponse.substring(8);
					console.log(stockINFO);
					var info = stockINFO.split(","); */
					location.reload();
				}
				
				else {
					/* alert(servletResponse); */
					document.getElementById('searchFormerror').innerHTML = servletResponse;
					console.log("invalid"); 
				}
				/* document.getElementById('searchPrice').innerHTML = data;
				document.getElementById('searchCode').innerHTML = stockCode;
				document.getElementById('searchResultTable').style.display = "initial"; */
				return false;
			}
		}
		var stockCode = document.getElementById('searchCode').value;
	/* 	var currPrice = document.getElementById('searchPrice').innerHTML; */
		var numShares = document.getElementById('numShares').value;
		var buyInDate = document.getElementById('buyInDate').value;
		var sellDate = document.getElementById('sellDate').value;
		console.log(buyInDate);
		
		console.log("call")
		xhr.open('GET', 'PortfolioServlet?stockCode=' + stockCode
				+ "&numShares=" + numShares + "&buyInDate=" + buyInDate + "&sellDate=" + sellDate + "&watchlist=yes", true);
		xhr.send();
	}
	
	var stockToDelete = "";
	var watchlistDelete = false;
	function removeStock() {
		/* var sentence = document.getElementById('deleteInformation').value;
		var stockCode = sentence.substring(1, 4); */
		console.log(stockToDelete);
		console.log("inside remove function");
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				var servletResponse = xhr.responseText;
				if (servletResponse === "success") {
					console.log("inside callback");
					
					// need to remove the stock from the invisible table for TESTING
					console.log(stockToDelete + " -> is deleted!!!!!");
					var hiddenTable = document.getElementById("hiddenGraphInfoTable");
					var found = false;
					
					for (var i = 0, row; row = hiddenTable.rows[i]; i++) {
					   //iterate through rows
					   //rows would be accessed using the "row" variable assigned in the for loop
					   for (var j = 0, col; col = row.cells[j]; j++) {
					     //iterate through columns
					     //columns would be accessed using the "col" variable assigned in the for loop
					     var str = col.innerHTML;
					     if(str.includes(stockToDelete)) {
						     console.log("FOUND!!! = " + str);
						     found = true;
						     document.getElementById("hiddenGraphInfoTable").deleteRow(row);
						     break;
					     }
					   }  
					   if(found) {
							break;   
					   }
					}
					
					location.reload();
				}
				else {
					document.getElementById('searchFormerror').innerHTML = servletResponse;
					console.log("invalid"); 
				}

				return false;
			}
		}
			console.log("call");
			var urlLink = "";
			if(watchlistDelete == false) {
				/* urlLink = 'PortfolioServlet?stockToDelete=' + stockToDelete; */
				xhr.open('GET', 'PortfolioServlet?stockToDelete=' + stockToDelete, true);
			}
			else if(watchlistDelete == true) {
				/* urlLink = 'PortfolioServlet?stockToDelete=' + stockToDelete + "&watchListDelete=yes"; */
				xhr.open('GET', 'PortfolioServlet?stockToDelete=' + stockToDelete + "&watchListDelete=yes", true);
			};
			/* console.log(urlLink) */
			/* xhr.open('GET', urlLink, true); */
			xhr.send();
		
	}
	
	
	function showAddToPortfolioButton() {
		document.getElementById('portfolioButton').style.display = "initial";
		document.getElementById('watchlistButton').style.display = "none";
		document.getElementById('confirmAddStockLabel').style.display = "initial";
		document.getElementById('confirmViewStockLabel').style.display = "none";
		document.getElementById('uploadCsvMessage').style.display = "none";
	}
	
	function showAddToWatchlistButton() {
		document.getElementById('portfolioButton').style.display = "none";
		document.getElementById('watchlistButton').style.display = "initial";
		document.getElementById('confirmAddStockLabel').style.display = "none";
		document.getElementById('confirmViewStockLabel').style.display = "initial";
		document.getElementById('uploadCsvMessage').style.display = "none";
	}
	
	function clearPrevUploadMessage() {
		document.getElementById('uploadCsvMessage').style.display = "none";
	}
	
	function updateDeleteCode(deleteStockID) {
		stockToDelete = deleteStockID;
		console.log(stockToDelete);
		var pre = "Are you sure you want to remove ";
		var after = " from your portfolio/watchlist ?"
		document.getElementById("deleteInformation").innerHTML = pre + stockToDelete + after;
		
		/* document.getElementById("deleteInformation").innerHTML = document.getElementById("deleteInformation").innerHTML.replace('deleteStockID', stockToDelete);
		 */
	}
	
	function updateWatchlistDeleteCode(deleteStockID) {
		stockToDelete = deleteStockID;
		console.log(stockToDelete);
		var pre = "Are you sure you want to remove ";
		var after = " from your portfolio/watchlist ?"
		document.getElementById("deleteInformation").innerHTML = pre + stockToDelete + after;
		watchlistDelete = true;
		
		/* document.getElementById("deleteInformation").innerHTML = document.getElementById("deleteInformation").innerHTML.replace('deleteStockID', stockToDelete);
		 */
	}

	$(document).ready(function(){
		$("#confirmAddStock").on('hidden.bs.modal', function() {
			document.getElementById('addStockForm').reset();
			document.getElementById('searchFormerror').innerHTML = "";
		});
	});
	
</script>
</head>
	<%
		session.setMaxInactiveInterval(120);
	
		if(session.getAttribute("loggedIn") != null) {
			/* System.out.println(session.getAttribute("loggedIn").toString()); */
			System.out.println("Logged In user detected!");
			boolean loggedIn = (boolean)session.getAttribute("loggedIn");
			if(!loggedIn) {
				response.sendRedirect("index.jsp");
			}
		}
		else{
			response.sendRedirect("index.jsp");
			System.out.println("User logged out!");
		}
	
	%>

<body>
	<nav class="navbar navbar-expand-lg navbar-light">
		<a class="navbar-brand text-white" href="#">USC CS310 Stock Portfolio Management</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><span class="nav-link text-white">Hi,
						${username} !</span></li>
				<li class="nav-item"><a class="nav-link text-white"
					href="#">Home <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item">
					<form id="logoutForm" action="LoginServlet" method="post">
					    <input id="logout_button" class="nav-link text-white" type="submit" name="logOut" value="Log Out" />
					</form>
				</li>
					
			</ul>
		</div>
	</nav>
	
	<div id="percentageChange">
		<table class="table table-striped" id="watchListTable">
				<thead>
					<tr>
						<th scope="col">Portfolio value</th>
						<th scope="col">Percentage change</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<tr>

						<td id="portfolioVal"></td>
						<td id="porPerChange"></td>
						<td id="triangle"></td>

					</tr>

				</tbody>
			</table>
	</div>
	
	<div id="precentageHelper" style="color:white;">
	</div>

	<div id="graph">
		<canvas id="stockLine"></canvas>
	</div>
	
	<div id="dateHelper" style="color:white; font-size: 1px;">
	</div>
	<%
		ArrayList<Stock> stockList = (ArrayList<Stock>) request.getAttribute("stockList");
		String stockToDelete = "";
		System.out.println(stockList.size());
		Portfolio my_por = (Portfolio)request.getAttribute("portfolio"); 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		String year = dtf.format(now).substring(0,4);
		String month = dtf.format(now).substring(5,7);
		String day = dtf.format(now).substring(8,10);
		int lastYear = Integer.parseInt(year) - 1;
		int threeMon = Integer.parseInt(month) - 3;
		String tMon = Integer.toString(threeMon);
		String startYear = Integer.toString(lastYear);
		String startDate = startYear + "-" + month + "-" + day;
		String endDate = year + "-" + month + "-" + day;
		/* System.out.println(startDate); 
		System.out.println(endDate); */
		
		ArrayList<Stock> watchList = (ArrayList<Stock>)request.getAttribute("watchList");
		if(watchList != null) {
			System.out.println("WatchList size at page is: " + watchList.size());
		}
		
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		Date earliestDay = sdformat.parse(endDate);
		
		String threeMonthAgo = year + "-" + tMon + "-" + day;
		Date threeMonth = sdformat.parse(threeMonthAgo);
		boolean bothEmpty = false;
		if(stockList.size() == 0 && watchList.size() == 0) {
			bothEmpty = true;
		}
		
		 /* find earliest buy in date */
		
		for(int i = 0; i < stockList.size(); i++) {
			Date curr = stockList.get(i).getBuyInDateinFormat();
			if(earliestDay.compareTo(curr) > 0) {
				/* earliestDay occurs after curr day */
				earliestDay = curr;
			}
		}
		for(int i = 0; i < watchList.size(); i++) {
			Date curr = watchList.get(i).getBuyInDateinFormat();
			if(earliestDay.compareTo(curr) > 0) {
				/* earliestDay occurs after curr day */
				earliestDay = curr;
			}
		}
		System.out.println("earliestDay is: " + earliestDay);
		
		
	%>
	
	<div id="hiddenGraphInfo">
		<table id="hiddenGraphInfoTable">
		  <thead>
			<tr>
				<th scope="col">Ticker Symbol</th>
				<th scope="col">Price 11/2/2020</th>
				<th scope="col">Current Price</th>
				<th scope="col">Color</th>
			</tr>
		  </thead>
		  <tbody>
		  </tbody>
		</table>
	</div>
	
	<div id="hiddenToggleStockInfo" style="display:none;"> 
		<div id="hiddenStockName"> </div>
		<div id="hiddenStockVisibility"> </div>
	</div>
	
	<div id="addRemoveDays">

		<button type="button" class="btn btn-primary" onclick="daysBack(1);"><i class="fa fa-plus" aria-hidden="true"></i></button>
		<button type="button" class="btn btn-primary" onclick="removeDays(1);"><i class="fa fa-minus" aria-hidden="true"></i></button>

	</div>
	
	<div id="addButtons">
		<button id="addToPortfolio" class="btn text-white" data-toggle="modal" data-target="#confirmAddStock" onclick="showAddToPortfolioButton();"> Add to My Portfolio </button>
		<button id="addToWatchlist" class="btn text-white" data-toggle="modal" data-target="#confirmAddStock" onclick="showAddToWatchlistButton();"> Add to Watchlist </button>
		<button id="uploadCSV" class="btn text-white" data-toggle="modal" data-target="#uploadCsvPopup" onclick="clearPrevUploadMessage();"> Upload CSV File </button>
		<p id="uploadCsvMessage">${csvSuccess}</p>
	</div>
	
	<!-- Pop-up for add/view stocks -->
	<div class="modal fade" id="confirmAddStock" tabindex="-1" role="dialog" aria-labelledby="confirmAddStockLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="confirmAddStockLabel">Add Stock</h5>
	        <h5 class="modal-title" id="confirmViewStockLabel">View Stock</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>	        
			</button>
	      </div>
	      <div class="modal-body">
	      
		      <form class="form-inline my-2 my-lg-0" action="MainPage.jsp" id="addStockForm">
				
				<div class="form-group">
					<label for="searchCode" class="col-form-label required">Ticker Symbol:</label>
					<input class="form-control mr-sm-2" type="text" id="searchCode" name="searchCode" required>
				</div>
				
				<div class="form-group">
					<label for="numShares" class="col-form-label required">Quantity:</label>
					<input class="form-control mr-sm-2" type="number" id="numShares" name="numShares" value="1" required>
				</div>
				
				<div class="form-group">
					<label for="buyInDate" class="col-form-label required">Date Purchase:</label>
					<input class="form-control mr-sm-2" type="date" id="buyInDate" name="buyInDate" required>
				</div>
				
				<div class="form-group">
					<label for="sellDate" class="col-form-label">Date Sold:</label>
					<input class="form-control mr-sm-2" type="date" id="sellDate" name="sellDate">
				</div>
				
			</form>
			<div id="searchFormerror" style="color: red;"></div>
			
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
	        <button type="button" class="btn btn-primary" id="portfolioButton" onclick="return addToPortfolio();">Add Stock</button>
	        <button type="button" class="btn btn-primary" id="watchlistButton" onclick="return addToWatchlist();">View Stock</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- Pop-up for CSV upload -->
	<div class="modal fade" id="uploadCsvPopup" tabindex="-1" role="dialog" aria-labelledby="uploadCsvPopupLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="uploadCsvPopupLabel">Upload CSV File</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>	        
			</button>
	      </div>
	      <div class="modal-body">
	      
		    <form class="pure-form pure-form-stacked" method="post" action="UploadFile"
		      enctype="multipart/form-data" id="uploadCsvForm">
			    <fieldset>
			        <input  type="file" name="myfile" accept=".csv" id="uploadSubmit">
			        <button type="submit" class="btn text-white" id="csvSubmitButton">Upload</button>
			    </fieldset>
			</form>
			
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
	        
	      </div>
	    </div>
	  </div>
	</div>

	<div id="tables" class="d-lg-flex">
		<div class="bootstrapTable col-lg-6 col-sm-10 mx-sm-auto">

			<h3 class="tableHeading">My Portfolio</h3>
			<table class="table table-striped" id="portfolioTable">
				<thead>
					<tr>
						<th scope="col">Ticker Symbol</th>
						<th scope="col">Purchase Price (USD)</th>
						<th scope="col">Quantity</th>
						<th scope="col">Date Purchase</th>
						<th scope="col">Date Sold</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<%	
						System.out.println(stockList.size());
						for (int i = 0; i < stockList.size(); i++) {
							String name = stockList.get(i).getStock_code();
							double initialPrice = stockList.get(i).getBuyInPrice();
							double shares = stockList.get(i).getNumShares();
							double buyInDate = stockList.get(i).getBuyInDate();
							
							String buyInDay = Double.toString(buyInDate);
							buyInDay = buyInDay.replace('E', '0');
							/* System.out.println(buyInDate); */
							/* 2.0201103E7 */
							/* System.out.println("buyInDate" + buyInDay.substring(7,9)); */
							buyInDay = buyInDay.substring(0,1) + buyInDay.substring(2,5) + "-" + 
									buyInDay.substring(5,7) + "-" + buyInDay.substring(7,9);
							/* System.out.println(buyInDay); */
							
							String sellDay = "none";
							double sellDate = stockList.get(i).getSellDate();
							/* System.out.println(sellDate); */
							
							if(sellDate > 0){
								sellDay = Double.toString(sellDate);
								sellDay = sellDay.replace('E', '0');
								sellDay = sellDay.substring(0,1) + sellDay.substring(2,5) + "-" + 
										sellDay.substring(5,7) + "-" + sellDay.substring(7,9);
							}
							/* System.out.println(sellDay); */
							
					%>
					<tr>
						<th scope="row"><%=name%></th>
						<td><%=initialPrice%></td>
						<td><%=shares%></td>
						<td><%=buyInDay%></td>
						<td><%=sellDay%></td>
						<td>
							<button class="removeStockButton" id="<%=name%>" data-toggle="modal" onclick="updateDeleteCode(this.id)" data-target="#confirmRemoveStock"> Delete Stock </button>
						</td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>

		<div class="bootstrapTable col-lg-6 col-sm-10 mx-sm-auto">
			<h3 class="tableHeading">Watchlist</h3>
			<table class="table table-striped" id="watchListTable">
				<thead>
					<tr>
						<th scope="col">Ticker Symbol</th>
						<th scope="col">Purchase Price (USD)</th>
						<th scope="col">Quantity</th>
						<th scope="col">Date Purchase</th>
						<th scope="col">Date Sold</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<%	
					if(watchList == null)
					{
						System.out.println("WANRING: WATCHLIST IS NULL!!!!!!!!!!!!!!!!!!");
					}
						System.out.println(watchList.size());
						for (int i = 0; i < watchList.size(); i++) {
							String name = watchList.get(i).getStock_code();
							double initialPrice = watchList.get(i).getBuyInPrice();
							double shares = watchList.get(i).getNumShares();
							double buyInDate = watchList.get(i).getBuyInDate();
							
							String buyInDay = Double.toString(buyInDate);
							
							buyInDay = buyInDay.replace('E', '0');
							
							buyInDay = buyInDay.substring(0,1) + buyInDay.substring(2,5) + "-" + 
									buyInDay.substring(5,7) + "-" + buyInDay.substring(7,9);
							/* System.out.println(buyInDay); */
							
							String sellDay = "none";
							double sellDate = watchList.get(i).getSellDate();
							/* System.out.println(sellDate); */
							
							if(sellDate > 0){
								sellDay = Double.toString(sellDate);
								sellDay = sellDay.replace('E', '0');
								sellDay = sellDay.substring(0,1) + sellDay.substring(2,5) + "-" + 
										sellDay.substring(5,7) + "-" + sellDay.substring(7,9);
							}
							/* System.out.println(sellDay); */
					%>
					<tr>
						<th scope="row"><%=name%></th>
						<td><%=initialPrice%></td>
						<td><%=shares%></td>
						<td><%=buyInDay%></td>
						<td><%=sellDay%></td>
						<td>
							<button class="removeStockButton" id="<%=name%>" data-toggle="modal" onclick="updateWatchlistDeleteCode(this.id)" data-target="#confirmRemoveStock"> Delete Stock </button>
						</td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
	
	<div class="modal fade" id="confirmRemoveStock" tabindex="-1" role="dialog" aria-labelledby="confirmRemoveStockLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="confirmRemoveStockLabel">Delete Stock</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>	        
			</button>
	      </div>
	      <div class="modal-body">
	        <h4 id="deleteInformation"> Are you sure you want to remove deleteStockID from "your portfolio/watchlist"?</h4>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
	        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="return removeStock();">Delete Stock</button>
	      </div>
	    </div>
	  </div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/chart.js@2.9.3/dist/Chart.min.js"></script>
	<!-- Bootstrap Scripts -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>

</body>


<script>

js_date = [];
js_spy = []


/* get S&P 500 data from api */
<%

DatePrice getDateInfo = Stock_api.stock_price_with_timerange("SPY", startDate,endDate);
List<String> spDate = getDateInfo.getDate();
List<Double> spStockPrice = getDateInfo.getPrice();
for(int j = 0; j < spDate.size(); j++) {

	String tempDate = spDate.get(j);
	/* System.out.println("date is "+ tempDate); */
	double spyPrice = spStockPrice.get(j);
	%>
	js_date.push(<%= tempDate %>); 
	js_spy.push(<%=spyPrice%>);
	<%
	/* System.out.println("js date is: " + tempDate); */
}
int defaultTimeRange = 1;


for(int i = spDate.size() - 1; i >= 0; i--) {
	String currDate = spDate.get(i);
	currDate = currDate.substring(1,11);
	/* System.out.println(currDate); */
	Date curr = sdformat.parse(currDate);
	if(curr.compareTo(earliestDay) > 0) {
		 /* curr occurs after earliest day */
		defaultTimeRange++;
     } 
}
if(bothEmpty == true) {
	
	for(int i = spDate.size() - 1; i >= 0; i--) {
		String currDate = spDate.get(i);
		currDate = currDate.substring(1,11);
		/* System.out.println(currDate); */
		Date curr = sdformat.parse(currDate);
		if(curr.compareTo(threeMonth) > 0) {
			 /* curr occurs after earliest day */
			defaultTimeRange++;
	     } 
	}
}
System.out.println("defaultTimeRange: " + defaultTimeRange);
%>

/* initialize graph dataset */
var defaultTimeRange = <%=defaultTimeRange%>;
console.log(defaultTimeRange);
var dateHelper = "";
function testDate(){
	timeStamp = [];
	for(var i = 1; i <= defaultTimeRange; i ++) {
		var currDate = js_date[js_date.length - i];
		timeStamp.unshift(currDate);
		dateHelper = currDate;
	}
	

return timeStamp;
};


var timeStamp;
var earliestDay = defaultTimeRange;

var newLegendClickHandler = function (e, legendItem) {
    var index = legendItem.datasetIndex;
	console.log("index = " + index);
	
	var meta = this.chart.getDatasetMeta(index);
	meta.hidden = meta.hidden === null ? !this.chart.data.datasets[index].hidden : null;

	this.chart.update();
	
	console.log(legendItem);
	
	//hiddenPortfolioTable / hiddenWatchlistTable
	document.getElementById("hiddenStockName").innerHTML = legendItem.text;
	document.getElementById("hiddenStockVisibility").innerHTML = legendItem.hidden;
};


	var stuff = []
	/* var timeFormat = 'DD/MM/YYYY'; */
	var ctx = document.getElementById('stockLine').getContext('2d');
	var chart = new Chart(ctx, {
		// The type of chart we want to create
		type : 'line',

		// The data for our dataset
		data : {
			labels: testDate(),
			datasets : []
		},

		options: {
			responsive: true,
			maintainAspectRatio : false,
			legend: {
				onClick: newLegendClickHandler
	        }
	    }
	});
	
	/* initialize portfolio dataset line */
	var porDataset = {
			label: 'Portfolio',
			borderColor : 'rgb(255, 165, 0)',
			fill: false,
			data: [],
			lineTension : 0.1
	}
	/* initialize S&P 500 dataset line */
	var spDataset = {
			label: 'S&P 500',
			borderColor : 'rgb(220, 32, 32)',
			fill: false,
			data: [],
			lineTension : 0.1
	}
	
	/* function to randomize colors */
	var colors = [];
	var dynamicColors = function() {
        var r = Math.floor(Math.random() * 255);
        var g = Math.floor(Math.random() * 255);
        var b = Math.floor(Math.random() * 255);
        return "rgb(" + r + "," + g + "," + b + ")";
    }; 
	for (var i = 0; i < <%=watchList.size()%>; i++) {
		colors.push(dynamicColors());
    }
	for (var i in colors) {
		console.log(colors[i]);
    }
	
	
	
	/* get watchlist stock price information from api calls and add to graph */
	<%
	int defulatStartDate = 0;
	defulatStartDate = spDate.size() - defaultTimeRange;
	System.out.println("stock defulatStartDate" + defulatStartDate);
	
	List<List<Double>> watchListStockPrice = new ArrayList<>();
	for (int i = 0; i < watchList.size(); i++) {
		String name = watchList.get(i).getStock_code();
		Double initialPrice = watchList.get(i).getBuyInPrice();
		
		DatePrice daily_open_price = Stock_api.stock_price_with_timerange(name, startDate,endDate);
		List<String> currStockDate = daily_open_price.getDate();
		List<Double> currStockPrice = daily_open_price.getPrice();
		
		List<Double> priceList = new ArrayList<>();
		for(int j = 0; j < currStockPrice.size(); j++) {
			priceList.add(currStockPrice.get(j));
			/* String tempDate = daily_open_price.get(j).getKey(); */
			%>
			<%-- js_date.push(<%= tempDate %>);  --%>
			<%
			/* System.out.println("js date is: " + tempDate); */
		}
		watchListStockPrice.add(priceList);
		
	%>
	
		/* initialize watchlist price line */
		var currStockDataset = {
				label: '<%= name%>',
				borderColor : colors[<%=i%>], 
				fill: false,
				data: [],
				lineTension : 0.1
		}
		
		// ADD TO HIDDEN TABLE
		var tbodyRef = document.getElementById('hiddenGraphInfoTable').getElementsByTagName('tbody')[0];

		// Insert a row at the end of table
		var newRow = tbodyRef.insertRow();

		// Insert a cell at the end of the row
		var newTickerCell = newRow.insertCell();
		var newPriceCell = newRow.insertCell();
		var newColorCell = newRow.insertCell();

		// Append a ticker symbol to the cell
		var newTicker = document.createTextNode('<%=name%>');
		newTickerCell.appendChild(newTicker);
		
		// Append the price on 11/2/2020 to the cell
		var newPrice = document.createTextNode(<%=initialPrice%>);
		newPriceCell.appendChild(newPrice);
		
		// Append the color of the line to the cell
		var newColor = document.createTextNode(colors[<%=i%>]);
		newColorCell.appendChild(newColor);
		
		<%
		
		/* dataSize = currStockPrice.size(); */
		for (int j = defulatStartDate; j < currStockPrice.size(); j++) {
			double currPrice = currStockPrice.get(j);
			/* System.out.println("stock price" + currPrice); */
			
		%>
		currStockDataset.data.push(<%=currPrice%>);
		<%
		}
		
		%>
		/* console.log(currStockDataset); */
		
		chart.data.datasets.push(currStockDataset);
	
	<%
	}
	%>
	
	/* calculate portfolio stock price and add to graph */
	<%

	List<List<Double>> portfolioStockPrice = new ArrayList<>();
	for (int i = 0; i < stockList.size(); i++) {
		String name = stockList.get(i).getStock_code();
		
		DatePrice daily_open_price = Stock_api.stock_price_with_timerange(name, startDate,endDate);
		List<String> currStockDate = daily_open_price.getDate();
		List<Double> currStockPrice = daily_open_price.getPrice();
		
		List<Double> currPriceList = new ArrayList<>();
		for(int j = 0; j < currStockPrice.size(); j++) {
			currPriceList.add(currStockPrice.get(j));
			/* String tempDate = daily_open_price.get(j).getKey(); */
			%>
			<%-- js_date.push(<%= tempDate %>);  --%>
			<%
			/* System.out.println("js date is: " + tempDate); */
		}
		portfolioStockPrice.add(currPriceList);
		
	%>
		/* console.log(currStockDataset);
		 */
		
	<%
	}
	%>
	js_portfolioPrice = [];

	/* calculate portfolio price */
	<%
	if(portfolioStockPrice.size() > 0) {
		for (int i = defulatStartDate; i < portfolioStockPrice.get(0).size(); i++) {
			double proPrice = 0;
			for (int j = 0; j < portfolioStockPrice.size(); j++) {
				int today = portfolioStockPrice.size() - 1;
				/* if before purchased or after sell date, not add*/
				Date currBD = stockList.get(j).getBuyInDateinFormat();
				Date currSD;
				double sellDateCheck = stockList.get(j).getSellDate();
				/* there is no sellDate */
				if(sellDateCheck == 0) {
					currSD = sdformat.parse(endDate);
				}
				else{
					currSD = stockList.get(j).getSellDateinFormat();
				}
				String currDate = spDate.get(i);
				currDate = currDate.substring(1,11);
				/* System.out.println(currDate); */
				Date curr = sdformat.parse(currDate);
				/* current date is after buy in date */
				if(curr.compareTo(currBD) >= 0 && currSD.compareTo(curr) >= 0) {
					 /* curr occurs after currBD day && sellday occurs after curr day*/
					double currPrice = portfolioStockPrice.get(j).get(i);
					double shares = stockList.get(j).getNumShares();
					/* System.out.println("curr stock price " + currPrice);
					System.out.println("shares " + shares); */
					proPrice += currPrice*shares;
					/* System.out.println(currDate); */
			     } 
				/* System.out.println(proPrice); */
				
			}
			/* System.out.println(proPrice); */
			%>
				porDataset.data.push(<%=proPrice%>);
				js_portfolioPrice.push(<%=proPrice%>);
			<%
		}
	}
	%>
	
	// ADD PORTFOLIO INFO TO HIDDEN TABLE
	var tbodyRef = document.getElementById('hiddenGraphInfoTable').getElementsByTagName('tbody')[0];

	// Insert a row at the end of table
	var newRow = tbodyRef.insertRow();

	// Insert a cell at the end of the row
	var newTickerCell = newRow.insertCell();
	var newPriceCell = newRow.insertCell();
	var newColorCell = newRow.insertCell();

	// Append a ticker symbol to the cell
	var newTicker = document.createTextNode("Portfolio");
	newTickerCell.appendChild(newTicker);
	
	// Append the price on 11/2/2020 to the cell
	var newPrice = document.createTextNode(porDataset.data[0]);
	newPriceCell.appendChild(newPrice);
	
	// Append the color of the line to the cell
	var newColor = document.createTextNode("rgb(255, 165, 0)");
	newColorCell.appendChild(newColor);
	
	chart.data.datasets.push(porDataset);
	
	/* add S&P 500 to graph */
	<%
	
	for (int i = defulatStartDate; i < spStockPrice.size(); i++) {
		double spPrice = spStockPrice.get(i);
		/* System.out.println(spPrice); */
		%>
		spDataset.data.push(<%=spPrice%>);
		<%
	}
	%>
	
	// ADD S&P 500 INFO TO HIDDEN TABLE
	// Insert a row at the end of table
	var newRow1 = tbodyRef.insertRow();

	// Insert a cell at the end of the row
	var newTickerCell1 = newRow1.insertCell();
	var newPriceCell1 = newRow1.insertCell();
	var newColorCell1 = newRow1.insertCell();

	// Append a ticker symbol to the cell
	var newTicker1 = document.createTextNode("S&P 500");
	newTickerCell1.appendChild(newTicker1);
	
	// Append the price on 11/2/2020 to the cell
	var newPrice1 = document.createTextNode(<%=spStockPrice.get(defulatStartDate)%>);
	newPriceCell1.appendChild(newPrice1);
	
	// Append the color of the line to the cell
	var newColor1 = document.createTextNode("rgb(220, 32, 32)");
	newColorCell1.appendChild(newColor1);
	
	chart.data.datasets.push(spDataset);
	/* update chart and date */
	document.getElementById('dateHelper').innerHTML = dateHelper;	
	chart.update();
	
	
	
	/* convert portfolioStock array into js array */
	js_portfolioStockPrice = [];
	/* calculate portfolio price */
	<%
	if(portfolioStockPrice.size() > 0) {
		for (int i = 0; i < portfolioStockPrice.get(0).size(); i++) {
			double proPrice = 0;
			for (int j = 0; j < portfolioStockPrice.size(); j++) {
				int today = portfolioStockPrice.size() - 1;
				/* if before purchased or after sell date, not add*/
				Date currBD = stockList.get(j).getBuyInDateinFormat();
				Date currSD;
				double sellDateCheck = stockList.get(j).getSellDate();
				/* there is no sellDate */
				if(sellDateCheck == 0) {
					currSD = sdformat.parse(endDate);
				}
				else{
					currSD = stockList.get(j).getSellDateinFormat();
				}
				String currDate = spDate.get(i);
				currDate = currDate.substring(1,11);
				/* System.out.println(currDate); */
				Date curr = sdformat.parse(currDate);
				/* current date is after buy in date */
				if(curr.compareTo(currBD) >= 0 && currSD.compareTo(curr) >= 0) {
					 /* curr occurs after currBD day && sellday occurs after curr day*/
					double currPrice = portfolioStockPrice.get(j).get(i);
					double shares = stockList.get(j).getNumShares();
					/* System.out.println("curr stock price " + currPrice);
					System.out.println("shares " + shares); */
					proPrice += currPrice*shares;
					/* System.out.println(currDate); */
			     } 
				/* System.out.println(proPrice); */
				
			}
			/* System.out.println(proPrice); */
			%>
			js_portfolioStockPrice.push(<%=proPrice%>);
			<%
		}
	}
	%>
	

/* convert portfolioStock array into js array */ 	js_portfolioStockPrice = []; 	<% for (int i=0; i<portfolioStockPrice.size(); i++) { %> 		js_stockPrice = []; 		<% for (int j=0; j<portfolioStockPrice.get(i).size(); j++) { %> 			js_stockPrice.push(<%= portfolioStockPrice.get(i).get(j) %>);  		<% } %> 		js_portfolioStockPrice.push(js_stockPrice); 	<% } %> 	 	js_stockShare = []; 	<% for (int j=0; j<stockList.size(); j++) { %> 		js_stockShare.push(<%= stockList.get(j).getNumShares() %>);  	<% } %> 
	
	
	/* convert watchlist array into js array */
	js_watchListStockPrice = [];
	<% for (int i=0; i<watchListStockPrice.size(); i++) { %>
		js_stockPrice = [];
		<% for (int j=0; j<watchListStockPrice.get(i).size(); j++) { %>
			js_stockPrice.push(<%= watchListStockPrice.get(i).get(j) %>); 
		<% } %>
		js_watchListStockPrice.push(js_stockPrice);
	<% } %>
	
	/* calculate portfolio precentage change */
	var porLength = js_portfolioPrice.length;
	if(porLength > 0){
		var porfolioCurrVal = js_portfolioPrice[porLength - 1];
		porfolioCurrVal = porfolioCurrVal.toFixed(2);
		document.getElementById('portfolioVal').innerHTML = porfolioCurrVal;
	}
	if(porLength > 1){
		var oldValue = js_portfolioPrice[porLength - 2];
		var porfolioDiff = js_portfolioPrice[porLength - 1] - js_portfolioPrice[porLength - 2];
		var porfolioPercentChange = porfolioDiff / js_portfolioPrice[porLength - 2];
		porfolioPercentChange = porfolioPercentChange*100;
		porfolioPercentChange = porfolioPercentChange.toFixed(2);
		document.getElementById('precentageHelper').innerHTML = oldValue;
		document.getElementById('porPerChange').innerHTML = porfolioPercentChange + "%";
		if(porfolioPercentChange >= 0) {
			document.getElementById('triangle').innerHTML = "&#9650";
			document.getElementById('triangle').style.color = "green";
			document.getElementById('porPerChange').style.color = "green";
		}
		else if(porfolioPercentChange < 0) {
			document.getElementById('triangle').innerHTML = "&#9660";
			document.getElementById('triangle').style.color = "red";
			document.getElementById('porPerChange').style.color = "red";
		}
	}

	
/* add date function */
	
	function daysBack(days){
/* 		console.log(days);
		
		console.log("earliestDay", earliestDay); */
		
		var targetDay = parseInt(earliestDay) + parseInt(days);
/* 		console.log("target", targetDay); */
		for(var i = earliestDay; i < targetDay; i++) {
			/* console.log("add days 1"); */
/*  			console.log("iterate through days", i);
			console.log("earliestDay", earliestDay); */
			var proPrice = 0;
			for(var j = 0; j < <%=watchList.size()%>; j++) {
 /* 				console.log("inside %%%% iterate through days", i);
				console.log("iterate through stocks"); 
				console.log(chart.data.datasets[j]);
				console.log(js_portfolioStockPrice[j].length);
				 */
				var reverseDate = js_watchListStockPrice[j].length - 1;
				var newPrice = js_watchListStockPrice[j][reverseDate - i];
				/* console.log("add the watchlist price", newPrice ," at ",j); */
				chart.data.datasets[j].data.unshift(newPrice);
				

				

			}
			var porLength = js_portfolioStockPrice.length - 1;
			proPrice = js_portfolioStockPrice[porLength - i];
			<%-- for(var j = 0; j < <%=stockList.size()%>; j++) {
				
				var reverseDate = js_portfolioStockPrice[j].length - 1;
				var newPrice = js_portfolioStockPrice[j][reverseDate - i];
				/* console.log("calculate the stock price", newPrice); */

				/*update por price  */
				
								
				var shares = js_stockShare[j];
				proPrice += newPrice*shares;
								
								

			} --%>
			/* console.log("dataset size", chart.data.datasets.length); 
			console.log("add curr porprice", proPrice ," at ",chart.data.datasets.length - 2);  */
			if(<%=stockList.size()%> > 0) {
				chart.data.datasets[chart.data.datasets.length - 2].data.unshift(proPrice);
			}
			
			var spPrice = js_spy[js_spy.length - 1 - i];
			chart.data.datasets[chart.data.datasets.length - 1].data.unshift(spPrice);
			
			var currDate = js_date[js_date.length - 1 - i];
			/* console.log("curr date", currDate);  */
			dateHelper = currDate;
			document.getElementById('dateHelper').innerHTML = dateHelper;
			chart.data.labels.unshift(currDate);
			
			
			
		}
		earliestDay = parseInt(earliestDay) + parseInt(days);

	    chart.update();
		};
		
		
		/* remove date function */
		function removeDays(days){
			 		/* console.log(days); */
					
					/* console.log("earliestDay", earliestDay); 
					var targetDay = 0;
					if(parseInt(days) > parseInt(earliestDay)) {
						targetDay = parseInt(earliestDay) - 1;
					}
					else{
						targetDay = parseInt(earliestDay) - parseInt(days);
					}
					
					
			 		console.log("target", targetDay);  */
			 if(earliestDay > 1) {
					for(var i = 0; i < days; i++) {

						for(var j = 0; j < <%=watchList.size()%>; j++) {
							/* console.log("remove watchlist day" ," at ",j);  */
							chart.data.datasets[j].data.splice(0, 1);


						}		
						/* console.log("remove portfolio day" ," at ",chart.data.datasets.length - 2);  */
						chart.data.datasets[chart.data.datasets.length - 2].data.splice(0, 1);
						/* console.log("remove sp 500 day" ," at ",chart.data.datasets.length - 1);  */
						chart.data.datasets[chart.data.datasets.length - 1].data.splice(0, 1);
						/* console.log("remove x-day");  */
						chart.data.labels.splice(0, 1);
						dateHelper = chart.data.labels[0];
						document.getElementById('dateHelper').innerHTML = dateHelper;
						
						
						
					}
					
					/* if(parseInt(days) > parseInt(earliestDay)) {
						earliestDay = 1;
					}
					else{
						earliestDay = parseInt(earliestDay) - parseInt(days);
					} */
					earliestDay = parseInt(earliestDay) - parseInt(days);


				    chart.update();
			 }
			};
			/* var meta = chart.getDatasetMeta(0);
			console.log(meta);  */
 

			
			
			
			// meta.data.length = number of days displayed on graph
			
</script>

</html>