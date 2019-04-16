package ru.yandex.test.appmanager;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.test.model.NotebookPower;

import java.util.HashMap;
import java.util.List;

public class GetHelper {

    private WebDriver driver;

    public GetHelper(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Считать раздел питание")
    public NotebookPower getPowerPrefs(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Время работы от аккумулятора", null);
        map.put("Тип аккумулятора", null);
        map.put("Количество ячеек аккумулятора",null);
        map.put("Емкость аккумулятора",null);

        List<WebElement> list = driver.findElements(By.xpath("//div[./h2[.='Питание']]/dl"));
        for (WebElement e : list){
            map.put(e.findElement(By.xpath("./dt/span")).getText(), e.findElement(By.xpath("./dd/span")).getText());
        }

        return new NotebookPower(map.get("Тип аккумулятора"),
                map.get("Количество ячеек аккумулятора") != null ?
                        Integer.parseInt(map.get("Количество ячеек аккумулятора")) :
                null,
                map.get("Емкость аккумулятора") != null ?
                        Double.parseDouble(map.get("Емкость аккумулятора").replaceAll("[^\\d.]", "")) :
                null,
                map.get("Время работы от аккумулятора")
        );
    }
}
