package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class DeleteAccountPageObject extends BasePage {
    WebDriver driver;
    public DeleteAccountPageObject(WebDriver driver){
        this.driver= driver;
    }
}
