package com.ibm.selenium_automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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


public class TestQuizApp {
  	static WebDriver driver;
  	
  	
//  	@Test
//  	public void testSignUp() throws InterruptedException {
//  		driver.findElement(By.name("name")).sendKeys("kk roy");
//  		driver.findElement(By.name("email")).sendKeys("kkroy@test.com");
//  		driver.findElement(By.name("password")).sendKeys("kkroy123");
//  		driver.findElement(By.name("confirmPassword")).sendKeys("kkroy123");
//  		Thread.sleep(2000);
//  		driver.findElement(By.className("LoginSignup_signupBtn__kloQI")).click();
//  	}
  
  	
  	static int iteration = 0;
  @Test(dataProvider = "loginData", enabled = true)
  public void testLogin(String email, String password) throws Exception {
//      driver.get("https://quizzie-front-end.vercel.app");

      Thread.sleep(1000);
      driver.findElement(By.className("LoginSignupPage_loginBtn__wHzCx")).click();
      
      driver.findElement(By.name("email")).clear();
      driver.findElement(By.name("password")).clear();
      
      Thread.sleep(1000);
      driver.findElement(By.name("email")).sendKeys(email);
		
      Thread.sleep(1000);
      
      driver.findElement(By.name("password")).sendKeys(password);
		
      File loginScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//      new Timestamp(System.currentTimeMillis());
      FileUtils.copyFile(loginScreenshot, new File("./Screenshots/loginScreenshot"+ String.valueOf(iteration++) +".jpg"));
      
      Thread.sleep(1000);
      driver.findElement(By.className("LoginSignup_loginBtn__Sw2o7")).click();

      Thread.sleep(1000);
      System.out.println(driver.getCurrentUrl());
      
      WebElement dashboardElement = driver.findElement(By.className("DashboardPage_dashboardContainer__uhTEc"));

      Assert.assertTrue(dashboardElement.isDisplayed(), "Login failed for user: " + email);
      createQuiz();
      
  }
  
  
  
  @DataProvider(name = "loginData")
  public Object[][] getLoginData() throws Exception {
	  String filePath = "C:/Users/MuttuKanabur/Documents/testing/testData.xlsx";
	  FileInputStream fin = new FileInputStream(new File(filePath));
	  XSSFWorkbook wb = new XSSFWorkbook(fin);
	  XSSFSheet sh = wb.getSheet("Sheet1");
	  
	  int rows = sh.getLastRowNum();
	  int cols = 2;
//	  int cols = sh.getRow(0).getLastCellNum();

	  Object[][] loginData = new Object[rows][cols];
	  
	  for(int i=0;i<rows;i++) {
		  Row row = sh.getRow(i + 1); // Skip header row
	        for (int j = 0; j < cols; j++) {
	            loginData[i][j] = row.getCell(j).getStringCellValue();
	        }
	  }
	 
	  return loginData;
  }
  
  
//  public Object[][] getQuizData() throws Exception{
//	  String filePath = "C:/Users/MuttuKanabur/Documents/testing/testData.xlsx";
//	  FileInputStream fin = new FileInputStream(new File(filePath));
//	  XSSFWorkbook wb = new XSSFWorkbook(fin);
//	  XSSFSheet sh = wb.getSheet("Sheet2");
//	  int rows = sh.getLastRowNum();
//	  int cols = 3;
//	  Object[][] QuizData = new Object[rows][cols];
//	  for(int i =0; i< rows; i++) {
//		  Row row = sh.getRow(i+1);
//		  for(int j = 0; j < cols; j++) {
//			  QuizData[i][j] = row.getCell(j).getStringCellValue();
//		  }
//	  }
//	  return QuizData;
//	  
//  }
  
  @Test(enabled = false)
  public void testData() throws IOException {
	  String filePath = "C:/Users/MuttuKanabur/Documents/testing/testData.xlsx";
	  FileInputStream fin = new FileInputStream(new File(filePath));
	  XSSFWorkbook wb = new XSSFWorkbook(fin);
	  XSSFSheet sh = wb.getSheet("Sheet2");
	  
	  int rows = sh.getLastRowNum();
	  int cols = sh.getRow(0).getLastCellNum();

	  Object[][] loginData = new Object[rows][cols];
	  
	  for(int i=0;i<rows;i++) {
		  Row row = sh.getRow(i + 1); // Skip header row
	        for (int j = 0; j < cols; j++) {
	            loginData[i][j] = row.getCell(j).getStringCellValue();
	            System.out.println(loginData[i][j]);
	        }
	  }
	  
  }
  
//  @Test(dataProvider = "QuizData", enabled = true)
  public void createQuiz() throws Exception {
//	  driver.get("https://quizzie-front-end.vercel.app");
//driver.findElement(By.className("LoginSignupPage_loginBtn__wHzCx")).click();
//      
//      driver.findElement(By.name("email")).clear();
//      driver.findElement(By.name("password")).clear();
//      
//      Thread.sleep(1000);
//      driver.findElement(By.name("email")).sendKeys("muttuk@test.com");
//		
//      Thread.sleep(1000);
//      
//      driver.findElement(By.name("password")).sendKeys("muttu2002");
//      Thread.sleep(1000);
//      driver.findElement(By.className("LoginSignup_loginBtn__Sw2o7")).click();

	  WebElement createQuizButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/button[3]"));
	  createQuizButton.click();
	  
	  driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/label/input")).sendKeys("nameOFQuiz");
	  Thread.sleep(1000);

	 
	  driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[1]/button[2]")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/button[2]")).click();
	 Object[][] newObj = getDataa();
	 String abc = (String) newObj[0][0];
	  driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/label/input")).sendKeys(abc);
	  driver.findElement(By.name("option1")).sendKeys(abc);
	  Thread.sleep(1000);
	  driver.findElement(By.name("option2")).sendKeys(abc);
	  driver.findElement(By.className("CreateQuestion_createBtn__mYEdH")).click();
	  String output = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/input")).getText();
	  System.out.println(output);
	  System.out.print(newObj[0][0]);
	  
//	  WebElement congratsMsg = driver.findElement(By.className("QuizPublished_message__ocWp2"));
//	  Assert.assertTrue(congratsMsg.isDisplayed(), "Not able to create quiz");
  }
  
  public Object[][] getDataa() throws Exception {
 
	  
	  String filePath = "C:/Users/MuttuKanabur/Documents/testing/testData.xlsx";
	  FileInputStream fin = new FileInputStream(new File(filePath));
	  XSSFWorkbook wb = new XSSFWorkbook(fin);
	  XSSFSheet sh = wb.getSheet("Sheet2");
	  int rows = sh.getLastRowNum();
	  int cols = 3;
	  Object[][] QuizData = new Object[rows][cols];
	  for(int i =0; i< rows; i++) {
		  Row row = sh.getRow(i+1);
		  for(int j = 0; j < cols; j++) {
			  QuizData[i][j] = row.getCell(j).getStringCellValue();
		  }
	  }
	  return QuizData;
  }
  
  @BeforeMethod
  public void setUp() throws IOException {
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45));
	  driver.get("https://quizzie-front-end.vercel.app");
  }

  @AfterMethod
  public void tearDown() throws InterruptedException {
      Thread.sleep(1000);
      driver.quit();
  }

}
