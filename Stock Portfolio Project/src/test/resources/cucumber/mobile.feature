Feature: Mobile 

	Scenario: Login Box Doesn't spill Over with Main Index Page 
		Given I am on the mobile login page80
		When I get the dimensions of the login Box and of the main Page80
		Then the login box isnt spilling over  with the main page80
		
	Scenario: Login Box Doesn't overlap with banner
		Given I am on the mobile login page800
		When I get the dimensions of the login Box and of the banner800
		Then the login box and banner shouldn't overlap one another800
		
	Scenario: Login Inputs don't overlap with Login Box and each other
		Given I am on the mobile login page81
		When I get the dimensions of the login inputs and of the Box81
		Then the login inputs dont overlap with each other and with the box81
	
	Scenario: Register Box Doesn't spill Over with Main Index Page 
		Given I am on the mobile register page82
		When I get the dimensions of the register Box and of the main Page82
		Then the register box isnt spilling over with the main page82
		
	Scenario: Register Inputs don't overlap with Register Box and each other
		Given I am on the mobile register page83
		When I get the dimensions of the register inputs and of the Box83
		Then the register inputs dont overlap with each other and with the box83
		
	
	Scenario: Banner elements aren't overlapping each other
		Given I am on the main portfolio page85
		When I get the dimensions of the hamburger menu and of the banner
		Then the banner elements aren't overlapping with each other
		
	Scenario: None of the buttons overlap each other 
		Given I am on the main portfolio page87
		When I get the locations of the buttons87
		Then the buttons on the portfolio pages do not overlap87
		
		 
	Scenario: Portfolio and watchlist tables do not overlap
		Given I am on the main portfolio page89
		When I get the locations and dimensions of the portfolio and watchlist tables89
		Then the two tables do not overlap with one another89
		
	Scenario: Portfolio column Labels do not overlap
		Given I am on the main portfolio page90
		When I get the x and y locations of the labels in portfolio90
		Then the column labels do not overlap90
	
	Scenario: Watchlist column Labels do not overlap
		Given I am on the main portfolio page91
		When I get the x and y locations of the labels in watchlist91
		Then the column labels do not overlap91
		
	Scenario: Graph auto populates and fits the page without any overlapping
		Given I am on the main portfolio page92
		When I get the x and y locations of the graph92
		Then the graph doesnt spill past the main page92
		
	Scenario: Adding to watchlist popup doesnt cut off 
		Given I am on the main portfolio page93
		When I click add to watchlist button93
		And get the x and y locations of the popup and main page93
		Then the popup doesnt cut off93
		
	Scenario: Adding to watchlist popup buttons do not overlap with each other and the box
		Given I am on the main portfolio page95
		When I click add to watchlist button95 
		And get the x and y locations of the buttons and inputs in the popup95
		Then the popup inputs and buttons dont overlap each other and with the box95
		
	Scenario: Adding to portfolio popup doesnt cut off
		Given I am on the main portfolio page96
		When I click add to portfolio button96
		And get the x and y locations of the popup and main page96
		Then the popup doesnt cut off96
	
	Scenario: Adding to portfolio popup buttons do not overlap with each other and the box
		Given I am on the main portfolio page98
		When I click add to portfolio button98 
		And get the x and y locations of the buttons and inputs in the popup98
		Then the popup inputs and buttons dont overlap each other and with the box98
		
	Scenario: each field in portfolio table dont overlap with each other
		Given I am on the main portfolio page99
		When I get the x and y locations of the first and second fields of the portfolio table99
		Then the fields dont overlap with each other99
		
	Scenario: each field in watchlist table dont overlap with each other
		Given I am on the main portfolio page100
		When I get the x and y locations of the first and second fields of the watchlist table100
		Then the fields dont overlap with each other100
	
	Scenario: The second banner with values dont overlap with graph
		Given I am on the main portfolio page1000
		When I get the x and y locations of the graph and the second banner1000
		Then the fields dont overlap with each other1000
		
	
		