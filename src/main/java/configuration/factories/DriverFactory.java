package configuration.factories;

import configuration.enums.Browser;
import configuration.ConfigurationRetriever;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class DriverFactory {

    public WebDriver getDriver() {
        switch (Browser.valueOf(ConfigurationRetriever.getConfig().getBrowser().toUpperCase())) {
            case IE -> {
                return new InternetExplorerDriver(getInternetExplorerOptions());
            }
            case EDGE -> {
                return new EdgeDriver(getEdgeOptions());
            }
            case FIREFOX -> {
                return new FirefoxDriver(getFirefoxOptions());
            }
            case CHROME -> {
                return new ChromeDriver(getChromeOptions());
            }
            default -> {
                throw new RuntimeException("Unsupported browser selection");

            }
        }
    }

    private InternetExplorerOptions getInternetExplorerOptions() {
        return new InternetExplorerOptions();
    }

    private EdgeOptions getEdgeOptions() {
        return new EdgeOptions().addArguments("start-maximized", "--remote-allow-origins=*");
    }

    private FirefoxOptions getFirefoxOptions() {
        return new FirefoxOptions().addArguments("--kiosk");
    }

    private ChromeOptions getChromeOptions() {
        return new ChromeOptions().addArguments("start-maximized", "--remote-allow-origins=*");
    }
}
