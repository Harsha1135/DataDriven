package com.page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class CustomerPage {
	WebDriver driver;
	public CustomerPage(WebDriver driver)
	{
		this.driver =driver;
	}
	//define repository
	@FindBy(xpath = "//li[@id='mi_a_customers']")
	WebElement ClickCustomarLink;
	@FindBy(xpath = "(//span[@class='glyphicon glyphicon-plus ewIcon'])[1]")
	WebElement ClickAddIconButton;
	@FindBy(name = "x_Customer_Number")
	WebElement CustomerNumber;
	@FindBy(name  = "x_Customer_Name")
	WebElement EnterCustomerName;
	@FindBy(name = "x_Address")
	WebElement EnterAddress;
	@FindBy(name = "x_City")
	WebElement EnterCity;
	@FindBy(name = "x_Country")
	WebElement EnterCountry;
	@FindBy(name = "x_Contact_Person")
	WebElement EnterContactPerson;
	@FindBy(name = "x_Phone_Number")
	WebElement EnterPhoneNumber;
	@FindBy(name = "x__Email")
	WebElement EnterEmail;
	@FindBy(name = "x_Mobile_Number")
	WebElement EnterMobileNumber;
	@FindBy(name = "x_Notes")
	WebElement EnterNotes;
	@FindBy(id = "btnAction")
	WebElement ClickAddButton;
	@FindBy(xpath = "//button[normalize-space()='OK!']")
	WebElement ClickConfirmOk;
	@FindBy(xpath = "(//button[contains(text(),'OK')])[6]")
	WebElement ClickAlertOk;
	@FindBy(xpath = "//button[@class='btn btn-default ewSearchToggle']")
	WebElement ClickSearchPanel;
	@FindBy(xpath = "//input[@name='psearch']")
	WebElement EnterSearchTextbox;
	@FindBy(xpath ="//button[@name='btnsubmit']")
	WebElement ClickSearchButton;
	@FindBy(xpath = "//table[@class='table ewTable']/tbody/tr[1]/td[5]/div/span/span")
	WebElement webtable;
	//methods for add customers
	public boolean addcustomer(String CName,String Address,String City,String Country,String CPerson,String PNumber,String Email,String MNumber,String Notes)
	{
		Actions ac= new Actions(driver);
		ac.moveToElement(ClickCustomarLink).click().perform();
		ac.moveToElement(ClickAddIconButton).click().perform();
		//capture customer number
		String Exp_data=this.CustomerNumber.getAttribute("Value");
		EnterCustomerName.sendKeys(CName);
		EnterAddress.sendKeys(Address);
		EnterCity.sendKeys(City);
		EnterCountry.sendKeys(Country);
		EnterContactPerson.sendKeys(CPerson);
		EnterPhoneNumber.sendKeys(PNumber);
		EnterEmail.sendKeys(Email);
		EnterMobileNumber.sendKeys(MNumber);
		EnterNotes.sendKeys(Notes);
		ClickAddButton.sendKeys(Keys.ENTER);
		ac.moveToElement(ClickConfirmOk).click().perform();
		ac.moveToElement(ClickAlertOk).click().build().perform();
		//if search textbox is already dont click search pannel button
		if(!EnterSearchTextbox.isDisplayed())
			ClickSearchPanel.click();
		EnterSearchTextbox.clear();
		EnterSearchTextbox.sendKeys(Exp_data);
		ClickSearchButton.click();
		String Act_data= webtable.getText();
		if(Act_data.equals(Exp_data))
		{
			Reporter.log(Act_data+"---------------"+Exp_data,true);
			return true;
		}
		else
		{
			Reporter.log(Act_data+"---------------"+Exp_data,true);
			return false;
		}
	}
}




