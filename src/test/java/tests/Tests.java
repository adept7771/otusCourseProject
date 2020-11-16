package tests;

import core.Core;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import steps.GlobalNavigationSteps;
import steps.SingleEventSteps;
import steps.UpcomingPastEventsSteps;
import steps.AllVideosPageSteps;
import utils.Utils;

@Execution(ExecutionMode.CONCURRENT)
public class Tests extends Core {

    @Test
    @DisplayName(value = "Upcoming events check test")
    public void firstTest() {
        globalNavigationSteps.openMainPage();
        globalNavigationSteps.navigateToUpcomingEvents();
        Assertions.assertEquals(upcomingPastEventsSteps.getChosenTabEventCounterValue(),
                upcomingPastEventsSteps.getUpcomingEventsCardsCount(),
                "There are difference while comparing event counter and event cards");
    }

    @Test
    @DisplayName(value = "Event cards check test")
    public void secondTest() {
        globalNavigationSteps.openMainPage();
        globalNavigationSteps.navigateToUpcomingEvents();
        int countOfEventCards = upcomingPastEventsSteps.getUpcomingEventsCardsCount();
        upcomingPastEventsSteps.checkEventCardsElements(countOfEventCards);
    }

    // у меня блока this week нет. Поэтому я проверяю, что все мероприятия проводятся в ЭТОМ месяце
    // то есть соответствуют системному месяцу на текущем пк
    @Test
    @DisplayName(value = "Event date validation test")
    public void thirdTest() {
        globalNavigationSteps.openMainPage();
        globalNavigationSteps.navigateToUpcomingEvents();
        upcomingPastEventsSteps.getAllEventsMonthsAsStringAndCompareWithMonthName(Utils.getSystemMonthInMMMFormat());
    }

    @Test
    @DisplayName(value = "Canada events dates validation")
    public void fourthTest() {
        globalNavigationSteps.openMainPage();
        globalNavigationSteps.navigateToPastEvents();
        upcomingPastEventsSteps.chooseCanada();
        Assertions.assertEquals(upcomingPastEventsSteps.getChosenTabEventCounterValue(),
                upcomingPastEventsSteps.getPastEventsCardsCount(),
                "There are difference while comparing event counter and event cards");
        Assertions.assertTrue(upcomingPastEventsSteps
                .isAllEventsInPastToCurrentDate(Utils.getSystemDateIn_dd_MMM_yyyy()));
    }

    @Test
    @DisplayName(value = "Detailed event info checks")
    public void fifthTest() {
        globalNavigationSteps.openMainPage();
        globalNavigationSteps.navigateToUpcomingEvents();
        upcomingPastEventsSteps.goToFirstEvent();
        singleEventSteps.checkMainEventBlocksAreExists();
    }

    @Test
    @DisplayName(value = "Video Filtration testing")
    public void sixthTest() {
        globalNavigationSteps.openMainPage();
        globalNavigationSteps.navigateToVideoBlock();
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

    @Test
    @DisplayName(value = "Find video reports testing")
    public void seventhTest() {
        globalNavigationSteps.openMainPage();
        globalNavigationSteps.navigateToVideoBlock();
        allVideosPageSteps.findVideo("QA");
        allVideosPageSteps.validateAllVideosContainsText("QA");
    }

    GlobalNavigationSteps globalNavigationSteps = new GlobalNavigationSteps();
    UpcomingPastEventsSteps upcomingPastEventsSteps = new UpcomingPastEventsSteps();
    SingleEventSteps singleEventSteps = new SingleEventSteps();
    AllVideosPageSteps allVideosPageSteps = new AllVideosPageSteps();
}






















