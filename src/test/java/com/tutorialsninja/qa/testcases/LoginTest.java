package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;
import com.tutorilasninja.qa.base.BaseClass;
// create a new branch for working in login
//added few more lines in login tests
public class LoginTest extends BaseClass {
	public WebDriver driver;
	LoginPage loginPage;

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

	@Test(priority = 1, dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {

		AccountPage accountPage = loginPage.login(email, password);

		Assert.assertTrue(accountPage.getDisplayStatusOfEditAccountInformationOption());

	}

	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");

		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {

		loginPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));

		String actualWarningMsg = loginPage.getemailPasswordNotMatchingWarningMessage();
		String expectedWarningMsg = dataProp.getProperty("emailPasswordNotMatchingWarning");
		Assert.assertEquals(actualWarningMsg, expectedWarningMsg);

	}

	@Test
	public void verifyLoginWithInvalidEmailAndValidPassword() {

		loginPage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));

//		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
//		loginPage.enterPassword(prop.getProperty("validPassword"));
//		loginPage.clickOnLoginButton();

		String actualWarningMsg = loginPage.getemailPasswordNotMatchingWarningMessage();
		Assert.assertEquals(actualWarningMsg, dataProp.getProperty("emailPasswordNotMatchingWarning"));

	}

	@Test(priority = 3)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		loginPage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
//		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
//		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
//		loginPage.clickOnLoginButton();

		String actualWarningMsg = loginPage.getemailPasswordNotMatchingWarningMessage();
		Assert.assertEquals(actualWarningMsg, dataProp.getProperty("emailPasswordNotMatchingWarning"));

	}

	@Test(priority = 4)
	public void verifyLoginWithoutGivingEmailAndPassword() {

		loginPage.clickOnLoginButton();

		String actualWarningMsg = loginPage.getemailPasswordNotMatchingWarningMessage();
		Assert.assertEquals(actualWarningMsg, dataProp.getProperty("emailPasswordNotMatchingWarning"));

	}

}
