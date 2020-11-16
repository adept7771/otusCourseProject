package pageObjects;

import org.openqa.selenium.By;

public class SingleEventPage {

    public By registerButton = By.xpath("//button[@class='evnt-button btn sky reg-button attend']");
    public By dateButton = By.xpath("//li[@class='evnt-day-tab active']");
    public By agendaWrapper = By.xpath("//div[@class='evnt-agenda-wrapper']");
}
