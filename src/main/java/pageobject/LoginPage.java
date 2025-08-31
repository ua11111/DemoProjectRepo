package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	
	{
		this.driver=driver;
		
		PageFactory.initElements(driver,this);

		
	}
	
	@FindBy(id="id_email_login")
	WebElement login;
	
	public void supplyusername(String username)
	{
		login.sendKeys(username);
	}
	
	
	@FindBy(id="id_password")
	WebElement password1;
	
	public void supplypassword(String password)
	{
		password1.sendKeys(password);
	}
	
	
	
	@FindBy(xpath="//div[@class='w-100']//button[@type='button'][normalize-space()='Login']")
	WebElement submit;
	
	public void clicksubmit()
	{
		submit.click();
	}
	
	}


