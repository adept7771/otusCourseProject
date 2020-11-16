package pageObjects;

import org.openqa.selenium.By;

public class HeaderBlock {

    public By eventsButton = By.xpath("//a[@href='/events' and @class='nav-link']");
    public By videoButton = By.xpath("//a[@class='nav-link' and text()='Video']");
}
