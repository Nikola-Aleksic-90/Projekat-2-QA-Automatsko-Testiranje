package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import page_objects.SiteNavigation;

class Test2_Login {

	@Test
	void Login() {

		SiteNavigation.login();
		assertEquals(SiteNavigation.loggedUser, "Nikola0001");
		System.out.println("User " + SiteNavigation.loggedUser + " has successfully logged in");
		SiteNavigation.closeWebDriver();

	}

}
