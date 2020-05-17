package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_objects.Login;
import utility.ReadingFromFile;
import utility.Wait;

class LoginTest {

	public static String baseUrl = "https://sandbox.2checkout.com/sandbox";
	static WebDriver wd;
	static String loggedUser;

	static void startBrowser() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		wd = new ChromeDriver();
		wd.manage().window().maximize();
		wd.get(baseUrl);
		
	}

	public static void login() {
		startBrowser();
		Login login = new Login(wd, ReadingFromFile.readXPaths());
		Wait.waitN(1.0);
		login.typeUsername("Nikola0001");
		Wait.waitN(1.0);
		login.typePassword("Nikola00001");
		Wait.waitN(1.0);
		login.clickOnLoginButton();
		Wait.waitN(1.0);
		loggedUser = login.checkLoggedUser();
	}
	
	@Test
	void Login() {
		
		login();
		assertEquals(loggedUser, "Nikola0001");
		System.out.println("User " + loggedUser + " has successfully logged in");
		wd.quit();
		
	}

}
