package com.tutorialsNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessPage {

	WebDriver driver;
	
	//Objects
	
	@FindBy(xpath = "//div[@id='content']/h1")
	private WebElement successHeadingText; 
	
	public SuccessPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}
	
	//Actions
	
	public String retrivesuccessHeadingText() {
		String successText = successHeadingText.getText();
		return successText;
		
	}
}
