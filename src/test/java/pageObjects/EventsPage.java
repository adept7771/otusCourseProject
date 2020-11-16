package pageObjects;

import org.openqa.selenium.By;

public class EventsPage {

    public By upcomingEventsLink = By.xpath("//span[text()='Upcoming events' and @class='evnt-tab-text desktop']");
    public By pastEventsLink = By.xpath("//span[@class='evnt-tab-text desktop' and text()='Past Events']");
}
