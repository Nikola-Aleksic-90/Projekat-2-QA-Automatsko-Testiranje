package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import page_objects.Login;
import page_objects.Products;
import utility.ExcelUtils;
import utility.ReadingFromFile;
import utility.Wait;

class ProductPriceIncreaseTest {

	public static String baseUrl = "https://sandbox.2checkout.com/sandbox";
	static WebDriver wd;
	String data;
	Products products = new Products(wd, ReadingFromFile.readXPaths());

	@BeforeAll
	static void startBrowser() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		wd = new ChromeDriver();
		wd.manage().window().maximize();
		wd.get(baseUrl);

		ExcelUtils.setExcell("ListaProizvoda.xlsx");
		ExcelUtils.setWorkSheet(0);
	}

	void login() {
		Login login = new Login(wd, ReadingFromFile.readXPaths());
		Wait.waitN(1.0);
		login.typeUsername("Nikola0001");
		Wait.waitN(1.0);
		login.typePassword("Nikola00001");
		Wait.waitN(1.0);
		login.clickOnLoginButton();
		Wait.waitN(1.0);
	}

	void addSingleProductFromExcel(int productNumberInExcel) {

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

	String increasePrice(int productNumberInExcel, double priceChange) {

		String priceOnPage = wd.findElement(
				By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[2]/form[1]/table[1]/tbody[1]/tr["
						+ (productNumberInExcel + 1) + "]/td[7]"))
				.getAttribute("innerHTML");

		double oldPrice = Double.parseDouble(priceOnPage);
		double newPrice = oldPrice + priceChange;
		data = Double.toString(newPrice);
		return data;
	}


	@Test
	@Order(1)
	void proba() {

		login();
		int productNumberInExcel = 1;
		addSingleProductFromExcel(productNumberInExcel);
		data = increasePrice(productNumberInExcel, 200.00);
		Wait.waitN(1.0);
		products.clickOnEditProduct(productNumberInExcel);
		Wait.waitN(1.0);
		products.typeProductPrice(data);
		Wait.waitN(1.0);
		products.clickOnSubmit();
		Wait.waitN(1.0);
		products.clickOnProducts();
		Wait.waitN(1.0);
		

	}

}
