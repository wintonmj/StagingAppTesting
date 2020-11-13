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

public class LoginPageTests {
	
	//Variables
	private int waitTimeSeconds = 3;
	
	//URLS
	private String celoHomePageURL = "https://stagingapp.celohealth.com";
	private String setPinPageURL = "https://stagingapp.celohealth.com/pin?returnUrl=%2Fconversations";
	
	//User details
	private String registeredUserEmail = "qa.candidate+01@celohealth.com";
	private String registeredUserPassword = "3tQp+,/Q";
	
	//Element IDs 
	private String emailInputID = "Username";
	private String passwordInputID = "Password";
	private String pinCodeID = "pin_code";
	
	//Element xpaths ()
	private String loginButtonXPath = "//*[text()='LOG IN']";
	private String logoTitleXPath = "//*[text()='Where healthcare comes together']";
	private String invalidInputWarningXPath = "//*[text()='Incorrect username and/or password. Please re-enter.']";
	private String emptyEmailWarningXPath = "//*[text()='The Username field is required.']";
	private String emptyPasswordWarningXPath = "//*[text()='The Password field is required.']";
	private String expiredPasswordWarningXPath = "//*[text()='Your account is locked. Please reset your password.']";

	
	private WebDriver getInitalisedDriver(){
		System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe"); 
		WebDriver driver = new ChromeDriver() ;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(waitTimeSeconds, TimeUnit.SECONDS);
		driver.get(celoHomePageURL);
		WebElement loginButton = driver.findElement(By.id("login"));
		loginButton.click();
		
		return driver; 
	}
	
	@Test
	public void login_Success_01_Log_In_Button() throws InterruptedException{
		WebDriver driver = this.getInitalisedDriver();
		WebElement emailInput = driver.findElement(By.id(emailInputID));
        WebElement passwordInput = driver.findElement(By.id(passwordInputID));
        WebElement loginButton = driver.findElement(By.xpath(loginButtonXPath));
        
        emailInput.sendKeys(registeredUserEmail);
        passwordInput.sendKeys(registeredUserPassword); 
        loginButton.click();
                
        //Allow for redirects and loading before getting the URL
        WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds); // seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(pinCodeID)));
        
        String currentURL= driver.getCurrentUrl();
		assertEquals(setPinPageURL, currentURL);
		
		//Must close driver
		driver.close();
	}
	
	@Test
	public void login_Success_02_Email_Focused_Enter() throws InterruptedException{
		WebDriver driver = this.getInitalisedDriver();
		WebElement emailInput = driver.findElement(By.id(emailInputID));
        WebElement passwordInput = driver.findElement(By.id(passwordInputID));
        
        emailInput.sendKeys(registeredUserEmail);
        passwordInput.sendKeys(registeredUserPassword); 
        emailInput.sendKeys(Keys.ENTER); 

                
        //Allow for redirects and loading before getting the URL
        WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds); // seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(pinCodeID)));
        
        String currentURL= driver.getCurrentUrl();
		assertEquals(setPinPageURL, currentURL);
		
		//Must close driver
		driver.close();
	}
	
	@Test
	public void login_Success_03_Password_Focused_Enter() throws InterruptedException{
		WebDriver driver = this.getInitalisedDriver();
		WebElement emailInput = driver.findElement(By.id(emailInputID));
        WebElement passwordInput = driver.findElement(By.id(passwordInputID));
        
        emailInput.sendKeys(registeredUserEmail);
        passwordInput.sendKeys(registeredUserPassword); 
        emailInput.sendKeys(Keys.ENTER); 

                
        //Allow for redirects and loading before getting the URL
        WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds); // seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(pinCodeID)));
        
        String currentURL= driver.getCurrentUrl();
		assertEquals(setPinPageURL, currentURL);
		
		//Must close driver
		driver.close();
	}
	
	@Test
	public void login_Failure_02_Invalid_Email() throws InterruptedException{
		WebDriver driver = this.getInitalisedDriver();
		WebElement emailInput = driver.findElement(By.id(emailInputID));
        WebElement passwordInput = driver.findElement(By.id(passwordInputID));

        emailInput.sendKeys("qa.candidate+01@SELOhealth.com");
        passwordInput.sendKeys(registeredUserPassword); 
        passwordInput.sendKeys(Keys.ENTER); 

        assertTrue(driver.findElement(By.xpath(invalidInputWarningXPath)).isDisplayed());
        
		//Must close driver
		driver.close();
	}
	
	@Test
	public void login_Failure_03_Invalid_Password() throws InterruptedException{
		WebDriver driver = this.getInitalisedDriver();
		WebElement emailInput = driver.findElement(By.id(emailInputID));
        WebElement passwordInput = driver.findElement(By.id(passwordInputID));

        emailInput.sendKeys(registeredUserEmail);
        passwordInput.sendKeys("invalidPassword"); 
        passwordInput.sendKeys(Keys.ENTER); 

        assertTrue(driver.findElement(By.xpath(invalidInputWarningXPath)).isDisplayed());
        
		//Must close driver
		driver.close();
	}
	
	@Test
	public void login_Failure_04_Empty_Email() throws InterruptedException{
		WebDriver driver = this.getInitalisedDriver();
		WebElement emailInput = driver.findElement(By.id(emailInputID));
        WebElement passwordInput = driver.findElement(By.id(passwordInputID));

        emailInput.sendKeys(registeredUserEmail);
        passwordInput.sendKeys(Keys.ENTER); 

        assertTrue(driver.findElement(By.xpath(emptyPasswordWarningXPath)).isDisplayed());
        
		//Must close driver
		driver.close();
	}
	
	@Test
	public void login_Failure_05_Empty_Password() throws InterruptedException{
		WebDriver driver = this.getInitalisedDriver();
		WebElement emailInput = driver.findElement(By.id(emailInputID));
        WebElement passwordInput = driver.findElement(By.id(passwordInputID));

        passwordInput.sendKeys(registeredUserPassword);
        passwordInput.sendKeys(Keys.ENTER); 

        assertTrue(driver.findElement(By.xpath(emptyEmailWarningXPath)).isDisplayed());
        
		//Must close driver
		driver.close();
	}
	
	@Test
	public void login_Failure_06_Empty_Email_And_Password() throws InterruptedException{
		WebDriver driver = this.getInitalisedDriver();
		WebElement emailInput = driver.findElement(By.id(emailInputID));
        WebElement passwordInput = driver.findElement(By.id(passwordInputID));

        passwordInput.sendKeys(Keys.ENTER); 

        assertTrue(driver.findElement(By.xpath(emptyPasswordWarningXPath)).isDisplayed());
        assertTrue(driver.findElement(By.xpath(emptyEmailWarningXPath)).isDisplayed());
        
		//Must close driver
		driver.close();
	}
	
	@Test
	public void login_Failure_07_Exceed_Password_Failure_Attempts() throws InterruptedException{
		WebDriver driver = this.getInitalisedDriver();
		WebElement emailInput = driver.findElement(By.id(emailInputID));
        WebElement passwordInput = driver.findElement(By.id(passwordInputID));
        emailInput.sendKeys(registeredUserEmail);
        
        for(int i=0; i<10;i++) {
            passwordInput.sendKeys("invalidPassword"); 
            passwordInput.sendKeys(Keys.ENTER); 
            
        	//after submitting the form, the element must be reset to interact with it again
        	passwordInput = driver.findElement(By.id(passwordInputID));

        }
        
        assertTrue(driver.findElement(By.xpath(expiredPasswordWarningXPath)).isDisplayed());
        
		//Must close driver
		driver.close();
	}
}