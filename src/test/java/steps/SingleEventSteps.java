package steps;

import core.Core;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import pageObjects.SingleEventPage;


public class SingleEventSteps extends Core {

    SingleEventPage singleEventPage = new SingleEventPage();

    public SingleEventSteps(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkMainEventBlocksAreExists(){
        Assertions.assertTrue(isElementVisible(singleEventPage.dateButton, 10L));
        Assertions.assertTrue(isElementVisible(singleEventPage.agendaWrapper, 10L));
        Assertions.assertTrue(isElementVisible(singleEventPage.registerButton, 10L));
    }
}
