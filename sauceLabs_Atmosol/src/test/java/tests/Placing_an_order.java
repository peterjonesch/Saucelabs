package tests;

import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import library.Customwait;
import pages.Cart_page;
import pages.Checkout_page;
import pages.Inventory_page;
import pages.Login_page;

public class Placing_an_order {

	By products = By.xpath("//*[@class='inventory_item_price' ]");
	By aboutPage_Load = By.xpath("//*[@id='__next']");
	By bill_title = By.xpath("//*[contains(text(),'Checkout: Overview')]");
	By cartPage_Load = By.xpath("//*[contains(text(),\"Your Cart\")]");
	By checkoutPage_Load = By.xpath("//*[contains(text(),'Checkout: Your Information')]");
	By total_Price = By.xpath("//*[@class='summary_info_label summary_total_label']");

	WebDriver driver;

	@Test
	public void test() {

		Login_page login = new Login_page();

		driver = login.loginpage();

		Inventory_page inventory = new Inventory_page(driver);
		Cart_page cartPage = new Cart_page(driver);
		Checkout_page checkout = new Checkout_page(driver);
		Customwait pause = new Customwait(driver);

//1.Login

		driver.get("https://www.saucedemo.com/");
		login.enterCreds(driver);

// 2 &3.navigate to about page using hamburger menu
		inventory.hamburgerMenu("about");

//4.Assertion of about page
		pause.expwait(aboutPage_Load);

		String current_site = driver.getCurrentUrl();
		String about_site = "https://saucelabs.com/";

		Assert.assertEquals(current_site, about_site);

//5. Go back to Inventory page and validate(saucedemo PRODUCTS page)
		driver.navigate().back();
		current_site = driver.getCurrentUrl();
		String invent_site = "https://www.saucedemo.com/inventory.html";

		Assert.assertEquals(current_site, invent_site);
//6.Select the item with the highest price dynamically

		Double[] prices = new Double[6];
		int i = 0;
		double highest = 0;
		int index = 0;

		List<WebElement> li = driver.findElements(products);

		for (WebElement price : li) {
			String s = price.getText();
			s = s.replace("$", "");
			prices[i] = Double.parseDouble(s);

			if (prices[i] > highest) {
				highest = prices[i];
				index = i;
			}

			i = i + 1;

		}
		inventory.addToCart(index);

//7.Click on the cart button available at the top right corner

		inventory.clickOnCart();

//8.Validate if you are taken to YOUR CART page
		pause.expwait(cartPage_Load);

		current_site = driver.getCurrentUrl();
		String cart_site = "https://www.saucedemo.com/cart.html";

		Assert.assertEquals(current_site, cart_site);
//9.Click Checkout
		cartPage.checkoutButton();

//10.Validate if you are navigated to CHECKOUT:YOUR INFORMATION page

		pause.expwait(checkoutPage_Load);

		current_site = driver.getCurrentUrl();
		String checkout_site = "https://www.saucedemo.com/checkout-step-one.html";

		Assert.assertEquals(current_site, checkout_site);

//11.Enter details and click continue
		checkout.firstName("Jacob");
		checkout.lastName("Adams");
		checkout.postalCode("500026");
		checkout.continueButton();

//12.Validate if you are navigated to CHECKOUT: OVERVIEW & Total Price is shown in $xx.yy format

		String checkoutOverview_Title = driver.findElement(bill_title).getText();

		Assert.assertEquals("Checkout: Overview", checkoutOverview_Title);

		boolean check = false;

		pause.expwait(total_Price);

		String total_price = driver.findElement(total_Price).getText();

		total_price = total_price.replaceAll(".*[A-Z|a-z|:|\\s]", "");

		check = Pattern.matches("[$]{1}[\\d][\\d][.][\\d][\\d]", total_price); // using REGEX pattern to validate

		Assert.assertEquals(check, true);

	}

}
