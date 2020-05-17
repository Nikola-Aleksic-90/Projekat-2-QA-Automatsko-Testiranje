package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import page_objects.Login;
import page_objects.Products;
import utility.ExcelUtils;
import utility.ReadingFromFile;
import utility.Wait;

class AddingProductsTest {

	public static String baseUrl = "https://sandbox.2checkout.com/sandbox";
	static WebDriver wd;
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
		//startBrowser();
		Login login = new Login(wd, ReadingFromFile.readXPaths());
		Wait.waitN(1.0);
		login.typeUsername("Nikola0001");
		Wait.waitN(1.0);
		login.typePassword("Nikola00001");
		Wait.waitN(1.0);
		login.clickOnLoginButton();
		Wait.waitN(1.0);
	}

	boolean checkName(int i) {
		
		String productOnPage = wd.findElement(By.xpath("//tr[" + i + "]//td[6]//span")).getAttribute("innerHTML");
		System.out.println(productOnPage);
		boolean proizvod = productOnPage.equals(ExcelUtils.getDataAt((i-2), 0));
		return proizvod;
	}
	
	boolean checkPrice(int i) {
		
		String priceOnPage = wd.findElement(
					By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[2]/form[1]/table[1]/tbody[1]/tr["
					+ i + "]/td[7]")).getAttribute("innerHTML");
		
		double site = Double.parseDouble(priceOnPage);
		double excel = Double.parseDouble(ExcelUtils.getDataAt((i-2), 1));
		
		boolean cena = (site == excel);
		return cena;
		
	}

	void addingProducts() {
		
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
	
	
	@Test
	void AddingProducts() {

		login();
		addingProducts();

		for (int i = 2; i < (ExcelUtils.getRowNumber() + 2); i++) {
			boolean nameOfProduct = checkName(i);
			boolean priceOfProduct = checkPrice(i);
			assertTrue(nameOfProduct);  
			assertTrue(priceOfProduct);
			System.out.println("Proizvod " + ExcelUtils.getDataAt(i-2, 0) + " je uspesno dodat i cena je: " + ExcelUtils.getDataAt(i-2, 1));
		}
		
	}
	
	
	
	@Test
	void RemovingProducts() {
		
		Wait.waitN(1.0);
		
		for(int i = 2; i < (ExcelUtils.getRowNumber() + 2); i++) {
			wd.findElement(By.xpath("//tr[" + i + "]//td[1]//input[1]")).click();
		}
		
		Wait.waitN(1.0);
		products.clickOnDeleteProducts();
		Wait.waitN(1.0);
		products.clickOnConfirmDelete();
		Wait.waitN(1.0);
		products.clickOnProducts();
		Wait.waitN(1.0);
		
		String str1 = wd.findElement(By.xpath("//span[@class='form_invalid']")).getAttribute("innerHTML");
		String str2 = "No Records Found!";
		assertEquals(str1, str2);
		
		wd.quit();
	}

}
