package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUIs.BasePageUI;

/**
 * @author hiennd
 *
 */

public class BasePage {

	/**
	 * 
	 * @param driver  driver of browser
	 * @param pageUrl The URL to load
	 */
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	/**
	 * @param driver driver of browser
	 * @return The title of the current page
	 */
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	/**
	 * @param driver driver of browser
	 * @return The URL of the page currently loaded in the browser
	 */
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	/**
	 * @param driver driver of browser
	 * @return The source of the current page
	 */
	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	/**
	 * @param driver driver of browser
	 * @see back a single "item" in the browser's history.
	 */
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	/**
	 * @param driver driver of browser
	 * @see Move a single "item" forward in the browser's history
	 */
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	/**
	 * @param driver driver of browser
	 * @see Refresh the current page
	 */
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	/**
	 * @param driver driver of browser
	 * @return All cookies for the current domain
	 */
	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	/**
	 * @param driver  driver of browser
	 * @param cookies The cookie to add.
	 */
	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(3);
	}

	/**
	 * @param driver driver of browser
	 * @return Alert is displayed
	 */
	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	/**
	 * @param driver driver of browser
	 * @see Accept alert.
	 */
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	/**
	 * @param driver driver of browser
	 * @see Cancel alert.
	 */
	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	/**
	 * @param driver driver of browser
	 * @return Text of alert
	 */
	public String getTextAlert(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	/**
	 * @param driver    driver of browser
	 * @param textValue The text to send key
	 * @see Sendkey to Alert
	 */
	public void senkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	/**
	 * @param driver   driver of browser
	 * @param parentID ID of Tab/Windows
	 * @see Switch to ID of window specification.
	 */
	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(parentID)) {
				driver.switchTo().window(window);
				break;
			}
		}
	}

	/**
	 * @param driver driver of browser
	 * @param title  Title of Tab/Windows
	 * @see Switch to window have title specification
	 */
	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			driver.switchTo().window(window);
			String currentWinTitle = driver.getTitle();
			if (currentWinTitle.equals(title)) {
				break;
			}
		}
	}

	/**
	 * @param driver   driver of browser
	 * @param parentID ID of Tab/Windows
	 * @see Close all window without current window.
	 */
	public void closeAllWindowWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String id : allWindows) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	/**
	 * @param driver
	 * @param title
	 */
	public void closeBrowserTabExceptTitle(WebDriver driver, String title) {
		String parentID = null;
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			driver.switchTo().window(window);
			String currentWinTitle = driver.getTitle();
			if (currentWinTitle.contains(title)) {
				parentID = driver.getWindowHandle();
			}
			if (!window.equals(parentID)) {
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	/**
	 * @param locatorType
	 * @return
	 */
	private By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("ID=") || locatorType.startsWith("Id=") || locatorType.startsWith("id=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("CLASS=") || locatorType.startsWith("Class=") || locatorType.startsWith("class=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("NAME=") || locatorType.startsWith("Name=") || locatorType.startsWith("name=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("CSS=") || locatorType.startsWith("Css=") || locatorType.startsWith("css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("xpath=") || locatorType.startsWith("XPath=")) {
			by = By.xpath(locatorType.substring(6));
		} else {
			throw new RuntimeException("Locator type is not supported!");
		}
		return by;
	}

	/**
	 * @param locatorType
	 * @param dynamicValues
	 * @return
	 */
	private String getDynamicXpath(String locatorType, String... dynamicValues) {
		if (locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("xpath=") || locatorType.startsWith("XPath=")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValues);
		}
		return locatorType;
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @return
	 */
	public WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @return
	 */
	public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	/**
	 * @param driver
	 * @param locatorType
	 */
	public void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param dynamicValues
	 */
	public void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param textValue
	 */
	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param textValue
	 * @param dynamicValues
	 */
	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	/**
	 * @param driver
	 * @param locatorType
	 */
	public void clearValueInElementByPressKey(WebDriver driver, String locatorType) {
		WebElement element = this.getWebElement(driver, locatorType);
		element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param textItem
	 */
	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(textItem);
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param textItem
	 * @param dynamicValues
	 */
	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		select.selectByVisibleText(textItem);
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @return
	 */
	public String getSelectItemDefaultDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param dynamicValues
	 * @return
	 */
	public String getSelectItemDefaultDropdown(WebDriver driver, String locatorType, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		return select.getFirstSelectedOption().getText();
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @return
	 */
	public boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}

	/**
	 * @param driver
	 * @param parentXpath
	 * @param childXpath
	 * @param expectedText
	 */
	public void selectItemInDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedText) {
		getWebElement(driver, parentXpath).click();
		sleepInSecond(1);
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedText)) {
				if (item.isDisplayed()) {
					item.click();
				} else {
					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					item.click();
				}
				break;
			}
		}
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param attributeName
	 * @return
	 */
	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param attributeName
	 * @param dynamicValues
	 * @return
	 */
	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @return
	 */
	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param dynamicValues
	 * @return
	 */
	public String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param propertyName
	 * @return
	 */
	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param propertyName
	 * @param dynamicValues
	 * @return
	 */
	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getCssValue(propertyName);
	}

	/**
	 * @param rgbaValue
	 * @return
	 */
	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @return
	 */
	public int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param dynamicValues
	 * @return
	 */
	public int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).size();
	}

	/**
	 * @param driver
	 * @param locatorType
	 */
	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param dynamicValues
	 */
	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}

	/**
	 * @param driver
	 * @param locatorType
	 */
	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param dynamicValues
	 */
	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @return
	 */
	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		try {
			return getWebElement(driver, locatorType).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param dynamicValues
	 * @return
	 */
	public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @return
	 */
	public boolean isElementUndisplayed(WebDriver driver, String locatorType) {
		overrideImplicitTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, locatorType);
		overrideImplicitTimeout(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param dynamicValues
	 * @return
	 */
	public boolean isElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		overrideImplicitTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		overrideImplicitTimeout(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param driver
	 * @param timeOut
	 */
	public void overrideImplicitTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @return
	 */
	public boolean isElementEnabled(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param dynamicValues
	 * @return
	 */
	public boolean isElementEnabled(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isEnabled();
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @return
	 */
	public boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param dynamicValues
	 * @return
	 */
	public boolean isElementSelected(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isSelected();
	}

	/**
	 * @param driver
	 * @param locatorType
	 */
	public void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}

	/**
	 * @param driver
	 */
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * @param driver
	 * @param locatorType
	 */
	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param dynamicValues
	 */
	public void hoverMouseToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues))).perform();
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param key
	 */
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key).perform();
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param key
	 * @param dynamicValues
	 */
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).perform();
	}

	/**
	 * @param driver
	 */
	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	/**
	 * @param driver
	 * @param locatorType
	 */
	public void highlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	/**
	 * @param driver
	 * @param locatorType
	 */
	public void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}

	/**
	 * @param driver
	 * @param locatorType
	 */
	public void scrollToElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param attributeRemove
	 */
	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
	}

	/**
	 * @param driver
	 * @return
	 */
	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @return
	 */
	public String getElementValidationMessage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @return
	 */
	public boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param dynamicValues
	 * @return
	 */
	public boolean isImageLoaded(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		return status;
	}

	/**
	 * @param driver
	 * @param locatorType
	 */
	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param dynamicValues
	 */
	public void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	/**
	 * @param driver
	 * @param locatorType
	 */
	public void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param dynamicValues
	 */
	public void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	/**
	 * @param driver
	 * @param locatorType
	 */
	public void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param dynamicValues
	 */
	public void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	/**
	 * @param driver
	 * @param locatorType
	 */
	public void waitForElementUndisplayed(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideImplicitTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideImplicitTimeout(driver, longTimeout);
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param dynamicValues
	 */
	public void waitForElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideImplicitTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
		overrideImplicitTimeout(driver, longTimeout);
	}

	/**
	 * @param driver
	 * @param locatorType
	 */
	public void waitForAllElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param dynamicValues
	 */
	public void waitForAllElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues))));
	}

	/**
	 * @param driver
	 * @param locatorType
	 */
	public void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}

	/**
	 * @param driver
	 * @param locatorType
	 * @param dynamicValues
	 */
	public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	/**
	 * @param timeoutInSecond
	 */
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param driver
	 * @param menuName
	 */
	public void openPagesAtSubMenuNavigationByName(WebDriver driver, String menuName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_AT_SUB_MENU_NAVAGATION, menuName);
		clickToElement(driver, BasePageUI.DYNAMIC_AT_SUB_MENU_NAVAGATION, menuName);
	}

	/**
	 * @param driver
	 * @param fieldName
	 */
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

	/**
	 * @param driver
	 * @param fieldName
	 * @param errorMessage
	 * @return
	 */
	public boolean isAnErrorMessageDisplayedAtFieldName(WebDriver driver, String fieldName, String errorMessage) {
		waitForElementVisible(driver, BasePageUI.ERROR_MESSAGE, fieldName, errorMessage);
		return isElementDisplayed(driver, BasePageUI.ERROR_MESSAGE, fieldName, errorMessage);
	}

	/**
	 * @param driver
	 * @param textboxName
	 * @param value
	 */
	public void enterToDynamicTextboxByName(WebDriver driver, String textboxName, String value) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX, textboxName);
		sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX, value, textboxName);
	}

	/**
	 * @param driver
	 * @param textareaName
	 * @param value
	 */
	public void enterToDynamicTextareaByName(WebDriver driver, String textareaName, String value) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTAREA, textareaName);
		sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTAREA, value, textareaName);
	}

	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
}
