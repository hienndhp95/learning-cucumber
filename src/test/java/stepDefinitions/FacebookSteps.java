package stepDefinitions;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FacebookSteps {
	WebDriver driver;

	@Before("@parameter")
	public void openFacebookApplication() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@When("^Input to email textbox$")
	public void inputToEmailTextbox() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("abcd@gmail.com");

	}

	@When("^Input password textbox$")
	public void inputPasswordTextbox() {
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("123456");
	}

	@When("^Input to email textbox with \"([^\"]*)\"$")
	public void inputToEmailTextboxWith(String email) {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(email);
	}

	@When("^Input password textbox with \"([^\"]*)\"$")
	public void inputPasswordTextboxWith(String password) {
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys(password);
	}

	@When("^Input to email textbox with ([^\"]*)$")
	public void inputToEmailTextbox(String email) {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(email);
	}

	@When("^Input password textbox with ([^\"]*)$")
	public void inputPasswordTextbox(String pass) {
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys(pass);
	}

	@When("^Input to email textbox with \"([^\"]*)\" and password with \"([^\"]*)\"$")
	public void inputToEmailTextboxWithAndPasswordWith(String email, String password) {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(email);

		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys(password);
	}

	@When("^Input to email and password$")
	public void inputToEmailAndPassword(DataTable table) {
		// List<Map<String, String>> customers = table.asMaps(String.class, String.class);
		// driver.findElement(By.id("email")).clear();
		// // get(0) -> dòng để lấy dữ liệu
		// // get("Email") -> Lấy theo tên cột
		// driver.findElement(By.id("email")).sendKeys(customers.get(0).get("Email"));
		//
		// driver.findElement(By.id("pass")).clear();
		// driver.findElement(By.id("pass")).sendKeys(customers.get(0).get("password"));

		for (Map<String, String> customer : table.asMaps(String.class, String.class)) {
			driver.findElement(By.id("email")).clear();
			driver.findElement(By.id("email")).sendKeys(customer.get("Email"));

			driver.findElement(By.id("pass")).clear();
			driver.findElement(By.id("pass")).sendKeys(customer.get("password"));
		}
	}

	@Then("^Verify submitted infor displayed$")
	public void verifySubmittedInforDisplayed(DataTable table) {

	}

	@When("^Click to Submit button$")
	public void clickToSubmitButton() {
		driver.findElement(By.name("login")).click();

	}

	@After("@parameter")
	public void closeApplication() {
		driver.quit();
	}
}
