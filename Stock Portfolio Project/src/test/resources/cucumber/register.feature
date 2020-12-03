Feature: Registering a New User

	Scenario: Username already taken 
		Given I am on the register page0
		When I enter in a username that is already taken 
		And enter in a password0
		And enter in the same password
		And click the Create User button0 
		Then I should see an error message in regards to the username already taken
	
	
	Scenario: Both Passwords don’t match
		Given I am on the register page2
		When I enter in a correct username 
		And enter in a password in the first input field 
		And enter in a different password in the second input field 
		And click the Create User button2 
		Then I should see an error message in regards to the passwords not matching
	
	
	Scenario: Successful Registration 
		Given I am on the register page3
		When I enter in a correct username3 
		And enter in a password that meets all requirement in the first input field 
		And enter in the same password as in the first in the second input field 
		And click the Create User button3 
		Then I should be successfully registered 
	
	
	Scenario: Nothing entered in inputs 
		Given I am on the register page4
		When I don’t enter anything in username input field
		And  I don’t enter anything in both password input fields 
		And click the Create User button4 
		Then I should be given an error in regards to nothing being entered 
	
	
	Scenario: only 1 password entered in 1st input field  
		Given I am on the register page5
		When I enter in a user name in the input field5
		And  I enter in a password in the first input field
		And not enter anything in the 2nd password input field 
		And click the Create User button5 
		Then I should be given an error in regards to nothing being entered in the second input field
	
	
	Scenario: only 1 password entered in 2nd input field  
		Given I am on the register page6
		When I enter in a user name in the input field6
		And  I enter in a password in the 2nd password input field
		And not enter anything in the 1st password input field 
		And click the Create User button6 
		Then I should be given an error in regards to nothing being entered in the first input field
	
	
	Scenario: No password entered 
		Given I am on the register page7
		When I enter in a user name in the input field
		And  I don’t enter in a password in the 1st password input field
		And don’t enter anything in the 2nd password input field 
		And click the Create User button7 
		Then I should be given an error in regards to a password not being entered
		
	
	Scenario: Empty User name 
		Given I am on the register page8
		When I don’t enter in a user name in the input field
		And  I enter in a password in the 1st password input field
		And enter the same password in the 2nd password input field 
		And click the Create User button 
		Then I should be given an error in regards to a user name not being entered