package pageObjects;

import org.openqa.selenium.By;

public class UpcomingEventsPage {

    public By upcomingEventsCounter = By.xpath("//a[@class='evnt-tab-link nav-link active']//span[@class='evnt-tab-counter evnt-label small white']");
    public By eventDetailsCell = By.xpath("//div[@class='evnt-details-cell online-cell']");


    // для проверки порядка верстки элементов используется конструкция c порядковым номером div, и названием класса
    // /div/div[1]/p[@class='online'] например. А так же /following-sibling:: с указанием класса
    // если порядок верстки div элементов изменится, то такой локатор не будет найден.
    // все остальные локаторы сделаны по той же методике. Они будут зависеть от порядкового номера элемента и названия класса
    // указывающего на элемент
    public By eventLocationCell = By.xpath("//div[contains(@class, 'evnt-events-row')]//div[@class='evnt-card-heading']/div/div[1]/p[@class='online']");
    public By eventLanguageCell = By.xpath("//div[contains(@class, 'evnt-events-row')]//div[@class='evnt-card-heading']/div/div[2]/p[@class='language']");
    public By eventHeadingCell = By.xpath("//div[contains(@class, 'evnt-events-row')]//div[@class='evnt-card-wrapper']//div[@class='evnt-card-cell']/div[1]/h1");
    public By eventDateCell = By.xpath("//div[contains(@class, 'evnt-events-row')]//div[@class='evnt-card-wrapper']//div[@class='evnt-card-cell']/div[2]//span[@class='date']");
    public By eventRegistrationStatus = By.xpath("//div[contains(@class, 'evnt-events-row')]//div[@class='evnt-card-wrapper']//div[@class='evnt-card-cell']/div[2]//span[1]/following-sibling::span[contains(@class, 'status')]");
    public By eventSpeakers = By.xpath("//div[contains(@class, 'evnt-events-row')]//div[@class='evnt-card-wrapper']//div[3 and @class='evnt-card-footer']//div[@class='speakers-wrapper']");
}
