Feature: Page Structure/Page Flow
	Scenario: After Registration send back to Login Page 
		Given I am on the register page 
		When I enter in a successful username 
		And enter in successful passwords in both input fields 
		And click on Create User button 
		Then I should be directed to the login page
	
	Scenario: After login successfully
		Given I am on the Login page1 
		When I enter in the correct user name 
		And enter in the correct password for that user name 
		And click on Login button 
		Then I should be directed to the main page
	
	Scenario: Clicking Create User button
		Given I am on the Login page 
		When I click on the Create User button 
		Then I should be directed to the register page
		
	Scenario: After clicking Logout
		Given I am on the main page
		When I click the Logout link
		Then I should be redirected to the login page
	
	Scenario: Canceling Registration
		Given I am on the register page10
		When I click the Cancel link
		Then I should be redirected to the login page10
		
	Scenario: Accessing Main without Logging In
		Given I am on the login page21
		When I access the Main portfolio page without logging in
		Then I should be redirected to the login page21
	
	Scenario: Redirecting to the login page after inactivity
		Given I am on the main page22
		When I dont move my mouse or press any keys
		Then I should be redirected to the login page22

	
		
	
	
	
	
	
	
	