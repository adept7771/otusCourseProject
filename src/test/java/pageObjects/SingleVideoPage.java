package pageObjects;

import org.openqa.selenium.By;

public class SingleVideoPage {

    public By location = By.xpath("//div[@class='evnt-talk-details location evnt-now-past-talk']//span");
    public By language = By.xpath("//div[@class='evnt-talk-details language evnt-now-past-talk']//span");
    public By category = By.xpath("//div[@class='evnt-topic evnt-talk-topic small gray']//label");

}
