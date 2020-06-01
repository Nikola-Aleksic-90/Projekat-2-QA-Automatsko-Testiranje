package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import page_objects.SiteNavigation;

class Test3_Logout {
	
	@Test
	@Order(1) 
	void Logout() {
		
		SiteNavigation.login();
		SiteNavigation.logout();
		
		assertEquals(SiteNavigation.loggedUser, "Nikola0001");
		System.out.println("User " + SiteNavigation.loggedUser + " has successfully logged out");
		assertEquals(SiteNavigation.baseUrl, SiteNavigation.currentURL);
		System.out.println("Successfull navigation to home page after logout");
		
		SiteNavigation.closeWebDriver();
		
	}

}
