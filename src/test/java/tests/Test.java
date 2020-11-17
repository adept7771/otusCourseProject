package tests;

import core.Core;
import core.JunitRunner;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import steps.GlobalNavigationSteps;
import steps.SingleEventSteps;
import steps.UpcomingPastEventsSteps;
import steps.AllVideosPageSteps;
import utils.Utils;

@Execution(ExecutionMode.CONCURRENT)
public class Test extends JunitRunner {

    @org.junit.jupiter.api.Test
    @DisplayName(value = "Upcoming events check test")
    public void firstTest() {
        GlobalNavigationSteps globalNavigationSteps = new GlobalNavigationSteps(webDriver);
        globalNavigationSteps.openMainPage();
        globalNavigationSteps.navigateToUpcomingEvents();
        UpcomingPastEventsSteps upcomingPastEventsSteps = new UpcomingPastEventsSteps(webDriver);
        Assertions.assertEquals(upcomingPastEventsSteps.getChosenTabEventCounterValue(),
                upcomingPastEventsSteps.getUpcomingEventsCardsCount(),
                "There are difference while comparing event counter and event cards");
    }

    @org.junit.jupiter.api.Test
    @DisplayName(value = "Event cards check test")
    public void secondTest() {
        GlobalNavigationSteps globalNavigationSteps = new GlobalNavigationSteps(webDriver);
        globalNavigationSteps.openMainPage();
        globalNavigationSteps.navigateToUpcomingEvents();
        UpcomingPastEventsSteps upcomingPastEventsSteps = new UpcomingPastEventsSteps(webDriver);
        int countOfEventCards = upcomingPastEventsSteps.getUpcomingEventsCardsCount();
        upcomingPastEventsSteps.checkEventCardsElements(countOfEventCards);
    }

    // у меня блока this week нет. Поэтому я проверяю, что все мероприятия проводятся в ЭТОМ месяце
    // то есть соответствуют системному месяцу на текущем пк
    @org.junit.jupiter.api.Test
    @DisplayName(value = "Event date validation test")
    public void thirdTest() {
        GlobalNavigationSteps globalNavigationSteps = new GlobalNavigationSteps(webDriver);
        UpcomingPastEventsSteps upcomingPastEventsSteps = new UpcomingPastEventsSteps(webDriver);
        globalNavigationSteps.openMainPage();
        globalNavigationSteps.navigateToUpcomingEvents();
        upcomingPastEventsSteps.getAllEventsMonthsAsStringAndCompareWithMonthName(Utils.getSystemMonthInMMMFormat());
    }

    @org.junit.jupiter.api.Test
    @DisplayName(value = "Canada events dates validation")
    public void fourthTest() {
        GlobalNavigationSteps globalNavigationSteps = new GlobalNavigationSteps(webDriver);
        UpcomingPastEventsSteps upcomingPastEventsSteps = new UpcomingPastEventsSteps(webDriver);
        globalNavigationSteps.openMainPage();
        globalNavigationSteps.navigateToPastEvents();
        upcomingPastEventsSteps.chooseCanada();
        Assertions.assertEquals(upcomingPastEventsSteps.getChosenTabEventCounterValue(),
                upcomingPastEventsSteps.getPastEventsCardsCount(),
                "There are difference while comparing event counter and event cards");
        Assertions.assertTrue(upcomingPastEventsSteps
                .isAllEventsInPastToCurrentDate(Utils.getSystemDateIn_dd_MMM_yyyy()));
    }

    @org.junit.jupiter.api.Test
    @DisplayName(value = "Detailed event info checks")
    public void fifthTest() {
        GlobalNavigationSteps globalNavigationSteps = new GlobalNavigationSteps(webDriver);
        UpcomingPastEventsSteps upcomingPastEventsSteps = new UpcomingPastEventsSteps(webDriver);
        SingleEventSteps singleEventSteps = new SingleEventSteps(webDriver);
        globalNavigationSteps.openMainPage();
        globalNavigationSteps.navigateToUpcomingEvents();
        upcomingPastEventsSteps.goToFirstEvent();
        singleEventSteps.checkMainEventBlocksAreExists();
    }

    @org.junit.jupiter.api.Test
    @DisplayName(value = "Video Filtration testing")
    public void sixthTest() {
        GlobalNavigationSteps globalNavigationSteps = new GlobalNavigationSteps(webDriver);
        globalNavigationSteps.openMainPage();
        globalNavigationSteps.navigateToVideoBlock();
        AllVideosPageSteps allVideosPageSteps = new AllVideosPageSteps(webDriver);
        allVideosPageSteps.openCloseMoreFilters();
        allVideosPageSteps.chooseLanguageEnglish();
        allVideosPageSteps.chooseLocationBelarus();
        allVideosPageSteps.chooseTestingCategory();
        allVideosPageSteps.openCloseMoreFilters();
        Assertions.assertEquals(
                allVideosPageSteps.countNumberOfLanguageIcons(),
                allVideosPageSteps.countNumberOfVideoHeadings(),
                "Number of events doesn't equal to number of language icons");
        allVideosPageSteps.checkEachVideoOnPage();
    }

    @org.junit.jupiter.api.Test
    @DisplayName(value = "Find video reports testing")
    public void seventhTest() {
        GlobalNavigationSteps globalNavigationSteps = new GlobalNavigationSteps(webDriver);
        globalNavigationSteps.openMainPage();
        globalNavigationSteps.navigateToVideoBlock();
        AllVideosPageSteps allVideosPageSteps = new AllVideosPageSteps(webDriver);
        allVideosPageSteps.findVideo("QA");
        allVideosPageSteps.validateAllVideosContainsText("QA");
    }
}






















