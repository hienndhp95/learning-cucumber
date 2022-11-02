package pageUIs;

public class BasePageUI {

	public static final String DYNAMIC_AT_SUB_MENU_NAVAGATION = "xpath=//a[text()='%s']";
	public static final String ERROR_MESSAGE = "xpath=//td[contains(text(),'%s')]/following-sibling::td//label[text()='%s']";
	public static final String DYNAMIC_TEXTBOX = "xpath=//td[contains(text(),'%s')]/following-sibling::td/input";
	public static final String DYNAMIC_TEXTAREA = "xpath=//td[text()='%s']/following-sibling::td/textarea";

}
