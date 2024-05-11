package com.ibm.stepsToExecute;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Steps1 {
	
	private WebDriver driver;
	
	@Before
	public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://quizzie-front-end.vercel.app");
	}
		
	@Given("User on Login page")
	public void user_on_login_page() throws InterruptedException {
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/button[2]")).click();
		System.out.println("User on login page");
	}

	@When("User types email and password")
	public void user_types_email_and_password() throws InterruptedException {
		Thread.sleep(2000);
	    driver.findElement(By.name("email")).sendKeys("muttuk@test.com");
	    
	    Thread.sleep(2000);
	    driver.findElement(By.name("password")).sendKeys("muttu2002");
	}

	@When("User clicks login button")
	public void user_clicks_login_button() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/form/button")).click();
	    System.out.println("User logs in");
	}

	@Then("User lands on Dashboard page")
	public void user_lands_on_dashboard_page() {
	    String title = driver.getTitle();
	    System.out.println("User on page: " + title);
	}
	
	
	@After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
	
}
