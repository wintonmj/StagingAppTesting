import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
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
	private String celoHomePageURL = "https://stagingapp.celohealth.com/";
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
	
	private WebDriver getInitalisedDriver(){
		System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe"); 
		WebDriver driver = new ChromeDriver() ;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(waitTimeSeconds, TimeUnit.SECONDS);
		driver.get("https://stagingapp.celohealth.com/");
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
        passwordInput.sendKeys(Keys.ENTER); 

                
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
}