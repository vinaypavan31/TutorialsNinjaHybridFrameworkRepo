package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage { 
	WebDriver driver;
	
	@FindBy(linkText="HP LP3065")
	private WebElement hpProduct;
	
	@FindBy(xpath="//input[@id='button-search']/following-sibling::p")
	private WebElement noProductsText;
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isProductPresent() {
		return hpProduct.isDisplayed();
	}
	
	public String getNoProductsMessageText() {
		return noProductsText.getText();
	}
	
	
	
	
	
	
	
	
}
