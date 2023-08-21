package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



import io.github.bonigarcia.wdm.WebDriverManager;
import library.Customwait;

public class Login_page {
	
	WebDriver driver;
	Customwait pause;
	
	By username=By.xpath("//*[@id='user-name']");
	By password=By.xpath("//*[@id='password']");
	By submit=By.xpath("//*[@id='login-button']");
	
	
	public WebDriver loginpage() {
		
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions option=new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		driver=new ChromeDriver(option);
		
		return driver;
		
	}

	public void enterCreds(WebDriver driver )  {
	pause=new Customwait(driver);
	pause.expwait(submit);
	
		driver.findElement(username).sendKeys("standard_user");
		driver.findElement(password).sendKeys("secret_sauce");
		driver.findElement(submit).click();
		
	}

}
