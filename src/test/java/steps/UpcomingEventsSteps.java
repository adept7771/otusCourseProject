package steps;

import core.Core;
import pageObjects.EventsPage;
import pageObjects.HeaderBlock;
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
}
