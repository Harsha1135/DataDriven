package com.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AdminLogin {
	WebDriver driver;
	public AdminLogin(WebDriver driver) {
		this.driver= driver;
	}
	//define repository for login
	@FindBy(name="btnreset")
	WebElement ObjReseat;
	@FindBy(id="username")
	WebElement ObjUser;
	@FindBy(xpath = "//input[@id='password']")
	WebElement ObjPass;
	@FindBy(name = "btnsubmit")
	WebElement Objlogin;
	//method for login
	public void verify_Login(String user,String pass)
	{
		ObjReseat.click();
		this.ObjUser.sendKeys(user);
		ObjPass.sendKeys(pass);
		Objlogin.click();
		String Expected ="dashboard";
		String Actual=driver.getCurrentUrl();
		try {
		Assert.assertTrue(Actual.contains(Expected),"Invalid username and password");
		}catch (AssertionError e) {
			System.out.println(e.getMessage());
			
		}
	}
	
}
