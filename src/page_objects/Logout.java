package page_objects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Logout {
	
	WebDriver wd;
	Map<String, String> xPaths;
	private static final String AccountProfile = "AccountProfile";
	private static final String LogedUser = "LogedUser";
	private static final String LogOutButton = "LogOutButton";

	public Logout(WebDriver wd, Map<String, String> xPaths) {
		this.wd = wd;
		this.xPaths = xPaths;
	}

	public void clickOnProfileButton() {
		wd.findElement(By.xpath(xPaths.get(AccountProfile))).click();
	}
	
	public String checkLoggedUser() {
		String currentlyLoggedUser = wd.findElement(By.xpath(xPaths.get(LogedUser))).getAttribute("innerHTML");
		return currentlyLoggedUser;
	}
	
	public void clickOnLogOut() {
		wd.findElement(By.xpath(xPaths.get(LogOutButton))).click();
	}

}
