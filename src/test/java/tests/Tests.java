package tests;

import core.Core;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import steps.GlobalNavigationSteps;
import steps.UpcomingEventsSteps;

@Execution(ExecutionMode.CONCURRENT)
public class Tests extends Core {

    GlobalNavigationSteps globalNavigationSteps = new GlobalNavigationSteps();
    UpcomingEventsSteps upcomingEventsSteps = new UpcomingEventsSteps();

    @Test
    public void firstTest() {
        globalNavigationSteps.openMainPage();
        globalNavigationSteps.navigateToUpcomingEvents();
        Assertions.assertEquals(upcomingEventsSteps.getEventCounterValue(), upcomingEventsSteps.getEventsCardsCount(),
                "There are difference while comparing event counter and event cards");
    }

    @Test
    public void secondTest() {
        globalNavigationSteps.openMainPage();
        globalNavigationSteps.navigateToUpcomingEvents();
        int countOfEventCards = upcomingEventsSteps.getEventsCardsCount();
        upcomingEventsSteps.checkEventCardsElements(countOfEventCards);
    }


}
