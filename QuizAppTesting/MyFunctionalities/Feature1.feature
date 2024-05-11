Feature: Login functionality

	Scenario: Validate login functionality for valid credentials
	
	Given User on Login page
	When User types email and password
	And User clicks login button
	Then User lands on Dashboard page
	