package tests;

import core.Core;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import steps.GlobalNavigationSteps;
import steps.UpcomingEventsSteps;

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
}
