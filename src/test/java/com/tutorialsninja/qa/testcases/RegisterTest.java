package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;
import com.tutorilasninja.qa.base.BaseClass;

//added code in register in from ram--added from cg laptop

public class RegisterTest extends BaseClass {

	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;

	public RegisterTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisteringAccountWithMandatoryFields() {

//		registerPage.enterFirstName(dataProp.getProperty("firstName"));
//		registerPage.enterLastName(dataProp.getProperty("lastName"));
//		registerPage.enterEmail(Utilities.generateEmailWithTimeStamp());
//		registerPage.enterTelephone(dataProp.getProperty("telephone"));
//		registerPage.enterPassword(prop.getProperty("validPassword"));
//		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
//		registerPage.clickOnAgreeCheckBox();
		AccountSuccessPage accountSuccessPage = registerPage.registerWithMandatoryFields(
				dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephone"),
				prop.getProperty("validPassword"), prop.getProperty("validPassword"));

		String actualHeading = accountSuccessPage.getPageHeading();

		Assert.assertEquals(actualHeading, dataProp.getProperty("accSuccessfullyCreatedHeading"),
				"Account successs page is not displayed");

	}

	@Test(priority = 2)
	public void verifyRegisteringAccountByProvidingAllFields() {

		AccountSuccessPage accountSuccessPage = registerPage.registerWithMandatoryFields(
				dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephone"),
				prop.getProperty("validPassword"), prop.getProperty("validPassword"));

		String actualHeading = accountSuccessPage.getPageHeading();

		Assert.assertEquals(actualHeading, dataProp.getProperty("accSuccessfullyCreatedHeading"));

	}

	@Test
	public void verifyRegisteringAccountWithExistingEmailAddress() {
		registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				prop.getProperty("validEmail"), dataProp.getProperty("telephone"), prop.getProperty("validPassword"),
				prop.getProperty("validPassword"));

		String actualMsg = registerPage.getDuplicateEmailErrorMsg();

		Assert.assertEquals(actualMsg, dataProp.getProperty("duplicateEmailWarning"),
				"Warning Message regarding duplicate email address not displayed");

	}

	@Test
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {

		registerPage.clickOnContinueButton();

		String actualPrivacyPolicyWarningMsg = registerPage.getPrivacyPolicyErrorText();
		String actualFirstNameWarningMsg = registerPage.getFirstNameErrorText();
		String actualLastNameWarningMsg = registerPage.getLastNameErrorText();
		String actualEmailWarningMsg = registerPage.getEmailErrorText();
		String actualTelephoneWarningMsg = registerPage.getTelephoneErrorText();
		String actualPasswordWarningMsg = registerPage.getPasswordErrorText();

		Assert.assertTrue(actualPrivacyPolicyWarningMsg.contains(dataProp.getProperty("privacyPolicyWarning")),
				"Privacy Policy Warning Message is not Displayed");
		Assert.assertEquals(actualFirstNameWarningMsg, dataProp.getProperty("firstNameWarning"),
				"First Name warning message is not displayed");
		Assert.assertEquals(actualLastNameWarningMsg, dataProp.getProperty("lastNameWarning"),
				"Last Name warning message is not displayed");

		Assert.assertEquals(actualEmailWarningMsg, dataProp.getProperty("emailWarning"),
				"Email warning message is not displayed");
		Assert.assertEquals(actualTelephoneWarningMsg, dataProp.getProperty("telephoneWarning"),
				"Telephone warning message is not displayed");
		// dataProp.getProperty("passwordWarning")
		Assert.assertEquals(actualPasswordWarningMsg, dataProp.getProperty("passwordWarning"),
				"Password warning message is not displayed");

	}
}
