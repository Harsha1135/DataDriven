package Drivenfactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.page.CustomerPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Config.ERP_Apputil;
import Utilities.ExcelFileUtil;

public class TestScript extends ERP_Apputil
{
	String inputputpath ="./Fileinput/CustomerData.xlsx";
	String outputpath="./Fileoutput/PomResults.xlsx";
	ExtentReports reports;
	ExtentTest logger;
	String TCSheet ="Customer";
	@Test
	public void startTest() throws Throwable
	{
		reports =new ExtentReports("./Reports/Customer.html");
		ExcelFileUtil xl = new ExcelFileUtil(inputputpath);
		int rc= xl.rowCount(TCSheet);
		Reporter.log("No of rows are::"+rc,true);
		for (int i = 1; i <=rc; i++)
		{
			logger= reports.startTest("Add Customer");
			logger.assignAuthor("harsha");
			String CName=xl.getcelldata(TCSheet, i, 0);
			String Address=xl.getcelldata(TCSheet, i, 1);
			String City=xl.getcelldata(TCSheet, i, 2);
			String Country=xl.getcelldata(TCSheet, i, 3);
			String CPerson=xl.getcelldata(TCSheet, i, 4);
			String PNumber=xl.getcelldata(TCSheet, i, 5);
			String Email=xl.getcelldata(TCSheet, i, 6);
			String MNumber=xl.getcelldata(TCSheet, i, 7);
			String Notes= xl.getcelldata(TCSheet, i, 8);
			CustomerPage cus =PageFactory.initElements(driver, CustomerPage.class);
			boolean res= cus.addcustomer(CName, Address, City, Country, CPerson, PNumber, Email, MNumber, Notes);
			logger.log(LogStatus.INFO, CName+" " +Address+"  "+City+"   " +Country+"  " +CPerson+"  " +PNumber+" "+ Email+" "+ MNumber+" "+ Notes);
			if(res)
			{
			xl.setCellData(TCSheet, i, 9, "pass", outputpath);
			logger.log(LogStatus.PASS, "Customer Added Success");
			}
			else
			{
				xl.setCellData(TCSheet, i, 9, "Fail", outputpath);
				logger.log(LogStatus.FAIL, "Customer Add Fail");
			}
			reports.endTest(logger);
			reports.flush();

		}
		
			
		}
	

	}


