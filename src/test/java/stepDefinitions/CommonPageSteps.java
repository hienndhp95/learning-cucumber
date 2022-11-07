package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import commons.PageGeneratorManager;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.CommonPageObject;
import ultilities.DataHelper;

public class CommonPageSteps {
	WebDriver driver;
	CommonPageObject commonPage;
	String email;

	public CommonPageSteps() {
		this.driver = Hooks.openAndQuitBrowser();
		commonPage = PageGeneratorManager.getCommonPage(driver);
		DataHelper fakeData = DataHelper.getData();
		email = fakeData.getEmailAddress();
	}

	@Given("^Open \"([^\"]*)\" page$")
	public void openPage(String pageName) {
		commonPage.clickToDynamicLink(driver, pageName);
	}

	@When("^Input to \"([^\"]*)\" textbox with value \"([^\"]*)\"$")
	public void inputToTextboxWithValue(String fieldName, String inputValue) {
		if (fieldName.equals("E-mail")) {
			inputValue = email;
		}
		commonPage.inputToDynamicTextboxByName(driver, fieldName, inputValue);
	}

	@When("^Click to \"([^\"]*)\" radio button$")
	public void clickToRadioButton(String radioButtonValue) {
		commonPage.clickToDynamicRadioButton(driver, radioButtonValue);
	}

	@When("^Input to \"([^\"]*)\" textarea with value \"([^\"]*)\"$")
	public void inputToTextareaWithValue(String fieldName, String inputValue) {
		commonPage.inputToDynamicTextareaByName(driver, fieldName, inputValue);
	}

	@When("^Click to \"([^\"]*)\" button$")
	public void clickToButton(String buttonValue) {
		commonPage.clickToDynamicButton(driver, buttonValue);
	}

	@Then("^Success message displayed with \"([^\"]*)\"$")
	public void successMessageDisplayedWith(String expectedMessage) {
		Assert.assertTrue(commonPage.isAnSuccessMessageDisplayed(driver, expectedMessage));
	}

	@Then("^The valid text displayed at \"([^\"]*)\" with value \"([^\"]*)\"$")
	public void theValidTextDisplayedAtWithValue(String rowName, String expectedValue) {
		if (rowName.equals("Email")) {
			expectedValue = email;
		}
		Assert.assertEquals(expectedValue, commonPage.getDynamicValueByRowName(driver, rowName));
	}
}
