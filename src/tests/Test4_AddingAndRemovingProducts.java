package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import page_objects.SiteNavigation;
import utility.ExcelUtils;

class Test4_AddingAndRemovingProducts {
	
	@Test
	void AddingProducts() {
		
		SiteNavigation.login();
		SiteNavigation.addingProducts();

		for (int i = 2; i < (ExcelUtils.getRowNumber() + 2); i++) {
			boolean nameOfProduct = SiteNavigation.checkName(i);
			boolean priceOfProduct = SiteNavigation.checkPrice(i);
			assertTrue(nameOfProduct);  
			assertTrue(priceOfProduct);
			System.out.println("Proizvod " + ExcelUtils.getDataAt(i-2, 0) + " je uspesno dodat i cena je: " + ExcelUtils.getDataAt(i-2, 1));
		}
		
	}
	
	
	
	@Test
	void RemovingProducts() {
		
		String str1 = SiteNavigation.deleteProducts(" ");
		String str2 = "No Records Found!";
		assertEquals(str1, str2);
		
		SiteNavigation.closeWebDriver();
	}

}
