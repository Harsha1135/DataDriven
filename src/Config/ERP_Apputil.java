package Config;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.page.AdminLogin;
import com.page.AdminLogout;

public class ERP_Apputil {
	public static WebDriver driver;
	public static Properties conpro;
	@BeforeTest
	public static void setUp() throws Throwable
	{
		conpro=new Properties();
		conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(conpro.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		AdminLogin login=PageFactory.initElements(driver, AdminLogin.class);
		login.verify_Login("admin", "master");
	}
	@AfterTest
	public static void tearDown() 
	{
		AdminLogout logout= PageFactory.initElements(driver,AdminLogout.class);
		logout.verify_Logout();
		driver.quit();
	}

}
