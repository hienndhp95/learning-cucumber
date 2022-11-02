package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.NewAccountPageUI;

public class NewAccountPageObject extends BasePage {
	WebDriver driver;

	public NewAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToSubmitButton() {
		waitForElementClickable(driver, NewAccountPageUI.SUBMIT_BUTTON);
		clickToElement(driver, NewAccountPageUI.SUBMIT_BUTTON);

	}

	public boolean isSuccessfulRegistrationMessageDisplayed(String message) {
		waitForElementVisible(driver, NewAccountPageUI.SUCCESSFUL_REGISTRATION_MESSAGE, message);
		return isElementDisplayed(driver, NewAccountPageUI.SUCCESSFUL_REGISTRATION_MESSAGE, message);
	}
}
