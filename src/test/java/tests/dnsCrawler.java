package tests;

import core.Core;
import core.JunitRunner;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import steps.GlobalNavigationSteps;
import steps.SingleEventSteps;
import steps.UpcomingPastEventsSteps;
import steps.AllVideosPageSteps;
import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

@Execution(ExecutionMode.CONCURRENT)
public class dnsCrawler extends JunitRunner {

    boolean orderIsAlreadyDone = false;

    @org.junit.jupiter.api.Test
    public void startCrawler() throws IOException, InterruptedException {

        String login = "metallfun@gmail.com";
        String password = "ym-cut-haas";
        String cardName = "9900Gt";
        boolean isItTrueOrder = false;
        int maxCardPrice = 50000;

        webDriver.get("https://www.dns-shop.ru/catalog/17a89aab16404e77/videokarty/?order=2&groupBy=none&f[19n]=c0l&f[v7]=kncm0&stock=0");
        Core core = new Core(webDriver);

        core.clickWithWait(By.xpath("//button[@data-role='login-button']"));
        core.clickWithWait(By.xpath("//div[@class='block-other-login-methods__password-caption']"));
        core.sendKeys(By.xpath("(//input[@class='base-ui-input-row__input base-ui-input-row__input_with-icon'])[1]"),
                login);
        core.sendKeys(By.xpath("(//input[@class='base-ui-input-row__input base-ui-input-row__input_with-icon'])[2]"),
                password);
        core.clickWithWait(By.xpath("//div[@class='base-ui-button base-ui-button_brand base-ui-button_big-flexible-width']"));
        core.waitStatic(5000);
        core.clickWithWait(By.xpath("//h1[@class='title']"));
        core.clickWithWait(By.xpath("//i[@class='location-icon']"));
        core.clickWithWait(By.xpath("//span[@data-role='big-cities']//a[text()='Санкт-Петербург']"));

        while (!orderIsAlreadyDone) {
            try {
                core.isElementVisible(By.xpath("//button[@class='pagination-widget__show-more-btn']"), 10L);

                if (core.isElementExists(
                        By.xpath("//div[@class='product-info__title-link']//a[contains(text(), '" + cardName + "')]" +
                                "/ancestor::div[@class='n-catalog-product ui-button-widget']//button[text()='Купить']"),
                        5L)) {
                    System.out.println("\n Найдены удовлетворяющие поиску элементы. Перебираю. \n");

                    ArrayList<WebElement> cardsWithMatchingNamesAndAvailableForOrder =
                            (ArrayList<WebElement>) core.findAllWebElements(
                                    By.xpath("//div[@class='product-info__title-link']//a[contains(text(), '" + cardName + "')]/ancestor::div[@class='n-catalog-product ui-button-widget']//button[text()='Купить']/ancestor::div[@class='n-catalog-product ui-button-widget']//div[@class='product-info__title-link']"));

                    System.out.println("\n Найдено " + cardsWithMatchingNamesAndAvailableForOrder.size() + " элементов! А именно: \n");
                    cardsWithMatchingNamesAndAvailableForOrder.forEach(cardFoundName -> {
                        System.out.println(cardFoundName.getText());
                    });

                    System.out.println("\n");

                    ArrayList<WebElement> matchingCardsPrices =
                            (ArrayList<WebElement>) core.findAllWebElements(
                                    By.xpath("//div[@class='product-info__title-link']//a[contains(text(), '" + cardName + "')]" +
                                            "/ancestor::div[@class='n-catalog-product ui-button-widget']" +
                                            "//button[text()='Купить']/ancestor::div[@data-id='product']" +
                                            "//div[@class='product-min-price__current']"));

                    ArrayList<WebElement> matchingCardsBuyButtons =
                            (ArrayList<WebElement>) core.findAllWebElements(
                                    By.xpath("//div[@class='product-info__title-link']//a[contains(text(), '5700')]/ancestor::div[@class='n-catalog-product ui-button-widget']//button[text()='Купить']"));

                    System.out.println("\n");

                    for (int i = 0; i < cardsWithMatchingNamesAndAvailableForOrder.size(); i++) {
                        System.out.println(cardsWithMatchingNamesAndAvailableForOrder.get(i).getText() + " стоит: "
                                + matchingCardsPrices.get(i).getText());
                        if (Integer.parseInt(matchingCardsPrices.get(i).getText().replaceAll("[^\\d.]", "")) > maxCardPrice) {
                            System.out.println("Цена больше " + maxCardPrice + " р. заказ осуществлен не будет. Перехожу к следующему экземпляру");

                        } else {
                            System.out.println("Цена меньше " + maxCardPrice + " р. переходим к осуществлению заказа");

                            matchingCardsBuyButtons.get(i).click();
                            core.clickWithWait(By.xpath("//span[@class='cart-link__icon']"));
                            core.clickWithWait(By.xpath("//button[@class='one-click-button']"));

                            if (isItTrueOrder) {
                                core.clickWithWait(By.xpath("//button[@class='one-click-init-form-btn-buy']"));
                                orderIsAlreadyDone = true;
                                System.out.println("\n Покупка успешно завершена! \n");
                            } else {
                                System.out.println("\n Мы можем совершить покупку. Но сейчас не в боевом режиме. Ожидаем. \n");
                                core.waitStatic(500000);
                            }
                        }
                    }

                } else {
                    System.out.println("Нет удовлетворяющих поиску элементов. Жду. И ищу снова.");
                    core.waitStatic(5000);
                    webDriver.get("https://www.dns-shop.ru/catalog/17a89aab16404e77/videokarty/?order=2&groupBy=none&f[19n]=c0l&f[v7]=kncm0&stock=0");
                    core.waitStatic(5000);
                }
            }
            catch (Exception e){
                System.out.println("Непредвиденный программный сбой. Произошла какая-то хуйня. Ждем и повторяем весь цикл снова.");
                core.waitStatic(5000);
                webDriver.get("https://www.dns-shop.ru/catalog/17a89aab16404e77/videokarty/?order=2&groupBy=none&f[19n]=c0l&f[v7]=kncm0&stock=0");
                core.waitStatic(5000);
            }
        }
    }
}






















