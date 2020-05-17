package page_objects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Registration {
	
	WebDriver wd;
	Map<String, String> xPaths;
	private static final String SignUpButton = "SignUpButton";
	private static final String SignUpUsername = "SignUpUsername";
	private static final String SignUpEmail = "SignUpEmail";
	private static final String SignUpPassword = "SignUpPassword";
	private static final String SignUpConfirmPassword = "SignUpConfirmPassword";
	private static final String SignUpDropDownMenu = "SignUpDropDownMenu";
	private static final String SignUpRegisterButton = "SignUpRegisterButton";
	private String username;
	private String email;
	private String password;
	private String reason;
	
	
	public Registration(WebDriver wd, Map<String, String> xPaths, String user, String email, String pass, String reason) {
		this.wd = wd;
		this.xPaths = xPaths;
		this.username = user;
		this.email = email;
		this.password = pass;
		this.reason = reason;
		
	}

	public void clickOnSignUpButton() {
		wd.findElement(By.xpath(xPaths.get(SignUpButton))).click();
	}
	
	public void typeUsername(String key) {
		wd.findElement(By.xpath(xPaths.get(SignUpUsername))).sendKeys(key);
	}
	
	public void typeEmail(String key) {
		wd.findElement(By.xpath(xPaths.get(SignUpEmail))).sendKeys(key);
	}

	public void typePassword(String key) {
		wd.findElement(By.xpath(xPaths.get(SignUpPassword))).sendKeys(key);
	}
	
	public void typeRepeatPassword(String key) {
		wd.findElement(By.xpath(xPaths.get(SignUpConfirmPassword))).sendKeys(key);
	}
	
	public void selectDropDownMenu(String key) {
		Select ddMenu = new Select(wd.findElement(By.xpath(xPaths.get(SignUpDropDownMenu))));
		ddMenu.selectByVisibleText(reason);
	}
	
	public void clickFinishRegistration() {
		wd.findElement(By.xpath(xPaths.get(SignUpRegisterButton))).click();
	}
	
}
