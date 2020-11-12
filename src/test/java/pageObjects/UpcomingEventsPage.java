package pageObjects;

import org.openqa.selenium.By;

public class UpcomingEventsPage {

    public By upcomingEventsCounter = By.xpath("//a[@class='evnt-tab-link nav-link active']//span[@class='evnt-tab-counter evnt-label small white']");

    public By eventDetailsCell = By.xpath("//div[@class='evnt-details-cell online-cell']");
}
