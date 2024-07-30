package Drivenfactory;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;	
import CommonFunctions.FunctionLibrary;
import Config.AppUtil;
import Utilities.ExcelFileUtil;

public class DriverScript extends AppUtil {
	String inputpath="./FileInput/LoginData.xlsx";
	String outputpath="./Fileoutput/dataDrivenresults.xlsx";
	ExtentReports reports;
	ExtentTest logger;
	@Test
	public void starttest() throws Throwable
	{
		//defines path of html
		reports = new ExtentReports("./Reports/Login.html");
		//create object for excelfileutil class
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		//count number of rows in login sheet
		int rc =xl.rowCount("Logindata");
		Reporter.log("no of rows are::"+rc,true);
		//iterate all rows in a sheet
		for (int i = 1; i <=rc; i++) 
		{
			logger =reports.startTest("Validate Login");
			//call username and password cell
			String username=xl.getcelldata("LoginData", i, 0);
			String password=xl.getcelldata("LoginData", i, 1);
			logger.log(LogStatus.INFO, username+"------------"+password);
			//calling login method from function library class
			boolean res = FunctionLibrary.adminLogin(username, password);
			if(res)
			{
				//if res is true write as valid username and passwoed in to resultscell
				xl.setCellData("LoginData", i, 2,"Valid username and password",outputpath);
				//write as pass in to status cell
				xl.setCellData("LoginData", i, 3, "pass", outputpath);
				logger.log(LogStatus.PASS, "Login is success");
			}
			else
			{
				//if res is false write as valid username and passwoed in to resultscell
				xl.setCellData("LoginData", i, 2,"Valid username and password",outputpath);
				//write as pass in to status cell
				xl.setCellData("LoginData", i, 3, "Fail", outputpath);
				logger.log(LogStatus.PASS, "Login is Fail");
			}
			reports.endTest(logger);
			reports.flush();
		}
	}
}
