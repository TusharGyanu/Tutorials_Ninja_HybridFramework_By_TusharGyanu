package com.tutorialsNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {


	WebDriver driver;
	
	//Objects
	
	@FindBy(id = "input-firstname")
	private WebElement firstNameFeild;
	
	@FindBy(id = "input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id = "input-email")
	private WebElement emailAddressField;
	
	@FindBy(id = "input-telephone")
	private WebElement  inputTelephoneNumberField;
	
	@FindBy(id = "input-password")
	private WebElement  inputPasswordField;
	
	@FindBy(id = "input-confirm")
	private WebElement  inputConfirmPasswordOption;
	
	@FindBy(xpath = "//input[@name = 'newsletter'][@value = '1']")
	private WebElement yesNewsLetterOption;
	
	@FindBy(name = "agree")
	private WebElement selectPrivacyPolicyOption;
	
	@FindBy(xpath = "//input[@value = 'Continue']")
	private WebElement clickOnContinueButton;

	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath = "//input[@id = 'input-firstname']/following-sibling::div")
	private WebElement firsrNameWarning;
	
	@FindBy(xpath = "//input[@id = 'input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath = "//input[@id = 'input-email']/following-sibling::div")
	private WebElement emailAddressWarning;
	
	@FindBy(xpath = "//input[@id = 'input-telephone']/following-sibling::div")
	private WebElement telephoneNumberWarning;
	
	@FindBy(xpath = "//input[@id = 'input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//Actions
	
	public void enterFirstName(String firstNameString) {
		firstNameFeild.sendKeys(firstNameString);
	}
	
	public void enterLastName(String LastNameString) {
		lastNameField.sendKeys(LastNameString);
	}
	
	public void enterEmailAddressField(String EmailAddress) {
		emailAddressField.sendKeys(EmailAddress);
	}
	
	public void enterTelephoneNumberField(String TelephoneNumber) {
		inputTelephoneNumberField.sendKeys(TelephoneNumber);
	}
	
	public void enterPasswordField(String PasswordFeild) {
		inputPasswordField.sendKeys(PasswordFeild);
	}
	
	public void enterConfirmPasswordField(String ConfirmPasswordFeild) {
		inputConfirmPasswordOption.sendKeys(ConfirmPasswordFeild);
	}
	
	public void yesNewsLetterOption() {
		yesNewsLetterOption.click();
	}
	
	public void clickPrivacyPolicyButton() {
		selectPrivacyPolicyOption.click();
	}
	
	public SuccessPage clickContinueButton() {
		clickOnContinueButton.click();
		return new SuccessPage(driver);
	}
	
	public String retriveDuplicateEmailAddressWarning() {
		String duplicateEmailWarning = duplicateEmailAddressWarning.getText();
		return duplicateEmailWarning;
	}

	public String retrivePrivacyPolicyWarning() {
		String privacyPolicyWarning_variable = privacyPolicyWarning.getText();
		return privacyPolicyWarning_variable;
	}
	
	public String retriveFirstNameWarning() {
		String firstNameWarningvariable = firsrNameWarning.getText();
		return firstNameWarningvariable;
	}
	
	public String retriveLastNameWarning() {
		String lastNameWarningvariable = lastNameWarning.getText();
		return lastNameWarningvariable;
	}
	
	public String retriveTelephoneNumberWarning() {
		String telephoneNumberWarningvariable = telephoneNumberWarning.getText();
		return telephoneNumberWarningvariable;
	}
	
	public String retriveEmailAddressWarning() {  
		String emailAddressWarningvariable = emailAddressWarning.getText();
		return emailAddressWarningvariable;
	}
	
	public String retrivePasswordWarning() {
		String passwordWarningvariable = passwordWarning.getText();
		return passwordWarningvariable;
	}
	
	
}
