package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.BasePageUI;

public class CommonPageObject extends BasePage {
	WebDriver driver;

	public CommonPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToDynamicLink(WebDriver driver, String menuName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_LINK, menuName);
		clickToElement(driver, BasePageUI.DYNAMIC_LINK, menuName);
	}

	public void moveToNextFieldByTabKey(WebDriver driver, String fieldName) {
		switch (fieldName) {
		case "Address":
			pressKeyToElement(driver, BasePageUI.DYNAMIC_TEXTAREA, Keys.TAB, fieldName);
			break;
		default:
			pressKeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX, Keys.TAB, fieldName);
			break;
		}
	}

	public boolean isAnErrorMessageDisplayedAtFieldName(WebDriver driver, String fieldName, String errorMessage) {
		waitForElementVisible(driver, BasePageUI.ERROR_MESSAGE, fieldName, errorMessage);
		return isElementDisplayed(driver, BasePageUI.ERROR_MESSAGE, fieldName, errorMessage);
	}

	public boolean isAnSuccessMessageDisplayed(WebDriver driver, String successMessage) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_MESSAGE, successMessage);
		return isElementDisplayed(driver, BasePageUI.DYNAMIC_MESSAGE, successMessage);
	}

	public void inputToDynamicTextboxByName(WebDriver driver, String textboxName, String value) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX, textboxName);
		sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX, value, textboxName);
	}

	public void inputToDynamicTextareaByName(WebDriver driver, String textareaName, String value) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTAREA, textareaName);
		sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTAREA, value, textareaName);
	}

	public void clickToDynamicRadioButton(WebDriver driver, String radioButtonValue) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_RADIO_BUTTON, radioButtonValue);
		clickToElement(driver, BasePageUI.DYNAMIC_RADIO_BUTTON, radioButtonValue);

	}

	public void clickToDynamicButton(WebDriver driver, String buttonValue) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_BUTTON, buttonValue);
		clickToElement(driver, BasePageUI.DYNAMIC_BUTTON, buttonValue);
	}

	public String getDynamicValueByRowName(WebDriver driver, String rowName) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_VALUE_BY_ROW_NAME, rowName);
		return getElementText(driver, BasePageUI.DYNAMIC_VALUE_BY_ROW_NAME, rowName);
	}

	public void closeAlertPopup() {
		waitForAlertPresence(driver);
		acceptAlert(driver);

	}
}
