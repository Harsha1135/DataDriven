package CommonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import Config.AppUtil;

public class FunctionLibrary extends AppUtil{
	public static boolean adminLogin(String user,String pass)throws Throwable
	{
		driver.get(conpro.getProperty("Url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath(conpro.getProperty("ObjReset"))).click();
		driver.findElement(By.xpath(conpro.getProperty("Objuser"))).sendKeys(user);
		driver.findElement(By.xpath(conpro.getProperty("Objpass"))).sendKeys(pass);
		driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
		String Expected ="dashboard";
		String Actual = driver.getCurrentUrl();
		if(Actual.contains(Expected))
		{
			Reporter.log("Validate Username and Password::"+Expected+"--------"+Actual,true);
			//click logout link
			driver.findElement(By.xpath(conpro.getProperty("ObjLogout"))).click();
			return true;
		}
		else
		{
			//capture error message
			String Message = driver.findElement(By.xpath(conpro.getProperty("Objerror"))).getText();
			driver.findElement(By.xpath(conpro.getProperty("Objok"))).click();
			Reporter.log(Message+"--------"+Expected+"-------"+Actual,true);
			return false;
		}
		
	}
}
