package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void openLoginPage(String url) {
		openPageUrl(driver, url);
	}

	public String getUsername() {
		waitForElementVisible(driver, RegisterPageUI.USER_ID_TEXTBOX);
		return getElementText(driver, RegisterPageUI.USER_ID_TEXTBOX);
	}

	public String getPassword() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		return getElementText(driver, RegisterPageUI.PASSWORD_TEXTBOX);
	}

	public void clickToSubmitButton() {
		waitForElementClickable(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
	}

	public void inputToEmailTextbox(String email) {
		waitForAllElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, email);
	}
}
