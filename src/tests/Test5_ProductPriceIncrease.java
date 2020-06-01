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
import page_objects.SiteNavigation;
import utility.ExcelUtils;
import utility.ReadingFromFile;
import utility.Wait;

class Test5_ProductPriceIncrease {

	/*
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
*/
/*
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
*/

	@Test
	@Order(1)
	void proba() {

		int productNumberInExcel = 1;
		double priceChange = 200.00;
		
		SiteNavigation.login();
		SiteNavigation.addSingleProductFromExcel(productNumberInExcel);
		String data = SiteNavigation.increasePrice(productNumberInExcel, priceChange);
		SiteNavigation.confirmPriceChange(productNumberInExcel);
		
	}

}
