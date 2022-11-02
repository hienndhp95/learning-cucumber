package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.NewCustomerPageUI;

public class NewCustomerPageObject extends BasePage {
	WebDriver driver;

	public NewCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void chooseGenderRadioButton() {
		waitForElementClickable(driver, NewCustomerPageUI.FEMALE_RADIO);
		clickToElement(driver, NewCustomerPageUI.FEMALE_RADIO);
	}

	public void enterToDateOfBirth(String dateOfBirthInput) {
		waitForElementVisible(driver, NewCustomerPageUI.DATE_OF_BIRTH_TEXTBOX);
		removeAttributeInDOM(driver, NewCustomerPageUI.DATE_OF_BIRTH_TEXTBOX, "type");
		sendkeyToElement(driver, NewCustomerPageUI.DATE_OF_BIRTH_TEXTBOX, dateOfBirthInput);
	}

	public boolean isFieldNameAsRequirement(String fieldName) {
		waitForElementVisible(driver, NewCustomerPageUI.LABEL, fieldName);
		return isElementDisplayed(driver, NewCustomerPageUI.LABEL, fieldName);
	}

	public void clicktoSubmitButton() {
		waitForElementClickable(driver, NewCustomerPageUI.SUBMIT_BUTTON);
		clickToElement(driver, NewCustomerPageUI.SUBMIT_BUTTON);
	}

	public String getDataAtFieldName(String titleName) {
		waitForElementVisible(driver, NewCustomerPageUI.TITLE_NAME, titleName);
		return getElementText(driver, NewCustomerPageUI.TITLE_NAME, titleName);
	}

	public boolean isMessageRegisteredSuccessfullyDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.MESSAGE_HEADING);
		return isElementDisplayed(driver, NewCustomerPageUI.MESSAGE_HEADING);
	}

}
