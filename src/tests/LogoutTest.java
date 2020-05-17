package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import page_objects.Login;
import page_objects.Logout;
import utility.ReadingFromFile;
import utility.Wait;

class LogoutTest {

	public static String baseUrl = "https://sandbox.2checkout.com/sandbox";
	static WebDriver wd;
	static String loggedUser;
	String currentURL;
	
	void startBrowser() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		wd = new ChromeDriver();
		wd.manage().window().maximize();
		wd.get(baseUrl);	
	}
	
	void login() {
		startBrowser();
		Login login = new Login(wd, ReadingFromFile.readXPaths());
		Wait.waitN(1.0);
		login.typeUsername("Nikola0001");
		Wait.waitN(1.0);
		login.typePassword("Nikola00001");
		Wait.waitN(1.0);
		login.clickOnLoginButton();
		Wait.waitN(1.0);
	}
	
	void logout() {
		Logout logout = new Logout(wd, ReadingFromFile.readXPaths());
		Wait.waitN(1.0);
		logout.clickOnProfileButton();
		Wait.waitN(1.0);
		loggedUser = logout.checkLoggedUser();
		Wait.waitN(1.0);		
		logout.clickOnLogOut();
		Wait.waitN(1.0);
		currentURL = wd.getCurrentUrl();
	}
	
	
	@Test
	@Order(1) 
	void Logout() {
		
		login();
		logout();
		
		assertEquals(loggedUser, "Nikola0001");
		System.out.println("User " + loggedUser + " has successfully logged out");
		assertEquals(baseUrl, currentURL);
		System.out.println("Successfull navigation to home page after logout");
		wd.quit();
		
	}

}
