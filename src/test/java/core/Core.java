package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Core {

    public WebDriver webDriver;
    private static Logger logger = LogManager.getLogger(Core.class);

    public Core(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void getUrl(String url) {
        logger.info("Открываю сайт: " + url);
        webDriver.get(url);
    }

    public String getText(By by) {
        WebElement webElement = waitBy(10, by);
        if (webElement == null) {
            return null;
        }
        if (webElement.getText().equals("") || webElement.getText().equals("null")) {
            return webElement.getAttribute("value");
        } else {
            return webElement.getText();
        }
    }

    public String getAttribute(By by, String attribute) {
        WebElement webElement = waitBy(10, by);
        if (webElement == null) {
            return null;
        } else {
            return webElement.getAttribute(attribute);
        }
    }

    public void sendKeys(By by, String text) {
        sendKeys(100L, by, text, null);
    }

    public void sendKeys(By by, Keys keys) {
        sendKeys(100L, by, null, keys);
    }

    public void sendKeys(long timeToWait, By by, String text, Keys keys) {
        getReadyState();
        WebDriverWait wait = new WebDriverWait(webDriver, timeToWait);
        int maxWaitForAvoidStaleElementException = (int) timeToWait;
        for (int i = 0; i < maxWaitForAvoidStaleElementException; i++) {
            try {
                if (keys == null) {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(by)).clear();
                    wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(text);
                } else {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(keys);
                }
                return;
            } catch (StaleElementReferenceException e) {
                wait.until(ExpectedConditions.presenceOfElementLocated(by));
                wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                waitStatic(1000);
            }
        }
    }

    public void scrollToElement(long timeToWait, By by) {
        getReadyState();
        WebDriverWait wait = new WebDriverWait(webDriver, timeToWait);
        WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public void scrollToElement(By by) {
        scrollToElement(100L, by);
    }

    public void scrollToElement(long timeToWait, WebElement webElement) {
        getReadyState();
        WebDriverWait wait = new WebDriverWait(webDriver, timeToWait);
        wait.until(ExpectedConditions.presenceOfElementLocated((By) webElement));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public void clickWithWait(long timeToWait, By by) {
        getReadyState();
        logger.info("Попытка поиска элемента и проведения клика по пути: " + by);
        waitBy(timeToWait, by).click();
    }

    public void clickWithWait(By by) {
        clickWithWait(100L, by);
    }

    private WebElement waitBy(long timeToWait, By by) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeToWait);
        wait.withMessage("WebElement can't be found by: " + by);
        logger.info("Ждем элемент: " + by);
        for (int i = 0; i < 10; i++) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } catch (StaleElementReferenceException e) {
                waitStatic(1000);
            }
        }
        return null;
    }

    public List<WebElement> findAllWebElements(long timeToWait, By by) {
        getReadyState();
        WebDriverWait wait = new WebDriverWait(webDriver, timeToWait);
        if (waitBy(timeToWait, by) == null) {
            return null;
        } else {
            return webDriver.findElements(by);
        }
    }

    public List<WebElement> findAllWebElements(By by) {
        return findAllWebElements(100L, by);
    }

    public String getText(long timeToWait, By by) {
        getReadyState();
        WebDriverWait wait = new WebDriverWait(webDriver, timeToWait);
        logger.info("Получение текста для: " + by);
        try {
            return waitBy(timeToWait, by).getText();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public boolean isElementVisible(By by, long timeToWait) {
        getReadyState();
        WebDriverWait wait = new WebDriverWait(webDriver, timeToWait);
        try {
            waitStatic((int) timeToWait);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (ElementNotVisibleException e) {
            return false;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void waitUntilNotExists(By by, long timeToWait) {
        while (timeToWait != 0) {
            if (isElementExists(by, 1L)) {
                timeToWait -= 1;
            } else {
                return;
            }
        }
    }

    public boolean isElementExists(By by, long timeToWait) {
        getReadyState();
        WebDriverWait wait = new WebDriverWait(webDriver, timeToWait);
        try {
            waitStatic((int) timeToWait);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void getReadyState() {
        WebDriverWait wait = new WebDriverWait(webDriver, 100L);
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
    }

    public void waitStatic(int timeInMs) {
        try {
            Thread.sleep(timeInMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void switchToNewWindowExceptMentioned(String windowToExcept) {
        Set windowHandles = webDriver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        while (iterator.hasNext()) {
            String window = iterator.next();
            if (!window.equals(windowToExcept)) {
                webDriver.switchTo().window(window);
            }
        }
    }

    public void goBack() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.history.go(-1)");
    }
}
