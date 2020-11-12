package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.Utils;
import java.net.MalformedURLException;

public class DriverManager {

    private static Logger logger = LogManager.getLogger(DriverManager.class);
    private static WebDriver webDriver;

    public static void setupDriver() {

        Platform platform = WebDriverFactory.recognizePlatform(Utils.getSystemVariableValue("browser"));

        if(platform.equals(Platform.selenoid)){
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setBrowserName("chrome");
            caps.setVersion("86.0");
            caps.setCapability("enableVNC", true);
            caps.setCapability("screenResolution", "1280x1024");
            caps.setCapability("enableVideo", true);
            caps.setCapability("enableLog", true);
            try {
                webDriver = WebDriverFactory.createDriver(platform, null,
                        caps, "http://localhost:4444/wd/hub");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                webDriver = WebDriverFactory.createDriver(platform, null, null, null);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void tearDownDriver() {
        if (webDriver != null) {
            logger.info("Драйвер выключен");
            webDriver.quit();
        }
        logger.info("Драйвер уже выключен, дополнительное отключение не требуется");
    }

    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            setupDriver();
        }
        return webDriver;
    }
}
