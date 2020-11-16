package pageObjects;

import org.openqa.selenium.By;

public class AllVideosPage {

    public By moreFiltersButton = By.xpath("//div[@class='evnt-toggle-filters-button evnt-button btn']");

    public By locationDropdown = By.xpath("//div[@id='filter_location']");
    public By belarusInDropdown = By.xpath("//label[@data-value='Belarus']");

    public By languageDropdown = By.xpath("//div[@id='filter_language']");
    public By englishInDropdown = By.xpath("//label[@data-value='ENGLISH']");

    public By categoryDropdown = By.xpath("//div[@id='filter_category']");
    public By testingInDropdown = By.xpath("//label[@data-value='Testing']");

    public By languageEventIcon = By.xpath("//p[@class='language']");
    public By videoEventIcon = By.xpath("//p[@class='video']");

    public By eventHeading = By.xpath("//div[@class='evnt-talk-name']//span");
    public By searchInput = By.xpath("//div[@class='evnt-search-filter']/input[@class='evnt-text-fields form-control evnt-search']");

    public By preloader = By.xpath("//div[@class='evnt-loader']");
}
