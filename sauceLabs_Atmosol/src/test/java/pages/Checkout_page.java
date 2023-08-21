package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Checkout_page {
	
	By first_Name = By.xpath("//*[@id='first-name']");
	By last_Name = By.xpath("//*[@id='last-name']");
	By postalCode = By.xpath("//*[@id='postal-code']");
	By continue_button = By.xpath("//*[@id='continue']");
	
	WebDriver driver;
	
	public Checkout_page(WebDriver driver){
		this.driver=driver;
	}
	
	public void firstName(String s) {
	driver.findElement(first_Name).sendKeys(s);
	}
	public void lastName(String s) {
	driver.findElement(last_Name).sendKeys(s);
	}
	public void postalCode(String s) {
	driver.findElement(postalCode).sendKeys(s);
	}
	
	public void continueButton() {

	driver.findElement(continue_button).click();
	}
}
