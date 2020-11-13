package tests;

import core.Core;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import steps.GlobalNavigationSteps;
import steps.UpcomingEventsSteps;
import utils.Utils;

@Execution(ExecutionMode.CONCURRENT)
public class Tests extends Core {

    GlobalNavigationSteps globalNavigationSteps = new GlobalNavigationSteps();
    UpcomingEventsSteps upcomingEventsSteps = new UpcomingEventsSteps();

    @Test
    @DisplayName(value = "Upcoming events check test")
    public void firstTest() {
        globalNavigationSteps.openMainPage();
        globalNavigationSteps.navigateToUpcomingEvents();
        Assertions.assertEquals(upcomingEventsSteps.getEventCounterValue(), upcomingEventsSteps.getEventsCardsCount(),
                "There are difference while comparing event counter and event cards");
    }

    @Test
    @DisplayName(value = "Event cards check test")
    public void secondTest() {
        globalNavigationSteps.openMainPage();
        globalNavigationSteps.navigateToUpcomingEvents();
        int countOfEventCards = upcomingEventsSteps.getEventsCardsCount();
        upcomingEventsSteps.checkEventCardsElements(countOfEventCards);
    }

    // у меня блока this week нет. Поэтому я проверяю, что все мероприятия проводятся в ЭТОМ месяце
    // то есть соответствуют системному месяцу на текущем пк
    @Test
    @DisplayName(value = "Event date validation test")
    public void thirdTest() {
        globalNavigationSteps.openMainPage();
        globalNavigationSteps.navigateToUpcomingEvents();
        upcomingEventsSteps.getAllEventsMonthsAsStringAndCompareWithMonthName(Utils.getSystemMonthInMMMFormat());
    }


}
