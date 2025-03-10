package Config;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {
public static WebDriver driver;
public static Properties conpro;
@BeforeTest
public static void setUp()throws Throwable
{
	conpro= new Properties();
	//load property  file
	conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
	if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
		driver= new ChromeDriver();
	}
	else if(conpro.getProperty("Browser").equalsIgnoreCase("MicrosoftEdge"))
	{
		driver= new EdgeDriver();
	}
	else
	{
		Reporter.log("Browser value is not matching",true);
	
	}
}
	@AfterTest
	public static void tearDown()
	{
		driver.quit();
	
	}
}


