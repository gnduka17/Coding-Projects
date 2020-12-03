Feature: CSV Parser

	Scenario: Upload CSV file
		Given I am on the main page500
		When I upload a CSV file that is formatted correctly
		Then I should see a successful display
		
	Scenario: Upload CSV file with duplicate stock
		Given I am on the main page501
		When I upload a CSV file that is already added
		Then I should see a duplicate stock display
		
	Scenario: Upload CSV file with an invalid buy in date
		Given I am on the main page502
		When I upload a CSV file with an invalid buy in date
		Then I should see an invalid date display
	
	Scenario: Upload CSV file that is not formatted correctly
		Given I am on the main page503
		When I upload a CSV file with an invalid format
		Then I should see a format error display
		
	Scenario: Upload CSV file with the dates being the same
		Given I am on the main page504
		When I upload a CSV file with the same dates
		Then I should see an invalid date display3
		
	Scenario: Upload CSV file with invalid sell in date
		Given I am on main page505
		When I upload a CSV file with an invalid sell in date
		Then I should see an invalid date display2
		
	Scenario: Upload a CSV file with nonexistent stock
		Given I am on main page506
		When I upload a CSV file with invalid stock
		Then I should see a stock error display