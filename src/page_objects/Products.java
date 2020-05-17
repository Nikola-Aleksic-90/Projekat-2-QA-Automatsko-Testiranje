package page_objects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Products {
	
	WebDriver wd;
	Map<String, String> xPaths;
	private static final String Products = "Products";
	private static final String AddNewProducts = "AddNewProducts";
	private static final String ProductName = "ProductName";
	private static final String ProductPrice = "ProductPrice";
	private static final String Submit = "Submit";
	private static final String DeleteSelected = "DeleteSelected";
	private static final String ConfirmDelete = "ConfirmDelete";
	
	public Products(WebDriver wd, Map<String, String> xPaths) {
		this.wd = wd;
		this.xPaths = xPaths;
	}
	
	public void clickOnProducts() {
		wd.findElement(By.xpath(xPaths.get(Products))).click();
	}
	
	public void clickOnAddNewProducts() {
		wd.findElement(By.xpath(xPaths.get(AddNewProducts))).click();
	}
	
	public void typeProductName(String data) {
		wd.findElement(By.xpath(xPaths.get(ProductName))).clear();
		wd.findElement(By.xpath(xPaths.get(ProductName))).sendKeys(data);
	}

	public void typeProductPrice(String data) {
		wd.findElement(By.xpath(xPaths.get(ProductPrice))).clear();
		wd.findElement(By.xpath(xPaths.get(ProductPrice))).sendKeys(data);
	}

	public void clickOnSubmit() {
		wd.findElement(By.xpath(xPaths.get(Submit))).click();
	}
	
	public void clickOnDeleteProducts() {
		wd.findElement(By.xpath(xPaths.get(DeleteSelected))).click();
	}
	
	public void clickOnConfirmDelete() {
		wd.findElement(By.xpath(xPaths.get(ConfirmDelete))).click();
	}
	
	public void clickOnEditProduct(int productNumberInExcel) {
		wd.findElement(By.xpath("//tr[" + (productNumberInExcel+1) + "]//td[2]//a[1]")).click();
	}
	
}
