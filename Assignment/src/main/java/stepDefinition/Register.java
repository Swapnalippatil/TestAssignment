package stepDefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.pageobject.RegisterPage;
import com.pageobject.SearchPage;
import com.pageobject.ShoppingCartPage;
import com.pageobject.HomePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.PageObjectManager;
import utility.ConfigFileReader;

public class Register {
	WebDriver driver;
	RegisterPage registerPage;
	HomePage homePage;
	ShoppingCartPage cartPage;
	SearchPage searchPage;
	PageObjectManager pageObjManager;
	ConfigFileReader configReader;
	
	@When("User clicks on Register link")
	public void user_clicks_on_register_link() throws InterruptedException {
		pageObjManager = new PageObjectManager(driver);
		registerPage = pageObjManager.getRegisterPage();
		registerPage.clickRegisterLink(driver);
	}

	@When("User enters details")
	public void User_enters_details(io.cucumber.datatable.DataTable dataTable) {
		registerPage.fillUserDetails(dataTable,driver);;
	}

	@When("User clicks on register button")
	public void user_clicks_on_register_button() {
		registerPage.clickRegisterButton();
	}

	@Then("Verify success message on the page")
	public void verify_success_message_on_the_page() {
		registerPage.getConfimationText();
	}

	@Then("Click on the here link to continue shopping")
	public void click_on_the_here_link_to_continue_shopping() {
		registerPage.linkClickHere();
	}
	
	@Then("Verify registered user name on the home page")
	public void verify_registered_user_name_on_the_home_page() throws InterruptedException {
		registerPage.verifyRegisteredUser(driver);
	}
	
	@When("User on the home page and search for {string}")
	public void user_on_the_home_page_and_search_for(String string) {
		searchPage = pageObjManager.getSearchPage();
		searchPage.searchProduct(string);
		searchPage.clickSearchButton();
	}
	
	@When("User adds the product to cart")
	public void user_adds_the_product_to_cart() throws InterruptedException {
		searchPage.addToCart(driver);
	}
	
	@Then("validate cart for added products")
	public void validate_cart_for_added_products() {
		cartPage = pageObjManager.getShoppingCartPage();
		cartPage.verifyCart();
	}
	
	@Then("Verify error message on the page")
	public void verify_error_message_on_the_page() {
		registerPage.getErrorMessage();
	}
	
	@Given("User on the Takealot home page")
	public void User_on_the_takealot_home_page() {
		configReader = new ConfigFileReader();
		System.setProperty("webdriver.chrome.driver", configReader.getDriverPath());
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		pageObjManager = new PageObjectManager(driver);
		homePage = pageObjManager.getHomePage();
		homePage.navigateToHomePage(driver);
	}

}
