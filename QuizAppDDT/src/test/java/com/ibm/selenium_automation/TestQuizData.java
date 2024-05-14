package com.ibm.selenium_automation;
//package com.ibm.selenium_automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class TestQuizData {
	static WebDriver driver;
	  
	static int iteration = 0;
	
	  @Test(dataProvider = "loginData", enabled = true)
	  public void testLogin(String email, String password) throws Exception {

	      Thread.sleep(1000);
	      driver.findElement(By.className("LoginSignupPage_loginBtn__wHzCx")).click();
	      
	      driver.findElement(By.name("email")).clear();
	      driver.findElement(By.name("password")).clear();
	      
	      Thread.sleep(1000);
	      driver.findElement(By.name("email")).sendKeys(email);
			
	      Thread.sleep(1000);
	      
	      driver.findElement(By.name("password")).sendKeys(password);
			

	      File loginScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//	      new Timestamp(System.currentTimeMillis());
	      FileUtils.copyFile(loginScreenshot, new File("./Screenshots/loginScreenshot"+ String.valueOf(iteration++) +".jpg"));
	      
	      Thread.sleep(1000);
	      driver.findElement(By.className("LoginSignup_loginBtn__Sw2o7")).click();

	      Thread.sleep(1000);
	      System.out.println(driver.getCurrentUrl());
	      
	      WebElement dashboardElement = driver.findElement(By.className("DashboardPage_dashboardContainer__uhTEc"));
	     
	      
	      Assert.assertTrue(dashboardElement.isDisplayed(), "Login failed for user: " + email);
	      
	      String filePath = "C:/Users/MuttuKanabur/Documents/testing/testData.xlsx";
		  FileInputStream fin = new FileInputStream(new File(filePath));
		  XSSFWorkbook wb = new XSSFWorkbook(fin);
		  XSSFSheet sh = wb.getSheet("Sheet2");
		  
		  int rows = sh.getLastRowNum();
		  int cols = sh.getRow(0).getLastCellNum();

		  Object[][] quizData = new Object[rows][cols];
		  
		  WebElement createQuizButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/button[3]"));
		  createQuizButton.click();
		  Thread.sleep(1000);
		  driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/label/input")).sendKeys("nameOFQuiz");
		  Thread.sleep(1000);
		  driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[1]/button[2]")).click();
		  Thread.sleep(1000);
		  driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/button[2]")).click();
		  
		  for(int i=0;i<rows;i++) {
			  Row row = sh.getRow(i + 1); // Skip header row
			  String qn = row.getCell(0).getStringCellValue();
			  String op1 = row.getCell(1).getStringCellValue();
			  String op2 = row.getCell(2).getStringCellValue();
//			  createQuiz(qn, op1, op2);
			  Thread.sleep(1000);
			  driver.findElement(By.className("CreateQuestion_question__8Yu+I")).sendKeys(qn);
			  Thread.sleep(1000);
			  driver.findElement(By.name("option1")).sendKeys(op1);
			  Thread.sleep(1000);
			  driver.findElement(By.name("option2")).sendKeys(op2);
			  
		      if(i==rows-1) {
		    	  driver.findElement(By.className("CreateQuestion_createBtn__mYEdH")).click();
//				  
		    	  Thread.sleep(1000);
		    	  
		    	  File newlastScreenScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			      FileUtils.copyFile(newlastScreenScreenshot, new File("./Screenshots/finalScreenshot"+ String.valueOf(i+1) +".jpg"));
		    	  
				  WebElement congratsMsg = driver.findElement(By.className("QuizPublished_message__ocWp2"));

				  Assert.assertTrue(congratsMsg.isDisplayed(), "Not able to create quiz");
		      }
		      else {
		    	  File newlastScreenScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			      FileUtils.copyFile(newlastScreenScreenshot, new File("./Screenshots/finalScreenshot"+ String.valueOf(i+1) +".jpg"));
			      
		    	  driver.findElement(By.className("CreateQuestion_addQuestionBtn__87AJr")).click();				  
		      }
		      Thread.sleep(1000);
		  }
	      
	  }
	  
	  
	 
	  @DataProvider(name = "loginData")
	  public Object[][] getLoginData() throws Exception {
		  String filePath = "C:/Users/MuttuKanabur/Documents/testing/testData.xlsx";
		  FileInputStream fin = new FileInputStream(new File(filePath));
		  XSSFWorkbook wb = new XSSFWorkbook(fin);
		  XSSFSheet sh = wb.getSheet("Sheet1");
		  
		  int rows = sh.getLastRowNum();
		  int cols = 2;
//		  int cols = sh.getRow(0).getLastCellNum();

		  Object[][] loginData = new Object[rows][cols];
		  
		  for(int i=0;i<rows;i++) {
			  Row row = sh.getRow(i + 1); // Skip header row
		        for (int j = 0; j < cols; j++) {
		            loginData[i][j] = row.getCell(j).getStringCellValue();
		        }
		  }
		  
		  return loginData;
	  }
	  
	  
	  
//	  public void createQuiz(String q, String op1, String op2) {
//		  
//		  
//		  driver.findElement(By.className("CreateQuestion_question__8Yu+I")).sendKeys(q);
//		  driver.findElement(By.name("option1")).sendKeys(op1);
//		  driver.findElement(By.name("option2")).sendKeys(op2);
//	  }
	  
	  
	  
	  @BeforeMethod
	  public void setUp() throws IOException {
		  driver = new ChromeDriver();
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  driver.get("https://quizzie-front-end.vercel.app");
	  }

	  @AfterMethod
	  public void tearDown() throws InterruptedException {
	      Thread.sleep(1000);
	      driver.quit();
	  }

}
