package com.pageobject;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.ConfigFileReader;

public class HomePage {

	WebDriver driver;
	ConfigFileReader configReader;
	
	@FindBy(name="search")
	private WebElement txtSearch;
	
	@FindBy(xpath="//a[contains(text(),'Register')]")
	private WebElement linkRegister;
	
	@FindBy(xpath="//button[contains(@class,'button search-btn search-icon')]")
	private WebElement btnSearch;
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		configReader = new ConfigFileReader();
	}
	
	public void verifyTitle(WebDriver driver) {
		String title = driver.getTitle().substring(0, 7);
		Assert.assertTrue(title.contains("Takealot"));
	}
	
	public void navigateToHomePage(WebDriver driver) {
		driver.get(configReader.getApplicationUrl());
	}
	
}
