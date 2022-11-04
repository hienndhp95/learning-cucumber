package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import commons.PageGeneratorManager;
import cucumber.api.java.en.Then;
import cucumberOptions.Hooks;
import pageObjects.HomePageObject;

public class HomePageSteps {
	WebDriver driver;
	HomePageObject homePage;

	public HomePageSteps() {
		this.driver = Hooks.openAndQuitBrowser();
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Then("^Homepage is displayed$")
	public void homepageIsDisplayed() {
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
	}

}
