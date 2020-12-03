Feature: Logging In

	Scenario: Wrong Username Entered
		Given I am on the login page
		When I enter in a wrong username 
		And enter in a password 
		And I click the login button
		Then I should see an error message in regards to the wrong username entered
		
	Scenario: Wrong Password Entered
		Given I am on the login page1
		When I enter in the correct user name1
		And enter in a wrong password 
		And I click the login button1
		Then I should see an error message in regards to the wrong password entered 
	
	Scenario: Log in with correct Username and Password Entered
		Given I am on the login page2
		When I enter in a correct username2 
		And enter in a correct password 
		And I click the login button2
		Then I should be successfully logged in
	
	Scenario: Nothing entered in inputs 
		Given I am on the login page3
		When I leave the input field for username empty 
		And I leave the input field for password empty 
		And I click the login button3
		Then an error should be displayed in regards to the input fields left blank 
		
	Scenario: Nothing entered for User name 
		Given I am on the login page4
		When I leave the input field for username empty4 
		And I type in a password
		And I click the login button4
		Then an error should be displayed in regards to the input field left blank
	
	Scenario: Nothing entered for Password 
		Given I am on the login page5
		When I type in a user name 
		And I leave the input field for password empty5 
		And I click the login button5
		Then an error should be displayed in regards to the input fields left blank5 
	
	Scenario: Locking out user after 3 failed login attempts
		Given I am on the login page6
		When I fail to login 3 times within a minute
		Then the user should be locked from logging in for a min
	
	Scenario: Locked out after 5 login attempts with 1 minute pause
		Given I am on the login page7
		When I fail to login two times within a minute
		And wait for a minute
		And log in with wrong information 3 times
		Then the user should be locked from logging in for a min7
	
	Scenario: Logging in with correct login info but still locked
		Given I am on the login page8
		When I fail to login 3 times within a minute8
		And log in with correct log in information
		Then the user should still be locked from logging in8
	
	Scenario: logging in successfully after being locked for a min 
		Given I am on the login page9
		When I fail to login three times within a minute
		And a minute passes by
		And I login correctly the fourth time
		Then the user should be logged in
	
	Scenario: logging in user2 while user1 is locked
		Given I am on the login page11
		When user1 is locked from logging in
		And user2 logs in correctly
		Then user2 should be logged in while user1 is still locked

		