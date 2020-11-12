package steps;

import core.Core;
import pageObjects.EventsPage;
import pageObjects.HeaderBlock;

public class GlobalNavigationSteps extends Core {

    public void openMainPage(){
        getUrl("https://events.epam.com/");
    }

    public void navigateToUpcomingEvents(){
        clickWithWait(new HeaderBlock().eventsButton);
        clickWithWait(new EventsPage().upcomingEventsLink);
    }



}
