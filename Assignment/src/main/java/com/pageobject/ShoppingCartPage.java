package com.pageobject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import utility.ConfigFileReader;

public class ShoppingCartPage {

	WebDriver driver;
	ConfigFileReader configReader;
	
	@FindBy(xpath = "//div[contains(@class,'mini-cart mini-cart-module_mini-cart_3_CNC')]//button")
	private WebElement btnCart;
	
	@FindBy(xpath = "//p[contains(text(),'Your shopping cart is empty')]")
	private WebElement emptyCart;
	
	@FindBys(@FindBy(xpath = "//div[contains(@class,'transition-height-module_item_1ikpj')]//h1"))
	List<WebElement> cartProductList;
	
	public ShoppingCartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		configReader = new ConfigFileReader();
	}
	
	public void verifyCart() {
		ArrayList<String> itemsToadd = new ArrayList<String>(Arrays.asList("Garmin Forerunner 45S Sports Watch Black","Garmin QuickFit 22mm Silicone Watch Band - Amp Yellow"));
		btnCart.click(); //clicking on cart
		
		List<WebElement> cartResults = cartProductList;
		if(cartResults.size()>0) {
			for(int i = 0;i<cartResults.size();i++) {
				Assert.assertTrue(cartResults.get(i).getText() +" is present the cart",itemsToadd.contains(cartResults.get(i).getText()));
			}
		}else {
			VerifyEmptyCart();
		}
		
	}
	
	public void VerifyEmptyCart() {
		Assert.assertEquals(emptyCart.getText().equals("Your shopping cart is empty"),true);
	}
}
