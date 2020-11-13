package steps;

import core.Core;
import org.junit.jupiter.api.Assertions;
import pageObjects.UpcomingEventsPage;

public class UpcomingEventsSteps extends Core {

    UpcomingEventsPage upcomingEventsPage = new UpcomingEventsPage();

    public int getEventsCardsCount() {
        return (findAllWebElements(upcomingEventsPage.eventDetailsCell) == null) ? 0 :
                findAllWebElements(upcomingEventsPage.eventDetailsCell).size();
    }

    public int getEventCounterValue() {
        return Integer.parseInt(
                getText(upcomingEventsPage.upcomingEventsCounter).replaceAll("[^\\d.]", ""
                        ));
    }

    public void checkEventCardsElements(int eventCardsAmount){
        // Сравнивается количество элементов, исходя из заданного количества карт.
        // попутно будет тестироваться порядок верстки элементов, который заложен в сами локаторы. При изменении верстки
        // локаторы не будут найдены
        Assertions.assertEquals(eventCardsAmount, findAllWebElements(upcomingEventsPage.eventLocationCell).size());
        Assertions.assertEquals(eventCardsAmount, findAllWebElements(upcomingEventsPage.eventLanguageCell).size());
        Assertions.assertEquals(eventCardsAmount, findAllWebElements(upcomingEventsPage.eventHeadingCell).size());
        Assertions.assertEquals(eventCardsAmount, findAllWebElements(upcomingEventsPage.eventDateCell).size());
        Assertions.assertEquals(eventCardsAmount, findAllWebElements(upcomingEventsPage.eventRegistrationStatus).size());
        Assertions.assertEquals(eventCardsAmount, findAllWebElements(upcomingEventsPage.eventSpeakers).size());
    }
}
