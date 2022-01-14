package managers;


import org.openqa.selenium.WebDriver;

import com.pageobject.RegisterPage;
import com.pageobject.SearchPage;
import com.pageobject.ShoppingCartPage;
import com.pageobject.HomePage;

public class PageObjectManager {
	private WebDriver driver;
	private RegisterPage registerPage;
	private HomePage homePage;
	private ShoppingCartPage cartPage;
	private SearchPage searchPage;
	
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}
	
	public HomePage getHomePage() {
		if(homePage == null) {
			homePage = new HomePage(driver);
		}else {
			System.out.println(homePage);
		}
		return (homePage == null) ? homePage = new HomePage(driver) : homePage;
	}
	
	public RegisterPage getRegisterPage() {
		if(registerPage == null) {
			registerPage = new RegisterPage(driver);
		}else {
			System.out.println(registerPage);
		}
		return (registerPage == null) ? registerPage = new RegisterPage(driver) : registerPage;
	}
	
	public ShoppingCartPage getShoppingCartPage() {
		if(cartPage == null) {
			cartPage = new ShoppingCartPage(driver);
		}else {
			System.out.println(cartPage);
		}
		return (cartPage == null) ? cartPage = new ShoppingCartPage(driver) : cartPage;
	}
	
	public SearchPage getSearchPage() {
		if(searchPage == null) {
			searchPage = new SearchPage(driver);
		}else {
			System.out.println(cartPage);
		}
		return (searchPage == null) ? searchPage = new SearchPage(driver) : searchPage;
	}

}
