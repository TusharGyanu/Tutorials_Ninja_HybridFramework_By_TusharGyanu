package com.tutorialsNinja.Qa.TestCases;

import java.io.FileNotFoundException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.tutorialsNinja.Qa.base.base;
import com.tutorialsNinja.qa.pages.AccountPage;
import com.tutorialsNinja.qa.pages.HomePage;
import com.tutorialsNinja.qa.pages.LoginPage;
import com.tutorialsNinja.qa.utils.Utils;

public class LoginTest extends base {
	LoginPage loginpage;
	
	public LoginTest() {
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void SetUpMethod() {
		
		driver = InitilizeBrowser_andOpenApplication(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		loginpage = homepage.navigateToLoginPage();
	}
	
	@AfterMethod
	public void TearDown() {
		driver.quit();
	}
	
	@Test(priority =1, dataProvider="ValidCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password ) throws InterruptedException {
	
		AccountPage accountpage = loginpage.login(email, password);
//	loginpage.enterEmailAddress(email);
//	loginpage.enterPassword(password);
	//AccountPage accountpage = loginpage.clickOnloginButton();
	Assert.assertTrue(accountpage.getDisplayStatusOfEditYourAccountInformationOption());

	}
	
	@DataProvider(name = "ValidCredentialsSupplier")
	public Object[][] supplyTestData() throws FileNotFoundException {
		
		Object[][] data = Utils.getTestDataFromExcel("Login");
				
				return data;
	}

	 
	@Test(priority =2)
	public void verifyLoginWithInValidCredentials() throws InterruptedException {
		
		loginpage.login(Utils.generateEmailWithTimeStamp(), dataprop.getProperty("invalidpassword"));
//		loginpage.enterEmailAddress(Utils.generateEmailWithTimeStamp());
//		loginpage.enterPassword(dataprop.getProperty("invalidpassword"));
//		loginpage.clickOnloginButton();

		Thread.sleep(2000);
		String actualWarningMessage = loginpage.retriveEmailPasswordNotMatchingWarningMessageText();
		String ExpectedWarningMessage = dataprop.getProperty("EmailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(ExpectedWarningMessage), "Expected warning message is not displayed");
		
	}
		
	@Test(priority =3)
	public void verifyLoginWithInValidEmail() throws InterruptedException {
		
		
		loginpage.enterEmailAddress(Utils.generateEmailWithTimeStamp());
		loginpage.enterPassword(prop.getProperty("validPassword"));
		loginpage.clickOnloginButton();
		String actualWarningMessage = loginpage.retriveEmailPasswordNotMatchingWarningMessageText();
		String ExpectedWarningMessage = dataprop.getProperty("EmailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(ExpectedWarningMessage), "Expected warning message is not displayed");
	
}
	
	@Test(priority =4)
	public void verifyLoginWithInValidPassword() throws InterruptedException {
		
		loginpage.enterEmailAddress(prop.getProperty("validEmail"));
		loginpage.enterPassword(dataprop.getProperty("invalidpassword"));
		loginpage.clickOnloginButton();
		String actualWarningMessage = loginpage.retriveEmailPasswordNotMatchingWarningMessageText();
		String ExpectedWarningMessage = dataprop.getProperty("EmailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(ExpectedWarningMessage), "Expected warning message is not displayed");
	
}
	
	@Test(priority =5)
	public void verifyLoginWithoutcredentials() throws InterruptedException {
		
		loginpage.clickOnloginButton();
		String actualWarningMessage = loginpage.retriveEmailPasswordNotMatchingWarningMessageText();
		String ExpectedWarningMessage = dataprop.getProperty("EmailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(ExpectedWarningMessage), "Expected warning message is not displayed");
	
	}
}
