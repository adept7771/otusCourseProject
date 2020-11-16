package steps;

import core.Core;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pageObjects.SingleEventPage;

@Execution(ExecutionMode.CONCURRENT)
public class SingleEventSteps extends Core {

    SingleEventPage singleEventPage = new SingleEventPage();

    public void checkMainEventBlocksAreExists(){
        Assertions.assertTrue(isElementVisible(singleEventPage.dateButton, 10L));
        Assertions.assertTrue(isElementVisible(singleEventPage.agendaWrapper, 10L));
        Assertions.assertTrue(isElementVisible(singleEventPage.registerButton, 10L));
    }
}
