package ru.yandex.test.appmanager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.test.model.Monitor;
import ru.yandex.test.model.NotebookPower;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    public final static int IMPLICITY_WAIT = 10;
    public PrintHelper printHelper;
    public ResultHelper resultHelper;
    public FilterHelper filterHelper;
    public NavigationHelper navigationHelper;
    public GetHelper getHelper;
    protected WebDriver driver;



    public void init() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(IMPLICITY_WAIT, TimeUnit.SECONDS);
        navigationHelper = new NavigationHelper(driver);
        filterHelper = new FilterHelper(driver);
        resultHelper = new ResultHelper(driver);
        printHelper = new PrintHelper(driver, resultHelper, filterHelper);
        getHelper = new GetHelper(driver);

    }

    public void stop() {
        driver.close();
    }

    @Step("Установить фильтры")
    public void setupFilter() {
        filterHelper.setMaxPrice("30000");
        filterHelper.setManufacturer("HP");
        filterHelper.setManufacturer("Lenovo");
        filterHelper.setColor("черный");
        filterHelper.setColor("белый");
        filterHelper.waitForLoad();
        ScreenshotHelper.makeScreenshot(driver);
    }

    @Step("Сравнить два блока \"питание\"")
    public void compare2powers(){
        NotebookPower power1 = getHelper.getPowerPrefs();
        driver.get("https://market.yandex.ru/product--noutbuk-hp-250-g6-4lt10ea-intel-core-i3-7020u-2300-mhz-15-6-1366x768-4gb-500gb-hdd-dvd-rw-amd-radeon-520-wi-fi-bluetooth-windows-10-pro/63945141/spec");
        NotebookPower power2 = getHelper.getPowerPrefs();
        if (power1.equals(power2)) System.out.println("Две секции питания одинаковые");
        else System.out.println("Две секции питания разные");
    }

    @Step("Вывести текст подсказки")
    public void printDescriotion() {
        printHelper.getDescription("Объем кэша L2");
    }

    @Step("Установить параметры из JSON")
    public void findFromJSON() throws  Exception{
        driver.get("https://market.yandex.ru/catalog--monitory/54539/list?hid=91052");
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("Output.json"));
        ArrayList<Monitor> monitors = gson.fromJson(reader, new TypeToken<ArrayList<Monitor>>(){}.getType());
        filterHelper.clearScreenshots();
        filterHelper.setMonitorParameters(monitors);
    }
}
