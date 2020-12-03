Feature: Portfolio Main Page

	Scenario: Updating the timeframe of the graph 
		Given I am on the main page39
		When I click on the increase button
		Then the date should be increased by 1
	
	Scenario: Updating the timeframe of the graph remove days 
		Given I am on the main page45
		When I click on the remove days button
		Then the date should be decremented by 1
		
	Scenario: searching stock that doesnt exist 
		Given I am on the main page40 
		When I enter in a stock ticker that doesnt exist
		Then I should see an error message
		
	Scenario: searching an empty stock 
		Given I am on the main page30 
		When I click search with empty field
		Then I should see an error message30
		
	Scenario: Adding stock to portfolio
		Given I am on the main page33 
		When I enter in a stock ticker that exists33
		And click the add to portfolio button33
		Then the stock should be added to portfolio33
		
	Scenario: Adding stock that already exists in portfolio 
		Given I am on the main page34 
		When I enter in a stock ticker that exists in the portfolio
		And click the add to portfolio button34
		Then I should see an error message34
	
	Scenario: Entering an invalid number of shares
		Given I am on the main page35
		When I enter in a correct tickr35
		And enter in a negative number of shares
		Then I should see an error message35
		
	Scenario: Deleting stock from watchList
		Given I am on the main page36
		When I click on remove stock button on WL
		And click delete stock in the popUp
		Then the stock is removed from the graph and WL
		
	Scenario: Adding stock to watchList 
		Given I am on the main page38
		When I click on add to Watchlist button
		And enter in credentials correctly 
		And click add stock button
		Then the WL should have that new stock inserted
		
	Scenario: Deleting stock from Portfolio
		Given I am on the main page44
		When I click on remove Button
		Then the stock should be removed from the portfolio
		
	Scenario: Displaying stock on graph via toggle 
		Given I am on the main page41
		When I click on the display toggle of the stock
		Then the stock should be displayed on graph41 
	
	Scenario: Not displaying stock on graph via toggle 
		Given I am on the main page43
		When I click on the not display toggle of the stock43
		Then the stock should not be displayed on graph43 
	
	Scenario: Adding stock that already exists in watchList
		Given I am on the main page42
		When I enter in a stock ticker that exists in the watchList
		And click the view stock button34
		Then I should see an error message42
		
	Scenario: clicking Cancel resets the popUp form
		Given I am on the main page47
		When I fill out the popUp form
		And click cancel
		And access the popup form again
		Then the form should be cleared
	
	Scenario: searching stock that doesnt exist in watchlist 
		Given I am on the main page50
		When I enter in a stock ticker that doesnt exist in watchlist
		Then I should see an error message50
	
	Scenario: searching an empty stock in watchlist 
		Given I am on the main page51 
		When I click search with empty field in WL
		Then I should see an error message51
		
	Scenario: Adding stock that already exists in watchList 
		Given I am on the main page52
		When I enter in a stock ticker that exists in the WL
		And click the add to watchList button52
		Then I should see an error message52
	
	Scenario: Entering an invalid number of shares for WL
		Given I am on the main page53
		When I enter in a correct tickr for WL53
		And enter in a negative number of shares53
		Then I should see an error message53
	
	Scenario: Buy date is before Sell date in Portfolio
		Given I am on the main page54
		When I enter in a correct tickr for portfolio54
		And enter in a sell date that is before buy date
		Then I should see an error message54
	
	Scenario: Buy date is before Sell date in WL
		Given I am on the main page55
		When I enter in a correct tickr for WL55
		And enter in a sell date that is before buy date55
		Then I should see an error message55
	
	Scenario: The earliest buy date is a year from today Portfolio 
		Given I am on the main page56
		When I enter in a correct tickr for portfolio56
		And enter in a buy date that is not within a year from today
		Then I should see an error message56
	
	Scenario: The earliest buy date is a year from today WL 
		Given I am on the main page560
		When I enter in a correct tickr for wl560
		And enter in a buy date that is not within a year from today560
		Then I should see an error message560
	
	Scenario: ensure that button says "View Stock" when adding to WL
		Given I am on the main page57
		When I click on add to watchList button
		Then I should see the the "View Stock" button57

	Scenario: ensure that button says "Add Stock" when adding to Portfolio
		Given I am on the main page58
		When I click on add to portfolio button
		Then I should see the the "Add Stock" button
    
	Scenario: each stock has a different color on the graph
		Given I am on the main page48
		When the graph is displayed with different stocks
		Then each stock should be in a different color
	
	Scenario: Portfolio value is accurate and displayed 
		Given I am on the main page49
		When I add a stock to portfolio49
		Then the portfolio value should be displayed49
		
	Scenario: Percent change of portfolio is displayed and accurate 
		Given I am on the main page120
		When I get the value of portfolio from day before and value today120
		Then the percent change is accurately displayed in portfolio
		
	Scenario: indicator triangle points upward
		Given I am on the main page121
		When I get the value of portfolio from day before and value today121
		Then the indicator triangle is pointing upwards in portfolio
		
	Scenario: text is red for negative 
		Given I am on the main page123
		When I get the value of portfolio from day before and value today123
		Then the text is red in portfolio if negative123

	Scenario: ensuring that graph automatically changes when deleted or added in WL
		Given I am on the main page610
		When I add in a correct stock610
		And click the add stock button610
		Then the graph should dynamically change610
	
		
	
		
	
	
		