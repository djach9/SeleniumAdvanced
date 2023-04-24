package baseTests;

import configuration.ConfigurationRetriever;
import configuration.factories.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseTest {

    protected WebDriver driver;
    private DriverFactory driverFactory;

    protected static Logger logger = LoggerFactory.getLogger("tests.BaseTest.class");


    @BeforeEach
    public void setUp() {
        driverFactory = new DriverFactory();
        driver = driverFactory.getDriver();
        driver.get(ConfigurationRetriever.getConfig().getUrl());
        logger.info("Driver is opened");
    }

    @AfterEach
    public void tearDown() {
      //  driver.quit();
        logger.info("Driver is closed");

    }
}