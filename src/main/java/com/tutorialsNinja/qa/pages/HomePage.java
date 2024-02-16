package com.tutorialsNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	//Objects
	@FindBy(xpath = "//span[text()=\"My Account\"]")
	private WebElement MyAccountDropMenu;
	
	@FindBy(linkText = "Login")
	private WebElement loginOption;
	
	@FindBy(linkText = "Register")
	private WebElement registerOption;
	
	@FindBy(name = "search")
	private WebElement searchBoxField;
	
	@FindBy(xpath = "//div[@id='search']/descendant::button")
	private WebElement searchButton; 
	
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public SearchPage clickOnSearchButton() {
		searchButton.click();
		return new SearchPage(driver);
	}
	
	public void enterProductNameInSearchBox(String productText) {
		searchBoxField.sendKeys(productText);
	}
	
	public void clickOnMyAccount() {
		MyAccountDropMenu.click();
	}
	
	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public LoginPage navigateToLoginPage() {
		MyAccountDropMenu.click();
		loginOption.click();
		return new LoginPage(driver);
	}
	
	
	public RegisterPage selectRegisterOption() {
		registerOption.click();
		return new RegisterPage(driver);
	}
	
}
