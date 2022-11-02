package commons;

import java.io.File;

public class GlobalConstants {
	public static final String patternDateInput ="MM/dd/yyyy";
	public static final String patternDateOutput ="yyyy-MM-dd";
	public static final String LOGIN_PAGE_URL = "https://demo.guru99.com/v4/";
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String DOWNLOAD_FILE = PROJECT_PATH + File.separator + "downloadFiles";
	public static final String BROWSER_LOG = PROJECT_PATH + File.separator + "browserLogs";
	public static final String REPORTNG_SCREENSHOT = PROJECT_PATH + File.separator + "reportNGImages" + File.separator;
	public static final String JAVA_VERSION = System.getProperty("java.version");
	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 15;
	public static final long RETRY_TEST_FAIL = 3; 
}
