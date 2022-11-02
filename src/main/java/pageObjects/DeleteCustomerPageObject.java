package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class DeleteCustomerPageObject extends BasePage {
    WebDriver driver;
    public DeleteCustomerPageObject(WebDriver driver){
        this.driver= driver;
    }
}
