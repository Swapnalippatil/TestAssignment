package utility;

import java.time.Duration;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {
//	
//	public static void main(String[] args) throws InterruptedException {
//		System.out.println(decodePwd("[B@3ac3fd8b"));
//	}
	
	public void encodePwd(String password) {
		byte[] encodePwd = Base64.encodeBase64(password.getBytes());
		System.out.println(encodePwd);
	}
	
	public String decodePwd(String password) {
		byte[] decodePwd = Base64.decodeBase64(password);
		return(new String(decodePwd));
	}
	
	public void waitToLoad(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
}


