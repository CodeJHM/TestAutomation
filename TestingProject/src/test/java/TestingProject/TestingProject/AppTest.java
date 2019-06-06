package TestingProject.TestingProject;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
			waitTime(By.xpath("//a[@class='an_a mn_blog']"));
			driver.findElement(By.xpath("//a[@class='an_a mn_blog']")).click();
			Assert.assertTrue(driver.getTitle().contains("블로그 홈"));
	    }
	    
	    @Test (priority=3)
	    public void nextPage() throws InterruptedException {
	    	waitTime(By.xpath("//a[@class='sp_common logo_naver']"));
	    	WebElement element = driver.findElement(By.xpath("//a[@class='sp_common logo_naver']"));
	    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
	    	waitTime(By.className("button_next"));
	    	driver.findElement(By.className("button_next")).click();
	    	//driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    	Thread.sleep(2000);
	    	Assert.assertEquals(driver.findElements(By.xpath("//a[@ng-if='currentPage==page']")).get(0).getText(), "11");
	    }
	    
	    @Test (priority=4) 
	    public void categoryMove() throws InterruptedException {
	    	waitTime(By.className("category_name"));
	    	List<WebElement> elements;
	    	elements = driver.findElements(By.className("category_name"));
	    	String threeCategory = elements.get(3).getText();
	    	elements.get(3).click();
	    	Thread.sleep(2000);
	    	String selectCategory = driver.findElements(By.xpath("//a[@aria-selected='true']")).get(1).getText();
	    	Assert.assertEquals(threeCategory, selectCategory);
	    }
	    
	    @Test (priority=5)
	    public void monthBlog() throws InterruptedException {
	    	waitTime(By.linkText("이달의 블로그"));
	    	driver.findElement(By.linkText("이달의 블로그")).click();
	    	Thread.sleep(2000);
	    	String nowTitle = driver.findElement(By.xpath("//div[@class='heading']//h2")).getText();
	    	Calendar oCalendar = Calendar.getInstance(); 
	    	int iYear = oCalendar.get(oCalendar.YEAR);
	    	int iMonth = oCalendar.get(oCalendar.MONTH);
	    	Assert.assertEquals(nowTitle, iYear + "년 " + iMonth + "월, 이달의 블로그");
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
		
		public void waitTime(By _path) {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(_path));
		}
}	