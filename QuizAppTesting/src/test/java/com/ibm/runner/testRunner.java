package com.ibm.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"MyFunctionalities/Feature1.feature", "MyFunctionalities/Feature2.feature"},
		
		glue = {"com.ibm.stepsToExecute"}
		)


public class testRunner {
	
}
