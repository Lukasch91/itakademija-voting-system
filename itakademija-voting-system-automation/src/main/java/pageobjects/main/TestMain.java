package pageobjects.main;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public abstract class TestMain {
    public WebDriver webDriver;

    /**
     * Initialize webDriver
     * @throws Exception
     */
    @BeforeMethod
    public void initialize() throws Exception {
        webDriver = initWebDriver(webDriver);
    }

    /**
     * Closes webDriver
     * @throws Exception
     */
    @AfterMethod
    public void tearDown() throws Exception {
       webDriver.close();
       webDriver.quit();
    }

    /**
     * Initialize Chrome browser
     * @param webDriver
     * @return
     */
    public static WebDriver initWebDriver(WebDriver webDriver) {

        //--- starting Chrome Marionette ---
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("marionette", true);
        
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        System.setProperty("webdriver.gecko.driver", "geckodriver");
        
        webDriver = new ChromeDriver(capabilities);
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();

        return webDriver;
    }
    

}
