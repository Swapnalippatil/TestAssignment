package com.pageobject;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;
import utility.ConfigFileReader;
import utility.Utility;

public class RegisterPage {
	WebDriver driver;
	ConfigFileReader configReader;
	Utility utilObj;
	String firstName;
	
	@FindBy(id = "firstname")
	private WebElement txtFirstname;
	
	@FindBy(id="surname")
	private WebElement txtLastName;
	
	@FindBy(id = "email")
	private WebElement txtEmail;
	
	@FindBy(id = "email2")
	private WebElement txtEmail2;
	
	@FindBy(id = "password")
	private WebElement txtPassword;
	
	@FindBy(id = "password2")
	private WebElement txtPassword2;
	
	@FindBy(id = "telno1")
	private WebElement txtMobileNumber;
	
	
//	@FindBys(@FindBy(className ="//p[@class='news-options']//span")) 
//	List<WebElement> listCheckBox;
	
	@FindBy(name = "registerButton")
	private WebElement btnRegister;
	
	@FindBy(xpath = "//a[contains(text(),'Register')]")
	private WebElement linkRegister;
	
	@FindBy(xpath = "//h1[contains(text(),'Register')]")
	private WebElement labelRegister;
	
	@FindBy(linkText ="TAKEALOT")
	private WebElement linkLogo;

	@FindBy(xpath = "//div[@class='header-module_bottom-banners-container_3F8RC']//button")
	private WebElement banner;
	
	@FindBy(xpath = "//div[@id='welcome']/h3")
	private WebElement txtWelcome;
	
	@FindBy(xpath = "//a[contains(text(),'here')]")
	private WebElement linkContinueShopping;
	
	@FindBy(className = "inner-inner")
	private WebElement errorMessage;
	
	@FindBy(xpath = "//div[@class='auto cell']//ul//li[1]")
	private WebElement registeredUser;
	
	public RegisterPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		configReader = new ConfigFileReader();
	}
	
	public void checkBoxNewsletter(WebDriver driver, String option) {
		driver.findElement(By.xpath("//p[@class='news-options']//input[@name='newsletters["+option+"]']")).click();
	}
	@FindBy(xpath = "//label[contains(text(),'Electronics')]")
	private WebElement checkBoxNewsletter;
	
	public void verifyRegisteredUser(WebDriver driver) {
		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(registeredUser));
		Assert.assertTrue(registeredUser.getText().contains(firstName));
	}
	
	public void getConfimationText() {
		String strWelcome = txtWelcome.getText();
		Assert.assertEquals(strWelcome,"Welcome to the TAKEALOT.com family!");
	}
	
	public void linkClickHere() {
		linkContinueShopping.click();
	}
	
	public void fillUserDetails(DataTable userDetails,WebDriver driver) {
		Utility utilObj = new Utility();
		//code to handle Data Table
		List<Map<String,String>> data = userDetails.asMaps(String.class,String.class);
		String password1 = utilObj.decodePwd(data.get(0).get("Password"));
		String password2 = utilObj.decodePwd(data.get(0).get("ReTypePassword"));
		
		firstName = data.get(0).get("FirstName");
		txtFirstname.sendKeys(firstName); 
	    txtLastName.sendKeys(data.get(0).get("LastName"));
	    txtEmail.sendKeys(data.get(0).get("Email"));
	    txtEmail2.sendKeys(data.get(0).get("ReTypeEmail")); 
	    txtPassword.sendKeys(password1);
	    txtPassword2.sendKeys(password2);
	    txtMobileNumber.sendKeys(data.get(0).get("MobileNumber"));
	    checkBoxNewsletter(driver,"books");
	}
	
	public void clickRegisterLink(WebDriver driver) {
		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(linkRegister));
		banner.click();//Clicking to close footer banner
		linkRegister.click();
	}
	
	public void clickRegisterButton() {
		btnRegister.click();
	}
	
	public void getErrorMessage() {
		String strError = errorMessage.getText();
		Assert.assertTrue(strError.contains("email"));
	}
	
	public void verifyRegiterLabel() {
		Assert.assertEquals("Register", labelRegister.getText());
	}
}
