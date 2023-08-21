package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Cart_page {
	WebDriver driver;
	
	By checkout = By.xpath("//*[@id='checkout']");
	
	public Cart_page(WebDriver driver){
		this.driver=driver;
	}
	public void checkoutButton() {
		driver.findElement(checkout).click();

	}

}
