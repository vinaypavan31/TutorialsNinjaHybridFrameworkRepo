package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;
import com.tutorilasninja.qa.base.BaseClass;

public class SearchTest extends BaseClass {
	public WebDriver driver;
	SearchPage searchPage;
	HomePage homePage;

	public SearchTest() {
		super();
	}
	
	
	//added by ramsai tester
	
	//added more comments
	
	
	

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		homePage = new HomePage(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority=1)
	public void verifySearchWithValidProduct() {

		searchPage = homePage.searchForProduct(dataProp.getProperty("validProduct"));

		Assert.assertTrue(searchPage.isProductPresent());
	}

	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		searchPage = homePage.searchForProduct(dataProp.getProperty("invalidProduct"));
		
		String actualSearchMsg = searchPage.getNoProductsMessageText();
//dataProp.getProperty("noProductMessage")
		Assert.assertEquals(actualSearchMsg, "abcd",
				"No product message in search results not displayed");
	}

	@Test(priority=3,dependsOnMethods= {"verifySearchWithInvalidProduct","verifySearchWithValidProduct"})
	public void verifySearchingWithoutAnyProduct() {

		searchPage = homePage.clickOnSearchButton();

		String actualSearchMsg = searchPage.getNoProductsMessageText();

		Assert.assertEquals(actualSearchMsg, dataProp.getProperty("noProductMessage"),
				"No product message in search results not displayed");

	}

}
