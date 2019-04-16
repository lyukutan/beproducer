package ru.cinemico.test.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    public final static int IMPLICITY_WAIT = 10;

    public AuthorizationPage authorizationPage;
    public MainPage mainPage;

    protected WebDriver driver;



    public void init() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(IMPLICITY_WAIT, TimeUnit.SECONDS);

        authorizationPage = new AuthorizationPage(driver);
        mainPage = new MainPage(driver);

        driver.get("https://frontend.beproducer.pro/main");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void stop() {
        driver.close();
    }

}
