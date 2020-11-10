package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Utils;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {

    private static Logger logger = LogManager.getLogger(DriverManager.class);
    private static WebDriver webDriver;

    public static void setupDriver() throws MalformedURLException {

        Platform platform = WebDriverFactory.recognizePlatform(Utils.getSystemVariableValue("browser"));

        if(platform.equals(Platform.selenoid)){
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setBrowserName("chrome");
            caps.setVersion("86.0");
            caps.setCapability("enableVNC", true);
            caps.setCapability("screenResolution", "1280x1024");
            caps.setCapability("enableVideo", true);
            caps.setCapability("enableLog", true);
            webDriver = WebDriverFactory.createDriver(platform, null,
                    caps, "http://localhost:4444/wd/hub");
        }
        else {
            webDriver = WebDriverFactory.createDriver(platform, null, null, null);
        }
    }

    public static void quitDriver() {
        if (webDriver != null) {
            logger.info("Драйвер выключен");
            webDriver.quit();
        }
        logger.info("Драйвер уже выключен, дополнительное отключение не требуется");
    }

    public static WebDriver getWebDriver() throws MalformedURLException {
        if (webDriver == null) {
            setupDriver();
        }
        return webDriver;
    }
}
