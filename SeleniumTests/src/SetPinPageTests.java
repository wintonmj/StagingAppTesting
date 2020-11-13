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

public class SetPinPageTests {
	
	//Variables
	private int waitTimeSeconds = 3;
	
	//URLS
	private String celoHomePageURL = "https://stagingapp.celohealth.com";
	private String setPinPageURL = "https://stagingapp.celohealth.com/pin?returnUrl=%2Fconversations";
	private String conversationsPageURL = "https://stagingapp.celohealth.com/conversations";
	
	//User details
	private String registeredUserEmail = "qa.candidate+01@celohealth.com";
	private String registeredUserPassword = "3tQp+,/Q";
	
	//Element IDs 
	private String emailInputID = "Username";
	private String passwordInputID = "Password";
	private String pinCodeID = "pin_code";
	private String pinCodeConfirmID = "pin_code_confirm";
	
	//Element xpaths
	private String nextButtonXPath = "//*[text()=' NEXT ']"; //spaces are needed on either side
	private String repetitivePinWarningXPath = "//*[text()='Repetitive PIN is too weak, please pick a stronger PIN']";
	private String weakPinWarningXPath = "//*[text()='Celo Security policy does not allow weak PIN codes. Please enter a more secure PIN.']";
	private String mismatchedPinsWarningXPath = "//*[text()='PINs do not match. Please re-enter.']";
	private String shortPinsWarningXPath = "//*[text()='PINs too short. Please re-enter.']";

	

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
		
		return driver; 
	}
	
	@Test
	public void Set_Pin_Success_01_Next_Button() throws InterruptedException{
		String pinCode = "1235";
		
		WebDriver driver = this.getInitalisedDriver();
		WebElement pinCodeInput = driver.findElement(By.id(pinCodeID));
        WebElement pinCodeConfirmInput = driver.findElement(By.id(pinCodeConfirmID));
        WebElement nextButton = driver.findElement(By.xpath(nextButtonXPath));
        
        pinCodeInput.sendKeys(pinCode);
        pinCodeConfirmInput.sendKeys(pinCode); 
        nextButton.click();
                
        String currentURL= driver.getCurrentUrl();
		driver.close();
		assertEquals(conversationsPageURL, currentURL);
	}
	
	@Test
	public void Set_Pin_Success_02_Pin_Focused_Enter() throws InterruptedException{
		String pinCode = "1235";
		
		WebDriver driver = this.getInitalisedDriver();
		WebElement pinCodeInput = driver.findElement(By.id(pinCodeID));
        WebElement pinCodeConfirmInput = driver.findElement(By.id(pinCodeConfirmID));
        
        pinCodeInput.sendKeys(pinCode);
        pinCodeConfirmInput.sendKeys(pinCode); 
        pinCodeInput.sendKeys(Keys.ENTER); 
                
        String currentURL= driver.getCurrentUrl();
        
		driver.close();
		assertEquals(conversationsPageURL, currentURL);
	}
	
	@Test
	public void Set_Pin_Success_03_Confirm_Focused_Enter() throws InterruptedException{
		String pinCode = "1235";
		WebDriver driver = this.getInitalisedDriver();
		
		WebElement pinCodeInput = driver.findElement(By.id(pinCodeID));
	    WebElement pinCodeConfirmInput = driver.findElement(By.id(pinCodeConfirmID));
	        
	    pinCodeInput.sendKeys(pinCode);
	    pinCodeConfirmInput.sendKeys(pinCode); 
	    pinCodeConfirmInput.sendKeys(Keys.ENTER); 
	                
	    String currentURL= driver.getCurrentUrl();
	    
	    driver.close();
		assertEquals(conversationsPageURL, currentURL);	
	}
	
	@Test
	public void Set_Pin_Success_04_Numbers() throws InterruptedException{
		String pinCode = "1235";
		WebDriver driver = this.getInitalisedDriver();
		
		WebElement pinCodeInput = driver.findElement(By.id(pinCodeID));
	    WebElement pinCodeConfirmInput = driver.findElement(By.id(pinCodeConfirmID));
	        
	    pinCodeInput.sendKeys(pinCode);
	    pinCodeConfirmInput.sendKeys(pinCode); 
	    pinCodeConfirmInput.sendKeys(Keys.ENTER); 
	                
	    String currentURL= driver.getCurrentUrl();
	    
	    driver.close();
		assertEquals(conversationsPageURL, currentURL);	
	}
	
	@Test
	public void Set_Pin_Success_05_Letters() throws InterruptedException{
		String pinCode = "word";
		WebDriver driver = this.getInitalisedDriver();
		
		WebElement pinCodeInput = driver.findElement(By.id(pinCodeID));
	    WebElement pinCodeConfirmInput = driver.findElement(By.id(pinCodeConfirmID));
	        
	    pinCodeInput.sendKeys(pinCode);
	    pinCodeConfirmInput.sendKeys(pinCode); 
	    pinCodeConfirmInput.sendKeys(Keys.ENTER); 
	                
	    String currentURL= driver.getCurrentUrl();
	    
	    driver.close();
		assertEquals(conversationsPageURL, currentURL);	
	}
	
	@Test
	public void Set_Pin_Success_06_Special_Characters() throws InterruptedException{
		String pinCode = "!@#$";
		WebDriver driver = this.getInitalisedDriver();
		
		WebElement pinCodeInput = driver.findElement(By.id(pinCodeID));
	    WebElement pinCodeConfirmInput = driver.findElement(By.id(pinCodeConfirmID));
	        
	    pinCodeInput.sendKeys(pinCode);
	    pinCodeConfirmInput.sendKeys(pinCode); 
	    pinCodeConfirmInput.sendKeys(Keys.ENTER); 
	                
	    String currentURL= driver.getCurrentUrl();
	    
	    driver.close();
		assertEquals(conversationsPageURL, currentURL);	
	}
	
	@Test
	public void Set_Pin_Success_07_Combination_Characters() throws InterruptedException{
		String pinCode = "1@e4";
		WebDriver driver = this.getInitalisedDriver();
		
		WebElement pinCodeInput = driver.findElement(By.id(pinCodeID));
	    WebElement pinCodeConfirmInput = driver.findElement(By.id(pinCodeConfirmID));
	        
	    pinCodeInput.sendKeys(pinCode);
	    pinCodeConfirmInput.sendKeys(pinCode); 
	    pinCodeConfirmInput.sendKeys(Keys.ENTER); 
	                
	    String currentURL= driver.getCurrentUrl();
	    
	    driver.close();
		assertEquals(conversationsPageURL, currentURL);	
	}
	
	@Test
	public void Set_Pin_Success_08_Oversized_Pin() throws InterruptedException{
		String pinCode = "1@e456789";
		WebDriver driver = this.getInitalisedDriver();
		
		WebElement pinCodeInput = driver.findElement(By.id(pinCodeID));
	    WebElement pinCodeConfirmInput = driver.findElement(By.id(pinCodeConfirmID));
	        
	    pinCodeInput.sendKeys(pinCode);
	    pinCodeConfirmInput.sendKeys(pinCode); 
	    pinCodeConfirmInput.sendKeys(Keys.ENTER); 
	                
	    String currentURL= driver.getCurrentUrl();
	    
	    driver.close();
		assertEquals(conversationsPageURL, currentURL);	
	}
	
	@Test
	public void Set_Pin_Failure_01_Repetitive_Number() throws InterruptedException{
		String pinCode = "1111";
		WebDriver driver = this.getInitalisedDriver();
		
		WebElement pinCodeInput = driver.findElement(By.id(pinCodeID));
	    WebElement pinCodeConfirmInput = driver.findElement(By.id(pinCodeConfirmID));
	        
	    pinCodeInput.sendKeys(pinCode);
	    pinCodeConfirmInput.sendKeys(pinCode); 
	    pinCodeConfirmInput.sendKeys(Keys.ENTER); 
	                
        //Wait until pop up comes up
        WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds); // seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(repetitivePinWarningXPath)));
        assertTrue(driver.findElement(By.xpath(repetitivePinWarningXPath)).isDisplayed());

	    driver.close();
	}
	
	@Test
	public void Set_Pin_Failure_02_Repetitive_Letter() throws InterruptedException{
		String pinCode = "qqqq";
		WebDriver driver = this.getInitalisedDriver();
		
		WebElement pinCodeInput = driver.findElement(By.id(pinCodeID));
	    WebElement pinCodeConfirmInput = driver.findElement(By.id(pinCodeConfirmID));
	        
	    pinCodeInput.sendKeys(pinCode);
	    pinCodeConfirmInput.sendKeys(pinCode); 
	    pinCodeConfirmInput.sendKeys(Keys.ENTER); 
	                
        //Wait until pop up comes up
        WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds); // seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(repetitivePinWarningXPath)));
        assertTrue(driver.findElement(By.xpath(repetitivePinWarningXPath)).isDisplayed());

	    driver.close();
	}
	
	@Test
	public void Set_Pin_Failure_03_Repetitive_Special_Character() throws InterruptedException{
		String pinCode = "!!!!";
		WebDriver driver = this.getInitalisedDriver();
		
		WebElement pinCodeInput = driver.findElement(By.id(pinCodeID));
	    WebElement pinCodeConfirmInput = driver.findElement(By.id(pinCodeConfirmID));
	        
	    pinCodeInput.sendKeys(pinCode);
	    pinCodeConfirmInput.sendKeys(pinCode); 
	    pinCodeConfirmInput.sendKeys(Keys.ENTER); 
	                
        //Wait until pop up comes up
        WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds); // seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(repetitivePinWarningXPath)));
        assertTrue(driver.findElement(By.xpath(repetitivePinWarningXPath)).isDisplayed());

	    driver.close();
	}
	
	@Test
	public void Set_Pin_Failure_04_Weak_Pin_Asending() throws InterruptedException{
		String pinCode = "1234";
		WebDriver driver = this.getInitalisedDriver();
		
		WebElement pinCodeInput = driver.findElement(By.id(pinCodeID));
	    WebElement pinCodeConfirmInput = driver.findElement(By.id(pinCodeConfirmID));
	        
	    pinCodeInput.sendKeys(pinCode);
	    pinCodeConfirmInput.sendKeys(pinCode); 
	    pinCodeConfirmInput.sendKeys(Keys.ENTER); 
	                
        //Wait until pop up comes up
        WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds); // seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(weakPinWarningXPath)));
        assertTrue(driver.findElement(By.xpath(weakPinWarningXPath)).isDisplayed());

	    driver.close();
	}
	
	@Test
	public void Set_Pin_Failure_05_Weak_Pin_Decending() throws InterruptedException{
		String pinCode = "4321";
		WebDriver driver = this.getInitalisedDriver();
		
		WebElement pinCodeInput = driver.findElement(By.id(pinCodeID));
	    WebElement pinCodeConfirmInput = driver.findElement(By.id(pinCodeConfirmID));
	        
	    pinCodeInput.sendKeys(pinCode);
	    pinCodeConfirmInput.sendKeys(pinCode); 
	    pinCodeConfirmInput.sendKeys(Keys.ENTER); 
	                
        //Wait until pop up comes up
        WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds); // seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(weakPinWarningXPath)));
        assertTrue(driver.findElement(By.xpath(weakPinWarningXPath)).isDisplayed());

	    driver.close();
	}
	
	@Test
	public void Set_Pin_Failure_06_Mismatched_Pin_Codes() throws InterruptedException{
		String pinCode = "1245";
		String pinCodeConfirm = "5412";
		
		WebDriver driver = this.getInitalisedDriver();
		
		WebElement pinCodeInput = driver.findElement(By.id(pinCodeID));
	    WebElement pinCodeConfirmInput = driver.findElement(By.id(pinCodeConfirmID));
	        
	    pinCodeInput.sendKeys(pinCode);
	    pinCodeConfirmInput.sendKeys(pinCodeConfirm); 
	                
        //Wait until warning text appears
        WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds); // seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(mismatchedPinsWarningXPath)));
        assertTrue(driver.findElement(By.xpath(mismatchedPinsWarningXPath)).isDisplayed());

	    driver.close();
	}
	
	@Test
	public void Set_Pin_Failure_07_Mismatched_Cases() throws InterruptedException{
		String pinCode = "word";
		String pinCodeConfirm = "WORD";
		
		WebDriver driver = this.getInitalisedDriver();
		
		WebElement pinCodeInput = driver.findElement(By.id(pinCodeID));
	    WebElement pinCodeConfirmInput = driver.findElement(By.id(pinCodeConfirmID));
	        
	    pinCodeInput.sendKeys(pinCode);
	    pinCodeConfirmInput.sendKeys(pinCodeConfirm); 
	                
        //Wait until warning text appears
        WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds); // seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(mismatchedPinsWarningXPath)));
        assertTrue(driver.findElement(By.xpath(mismatchedPinsWarningXPath)).isDisplayed());

	    driver.close();
	}
	
	@Test
	public void Set_Pin_Failure_08_Undersized_Pin() throws InterruptedException{
		String pinCode = "word";
		String pinCodeConfirm = "WORD";
		
		WebDriver driver = this.getInitalisedDriver();
		
		WebElement pinCodeInput = driver.findElement(By.id(pinCodeID));
	    WebElement pinCodeConfirmInput = driver.findElement(By.id(pinCodeConfirmID));
	        
	    pinCodeInput.sendKeys(pinCode);
	    pinCodeConfirmInput.sendKeys(pinCodeConfirm); 
	                
        //Wait until warning text appears
        WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds); // seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(shortPinsWarningXPath)));
        assertTrue(driver.findElement(By.xpath(shortPinsWarningXPath)).isDisplayed());

	    driver.close();
	}
}