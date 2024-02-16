package com.tutorialsNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;
	
	@FindBy(linkText = "HP LP3065")
	private WebElement valid_HP_Product;
	
	@FindBy(xpath = "//div[@id='content']/h2/following-sibling::p")
	private WebElement noProductMessage;
	
	
	public SearchPage(WebDriver driver){
	this.driver = driver;
	PageFactory.initElements(driver, this);
}
	
	public String retriveNoProductMessageText() {
		String noProductMessageText= noProductMessage.getText();
		return noProductMessageText;
	}
	
	public boolean displayStatusOf_HP_Product() {
		boolean displayStatus = valid_HP_Product.isDisplayed();
		return displayStatus;
	}
}
