package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class EditAccountPageObject extends BasePage {
    WebDriver driver;
    public EditAccountPageObject(WebDriver driver){
        this.driver= driver;
    }
}
