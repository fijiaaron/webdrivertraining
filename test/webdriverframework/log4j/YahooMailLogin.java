package webdriverframework.log4j;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

//import testNG.log4j.YahooInboxPO;

public class YahooMailLogin extends LoadableComponent<YahooMailLogin>{
	@FindBy(id="login-username")
	WebElement emailField;
	
	@FindBy(id="login-signin")
	WebElement nextButton;
	
	@FindBy(id="login-passwd")
	WebElement passWordField;
	
	@FindBy(id="login-signin")
	WebElement signInButton;
	
	WebDriver driver;
	WebDriverWait wait;
	String url = "http://yahoomail.com/";
    String title = "Yahoo - login";
	
	public YahooMailLogin(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void load()
	{
		this.driver.get(url);
		driver.manage().window().maximize();
		Log.info("Yahoo login URL has been loaded in the browser.");
	}
	
	public void isLoaded()
	{
		assertTrue(driver.getTitle().equals(title));
	}
	
	public YahooInboxPO yahooSignIn(String userID, String passWord)
	{
		wait = new WebDriverWait(driver, 30);
		
		Log.info("Username picked from XLS file is:"+userID);
		wait.until(ExpectedConditions.visibilityOf(emailField));
		emailField.clear();
		emailField.sendKeys(userID);
		
		nextButton.click();
		Log.info("Clicked on Next button");
		
		
		wait.until(ExpectedConditions.visibilityOf(passWordField));
		
		Log.info("Username picked from XLS file is:"+userID);
		passWordField.sendKeys(passWord);
		signInButton.click();
		Log.info("Clicked on Sign In button.");
		
		YahooInboxPO myInbox = new YahooInboxPO(driver);
		return myInbox;
	}

}
