package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;
	@FindBy(id = "input-lastname")
	private WebElement lastNameField;
	@FindBy(id = "input-email")
	private WebElement emailField;
	@FindBy(id = "input-telephone")
	private WebElement telephoneField;
	@FindBy(id = "input-password")
	private WebElement passwordField;
	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordField;
	@FindBy(xpath = "//input[@name='agree']")
	private WebElement agreeCheckBox;
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;
	@FindBy(xpath = "//input[@value=1 and @name='newsletter']")
	private WebElement yesNewsLetterRadioButton;
	@FindBy(xpath = "//div[contains(text(),'E-Mail')]")
	private WebElement duplicateEmailWarningText;
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyErrorText;
	@FindBy(xpath = "//div[@class='text-danger'][contains(text(),'First Name')]")
	private WebElement firstNameErrorText;
	@FindBy(xpath = "//div[@class='text-danger'][contains(text(),'Last Name')]")
	private WebElement lastNameErrorText;
	@FindBy(xpath = "//div[@class='text-danger'][contains(text(),'E-Mail')]")
	private WebElement emailErrorText;
	@FindBy(xpath = "//div[@class='text-danger'][contains(text(),'Telephone')]")
	private WebElement telephoneErrorText;
	@FindBy(xpath = "//div[@class='text-danger'][contains(text(),'Password')]")
	private WebElement passwordErrorText;

	public String getPrivacyPolicyErrorText() {
		return privacyPolicyErrorText.getText();
	}

	public String getFirstNameErrorText() {
		return firstNameErrorText.getText();
	}

	public String getLastNameErrorText() {
		return lastNameErrorText.getText();
	}

	public String getEmailErrorText() {
		return emailErrorText.getText();
	}

	public String getTelephoneErrorText() {
		return telephoneErrorText.getText();
	}

	public String getPasswordErrorText() {
		return passwordErrorText.getText();
	}

	public void enterFirstName(String firstName) {
		firstNameField.sendKeys(firstName);
	}

	public void enterLastName(String lasttName) {
		lastNameField.sendKeys(lasttName);
	}

	public void enterEmail(String email) {
		emailField.sendKeys(email);
	}

	public void enterTelephone(String telephone) {
		telephoneField.sendKeys(telephone);
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	public void enterConfirmPassword(String confirmPassword) {
		confirmPasswordField.sendKeys(confirmPassword);
	}

	public void clickOnAgreeCheckBox() {
		agreeCheckBox.click();
	}

	public AccountSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}

	public void clickOnYesNewsLetterRadioButton() {
		yesNewsLetterRadioButton.click();
	}

	public String getDuplicateEmailErrorMsg() {
		return duplicateEmailWarningText.getText();
	}
	
	public AccountSuccessPage registerWithMandatoryFields(String firstName,String lastName,String email,String telephone,String password,String confirmPassword) {
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		emailField.sendKeys(email);
		telephoneField.sendKeys(telephone);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(confirmPassword);
		agreeCheckBox.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage registerWithAllFields(String firstName,String lastName,String email,String telephone,String password,String confirmPassword) {
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		emailField.sendKeys(email);
		telephoneField.sendKeys(telephone);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(confirmPassword);
		yesNewsLetterRadioButton.click();
		agreeCheckBox.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
}
