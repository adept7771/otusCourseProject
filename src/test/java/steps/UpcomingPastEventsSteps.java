package steps;

import core.Core;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.UpcomingPastEventsPage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class UpcomingPastEventsSteps extends Core {

    UpcomingPastEventsPage upcomingPastEventsPage = new UpcomingPastEventsPage();

    public UpcomingPastEventsSteps(WebDriver webDriver) {
        super(webDriver);
    }

    public int getUpcomingEventsCardsCount() {
        return (findAllWebElements(upcomingPastEventsPage.eventDetailsCell) == null) ? 0 :
                findAllWebElements(upcomingPastEventsPage.eventDetailsCell).size();
    }

    public int getPastEventsCardsCount() {
        return (findAllWebElements(upcomingPastEventsPage.eventHeadingCell) == null) ? 0 :
                findAllWebElements(upcomingPastEventsPage.eventHeadingCell).size();
    }

    public int getChosenTabEventCounterValue() {
        return Integer.parseInt(
                getText(upcomingPastEventsPage.currentEventTapEventsCounter).replaceAll("[^\\d.]", ""
                ));
    }

    public void checkEventCardsElements(int eventCardsAmount) {
        // Сравнивается количество элементов, исходя из заданного количества карт.
        // попутно будет тестироваться порядок верстки элементов, который заложен в сами локаторы. При изменении верстки
        // локаторы не будут найдены
        Assertions.assertEquals(eventCardsAmount, findAllWebElements(upcomingPastEventsPage.eventLocationCell).size());
        Assertions.assertEquals(eventCardsAmount, findAllWebElements(upcomingPastEventsPage.eventLanguageCell).size());
        Assertions.assertEquals(eventCardsAmount, findAllWebElements(upcomingPastEventsPage.eventHeadingCell).size());
        Assertions.assertEquals(eventCardsAmount, findAllWebElements(upcomingPastEventsPage.eventDateCell).size());
        Assertions.assertEquals(eventCardsAmount, findAllWebElements(upcomingPastEventsPage.eventRegistrationStatus).size());
        Assertions.assertEquals(eventCardsAmount, findAllWebElements(upcomingPastEventsPage.eventSpeakers).size());
    }

    public void goToFirstEvent() {
        clickWithWait(upcomingPastEventsPage.eventHeadingCell);
    }

    public void getAllEventsMonthsAsStringAndCompareWithMonthName(String monthNameInMMMFormat) {
        for (WebElement webElement : findAllWebElements(upcomingPastEventsPage.eventDateCell)) {
            Assertions.assertEquals(
                    webElement.getText().replaceAll("[^A-Za-z]+", ""),
                    monthNameInMMMFormat,
                    "Month of element is not equals to current system month");
        }
    }

    public boolean isAllEventsInPastToCurrentDate(String incomingDateString) {
        for (WebElement webElement : findAllWebElements(upcomingPastEventsPage.eventDateCell)) {
            String webElementText = webElement.getText();
            Date date = null, incomingDate = null;

            // у события может быть дата начала и конца. Поэтому, если такая дата есть, мы берем дату окончания
            // например если сейчас 13 число, дата начала 10, но конца 15, то первая дата будет устаревшая, но вторая
            // нет. Если дата окончания события выше текущей то возвращать false
            // Кроме того если дата окончания равна текущей дате, то так же возвращать false. Так как событие еще идет
            // проверил. Одинаковые даты в date.after(date) выдают false

            SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy", Locale.US);

            try {
                incomingDate = formatter.parse(incomingDateString);
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }

            if (webElementText.contains("-")) {
                webElementText = webElementText.substring(webElementText.indexOf("-")+2);
            }
            try {
                date = formatter.parse(webElementText);
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }
            return incomingDate.after(date);
        }
        return false;
    }

    public void chooseCanada() {
        clickWithWait(upcomingPastEventsPage.locationDropdown);
        clickWithWait(upcomingPastEventsPage.canadaInDropdown);
        clickWithWait(upcomingPastEventsPage.allEventsH3Header);
    }
}
