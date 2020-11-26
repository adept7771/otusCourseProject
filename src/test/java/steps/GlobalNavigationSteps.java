package steps;

import core.Core;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.EventsPage;
import pageObjects.HeaderBlock;


public class GlobalNavigationSteps extends Core {

    public GlobalNavigationSteps(WebDriver webDriver) {
        super(webDriver);
    }

    public void openMainPageAndAllowCookies(){
        getUrl("https://events.epam.com/");
        if(isElementExists(By.xpath("//button[@id='onetrust-accept-btn-handler']"), 2L)){
            clickWithWait(By.xpath("//button[@id='onetrust-accept-btn-handler']"));
        };
    }

    public void navigateToUpcomingEvents(){
        clickWithWait(new HeaderBlock().eventsButton);
        clickWithWait(new EventsPage().upcomingEventsLink);
    }

    public void navigateToPastEvents(){
        clickWithWait(new HeaderBlock().eventsButton);
        clickWithWait(new EventsPage().pastEventsLink);
    }

    public void navigateToVideoBlock(){
        clickWithWait(new HeaderBlock().videoButton);
    }
}
