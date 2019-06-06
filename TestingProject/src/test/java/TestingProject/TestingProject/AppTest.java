package TestingProject.TestingProject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;		
import org.testng.Assert;		
import org.testng.annotations.Test;	
import org.testng.annotations.BeforeTest;	
import org.testng.annotations.AfterTest;

public class AppTest {		
	    private WebDriver driver;		
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	    
	    @Test (priority=1)				
		public void getOpen() {	
			driver.get("http://www.naver.com");  
			String name = "NAVER";
			Assert.assertEquals(driver.getTitle(), name);
			driver.manage().window().maximize();
		}
	    
	    @Test (priority=2)
	    public void moveBlog() throws InterruptedException {
	    	Thread.sleep(2000);
	    	driver.findElement(By.xpath("//a[@class='an_a mn_blog']")).click();
	    	Thread.sleep(2000);
	    	WebElement element = driver.findElement(By.xpath("//a[@class='sp_common logo_naver']"));
	    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
	    	Thread.sleep(2000);
	    	driver.findElement(By.className("button_next")).click();
	    	Thread.sleep(2000);
	    }
	    
		@BeforeTest
		public void beforeTest() {	
			
			String Web_Driver_ID = "webdriver.chrome.driver";
			String Web_Driver_PATH = "C:/driver/chromedriver.exe";
			
			System.setProperty(Web_Driver_ID, Web_Driver_PATH);
			
		    driver = new ChromeDriver();  
		}		
		@AfterTest
		public void afterTest() {
			driver.quit();			
		}		
}	