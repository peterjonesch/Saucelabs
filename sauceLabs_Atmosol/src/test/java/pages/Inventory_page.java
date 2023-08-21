package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import library.Customwait;

public class Inventory_page {

	By hamburger = By.xpath("//button[@id='react-burger-menu-btn']");
	By about = By.xpath("//*[@id='about_sidebar_link']");
	By allItems = By.xpath("//*[@id='inventory_sidebar_link']");
	By logout = By.xpath("//*[@id='inventory_sidebar_link']");
	By resetApp = By.xpath("//*[@id='reset_sidebar_link']");
	By addtocart_button = By.xpath("//*[@class='inventory_item_price' ]//following-sibling::button");
	By cart = By.xpath("//*[@id='shopping_cart_container']");
	
	
	
	WebDriver driver;
	Customwait pause;

	public Inventory_page(WebDriver driver) {
		this.driver = driver;
		 pause= new Customwait(driver);
	}
	
	
	
	public void hamburgerMenu(String select) {

		pause.expwait(hamburger);
		
		driver.findElement(hamburger).click();
		
		pause.expwait(about);

		switch (select) {
		
		case "about":
			driver.findElement(about).click();
			break;
			
		case "allItems":
			driver.findElement(allItems).click();
			break;
			
		case "logout":
			driver.findElement(logout).click();
			break;
			
		case "resetApp":
			driver.findElement(resetApp).click();
			break;
			
			default:
				System.out.println("No option selected");
		}

	}
	
public void addToCart(int index) {
	
	List<WebElement> addtocart = driver.findElements(addtocart_button);
	addtocart.get(index).click();
}

public void clickOnCart() {
	driver.findElement(cart).click();
}
}
