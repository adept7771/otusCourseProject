package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {

    private static Logger logger = LogManager.getLogger(WebDriverFactory.class);

    public static RemoteWebDriver createDriver (Platform platform, WebDriver.Options options,
                                         DesiredCapabilities desiredCapabilities, String selenoidPath) throws MalformedURLException {
        switch (platform) {
            case chrome: {
                WebDriverManager.chromedriver().setup();
                logger.info("Драйвер Chrome формируется фабрикой");
                if (options != null) return new ChromeDriver((ChromeOptions) options);
                else return new ChromeDriver();
            }
            case opera: {
                WebDriverManager.operadriver().setup();
                logger.info("Драйвер Opera формируется фабрикой");
                if (options != null) return new OperaDriver((OperaOptions) options);
                else return new OperaDriver();
            }
            case firefox: {
                WebDriverManager.firefoxdriver().setup();
                logger.info("Драйвер Firefox формируется фабрикой");
                if (options != null) return new FirefoxDriver((FirefoxOptions) options);
                else return new FirefoxDriver();
            }
            case selenoid: {
                logger.info("Драйвер в Selenoid формируется фабрикой, по пути: " + selenoidPath);
                if (desiredCapabilities != null) return new RemoteWebDriver(new URL(selenoidPath), desiredCapabilities);
                else return new RemoteWebDriver(new URL(selenoidPath), null);
            }
        }
        logger.error("Фабрике не удалось сформировать драйвер");
        return null;
    }

    public static Platform recognizePlatform(String incomingBrowserName){
        if(incomingBrowserName == null){
            logger.info("Chrome browser by default.");
            return Platform.chrome;
        }
        String browserName = incomingBrowserName.toLowerCase();
        if(browserName.contains("chrome")){
            logger.info("Chrome recognized from system variable.");
            return Platform.chrome;
        }
        if(browserName.contains("opera")){
            logger.info("Opera recognized from system variable.");
            return Platform.opera;
        }
        if(browserName.contains("selenoid")){
            logger.info("Selenoid recognized from system variable.");
            return Platform.selenoid;
        }
        else {
            logger.info("Firefox recognized from system variable.");
            return Platform.firefox;
        }
    }
}
