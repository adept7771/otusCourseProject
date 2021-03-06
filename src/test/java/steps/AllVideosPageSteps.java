package steps;

import core.Core;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.AllVideosPage;
import pageObjects.SingleVideoPage;

import java.util.ArrayList;


public class AllVideosPageSteps extends Core {

    AllVideosPage allVideosPage = new AllVideosPage();
    SingleVideoPage singleVideoPage = new SingleVideoPage();

    public AllVideosPageSteps(WebDriver webDriver) {
        super(webDriver);
    }

    public void openCloseMoreFilters(){
        clickWithWait(allVideosPage.moreFiltersButton);
    }

    public void chooseVideoCategory(String categoryName){
        clickWithWait(allVideosPage.categoryDropdown);
        clickWithWait(By.xpath("//label[@data-value='" + categoryName + "']"));
    }

    public void chooseLocation(String locationName){
        clickWithWait(allVideosPage.locationDropdown);
        clickWithWait(By.xpath("//label[@data-value='" + locationName +"']"));
    }

    public void chooseLanguage(String languageName){
        clickWithWait(allVideosPage.languageDropdown);
        clickWithWait(By.xpath("//label[@data-value='" + languageName + "']"));
    }

    public int countNumberOfVideoHeadings(){
        return findAllWebElements(allVideosPage.eventHeading).size();
    }

    public int countNumberOfLanguageIcons(){
        return findAllWebElements(allVideosPage.languageEventIcon).size();
    }

    public void checkEachVideoOnPage(){
        int videoNumbers = findAllWebElements(allVideosPage.eventHeading).size();
        for(int i=0 ; i<videoNumbers; i++){
            AllVideosPage allVideosPage = new AllVideosPage();
            SingleVideoPage singleVideoPage = new SingleVideoPage();
            findAllWebElements(allVideosPage.eventHeading).get(i).click();
            Assertions.assertEquals(getText(singleVideoPage.language).toLowerCase(), "english");
            Assertions.assertTrue(getText(singleVideoPage.location).toLowerCase().contains("belarus"));

            int categoryMatches = 0;
            for(WebElement categoryName : findAllWebElements(singleVideoPage.category)){
                String categoryText = categoryName.getText().toLowerCase();
                if(categoryText.contains("qa") || categoryText.contains("test")){
                    categoryMatches += 1;
                }
            }
            Assertions.assertTrue(categoryMatches > 0,
                    "Categories of video event doesn't contains QA themes");
            goBack();
        }
    }

    public void findVideo(String nameForVideo){
        clickWithWait(allVideosPage.searchInput);
        sendKeys(allVideosPage.searchInput, nameForVideo);
        waitUntilNotExists(allVideosPage.preloader, 10L);
    }

    public void validateAllVideosContainsText(String textToCheck){
        ArrayList<WebElement> listOfVideos = (ArrayList<WebElement>) findAllWebElements(allVideosPage.eventHeading);
        for(WebElement webElement : listOfVideos){
            Assertions.assertTrue(webElement.getText().toLowerCase().contains(textToCheck.toLowerCase()),
                    "Video event doesn't contains mentioned text: " + webElement.getText().toLowerCase());
        }
    }
}
