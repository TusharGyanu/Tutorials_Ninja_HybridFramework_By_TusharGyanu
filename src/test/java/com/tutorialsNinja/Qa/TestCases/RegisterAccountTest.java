package com.tutorialsNinja.Qa.TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialsNinja.Qa.base.base;
import com.tutorialsNinja.qa.pages.HomePage;
import com.tutorialsNinja.qa.pages.RegisterPage;
import com.tutorialsNinja.qa.pages.SuccessPage;
import com.tutorialsNinja.qa.utils.Utils;

public class RegisterAccountTest extends base {
	SuccessPage successpage;
	RegisterPage registerPage;
	public RegisterAccountTest () {
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void SetUpMethod() {
		
		driver = InitilizeBrowser_andOpenApplication(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccount();
		registerPage = homepage.selectRegisterOption();
	}
	
	@AfterMethod
	public void TearDown() {
		driver.quit();
	}
	
	@Test(priority = 1)
	public void VerifyRegisterWithMandatoryFeild() {

		registerPage.enterFirstName(dataprop.getProperty("FirstName"));
		registerPage.enterLastName(dataprop.getProperty("LastName"));
		registerPage.enterEmailAddressField(Utils.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumberField(dataprop.getProperty("TelephoneNumber"));
		registerPage.enterPasswordField(prop.getProperty("validPassword"));
		registerPage.enterConfirmPasswordField(prop.getProperty("validPassword"));
		registerPage.clickPrivacyPolicyButton();
		successpage = registerPage.clickContinueButton();
		
		//SuccessPage successpage = new SuccessPage(driver);
		
		String actualSuccessHeading = successpage.retrivesuccessHeadingText();
		Assert.assertEquals(actualSuccessHeading, dataprop.getProperty("AccountSuccessfullyCreatedHeading"), "Account success page is not displayed");
	
	}
	
	@Test(priority = 2)
	public void verifyRegistringAccountAllFeild() {

		registerPage.enterFirstName(dataprop.getProperty("FirstName"));
		registerPage.enterLastName(dataprop.getProperty("LastName"));
		registerPage.enterEmailAddressField(Utils.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumberField(dataprop.getProperty("TelephoneNumber"));
		registerPage.enterPasswordField(prop.getProperty("validPassword"));
		registerPage.enterConfirmPasswordField(prop.getProperty("validPassword"));
		registerPage.yesNewsLetterOption();
		registerPage.clickPrivacyPolicyButton();
		successpage = registerPage.clickContinueButton();
	
		//SuccessPage successpage = new SuccessPage(driver);
		String actualSuccessHeading = successpage.retrivesuccessHeadingText();
		Assert.assertEquals(actualSuccessHeading, dataprop.getProperty("AccountSuccessfullyCreatedHeading"), "Account success page is not displayed");
	
	}
	@Test(priority = 3)
	public void verifyRegist_registeredEmail() {
				
		registerPage.enterFirstName(dataprop.getProperty("FirstName"));
		registerPage.enterLastName(dataprop.getProperty("LastName"));
		registerPage.enterEmailAddressField(prop.getProperty("validEmail"));
		registerPage.enterTelephoneNumberField(dataprop.getProperty("TelephoneNumber"));
		registerPage.enterPasswordField(prop.getProperty("validPassword"));
		registerPage.enterConfirmPasswordField(prop.getProperty("validPassword"));
		registerPage.yesNewsLetterOption();
		registerPage.clickPrivacyPolicyButton();
		registerPage.clickContinueButton();

		String actualWarning = registerPage.retriveDuplicateEmailAddressWarning();
		Assert.assertTrue(actualWarning.contains(dataprop.getProperty("DuplicateEmailWarning")), "Warning message regarding duplicate email is not disaplyed");
		
	}
	@Test(priority = 4)
	public void verifyRegisterwithoutdetails() {

		registerPage.clickContinueButton();
				
		String actualprivacyPolicyWarning = registerPage.retrivePrivacyPolicyWarning();
		Assert.assertTrue(actualprivacyPolicyWarning.contains(dataprop.getProperty("PrivacyPolicyWarning")),  "Warning message is not displayed");
		
		String actualFirstNameWarning = registerPage.retriveFirstNameWarning();
		Assert.assertEquals(actualFirstNameWarning,dataprop.getProperty("FirstNameWarning"),"First name warning message is not displayed");
		
		String actualLastNameWarning = registerPage.retriveLastNameWarning();
		Assert.assertEquals(actualLastNameWarning,dataprop.getProperty("LastNameWarning"),"Last name warning message is not displayed");
		
		String actualEmailWarning = registerPage.retriveEmailAddressWarning();
		Assert.assertEquals(actualEmailWarning,dataprop.getProperty("EmailAddessWarning"),"Email warning message is not displayed");
		
		String actualPhoneNumberWarning = registerPage.retriveTelephoneNumberWarning();
		Assert.assertEquals(actualPhoneNumberWarning,dataprop.getProperty("TelephoneNumberWarning"),"Phone Number warning message is not displayed");
		
		String actualPasswordWarning = registerPage.retrivePasswordWarning();
		Assert.assertEquals(actualPasswordWarning,dataprop.getProperty("PasswordWarning"),"Password warning message is not displayed");
		
	}
	
	
}


