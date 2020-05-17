package tests;

import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import page_objects.Registration;
import utility.ReadingFromFile;
import utility.Wait;

class RegisterOneUserTest {

	public static String baseUrl = "https://sandbox.2checkout.com/sandbox";
	static WebDriver wd;
	
	String user = "Nikola0001";
	String email = "nikola0001@nikola0001.com";
	String pass = "Nikola00001";		// confirm password je isti podatak
	String reason = "I just want to play in the sandbox";
	
	@BeforeAll
	static void startBrowser() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		wd = new ChromeDriver();
		wd.manage().window().maximize();
		wd.get(baseUrl);
	}
	
	@Test
	void RegisterOne() {
		
		Registration register1 = new Registration(wd, ReadingFromFile.readXPaths(), user, email, pass, reason);
		Wait.waitN(1.0);
		register1.clickOnSignUpButton();
		Wait.waitN(1.0);
		register1.typeUsername(user);
		Wait.waitN(1.0);
		register1.typeEmail(email);
		Wait.waitN(1.0);
		register1.typePassword(pass);
		Wait.waitN(1.0);
		register1.typeRepeatPassword(pass);
		Wait.waitN(1.0);
		register1.selectDropDownMenu(reason);
		Wait.waitN(1.0);
		register1.clickFinishRegistration();
		Wait.waitN(5.0);
		wd.quit();

	}

}
