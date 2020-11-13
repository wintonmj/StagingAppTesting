import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CoversationsPageTests {
	
	//Variables
	private int waitTimeSeconds = 3;
	
	//URLS
	private String celoHomePageURL = "https://stagingapp.celohealth.com/";
	private String conversationsPageURL = "https://stagingapp.celohealth.com/conversations";
	
	//User details
	private String registeredUserEmail = "qa.candidate+01@celohealth.com";
	private String registeredUserPassword = "3tQp+,/Q";
	
	//Element IDs 
	private String emailInputID = "Username";
	private String passwordInputID = "Password";
	private String pinCodeID = "pin_code";
	private String pinCodeConfirmID = "pin_code_confirm";
	private String navMenuButtonID = "nav-menu";
	private String lockButtonID = "celo-lock-button";
	
	//Element classnames
	private String conversationsListClass = "mat-ripple convo-grid pt-1 pb-1 ng-tns-c130-5 ng-trigger ng-trigger-slideInOut ng-star-inserted selected";
	
	//Element xpaths
	private String nextButtonXPath = "//*[text()=' NEXT ']"; //spaces are needed on either side
	private String incorrectPinPopUpXPath = "//*[text()='Incorrect PIN. Please re-enter.']"; 
	private String forgotPinLinkXPath = "//*[text()='Forgot pin']"; 
	private String switchAccountLinkXPath = "//*[text()='Sign in with a different account']"; 
	private String logoTitleXPath = "//*[text()='Where healthcare comes together']"; 

	
	private WebDriver getInitalisedDriver(){
		System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe"); 
		WebDriver driver = new ChromeDriver() ;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(waitTimeSeconds, TimeUnit.SECONDS);
		driver.get(celoHomePageURL);
		
		//Navigate to login page
		driver.findElement(By.id("login")).click();
		
		//Login
		driver.findElement(By.id(emailInputID)).sendKeys(registeredUserEmail);
        driver.findElement(By.id(passwordInputID)).sendKeys(registeredUserPassword); 
        driver.findElement(By.id(passwordInputID)).sendKeys(Keys.ENTER); 
        
        //Wait until redirected to Set Pin page
        WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds); // seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(pinCodeID)));
        
        //Set pin code and navigate to conversations
		String pinCode = "1235";
		driver.findElement(By.id(pinCodeID)).sendKeys(pinCode);
        driver.findElement(By.id(pinCodeConfirmID)).sendKeys(pinCode);
        driver.findElement(By.xpath(nextButtonXPath)).click();
		driver.manage().timeouts().implicitlyWait(waitTimeSeconds, TimeUnit.SECONDS);

		return driver; 
	}
	
	@Test
	public void Conversations_Success_01_String() throws InterruptedException{
		WebDriver driver = this.getInitalisedDriver();
		
		//wait for conversations to load
        WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds); // seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(navMenuButtonID)));
        
		WebElement conversationsList = driver.findElement(By.className(conversationsListClass));
		conversationsList.click();
		
//        String currentURL= driver.getCurrentUrl();
//		driver.close();
//		assertEquals(conversationsPageURL, currentURL);
	}
}