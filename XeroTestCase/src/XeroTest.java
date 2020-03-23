import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class XeroTest extends ReusableMethods {
	
	@BeforeClass
	@Parameters("browserName")
	public void Initialize(String browserName) {
		System.out.println("Browser Name is " +browserName);
		System.out.println("Thread is " + Thread.currentThread().getId());
		
		
		if(browserName.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			System.out.println("success");
		}
		else if(browserName.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		else if(browserName.equals("ie")) {
			WebDriverManager.iedriver().setup();
		driver=new InternetExplorerDriver();
		}
		CreateReport();
	}
	
	@Test(description = "Login to the xero website")
	public void TC_ID01A_Navigate_To_Xero() {
		logger = report.startTest("TC_ID01A_Navigate_T0_Xero");
		
		OpenUrl("https://login.xero.com/us/");
		PageTitle("Login | Xero Accounting Software", driver.getTitle(), "Home Page");
		
		WebElement Uname = driver.findElement(By.xpath("//input[@id='email']"));
		EnterText(Uname, "srujani@gmail.com", "Username");
		
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		EnterText(pwd, "26SQ7*uVr&qnw", "Password");
		WebElement loginBtn = driver.findElement(By.xpath("//*[@id='submitButton']"));
		Click(loginBtn, "Login Button");
		PageTitle("Home Page ~ Salesforce - Developer Edition", driver.getTitle(), "Home Page");	
	}
	@AfterClass
	public void CloseTest() {
		CloseBrowser();
		CloseReport();
	}

}