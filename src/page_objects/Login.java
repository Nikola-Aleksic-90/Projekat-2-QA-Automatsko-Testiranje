package page_objects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
	
	WebDriver wd;
	Map<String, String> xPaths;
	private static final String LogInUsername = "LogInUsername";
	private static final String LogInPassword = "LogInPassword";
	private static final String LogInButton = "LogInButton";
	private static final String LogedUser = "LogedUser";
	
	public Login(WebDriver wd, Map<String, String> xPaths) {
		this.wd = wd;
		this.xPaths = xPaths;
	}

	public void typeUsername(String key) {
		wd.findElement(By.xpath(xPaths.get(LogInUsername))).sendKeys(key);
	}

	public void typePassword(String key) {
		wd.findElement(By.xpath(xPaths.get(LogInPassword))).sendKeys(key);
	}

	public void clickOnLoginButton() {
		wd.findElement(By.xpath(xPaths.get(LogInButton))).click();
	}
	
	public String checkLoggedUser() {
		String currentlyLoggedUser = wd.findElement(By.xpath(xPaths.get(LogedUser))).getAttribute("innerHTML");
		return currentlyLoggedUser;
	}

}
