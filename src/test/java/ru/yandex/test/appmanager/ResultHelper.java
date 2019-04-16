package ru.yandex.test.appmanager;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResultHelper {
    private WebDriver driver;

    public ResultHelper(WebDriver driver) {
        this.driver=driver;
    }

    @Step("Определить цену товара")
    public static int getElementPrice(WebElement element) {
        return Integer.parseInt(element.findElement(By.xpath(".//div[@class='n-snippet-card2__main-price-wrapper']/a/div")).getText().replaceAll("[^\\d.]", ""));
    }

    @Step("Получить все товары со страницы")
    public List<WebElement> getAllResults() {
        return driver.findElements(By.xpath("//div[contains(@class, 'n-snippet-list')]/div[contains(@class,'n-snippet-card2')]"));
    }

    @Step("Определить имя товара")
    public String getElementName(WebElement element) {
        return element.findElement(By.xpath(".//div[@class='n-snippet-card2__title']/a")).getText();
    }
}
