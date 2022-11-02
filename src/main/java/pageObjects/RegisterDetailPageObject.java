package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.RegisterDetailPageUI;

public class RegisterDetailPageObject extends BasePage {
	WebDriver driver;

	public RegisterDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getUsername() {
		waitForElementVisible(driver, RegisterDetailPageUI.USER_ID_TEXTBOX);
		return getElementText(driver, RegisterDetailPageUI.USER_ID_TEXTBOX);
	}

	public String getPassword() {
		waitForElementVisible(driver, RegisterDetailPageUI.PASSWORD_TEXTBOX);
		return getElementText(driver, RegisterDetailPageUI.PASSWORD_TEXTBOX);
	}
}
