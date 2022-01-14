//package stepDefinition;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import com.pageobject.HomePage;
//import com.pageobject.RegisterPage;
//import com.pageobject.SearchPage;
//import com.pageobject.ShoppingCartPage;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import managers.PageObjectManager;
//import utility.ConfigFileReader;
//
//public class SearchProduct {
//	
//	WebDriver driver;
//	RegisterPage registerPage;
//	HomePage homePage;
//	ShoppingCartPage cartPage;
//	SearchPage searchPage;
//	ConfigFileReader configReader;
//	PageObjectManager pageObjManager;
//
//		@Given("User on the Takealot home page")
//		public void User_on_the_takealot_home_page() {
//			configReader = new ConfigFileReader();
//			System.setProperty("webdriver.chrome.driver", configReader.getDriverPath());
//			
//			driver = new ChromeDriver();
//			driver.manage().window().maximize();
//			pageObjManager = new PageObjectManager(driver);
//			homePage = pageObjManager.getHomePage();
//			homePage.navigateToHomePage(driver);
//		}
//
//		@When("User search for {string}")
//		public void user_search_for(String string) {
//			searchPage = pageObjManager.getSearchPage();
//			searchPage.searchProduct(string);
//			searchPage.clickSearchButton();
//		}
//
//		@When("User add the product to cart")
//		public void user_add_the_product_to_cart() {
//			searchPage.addToCart(driver);
//		}
//
//		@Then("Ualidate cart for added products")
//		public void validate_cart_for_added_products() {
//			cartPage = pageObjManager.getShoppingCartPage();
//			cartPage.verifyCart();
//		}
//
//}
