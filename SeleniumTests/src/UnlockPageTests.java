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

public class UnlockPageTests {
	
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

	
	//Element xpaths
	private String nextButtonXPath = "//*[text()=' NEXT ']"; //spaces are needed on either side
	private String incorrectPinPopUpXPath = "//*[text()='Incorrect PIN. Please re-enter.']"; 
	private String forgotPinLinkXPath = "//*[text()='Forgot pin']"; 
	private String switchAccountLinkXPath = "//*[text()='Sign in with a different account']"; 
	private String logoTitleXPath = "//*[text()='Where healthcare comes together']"; 

	
	private WebDriver getInitalisedDriver(String pinCode){
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
		driver.findElement(By.id(pinCodeID)).sendKeys(pinCode);
        driver.findElement(By.id(pinCodeConfirmID)).sendKeys(pinCode);
        driver.findElement(By.xpath(nextButtonXPath)).click();
		driver.manage().timeouts().implicitlyWait(waitTimeSeconds, TimeUnit.SECONDS);
		
		//Open nav menu account
        driver.findElement(By.id(navMenuButtonID)).click();
        
        //Wait until menu elements are displayed
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(lockButtonID)));
        driver.findElement(By.id(lockButtonID)).click();

		return driver; 
	}
	
	@Test
	public void Unlock_Success_01_Number() throws InterruptedException{
		String pinCode = "1235";
		WebDriver driver = this.getInitalisedDriver(pinCode);

		WebElement pinCodeConfirmInput = driver.findElement(By.id(pinCodeConfirmID));
		pinCodeConfirmInput.sendKeys(pinCode);
		
		//wait for conversations to load
        WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds); // seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(navMenuButtonID)));
		
        String currentURL= driver.getCurrentUrl();
		driver.close();
		assertEquals(conversationsPageURL, currentURL);
	}

	@Test
	public void Unlock_Success_02_Letter() throws InterruptedException{
		String pinCode = "word";
		WebDriver driver = this.getInitalisedDriver(pinCode);

		WebElement pinCodeConfirmInput = driver.findElement(By.id(pinCodeConfirmID));
		pinCodeConfirmInput.sendKeys(pinCode);
		
		//wait for conversations to load
        WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds); // seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(navMenuButtonID)));
		
        String currentURL= driver.getCurrentUrl();
		driver.close();
		assertEquals(conversationsPageURL, currentURL);
	}
	
	@Test
	public void Unlock_Success_03_Special_Character() throws InterruptedException{
		String pinCode = "!@#$";
		WebDriver driver = this.getInitalisedDriver(pinCode);

		WebElement pinCodeConfirmInput = driver.findElement(By.id(pinCodeConfirmID));
		pinCodeConfirmInput.sendKeys(pinCode);
		
		//wait for conversations to load
        WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds); // seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(navMenuButtonID)));
		
        String currentURL= driver.getCurrentUrl();
		driver.close();
		assertEquals(conversationsPageURL, currentURL);
	}
	
	@Test
	public void Unlock_Success_04_Combination_Characters() throws InterruptedException{
		String pinCode = "1W@4";
		WebDriver driver = this.getInitalisedDriver(pinCode);

		WebElement pinCodeConfirmInput = driver.findElement(By.id(pinCodeConfirmID));
		pinCodeConfirmInput.sendKeys(pinCode);
		
		//wait for conversations to load
        WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds); // seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(navMenuButtonID)));
		
        String currentURL= driver.getCurrentUrl();
		driver.close();
		assertEquals(conversationsPageURL, currentURL);
	}
	
	@Test
	public void Unlock_Success_05_Forgot_Pin() throws InterruptedException{
		String pinCode = "1W@4";
		WebDriver driver = this.getInitalisedDriver(pinCode);

		WebElement forgotPinLink = driver.findElement(By.xpath(forgotPinLinkXPath));
		forgotPinLink.click();
		
		//wait for conversations to load
        WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds); // seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(logoTitleXPath)));
		
        String currentURL= driver.getCurrentUrl();
		driver.close();
		assertEquals(celoHomePageURL, currentURL);
	}
	
	@Test
	public void Unlock_Success_06_Switch_Account() throws InterruptedException{
		String pinCode = "1W@4";
		WebDriver driver = this.getInitalisedDriver(pinCode);

		WebElement switchAccountLink = driver.findElement(By.xpath(switchAccountLinkXPath));
		switchAccountLink.click();
		
		//wait for conversations to load
        WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds); // seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(logoTitleXPath)));
		
        String currentURL= driver.getCurrentUrl();
		driver.close();
		assertEquals(celoHomePageURL, currentURL);
	}
	
	@Test
	public void Unlock_Failure_01_Invalid_Pin_Code() throws InterruptedException{
		String pinCode = "1235";
		String incorrectPinCode = "3333";

		WebDriver driver = this.getInitalisedDriver(pinCode);

		WebElement pinCodeConfirmInput = driver.findElement(By.id(pinCodeConfirmID));
		pinCodeConfirmInput.sendKeys(incorrectPinCode);
		
		//wait for conversations to load
        WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds); // seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(incorrectPinPopUpXPath)));
		
        assertTrue(driver.findElement(By.xpath(incorrectPinPopUpXPath)).isDisplayed());
		driver.close();
	}
	
	@Test
	public void Unlock_Failure_04_Empty_Pin_Code() throws InterruptedException{
		String pinCode = "1235";
		WebDriver driver = this.getInitalisedDriver(pinCode);

		WebElement pinCodeConfirmInput = driver.findElement(By.id(pinCodeConfirmID));
		pinCodeConfirmInput.sendKeys(Keys.ENTER);
		
		//wait for conversations to load
        WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds); // seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(incorrectPinPopUpXPath)));
		
        assertTrue(driver.findElement(By.xpath(incorrectPinPopUpXPath)).isDisplayed());
		driver.close();
	}
}