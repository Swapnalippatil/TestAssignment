package com.pageobject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ConfigFileReader;
import utility.Utility;

public class SearchPage {
	WebDriver driver;
	ConfigFileReader configReader;
	Utility utilObj;
	
	@FindBy(name = "search")
	private WebElement txtSearch;
	
	@FindBy(xpath = "//button[contains(@class,'button search-btn search-icon')]")
	private WebElement searchButton;
	
	
	@FindBys(@FindBy(xpath = "//div[contains(@class,'search-product grid ')]"))
	List<WebElement> searchProductGrid;
	
	@FindBy(xpath = "//div[contains(@class,'product-title')]//h1")
	private WebElement productText;
	
	@FindBy(xpath = "//div[@class='  buybox-actions-module_button-cell_2dQyM buybox-actions-module_add-to-cart-cell_3fXyS']")
	private WebElement btnAddToCart;
	
	@FindBy(xpath = "//button[contains(text(),'Load More')]")
	private WebElement btnLoadMore;
	
	
	
	public SearchPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		configReader = new ConfigFileReader();
	}
	
	public void searchProduct(String searchItem) {
		txtSearch.sendKeys(searchItem);
	}
	
	public void clickSearchButton() {
		searchButton.click();
	}
	
	
	
	public void addToCart(WebDriver driver) {
		
		ArrayList<String> itemsToadd = new ArrayList<String>(Arrays.asList("Garmin Forerunner 45S Sports Watch Black","Garmin QuickFit 22mm Silicone Watch Band - Amp Yellow"));
		while (true) {
			int count = 0;
			
			List<WebElement> results = searchProductGrid;
			for(int i=0;i<results.size();i++)  
			{
				WebElement linkP = results.get(i).findElement(By.tagName("h4"));
				String strProduct = linkP.getText();
				
				if(itemsToadd.contains(strProduct)) {
					results.get(i).findElement(By.tagName("a")).click();
					
					//Get handles of the windows
			        String mainWindowHandle = driver.getWindowHandle();
			        Set<String> allWindowHandles = driver.getWindowHandles();
			        Iterator<String> iterator = allWindowHandles.iterator();

			        // Here we will check if child window has other child windows and will fetch the heading of the child window
			        while (iterator.hasNext()) {
			            String ChildWindow = iterator.next();
			                if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
			                driver.switchTo().window(ChildWindow);
			            	WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(30));
			        		wait.until(ExpectedConditions.elementToBeClickable(productText));
			                String productTitle = productText.getText();
			                //Verify product tile from child window
			                Assert.assertEquals(productTitle, strProduct);
			                btnAddToCart.click();  //Add to cart button
			                driver.close();
			            }
			        }
			        count++;
			        driver.switchTo().window(mainWindowHandle);
				}
			}
			
			if(count==itemsToadd.size()) {
				break;
			}else {
				WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(30));
        		wait.until(ExpectedConditions.elementToBeClickable(btnLoadMore));
				btnLoadMore.click(); // Cick on load mpre to load more products
			}
		}
	}
}
