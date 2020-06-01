package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utility.ExcelUtils;
import utility.ReadingFromFile;
import utility.Wait;

public class SiteNavigation {

	public static String baseUrl = "https://sandbox.2checkout.com/sandbox";
	public static WebDriver wd;
	public static String loggedUser;
	public static String currentURL;
	public static String priceOnPage;
	public static String str1;
	public static String data;

	static void startBrowser() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		wd = new ChromeDriver();
		wd.manage().window().maximize();
		wd.get(baseUrl);
		
		ExcelUtils.setExcell("ListaProizvoda.xlsx");
		ExcelUtils.setWorkSheet(0);
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

	public static void logout() {
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

	public static boolean checkName(int i) {
		String productOnPage = wd.findElement(By.xpath("//tr[" + i + "]//td[6]//span")).getAttribute("innerHTML");
		System.out.println(productOnPage);
		boolean proizvod = productOnPage.equals(ExcelUtils.getDataAt((i - 2), 0));
		return proizvod;
	}

	public static boolean checkPrice(int i) {
		priceOnPage = wd.findElement(
				By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[2]/form[1]/table[1]/tbody[1]/tr[" + i + "]/td[7]"))
				.getAttribute("innerHTML");

		double site = Double.parseDouble(priceOnPage);
		double excel = Double.parseDouble(ExcelUtils.getDataAt((i - 2), 1));

		boolean cena = (site == excel);
		return cena;

	}

	public static void addingProducts() {
		Products products = new Products(wd, ReadingFromFile.readXPaths());
		
		for (int i = 0; i < ExcelUtils.getRowNumber(); i++) {
			products.clickOnProducts();
			Wait.waitN(0.5);
			products.clickOnAddNewProducts();
			Wait.waitN(0.5);
			products.typeProductName(ExcelUtils.getDataAt(i, 0));
			Wait.waitN(0.5);
			products.typeProductPrice(ExcelUtils.getDataAt(i, 1));
			Wait.waitN(0.5);
			products.clickOnSubmit();
			Wait.waitN(1.0);
		}
		
		products.clickOnProducts();
		Wait.waitN(1.0);
	}

	public static String deleteProducts(String noProducts) {
		Products products = new Products(wd, ReadingFromFile.readXPaths());
		
		for (int i = 2; i < (ExcelUtils.getRowNumber() + 2); i++) {
			wd.findElement(By.xpath("//tr[" + i + "]//td[1]//input[1]")).click();
			Wait.waitN(0.5);
		}
		
		Wait.waitN(1.0);
		products.clickOnDeleteProducts();
		Wait.waitN(1.0);
		products.clickOnConfirmDelete();
		Wait.waitN(1.0);
		products.clickOnProducts();
		Wait.waitN(1.0);

		str1 = wd.findElement(By.xpath("//span[@class='form_invalid']")).getAttribute("innerHTML");
		return str1;
	}

	public static void addSingleProductFromExcel(int productNumberInExcel) {
		Products products = new Products(wd, ReadingFromFile.readXPaths());
		products.clickOnProducts();
		Wait.waitN(0.5);
		products.clickOnAddNewProducts();
		Wait.waitN(0.5);
		products.typeProductName(ExcelUtils.getDataAt(productNumberInExcel, 0));
		Wait.waitN(0.5);
		products.typeProductPrice(ExcelUtils.getDataAt(productNumberInExcel, 1));
		Wait.waitN(0.5);
		products.clickOnSubmit();
		Wait.waitN(1.0);
		products.clickOnProducts();
		Wait.waitN(1.0);
	}
	
	public static String increasePrice(int productNumberInExcel, double priceChange) {

		String priceOnPage = wd.findElement(
				By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[2]/form[1]/table[1]/tbody[1]/tr["
						+ (productNumberInExcel + 1) + "]/td[7]"))
				.getAttribute("innerHTML");

		double oldPrice = Double.parseDouble(priceOnPage);
		double newPrice = oldPrice + priceChange;
		data = Double.toString(newPrice);
		return data;
	}
	
	public static void confirmPriceChange(int productNumberInExcel) {
		Products products = new Products(wd, ReadingFromFile.readXPaths());
		Wait.waitN(1.0);
		products.clickOnEditProduct(productNumberInExcel);
		Wait.waitN(1.0);
		products.typeProductPrice(data);
		Wait.waitN(1.0);
		products.clickOnSubmit();
		Wait.waitN(1.0);
		products.clickOnProducts();
	}
	
	public static void closeWebDriver() {
		wd.quit();
	}

}
