package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FacebookSteps {
	WebDriver driver;

	@Given("^Open facebook application$")
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
	public void inputToEmailAndPassword(DataTable arg1) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		// E,K,V must be a scalar (String, Integer, Date, enum etc)
	}

	@Then("^Verify submitted infor displayed$")
	public void verifySubmittedInforDisplayed(DataTable arg1) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		// E,K,V must be a scalar (String, Integer, Date, enum etc)
	}

	@When("^Click to Submit button$")
	public void clickToSubmitButton() {
		driver.findElement(By.name("login")).click();

	}

	@Then("^Verify email textbox is displayed$")
	public void verifyEmailTextboxIsDisplayed() {
		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
	}

	@And("^Verify password textbox is displayed$")
	public void verifyPasswordTextboxIsDisplayed() {
		Assert.assertTrue(driver.findElement(By.id("pass")).isDisplayed());
	}

	@And("^Close application$")
	public void closeApplication() {
		driver.quit();
	}
}
