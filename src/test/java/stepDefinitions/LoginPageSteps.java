package stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.PageGeneratorManager;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.CommonPageObject;
import pageObjects.LoginPageObject;

public class LoginPageSteps {
	WebDriver driver;
	LoginPageObject loginPage;
	CommonPageObject commonPage;
	static String loginPageUrl;

	public LoginPageSteps() {
		this.driver = Hooks.openAndQuitBrowser();
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.closeBrowserTabExceptTitle(driver, "Guru99 Bank");
	}

	@Given("^Get login page Url$")
	public void getLoginPageUrl() {
		loginPageUrl = loginPage.getLoginPageUrl();
	}

	@When("^Open Register page$")
	public void openRegisterPage() {
		loginPage.clickToHereLink();
	}

	@When("^Submit valid infor to login form$")
	public void submitValidInforToLoginForm() {
		commonPage = PageGeneratorManager.getCommonPage(driver);
		commonPage.inputToDynamicTextboxByName(driver, "UserID", RegisterPageSteps.username);
		commonPage.inputToDynamicTextboxByName(driver, "Password", RegisterPageSteps.password);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.clickToLoginButton();
	}

}
