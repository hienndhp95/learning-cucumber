package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class HomePageObject extends BasePage {
	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isWelcomeMessageDisplayed() {
		return true;
	}
}
