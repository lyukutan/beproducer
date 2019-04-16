package ru.yandex.test.appmanager;

import io.qameta.allure.Step;
import org.openqa.selenium.*;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.util.*;

public class PrintHelper {

    private WebDriver driver;

    private ResultHelper resultHelper;
    private FilterHelper filterHelper;

    public PrintHelper(WebDriver driver, ResultHelper resultHelper, FilterHelper filterHelper) {
        this.filterHelper=filterHelper;
        this.resultHelper=resultHelper;
        this.driver=driver;
    }

    @Step("Вывести самый дорогой и самый дешевый товар, вывести разницу в цене")
    public void printExpAndCheap() throws Exception{
        filterHelper.rateByCoast();
        System.out.print("Самый дешевый ноутбук: ");
        printFirstElement();
        int priceMin = ResultHelper.getElementPrice(resultHelper.getAllResults().get(0));
        filterHelper.rateByCoast();
        System.out.println("Самый дорогой ноутбук: ");
        printFirstElement();
        int priceMax = ResultHelper.getElementPrice(resultHelper.getAllResults().get(0));
        System.out.println("Разница цен: " + (priceMax-priceMin));

    }

    public void printFirstElement() {
        WebElement firstElement = resultHelper.getAllResults().get(0);
        System.out.println(resultHelper.getElementName(firstElement) + " " + ResultHelper.getElementPrice(firstElement));
    }

    @Step("Вывести сортированный список")
    public void printSortedContent() {
        List<WebElement> allElements = resultHelper.getAllResults();
        ArrayList<String> list = new ArrayList<String>();
        for (WebElement b : allElements) {
            list.add(resultHelper.getElementName(b));
        }
        Collections.sort(list);
        System.out.println("Ноутбуки в сортированном списке:");
        for (String s : list) {
            System.out.println(s);
        }
    }

    @Step("Вывести HashMap")
    public void printMap() {
        List<WebElement> allElements = resultHelper.getAllResults();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (WebElement b : allElements) {
            map.put(resultHelper.getElementName(b), ResultHelper.getElementPrice(b));
        }
        System.out.println("Ноутбуки в мапе:");
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            System.out.println(e.getKey() + " цена:" + e.getValue());
        }
    }


    @Step("Вывести текст из подсказки {item}")
    public void getDescription(String item){
        driver.findElement(By.xpath("//span[text()='" + item + "']//span[@role='button']")).click();
        System.out.println(driver.findElement(By.xpath("//div[contains(@class, 'popup_visibility_visible')]//div[@class='n-hint-button__article']")).getText());
    }


}
