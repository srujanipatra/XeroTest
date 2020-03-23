import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReusableMethods {
	
	static ExtentTest logger;
	static ExtentReports report;
	static WebDriver driver;
	

	
	public static void InitializeDriver()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();	
	}
	
	public static void CreateReport() {
		String fileName = new SimpleDateFormat("'Xero_'YYYYMMddHHmm'.html'").format(new Date());
		String path = "C:\\Users\\sruja\\Selenium-workspace\\Log File\\" + fileName;
		report = new ExtentReports(path);
	}
	
	public static void OpenUrl(String url)
	{
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	public static void PageTitle(String acttitle, String exptitle, String message) {

		if (acttitle.equalsIgnoreCase(exptitle)) {
			logger.log(LogStatus.PASS, (message + " is launched"));
		} else {
			logger.log(LogStatus.FAIL,(message+ " is not Launched"));
		}
	}
	
	/*
	 * name of the method: EnterText BriefDescription : entering textvalue for
	 * textbox Arguments : element --->object text --->to be entered objName
	 * --->object name createdby : Automation team created date : 14/02/20209
	 * LastModified Date: 14/02/2020
	 */
	public static void EnterText(WebElement element, String text, String objName) {
		if (element == null || !element.isDisplayed()) {
			logger.log(LogStatus.ERROR, objName + " Textbox is not found.");
		} else {
			logger.log(LogStatus.INFO, objName + " Textbox is found");
			element.sendKeys(text);
		}
	}
	
	/*
	 * name of the method: Click BriefDescription : Clickling the button Arguments :
	 * element --->object objName --->object name createdby : Automation team
	 * created date : 14/02/20209 LastModified Date: 14/02/2020
	 */

	public static void Click(WebElement element, String objName) {
		if (element == null || !element.isDisplayed()) {
			logger.log(LogStatus.ERROR, objName + " Element is not found.");
		} else {
			logger.log(LogStatus.INFO, objName + " Element is found");
			element.click();
		}
	}
	
	public static void MsgDisplay(String actmsg, String expmsg)
	{
		if (actmsg.equalsIgnoreCase(expmsg)) {
			logger.log(LogStatus.PASS, "Correct  message displayed");
		} else {
			logger.log(LogStatus.FAIL, "Incorrect  Message Displayed");
		}
	}
	
	public static void CloseBrowser() {
		driver.quit();
	}
	
	public static void CloseReport() {
		report.flush();
	}
	

}
