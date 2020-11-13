package steps;

import core.Core;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pageObjects.EventsPage;
import pageObjects.HeaderBlock;

@Execution(ExecutionMode.CONCURRENT)
public class GlobalNavigationSteps extends Core {

    public void openMainPage(){
        getUrl("https://events.epam.com/");
    }

    public void navigateToUpcomingEvents(){
        clickWithWait(new HeaderBlock().eventsButton);
        clickWithWait(new EventsPage().upcomingEventsLink);
    }
}
