package cucumberOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;

import commons.GlobalConstants;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {
	// Run for many thread
	private static WebDriver driver;
	private static final Logger log = Logger.getLogger(Hooks.class.getName());

	@Before // synchronized = handle đồng bộ
	public synchronized static WebDriver openAndQuitBrowser() {
		// Run by Maven command line
		String browser = System.getProperty("BROWSER");
		System.out.println("Browser name run by command line = " + browser);

		// Check driver đã được khởi tạo hay chưa?
		if (driver == null) {

			// Happy path case
			try {
				// Kiem tra BROWSER = null -> gan = chrome/ firefox (browser default for project)
				if (browser == null) {
					// Get browser name from Environment Variable in OS
					browser = System.getenv("BROWSER");
					if (browser == null) {
						// Set default browser
						browser = "chrome";
					}
				}

				switch (browser) {
				case "chrome":
					WebDriverManager.chromedriver().setup();
					ChromeOptions options = new ChromeOptions();
					options.addExtensions(new File(GlobalConstants.PROJECT_PATH + File.separator + "extension" + File.separator + "AdBlock.crx"));
					driver = new ChromeDriver(options);
					break;
				case "hchrome":
					WebDriverManager.chromedriver().setup();
					ChromeOptions chromeOptions = new ChromeOptions();
					chromeOptions.addArguments("headless");
					driver = new ChromeDriver(chromeOptions);
					break;
				case "firefox":
					WebDriverManager.firefoxdriver().setup();
					System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
					System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
					driver = new FirefoxDriver();
					break;
				case "hfirefox":
					WebDriverManager.firefoxdriver().setup();
					System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
					System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
					FirefoxOptions firefoxOptions = new FirefoxOptions();
					firefoxOptions.setHeadless(true);
					driver = new FirefoxDriver(firefoxOptions);
					break;
				case "edge":
					WebDriverManager.edgedriver().setup();
					DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
					EdgeOptions opt = new EdgeOptions();
					ArrayList<String> extensionList = new ArrayList<String>();
					String extensionLocation = GlobalConstants.PROJECT_PATH + File.separator + "extension" + File.separator + "AdBlock.crx";
					extensionList.add(extensionLocation);
					opt.setCapability("disable-infobars", true);
					opt.setCapability("extensionPaths", extensionList);
					opt.merge(desiredCapabilities);
					driver = new EdgeDriver(opt);
					break;
				default:
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					break;
				}
			}
			// Code này luôn luôn được chạy dù pass hay fail
			finally {
				Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
			}
			driver.manage().window().maximize();
			driver.get(GlobalConstants.BANK_GURU_URL);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			log.info("------------- Started the browser -------------");
		}
		return driver;
	}

	public static void close() {
		try {
			if (driver != null) {
				openAndQuitBrowser().quit();
				log.info("------------- Closed the browser -------------");
			}
		} catch (UnreachableBrowserException e) {
			System.out.println("Can not close the browser");
		}
	}

	private static class BrowserCleanup implements Runnable {
		@Override
		public void run() {
			close();
		}
	}

}