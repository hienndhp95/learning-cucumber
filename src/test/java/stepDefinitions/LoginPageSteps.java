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
	// Share data
	TestContext testContext;

	public LoginPageSteps(TestContext testContext) {
		this.driver = Hooks.openAndQuitBrowser();
		this.testContext = testContext;
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.closeBrowserTabExceptTitle(driver, "Guru99 Bank");
	}

	@Given("^Get login page Url$")
	public void getLoginPageUrl() {
		testContext.getDataContext().setContext(Context.LOGIN_URL, loginPage.getLoginPageUrl());
	}

	@When("^Open Register page$")
	public void openRegisterPage() {
		loginPage.clickToHereLink();
	}

	@When("^Submit valid infor to login form$")
	public void submitValidInforToLoginForm() {
		commonPage = PageGeneratorManager.getCommonPage(driver);
		commonPage.inputToDynamicTextboxByName(driver, "UserID", testContext.getDataContext().getContext(Context.USER_ID));
		commonPage.inputToDynamicTextboxByName(driver, "Password", testContext.getDataContext().getContext(Context.PASSWORD));
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.clickToLoginButton();
	}

}
