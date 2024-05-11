Feature: Create Quiz functionality

	Scenario: Validate create quiz functionality for valid input
	
	Given User on Dashboard page
	When User clicks on Create Quiz button
	And User types Quiz name, selects quiz type and clicks on Continue button
	And User types question, selects option type, types options, select correct option, select timer
	And User clicks on Create Quiz button
	Then User gets the quiz link