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
	
	//User details
	private String registeredUserEmail = "qa.candidate+01@celohealth.com";
	private String registeredUserPassword = "3tQp+,/Q";
	
	//Element IDs 
	private String emailInputID = "Username";
	private String passwordInputID = "Password";
	private String pinCodeID = "pin_code";
	private String pinCodeConfirmID = "pin_code_confirm";
	private String navMenuButtonID = "nav-menu";
	private String sendMessageTextAreaID = "celo-send-message-textarea";
	
	//Element xpaths
	private String nextButtonXPath = "//*[text()=' NEXT ']"; //spaces are needed on either side
	private String sendMessageButtonXPath = "//*[text()='send']"; 


	
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
		
		//wait for conversations to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(navMenuButtonID)));

		return driver; 
	}
	
	@Test
	public void Conversations_Success_01_String() throws InterruptedException{
		WebDriver driver = this.getInitalisedDriver();
		
		//WebElement conversationsList = driver.findElement(By.id(""));
		//find first element of conversationsList
		//click on first element in conversationsList
		
		//Wait for conversation to load
        //WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds); // seconds
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(sendMessageTextAreaID)));
		
		WebElement messageTextArea = driver.findElement(By.id(sendMessageTextAreaID));
		WebElement sendMessageButton = driver.findElement(By.xpath(sendMessageButtonXPath));

		messageTextArea.sendKeys("new message");
		sendMessageButton.click();
		
		//assert new message is there
		
		driver.close();
	}
}