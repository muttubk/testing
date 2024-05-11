package com.ibm.stepsToExecute;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Steps1 {
	
	private static boolean initialized = false;
	private WebDriver driver;
	
	@Before
	public void setUp() {
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
		if (!initialized) {
	         // initialize the driver
	         driver = new ChromeDriver();

	         initialized = true;
	      }
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	@Given("User on Login page")
	public void user_on_login_page() throws InterruptedException {
		driver.get("https://quizzie-front-end.vercel.app");
		
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
	
	@Given("User on Dashboard page")
	public void user_on_dashboard_page() {
		System.out.println("User on dashboard page");
	}

	@When("User clicks on Create Quiz button")
	public void user_clicks_on_create_quiz_button() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/button[3]")).click();
		System.out.println("User clicks create quiz button");
	}

	@And("User types Quiz name, selects quiz type and clicks on Continue button")
	public void user_types_quiz_name_selects_quiz_type_and_clicks_on_continue_button() {
		driver.findElement(By.className("CreateQuiz_quizNameInput__PbpBl")).sendKeys("Automation Testing quiz");
		driver.findElement(By.className("CreateQuiz_qnaTypeBtn__JrwKi")).click();
		driver.findElement(By.className("CreateQuiz_continueBtn__H-FQu")).click();
	}

	@And("User types question, selects option type, types options, select correct option, select timer")
	public void user_types_question_selects_option_type_types_options_select_correct_option_select_timer() {
		driver.findElement(By.className("CreateQuestion_question__8Yu+I")).sendKeys("What does CSS expands to?");

		driver.findElement(By.xpath("//*[@id=\"text\"]")).click();

		driver.findElement(By.name("option1")).sendKeys("Cascading Style Sheets");
		driver.findElement(By.name("option2")).sendKeys("Cascading Style Sheets");
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[3]/div[1]/div[1]/input[1]")).click();

		driver.findElement(By.className("Timer_timer10sec__DYV-y")).click();

		driver.findElement(By.className("CreateQuestion_createBtn__mYEdH")).click();
	}

	@Then("User gets the quiz link")
	public void user_gets_the_quiz_link() {
		WebElement e = driver.findElement(By.className("QuizPublished_linkContainer__3ptqs"));

		System.out.println("Quiz created and link is: " + e.getText());
	}
}
