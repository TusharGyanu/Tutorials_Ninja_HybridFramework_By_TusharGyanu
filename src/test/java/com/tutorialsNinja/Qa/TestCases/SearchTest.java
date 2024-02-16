package com.tutorialsNinja.Qa.TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialsNinja.Qa.base.base;
import com.tutorialsNinja.qa.pages.HomePage;
import com.tutorialsNinja.qa.pages.SearchPage;
//Updated Comment
public class SearchTest extends base {
	
	SearchPage searchpage;
	
	public SearchTest () {
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver = InitilizeBrowser_andOpenApplication(prop.getProperty("browser"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@Test (priority = 1)
	public void verifySearchWithValidProducts() {
		
		HomePage homepage = new HomePage(driver);
		homepage.enterProductNameInSearchBox(dataprop.getProperty("ValidProduct"));
		searchpage = homepage.clickOnSearchButton();
		
		//SearchPage searchpage = new SearchPage(driver);
		Assert.assertTrue(searchpage.displayStatusOf_HP_Product(),"Valid product HP is not displayed in search criteria");
		
	}
	//intentionally Failed
	@Test (priority = 2)
	public void verifySearchWithInvalidProductName() {
		
		HomePage homepage = new HomePage(driver);
		homepage.enterProductNameInSearchBox(dataprop.getProperty("InvalidProduct"));
		searchpage = homepage.clickOnSearchButton();
		String actualSearchMessage = searchpage.retriveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage, dataprop.getProperty("NoProductWarning") , "No product is search result is not diaplayed");
	}
	//intentionally Failed
	@Test(priority =3)
	public void verifySearchWithoutAnyProduct() {
		
		HomePage homepage = new HomePage(driver);
		searchpage = homepage.clickOnSearchButton();
		String actualSearchMessage = searchpage.retriveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage, dataprop.getProperty("NoProductWarning") , "No product is search result is not diaplayed");
	}
}
