package stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.PageGeneratorManager;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.RegisterPageObject;
import ultilities.DataHelper;

public class RegisterPageSteps {

	WebDriver driver;
	RegisterPageObject registerPage;
	DataHelper fakeData = DataHelper.getData();
	static String username, password;

	// Constructor
	public RegisterPageSteps() {
		this.driver = Hooks.openAndQuitBrowser();
		registerPage = PageGeneratorManager.getRegisterPage(driver);
	}

	@When("^Input to email with \"([^\"]*)\"$")
	public void inputToEmailWith(String email) {
		registerPage.inputToEmailTextbox(fakeData.getEmailAddress());
	}

	@When("^Click to submit button$")
	public void clickToSubmitButton() {
		registerPage.clickToSubmitButton();
	}

	@Then("^Get User and password infor$")
	public void getUserAndPasswordInfor() {
		username = registerPage.getUsername();
		password = registerPage.getPassword();
	}

	@When("^Back to Login page$")
	public void backToLoginPage() {
		registerPage.openLoginPage(LoginPageSteps.loginPageUrl);
	}

}
